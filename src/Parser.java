package src;
//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";

public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short DEF=257;
public final static short IF=258;
public final static short THEN=259;
public final static short ELSE=260;
public final static short REPEAT=261;
public final static short UNTIL=262;
public final static short IDENTIFIER=263;
public final static short INTEGER=264;
public final static short LPAREN=265;
public final static short RPAREN=266;
public final static short SEMICOLON=267;
public final static short COMMA=268;
public final static short EQUAL=269;
public final static short EQUALS=270;
public final static short GREATER=271;
public final static short LESS=272;
public final static short GE=273;
public final static short LE=274;
public final static short NE=275;
public final static short PLUS=276;
public final static short MINUS=277;
public final static short MULTIPLY=278;
public final static short DIVIDE=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    2,    2,    2,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    5,    5,    5,    4,    4,
    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    3,    1,    7,    3,    1,    0,    1,    1,    8,    4,
    3,    3,    3,    3,    4,    3,    1,    0,    1,    1,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,    4,    0,    0,    0,    0,    7,    0,    0,    0,
    0,    0,    0,    0,    0,   19,   20,   21,   22,   23,
   24,    0,    0,    0,    0,    0,    0,   13,   14,    0,
    0,    0,   15,    0,   16,    0,    0,    0,
};
final static short yydgoto[] = {                          2,
    3,    9,   34,   32,   35,
};
final static short yysindex[] = {                      -255,
 -253,    0, -256, -244, -255, -231,    0, -238, -254, -231,
 -236,    0, -227, -227, -227, -223,    0, -225, -209, -233,
 -227, -227, -227, -227, -227,    0,    0,    0,    0,    0,
    0, -227, -227, -229, -226, -271, -271,    0,    0, -259,
 -225, -227,    0, -227,    0, -251, -227, -225,
};
final static short yyrindex[] = {                         0,
    0,    0,   55,    0,    0, -210,    0, -208,    0, -210,
    0,    0,    0,    0,    0,    1,    0,    3,    0,    0,
 -207,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -195,    0,   22,   41,    0,    0,    0,
   60, -207,    0,    0,    0,    0,    0,   77,
};
final static short yygindex[] = {                        52,
    0,   62,   -9,    0,   31,
};
final static int YYTABLESIZE=352;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         44,
    8,    1,    3,   18,   19,   20,   24,   25,   47,    4,
    5,   11,   36,   37,   38,   39,   22,   23,   24,   25,
    6,   11,   40,   41,   22,   23,   24,   25,   33,   10,
   14,    8,   13,   15,   46,   16,   17,   48,   42,   43,
   12,   21,   22,   23,   24,   25,   22,   23,   24,   25,
   22,   23,   24,   25,    2,    6,    7,    5,   18,   10,
   26,   27,   28,   29,   30,   31,   22,   23,   24,   25,
   17,   12,   45,    0,    0,    0,    9,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    8,
    8,    0,    8,    0,    0,    0,    8,    8,    8,    3,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
   11,   11,    0,   11,    0,    0,    0,   11,   11,   11,
    0,   11,   11,   11,   11,   11,   11,   11,   11,   12,
   12,    0,   12,    0,    0,    0,   12,   12,   12,    0,
   12,   12,   12,   12,   12,   12,   12,   12,   10,   10,
    0,   10,    0,    0,    0,   10,   10,   10,    0,   10,
   10,   10,   10,   10,   10,    9,    9,    0,    9,    0,
    0,    0,    9,    9,    9,    0,    9,    9,    9,    9,
    9,    9,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        259,
    0,  257,    0,   13,   14,   15,  278,  279,  260,  263,
  267,  266,   22,   23,   24,   25,  276,  277,  278,  279,
  265,    0,   32,   33,  276,  277,  278,  279,  262,  268,
  258,  263,  269,  261,   44,  263,  264,   47,  268,  266,
    0,  265,  276,  277,  278,  279,  276,  277,  278,  279,
  276,  277,  278,  279,    0,  266,    5,  266,  266,    0,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  266,   10,   42,   -1,   -1,   -1,    0,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
  260,   -1,  262,   -1,   -1,   -1,  266,  267,  268,  267,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  259,  260,   -1,  262,   -1,   -1,   -1,  266,  267,  268,
   -1,  270,  271,  272,  273,  274,  275,  276,  277,  259,
  260,   -1,  262,   -1,   -1,   -1,  266,  267,  268,   -1,
  270,  271,  272,  273,  274,  275,  276,  277,  259,  260,
   -1,  262,   -1,   -1,   -1,  266,  267,  268,   -1,  270,
  271,  272,  273,  274,  275,  259,  260,   -1,  262,   -1,
   -1,   -1,  266,  267,  268,   -1,  270,  271,  272,  273,
  274,  275,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"DEF","IF","THEN","ELSE","REPEAT","UNTIL","IDENTIFIER","INTEGER",
"LPAREN","RPAREN","SEMICOLON","COMMA","EQUAL","EQUALS","GREATER","LESS","GE",
"LE","NE","PLUS","MINUS","MULTIPLY","DIVIDE",
};
final static String yyrule[] = {
"$accept : Program",
"Program : Declaration SEMICOLON Program",
"Program : Declaration",
"Declaration : DEF IDENTIFIER LPAREN Args RPAREN EQUAL Expression",
"Args : IDENTIFIER COMMA Args",
"Args : IDENTIFIER",
"Args :",
"Expression : INTEGER",
"Expression : IDENTIFIER",
"Expression : IF Expression OP Expression THEN Expression ELSE Expression",
"Expression : REPEAT Expression UNTIL Expression",
"Expression : Expression PLUS Expression",
"Expression : Expression MINUS Expression",
"Expression : Expression MULTIPLY Expression",
"Expression : Expression DIVIDE Expression",
"Expression : IDENTIFIER LPAREN ArgList RPAREN",
"ArgList : Expression COMMA ArgList",
"ArgList : Expression",
"ArgList :",
"OP : EQUALS",
"OP : GREATER",
"OP : LESS",
"OP : GE",
"OP : LE",
"OP : NE",
};

//#line 54 "parser.y"
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
//#line 317 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
