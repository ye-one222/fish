package com.fisherman.fish.entity;

//import com.fisherman.fish.repository.UploadRepository;
import com.fisherman.fish.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
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

    //@OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    //private List<UploadEntity> uploadEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "gmoolOwner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<GmoolEntity> uploadedGmools;

    public void addUploadedGmools(GmoolEntity gmoolEntity){
        uploadedGmools.add(gmoolEntity);
    }

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        // TODO

        return new MemberEntity(
                memberDTO.getId(),
                memberDTO.getPassword(),
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                new ArrayList<>()
        );
    }
}
