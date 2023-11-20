package fr.eseo.lang

import TokenType.TokenType

case class Token(tokenType: TokenType, lexeme: String, literal: Object, line: Int) {
  override def toString: String = {
    "type: " + tokenType + ", lexeme: \"" + lexeme + "\", literal: " + literal
  }
}


