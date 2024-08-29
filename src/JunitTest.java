import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import org.junit.Test;

public class JunitTest {
	
@Test
    public void WordsDigit() throws Exception {
		
    String file = "Haneen Qasem student 20";  
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(4, TokenLikedlist.size());

    assertEquals("Token Type is WORD (Haneen)", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is WORD (Qasem)", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is WORD (student)", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is NUMBER (20)", TokenLikedlist.get(3).toString());
    
}

@Test
public void Digits() throws Exception {
	
    String file = "21 \n888 000";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(4, TokenLikedlist.size());

    assertEquals("Token Type is NUMBER (21)", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is SEPERATOR", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is NUMBER (888)", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is NUMBER (000)", TokenLikedlist.get(3).toString());
}


@Test
public void WordsDigits_Sepereter() throws Exception {
	
    String file = "ILikeCoding_ \n 000 ";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(3, TokenLikedlist.size());

    assertEquals("Token Type is WORD (ILikeCoding_)", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is SEPERATOR", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is NUMBER (000)", TokenLikedlist.get(2).toString());
}

@Test
public void Carriagereturn() throws Exception {
	
    String file = "Green \n \r is my favorite color";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(6, TokenLikedlist.size());

    assertEquals("Token Type is WORD (Green)", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is SEPERATOR", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is WORD (is)", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is WORD (my)", TokenLikedlist.get(3).toString());
    assertEquals("Token Type is WORD (favorite)", TokenLikedlist.get(4).toString());
    assertEquals("Token Type is WORD (color)", TokenLikedlist.get(5).toString()); 
}

@Test
public void Comments() throws Exception {
	
    String file = "#When nothing is going right, go left \n The only power you have is the word no";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(10, TokenLikedlist.size());

    assertEquals("Token Type is SEPERATOR", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is WORD (The)", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is WORD (only)", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is WORD (power)", TokenLikedlist.get(3).toString());
    assertEquals("Token Type is WORD (you)", TokenLikedlist.get(4).toString());
    assertEquals("Token Type is WORD (have)", TokenLikedlist.get(5).toString());
    assertEquals("Token Type is WORD (is)", TokenLikedlist.get(6).toString());
    assertEquals("Token Type is WORD (the)", TokenLikedlist.get(7).toString());
    assertEquals("Token Type is WORD (word)", TokenLikedlist.get(8).toString());
    assertEquals("Token Type is WORD (no)", TokenLikedlist.get(9).toString());
}

@Test
public void StringLiteral() throws Exception {
	
    String file = "String quote “She said \\”hello there\\” and then she left.”";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(3, TokenLikedlist.size());

    assertEquals("Token Type is WORD (String)", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is WORD (quote)", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is StringLiterals (She said ”hello there” and then she left.)"
    											, TokenLikedlist.get(2).toString());
}

@Test
public void HandlesPattern() throws Exception {
	
    String file = " `Exercise??, I thought you said extra fries` \n lol";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(3, TokenLikedlist.size());
    
    
    assertEquals("Token Type is Pattern (Exercise??, I thought you said extra fries)", TokenLikedlist.get(0).toString());
   assertEquals("Token Type is SEPERATOR", TokenLikedlist.get(1).toString());
   assertEquals("Token Type is WORD (lol)", TokenLikedlist.get(2).toString());
    
}

@Test
public void TWOCharacterSymboLL() throws Exception {
	
	String file = "&& Haneen ";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(2, TokenLikedlist.size());
    
    assertEquals("Token Type is LOGICALAND", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is WORD (Haneen)", TokenLikedlist.get(1).toString());
    
}

@Test
public void ONECharacterSymboLL() throws Exception {
	String file = "$  Haneen 21 ";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    assertEquals(3, TokenLikedlist.size());
    
    assertEquals("Token Type is DOLLAR", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is WORD (Haneen)", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is NUMBER (21)", TokenLikedlist.get(2).toString());
}


@Test
public void KnownWords() throws Exception {
	String file = "while if do for break continue else return BEGIN END print ";
	
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
   assertEquals(11, TokenLikedlist.size());
   
    assertEquals("Token Type is WHILE", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is IF", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is DO", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is FOR", TokenLikedlist.get(3).toString());
    assertEquals("Token Type is BREAK", TokenLikedlist.get(4).toString());
    assertEquals("Token Type is CONTINUE", TokenLikedlist.get(5).toString());
    assertEquals("Token Type is ELSE", TokenLikedlist.get(6).toString());
    assertEquals("Token Type is RETURN", TokenLikedlist.get(7).toString());
    assertEquals("Token Type is BEGIN", TokenLikedlist.get(8).toString());
    assertEquals("Token Type is END", TokenLikedlist.get(9).toString());
   assertEquals("Token Type is PRINT", TokenLikedlist.get(10).toString());
}

@Test
public void KNOWNWORDSS() throws Exception {
	String file = " in delete getline exit next function printf nextfile";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
  assertEquals(8, TokenLikedlist.size());
    
    assertEquals("Token Type is IN", TokenLikedlist.get(0).toString());
    assertEquals("Token Type is DELETE", TokenLikedlist.get(1).toString());
    assertEquals("Token Type is GETLINE", TokenLikedlist.get(2).toString());
    assertEquals("Token Type is EXIT", TokenLikedlist.get(3).toString());
    assertEquals("Token Type is NEXT", TokenLikedlist.get(4).toString());
    assertEquals("Token Type is FUNCTION", TokenLikedlist.get(5).toString());
    assertEquals("Token Type is PRINTF", TokenLikedlist.get(6).toString());
    assertEquals("Token Type is NEXTFILE", TokenLikedlist.get(7).toString());
    
}

@Test
public void emptyString() throws Exception {
	
	String file = "";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
  assertEquals(0, TokenLikedlist.size());
}

//@Test don't
public void chard() throws Exception {
	
	String file = "@";
    Lexer InstanceOfLexer = new Lexer(file);
    LinkedList<Token> TokenLikedlist = InstanceOfLexer.Lex();
    
  //equals("Not recognized characters");
  //, exception.getMessage());
}

@Test
public void AcceptSeperatorsReturnTrue()throws Exception{
	String test = "\n \n ; " ;
	Lexer testLex = new Lexer(test);
	LinkedList<Token> mytokens = testLex.Lex();
	
	Parser testParser = new Parser( mytokens);
	
	assertTrue(testParser.AcceptSeperators());
	
}

@Test
public void AcceptSeperatorsTurnFalse()throws Exception{
	String test = "Haneen is stressing " ;
	Lexer testLex = new Lexer(test);
	LinkedList<Token> mytokens = testLex.Lex();
	
	Parser testParser = new Parser( mytokens);
	assertFalse(testParser.AcceptSeperators());
	
}

@Test
public void MatchAndRemove()throws Exception{
	String test = "Token Type is COMMA" ;
	Lexer testLex = new Lexer(test);
	LinkedList<Token> mytokens = testLex.Lex();
	
	TokenManager testParser = new TokenManager( mytokens);

	assertEquals(Optional.empty(), testParser.MatchAndRemove(Token.values.COMMA));
}



@Test 
public void ParserAction() throws Exception {
    Lexer InstanceOfLexer = new Lexer("End {hhh}");
    
    LinkedList<Token> tokenList = InstanceOfLexer.Lex();
    
    Parser Parserclass = new Parser(tokenList);
    
    ProgramNode Test = Parserclass.Parse();
    
    //assertTrue(Parserclass.ParseAction(Test));

}

@Test
public void ParseFunctionReturnFalse() throws Exception {
    Lexer InstanceOfLexer = new Lexer(" name (name) ");
    
    LinkedList<Token> tokens = InstanceOfLexer.Lex();
    
    Parser Parserclass = new Parser(tokens);
    
    ProgramNode n = new ProgramNode();
    
    assertFalse(Parserclass.ParseFunction(n));
}

@Test
public void ParseFunctionReturnTrue() throws Exception {
    Lexer InstanceOfLexer = new Lexer("function  name (paramter1, paramter2) ");
    
    LinkedList<Token> tokens = InstanceOfLexer.Lex();
    
    Parser Parserclass = new Parser(tokens);
    
    ProgramNode n = new ProgramNode();
    
    assertTrue(Parserclass.ParseFunction(n));
}


@Test
public void ParseFunctionReturnTruee() throws Exception {
    Lexer InstanceOfLexer = new Lexer("function  name (paramter1 ,paramter2 paramter3) ");
    
    LinkedList<Token> tokens = InstanceOfLexer.Lex();
    
    Parser Parserclass = new Parser(tokens);
    
    ProgramNode n = new ProgramNode();
    
    Exception exception = assertThrows(Exception.class, () -> Parserclass.ParseFunction(n));
    
  assertEquals("There's no comma after the first Parameter", exception.getMessage());
}

//@Test don't
public void ParseLValue() throws Exception {
  Lexer forTest = new Lexer("$ WORD[]");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
  Optional<Node> Test2 = parser.ParseOperation();
  //assertEquals(1, Test2.get().toString());
  System.out.println(Test2.get().toString());
  
//  ProgramNode  program = parser.Parse();
//  System.out.println(program.GetAddBlocks().toString());
}

@Test
public void ParseLValueWordAndLeftBracket() throws Exception {
  Lexer forTest = new Lexer(" e[++b] ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
  Optional<Node>  program = parser.ParseLValue();
  assertEquals("e b PREINC", program.get().toString());
  
 //System.out.println(program.get().toString());
//  ProgramNode Test3 = parser.Parse();
//  assertEquals(0, Test3.GetAddBlocks().size());
}


@Test
public void ParseBottomLevelNumber() throws Exception {
  Lexer forTest = new Lexer("$3");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
  Optional<Node>  program = parser.ParseBottomLevel();
  
  assertEquals("3 DOLLAR", program.get().toString());
  
//System.out.println(program.get().toString());
//  ProgramNode programm = parser.Parse();
//  assertEquals(1, programm.GetAddBlocks().size());
}



@Test
public void ParseBottomLevelDollarPlusLetter() throws Exception {
  Lexer forTest = new Lexer("++$b");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
  Optional<Node>  program = parser.ParseBottomLevel();
  assertEquals("b DOLLAR PREINC", program.get().toString());
 // System.out.println(program.get().toString());
}

@Test 
public void ParseBottomLevelMinus() throws Exception {
  Lexer forTest = new Lexer("-5");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  
  Optional<Node>  Test5 = parser.ParseBottomLevel();
  assertEquals("5 UNARYNEG", Test5.get().toString());
  
 // System.out.println(Test5.get().toString());
  
}

@Test
public void ParseBottomLevelParnth() throws Exception {
  Lexer forTest = new Lexer("(++d)");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
 // ProgramNode program = parser.Parse();
  //assertEquals(1, program.GetAddBlocks().size());
  
 Optional<Node> Test5 = parser.ParseBottomLevel();
 assertEquals("d PREINC", Test5.get().toString());

// System.out.println(p1.get().toString());
}

@Test
public void ParseBottomLevelINCREMENT() throws Exception {
  Lexer forTest = new Lexer("--i");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
  Optional<Node>  p2 = parser.ParseBottomLevel();
 
  assertEquals("i PREDEC", p2.get().toString());
  
  
 // System.out.println(p2.get().toString());

}

@Test
public void ParseBottomLevelParttern() throws Exception {
  Lexer forTest = new Lexer("`[abc]`");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  
  
  Optional<Node>  program = parser.ParseBottomLevel();
  assertEquals("[abc]", program.get().toString());
  //System.out.println(program.get().toString());
}


@Test
public void Stringconcatenation() throws Exception {
  Lexer forTest = new Lexer("(Haneen Qasem) ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
  Optional<Node>  program = parser.ParseOperation();
 assertEquals("Haneen CONCATENATION Qasem", program.get().toString());
  
  
 System.out.println(program.get().toString());
}

@Test
public void OrMethod() throws Exception {
  Lexer forTest = new Lexer("a || b ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 

 Optional<Node> program = parser.ParseOperation();
 assertEquals("a OR b", program.get().toString());
 
 
// System.out.println(program.get().toString());
}


@Test
public void AddMethod() throws Exception {
  Lexer forTest = new Lexer("a && c ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a AND c", program.get().toString());
 
//System.out.println(program.get().toString());
}



@Test
public void MultiMathOperation() throws Exception {
  Lexer forTest = new Lexer("a * h ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a MULTIPLY h", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void PostDecrementAndIncrement() throws Exception {
  Lexer forTest = new Lexer("k++");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("k POSTINC", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void AdditionSubtraction() throws Exception {
  Lexer forTest = new Lexer("a + q ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a ADD q", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void NotEqua() throws Exception {
  Lexer forTest = new Lexer("a != w ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a NE w", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void EqualSign() throws Exception {
  Lexer forTest = new Lexer("a = q ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a = q", program.get().toString());
 
//System.out.println(program.get().toString());
}



@Test
public void SubtractionAssignment() throws Exception {
  Lexer forTest = new Lexer("a -= o ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a = a SUBTRACT o", program.get().toString());
 
 //System.out.println(program.get().toString());
}

@Test
public void Ternary() throws Exception {
  Lexer forTest = new Lexer("a ? c : v ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 
System.out.println(program.get().toString());
}

@Test
public void Match() throws Exception {
  Lexer forTest = new Lexer("a ~ b ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a MATCH b", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void NotMatch() throws Exception {
  Lexer forTest = new Lexer("a !~ k ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 assertEquals("a NOTMATCH k", program.get().toString());
 
//System.out.println(program.get().toString());
}

@Test
public void Less() throws Exception {
  Lexer forTest = new Lexer("a < k ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 
//System.out.println(program.get().toString());
assertEquals("a LT k", program.get().toString());
}

@Test
public void Expo() throws Exception {
  Lexer forTest = new Lexer("a ^ k ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
 Optional<Node> program = parser.ParseOperation();
 
//System.out.println(program.get().toString());
assertEquals("a EXPONENT k", program.get().toString());
}

@Test 
public void If() throws Exception {
  Lexer forTest = new Lexer("if (something){a--} ");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
  StatementNode program = parser.ParseStatement();
 // ProgramNode Something = parser.Parse();
  System.out.println(program.toString());
}

@Test
public void While() throws Exception {
  Lexer forTest = new Lexer("while (i > something){n--}");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);
 
StatementNode Something = parser.ParseStatement();

//System.out.println(Something.toString());
assertEquals("i GT something [n POSTDEC]", Something.toString());
}

@Test
public void DoWhile() throws Exception {
  Lexer forTest = new Lexer("do a+b while(a == n)");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
  assertEquals("do [a ADD b] while (a EQ n )", Something.toString());
  
System.out.println(Something.toString());
}

@Test
public void Continue() throws Exception {
  Lexer forTest = new Lexer("continue");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
 assertEquals("Continue", Something.toString());
  
//System.out.println(Something.toString());
}

@Test
public void Break() throws Exception {
  Lexer forTest = new Lexer("break");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
  assertEquals("Break", Something.toString());
  
//System.out.println(Something.toString());
}

@Test
public void Delete() throws Exception {
  Lexer forTest = new Lexer("delete ArrayName[0]");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
  //assertEquals("ArrayName 0", Something.toString());
  
System.out.println(Something.toString());
}

@Test
public void returnNode() throws Exception {
  Lexer forTest = new Lexer("return ArrayName");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
  //assertEquals("ArrayName", Something.toString());
  
System.out.println(Something.toString());
}


//@Test this one
public void ForNode() throws Exception {
  Lexer forTest = new Lexer("for (a in name) {a++}");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
 //assertEquals("ArrayName 0", Something.toString());
  
System.out.println(Something.toString());
}


//@Test 
public void FunctionNode() throws Exception {
  Lexer forTest = new Lexer("name (10)");
  LinkedList<Token> tokens = forTest.Lex();
  Parser parser = new Parser(tokens);

  StatementNode Something = parser.ParseStatement();
 // assertEquals("10", Something.toString());
  
System.out.println(Something.toString());
}

@Test 
public void FunctionCallGetLine() throws Exception {
	  Lexer forTest = new Lexer("getline 11");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}

@Test 
public void FunctionCallPrint() throws Exception {
	  Lexer forTest = new Lexer("print 12");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}

@Test 
public void FunctionCallPrintf() throws Exception {
	  Lexer forTest = new Lexer("printf 12");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}

@Test 
public void FunctionCallExit() throws Exception {
	  Lexer forTest = new Lexer("exit 13");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}


@Test 
public void FunctionCallNextFile() throws Exception {
	  Lexer forTest = new Lexer("nextfile 14");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}



@Test 
public void FunctionCallNext() throws Exception {
	  Lexer forTest = new Lexer("next 15");
	  LinkedList<Token> tokens = forTest.Lex();
	  Parser parser = new Parser(tokens);

	  StatementNode Something = parser.ParseStatement();
	 // assertEquals("10", Something.toString());
	  
	System.out.println(Something.toString());
	}




@Test
public void match() throws Exception {

   ProgramNode print = new ProgramNode();
   Interpreter print2 = new Interpreter(print,null);

   HashMap<String,InterpreterDataType> testHash= new HashMap<String,InterpreterDataType>();

   testHash.put("regexp", new InterpreterDataType("is"));
   testHash.put("input", new InterpreterDataType("this"));



   BuiltInFunctionDefinitionNode bfd = (BuiltInFunctionDefinitionNode) print2.getSourceFunctionCall().get("match");
   String result = bfd.getExecute().apply(testHash);
   assertEquals(result,"is" );
}










}