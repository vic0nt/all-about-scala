package ch4

object Ex2 extends App {

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.size)

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap (m ⇒ mean(xs.map(x ⇒ math.pow(x - m, 2))))

  def lift[A, B](f: A ⇒ B): Option[A] ⇒ Option[B] = _ map f

  def map2pm[A, B, C](a: Option[A], b: Option[B])(f: (A, B) ⇒ C): Option[C] = (a, b) match {
    case (Some(aa), Some(bb)) ⇒ Some(f(aa, bb))
    case _ ⇒ None
  }

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) ⇒ C): Option[C] =
    a flatMap (aa ⇒ b map (bb ⇒ f(aa, bb)))

  def sequence[A](a: List[Option[A]]): Option[List[A]] = ???


}
