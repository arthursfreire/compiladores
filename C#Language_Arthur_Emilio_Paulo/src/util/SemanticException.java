package util;



public class SemanticException extends IllegalArgumentException {
	
	public SemanticException(String message) {
		super("Error at " + LexicalAnalysis.currentLine + "\n" + message);
	}

}
