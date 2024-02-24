package com.marceloserpa.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunWithPredicates {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Sydney", "Dhaka", "New York", "London", "São Paulo");
        Predicate<String> startWithS = text -> text.startsWith("S");
        Predicate<String> lengthLessThan6 = text -> text.length() <= 6;

        cities.stream().filter(startWithS.and(lengthLessThan6)).forEach(System.out::println);
    }
}
