package com.marceloserpa;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// Semaphore works like pool of permits
public class SemaphorePoC {

    private static final Semaphore POOL = new Semaphore(3);
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        SemaphorePoC semaphorePoC = new SemaphorePoC();

        try(ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < 10; i++) {
                service.submit(() -> {
                    try {
                        System.out.println(semaphorePoC.callService());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        }

    }

    public Integer callService() throws InterruptedException {
        POOL.acquire();
        try {
            System.out.println("Service called");
            return externalService();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            POOL.release();
        }
        return null;
    }

    public Integer externalService() throws InterruptedException {
        Thread.sleep(1000);
        return RANDOM.nextInt(100);
    }




}
