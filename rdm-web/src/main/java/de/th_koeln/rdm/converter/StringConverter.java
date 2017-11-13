package de.th_koeln.rdm.converter;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.StringAttribute;

@FacesConverter(forClass = StringAttribute.class)
public class StringConverter extends AttributeConverter<String> {

	@Override
	String toConstructorParameterValue(String aValue) {
		return aValue;
	}

	@Override
	Class<?> getConstructorParameterClass() {
		return String.class;
	}
}
