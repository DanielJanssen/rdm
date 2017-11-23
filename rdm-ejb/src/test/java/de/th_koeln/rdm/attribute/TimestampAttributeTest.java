package de.th_koeln.rdm.attribute;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TimestampAttributeTest {

	@Test
	public void testIsBeforeTodayWithToday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(today());
		assertFalse(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsBeforeTodayWithYesterday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(yesterday());
		assertTrue(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsBeforeTodayWithTomorrow() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(tomorrow());
		assertFalse(serviceUnderTest.isBeforeToday());
	}

	@Test
	public void testIsAfterTodayWithToday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(today());
		assertFalse(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsAfterTodayWithYesterday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(yesterday());
		assertFalse(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsAfterTodayWithTomorrow() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(tomorrow());
		assertTrue(serviceUnderTest.isAfterToday());
	}

	@Test
	public void testIsTodayWithToday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(today());
		assertTrue(serviceUnderTest.isToday());
	}

	@Test
	public void testIsTodayWithYesterday() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(yesterday());
		assertFalse(serviceUnderTest.isToday());
	}

	@Test
	public void testIsTodayWithTomorrow() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(tomorrow());
		assertFalse(serviceUnderTest.isToday());
	}

	@Test
	public void testToString() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(fixDate());
		String expected = "1988-09-09";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToStringWithNullValue() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute();
		String expected = "";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToStringWithNullValue2() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(null);
		String expected = "";
		String actual = serviceUnderTest.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testIsEqualsThanNotEqual() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(tomorrow());
		TimestampAttribute testValue = createTimestampAttribute(yesterday());
		assertFalse(serviceUnderTest.isEqualsThan(testValue));
	}

	@Test
	public void testIsEqualsThanEqual() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(new Date());
		TimestampAttribute testValue = createTimestampAttribute(new Date());
		assertTrue(serviceUnderTest.isEqualsThan(testValue));
	}

	@Test
	public void testIsEqualsThanNull() {
		TimestampAttribute serviceUnderTest = createTimestampAttribute(new Date());
		TimestampAttribute testValue = createTimestampAttribute();
		assertFalse(serviceUnderTest.isEqualsThan(testValue));
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

	private TimestampAttribute createTimestampAttribute(Date aDate) {
		return new TimestampAttribute(aDate) {
			private static final long serialVersionUID = 1L;
		};
	}

	private TimestampAttribute createTimestampAttribute() {
		return new TimestampAttribute() {
			private static final long serialVersionUID = 1L;
		};
	}

}
