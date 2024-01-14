package com.fisherman.fish.service;

import com.fisherman.fish.dto.MemberDTO;
import com.fisherman.fish.entity.MemberEntity;
import com.fisherman.fish.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public List<MemberDTO> getAllMembers() {
        // TODO
        // 모든 회원 dto 반환
        List<MemberDTO> memberDTOList = new ArrayList<>();
        return memberDTOList;
    }

    public MemberDTO searchById(String id) {
        // TODO
        // id로 회원 검색
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isEmpty()) return null;
        MemberDTO memberDTO = MemberDTO.toMemberDTO(optionalMemberEntity.get());
        return memberDTO;
    }
}
