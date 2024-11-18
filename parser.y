
%token DEF IF THEN ELSE REPEAT UNTIL IDENTIFIER INTEGER LPAREN RPAREN SEMICOLON COMMA EQUAL // Defining the tokens
%token EQUALS GREATER LESS GE LE NE PLUS MINUS MULTIPLY DIVIDE

%start Program // Start symbol
%left PLUS MINUS // Associativity for addition and subtraction
%left MULTIPLY DIVIDE // Associativity for multiplication and division

%%
// Grammar rules

Program:
      Declaration SEMICOLON Program
    | Declaration
    ;

Declaration:
      DEF IDENTIFIER LPAREN Args RPAREN EQUAL Expression_0
    ;

Args:
      IDENTIFIER COMMA Args
    | IDENTIFIER
    ;

Expression_0:
    IF Expression_0 OP Expression_0 THEN Expression_0 ELSE Expression_0
  | REPEAT Expression_0 UNTIL Expression_0
  | Expression_1
  ;

Expression_1:
    Expression_1 PLUS Expression_2
  | Expression_1 MINUS Expression_2
  | Expression_2
  ;

Expression_2:
    Expression_2 MULTIPLY Expression_3
  | Expression_2 DIVIDE Expression_3
  | Expression_3
  ;

Expression_3:
    INTEGER
  | IDENTIFIER
  | IDENTIFIER LPAREN ArgExpressions RPAREN
  ;

ArgExpressions:
    Expression_0 COMMA ArgExpressions
  | Expression_0
  ;

  
ArgList:
      IDENTIFIER COMMA ArgList
    | IDENTIFIER
    | /* empty */
    ;
OP:
  EQUALS 
  | GREATER
  | LESS 
  | GE 
  | LE 
  | NE
  ;

%%
Lexical lexer;
  
public Parser(Lexical lexer) {
    this.lexer = lexer; 
}

int yylex() {
    try {
      int token = (Short) lexer.yylex(); // Get token from lexer

      // Print information about the current token
      System.out.println("Token: " + token + ", Text: " + lexer.yytext() +
          ", Line: " + lexer.yyline +
          ", Column: " + lexer.yycolumn);

      return token;
    } catch (Exception e) {
      System.err.println("Lexer error at line " + lexer.yyline + ", column " +
          lexer.yycolumn + ": " + e.getMessage());
      return -1; // Indicate an error
    }
  }

  // Error handling function
  void yyerror(String message) {
    // Access current line and column from the lexer
    int line = lexer.yyline;
    int column = lexer.yycolumn;

    System.err.println("Parse error at line " + line + ", column " + column + ": " + message);

    // Provide additional details if needed
    System.err.println("Error token: " + lexer.yytext());
  }
