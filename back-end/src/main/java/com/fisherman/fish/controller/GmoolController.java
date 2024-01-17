package com.fisherman.fish.controller;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.dto.GmoolReceiveDTO;
import com.fisherman.fish.service.GmoolService;
import com.fisherman.fish.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gmool")
public class GmoolController {
    private final GmoolService gmoolService;
    private final MemberService memberService;

    @GetMapping("/")
    public List<GmoolDTO> getGmools(){
        // 모든 그물 반환
        List<GmoolDTO> gmools = gmoolService.findAll();
        return gmools;
    }
    
    @PostMapping("/")
    public GmoolDTO createGmool(@ModelAttribute("gmool") GmoolReceiveDTO gmoolReceiveDTO){
        System.out.println("GmoolController: [POST at '/gmool'] "); // test
        // 예외처리 : DTO 없이 POST 요청 보낸 경우
        if(gmoolReceiveDTO == null){
            System.out.println("- EXCEPTION: gmoolDTO is null"); // test
            return null;
        }
        // 예외처리 : 파일이 비어있는 경우
        if(gmoolReceiveDTO.getFiles() == null){
            System.out.println("- EXCEPTION: no files are included"); // test
            return null;
        }
        // 예외처리 : 유효기간이 설정되어있지 않은 경우, 기본값(10분)으로 설정 TODO: 미설정시 기본값 0 맞는지 테스트 필요
        if(gmoolReceiveDTO.getDueMinute() == 0){
            int defaultTime = 10;
            System.out.println("- WARNING: due minute is not set; it would be set to default (" + defaultTime + ")");
            gmoolReceiveDTO.setDueMinute(defaultTime);
        }
        // TODO : 그물 생성시각 할당
        // TODO : 파일 개수 할당
        GmoolDTO gmoolDTO = new GmoolDTO(gmoolReceiveDTO); // 첨부파일을 fileDTO로 만듬
        GmoolDTO savedDTO = gmoolService.save(gmoolDTO);
        System.out.println("GmoolController: saved gmool.");
        return savedDTO;
    }

    @GetMapping("/{gid}")
    public Object getGmool(@PathVariable(name="gid") Long gid){
        // 해당 그물 반환
        GmoolDTO gmool = gmoolService.findById(gid);
        if(gmool == null) return "no gmool for you!";
        return gmool;
    }

    @GetMapping("/{gid}/files")
    public String getFiles(@PathVariable(name="gid") Long gid){
        // 해당 그물의 파일 모두 반환
        return "ok";
    }

    @GetMapping("/{gid}/files/{filename}")
    public String getFile(@PathVariable(name="gid") Long gid, @PathVariable(name="filename") String fname){
        // 해당 그물의 해당 파일 반환
        return gid + fname;
    }



}
