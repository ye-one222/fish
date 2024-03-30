package com.fisherman.fish.dto;

import com.fisherman.fish.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberResponseDTO {
    private String id; // 아이디
    private String firstName; // 이름
    private String lastName; // 성

    public MemberResponseDTO(MemberRequestDTO m){
        id = m.getId();
        firstName = m.getFirstName();
        lastName = m.getLastName();
    }

    public MemberResponseDTO(MemberEntity m){
        id = m.getId();
        firstName = m.getFirstName();
        lastName = m.getLastName();
    }

    public static MemberResponseDTO toMemberDTO(MemberEntity me) {
        return new MemberResponseDTO(me);
    }
}
