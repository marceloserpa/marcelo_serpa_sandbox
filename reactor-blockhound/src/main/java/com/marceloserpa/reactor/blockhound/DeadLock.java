package com.marceloserpa.reactor.blockhound;

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class DeadLock {

    public static void main(String[] args) {
        BlockHound.install();

        Flux.range(0, Runtime.getRuntime().availableProcessors() * 2)
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    System.out.println("Running..");
                    CountDownLatch latch = new CountDownLatch(1);

                    Mono.delay(Duration.ofMillis(i * 100))
                            .subscribe(it -> latch.countDown());

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return i;
                })
                .blockLast();
    }

}
