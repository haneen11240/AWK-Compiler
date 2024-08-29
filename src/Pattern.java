

public class Pattern extends Node{
	
	private String value;
	
	public Pattern(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		
		return value.toString();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}