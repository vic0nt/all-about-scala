import java.time.ZonedDateTime
import java.util.UUID

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, ObjectEncoder}

sealed trait Document[W[_]] {
  def id: UUID
  def organizationId: W[UUID]
  def userId: W[UUID]
  def accountId: W[UUID]
}

case class CommonDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID]
) extends Document[W]

case class InfoDocument[W[_]](
  id: UUID,
  organizationId: W[UUID],
  userId: W[UUID],
  accountId: W[UUID],
  documentInfo: DocumentInfo[W]
) extends Document[W]


case class DocumentInfo[W[_]](
  number: W[String],
  date: W[ZonedDateTime],
  note: W[String],
  linkedDocuments: Seq[String]
)

object DocumentInfo {
  implicit val decoderInfoId: Decoder[DocumentInfo[cats.Id]] = deriveDecoder
  implicit val encoderInfoId: Encoder[DocumentInfo[cats.Id]] = deriveEncoder
  implicit val decoderInfoOption: Decoder[DocumentInfo[Option]] = deriveDecoder
  implicit val encoderInfoOption: Encoder[DocumentInfo[Option]] = deriveEncoder
}

import io.circe.generic.semiauto.{deriveDecoder => genericallyDeriveDecoder, deriveEncoder => genericallyDeriveEncoder}

implicit val encodeDocumentId: ObjectEncoder[Document[cats.Id]] = genericallyDeriveEncoder
implicit val decodeDocumentId: Decoder[Document[cats.Id]] = genericallyDeriveDecoder
implicit val encodeDocumentOption: ObjectEncoder[Document[Option]] = genericallyDeriveEncoder
implicit val decodeDocumentOption: Decoder[Document[Option]] = genericallyDeriveDecoder


val docInfo = DocumentInfo[Option](
  Some("123"),
  Some(ZonedDateTime.now()),
  None,
  List.empty[String]
)

val commonDoc = CommonDocument[Option](
  UUID.fromString("0-0-0-0-0"),
  Some(UUID.fromString("0-0-0-0-0")),
  None,
  None
)

val infoDoc = InfoDocument[Option](
  UUID.fromString("0-0-0-0-0"),
  Some(UUID.fromString("0-0-0-0-0")),
  None,
  None,
  docInfo
)