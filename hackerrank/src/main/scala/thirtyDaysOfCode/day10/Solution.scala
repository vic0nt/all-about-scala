package main.scala.thirtyDaysOfCode.day10

object Solution {
  def main(args: Array[String]) {

    def anesCounter(l: List[Int], totalQty: Int, currQty: Int): Int = {
      l match {
        case Nil => List(totalQty, currQty).max
        case head :: tail => if (head == 1) anesCounter(tail, totalQty, currQty + 1)
          else anesCounter(tail, List(totalQty, currQty).max, 0)
      }
    }

    val list: List[Int] = io.StdIn.readInt().toBinaryString.split("").map(c => if (c=="1") 1 else 0).toList
    println(anesCounter(list,0,0))
  }
}
