package lms.file.reader

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext

trait ScannerExp extends ScannerBase with EffectExp {
  case class ScannerNew(fn: Exp[String]) extends Def[Scanner]
  case class ScannerNext(s: Exp[Scanner], d: Exp[Char]) extends Def[String]
  case class ScannerHasNext(s: Exp[Scanner]) extends Def[Boolean]
  case class ScannerClose(s: Exp[Scanner]) extends Def[Unit]

  override def newScanner(fn: Rep[String])(implicit pos: SourceContext): Rep[Scanner] =
    reflectMutable(ScannerNew(fn))
  override def scannerNext(s: Rep[Scanner], d: Char)(implicit pos: SourceContext): Rep[String] =
    reflectWrite(s)(ScannerNext(s, Const(d)))
  override def scannerHasNext(s: Rep[Scanner])(implicit pos: SourceContext): Rep[Boolean] =
    reflectWrite(s)(ScannerHasNext(s))
  override def scannerClose(s: Rep[Scanner])(implicit pos: SourceContext): Rep[Unit] =
    reflectWrite(s)(ScannerClose(s))

  override def mirror[A:Manifest](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case Reflect(e@ScannerNew(fn), u, es) => reflectMirrored(Reflect(ScannerNew(f(fn)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case Reflect(ScannerNext(s, d), u, es) => reflectMirrored(Reflect(ScannerNext(f(s), f(d)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case Reflect(ScannerHasNext(s), u, es) => reflectMirrored(Reflect(ScannerHasNext(f(s)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case Reflect(ScannerClose(s), u, es) => reflectMirrored(Reflect(ScannerClose(f(s)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case _ => super.mirror(e,f)
  }).asInstanceOf[Exp[A]]
}