package com.marceloserpa.leaderboard;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LeaderBoardService {

    private LeaderBoardRepository leaderBoardRepository;

    public LeaderBoardService(LeaderBoardRepository leaderBoardRepository) {
        this.leaderBoardRepository = leaderBoardRepository;
    }

    public Mono<Double> addScore(Integer gameId, Integer userId, Integer score){
        return leaderBoardRepository.addScore(gameId, userId, score);
    }

    public Flux<Score> getTop3(Integer gameId){
        return leaderBoardRepository.getLeaderboard(gameId, 3);
    }

}
