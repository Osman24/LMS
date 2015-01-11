package tr.lms

import lms.file.reader.{CGenScannerLower, ScannerLowerExp, Scanner}
import tr.lms.dsl.{DslGenC, DslDriver, DslDriverC}
import scala.virtualization.lms.common._
import scala.io.Source._
import lms.query._
import lms.csvReader._
/**
 * Created by OsmanSelçuk on 27.12.2014.
 */

object Main {

  def main(args: Array[String]) {

    println("Program started")
    val wholeT0 = System.currentTimeMillis
    val schema = Vector[String]("String", "String")
    val snippet = new  DslDriverC[String,Unit] with ScannerLowerExp with CSVProcessor{q =>
      override val codegen = new DslGenC with CGenScannerLower {
        val IR: q.type = q
      }
      def snippet(fileName: Rep[String]) = {
          Process(fileName, schema)
      }


    }


    snippet.eval("deneme.csv")
    println("Program completely finished in a duration : " + (System.currentTimeMillis - wholeT0) + "ms")
  }
}
