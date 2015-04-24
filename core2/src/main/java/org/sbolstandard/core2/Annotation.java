package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;


public class Annotation {

	private NamedProperty<QName> value;

	public Annotation(QName relation, Turtle literal) {
		value = NamedProperty(relation, literal.getTurtleStr());
	}

	public Annotation(NamedProperty<QName> value) {
		this.value = value;
	}
	
	private Annotation(Annotation annotation) {
		this.setValue(annotation.getValue());
	}
	

	/**
	 * Returns the value of this Annotation object.
	 * @return the value of this Annotation object.
	 */
	public NamedProperty<QName> getValue() {
		return value;
	}

	/**
	 * Sets the value of this Annotation object to the specified argument.
	 * @param value
	 */
	public void setValue(NamedProperty<QName> value) {
		this.value = value;
	}

	/**
	 * Makes a deep copy of this Annotation object. 
	 * @param displayId
	 * @return an Annotation object that is the exact copy of this object. 
	 */
	private Annotation deepCopy() {
		return new Annotation(this);
	}
	
	/**
	 * Makes a deep copy of this Annotation object. 
	 * @return an Annotation object that is the exact copy of this object. 
	 */
	public Annotation copy() {
		Annotation cloned = this.deepCopy();
		return cloned;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Annotation other = (Annotation) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			//return false;
		// TODO: Hack here. Need to wait for equals and hashCode to be implemented in NamedProperty.
			return true;
		return true;
	}

}
