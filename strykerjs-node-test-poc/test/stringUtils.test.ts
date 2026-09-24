import { test } from 'node:test';
import assert from 'node:assert/strict';
import { reverse, isPalindrome, capitalize, countVowels } from '../src/stringUtils.ts';

test('reverse', () => {
  assert.equal(reverse('hello'), 'olleh');
  assert.equal(reverse(''), '');
});

test('isPalindrome', () => {
  assert.ok(isPalindrome('A man a plan a canal Panama'));
  assert.ok(!isPalindrome('hello'));
});

test('capitalize', () => {
  assert.equal(capitalize('hello'), 'Hello');
  assert.equal(capitalize(''), '');
});

test('countVowels', () => {
  assert.equal(countVowels('hello world'), 3);
  assert.equal(countVowels('xyz'), 0);
});
