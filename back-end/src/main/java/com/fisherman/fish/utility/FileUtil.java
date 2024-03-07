package com.fisherman.fish.utility;

import org.springframework.beans.factory.annotation.Value;

public class FileUtil {
    @Value("${gmool.file-save-path}")
    private static String fileSavePath;

    public static String createStoreFilename(String originalFilename, String prefix){
        // 저장할 고유한 파일명 생성
        // - 그물 총 개수를 파일명 앞에 추가
        String storeFilename = prefix + originalFilename;
        return storeFilename;
    }

    public static String getFinalPath(String storeFilename){
        // 저장 경로 반환
        String path = fileSavePath + storeFilename;
        return path;
    }
}
