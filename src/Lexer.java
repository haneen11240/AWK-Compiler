import java.io.IOException;

import java.util.LinkedList;
import java.util.HashMap;

public class Lexer {

	private StringHandler HandlesStrings;
	private int lineNumber;
	private int characterposition;
	private LinkedList<Token> TokenLikedlist = new LinkedList<Token>();
	private HashMap<String, Token.values> makeTokenType = new HashMap<>();
	private HashMap<String, Token.values> TwoCharacterSymbols = new HashMap<>();
	private HashMap<String, Token.values> OneCharacterSymbols = new HashMap<>();

	public Lexer(String StringDocument) {

		// create a string handler and pass a string to it
		HandlesStrings = new StringHandler(StringDocument, 0);
		lineNumber = 1;
		characterposition = 0;
		KeywordsHashMap();
		TwoCharacterHashMap();
		OneCharacterSymbolsHashMap();
	}

	/**
	 * This is a helper method called in the ProcessWord() method and in the
	 * constructor, It has a key of known word and makes a token type of each
	 * word(Enum types). then I add (put) them to the makeTokenType hashmap of type
	 * string and token type (token.value)
	 * 
	 * @return
	 */
	private HashMap<String, Token.values> KeywordsHashMap() {
		makeTokenType.put("while", Token.values.WHILE);
		makeTokenType.put("if", Token.values.IF);
		makeTokenType.put("do", Token.values.DO);
		makeTokenType.put("for", Token.values.FOR);
		makeTokenType.put("break", Token.values.BREAK);
		makeTokenType.put("continue", Token.values.CONTINUE);
		makeTokenType.put("else", Token.values.ELSE);
		makeTokenType.put("return", Token.values.RETURN);
		makeTokenType.put("BEGIN", Token.values.BEGIN);
		makeTokenType.put("END", Token.values.END);
		makeTokenType.put("print", Token.values.PRINT);
		makeTokenType.put("next", Token.values.NEXT);
		makeTokenType.put("in", Token.values.IN);
		makeTokenType.put("delete", Token.values.DELETE);
		makeTokenType.put("getline", Token.values.GETLINE);
		makeTokenType.put("exit", Token.values.EXIT);
		makeTokenType.put("nextfile", Token.values.NEXTFILE);
		makeTokenType.put("function", Token.values.FUNCTION);
		makeTokenType.put("printf", Token.values.PRINTF);
		return makeTokenType;
	}

	/**
	 * This is a helper method called in the ProcessSymbol() method and in the
	 * constructor, It has a key of symbols and makes a token type of each
	 * Symbol(Enum types). then I add (put) them to the TwoCharacterSymbols hashmap
	 * of type string and token type (token.value)
	 * 
	 * @return
	 */
	private HashMap<String, Token.values> TwoCharacterHashMap() {
		TwoCharacterSymbols.put(">=", Token.values.GREATERTHANEQUALTO);
		TwoCharacterSymbols.put("++", Token.values.INCREMENT);
		TwoCharacterSymbols.put("--", Token.values.DECREMENT);
		TwoCharacterSymbols.put("<=", Token.values.LESSTHANEQUALTO);
		TwoCharacterSymbols.put("==", Token.values.LOGICALEQUAL);
		TwoCharacterSymbols.put("!=", Token.values.NOTEQUAL);
		TwoCharacterSymbols.put("^=", Token.values.EXPONENT);
		TwoCharacterSymbols.put("%=", Token.values.MODULOASSIGNMENT);
		TwoCharacterSymbols.put("*=", Token.values.MULTIPLYCATIONASSIGNMENT);
		TwoCharacterSymbols.put("/=", Token.values.DIVIDEASSIGNMENT);
		TwoCharacterSymbols.put("+=", Token.values.PLUSASSIGNMENT);
		TwoCharacterSymbols.put("-=", Token.values.MINUSASSIGNMENT);
		TwoCharacterSymbols.put("!~", Token.values.NOTMATCH);
		TwoCharacterSymbols.put("&&", Token.values.LOGICALAND);
		TwoCharacterSymbols.put(">>", Token.values.APPEND);
		TwoCharacterSymbols.put("||", Token.values.LOGICALOR);
		return TwoCharacterSymbols;
	}

	/**
	 * This is a helper method called in the ProcessSymbol() method and in the
	 * constructor, It has a key of symbols and makes a token type of each
	 * Symbol(Enum types). then I add (put) them to the OneCharacterSymbols hashmap
	 * of type string and token type (token.value)
	 * 
	 * @return
	 */
	private HashMap<String, Token.values> OneCharacterSymbolsHashMap() {
		OneCharacterSymbols.put("{", Token.values.LEFTBRACE);
		OneCharacterSymbols.put("}", Token.values.RIGHTBRACE);
		OneCharacterSymbols.put("[", Token.values.LEFTBRACKET);
		OneCharacterSymbols.put("]", Token.values.RIGHTBRACKET);
		OneCharacterSymbols.put("(", Token.values.LEFTPARENTHESIS);
		OneCharacterSymbols.put(")", Token.values.RIGHTPARENTHESIS);
		OneCharacterSymbols.put("$", Token.values.DOLLAR);
		OneCharacterSymbols.put("~", Token.values.TILDE);
		OneCharacterSymbols.put("=", Token.values.EQUAL);
		OneCharacterSymbols.put("<", Token.values.LESSTHAN);
		OneCharacterSymbols.put(">", Token.values.GREATERTHAN);
		OneCharacterSymbols.put("!", Token.values.EXCLAMATIONMARK);
		OneCharacterSymbols.put("+", Token.values.PLUS);
		OneCharacterSymbols.put("^", Token.values.CARET);
		OneCharacterSymbols.put("-", Token.values.MINUS);
		OneCharacterSymbols.put("?", Token.values.QUESTIONMARK);
		OneCharacterSymbols.put(":", Token.values.COLON);
		OneCharacterSymbols.put("*", Token.values.ASTERISK);
		OneCharacterSymbols.put("/", Token.values.SLASH);
		OneCharacterSymbols.put("%", Token.values.PERCENT);
		OneCharacterSymbols.put(";", Token.values.SEPERATOR);
		OneCharacterSymbols.put("\n", Token.values.SEPERATOR);
		OneCharacterSymbols.put("|", Token.values.PIPE);
		OneCharacterSymbols.put(",", Token.values.COMMA);
		return OneCharacterSymbols;
	}

	/**
	 * This method convert the string to a characters then it peeks using peek
	 * method from StringHandler to check the next character if it's the characters
	 * that's allowed in the file. If so it will return a token linked list with the
	 * type of that token and the value. if not it's not one of the allowed
	 * characters, it will throw an exception error
	 * 
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Token> Lex() throws Exception {

		while (!(HandlesStrings.IsDone())) {
			char NextIcharacters = HandlesStrings.Peek(0);
			if (NextIcharacters == ' ' || NextIcharacters == '\t') {
				HandlesStrings.GetChar();
				characterposition++;

			} else if (NextIcharacters == '\n' || NextIcharacters == ';') {

				TokenLikedlist.add(new Token(Token.values.SEPERATOR, lineNumber, characterposition));
				HandlesStrings.GetChar();
				lineNumber++;
				characterposition = 0;
			} else if (NextIcharacters == '\r') {
				HandlesStrings.GetChar();
				characterposition++;
			} else if (Character.isLetter(NextIcharacters)) {
				TokenLikedlist.add(ProcessWord());
			} else if (Character.isDigit(NextIcharacters)) {
				TokenLikedlist.add(ProcessNumber());
			} else if (NextIcharacters == '#') {
				HandlesComments();
				lineNumber++;
			} else if (NextIcharacters == '“') {
				HandlesStrings.Swallow(1);
				TokenLikedlist.add(HandleStringLiteral());
			} else if (NextIcharacters == '`') {
				HandlesStrings.Swallow(1);
				TokenLikedlist.add(HandlePattern());
			} else if ((!(Character.isLetter(NextIcharacters))) && (!(Character.isDigit(NextIcharacters)))) {
				TokenLikedlist.add(ProcessSymbol());
			} else {
				throw new Exception("Not recognized characters");
			}
		}
		return TokenLikedlist;
	}

	/**
	 * This method is called in the lex() method if the character was not a letter
	 * or a digit. It has two Symbols and one symbols hashmaps, First it peeks at
	 * the next two letters then check if the two symbols hashmap have these two
	 * characters as a key, if so it returns the token type. Then It checks if the
	 * one symbols hashmap have that as a key, if so it returns the token type. if
	 * neither of these two hashmaps have it, then it returns null
	 * 
	 * @return
	 */
	private Token ProcessSymbol() {

		String CurrentTwoSymbols = HandlesStrings.PeekString(2);
		String CurrenOneSymbols = HandlesStrings.PeekString(1);
		Token.values HashMapValue;
		Token.values Hashmapvalue;
		if (TwoCharacterSymbols.containsKey(CurrentTwoSymbols)) {
			//System.out.println("test");
			HashMapValue = TwoCharacterSymbols.get(CurrentTwoSymbols);
			characterposition += 2;
			HandlesStrings.Swallow(2);

			return new Token(HashMapValue, lineNumber, characterposition);
		} else if (OneCharacterSymbols.containsKey(CurrenOneSymbols)) {
			Hashmapvalue = OneCharacterSymbols.get(CurrenOneSymbols);
			characterposition += 1;
			HandlesStrings.Swallow(1);

			return new Token(Hashmapvalue, lineNumber, characterposition);
		}
		return null;
	}

	/**
	 * This method is called in Lex() method, when a back tick ` is encountered. in
	 * the Lex() method, I had an if statement for the back tick ` that swallow the
	 * first character. Then I loop until its the end of the string and when
	 * CurrentCharacters is ` finally, I used the peek method then the getChar
	 * method to return the Characters then added each CurrentCharacters to
	 * patternLiteral and move the index.
	 * 
	 * @return
	 */
	private Token HandlePattern() {
		String patternLiteral = "";
		char CurrentCharacters = HandlesStrings.Peek(0);

		while ((!(HandlesStrings.IsDone())) && CurrentCharacters != '`') {
			CurrentCharacters = HandlesStrings.GetChar();
			patternLiteral += CurrentCharacters;
			characterposition++;
			CurrentCharacters = HandlesStrings.Peek(0);
		}
		if (CurrentCharacters == '`') {
			CurrentCharacters = HandlesStrings.GetChar();
			characterposition++;
		}

		return new Token(Token.values.Pattern, lineNumber, characterposition, patternLiteral.toString());
	}

	/**
	 * This method is called in the Lex() method, if “ was in the string, It makes
	 * it output a token of string literal. it has a while loop that check if the
	 * string is done and the Current Character is not ”, which means its the end of
	 * the string literal. inside the while loop it ignores the backslash \\ and
	 * then considers ” as part of the string literal. if there's no backslash it
	 * adds every Characters in the sting literal.
	 * 
	 * @return
	 */
	private Token HandleStringLiteral() {

		String StringLiterals = "";
		char CurrentCharacters = HandlesStrings.Peek(0);
		// HandlesStrings.Swallow(1);

		while ((!(HandlesStrings.IsDone())) && CurrentCharacters != '”') {

			if (CurrentCharacters == '\\') {
				CurrentCharacters = HandlesStrings.GetChar();
				characterposition++;
				CurrentCharacters = HandlesStrings.Peek(0);
				if (CurrentCharacters == '”') {
					CurrentCharacters = HandlesStrings.GetChar();
					StringLiterals += CurrentCharacters;
					characterposition++;
					CurrentCharacters = HandlesStrings.Peek(0);
				}
			} else {
				CurrentCharacters = HandlesStrings.GetChar();
				StringLiterals += CurrentCharacters;
				characterposition++;
				CurrentCharacters = HandlesStrings.Peek(0);
			}
		}
		if (CurrentCharacters == '”') {
			CurrentCharacters = HandlesStrings.GetChar();
			characterposition++;

		}
		return new Token(Token.values.StringLiterals, lineNumber, characterposition, StringLiterals.toString());

	}

	/**
	 * This method is called in the lex() method, when # is encountered at the
	 * beginning or middle of the line this is a comment in AWK, and this method
	 * will loop till the end of the line. I return Character, then I update the
	 * character position and used the peek method to check the every Character
	 */
	private void HandlesComments() {
		char CurrentCharacters = HandlesStrings.Peek(0);

		while ((!(HandlesStrings.IsDone())) && CurrentCharacters != '\n') {
			CurrentCharacters = HandlesStrings.GetChar();
			characterposition++;
			CurrentCharacters = HandlesStrings.Peek(0);
		}
	}

	/**
	 * This method process a word a digit and _ it returns a value with its right
	 * token type I used the peek method then the get char to return the Characters
	 * and move the index. Then I used an if statement to check if the word one of
	 * the key strings that I added to the hashmap, if so, then it returns the value
	 * of the hashmaps (token type)
	 * 
	 * @return
	 */
	private Token ProcessWord() {
		String MakeAWord = "";
		char CurrentCharacters = HandlesStrings.Peek(0);
		Token.values hashMapValue;

		while ((!(HandlesStrings.IsDone())) && Character.isLetter(CurrentCharacters) || CurrentCharacters == '_'
				|| Character.isDigit(CurrentCharacters)) {

			CurrentCharacters = HandlesStrings.GetChar();
			MakeAWord += CurrentCharacters;
			characterposition++;
			CurrentCharacters = HandlesStrings.Peek(0);

			}
			if (makeTokenType.containsKey(MakeAWord)) {
				hashMapValue = makeTokenType.get(MakeAWord);
				return new Token(hashMapValue, lineNumber, characterposition);
		}
		return new Token(Token.values.WORD, lineNumber, characterposition, MakeAWord.toString());
	}

	/**
	 * This method process a digit and a . it returns a value with its right token
	 * type I used the peek method then the get char to return the Characters and
	 * move the index.
	 * 
	 * @return
	 * @throws Exception 
	 */
	private Token ProcessNumber() throws Exception {

		String Numbers = "";
	boolean OnePeriod = false;
		char CurrentCharacters = HandlesStrings.Peek(0);
		while ((!(HandlesStrings.IsDone())) && Character.isDigit(CurrentCharacters) || (CurrentCharacters == '.' 
				&& !OnePeriod )) {
			
			if (CurrentCharacters == '.') {
				OnePeriod = true;
			}
	
			CurrentCharacters = HandlesStrings.GetChar();
			Numbers += CurrentCharacters;
			characterposition++;
			CurrentCharacters = HandlesStrings.Peek(0);
			
		

		}
		return new Token(Token.values.NUMBER, lineNumber, characterposition, Numbers.toString());
	}
}