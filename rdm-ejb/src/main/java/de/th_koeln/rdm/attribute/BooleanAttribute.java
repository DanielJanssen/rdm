package de.th_koeln.rdm.attribute;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public abstract class BooleanAttribute extends Attribute<Boolean> {
	private static final long serialVersionUID = 1L;

	@Column
	Boolean value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected BooleanAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 */
	@Deprecated
	protected BooleanAttribute(Boolean aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	protected Boolean isValid(@SuppressWarnings("unused") Boolean aValue) {
		return Boolean.TRUE;
	}
}