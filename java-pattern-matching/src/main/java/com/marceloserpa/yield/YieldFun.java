package com.marceloserpa.yield;

public class YieldFun {

    public enum Number {ONE, TWO, THREE, FOUR}

    public static void main(String[] args) {

        Number number = Number.FOUR;

        var message = switch (number) {
            case ONE: yield "This is 1";
            case TWO: yield "This is 2";
            case THREE, FOUR : yield "More than 2";
            case null: yield "not valid";
        };

        System.out.println("Message: " + message);




    }



}
