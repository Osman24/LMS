package lms.file.reader

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext

trait ScannerBase extends Base {
  implicit class RepScannerOps(s: Rep[Scanner]) {
    def next(d: Char)(implicit pos: SourceContext) = scannerNext(s, d)
    def hasNext(implicit pos: SourceContext) = scannerHasNext(s)
    def close(implicit pos: SourceContext) = scannerClose(s)
  }
  def newScanner(fn: Rep[String])(implicit pos: SourceContext): Rep[Scanner]
  def scannerNext(s: Rep[Scanner], d: Char)(implicit pos: SourceContext): Rep[String]
  def scannerHasNext(s: Rep[Scanner])(implicit pos: SourceContext): Rep[Boolean]
  def scannerClose(s: Rep[Scanner])(implicit pos: SourceContext): Rep[Unit]
}