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

import org.logicware.jpi.PrologClause;
import org.logicware.jpi.PrologTerm;

public class ZPrologClause /* extends AbstractClause */ implements PrologClause {

	private boolean dynamic;
	private boolean multifile;
	private boolean discontiguous;

	protected PrologTerm term;
	protected PrologTerm next;

	ZPrologClause(PrologTerm term) {
		this.term = term;
		if (term.hasIndicator(":-", 2)) {
			this.term = term.getArguments()[0];
			this.next = term.getArguments()[1];
		}
	}

	/**
	 * Used only for goals
	 * 
	 * @param terms
	 *            goal array
	 */
	ZPrologClause(PrologTerm[] terms) {
		term = terms[terms.length - 1];
		for (int i = terms.length; i > 1; i--) {
			term = new ZPrologTerm(term.getProvider(), ",", terms[i - 2], term);
		}
	}

	ZPrologClause(PrologTerm term, PrologTerm... terms) {
		this.term = term;
		if (terms != null && terms.length > 0) {
			this.next = terms[terms.length - 1];
			for (int i = terms.length; i > 1; i--) {
				next = new ZPrologTerm(next.getProvider(), ",", terms[i - 2], next);
			}
		}
	}

	public PrologTerm getTerm() {
		return term;
	}

	public PrologTerm getHead() {
		return term;
	}

	public PrologTerm getBody() {
		return next;
	}

	public String getFunctor() {
		return term.getFunctor();
	}

	public int getArity() {
		return term.getArity();
	}

	public String getIndicator() {
		return term.getIndicator();
	}

	public boolean isDirective() {
		return term == null && next != null;
	}

	public boolean isFact() {
		return term != null && next == null;
	}

	public boolean isRule() {
		return term != null && next != null;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public void markDynamic() {
		dynamic = true;
	}

	public void unmarkDynamic() {
		dynamic = false;
	}

	public boolean isMultifile() {
		return multifile;
	}

	public void markMultifile() {
		multifile = true;
	}

	public void unmarkMultifile() {
		multifile = false;
	}

	public boolean isDiscontiguous() {
		return discontiguous;
	}

	public void markDiscontiguous() {
		discontiguous = true;
	}

	public void unmarkDiscontiguous() {
		discontiguous = false;
	}

	public boolean unify(PrologClause clause) {
		return term.unify(clause.getTerm());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (discontiguous ? 1231 : 1237);
		result = prime * result + (dynamic ? 1231 : 1237);
		result = prime * result + (multifile ? 1231 : 1237);
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZPrologClause other = (ZPrologClause) obj;
		if (discontiguous != other.discontiguous)
			return false;
		if (dynamic != other.dynamic)
			return false;
		if (multifile != other.multifile)
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (isDirective()) {
			PrologTerm b = next;
			String clause = ":-";
			while (b.hasIndicator(",", 2)) {
				clause += b.getArguments()[0];
				clause += ",\n\t";
				b = b.getArguments()[1];
			}
			return clause + b + ".\n";
		} else if (isRule()) {
			PrologTerm h = term;
			PrologTerm b = next;
			String clause = "" + h + " :- \n\t";
			while (b.hasIndicator(",", 2)) {
				clause += b.getArguments()[0];
				clause += ",\n\t";
				b = b.getArguments()[1];
			}
			return clause + b + ".\n";
		}
		return "" + term + ".";
	}

}
