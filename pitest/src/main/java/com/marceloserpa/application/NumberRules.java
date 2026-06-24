package com.marceloserpa.application;

/**
 * A deliberately small class whose methods each map to one (or more) Pitest mutators.
 * Pair this with NumberRulesTest, which is intentionally WEAK so surviving mutants
 * are easy to spot in the Pitest report.
 */
public class NumberRules {

    // [Math] + - * / %  --> e.g. * mutated to /
    public int multiply(int price, int qty) {
        return price * qty;
    }

    // [Math] addition --> e.g. + mutated to -
    public int add(int a, int b) {
        return a + b;
    }

    // [Conditionals Boundary] >= mutated to >  (off-by-one at the boundary)
    public boolean isAdult(int age) {
        return age >= 18;
    }

    // [Negate Conditionals] == mutated to !=  (both branches must be asserted)
    public String classify(int n) {
        if (n == 0) {
            return "zero";
        }
        return "non-zero";
    }

    // [Remove Conditionals] the div-by-zero guard can be removed by a mutant
    public int safeDiv(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    // [Boolean Return / Negate / short-circuit] true<->false, && <-> ||
    public boolean inRange(int x) {
        return x > 0 && x < 100;
    }

    // [Empty/Null Return] returned String can be replaced with "" or null
    public String greet(String name) {
        return "Hello, " + name;
    }

    // [Increments] ++ mutated to --
    private int counter = 0;

    public int hit() {
        counter++;
        return counter;
    }

    // [Invert Negatives] -x mutated to x
    public int negate(int x) {
        return -x;
    }

    // [Void Method Call] the call to validate(...) can be removed by a mutant
    public String register(String name) {
        validate(name);
        return "registered:" + name;
    }

    private void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name required");
        }
    }
}
