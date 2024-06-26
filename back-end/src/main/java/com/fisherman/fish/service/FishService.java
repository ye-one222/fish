package com.fisherman.fish.service;

import com.fisherman.fish.dto.FileDTO;
import com.fisherman.fish.dto.FishDTO;
import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.FishEntity;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.FileRepository;
import com.fisherman.fish.repository.FishRepository;
import com.fisherman.fish.repository.MemberRepository;
import com.fisherman.fish.utility.FileUtil;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishService {
    private final FishRepository fishRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final FileUtil fileUtil;


    private final Map<Integer, Long> pinMap = new HashMap<>();
    private static int gmoolCount = 0; // test

    private static int fishCount = 0; // test
    private static int pinNumber = 0;

    @PostConstruct
    @Transactional
    public void init(){
        // pinMap 초기화
        List<FishDTO> fishes = findAll();
        for(FishDTO f : fishes){
            int key = f.getPinNumber();
            long val = f.getCreatedTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
    public FishDTO findById(Long gid) {
        // id에 해당하는 fish 반환
        Optional<FishEntity> optionalFishEntity = fishRepository.findById(gid);
        if(optionalFishEntity.isEmpty()) return null; // fish가 존재하지 않는 경우 null 반환
        // Member 엔티티도 같이 검색 (아직 Proxy 객체로 채워져있음)
        // DTO로 변환하여 반환
        return FishDTO.toFishDTO(optionalFishEntity.get());
    }

    public List<FishDTO> findByUserId(String uid){
        // 해당 user가 게시한 모든 그물 반환
        Optional<List<FishEntity>> optionalFishEntities = fishRepository.findByFishOwner_id(uid);
        if(optionalFishEntities.isEmpty()) return null;  // 게시한 그물이 존재하지 않는 경우 null 반환 (그물 게시 X or user 존재 X)
        // DTO로 변환하여 반환
        List<FishEntity> fishEntities = optionalFishEntities.get();
        List<FishDTO> fishDTOS = new ArrayList<>();
        for(FishEntity ge : fishEntities)
            fishDTOS.add(FishDTO.toFishDTO(ge));
        return fishDTOS;
    }

    public FishDTO findByPinNumber(String pinNumber){
        // int로 파싱해서 넘겨줌
        try {
            return findByPinNumber(Integer.parseInt(pinNumber));
        } catch (NumberFormatException e){
            System.out.println("findByPinNumber - '" + pinNumber + "' doesn't match the rule");
            return null;
        }
    }

    @Transactional
    public FishDTO findByPinNumber(int pinNumber) {
        // 핀번호로 fish 가져오기
        //   핀번호가 겹치는 fish가 존재하는 경우,
        //   가장 만료기간이 오래 남은 fish만 유효하므로 그걸 반환한다
        if(pinNumber < 0 || pinNumber > 999999){
            System.out.println("findByPinNumber - '" + pinNumber + "' doesn't match the rule");
            return null;
        }
        List<FishEntity> fishes = fishRepository.findByPinNumberOrderByExpireDateDesc(pinNumber);
        System.out.println("findByPinNumber : pin " + pinNumber + " -> " + fishes);
        if(fishes.isEmpty()){
            return null;
        }
        List<FishDTO> fishDTOs = new ArrayList<>();
        fishes.forEach(fishEntity -> {
            fishDTOs.add(FishDTO.toFishDTO(fishEntity));
        });
        // 가장 만료기간 오래 남은 fish 반환
        return fishDTOs.get(0);
    }

    /**
     * save():
     * 해당 그물을 저장한다.
     * 그물에 pin번호를 부여하고 각 파일의 고유파일명을 할당한 후,
     * 그물을 DB에 저장하고 해당 그물dto를 반환한다
     */
    @Transactional
    public FishDTO save(FishDTO fishDTO) {
        // 해당 그물을 저장한다.
        // 받은 정보로 그물 생성
        //  1. pin 번호 생성
        //  2. 각 파일의 고유파일명 생성
        //  3. 해당 그물 저장
        //  전부 묶어서 반환
        // 해당 그물 저장

        // TODO: 로그인된 경우 유저 id도 저장

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<GrantedAuthority> authorities = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        System.out.println("FishService: [save() called]"); // test
        System.out.println("FishService: username - " + username + ", role - " + authorities.get(0));

        // 1. pin 번호 생성
        int pinNumber = generatePinNumber(fishDTO.getDueMinute());
        fishDTO.setPinNumber(pinNumber);
        System.out.println("- pinNumber " + pinNumber + " created."); // test

        // 2. 고유파일명 생성
        //  - 현재 시간을 파일명 앞에 붙인다
        // TODO: 경로는 다르지만 파일명이 같은 경우 처리
        //  - 이거 프론트 상에서 알아서 걸러지나?  filename(1) filename(2) 이런 식으로?
        //  - 되는지 안되는지 확인하고, 안되면 예외처리 필요
        List<FileDTO> fileDTOS = fishDTO.getFileDTOList();
        for(FileDTO fd : fileDTOS) {
            String fileName = fileUtil.createStoreFilename(fd.getOriginalFileName(), String.valueOf(fishCount));
            System.out.println("- File '" + fd.getOriginalFileName() + "'will be saved as '" + fileName + "'"); // test
            fd.setStoredFileName(fileName);
        }
        // 파일을 서버에 저장한다.
        String filename = null, savePath = null; // exception 출력용
        try{
            for(FileDTO fd : fileDTOS){
                filename = fd.getOriginalFileName();
                MultipartFile file = fd.getFile();
                savePath = fileUtil.getFinalPath(fd.getStoredFileName());
                file.transferTo(new File(savePath));
                System.out.println("'" + filename + "' saved in '" + savePath + "'");
            }
        } catch(Exception e){
            System.out.println("- EXCEPTION: Exception occurred while saving '" + filename + "'"); // test
            e.printStackTrace();
        }
        
        // 3. 해당 fish 저장
        // - 파일 Entity를 생성한다
        List<FileEntity> fileEntities = new ArrayList<>();
        for(FileDTO fd : fileDTOS){
            FileEntity fe = new FileEntity(
                    fd.getStoredFileName(),
                    fd.getOriginalFileName(),
                    fd.getFileSize(),
                    null);
            System.out.println("Created file entity '" + fe + "'"); // test
            fileEntities.add(fe);
        }
        // - fish Entity를 생성하여 파일 Entity와 연결한다.
        FishEntity fishEntity = FishEntity.toFishEntity(fishDTO);
        System.out.println("Created fish entity '" + fishEntity.getFishName() + "'"); // test
        for(FileEntity fe : fileEntities){
            fe.setFishEntity(fishEntity);
            fishEntity.addFileEntity(fe);
        }
        // - 게시자가 존재하는 경우 멤버 Entity를 fish Entity와 연결한다
        if(fishDTO.getUserId() != null){
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(fishDTO.getUserId());
            optionalMemberEntity.ifPresent( (fishOwner) -> fishEntity.setFishOwner(fishOwner) );
            System.out.println("Fetched fish uploader entity '" + optionalMemberEntity.get().getId() + "'"); // test
            if(optionalMemberEntity.isEmpty()) {
                // id는 실려왔지만 멤버 엔티티가 존재하지 않는 경우
                System.out.println("- EXCEPTION: member '" + fishDTO.getUserId() + "' doesn't exist."); // test
                return null;
            }
        }
        System.out.println(fishDTO);
        System.out.println(fishEntity);
        // - 그물 Entity와 각 파일 Entity를 저장한다.
        FishEntity savedEntity = fishRepository.save(fishEntity);
        System.out.println("Saved fish '" + fishEntity.getFishName() + "' to db"); // test
        for(FileEntity fe : fishEntity.getFileEntityList()){
            fileRepository.save(fe);
            System.out.println("Saved file entity '" + fe.getOriginalFileName() + "' to db");
        }

        System.out.println("FishService: fish saved. (id: " + savedEntity.getId() + ", pinNumber: " + savedEntity.getPinNumber() + ")");
        System.out.println();
        // 저장된 그물 dto를 반환한다.
        fishCount++;
        FishDTO tempReturnObject = FishDTO.toFishDTO(savedEntity); //temp
        return tempReturnObject;
    }

    @Transactional
    public List<FishDTO> findAll() {
        // 모든 그물 return
        List<FishEntity> fishEntities = fishRepository.findAll();
        List<FishDTO> fishDTOS = new ArrayList<>();
        for(FishEntity g : fishEntities)
            fishDTOS.add(FishDTO.toFishDTO(g));
        return fishDTOS;
    }
}
