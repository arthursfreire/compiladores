package semantica;

import java.util.ArrayList;
import java.util.List;

public class IfStatement extends Statement {
	
	public Expression expression;
	public List<MethodItems> ifItems;
	public List<MethodItems> elseItems;
	
	public IfStatement() {
		this.ifItems = new ArrayList<MethodItems>();
		this.elseItems = new ArrayList<MethodItems>();
	}
	
	public IfStatement(Expression exp) {
		this.expression = exp;
		this.ifItems = new ArrayList<MethodItems>();
		this.elseItems = new ArrayList<MethodItems>();
	}
	
	@Override
	public String toString() {
		return "Statements of IfStatement: " + this.ifItems.toString();
	}
	
}
