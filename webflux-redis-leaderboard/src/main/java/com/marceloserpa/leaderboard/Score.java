package com.marceloserpa.leaderboard;

public class Score {

    private Integer user;
    private Double score;

    public Score() {
    }

    public Score(Integer user, Double score) {
        this.user = user;
        this.score = score;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
