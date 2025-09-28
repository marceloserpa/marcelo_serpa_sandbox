package com.marceloserpa.springredis.leaderboard;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private RedisTemplate<String, String> redisTemplate;

    private final Function<String, String> getRedisKey = (game) ->"gamerank:" + game;

    public LeaderboardController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping
    public void insert(@RequestBody Score score){
        redisTemplate.opsForZSet().incrementScore(getRedisKey.apply(score.game()), score.username(), score.points());
    }

    @GetMapping("/{game}")
    public List<Score> getRank(@PathVariable("game") String game) {
        Set<ZSetOperations.TypedTuple<String>> rank = redisTemplate.opsForZSet()
                .reverseRangeWithScores(getRedisKey.apply(game), 0, 9);

        return rank.stream().map(element -> new Score(element.getValue(), element.getScore().intValue(), game))
                .collect(Collectors.toList());

    }

}
