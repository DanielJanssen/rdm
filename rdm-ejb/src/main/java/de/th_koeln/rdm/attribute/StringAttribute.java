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

	protected Boolean isValid(@SuppressWarnings("unused") String aValue) {
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		if (isNullOrEmpty()) {
			return "";
		}
		return getValue();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Boolean equalsValue(Attribute<String> anAttribute) {
		if (getValue() == null) {
			if (anAttribute.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(anAttribute.getValue())) {
			return false;
		}
		return true;
	}

}
