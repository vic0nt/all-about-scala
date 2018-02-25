package main.scala.thirtyDaysOfCode.day25

/**
  * Created by vicont on 23.08.2016.
  */
object Solution {

  def isPrime(n: Int) =
    if (n > 1 && (Iterator.from(2) takeWhile (i => i < Math.sqrt(n).toInt) forall (n % _ != 0))) "Prime" else "Not prime"

  def main(args: Array[String]) {
    val in = new java.util.Scanner(System.in)
    val n = in.nextInt()
    val numbers = (for (_ <- 1 to n) yield in.next()).map(Integer.parseInt)
    numbers.map(isPrime).foreach(println)
  }
}
