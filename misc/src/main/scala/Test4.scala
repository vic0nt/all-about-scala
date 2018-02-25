package main.scala

object Test4 extends App {

  def mySum(a: Int, b: Int) = a + b
  val a = List(1,2,4,5,6,32).reduce(mySum)
  val b = List(1,2,4,5,6,32).foldRight(0)(mySum)
  val c = List(1,2,4,5,6,32).foldLeft(0)(mySum)
  println(a)
  println(b)

}
