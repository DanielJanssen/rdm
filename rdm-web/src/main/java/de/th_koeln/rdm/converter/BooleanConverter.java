package de.th_koeln.rdm.converter;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.BooleanAttribute;

@FacesConverter(forClass = BooleanAttribute.class)
public class BooleanConverter extends AttributeConverter<Boolean> {

	@Override
	Boolean toFromValueParameterValue(String aValue) {
		if (aValue == null) {
			return null;
		}
		return Boolean.valueOf(aValue);
	}

	@Override
	Class<?> getFromValueParameterClass() {
		return Boolean.class;
	}
}
