package de.th_koeln.rdm.attribute;

import java.io.Serializable;

import org.apache.commons.lang3.ObjectUtils;

public abstract class Attribute<T extends Comparable<T>> implements Serializable, Comparable<Attribute<T>> {

	private static final long serialVersionUID = 1L;

	public abstract T getValue();

	public abstract boolean equalsValue(Attribute<T> anAttribute);

	public boolean isNullOrEmpty() {
		if (getValue() == null || getValue().toString().isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public boolean isGreaterThan(Attribute<T> anAttribute) {
		return compareTo(anAttribute) > 0;
	}

	@Override
	public int compareTo(Attribute<T> aSimpleValueObject) {
		return ObjectUtils.compare(getValue(), aSimpleValueObject.getValue());
	}

	@Override
	public String toString() {
		if (isNullOrEmpty()) {
			return "";
		}
		return "" + getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Attribute<T> other = (Attribute<T>) obj;
		if (getValue() == null) {
			if (other.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(other.getValue())) {
			return false;
		}
		return true;
	}
}