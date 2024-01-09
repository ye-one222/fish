package com.fisherman.fish.entity;

import com.fisherman.fish.repository.UploadRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
public class MemberEntity {
    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UploadEntity> uploadEntityList = new ArrayList<>();

}
