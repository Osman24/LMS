package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 27.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

abstract class DslDriver[A:Manifest,B:Manifest] extends DslSnippet[A,B] with DslImpl with CompileScala {
  lazy val f = compile(snippet)
  def precompile: Unit = f
  def nullout = new PrintStream(new OutputStream() {
    override def write(b: Int) = {  }
    override def write(b: Array[Byte]) = {}
    override def write(b: Array[Byte], off: Int, len: Int) = {}
  })
  def withOutput[T](out: PrintStream)(f: => Unit): Unit = {
    scala.Console.withOut(out)(scala.Console.withErr(out)(f))
  }
  def devnull(f: => Unit): Unit = {
    withOutput(nullout)(f)
  }
  def precompileSilently: Unit = devnull(f)
  def eval(x: A): B = f(x)
  lazy val code: String = {
    val source = new java.io.StringWriter()
    codegen.emitSource(snippet, "Snippet", new java.io.PrintWriter(source))
    source.toString
  }

}
