package cml.models

import cml.Model
import cml.algebra._

import scalaz.Functor

final case class FunctorMap[F[_], U[_], V[_]] (
  m: Model[U, V]
) (implicit
  f: Functor[F]
) extends Model[({type T[A] = F[U[A]]})#T, ({type T[A] = F[V[A]]})#T] {
  override type Params[A] = m.Params[A]

  override implicit val params = m.params

  override def apply[A](inst: Params[A])(input: F[U[A]])(implicit a: Analytic[A]): F[V[A]] =
    f.map(input)(m(inst)(_))
}
