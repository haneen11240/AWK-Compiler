import java.util.Optional;

public class ForNode extends StatementNode {
	
	Optional<Node> ParseOperation1;
	Optional<Node> ParseOperation2;
	//Node in;
	BlockNode parseblock;
	OperationNode.PossibleOperations in;

	public ForNode(Optional<Node> ParseOperation1,OperationNode.PossibleOperations in, Optional<Node> ParseOperation2, BlockNode parseblock) {
		
		this.ParseOperation1 = ParseOperation1;
		this.in = in;
		this. ParseOperation2 =  ParseOperation2;
		this.parseblock = parseblock;
	}
	
	
	//this was commented
	public ForNode(Optional<Node> ParseOperation1, BlockNode parseblock) {
		
		this.ParseOperation1 = ParseOperation1;
		this.in = null;
		this.ParseOperation2 = Optional.empty();
		this.parseblock = parseblock;
		
	}
	
	
	
	public Optional<Node> getParseOperation1() {
		return ParseOperation1;
	}


	public void setParseOperation1(Optional<Node> parseOperation1) {
		ParseOperation1 = parseOperation1;
	}


	public Optional<Node> getParseOperation2() {
		return ParseOperation2;
	}


	public void setParseOperation2(Optional<Node> parseOperation2) {
		ParseOperation2 = parseOperation2;
	}


	public BlockNode getParseblock() {
		return parseblock;
	}


	public void setParseblock(BlockNode parseblock) {
		this.parseblock = parseblock;
	}


	public OperationNode.PossibleOperations getIn() {
		return in;
	}


	public void setIn(OperationNode.PossibleOperations in) {
		this.in = in;
	}


	@Override
	public String toString() {
		
		if(ParseOperation1.isPresent() && ParseOperation2.isPresent() ) {
			return ParseOperation1.get().toString() + ParseOperation2.get().toString()
					+ in.toString() ;
		}
		
		else if(ParseOperation1.isPresent()){
			 return ParseOperation1.get().toString()  + parseblock.toString();
		}
		else {
			return  in.toString() + parseblock.toString();
}
	}
}
