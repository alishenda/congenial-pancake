package fr.eseo.lang

object Main {
  def main(args: Array[String]): Unit = {
    val test = "var x = 4 ;"
    println(f(test))
  }

  def f(input: String): List[String] = {
    input.split("\\s").toList
  }


}