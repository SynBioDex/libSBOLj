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
	 * @return
	 */
	public NamedProperty<QName> getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(NamedProperty<QName> value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	private Annotation deepCopy() {
		return new Annotation(this);
	}
	
	/**
	 * 
	 * @param displayId
	 * @return
	 */
	public Annotation copy() {
		Annotation cloned = this.deepCopy();
		return cloned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

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
