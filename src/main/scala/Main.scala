package fr.eseo.lang

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val test = "var x = 4 ;"

    if (args.size == 0) {
      println("REPL")
    } else if (args.size == 1) {
      runFile(args(0))
    } else {
      println("Usage: jeseo [script]")
      System.exit(64)
    }
  }



  private def runFile(path: String): Unit = {

    val bufferedIterator = Source.fromFile(path)
    bufferedIterator.getLines().foreach(line => println(f(line)))
    bufferedIterator.close()
  }

  private def f(input: String): List[String] = {
    input.split("\\s").toList
  }


}