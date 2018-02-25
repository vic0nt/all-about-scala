package main.scala.fp.pascalsTriangle

/**
  * Created by vicont on 12.11.2016.
  */
object Solution extends App {

  def factorial(n: Int) = {
    def helper_(n: Int, acc: Int): Int = {
      n match {
        case 0 => 1
        case 1 => acc
        case _ => helper_(n-1, acc * n)
      }
    }
    helper_(n, 1)
  }

  def getRow(num: Int): List[Int] = {
    def pointValue(n: Int, r: Int) = factorial(n) / (factorial(r) * factorial(n - r))
    (0 to num).toList.map(pointValue(num, _))
  }

  for (i <- 0 until readInt()) println(getRow(i) mkString " ")
}
