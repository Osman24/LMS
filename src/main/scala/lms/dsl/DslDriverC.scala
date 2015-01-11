package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

abstract class DslDriverC[A:Manifest,B:Manifest] extends DslSnippet[A,B] with DslExp { q =>
  val codegen = new DslGenC {
    val IR: q.type = q
  }
  lazy val code: String = {
    val source = new java.io.StringWriter()
    codegen.emitSource(snippet, "Snippet", new java.io.PrintWriter(source))
    source.toString
  }
  def eval(a:A): Unit = { // TBD: should read result of type B?
  val out = new java.io.PrintWriter("./snippet.c")
    out.println(code)
    out.close
    //TODO: use precompile
    (new java.io.File("./snippet")).delete
    import scala.sys.process._
    (s"gcc -std=c99 -O3 ./snippet.c -o ./snippet":ProcessBuilder).lines.foreach(scala.Console.println _)
    (s"./snippet $a":ProcessBuilder).lines.foreach(scala.Console.println _)
  }
}
