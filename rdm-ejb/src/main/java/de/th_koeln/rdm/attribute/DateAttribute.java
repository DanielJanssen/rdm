package de.th_koeln.rdm.attribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public abstract class DateAttribute extends Attribute<Date> {

	private static final long serialVersionUID = 1L;
	@Column
	Date value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected DateAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected DateAttribute(Date aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		if (aValue == null) {
			value = null;
		} else {
			value = fixDate(aValue);
		}
	}

	protected Boolean isValid(@SuppressWarnings("unused") Date aValue) {
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		if (isNullOrEmpty()) {
			return "";
		}
		return getDateFormat().format(getValue());
	}

	protected DateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	Date fixDate(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Calendar fixedDate = calendar;
		return fixedDate.getTime();
	}

	public Boolean isBeforeToday() {
		boolean result = today().after(getValue());
		return result;
	}

	public Boolean isAfterToday() {
		boolean result = today().before(getValue());
		return result;
	}

	public Boolean isToday() {
		boolean result = today().equals(getValue());
		return result;
	}

	private Date today() {
		Date today = new Date();
		today = fixDate(today);
		return today;
	}

	@Override
	public Date getValue() {
		return value;
	}

	@Override
	public boolean equalsValue(Attribute<Date> anAttribute) {
		if (getValue() == null) {
			if (anAttribute.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(anAttribute.getValue())) {
			return false;
		}
		return true;
	}

}
