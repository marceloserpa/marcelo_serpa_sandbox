import { test } from 'node:test';
import assert from 'node:assert/strict';
import { checkLength, checkDigit, checkUpper, validatePassword, classifyAge } from '../src/validator.ts';

test('checkLength', () => {
  assert.equal(checkLength('short'), 'Password must be at least 8 characters');
  assert.equal(checkLength('longenough'), undefined);
});

test('checkDigit', () => {
  assert.equal(checkDigit('nodigits'), 'Password must contain a digit');
  assert.equal(checkDigit('has1digit'), undefined);
});

test('checkUpper', () => {
  assert.equal(checkUpper('nouppercase'), 'Password must contain an uppercase letter');
  assert.equal(checkUpper('hasUpper'), undefined);
});

test('validatePassword', () => {
  assert.deepEqual(validatePassword('Aa1longenough'), { valid: true });
  assert.deepEqual(validatePassword('short'), {
    valid: false,
    reasons: [
      'Password must be at least 8 characters',
      'Password must contain a digit',
      'Password must contain an uppercase letter',
    ],
  });
});

// NOTE: intentionally weak — only exercises the "adult" branch, so the
// child/teen/senior/invalid boundary mutants are left to survive on purpose.
test('classifyAge', () => {
  assert.equal(classifyAge(30), 'adult');
});
