package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.GmoolEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GmoolReceiveDTO extends GmoolDTO{
    private List<MultipartFile> files = null;

    public List<FileDTO> convertFilesToFileDTOList(){
        if(fileDTOList == null) fileDTOList = new ArrayList<>();
        for(MultipartFile f : files){
            FileDTO fd = new FileDTO(f.getOriginalFilename(), null, f, f.getSize());
            fileDTOList.add(fd);
        }
        setFileCount(files.size());
        return fileDTOList;
    }
}
