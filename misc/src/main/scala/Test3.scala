package main.scala

object Test3 extends App {

  def balanceNaive(chars: Array[Char]): Boolean = {
    var open = 0
    var close = 0
    if (chars.length == 0) true
    else {
      chars.foreach {
        case '(' ⇒ open += 1
        case ')' ⇒ close += 1
        case _ ⇒
      }
      (open > 0 && open == close && chars.indexOf('(') < chars.indexOf(')')) || (open == 0 && close == 0)
    }
  }

  def balance(chars: Array[Char]): Boolean = {
    val result = chars.foldLeft(0)((n, c) => c match {
      case '(' => if (n < 0) -1 else n + 1
      case ')' => if (n <= 0) -1 else n - 1
      case _ => n
    })
    result == 0
  }

  val arr = ")(".toCharArray
  println(balance(arr))
}

