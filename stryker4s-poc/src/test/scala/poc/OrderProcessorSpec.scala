package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import poc.OrderProcessor.Order

class OrderProcessorSpec extends AnyFlatSpec with Matchers {

  "subtotal" should "multiply quantity by unit price" in {
    OrderProcessor.subtotal(Order(3, 10.0)) shouldBe 30.0
  }

  "total" should "apply no discount below the smallest tier" in {
    OrderProcessor.total(Order(5, 10.0)) shouldBe 50.0
  }

  it should "apply the top discount tier for large orders" in {
    OrderProcessor.total(Order(100, 10.0)) shouldBe 800.0
  }
  // Note: deliberately no tests exactly at quantity 10, 50, or 100 (the
  // discount tier boundaries) — leaves the ">=" boundary mutants alive.

  "validateOrder" should "accept a valid order" in {
    OrderProcessor.validateOrder(Order(1, 10.0)) shouldBe Right(Order(1, 10.0))
  }

  it should "reject a non-positive quantity" in {
    OrderProcessor.validateOrder(Order(0, 10.0)) shouldBe Left("Quantity must be positive")
  }
  // Note: deliberately no test for a negative unit price — leaves that
  // branch's mutants alive.
}
