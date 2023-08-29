package com.marceloserpa;

import jdk.incubator.concurrent.ScopedValue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var context = new Context();

        context.process(3L);

        context.process(8L);
        System.out.println("-----------------");

        Runnable task1 = () -> context.process(1L);
        Runnable task2 = () -> context.process(5L);

        new Thread(task1).start();
        new Thread(task2).start();

        System.out.println("End");


    }

}

