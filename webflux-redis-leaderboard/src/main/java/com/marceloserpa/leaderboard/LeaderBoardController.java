package com.marceloserpa.leaderboard;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class LeaderBoardController {

    private LeaderBoardService leaderBoardService;


    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping("games/{gameId}")
    public Flux<Score> getLeaderBoard(@PathVariable("gameId") Integer gameId){
        return leaderBoardService.getTop3(gameId);
    }

    @PostMapping("games/{gameId}/users/{userId}/score/{score}")
    public Mono<Double> addScore(@PathVariable("gameId") Integer gameId,
                                 @PathVariable("userId") Integer userId,
                                 @PathVariable("score") Integer score){
        return leaderBoardService.addScore(gameId, userId, score);
    }

}
