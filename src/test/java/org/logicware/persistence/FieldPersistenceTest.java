package org.logicware.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.logicware.BaseTest;
import org.logicware.domain.classes.NonFieldClass;
import org.logicware.domain.classes.StaticFieldClass;
import org.logicware.domain.classes.StaticFinalFieldClass;
import org.logicware.domain.classes.TransientFieldClass;

public class FieldPersistenceTest extends BaseTest {

	@Test
	public final void testEmpty() {

		storage.getTransaction().begin();
		storage.insert(new NonFieldClass());
		assertTrue(storage.contains(NonFieldClass.class));
		assertTrue(storage.contains(new NonFieldClass()));
		assertEquals(new NonFieldClass(), storage.find(new NonFieldClass()));
		storage.delete(new NonFieldClass());
		assertFalse(storage.contains(NonFieldClass.class));
		assertFalse(storage.contains(new NonFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testStatic() {

		storage.getTransaction().begin();
		storage.insert(new StaticFieldClass());
		assertTrue(storage.contains(StaticFieldClass.class));
		assertTrue(storage.contains(new StaticFieldClass()));
		assertEquals(new StaticFieldClass(), storage.find(new StaticFieldClass()));
		storage.delete(new StaticFieldClass());
		assertFalse(storage.contains(StaticFieldClass.class));
		assertFalse(storage.contains(new StaticFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testStaticFinal() {

		storage.getTransaction().begin();
		storage.insert(new StaticFinalFieldClass());
		assertTrue(storage.contains(StaticFinalFieldClass.class));
		assertTrue(storage.contains(new StaticFinalFieldClass()));
		assertEquals(new StaticFinalFieldClass(), storage.find(new StaticFinalFieldClass()));
		storage.delete(new StaticFinalFieldClass());
		assertFalse(storage.contains(StaticFinalFieldClass.class));
		assertFalse(storage.contains(new StaticFinalFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testTransient() {

		storage.getTransaction().begin();
		storage.insert(new TransientFieldClass());
		assertTrue(storage.contains(new TransientFieldClass()));
		assertEquals(new TransientFieldClass(), storage.find(new TransientFieldClass()));
		storage.delete(new TransientFieldClass());
		assertFalse(storage.contains(new TransientFieldClass()));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

}
