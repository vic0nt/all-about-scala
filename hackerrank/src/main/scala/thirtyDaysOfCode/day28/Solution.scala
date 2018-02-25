package main.scala.thirtyDaysOfCode.day28

/**
  * Created by vicont on 04.09.2016.
  */
object Solution {

  def main(args: Array[String]) {
    val in = new java.util.Scanner(System.in)
    val n = in.nextInt()
    val addressBook = for (_ <- 1 to n) yield {
      val name = in.next()
      val email = in.next()
      (name, email)
    }

    addressBook.filter(_._2.endsWith("@gmail.com")).sortBy(_._1).foreach(e => println(e._1))
  }
}