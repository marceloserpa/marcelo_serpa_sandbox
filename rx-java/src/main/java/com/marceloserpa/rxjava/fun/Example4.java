package com.marceloserpa.rxjava.fun;

import rx.Observable;
import rx.schedulers.Schedulers;

public class Example4 {

    private static long startTime;

    public static void main(String[] args) {
        Observable.zip(
            slowFunction(300).subscribeOn(Schedulers.newThread()),
            slowFunction(200).subscribeOn(Schedulers.newThread()),
            slowFunction(100).subscribeOn(Schedulers.newThread()),
            (integer, integer2, integer3) -> {
                System.out.println("Combining results");
                return null;
            }
        ).subscribeOn(Schedulers.newThread())
         .doOnSubscribe(() -> startTime = System.currentTimeMillis()).doOnTerminate(() -> {
            long totalDuration = System.currentTimeMillis() - startTime;
            System.out.println("Total execution time: " + totalDuration + "ms");
        }).doOnTerminate(() -> {
            long totalDuration = System.currentTimeMillis() - startTime;
            System.out.println("Total execution time: " + totalDuration + "ms");
        }).toBlocking()
        .subscribe();
    }

    public static Observable<Integer> slowFunction(Integer waitingTime){
        return Observable.fromCallable(() -> {
            System.out.println("Sleeping " + waitingTime + "ms");
            Thread.sleep(waitingTime);
            return waitingTime;
        });
    }


}
