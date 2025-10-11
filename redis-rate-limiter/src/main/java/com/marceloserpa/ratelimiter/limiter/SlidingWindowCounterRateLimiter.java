package com.marceloserpa.ratelimiter.limiter;

import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.util.Map;

public class SlidingWindowCounterRateLimiter implements RateLimiter {

    private int requestPerMinute = 5;
    private Duration window;
    private Duration subWindow;

    private RedisCommands<String, String> commands;

    public SlidingWindowCounterRateLimiter(RedisCommands<String, String> commands,
                                           int requestPerMinute,
                                           Duration window,
                                           Duration subWindow) {
        this.commands = commands;
        this.requestPerMinute = requestPerMinute;
        this.window = window;
        this.subWindow = subWindow;
    }

    @Override
    public boolean requestAllowed(String user) {

        var key = String.format("rate_limiter:%s", user);
        Map<String, String> subWindowCounts = commands.hgetall(key);

        IO.println(subWindowCounts);

        long total = subWindowCounts.values().stream().mapToLong(Long::parseLong).sum();

        IO.println(total);

        if (total < requestPerMinute) {
            long currentTime = System.currentTimeMillis();
            long subWindowSize = subWindow.getSeconds() * 1000;

            long currentWindow = currentTime / subWindowSize;

            commands.multi();

            commands.hincrby(key, Long.toString(currentWindow), 1);
            commands.hexpire(key, window, Long.toString(currentWindow));

            TransactionResult exec = commands.exec();

            if(exec.isEmpty()){
                throw new RuntimeException("Failed");
            }
            return true;
        }
        return false;
    }

}
