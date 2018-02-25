package main.scala.thirtyDaysOfCode.day08

import java.util.Scanner

/**
  * Created by vicont on 30.07.2016.
  */
object Solution {
  def main(args: Array[String]) {

    def findNumber(phoneBook: Map[String, Int], name: String) = phoneBook.get(name) match {
      case Some(number) => s"$name=$number"
      case None => "Not found"
    }

    val in = new Scanner(System.in)
    val n = in.nextInt()
    val phoneBook = (for (_ <- 1 to n) yield {
      val name = in.next()
      val phone = in.nextInt()
      name -> phone
    }).toMap

    while(in.hasNext()){
      val s = in.next()
      println(findNumber(phoneBook,s))
    }
    in.close()
  }
}
