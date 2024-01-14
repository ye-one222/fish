package com.fisherman.fish.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    @Column
    private LocalDateTime createdTime;

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
}
