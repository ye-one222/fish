package com.fisherman.fish.entity;

//import com.fisherman.fish.repository.UploadRepository;
import com.fisherman.fish.dto.MemberRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor // 임시 (No default constructor for entity 'GmoolEntity')
@Setter // 임시
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

    @Column
    private String role;

    public void addUploadedGmools(GmoolEntity gmoolEntity){
        uploadedGmools.add(gmoolEntity);
    }

    public static MemberEntity toMemberEntity(MemberRequestDTO memberDTO){
        // TODO: toMemberEntity 완성

        return new MemberEntity(
                memberDTO.getId(),
                memberDTO.getPassword(),
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                new ArrayList<>(),
                "ROLE_USER"
        );
    }
}
