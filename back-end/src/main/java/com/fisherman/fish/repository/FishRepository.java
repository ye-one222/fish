package com.fisherman.fish.repository;

import com.fisherman.fish.dto.FishDTO;
import com.fisherman.fish.entity.FishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FishRepository extends JpaRepository<FishEntity, Long> {
    Optional<List<FishEntity>> findByFishOwner_id(String uid); // 그물 게시자 id로 검색

    @Query(value = "SELECT * FROM fish f " +
            "WHERE f.pin_number = ? " +
            "ORDER BY created_time + due_minute * 60000 DESC", nativeQuery = true)
    // 만료기간 가장 긴 순으로 정렬
    List<FishEntity> findByPinNumberOrderByExpireDateDesc(int pinNumber);
}
