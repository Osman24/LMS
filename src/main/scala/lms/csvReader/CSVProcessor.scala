package lms.csvReader

/**
 * Created by OsmanSelçuk on 10.01.2015.
 */

import scala.virtualization.lms.common._
import tr.lms.dsl._
import lms.file.reader._

import scala.collection.mutable.ListBuffer
trait CSVProcessor extends Dsl with ScannerLowerExp{

  abstract class RField {
    def print()
    def compare(o: RField): Rep[Boolean]
    def hash: Rep[Long]
  }
  case class RString(data: Rep[String], len: Rep[Int]) extends RField {
    def print() = printString(data, len)
    def compare(o: RField) = o match { case RString(data2, len2) => if (len != len2) false else {
      // TODO: we may or may not want to inline this (code bloat and icache considerations).
      var i = 0
      while (i < len && data.charAt(i) == data2.charAt(i)) {
        i += 1
      }
      i == len
    }}
    def hash = data.HashCode(len)
  }
  case class RInt(value: Rep[Int]) extends RField {
    def print() = printf("%d",value)
    def compare(o: RField) = o match { case RInt(v2) => value == v2 }
    def hash = value.asInstanceOf[Rep[Long]]
  }

  class Scanner(name: Rep[String])  {
    val fd = open(name)
    val fl = filelen(fd)
    val data = mmap[Char](fd,fl)
    var pos = 0
    var pos2 = 0
    var lineFinish = 0
    var lineCorrupted = false
    var lineFinished = false

    def next(d: Rep[Char]) = {
      val start = pos: Rep[Int] // force read
      while (data(pos) != d && hasNext) pos += 1
      val len = pos - start
      pos += 1
      RString(stringFromCharArray(data,start,len), len)
    }

    def validateLine(d: Rep[Char], schema:Array[Int]): Rep[Boolean] ={
      pos2 = pos
      var i=0
      val schemaData = staticData(schema)
      val length = schema.length
      var status = true
      while(data(pos2) != '\n' && data(pos2) != 13 && hasNext){ //Cariage Return Fix
        if(data(pos2) == d){
          i += 1
        }
        else if(i == length){
          status = false
          breakLoop()
        }
        else if(schemaData(i) == 1 && (data(pos2)< '0' || data(pos2) > '9')){
          status = false
          breakLoop()
        }
        pos2 += 1
      }
      status

    }

    def getNextLine() = {
      val start = pos: Rep[Int] // force read
      while (data(pos) != '\n' && hasNext) pos += 1
      val len = pos - start
      pos +=1
      RString(stringFromCharArray(data,start,len), len)
    }

    def nextString(d: Rep[Char]) = {
      val start = pos: Rep[Int]
      while (data(pos)!= d && data(pos)!= '\n' && hasNext) pos+=1
      lineFinish = pos -1
      if(data(pos) == '\n') lineFinish = true
    }

    def hasNext = pos < fl
    def close = fclose(fd)
  }


  def Process(fileName : Rep[String], schema: Array[Int]): Rep[Unit] = {
    var i =0
    val scanner = new Scanner(fileName)
    while(scanner.hasNext){
      val isValidated = scanner.validateLine(',', schema);
      if(isValidated){
        scanner.getNextLine().print()
      }
      else{
        val line = scanner.getNextLine()
      }

    }

  }
}
