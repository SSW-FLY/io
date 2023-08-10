grammar Arithmetic;

prog : stat+;

stat : expr                 # printExpr
     | ID '=' expr          # assign
     ;

expr : expr op=(MUL|DIV) expr  # mulDiv
     | expr op=(ADD|SUB) expr  # addSub
     | INT                     # int
     | FLOAT                   # float
     | '(' expr ')'            # bracket
     ;

MUL : '*' ;

DIV : '/' ;

ADD : '+' ;

SUB : '-' ;


ID : [a-zA-Z]+ ;

INT : [0-9]+ ;

FLOAT : DIGIT+ '.' DIGIT*
      | '.' + DIGIT
      ;
fragment
DIGIT:[0-9];

WS  : [ \t\r\n]+ -> skip ;    // toss out whitespace
