package com.fisherman.fish.dto;

import com.fisherman.fish.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberRequestDTO {
    private String id; // 아이디
    private String password; // 비밀번호
    private String firstName; // 이름
    private String lastName; // 성

    public MemberRequestDTO(MemberEntity memberEntity){
        id = memberEntity.getId();
        password = memberEntity.getPassword();
        firstName = memberEntity.getFirstName();
        lastName = memberEntity.getLastName();
    }

    public static MemberRequestDTO toMemberDTO(MemberEntity memberEntity) {
        return new MemberRequestDTO(memberEntity);
    }
}
