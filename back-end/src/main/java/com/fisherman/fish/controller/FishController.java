package com.fisherman.fish.controller;

import com.fisherman.fish.dto.FileDTO;
import com.fisherman.fish.dto.FishDTO;
import com.fisherman.fish.dto.FishRequestDTO;
import com.fisherman.fish.service.FishService;
import com.fisherman.fish.service.MemberService;
import com.fisherman.fish.utility.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fishes")
public class FishController {
    private final FishService fishService;
    private final MemberService memberService;
    private final FileUtil fileUtil;

    @GetMapping
    public Object getFishes(){
        // 모든 그물 반환
        // TODO: 그물dto 반환 시 password는 주면 안된다!
        // TODO: admin 권한 있는 경우만 보내주기
        List<FishDTO> fishes = fishService.findAll();
        return fishes;
    }
    
    @PostMapping
    public FishDTO createFish(@ModelAttribute FishRequestDTO fishRequestDTO){
        // TODO: 로그인 했을 시 유저 정보 기록
        System.out.println("FishController: [POST at '/gmool'] "); // test
        // 예외처리 : DTO 없이 POST 요청 보낸 경우
        if(fishRequestDTO == null){
            System.out.println("- EXCEPTION: fishDTO is null"); // test
            return null;
        }
        // 예외처리 : 파일이 비어있는 경우
        if(fishRequestDTO.getFiles() == null){
            System.out.println("- EXCEPTION: no files are included"); // test
            return null;
        }
        // 예외처리 : 유효기간이 설정되어있지 않은 경우, 기본값(10분)으로 설정 TODO: 미설정시 기본값 0 맞는지 테스트 필요
        if(fishRequestDTO.getDueMinute() == 0){
            int defaultTime = 10;
            System.out.println("- WARNING: due minute is not set; it would be set to default (" + defaultTime + ")");
            fishRequestDTO.setDueMinute(defaultTime);
        }
        FishDTO fishDTO = new FishDTO(fishRequestDTO); // 첨부파일을 fileDTO로 만듬
        FishDTO savedDTO = fishService.save(fishDTO);
        System.out.println("FishController: saved gmool.");
        return savedDTO;
    }

    @GetMapping("/{pin}")
    public Object getFish(@PathVariable(name="pin") int pin){
        // TODO : 비밀번호 맞춰야 함
        // 해당 그물 반환
        //FishDTO fish = fishService.findById(pin);
        FishDTO fish = fishService.findByPinNumber(pin);
        if(fish == null) return "no fish for you!";
        return fish;
    }

    @GetMapping("/{gid}/files")
    public Object getFiles(@PathVariable(name="gid") Long gid){
        // TODO : 비밀번호 맞춰야 함
        // 해당 그물의 파일 모두 반환
        // 그물 검색
        FishDTO fishDTO = fishService.findById(gid);
        if(fishDTO == null) return "gmool is not found!";
        // 파일 반환
        List<FileDTO> files = fishDTO.getFileDTOList();
        if(files == null) return "no files for you!";
        return files;
    }

    @GetMapping("/{gid}/files/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable(name="gid") Long gid, @PathVariable(name="filename") String filename)
            throws MalformedURLException {
        // TODO : 비밀번호 맞춰야 함
        // TODO : Service로 로직 옮기기
        // 해당 그물의 해당 파일 반환
        // 그물 검색
        FishDTO fishDTO = fishService.findById(gid);
        if(fishDTO == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // TODO : message 포함?


        // 파일 반환
        List<FileDTO> files = fishDTO.getFileDTOList();
        if(files == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        FileDTO targetFileDTO = null;
        for(FileDTO f : files){
            // 선형탐색 (시간 나면 다른 걸로 바꾸기)
            if(f.getOriginalFileName().equals(filename)) {
                targetFileDTO = f;
                break;
            }
        }
        if(targetFileDTO == null) {
            // 파일 없을 시 NOT FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String filePath = fileUtil.getFinalPath(targetFileDTO.getStoredFileName());
        UrlResource urlResource = new UrlResource("file:///" + filePath);
        String encode = UriUtils.encode(targetFileDTO.getOriginalFileName(), StandardCharsets.UTF_8); // 사용자에게 보여질 파일명
        String contentDisposition = "attachment; filename=\"" + encode + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
