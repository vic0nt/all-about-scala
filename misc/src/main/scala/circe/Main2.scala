package circe

import java.time.ZonedDateTime
import java.util.UUID

import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, ObjectEncoder}

import scala.language.higherKinds

sealed trait Document[W[_]] {
  def id: UUID
  def organizationId: W[UUID]
  def userId: W[UUID]
  def accountId: W[UUID]
}

object Document {
  implicit val encodeCommonDocumentId: ObjectEncoder[CommonDocument[cats.Id]] = deriveEncoder
  implicit val decodeCommonDocumentId: Decoder[CommonDocument[cats.Id]] = deriveDecoder
  implicit val encodeCommonDocumentOption: ObjectEncoder[CommonDocument[Option]] = deriveEncoder
  implicit val decodeCommonDocumentOption: Decoder[CommonDocument[Option]] = deriveDecoder

  implicit val encodeInfoDocumentId: ObjectEncoder[InfoDocument[cats.Id]] = deriveEncoder
  implicit val decodeInfoDocumentId: Decoder[InfoDocument[cats.Id]] = deriveDecoder
  implicit val encodeInfoDocumentOption: ObjectEncoder[InfoDocument[Option]] = deriveEncoder
  implicit val decodeInfoDocumentOption: Decoder[InfoDocument[Option]] = deriveDecoder

  implicit val encodeBudgetDocumentId: ObjectEncoder[BudgetDocument[cats.Id]] = deriveEncoder
  implicit val decodeBudgetDocumentId: Decoder[BudgetDocument[cats.Id]] = deriveDecoder
  implicit val encodeBudgetDocumentOption: ObjectEncoder[BudgetDocument[Option]] = deriveEncoder
  implicit val decodeBudgetDocumentOption: Decoder[BudgetDocument[Option]] = deriveDecoder
}

case class CommonDocument[W[_]](id: UUID, organizationId: W[UUID], userId: W[UUID], accountId: W[UUID])
  extends Document[W]

case class InfoDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID],
  documentInfo: DocumentInfo[W]
) extends Document[W]

case class BudgetDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID],
  documentInfo: DocumentInfo[W],
  budgetary: Budgetary[W]
) extends Document[W]

case class DocumentInfo[W[_]](number: W[String], date: W[ZonedDateTime], note: W[String], linkedDocuments: Seq[String])

object DocumentInfo {
  implicit val decoderInfoId: Decoder[DocumentInfo[cats.Id]] = deriveDecoder
  implicit val encoderInfoId: Encoder[DocumentInfo[cats.Id]] = deriveEncoder
  implicit val decoderInfoOption: Decoder[DocumentInfo[Option]] = deriveDecoder
  implicit val encoderInfoOption: Encoder[DocumentInfo[Option]] = deriveEncoder
}

case class Budgetary[W[_]](status: W[String])

object Budgetary {
  implicit val decoderBudgetaryId: Decoder[Budgetary[cats.Id]] = deriveDecoder
  implicit val encoderBudgetaryId: Encoder[Budgetary[cats.Id]] = deriveEncoder
  implicit val decoderBudgetaryOption: Decoder[Budgetary[Option]] = deriveDecoder
  implicit val encoderBudgetaryOption: Encoder[Budgetary[Option]] = deriveEncoder
}

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
