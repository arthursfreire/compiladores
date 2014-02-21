package semantica;

public class IdentifierValueType {
	
	public String identifier;
	public Type valueType;
	
	public IdentifierValueType(String identifier) {
		this.identifier = identifier;
	}
	
	public IdentifierValueType(String identifier, Type valueType) {
		this.identifier = identifier;
		this.valueType = valueType;
	}
	
	@Override
	public String toString() {
		return "IdentifierValueType identifier: " + this.identifier + " valueType: " + this.valueType;
	}

}
