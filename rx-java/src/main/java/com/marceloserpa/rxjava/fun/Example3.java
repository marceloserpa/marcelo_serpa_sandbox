package com.marceloserpa.rxjava.fun;

import rx.Observable;

public class Example3 {

    private static long startTime;

    public static void main(String[] args) {
        Observable.zip(
            slowFunction(300),
            slowFunction(250),
            slowFunction(400),
            (integer, integer2, integer3) -> null
        ).doOnSubscribe(() -> startTime = System.currentTimeMillis()).doOnTerminate(() -> {
            long totalDuration = System.currentTimeMillis() - startTime;
            System.out.println("Total execution time: " + totalDuration + "ms");
        }).subscribe();
    }

    public static Observable<Integer> slowFunction(Integer waitingTime){
        return Observable.fromCallable(() -> {
            System.out.println("Sleeping " + waitingTime + "ms");
            Thread.sleep(waitingTime);
            return waitingTime;
        });
    }


}
