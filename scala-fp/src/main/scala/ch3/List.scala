package ch3

import scala.annotation.tailrec

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case Cons(_, xs) => xs
  }

  def drop[A](list: List[A], n: Int): List[A] =
    if (n <= 0) list else list match {
      case Nil => Nil
      case Cons(_, xs) => drop(xs, n - 1)
    }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) dropWhile(xs)(f) else l
  }

  def setHead[A](l: List[A])(head: A): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => Cons(head, xs)
  }

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B =
    l match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  /* cheating */
  def foldRight2[A, B](as: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(as), z)((a, b) ⇒ f(b, a))

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def append2[A](a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)((el, list) ⇒ Cons(el, list))

  def init[A](l: List[A]): List[A] =
    l match {
      case Nil ⇒ l
      case Cons(x, Cons(_, Nil)) ⇒ Cons(x, Nil)
      case Cons(x, xs) ⇒ Cons(x, init(xs))
    }

  def length[A](as: List[A]): Int = foldRight(as, 0)((_, b) ⇒ b + 1)

  def reverse[A](as: List[A]): List[A] = foldLeft(as, Nil: List[A])((a, b) ⇒ Cons(b, a))

  def concatenate[A](as: List[List[A]]): List[A] = foldRight(as, Nil: List[A])(append)

  def map[A, B](as: List[A])(f: A ⇒ B): List[B] = List.foldRight(as, Nil: List[B])((h, t) ⇒ Cons(f(h), t))

  def mapMutable[A, B](l: List[A])(f: A => B): List[B] = {
    val buf = new collection.mutable.ListBuffer[B]

    def go(l: List[A]): Unit = l match {
      case Nil => ()
      case Cons(h, t) => buf += f(h); go(t)
    }

    go(l)
    List(buf.toList: _*)
  }

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldLeft(reverse(as), Nil: List[A])((list, el) ⇒ if (f(el)) Cons(el, list) else list)

  def filterMutable[A](l: List[A])(f: A => Boolean): List[A] = {
    val buf = new collection.mutable.ListBuffer[A]

    def go(l: List[A]): Unit = l match {
      case Nil => ()
      case Cons(h, t) => if (f(h)) buf += h; go(t)
    }

    go(l)
    List(buf.toList: _*)
  }

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] =
    foldLeft(reverse(as), Nil: List[B])((list, el) ⇒ append(f(el), list))

  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] = flatMap(as)(a ⇒ if (f(a)) List(a) else Nil)

  //def addingLists(l1: List[Int], l2: List[Int]): List[Int] = flatMap(l1)(a ⇒ map(l2)(b ⇒ a + b))

  def addingLists(l1: List[Int], l2: List[Int]): List[Int] = {
    @tailrec
    def go(l1: List[Int], l2: List[Int], acc: List[Int]): List[Int] = (l1, l2) match {
      case (Cons(h1, t1), Cons(h2, t2)) ⇒ go(t1, t2, Cons(h1 + h2, acc))
      case _ ⇒ acc
    }

    reverse(go(l1, l2, Nil: List[Int]))
  }

  def zipWith[A](l1: List[A], l2: List[A])(f: (A, A) ⇒ A): List[A] = {
    @tailrec
    def go(l1: List[A], l2: List[A], acc: List[A]): List[A] = (l1, l2) match {
      case (Cons(h1, t1), Cons(h2, t2)) ⇒ go(t1, t2, Cons(f(h1, h2), acc))
      case _ ⇒ acc
    }

    reverse(go(l1, l2, Nil: List[A]))
  }

  /* Ex.22 - canonical answer */
  def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addPairwise(t1, t2))
  }

  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    def go(sup: List[A], sub: List[A], acc: Boolean): Boolean = (sup, sub) match {
      case (Cons(h1, t1), b @ Cons(h2, t2)) ⇒ if (h1 == h2) go(t1, t2, true) else go(t1, b, false)
      case _ ⇒ acc
    }
    go(sup, sub, acc = false)
  }

  val example = Cons(1, Cons(2, Cons(3, Nil)))
  val example2 = List(1, 2, 3)
  val total = sum(example)
}