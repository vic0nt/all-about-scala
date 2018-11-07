sealed abstract class PList[T]
final case class PNil[T]() extends PList[T]
final case class PCons[T](head: T, tail: PList[T]) extends PList[T]

sealed abstract class MList {self =>
  type T
  def uncons: Option[MCons {type T = self.T}]
}

sealed abstract class MNil extends MList {
  def uncons = None
}

sealed abstract class MCons extends MList {self =>
  val head: T
  val tail: MList {type T = self.T}
  def uncons = Some(self: MCons {type T = self.T})
}

class Zzz {a => 5}
val z = new Zzz


