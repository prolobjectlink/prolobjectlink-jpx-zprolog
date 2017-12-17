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
package org.logicware.jpd.jpi.zprolog;

import org.logicware.jpd.Cache;
import org.logicware.jpd.ContainerFactory;
import org.logicware.jpd.Documents;
import org.logicware.jpd.Properties;
import org.logicware.jpd.jpi.JPICache;
import org.logicware.jpd.jpi.JPIDocuments;
import org.logicware.jpi.zprolog.ZPrologProvider;

public final class JPIZPrologDocuments extends JPIDocuments {

    static final Documents instance = new JPIZPrologDocuments();

    protected JPIZPrologDocuments() {
	super(new Properties(), new ZPrologProvider());
    }

    @Override
    public Documents getInstance() {
	return instance;
    }

    public Cache createCache() {
	return new JPICache(getProvider());
    }

    @Override
    public ContainerFactory createContainerFactory() {
	return new JPIZPrologContainerFactory(getProperties(), getProvider());
    }

}
