import java.util.Optional;

public class DeleteNode extends StatementNode{
	
	Optional<Node> ParseOperation;
	
	
	
	public DeleteNode(Optional<Node> ParseOperation) {
	
		 this.ParseOperation = ParseOperation;
	}
	
	
	
	public Optional<Node> getParseOperation() {
		return ParseOperation;
	}



	public void setParseOperation(Optional<Node> parseOperation) {
		ParseOperation = parseOperation;
	}



	@Override
	public String toString() {
		
		if(ParseOperation.isPresent()) {
			return ParseOperation.get().toString();
		}
		else {
		return null;
	}
	}
}