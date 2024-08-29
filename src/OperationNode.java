
import java.util.Optional;

public class OperationNode extends StatementNode {
	
	 private Node left;
	 private Optional<Node> right;
	 private PossibleOperations operationType;
	 
	 public enum PossibleOperations{
	        EQ, NE, LT, LE, GT, GE, AND, OR, NOT, MATCH, NOTMATCH, DOLLAR,
	        PREINC,POSTINC,PREDEC, POSTDEC,UNARYPOS, UNARYNEG, IN,
	        EXPONENT, ADD, SUBTRACT,MULTIPLY, DIVIDE,MODULO, CONCATENATION;
	 }
	 

	public OperationNode(Node left,PossibleOperations operationType) {
		this.left = left;
		this.operationType = operationType;
		this.right = Optional.empty();
	}
	
	
	public OperationNode(Node left,PossibleOperations operationType, Optional<Node> right) {
		this.left = left;
		this.operationType = operationType;
		this.right = right ;
	}
	
	public PossibleOperations GetoperationType() {
		return operationType;
	}
	public Node getLeft() {
		return left;
	}

	public Optional<Node> getRight() {
		return right;
	}

	@Override
	public String toString() {
		
		if (right.isPresent()) {
			return left.toString() +" "+ GetoperationType().toString() + " " +right.get().toString();	
		}
		else  {
		 return left.toString() +" "+ GetoperationType().toString() ;
		}
	
	}

	

	

}