package semantica;

public class Parameter implements Comparable {

	public Type type;
	public String name;
	
	public Parameter(Type type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String toString() {
		return "Parameter name: " + this.name + " type: " + this.type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Parameter))
			return false;

		Parameter p = (Parameter) obj;
		boolean result = this.name.equals(p.name);
		return result;
	}

	@Override
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof Parameter)) return -1;
		Parameter p = (Parameter) arg0;
		return p.name.compareTo(this.name);
	}

}
