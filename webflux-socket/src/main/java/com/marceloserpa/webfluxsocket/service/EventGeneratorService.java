package com.marceloserpa.webfluxsocket.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Service
public class EventGeneratorService {

    private EmitterProcessor<String> processor = EmitterProcessor.create();

    public void onNext(String next) {
        processor.onNext(next);
    }

    public Flux<String> getMessages() {
        return processor.publish().autoConnect();
    }
}