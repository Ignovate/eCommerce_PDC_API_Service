package com.gaia.domain;

import java.io.Serializable;

public abstract class GenericBean implements Serializable, Cloneable, Comparable {

	public abstract String getKey();

	public String getCaption() {
		return "";
	}

	public int compareTo(Object o) {
		if (o == null)
			return -1;
		return getKey().compareTo(((GenericBean) o).getKey());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		return o.hashCode() == hashCode();
	}

	@Override
	public int hashCode() {
		if (toString() != null) {
			return toString().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public String toString() {
		return getKey();
	}

	/**
	 * Used when after having been edited, bean changes must be applied to another instance of the class.
	 * 
	 * @param object Recipient instance
	 */
	public <X> void copyTo(X dest) {
		if (dest == null)
			return;

		try {
			// PropertyUtils.copyProperties(dest, this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
