package de.th_koeln.rdm.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.DateAttribute;

@FacesConverter(forClass = DateAttribute.class)
public class DateConverter extends AttributeConverter<Date> {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	Date toFromValueParameterValue(String aValue) {
		if (aValue == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			return format.parse(aValue);
		} catch (ParseException e) {
			throw new ConverterException("Cannot parse: " + aValue + " with format " + DATE_FORMAT + " to a Date", e);
		}
	}

	@Override
	Class<?> getFromValueParameterClass() {
		return Date.class;
	}
}
