package com.fisherman.fish.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {
    @Value("${gmool.file-save-path}")
    private String fileSavePath;

    public String createStoreFilename(String originalFilename, String prefix){
        // TODO: 파일명 앞에 prefix 어떻게 할지
        // - 그물 총 개수를 파일명 앞에 추가

        // 일단, 현재시간 밀리초를 파일명 앞에 붙인다
        prefix = String.valueOf(System.currentTimeMillis()); //test
        
        String storeFilename = prefix + originalFilename;
        return storeFilename;
    }

    public String getFinalPath(String storeFilename){
        // 저장 경로 반환
        String path = fileSavePath + storeFilename;
        return path;
    }
}
