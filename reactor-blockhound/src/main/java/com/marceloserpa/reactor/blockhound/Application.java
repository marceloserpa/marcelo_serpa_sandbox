package com.marceloserpa.reactor.blockhound;

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Application {

    public static void main(String[] args) {

        BlockHound.install();

        Mono.delay(Duration.ofSeconds(1))
                .doOnNext(it -> {
                    try {
                        System.out.println("STARTING: >>>>>>>>>>>>>>>");
                        Thread.sleep(10);
                        System.out.println("COMPLETED: >>>>>>>>>>>>>>>");
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .block();


    }

}
