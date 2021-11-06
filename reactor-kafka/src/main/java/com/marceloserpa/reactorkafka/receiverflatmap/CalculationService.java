package com.marceloserpa.reactorkafka.receiverflatmap;

import reactor.core.publisher.Mono;

public class CalculationService {

    public Mono<Integer> sum(Integer a, Integer b){
        return Mono.just(a + b);
    }

}
