import { test } from 'node:test';
import assert from 'node:assert/strict';
import { factorial, fibonacci, gcd, isPrime, binarySearch } from '../src/recursion.ts';

test('factorial', () => {
  assert.equal(factorial(0), 1);
  assert.equal(factorial(5), 120);
});

test('fibonacci', () => {
  assert.equal(fibonacci(0), 0);
  assert.equal(fibonacci(10), 55);
});

test('gcd', () => {
  assert.equal(gcd(48, 18), 6);
  assert.equal(gcd(17, 5), 1);
});

// NOTE: intentionally weak — only checks the "obviously prime" happy path,
// so boundary mutants around n<2 and the loop condition survive.
test('isPrime', () => {
  assert.ok(isPrime(7));
});

// NOTE: intentionally weak — only checks the "found" path, so mutants in the
// not-found / boundary handling (low/high/mid arithmetic) survive.
test('binarySearch', () => {
  assert.equal(binarySearch([1, 3, 5, 7, 9], 7), 3);
});
