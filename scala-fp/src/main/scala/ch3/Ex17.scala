package ch3

object Ex17 extends App {

  import List._

  def toStringList(as: List[Double]): List[String] = foldRight(as, Nil:List[String])((h, t) => Cons(h.toString, t))

  val a1 = List(1.0,2.2,3.4356,4.45,5.1,6)
  println(toStringList(a1))
}
