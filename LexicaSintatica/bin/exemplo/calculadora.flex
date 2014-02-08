package exemplo;

import java_cup.runtime.*;

%%

%class LexicalAnalysisCalculator
%column
%line
%cup

%{
	   
	   private Symbol symbol(int type) {
		   return new Symbol(type, yyline, yycolumn);
   	   }
   	
 	   private Symbol symbol(int type, Object val) {
		   return new Symbol(type, yyline, yycolumn, val);
   	   }
%}

%%
/*Aqui estao definidos os possiveis tokens que podem ser encontrados numa calculadora simples*/
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
"-" { return symbol(sym.MINUS); }
"+" { return symbol(sym.PLUS); }
"/" { return symbol(sym.DIV); }
"*" { return symbol(sym.TIMES); }
[0-9]+ { return symbol(sym.NUMBER); }
[ \r\n\t\f] { /*nao faz nada*/ }
[a-z] { return symbol(sym.STRING); }
<<EOF>> { return symbol(sym.EOF); }