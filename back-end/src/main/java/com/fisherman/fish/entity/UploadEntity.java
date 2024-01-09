package com.fisherman.fish.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="upload")
public class UploadEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;
    
    @Id
    @OneToOne(mappedBy = "uploadEntity", fetch = FetchType.LAZY)
    private GmoolEntity gmoolEntity; // TODO: 나중에 살펴보기 (1대1맞는지, 이 관계를 어떻게 표현할지)
}
