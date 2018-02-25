package main.scala.samples

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable

/**
  * Created by vicont on 04.06.2017.
  */
object CanBuildFromTutorial extends App {

  def combineValues_(pairs: Seq[(String, String)]): Seq[(String, Seq[String])] = {
    val result = mutable.LinkedHashMap[String, List[String]]()

    for ((name, value) ← pairs)
      result += name → (value :: result.getOrElse(name, Nil))

    result.toList
  }

  def combineValues[U, T[_]](pairs: Seq[(String, U)])
                            (implicit cbf: CanBuildFrom[T[U], U, T[U]]): Seq[(String, T[U])] = {

    val result = mutable.LinkedHashMap[String, mutable.Builder[U, T[U]]]()

    for ((name, value) ← pairs)
      result.getOrElseUpdate(name, cbf()) += value

    result map { case (k, v) ⇒ k → v.result } toList
  }

  val l = List(("Alice", "3"), ("Bob", "18"), ("Alice", "6"), ("John", "4"))

  println(combineValues[String, List](l))
}
