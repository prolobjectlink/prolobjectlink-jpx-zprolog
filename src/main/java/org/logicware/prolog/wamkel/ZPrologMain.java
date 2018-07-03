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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;

import org.logicware.pdb.prolog.PrologEngine;
import org.logicware.pdb.prolog.PrologProvider;
import org.logicware.pdb.prolog.PrologQuery;
import org.logicware.pdb.prolog.PrologTerm;

class ZPrologMain {

	// default input stream
	private static final InputStreamReader reader = new InputStreamReader(System.in);

	// buffered reader for read from standard input stream
	private static final BufferedReader stdin = new BufferedReader(reader);

	// standard output stream
	private static final PrintStream stdout = System.out;

	private static final PrologProvider provider = new ZPrologProvider();

	public static void main(String args[]) {

		String stringQuery;
		PrologEngine engine = provider.newEngine();

		stdout.println();
		// stdout.print(engine.getName());
		stdout.print(" v");
		stdout.println(engine.getVersion());
		// stdout.println(engine.getCopyright());
		// stdout.println(engine.getPoweredBy());
		stdout.println();

		try {

			if (args.length > 0) {
				engine.consult(args[0]);
			}

			stdout.print("?- ");
			stdout.flush();
			stringQuery = stdin.readLine();

			while (true) {

				if (!stringQuery.equals("")) {
					stdout.println();
					PrologQuery query = engine.query(stringQuery);
					if (query.hasSolution()) {
						Map<String, PrologTerm> s = query.oneVariablesSolution();
						for (String key : s.keySet()) {
							stdout.println(key + " = " + s.get(key));
						}
						stdout.println("Yes.");
					} else {
						stdout.println("No.");
					}

					stdout.println();
					// stdout.println("Stack Size: " + query.stack.size());
					// stdout.println("Backtracks: " + query.getBacktracks());
					// stdout.println("Inferences: " + query.getInferences());
					// stdout.println("Unifications: " +
					// query.getUnifications());
					// stdout.println("Query time: " + query.getCputime() + "
					// seconds.");
					// stdout.println(" LIPS: " + query.getInferences() /
					// query.getCputime());
					stdout.println();

				} else {
					stdout.println("Emty query");
					stdout.println();
				}

				stdout.print("?- ");
				stdout.flush();
				stringQuery = stdin.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
