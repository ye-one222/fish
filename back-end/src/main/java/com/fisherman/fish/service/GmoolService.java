package com.fisherman.fish.service;

import com.fisherman.fish.dto.FileDTO;
import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.dto.MemberDTO;
import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.GmoolEntity;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.FileRepository;
import com.fisherman.fish.repository.GmoolRepository;
import com.fisherman.fish.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GmoolService {
    private final GmoolRepository gmoolRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    private static String fileSavePath = "";
    private static int gmoolCount = 0; // test
    private static int pinNumber = 0;
    public static int generatePinNumber() {
        // TODO : 핀번호 관리
        // 랜덤으로 생성
        // 현재 사용 중인 pin번호 : hashmap에 관리?
        // 해당 번호가 찬 경우 : 1, 2, 4, 8, ... 순으로 커지도록?
        // -> 근데 이러면 자기 번호에서 1을 더하면 바로 다른 사람 께 보이니까 비효율적이지 않나??
        return pinNumber++;
    }

    public GmoolDTO findById(Long gid) {
        // id에 해당하는 그물 반환
        Optional<GmoolEntity> optionalGmoolEntity = gmoolRepository.findById(gid);
        if(optionalGmoolEntity.isEmpty()) return null; // 그물이 존재하지 않는 경우 null 반환
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

        // 1. pin 번호 생성
        int pinNumber = GmoolService.generatePinNumber();
        gmoolDTO.setPinNumber(pinNumber);

        // 2. 링크 생성 -> 생략 (url에 pin넘버만 붙이면 됨)

        // 3. 고유파일명 생성
        //  - 그물 인덱스(gmoolCount)를 파일명 앞에 붙인다.
        // TODO: 경로는 다르지만 파일명이 같은 경우 처리
        //  - 이거 프론트 상에서 알아서 걸러지나?  filename(1) filename(2) 이런 식으로?
        //  - 되는지 안되는지 확인하고, 안되면 예외처리 필요
        List<FileDTO> fileDTOs = gmoolDTO.getFileDTOList();
        for(FileDTO fd : fileDTOs) {
            String fileName = gmoolCount + fd.getOriginalFileName();
            System.out.println("GmoolService: File '" + fd.getOriginalFileName() + "'will be saved as '" + fileName + "'");
            fd.setStoredFileName(fileName);
        }
        // 파일을 서버에 저장한다.
        String fileName = "";
        try{
            for(FileDTO fd : fileDTOs){
                fileName = fd.getOriginalFileName();
                MultipartFile file = fd.getFile();
                String savePath = GmoolService.fileSavePath + fileName;
                file.transferTo(new File(savePath));
            }
        } catch(Exception e){
            System.out.println("GmoolService : Exception occurred while saving '" + fileName + "'");
            e.printStackTrace();
        }
        
        // 4. 해당 그물 저장
        // - 멤버 Entity를 받아온다
        if(gmoolDTO.getUserId() != null){
            // TODO : 멤버가 익명일 때, dto에 적히는 값이 null인지 ""인지 확인 필요
            // 익명이 아닌 경우
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(gmoolDTO.getUserId());
            if(optionalMemberEntity.isEmpty()) {
                // 멤버가 존재하지 않는 경우
                System.out.println("GmoolService: member '" + gmoolDTO.getUserId() + "' doesn't exist.");
                return null;
            }
        }
        // - 파일 Entity를 생성한다
        List<FileEntity> fileEntities = new ArrayList<>();
        for(FileDTO fd : fileDTOs){
            fileEntities.add(new FileEntity(
                    fd.getStoredFileName(),
                    fd.getOriginalFileName(),
                    fd.getFileSize(),
                    null)
            );
        }
        // - 그물 Entity를 생성한다.
        GmoolEntity gmoolEntity = GmoolEntity.toGmoolEntity(gmoolDTO);
        // - 파일 Entity와 그물 Entity를 연결한다.
        for(FileEntity fe : fileEntities){
            fe.setGmoolEntity(gmoolEntity);
            gmoolEntity.addFileEntity(fe);
        }
        // - 그물 Entity를 저장하고, dto를 반환한다
        gmoolCount++; // test
        GmoolEntity savedEntity = gmoolRepository.save(gmoolEntity);
        System.out.println("GmoolService: gmool saved. (id: " + savedEntity.getId() + ", pinNumber: " + savedEntity.getPinNumber());
        return GmoolDTO.toGmoolDTO(savedEntity);  // 생성된 Id 반환
    }

    public List<GmoolDTO> findAll() {
        // 모든 그물 return
        List<GmoolEntity> gmoolEntities = gmoolRepository.findAll();
        List<GmoolDTO> gmoolDTOs = new ArrayList<>();
        for(GmoolEntity g : gmoolEntities)
            gmoolDTOs.add(GmoolDTO.toGmoolDTO(g));
        return gmoolDTOs;
    }
}
