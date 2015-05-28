package cml.algebra

import cml.algebra.traits._

object Instances {
  class FunctionAdditive[A, B](implicit a: Additive[B]) extends Additive[(A) => B] {
    override val zero: (A) => B = _ => a.zero
    override def add(f: (A) => B, g: (A) => B): (A) => B = x => a.add(f(x), g(x))
    override def neg(f: (A) => B): (A) => B = x => a.neg(f(x))

    override def runtimeClass: Class[_] = zero.getClass
  }

  implicit def functionAdditive[A, B](implicit a: Additive[B]): Additive[(A) => B] =
    new FunctionAdditive[A, B]

  implicit object FloatFloating extends Floating[Float] {
    override def add(x: Float, y: Float): Float = x + y
    override def sub(x: Float, y: Float): Float = x - y
    override def neg(x: Float): Float = -x
    override val zero: Float = 0

    override def mul(x: Float, y: Float): Float = x * y
    override val one: Float = 1
    override def fromInt(n: Int): Float = n

    override def div(x: Float, y: Float): Float = x / y
    override def inv(x: Float): Float = 1 / x

    override def abs(x: Float): Float = math.abs(x)
    override def signum(x: Float): Float = math.signum(x)
    override def exp(x: Float): Float = math.exp(x).toFloat
    override def log(x: Float): Float = math.log(x).toFloat
    override def sqrt(x: Float): Float = math.sqrt(x).toFloat
    override def sin(x: Float): Float = math.sin(x).toFloat
    override def cos(x: Float): Float = math.cos(x).toFloat
    override def tan(x: Float): Float = math.tan(x).toFloat
    override def asin(x: Float): Float = math.asin(x).toFloat
    override def acos(x: Float): Float = math.acos(x).toFloat
    override def atan(x: Float): Float = math.atan(x).toFloat
    override def sinh(x: Float): Float = math.sinh(x).toFloat
    override def cosh(x: Float): Float = math.cosh(x).toFloat
    override def tanh(x: Float): Float = math.tanh(x).toFloat

    override def fromFloat(x: Float): Float = x
    override def fromDouble(x: Double): Float = x.toFloat

    override def isNaN(x: Float): Boolean = x.isNaN
    override def toFloat(x: Float): Float = x
    override def toDouble(x: Float): Double = x.toDouble
    override val infinity: Float = Float.PositiveInfinity
  }

  implicit object DoubleFloating extends Floating[Double] {
    override def add(x: Double, y: Double): Double = x + y
    override def sub(x: Double, y: Double): Double = x - y
    override def neg(x: Double): Double = -x
    override val zero: Double = 0

    override def mul(x: Double, y: Double): Double = x * y
    override val one: Double = 1
    override def fromInt(n: Int): Double = n

    override def div(x: Double, y: Double): Double = x / y
    override def inv(x: Double): Double = 1 / x

    override def abs(x: Double): Double = math.abs(x)
    override def signum(x: Double): Double = math.signum(x)
    override def exp(x: Double): Double = math.exp(x)
    override def log(x: Double): Double = math.log(x)
    override def sqrt(x: Double): Double = math.sqrt(x)
    override def sin(x: Double): Double = math.sin(x)
    override def cos(x: Double): Double = math.cos(x)
    override def tan(x: Double): Double = math.tan(x)
    override def asin(x: Double): Double = math.asin(x)
    override def acos(x: Double): Double = math.acos(x)
    override def atan(x: Double): Double = math.atan(x)
    override def sinh(x: Double): Double = math.sinh(x)
    override def cosh(x: Double): Double = math.cosh(x)
    override def tanh(x: Double): Double = math.tanh(x)

    override def fromFloat(x: Float): Double = x
    override def fromDouble(x: Double): Double = x

    override def isNaN(x: Double): Boolean = x.isNaN
    override def toDouble(x: Double): Double = x
    override def toFloat(x: Double): Float = x.toFloat
    override val infinity: Double = Double.PositiveInfinity
  }

  implicit object BooleanField extends Field[Boolean] {
    override def add(x: Boolean, y: Boolean): Boolean = x ^ y
    override def neg(x: Boolean): Boolean = x
    override def mul(x: Boolean, y: Boolean): Boolean = x && y
    override def inv(x: Boolean): Boolean = true

    override val zero: Boolean = false
    override val one: Boolean = true
  }
}