package main.scala.fp.stringMingling

/**
  * Created by vicont on 26.12.2016.
  */
object Solution extends App {

  def mingle(p: List[Char], q: List[Char]) = {
    def mingle_(p: List[Char], q: List[Char], acc: List[Char]): List[Char] = {
      p match {
        case Nil => acc
        case _ :: _ => mingle_(p.tail, q.tail, q.head :: p.head :: acc)
      }
    }
    mingle_(p, q, Nil).mkString.reverse
  }

  val in = new java.util.Scanner(System.in)
  val lines = for (_ <- 1 to 2) yield in.next()
  print(mingle(lines.head.toList, lines(1).toList))
}
