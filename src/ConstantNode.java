public class ConstantNode extends Node{

	String Value;
	
	public ConstantNode (String Value){
		this.Value = Value;
	}
		
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public String toString() {
	//there was no tostring
		return Value.toString();
	}

}