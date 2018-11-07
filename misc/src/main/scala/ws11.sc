import cats.arrow.FunctionK
import cats.{Id, ~>}
import henkan.optional.all._

case class Document[W[_]](field1: W[String], field2: W[Int], info: W[DocumentInfo[W]])
case class DocumentInfo[W[_]](field3: W[String], field4: W[Int])

val docInfoId = DocumentInfo[cats.Id]("lkj",100)
val docId = Document[cats.Id]("abc", 10, docInfoId)
val docOption = Document[Option](Some("abc"), Some(10), None)

// from(Domain("a", 2)).toOptional[Message]

from(docId).toOptional[Document[Option]]