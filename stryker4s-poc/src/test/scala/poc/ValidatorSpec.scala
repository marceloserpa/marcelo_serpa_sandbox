package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValidatorSpec extends AnyFlatSpec with Matchers {

  "validatePassword" should "accept a strong password" in {
    Validator.validatePassword("Abcdef12") shouldBe Valid
  }

  it should "reject a password that is too short" in {
    Validator.validatePassword("Ab1") shouldBe a[Invalid]
  }
  // Note: deliberately no dedicated tests for the missing-digit-only or
  // missing-uppercase-only cases — leaves checkDigit/checkUpper mutants alive.

  "classifyAge" should "classify a child" in {
    Validator.classifyAge(5) shouldBe "child"
  }

  it should "classify a teen" in {
    Validator.classifyAge(15) shouldBe "teen"
  }

  it should "classify an adult" in {
    Validator.classifyAge(30) shouldBe "adult"
  }

  it should "classify a senior" in {
    Validator.classifyAge(70) shouldBe "senior"
  }
  // Note: deliberately no tests at the exact boundaries (0, 13, 20, 65) or
  // for negative ages — leaves the "<" boundary mutants alive.
}
