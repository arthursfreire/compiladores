package projeto;

import java_cup.runtime.*;

%%

%class LexicalAnalysis
%public
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
   	   
	private void mensagemErro() {
		throw new RuntimeException("Erro Lexico na linha " + yyline + " e coluna " + yycolumn + " . Nao existe padrao para o lexema: " + yytext());
	}
   	   
%}

/* -------------------------------------------------- *
 *                       Numeric                      *
 * -------------------------------------------------- */
Digit = [0-9]
HexDigit = {Digit} | [a-f] | [A-F]

IntegerTypeSuffix = "U" | "u" | "L" | "l" | "UL" | "Ul" | "uL" | "ul" | "LU" | "Lu" | "lU" | "lu"
IntegerLiteral = ( {Digit} ({Digit})* ({IntegerTypeSuffix})?) |
		  		 ( "0" ("x" | "X") {HexDigit} ({HexDigit})* ({IntegerTypeSuffix})?)
		  
RealTypeSuffix = "F" | "f" | "D" | "d" | "M" | "m"
ExponentPart = (("e" | "E") ("+" | "-"))? {Digit}+		  
RealLiteral = ({Digit})* "." ({Digit})+ ({ExponentPart})? ({RealTypeSuffix})? |
	   		  ({Digit})+ ({ExponentPart}) ({RealTypeSuffix})? |
			  ({Digit})+ ({RealTypeSuffix})
			  
/* -------------------------------------------------- *
 *                        Text                        *
 * -------------------------------------------------- */

LowerCase = [a-z]
UpperCase = [A-Z]
Letter = {LowerCase} | {UpperCase}

Whitespace = [ \r\n\t\f]

NewLine = "\r" | "\n" | "\r\n"
InputCharacter = [^\r\n]

Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {NewLine}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* missing CharacterLiteral */

/* fix StringLiteral*/
StringLiteral = ("'" ([^'])* } "'") | 
      	 		( "\"" ([^\"])* "\"" )
      	 		
/* -------------------------------------------------- *
 *                   Identifier                       *
 * -------------------------------------------------- */      	 		
      	 		
/* fix Identifier */
Identifier = ({Letter} | "_") ({Letter} | {Digit} | "_")*

%%

<YYINITIAL> {

	/* keywords */
	"abstract"			{ return symbol(sym.ABSTRACT); }
	"as"				{ return symbol(sym.AS); }
	"base"				{ return symbol(sym.BASE); }
	"bool"				{ return symbol(sym.BOOL); }
	"break"				{ return symbol(sym.BREAK); }
	"byte"				{ return symbol(sym.BYTE); }
	"case"				{ return symbol(sym.CASE); }
	"catch"				{ return symbol(sym.CATCH); }
	"char"				{ return symbol(sym.CHAR); }
	"checked"			{ return symbol(sym.CHECKED); }
	"class"				{ return symbol(sym.CLASS); }
	"const"				{ return symbol(sym.CONST); }
	"continue"			{ return symbol(sym.CONTINUE); }
	"decimal"			{ return symbol(sym.DECIMAL); }
	"default"			{ return symbol(sym.DEFAULT); }
	"delegate"			{ return symbol(sym.DELEGATE); }
	"do"				{ return symbol(sym.DO); }
	"double"			{ return symbol(sym.DOUBLE); }
	"else"				{ return symbol(sym.ELSE); }
	"enum"				{ return symbol(sym.ENUM); }
	"event"				{ return symbol(sym.EVENT); }
	"explicit"			{ return symbol(sym.EXPLICIT); }
	"extern"			{ return symbol(sym.EXTERN); }
	"finally"			{ return symbol(sym.FINALLY); }
	"fixed"				{ return symbol(sym.FIXED); }
	"float"				{ return symbol(sym.FLOAT); }
	"for"				{ return symbol(sym.FOR); }
	"foreach"			{ return symbol(sym.FOREACH); }
	"goto"				{ return symbol(sym.GOTO); }
	"if"				{ return symbol(sym.IF); }
	"implicit"			{ return symbol(sym.IMPLICIT); }
	"in"				{ return symbol(sym.IN); }
	"int"				{ return symbol(sym.INT); }
	"interface"			{ return symbol(sym.INTERFACE); }
	"internal"			{ return symbol(sym.INTERNAL); }
	"is"				{ return symbol(sym.IS); }
	"lock"				{ return symbol(sym.LOCK); }
	"long"				{ return symbol(sym.LONG); }
	"namespace"			{ return symbol(sym.NAMESPACE); }
	"new"				{ return symbol(sym.NEW); }
	"object"			{ return symbol(sym.OBJECT); }
	"operator"			{ return symbol(sym.OPERATOR); }
	"out"				{ return symbol(sym.OUT); }
	"override"			{ return symbol(sym.OVERRIDE); }
	"params"			{ return symbol(sym.PARAMS); }
	"private"			{ return symbol(sym.PRIVATE); }
	"protected"			{ return symbol(sym.PROTECTED); }
	"public"			{ return symbol(sym.PUBLIC); }
	"readonly"			{ return symbol(sym.READONLY); }
	"ref"				{ return symbol(sym.REF); }
	"return"			{ return symbol(sym.RETURN); }
	"sbyte"				{ return symbol(sym.SBYTE); }
	"sealed"			{ return symbol(sym.SEALED); }
	"short"				{ return symbol(sym.SHORT); }
	"sizeof"			{ return symbol(sym.SIZEOF); }
	"stackalloc"		{ return symbol(sym.STACKALLOC); }
	"static"			{ return symbol(sym.STATIC); }
	"string"			{ return symbol(sym.STRING); }
	"struct"			{ return symbol(sym.STRUCT); }
	"switch"			{ return symbol(sym.SWITCH); }
	"this"				{ return symbol(sym.THIS); }
	"throw"				{ return symbol(sym.THROW); }
	"try"				{ return symbol(sym.TRY); }
	"typeof"			{ return symbol(sym.TYPEOF); }
	"uint"				{ return symbol(sym.UINT); }
	"ulong"				{ return symbol(sym.ULONG); }
	"unchecked"			{ return symbol(sym.UNCHECKED); }
	"unsafe"			{ return symbol(sym.UNSAFE); }
	"ushort"			{ return symbol(sym.USHORT); }
	"using"				{ return symbol(sym.USING); }
	"virtual"			{ return symbol(sym.VIRTUAL); }
	"void"				{ return symbol(sym.VOID); }
	"volatile"			{ return symbol(sym.VOLATILE); }
	"while"				{ return symbol(sym.WHILE); }
	
	/* boolean literals */
 	"true"                         { return symbol(sym.TRUE); }
	"false"                        { return symbol(sym.FALSE); }
	
	/* null literal */
  	"null"                         { return symbol(sym.NULL); }
  	
  	/* get and set */
  	"get"						   { return symbol(sym.GET); }
  	"set"						   { return symbol(sym.SET); }
  	
  	/* add and remove */
  	"add"						   { return symbol(sym.ADD); }
  	"remove"					   { return symbol(sym.REMOVE); }
  	
  	/* global-attribute-section */
  	"assembly"					   { return symbol(sym.ASSEMBLY); }
  	
  	/* attribute-target */
  	"field"						   { return symbol(sym.FIELD); }
  	"event"						   { return symbol(sym.EVENT); }
  	"method"					   { return symbol(sym.METHOD); }
  	"module"					   { return symbol(sym.MODULE); }
  	"param"						   { return symbol(sym.PARAM); }
  	"property"					   { return symbol(sym.PROPERTY); }
  	"type"					   	   { return symbol(sym.TYPE); }

  	
  	
  	/* operators and punctuators */
  	"="                            { return symbol(sym.EQ); }
	">"                            { return symbol(sym.GT); }
  	"<"                            { return symbol(sym.LT); }
  	"!"                            { return symbol(sym.NOT); }
  	"~"                            { return symbol(sym.COMP); }
  	"?"                            { return symbol(sym.QUESTION); }
  	"=="                           { return symbol(sym.EQEQ); }
  	"<="                           { return symbol(sym.LTEQ); }
  	">="                           { return symbol(sym.GTEQ); }
  	"!="                           { return symbol(sym.NOTEQ); }
  	"&&"                           { return symbol(sym.ANDAND); }
  	"||"                           { return symbol(sym.OROR); }
  	"++"                           { return symbol(sym.PLUSPLUS); }
  	"--"                           { return symbol(sym.MINUSMINUS); }
  	"+"                            { return symbol(sym.PLUS); }
  	"-"                            { return symbol(sym.MINUS); }
  	"*"                            { return symbol(sym.MULT); }
  	"/"                            { return symbol(sym.DIV); }
  	"&"                            { return symbol(sym.AND); }
  	"|"                            { return symbol(sym.OR); }
  	"^"                            { return symbol(sym.XOR); }
  	"%"                            { return symbol(sym.MOD); }
  	"<<"                           { return symbol(sym.LSHIFT); }
  	">>"                           { return symbol(sym.RSHIFT); }
  	"+="                           { return symbol(sym.PLUSEQ); }
  	"-="                           { return symbol(sym.MINUSEQ); }
  	"*="                           { return symbol(sym.MULTEQ); }
  	"/="                           { return symbol(sym.DIVEQ); }
  	"&="                           { return symbol(sym.ANDEQ); }
  	"|="                           { return symbol(sym.OREQ); }
  	"^="                           { return symbol(sym.XOREQ); }
  	"%="                           { return symbol(sym.MODEQ); }
  	"<<="                          { return symbol(sym.LSHIFTEQ); }
  	">>="                          { return symbol(sym.RSHIFTEQ); }
	"("                            { return symbol(sym.LPAREN); }
  	")"                            { return symbol(sym.RPAREN); }
  	"{"                            { return symbol(sym.LBRACE); }
  	"}"                            { return symbol(sym.RBRACE); }
  	"["                            { return symbol(sym.LBRACK); }
  	"]"                            { return symbol(sym.RBRACK); }
  	":"                            { return symbol(sym.COLON); }
  	";"                            { return symbol(sym.SEMICOLON); }
  	","                            { return symbol(sym.COMMA); }
  	"."                            { return symbol(sym.DOT); }
  	
  	/* others */
  	{Identifier}				   { return symbol(sym.IDENTIFIER, yytext()); }
  	{IntegerLiteral}			   { return symbol(sym.INTEGER_LITERAL, yytext()); }
  	{RealLiteral}				   { return symbol(sym.REAL_LITERAL, yytext()); }
  	{StringLiteral}				   { return symbol(sym.STRING_LITERAL, yytext()); }
  	{Whitespace}				   { }
  	{Comment}					   { }
 	
}

<<EOF>>							{ return symbol(sym.EOF); }