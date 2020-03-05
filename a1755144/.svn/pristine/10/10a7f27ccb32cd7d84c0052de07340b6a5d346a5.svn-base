// convert Hack Assembly Language into an abstract syntax tree
#include "ast.h"

// to make out programs a bit neater
using namespace std;

// the tokeniser being used
static tokeniser the_tokeniser = nullptr;

// the current token being read 
static Token token;
//temporarily store information for c-instructions 
static string spellings[3];

// function to read the next token
static void next_token()
{
	token = the_tokeniser->next_token();
}

// for all syntax errors - do not report an error just die!
static void *fatal_error()
{
	exit(0);
}

// token must be expected, if it is then read next token otherwise it is a fatal error
static void mustbe(TokenKind expected)
{
	if (token.kind != expected) fatal_error();
	next_token();
}

static void opHandler()
{
	if (token.kind == ak_register || token.kind == ak_alu_op)
	{
		spellings[0] = token.spelling;
	}
	else
	{
		fatal_error();
	}
}

static void jumpHandler()
{
	if (token.kind == ak_semi)
	{
		next_token();
		spellings[2] = token.spelling;
		mustbe(ak_jump);
	}
}

// grammer to be parsed:
// a_program ::= instruction* eoi
// instruction ::= label | a_instr_name | a_instruction | c_instruction
// c_instruction ::= [c_destination equals] c_alu_op [ semi c_jump]
// c_alu_op ::= register | aluop
// c_destination ::= null | dest | register
// c_jump ::= null | jump

// forward declare parsing functions - one per rule
an_program parse_program();
an_instruction parse_instruction();
an_label parse_label();
an_a_instr_name parse_a_instr_name();
an_a_instruction parse_a_instruction();
an_c_instruction parse_c_instruction();

// Note: always read one token after the one recognised

// a_program ::= instruction* eoi
an_program parse_program()
{
	an_program the_program = an_program_create();

	// we stop when we see the EOI token
	// this may be end of input or an error
	// we cannot tell so treat both as end of input
	// every new assembler instruction we find is appended to the_program
	while (token.kind != ak_eoi)
	{
		//initialize all the spellings to "NULL"
		for (int i = 0; i < 3; i++)
		{
			spellings[i] = "NULL";
		}
		the_program->append(parse_instruction());

		// this call to next_token must be removed when the parsing functions start reading their own tokens
		// the next_token mehtod would be called when parsing
		//	next_token();
	}


	return the_program;
}

/*****************   REPLACE THE FOLLOWING CODE  ******************/

// a_program ::= instruction* eoi
an_instruction parse_instruction()
{
	// inspect the token to see what it might start

	string temp;

	switch (token.kind)
	{
		//labels and a-instructions could be returned immediately
	case ak_label:

		return to_an_instruction(parse_label());

	case ak_address:

		if (token.token == ak_name)
		{
			return to_an_instruction(parse_a_instr_name());
		}

		return to_an_instruction(parse_a_instruction());

		//for c-instructions, there are three kinds of possibilities
		//start with ak_dest, ak_register or ak_alu_op
	case ak_dest:

		spellings[1] = token.spelling;
		next_token();
		//the destination must follow by "="
		mustbe(ak_assign);
		//the "=" must follw by alu_op or register, terminate the program if not
		opHandler();

		next_token();
		//a comp could possibly follow by an jump, handle it
		jumpHandler();

		return to_an_instruction(parse_c_instruction());

	case ak_register:

		temp = token.spelling;
		next_token();
		//register could be either a destination or an alu_op
		//the next token can tell us what it is
		//case destination
		if (token.kind == ak_assign)
		{
			//process that is very similar to case ak_dest:
			spellings[1] = temp;

			next_token();
			opHandler();

			next_token();
			jumpHandler();

			return to_an_instruction(parse_c_instruction());

		}
		//case alu_op
		else
		{
			//process that is very similar to case ak_alu_op:
			spellings[0] = temp;
			jumpHandler();

			return to_an_instruction(parse_c_instruction());

		}
	case ak_alu_op:

		spellings[0] = token.spelling;
		next_token();
		//a comp could possibly follow by an jump, handle it
		jumpHandler();

		return to_an_instruction(parse_c_instruction());

		/*default:
		return to_an_instruction(parse_c_instruction()) ;*/
	}
}

// label is a primitive token for '(' label ')'
an_label parse_label()
{
	// ...
	string spelling = token.spelling;
	next_token();
	return an_label_create(spelling);
	//return nullptr ;
}

// special case for '@' name
an_a_instr_name parse_a_instr_name()
{
	// ...
	string spelling = token.spelling;
	next_token();
	return an_a_instr_name_create(spelling);
	//return nullptr ;
}

// special case for '@' number
an_a_instruction parse_a_instruction()
{
	// ...
	string spelling = token.spelling;
	next_token();
	return an_a_instruction_create(spelling);
	//return nullptr ;
}

// c_instruction ::= [c_destination equals] c_alu_op [ semi c_jump]
an_c_instruction parse_c_instruction()
{
	// ...
	//use the strings preapred by each instruction case
	return an_c_instruction_create(spellings[0], spellings[1], spellings[2]);
	//return nullptr ;
}

/*****************        DOWN TO HERE         ******************/


// main program
int main(int argc, char **argv)
{
	// initialise the tokeniser by reading the first token
	the_tokeniser = a_tokeniser();
	next_token();

	// parse a class and print the abstract syntax tree as XML
	an_print_as_xml(parse_program(), 4);

	// flush the output and any errors
	print_output();
	print_errors();
}
