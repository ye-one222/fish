package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.GmoolEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GmoolDTO {
    private long id; // 그물 id
    private String gmoolName; // 그물명
    private String password; // 그물 암호
    private LocalDateTime createdTime; // 생성시각
    private int dueMinute; // 유효기간 (분)
    private int pinNumber; // 그물 핀번호
    
    private List<FileDTO> fileDTOList; // 첨부 파일
    private int fileCount; // 첨부 파일 수
    private String userId; // 게시자 id
    // TODO: QR코드 이미지 저장?

    public static GmoolDTO toGmoolDTO(GmoolEntity gmoolEntity) {
        GmoolDTO gmoolDTO = new GmoolDTO();
        gmoolDTO.setId(gmoolEntity.getId());
        gmoolDTO.setGmoolName(gmoolEntity.getGmoolName());
        gmoolDTO.setPassword(gmoolEntity.getPassword());
        gmoolDTO.setCreatedTime(gmoolEntity.getCreatedTime());
        gmoolDTO.setDueMinute(gmoolEntity.getDueMinute());
        gmoolDTO.setPinNumber(gmoolEntity.getPinNumber());
        List<FileEntity> fileEntityList = gmoolEntity.getFileEntityList();
        List<FileDTO> fileDTOList = new ArrayList<>();
        for(FileEntity f : fileEntityList)fileDTOList.add(FileDTO.toFileDTO(f));
        gmoolDTO.setFileDTOList(fileDTOList);
        gmoolDTO.setFileCount(gmoolEntity.getFileCount());
        return gmoolDTO;
    }

}
