export function reverse(s: string): string {
  return s.split('').reverse().join('');
}

export function isPalindrome(s: string): boolean {
  const cleaned = s.toLowerCase().replace(/[^a-z0-9]/g, '');
  return cleaned === reverse(cleaned);
}

export function capitalize(s: string): string {
  if (s.length === 0) return s;
  return s[0].toUpperCase() + s.slice(1);
}

export function countVowels(s: string): number {
  const matches = s.toLowerCase().match(/[aeiou]/g);
  return matches ? matches.length : 0;
}
