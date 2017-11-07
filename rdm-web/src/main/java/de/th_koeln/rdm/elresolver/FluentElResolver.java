package de.th_koeln.rdm.elresolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.el.BeanELResolver;
import javax.el.ELContext;

public class FluentElResolver extends BeanELResolver {

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		if (base == null || !(property instanceof String)) {
			return true;
		}
		Method method = getFluentMethod(context, base, property.toString());
		if (method == null) {
			return true;
		}
		context.setPropertyResolved(true);
		return false;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value) {
		if (base == null || !(property instanceof String)) {
			return;
		}
		Method method = getFluentMethod(context, base, property.toString());
		if (method == null) {
			return;
		}
		try {
			method.invoke(base, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof RuntimeException) {
				throw (RuntimeException) e.getTargetException();
			} else {
				throw new IllegalStateException(e.getTargetException());
			}
		}
		context.setPropertyResolved(true);
	}

	private Method getFluentMethod(ELContext context, Object base, String name) {
		for (Method method : base.getClass().getMethods()) {
			if (isFluentWriteMethod(method, context, base, name)) {
				return method;
			}
		}
		return null;
	}

	private boolean isFluentWriteMethod(Method method, ELContext context, Object base, String property) {
		String suffix = Character.toUpperCase(property.charAt(0)) + property.substring(1);
		return method.getName().endsWith(suffix) && method.getParameterTypes().length == 1 && method.getParameterTypes()[0] == getType(context, base, property);
	}

}
