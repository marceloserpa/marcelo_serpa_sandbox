package poc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StringUtilsSpec extends AnyFlatSpec with Matchers {

  "reverse" should "reverse a string" in {
    StringUtils.reverse("hello") shouldBe "olleh"
  }

  "isPalindrome" should "recognize a palindrome ignoring case and punctuation" in {
    StringUtils.isPalindrome("A man, a plan, a canal: Panama") shouldBe true
  }

  it should "reject a non-palindrome" in {
    StringUtils.isPalindrome("hello") shouldBe false
  }

  "capitalize" should "capitalize the first letter" in {
    StringUtils.capitalize("world") shouldBe "World"
  }

  it should "return an empty string unchanged" in {
    StringUtils.capitalize("") shouldBe ""
  }

  "countVowels" should "count vowels in a word" in {
    StringUtils.countVowels("hello") shouldBe 2
  }
  // Note: deliberately no test for a string with zero vowels or with
  // uppercase vowels — leaves some countVowels mutants alive as a demo.
}
