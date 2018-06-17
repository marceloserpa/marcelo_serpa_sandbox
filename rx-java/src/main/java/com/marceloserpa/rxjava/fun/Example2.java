package com.marceloserpa.rxjava.fun;

import rx.Observable;
import rx.Subscriber;

public class Example2 {

    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d", "e", "f")
            .take(4)
            .subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    System.out.println("Completed");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("Error");
                }

                @Override
                public void onNext(String letter) {
                    System.out.println("Next: " + letter);
                }
            });

    }

}
