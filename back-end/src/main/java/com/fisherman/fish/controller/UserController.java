package com.fisherman.fish.controller;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.dto.MemberDTO;
import com.fisherman.fish.service.GmoolService;
import com.fisherman.fish.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final MemberService memberService;
    private final GmoolService gmoolService;

    @GetMapping("/")
    public List<MemberDTO> getAllUsers(){
        // 모든 user 반환
        List<MemberDTO> members = memberService.getAllMembers();
        return members;
    }

    @PostMapping("/")
    public String signup(){
        // 받은 정보로 회원가입
        return "ok";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(name="id") String id){
        // 해당 user의 정보 반환
        MemberDTO member = memberService.searchById(id);
        if(member == null) return "no";
        return "yes";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(name="id") String id){
        // 해당 회원 삭제
        return "yes";
    }



    @GetMapping("/{id}/gmools")
    public String getUserGmool(@PathVariable(name="id") String id){
        // 해당 유저의 그물들 반환
        MemberDTO memberDTO = memberService.searchById(id);
        if(memberDTO == null) return "no";
        List<GmoolDTO> gmools = gmoolService.findByUserId(memberDTO.getId());
        return "gmool of " + id;
    }
}
