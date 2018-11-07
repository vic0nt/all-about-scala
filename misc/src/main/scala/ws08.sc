import java.time.ZonedDateTime
import java.util.UUID

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, ObjectEncoder}

case class DocumentInfo[W[_]](
  number: W[String],
  date: W[ZonedDateTime],
  note: W[String],
  linkedDocuments: Seq[String]
)

case class Budgetary[W[_]](
  statusIndicator: String,
  cbc: W[String],
  oktmo: W[String],
  payReason: W[String],
  typeBudgetPayment: W[String],
  taxDocNumber: W[String],
  taxDateDay: W[Int],
  taxDateMonth: W[Int],
  taxDateYear: W[Int],
  taxPeriodDay: W[String],
  taxPeriodMonth: W[String],
  taxPeriodYear: W[Int],
  customsCode: W[Int]
)

sealed trait Document[W[_]] {
  def id: UUID
  def organizationId: W[UUID]
  def userId: W[UUID]
  def accountId: W[UUID]
  def documentInfo: W[DocumentInfo[W]]
}

case class CommonDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID],
  documentInfo: W[DocumentInfo[W]]
) extends Document[W]

case class BudgetDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID],
  documentInfo: W[DocumentInfo[W]],
  budgetary: W[Budgetary[W]]
) extends Document[W]

val docInfo1 = DocumentInfo[Option](
  Some("123"),
  Some(ZonedDateTime.now()),
  None,
  List.empty[String]
)

val common1 = CommonDocument[Option](
  UUID.fromString("0-0-0-0-0"),
  Some(UUID.fromString("0-0-0-0-0")),
  None,
  None,
  Some(docInfo1)
)

import io.circe.generic.semiauto.{
  deriveDecoder => genericallyDeriveDecoder,
  deriveEncoder => genericallyDeriveEncoder
}

implicit val encodeDocumentId: ObjectEncoder[Document[cats.Id]] = genericallyDeriveEncoder
implicit val decodeDocumentId: Decoder[Document[cats.Id]] = genericallyDeriveDecoder
implicit val encodeDocumentOption: ObjectEncoder[Document[Option]] = genericallyDeriveEncoder
implicit val decodeDocumentOption: Decoder[Document[Option]] = genericallyDeriveDecoder
implicit val decoderInfoId: Decoder[DocumentInfo[cats.Id]] = deriveDecoder
implicit val encoderInfoId: Encoder[DocumentInfo[cats.Id]] = deriveEncoder
implicit val decoderInfoOption: Decoder[DocumentInfo[Option]] = deriveDecoder
implicit val encoderInfoOption: Encoder[DocumentInfo[Option]] = deriveEncoder
implicit val decoderBId: Decoder[Budgetary[cats.Id]] = deriveDecoder
implicit val encoderBId: Encoder[Budgetary[cats.Id]] = deriveEncoder
implicit val decoderBOption: Decoder[Budgetary[Option]] = deriveDecoder
implicit val encoderBOption: Encoder[Budgetary[Option]] = deriveEncoder