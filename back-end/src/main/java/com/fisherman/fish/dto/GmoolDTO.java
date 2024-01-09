package com.fisherman.fish.dto;

import com.fisherman.fish.entity.GmoolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GmoolDTO {
    private long id; // 그물 id
    private String gmoolName; // 그물명
    private String password; // 그물 암호
    private LocalDateTime createdTime; // 생성시각
    private int dueMinute; // 유효기간 (분)
    private int pinNumber; // 그물 핀번호
    private int fileCount; // 첨부 파일 수
    // TODO: QR코드 이미지 저장?

    public static GmoolDTO toGmoolDTO(GmoolEntity gmoolEntity) {

    }

}
