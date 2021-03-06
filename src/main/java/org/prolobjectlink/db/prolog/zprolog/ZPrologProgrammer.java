/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.db.prolog.zprolog;

import java.io.PrintWriter;

import org.prolobjectlink.db.prolog.PrologProgrammer;
import org.prolobjectlink.web.application.AbstractViewProgrammer;

import io.github.prolobjectlink.prolog.PrologProvider;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class ZPrologProgrammer extends AbstractViewProgrammer implements PrologProgrammer {

	public ZPrologProgrammer(PrologProvider provider) {
		super(provider);
	}

	public void codingInclusion(PrintWriter programmer, String jarEntryName) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < jarEntryName.lastIndexOf('/'); i++) {
			if (jarEntryName.charAt(i) == '/') {
				b.append("../");
			}
		}
		b.append("../../obj/prolobject.pl");
		programmer.println(":-" + provider.prologInclude("" + b + "") + ".");
		programmer.println();
	}

	public void codingInclusion(PrintWriter programmer, String jarEntryName, String dao) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < jarEntryName.lastIndexOf('/'); i++) {
			if (jarEntryName.charAt(i) == '/') {
				b.append("../");
			}
		}
		b.append("../../" + dao);
		programmer.println(":-" + provider.prologInclude("" + b + "") + ".");
		programmer.println();
	}

}
