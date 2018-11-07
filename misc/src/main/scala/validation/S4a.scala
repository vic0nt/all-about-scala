package validation

/*
In this sprint we deal with arbitrary arity using a simple HList-like
recursive data structure `~`:

  val pairs: ~[~[Int, String], String] = ~(~(29, "Acacia Road"), "ABC 123")

which can also be written infix:

  val pairs: Int ~ String ~ String = ~(~(29, "Acacia Road"), "ABC 123")

We create a combinator on `Rule` that combines rules with the same input
type to create a single rule that outputs a nested tuple of results:

  val bigRule: Rule[FormData, Int ~ String ~ String] =
    readNumber ~ readStreet ~ readZipCode

and map the result through a function that applies pattern matching to the
tuples, extracts the fields, and creates an Address:

   bigRule map {
     case ~(~(number, street), zipCode) => Address(number, street, zipCode)
   }

this can also be written in infix form:

   bigRule map {
     case number ~ street ~ zipCode => Address(number, street, zipCode)
   }
*/
object S4a extends App {

  // Validation library -------------------------

  final case class ~[+A, +B](_1: A, _2: B)

  sealed trait Result[+A] {
    def and[B, C](that: Result[B])(func: (A, B) => C) = (this, that) match {
      case (Pass(a), Pass(b)) => Pass(func(a, b))
      case (Fail(a), Pass(b)) => Fail(a)
      case (Pass(a), Fail(b)) => Fail(b)
      case (Fail(a), Fail(b)) => Fail(a ++ b)
    }

    def map[B](func: A => B) = this match {
      case Pass(a) => Pass(func(a))
      case Fail(a) => Fail(a)
    }

    def flatMap[B](func: A => Result[B]) = this match {
      case Pass(a) => func(a)
      case Fail(a) => Fail(a)
    }

    def ~[B](that: Result[B]): Result[A ~ B] =
      (this and that)(new ~(_, _))
  }

  final case class Pass[A](value: A) extends Result[A]
  final case class Fail(messages: List[String]) extends Result[Nothing]

  type Rule[-A, +B] = A => Result[B]

  implicit class RuleOps[A, B](rule: Rule[A, B]) {
    def map[C](func: B => C): Rule[A, C] =
      (a: A) => rule(a) map func

    def flatMap[C](rule2: Rule[B, C]): Rule[A, C] =
      (a: A) => rule(a) flatMap rule2

    def and[C, D](rule2: Rule[A, C])(func: (B, C) => D): Rule[A, D] =
      (a: A) => (rule(a) and rule2(a))(func)

    def ~[C](that: Rule[A, C]): Rule[A, B ~ C] =
      (this and that)(new ~(_, _))
  }

  def rule[A]: Rule[A, A] =
    (input: A) => Pass(input)

  // Application code ---------------------------

  type FormData = Map[String, String]

  case class Address(number: Int, street: String, zipCode: String)

  def createAddress(input: Int ~ String ~ String): Address =
    input match {
      case n ~ s ~ z => Address(n, s, z)
    }

  // Validating existing addresses:

  val nonEmpty: Rule[String, String] =
    (str: String) =>
      if(str.isEmpty) Fail(List("Empty string")) else Pass(str)

  val initialCap: Rule[String, String] =
    (str: String) =>
      if(str(0).isUpper) Pass(str) else Fail(List("No initial cap"))

  def capitalize(str: String): String =
    str(0).toUpper +: str.substring(1)

  def gte(min: Int) = (num: Int) =>
    if(num < min) Fail(List("Too small")) else Pass(num)

  val checkNumber: Rule[Address, Int] =
    rule[Address] map (_.number) flatMap gte(1)

  val checkStreet: Rule[Address, String] =
    rule[Address] map (_.street) flatMap nonEmpty map capitalize

  val checkZip: Rule[Address, String] =
    rule[Address] map (_.zipCode) flatMap nonEmpty

  val checkAddress: Rule[Address, Address] =
    (checkNumber ~ checkStreet ~ checkZip) map createAddress

  // Reading form data:

  def getField(name: String): Rule[FormData, String] =
    (form: FormData) =>
      form.get(name) map (Pass.apply) getOrElse Fail(List(s"Field not found"))

  val parseInt: Rule[String, Int] =
    (str: String) =>
      try {
        Pass(str.toInt)
      } catch {
        case exn: NumberFormatException =>
          Fail(List("Not a number"))
      }

  val readNumber: Rule[FormData, Int] =
    rule[FormData] flatMap getField("number") flatMap parseInt

  val readStreet: Rule[FormData, String] =
    rule[FormData] flatMap getField("street")

  val readZip: Rule[FormData, String] =
    rule[FormData] flatMap getField("zip")

  val readAddress: Rule[FormData, Address] =
    (readNumber ~ readStreet ~ readZip) map createAddress flatMap checkAddress

  println("GOOD " + readAddress(Map("number" -> "29", "street" -> "acacia road", "zip" -> "ABC 123")))
  println("BAD  " + readAddress(Map("number" -> "-1", "street" -> "", "zip" -> "")))

}