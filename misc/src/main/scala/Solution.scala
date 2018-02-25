package main.scala

import java.util.Locale

object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in).useLocale(Locale.ENGLISH)
    val n = sc.nextInt()
    val list = (for (i <- 0 until n) yield (sc.nextDouble())).toList

    def eval(d: Double): Double = {
      def _e(x: Int, d: Double, acc: Double): Double = x match {
        case 10 => acc
        case _ => _e(x + 1, d, acc + pow(d, x) / fact(x))
      }
      _e(0, d, 0)
    }

    for (i <- 0 until n) yield (println(eval(list(i))))

    def fact(x: Double): Double = {
      def _f(x: Double, acc: Double): Double = x match {
        case 0 => acc
        case _ => _f(x - 1, acc * x)
      }
      _f(x, 1)
    }

    def pow(x: Double, ex: Int): Double = {
      def _p(x: Double, acc: Double, ex: Int): Double = ex match {
        case 0 => acc
        case _ => _p(x, acc * x, ex - 1)
      }
      _p(x, 1, ex )
    }
  }
}