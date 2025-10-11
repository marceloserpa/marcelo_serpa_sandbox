package com.marceloserpa.ratelimiter;

import com.marceloserpa.ratelimiter.limiter.RateLimiter;
import com.marceloserpa.ratelimiter.limiter.SlidingWindowCounterRateLimiter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

public class Main {

    static void main() throws InterruptedException {
        IO.println("hello");

        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> redisCommands = connection.sync();

        IO.println("Simulating requests");

        RateLimiter ratelimiter = new SlidingWindowCounterRateLimiter(redisCommands, 5, Duration.ofSeconds(60), Duration.ofSeconds(20));
        var businessAPI = new BusinessAPI();

        for (int i = 0; i < 60; i++) {
            IO.println(String.format("Execution %d", i));

            if(ratelimiter.requestAllowed("Marcelo")) {
                businessAPI.doSomething("Marcelo");
            } else {
                IO.println("Request not allowed");
            }
            Thread.sleep(1000 * 10);
        }
    }
}
