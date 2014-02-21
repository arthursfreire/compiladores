package semantica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SemanticAnalysis {
	
	private static SemanticAnalysis instance;
	private List<Method> methods = new ArrayList<Method>();
	private List<Variable> variables  = new ArrayList<Variable>();
	
	public String assembly = "";
	
	public synchronized static SemanticAnalysis getInstance() {
		if (instance == null) {
			instance = new SemanticAnalysis();
		}
		return instance;
	}
	
	public void addMethod(Method m) throws SemanticException {
		if (!checkForOverloading(m)) {
			throw new SemanticException("Duplicate Method Declaration: " + m.toString());
		}
		
		methods.add(m);
	}
	
	public void addVariable(Variable v) {
		if (!checkVariable(v)) {
			throw new SemanticException("Duplicate Field: " + v.toString());
		}
	
		variables.add(v);
	}
	
	private boolean checkForOverloading(Method m) {
		for (Method method : this.methods) {
			if (m.equals(method)) {
				Collections.sort(method.parameters);
				Collections.sort(m.parameters);
				if (m.parameters.equals(method.parameters)) {
					return false;
				}
			} else {
				if (m.name.equals(method.name)) {
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
	
	public void toAssembly() {
		int line = 100;
		this.assembly += line + ": LD SP, 100000\n";
		int reg = 1;
		
		for (Variable v : this.variables) {
			line += 8;
			this.assembly += line + ": ST " + v.name + ", " + v.value + "\n";
		}
		
		for (Method m : this.methods) {
			line += 20;
			this.assembly += line + ": " + m.name + "\n";
			if (m.items != null) {
				for (Variable vm : m.items.variables) {
					line += 8;
					this.assembly += line + ": ST " + vm.name + ", " + vm.value + "\n";
				}
				for (Statement stm : m.items.statements) {
					if (stm instanceof IfStatement) {
						
						IfStatement ifStm = (IfStatement) stm;
						line += 20;
						
						
						
						String[] ifExp = ifStm.expression.value.split(" ");
						this.assembly += line + ": LD R" + reg++ + ", " + ifExp[0] + "\n";
						
						line += 8;
						this.assembly += line + ": LD R" + reg++ + ", " + ifExp[2] + "\n";
						
						line += 8;
						this.assembly += line + ": SUB R" + (reg - 1) + ", R" + (reg - 1) + ", R" + reg + "\n";
						
						line += 8;
						switch (ifExp[1]) {
							case "<":
								this.assembly += line + ": BLTZ R" + (reg - 1) + ", 400\n";
								break;
							case ">":
								this.assembly += line + ": BGTZ R" + (reg - 1) + ", 400\n";
								break;
							case "<=":
								this.assembly += line + ": BLTEQZ R" + (reg - 1) + ", 400\n";
								break;
							case ">=":
								this.assembly += line + ": BGTEQZ R" + (reg - 1) + ", 400\n";
								break;
															
						}
						
						if (ifStm.elseItems.size() > 0) {
							MethodItems mElse = ifStm.elseItems.get(0);
							
							for (int i = 0; i < mElse.variables.size(); i++) {
								if (i == 0) {
									line += 20;
								} else {
									line += 8;
								}
								this.assembly += line + ": ST " + mElse.variables.get(i).name + ", " + mElse.variables.get(i).value + "\n";
							}
							
						}
						
						if (ifStm.ifItems.size() > 0) {
							MethodItems mif = ifStm.ifItems.get(0);
							
							for (int i = 0; i < mif.variables.size(); i++) {
								if (i == 0) {
									line = 400;
								} else {
									line += 8;
								}
								this.assembly += line + ": ST " + mif.variables.get(i).name + ", " + mif.variables.get(i).value + "\n";
							}
							
						}
							
					}
				}
			}
			
		}
		
	}

}
