package com.marceloserpa.accumulator;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class Main {
    public static void main(String[] args) {
        var balanceAcc = new LongAccumulator(Long::sum, 10_000L);
        Runnable write = () -> balanceAcc.accumulate(1_000L);
        try(var executor = Executors.newFixedThreadPool(50)) {
            for(int i=0; i < 50; i++) {
                executor.submit(write);
            }
            executor.shutdown();
            if(executor.awaitTermination(1_000L, TimeUnit.MILLISECONDS)) {
                System.out.println("Balance: " + balanceAcc.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assert  balanceAcc.get() == 60_000L;
    }
}