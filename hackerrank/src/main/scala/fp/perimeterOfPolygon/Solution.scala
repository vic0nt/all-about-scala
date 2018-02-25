package main.scala.fp.perimeterOfPolygon

/**
  * Created by vicont on 05.11.2016.
  */
object Solution extends App {
  def distance(p1: (Int, Int), p2: (Int, Int)) = Math.sqrt(Math.pow(p1._1 - p2._1, 2) + Math.pow(p1._2 - p2._2, 2))

  def calcPerimeter(points: List[(Int, Int)]) = {
    def calcPerimeter_(points: List[(Int, Int)], acc: Double): Double = {
      points match {
        case Nil => acc
        case head :: Nil => acc
        case head :: tail => calcPerimeter_(tail, acc + distance(head, tail.head))
      }
    }
    calcPerimeter_(points, 0) + distance(points.head, points.last).toInt
  }

  val in = new java.util.Scanner(System.in)
  val n = in.nextInt()
  val list = (for (i <- 1 to n) yield (in.nextInt(), in.nextInt())).toList
  println(calcPerimeter(list))
}
