package poc

object Calculator {

  def add(a: Int, b: Int): Int = a + b

  def subtract(a: Int, b: Int): Int = a - b

  def multiply(a: Int, b: Int): Int = a * b

  def divide(a: Int, b: Int): Double = {
    if (b == 0) throw new ArithmeticException("Division by zero")
    a.toDouble / b.toDouble
  }

  def max(a: Int, b: Int): Int = if (a > b) a else b

  def isEven(n: Int): Boolean = n % 2 == 0

  def classify(n: Int): String = {
    if (n % 15 == 0) "FizzBuzz"
    else if (n % 3 == 0) "Fizz"
    else if (n % 5 == 0) "Buzz"
    else n.toString
  }
}
