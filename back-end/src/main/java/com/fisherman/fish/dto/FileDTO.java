package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class FileDTO {
    private String originalFileName; // 원래 파일명
    private String storedFileName; // 서버에 저장되는 파일명 -> PK
    private MultipartFile file; // 파일
    
    //private long gmoolId; // 연결된 그물의 id -> 필요한가??
    private long fileSize; // 해당 파일 크기

    public static FileDTO toFileDTO(FileEntity fileEntity) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setOriginalFileName(fileEntity.getOriginalFileName());
        fileDTO.setStoredFileName(fileEntity.getStoredFileName());
        fileDTO.setFileSize(fileEntity.getFileSize());
        // TODO: 파일 꺼내서 주기
        return fileDTO;
    }
}
