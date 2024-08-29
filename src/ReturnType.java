
public class ReturnType {

	public enum enumType{
		Normal, Break, Continue, Return, NONE

	}
	
	
	private enumType enumTypee;
	private String value;
	
	public ReturnType(enumType enumTypee) {
		this.enumTypee = enumTypee;
		
	}
	
	public ReturnType(enumType enumTypee, String value) {
		
		this.enumTypee = enumTypee;
		this.value = value;
		
	}

	public enumType getEnumTypee() {
		return enumTypee;
	}

	public void setEnumTypee(enumType enumTypee) {
		this.enumTypee = enumTypee;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public String toString() {
		
		if(value == null) {
			return "Type is " +" "+enumTypee;	
		}
		else {
		return enumTypee +" " +value;
		}
	}
}