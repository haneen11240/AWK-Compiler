
import java.util.LinkedList;




import java.util.Optional;



public class Parser {

	private TokenManager tokenManagerMember;
	
	public Parser(LinkedList<Token> TokenLikedlist ) {
		this.tokenManagerMember = new TokenManager(TokenLikedlist);
	}
	
	//AcceptSeperators should peek to check if the first token is a SEPERATOR, then call the method
	//MatchAndRemove and get the token type.... loop until  it's no longer a SEPERATOR
	public boolean AcceptSeperators() {
		boolean atleastOneSeperator = false;
		
		Optional<Token> StorePeek = tokenManagerMember.Peek(0);
	
			while (StorePeek.isPresent() && StorePeek.get().getTokenType().equals(Token.values.SEPERATOR) ) {
			tokenManagerMember.MatchAndRemove(StorePeek.get().getTokenType());
			StorePeek = tokenManagerMember.Peek(0);
			  atleastOneSeperator = true;
		}
	
		return atleastOneSeperator;
	}
	
	//if it's a function look for a word Token. That's your function name.
	//look for a left parenthesis token. After that, you can find 
	//A single word Token (one param)
	//Multiple word Tokens (multiple param)
	// A right parenthesis 
	public boolean ParseFunction(ProgramNode NodeProgram) throws Exception {
		
		boolean parseFunction = false;
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		
		if((!(current.get().getTokenType().equals(Token.values.FUNCTION)))) {
			return parseFunction;
		}
		if(current.get().getTokenType().equals(Token.values.FUNCTION)) {
			
			 tokenManagerMember.MatchAndRemove(Token.values.FUNCTION);
			 current = tokenManagerMember.Peek(0);
			 
			if((!(current.get().getTokenType().equals(Token.values.WORD)))){
			throw new Exception("A function name is not present");	
			}
			
			String AFunctionName = current.get().getTheValue();
			 tokenManagerMember.MatchAndRemove(Token.values.WORD);
			 current = tokenManagerMember.Peek(0);
			 
			if((!(current.get().getTokenType().equals(Token.values.LEFTPARENTHESIS)))) {
				throw new Exception("A function LeftParenthesis is not present");
			}
			tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS);
			LinkedList<String> ListOfParameters = new LinkedList<>();
			current = tokenManagerMember.Peek(0);
			
			
			while((!(current.get().getTokenType().equals(Token.values.RIGHTPARENTHESIS)))) {
			
			if(current.get().getTokenType().equals(Token.values.WORD)) {
				ListOfParameters.add(current.get().getTheValue());
				tokenManagerMember.MatchAndRemove(Token.values.WORD);
			}
			current = tokenManagerMember.Peek(0);
			if (current.get().getTokenType().equals(Token.values.SEPERATOR)) {
				AcceptSeperators();
			}
			
			current = tokenManagerMember.Peek(0);
			if (current.get().getTokenType().equals(Token.values.COMMA)) {
				tokenManagerMember.MatchAndRemove(Token.values.COMMA);
				
				current = tokenManagerMember.Peek(0);
				if (current.get().getTokenType().equals(Token.values.SEPERATOR)) {
					AcceptSeperators();
				}
			}
			
			current = tokenManagerMember.Peek(0);
			if((!(current.get().getTokenType().equals(Token.values.COMMA)))){
				if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.WORD) && 
					tokenManagerMember.Peek(1).get().getTokenType().equals(Token.values.WORD)) {
					throw new Exception("There's no comma after the first Parameter");
				}
			}
		 tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS);
		
		LinkedList<StatementNode> statements =ParseBlock().GetListOfStatementNode();
		
		FunctionDefinitionNode functionDefinitionNode = new FunctionDefinitionNode(AFunctionName, ListOfParameters, statements);
		NodeProgram.FunctionNode().add(functionDefinitionNode);
		return  parseFunction = true;
	}
}
		return parseFunction;
		}
	
	
	//it peeks at the first token type, if its equals token type BEGIN, then it calls the method MatchAndRemove
	//then it calls the method ParseBlock();
	//it peeks at the first token type, if its equals token type End, then it calls the method MatchAndRemove
	//then it calls the method ParseBlock();
	public boolean ParseAction(ProgramNode NodeProgram) throws Exception {
		boolean parseAction = false;
		Optional<Token> current = tokenManagerMember.Peek(0);
		if(current.get().getTokenType().equals(Token.values.BEGIN)){
			tokenManagerMember.MatchAndRemove(Token.values.BEGIN);
			NodeProgram.AddStartBlocks(ParseBlock());
			while(AcceptSeperators()){
				
				tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR);
            }
			parseAction = true;
		}
		
		else if(current.get().getTokenType().equals(Token.values.END)){
			tokenManagerMember.MatchAndRemove(Token.values.END);
			NodeProgram.AddEndBlocks(ParseBlock());
			while(AcceptSeperators()){
				
				tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR);
            }
			parseAction = true;
			
		}
		
		else {
			
			Optional<Node> Block = ParseOperation();
			BlockNode parseBlock = ParseBlock();
	
			while(AcceptSeperators()){
				tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR);
            }

			NodeProgram.AddBlocks(parseBlock);
			parseAction = true;
    
		}
		
		return parseAction;
	}
	//a Parse method that returns a ProgramNode. While there are more tokens in the TokenManager
	//it should loop calling two other methods – ParseFunction() and ParseAction(). If neither one is true, it 
	//should throw an exception
	public ProgramNode Parse() throws Exception {
		
		ProgramNode NodeProgram = new ProgramNode();
		
		while (tokenManagerMember.MoreTokens()) {
			
			if ((!(ParseFunction(NodeProgram))) && (!(ParseAction(NodeProgram))) ) {
				throw new Exception("ParseFunction() and ParseAction() return false");
			}
			
		}
		return NodeProgram;
	}
	
	public Optional<Node> ParseOperation() throws Exception{
		
		return Optional.of(Assignment().get());
	}
	
	public BlockNode ParseBlock() throws Exception{
		
		//Optional<Node> StoreparseOperation =  ParseOperation();
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		LinkedList <StatementNode> ListOfStatementNode = new LinkedList <StatementNode>();
		
		if (current.isPresent()) {
			
			AcceptSeperators();
			if (tokenManagerMember.MatchAndRemove(Token.values.LEFTBRACE).isPresent()) {
				
				AcceptSeperators();
				
				while((!(tokenManagerMember.MatchAndRemove(Token.values.RIGHTBRACE).isPresent()))) {
					AcceptSeperators();
					StatementNode StoreParseStatemen = ParseStatement();
					ListOfStatementNode.add(StoreParseStatemen);
					
				}	
				
			}
			//if no left brace u call it once ..... no while
			if(!(current.get().getTokenType().equals(Token.values.LEFTBRACE))) {
				AcceptSeperators();
				StatementNode StoreParseStatemen = ParseStatement();
				
				ListOfStatementNode.add(StoreParseStatemen);
				
			}
		}
	
		BlockNode blockNode = new BlockNode(ListOfStatementNode, Optional.empty());
		return  blockNode;
		
	}
	
	//Check for a token type, if it finds it, then calls a method for each one
	public StatementNode ParseStatement() throws Exception{
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		Optional<Node> Casting = null;
		
		if (current.isPresent()) {
			if (tokenManagerMember.MatchAndRemove(Token.values.WHILE).isPresent()) {
				return ParseWhile();
			}
			if (tokenManagerMember.MatchAndRemove(Token.values.IF).isPresent()) {
				return ParseIf();
			}
			
			if (tokenManagerMember.MatchAndRemove(Token.values.FOR).isPresent()) {
				return ParseFor();
				
			}
			if (tokenManagerMember.MatchAndRemove(Token.values.BREAK).isPresent()) {
				return ParseBreak();
			}
			if (tokenManagerMember.MatchAndRemove(Token.values.CONTINUE).isPresent()) {
				return ContinueNode();
				
			}
			if (tokenManagerMember.MatchAndRemove(Token.values.RETURN).isPresent()) {
				return ParseReturn();
			}	
			if (tokenManagerMember.MatchAndRemove(Token.values.DELETE).isPresent()) {
				return ParseDelete();
			}
			
			if (tokenManagerMember.MatchAndRemove(Token.values.DO).isPresent()) {
				return ParseDoWhile();
			}
			else {
			 
				Optional<Node> Parseoperation = ParseOperation();
				
				if(Parseoperation.get()instanceof AssignmentNode) {
					return (StatementNode)Parseoperation.get();
					
				}
				else if (Parseoperation.get() instanceof FunctionCallNode) {
					
					return (StatementNode)Parseoperation.get();
				}
				
				else if (Parseoperation.get()instanceof OperationNode) {
					return (StatementNode)Parseoperation.get();
					
				}
				
			}
			
		}
		
		return null;	
	}
	
	public StatementNode ContinueNode() {
		return new ContinueNode();
	}
	
	public StatementNode ParseBreak(){
		return new BreakNode();
	}
	
	//check for an if token type, then check for a left parenthesis and call  ParseOperation(), then checks for a right parenthesis.
	//If there's an else call ParseIf() recursively  
	public StatementNode ParseIf() throws Exception {
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		
		if (current.isPresent()) {
			
			if((!(current.get().getTokenType().equals(Token.values.LEFTPARENTHESIS)))){
				throw new Exception("No left parenthsis in the if statment");	
			}
			
			if(tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS).isPresent()){
				
				Optional<Node> ParseOperation = ParseOperation();
				
				if((!(ParseOperation.isPresent()))) {
					throw new Exception("If statment method, has no condition");
				}
				
				
				 current = tokenManagerMember.Peek(0);
				if((!(current.get().getTokenType().equals(Token.values.RIGHTPARENTHESIS)))){
					throw new Exception("No right parenthsis in the if statment");	
				}
				
				
				if(tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS).isPresent()) {
					BlockNode parseblock = ParseBlock();
				
				
				
				if(tokenManagerMember.MatchAndRemove(Token.values.ELSE).isPresent()) {
					
					StatementNode StoreIfNode = ParseIf();
					
					if(tokenManagerMember.MatchAndRemove(Token.values.IF).isPresent()) {
						
						return new IfNode(ParseOperation, parseblock,StoreIfNode );
					}
					
				}
				else{
					return new IfNode(ParseOperation, parseblock);
				}
		
				}
			}
			
		}
		
		return null;
	}
	
	//in this method, check for a "for" token type then a left parenthesis, get ParseOperation() then check for right parenthesis if it finds it
	//return ForNode
	public StatementNode ParseFor() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		if (current.isPresent()) {
			
				if(tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS).isPresent()) {
					
					Optional<Node> ParseOperation1 = ParseOperation();
					
					if ((!(ParseOperation1.isPresent()))) {
					throw new Exception("In parse For, there's no condtion");
				}
					if(tokenManagerMember.MatchAndRemove(Token.values.IN).isPresent()) {
					
						Optional<Node> ParseOperation2 = ParseOperation();
						
						if ((!(ParseOperation2.isPresent()))) {
							throw new Exception("In parse For, there's no condtion");
						}
					

					if(tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS).isPresent()) {
						BlockNode parseblock = ParseBlock();
						return new ForNode(ParseOperation1, OperationNode.PossibleOperations.IN, ParseOperation2,parseblock);
						//return new ForNode(ParseOperation, parseblock);	
				}
					}	
				}
						//BlockNode parseblock = ParseBlock();			
	}
		
		return null;
		
	}
	
	//In this method I check for a delete token type, if it finds one it delete a ReturnNode
	public StatementNode ParseDelete() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		
	
		if(current.isPresent()) {
			
			Optional<Node> ParseLvalue = ParseLValue();
			
			if((!(ParseLvalue.isPresent()))) {
				
				throw new Exception("No parse value in delete");
			}
			return new DeleteNode(ParseLvalue);
	
		}
		return null;
	}
	
	//Check for a while token type, then a left parenthesis, call ParseOperation() then check for a right parenthesis
	//call ParseBlock() and then return new WhileNode
	public StatementNode ParseWhile() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		Optional<Node> parseoperation = Optional.empty();
		BlockNode parseblock = null;
		
		if(current.isPresent()) {
			
			current = tokenManagerMember.Peek(0);
			if((!(current.get().getTokenType().equals(Token.values.LEFTPARENTHESIS)))) {
				
				throw new Exception("No Left parenthesis in parse while");
			}
		
			if(tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS).isPresent()) {
				
				parseoperation = ParseOperation();
			
			if ((!(parseoperation.isPresent()))) {
				throw new Exception("In parse While, there's no condtion");
			}
			
			 current = tokenManagerMember.Peek(0);
			if((!(current.get().getTokenType().equals(Token.values.RIGHTPARENTHESIS))))  {
				
				throw new Exception("In parse While, there's no right parenthesis");
			}
			
			if((tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS).isPresent())) {
				
				 parseblock = ParseBlock();
				
	        }
			return new WhileNode(parseoperation, parseblock);
		   }
		}
	
		return null;
	}
	
	
	// In this method, I check for a do token type then call ParseBlock for the curly(Optional), then check for a left parenthesis 
	//if it's there, then call ParseOperation() for condition, check for right parenthesis then return DoWhileNode
	public StatementNode ParseDoWhile() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		
		if(current.isPresent()) {
			
			BlockNode parseblock = ParseBlock();
			
			
			 current = tokenManagerMember.Peek(0);
				if((!(current.get().getTokenType().equals(Token.values.WHILE)))) {
					
					throw new Exception("No while token type in parse do while");
				}
			if(tokenManagerMember.MatchAndRemove(Token.values.WHILE).isPresent()) {
				
				 current = tokenManagerMember.Peek(0);
				if((!(current.get().getTokenType().equals(Token.values.LEFTPARENTHESIS)))) {
					
					throw new Exception("No Left parenthesis in parse do while");
				}
				if(tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS).isPresent()) {
				
					Optional<Node> ParseOperation = ParseOperation();
					
					
					if ((!(ParseOperation.isPresent()))) {
						throw new Exception("In parse do While, there's no condtion");
					}
					
					current = tokenManagerMember.Peek(0);
					if((!(current.get().getTokenType().equals(Token.values.RIGHTPARENTHESIS)))) {
						
						throw new Exception("In parse do While, there's no right parenthesis");
					}
					if(tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS).isPresent()) {
						
						return new DoWhileNode(parseblock, ParseOperation);
					}
					
				}
			}
			
	}

		return null;
	}
	
	
	//In this method I check for a return token type, if it finds one it returns a ReturnNode
	public StatementNode ParseReturn() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		
		
		if(current.isPresent()) {
			
			Optional<Node> ParseOperation = ParseOperation();
			
			if((!(ParseOperation.isPresent()))) {
				
				throw new Exception("No condtion in return");
			}
			return new ReturnNode(ParseOperation);
		}
		
		return null;
		
		
	}
	
	
	public Optional<Node> ParseCallFunction() throws Exception {
		
		Optional<Token> current = tokenManagerMember.Peek(0);
		LinkedList <Node> ListofNode = new LinkedList <Node>();
		
		if(current.isPresent()) {
			
			if (tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.WORD) && 
					tokenManagerMember.Peek(1).isPresent()&&tokenManagerMember.Peek(1).get().getTokenType().equals(Token.values.LEFTPARENTHESIS)) {
				
				String Name = tokenManagerMember.Peek(0).get().getTheValue();
				tokenManagerMember.MatchAndRemove(Token.values.WORD);
				tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS);
				
			while((!(tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS).isPresent()))) {
				
				if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.COMMA)) {
					tokenManagerMember.MatchAndRemove(Token.values.COMMA);
				}
				
				if(tokenManagerMember.MatchAndRemove(Token.values.WORD).isPresent()) {
					
				Name = tokenManagerMember.Peek(0).get().getTheValue();
				Optional<Node> ParseOperationname = ParseOperation();
				
				ListofNode.add(ParseOperationname.get());
				return Optional.of(new FunctionCallNode(Name, ListofNode));
				}	
			}
			}
			
			if(tokenManagerMember.MatchAndRemove(Token.values.GETLINE).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.GETLINE.toString(), ListofNode));
				}	
			}
			
			
			if(tokenManagerMember.MatchAndRemove(Token.values.PRINT).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.PRINT.toString(), ListofNode));
				}	
			}
			
			
			
			if(tokenManagerMember.MatchAndRemove(Token.values.PRINTF).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.PRINTF.toString(), ListofNode));
				}	
			}
			
			
			if(tokenManagerMember.MatchAndRemove(Token.values.EXIT).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.EXIT.toString(), ListofNode));
				}	
			}
			
			
			
			if(tokenManagerMember.MatchAndRemove(Token.values.NEXTFILE).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.NEXTFILE.toString(), ListofNode));
				}	
			}
			
			
			
			if(tokenManagerMember.MatchAndRemove(Token.values.NEXT).isPresent()) {
				if(!(tokenManagerMember.MatchAndRemove(Token.values.SEPERATOR).isPresent())) {
			do {
				Optional<Node> ParseOperation1 = ParseOperation();
				if(ParseOperation1.isPresent()) {
				ListofNode.add(ParseOperation1.get());
				}

				
				}
			while((tokenManagerMember.MatchAndRemove(Token.values.COMMA).isPresent()));
				return Optional.of(new FunctionCallNode(Token.values.NEXT.toString(), ListofNode));
				}	
			}
			
			
			else {
				return ParseLValue();
				
		
			}
		}
		
		return null;
	}
	
	//peek to check for a StringLiterals then match and remove then make
	//ConstantNode with the name
	//peek if it's a number match and remove then make a ConstantNode
	//peek if it's a Pattern match and remove then make a ConstantNode
	//peek if it a leftbrace and a rightbrace match and remove then then return ParseOperation
	//peek if its a EXCLAMATIONMARK match and remove then return ParseOperation and EXCLAMATIONMARK
	//peek if it's a MINUS match and remove then then return ParseOperation and UNARYNEG
	// peek if its a PLUS match and remove then return ParseOperation and UNARYPOS
	//peek if it's a INCREMENT match and remove then return ParseOperation and PREINC
	// peek if it's a DECREMENT match and remove then return ParseOperation and PREDEC
	public Optional<Node> ParseBottomLevel() throws Exception{
		
		Optional<Token> currentToken = tokenManagerMember.Peek(0);
		
		if(currentToken.isPresent()) {
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.StringLiterals)) {
			
			String StringLiteralsValue = tokenManagerMember.Peek(0).get().getTheValue();
			tokenManagerMember.MatchAndRemove(Token.values.StringLiterals);
			
			return Optional.of(new ConstantNode(StringLiteralsValue));
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.NUMBER)) {
			
			String Number = tokenManagerMember.Peek(0).get().getTheValue();
			tokenManagerMember.MatchAndRemove(Token.values.NUMBER);
			
			return Optional.of(new ConstantNode(Number));
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.Pattern)) {
			
			String Pattern = tokenManagerMember.Peek(0).get().getTheValue();
			tokenManagerMember.MatchAndRemove(Token.values.Pattern);
			
			return Optional.of(new Pattern(Pattern));
		}
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LEFTPARENTHESIS)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.LEFTPARENTHESIS);
			Optional<Node> Parseoperation = ParseOperation();
			
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.RIGHTPARENTHESIS)) {
				
				tokenManagerMember.MatchAndRemove(Token.values.RIGHTPARENTHESIS);
				return Optional.of(Parseoperation.get());
			}
			else {
				throw new Exception("No Right PARENTHESIS");
				
			}
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.EXCLAMATIONMARK)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.EXCLAMATIONMARK);
			Optional<Node> Parseoperation = ParseOperation();
			
			return Optional.of(new OperationNode(Parseoperation.get(), OperationNode.PossibleOperations.NOT));
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.MINUS)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.MINUS);
			Optional<Node> Parseoperation = ParseOperation();
			
			return Optional.of(new OperationNode(Parseoperation.get(), OperationNode.PossibleOperations.UNARYNEG));	
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.PLUS)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.PLUS);
			Optional<Node> Parseoperation = ParseOperation();
		
			return Optional.of(new OperationNode(Parseoperation.get(), OperationNode.PossibleOperations.UNARYPOS));			
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.INCREMENT)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.INCREMENT);
			Optional<Node> Parseoperation = ParseOperation();
		
			return Optional.of(new OperationNode(Parseoperation.get(), OperationNode.PossibleOperations.PREINC));			
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.DECREMENT)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.DECREMENT);
			Optional<Node> Parseoperation = ParseOperation();
		
			return Optional.of(new OperationNode(Parseoperation.get(), OperationNode.PossibleOperations.PREDEC));			
		}

		else {
			Optional<Node> Parseoperation = ParseCallFunction();
			return Parseoperation;
		}
		}
		return Optional.empty();
	}
	
	//This peeks at the first thing in linked list, if it's equal dollar then matchAndRemove
	//then returns OperationNode instance and pass left node and DOLLAR type
	// peek again then if its a word and a left bracket and right bracket 
	//then match and remove and create VariableReferenceNode with IndexExpression
	//peek again then if its a word and left bracket make VariableReferenceNode with the name
	public Optional<Node> ParseLValue() throws Exception{
		

		Optional<Token> current = tokenManagerMember.Peek(0);
		
		if(current.isPresent()) {
			
		if(current.get().getTokenType().equals(Token.values.DOLLAR)) {
			tokenManagerMember.MatchAndRemove(Token.values.DOLLAR);
			Optional<Node> left = ParseBottomLevel();	
			 
			 if(left.isPresent()) {
			 return Optional.of(new OperationNode(left.get() ,OperationNode.PossibleOperations.DOLLAR));	 
		}
			 else {
					 throw new Exception("nothing after dollar sign");
				}
		}
		
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.WORD)) {
			
			String VariableReferenceNode = tokenManagerMember.Peek(0).get().getTheValue();
			tokenManagerMember.MatchAndRemove(Token.values.WORD);
			
			if(tokenManagerMember.MoreTokens() && tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LEFTBRACKET)) {
				
				tokenManagerMember.MatchAndRemove(Token.values.LEFTBRACKET);
				Optional<Node> IndexExpression = ParseOperation();
				
				if(tokenManagerMember.MoreTokens() && tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.RIGHTBRACKET)) {
					
					tokenManagerMember.MatchAndRemove(Token.values.RIGHTBRACKET);
					
					if(IndexExpression.isPresent()) {
					return Optional.of(new VariableReferenceNode(VariableReferenceNode,IndexExpression ));
					}
					
				if(IndexExpression.isEmpty()) {
					return Optional.of(new VariableReferenceNode(VariableReferenceNode));	
				}
			}
				else {
					throw new Exception("No right bracket");
				}	
			}
			else {
				return Optional.of(new VariableReferenceNode(VariableReferenceNode));
			}
		}
		
		}
		return Optional.empty() ;
		
	}

// This method is being called in ParseOperation(), it checks for a equal sign, MINUSASSIGNMENT,
//PLUSASSIGNMENT, DIVIDEASSIGNMENT, MULTIPLYCATIONASSIGNMENT, MODULOASSIGNMENT and EXPONENT, if
//if it finds it then it returns AssignmentNode with the value, type and expression.
public Optional<Node> Assignment() throws Exception{
	Optional<Node> lvalue = Ternary();
		
			
	if(lvalue.isPresent()) {
		
		if(tokenManagerMember.MoreTokens()) {
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.EQUAL)) {
				tokenManagerMember.MatchAndRemove(Token.values.EQUAL);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(),expression.get()));
			}
			}
			
			
	
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.MINUSASSIGNMENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.MINUSASSIGNMENT);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.SUBTRACT, expression ))); 	
		}
		}
			
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.PLUSASSIGNMENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.PLUSASSIGNMENT);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.ADD,expression )));	
		}
		}
			
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.DIVIDEASSIGNMENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.DIVIDEASSIGNMENT);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.DIVIDE,expression )));	
		}
		}
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.MULTIPLYCATIONASSIGNMENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.MULTIPLYCATIONASSIGNMENT);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.MULTIPLY,expression )));	
		}
			}
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.MODULOASSIGNMENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.MODULOASSIGNMENT);
				
		
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.MODULO,expression )));
		}
		}
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.EXPONENT)) {
				tokenManagerMember.MatchAndRemove(Token.values.EXPONENT);
				
				
				Optional<Node> expression = Assignment();
				if(expression.isPresent()) {
				return Optional.of(new AssignmentNode(lvalue.get(), new OperationNode(lvalue.get(), OperationNode.PossibleOperations.EXPONENT,expression )));
		}
		}
	}
		return lvalue;
	}
		return lvalue;
}

//This method is being called in Assignment(), it checks for a QUESTIONMARK, COLON
//if it finds it then it returns TernaryNode with the value1, value2, value3
public Optional<Node> Ternary() throws Exception{
	
		
		Optional<Node> Value1 =  LogicalOR();
		
		if(tokenManagerMember.MoreTokens()) {
		if(!(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.QUESTIONMARK))) {
			return Value1;
		}
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.QUESTIONMARK)) {
			
			tokenManagerMember.MatchAndRemove(Token.values.QUESTIONMARK);
			
			Optional<Node> Value2 = LogicalOR();
		
		
			if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.COLON)) {
				
				tokenManagerMember.MatchAndRemove(Token.values.COLON);
				
				Optional<Node> Value3 = LogicalOR();
				
				return Optional.of(new TernaryNode(Value1.get(),Value2.get(), Value3.get()));
			}
			
	}
}
	return Value1;
}
	
//This is being called in Ternary() if only checks for an LOGICALOR, if it finds it then it sets
//value1 for new OperationNode with the value and the type or, it also keeps looping until there's 
//no more LogicalAnd
public Optional<Node> LogicalOR() throws Exception{
	
	Optional<Node> Value1 = LogicalAnd();
	
	do {
		if(tokenManagerMember.MoreTokens()) {
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LOGICALOR)) {
		
		tokenManagerMember.MatchAndRemove(Token.values.LOGICALOR);
		Optional<Node> Value2 = LogicalAnd();
		Value1 = Optional.of(new OperationNode(Value1.get(),OperationNode.PossibleOperations.OR , Value2));	
	}
	else{
		return Value1;
	}
		}
		return Value1;
	}while (true);
	
}

//This method being called in LogicalOR(), it only checks for LOGICALAND, if it finds it then it set
// value1 to OperationNode with the value and the type AND, it also keeps looping to check if there's
// more LOGICALAND
public Optional<Node> LogicalAnd() throws Exception{
	
Optional<Node> Value1 = Array();

do {
	if (tokenManagerMember.MoreTokens()) {
	
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LOGICALAND)) {
		
		tokenManagerMember.MatchAndRemove(Token.values.LOGICALAND);
		Optional<Node> Value2 = Array();
	
		Value1 = Optional.of(new OperationNode(Value1.get(),OperationNode.PossibleOperations.AND, Value2));
	}
	else {
		return Value1;
	}
}
	return Value1;
}
	while(true);
}

//This method is being called in LogicalAnd(), it checks for a token type called IN , then it gets the
//value2 and sets value1 to a new OperationNode with value1, In, and value2. This method will loop until
//there's no more arrays
public Optional<Node> Array() throws Exception{
	
	Optional<Node> Value1 = MatchAndNotMatch();
	do {
		if(tokenManagerMember.MoreTokens()) {
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.IN)) {
		tokenManagerMember.MatchAndRemove(Token.values.IN);
		Optional<Node> Value2 = MatchAndNotMatch();
		Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.IN,Value2));
	}
	else {
		return Value1;
	}
}
		return Value1;
	}
	while(true);
}

//This method is being called in Array(), it checks for a TILDE, gets the value, then return a new 
//OperationNode with the value, a MATCH type, and value2.
//it checks for a NotMatch, gets the value, then return a new 
//OperationNode with the value, a NOTMATCH type, and value2.
public Optional<Node> MatchAndNotMatch() throws Exception{
	
	Optional<Node> Value1 = LogicalCompare();
	
	if (tokenManagerMember.MoreTokens()) {
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.TILDE)) {
		tokenManagerMember.MatchAndRemove(Token.values.TILDE);
		Optional<Node> Value2 = LogicalCompare();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.MATCH,Value2));
	}
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.NOTMATCH)) {
		tokenManagerMember.MatchAndRemove(Token.values.NOTMATCH);
		Optional<Node> Value2 = LogicalCompare();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.NOTMATCH,Value2));
	}
	}

	return Value1;
	
	
}

//This method is being called in MatchAndNotMatch(), it checks for GREATERTHANEQUALTO,GREATERTHAN, 
//LOGICALEQUAL, NOTEQUAL, LESSTHANEQUALTO, LESSTHAN. if it finds one of them, then it returns 
//new OperationNode with value1 the type and value2
public Optional<Node> LogicalCompare() throws Exception{
	
	Optional<Node> Value1 = StringConcatenation();
	if (tokenManagerMember.MoreTokens()) {
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.GREATERTHANEQUALTO)) {
		tokenManagerMember.MatchAndRemove(Token.values.GREATERTHANEQUALTO);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.GE,Value2));
	}
	
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.GREATERTHAN)) {
		tokenManagerMember.MatchAndRemove(Token.values.GREATERTHAN);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.GT,Value2));
	}
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LOGICALEQUAL)) {
		tokenManagerMember.MatchAndRemove(Token.values.LOGICALEQUAL);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.EQ,Value2));
	}
	
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.NOTEQUAL)) {
		tokenManagerMember.MatchAndRemove(Token.values.NOTEQUAL);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.NE,Value2));
	}
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LESSTHANEQUALTO)) {
		tokenManagerMember.MatchAndRemove(Token.values.LESSTHANEQUALTO);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.LE,Value2));
	}
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.LESSTHAN)) {
		tokenManagerMember.MatchAndRemove(Token.values.LESSTHAN);
		Optional<Node> Value2 = StringConcatenation();
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.LT,Value2));
	}
	}

	return Value1;
}

//This method is being called in LogicalCompare(), it checks for a value then checks for a value 
//again, if it's present then set value1 to a new OperationNode with value1 and CONCATENATION type and 
// value 2. it will also loop until there's no more strings/value
public Optional<Node> StringConcatenation() throws Exception{
	
	Optional<Node> Value1 = AdditionSubtraction();

			do {
				if(tokenManagerMember.MoreTokens()) {
						Optional<Node> Value2 = AdditionSubtraction();
						
						if(Value2.isPresent()) {
							
							Value1 = Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.CONCATENATION,Value2));
							}
				else {
					return Value1;
				}
			}
				else {
					return Value1;
				}
		}while(true);

}

//This method is being called in StringConcatenation(), it checks for a minus, if it finds then
//then it returns a new OperationNode with the value, the minus sign and value2.
//Again, it checks for a plus, if it finds it then
//it returns a new OperationNode with the value, the plus sign and value2.

public Optional<Node> AdditionSubtraction() throws Exception{
	
	Optional<Node> Value1 = MultiMathOperation();
	
	do {
		if(tokenManagerMember.MoreTokens()) {
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.MINUS)) {
			tokenManagerMember.MatchAndRemove(Token.values.MINUS);
			Optional<Node> Value2 = MatchAndNotMatch();
			Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.SUBTRACT,Value2));
		}
		else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.PLUS)) {
			tokenManagerMember.MatchAndRemove(Token.values.PLUS);
			Optional<Node> Value2 = MatchAndNotMatch();
			Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.ADD,Value2));
		}
		else {
			return Value1;
		}
		}
		return Value1;
	}
		while(true);
}

//This method is called in AdditionSubtraction(),it checks for ASTERISK, SLASH, PERCENT
//MODULO, if it finds it then it returns new OperationNode with value1, the type, value2
public Optional<Node> MultiMathOperation() throws Exception{
	
	Optional<Node> Value1 = Exponent();
	
	do {
		if(tokenManagerMember.MoreTokens()) {
		if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.ASTERISK)) {
			tokenManagerMember.MatchAndRemove(Token.values.ASTERISK);
			Optional<Node> Value2 = MatchAndNotMatch();
			Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.MULTIPLY,Value2));
		}
		else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.SLASH)) {
			tokenManagerMember.MatchAndRemove(Token.values.SLASH);
			Optional<Node> Value2 = MatchAndNotMatch();
			Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.DIVIDE,Value2));
		}
		else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.PERCENT)) {
			tokenManagerMember.MatchAndRemove(Token.values.PERCENT);
			Optional<Node> Value2 = MatchAndNotMatch();
			Value1 =  Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.MODULO,Value2));
		}
		
		else {
			return Value1;
		}
		
	}
		return Value1;
	}
		while(true);
}
	
//this is being called MultiMathOperation()
public Optional<Node> Exponent() throws Exception{
	
	Optional<Node> Value1 = PostDecrementAndIncrement();
	
	
	if(tokenManagerMember.MoreTokens()) {
		
		if(tokenManagerMember.MatchAndRemove(Token.values.CARET).isPresent()) {
			
			//Optional<Node> Value2 = Exponent();
			Optional<Node> Value2 = PostDecrementAndIncrement();
			
			if(Value2.isPresent()) {
				
			return Optional.of( new OperationNode(Value1.get(), OperationNode.PossibleOperations.EXPONENT,Value2));	
			}
		}	
		
	}
	
	return Value1 ;
}


//This method is being called in Exponent(), it checks for increment and decrement sign if it
//if it's there, it make a new OperationNode with value1 and the type
public Optional<Node> PostDecrementAndIncrement() throws Exception{
	
	//lvalue
	Optional<Node> Value1 = ParseBottomLevel();
	
	
	if(tokenManagerMember.MoreTokens()) {
	if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.INCREMENT)) {
		
		tokenManagerMember.MatchAndRemove(Token.values.INCREMENT); 
	
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.POSTINC));			
	}
	
	else if(tokenManagerMember.Peek(0).get().getTokenType().equals(Token.values.DECREMENT)) {
		
		tokenManagerMember.MatchAndRemove(Token.values.DECREMENT); 
	
		return Optional.of(new OperationNode(Value1.get(), OperationNode.PossibleOperations.POSTDEC));			
	}
	}
	return Value1 ;
}




	

	
	
	
	
	
}

