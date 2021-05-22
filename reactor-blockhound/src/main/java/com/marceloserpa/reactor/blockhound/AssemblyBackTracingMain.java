package com.marceloserpa.reactor.blockhound;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

public class AssemblyBackTracingMain {

    public static void main(String[] args) {
       Hooks.onOperatorDebug();

        Flux.range(0, 5)
                    .single()
                   .subscribeOn(Schedulers.parallel())
                    .block();
    }

}
