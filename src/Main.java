import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
	public static void main (String [] args) throws Exception 
	{
		
		//File From Commend Line
		
		
		//String FileFromCommendLine = args[0];
		
		
		try {
			//Read the file using readAllBytes ;) 
			Path filePath = Paths.get("AWK.FILE");
			String File = new String(Files.readAllBytes(filePath));
			
			//Create an instance of Lexer and a linkedlist of tokens then pass instance of lexer 
			//to it
			Lexer InstanceOfLexer = new Lexer(File);
			LinkedList<Token> TokenLikedlist= InstanceOfLexer.Lex();
			
			//print out the resultant tokens
			for(Token i : TokenLikedlist) {
				System.out.println(i);
			}	
			
		
	      Parser parserclass = new Parser(TokenLikedlist);
	      ProgramNode programNodeClass = parserclass.Parse();
	      System.out.println(programNodeClass);
	
	       HashMap<String, InterpreterDataType> LocalVariables = null ;
	      Interpreter interpreter = new Interpreter(programNodeClass,File );
	      
         interpreter.GetIDT(programNodeClass, LocalVariables);

          System.out.println(programNodeClass);
			
		} catch (IOException e) {
			System.out.println("Can't read from a file");
		} catch (Exception e) {

			System.out.println("");
		}
		
	}
	
}

//I used this website to teste vaild awk:
// https://www.thegeekstuff.com/2010/02/awk-conditional-statements/


	
//	String test = "function factorial(f) " ;
//	Lexer testLex = new Lexer(test);
//	LinkedList<Token> mytokens = testLex.Lex();
//	TokenManager testMatchANDRemove = new TokenManager (mytokens);
//	System.out.println(testMatchANDRemove.MatchAndRemove(Token.values.WORD));
//	System.out.println(testMatchANDRemove.MatchAndRemove(Token.values.WORD));
	
//	Parser testParser = new Parser( mytokens);
//	ProgramNode n = new ProgramNode();
//	System.out.println(testParser.ParseFunction(n));
	//System.out.println(testParser.AcceptSeperators());
//}
//}