
public class AssignmentNode extends StatementNode {
	
	Node Target;
	Node Expression;
	
	public AssignmentNode(Node Target, Node Expression) {
		this.Target = Target;
		this.Expression = Expression;
	}
	

	public Node getTarget() {
		return Target;
	}


	public void setTarget(Node target) {
		Target = target;
	}


	public Node getExpression() {
		return Expression;
	}


	public void setExpression(Node expression) {
		Expression = expression;
	}


	@Override
	public String toString() {
		
		return Target.toString()+" " +"=" +" "+ Expression.toString() ;
	}
	
	
}