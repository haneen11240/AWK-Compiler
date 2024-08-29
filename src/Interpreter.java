import java.io.IOException;

import java.nio.file.Files;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
	
	private  HashMap<String, InterpreterDataType> GlobalVariables;
	private HashMap<String, FunctionDefinitionNode> SourceFunctionCall ;
	private ProgramNode TakeAProgramNode;
	private String FilePath;
	private LineManager LineManagerInstance;
	
	private String FS;
	private String OFMT;
	private String OFS;
	private String ORS;
	private ProgramNode ProgramNodeInstance;
	
	private FunctionDefinitionNode some;
	
	
	private TokenManager tokenManagerMember;
	
	
		public Interpreter(ProgramNode TakeAProgramNode, String FilePath) throws Exception{
		
			this.FilePath = FilePath;
			this.GlobalVariables = new HashMap();
			this.SourceFunctionCall = new HashMap();
		
		
		if(FilePath != null) {
			
			List<String>  ReadFiles = Files.readAllLines(Paths.get(FilePath));
			LineManagerInstance = new LineManager(ReadFiles);
		}
		
		else {
			LineManagerInstance = new LineManager();
		}
		GlobalVariables.put(FS, new InterpreterDataType(" "));
		GlobalVariables.put(OFMT, new InterpreterDataType("%.6g"));
		GlobalVariables.put(OFS, new InterpreterDataType(" "));
		GlobalVariables.put(ORS, new InterpreterDataType("\n"));
		
//		for(int i =0; i <= ProgramNodeInstance.FunctionNode().size(); i++) {
//			
//			FunctionDefinitionNode name = ProgramNodeInstance.FunctionNode().get(i);
//			
//			SourceFunctionCall.put(FilePath, name);	
//		}

		for(FunctionDefinitionNode some1 : TakeAProgramNode.FunctionNode()) {
			
			SourceFunctionCall.put(some1.GetName(),  some1);
;		}

		
		
		SourceFunctionCall.put("print", new BuiltInFunctionDefinitionNode("print", true, new LinkedList<String>(List.of("input")), (printlambda) -> {
			
		   
		   
		    //get a thing called input 
		    //then that going to return a IADT 
		    //then call .get value
		    
			InterpreterDataType Storeprintlambda1 = printlambda.get("input");
			String Storeprintlambda11 = Storeprintlambda1 .toString();
			
			System.out.print(Storeprintlambda11);
			
		    return "0"; 
		}));
		
		
		SourceFunctionCall.put("printf", new BuiltInFunctionDefinitionNode("printf", true, new LinkedList<String>(List.of("Format")), (printflambda) -> {
			   
			
			
			//takes string %s, format
			//array object 
			//loop through it to find the arguments
			//when you make the object array object[name = object] lambda -1 
			//start at i = 1, i< arry.length
			//interdattype = string +i
			//if parm != null { i-1} = parm.value
			
			InterpreterDataType Storeprintflambda1 = printflambda.get("%s");
			String Storeprintflambda11 = Storeprintflambda1 .toString();
			
			Object multipleParameters[] ;
			
			multipleParameters = new Object [200];
			
			for(int i =1; i < multipleParameters.length;  i++) {
				
				
			}
		    
		    return "0"; 
		}));
		
		
		SourceFunctionCall.put("getline", new BuiltInFunctionDefinitionNode("getline", true, new LinkedList<String>(List.of("getline")), (getlinelambda) -> {
			
			//check if it return a value return 1 else return 0
		   // System.out.println(getlinelambda);
		    
			LineManagerInstance.SplitAndAssign();
			
			if(LineManagerInstance.SplitAndAssign() == true) {
				return "1";
				
			}
			else {
				return "0";
			}
			
		    
		    
		}));
		
		
		SourceFunctionCall.put("next", new BuiltInFunctionDefinitionNode("next", true,new LinkedList<String>(List.of("next")), (nextlambda) -> {
			
			//call SplitAndAssign    
			
			LineManagerInstance.SplitAndAssign();
			
			if(LineManagerInstance.SplitAndAssign() == true) {
				return "1";
				
			}
			else {
				return "0";
			}
		    
		}));
		
		
		SourceFunctionCall.put("gsub", new BuiltInFunctionDefinitionNode("gsub", true, new LinkedList<String>(List.of("gsub")),(gsublambda) -> {
			
			//use Java’s built in regular expression   
			InterpreterDataType StoreGsub1 = gsublambda.get("Pattern");
			InterpreterDataType StoreGsub2 = gsublambda.get("Replace");
			InterpreterDataType StoreGsub3 = gsublambda.get("Target");
			int count = 0;
			
			String StoreGsub11 = StoreGsub1.toString();
			String StoreGsub22 = StoreGsub2.toString();
			String StoreGsub33 = StoreGsub3.toString();
			
			Pattern pattern1 = Pattern.compile(StoreGsub11);
			
			Matcher matcher1 = pattern1.matcher(StoreGsub22);
			
			if( matcher1.find()) {
				count++;
				matcher1.replaceAll(StoreGsub33);
			
			}
			
		    return Integer.toString(count); 
		}));
		
		
		SourceFunctionCall.put("match", new BuiltInFunctionDefinitionNode("match", true, new LinkedList<String>(List.of("match")),(matchlambda) -> {
			
			InterpreterDataType Storematch1 = matchlambda.get("regexp");
			InterpreterDataType Storematch2 = matchlambda.get("input");
			
			
			String Storematch11 = Storematch1.getIDTvalue();
			String Storematch22  = Storematch2.getIDTvalue();
			
			Pattern pattern1 = Pattern.compile(Storematch11);
			
			
			
			Matcher matcher1 = pattern1.matcher(Storematch22);
			
			
			if (matcher1.find()) {
	            return matcher1.group(); 
	        } else {
	            return "No match was found"; 
	        } 
			
			
			
			
//			String input = matchlambda.get("string").getIDTvalue();
//			
//			String reg = matchlambda.get("matcher").getIDTvalue();
//			
//			Pattern pat = Pattern.compile(reg);
//			Matcher match = pat.matcher(input);
//			return Integer.toString(match.find() ? match.start():-1);
			
			
		}));
		
		SourceFunctionCall.put("sub", new BuiltInFunctionDefinitionNode("sub", true,new LinkedList<String>(List.of("sub")) ,(sublambda) -> {
			
			//use Java’s built in regular expression   
		    
			InterpreterDataType Storematch1 = sublambda.get("input");
			InterpreterDataType Storematch2 = sublambda.get("regexp");
			InterpreterDataType Storematch3 = sublambda.get("replacement");
			
			
			String Storematch11 = Storematch1.toString();
			String Storematch22  = Storematch2.toString();
			String Storematch33  = Storematch3.toString();
			
			Pattern pattern1 = Pattern.compile(Storematch11);
			
			
			
			Matcher matcher1 = pattern1.matcher(Storematch22);
			
			//string. .substring
			
			String.valueOf(Storematch11.substring(0));
			// return Storematch11.replaceFirst(Storematch22, Storematch3); 
		    return "0"; 
		}));
		
		SourceFunctionCall.put("index", new BuiltInFunctionDefinitionNode("index", true, new LinkedList<String>(List.of("index")),(indexlambda) -> {
			
			//use Java’s built in regular expression   
		   if (indexlambda.size() < 2) {
			   return "Index() is less than two arguments";
			   
		   }
		   
		    
		    // function is used to search for a substring
		    //within a string and return the position of the first occurrence of the substring.
		    
		    return "0"; 
		}));
		
		SourceFunctionCall.put("length", new BuiltInFunctionDefinitionNode("length", true, new LinkedList<String>(List.of("length")),(lengthlambda) -> {
			
		
			//Get the input string from the argument
		InterpreterDataType Input = lengthlambda.get("0");
		
		String Input1 = Input.toString();
		
		
		// Calculate the length of the input string
		int InputLength = Input1.length();
		
		//Convert the length to a string and return it
		return String.valueOf(InputLength);
		
		
		}));
		
		
		
		SourceFunctionCall.put("split", new BuiltInFunctionDefinitionNode("split", true, new LinkedList<String>(List.of("split")),(splitlambda) -> {
			
			//use Java’s built in regular expression   
		   // System.out.println(splitlambda);
			//parts.length
			
			if (splitlambda.size() != 1) {
		        return "split function expects one argument";
		    }
			
			
			InterpreterDataType Input = splitlambda.get("0");
			
			String Input1 = Input.toString();
			
			String Input11[] = Input1.split(",");
		
		    
		    return String.valueOf(Input11.length); 
		}));
		
		
	SourceFunctionCall.put("substr", new BuiltInFunctionDefinitionNode("substr", true, new LinkedList<String>(List.of("substr")),(substrlambda) -> {
			
			//use Java’s built in regular expression   
		    
		    
		    return "0"; 
		}));
	
	
	SourceFunctionCall.put("tolower", new BuiltInFunctionDefinitionNode("tolower", true, new LinkedList<String>(List.of("tolower")),(tolowerlambda) -> {
	    
	    if (tolowerlambda.size() != 1) {
	        return ("tolower function expects one argument");
	    }

	    
	    InterpreterDataType Input = tolowerlambda.get("0");
		
		String Input1 = Input.toString();
		
		String result = Input1.toLowerCase();
	    
	    return result; 
	}));
	
	
	SourceFunctionCall.put("toupper", new BuiltInFunctionDefinitionNode("toupper", true,new LinkedList<String>(List.of("toupper")) ,(toupperlambda) -> {
		
		 if ( toupperlambda.size() != 1) {
		        return ("tolower function expects one argument");
		    }

		    
		    InterpreterDataType Input = toupperlambda.get("0");
			
			String Input1 = Input.toString();
			
			String result = Input1.toUpperCase();
		    
		    return result; 
	}));
		
		
	}
		
		
		public HashMap<String, FunctionDefinitionNode> getSourceFunctionCall() {
			return SourceFunctionCall;
		}


		public void setSourceFunctionCall(HashMap<String, FunctionDefinitionNode> sourceFunctionCall) {
			SourceFunctionCall = sourceFunctionCall;
		}


		public InterpreterDataType GetIDT(Node node, HashMap<String, InterpreterDataType> LocalVariables)
		throws Exception{
			
			//Check if instance of then call IsVariableOrOperationNode helper method
			//add to the  GlobalVariables then return new InterpreterDataType passing
			//Result  that we got from GetIDT
			 if (node instanceof AssignmentNode) {
				 
				 AssignmentNode AssignmentNodee = (AssignmentNode) node;
				 
				 if(IsVariableOrOperationNode(AssignmentNodee.getTarget())) { 
					 
					 InterpreterDataType StoreResult = GetIDT(AssignmentNodee.getExpression(),LocalVariables );
					 GlobalVariables.put(AssignmentNodee.getTarget().toString(), StoreResult);
					 
//						InterpreterDataType left = GetIDT(AssignmentNodee.getTarget(),locals );
//						InterpreterDataType right = GetIDT(AssignmentNodee.getExpression(),locals );
//						
//						left.setIDTvalue(right.getIDTvalue());
//						
//						return new  ReturnType( ReturnType.enumType.NONE, right.getIDTvalue());
//					}
					 
					return new InterpreterDataType(StoreResult.toString());
					 
				 }	  
			 }
			 
			 
			 //If instance of ConstantNode then get the value and set the IDT to the value result then return as a IDT
			 if (node instanceof ConstantNode) {
				 
				 ConstantNode ConstantNodee = (ConstantNode) node;
				 
				 String ConstantValue = ConstantNodee.getValue();
				 
				 InterpreterDataType DataType = new InterpreterDataType();
				
				 DataType.setIDTvalue(ConstantValue);
				 
				 return new InterpreterDataType(DataType.toString()); 
			 }
			 
			//If instance of FunctionCallNode then get the value from the method
			 // RunFunctionResult and set the IDT to the value result then return as a IDT

			 if(node instanceof FunctionCallNode) {			 
				 InterpreterDataType DataType = new InterpreterDataType();
				 
				 
				 FunctionCallNode functionCallNode = (FunctionCallNode) node;
				 
				 String RunFunctionResult = RunFunctionCall(functionCallNode, LocalVariables );
				 
				 DataType.setIDTvalue(RunFunctionResult);
				 
				 return new InterpreterDataType(DataType.toString());
			 }
			  
			 
			 //If instance of Pattern then get the value from then throw an exception 
			 if(node.getClass().equals(Pattern.class)) {
			 
				 throw new Exception("Erorr, pattern to a function or an assignment.");
			 }	 
			 
			 if(node instanceof TernaryNode ) {
				 
				 TernaryNode TernaryNodee = (TernaryNode) node;
				 InterpreterDataType StoreResult = GetIDT(TernaryNodee.getExpress(),LocalVariables );
				 
				 if(StoreResult.toString().compareTo("false") == 0) {
					 
					return new InterpreterDataType("false");
					 
				 } 
				 else {
					 return new InterpreterDataType("true");
				 }
			 }
			
			 if(node instanceof VariableReferenceNode) {
				 
				 VariableReferenceNode VariableReferenceNodee = null;
				 
				 if(!(VariableReferenceNodee.getVariableName().equals(null))) { 
					 
					 InterpreterDataType StoreResult = GetIDT(VariableReferenceNodee,LocalVariables );
					 
					 String StoreName = VariableReferenceNodee.getVariableName();
					 
					 if (GlobalVariables.containsKey(StoreName)) {
						 
						 if(GlobalVariables.get(StoreName)instanceof InterpreterArrayDataType ) {
							 
							 return new InterpreterDataType(StoreName);
						 }
					 }
				 }
				 
				 
				 if(VariableReferenceNodee.getVariableName().equals(null)) {
					 
					 InterpreterDataType StoreResult = GetIDT(VariableReferenceNodee,LocalVariables );
					 String StoreName = VariableReferenceNodee.getVariableName();
					 
					 if(LocalVariables.containsKey(StoreName)) {
						 
						 var GetName =  LocalVariables.get(StoreName);
						 return new InterpreterDataType(GetName.toString());
						 
					 }
					 else if (GlobalVariables.containsKey(StoreName)) {
						var GetName =  GlobalVariables.get(StoreName);
						 return new InterpreterDataType(GetName.toString());
					 }
					 
					 else {
						 GlobalVariables.put(StoreName, new InterpreterDataType(""));
					 }	 
				 } 
					 
			 }
			 
			 if(node instanceof  OperationNode) {
				 
				 OperationNode OperationNodee = (OperationNode) node;
				 
				 
				if( !(OperationNodee.getLeft().equals(null))) {
					
					InterpreterDataType StoreResult1 = GetIDT(OperationNodee.getLeft(),LocalVariables );
				
					
					if(OperationNodee.getRight().isPresent()) {
						
						InterpreterDataType StoreResult2 = GetIDT(OperationNodee.getRight().get(),LocalVariables );	
						
						
						float convertToFloat1 = Float.parseFloat(StoreResult1.toString());
						float convertToFloat2 = Float.parseFloat(StoreResult2.toString());
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.ADD)) {
							
							//OperationNodee.GetoperationType().DOLLAR
							float addResult = convertToFloat1 + convertToFloat2;
							
							String ConvertToString = String.valueOf(addResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.SUBTRACT)) {
							
							float SubResult = convertToFloat1 - convertToFloat2;
							
							String ConvertToString = String.valueOf(SubResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.MULTIPLY)) {
							
							float MultiResult = convertToFloat1 * convertToFloat2;
							
							String ConvertToString = String.valueOf(MultiResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.DIVIDE)) {
							
							float DivResult = convertToFloat1 / convertToFloat2;
							
							String ConvertToString = String.valueOf(DivResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.MODULO)) {
							
							float MoResult = convertToFloat1 % convertToFloat2;
							
							String ConvertToString = String.valueOf(MoResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.EXPONENT)) {
							
							float ExResult = (float) Math.pow(convertToFloat1, convertToFloat2); 
							String ConvertToString = String.valueOf(ExResult);
							return new InterpreterDataType(ConvertToString);
						}
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.EQ)) {
							
				
							if(convertToFloat1 == convertToFloat2) {
							
							String ConvertToString = String.valueOf(convertToFloat2);
							return new InterpreterDataType("true");
							}
							if(StoreResult1.equals(StoreResult2)) {
						
								String StoreName = OperationNodee.getLeft().toString();
								return new InterpreterDataType("true" );
							}
							else {
								return new InterpreterDataType("false");
							}
							
						}
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.NE)) {
							
							
							if(convertToFloat1 != convertToFloat2) {
							
							String ConvertToString = String.valueOf(convertToFloat2);
							return new InterpreterDataType("true");
							}
							
							if(!(StoreResult1.equals(StoreResult2))) {
								
								String StoreName = OperationNodee.getLeft().toString();
								return new InterpreterDataType("true");
							}
							else {
								return new InterpreterDataType("false");
							}
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.AND)) {
						
							if(StoreResult1.toString().compareTo(StoreResult2.toString())== 0) {
								return new InterpreterDataType( "true");
								
							}
							if(convertToFloat1 != 0 && convertToFloat2 !=0 ) {
								return new InterpreterDataType( "true");
							}
							else {
								return new InterpreterDataType( "false");
							}
							
						}
						
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.OR)) {
							
							if(StoreResult1.equals(true) || StoreResult2.toString().equals(true)) {
								return new InterpreterDataType( "true");
								
							}
							if(convertToFloat1 != 0 || convertToFloat2 !=0 ) {
								return new InterpreterDataType( "true");
							}
							else {
								return new InterpreterDataType( "false");
							}
							
						}
						
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.MATCH)) {
	
							
							String StoreResultt1 = OperationNodee.getLeft().toString();
							String Storematch22  =  OperationNodee.getRight().get().toString();
							
							Pattern pattern1 = Pattern.compile(StoreResultt1);
							
							Matcher matcher1 = pattern1.matcher(Storematch22);
							
							if (matcher1.find()) {
								return new InterpreterDataType( "true");
					        } else {
					        	return new InterpreterDataType( "false");
					        }	
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.NOTMATCH)) {
	
							
							String StoreResultt1 = OperationNodee.getLeft().toString();
							String Storematch22  =  OperationNodee.getRight().get().toString();
							
							Pattern pattern1 = Pattern.compile(StoreResultt1);
							
							Matcher matcher1 = pattern1.matcher(Storematch22);
							
							if (matcher1.find()) {
								return new InterpreterDataType( "false");
					        } else {
					        	return new InterpreterDataType( "true");
					        }
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.DOLLAR)) {
							
							String Dollar = "$" + OperationNodee.getLeft().toString();
							//var storewithDollar = LocalVariables.put(Dollar, StoreResult2);
								return new InterpreterDataType(Dollar);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.PREINC)) {
							
							float PreIncResult = ++convertToFloat1  ;
							
							String ConvertToString = String.valueOf(PreIncResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.PREDEC)) {
							
							float PreIncResult = --convertToFloat1  ;
							
							String ConvertToString = String.valueOf(PreIncResult);
							return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.POSTDEC)) {
							
							float PostIncResult = convertToFloat1 -- ;

							InterpreterDataType StoreResult11 = GetIDT(OperationNodee.getLeft(),LocalVariables );
							StoreResult11.setIDTvalue(StoreResult1 .toString());
							String ConvertToString = String.valueOf(PostIncResult);
							
							 return new InterpreterDataType(ConvertToString);
						}
						
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.POSTINC)) {
							
							float PostIncResult = convertToFloat1 ++ ;

							InterpreterDataType StoreResult11 = GetIDT(OperationNodee.getLeft(),LocalVariables );
							StoreResult11.setIDTvalue(StoreResult1 .toString());
							String ConvertToString = String.valueOf(PostIncResult);
							
							 return new InterpreterDataType(ConvertToString);
						}
						
						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.UNARYNEG)) {
							
							float PreIncResult = convertToFloat1 -1 ;
							
							String ConvertToString = String.valueOf(PreIncResult);
							return new InterpreterDataType(ConvertToString);
						}
						

						if(OperationNodee.GetoperationType().equals(OperationNode.PossibleOperations.UNARYPOS)) {
							float PreIncResult = convertToFloat1 +1 ;
							
							String ConvertToString = String.valueOf(PreIncResult);
							return new InterpreterDataType(ConvertToString);
						}	
				} 
				}
			 }
			InterpreterDataType DataType = new InterpreterDataType();
			return DataType;
		}
		
		public ReturnType ProcessStatement(HashMap<String, InterpreterDataType> locals,  StatementNode  stmt) throws Exception { 
			
			
			//If it's an instanceof  BreakNode then return enum type break
			if (stmt instanceof  BreakNode) {
				
				return new ReturnType(ReturnType.enumType.Break);
				
			}
			
			
			//If it's an instanceof  ContinueNode then return enum type Continue
			if(stmt instanceof ContinueNode ) {
				
				return new ReturnType(ReturnType.enumType.Continue);
			}
			
			//if it's an instanceof  DeleteNode get the array from the variables (local, then global),
			//If indices is set, delete them from the array, otherwise delete them all. 

			if (stmt instanceof DeleteNode) {
				
				DeleteNode node = (DeleteNode) stmt;
				
				VariableReferenceNode varRef = (VariableReferenceNode) node.getParseOperation().get();
				String name = varRef.getVariableName();
				
				if(locals.containsKey(name)) {
					
					if(locals.get(name) instanceof InterpreterArrayDataType) {
						
						Optional<Node> array = varRef.getIndexExpression();
						
						if(array != null) {
							
							var Index =(InterpreterArrayDataType) GetIDT(array.get(), locals);
							locals.remove(Index.getIDTvalue());
						}
					}
						else {
							locals.remove(name);
						}
					
				}
				
				if(GlobalVariables.containsKey(name)) {
					
					if(GlobalVariables.get(name) instanceof InterpreterArrayDataType) {
						Optional<Node> array = varRef.getIndexExpression();
						
						if(array != null) {
							
							var Index =(InterpreterArrayDataType) GetIDT(array.get(), locals);
							GlobalVariables.remove(Index.getIDTvalue());
						}
					}
						else {
							GlobalVariables.remove(name);
						}
				}	
			}
			
			
			//call InterpretListOfStatements, a do-while loop, using GetIDT to evaluate the condition. 
			//Check the return value of InterpretListOfStatements
			//if it is Break, then break out of the loop, on return, return from ProcessStatement.

			if(stmt instanceof DoWhileNode) {
				
				Node condition = ((DoWhileNode) stmt).ParseOperation.get();
				
				LinkedList<StatementNode> StatmentList = ((DoWhileNode) stmt).parseblock.GetListOfStatementNode();
				
				do {
				ReturnType returntype = InterpretListOfStatements(StatmentList, locals);
				
				if(returntype.getEnumTypee().equals(ReturnType.enumType.Break)) {
					 break;
				}
				
				if(returntype.getEnumTypee().equals(ReturnType.enumType.Return)) {
					return returntype; 
				}
					
				}while(GetIDT(condition,locals ).getIDTvalue().equals("true") || GetIDT(condition,locals ).getIDTvalue().equals("1"));
				
				
			}
			
			
			
			//call processStatement on it, create a while loop, using the forNode’s condition as the while’s condition.
			//Inside, call InterpretListOfStatements() on forNode’s statements
			//if it is Break, then break out of the loop, on return, return from ProcessStatement.

			if(stmt instanceof ForNode ) {
				
				Optional<Node> condition = ((ForNode) stmt).getParseOperation1();
				
				  ProcessStatement(locals, stmt);
				 LinkedList<StatementNode> StatmentList = ((ForNode ) stmt).parseblock.GetListOfStatementNode();
				 
				 while(GetIDT(condition.get(),locals).getIDTvalue().equals("true") || GetIDT(condition.get(),locals).getIDTvalue().equals("1")) {
					 
					 ReturnType returntype = InterpretListOfStatements(StatmentList, locals);
					
					 if(returntype.getEnumTypee().equals(ReturnType.enumType.Break)){
							break;
						}
					 
					 if(returntype.getEnumTypee().equals(ReturnType.enumType.Return)) {
							return returntype;
						}
					 
					 ProcessStatement(locals, stmt);
				 }
				
			}
			
			if(stmt instanceof ForEachNode )  {
				
				
				Optional<Node> condition = ((ForEachNode) stmt).getParseOperation1();
				
				  ProcessStatement(locals, stmt);
				 LinkedList<StatementNode> StatmentList = ((ForEachNode ) stmt).parseBlock.GetListOfStatementNode();
				 
				 while(GetIDT(condition.get(),locals).getIDTvalue().equals("true") || GetIDT(condition.get(),locals).getIDTvalue().equals("1")) {
					 
					 ReturnType returntype = InterpretListOfStatements(StatmentList, locals);
					
					 if(returntype.getEnumTypee().equals(ReturnType.enumType.Break)){
							break;
						}
					 
					 if(returntype.getEnumTypee().equals(ReturnType.enumType.Return)) {
							return returntype;
						}
					 
					 ProcessStatement(locals, stmt);
				 }
				
				
				
			}
			
			
			
			//Remember that ifNodes are a linked list. Walk the linked list, looking for an IfNode where Condition is empty OR it evaluates to true. 
			//If the return from InterpretListOfStatements is not “None” then return, passing that result back to the caller
			if(stmt instanceof IfNode) {
				
				var ifnodee = ((IfNode) stmt).parseBlock.GetListOfStatementNode();
				var body = ((IfNode) stmt).getParseBlock();
				var condition =  ((IfNode) stmt).ParseOperation.get();
				
					if(GetIDT(condition , locals).getIDTvalue().isEmpty()  || GetIDT(condition , locals).getIDTvalue().equals("true")
							|| GetIDT(condition , locals).getIDTvalue().equals("1")) {
						
					var  ReturnTypee = InterpretListOfStatements(ifnodee, locals );
					
					if(ReturnTypee.getEnumTypee().equals(ReturnType.enumType.Normal)) {
						return new ReturnType(ReturnType.enumType.Normal );
					}	
					
					
					if(ReturnTypee.getEnumTypee().equals(ReturnType.enumType.Break)){
						return new ReturnType(ReturnType.enumType.Break );
					}	
				}
			}
			
			
			//if there is a value, evaluate it. Make a ReturnType of (value, Return).
			if(stmt instanceof ReturnNode) {
				
				Optional<Node> condition = ((ReturnNode) stmt).getParseLvalue();
	
				if(condition.isPresent()) {
					
					var callGetITD = GetIDT(condition.get(),locals);
					
				return new ReturnType(ReturnType.enumType.Return, callGetITD.toString());
					
				}
			}
			
			////call InterpretListOfStatements, a while loop, using GetIDT to evaluate the condition. 
			//Check the return value of InterpretListOfStatements
			//if it is Break, then break out of the loop, on return, return from ProcessStatement.
			if (stmt instanceof  WhileNode) {
				
				Node condition = ((WhileNode) stmt).ParseOperation.get();
				
				LinkedList<StatementNode> StatmentList = ((WhileNode) stmt).parseblock.GetListOfStatementNode();
				
		
				while(GetIDT(condition,locals ).getIDTvalue().equals("true") || GetIDT(condition,locals ).getIDTvalue().equals("1")) {
				ReturnType returntype = InterpretListOfStatements(StatmentList, locals);
				
				if(returntype.getEnumTypee().equals(ReturnType.enumType.Break)){
					break;
				}
			 
			 if(returntype.getEnumTypee().equals(ReturnType.enumType.Return)) {
					return returntype;
				}
				
					}
				
						}
			
			
			//this will take care of assignment ,functionCall
			
			else {
				InterpreterDataType  getIDTValue = GetIDT( stmt,locals);
				if (getIDTValue == null) {
					 throw new Exception("getIDTValue is null");
					
				}
				else {
					return new ReturnType(ReturnType.enumType.Normal,getIDTValue.getIDTvalue());
				}
				
			}
			return null;
		}
		
		
		public ReturnType InterpretListOfStatements(LinkedList<StatementNode> statements,
													HashMap<String, InterpreterDataType> locals) throws Exception {
		
			
		    for (StatementNode EachStatmentNode : statements) {
		    	
		        ReturnType ReturnTypeFromStatement = ProcessStatement(locals, EachStatmentNode);
		        
		        if (ReturnTypeFromStatement.getEnumTypee()!= ReturnType.enumType.Normal) {
		        	return ReturnTypeFromStatement;
		        }
		    }
		    return new ReturnType(ReturnType.enumType.Normal);
			
			
}
		
		private boolean IsVariableOrOperationNode(Node node) {
			
	        return (node instanceof VariableReferenceNode) || (node instanceof OperationNode &&
	        		
	        		OperationNode.PossibleOperations.DOLLAR.equals(node));
	        
	    }
		
		
		public void InterpretProgram(ProgramNode programNode) throws Exception {
			
			LinkedList <BlockNode> BEGIN = programNode.GetAddStartBlocks();
			
			LineManager InstanceOf = new LineManager();
			
			for(BlockNode eachBeginBlock : BEGIN) {
				
				InterpretBlock(eachBeginBlock);
			}
			
			
			LinkedList <BlockNode> Blocks = programNode.GetAddBlocks();
			
		while(InstanceOf.SplitAndAssign()) {
			
			int BlocksSize = Blocks.size();	
			
			for(int i = 0; i < BlocksSize; i++) {
				
				 BlockNode eachOtherBlock = Blocks.get(i);
				 
				 InterpretBlock(eachOtherBlock);
		}
			}
		
	
		LinkedList <BlockNode> End = programNode.GetAddEndBlocks();
		
			
		for(BlockNode eachEndBlock : End) {
			
			InterpretBlock(eachEndBlock);
		}
				}
		
		
		public LinkedList <ReturnType>InterpretBlock(BlockNode blockNode) throws Exception {
			
			Optional<Node> condition = blockNode.GetOptionalValue();
			LinkedList <ReturnType> list = new LinkedList<>();
			HashMap<String, InterpreterDataType> locals = new HashMap();
	
			if(condition.isPresent()) {
				
				if(GetIDT(condition.get(), locals).getIDTvalue().equals("1") ||
						GetIDT(condition.get(), locals).getIDTvalue().equals("true")) {
					
					
					for(StatementNode statementNode : blockNode.GetListOfStatementNode()) {
						//ProcessStatement(locals, statementNode);
						list.add(ProcessStatement(locals, statementNode));
					}
					return list;
				}
			}
			else {
				
				
				for(StatementNode statementNode : blockNode.GetListOfStatementNode()) {
					
					list.add(ProcessStatement(locals, statementNode));
					
					}
				
				return list;
				
			}
			
			return null;
			
}
		
		private String RunFunctionCall(FunctionCallNode FunctionCallNodee, HashMap<String, InterpreterDataType> LocalVariables ) throws Exception {
			
			if(GlobalVariables.containsKey(FunctionCallNodee.getName())){
				
				FunctionDefinitionNode functionDefinitionnode = SourceFunctionCall.get(FunctionCallNodee.getName());
				HashMap<String, InterpreterDataType> NewLocal = new HashMap<>();
				
				if(functionDefinitionnode.GetListOfParameters().size() == FunctionCallNodee.GetListOfParameters().size()) {
					
					int i = 0;
					for( String EachParameters : functionDefinitionnode.GetListOfParameters()) {
						
						NewLocal.put(EachParameters, GetIDT( FunctionCallNodee.GetListOfParameters().get(i++),LocalVariables ));
					}
				}
				
		
				if(functionDefinitionnode instanceof BuiltInFunctionDefinitionNode Built && Built.GetisVariadic()  ) {
					
				//	BuiltInFunctionDefinitionNode DefintionNode = new BuiltInFunctionDefinitionNode(FS, true, null, null);
					
					int i = 0;
					
					for(var s : FunctionCallNodee.GetListOfParameters()) {
						
						NewLocal.put(Built.GetListOfParameters().get(i++), GetIDT(FunctionCallNodee.GetListOfParameters().get(i++), LocalVariables));
					}
				
				}
				else {
					
				return 	InterpretListOfStatements(functionDefinitionnode.GetListOfStatementNode(), NewLocal).getValue();
					
				}
			}
			
			return "";
			
		}	
		
		
	class LineManager{
		
		
		
		private List<String> ReadInFileLines;
		int CurrentLine = 0;
		String StroreGetLineNumber;
		String CurrentLineFileds[];
		int NF =0;
		int NR = 0;
		int FNR = 0;
		
		
		public LineManager(List<String> ReadInFile) {
			this.ReadInFileLines =  ReadInFile;
			
		}
		public LineManager() {
			
		}
		
		public boolean SplitAndAssign(){
			
			if(CurrentLine > ReadInFileLines.size()) {
				return false;
				
			}
			
		//	Get the line of text at the current line number and store it in a variable called StroreGetLineNumber 
			StroreGetLineNumber = ReadInFileLines.get(CurrentLine);
			
			//the result is an array of strings(Each word in the StroreGetLineNumber line) 
			//, and we are storing this it in a variable called CurrentLineFileds. It will split at the string FS = separator
			CurrentLineFileds = StroreGetLineNumber.split(FS);
			
			NF = CurrentLineFileds.length;
			CurrentLine++;
				NR++;
				FNR++;
				
				 ////put into the hashMap and print using the $ sign
				//GlobalVariables
		        for (int i = 0; i < NF; i++) {
		        	GlobalVariables.put("$" +i, new InterpreterDataType(CurrentLineFileds[i]));
		        }
		        
			return true;
			
		}	
		
	}

}
