#include "ast.h"

using namespace std;

static string className;
//the memory space required for an object
static int numOfFields = 0;
//ensure labels are unique
static int ifLabelCounter = 0;
static int whileLabelCounter = 0;

//declare subroutines
void classVarDecs(jn_var_decs node);
void subroutineDecs(jn_subr_decs node);
void subroutineDec(jn_subr node);
void statements(jn_statements node);
void letStatement(jn_statement node);
void letArrayStatment(jn_statement node);
void ifStatement(jn_statement node);
void ifelseStatement(jn_statement node);
void whileStatement(jn_statement node);
void doStatement(jn_statement node);
void returnStatement(jn_statement node);
void expression(jn_expr node);
void subroutineCall(jn_call node);
void expressionList(jn_expr_list node);
void unary_op(jn_unary_op node);
void infix_op(jn_infix_op node);
void variable(jn_var node);
void array(jn_array_index node);
void buildString(string content);

void jack_codegen(jn_class t)
{
	className = t->class_name;
	classVarDecs(t->decs);
	subroutineDecs(t->subrs);
	return;
}

//calculate the number of the fields
void classVarDecs(jn_var_decs node)
{
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		jn_var_dec var = node->get(i);
		if (var->segment.compare("this") == 0)
		{
			++numOfFields;
		}
	}
	return;
}

void subroutineDecs(jn_subr_decs node)
{
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		subroutineDec(node->get(i));
	}
	return;
}
void subroutineDec(jn_subr node)
{
	ifLabelCounter = 0;
	whileLabelCounter = 0;

	int kind = 0;
	switch (node->kind)
	{
	case k_jn_constructor:
		kind = 1;
		break;
	case k_jn_function:
		kind = 2;
		break;
	case k_jn_method:
		kind = 3;
		break;
	}

	jn_subr_body body = node->body;
	jn_var_decs decs = body->decs;
	jn_statements stats = body->body;

	int numOfVars = decs->size();

	write_to_output("function " + className + "." + node->name + " " + to_string(numOfVars) + "\n");
	//constructor always start with
	if (kind == 1)
	{
		write_to_output("push constant " + to_string(numOfFields) + "\n");
		write_to_output("call Memory.alloc 1\n");
		write_to_output("pop pointer 0\n");
	}
	//method call always start with
	else if (kind == 3)
	{
		write_to_output("push argument 0\n");
		write_to_output("pop pointer 0\n");
	}

	statements(stats);

	return;
}

void statements(jn_statements node)
{
	for (int i = 0; i < node->size(); i++)
	{
		jn_statement stat = node->get(i);
		switch (stat->kind)
		{
		case k_jn_let:
			letStatement(stat);
			break;
		case k_jn_let_array:
			letArrayStatment(stat);
			break;
		case k_jn_if:
			ifStatement(stat);
			break;
		case k_jn_if_else:
			ifelseStatement(stat);
			break;
		case k_jn_while:
			whileStatement(stat);
			break;
		case k_jn_do:
			doStatement(stat);
			break;
		case k_jn_return:
			write_to_output("push constant 0\n");
			write_to_output("return\n");
			break;
		case k_jn_return_expr:
			returnStatement(stat);
			break;
		}
	}
	return;
}

void letStatement(jn_statement node)
{
	jn_let stat = to_jn_let(node);
	expression(stat->expr);
	jn_var var = stat->var;
	write_to_output("pop");
	variable(var);
	return;
}

void letArrayStatment(jn_statement node)
{
	jn_let_array stat = to_jn_let_array(node);
	//calculate the real address of the left hand side element
	expression(stat->index);
	write_to_output("push");
	variable(stat->var);
	write_to_output("add\n");
	expression(stat->expr);
	write_to_output("pop temp 0\n");
	//use pointer 1 to save the address
	write_to_output("pop pointer 1\n");
	write_to_output("push temp 0\n");
	write_to_output("pop that 0\n");
	return;
}

void ifStatement(jn_statement node)
{
	int counter = ifLabelCounter++;
	jn_if stat = to_jn_if(node);
	expression(stat->cond);
	write_to_output("if-goto IF_TRUE" + to_string(counter) + "\n");
	write_to_output("goto IF_FALSE" + to_string(counter) + "\n");
	write_to_output("label IF_TRUE" + to_string(counter) + "\n");
	statements(stat->if_true);
	write_to_output("label IF_FALSE" + to_string(counter) + "\n");
	return;
}

void ifelseStatement(jn_statement node)
{
	int counter = ifLabelCounter++;
	jn_if_else stat = to_jn_if_else(node);
	expression(stat->cond);
	write_to_output("if-goto IF_TRUE" + to_string(counter) + "\n");
	write_to_output("goto IF_FALSE" + to_string(counter) + "\n");
	write_to_output("label IF_TRUE" + to_string(counter) + "\n");
	statements(stat->if_true);
	write_to_output("goto IF_END" + to_string(counter) + "\n");
	write_to_output("label IF_FALSE" + to_string(counter) + "\n");
	statements(stat->if_false);
	write_to_output("label IF_END" + to_string(counter) + "\n");
	return;
}

void whileStatement(jn_statement node)
{
	int counter = whileLabelCounter++;
	jn_while stat = to_jn_while(node);
	write_to_output("label WHILE_EXP" + to_string(counter) + "\n");
	expression(stat->cond);
	write_to_output("not\n");
	write_to_output("if-goto WHILE_END" + to_string(counter) + "\n");
	statements(stat->body);
	write_to_output("goto WHILE_EXP" + to_string(counter) + "\n");
	write_to_output("label WHILE_END" + to_string(counter) + "\n");
	return;
}

void doStatement(jn_statement node)
{
	jn_do stat = to_jn_do(node);
	subroutineCall(stat->call);
	write_to_output("pop temp 0\n");
	return;
}

void returnStatement(jn_statement node)
{
	jn_return_expr stat = to_jn_return_expr(node);
	expression(stat->expr);
	write_to_output("return\n");
	return;
}

void expression(jn_expr node)
{
	switch (node->kind)
	{
	case k_jn_int:
		write_to_output("push constant " + to_string(to_jn_int(node)->ic) + "\n");
		break;
	case k_jn_string:
		buildString(to_jn_string(node)->sc);
		break;
	case k_jn_bool:
		if (to_jn_bool(node)->tf)
		{
			write_to_output("push constant 0\n");
			write_to_output("not\n");
		}
		else
		{
			write_to_output("push constant 0\n");
		}
		break;
	case k_jn_null:
	//a null simply push a constant 0 to the stack
		write_to_output("push constant 0\n");
		break;
	case k_jn_this:
		write_to_output("push pointer 0\n");
		break;
	case k_jn_var:
		write_to_output("push");
		variable(to_jn_var(node));
		break;
	case k_jn_array_index:
		array(to_jn_array_index(node));
		break;
	case k_jn_unary_op:
		unary_op(to_jn_unary_op(node));
		break;
	case k_jn_infix_op:
		infix_op(to_jn_infix_op(node));
		break;
	case k_jn_call:
		subroutineCall(to_jn_call(node));
		break;
	}
	return;
}

void subroutineCall(jn_call node)
{
	expressionList(node->expr_list);
	write_to_output("call " + node->class_name + "." + node->subr_name + " " + to_string(node->expr_list->size()) + "\n");
	return;
}

void expressionList(jn_expr_list node)
{
	for (int i = 0; i < node->size(); i++)
	{
		expression(node->get(i));
	}
	return;
}

void unary_op(jn_unary_op node)
{
	expression(node->expr);
	if (node->op.compare("-") == 0)
	{
		write_to_output("neg\n");
	}
	else
	{
		write_to_output("not\n");
	}
	return;
}

void infix_op(jn_infix_op node)
{
	expression(node->lhs);
	expression(node->rhs);
	if (node->op.compare("+") == 0)
	{
		write_to_output("add\n");
	}
	else if (node->op.compare("-") == 0)
	{
		write_to_output("sub\n");
	}
	else if (node->op.compare("*") == 0)
	{
		write_to_output("call Math.multiply 2\n");
	}
	else if (node->op.compare("/") == 0)
	{
		write_to_output("call Math.divide 2\n");
	}
	else if (node->op.compare("&") == 0)
	{
		write_to_output("and\n");
	}
	else if (node->op.compare("|") == 0)
	{
		write_to_output("or\n");
	}
	else if (node->op.compare("<") == 0)
	{
		write_to_output("lt\n");
	}
	else if (node->op.compare(">") == 0)
	{
		write_to_output("gt\n");
	}
	else if (node->op.compare("=") == 0)
	{
		write_to_output("eq\n");
	}
	return;
}

void variable(jn_var node)
{
	write_to_output(" " + node->segment + " " + to_string(node->offset) + "\n");
	return;
}

void array(jn_array_index node)
{
	expression(node->index);
	write_to_output("push");
	variable(node->var);
	write_to_output("add\n");
	write_to_output("pop pointer 1\n");
	write_to_output("push that 0\n");
}

//a string is a char array, allocate the memory first and then append every single char to it
void buildString(string content)
{
	int length = content.length();
	write_to_output("push constant " + to_string(length) + "\n");
	write_to_output("call String.new 1\n");
	for (int i = 0; i < length; i++)
	{
		int asc = content.at(i);
		write_to_output("push constant " + to_string(asc) + "\n");
		write_to_output("call String.appendChar 2\n");
	}
	return;
}

int main(int argc, char **argv)
{
	// parse an AST in XML and print VM code
	jack_codegen(jn_parse_xml());

	// flush the output and any errors
	print_output();
	print_errors();
	return 0;
}