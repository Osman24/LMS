package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

trait UtilOps extends Base {
  def infix_HashCode[T:Manifest](o: Rep[T])(implicit pos: SourceContext): Rep[Long]
  def infix_HashCode(o: Rep[String], len: Rep[Int])(implicit pos: SourceContext): Rep[Long]
}