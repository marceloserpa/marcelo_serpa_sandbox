package com.marceloserpa;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var context = new Context();

        Runnable task1 = () -> context.process(1L);
        Runnable task2 = () -> context.process(5L);

        new Thread(task1).start();
        new Thread(task2).start();
    }
}