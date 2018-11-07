package main.scala

object Test4 extends App {

  val a = Some(5)
  val b = Some(3)

  def multiply(a: Int, b: Int): Int = a * b

  val rez = for (a ← a; b ← b) yield multiply(a, b)

  val rez2 = (a,b) match {
    case (Some(x), Some(y)) ⇒ Some(multiply(x,y))
    case _ ⇒ None
  }

  println(rez)
  println(rez2)

}
