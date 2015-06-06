package cml.models

import cml._
import cml.algebra._

final case class SetMap[K, V[_]] (implicit
  valueSpace: Cartesian[V],
  ord: Ordering[K]
) extends Model[({type T[A] = K})#T, V] {
  override type Type[A] = TotalMap[K, V[A]]

  implicit val mapSpace = TotalMap.representable[K](ord)
  override implicit val space =
    Representable.compose[({type T[A] = TotalMap[K, A]})#T, V](mapSpace, valueSpace)

  import ZeroEndofunctor.asZero

  def apply[A](inst: Type[A])(input: K)(implicit field: Analytic[A]): V[A] =
    mapSpace.index(inst)(input)
}