package poc

object Collections {

  def sum(xs: List[Int]): Int = xs.sum

  def average(xs: List[Int]): Option[Double] =
    if (xs.isEmpty) None else Some(xs.sum.toDouble / xs.size)

  def evens(xs: List[Int]): List[Int] = xs.filter(_ % 2 == 0)

  def hasNegative(xs: List[Int]): Boolean = xs.exists(_ < 0)

  def allPositive(xs: List[Int]): Boolean = xs.forall(_ > 0)

  def max(xs: List[Int]): Option[Int] = xs.headOption.map(_ => xs.max)

  def dedupe(xs: List[Int]): List[Int] = xs.distinct

  def take(xs: List[Int], n: Int): List[Int] = xs.take(n)
}
