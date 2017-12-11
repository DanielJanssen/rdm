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
		} catch (RuntimeException e) {
			throw new ConverterException("The conversion is not possible.", e);
		}
		return value;
	}

	private Class<?> getConcreteClass(FacesContext aContext, UIComponent aComponent) {
		ValueExpression expression = aComponent.getValueExpression("value");
		return expression.getType(aContext.getELContext());
	}

	private Object createObject(Object aValue, Class<?> aConcreteClass) {
		try {
			Method method = aConcreteClass.getMethod("fromValue", getFromValueParameterClass());
			return method.invoke(null, aValue);
		} catch (ReflectiveOperationException e) {
			throw new ConverterException("Static Method forValue() is needed to create a " + aConcreteClass.getSimpleName(), e);
		}
	}
}
