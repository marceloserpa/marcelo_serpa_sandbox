export function factorial(n: number): number {
  return n <= 1 ? 1 : n * factorial(n - 1);
}

export function fibonacci(n: number): number {
  let [a, b] = [0, 1];
  for (let i = 0; i < n; i++) {
    [a, b] = [b, a + b];
  }
  return a;
}

export function gcd(a: number, b: number): number {
  return b === 0 ? Math.abs(a) : gcd(b, a % b);
}

export function isPrime(n: number): boolean {
  if (n < 2) return false;
  for (let i = 2; i < n; i++) {
    if (n % i === 0) return false;
  }
  return true;
}

export function binarySearch(xs: number[], target: number): number {
  let low = 0;
  let high = xs.length - 1;
  while (low <= high) {
    const mid = Math.floor((low + high) / 2);
    if (xs[mid] === target) return mid;
    if (xs[mid] < target) low = mid + 1;
    else high = mid - 1;
  }
  return -1;
}
