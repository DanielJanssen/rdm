package de.th_koeln.rdm.attribute;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public abstract class StringAttribute extends Attribute<String> {

	private static final long serialVersionUID = 1L;
	@Column
	String value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected StringAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 */
	@Deprecated
	protected StringAttribute(String aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	public String getValue() {
		return value;
	}

	protected Boolean isValid(@SuppressWarnings("unused") String aValue) {
		return Boolean.TRUE;
	}
}