package de.th_koeln.rdm.attribute;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateAttributeTest {

	@Test
	public void testIsBeforeTodayWithToday() {
		DateAttribute serviceUnderTest = createDateAttribute(today());
		assertFalse(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsBeforeTodayWithYesterday() {
		DateAttribute serviceUnderTest = createDateAttribute(yesterday());
		assertTrue(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsBeforeTodayWithTomorrow() {
		DateAttribute serviceUnderTest = createDateAttribute(tomorrow());
		assertFalse(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsAfterTodayWithToday() {
		DateAttribute serviceUnderTest = createDateAttribute(today());
		assertFalse(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsAfterTodayWithYesterday() {
		DateAttribute serviceUnderTest = createDateAttribute(yesterday());
		assertFalse(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsAfterTodayWithTomorrow() {
		DateAttribute serviceUnderTest = createDateAttribute(tomorrow());
		assertTrue(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsTodayWithToday() {
		DateAttribute serviceUnderTest = createDateAttribute(today());
		assertTrue(serviceUnderTest.isToday());
	}

	@Test
	public void testIsTodayWithYesterday() {
		DateAttribute serviceUnderTest = createDateAttribute(yesterday());
		assertFalse(serviceUnderTest.isToday());
	}

	@Test
	public void testIsTodayWithTomorrow() {
		DateAttribute serviceUnderTest = createDateAttribute(tomorrow());
		assertFalse(serviceUnderTest.isToday());
	}

	@Test
	public void testToString() {
		DateAttribute serviceUnderTest = createDateAttribute(fixDate());
		String expected = "1988-09-09";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToStringWithNullValue() {
		DateAttribute serviceUnderTest = createDateAttribute();
		String expected = "";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToStringWithNullValue2() {
		DateAttribute serviceUnderTest = createDateAttribute(null);
		String expected = "";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testEqualsValueNotEqual() {
		DateAttribute serviceUnderTest = createDateAttribute(tomorrow());
		DateAttribute testValue = createDateAttribute(yesterday());
		assertFalse(serviceUnderTest.equalsValue(testValue));
	}

	@Test
	public void testEqualsValueEqual() {
		DateAttribute serviceUnderTest = createDateAttribute(new Date());
		DateAttribute testValue = createDateAttribute(new Date());
		assertTrue(serviceUnderTest.equalsValue(testValue));
	}

	@Test
	public void testEqualsValueNull() {
		DateAttribute serviceUnderTest = createDateAttribute(new Date());
		DateAttribute testValue = createDateAttribute();
		assertFalse(serviceUnderTest.equalsValue(testValue));
	}

	private Date fixDate() {
		return new Date(88, 8, 9);
	}

	private Date today() {
		return new Date();
	}

	private Date yesterday() {
		Calendar tempYesterday = Calendar.getInstance();
		tempYesterday.setTime(today());
		tempYesterday.add(Calendar.DAY_OF_MONTH, -1);
		Date tempTime = tempYesterday.getTime();
		return tempTime;
	}

	private Date tomorrow() {
		Calendar tempTomorrow = Calendar.getInstance();
		tempTomorrow.setTime(today());
		tempTomorrow.add(Calendar.DAY_OF_MONTH, 1);
		Date tempTime = tempTomorrow.getTime();
		return tempTime;
	}

	private DateAttribute createDateAttribute(Date aDate) {
		return new DateAttribute(aDate) {
			private static final long serialVersionUID = 1L;
		};
	}

	private DateAttribute createDateAttribute() {
		return new DateAttribute() {
			private static final long serialVersionUID = 1L;
		};
	}

}
