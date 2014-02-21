package semantica;

public class Variable {

	public Type type;
	public String name;
	public Type valueType;

	public Variable(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public String toString() {
		return "Variable name: " + this.name + " type: " + this.type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Variable)) {
			return false;
		}
		
		Variable v = (Variable) obj;
		return (v.name.equals(this.name));
	}

}
