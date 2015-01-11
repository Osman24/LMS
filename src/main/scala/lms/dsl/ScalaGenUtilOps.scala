package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

trait ScalaGenUtilOps extends ScalaGenBase {
  val IR: UtilOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = rhs match {
    case ObjHashCode(o) => emitValDef(sym, src"$o.##")
    case _ => super.emitNode(sym, rhs)
  }
}
