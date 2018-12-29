/*
 * #%L
 * prolobjectlink-db-wamkel
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package org.logicware.jpi.zprolog;

import static org.logicware.jpi.zprolog.ZPrologOperator.FX;
import static org.logicware.jpi.zprolog.ZPrologOperator.FY;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_AFTER;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_AFTER_EQUALS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BEFORE;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BEFORE_EQUALS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_AND;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_COMPLEMENT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_OR;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_SHIFT_LEFT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_SHIFT_RIGHT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_BITWISE_XOR;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_DIV;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_EQUAL;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_EQUIVALENT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_FLOAT_POW;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_GREATER;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_GREATER_EQUAL;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_INTEGER_DIV;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_INT_FLOAT_POW;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_IS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_LESS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_LESS_EQUAL;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_MINUS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_MOD;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_NOT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_NOT_EQUAL;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_NOT_EQUIVALENT;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_NOT_UNIFY;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_PLUS;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_QUERY;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_REM;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_TIMES;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_UNIFY;
import static org.logicware.jpi.zprolog.ZPrologOperator.TOKEN_UNIV;
import static org.logicware.jpi.zprolog.ZPrologOperator.XFX;
import static org.logicware.jpi.zprolog.ZPrologOperator.XFY;
import static org.logicware.jpi.zprolog.ZPrologOperator.YFX;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_ANONIM;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_ATOM;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_BAR;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_COMMA;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_CARRIAGE_RETURN;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_CUT;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_DOT;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_EOF;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_ERROR;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_FLOAT;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_FORM_FEED;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_IF_THEN;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_INTEGER;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_LBRACKET;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_LCURLY;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_LPAR;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_NECK;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_NEW_LINE;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_RBRACKET;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_RCURLY;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_RPAR;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_SEMICOLON;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_SPACE;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_TAB;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_VARIABLE;
import static org.logicware.jpi.zprolog.ZPrologToken.TOKEN_WORD_BOUNDARY;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file 
 * <tt>${basedir}/src/main/jflex/ZPrologScanner.flex</tt>
 */
 
%%

%unicode


%class ZPrologScanner

%type ZPrologToken %function getNextToken

%char
%line
%column



%{ 

  private static final Map<String, Integer> builtins;
	static {

		builtins = new HashMap<String, Integer>();

		// 7.4 directives
		builtins.put(ZPrologBuiltin.DYNAMIC, ZPrologToken.TOKEN_DYNAMIC_BUILTIN);
		builtins.put(ZPrologBuiltin.INCLUDE, ZPrologToken.TOKEN_INCLUDE_BUILTIN);
		builtins.put(ZPrologBuiltin.MULTIFILE, ZPrologToken.TOKEN_MULTIFILE_BUILTIN);
		builtins.put(ZPrologBuiltin.SET_PROLOG_FLAG, ZPrologToken.TOKEN_SET_PROLOG_FLAG_BUILTIN);
		builtins.put(ZPrologBuiltin.ENSURE_LOADED, ZPrologToken.TOKEN_ENSURE_LOADED_BUILTIN);
		builtins.put(ZPrologBuiltin.DISCONTIGUOUS, ZPrologToken.TOKEN_DISCONTIGUOUS_BUILTIN);
		builtins.put(ZPrologBuiltin.CURRENT_PROLOG_FLAG, ZPrologToken.TOKEN_CURRENT_PROLOG_FLAG_BUILTIN);
		builtins.put(ZPrologBuiltin.INITIALIZATION, ZPrologToken.TOKEN_INITIALIZATION_BUILTIN);

		// 7.8 control
		builtins.put(ZPrologBuiltin.NIL, ZPrologToken.TOKEN_NIL);
		builtins.put(ZPrologBuiltin.FAIL_FUNCTOR, ZPrologToken.TOKEN_FAIL);
		builtins.put(ZPrologBuiltin.TRUE_FUNCTOR, ZPrologToken.TOKEN_TRUE);
		builtins.put(ZPrologBuiltin.FALSE_FUNCTOR, ZPrologToken.TOKEN_FALSE);
		builtins.put(ZPrologBuiltin.THROW, ZPrologToken.TOKEN_THROW_BUILTIN);

		// 8.2 term unification
		builtins.put(ZPrologBuiltin.UNIFY_WITH_OCCURS_CHECK, ZPrologToken.TOKEN_UNIFY_WITH_OCCURS_CHECK_BUILTIN);

		// 8.3 type testing
		builtins.put(ZPrologBuiltin.VAR, ZPrologToken.TOKEN_VAR_BUILTIN);
		builtins.put(ZPrologBuiltin.ATOM, ZPrologToken.TOKEN_ATOM_BUILTIN);
		builtins.put(ZPrologBuiltin.FLOAT, ZPrologToken.TOKEN_FLOAT_BUILTIN);
		builtins.put(ZPrologBuiltin.NUMBER, ZPrologToken.TOKEN_NUMBER_BUILTIN);
		builtins.put(ZPrologBuiltin.NONVAR, ZPrologToken.TOKEN_NONVAR_BUILTIN);
		builtins.put(ZPrologBuiltin.OBJECT, ZPrologToken.TOKEN_OBJECT);
		builtins.put(ZPrologBuiltin.GROUND, ZPrologToken.TOKEN_GROUND);
		builtins.put(ZPrologBuiltin.ATOMIC, ZPrologToken.TOKEN_ATOMIC_BUILTIN);
		builtins.put(ZPrologBuiltin.INTEGER, ZPrologToken.TOKEN_INTEGER_BUILTIN);
		builtins.put(ZPrologBuiltin.COMPOUND, ZPrologToken.TOKEN_COMPOUND_BUILTIN);
		builtins.put(ZPrologBuiltin.CALLABLE, ZPrologToken.TOKEN_CALLABLE);
		builtins.put(ZPrologBuiltin.CYCLIC_TERM, ZPrologToken.TOKEN_CYCLIC_TERM);
		builtins.put(ZPrologBuiltin.ACYCLIC_TERM, ZPrologToken.TOKEN_ACYCLIC_TERM);

		// 8.4 term comparison
		builtins.put(ZPrologBuiltin.SORT, ZPrologToken.TOKEN_SORT);
		builtins.put(ZPrologBuiltin.COMPARE, ZPrologToken.TOKEN_COMPARE);

		// 8.5 term creation and decomposition
		builtins.put(ZPrologBuiltin.ARG, ZPrologToken.TOKEN_ARG_BUILTIN);
		builtins.put(ZPrologBuiltin.FUNCTOR, ZPrologToken.TOKEN_FUNCTOR_BUILTIN);
		builtins.put(ZPrologBuiltin.COPY_TERM, ZPrologToken.TOKEN_COPY_TERM_BUILTIN);
		builtins.put(ZPrologBuiltin.TERM_VARIABLES, ZPrologToken.TOKEN_TERM_VARIABLES);

		// 8.6 arithmetic evaluation (operator)
		// 8.7 arithmetic comparison (operator)

		// 8.8 clause retrieval and information
		builtins.put(ZPrologBuiltin.CLAUSE, ZPrologToken.TOKEN_CLAUSE_BUILTIN);
		builtins.put(ZPrologBuiltin.CURRENT_PREDICATE, ZPrologToken.TOKEN_CURRENT_PREDICATE_BUILTIN);

		// 8.9 clause creation and destruction
		builtins.put(ZPrologBuiltin.ABOLISH, ZPrologToken.TOKEN_ABOLISH);
		builtins.put(ZPrologBuiltin.ASSERTA, ZPrologToken.TOKEN_ASSERTA);
		builtins.put(ZPrologBuiltin.ASSERTZ, ZPrologToken.TOKEN_ASSERTZ);
		builtins.put(ZPrologBuiltin.RETRACT, ZPrologToken.TOKEN_RETRACT);

		// 8.10 All solutions
		builtins.put(ZPrologBuiltin.BAGOF, ZPrologToken.TOKEN_BAGOF_BUILTIN);
		builtins.put(ZPrologBuiltin.SETOF, ZPrologToken.TOKEN_SETOF_BUILTIN);
		builtins.put(ZPrologBuiltin.FINDALL, ZPrologToken.TOKEN_FINDALL_BUILTIN);

		// 8.11 Stream Selection and Control
		builtins.put(ZPrologBuiltin.OPEN, ZPrologToken.TOKEN_OPEN);
		builtins.put(ZPrologBuiltin.CLOSE, ZPrologToken.TOKEN_CLOSE);
		builtins.put(ZPrologBuiltin.SET_INPUT, ZPrologToken.TOKEN_SET_INPUT);
		builtins.put(ZPrologBuiltin.SET_OUTPUT, ZPrologToken.TOKEN_SET_OUTPUT);
		builtins.put(ZPrologBuiltin.CURRENT_INPUT, ZPrologToken.TOKEN_CURRENT_INPUT);
		builtins.put(ZPrologBuiltin.CURRENT_OUTPUT, ZPrologToken.TOKEN_CURRENT_OUTPUT);

		// 8.12 character input/output
		// 8.13 byte input/output

		// 8.14 Term input/output
		builtins.put(ZPrologBuiltin.NL, ZPrologToken.TOKEN_NL);
		builtins.put(ZPrologBuiltin.READ, ZPrologToken.TOKEN_READ);
		builtins.put(ZPrologBuiltin.WRITE, ZPrologToken.TOKEN_WRITE);

		// 8.15 logic and control
		builtins.put(ZPrologBuiltin.CALL, ZPrologToken.TOKEN_CALL);
		builtins.put(ZPrologBuiltin.ONCE, ZPrologToken.TOKEN_ONCE);
		builtins.put(ZPrologBuiltin.REPEAT, ZPrologToken.TOKEN_REPEAT);

		// 8.16 atomic term processing
		builtins.put(ZPrologBuiltin.SUB_ATOM, ZPrologToken.TOKEN_SUB_ATOM_BUILTIN);
		builtins.put(ZPrologBuiltin.CHAR_CODE, ZPrologToken.TOKEN_CHAR_CODE_BUILTIN);
		builtins.put(ZPrologBuiltin.ATOM_CHARS, ZPrologToken.TOKEN_ATOM_CHARS_BUILTIN);
		builtins.put(ZPrologBuiltin.ATOM_CODES, ZPrologToken.TOKEN_ATOM_CODES_BUILTIN);
		builtins.put(ZPrologBuiltin.ATOM_LENGTH, ZPrologToken.TOKEN_ATOM_LENGTH_BUILTIN);
		builtins.put(ZPrologBuiltin.ATOM_CONCAT, ZPrologToken.TOKEN_ATOM_CONCAT_BUILTIN);
		builtins.put(ZPrologBuiltin.NUMBER_CHARS, ZPrologToken.TOKEN_NUMBER_CHARS_BUILTIN);
		builtins.put(ZPrologBuiltin.NUMBER_CODES, ZPrologToken.TOKEN_NUMBER_CODES_BUILTIN);

		// 8.17 Implementation defined hooks
		builtins.put(ZPrologBuiltin.OP, ZPrologToken.TOKEN_OP_BUILTIN);
		builtins.put(ZPrologBuiltin.HALT, ZPrologToken.TOKEN_HALT_BUILTIN);
		builtins.put(ZPrologBuiltin.CURRENT_OP, ZPrologToken.TOKEN_CURRENT_OP_BUILTIN);
		builtins.put(ZPrologBuiltin.CHAR_CONVERSION, ZPrologToken.TOKEN_CHAR_CONVERSION_BUILTIN);
		builtins.put(ZPrologBuiltin.CURRENT_CHAR_CONVERSION, ZPrologToken.TOKEN_CURRENT_CHAR_CONVERSION);

		// 9.X Valuable functors
		builtins.put(ZPrologBuiltin.E, ZPrologToken.TOKEN_E);
		builtins.put(ZPrologBuiltin.PI, ZPrologToken.TOKEN_PI);
		builtins.put(ZPrologBuiltin.ABS, ZPrologToken.TOKEN_ABS_BUILTIN);
		builtins.put(ZPrologBuiltin.EXP, ZPrologToken.TOKEN_EXP_BUILTIN);
		builtins.put(ZPrologBuiltin.LOG, ZPrologToken.TOKEN_LOG_BUILTIN);
		builtins.put(ZPrologBuiltin.SIN, ZPrologToken.TOKEN_SIN_BUILTIN);
		builtins.put(ZPrologBuiltin.COS, ZPrologToken.TOKEN_COS_BUILTIN);
		builtins.put(ZPrologBuiltin.MAX, ZPrologToken.TOKEN_MAX);
		builtins.put(ZPrologBuiltin.MIN, ZPrologToken.TOKEN_MIN);
		builtins.put(ZPrologBuiltin.GCD, ZPrologToken.TOKEN_GCD);
		builtins.put(ZPrologBuiltin.LCM, ZPrologToken.TOKEN_LCM);
		builtins.put(ZPrologBuiltin.TAN, ZPrologToken.TOKEN_TAN);
		builtins.put(ZPrologBuiltin.ASIN, ZPrologToken.TOKEN_ASIN);
		builtins.put(ZPrologBuiltin.ACOS, ZPrologToken.TOKEN_ACOS);
		builtins.put(ZPrologBuiltin.ATAN, ZPrologToken.TOKEN_ATAN_BUILTIN);
		builtins.put(ZPrologBuiltin.SIGN, ZPrologToken.TOKEN_SIGN_BUILTIN);
		builtins.put(ZPrologBuiltin.SQRT, ZPrologToken.TOKEN_SQRT_BUILTIN);
		builtins.put(ZPrologBuiltin.CBRT, ZPrologToken.TOKEN_CBRT);
		builtins.put(ZPrologBuiltin.FLOOR, ZPrologToken.TOKEN_FLOOR_BUILTIN);
		builtins.put(ZPrologBuiltin.ROUND, ZPrologToken.TOKEN_ROUND_BUILTIN);
		builtins.put(ZPrologBuiltin.EPSILON, ZPrologToken.TOKEN_EPSILON);
		builtins.put(ZPrologBuiltin.CEILING, ZPrologToken.TOKEN_CEILING_BUILTIN);
		builtins.put(ZPrologBuiltin.TRUNCATE, ZPrologToken.TOKEN_TRUNCATE_BUILTIN);
		builtins.put(ZPrologBuiltin.FLOAT_INTEGER_PART, ZPrologToken.TOKEN_FLOAT_INTEGER_PART_BUILTIN);
		builtins.put(ZPrologBuiltin.FLOAT_FRACTIONAL_PART, ZPrologToken.TOKEN_FLOAT_FRACTIONAL_PART_BUILTIN);

		// non ISO

		// java foreign language
		builtins.put(ZPrologBuiltin.GET, ZPrologToken.TOKEN_GET);
		builtins.put(ZPrologBuiltin.SET, ZPrologToken.TOKEN_SET);
		builtins.put(ZPrologBuiltin.CAST, ZPrologToken.TOKEN_CAST);
		builtins.put(ZPrologBuiltin.INVOKE, ZPrologToken.TOKEN_INVOKE);
		builtins.put(ZPrologBuiltin.INSTANCE_OF, ZPrologToken.TOKEN_INSTANCE_OF);
		builtins.put(ZPrologBuiltin.NEW_INSTANCE, ZPrologToken.TOKEN_NEW_INSTANCE);
		builtins.put(ZPrologBuiltin.LOAD_LIBRARY, ZPrologToken.TOKEN_LOAD_LIBRARY);
		builtins.put(ZPrologBuiltin.OBJECT_CONVERSION, ZPrologToken.TOKEN_OBJECT_CONVERSION);

		// java runtime reflection
		builtins.put(ZPrologBuiltin.CLASS_OF, ZPrologToken.TOKEN_CLASS_OF);
		builtins.put(ZPrologBuiltin.FIELDS_OF, ZPrologToken.TOKEN_FIELDS_OF);
		builtins.put(ZPrologBuiltin.METHODS_OF, ZPrologToken.TOKEN_METHODS_OF);
		builtins.put(ZPrologBuiltin.SUPER_CLASS_OF, ZPrologToken.TOKEN_SUPER_CLASS_OF);
		builtins.put(ZPrologBuiltin.CONSTRUCTORS_OF, ZPrologToken.TOKEN_CONSTRUCTORS_OF);

		// runtime statistics
		builtins.put(ZPrologBuiltin.STATISTICS, ZPrologToken.TOKEN_STATISTICS);
		builtins.put(ZPrologBuiltin.CURRENT_TIME, ZPrologToken.TOKEN_CURRENT_TIME);

  }
 	
  private ZPrologToken token(int type, String value) {
	return new ZPrologToken(type, yyline + 1, yycolumn + 1, yychar+1, value);
  }
	
  private ZPrologOperator operator(int type, String value, int priority, int position) {
	return new ZPrologOperator(type, yyline + 1, yycolumn + 1, yychar+1, value, priority, position);
  }

%}

%init{ 
  yybegin( NORMALSTATE ); 
%init} 


newline  = \r|\n|\r\n
atom 	 = [a-z][A-Za-z0-9_]*
complex	 = \'.[^\r\n\'\\]*.\'
variable = [A-Z_][A-Za-z0-9_]*
integer  = 0 | [1-9][0-9]*
float	 = [0-9]+ \. [0-9]+ | [0-9]+ \. [0-9]+

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
comment = {traditionalComment} | {endOfLineComment} | 
          {documentationComment}

traditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
endOfLineComment = "%" {InputCharacter}* {LineTerminator}?
documentationComment = "/*" "*"+ [^/*] ~"*/"


%state NORMALSTATE ERRORSTATE 
 
%%
 
<NORMALSTATE> { 

	/* separators */
    "!"	   { return token(TOKEN_CUT,  yytext()); }
    "."	   { return token(TOKEN_DOT,  yytext()); }
    "|"    { return token(TOKEN_BAR,  yytext()); }
    "("    { return token(TOKEN_LPAR,  yytext()); }
    ")"    { return token(TOKEN_RPAR,  yytext()); }
    "["    { return token(TOKEN_LBRACKET,  yytext()); }
    "]"    {  return token(TOKEN_RBRACKET,  yytext()); }
    "{"    { return token(TOKEN_LCURLY,  yytext()); }
    "}"    { return token(TOKEN_RCURLY,  yytext()); }
    
    /* operators */
    "?-"   { return operator(TOKEN_QUERY,  yytext(), 1200, FX); }
    ":-"   { return operator(TOKEN_NECK,  yytext(), 1200, XFX); }
    ";"    { return operator(TOKEN_SEMICOLON,  yytext(), 1100, XFY); }
    "->"   { return operator(TOKEN_IF_THEN,  yytext(), 1050, XFY); }
    ","    { return operator(TOKEN_COMMA,  yytext(), 1000, XFY); }
    "\\+"  { return operator(TOKEN_NOT,  yytext(), 900, FY); }
    "="    { return operator(TOKEN_UNIFY,  yytext(), 700, XFX); }
    "<"    { return operator(TOKEN_LESS,  yytext(), 700, XFX); }
    ">"    { return operator(TOKEN_GREATER,  yytext(), 700, XFX); }
    "is"   { return operator(TOKEN_IS,  yytext(), 700, XFX); }
    "=<"   { return operator(TOKEN_LESS_EQUAL,  yytext(), 700, XFX); }
    ">="   { return operator(TOKEN_GREATER_EQUAL,  yytext(), 700, XFX); }
    "=="   { return operator(TOKEN_EQUIVALENT,  yytext(), 700, XFX); }
    "\\="  { return operator(TOKEN_NOT_UNIFY,  yytext(), 700, XFX); }
    "\\==" { return operator(TOKEN_NOT_EQUIVALENT,  yytext(), 700, XFX); }
    "=:="  { return operator(TOKEN_EQUAL,  yytext(), 700, XFX); }
    "=\\=" { return operator(TOKEN_NOT_EQUAL,  yytext(), 700, XFX); }
    "=.."  { return operator(TOKEN_UNIV,  yytext(), 700, XFX); }
    "@<"   { return operator(TOKEN_BEFORE,  yytext(), 700, XFX); }
    "@>"   { return operator(TOKEN_AFTER,  yytext(), 700, XFX); }
    "@=<"  { return operator(TOKEN_BEFORE_EQUALS,  yytext(), 700, XFX); }
    "@>="  { return operator(TOKEN_AFTER_EQUALS,  yytext(), 700, XFX); }
    "+"    { return operator(TOKEN_PLUS,  yytext(), 500, YFX); }
    "-"    { return operator(TOKEN_MINUS,  yytext(), 500, YFX); }
    "/\\"  { return operator(TOKEN_BITWISE_AND,  yytext(), 500, YFX); }
    "\\/"  { return operator(TOKEN_BITWISE_OR,  yytext(), 500, YFX); }
    "><"   { return operator(TOKEN_BITWISE_XOR,  yytext(), 500, YFX); }
    "xor"  { return operator(TOKEN_BITWISE_XOR,  yytext(), 500, YFX); }
    "*"    { return operator(TOKEN_TIMES,  yytext(), 400, YFX); }
    "/"    { return operator(TOKEN_DIV,  yytext(), 400, YFX); }
    "//"   { return operator(TOKEN_INTEGER_DIV,  yytext(), 400, YFX); }
    "mod"  { return operator(TOKEN_MOD,  yytext(), 400, YFX); }
    "rem"  { return operator(TOKEN_REM,  yytext(), 400, YFX); }
    ">>"   { return operator(TOKEN_BITWISE_SHIFT_RIGHT,  yytext(), 400, YFX); }
    "<<"   { return operator(TOKEN_BITWISE_SHIFT_LEFT,  yytext(), 400, YFX); }
    "^"    { return operator(TOKEN_INT_FLOAT_POW,  yytext(), 200, XFY); }
    "**"   { return operator(TOKEN_FLOAT_POW,  yytext(), 200, XFX); }
    "\\"   { return operator(TOKEN_BITWISE_COMPLEMENT,  yytext(), 200, FY); }
    
    "@"    { return token(TOKEN_ATOM,  yytext()); }
    "#"    { return token(TOKEN_ATOM,  yytext()); }
    "?"    { return token(TOKEN_ATOM,  yytext()); }
    "_"    { return token(TOKEN_ANONIM,  yytext()); }
    
    {float} { return token(TOKEN_FLOAT,  yytext()); }
    {integer} { return token(TOKEN_INTEGER,  yytext()); }
    {variable} { return token(TOKEN_VARIABLE,  yytext()); }
    {complex} { return token(TOKEN_ATOM,  yytext()); }
    
    {atom} { 
                if (builtins.containsKey(yytext())) {
	  				return token(builtins.get(yytext()),  yytext());
	            }
    			return token(TOKEN_ATOM,  yytext()); 
    		}
    
    " "    { return token(TOKEN_SPACE,  yytext()); }
    "\t"   { return token(TOKEN_TAB,  yytext()); }
    "\b"   { return token(TOKEN_WORD_BOUNDARY,  yytext()); }
    "\f"   { return token(TOKEN_FORM_FEED,  yytext()); }
    "\r"   { return token(TOKEN_CARRIAGE_RETURN,  yytext()); }
    "\n"   { return token(TOKEN_NEW_LINE,  yytext()); }

   {comment}        { /* ignore */ }
    
	.               { 
		               yybegin( ERRORSTATE );  
		               return token(TOKEN_ERROR,  yytext()); 
               		} 
} 

<ERRORSTATE> { 
       "."           { 
                      yybegin( NORMALSTATE );  
                      return token(TOKEN_DOT,  yytext()); 
                     } 
       .             { } 
  } 
 
<<EOF>>         {  return token(TOKEN_EOF,  "EOF");  } 