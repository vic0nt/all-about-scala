package ch3

object Ex24 extends App {

  import List._

  val a1 = List(1,2,3,120,121,9)
  val a2 = List(1,2)
  val a3 = List(6,2)
  val a4 = List(121,9)

  println(hasSubsequence(a1, a2))
  println(hasSubsequence(a1, a3))
  println(hasSubsequence(a1, a4))
}
