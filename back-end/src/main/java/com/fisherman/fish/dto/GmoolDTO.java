package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.GmoolEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GmoolDTO {
    private long id; // 그물 id
    private String gmoolName; // 그물명
    private String password; // 그물 암호
    private LocalDateTime createdTime; // 생성시각
    private int dueMinute; // 유효기간 (분)
    private int pinNumber; // 그물 핀번호
    
    protected List<FileDTO> fileDTOList; // 첨부 파일
    private int fileCount; // 첨부 파일 수
    private String userId; // 게시자 id

    public GmoolDTO(GmoolReceiveDTO g){
        id = g.getId();
        gmoolName = g.getGmoolName();
        password = g.getPassword();
        createdTime = g.getCreatedTime(); // TODO: null이면 직접 부여
        dueMinute = g.getDueMinute();
        pinNumber = g.getPinNumber();
        fileDTOList = g.convertFilesToFileDTOList();
        fileCount = fileDTOList.size();
        userId = g.getUserId();
    }

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

    public static GmoolDTO toGmoolDTO(GmoolReceiveDTO gmoolReceiveDTO) {
        gmoolReceiveDTO.convertFilesToFileDTOList();
        return gmoolReceiveDTO;
    }

}
