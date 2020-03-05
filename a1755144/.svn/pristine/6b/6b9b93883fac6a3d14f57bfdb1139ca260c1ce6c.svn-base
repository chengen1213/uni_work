// a skeleton implementation of a tokeniser

#include "tokeniser.h"
#include <iostream>
#include <ctype.h>

// to shorten the code
using namespace std;

////////////////////////////////////////////////////////////////////////

// the following externally defined function must be used to lookup keywords
// it returns IDENTIFIER if the string passed in is not a keyword
extern TokenKind lookup_keyword(string);

// the current input character, initiliased to ' ' which we ignore
// it is an int so that the EOF marker is not confused with a legal character
static int ch = ' ';

// the current line number and column, initialised to line 1 column 0
static int line_num = 1;
static int column_num = 0;

// the line number and column for the first character in the current token
static int start_line = 0;
static int start_column = 0;

// read next character if not at the end of input
// and update the line and column numbers
static void nextch()
{
	extern int read_char();

	if (ch == EOF) return;

	if (ch == '\n')           // if last ch was newline ...
	{
		line_num++;            // increment line number
		column_num = 0;        // reset column number
	}

	ch = read_char();          // read the next character
	column_num++;              // increment the column number
}

////////////////////////////////////////////////////////////////////////

// Some useful builtin helper functions include, isalpha(), isdigit(), isalnum(), etc.
// isalpha returns true if passed a letter
// isdigit returns true if passed a digit
// isalnum returns true if passed a letter or a digit

// return true if c can start an identifier, ie it is a letter or '_'
static bool is_idstart(int c)
{
	return isalpha(c) || c == '_';
}

// return true if c can be inside an identifier, ie it is a letter, digit or '_'
static bool is_inid(int c)
{
	return isalnum(c) || c == '_';
}

// return true if c is a digit from '1' to '9'
static bool is_digit19(int c)
{
	return c >= '1' && c <= '9';
}

// return a token - including current line and column numbers
static Token new_token(TokenKind kind, string value)
{
	Token t = { kind,kind,value,0,0.0,start_line,start_column };
	return t;
}

////////////////////////////////////////////////////////////////////////


// Add your own helper functions here - if they will simplify writing next_token()

static Token symbol_token(TokenKind kind, TokenKind token, string value)
{
	Token t = { kind,token,value,0,0.0,start_line,start_column };
	return t;
}

// ****  You must modify the following function *****
// return the next token object by reading more of the input
Token next_token()
{
	//initialise the token object to save the information from previous char
	Token t = new_token(OOPS, "");
	// you must read input using the nextch() function
	// the last character read is in the static variable ch
	// always read one character past the end of the token being returned

	// this loop reads one character at a time until it reaches end of input
	while (ch != EOF)
	{
		start_line = line_num;                 // remember current position in case we find a token
		start_column = column_num;
		//handling cases that could be determined by single char
		switch (ch)                              // ch is always the next char to read
		{
		case ' ':                               // ignore space, tab, CR and LF
		case '\t':
		case '\r':
		case '\n':
			nextch();                          // read one more character and try again
			break;
			// add additional case labels here for characters that can start tokens
			// using helper functions to return an initialised token object would be a good idea
			// ...
			// symbols ';' | ':' | '!' | ',' | '.' | '=' | '{' | '}' | '(' | ')' | '[' | ']' | '@'
		case ';':
			nextch();
			t = symbol_token(SYMBOL, SEMI_SYMBOL, ";");
			return t;

		case ':':
			nextch();
			t = symbol_token(SYMBOL, COLON_SYMBOL, ":");
			return t;

		case '!':
			nextch();
			t = symbol_token(SYMBOL, NOT_SYMBOL, "!");
			return t;

		case ',':
			nextch();
			t = symbol_token(SYMBOL, COMMA_SYMBOL, ",");
			return t;

		case '.':
			nextch();
			t = symbol_token(SYMBOL, STOP_SYMBOL, ".");
			return t;

		case '=':
			nextch();
			t = symbol_token(SYMBOL, EQUALS_SYMBOL, "=");
			return t;

		case '{':
			nextch();
			t = symbol_token(SYMBOL, LCB_SYMBOL, "{");
			return t;

		case '}':
			nextch();
			t = symbol_token(SYMBOL, RCB_SYMBOL, "}");
			return t;

		case '(':
			nextch();
			t = symbol_token(SYMBOL, LRB_SYMBOL, "(");
			return t;

		case ')':
			nextch();
			t = symbol_token(SYMBOL, RRB_SYMBOL, ")");
			return t;

		case '[':
			nextch();
			t = symbol_token(SYMBOL, LSB_SYMBOL, "[");
			return t;

		case ']':
			nextch();
			t = symbol_token(SYMBOL, RSB_SYMBOL, "]");
			return t;

		case '@':
			nextch();
			t = symbol_token(SYMBOL, AT_SYMBOL, "@");
			return t;

		default:
		//the first char of the current token
			if(t.kind == OOPS)
			{
				//handling identifiers and keywords
				if(is_idstart(ch))
				{
					t.kind = IDENTIFIER;
					t.token = IDENTIFIER;
					t.spelling += ch;
					t.start_line = start_line;
					t.start_column = start_column;
					nextch();
					/*check the following char, if it can not be a part of an identifier, 
					check if it can be a keyword by calling the function "string_to_token",
					modify the token object return the current token immediately, 
					otherwise just append the current char to spelling.*/
					if(!is_inid(ch))
					{
						t.token = string_to_token(t.spelling,OOPS);
						if(t.token == OOPS)
						{
							t.token = IDENTIFIER;
						}
						else
						{
							t.kind = KEYWORD;
						}
						return t;
					}
					break;
				}
				//handling numbers
				if (is_digit19(ch))
				{
					t.kind = INT;
					t.token = INT;
					t.spelling += ch;
					t.start_line = start_line;
					t.start_column = start_column;
					nextch();
					/*check the following char, if that can not make the current char an integer or a double,
					just return the current token*/
					if(!isdigit(ch) && ch != '.')
					{
						t.ivalue = stoi(t.spelling);
						return t;
					}
					//the next char is '.', transfer the token to double
					if(ch == '.')
					{
							t.kind = DOUBLE;
							t.token = DOUBLE;
							t.spelling += ch;
							//find the next char again if it can not be a double anymore, return it
							nextch();
							if(ch != '0' && !is_digit19(ch))
							{
								t.ivalue = 0;
								t.dvalue = stod(t.spelling);
								return t;
							}
							break;
					}
					break;
				}
				//handling single '0' or double begins with zero
				if(ch == '0')
				{
					t.kind = INT;
					t.token = INT;
					t.spelling += ch;
					t.start_line = start_line;
					t.start_column = start_column;
					nextch();
					/*if the next char is '.', change to kind to double, 
					otherwise return token as integer zero*/
					if(ch == '.')
					{
						t.kind = DOUBLE;
						t.token = DOUBLE;
						t.spelling += ch;
						nextch();
						if(ch != '0' && !is_digit19(ch))
						{
							t.ivalue = 0;
							t.dvalue = stod(t.spelling);
							return t;
						}
						break;
					}
					return t;

				}
				//handling comments
				if(ch == '/')
				{
					int tempLine = start_line;
					int tempColumn = start_column;
					nextch();
					//one line comment
					if(ch == '/')
					{
						t.kind = ONELINE_COMMENT;
						t.token = ONELINE_COMMENT;
						t.start_line = start_line;
						t.start_column = start_column;
						//keep reading the next char until the change line symbol
						do
						{
							nextch();
							if(ch == EOF)
							{
								return new_token(BAD, "");
							}//append the current char to the comment content, except the change line symbol
							if(ch != '\n')
							{
								t.spelling += ch;
							}
						}
						while(ch != '\n');

						nextch();
						return t;
					}
					//multi line comment
					else if (ch == '*')
					{
						t.kind = MULTILINE_COMMENT;
						t.token = MULTILINE_COMMENT;
						t.start_line = start_line;
						t.start_column = start_column;
						bool redo = true;
						//keep reading the next char until the continuous '*/' symbol
						do
						{
							nextch();
							if(ch == EOF)
							{
								redo = false;
							}
							if(ch == '*')
							{
								nextch();
								if(ch == '/')
								{
									nextch();
									return t;
								}
								else
								{
									t.spelling += '*';
								}
							}
							t.spelling += ch;
						}
						while(redo);

						return new_token(BAD, "");
					}//once got the '/*' symbol and can not find the '*/' symbol, return bad
					else
					{
						ch = EOF;
						return new_token(BAD, "");
					}
				}
			}//conider the current token kind to deal with different kind of tokens
			else if (t.kind == IDENTIFIER && is_inid(ch))//case identifier
			{
				//append the current char and read next char
				t.spelling += ch;
				// test next char
				nextch();
				if(!is_inid(ch))
				{
					t.token = string_to_token(t.spelling,OOPS);
					if(t.token == OOPS)
					{
						t.token = IDENTIFIER;
					}
					else
					{
						t.kind = KEYWORD;
					}
					return t;
				}
				break;
			}
			else if (t.kind == INT && (isdigit(ch) || ch == '.'))//case integer
			{
				//append the current char and read next char
				t.spelling += ch;
				//test next char
				nextch();
				if(!isdigit(ch) && ch != '.')
				{
					t.ivalue = stoi(t.spelling);
					return t;
				}//change to double
				if(ch == '.')
				{
					t.kind = DOUBLE;
					t.token = DOUBLE;
					t.spelling += ch;
					nextch();
					if(ch != '0' && !is_digit19(ch))
					{
						t.ivalue = 0;
						t.dvalue = stod(t.spelling);
						return t;
					}
					break;
				}
				break;
			}
			else if (t.kind == DOUBLE && isdigit(ch))//case double
			{
				//append the current char and read next char
				t.spelling += ch;
				//test next char
				nextch();
				if(ch != '0' && !is_digit19(ch))
				{
					t.ivalue = 0;
					t.dvalue = stod(t.spelling);
					return t;
				}
				break;
			}

			// add if statements here for characters that can start tokens
			// using helper functions to return an initialised token object would be a good idea
			// ...

			//if char case did not fit all cases above, return bad 
			ch = EOF;                          // simulate EOI
			return new_token(BAD, "");          // the next character cannot start a token, return a BAD token
		}
	}

	start_line = line_num;                     // remember current position so EOI token is correct
	start_column = column_num;

	return new_token(EOI, "");                  // return an EOI token
}
