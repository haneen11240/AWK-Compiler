import java.util.Optional;

public class ForEachNode extends StatementNode{
	Optional<Node> ParseOperation1;
	
	BlockNode parseBlock;

	public Optional<Node> getParseOperation1() {
		return ParseOperation1;
	}

	public void setParseOperation1(Optional<Node> parseOperation1) {
		ParseOperation1 = parseOperation1;
	}

	public BlockNode getParseBlock() {
		return parseBlock;
	}

	public void setParseBlock(BlockNode parseBlock) {
		this.parseBlock = parseBlock;
	}
	
	

}
