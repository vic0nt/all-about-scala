package ch3

object Ex29 extends App {

  import Tree._

  val l01 = Leaf(10)
  val l02 = Leaf(20)
  val b01 = Branch(l01, l02)

  val l11 = Leaf(100)
  val l12 = Leaf(200)
  val b11 = Branch(l11, l12)

  val tree = Branch(b01, b11)

  assert(sizeViaFold(tree) == 7)
  assert(maximumViaFold(tree) == 200)
}
