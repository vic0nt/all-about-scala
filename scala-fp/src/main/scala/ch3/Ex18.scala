package ch3

object Ex18 extends App {

  import List._

  val a1 = List(1.0,2.2,3.4356,4.45,5.1,6)
  println(map(a1)(_.toString))
}
