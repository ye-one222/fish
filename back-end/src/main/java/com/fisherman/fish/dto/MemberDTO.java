package com.fisherman.fish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDTO {
    private String Id; // 아이디
    private String Password; // 비밀번호
    private String FirstName; // 이름
    private String LastName; // 성

}
