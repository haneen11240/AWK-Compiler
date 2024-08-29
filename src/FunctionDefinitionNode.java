import java.util.LinkedList;

public class FunctionDefinitionNode extends Node{
	
	private String Name;
	private LinkedList <String>  ListOfParameters;
	private LinkedList <StatementNode> ListOfStatementNode;
	
	/** FunctionDefinitionNode contains a name, a linked list of parameters and a linked list of StatementNode. 
	 * Assessors 
	 *  and a toString
	 */
	public FunctionDefinitionNode(String Name, LinkedList<String> ListOfParameters, LinkedList<StatementNode> ListOfStatementNode) {
		this.Name = Name;
		this.ListOfParameters = ListOfParameters;
		this.ListOfStatementNode = ListOfStatementNode;
	}
	
	public String GetName() {
		return Name;
	}
	
	public LinkedList<String> GetListOfParameters(){
		return ListOfParameters;
	}
	
	public LinkedList<StatementNode> GetListOfStatementNode(){
		return ListOfStatementNode;
	}
	
	@Override
	public String toString() {
		
		return Name.toString() + ListOfParameters.toString() + ListOfStatementNode.toString();
	}
	

}