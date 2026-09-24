export type ValidationResult = { valid: true } | { valid: false; reasons: string[] };

export function checkLength(password: string): string | undefined {
  return password.length < 8 ? 'Password must be at least 8 characters' : undefined;
}

export function checkDigit(password: string): string | undefined {
  return /\d/.test(password) ? undefined : 'Password must contain a digit';
}

export function checkUpper(password: string): string | undefined {
  return /[A-Z]/.test(password) ? undefined : 'Password must contain an uppercase letter';
}

export function validatePassword(password: string): ValidationResult {
  const reasons = [checkLength(password), checkDigit(password), checkUpper(password)].filter(
    (reason): reason is string => reason !== undefined,
  );
  return reasons.length === 0 ? { valid: true } : { valid: false, reasons };
}

export function classifyAge(age: number): string {
  if (age < 0) return 'invalid';
  if (age < 13) return 'child';
  if (age < 20) return 'teen';
  if (age < 65) return 'adult';
  return 'senior';
}
