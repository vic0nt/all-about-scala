import java.time.ZonedDateTime
import java.util.UUID

import io.circe.{Decoder, ObjectEncoder}

case class DocumentInfo(
                               number: String,
                               date: ZonedDateTime,
                               note: String,
                               linkedDocuments: Seq[String]
                             )

case class Budgetary(
                            statusIndicator: String,
                            cbc: String,
                            oktmo: String,
                            payReason: String,
                            typeBudgetPayment: String,
                            taxDocNumber: String,
                            taxDateDay: Int,
                            taxDateMonth: Int,
                            taxDateYear: Int,
                            taxPeriodDay: String,
                            taxPeriodMonth: String,
                            taxPeriodYear: Int,
                            customsCode: Int
                          )

sealed trait Document {
  def id: UUID
  def organizationId: UUID
  def userId: UUID
  def accountId: UUID
  def documentInfo: DocumentInfo
}

case class CommonDocument(
                                 id: UUID,
                                 organizationId: UUID,
                                 userId: UUID,
                                 accountId: UUID,
                                 documentInfo: DocumentInfo
                               ) extends Document

case class BudgetDocument(
                                 id: UUID,
                                 organizationId: UUID,
                                 userId: UUID,
                                 accountId: UUID,
                                 documentInfo: DocumentInfo,
                                 budgetary: Budgetary
                               ) extends Document

val docInfo1 = DocumentInfo(
  "123",
  ZonedDateTime.now(),
  "asdf",
  List.empty[String]
)

val common1 = CommonDocument(
  UUID.fromString("0-0-0-0-0"),
  UUID.fromString("0-0-0-0-0"),
  UUID.fromString("0-0-0-0-0"),
  UUID.fromString("0-0-0-0-0"),
  docInfo1
)

import io.circe.generic.semiauto.{deriveDecoder => genericallyDeriveDecoder, deriveEncoder => genericallyDeriveEncoder}

implicit val encodeDocument: ObjectEncoder[Document] = genericallyDeriveEncoder
implicit val decodeDocument: Decoder[Document] = genericallyDeriveDecoder

