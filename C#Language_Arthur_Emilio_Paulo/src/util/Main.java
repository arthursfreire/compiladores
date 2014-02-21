package util;

import java.io.FileReader;

import semantica.SemanticAnalysis;
import java_cup.runtime.Symbol;

public class Main {
	
	private static final String FILE = "./files/entrada1.txt";
	private static final boolean SHOW_ASSEMBLY = false;
	
	public static void main (String[] args) {
		try {
			LexicalAnalysis scanner = new LexicalAnalysis( new java.io.FileReader(FILE) );
			parser p = new parser(scanner);
			Symbol parseResult = p.parse();
			if (parseResult.toString().equals("#0"))
				System.out.println("File " + FILE + " successfully compiled");
			else
				System.out.println(parseResult);
			
			if (SHOW_ASSEMBLY) {
				System.out.println("Generated Assembly: ");
				System.out.println(SemanticAnalysis.getInstance().assembly);
			}
		} catch (Exception e) {	
			System.err.println(e);
		}
	}
}

