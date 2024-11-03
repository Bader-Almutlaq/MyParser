
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
      DEF IDENTIFIER LPAREN Args RPAREN EQUAL Expression
    ;

Args:
      IDENTIFIER COMMA Args
    | IDENTIFIER
    | /* empty */
    ;

Expression:
    INTEGER
  | IDENTIFIER
  | IF Expression OP Expression THEN Expression ELSE Expression
  | REPEAT Expression UNTIL Expression
  | Expression PLUS Expression
  | Expression MINUS Expression
  | Expression MULTIPLY Expression
  | Expression DIVIDE Expression
  | IDENTIFIER LPAREN ArgList RPAREN
  ;

ArgList:
      Expression COMMA ArgList
    | Expression
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