package com.fisherman.fish.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Entity
@Getter
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
}
