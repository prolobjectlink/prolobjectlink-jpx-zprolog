/*
 * #%L
 * prolobjectlink-db-zprolog
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
package org.logicware.prolog.zprolog;

import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;
import org.worklogic.db.ContainerFactory;
import org.worklogic.db.HierarchicalCache;
import org.worklogic.db.ObjectConverter;
import org.worklogic.db.etc.Settings;
import org.worklogic.db.prolog.PrologHierarchicalCache;

public class ZPrologHierarchicalCache extends PrologHierarchicalCache implements HierarchicalCache {

	public ZPrologHierarchicalCache(PrologProvider provider, Settings settings, ContainerFactory containerFactory) {
		super(provider, settings, new ZPrologContainerFactory(settings));
	}

	public ZPrologHierarchicalCache(PrologProvider provider, Settings settings, ObjectConverter<PrologTerm> converter,
			ContainerFactory containerFactory) {
		super(provider, settings, converter, new ZPrologContainerFactory(settings));
	}

}
