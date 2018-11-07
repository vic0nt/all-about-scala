import java.time.ZonedDateTime
import java.util.UUID

import io.circe.{Decoder, Encoder, ObjectEncoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import scala.language.higherKinds

package object circe {
  abstract class Document[W[_]] {
    val id: UUID
    val organizationId: W[UUID]
    val userId: W[UUID]
    val accountId: W[UUID]
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

  abstract class BudgetDocument[W[_]] extends Document[W] {
    val id: UUID
    val organizationId: W[UUID]
    val userId: W[UUID]
    val accountId: W[UUID]
    val documentInfo: DocumentInfo[W]
    val budgetary: Budgetary[W]
  }

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

  abstract class Budgetary[W[_]] { val status: W[String] }
  case class CommonBudgetary[W[_]](status: W[String]) extends Budgetary[W]
  case class TaxBudgetary[W[_]](status: W[String], tax: W[String]) extends Budgetary[W]

  object Budgetary {
    implicit val decoderBudgetaryId: Decoder[Budgetary[cats.Id]] = deriveDecoder
    implicit val encoderBudgetaryId: Encoder[Budgetary[cats.Id]] = deriveEncoder
    implicit val decoderBudgetaryOption: Decoder[Budgetary[Option]] = deriveDecoder
    implicit val encoderBudgetaryOption: Encoder[Budgetary[Option]] = deriveEncoder
  }

}
