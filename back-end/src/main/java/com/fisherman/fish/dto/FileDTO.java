package com.fisherman.fish.dto;

public class FileDTO {
    private String id; // 파일 id -> redundant?
    private String originalFileName; // 원래 파일명
    private String storedFileName; // 서버에 저장되는 파일 명
    private long gmoolId; // 연결된 그물의 id
    private long fileSize; // 해당 파일 크기
}
