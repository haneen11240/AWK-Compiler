
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StringHandler {

	private String FileHolder; 
	private int CurrentIndex;
	
	public StringHandler(String FileHolder, int CurrentIndex) {
		
		this.FileHolder = FileHolder;
		this.CurrentIndex = CurrentIndex;	
	}
	//looks “ICharactersAhead” characters ahead and returns that character
	public char Peek (int ICharactersAhead) {
		if (!IsDone()) {
			return FileHolder.charAt(CurrentIndex + ICharactersAhead);
		}
		char Done= 0;
		return Done;
	}
	
	//returns a string of the next “NextIcharacters” characters 
	public String PeekString(int NextIcharacters ) {
		if(CurrentIndex + NextIcharacters  <= FileHolder.length()) {
		String subString = FileHolder.substring(CurrentIndex ,  CurrentIndex + NextIcharacters);
		return subString;
		}
		String NextCharaterDoseNotExist = "";
		return NextCharaterDoseNotExist;
	}
	
	//returns the next character and moves the index
	public char GetChar() {
		return FileHolder.charAt(CurrentIndex++);	
	}
	
	//moves the index ahead "AheadIndex" positions
	public void Swallow(int AheadIndex) {
		CurrentIndex += AheadIndex;	
	}
	
	//returns true if we are at the end of the document and false otherwise
	public boolean IsDone() {
		if(CurrentIndex >= FileHolder.length()) {
			return true;
		}
		return false;
	}
	
	//returns the rest of the document as a string
	public String Remainder() {
		return FileHolder.substring(CurrentIndex);
	}
}