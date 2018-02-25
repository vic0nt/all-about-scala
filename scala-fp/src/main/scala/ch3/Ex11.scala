package ch3

object Ex11 extends App {

  def sum(ints: List[Int]): Int = List.foldLeft(ints,0)(_ + _)
  def product(ints: List[Int]): Int = List.foldLeft(ints,1)(_ * _)
  def length[A](as: List[A]): Int = List.foldLeft(as,0)((a,b) ⇒ a + 1)

  val r = sum(List(1,2,4,5,6))
  val r2 = product(List(1,2,4,5,6))
  val r3 = length(List(1,2,4,5,6))
  println(r)
  println(r2)
  println(r3)
}
