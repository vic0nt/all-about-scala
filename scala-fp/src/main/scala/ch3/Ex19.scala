package ch3

object Ex19 extends App {

  import List._

  val a1 = List(1.0,2.2,3.4356,4.45,5.1,6)
  val a2 = Nil: List[Double]
  println(filter(a1)(_ > 2))
  println(filter(a2)(_ > 2))
}
