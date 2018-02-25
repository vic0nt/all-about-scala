package main.scala.thirtyDaysOfCode.day06

/**
  * Created by ruatvv2 on 26.07.16.
  */
object Solution {

  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines
    for (s <- lines.drop(1)) {
      val rez = s.zipWithIndex.partition(_._2 % 2 == 0) match {
        case (odds, evens) => (odds.map(el => el._1).mkString(""), evens.map(el => el._1).mkString(""))
        case _ => (Nil, Nil)
      }
      println(rez._1 + " " + rez._2)
    }
  }
}
