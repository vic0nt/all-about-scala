package main.scala.fp.fibonacci

/**
  * Created by vicont on 12.11.2016.
  */
object Solution extends App {

  def fibonacci(n: Int) = {
    def helper_(n: Int, low: BigInt, high: BigInt, accum: List[BigInt] = Nil): BigInt = {
      if (n == 1)
        (low :: accum).head
      else
        helper_(n - 1, high, high + low, low :: accum)
    }
    helper_(n, 0, 1, Nil)
  }

  println(fibonacci(7))

}
