import java.util.LinkedList;
import java.util.Optional;

public class FunctionCallNode extends StatementNode {
	
	
	
	private String Name;
	private LinkedList<Node> ListOfParameters  ;
	

	public FunctionCallNode(String Name, LinkedList<Node> Function) {
		this.Name = Name;
		this.ListOfParameters = Function;
		
	}
	
	
	
	
	public String getName() {
		return Name;
	}




	public void setName(String name) {
		Name = name;
	}




	public LinkedList<Node> GetListOfParameters() {
		return ListOfParameters;
	}




	public void setFunction(LinkedList<Node> function) {
		ListOfParameters = function;
	}




	@Override
	public String toString() {
		
		 
		return Name.toString() + ListOfParameters.toString();
	}

}