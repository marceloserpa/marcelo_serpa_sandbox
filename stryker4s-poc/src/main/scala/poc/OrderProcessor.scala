package poc

object OrderProcessor {

  final case class Order(quantity: Int, unitPrice: Double)

  def subtotal(order: Order): Double = order.quantity * order.unitPrice

  def discountRate(quantity: Int): Double =
    if (quantity >= 100) 0.20
    else if (quantity >= 50) 0.10
    else if (quantity >= 10) 0.05
    else 0.0

  def total(order: Order): Double = {
    val sub = subtotal(order)
    sub - sub * discountRate(order.quantity)
  }

  def validateOrder(order: Order): Either[String, Order] =
    if (order.quantity <= 0) Left("Quantity must be positive")
    else if (order.unitPrice < 0) Left("Unit price cannot be negative")
    else Right(order)
}
