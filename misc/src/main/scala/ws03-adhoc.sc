import scalaz._
import Scalaz._

trait Plus[A] {
  def plus(a1: A, a2: A): A
}

object Plus {
  implicit val intPlus: Plus[Int] = (a1: Int, a2: Int) ⇒ a1 + a2
}

//def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
def plus[A](a1: A, a2: A)(implicit p: Plus[A]): A = p.plus(a1, a2)
plus(5,6)

1 === 1
1.some =/= 2.some
