import cats.implicits._
import cats.data.{Ior, NonEmptyList => Nel}

case class Username(value: String) extends AnyVal
case class Password(value: String) extends AnyVal

case class User(name: Username, pw: Password)

object Test5 extends App {

  type Failures = Nel[String]

  def validateUsername(u: String): Failures Ior Username = {
    if (u.isEmpty)
      Nel.one("Can't be empty").leftIor
    else if (u.contains("."))
      Ior.both(Nel.one("Dot in name is deprecated"), Username(u))
    else
      Username(u).rightIor
  }

  def validatePassword(p: String): Failures Ior Password = {
    if (p.length < 8)
      Nel.one("Password too short").leftIor
    else if (p.length < 10)
      Ior.both(Nel.one("Password should be longer"), Password(p))
    else
      Password(p).rightIor
  }

/*  def validateUser(name: String, password: String): Failures Ior User =
    (validateUsername(name), validatePassword(password)).mapN(User)


  validateUser("John", "password12")*/
}
