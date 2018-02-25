package ch3

object Ex8 extends App {
  val rez = List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
  println(rez)
}
