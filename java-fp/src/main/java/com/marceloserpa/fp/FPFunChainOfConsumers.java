package com.marceloserpa.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FPFunChainOfConsumers {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Sydney", "Dhaka", "New York", "London");

        Consumer<List<String>> upperConsumer = list -> {
            for(int i = 0; i<list.size();i++) {
                list.set(i, list.get(i).toUpperCase());
            }
        };

        Consumer<List<String>> printConsumer = list -> list.stream().forEach(System.out::println);

        // make a chain of consumers
        upperConsumer.andThen(printConsumer).accept(cities);

    }
}