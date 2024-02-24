package com.marceloserpa.fp;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class FPFunConsumer {
    public static void main(String[] args) {
        Consumer<String> printConsumer = text -> System.out.println(text);
        Stream<String> cities = Stream.of("Sydney", "Dhaka", "New York", "London");
        cities.forEach(printConsumer);
    }
}