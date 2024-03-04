package com.marceloserpa.delayedqueue.fun;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        final BlockingQueue<DelayedMessage> delayQueue = new DelayQueue<>();

        delayQueue.offer(new DelayedMessage("1", 3));
        delayQueue.offer(new DelayedMessage("2", 2));
        delayQueue.offer(new DelayedMessage("3", 1));
        delayQueue.offer(new DelayedMessage("4", 1));
        delayQueue.offer(new DelayedMessage("5", 1));


        System.out.println(delayQueue.take().getValue());
        System.out.println(delayQueue.take().getValue());
        System.out.println(delayQueue.take().getValue());
        System.out.println(delayQueue.take().getValue());
        System.out.println(delayQueue.take().getValue());


    }
}