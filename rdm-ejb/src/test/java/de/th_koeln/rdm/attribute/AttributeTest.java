package de.th_koeln.rdm.attribute;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import de.th_koeln.rdm.attribute.Attribute;

public class AttributeTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testIsGreaterThan() {
		Attribute<Integer> sut = Mockito.mock(Attribute.class, Mockito.CALLS_REAL_METHODS);
		Mockito.when(sut.getValue()).thenReturn(2);
		Attribute<Integer> comparable = Mockito.mock(Attribute.class);
		Mockito.when(sut.getValue()).thenReturn(1);

		assertTrue(sut.isGreaterThan(comparable));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testIsNullOrEmpty() {
		Attribute<String> sut = Mockito.mock(Attribute.class, Mockito.CALLS_REAL_METHODS);
		assertTrue(sut.isNullOrEmpty());
		Mockito.when(sut.getValue()).thenReturn("");
		assertTrue(sut.isNullOrEmpty());
		Mockito.when(sut.getValue()).thenReturn("test");
		assertFalse(sut.isNullOrEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testToString() {
		Attribute<String> sut = Mockito.mock(Attribute.class, Mockito.CALLS_REAL_METHODS);
		assertEquals("", sut.toString());
		Mockito.when(sut.getValue()).thenReturn("");
		assertEquals("", sut.toString());
		Mockito.when(sut.getValue()).thenReturn("test");
		assertEquals("test", sut.toString());
	}
}
