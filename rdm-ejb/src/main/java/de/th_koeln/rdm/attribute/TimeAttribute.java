package de.th_koeln.rdm.attribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@Embeddable
public abstract class TimeAttribute extends Attribute<Date> {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIME)
	@Column
	Date value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected TimeAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 */
	@Deprecated
	protected TimeAttribute(Date aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	public Date getValue() {
		return value;
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

	Date fixTime(Date aDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);
		calendar.set(Calendar.DAY_OF_YEAR, 0);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 0);
		Calendar fixedDate = calendar;
		return fixedDate.getTime();
	}
}