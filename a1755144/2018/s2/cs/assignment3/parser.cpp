#include "ast.h"
#include "symbols.h"

using namespace std;

//variable class
class variable : public symbol_value { public: string name, kind, segment; int offset; };


// the parser we are using
static tokeniser the_tokeniser;

// the last token read - start with ?
static Token token;
static string className;
static bool voidType = true;
static int subroutineType = 0;
static int returnCounter = 0;

//class level
static symbol_table_values classVariables;
//subroutine level
static symbol_table_values subroutineVariables;


// useful parsing functions
void nextToken()
{
	token = the_tokeniser->next_token();
}

// if we have the expected token - move to next token otherwise give up!
void mustbe(TokenKind expected)
{
	if (expected != token.token)
	{
		//cout << "Error: found token \"" << token.spelling << "\" but expected \"" << the_tokeniser->token_to_string(expected) << "\"" << endl;
		exit(0);
	}
	nextToken();
}

void error(string expected)
{
	//cout << "Error: found token \"" << token.spelling << "\" error message: \"" << expected << "\"" << endl;
	exit(0);
}

// if we have the expected token - move to next token and return true
bool have(TokenKind expected)
{
	if (expected != token.token) return false;
	nextToken();
	return true;
}

//usefull tools for telling what the current token is
bool isSubroutine()
{
	return (token.token == jk_constructor
		|| token.token == jk_function
		|| token.token == jk_method);
}

bool isType()
{
	return (token.token == jk_int
		|| token.token == jk_char
		|| token.token == jk_boolean
		|| token.token == jk_identifier);
}

bool isVtype()
{
	return (isType() || token.token == jk_void);
}

bool isStatement()
{
	return (token.token == jk_let
		|| token.token == jk_do
		|| token.token == jk_if
		|| token.token == jk_while
		|| token.token == jk_return);
}

bool isInfixOp()
{
	return (token.token == jk_add
		|| token.token == jk_sub
		|| token.token == jk_times
		|| token.token == jk_divide
		|| token.token == jk_and
		|| token.token == jk_or
		|| token.token == jk_lt
		|| token.token == jk_gt
		|| token.token == jk_eq);
}

bool isUnaryOp(TokenKind tokenkind)
{
	return (tokenkind == jk_sub || tokenkind == jk_not);
}

bool isKeywordConstant(TokenKind tokenkind)
{
	return (tokenkind == jk_true
		|| tokenkind == jk_false
		|| tokenkind == jk_null
		|| tokenkind == jk_this);
}

//insert a variable to specific symbol table
void insertClassSymbolTable(string name, string type, string segment, int offset)
{
	variable* var = new variable();
	var->kind = type;
	var->name = name;
	var->offset = offset;
	var->segment = segment;

	if (!classVariables->insert(name, var)) error("Error! Duplicated variable!");
	return;
}

void insertSubroutineSymbolTable(string name, string type, string segment, int offset)
{
	variable* var = new variable();
	var->kind = type;
	var->name = name;
	var->offset = offset;
	var->segment = segment;

	if (!subroutineVariables->insert(name, var)) error("Error! Duplicated variable!");
	return;
}

//find a variable in a symbol table, if not found, report error
jn_var findVariable(string name)
{
	string segment = "";
	int offset;
	string type;
	variable *result = (variable*)subroutineVariables->lookup(name);
	if (result != nullptr)
	{
		segment = result->segment;
		offset = result->offset;
		type = result->kind;
	}
	else
	{
		variable *result = (variable*)classVariables->lookup(name);
		if (result != nullptr)
		{
			segment = result->segment;
			offset = result->offset;
			type = result->kind;
		}
		else
		{
			return nullptr;
			error("variable " + name + " not defined");
		}
	}
	return jn_var_create(segment, name, offset, type);
}

//declare subroutines
jn_var_decs classVarDecs();
jn_var_dec varDec(string segment, int offset);
jn_subr_decs subroutineDecs();
jn_subr subroutineDec();
jn_constructor constructor();
jn_function function();
jn_method method();
jn_param_list parameterList();
jn_subr_body subroutineBody();
jn_var_decs varDecs();
jn_statements statements();
jn_let letStatement();
jn_if ifStatement();
jn_while whileStatement();
jn_do doStatement();
jn_return returnStatement();
jn_expr expression();
jn_expr term();
jn_call subroutineCall(string prev);
jn_expr_list expressionList(bool method_call, string class_name);

jn_class jack_parser()
{
	classVariables = create_symbol_table_values();

	string class_name;
	jn_var_decs decs;
	jn_subr_decs subrs;

	mustbe(jk_class);
	class_name = token.spelling;
	className = class_name;
	mustbe(jk_identifier);
	mustbe(jk_lcb);
	decs = classVarDecs();
	subrs = subroutineDecs();
	mustbe(jk_rcb);
	return jn_class_create(class_name, decs, subrs);
	//return nullptr ;
}

jn_var_decs classVarDecs()
{
	jn_var_decs decs = jn_var_decs_create();
	//static and field have different offset
	int staticCounter = 0;
	int fieldCounter = 0;
	//the first while would stop when static or field were no longer found
	while (token.token == jk_static || token.token == jk_field)
	{
		TokenKind tokenkind = token.token;
		jn_var_dec dec;
		if (tokenkind == jk_static)
		{
			dec = varDec(token.spelling, staticCounter++);
		}
		else
		{
			dec = varDec("this", fieldCounter++);
		}
		insertClassSymbolTable(dec->name, dec->type, dec->segment, dec->offset);
		decs->append(dec);
		string name;
		//the second while handle the situation that an identifier followed by a comma
		while (have(jk_comma))
		{
			name = token.spelling;
			mustbe(jk_identifier);
			if (tokenkind == jk_static)
			{
				insertClassSymbolTable(name, dec->type, dec->segment, staticCounter);
				decs->append(jn_var_dec_create(dec->segment, name, staticCounter++, dec->type));
			}
			else
			{
				insertClassSymbolTable(name, dec->type, dec->segment, fieldCounter);
				decs->append(jn_var_dec_create(dec->segment, name, fieldCounter++, dec->type));
			}
		}
		mustbe(jk_semi);
	}
	return decs;
}

jn_var_dec varDec(string segment, int offset)
{
	string name;
	string type;
	nextToken();
	if (isType())
	{
		type = token.spelling;
		nextToken();
	}
	else
	{
		error("type");
	}
	name = token.spelling;
	mustbe(jk_identifier);
	return jn_var_dec_create(segment, name, offset, type);
}

jn_subr_decs subroutineDecs()
{
	jn_subr_decs decs = jn_subr_decs_create();
	while (isSubroutine())
	{
		decs->append(subroutineDec());
	}
	return decs;
	//return nullptr;
}

jn_subr subroutineDec()
{
	subroutineVariables = create_symbol_table_values();
	//3 kinds of subroutine
	switch (token.token)
	{
	case jk_constructor:
		subroutineType = 1;
		break;
	case jk_function:
		subroutineType = 2;
		break;
	case jk_method:
		subroutineType = 3;
		break;
	}
	nextToken();
	string vtype;
	string name;
	jn_param_list params;
	jn_subr_body body;
	if (!isVtype())
	{
		error("vtype");
	}
	vtype = token.spelling;
	if (subroutineType == 1 && vtype.compare(className) != 0)
		error("a constructor should it's own class");
	if (vtype.compare("void") == 0)
		voidType = true;
	else
		voidType = false;
	nextToken();
	name = token.spelling;
	mustbe(jk_identifier);
	mustbe(jk_lrb);
	params = parameterList();
	mustbe(jk_rrb);
	body = subroutineBody();

	if (subroutineType == 1)
	{
		return jn_constructor_create(vtype, name, params, body);
	}
	else if (subroutineType == 2)
	{
		return jn_function_create(vtype, name, params, body);
	}
	else if (subroutineType == 3)
	{
		return jn_method_create(vtype, name, params, body);
	}
}

jn_param_list parameterList()
{
	jn_param_list param_list = jn_param_list_create();
	if (isType())
	{
		string segment = "argument";
		string name;
		int counter = 0;
		if (subroutineType == 3)
		{
			counter = 1;
		}
		string type;
		do
		{
			if (isType())
			{
				type = token.spelling;
				nextToken();
				name = token.spelling;
				mustbe(jk_identifier);
			}
			else
			{
				error("param_list");
			}
			insertSubroutineSymbolTable(name, type, segment, counter);
			param_list->append(jn_var_dec_create(segment, name, counter++, type));
		} while (have(jk_comma));
	}
	//end of parameter list
	if (token.token == jk_rrb)
	{
		return param_list;
	}
	else
	{
		error("param_list");
	}
}

jn_subr_body subroutineBody()
{
	jn_var_decs decs;
	jn_statements stats;
	mustbe(jk_lcb);
	decs = varDecs();
	returnCounter = 0;
	stats = statements();
	if (returnCounter == 0)
		error("no return");
	mustbe(jk_rcb);
	return jn_subr_body_create(decs, stats);
}

jn_var_decs varDecs()
{
	jn_var_decs decs = jn_var_decs_create();
	int counter = 0;
	while (token.token == jk_var)
	{
		jn_var_dec dec = varDec("local", counter++);
		insertSubroutineSymbolTable(dec->name, dec->type, dec->segment, dec->offset);
		decs->append(dec);
		string name;
		while (have(jk_comma))
		{
			name = token.spelling;
			mustbe(jk_identifier);
			insertSubroutineSymbolTable(name, dec->type, dec->segment, counter);
			decs->append(jn_var_dec_create(dec->segment, name, counter++, dec->type));
		}
		mustbe(jk_semi);
	}
	return decs;
}

jn_statements statements()
{
	jn_statements stats = jn_statements_create();
	bool rtStat = false;
	while (isStatement())
	{
		jn_statement statement;
		switch (token.token)
		{
		case jk_let:
			statement = (jn_statement)letStatement();
			break;
		case jk_do:
			statement = (jn_statement)doStatement();
			break;
		case jk_if:
			statement = (jn_statement)ifStatement();
			break;
		case jk_while:
			statement = (jn_statement)whileStatement();
			break;
		case jk_return:
			returnCounter++;
			statement = (jn_statement)returnStatement();
			break;
		}
		stats->append(statement);
	}
	return stats;
}

jn_let letStatement()
{
	jn_var var;
	jn_expr index;
	jn_expr expr;
	bool array = false;
	nextToken();
	string name = token.spelling;
	mustbe(jk_identifier);

	var = findVariable(name);
	//handle the situation that the left hand side is an array
	if (have(jk_lsb))
	{
		array = true;
		index = expression();
		mustbe(jk_rsb);
	}
	mustbe(jk_eq);
	expr = expression();
	mustbe(jk_semi);
	if (array)
	{
		return jn_let_array_create(var, index, expr);
	}
	else
	{
		return jn_let_create(var, expr);
	}
}

jn_if ifStatement()
{
	jn_expr cond;
	jn_statements if_true;
	nextToken();
	mustbe(jk_lrb);
	cond = expression();
	mustbe(jk_rrb);
	mustbe(jk_lcb);
	if_true = statements();
	mustbe(jk_rcb);
	//may or may not have an else
	if (have(jk_else))
	{
		jn_statements if_false;
		mustbe(jk_lcb);
		if_false = statements();
		mustbe(jk_rcb);
		return jn_if_else_create(cond, if_true, if_false);
	}
	return jn_if_create(cond, if_true);
}

jn_while whileStatement()
{
	jn_expr cond;
	jn_statements body;
	nextToken();
	mustbe(jk_lrb);
	cond = expression();
	mustbe(jk_rrb);
	mustbe(jk_lcb);
	body = statements();
	mustbe(jk_rcb);
	return jn_while_create(cond, body);
}

jn_do doStatement()
{
	nextToken();
	string temp = token.spelling;
	mustbe(jk_identifier);
	jn_do doStat = jn_do_create(subroutineCall(temp));
	mustbe(jk_semi);
	return doStat;
}

jn_return returnStatement()
{
	mustbe(jk_return);
	if (have(jk_semi))
	{
		//not void but do not return anything
		if (!voidType)
		{
			error("not void");
		}
		return jn_return_create();
	}
	else
	{
		//void but return something
		if (voidType)
		{
			error("void");
		}
		jn_expr expr;
		if (subroutineType == 1)
		{
			mustbe(jk_this);
			expr = to_jn_expr(jn_this_create());
		}
		else
		{
			expr = expression();
		}
		mustbe(jk_semi);
		return (jn_return)jn_return_expr_create(expr);
	}
}

jn_expr expression()
{
	jn_expr lhs = term();
	jn_expr rhs;
	while (isInfixOp())
	{
		string op = token.spelling;
		nextToken();
		rhs = term();
		lhs = to_jn_expr(jn_infix_op_create(lhs, op, rhs));
	}
	return lhs;
}

jn_expr term()
{
	Token temp = token;
	nextToken();
	if (temp.token == jk_integerConstant)
	{
		return to_jn_expr(jn_int_create(temp.ivalue));
	}
	if (temp.token == jk_stringConstant)
	{
		return to_jn_expr(jn_string_create(temp.spelling));
	}
	if (temp.token == jk_lrb)
	{
		jn_expr expr = expression();
		mustbe(jk_rrb);
		return expr;
	}
	if (isKeywordConstant(temp.token))
	{
		switch (temp.token)
		{
		case jk_true:
			return to_jn_expr(jn_bool_create(true));
		case jk_false:
			return to_jn_expr(jn_bool_create(false));
		case jk_null:
			return to_jn_expr(jn_null_create());
		case jk_this:
			return to_jn_expr(jn_this_create());
		}
	}
	if (isUnaryOp(temp.token))
	{
		return to_jn_expr(jn_unary_op_create(temp.spelling, term()));
	}
	//a term begins with an identifier could be the following construct
	if (temp.token == jk_identifier)
	{
		jn_var var;
		//array
		if (have(jk_lsb))
		{
			var = findVariable(temp.spelling);
			jn_expr index = expression();
			mustbe(jk_rsb);
			return to_jn_expr(jn_array_index_create(var, index));
		}
		//call subroutine
		else if (token.token == jk_stop || token.token == jk_lrb)
		{
			return to_jn_expr(subroutineCall(temp.spelling));
		}
		//variable
		else
		{
			var = findVariable(temp.spelling);
			return to_jn_expr(var);
		}
	}
	error("Unexpected token!");
}

jn_call subroutineCall(string prev)
{
	bool method_call = true;
	string objectName = "";
	string class_name = "";
	string subr_name;
	jn_expr_list expr_list;
	//before the stop could be a class name or an object
	if (have(jk_stop))
	{
		variable *result = (variable*)subroutineVariables->lookup(prev);
		if (result == nullptr)
			result = (variable*)classVariables->lookup(prev);
		//no such object in the symbol table -> previous identifier is a class name
		if (result == nullptr)
		{
			method_call = false;
			class_name = prev;
		}
		//an object
		else
		{
			class_name = result->kind;
			objectName = result->name;
		}
		subr_name = token.spelling;
		mustbe(jk_identifier);
	}
	//no stop, method call inside a class
	else
	{
		class_name = className;
		objectName = "this";
		subr_name = prev;
	}
	mustbe(jk_lrb);
	expr_list = expressionList(method_call, objectName);
	mustbe(jk_rrb);
	return jn_call_create(method_call, class_name, subr_name, expr_list);
}

jn_expr_list expressionList(bool method_call, string objectName)
{
	jn_expr_list expr_list = jn_expr_list_create();
	//method call, add the hidden parameter
	if (method_call)
	{
		if (objectName.compare("this") == 0)
		{
			expr_list->append(to_jn_expr(jn_this_create()));
		}
		else
		{
			expr_list->append(to_jn_expr(findVariable(objectName)));
		}
	}
	//empty list
	if (token.token == jk_rrb)
	{
		return expr_list;
	}
	jn_expr expr = expression();
	expr_list->append(to_jn_expr(expr));
	while (have(jk_comma))
	{
		expr = expression();
		expr_list->append(to_jn_expr(expr));
	}
	return expr_list;
}

int main(int argc, char **argv)
{
	the_tokeniser = j_tokeniser();
	nextToken();

	// parse a class and print the abstract syntax tree as XML
	jn_print_as_xml(jack_parser(), 4);

	// flush the output and any errors
	print_output();
	print_errors();
}

