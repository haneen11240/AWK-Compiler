import java.util.LinkedList;
import java.util.Optional;

public class BlockNode extends Node {
	
	/**
	 * A BlockNode has two components one is a linked list of StatementNode and the other is a condition
	 * Assessors 
	 * toString method
	 */
	private LinkedList <StatementNode> ListOfStatementNode;
	private Optional<Node> OptionalValue;
	
	
	
	public BlockNode(LinkedList <StatementNode> ListOfStatementNode, Optional<Node> OptionalValue) {
		
		this.ListOfStatementNode = ListOfStatementNode;
		this.OptionalValue = OptionalValue;
	}
	
	public LinkedList<StatementNode> GetListOfStatementNode(){
		return ListOfStatementNode;
	}
	
	public Optional<Node> GetOptionalValue(){
		return OptionalValue;
	}
	
	@Override
	public String toString() {
		
		if(OptionalValue.isPresent()) {
			return ListOfStatementNode.toString() + OptionalValue.get().toString();
		}
		else {
		return ListOfStatementNode.toString();
	}
	}
}