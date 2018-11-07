trait Monoid[A] {
  def op(a1: A, a2: A): A

  def zero: A
}

// ex 10.1
val intAddition: Monoid[Int] = new Monoid[Int] {
  def op(a1: Int, a2: Int): Int = a1 + a2

  val zero: Int = 0
}

val intMultiplication: Monoid[Int] = new Monoid[Int] {
  def op(a1: Int, a2: Int): Int = a1 * a2
  val zero: Int = 1
}

val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
  def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
  val zero: Boolean = false
}

val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
  def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
  val zero: Boolean = true
}

// ex 10.2
def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
  def op(a1: Option[A], a2: Option[A]): Option[A] = (a1, a2) match {
    case (Some(x), Some(y)) ⇒ ??? // Здесь же должен быть append от полугруппы на A?
    case (Some(_), None) ⇒ a1
    case (None, Some(_)) ⇒ a2
    case (None, None) ⇒ None
  }

  val zero: Option[A] = None
}

// ex 10.3
def endoMonoid[A]: Monoid[A ⇒ A] = new Monoid[A ⇒ A] {
  override def op(a1: A ⇒ A, a2: A ⇒ A): A ⇒ A = a1 compose a2
  val zero: A ⇒ A = (a: A) ⇒ a
}

// ex 10.5
def foldMap[A, B](as: List[A], m: Monoid[B])(f: A ⇒ B): B = {
  as.foldLeft(m.zero)((b, a) ⇒ m.op(b, f(a)))
}

// ex 10.6
def foldRight[A, B](as: List[A])(z: B)(f: (A, B) ⇒ B): B = {
  foldMap(as, endoMonoid[B])(f.curried)(z)
}

// ex 10.7
def foldMapV[A,B](v: IndexedSeq[A], m: Monoid[B])(f: A ⇒ B): B = v.length match {
  case 0 ⇒ m.zero
  case 1 ⇒ f(v.head)
  case _ ⇒
    val (l,r) = v.splitAt(v.length/2)
    m.op(foldMapV(l,m)(f), foldMapV(r,m)(f))
}

sealed trait WC
case class Stub(chars: String) extends WC
case class Part(lStub: String, words: Int, rStub: String) extends WC

// ex 10.10
val wcMonoid = new Monoid[WC] {
  override def op(a1: WC, a2: WC): WC = (a1,a2) match {
    case (Stub(x), Stub(y)) ⇒ Stub(x + y)
    case (Stub(x), Part(l,w,r)) ⇒ Part(x + l, w, r)
    case (Part(l,w,r), Stub(x)) ⇒ Part(l, w, r + x)
    case (Part(l1, w1, r1), Part(l2, w2, r2)) ⇒
      Part(l1, w1 + (if ((r1 + l2).isEmpty) 0 else 1) + w2, r2)
  }
  val zero = Stub("")
}

def count(s: String): Int = {}

def foo[T] : T = null[Nothing]