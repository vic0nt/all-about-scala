"123456789abcdefgh".substring(3,10)

"0000001000".replaceAll("0","")

"01;123456789a".substring(3).matches("[0-9]{10}")

"01;123456789a".substring(2,3)

"00;000000000".replace("0","")

"07".toInt
"07".matches("^([0-3][0-9])$")
"37".matches("^([0-3][0-9])$")
"47".matches("^([0-3][0-9])$")
"sdf".matches("^([0-3][0-9])$")

val left = "ValidationCheck".getBytes
val right = "ValidationCheck".getBytes()

val t = (1,2,3,4)

//sealed trait Shape

abstract class Shape {val radius: Double}
final case class Circle(radius: Double) extends Shape
final case class Rectangle(radius: Double, width: Double, height: Double) extends Shape

val circle = Circle(123D)

circle match {
  case s: Shape => println(s.radius)
  case _ => print(false)
}

