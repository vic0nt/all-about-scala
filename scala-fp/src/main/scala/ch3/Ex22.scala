package ch3

object Ex22 extends App {

  import List._

  val a1 = List(1,2,3)
  val a2 = List(5,6,7,11)

  println(addingLists(a1,a2))
  println(addPairwise(a1,a2))
}
