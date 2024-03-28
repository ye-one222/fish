package com.fisherman.fish.entity;

import com.fisherman.fish.dto.FileDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter // 임시
@NoArgsConstructor // 임시
@Table(name = "file")
@AllArgsConstructor
@ToString
public class FileEntity {
    @Id
    private String storedFileName;

    @Column
    private String originalFileName;

    @Column
    private long fileSize;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private FishEntity fishEntity;

    public static FileEntity toFileEntity(FileDTO fileDTO){
        return new FileEntity(
                fileDTO.getStoredFileName(),
                fileDTO.getOriginalFileName(),
                fileDTO.getFileSize(),
                null);
    }

    public static FileEntity toFileEntity(FileDTO fileDTO, FishEntity fishEntity){
        // TODO: fishEntity 처리
        return new FileEntity(
                fileDTO.getStoredFileName(),
                fileDTO.getOriginalFileName(),
                fileDTO.getFileSize(),
                fishEntity);
    }
}
