// convert an abstract syntax tree for Hack Assembly language into machine code
#include "ast.h"
#include "symbols.h"

// to make out programs a bit neater
using namespace std;

static symbol_table_int address;
static symbol_table_string bcode;

static void *fatal_error()
{
	exit(0);
}

void initialize()
{
	//put the predefined symbols into the map
	address->insert("SP", 0);
	address->insert("LCL", 1);
	address->insert("ARG", 2);
	address->insert("THIS", 3);
	address->insert("THAT", 4);
	address->insert("SCREEN", 16384);
	address->insert("KBD", 24576);
	address->insert("R0", 0);
	address->insert("R1", 1);
	address->insert("R2", 2);
	address->insert("R3", 3);
	address->insert("R4", 4);
	address->insert("R5", 5);
	address->insert("R6", 6);
	address->insert("R7", 7);
	address->insert("R8", 8);
	address->insert("R9", 9);
	address->insert("R10", 10);
	address->insert("R11", 11);
	address->insert("R12", 12);
	address->insert("R13", 13);
	address->insert("R14", 14);
	address->insert("R15", 15);
}

void twoPass(an_program the_program)
{
	//first pass: determined the address of the labels 
	int counter = -1;
	int ninstructions = the_program->size();
	//go through all the instructions of the program
	for (int i = 0; i < ninstructions; i++)
	{
		an_instruction instruction = the_program->get(i);

		if (ast_kind_of(instruction) != k_an_label)
		{
			++counter;
		}
		else
		{
			string context = to_an_label(instruction)->label;
			int result = address->lookup(context);
			//duplicated label, terminate the program
			if (result != -1)
				fatal_error();

			address->insert(context, counter + 1);
		}
	}
	//second pass: determined the address of the variables
	//the first address for the variables
	counter = 16;
	//go through all the instructions of the program
	for (int i = 0; i < ninstructions; i++)
	{
		an_instruction instruction = the_program->get(i);

		if (ast_kind_of(instruction) == k_an_a_instr_name)
		{
			string context = to_an_a_instr_name(instruction)->name;
			int result = address->lookup(context);
			//new symbol found, insert into the map
			if (result == -1)
				address->insert(context, counter++);
		}
	}
}
//transfer decimal numbers to binary numbers
string intToCode(int number)
{
	string code = "";
	int rem = number;
	//divide by 2 recursively to get every binary digit bit of the number 
	while (number > 0)
	{
		rem = number % 2;
		code = to_string(rem) + code;
		number = number / 2;
	}
	return code;
}
//fill zeros at the front of the binary numbers
string fillZero(string code, int length)
{
	int size = code.length();
	for (int i = 0; i < length - size; i++)
	{
		code = "0" + code;
	}
	return code;
}
//transfer the destination to binary code
string desDecode(string des)
{
	string code = "";
	size_t foundA = des.find("A");
	size_t foundD = des.find("D");
	size_t foundM = des.find("M");
	//the first bit determines wether the destination includes "A" or not
	if (foundA != string::npos)
		code += "1";
	else
		code += "0";
	//the second bit determines wether the destination includes "D" or not
	if (foundD != string::npos)
		code += "1";
	else
		code += "0";
	//the third bit determines wether the destination includes "M" or not
	if (foundM != string::npos)
		code += "1";
	else
		code += "0";
	return code;
}
//map the alu codes and jump codes
void binaryMap()
{
	//alu binary codes
	bcode->insert("0", "0101010");
	bcode->insert("1", "0111111");
	bcode->insert("-1", "0111010");
	bcode->insert("D", "0001100");
	bcode->insert("A", "0110000");
	bcode->insert("!D", "0001101");
	bcode->insert("!A", "0110001");
	bcode->insert("-D", "0001111");
	bcode->insert("-A", "0110011");
	bcode->insert("D+1", "0011111");
	bcode->insert("A+1", "0110111");
	bcode->insert("D-1", "0001110");
	bcode->insert("A-1", "0110010");
	bcode->insert("D+A", "0000010");
	bcode->insert("D-A", "0010011");
	bcode->insert("A-D", "0000111");
	bcode->insert("D&A", "0000000");
	bcode->insert("D|A", "0010101");
	bcode->insert("M", "1110000");
	bcode->insert("!M", "1110001");
	bcode->insert("-M", "1110011");
	bcode->insert("M+1", "1110111");
	bcode->insert("M-1", "1110010");
	bcode->insert("D+M", "1000010");
	bcode->insert("D-M", "1010011");
	bcode->insert("M-D", "1000111");
	bcode->insert("D&M", "1000000");
	bcode->insert("D|M", "1010101");
	//jump binary codes
	bcode->insert("NULL", "000");
	bcode->insert("JGT", "001");
	bcode->insert("JEQ", "010");
	bcode->insert("JGE", "011");
	bcode->insert("JLT", "100");
	bcode->insert("JNE", "101");
	bcode->insert("JLE", "110");
	bcode->insert("JMP", "111");
}
//dealing with the a-instruction with a variable name
void aNameIns(an_a_instr_name ins)
{
	//a instruction starts with "0"
	string result = "0";
	//find address in the map, and transfer it to binary representation.
	int number = address->lookup(ins->name);
	string code = intToCode(number);
	result += fillZero(code, 15);
	write_to_output(result + '\n');
}
//dealing with the a-instruction with a number
void aIns(an_a_instruction ins)
{
	//a instruction starts with "0"
	string result = "0";
	int number = stoi(ins->number);
	string code = intToCode(number);
	result += fillZero(code, 15);
	write_to_output(result + '\n');
}
//dealing with the c-instruction
void cIns(an_c_instruction ins)
{
	//c instruction starts with "111"
	string result = "111";
	//figure out the binary code of alu, dest and jump seperately and combine them together
	result = result + bcode->lookup(ins->alu_op) + desDecode(ins->dest) + bcode->lookup(ins->jump);
	write_to_output(result + '\n');
}

// the function asm_translator() is passed the abstract syntax tree constructed
// by an_parse_xml(), it walks the abstract syntax tree and produces the equivalent
// Hack machine code as output
void asm_translator(an_program the_program)
{
	twoPass(the_program);
	// the_program contains a vector of instructions - translate them one at a time
	int ninstructions = the_program->size();
	for (int i = 0; i < ninstructions; i++)
	{
		an_instruction instruction = the_program->get(i);
		switch (ast_kind_of(instruction))
		{
		case k_an_label:
			// ... call a translation function and pass it the instruction
			//do nothing when a label found
			break;

		case k_an_a_instr_name:

			// ... call a translation function and pass it the instruction
			aNameIns(to_an_a_instr_name(instruction));

			break;

		case k_an_a_instruction:

			// ... call a translation function and pass it the instruction
			aIns(to_an_a_instruction(instruction));

			break;
		case k_an_c_instruction:

			// ... call a translation function and pass it the instruction
			cIns(to_an_c_instruction(instruction));

			break;

		default:

			write_to_errors("// bad node - expected an-label,an-a-instr-name,an-a-instruction or an-c-instruction\n");
			break;

		}
	}
}

// main program
int main(int argc, char **argv)
{
	//initialize the maps
	address = create_symbol_table_int();
	bcode = create_symbol_table_string();
	//set predefined symbols, alu_op code and jump code to the maps
	initialize();
	binaryMap();

	// parse abstract syntax tree and pass to the translator
	asm_translator(an_parse_xml());

	// flush output and errors
	print_output();
	print_errors();
}