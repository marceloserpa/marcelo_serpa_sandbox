package com.marceloserpa.strampedlock.fun;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class Application {
    public static void main(String[] args) {

        StampedLock lock = new StampedLock();
        var balance = new Balance(1000);

        Runnable write = () -> {
            long stamp = lock.writeLock();
            balance.addAmount(1000);
            System.out.println("Write " + balance.getAmount());
            lock.unlockWrite(stamp);
        };

        Runnable read = () -> {
            long stamp = lock.tryOptimisticRead();
            if(!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    System.out.println("Read: " + balance.getAmount() );
                } finally {
                    lock.unlockRead(stamp);
                }
            } else {
                System.out.println("Optimistic read fails");
            }
        };


        try(var executor = Executors.newFixedThreadPool(10)){
            for(int i=0;i < 50; i++){
                executor.submit(write);
                executor.submit(read);
            }
        }
    }
}