package com.marceloserpa.delayedqueue.fun;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedMessage implements Delayed {

    private final long ttl;
    private final String value;

    private final long startTime;

    public DelayedMessage(String value, long ttlSeconds) {
        this.ttl = ttlSeconds * 1000;
        this.value = value;
        this.startTime = System.currentTimeMillis() + this.ttl;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.startTime, ((DelayedMessage) o).startTime);
    }

    public String getValue(){
        return this.value;
    }
}
