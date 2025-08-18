package com.example.dungeongame.controller;

import com.example.dungeongame.service.DungeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

record DungeonRequest(int[][] dungeon) {}

@RestController
@RequestMapping("/api/dungeon")
public class DungeonController {

    @Autowired
    private DungeonService dungeonService;

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, ?>> calculateMinimumHP(@RequestBody DungeonRequest request) {
        if (request.dungeon() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Dungeon layout cannot be null."));
        }
        try {
            int result = dungeonService.calculateMinimumHP(request.dungeon());
            return ResponseEntity.ok(Collections.singletonMap("minInitialHealth", result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error", "An unexpected error occurred."));
        }
    }
}