package semantica;

import util.LexicalAnalysis;

public class SemanticException extends IllegalArgumentException {
	
	public SemanticException(String message) {
		super("Error near " + LexicalAnalysis.currentLine + "\n" + message);
	}

}
