package main.scala.fp.areaOfPolygon

/**
  * Created by vicont on 05.11.2016.
  */
object Solution extends App {

  def calcShoelaceFormula(points: List[(Int, Int)]) = {
    def helper_(points: List[(Int,Int)], accSum: Double, accDiff: Double):(Double, Double) = {
      points match  {
        case Nil => (accSum, accDiff)
        case head :: Nil => (accSum, accDiff)
        case head :: tail => helper_(tail, accSum + head._1 * tail.head._2, accDiff + tail.head._1 * head._2)
      }
    }
    val subtotal = helper_(points, 0, 0)
    Math.abs((subtotal._1 + points.last._1 * points.head._2) - (subtotal._2 + points.head._1 * points.last._2)) / 2
  }

  val in = new java.util.Scanner(System.in)
  val n = in.nextInt()
  val list = (for (i <- 1 to n) yield (in.nextInt(), in.nextInt())).toList
  println(calcShoelaceFormula(list))
}
