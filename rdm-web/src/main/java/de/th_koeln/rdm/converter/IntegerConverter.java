package de.th_koeln.rdm.converter;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.IntegerAttribute;

@FacesConverter(forClass = IntegerAttribute.class)
public class IntegerConverter extends AttributeConverter<Integer> {

	@Override
	Integer toConstructorParameterValue(String aValue) {
		if (aValue == null) {
			return null;
		}
		return Integer.valueOf(aValue);
	}

	@Override
	Class<?> getConstructorParameterClass() {
		return Integer.class;
	}
}
