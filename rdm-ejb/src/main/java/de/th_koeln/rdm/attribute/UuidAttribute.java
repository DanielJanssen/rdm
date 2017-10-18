package de.th_koeln.rdm.attribute;

import javax.persistence.Embeddable;

@Embeddable
public abstract class UuidAttribute extends StringAttribute {

	private static final long serialVersionUID = 1L;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected UuidAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected UuidAttribute(String aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	protected Boolean isValid(String aValue) {
		return aValue.length() == 36;
	}
}
