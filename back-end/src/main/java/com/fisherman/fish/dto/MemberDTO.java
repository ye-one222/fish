package com.fisherman.fish.dto;

import com.fisherman.fish.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDTO {
    private String Id; // 아이디
    private String Password; // 비밀번호
    private String FirstName; // 이름
    private String LastName; // 성

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        // TODO
        return new MemberDTO("id", "pw", "Jane", "Doe");
    }
}
