package com.marceloserpa.leaderboard;

import io.lettuce.core.api.reactive.RedisSortedSetReactiveCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LeaderBoardRepository {

    private static final Logger log = LoggerFactory.getLogger(LeaderBoardRepository.class.getName());
    private RedisSortedSetReactiveCommands<String, String> commands;

    public LeaderBoardRepository(RedisSortedSetReactiveCommands<String, String> commands) {
        this.commands = commands;
    }

    public Mono<Double> addScore(Integer gameId, Integer userId, Integer score){
        var key = "game:"+gameId;
        var member = "user:"+userId;
        var amount = Double.valueOf(score);

        return commands.zincrby(key, amount, member)
                .doOnSuccess(next -> {
                   log.info("Game: {} - Updated score for user {}", gameId, userId);
                });
    }

    public Flux<Score> getLeaderboard(Integer gameId, Integer total){
        var key = "game:"+gameId;

        return commands.zrevrangeWithScores(key, 0, total -1)
                .flatMap(scoredValue -> {
                    var user = Integer.valueOf(scoredValue.getValue().split("\\:")[1]);
                    var score = scoredValue.getScore();
                    return Flux.just(new Score(user, score));
                })
                .log();
    }

}
