package main.scala.thirtyDaysOfCode.day11

object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val arr = Array.ofDim[Int](6,6)
    for(arr_i <- 0 until 6) {
      for(arr_j <- 0 until 6){
        arr(arr_i)(arr_j) = sc.nextInt()
      }
    }

    def getHourglassSum(a: Array[Array[Int]], row: Int, col: Int) = {
      val sublist = for {
        i <- row to row + 2
        j <- col to col + 2
      } yield a(i)(j)
      sublist.zipWithIndex.filter(e => e._2 != 3 && e._2 != 5).map(e => e._1).sum
    }

    val rez = for {
      i <- 0 to 3
      j <- 0 to 3
    } yield getHourglassSum(arr, i, j)

    print(rez.max)
  }
}
