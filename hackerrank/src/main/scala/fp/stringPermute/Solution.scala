package main.scala.fp.stringPermute

/**
  * Created by vicont on 26.12.2016.
  */
object Solution extends App {
  def permute(s: String) = {
    def permute_(s: List[Char], acc: List[Char]): List[Char] =
      s match {
        case Nil => acc
        case x :: y :: tail => permute_(tail, x :: y :: acc)
      }
    permute_(s.toList, Nil).reverse.mkString
  }

  val in = new java.util.Scanner(System.in)
  val n = in.nextInt()
  val list = (for (i <- 1 to n) yield in.next()).toList
  list.map(permute).foreach(println)
}
