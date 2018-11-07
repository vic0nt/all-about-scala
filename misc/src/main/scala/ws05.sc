sealed trait SeverityLevel
object SeverityLevel {
  case object Block extends SeverityLevel
  case object Error extends SeverityLevel
  case object Warning extends SeverityLevel
}

case class ValidationCheck(
  checkId: String,
  fields: Seq[String],
  severityLevel: SeverityLevel,
  messageInEnglish: Option[String],
  messageInRussian: Option[String]
)

val list =  List(
  ValidationCheck(
    "1.1.2",
    List("documentInfo.number"),
    SeverityLevel.Error,
    None,
    Some("Номер документа 0123456 длиннее допустимого (более 6 символов)")
  ),
  ValidationCheck(
    "1.3.1",
    List("paymentCode"),
    SeverityLevel.Error,
    None,
    Some("Информация о виде платежа не соответствует справочнику")
  )
)

list.foldLeft(Map[String, SeverityLevel]()) { (m, s) ⇒ m + (s.checkId → s.severityLevel) }