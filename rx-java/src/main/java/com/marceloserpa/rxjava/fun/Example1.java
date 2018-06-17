package com.marceloserpa.rxjava.fun;

import rx.Observable;

public class Example1 {

    public static void main(String[] args) {
        hello("Goku", "Gohan", "Vegetta");
        hello("Naruto", "Obito");
        hello("Yusei");
    }

    private static void hello(String... names){
        Observable.from(names)
            .subscribe((String name) -> {
                System.out.println("Hello " + name + "!");
            });
    }

}
