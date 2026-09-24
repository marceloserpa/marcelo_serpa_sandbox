import { test } from 'node:test';
import assert from 'node:assert/strict';
import { subtotal, total, validateOrder } from '../src/orderProcessor.ts';

test('subtotal', () => {
  assert.equal(subtotal({ quantity: 3, unitPrice: 10 }), 30);
});

// NOTE: intentionally weak — only exercises the >=100 discount tier, so
// mutants in the 50/10/0 tier boundaries of discountRate survive.
test('total', () => {
  assert.equal(total({ quantity: 100, unitPrice: 10 }), 800);
});

test('validateOrder', () => {
  assert.deepEqual(validateOrder({ quantity: 5, unitPrice: 10 }), { ok: true, order: { quantity: 5, unitPrice: 10 } });
  assert.deepEqual(validateOrder({ quantity: 0, unitPrice: 10 }), { ok: false, reason: 'Quantity must be positive' });
  assert.deepEqual(validateOrder({ quantity: 5, unitPrice: -1 }), { ok: false, reason: 'Unit price cannot be negative' });
});
