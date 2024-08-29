
import java.util.Optional;

public class ReturnNode extends StatementNode{
	
	Optional<Node> ParseLvalue;
	
	
	
	public Optional<Node> getParseLvalue() {
		return ParseLvalue;
	}



	public void setParseLvalue(Optional<Node> parseLvalue) {
		ParseLvalue = parseLvalue;
	}



	public ReturnNode(Optional<Node> ParseLvalue) {
	
		 this.ParseLvalue = ParseLvalue;
	}
	
	
	
	@Override
	public String toString() {
		
		
		return ParseLvalue.get().toString();
	} 

}