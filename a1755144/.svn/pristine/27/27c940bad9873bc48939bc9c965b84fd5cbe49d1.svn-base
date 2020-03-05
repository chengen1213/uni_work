#ifndef TOKENISER_H
#define TOKENISER_H

// tokeniser for the tokeniser workshops
// the recognised keywords and symbols will
// change to suit future workshops and assignments

#include <string>

// ***** BNF *****
//
// *** SYNTAX
// * literals inside ' '
// * grouping ( )
// * ASCII ranges -
// * alternatives |
// * 0 or 1 [ ]
// * 0 or more *
// * 1 or more +
//
// *** TOKENS
// * digit  ::= '0'-'9'
// * digit19  ::= '1'-'9'
// * int ::= '0' | (digit19 digit*)
// * double ::= int ['.' digit*] [('e'|'E') ['+'|'-'] int]
// * printable ::= ' '-'~'
// * string ::= '"' printable* '"'
// * letter ::= 'a'-'z'|'A'-'Z'
// * identifier ::= (letter|'_') (letter|digit|'_')*
//
// * keyword ::= 'if', 'then', 'else', etc.
//
// * symbol ::= ';', '+', '-', etc.
//
// * inoneline ::= printable|'\t'|'\r'
// * oneline ::= '/' '/' inoneline* '\n'
// 
// * inmultiline ::= inoneline|'\n'
// * multiline ::= '/*' inmultiline* '*/'
//
// *** OTHER RULES
// * a multiline comment finishes at the first '*/' after '/*'
// * between tokens, space, tab, carriage return and newline are ignored
// * between tokens any other character that cannot start a token results in a BAD token
// * only printables, tabs, carriage returns and newline are permitted, all other characters are bad
// * inside a string, oneline or multiline comment, EOI or a bad character results in a BAD token
// * if a BAD token has been returned all future tokens returned are EOI
// * once the end of input is reached all future tokens returned are EOI
//


// The kinds of token that are recognised
enum TokenKind
{
    IDENTIFIER,                 // an identifier
    KEYWORD,                    // a keyword
    SYMBOL,                     // an operator or other punctuation
    INT,                        // an int
    DOUBLE,                     // a double
    ONELINE_COMMENT,            // // comment
    MULTILINE_COMMENT,          // /* comment
    BAD,                        // a legal token cannot start with the next character
    EOI,                        // end of input reached

                                // the specific keywords recognised by this tokeniser
    IF_KEYWORD,                 // if
    WHILE_KEYWORD,              // while
    ELSE_KEYWORD,               // else
    CLASS_KEYWORD,              // class
    INT_KEYWORD,                // int
    STRING_KEYWORD,             // string

                                // the specific symbols recognised by this tokeniser
    AT_SYMBOL,                  // '@'
    SEMI_SYMBOL,                // ';'
    COLON_SYMBOL,               // ':'
    NOT_SYMBOL,                 // '!'
    COMMA_SYMBOL,               // ','
    STOP_SYMBOL,                // '.'
    EQUALS_SYMBOL,              // '='
    LCB_SYMBOL,                 // '{'
    RCB_SYMBOL,                 // '}'
    LRB_SYMBOL,                 // '('
    RRB_SYMBOL,                 // ')'
    LSB_SYMBOL,                 // '['
    RSB_SYMBOL,                 // ']'

    OOPS                        // a stand in null value for TokenKind
} ;

// A Token object
struct Token
{
    TokenKind kind ;            // the kind of token, IDENTIFIER, KEYWORD, SYMBOL, INT, DOUBLE, BAD, EOI
    TokenKind token ;           // if KEYWORD or SYMBOL then the actual token, eg PLUS, otherwise the token kind
    std::string spelling ;      // the actual characters,
                                // if BAD or EOI the value is ""
                                // if a oneline COMMENT the '//' and '\n' are removed
                                // if a multiline COMMENT the '/*' and '*/' are removed
    int ivalue ;                // if INT then its value otherwise 0
    double dvalue ;             // if DOUBLE then its value otherwise 0.0
    int start_line ;            // line number the token started on - first line is number 1
    int start_column ;          // line column the token started on - first column is number 1
};

extern int read_char() ;        // all input must be read using this function

                                // the tokeniser will initialise itself on its first call
extern Token next_token() ;     // return the next token

                                // return a string representation of a TokenKind
extern std::string token_to_string(TokenKind k) ;
                                // return the kind of token k is, ie KEYWORD, SYMBOL, ...
extern TokenKind token_to_token(TokenKind k,TokenKind oops) ;
                                // which TokenKind is uniquely represented by the string
extern TokenKind string_to_token(std::string s,TokenKind oops) ;


#endif //TOKENISER_H
