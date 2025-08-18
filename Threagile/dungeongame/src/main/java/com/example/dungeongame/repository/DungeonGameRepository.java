package com.example.dungeongame.repository;

import com.example.dungeongame.model.DungeonGameRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonGameRepository extends JpaRepository<DungeonGameRecord, Long> {
}