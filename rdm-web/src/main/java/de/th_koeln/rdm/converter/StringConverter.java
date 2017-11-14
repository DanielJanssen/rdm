package de.th_koeln.rdm.converter;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.StringAttribute;

@FacesConverter(forClass = StringAttribute.class)
public class StringConverter extends AttributeConverter<String> {

	@Override
	String toFromValueParameterValue(String aValue) {
		return aValue;
	}

	@Override
	Class<?> getFromValueParameterClass() {
		return String.class;
	}
}
