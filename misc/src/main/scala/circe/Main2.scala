package circe

import java.time.ZonedDateTime
import java.util.UUID

import io.circe.parser._

import scala.language.higherKinds

/*sealed trait Document[W[_]] {
  def id: UUID
  def organizationId: W[UUID]
  def userId: W[UUID]
  def accountId: W[UUID]
}*/


object Main extends App {
  val docInfo = DocumentInfo[Option](Some("123"), Some(ZonedDateTime.now()), None, List.empty[String])
  val commonDoc = CommonDocument[Option](UUID.fromString("0-0-0-0-0"), Some(UUID.fromString("0-0-0-0-0")), None, None)
  val infoDoc =
    InfoDocument[Option](UUID.fromString("0-0-0-0-0"), Some(UUID.fromString("0-0-0-0-0")), None, None, docInfo)

  //println(infoDoc.asJson)

  val rawJsonInfo = s"""
    {
      "id" : "00000000-0000-0000-0000-000000000000",
      "organizationId" : "00000000-0000-0000-0000-000000000000",
      "userId" : null,
      "accountId" : null,
      "documentInfo" : {
        "number" : "123",
        "date" : "2018-10-16T22:27:11.644+03:00[Europe/Moscow]",
        "note" : null,
        "linkedDocuments" : [
        ]
      }
    }"""

  val docInfoParsed = parse(rawJsonInfo).flatMap(_.as[InfoDocument[Option]])
  println(docInfoParsed)

  val docCommonParsed = parse(rawJsonInfo).flatMap(_.as[CommonDocument[Option]])
  println(docCommonParsed)

}
