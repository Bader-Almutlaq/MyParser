%%

%class Lexical
%type Object
%column
%line

%{
    public final static short DEF = 257;
    public final static short IF = 258;
    public final static short THEN = 259;
    public final static short ELSE = 260;
    public final static short REPEAT = 261;
    public final static short UNTIL = 262;
    public final static short IDENTIFIER = 263;
    public final static short INTEGER = 264;
    public final static short LPAREN = 265;
    public final static short RPAREN = 266;
    public final static short SEMICOLON = 267;
    public final static short COMMA = 268;
    public final static short EQUAL = 269;
    public final static short EQUALS = 270;
    public final static short GREATER = 271;
    public final static short LESS = 272;
    public final static short GE = 273;
    public final static short LE = 274;
    public final static short NE = 275;
    public final static short PLUS = 276;
    public final static short MINUS = 277;
    public final static short MULTIPLY = 278;
    public final static short DIVIDE = 279;
%}


%% 

def { return DEF; }
if { return IF; }
then { return THEN; }
else { return ELSE; }
repeat { return REPEAT; }
until { return UNTIL; }
"(" { return LPAREN; }
")" { return RPAREN; }
";" { return SEMICOLON; }
"," { return COMMA; }
"=" { return EQUAL; }
"==" { return EQUALS; }
">" { return GREATER; }
"<" { return LESS; }
">=" { return GE; }
"<=" { return LE; }
"!=" { return NE; }
"+" { return PLUS; }
"-" { return MINUS; }
"*" { return MULTIPLY; }
"/" { return DIVIDE; }
[0-9]+ { return INTEGER; }
[_a-zA-Z][_a-zA-Z0-9]* { return IDENTIFIER; }

[ \t\n]+ { /* ignore whitespace */ }

. {
    System.err.println("Unrecognized character at line " + (yyline + 1) + ", column " + (yycolumn + 1) + ": " + yytext());
    System.exit(1);
}

