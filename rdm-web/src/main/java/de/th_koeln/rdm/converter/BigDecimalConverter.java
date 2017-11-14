package de.th_koeln.rdm.converter;

import java.math.BigDecimal;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.BigDecimalAttribute;

@FacesConverter(forClass = BigDecimalAttribute.class)
public class BigDecimalConverter extends AttributeConverter<BigDecimal> {

	@Override
	BigDecimal toFromValueParameterValue(String aValue) {
		if (aValue == null) {
			return null;
		}
		return new BigDecimal(aValue);
	}

	@Override
	Class<?> getFromValueParameterClass() {
		return BigDecimal.class;
	}
}
