package com.fisherman.fish.entity;

import com.fisherman.fish.dto.GmoolDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // 임시
@NoArgsConstructor // 임시
@AllArgsConstructor
@Table(name = "gmool")
public class GmoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String gmoolName;

    @Column
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;

    @Column
    private int dueMinute;

    @Column
    private int pinNumber;

    @OneToMany(mappedBy = "gmoolEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FileEntity> fileEntityList;

    @Column
    private int fileCount;

    //@OneToOne(mappedBy = "gmoolEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //private UploadEntity uploadEntity;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberEntity gmoolOwner;

    public void updatePinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    public void addFileEntity(FileEntity fileEntity){
        fileEntityList.add(fileEntity);
    }

    public void setGmoolOwner(MemberEntity memberEntity){
        gmoolOwner = memberEntity;
    }

    public static GmoolEntity toGmoolEntity(GmoolDTO gmoolDTO) {
        // TODO: fileEntityList, gmoolOwner 처리 -> setter로 설정?

        return new GmoolEntity(
                gmoolDTO.getId(),
                gmoolDTO.getGmoolName(),
                gmoolDTO.getPassword(),
                gmoolDTO.getCreatedTime(),
                gmoolDTO.getUpdatedTime(),
                gmoolDTO.getDueMinute(),
                gmoolDTO.getPinNumber(),
                new ArrayList<>(),
                gmoolDTO.getFileCount(),
                null
        );
    }
}
