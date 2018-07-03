/*
 * #%L
 * prolobjectlink-db-zprolog
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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
package org.logicware.prolog.wamkel;

import static org.logicware.pdb.prolog.PrologTermType.ATOM_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.CUT_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.DOUBLE_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.EMPTY_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.FAIL_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.FALSE_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.FLOAT_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.INTEGER_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.LIST_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.LONG_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.NIL_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.STRUCTURE_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.TRUE_TYPE;
import static org.logicware.pdb.prolog.PrologTermType.VARIABLE_TYPE;
import static org.logicware.prolog.wamkel.ZPrologTerm.CUT_TERM;
import static org.logicware.prolog.wamkel.ZPrologTerm.EMPTY_TERM;
import static org.logicware.prolog.wamkel.ZPrologTerm.FAIL_TERM;
import static org.logicware.prolog.wamkel.ZPrologTerm.FALSE_TERM;
import static org.logicware.prolog.wamkel.ZPrologTerm.NIL_TERM;
import static org.logicware.prolog.wamkel.ZPrologTerm.TRUE_TERM;

import org.logicware.pdb.prolog.AbstractConverter;
import org.logicware.pdb.prolog.PrologAtom;
import org.logicware.pdb.prolog.PrologConverter;
import org.logicware.pdb.prolog.PrologDouble;
import org.logicware.pdb.prolog.PrologFloat;
import org.logicware.pdb.prolog.PrologInteger;
import org.logicware.pdb.prolog.PrologLong;
import org.logicware.pdb.prolog.PrologProvider;
import org.logicware.pdb.prolog.PrologTerm;
import org.logicware.pdb.prolog.PrologVariable;
import org.logicware.pdb.prolog.UnknownTermError;

public class ZPrologConverter extends AbstractConverter<ZPrologTerm> implements PrologConverter<ZPrologTerm> {

	public PrologTerm toTerm(ZPrologTerm term) {
		return term;
	}

	public ZPrologTerm fromTerm(PrologTerm term) {
		switch (term.getType()) {
		case NIL_TYPE:
			return NIL_TERM;
		case CUT_TYPE:
			return CUT_TERM;
		case FAIL_TYPE:
			return FAIL_TERM;
		case TRUE_TYPE:
			return TRUE_TERM;
		case FALSE_TYPE:
			return FALSE_TERM;
		case EMPTY_TYPE:
			return EMPTY_TERM;
		case ATOM_TYPE:
			return new ZPrologTerm(provider, ((PrologAtom) term).getStringValue());
		case FLOAT_TYPE:
			return new ZPrologTerm(provider, ((PrologFloat) term).getFloatValue());
		case INTEGER_TYPE:
			return new ZPrologTerm(provider, ((PrologInteger) term).getIntValue());
		case DOUBLE_TYPE:
			return new ZPrologTerm(provider, ((PrologDouble) term).getDoubleValue());
		case LONG_TYPE:
			return new ZPrologTerm(provider, ((PrologLong) term).getLongValue());
		case VARIABLE_TYPE:
			PrologVariable v = (PrologVariable) term;
			String name = v.getName();
			ZPrologTerm variable = sharedPrologVariables.get(name);
			if (variable == null) {
				variable = new ZPrologTerm(provider, name, v.getPosition());
				sharedPrologVariables.put(name, variable);
			}
			return variable;
		case LIST_TYPE:
			return new ZPrologTerm(provider, term.getArguments());
		case STRUCTURE_TYPE:
			String functor = term.getFunctor();
			return new ZPrologTerm(provider, functor, term.getArguments());
		default:
			throw new UnknownTermError(term);
		}
	}

	public ZPrologTerm[] fromTermArray(PrologTerm[] terms) {
		ZPrologTerm[] prologTerms = new ZPrologTerm[terms.length];
		for (int i = 0; i < terms.length; i++) {
			prologTerms[i] = fromTerm(terms[i]);
		}
		return prologTerms;
	}

	public ZPrologTerm fromTerm(PrologTerm head, PrologTerm[] body) {
		ZPrologTerm clauseHead = fromTerm(head);
		if (body != null && body.length > 0) {
			ZPrologTerm clauseBody = fromTerm(body[body.length - 1]);
			for (int i = body.length - 2; i >= 0; --i) {
				clauseBody = new ZPrologTerm(provider, ",", fromTerm(body[i]), clauseBody);
			}
			return new ZPrologTerm(provider, ":-", clauseHead, clauseBody);
		}
		return clauseHead;
	}

	public PrologProvider createProvider() {
		return new ZPrologProvider(this);
	}

}
