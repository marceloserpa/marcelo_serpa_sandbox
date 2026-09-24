export function sum(xs: number[]): number {
  return xs.reduce((acc, x) => acc + x, 0);
}

export function average(xs: number[]): number | undefined {
  if (xs.length === 0) return undefined;
  return sum(xs) / xs.length;
}

export function evens(xs: number[]): number[] {
  return xs.filter((x) => x % 2 === 0);
}

export function hasNegative(xs: number[]): boolean {
  return xs.some((x) => x < 0);
}

export function allPositive(xs: number[]): boolean {
  return xs.every((x) => x > 0);
}

export function max(xs: number[]): number | undefined {
  if (xs.length === 0) return undefined;
  return Math.max(...xs);
}

export function dedupe(xs: number[]): number[] {
  return [...new Set(xs)];
}

export function take(xs: number[], n: number): number[] {
  return xs.slice(0, n);
}
