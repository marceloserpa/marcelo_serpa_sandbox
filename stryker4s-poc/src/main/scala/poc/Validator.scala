package poc

sealed trait ValidationResult
case object Valid extends ValidationResult
final case class Invalid(reasons: List[String]) extends ValidationResult

object Validator {

  def checkLength(password: String): Option[String] =
    if (password.length < 8) Some("Password must be at least 8 characters") else None

  def checkDigit(password: String): Option[String] =
    if (!password.exists(_.isDigit)) Some("Password must contain a digit") else None

  def checkUpper(password: String): Option[String] =
    if (!password.exists(_.isUpper)) Some("Password must contain an uppercase letter") else None

  def validatePassword(password: String): ValidationResult = {
    val reasons = List(checkLength(password), checkDigit(password), checkUpper(password)).flatten
    if (reasons.isEmpty) Valid else Invalid(reasons)
  }

  def classifyAge(age: Int): String = age match {
    case a if a < 0  => "invalid"
    case a if a < 13 => "child"
    case a if a < 20 => "teen"
    case a if a < 65 => "adult"
    case _           => "senior"
  }
}
