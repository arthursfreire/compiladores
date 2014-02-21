package semantica;

public class Type {
	
	public String name;
	
	public Type(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Type))
			return false;
		
		Type t = (Type) obj;
		return (this.name.equals(t.name));
	}

}
