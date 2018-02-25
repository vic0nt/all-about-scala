package main

/**
 * Created by vicont on 17.01.2016.
 */
object Euler03 extends App {


/*
  def sieve(list: Stream[Long], acc: List[Long]=Nil, maxValue: Long): List[Long] = {
    if (list.tail.isEmpty || Math.pow(list.head,2) > maxValue) acc.reverse
    else sieve(list.tail.filter(_ % list.head != 0), list.head :: acc, maxValue)
  }
  def stream(i: Long = 1): Stream[Long] = i #:: stream(i + 1)
  print(sieve(stream(1).takeWhile(_ < 600851475143L), Nil, 600851475143L).max)
*/

  def sieve(list: Seq[Int], acc: List[Int]=Nil): List[Int] = {
    if (list.tail.isEmpty) acc.reverse
    else sieve(list.tail.filter(_ % list.head != 0), list.head :: acc)
  }
  print(sieve(2 to math.sqrt(600851475143L).toInt).last)


  /*  def factors(n: Long): List[Long] = (2 to math.sqrt(n).toInt)
      .find(n % _ == 0).fold(List(n))(i => i.toLong :: factors(n / i))

    val r = factors(600851475143L).last

    assert(r == 6857) // 1 ms*/

}
