package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.FishEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FishDTO {
    private long id; // 그물 id
    private String fishName; // 그물명
    private String password; // 그물 암호
    private LocalDateTime createdTime; // 생성시각
    private LocalDateTime updatedTime; // 수정시각
    private int dueMinute; // 유효기간 (분)
    private int pinNumber; // 그물 핀번호
    
    protected List<FileDTO> fileDTOList; // 첨부 파일
    private int fileCount; // 첨부 파일 수
    private String userId; // 게시자 id

    public FishDTO(FishRequestDTO g){
        // GmoolReceiver로부터 dto 생성
        id = g.getId();
        fishName = g.getFishName();
        password = g.getPassword();
        createdTime = g.getCreatedTime(); // TODO: null이면 직접 부여 (완)
        dueMinute = g.getDueMinute();
        pinNumber = g.getPinNumber();
        fileDTOList = g.convertFilesToFileDTOList();
        fileCount = fileDTOList.size();
        userId = g.getUserId();
    }

    public static FishDTO toFishDTO(FishEntity fishEntity) {
        FishDTO fishDTO = new FishDTO();
        fishDTO.setId(fishEntity.getId());
        fishDTO.setFishName(fishEntity.getFishName());
        fishDTO.setPassword(fishEntity.getPassword());
        fishDTO.setCreatedTime(fishEntity.getCreatedTime());
        fishDTO.setDueMinute(fishEntity.getDueMinute());
        fishDTO.setPinNumber(fishEntity.getPinNumber());
        List<FileEntity> fileEntityList = fishEntity.getFileEntityList();
        List<FileDTO> fileDTOList = new ArrayList<>();
        for(FileEntity f : fileEntityList)fileDTOList.add(FileDTO.toFileDTO(f));
        fishDTO.setFileDTOList(fileDTOList);
        fishDTO.setFileCount(fishEntity.getFileCount());
        return fishDTO;
    }

    public static FishDTO toFishDTO(FishRequestDTO fishRequestDTO) {
        fishRequestDTO.convertFilesToFileDTOList();
        return fishRequestDTO;
    }

}
