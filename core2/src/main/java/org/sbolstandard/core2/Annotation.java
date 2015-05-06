package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;

import java.net.URI;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class Annotation {

	private NamedProperty<QName> value;

	Annotation(QName qName, String literal) {
		value = NamedProperty(qName,literal);
	}

	Annotation(QName qName, URI literal) {
		value = NamedProperty(qName,literal);
	}
	
	Annotation(NamedProperty<QName> value) {
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
	 */
	public void setValue(NamedProperty<QName> value) {
		this.value = value;
	}

	/**
	 * Makes a deep copy of this Annotation object. 
	 * @return an Annotation object that is the exact copy of this object.
	 */
	private Annotation deepCopy() {
		return new Annotation(this);
	}
	
	/**
	 * Makes a deep copy of this Annotation object. 
	 * @return an Annotation object that is the exact copy of this object. 
	 */
	Annotation copy() {
		return this.deepCopy();
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
