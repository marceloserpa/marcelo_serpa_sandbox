package com.marceloserpa.fp;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FPFunSupplier {
    public static void main(String[] args) {
        Supplier<Double> doubleSupplier1 = () -> Math.random();
        DoubleSupplier doubleSupplier2 = Math::random;

        System.out.println(doubleSupplier1.get());
        System.out.println(doubleSupplier2.getAsDouble());

        // Delay the execution to only when it is really needed
        Optional<Double> number = Optional.empty();

        System.out.println(number.orElseGet(doubleSupplier1));
    }
}