import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

public class BuiltInFunctionDefinitionNode  extends FunctionDefinitionNode{
	
	private Function <HashMap<String,InterpreterDataType >, String> Execute ;
	private boolean isVariadic;
	

	
	public BuiltInFunctionDefinitionNode(String Name, boolean isVariadic, LinkedList <String>  ListOfParameters
	,Function <HashMap<String,InterpreterDataType >,String> Execute) {
		
		super(Name,ListOfParameters , null);
		
		this.isVariadic = isVariadic;
		this.Execute = Execute;
	}
	
	

	public boolean GetisVariadic() {
		return isVariadic;
	}

	public void setVariadic(boolean isVariadic) {
		this.isVariadic = isVariadic;
	}

	public Function<HashMap<String, InterpreterDataType>, String> getExecute() {
		return Execute;
	}


	public void setExecute(Function<HashMap<String, InterpreterDataType>, String> execute) {
		Execute = execute;
	}
	
	
}
