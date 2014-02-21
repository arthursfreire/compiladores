package semantica;

public class ReturnStatement extends Statement {
	
	public Expression value;
		
	public ReturnStatement(Expression value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ReturnStatement: " + value.toString();
	}

}
