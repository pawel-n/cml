package cml.algebra.traits

import cml.syntax.FieldSyntax

trait Field[T] extends Ring[T] {
  def div(x: T, y: T): T = mul(x, inv(y))
  def inv(x: T): T

  /**
   * Adds one n times in O(log n) time.
   */
  def fromInt(n: Int): T = {
    import fieldSyntax._
    n match {
      case 0 => zero
      case 1 => one
      case _ if n < 0 => -this.fromInt(-n)
      case _ if n % 2 == 0 => {
        val half = this.fromInt(n/2)
        half + half
      }
      case _ => {
        val nearlyHalf = this.fromInt(n/2)
        nearlyHalf + nearlyHalf + one
      }
    }
  }

  /**
   * Adds one n times in O(log n) time.
   */
  def fromLong(n: Long): T = {
    import fieldSyntax._
    n match {
      case 0 => zero
      case 1 => one
      case _ if n < 0 => -this.fromLong(-n)
      case _ if n % 2 == 0 => {
        val half = this.fromLong(n/2)
        half + half
      }
      case _ => {
        val nearlyHalf = this.fromLong(n/2)
        nearlyHalf + nearlyHalf + one
      }
    }
  }

  val fieldSyntax = new FieldSyntax[T] { def F = Field.this }
}
