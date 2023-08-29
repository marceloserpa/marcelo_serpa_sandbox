package com.marceloserpa;

import jdk.incubator.concurrent.ScopedValue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ScopedValue.where(Context.LoggedUserID, 1L, () -> {
            System.out.println("ID: " + Context.LoggedUserID.get());
        });


    }
}

