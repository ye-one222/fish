package com.fisherman.fish.entity;

import com.fisherman.fish.dto.FishDTO;
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
@Table(name = "fish")
public class FishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String fishName;

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

    public void setDueMinute(int dueMinute) { this.dueMinute = (dueMinute >= 0) ? dueMinute : 0; }

    @Column
    private int pinNumber;

    @OneToMany(mappedBy = "fishEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FileEntity> fileEntityList;

    @Column
    private int fileCount;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberEntity fishOwner;

    public void updatePinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    public void addFileEntity(FileEntity fileEntity){
        fileEntityList.add(fileEntity);
    }

    public static FishEntity toFishEntity(FishDTO fishDTO) {
        // TODO: fileEntityList, gmoolOwner 처리 -> setter로 설정?

        return new FishEntity(
                fishDTO.getId(),
                fishDTO.getFishName(),
                fishDTO.getPassword(),
                fishDTO.getCreatedTime(),
                fishDTO.getUpdatedTime(),
                fishDTO.getDueMinute(),
                fishDTO.getPinNumber(),
                new ArrayList<>(),
                fishDTO.getFileCount(),
                null
        );
    }
}
