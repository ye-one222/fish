package com.fisherman.fish.repository;

import com.fisherman.fish.entity.GmoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GmoolRepository extends JpaRepository<GmoolEntity, Long> {
    Optional<List<GmoolEntity>> findByMemberId(String userID);
}
