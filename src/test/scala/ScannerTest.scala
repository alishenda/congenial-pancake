package fr.eseo.lang

import org.scalatest.funsuite.AnyFunSuiteLike

class ScannerTest extends AnyFunSuiteLike {

  test("testScanTokens") {
    val sourceCode = "_)(}{,.-+;*"

    val scannerState = Scanner(sourceCode, List(), 0, 0, 1, None)
    val value = scannerState.scanTokens(scannerState)
    value.tokens.foreach(println(_))

    assert(value.tokens === List(
      Token(TokenType.RIGHT_PAREN, ")", null, 1),
      Token(TokenType.LEFT_PAREN, "(", null, 1),
      Token(TokenType.RIGHT_BRACE, "}", null, 1),
      Token(TokenType.LEFT_BRACE, "{", null, 1),
      Token(TokenType.COMMA, ",", null, 1),
      Token(TokenType.DOT, ".", null, 1),
      Token(TokenType.MINUS, "-", null, 1),
      Token(TokenType.PLUS, "+", null, 1),
      Token(TokenType.SEMICOLON, ";", null, 1),
      Token(TokenType.STAR, "*", null, 1),
      Token(TokenType.EOF, "", null, 1),
    ))

  }

}
