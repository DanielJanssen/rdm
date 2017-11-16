package de.th_koeln.rdm.converter;

import javax.faces.convert.FacesConverter;

import de.th_koeln.rdm.attribute.ShortAttribute;

@FacesConverter(forClass = ShortAttribute.class)
public class ShortConverter extends AttributeConverter<Short> {

	@Override
	Short toFromValueParameterValue(String aValue) {
		if (aValue == null) {
			return null;
		}
		return Short.valueOf(aValue);
	}

	@Override
	Class<?> getFromValueParameterClass() {
		return Short.class;
	}
}
