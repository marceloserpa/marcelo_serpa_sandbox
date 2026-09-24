package poc

object StringUtils {

  def reverse(s: String): String = s.reverse

  def isPalindrome(s: String): Boolean = {
    val cleaned = s.toLowerCase.filter(_.isLetterOrDigit)
    cleaned == cleaned.reverse
  }

  def capitalize(s: String): String =
    if (s.isEmpty) s else s.head.toUpper + s.tail

  def countVowels(s: String): Int =
    s.toLowerCase.count(c => "aeiou".contains(c))
}
