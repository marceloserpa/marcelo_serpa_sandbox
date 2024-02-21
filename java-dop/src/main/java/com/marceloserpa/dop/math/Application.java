package com.marceloserpa.dop.math;

public class Application {

    public static void main(String[] args) {
        var number1 = new ConstNode(5);
        var number2 = new ConstNode(2);
        var number3 = new ConstNode(10);
        var number4 = new ConstNode(39);

        //-1 * (5 + 2 - 10 + 39)
        var tree = new NegNode(new AddNode(new SubNode(new AddNode(number1, number2), number3), number4));
        System.out.println(MathTreeCalc.calculate(tree));
    }
}
