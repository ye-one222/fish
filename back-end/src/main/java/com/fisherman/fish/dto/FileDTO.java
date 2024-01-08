package com.fisherman.fish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDTO {
    private String originalFileName; // 원래 파일명
    private String storedFileName; // 서버에 저장되는 파일 명
    private long gmoolId; // 연결된 그물의 id
    private long fileSize; // 해당 파일 크기
}
