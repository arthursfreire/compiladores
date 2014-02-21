package semantica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Method {

	public Type type;
	public String name;
	public Type returnType;
	public List<Parameter> parameters;
	public MethodItems items;

	public Method(Type type, String name) {
		this.type = type;
		this.name = name;
		this.parameters = new ArrayList<Parameter>();
	}
	
	public Method(Type type, String name, List<Parameter> params)
			throws Exception {

		this.type = type;
		this.name = name;

		if (!checkParameters(params))
			throw new SemanticException("Duplicate Parameters in " + name);

		this.parameters = params;
	}

	private boolean checkParameters(List<Parameter> params) {
		if (params.size() > 1) {
			List<Parameter> temp = new ArrayList<Parameter>();
			for (Parameter p : params) {
				if (temp.contains(p)) {
					return false;
				} else {
					temp.add(p);
				}
			}
		}
		
		return true;
	}
	
	public String toString() {
		return "Method: " + this.name + " Type: " + this.type + " Params: "
				+ this.parameters;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Method)) {
			return false;
		}
		
		Method m = (Method) obj;
		return (m.name.equals(this.name) && m.type.equals(this.type));
	}

//	public List<Variable> setMethodItems(MethodItems methodItems, List<Variable> variables) throws SemanticException {
//		int varIndex = 0;
//		int stmIndex = 0;
//		
//		List<Variable> vars = new ArrayList<Variable>();
//		
//		for (int i = 0; i < methodItems.addAux.size(); i++) {
//			
//			if (methodItems.addAux.get(i) == methodItems.VARIABLE) {
//				
//				if (vars.contains(methodItems.variables.get(varIndex))) {
//					throw new SemanticException("Already exists a variable within " + toString() + " scope with the same name");
//				} else {
//					vars.add(methodItems.variables.get(varIndex));
//					varIndex++;
//				}
//				
//			} else {
//				
//				Statement stm = methodItems.statements.get(stmIndex);
//				
//				
//			}
//		}
//		
//	}
}
