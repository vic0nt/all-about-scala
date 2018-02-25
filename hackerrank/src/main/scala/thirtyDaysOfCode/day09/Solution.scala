package main.scala.thirtyDaysOfCode.day09

object Solution {
  def factorial_(n: Int, acc: Int): Int = n match {
    case 1 => acc
    case _ => factorial_(n-1, acc * n)
  }

  def factorial(n: Int) = factorial_(n, 1)

  def main(args: Array[String]) {
    val n = io.StdIn.readInt()
    println(factorial(n))
  }
}
