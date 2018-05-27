package com.marceloserpa.uber.nullaway.poc;

import javax.annotation.Nullable;


public class Application {

    public static void main(String[] args) {

        Object x = null;
        x.toString();
        foo();
    }

    static void log(@Nullable Object x) {
        System.out.println(x.toString());
    }
    static void foo() {
        log(null);
    }
}
