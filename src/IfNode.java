import java.util.Optional;

public class IfNode extends StatementNode{
	
	
	
	Optional<Node> ParseOperation;
	BlockNode parseBlock;
	StatementNode IfNode;

	public IfNode(Optional<Node> ParseOperation,BlockNode parseBlock){
		
		this.ParseOperation = ParseOperation;
		this.parseBlock = parseBlock;
		this.IfNode = null;
	}
	
	public IfNode(Optional<Node> ParseOperation,BlockNode parseBlock, StatementNode IfNode){
		
		this.ParseOperation = ParseOperation;
		this.parseBlock = parseBlock;
		this.IfNode =  IfNode;
	}
	
	
	
	public Optional<Node> getParseOperation() {
		return ParseOperation;
	}

	public void setParseOperation(Optional<Node> parseOperation) {
		ParseOperation = parseOperation;
	}

	public BlockNode getParseBlock() {
		return parseBlock;
	}

	public void setParseBlock(BlockNode parseBlock) {
		this.parseBlock = parseBlock;
	}

	public StatementNode getIfNode() {
		return IfNode;
	}

	public void setIfNode(StatementNode ifNode) {
		IfNode = ifNode;
	}

	@Override
	public String toString() {
		
		if(ParseOperation.isPresent()) {
			
		return ParseOperation.get().toString() + parseBlock.toString() ;	
		}
		
		else {
			
		return parseBlock.toString()+ IfNode.toString() ;	
	
		}	
	}

}