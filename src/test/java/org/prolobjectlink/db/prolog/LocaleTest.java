package org.prolobjectlink.db.prolog;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.prolobjectlink.BaseTest;
import org.prolobjectlink.db.prolog.PrologLocale;

public class LocaleTest extends BaseTest {

	private Locale locale = Locale.getDefault();
	private PrologLocale prologLocale = new PrologLocale();

	@Test
	public final void testGetJavaUtilLocale() {
		assertEquals(locale, prologLocale.getJavaUtilLocale());
	}

	@Test
	public final void testGetLanguage() {
		assertEquals(locale.getLanguage(), prologLocale.getLanguage());
	}

	@Test
	public final void testGetCountry() {
		assertEquals(locale.getCountry(), prologLocale.getCountry());
	}

	@Test
	public final void testGetVariant() {
		assertEquals(locale.getVariant(), prologLocale.getVariant());
	}

}
