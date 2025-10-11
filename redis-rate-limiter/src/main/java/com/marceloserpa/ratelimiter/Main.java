package com.marceloserpa.ratelimiter;

import com.marceloserpa.ratelimiter.limiter.FixedWindowRateLimiter;
import com.marceloserpa.ratelimiter.limiter.RateLimiter;
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

        RateLimiter ratelimiter = new FixedWindowRateLimiter(redisCommands, 5, Duration.ofSeconds(60));
        var businessAPI = new BusinessAPI();

        for (int i = 0; i < 60; i++) {
            IO.println(String.format("Executation %d", i));

            if(ratelimiter.requestAllowed("Marcelo")) {
                businessAPI.doSomething("Marcelo");
            }
            Thread.sleep(1000 * 2);
        }
    }
}
