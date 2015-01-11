package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

trait CGenUtilOps extends CGenBase {
  val IR: UtilOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = rhs match {
    case StrSubHashCode(o,len) => emitValDef(sym, src"hash($o,$len)")
    case _ => super.emitNode(sym, rhs)
  }
}
