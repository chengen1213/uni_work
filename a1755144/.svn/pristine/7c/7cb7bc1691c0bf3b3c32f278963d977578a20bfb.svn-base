#include "ast.h"

using namespace std;

static string className;
static int level = 0;

void indentation();
void varDecs(jn_var_decs node);
void varDec(jn_var_decs node);
void subroutineDecs(jn_subr_decs node);
void subroutineDec(jn_subr node);
void parameterList(jn_param_list node);
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
void unary_op(jn_unary_op node);
void infix_op(jn_infix_op node);
void array(jn_array_index node);

//control the indentation
void indentation()
{
	for (int i = 1; i <= level; i++)
	{
		write_to_output("    ");
	}
}

//a "{" or a "}" change the level
void printLcb()
{
	indentation();
	write_to_output("{\n");
	level++;
}

void printRcb()
{
	level--;
	indentation();
	write_to_output("}\n");
}

void jack_pretty(jn_class t)
{
	className = t->class_name;
	write_to_output("class " + className + "\n");
	printLcb();
	varDecs(t->decs);
	subroutineDecs(t->subrs);
	printRcb();
	return;
}

void varDecs(jn_var_decs node)
{
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		indentation();
		jn_var_dec var = node->get(i);
		string segment;
		if (var->segment.compare("this") == 0)
		{
			segment = "field";
		}
		else if (var->segment.compare("local") == 0)
		{
			segment = "var";
		}
		else
		{
			segment = var->segment;
		}
		write_to_output(segment + " " + var->type + " " + var->name + " ;\n");
	}
	if (size > 0)
	{
		write_to_output("\n");
	}
	return;
}

void subroutineDecs(jn_subr_decs node)
{
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		subroutineDec(node->get(i));
		if (i < (size - 1))
		{
			write_to_output("\n");
		}
	}
	return;
}

void subroutineDec(jn_subr node)
{
	int kind = 0;
	indentation();
	switch (node->kind)
	{
	case k_jn_constructor:
		kind = 1;
		write_to_output("constructor ");
		break;
	case k_jn_function:
		kind = 2;

		write_to_output("function ");
		break;
	case k_jn_method:
		kind = 3;
		write_to_output("method ");
		break;
	}

	write_to_output(node->vtype + " " + className + "." + node->name);

	jn_param_list params = node->params;
	jn_subr_body body = node->body;
	jn_var_decs decs = body->decs;
	jn_statements stats = body->body;

	parameterList(params);
	printLcb();
	varDecs(decs);
	statements(stats);
	printRcb();

	return;
}

void parameterList(jn_param_list node)
{
	write_to_output("(");
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		jn_var_dec var = node->get(i);
		write_to_output(var->type + " " + var->name);
		if (i < (size - 1))
		{
			write_to_output(",");
		}
	}
	write_to_output(")\n");
}

void statements(jn_statements node)
{
	int size = node->size();
	for (int i = 0; i < size; i++)
	{
		indentation();
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
			if (i < (size - 1))
			{
				write_to_output("\n");
			}
			break;
		case k_jn_if_else:
			ifelseStatement(stat);
			if (i < (size - 1))
			{
				write_to_output("\n");
			}
			break;
		case k_jn_while:
			whileStatement(stat);
			if (i < (size - 1))
			{
				write_to_output("\n");
			}
			break;
		case k_jn_do:
			doStatement(stat);
			break;
		case k_jn_return:
			write_to_output("return ;\n");
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
	jn_var var = stat->var;
	write_to_output("let " + var->name + " = ");
	expression(stat->expr);
	write_to_output(" ;\n");
	return;
}

void letArrayStatment(jn_statement node)
{
	jn_let_array stat = to_jn_let_array(node);
	jn_var var = stat->var;
	write_to_output("let " + var->name + "[");
	expression(stat->index);
	write_to_output("] = ");
	expression(stat->expr);
	write_to_output(" ;\n");
	return;
}

void ifStatement(jn_statement node)
{
	jn_if stat = to_jn_if(node);
	write_to_output("if (");
	expression(stat->cond);
	write_to_output(")\n");
	printLcb();
	statements(stat->if_true);
	printRcb();
	return;
}

void ifelseStatement(jn_statement node)
{
	jn_if_else stat = to_jn_if_else(node);
	write_to_output("if (");
	expression(stat->cond);
	write_to_output(")\n");
	printLcb();
	statements(stat->if_true);
	printRcb();
	indentation();
	write_to_output("else\n");
	printLcb();
	statements(stat->if_false);
	printRcb();
	return;
}

void whileStatement(jn_statement node)
{
	jn_while stat = to_jn_while(node);
	write_to_output("while (");
	expression(stat->cond);
	write_to_output(")\n");
	printLcb();
	statements(stat->body);
	printRcb();
	return;
}

void doStatement(jn_statement node)
{
	jn_do stat = to_jn_do(node);
	write_to_output("do ");
	subroutineCall(stat->call);
	write_to_output(" ;\n");
	return;
}

void returnStatement(jn_statement node)
{
	jn_return_expr stat = to_jn_return_expr(node);
	write_to_output("return ");
	expression(stat->expr);
	write_to_output(" ;\n");
	return;
}

void expression(jn_expr node)
{
	switch (node->kind)
	{
	case k_jn_int:
		write_to_output(to_string(to_jn_int(node)->ic));
		break;
	case k_jn_string:
		write_to_output("\"" + to_jn_string(node)->sc + "\"");
		break;
	case k_jn_bool:
		if (to_jn_bool(node)->tf)
		{
			write_to_output("true");
		}
		else
		{
			write_to_output("false");
		}
		break;
	case k_jn_null:
		write_to_output("null");
		break;
	case k_jn_this:
		write_to_output("this");
		break;
	case k_jn_var:
		write_to_output(to_jn_var(node)->name);
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
	bool method_call = node->method_call;
	string class_name = node->class_name;
	string subr_name = node->subr_name;
	jn_expr_list expr_list = node->expr_list;
	//method call
	if (method_call)
	{
		//this
		if (expr_list->get(0)->kind == k_jn_this)
			write_to_output("this");
		//object
		else
			write_to_output(to_jn_var(expr_list->get(0))->name);
		write_to_output("." + subr_name + "(");
		//expression list
		for (int i = 1; i < expr_list->size(); i++)
		{
			expression(expr_list->get(i));
			if (i < expr_list->size() - 1)
			{
				write_to_output(",");
			}
		}
	}
	//function or constructor
	else
	{
		write_to_output(class_name + "." + subr_name + "(");
		for (int i = 0; i < expr_list->size(); i++)
		{
			expression(expr_list->get(i));
			if (i < expr_list->size() - 1)
			{
				write_to_output(",");
			}
		}
	}
	write_to_output(")");
	return;
}

void unary_op(jn_unary_op node)
{
	write_to_output(node->op);
	expression(node->expr);
	return;
}

void infix_op(jn_infix_op node)
{
	expression(node->lhs);
	write_to_output(" " + node->op + " ");
	expression(node->rhs);
	return;
}

void array(jn_array_index node)
{
	write_to_output(node->var->name);
	write_to_output("[");
	expression(node->index);
	write_to_output("]");
	return;
}

int main(int argc, char **argv)
{
	// parse an AST in XML and pretty print as Jack
	jack_pretty(jn_parse_xml());

	// flush the output and any errors
	print_output();
	print_errors();
	return 0;
}
