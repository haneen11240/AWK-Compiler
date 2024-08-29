
import java.util.Optional;

public class WhileNode extends StatementNode{
	
	Optional<Node> ParseOperation;
	BlockNode parseblock;
	
	public WhileNode(Optional<Node> ParseOperation,BlockNode parseblock ) {
		
		this.ParseOperation = ParseOperation;
		this.parseblock = parseblock;
		
	}
	
	
	
	@Override
	public String toString() {
		
		if(ParseOperation.isPresent()) {
			return ParseOperation.get().toString() +" " + parseblock.toString() ;
		}
		
		else {
			return parseblock.toString() ;
		}
	}

}