package com.fisherman.fish.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gmool")
public class GmoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String gmoolName;

    @Column
    private String password;

    @Column
    private LocalDateTime createdTime;

    @Column
    private int dueMinute;

    @Column
    private int pinNumber;

    @OneToMany(mappedBy = "gmoolEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> fileEntityList = new ArrayList<>();

    @Column
    private int fileCount;

    @OneToOne(fetch = FetchType.LAZY)
    private UploadEntity uploadEntity; // TODO: 나중에 관계 어떻게 표현할지 찾아보기
}
