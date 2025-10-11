package com.marceloserpa.ratelimiter.limiter;

public interface RateLimiter {

    boolean requestAllowed(String user);

}
