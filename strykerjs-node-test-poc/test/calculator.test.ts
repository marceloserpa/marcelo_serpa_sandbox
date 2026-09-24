import { test } from 'node:test';
import assert from 'node:assert/strict';
import { add, subtract, multiply, divide, max, isEven, classify } from '../src/calculator.ts';

test('add', () => {
  assert.equal(add(2, 3), 5);
  assert.equal(add(-2, 2), 0);
});

test('subtract', () => {
  assert.equal(subtract(5, 3), 2);
});

test('multiply', () => {
  assert.equal(multiply(4, 3), 12);
  assert.equal(multiply(0, 5), 0);
});

test('divide', () => {
  assert.equal(divide(10, 2), 5);
  assert.throws(() => divide(1, 0), /Division by zero/);
});

test('max', () => {
  assert.equal(max(3, 7), 7);
  assert.equal(max(7, 3), 7);
});

test('isEven', () => {
  assert.ok(isEven(4));
  assert.ok(!isEven(5));
});

// NOTE: intentionally weak — only exercises the "plain number" branch of
// classify, so Fizz/Buzz/FizzBuzz mutants are left to survive on purpose.
test('classify', () => {
  assert.equal(classify(7), '7');
});
