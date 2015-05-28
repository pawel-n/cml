package cml

import cml.algebra.traits._

abstract class CostFun[In[_], Out[_]] {
  def scoreSample[A](sample: Sample[In[A], Out[A]])(implicit an: Analytic[A]): A
  def regularization[V[_], A](instance: V[A])(implicit an: Analytic[A], space: LocallyConcrete[V]): A

  def mean[A](data: Seq[Sample[In[A], Out[A]]])(implicit an: Analytic[A]): A = {
    import an.analyticSyntax._
    data.map(scoreSample(_)).fold[A](_0)(_ + _) / fromInt(data.size)
  }

  def sum[A](data: Seq[Sample[In[A], Out[A]]])(implicit an: Analytic[A]): A = {
    import an.analyticSyntax._
    data.map(scoreSample(_)).fold[A](_0)(_ + _)
  }

  def apply[V[_], A](
    instance: V[A],
    data: Seq[Sample[In[A], Out[A]]]
  )(implicit an: Analytic[A], space: LocallyConcrete[V]): A =
    an.add(sum(data), regularization(instance))
}