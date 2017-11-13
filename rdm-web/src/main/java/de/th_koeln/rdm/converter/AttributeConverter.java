package de.th_koeln.rdm.converter;

import java.lang.reflect.Method;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import de.th_koeln.rdm.attribute.Attribute;

public abstract class AttributeConverter<T> implements Converter {

	abstract T toConstructorParameterValue(String aValue);

	abstract Class<?> getConstructorParameterClass();

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext aContext, @SuppressWarnings("unused") UIComponent aComponent, Object aValue)
			throws ConverterException {
		Attribute<?> value = (Attribute<?>) aValue;
		return value.toString();
	}

	@Override
	public Object getAsObject(FacesContext aContext, UIComponent aComponent, String aValue) throws ConverterException {
		Object value;
		try {
			value = toConstructorParameterValue(aValue);
		} catch (NumberFormatException e) {
			throw new ConverterException("Only a number is allowed");
		}

		ValueExpression expression = aComponent.getValueExpression("value");
		Class<?> classToBeCreated = expression.getType(aContext.getELContext());
		try {
			Method method = classToBeCreated.getMethod("fromValue", getConstructorParameterClass());
			return method.invoke(null, value);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Static Method forValue() is needed to create a " + classToBeCreated.getSimpleName(), e);
		}
	}
}
