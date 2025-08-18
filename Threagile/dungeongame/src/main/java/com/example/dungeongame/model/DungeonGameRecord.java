package com.example.dungeongame.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name = "dungeon_game_records")
@Data
@NoArgsConstructor
public class DungeonGameRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dungeon_input", columnDefinition = "TEXT", nullable = false)
    private String dungeonInput;

    @Column(name = "min_health", nullable = false)
    private int minHealth;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public DungeonGameRecord(String dungeonInput, int minHealth) {
        this.dungeonInput = dungeonInput;
        this.minHealth = minHealth;
        this.createdAt = Instant.now();
    }
}