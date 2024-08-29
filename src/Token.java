
public class Token {

	public enum values {
		WORD, NUMBER, SEPERATOR, WHILE, IF, DO, FOR, BREAK, CONTINUE, ELSE, RETURN, BEGIN, END,PRINT, PRINTF, NEXT,
		IN, DELETE, GETLINE, EXIT,NEXTFILE,FUNCTION, StringLiterals, Pattern, GREATERTHANEQUALTO, INCREMENT, DECREMENT,
		LESSTHANEQUALTO, LOGICALEQUAL, NOTEQUAL, EXPONENT, MODULOASSIGNMENT, MULTIPLYCATIONASSIGNMENT, DIVIDEASSIGNMENT, PLUSASSIGNMENT,
		MINUSASSIGNMENT, NOTMATCH,LOGICALAND, APPEND, LOGICALOR, LEFTBRACE , RIGHTBRACE, LEFTBRACKET, RIGHTBRACKET, LEFTPARENTHESIS,
		RIGHTPARENTHESIS, DOLLAR, TILDE, EQUAL ,LESSTHAN, GREATERTHAN, EXCLAMATIONMARK, PLUS, CARET, MINUS, QUESTIONMARK, COLON, ASTERISK,
		SLASH, PERCENT, PIPE, COMMA ;
	}
	 private values TokenType;
	 private String TheValue;
	 private int lineNumber;
	 private int characterposition;

//constructor for Token Type, line number, character position of the start of the token
public Token(values TokenType, int lineNumber, int characterposition) {
	this.TokenType = TokenType;
	this.lineNumber = lineNumber;
	this.characterposition = characterposition;
}

//constructor for Token Type, line number, character position of the start of the token and value of token type
public Token(values TokenType, int lineNumber, int characterposition, String TheValue ){
	
	this.TokenType = TokenType;
	this.lineNumber = lineNumber;
	this.characterposition = characterposition;
	this.TheValue = TheValue;	
}

public values getTokenType() {
	return TokenType;
}

public String getTheValue() {
	return TheValue;
}
//A ToString method that returns the token type if it dosen't have a value, if it dose then
//it returns the token type and the value of that token type

public String toString() {
	 
	 if (TheValue == null) {
		 return ("Token Type is"+" "+ TokenType); 
	 }
	 else
	 {
	return ("Token Type is"+" "+ TokenType + " "+ "(" + TheValue + ")");
	 }
		}
			}