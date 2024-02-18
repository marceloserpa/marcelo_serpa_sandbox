package com.marceloserpa;


import java.util.concurrent.*;
import java.util.random.RandomGenerator;

public class VirtualThreadsPoC {

    private static ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Hello World!");

        Callable<Integer> generateRandomInteger = new Callable<Integer>() {
            static RandomGenerator generator = RandomGenerator.getDefault();
            @Override
            public Integer call() throws Exception {
                return generator.nextInt();
            }
        };

        Future<Integer> call1 = service.submit(generateRandomInteger);
        Future<Integer> call2 = service.submit(generateRandomInteger);
        Future<Integer> call3 = service.submit(generateRandomInteger);

        System.out.println(call1.get() + call2.get() + call3.get());
    }
}