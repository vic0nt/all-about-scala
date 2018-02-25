package main.scala.dsa

/**
  * Created by vicont on 09.10.2016.
  */

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def head[A](list: List[A]): A = list match {
    case Nil => throw new NoSuchElementException("head of empty list")
    case Cons(x, _) => x
  }

  def length[A](list: List[A]): Int = {
    def length_(list: List[A], acc: Int): Int = list match {
        case Nil => acc
        case Cons(x, xs) => length_(xs, acc+1)
      }
    length_(list, 0)
  }

  def isEmpty[A](list: List[A]): Boolean = list match {
    case Nil => true
    case _ => false
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

object Launcher extends App {
  val example = Cons(1, Cons(2, Cons(3, Nil)))
  val example2 = List(1,2,3)
  println(List.isEmpty(example))
  println(List.length(example))
  println(List.head(example))
}