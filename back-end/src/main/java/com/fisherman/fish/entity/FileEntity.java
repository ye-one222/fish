package com.fisherman.fish.entity;

import com.fisherman.fish.dto.FileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter // 임시
@NoArgsConstructor // 임시
@Table(name = "file")
@AllArgsConstructor
public class FileEntity {
    @Id
    private String storedFileName;

    @Column
    private String originalFileName;

    @Column
    private long fileSize;

    @ManyToOne
    @JoinColumn(name = "gmool_id")
    private GmoolEntity gmoolEntity;

    public void setGmoolEntity(GmoolEntity gmoolEntity){
        this.gmoolEntity = gmoolEntity;
    }

    public static FileEntity toFileEntity(FileDTO fileDTO){
        return new FileEntity(
                fileDTO.getStoredFileName(),
                fileDTO.getOriginalFileName(),
                fileDTO.getFileSize(),
                null);
    }

    public static FileEntity toFileEntity(FileDTO fileDTO, GmoolEntity gmoolEntity){
        // TODO: gmoolEntity 처리
        return new FileEntity(
                fileDTO.getStoredFileName(),
                fileDTO.getOriginalFileName(),
                fileDTO.getFileSize(),
                gmoolEntity);
    }
}
