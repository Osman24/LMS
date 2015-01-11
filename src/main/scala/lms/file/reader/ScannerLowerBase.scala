package lms.file.reader

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext

trait ScannerLowerBase extends Base with UncheckedOps {
  def open(name: Rep[String]): Rep[Int]
  def fclose(fd: Rep[Int]): Rep[Unit]
  def filelen(fd: Rep[Int]): Rep[Int]
  def mmap[T:Manifest](fd: Rep[Int], len: Rep[Int]): Rep[Array[T]]
  def stringFromCharArray(buf: Rep[Array[Char]], pos: Rep[Int], len: Rep[Int]): Rep[String]
  def prints(s: Rep[String]): Rep[Int]
  def infix_toInt(c: Rep[Char]): Rep[Int] = c.asInstanceOf[Rep[Int]]
}