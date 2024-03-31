package com.fisherman.fish.dto;

import com.fisherman.fish.entity.FileEntity;
import com.fisherman.fish.entity.FishEntity;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.LazyInitializationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FishDTO {
    private long id; // 그물 id
    private String fishName; // 그물명
    private String password; // 그물 암호
    private LocalDateTime createdTime; // 생성시각
    private LocalDateTime updatedTime; // 수정시각
    private int dueMinute; // 유효기간 (분)
    private int pinNumber; // 그물 핀번호
    
    protected List<FileDTO> fileDTOList; // 첨부 파일
    private int fileCount; // 첨부 파일 수
    private String userId; // 게시자 id

    public FishDTO(FishRequestDTO g){
        // GmoolReceiver로부터 dto 생성
        id = g.getId();
        fishName = g.getFishName();
        password = g.getPassword();
        createdTime = g.getCreatedTime(); // TODO: null이면 직접 부여 (완)
        dueMinute = g.getDueMinute();
        pinNumber = g.getPinNumber();
        fileDTOList = g.convertFilesToFileDTOList();
        fileCount = fileDTOList.size();
        userId = g.getUserId();
    }

    public static FishDTO toFishDTO(FishEntity fishEntity) {
        FishDTO fishDTO = new FishDTO();
        fishDTO.setId(fishEntity.getId());
        fishDTO.setFishName(fishEntity.getFishName());
        fishDTO.setPassword(fishEntity.getPassword());
        fishDTO.setCreatedTime(fishEntity.getCreatedTime());
        fishDTO.setDueMinute(fishEntity.getDueMinute());
        fishDTO.setPinNumber(fishEntity.getPinNumber());
        List<FileEntity> fileEntityList = fishEntity.getFileEntityList();
        List<FileDTO> fileDTOList = new ArrayList<>();
        try {
            for (FileEntity f : fileEntityList)
                fileDTOList.add(FileDTO.toFileDTO(f));
        } catch (LazyInitializationException le){
            // TODO : fishService init()에서 lazy load 뜸. 근데 사실 상관없으므로 catch로 에러잡고 진행.
            // - 근데 마음에 안드니까 나중에 고쳐보자
            System.out.println("Lazy load exception for file entities for fish '" + fishDTO.id + "'");
        }
        catch (Exception e){
            System.out.println("Error handling File Entities for fish '" + fishDTO.id + "'");
            e.printStackTrace();
        }
        fishDTO.setFileDTOList(fileDTOList);
        fishDTO.setFileCount(fishEntity.getFileCount());
        return fishDTO;
    }

    public static FishDTO toFishDTO(FishRequestDTO fishRequestDTO) {
        fishRequestDTO.convertFilesToFileDTOList();
        return fishRequestDTO;
    }

}
