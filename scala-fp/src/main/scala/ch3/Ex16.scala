package ch3

object Ex16 extends App {

  import List._

  def increment(as: List[Int]): List[Int] = foldRight(as, Nil:List[Int])((h,t) => Cons(h+1, t))

  val a1 = List(1,2,3,4,5,6)
  println(increment(a1))
}
