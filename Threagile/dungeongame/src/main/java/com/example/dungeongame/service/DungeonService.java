package com.example.dungeongame.service;

import com.example.dungeongame.model.DungeonGameRecord;
import com.example.dungeongame.repository.DungeonGameRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DungeonService {

    @Autowired
    private DungeonGameRepository repository;

    @Autowired
    private Gson gson;

    /**
     *
     * @param dungeon A 2D array representing the dungeon rooms.
     * @return The minimum initial health required.
     */
    @Transactional
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 1;
        }

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthOnExit = Math.min(dp[j], dp[j + 1]);

                int healthNeeded = Math.max(1, minHealthOnExit - dungeon[i][j]);
                dp[j] = healthNeeded;
            }
        }

        int result = dp[0];

        saveResult(dungeon, result);

        return result;
    }

    private void saveResult(int[][] dungeon, int minHealth) {
        String dungeonJson = gson.toJson(dungeon);
        DungeonGameRecord record = new DungeonGameRecord(dungeonJson, minHealth);
        repository.save(record);
    }
}