package com.fisherman.fish.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FishRequestDTO extends FishDTO {
    // FishDTO에 파일을 담는 files객체 포함
    private List<MultipartFile> files = null;

    public List<FileDTO> convertFilesToFileDTOList(){
        // 파일을 FileDTO 리스트로 변환하여 반환
        if(fileDTOList == null) fileDTOList = new ArrayList<>();
        for(MultipartFile f : files){
            FileDTO fd = new FileDTO(f.getOriginalFilename(), null, f, f.getSize());
            fileDTOList.add(fd);
        }
        setFileCount(files.size());
        return fileDTOList;
    }
}
