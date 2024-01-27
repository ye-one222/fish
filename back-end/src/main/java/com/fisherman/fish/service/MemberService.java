package com.fisherman.fish.service;

import com.fisherman.fish.dto.MemberRequestDTO;
import com.fisherman.fish.dto.MemberResponseDTO;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public List<MemberResponseDTO> getAllMembers() {
        // TODO : Member서비스 완성
        // 모든 회원 dto 반환
        List<MemberResponseDTO> memberDTOList = new ArrayList<>();
        return memberDTOList;
    }

    public MemberResponseDTO searchById(String id) {
        // TODO : Member서비스 완성
        // id로 회원 검색
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isEmpty()) return null;
        return new MemberResponseDTO(optionalMemberEntity.get());
    }

    public MemberResponseDTO save(MemberRequestDTO signupRequestDTO) {
        // TODO : 회원가입 예외처리 필요할지 생각해보기
        //  - password 8자 이상
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(signupRequestDTO.getId());
        memberEntity.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        memberEntity.setFirstName(signupRequestDTO.getFirstName());
        memberEntity.setLastName(signupRequestDTO.getLastName());
        // 저장
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        return new MemberResponseDTO(savedEntity);
    }
}
