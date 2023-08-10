grammar Arithmetic;

prog : stat+;

stat : expr
     | ID '=' expr
     ;

expr : expr op=(MUL|DIV) expr
     | expr op=(ADD|SUB) expr
     | INT
     | FLOAT
     | '(' expr ')'
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
