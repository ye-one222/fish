package com.fisherman.fish.controller;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.dto.MemberRequestDTO;
import com.fisherman.fish.dto.MemberResponseDTO;
import com.fisherman.fish.service.GmoolService;
import com.fisherman.fish.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    private final GmoolService gmoolService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public List<MemberResponseDTO> getAllUsers(){
        // 모든 user 반환
        List<MemberResponseDTO> members = memberService.getAllMembers();
        return members;
    }

    @PostMapping("/login")
    public Object login(@RequestBody MemberRequestDTO loginRequestDTO){
        // 로그인 진행
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getId(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody MemberRequestDTO signupRequestDTO){
        // 받은 정보로 회원가입
        // TODO : 아이디 예외처리 (금지단어, 정규식 처리 등)
        System.out.println("sign up request by " + signupRequestDTO.toString());
        try {
            memberService.save(signupRequestDTO);
        } catch(DataIntegrityViolationException e){
            // ID가 중복인 경우
            e.printStackTrace(); // test
            return "이미 존재하는 ID입니다";
        } catch(Exception e){
            e.printStackTrace(); // test
            return "회원가입 실패";
        }
        return signupRequestDTO;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(name="id") String id){
        // 해당 user의 정보 반환
        MemberResponseDTO member = memberService.searchById(id);
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
        MemberResponseDTO memberDTO = memberService.searchById(id);
        if(memberDTO == null) return "no";
        List<GmoolDTO> gmools = gmoolService.findByUserId(memberDTO.getId());
        return "gmool of " + id;
    }
}
