package de.th_koeln.rdm.attribute;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public abstract class IntegerAttribute extends Attribute<Integer> {
	private static final long serialVersionUID = 1L;

	@Column
	Integer value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected IntegerAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 */
	@Deprecated
	protected IntegerAttribute(Integer aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	protected Boolean isValid(@SuppressWarnings("unused") Integer aValue) {
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		if (isNullOrEmpty()) {
			return "";
		}
		return getNumberFormat().format(getValue());
	}

	protected NumberFormat getNumberFormat() {
		return new DecimalFormat("0");
	}

}