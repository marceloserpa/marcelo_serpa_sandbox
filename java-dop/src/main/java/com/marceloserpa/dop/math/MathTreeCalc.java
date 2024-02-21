package com.marceloserpa.dop.math;

public class MathTreeCalc {

    public static double calculate(Node n){
        return switch (n) {
            case AddNode(var left, var right) -> calculate(left) + calculate(right);
            case SubNode(var left, var right) -> calculate(left) - calculate(right);
            case NegNode(var node) -> calculate(node) * -1;
            case ConstNode(double val) -> val;
        };
    }

}
