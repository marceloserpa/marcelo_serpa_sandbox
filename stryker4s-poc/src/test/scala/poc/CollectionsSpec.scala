package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CollectionsSpec extends AnyFlatSpec with Matchers {

  "sum" should "add all elements" in {
    Collections.sum(List(1, 2, 3)) shouldBe 6
  }

  it should "be zero for an empty list" in {
    Collections.sum(Nil) shouldBe 0
  }

  "average" should "compute the mean of a non-empty list" in {
    Collections.average(List(2, 4, 6)) shouldBe Some(4.0)
  }
  // Note: deliberately no test for average(Nil) == None — leaves the
  // "xs.isEmpty" branch's mutants alive.

  "evens" should "keep only even numbers" in {
    Collections.evens(List(1, 2, 3, 4)) shouldBe List(2, 4)
  }

  "hasNegative" should "detect a negative number" in {
    Collections.hasNegative(List(1, -2, 3)) shouldBe true
  }

  it should "return false when there are none" in {
    Collections.hasNegative(List(1, 2, 3)) shouldBe false
  }

  "allPositive" should "be true when every element is positive" in {
    Collections.allPositive(List(1, 2, 3)) shouldBe true
  }

  it should "be false when one element isn't positive" in {
    Collections.allPositive(List(1, -2, 3)) shouldBe false
  }

  "max" should "return the largest element" in {
    Collections.max(List(3, 1, 4, 1, 5)) shouldBe Some(5)
  }
  // Note: deliberately no test for max(Nil) == None.

  "dedupe" should "remove duplicate elements" in {
    Collections.dedupe(List(1, 2, 2, 3, 1)) shouldBe List(1, 2, 3)
  }

  "take" should "return the first n elements" in {
    Collections.take(List(1, 2, 3, 4), 2) shouldBe List(1, 2)
  }
}
