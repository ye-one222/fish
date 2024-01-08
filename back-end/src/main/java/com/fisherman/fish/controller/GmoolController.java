package com.fisherman.fish.controller;

import com.fisherman.fish.dto.FileDTO;
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
        List<GmoolDTO> gmools = gmoolService.searchAll();
        // TODO: 비어있다면 예외처리

        return gmools;
    }
    
    @PostMapping("/")
    public String createGmool(@ModelAttribute("gmool") GmoolDTO gmoolDTO, @ModelAttribute("file") FileDTO file){
        // 받은 정보로 그물 생성
        //  1. pin 번호 생성
        //  2. qr코드 생성
        //  3. 링크 생성
        //  셋 다 묶어서 반환
        // 해당 그물 저장
        int pinNumber = GmoolService.generatePinNumber();
        gmoolDTO.setPinNumber(pinNumber);
        // TODO: 첨부 파일 수를 여기서 세서 그물dto에 채워줘야 할듯
        // qr코드랑 링크 생성했다고 가정
        gmoolService.save(gmoolDTO);
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
