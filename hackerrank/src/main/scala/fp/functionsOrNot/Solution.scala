package main.scala.fp.functionsOrNot

/**
  * Created by vicont on 04.11.2016.
  */
object Solution extends App {

  def isFunction(list: List[(Int, Int)]): Boolean = {
    val t = list.unzip
    t._1.size == t._1.toSet.size && t._1.toSet.size >= t._2.toSet.size
  }

  val in = new java.util.Scanner(System.in)
  val n = in.nextInt()
  val list = for (i <- 1 to n) yield for (j <- 1 to in.nextInt()) yield (in.nextInt(), in.nextInt())
  list.map(_.toList).map(isFunction).map(x => if (x) "YES" else "NO").foreach(println)
}
