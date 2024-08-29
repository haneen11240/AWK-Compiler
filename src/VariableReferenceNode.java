
import java.util.Optional;



public class VariableReferenceNode extends Node {
	
	private String VariableName;
	private Optional<Node> IndexExpression;
	
	public VariableReferenceNode(String VariableName, Optional<Node> IndexExpression) {
		
		this.VariableName = VariableName;
		this.IndexExpression = IndexExpression;
		
	}
	public VariableReferenceNode(String VariableName) {
		this.VariableName = VariableName;
		this.IndexExpression = Optional.empty();
	}
	
	
	public String getVariableName() {
		return VariableName;
	}
	
	public Optional<Node> getIndexExpression() {
		return IndexExpression;
	}
	 
	

	@Override
	public String toString() {
		if(IndexExpression.isPresent()) {
			//new
			return getVariableName().toString()  +" "+ getIndexExpression().get().toString();	
		}
		else {
		return getVariableName().toString();
	}
		}

}