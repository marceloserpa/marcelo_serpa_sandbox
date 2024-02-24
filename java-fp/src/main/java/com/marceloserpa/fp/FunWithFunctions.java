package com.marceloserpa.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunWithFunctions {
    public static void main(String[] args) {
        List<String> cities = List.of("Sydney", "Dhaka", "New York", "London", "São Paulo");
        Function<String, Integer> getLength = String::length;

        List<Integer> cityNameLength = cities.stream().map(getLength).collect(Collectors.toList());
        System.out.println(cityNameLength);

    }
}
