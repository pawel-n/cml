package cml.models

import cml._
import cml.algebra.Representable.HashMapWithDefault
import cml.algebra._

import scalaz.Const

case class HashMap[K, V[_]] (implicit
  valueSpace: Cartesian[V]
) extends Model[({type T[A] = Const[K, A]})#T, V] {
  override type Type[A] = HashMapWithDefault[K, V[A]]

  implicit val mapSpace = Representable.hashMap[K]
  override implicit val space =
    Representable.compose[({type T[A] = HashMapWithDefault[K, A]})#T, V](mapSpace, valueSpace)

  import ZeroFunctor.asZero

  def apply[A](inst: Type[A])(input: Const[K, A])(implicit field: Analytic[A]): V[A] =
    mapSpace.index(inst)(input.getConst)
}
