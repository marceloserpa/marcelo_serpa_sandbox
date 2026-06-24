package com.marceloserpa.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * INTENTIONALLY WEAK tests. The goal is to study Pitest: run it and watch
 * mutants SURVIVE here, then strengthen the tests until they are killed.
 * Each comment marks why the test fails to kill the relevant mutant.
 */
class NumberRulesTest {

    private final NumberRules rules = new NumberRules();

    // WEAK: 2*2 == 2+2 == 4. The * mutated to + (and others) gives the same
    // result, so the Math mutant survives. Fix: use inputs like 3 and 4.
    @Test
    void multiply() {
        assertEquals(4, rules.multiply(2, 2));
    }

    // WEAK: 0+0 == 0-0. The +->- mutant survives. Fix: use non-equal inputs.
    @Test
    void add() {
        assertEquals(0, rules.add(0, 0));
    }

    // WEAK: never tests the boundary age=18, only a clearly-adult value.
    // >= mutated to > survives. Fix: assert isAdult(18) == true.
    @Test
    void isAdult() {
        assertTrue(rules.isAdult(25));
    }

    // WEAK: only asserts the "non-zero" branch. == mutated to != survives
    // because the zero branch is never checked. Fix: also assert classify(0).
    @Test
    void classify() {
        assertEquals("non-zero", rules.classify(5));
    }

    // WEAK: never passes b=0, so removing the guard changes nothing observable.
    // Remove-conditional mutant survives. Fix: assert safeDiv(10, 0) == 0.
    @Test
    void safeDiv() {
        assertEquals(5, rules.safeDiv(10, 2));
    }

    // WEAK: only the "true" case is tested. return-false / && -> || mutants
    // survive. Fix: also assert inRange(-1), inRange(150) are false.
    @Test
    void inRange() {
        assertTrue(rules.inRange(50));
    }

    // WEAK: calls the method but asserts nothing -> every return mutant
    // (empty string, null) survives. Fix: assertEquals("Hello, Sam", ...).
    @Test
    void greet() {
        rules.greet("Sam");
    }

    // WEAK: only checks the result is positive, not its exact value.
    // ++ mutated to -- still returns a value; weak assert lets some survive.
    // Fix: assert exact sequence 1, 2, 3 across repeated calls.
    @Test
    void hit() {
        assertTrue(rules.hit() > -100);
    }

    // WEAK: negate(0) == 0 whether or not the minus is inverted.
    // Invert-negatives mutant survives. Fix: use a non-zero input.
    @Test
    void negate() {
        assertEquals(0, rules.negate(0));
    }

    // WEAK: uses a valid name, so removing the validate(...) call is invisible.
    // Void-method-call mutant survives. Fix: assert register("") throws.
    @Test
    void register() {
        assertEquals("registered:Sam", rules.register("Sam"));
    }
}
