package com.marceloserpa.ratelimiter.limiter;

import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

public class FixedWindowRateLimiter implements RateLimiter{

    private int requestPerMinute = 5;
    private Duration window;

    private RedisCommands<String, String> commands;

    public FixedWindowRateLimiter(RedisCommands<String, String> commands, int requestPerMinute, Duration window) {
        this.commands = commands;
        this.requestPerMinute = requestPerMinute;
        this.window = window;
    }

    @Override
    public boolean requestAllowed(String user){
        var key = String.format("rate_limiter:%s", user);
        Long incr = commands.incr(key);
        if (incr == 1) {
            commands.expire(key, window);
        } else if (incr > requestPerMinute) {
            IO.println("Number of requests exceeded");
            return false;
        }
        return true;
    }


}
