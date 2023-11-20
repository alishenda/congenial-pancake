package fr.eseo.lang

import TokenType.{COMMA, DOT, LEFT_BRACE, LEFT_PAREN, MINUS, PLUS, RIGHT_BRACE, RIGHT_PAREN, SEMICOLON, STAR, TokenType}

import scala.annotation.tailrec

case class Scanner(
                    source: String,
                    tokens: List[Token],
                    start: Int,
                    current: Int,
                    line: Int,
                    hadError: Option[String]
                  ) {

  private val isAtEnd: Boolean = current >= source.length

  @tailrec
  final def scanTokens(scannerState: Scanner): Scanner = {

    scannerState.hadError match {
      case Some(value) =>
        Main.hadError = true
        Main.error(scannerState.line, value)
        scannerState
      case None =>
        if (!scannerState.isAtEnd) {
          scanTokens(scannerState.scanToken())
        } else {
          scannerState.copy(
            tokens = scannerState.tokens.appended(Token(TokenType.EOF, "", null, scannerState.line))
          )
        }
    }
  }

  private def scanToken(): Scanner = {
    val c: Char = advance()
    c match {
      case '(' => addToken(LEFT_PAREN)
      case ')' => addToken(RIGHT_PAREN)
      case '{' => addToken(LEFT_BRACE)
      case '}' => addToken(RIGHT_BRACE)
      case ',' => addToken(COMMA)
      case '.' => addToken(DOT)
      case '-' => addToken(MINUS)
      case '+' => addToken(PLUS)
      case ';' => addToken(SEMICOLON)
      case '*' => addToken(STAR)
      case _ => this.copy(hadError = Some("Unexpected character '" + c + "'"))
    }
  }

  private def advance(): Char = source.charAt(current)

  private def addToken(tokenType: TokenType): Scanner = {
    addToken(tokenType, null)
  }

  private def addToken(tokenType: TokenType, literal: Object): Scanner = {

    val text: String = source.substring(start, current + 1)

    val state = this.copy(start = current + 1, current = current + 1, tokens = tokens.appended(Token(tokenType, text, literal, line)))
    state
  }
}
