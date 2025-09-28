package com.marceloserpa.springredis.leaderboard;

public record Score(String username, Integer points, String game) {
}
