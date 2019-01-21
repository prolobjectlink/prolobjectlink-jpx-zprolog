package org.worklogic.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.worklogic.BaseTest;
import org.worklogic.db.generator.IncrementGenerator;
import org.worklogic.db.generator.TimestampGenerator;
import org.worklogic.db.generator.UUIDGenerator;
import org.worklogic.domain.geometry.Point;

public class IdGeneratorPersistenceTest extends BaseTest {

	private IncrementGenerator incGenerator = new IncrementGenerator(Point.class);
	private TimestampGenerator timeGenerator = new TimestampGenerator(Point.class);
	private UUIDGenerator uuidGenerator = new UUIDGenerator(Point.class);

	@Test
	public final void testIncrement() {

		storage.getTransaction().begin();
		storage.insert(incGenerator);
		assertTrue(storage.contains(incGenerator));
		assertEquals(incGenerator, storage.find(incGenerator));
		storage.delete(incGenerator);
		assertFalse(storage.contains(new IncrementGenerator(Point.class)));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testTimestamp() {

		storage.getTransaction().begin();
		storage.insert(timeGenerator);
		assertTrue(storage.contains(timeGenerator));
		assertEquals(timeGenerator, storage.find(timeGenerator));
		storage.delete(timeGenerator);
		assertFalse(storage.contains(new TimestampGenerator(Point.class)));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

	@Test
	public final void testUUID() {

		storage.getTransaction().begin();
		storage.insert(uuidGenerator);
		assertTrue(storage.contains(uuidGenerator));
		assertEquals(uuidGenerator, storage.find(uuidGenerator));
		storage.delete(uuidGenerator);
		assertFalse(storage.contains(uuidGenerator));
		storage.getTransaction().commit();
		storage.getTransaction().close();

	}

}
