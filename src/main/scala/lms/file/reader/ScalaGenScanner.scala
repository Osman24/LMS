package lms.file.reader

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext

trait ScalaGenScanner extends ScalaGenEffect {
  val IR: ScannerExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = rhs match {
    case ScannerNew(fn) => emitValDef(sym, src"new scala.lms.tutorial.Scanner($fn)")
    case ScannerNext(s, d) => emitValDef(sym, src"$s.next($d)")
    case ScannerHasNext(s) => emitValDef(sym, src"$s.hasNext")
    case ScannerClose(s) => emitValDef(sym, src"$s.close")
    case _ => super.emitNode(sym, rhs)
  }
}