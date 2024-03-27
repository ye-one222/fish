package com.fisherman.fish.repository;

import com.fisherman.fish.entity.FishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FishRepository extends JpaRepository<FishEntity, Long> {
    Optional<List<FishEntity>> findByFishOwner_id(String uid); // 그물 게시자 id로 검색
}
