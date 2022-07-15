package com.marceloserpa.webfluxsocket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventGenerator {

    private AtomicInteger counter = new AtomicInteger(0);

    private EventGeneratorService generatorService;

    @Autowired
    public EventGenerator(EventGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void generateEvent() {

        int count = counter.getAndIncrement();
        System.out.println("-------------------------------------------------: " + count);
        generatorService.onNext("created: " + count);
    }
}