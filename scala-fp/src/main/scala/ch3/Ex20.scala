package ch3

object Ex20 extends App {
  import List._
  println(flatMap(List(1,2,3))(i ⇒ List(i,i)))
}
