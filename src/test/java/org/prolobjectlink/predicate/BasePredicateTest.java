/*
 * #%L
 * prolobjectlink-jpd
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
package org.prolobjectlink.predicate;

import org.junit.Assert;
import org.prolobjectlink.BaseTest;
import org.prolobjectlink.db.Predicate;
import org.prolobjectlink.db.predicate.NotNullPredicate;
import org.prolobjectlink.domain.geometry.Point;

/** @author Jose Zalacain @since 1.0 */
public abstract class BasePredicateTest extends BaseTest {

	protected Predicate<Point> leftPredicate = new NotNullPredicate<Point>();
	protected Predicate<Point> rigthPredicate = new Predicate<Point>() {

		public boolean evaluate(Point o) {
			return o != null ? o.getX() < o.getY() : false;
		}
	};

	protected <T> void assertFalse(final Predicate<T> predicate, final T testObject) {
		Assert.assertFalse(predicate.evaluate(testObject));
	}

	protected <T> void assertTrue(final Predicate<T> predicate, final T testObject) {
		Assert.assertTrue(predicate.evaluate(testObject));
	}

}
