package main.scala.thirtyDaysOfCode.day26

import java.time._
import java.time.format.DateTimeFormatter

/**
  * Created by vicont on 24.08.2016.
  */
object Solution_ {

  def getFee(dateActual: LocalDate, dateExpected: LocalDate): Int = {
    if (Period.between(dateExpected, dateActual).getDays < 1) return 0
    if (YearMonth.from(dateExpected) == YearMonth.from(dateActual))
      return Period.between(dateExpected, dateActual).getDays * 15
    if (Year.from(dateExpected) == Year.from(dateActual))
      Period.between(dateExpected, dateActual).getMonths * 500
    else 10000
  }

  def main(args: Array[String]) {
    val formatter = DateTimeFormatter.ofPattern("d M yyyy")
    val dates = io.Source.stdin.getLines.take(2).map(s => LocalDate.parse(s, formatter)).toList
    print(getFee(dates.head, dates.tail.head))
  }
}
