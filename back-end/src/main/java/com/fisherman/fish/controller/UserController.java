package com.fisherman.fish.controller;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.dto.MemberDTO;
import com.fisherman.fish.service.GmoolService;
import com.fisherman.fish.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(name="id") String id){
        // 해당 user의 정보 반환
        Optional<MemberDTO> member = memberService.searchById(id);
        if(member.isPresent()){
            // member 존재하는 경우
            return "yes";
        } else {
            // member 존재하지 않는 경우
            return "no";
        }
    }

    @PostMapping("/signup")
    public String signup(){
        // 받은 정보로 회원가입
        return "ok";
    }

    @GetMapping("/{id}/gmools")
    public String getUserGmool(@PathVariable(name="id") String id){
        // 해당 유저의 그물들 반환
        Optional<MemberDTO> member = memberService.searchById(id);
        if(member.isEmpty()) return "no";
        List<GmoolDTO> gmools = gmoolService.searchByUserId(member.id);
        return "gmool of " + id;
    }
}
