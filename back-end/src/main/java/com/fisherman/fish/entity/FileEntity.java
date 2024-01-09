package com.fisherman.fish.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file")
public class FileEntity {
    @Id
    private String storedFileName;

    @Column
    private String originalFileName;

    @Column
    private long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="") //TODO
    private GmoolEntity gmoolEntity;
}
