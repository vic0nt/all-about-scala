package main.scala.strings.stringSimilarity

/**
  * Created by ruatvv2 on 26.08.16.
  */
object Solution {

  def stringSimilarity(str1: List[Char], str2: List[Char]): Long = {
    def ss(str1: List[Char], str2: List[Char], acc: Long): Long = {
      if (str1 == Nil || str2 == Nil) return acc
      if (str1.head.equals(str2.head)) ss(str1.tail, str2.tail, acc + 1) else acc
    }
    ss(str1, str2, 0)
  }

  def sumOfSuffixesSimilarity(str: List[Char]): Long = {
    def sumSuff(source: List[Char], str: List[Char], acc: Long): Long = str match {
      case Nil => acc
      case list => sumSuff(source, str.tail, acc + stringSimilarity(source, str))
    }

    sumSuff(str, str, 0)

  }

  def sumOfSuffixesSimilarityWrapper(str: List[Char], num: Int): (Long, Int) = (sumOfSuffixesSimilarity(str), num)

  def main(args: Array[String]) {
    val in = new java.util.Scanner(System.in)
    val n = in.nextInt()
    val strings = for (_ <- 1 to n) yield in.next()
    val t0 = System.nanoTime()
    strings.par.zipWithIndex.map(t => sumOfSuffixesSimilarityWrapper(t._1.toList, t._2)).toArray.sortBy(_._2).foreach(r => println(r._1))
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000.0 + "ms")

  }
}
