import cats.implicits._
import cats.kernel.{Monoid, Semigroup}

val aMap = Map("foo" → Map("bar" → 5))
val anotherMap = Map("foo" → Map("bar" → 6))
val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)

Monoid[Map[String, Int]].combineAll(List())
Monoid[Map[String, Int]].combineAll(List(Map("a" → 1, "b" → 2), Map("a" → 3)))

import cats._

implicit val optionApply: Apply[Option] = new Apply[Option] {

  def ap[A, B](f: Option[A => B])(fa: Option[A]): Option[B] =
    fa.flatMap(a => f.map(ff => ff(a)))

  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa map f

  override def product[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] =
    fa.flatMap(a => fb.map(b => (a, b)))
}

List(1, 2, 3, 4) flatMap (Some(_))
