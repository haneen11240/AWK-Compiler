
import java.util.LinkedList;
import java.util.Optional;

public class TokenManager {
	
	
	private LinkedList<Token> TokenLikedList;
	
	
	public TokenManager (LinkedList<Token> TokenLikedList){
		this.TokenLikedList = TokenLikedList;
	}
	
	//it peek “j” tokens ahead and return the token if we aren’t past the end of the token list.
	public Optional<Token> Peek(int j){
		
		if ( TokenLikedList.size() >  j) {
			return Optional.of(TokenLikedList.get(j));
			
		}
		return Optional.empty();	
	}
	
	//Checks if there's more token in the list
	public boolean MoreTokens() {
		
		if((!(TokenLikedList.isEmpty()))) {
			return true;
		}
		return false;
	}
	
	//looks at the head of the list. If the token type of the 
	//head is the same as what was passed in, remove that token from the list and return it. In all other cases, 
	//returns Optional.Empty(). 
	public Optional<Token> MatchAndRemove(Token.values t) {
		
		if((!(TokenLikedList.isEmpty())) && TokenLikedList.getFirst().getTokenType().equals(t)) {
			
			return Optional.of(TokenLikedList.removeFirst());
		}
		return Optional.empty();
		
	}
}