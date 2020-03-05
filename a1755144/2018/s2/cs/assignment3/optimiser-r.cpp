#include "ast.h"

using namespace std;

//declare subroutines
jn_class copy_class(jn_class);
jn_var_decs copy_var_decs(jn_var_decs);
jn_var_dec copy_var_dec(jn_var_dec);
jn_subr_decs copy_subr_decs(jn_subr_decs);
jn_subr copy_subr_dec(jn_subr);
jn_param_list copy_param_list(jn_param_list);
jn_subr_body copy_subr_body(jn_subr_body);
jn_statements copy_statements(jn_statements);
jn_statement copy_statement(jn_statement);
jn_let copy_let(jn_let);
jn_statement copy_if(jn_if);
jn_statement copy_while(jn_while);
jn_call copy_call(jn_call);
jn_do copy_do(jn_do);
jn_return copy_return(jn_return);
jn_int copy_int(jn_int);
jn_string copy_string(jn_string);
jn_bool copy_bool(jn_bool);
jn_array_index copy_array_index(jn_array_index);
jn_unary_op copy_unary_op(jn_unary_op);
jn_infix_op copy_infix_op(jn_infix_op);
jn_var copy_var(jn_var);
jn_expr copy_expr(jn_expr);
jn_expr_list copy_expr_list(jn_expr_list);
bool isReturn(jn_statement stat);
bool checkIf(jn_if if_s);
bool checkWhile(jn_while while_s);

jn_class copy_class(jn_class t)
{
	string class_name = t->class_name;
	jn_var_decs decs = copy_var_decs(t->decs);
	jn_subr_decs subrs = copy_subr_decs(t->subrs);

	return jn_class_create(class_name, decs, subrs);
}

jn_var_decs copy_var_decs(jn_var_decs decs)
{
	jn_var_decs copy = jn_var_decs_create();
	int ndecs = decs->size();
	for (int i = 0; i < ndecs; i++)
	{
		copy->append(copy_var_dec(decs->get(i)));
	}
	return copy;
}

jn_var_dec copy_var_dec(jn_var_dec var_dec)
{
	string segment = var_dec->segment;
	string name = var_dec->name;
	int offset = var_dec->offset;
	string type = var_dec->type;

	return jn_var_dec_create(segment, name, offset, type);
}

jn_subr_decs copy_subr_decs(jn_subr_decs subrs)
{
	jn_subr_decs copy = jn_subr_decs_create();
	int ndecs = subrs->size();
	for (int i = 0; i < ndecs; i++)
	{
		copy->append(copy_subr_dec(subrs->get(i)));
	}
	return copy;
}

jn_subr copy_subr_dec(jn_subr subr)
{
	string vtype = subr->vtype;
	string name = subr->name;
	jn_param_list params = copy_param_list(subr->params);
	jn_subr_body body = copy_subr_body(subr->body);

	switch (ast_kind_of(subr))
	{
	case k_jn_constructor:
		return to_jn_subr(jn_constructor_create(vtype, name, params, body));

	case k_jn_function:
		return to_jn_subr(jn_function_create(vtype, name, params, body));

	case k_jn_method:
	default:
		return to_jn_subr(jn_method_create(vtype, name, params, body));
	}
}

jn_param_list copy_param_list(jn_param_list params)
{
	jn_param_list copy = jn_param_list_create();
	int ndecs = params->size();
	for (int i = 0; i < ndecs; i++)
	{
		copy->append(copy_var_dec(params->get(i)));
	}
	return copy;
}

jn_subr_body copy_subr_body(jn_subr_body subr)
{
	jn_var_decs decs = copy_var_decs(subr->decs);
	jn_statements statements = copy_statements(subr->body);

	return jn_subr_body_create(decs, statements);
}

jn_statements copy_statements(jn_statements statements)
{
	jn_statements copy = jn_statements_create();
	int nstatements = statements->size();
	for (int i = 0; i < nstatements; i++)
	{
		jn_statement stat = statements->get(i);
		copy->append(copy_statement(stat));
		//if the current statement could see as a return, return immediately
		if (isReturn(stat))
		{
			return copy;
		}
	}

	return copy;
}

//check if the current statement could see as a return
bool isReturn(jn_statement stat)
{
	bool tf = false;
	switch (stat->kind)
	{
	case k_jn_if:
	case k_jn_if_else:
		tf = checkIf(to_jn_if(stat));
		break;
	case k_jn_while:
		tf = checkWhile(to_jn_while(stat));
		break;
	case k_jn_return:
	case k_jn_return_expr:
		tf = true;
		break;
	}
	return tf;
}

//check if a "if" statement could see as a return
bool checkIf(jn_if if_s)
{
	bool if_tf = false;
	bool else_tf = false;

	jn_statements stats = if_s->if_true;
	int size = stats->size();
	for (int i = 0; i < size; i++)
	{
		jn_statement temp = stats->get(i);
		if_tf = isReturn(temp);
		if (if_tf)
			break;
	}
	if (if_s->kind == k_jn_if)
	{
		return if_tf;
	}
	else
	{
		stats = to_jn_if_else(if_s)->if_false;
		int elseSize = stats->size();
		for (int i = 0; i < elseSize; i++)
		{
			jn_statement temp = stats->get(i);
			else_tf = isReturn(temp);
			if (else_tf)
				break;
		}
		return if_tf && else_tf;
	}
}

//check if a "while" statement could see as a return
bool checkWhile(jn_while while_s)
{
	jn_expr cond = while_s->cond;
	int condition = 0;
	if (cond->kind == k_jn_bool)
	{
		if (to_jn_bool(cond)->tf)
		{
			jn_statements stats = to_jn_while(while_s)->body;
			int size = stats->size();
			for (int i = 0; i < size; i++)
			{
				jn_statement temp = stats->get(i);
				if (isReturn(temp))
					return true;
			}
		}
	}
	return false;
}

jn_statement copy_statement(jn_statement statement)
{
	switch (ast_kind_of(statement))
	{
	case k_jn_let:
	case k_jn_let_array:
		return to_jn_statement(copy_let(to_jn_let(statement)));
		break;

	case k_jn_if:
	case k_jn_if_else:
		return to_jn_statement(copy_if(to_jn_if(statement)));
		break;

	case k_jn_while:
		return to_jn_statement(copy_while(to_jn_while(statement)));
		break;

	case k_jn_do:
		return to_jn_statement(copy_do(to_jn_do(statement)));
		break;

	case k_jn_return:
	case k_jn_return_expr:
		return to_jn_statement(copy_return(to_jn_return(statement)));
		break;

	case k_jn_statements:
	default:
		return to_jn_statement(copy_statements(to_jn_statements(statement)));
		break;
	}
}

jn_let copy_let(jn_let let_s)
{
	jn_var var = copy_var(let_s->var);
	jn_expr expr = copy_expr(let_s->expr);

	switch (ast_kind_of(let_s))
	{
	case k_jn_let:
		return jn_let_create(var, expr);

	case k_jn_let_array:
	default:
	{
		jn_let_array let_array = to_jn_let_array(let_s);
		jn_expr index = copy_expr(let_array->index);

		return to_jn_let(jn_let_array_create(var, index, expr));
	}
	}
}

jn_statement copy_if(jn_if if_s)
{
	jn_expr cond = copy_expr(if_s->cond);
	jn_statements if_true = copy_statements(if_s->if_true);
	//condition 1: if(true) 2: if(false)  retrun statements only
	int condition = 0;
	if (cond->kind == k_jn_bool)
	{
		if (to_jn_bool(cond)->tf)
			condition = 1;
		else
			condition = 2;
	}

	switch (ast_kind_of(if_s))
	{
	case k_jn_if:
		if (condition == 1)
			return to_jn_statement(copy_statements(if_true));

		return to_jn_statement(jn_if_create(cond, if_true));

	case k_jn_if_else:
	default:
	{
		jn_if_else if_else_s = to_jn_if_else(if_s);
		jn_statements if_false = copy_statements(if_else_s->if_false);

		if (condition == 1)
		{
			return to_jn_statement(if_true);
		}
		else if (condition == 2)
		{
			return to_jn_statement(if_false);
		}

		return to_jn_statement(jn_if_else_create(cond, if_true, if_false));
	}
	}
}

jn_statement copy_while(jn_while while_s)
{
	jn_expr cond = copy_expr(while_s->cond);
	//while(false)->return empty statements
	if (cond->kind == k_jn_bool && !to_jn_bool(cond)->tf)
	{
		return to_jn_statement(jn_statements_create());
	}
	jn_statements body = copy_statements(while_s->body);

	return to_jn_statement(jn_while_create(cond, body));
}

jn_call copy_call(jn_call call)
{
	bool method_call = call->method_call;
	string class_name = call->class_name;
	string subr_name = call->subr_name;
	jn_expr_list expr_list = copy_expr_list(call->expr_list);

	return jn_call_create(method_call, class_name, subr_name, expr_list);
}

jn_do copy_do(jn_do do_s)
{
	jn_call call = copy_call(do_s->call);

	return jn_do_create(call);
}

jn_return copy_return(jn_return ret_s)
{
	switch (ast_kind_of(ret_s))
	{
	case k_jn_return:
		return jn_return_create();

	case k_jn_return_expr:
	default:
	{
		jn_return_expr ret_e = to_jn_return_expr(ret_s);

		return to_jn_return(jn_return_expr_create(copy_expr(ret_e->expr)));
	}
	}
}

jn_int copy_int(jn_int intc)
{
	int ic = intc->ic;

	return jn_int_create(ic);
}

jn_string copy_string(jn_string stringc)
{
	string sc = stringc->sc;

	return jn_string_create(sc);
}

jn_bool copy_bool(jn_bool boolc)
{
	int tf = boolc->tf;

	return jn_bool_create(tf);
}

jn_array_index copy_array_index(jn_array_index array_index)
{
	jn_var var = copy_var(array_index->var);
	jn_expr index = copy_expr(array_index->index);

	return jn_array_index_create(var, index);
}

jn_unary_op copy_unary_op(jn_unary_op unary_op)
{
	string op = unary_op->op;
	jn_expr expr = copy_expr(unary_op->expr);

	return jn_unary_op_create(op, expr);
}

jn_infix_op copy_infix_op(jn_infix_op infix_op)
{
	jn_expr lhs = copy_expr(infix_op->lhs);
	string op = infix_op->op;
	jn_expr rhs = copy_expr(infix_op->rhs);

	return jn_infix_op_create(lhs, op, rhs);
}

jn_var copy_var(jn_var var)
{
	string segment = var->segment;
	string name = var->name;
	int offset = var->offset;
	string type = var->type;

	return jn_var_create(segment, name, offset, type);
}

jn_expr copy_expr(jn_expr expr)
{
	switch (ast_kind_of(expr))
	{
	case k_jn_int:
	{
		jn_int expr_i = to_jn_int(expr);
		return to_jn_expr(copy_int(expr_i));
	}

	case k_jn_string:
	{
		jn_string expr_s = to_jn_string(expr);
		return to_jn_expr(copy_string(expr_s));
	}

	case k_jn_bool:
	{
		jn_bool expr_b = to_jn_bool(expr);
		return to_jn_expr(copy_bool(expr_b));
	}

	case k_jn_null:
	{
		return to_jn_expr(jn_null_create());
	}

	case k_jn_this:
	{
		return to_jn_expr(jn_this_create());
	}

	case k_jn_var:
	{
		jn_var expr_v = to_jn_var(expr);
		return to_jn_expr(copy_var(expr_v));
	}

	case k_jn_array_index:
	{
		jn_array_index expr_ai = to_jn_array_index(expr);
		return to_jn_expr(copy_array_index(expr_ai));
	}

	case k_jn_unary_op:
	{
		jn_unary_op expr_uop = to_jn_unary_op(expr);
		return to_jn_expr(copy_unary_op(expr_uop));
	}

	case k_jn_infix_op:
	{
		jn_infix_op expr_iop = to_jn_infix_op(expr);
		return to_jn_expr(copy_infix_op(expr_iop));
	}

	case k_jn_call:
	default:
	{
		jn_call expr_c = to_jn_call(expr);
		return to_jn_expr(copy_call(expr_c));
	}
	}
}

jn_expr_list copy_expr_list(jn_expr_list expr_list)
{
	jn_expr_list copy = jn_expr_list_create();
	int nexpressions = expr_list->size();
	for (int i = 0; i < nexpressions; i++)
	{
		copy->append(copy_expr(expr_list->get(i)));
	}
	return copy;
}

jn_class jack_optimiser_r(jn_class t)
{
	return copy_class(t);
}

int main(int argc, char **argv)
{
	// parse an AST in XML, optimise it, print the new AST as XML
	jn_print_as_xml(jack_optimiser_r(jn_parse_xml()), 4);

	// flush the output and any errors
	print_output();
	print_errors();
}
