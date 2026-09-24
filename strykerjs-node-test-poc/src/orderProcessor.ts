export type Order = { quantity: number; unitPrice: number };

export function subtotal(order: Order): number {
  return order.quantity * order.unitPrice;
}

export function discountRate(quantity: number): number {
  if (quantity >= 100) return 0.2;
  if (quantity >= 50) return 0.1;
  if (quantity >= 10) return 0.05;
  return 0;
}

export function total(order: Order): number {
  const sub = subtotal(order);
  return sub - sub * discountRate(order.quantity);
}

export function validateOrder(order: Order): { ok: true; order: Order } | { ok: false; reason: string } {
  if (order.quantity <= 0) return { ok: false, reason: 'Quantity must be positive' };
  if (order.unitPrice < 0) return { ok: false, reason: 'Unit price cannot be negative' };
  return { ok: true, order };
}
