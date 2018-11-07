import cats.Apply
import org.scalatest.{Matchers, WordSpec}

class ApplySpec extends WordSpec with Matchers {
  "Apply" must {
    "be correct in sample 01" in {
      import cats.implicits._

      val intToString: Int ⇒ String = _.toString
      val double: Int ⇒ Int = _ * 2
      val addTwo: Int ⇒ Int = _ + 2

      Apply[Option].map(Some(1))(intToString) should be(Some("1"))
      Apply[Option].map(Some(1))(double) should be(Some(2))
      Apply[Option].map(None)(addTwo) should be(Some(3))
    }
  }
}
