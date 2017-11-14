package de.th_koeln.rdm.converter;

import java.lang.reflect.Method;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import de.th_koeln.rdm.attribute.Attribute;

public abstract class AttributeConverter<T> implements Converter {

	abstract T toFromValueParameterValue(String aValue);

	abstract Class<?> getFromValueParameterClass();

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext aContext, @SuppressWarnings("unused") UIComponent aComponent, Object aValue) {
		Attribute<?> value = (Attribute<?>) aValue;
		return value.toString();
	}

	@Override
	public Object getAsObject(FacesContext aContext, UIComponent aComponent, String aValue) {
		Object value = getValue(aValue);
		Class<?> concreteClass = getConcreteClass(aContext, aComponent);
		return createObject(value, concreteClass);
	}

	private Object getValue(String aValue) {
		Object value;
		try {
			value = toFromValueParameterValue(aValue);
		} catch (NumberFormatException e) {
			throw new ConverterException("Only a number is allowed");
		}
		return value;
	}

	private Class<?> getConcreteClass(FacesContext aContext, UIComponent aComponent) {
		ValueExpression expression = aComponent.getValueExpression("value");
		return expression.getType(aContext.getELContext());
	}

	private Object createObject(Object value, Class<?> concreteClass) {
		try {
			Method method = concreteClass.getMethod("fromValue", getFromValueParameterClass());
			return method.invoke(null, value);
		} catch (ReflectiveOperationException e) {
			throw new ConverterException("Static Method forValue() is needed to create a " + concreteClass.getSimpleName(), e);
		}
	}
}
