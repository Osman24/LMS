package lms.csvReader

/**
 * Created by OsmanSelçuk on 10.01.2015.
 */

import scala.virtualization.lms.common._
import tr.lms.dsl._
import lms.file.reader._

import scala.collection.mutable.ListBuffer
trait CSVProcessor extends Dsl with ScannerLowerExp{

  class Scanner(name: Rep[String], schema: Vector[String])  {
    val fd = open(name)
    val fl = filelen(fd)
    val data = mmap[Char](fd,fl)
    var pos = 0
    var lineFinish = 0
    var lineCorrupted = false
    var lineFinished = false

    /*def nextLine(d: Rep[Char]) = {
      lineCorrupted = false
      val start = pos: Rep[Int]
      while (data(pos) != '\r' && data(pos)!= '\n' && hasNext){
        if(data(pos) == d){
          delimeterCount += 1
        }
        pos += 1
      }
      lineFinish = pos -1
      if(data(pos) == '\r')pos += 1 // For linux and windows together

    }*/

    def nextString(d: Rep[Char]) = {
      val start = pos: Rep[Int]
      while (data(pos)!= d && data(pos)!= '\n' && hasNext) pos+=1
      lineFinish = pos -1
      if(data(pos) == '\n') lineFinish = true
    }



    def hasNext = pos < fl
    def close = fclose(fd)
  }



  def Process(fileName : Rep[String], schema: Vector[String]): Rep[Unit] = {
    var i =0
    val scanner = new Scanner(fileName, schema)
    while(scanner.hasNext){
      for(i <- 0 to schema.length - 1){

        if(schema(i).equals("String")){
          scanner.nextString(',')
        }
        else if(schema(i).equals("Int")){

        }
        else{}

      }


    }

  }
}
