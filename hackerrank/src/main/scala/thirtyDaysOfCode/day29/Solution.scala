package main.scala.thirtyDaysOfCode.day29

/**
  * Created by vicont on 04.09.2016.
  * THIS SOLUTION IS POOR, IT HAS O(N * N) COMPLEXITY
  */
object Solution {

/*  def getBitAndMax(N: Int, K: Int): Int = {
    (for {
      i <- 1 to N
      j <- 1 to N
    } yield (i, j))
      .filter(p => p._1 != p._2 && p._1 < p._2)
      .map(p => p._1 & p._2)
      .sorted
      .takeWhile(_ < K).last
  }*/

  def getBitAndMax(N: Int, K: Int): Int = {
    (1 to N).combinations(2).map(v => (v.head, v.tail.head))
      .filter(p => p._1 != p._2 && p._1 < p._2)
      .map(p => p._1 & p._2).toList
      .sorted
      .takeWhile(_ < K).last
  }

  def main(args: Array[String]) {
    val in = new java.util.Scanner(System.in)
    val n = in.nextInt()
    val pairs = for (_ <- 1 to n) yield {
      val N = in.nextInt()
      val K = in.nextInt()
      (N, K)
    }
    pairs.map(e => getBitAndMax(e._1,e._2)).foreach(println)
  }

}

