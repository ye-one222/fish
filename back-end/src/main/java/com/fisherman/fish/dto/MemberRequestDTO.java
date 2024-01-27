package com.fisherman.fish.dto;

import com.fisherman.fish.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDTO {
    private String id; // 아이디
    private String password; // 비밀번호
    private String firstName; // 이름
    private String lastName; // 성

    public static MemberRequestDTO toMemberDTO(MemberEntity memberEntity) {
        // TODO: toMemberDTO 완성
        return new MemberRequestDTO("id", "pw", "Jane", "Doe");
    }
}
