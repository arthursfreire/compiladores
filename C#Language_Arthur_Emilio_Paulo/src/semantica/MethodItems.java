package semantica;

import java.util.ArrayList;
import java.util.List;

public class MethodItems {
	
	public final int VARIABLE = 1;
	public final int STATEMENT = 2;
	public List<Integer> addAux;
	
	public Method method;
	public List<Variable> variables;
	public List<Statement> statements;
	
	public MethodItems() {
		this.addAux = new ArrayList<Integer>();
		this.variables = new ArrayList<Variable>();
		this.statements = new ArrayList<Statement>();
	}
		
	public boolean addItem(Object obj) {
		if (obj instanceof Variable) {
			return addVariable((Variable) obj);
		}
		
		return addStatement((Statement) obj);
	}
	
	public void setMethod(Method m) throws SemanticException {
		if (!(checkParametersAndVariables(m))) {
			throw new SemanticException("Parameter and Variable names are duplicated: " + m.toString());
		}
		this.method = m;
		m.items = this;
	}
	
	private boolean checkParametersAndVariables(Method m) {
		for (Parameter p : m.parameters) {
			for (Variable v : this.variables) {
				if (v.name.equals(p.name)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean addVariable(Variable v) throws SemanticException {
		if (!checkVariable(v)) {
			throw new SemanticException("Duplicate Local Field: " + v.toString());
		}
		this.addAux.add(VARIABLE);
		return this.variables.add(v);
	}
	
	private boolean checkVariable(Variable v) {
		if (this.variables.contains(v)) return false;
		return true;
	}
	
	private boolean addStatement(Statement s) {
		this.addAux.add(STATEMENT);
		return this.statements.add(s);
	}
	
	@Override
	public String toString() {
		return "Aux: " + this.addAux.toString() + "\n" +
			   "Variables: " + this.variables.toString() + "\n" +
			   "Statements: " + this.statements.toString();
	}

}
