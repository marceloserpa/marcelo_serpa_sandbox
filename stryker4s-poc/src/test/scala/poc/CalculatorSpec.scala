package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CalculatorSpec extends AnyFlatSpec with Matchers {

  "add" should "sum two numbers" in {
    Calculator.add(2, 3) shouldBe 5
  }

  "subtract" should "subtract two numbers" in {
    Calculator.subtract(5, 3) shouldBe 2
  }

  "multiply" should "multiply two numbers" in {
    Calculator.multiply(4, 3) shouldBe 12
  }

  "divide" should "divide two numbers" in {
    Calculator.divide(10, 2) shouldBe 5.0
  }

  it should "throw on division by zero" in {
    an[ArithmeticException] should be thrownBy Calculator.divide(1, 0)
  }

  "max" should "return the first number when it's bigger" in {
    Calculator.max(5, 3) shouldBe 5
  }

  it should "return the second number when it's bigger" in {
    Calculator.max(3, 5) shouldBe 5
  }
  // Note: deliberately no test for max(a, a) — leaves the ">" vs ">="
  // boundary mutant alive as a demo of an undetected mutant.

  "isEven" should "identify even numbers" in {
    Calculator.isEven(4) shouldBe true
  }

  it should "identify odd numbers" in {
    Calculator.isEven(3) shouldBe false
  }

  "classify" should "return Fizz for multiples of 3" in {
    Calculator.classify(3) shouldBe "Fizz"
  }

  it should "return Buzz for multiples of 5" in {
    Calculator.classify(5) shouldBe "Buzz"
  }

  it should "return the number as a string otherwise" in {
    Calculator.classify(7) shouldBe "7"
  }
  // Note: deliberately no test for classify(15) == "FizzBuzz" — leaves the
  // "n % 15 == 0" branch's mutants alive as a second demo survivor.
}
