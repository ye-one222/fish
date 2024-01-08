package com.fisherman.fish.controller;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.service.GmoolService;
import com.fisherman.fish.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gmool")
public class GmoolController {
    private final GmoolService gmoolService;
    private final MemberService memberService;

    @GetMapping("/")
    public List<GmoolDTO> getGmools(){
        // 모든 그물 반환
        List<GmoolDTO> gmools = gmoolService.getAllGmools();
        // TODO: 비어있다면 예외처리

        return gmools;
    }
    
    @PostMapping("/")
    public String createGmool(){
        // 받은 정보로 그물 생성
        // 1. pin 번호 생성
        // 2. qr코드 생성
        // 3. 링크 생성
        // 셋 다 묶어서 반환
        return "ok";
    }

    @GetMapping("/{gid}")
    public String getGmool(@PathVariable(name="gid") Long gid){
        // 해당 그물 반환
        Optional<GmoolDTO> gmool = gmoolService.searchById(gid);
        if(gmool.isPresent()){
            return "yes";
        }
        else{
            return "no";
        }
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
