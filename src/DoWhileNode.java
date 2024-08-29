import java.util.Optional;

public class DoWhileNode extends StatementNode{
	
	//condition
	Optional<Node> ParseOperation;
	BlockNode parseblock;
	
	public DoWhileNode( BlockNode parseblock ,Optional<Node> ParseOperation ) {
		
		
		this.parseblock = parseblock;
		this.ParseOperation = ParseOperation;
	}
	
	
	
	public Optional<Node> getParseOperation() {
		return ParseOperation;
	}



	public void setParseOperation(Optional<Node> parseOperation) {
		ParseOperation = parseOperation;
	}



	public BlockNode getParseblock() {
		return parseblock;
	}



	public void setParseblock(BlockNode parseblock) {
		this.parseblock = parseblock;
	}
	
	
	@Override
	public String toString() {
		
		if(ParseOperation.isPresent()) {
			
		return "do " + parseblock.toString() +" while (" +ParseOperation.get().toString()
				+ " )";
		}
		else {
		return parseblock.toString() ;
	}
	}
}