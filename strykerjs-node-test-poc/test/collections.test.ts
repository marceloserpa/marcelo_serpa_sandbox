import { test } from 'node:test';
import assert from 'node:assert/strict';
import { sum, average, evens, hasNegative, allPositive, max } from '../src/collections.ts';

// NOTE: `dedupe` and `take` are intentionally left untested to demonstrate
// Stryker's "no coverage" mutants — the weakest possible test signal.

test('sum', () => {
  assert.equal(sum([1, 2, 3]), 6);
  assert.equal(sum([]), 0);
});

test('average', () => {
  assert.equal(average([2, 4, 6]), 4);
  assert.equal(average([]), undefined);
});

test('evens', () => {
  assert.deepEqual(evens([1, 2, 3, 4]), [2, 4]);
});

test('hasNegative', () => {
  assert.ok(hasNegative([1, -2, 3]));
  assert.ok(!hasNegative([1, 2, 3]));
});

test('allPositive', () => {
  assert.ok(allPositive([1, 2, 3]));
  assert.ok(!allPositive([1, -2, 3]));
});

test('max', () => {
  assert.equal(max([3, 1, 4, 1, 5]), 5);
  assert.equal(max([]), undefined);
});
