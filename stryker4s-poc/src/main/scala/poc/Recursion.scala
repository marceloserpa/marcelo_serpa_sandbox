package poc

object Recursion {

  def factorial(n: Int): Long = {
    @annotation.tailrec
    def loop(acc: Long, i: Int): Long = if (i <= 1) acc else loop(acc * i, i - 1)
    loop(1L, n)
  }

  def fibonacci(n: Int): Long = {
    @annotation.tailrec
    def loop(a: Long, b: Long, i: Int): Long = if (i == 0) a else loop(b, a + b, i - 1)
    loop(0L, 1L, n)
  }

  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

  def isPrime(n: Int): Boolean =
    if (n < 2) false
    else (2 until n).forall(i => n % i != 0)

  def binarySearch(xs: Array[Int], target: Int): Int = {
    var low = 0
    var high = xs.length - 1
    var result = -1
    while (low <= high && result == -1) {
      val mid = (low + high) / 2
      if (xs(mid) == target) result = mid
      else if (xs(mid) < target) low = mid + 1
      else high = mid - 1
    }
    result
  }
}
