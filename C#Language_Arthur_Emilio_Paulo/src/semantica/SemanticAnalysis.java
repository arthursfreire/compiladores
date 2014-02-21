package semantica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SemanticAnalysis {
	
	private static SemanticAnalysis instance;
	private List<Method> methods = new ArrayList<Method>();
	private List<Variable> variables  = new ArrayList<Variable>();
	
	public synchronized static SemanticAnalysis getInstance() {
		if (instance == null) {
			instance = new SemanticAnalysis();
		}
		return instance;
	}
	
	public void addMethod(Method m) throws SemanticException {
		if (!checkForOverloading(m)) {
			throw new SemanticException("Illegal Method Declaration: " + m.toString());
		}
		
		methods.add(m);
		System.out.println("Added " + m.toString());
		System.out.println("Methods List: " + methods.toString());
	}
	
	public void addVariable(Variable v) {
		if (!checkVariable(v)) {
			throw new SemanticException("Duplicate Field: " + v.toString());
		}
	
		variables.add(v);
		System.out.println("Added Variable " + v.toString());
		System.out.println("Variables List: " + variables.toString());
	}
	
	private boolean checkForOverloading(Method m) {
		for (Method method : this.methods) {
			if (m.equals(method)) {
				Collections.sort(method.parameters);
				Collections.sort(m.parameters);
				if (m.parameters.equals(method.parameters)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkVariable(Variable v) {
		if (variables.contains(v)) return false;
		return true;
	}

}
