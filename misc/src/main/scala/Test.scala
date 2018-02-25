package main.scala

object Test extends App {

  def sumOptionList(l: List[Option[Int]]): Option[Int] = l.reduce((a, b) => a.flatMap(s => b.map(s + _)))
  val a: List[Option[Int]] = List(Some(10), None, Some(2))
  val b: List[Option[Int]] = List(Some(10), Some(3), Some(2))

  assert(sumOptionList(a).isEmpty)
  assert(sumOptionList(b).contains(15))
}
