package de.th_koeln.rdm.attribute;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public abstract class BigDecimalAttribute extends Attribute<BigDecimal> {
	private static final long serialVersionUID = 1L;

	@Column
	BigDecimal value;

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 * JPA needs an protected/public non argument constructor
	 */
	@Deprecated
	protected BigDecimalAttribute() {
		super();
	}

	/*
	 * @deprecated
	 * Use fromValue()-method from your concreteAttribute;
	 */
	@Deprecated
	protected BigDecimalAttribute(BigDecimal aValue) {
		super();
		if (!isValid(aValue)) {
			throw new IllegalArgumentException("Value " + aValue + " is not valid");
		}
		value = aValue;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	protected Boolean isValid(@SuppressWarnings("unused") BigDecimal aValue) {
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
		return new DecimalFormat("0.00");
	}

	@Override
	public boolean equalsValue(Attribute<BigDecimal> anAttribute) {
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