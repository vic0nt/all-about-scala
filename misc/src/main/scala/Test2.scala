package main.scala

import scala.collection.parallel.ParSeq
import utils.ProfilingUtils._

object Test2 extends App {
  val intParList: ParSeq[Int] = (1 to 1000000).map(_ => scala.util.Random.nextInt()).par
  timeMany(1000, intParList.reduce(_ + _))
  timeMany(1000, intParList.foldLeft(0)(_ + _))
}
