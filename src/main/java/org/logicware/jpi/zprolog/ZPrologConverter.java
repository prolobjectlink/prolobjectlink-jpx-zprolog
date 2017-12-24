/*
 * #%L
 * prolobjectlink-zprolog
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
package org.logicware.jpi.zprolog;

import static org.logicware.jpi.zprolog.ZPrologTerm.CUT_TERM;
import static org.logicware.jpi.zprolog.ZPrologTerm.EMPTY_TERM;
import static org.logicware.jpi.zprolog.ZPrologTerm.FAIL_TERM;
import static org.logicware.jpi.zprolog.ZPrologTerm.FALSE_TERM;
import static org.logicware.jpi.zprolog.ZPrologTerm.NIL_TERM;
import static org.logicware.jpi.zprolog.ZPrologTerm.TRUE_TERM;

import org.logicware.jpi.AbstractConverter;
import org.logicware.jpi.PrologAtom;
import org.logicware.jpi.PrologConverter;
import org.logicware.jpi.PrologDouble;
import org.logicware.jpi.PrologFloat;
import org.logicware.jpi.PrologInteger;
import org.logicware.jpi.PrologLong;
import org.logicware.jpi.PrologProvider;
import org.logicware.jpi.PrologTerm;
import org.logicware.jpi.PrologVariable;
import org.logicware.jpi.UnknownTermError;

public class ZPrologConverter extends AbstractConverter<ZPrologTerm> implements PrologConverter<ZPrologTerm> {

	public PrologTerm toTerm(ZPrologTerm term) {
		return term;
	}

	public ZPrologTerm fromTerm(PrologTerm term) {
		switch (term.getType()) {
		case PrologTerm.NIL_TYPE:
			return NIL_TERM;
		case PrologTerm.CUT_TYPE:
			return CUT_TERM;
		case PrologTerm.FAIL_TYPE:
			return FAIL_TERM;
		case PrologTerm.TRUE_TYPE:
			return TRUE_TERM;
		case PrologTerm.FALSE_TYPE:
			return FALSE_TERM;
		case PrologTerm.EMPTY_TYPE:
			return EMPTY_TERM;
		case PrologTerm.ATOM_TYPE:
			return new ZPrologTerm(provider, ((PrologAtom) term).getStringValue());
		case PrologTerm.FLOAT_TYPE:
			return new ZPrologTerm(provider, ((PrologFloat) term).getFloatValue());
		case PrologTerm.INTEGER_TYPE:
			return new ZPrologTerm(provider, ((PrologInteger) term).getIntValue());
		case PrologTerm.DOUBLE_TYPE:
			return new ZPrologTerm(provider, ((PrologDouble) term).getDoubleValue());
		case PrologTerm.LONG_TYPE:
			return new ZPrologTerm(provider, ((PrologLong) term).getLongValue());
		case PrologTerm.VARIABLE_TYPE:
			PrologVariable v = (PrologVariable) term;
			String name = v.getName();
			ZPrologTerm variable = sharedPrologVariables.get(name);
			if (variable == null) {
				variable = new ZPrologTerm(provider, name, v.getPosition());
				sharedPrologVariables.put(name, variable);
			}
			return variable;
		case PrologTerm.LIST_TYPE:
			return new ZPrologTerm(provider, term.getArguments());
		case PrologTerm.STRUCTURE_TYPE:
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
				clauseBody = new ZPrologTerm(provider, ",", new ZPrologTerm[] { fromTerm(body[i]), clauseBody });
			}
			return new ZPrologTerm(provider, ":-", new ZPrologTerm[] { clauseHead, clauseBody });
		}
		return clauseHead;
	}

	public PrologProvider createProvider() {
		return new ZPrologProvider(this);
	}

}
