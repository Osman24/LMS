package lms.file.reader

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext

trait ScannerLowerExp extends ScannerLowerBase with UncheckedOpsExp {
  def open(name: Rep[String]) = uncheckedPure[Int]("open(",name,",0);")
  def fclose(fd: Rep[Int]) = unchecked[Unit]("fclose(",fd,")")
  def filelen(fd: Rep[Int]) = uncheckedPure[Int]("fsize(",fd,")") // FIXME: fresh name
  def mmap[T:Manifest](fd: Rep[Int], len: Rep[Int]) = uncheckedPure[Array[T]]("mmap(0, ",len,", PROT_READ, MAP_FILE | MAP_SHARED, ",fd,", 0)")
  def stringFromCharArray(data: Rep[Array[Char]], pos: Rep[Int], len: Rep[Int]): Rep[String] = uncheckedPure[String](data,"+",pos)
  def copyCharArray(data: Rep[Array[Char]], pos: Rep[Int], len: Rep[Int]) = unchecked[Array[Char]]("copyBuffer(",data,"+",pos, ",", len, ")")
  def printBufWithLen(buf: Rep[Array[Char]], pos: Rep[Int], len: Rep[Int]) = unchecked[Int]("printf(\"%.*s\",",len,",",pos,"+",buf,")")
  def printString(buf: Rep[String], len: Rep[Int]) = unchecked[Int]("printf(\"%.*s\",",len,",",buf,")")
  def printInt(value: Rep[Int]) = unchecked[Int]("printf(\"%d\\n\",", value, ")")
  def prints(s: Rep[String]): Rep[Int] = unchecked[Int]("printll(",s,")")
  def compileRegex(s: Rep[String]): Rep[Int] = unchecked[Int]("regcomp(&regex,\"", s ,"\", 0)")
  def runRegex(s: Rep[String]):Rep[Int] = unchecked[Int]("regexec(&regex, \"", s ,"\", 0, NULL, 0)")
  def breakLoop():Rep[Unit] = unchecked[Unit]("break")
  def continueLoop():Rep[Unit] = unchecked[Unit]("continue")
 // def splitString(s: Rep[String])= unchecked[Array[String]]("str")

}

trait CGenScannerLower extends CGenUncheckedOps