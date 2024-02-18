package com.marceloserpa;

import com.marceloserpa.math.Sub;
import com.marceloserpa.math.Sum;

public class Main {
    public static void main(String[] args) {

        var sum = new Sum();
        System.out.println("sum: " + sum.exec(1,2));

        var sub = new Sub();
        System.out.println("sub: " + sub.exec(1,2));
    }
}


