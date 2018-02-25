package main

/**
 * Created by vicont on 17.01.2016.
 */

object Euler02 extends App {

  //val fibs: Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)

  def fibonacci(cnt: Int, low: BigInt = 1, high: BigInt = 2, accum: List[BigInt] = Nil): List[BigInt] = {
    if (cnt == 0)
      (low :: accum).reverse
    else
      fibonacci(cnt - 1, high, high + low, low :: accum)
  }


  //val fibs: Stream[BigInt] = BigInt(1) #:: BigInt(2) #:: fibs.zip(fibs.tail).map(n => n._1 + n._2)
  //fibs take 5 foreach println

  def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)
  println(fibFrom(1,2) takeWhile(_ < 4000000) filter (n => n % 2 == 0) sum)
}
