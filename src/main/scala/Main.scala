package fr.eseo.lang

import scala.io.Source
import scala.io.StdIn.readLine
import scala.util.control.Breaks.break

object Main {
  def main(args: Array[String]): Unit = {

    val test = "var x = 4 ;"

    if (args.size == 0) {
      runRepl()
    } else if (args.size == 1) {
      runFile(args(0))
    } else {
      println("Usage: jeseo [script]")
      System.exit(64)
    }
  }

  private def runRepl(): Unit = {

    while (true) {
      print("> ")
      val line: String = readLine()
      if (line == null) break;
      println(run(line))
    }
  }

  private def runFile(path: String): Unit = {

    val bufferedIterator = Source.fromFile(path)
    bufferedIterator.getLines().foreach(line => println(run(line)))
    bufferedIterator.close()
  }

  private def run(source: String): List[String] = {
    source.split("\\s").toList
  }


}