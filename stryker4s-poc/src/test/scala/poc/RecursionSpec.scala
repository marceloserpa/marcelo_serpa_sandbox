package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RecursionSpec extends AnyFlatSpec with Matchers {

  "factorial" should "compute 0! as 1" in {
    Recursion.factorial(0) shouldBe 1L
  }

  it should "compute 5! as 120" in {
    Recursion.factorial(5) shouldBe 120L
  }

  "fibonacci" should "compute the first two terms" in {
    Recursion.fibonacci(0) shouldBe 0L
    Recursion.fibonacci(1) shouldBe 1L
  }

  it should "compute the 10th term" in {
    Recursion.fibonacci(10) shouldBe 55L
  }

  "gcd" should "find the greatest common divisor" in {
    Recursion.gcd(12, 8) shouldBe 4
  }

  it should "return 1 for coprime numbers" in {
    Recursion.gcd(7, 13) shouldBe 1
  }
  // Note: deliberately no test for gcd(0, n) — leaves the "a.abs" branch's
  // mutants alive.

  "isPrime" should "reject numbers below 2" in {
    Recursion.isPrime(1) shouldBe false
  }

  it should "detect a prime" in {
    Recursion.isPrime(7) shouldBe true
  }

  it should "detect a non-prime" in {
    Recursion.isPrime(8) shouldBe false
  }
  // Note: deliberately no test for isPrime(2) — the smallest prime is an
  // edge case ("2 until n" is an empty range) that some mutants exploit.

  "binarySearch" should "find an element in the middle" in {
    Recursion.binarySearch(Array(1, 3, 5, 7, 9), 5) shouldBe 2
  }

  it should "find an element at the edges" in {
    Recursion.binarySearch(Array(1, 3, 5, 7, 9), 1) shouldBe 0
    Recursion.binarySearch(Array(1, 3, 5, 7, 9), 9) shouldBe 4
  }

  it should "return -1 when the element is absent" in {
    Recursion.binarySearch(Array(1, 3, 5, 7, 9), 4) shouldBe -1
  }
}
