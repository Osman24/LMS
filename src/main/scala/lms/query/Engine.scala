package lms.query

/**
 * Created by OsmanSelçuk on 29.12.2014.
 */

import java.io.PrintStream

import scala.virtualization.lms.common._

trait Engine extends QueryProcessor with SQLParser {
  def query: String = "select * from C:/deneme.txt"
  def filename: String = "C:/deneme.txt"
  def liftTable(n: String): Table
  def eval: Unit
  def prepare: Unit = {}
  def run: Unit = execQuery(PrintCSV(parseSql(query)))
  override def dynamicFilePath(table: String): Table =
    liftTable(if (table == "?") filename else filePath(table))
  def evalString = {
    val source = new java.io.ByteArrayOutputStream()
    def withOutputFull(out: PrintStream)(func: => Unit): Unit = {
      val oldStdOut = System.out
      val oldStdErr = System.err
      try {
        System.setOut(out)
        System.setErr(out)
        scala.Console.withOut(out)(scala.Console.withErr(out)(func))
      } finally {
        out.flush()
        out.close()
        System.setOut(oldStdOut)
        System.setErr(oldStdErr)
      }
    }
    withOutputFull(new java.io.PrintStream(source)) {
      eval
    }
    source.toString
  }
}