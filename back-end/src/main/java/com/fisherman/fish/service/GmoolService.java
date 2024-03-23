package com.fisherman.fish.service;

import com.fisherman.fish.dto.FileDTO;
import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.GmoolEntity;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.FileRepository;
import com.fisherman.fish.repository.GmoolRepository;
import com.fisherman.fish.repository.MemberRepository;
import com.fisherman.fish.utility.FileUtil;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GmoolService {
    private final GmoolRepository gmoolRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    private final Map<Integer, Long> pinMap = new HashMap<>();
    private static int gmoolCount = 0; // test

    //@PostConstruct
    @Transactional
    public void init(){
        // pinMap 초기화
        List<GmoolDTO> gmools = findAll();
        for(GmoolDTO g : gmools){
            int key = g.getPinNumber();
            long val = g.getCreatedTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            System.out.println(key + ", " + val);
            pinMap.put(key, val);
        }
    }

    public int generatePinNumber(int dueMinute) {
        // 랜덤 핀 생성
        int candidatePin;
        boolean isPinValid;
        do {
            isPinValid = false;
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            candidatePin = random.nextInt(999999);

            Long expireDate = pinMap.get(candidatePin);
            if(expireDate == null || expireDate < System.currentTimeMillis())
                // pin이 할당되지 않았거나, 할당되었지만 유효기간이 지난 경우 반복문 break
                isPinValid = true;

        } while (!isPinValid);

        pinMap.put(candidatePin, System.currentTimeMillis() + (long) dueMinute * 6000);
        return candidatePin;
    }

    @Transactional
    public GmoolDTO findById(Long gid) {
        // id에 해당하는 그물 반환
        Optional<GmoolEntity> optionalGmoolEntity = gmoolRepository.findById(gid);
        if(optionalGmoolEntity.isEmpty()) return null; // 그물이 존재하지 않는 경우 null 반환
        // Member 엔티티도 같이 검색 (아직 Proxy 객체로 채워져있음)
        // DTO로 변환하여 반환
        return GmoolDTO.toGmoolDTO(optionalGmoolEntity.get());
    }

    public List<GmoolDTO> findByUserId(String uid){
        // TODO : uid 예외처리?
        // 해당 user가 게시한 모든 그물 반환
        Optional<List<GmoolEntity>> optionalGmoolEntities = gmoolRepository.findByGmoolOwner_id(uid);
        if(optionalGmoolEntities.isEmpty()) return null;  // 게시한 그물이 존재하지 않는 경우 null 반환 (그물 게시 X or user 존재 X)
        // DTO로 변환하여 반환
        List<GmoolEntity> gmoolEntities = optionalGmoolEntities.get();
        List<GmoolDTO> gmoolDTOs = new ArrayList<>();
        for(GmoolEntity ge : gmoolEntities){
            gmoolDTOs.add(GmoolDTO.toGmoolDTO(ge));
        }
        return gmoolDTOs;
    }


    public MultipartFile getQRCode(GmoolDTO gmoolDTO){
        return null;
    }

    public MultipartFile getQRCode(String url){
        return null;
    }

    /**
     * save():
     * 해당 그물을 저장한다.
     * 그물에 pin번호를 부여하고 각 파일의 고유파일명을 할당한 후,
     * 그물을 DB에 저장하고 해당 그물dto를 반환한다
     */
    @Transactional
    public GmoolDTO save(GmoolDTO gmoolDTO) {
        // 해당 그물을 저장한다.
        // 받은 정보로 그물 생성
        //  1. pin 번호 생성
        //  2. 링크 생성
        //  3. 각 파일의 고유파일명 생성
        //  4. 해당 그물 저장
        //  전부 묶어서 반환
        // 해당 그물 저장

        // TODO: 로그인된 경우 유저 id도 저장

        System.out.println("GmoolService: [save() called]"); // test
        // 1. pin 번호 생성
        int pinNumber = generatePinNumber(gmoolDTO.getDueMinute());
        gmoolDTO.setPinNumber(pinNumber);
        System.out.println("- pinNumber " + pinNumber + " created."); // test

        // 2. 링크 생성 -> 생략 (url에 pin넘버만 붙이면 됨)

        // 3. 고유파일명 생성
        //  - 그물 인덱스(gmoolCount)를 파일명 앞에 붙인다.
        // TODO: 경로는 다르지만 파일명이 같은 경우 처리
        //  - 이거 프론트 상에서 알아서 걸러지나?  filename(1) filename(2) 이런 식으로?
        //  - 되는지 안되는지 확인하고, 안되면 예외처리 필요
        List<FileDTO> fileDTOS = gmoolDTO.getFileDTOList();
        for(FileDTO fd : fileDTOS) {
            String fileName = FileUtil.createStoreFilename(fd.getOriginalFileName(), String.valueOf(gmoolCount));
            System.out.println("- File '" + fd.getOriginalFileName() + "'will be saved as '" + fileName + "'"); // test
            fd.setStoredFileName(fileName);
        }
        // 파일을 서버에 저장한다.
        String filename = null, savePath = null; // exception 출력용
        try{
            for(FileDTO fd : fileDTOS){
                filename = fd.getOriginalFileName();
                MultipartFile file = fd.getFile();
                savePath = FileUtil.getFinalPath(fd.getStoredFileName());
                file.transferTo(new File(savePath));
                System.out.println("'" + filename + "' saved in '" + savePath + "'");
            }
        } catch(Exception e){
            System.out.println("- EXCEPTION: Exception occurred while saving '" + filename + "'"); // test
            e.printStackTrace();
        }
        
        // 4. 해당 그물 저장
        // - 멤버 Entity를 받아온다 (유효한 계정인지 확인)
        if(gmoolDTO.getUserId() != null){
            // TODO : 멤버가 익명일 때, dto에 적히는 값이 null인지 ""인지 확인 필요
            // 익명이 아닌 경우
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(gmoolDTO.getUserId());
            if(optionalMemberEntity.isEmpty()) {
                // 멤버가 존재하지 않는 경우
                System.out.println("- EXCEPTION: member '" + gmoolDTO.getUserId() + "' doesn't exist."); // test
                return null;
            }
        }
        // - 파일 Entity를 생성한다
        List<FileEntity> fileEntities = new ArrayList<>();
        for(FileDTO fd : fileDTOS){
            fileEntities.add(new FileEntity(
                    fd.getStoredFileName(),
                    fd.getOriginalFileName(),
                    fd.getFileSize(),
                    null)
            );
        }
        // - 그물 Entity를 생성하여 파일 Entity와 연결한다.
        GmoolEntity gmoolEntity = GmoolEntity.toGmoolEntity(gmoolDTO);
        for(FileEntity fe : fileEntities){
            fe.setGmoolEntity(gmoolEntity);
            gmoolEntity.addFileEntity(fe);
        }
        // - 그물 Entity와 각 파일 Entity를 저장한다.
        gmoolCount++; // test
        GmoolEntity savedEntity = gmoolRepository.save(gmoolEntity);
        for(FileEntity fe : gmoolEntity.getFileEntityList()){
            fileRepository.save(fe);
        }
        System.out.println("GmoolService: gmool saved. (id: " + savedEntity.getId() + ", pinNumber: " + savedEntity.getPinNumber());
        // 저장된 그물 dto를 반환한다.
        gmoolCount++;
        return GmoolDTO.toGmoolDTO(savedEntity);
    }

    @Transactional
    public List<GmoolDTO> findAll() {
        // 모든 그물 return
        List<GmoolEntity> gmoolEntities = gmoolRepository.findAll();
        List<GmoolDTO> gmoolDTOs = new ArrayList<>();
        for(GmoolEntity g : gmoolEntities)
            gmoolDTOs.add(GmoolDTO.toGmoolDTO(g));
        return gmoolDTOs;
    }
}
