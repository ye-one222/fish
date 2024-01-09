package com.fisherman.fish.service;

import com.fisherman.fish.dto.GmoolDTO;
import com.fisherman.fish.entity.GmoolEntity;
import com.fisherman.fish.repository.GmoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GmoolService {
    private final GmoolRepository gmoolRepository;
    public static int generatePinNumber() {
        // 현재 사용 중인 pin번호 : hashmap에 관리?
        // 해당 번호가 찬 경우 : 1, 2, 4, 8, ... 순으로 커지도록?
        // -> 근데 이러면 자기 번호에서 1을 더하면 바로 다른 사람 께 보이니까 비효율적이지 않나??

    }

    public GmoolDTO findById(Long gid) {
        Optional<GmoolEntity> optionalGmoolEntity = gmoolRepository.findById(gid);
        if(optionalGmoolEntity.isEmpty()) return null;
        GmoolDTO gmoolDTO = GmoolDTO.toGmoolDTO(optionalGmoolEntity.get());
        return gmoolDTO;
    }

    public List<GmoolDTO> findByUserId(String uid){
        // 해당 user가 공유한 모든 그물 return
    }

    public Long save(GmoolDTO gmoolDTO) {

    }

    public List<GmoolDTO> findAll() {
        List<GmoolEntity> gmoolEntities = gmoolRepository.findAll();
        List<GmoolDTO> gmoolDTOs = new ArrayList<>();
        for(GmoolEntity g : gmoolEntities){
            gmoolDTOs.add(GmoolDTO.toGmoolDTO(g));
        }
        return gmoolDTOs;
    }
}
