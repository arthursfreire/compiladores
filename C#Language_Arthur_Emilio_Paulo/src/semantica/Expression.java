package semantica;

public class Expression {
	
	public String value;
	public Type type;
	
	public Expression(String value, Type type) {
		this.value = value;
		this.type = type;
	}
	
	public Expression() {
		
	}
	
	public String toString() {
		return "Value: " + this.value + " type: " + this.type;
	}

}
