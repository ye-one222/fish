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
    public List<MemberResponseDTO> findAll() {
        // 모든 회원 dto 반환
        List<MemberResponseDTO> memberDTOList = new ArrayList<>();
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        if(memberEntityList.isEmpty()) return new ArrayList<>();
        memberEntityList.iterator().forEachRemaining((memberEntity -> {
            memberDTOList.add(MemberResponseDTO.toMemberDTO(memberEntity));
        }));
        return memberDTOList;
    }

    public MemberResponseDTO findById(String id) {
        // id로 회원 검색
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isEmpty()) return null;
        return new MemberResponseDTO(optionalMemberEntity.get());
    }

    public MemberResponseDTO save(MemberRequestDTO signupRequestDTO) {
        // TODO : 회원가입 예외처리
        //  - password 8자 이상
        //  - id 존재 시 기각 처리 -> 현재 컨트롤러에서 처리
        //  - role ROLE_USER로 변경
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(signupRequestDTO.getId());
        memberEntity.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        memberEntity.setFirstName(signupRequestDTO.getFirstName());
        memberEntity.setLastName(signupRequestDTO.getLastName());
        memberEntity.setRole("ROLE_ADMIN");
        // 저장
        MemberEntity savedEntity = memberRepository.save(memberEntity);
        return new MemberResponseDTO(savedEntity);
    }
}
