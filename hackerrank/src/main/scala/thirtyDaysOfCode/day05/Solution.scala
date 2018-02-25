package main.scala.thirtyDaysOfCode.day05

/**
  * Created by vicont on 28.07.2016.
  */
object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var N = sc.nextInt();
    for (i <- 1 to 10) println(N + " x " + i + " = " + N * i)
  }
}