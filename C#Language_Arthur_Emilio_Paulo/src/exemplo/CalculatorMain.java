package exemplo;

import java.io.FileReader;

public class CalculatorMain {
	public static void main (String[] args) {
		try {
			LexicalAnalysisCalculator scanner = new LexicalAnalysisCalculator( new java.io.FileReader(args[0]) );
			parser p = new parser(scanner);
			System.out.println(p.parse());
		} catch (Exception e) {	
			System.out.println(e);
		}
	}
}

