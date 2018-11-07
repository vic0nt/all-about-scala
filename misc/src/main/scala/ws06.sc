val uip = "1234567890000000"
val acc = "40822"

val accKoef = "37137"
val uipKoef = "3713737137371373"

val accSum = acc.map(_.toString.toInt)
  .zip(accKoef.map(_.toString.toInt))
  .map(r ⇒ r._1 * r._2)
  .map(_.toString.takeRight(1))

val uipSum = s"0${uip.substring(1, 15)}".
  map(_.toString.toInt)
  .zip(uipKoef.map(_.toString.toInt))
  .map(r ⇒ r._1 * r._2)
  .map(_.toString.takeRight(1))