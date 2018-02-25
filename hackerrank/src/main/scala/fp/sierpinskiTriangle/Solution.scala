package main.scala.fp.sierpinskiTriangle

/**
  * Created by vicont on 12.11.2016.
  */
object Solution extends App {

  def printSierpinski(n: Int) {
    def sierpinski(n: Int): List[String] = {
      lazy val down = sierpinski(n - 1)
      lazy val space = "_" * (1 << (n - 1))
      n match {
        case 0 => List("1")
        case _ => (down map (space + _ + space)) :::
          (down map (List.fill(2)(_) mkString " "))
      }
    }
    sierpinski(n) foreach println
  }

  printSierpinski(5)
}
