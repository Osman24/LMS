package tr.lms.dsl

/**
 * Created by OsmanSelçuk on 28.12.2014.
 */

import scala.virtualization.lms.common._
import scala.reflect.SourceContext
import java.io._

trait DslImpl extends DslExp { q =>
  val codegen = new DslGen {
    val IR: q.type = q
  }
}