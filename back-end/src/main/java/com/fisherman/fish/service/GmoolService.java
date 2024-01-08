package com.fisherman.fish.service;

import com.fisherman.fish.dto.GmoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GmoolService {

    public static int generatePinNumber() {
    }

    public Optional<GmoolDTO> searchById(Long gid) {
    }

    public Optional<GmoolDTO> searchByUserId(String uid){

    }

    public void save(GmoolDTO gmoolDTO) {
    }

    public List<GmoolDTO> searchAll() {
    }
}
