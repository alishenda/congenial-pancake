package fr.eseo.lang

import scala.io.Source
import scala.io.StdIn.readLine
import scala.util.control.Breaks.break

object Main {

  var hadError: Boolean = false

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
      hadError = false;
    }
  }

  private def runFile(path: String): Unit = {

    val bufferedIterator = Source.fromFile(path)

    val impureRun: String => Unit = line => {
      val result = run(line)
      if (hadError) System.exit(65)
      println(result)
    }

    bufferedIterator.getLines().foreach(impureRun)
    bufferedIterator.close()
  }

  private def run(source: String): List[String] = {
    source.split("\\s").toList
  }

  def error(line: Int, message: String): Unit = {
    report(line, "", message)
  }

  private def report(line: Int, where: String, message: String) : Unit = {

    Console.err.println("[line " + line + "] Error" + where + ": " + message)
    hadError = true;
  }
}

