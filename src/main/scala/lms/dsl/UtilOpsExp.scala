package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

trait UtilOpsExp extends UtilOps with BaseExp {
  case class ObjHashCode[T:Manifest](o: Rep[T])(implicit pos: SourceContext) extends Def[Long]
  case class StrSubHashCode(o: Rep[String], len: Rep[Int])(implicit pos: SourceContext) extends Def[Long]
  def infix_HashCode[T:Manifest](o: Rep[T])(implicit pos: SourceContext) = ObjHashCode(o)
  def infix_HashCode(o: Rep[String], len: Rep[Int])(implicit pos: SourceContext) = StrSubHashCode(o,len)

  override def mirror[A:Manifest](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case e@ObjHashCode(a) => infix_HashCode(f(a))
    case e@StrSubHashCode(o,len) => infix_HashCode(f(o),f(len))
    case _ => super.mirror(e,f)
  }).asInstanceOf[Exp[A]]
}

