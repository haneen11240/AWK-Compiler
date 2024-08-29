import java.util.HashMap;
import java.util.function.Function;

public class InterpreterArrayDataType extends InterpreterDataType{
	
	
	//This is a funcation with hashmap and strings. A boolean to check if it's variadic
	private Function<HashMap, String> execute ;
	
	private Boolean CheckIfvariadic;
	
	public InterpreterArrayDataType(Function<HashMap, String> execute) {
		this.execute = execute;
		
	}
	
	
	

}
