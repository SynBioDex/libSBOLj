package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperties;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NestedDocument;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.Literal.BooleanLiteral;
import uk.ac.ncl.intbio.core.datatree.Literal.DoubleLiteral;
import uk.ac.ncl.intbio.core.datatree.Literal.IntegerLiteral;
import uk.ac.ncl.intbio.core.datatree.Literal.StringLiteral;
import uk.ac.ncl.intbio.core.datatree.Literal.UriLiteral;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;

/**
 * Represents the SBOL Annotation data model.
 * 
 * @author Zhen Zhang
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.1
 */

public class Annotation {

	private NamedProperty<QName> value;

	/**
	 * Constructs an Annotation instance using the given {@code qName} and the {@code literal} string.
	 *
	 * @param qName - composed of a namespace, an OPTIONAL prefix, and a local name.
	 * @param literal - used to denote an object that is of type String
	 */
	public Annotation(QName qName, String literal) {
		value = NamedProperty(qName,literal);
	}

	/**
	 * Constructs an Annotation instance using the given {@code qName} and the {@code literal} integer.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal Used to denote an object that is of type Integer
	 */
	public Annotation(QName qName, int literal) {
		value = NamedProperty(qName,literal);
	}

	/**
	 * Constructs an Annotation instance using the given {@code qName} and the {@code double} type {@code literal}.
	 *
	 * @param qName - composed of a namespace, an OPTIONAL prefix, and a local name.
	 * @param literal - used to denote an object that is of type Double
	 */
	public Annotation(QName qName, double literal) {
		value = NamedProperty(qName, literal);
	}

	/**
	 * Constructs an Annotation instance using the given {@code qName} and the {@code boolean} type {@code literal}.
	 *
	 * @param qName - composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal - used to denote an object that is of type Boolean
	 */
	public Annotation(QName qName, boolean literal) {
		value = NamedProperty(qName,literal);
	}

	/**
	 * Constructs an Annotation instance using the given {@code qName} and the URI type {@code literal}.
	 *
	 * @param qName - composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal - used to denote an object that is of type URI
	 */
	public Annotation(QName qName, URI literal) {
		value = NamedProperty(qName,literal);
	}

	/**
	 * Constructs a nested Annotation instance using the given {@code qName}, {@code nestedQName},
	 * {@code nestedURI}, and a list of {@code annotations} to include.
	 *
	 * @param qName - composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param nestedQName A part of NestedAnnotations composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param nestedURI is a part of NestedAnnotations used to denote an object that is of type URI
	 * @param annotations A property of NestedAnnotations that contains zero or more Annotation objects that store data in the form of name/value property pairs.
	 */
	public Annotation(QName qName, QName nestedQName, URI nestedURI, List<Annotation> annotations) {
		List<NamedProperty<QName>> list = new ArrayList<>();
		for(Annotation a : annotations)
		{
			list.add(a.getValue());
		}
		value = NamedProperty(qName, NestedDocument(nestedQName, nestedURI, NamedProperties(list)));
	}

	Annotation(NamedProperty<QName> value) {
		if (value.getName().getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI()) ||
				value.getName().getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI())) {
			if (value.getName().equals(Sbol2Terms.Identified.timeStamp)) {
				System.out.println("Warning: sbol:timeStamp is deprecated");
			}
		}
		this.value = value;
	}

	private Annotation(Annotation annotation) {
		this.setValue(annotation.getValue());
	}

	/**
	 * Returns the name of the {@code value} property.
	 *
	 * @return the name of the {@code value} property
	 */
	public QName getQName() {
		return value.getName();
	}

	/**
	 * Sets the Boolean representation of the {@code value} property.
	 * @param literal - The boolean representation of the property.
	 */
	public void setBooleanValue(boolean literal) {
		QName qName = value.getName();
		value = NamedProperty(qName,literal);
	}

	/**
	 * Checks if the annotation is a boolean {@code value} property.
	 *
	 * @return true if the annotation is a boolean {@code value} property.
	 */
	public boolean isBooleanValue() {
		if (value.getValue() instanceof BooleanLiteral<?>) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a Boolean representation of the {@code value} property.
	 *
	 * @return a Boolean representation of the {@code value} property if its
	 * value is of Boolean type, or {@code null} otherwise.
	 */
	public Boolean getBooleanValue() {
		if (value.getValue() instanceof BooleanLiteral) {
			return ((BooleanLiteral<QName>) value.getValue()).getValue();
		}
		return null;
	}

	/**
	 * Sets the double representation of the {@code value} property.
	 * @param literal - The double representation of the property
	 */
	public void setDoubleValue(double literal) {
		QName qName = value.getName();
		value = NamedProperty(qName,literal);
	}

	/**
	 * Checks if the annotation is a double {@code value} property.
	 *
	 * @return true if the annotation is a double {@code value} property.
	 */
	public boolean isDoubleValue() {
		if (value.getValue() instanceof DoubleLiteral<?>) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a Double representation of the {@code value} property.
	 *
	 * @return a Double representation of the {@code value} property if its
	 * value is of Double type, or {@code null} otherwise.
	 */
	public Double getDoubleValue() {
		if (value.getValue() instanceof DoubleLiteral) {
			return ((DoubleLiteral<QName>) value.getValue()).getValue();
		}
		return null;
	}

	/**
	 * Sets the integer representation of the {@code value} property.
	 * @param literal - The integer representation of the property
	 */
	public void setIntegerValue(int literal) {
		QName qName = value.getName();
		value = NamedProperty(qName,literal);
	}

	/**
	 * Checks if the annotation is a integer {@code value} property.
	 *
	 * @return true if the annotation is a integer {@code value} property.
	 */
	public boolean isIntegerValue() {
		if (value.getValue() instanceof IntegerLiteral<?>) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a Integer representation of the {@code value} property.
	 *
	 * @return a Integer representation of the {@code value} property if its
	 * value is of Integer type, or {@code null} otherwise.
	 */
	public Integer getIntegerValue() {
		if (value.getValue() instanceof IntegerLiteral) {
			return ((IntegerLiteral<QName>) value.getValue()).getValue();
		}
		return null;
	}

	/**
	 * Sets the string representation of the {@code value} property.
	 * @param literal - The string representation of the property.
	 */
	public void setStringValue(String literal) {
		QName qName = value.getName();
		value = NamedProperty(qName,literal);
	}

	/**
	 * Checks if the annotation is a string {@code value} property.
	 *
	 * @return true if the annotation is a string {@code value} property.
	 */
	public boolean isStringValue() {
		if (value.getValue() instanceof StringLiteral<?>) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a string representation of the {@code value} property.
	 *
	 * @return a string representation of the {@code value} property if its
	 * value is of String type, or {@code null} otherwise.
	 */
	public String getStringValue() {
		if (value.getValue() instanceof StringLiteral) {
			return ((Literal<QName>) value.getValue()).getValue().toString();
		}
		return null;
	}

	/**
	 * Sets the string representation of the {@code value} property.
	 * @param literal - The URI representation of the property.
	 */
	public void setURIValue(URI literal) {
		QName qName = value.getName();
		value = NamedProperty(qName,literal);
	}
	
	/**
	 * Checks if the annotation is a URI {@code value} property.
	 *
	 * @return true if the annotation is a URI {@code value} property.
	 */
	public boolean isURIValue() {
		if (value.getValue() instanceof UriLiteral<?>) {
			return true;
		}
		return false;
	}


	/**
	 * Returns a URI representation of the {@code value} property.
	 *
	 * @return a URI representation of the {@code value} property if its
	 * the value is of a URI type, or {@code null} otherwise.
	 */
	public URI getURIValue() {
		if (value.getValue() instanceof UriLiteral) {
			return ((UriLiteral<QName>) value.getValue()).getValue();
		}
		return null;
	}

	/**
	 * Sets the {@code value} property to the specified list of annotations.
	 * 
	 * @param annotations The list of annotations
	 */
	public void setNestedAnnotations(List<Annotation> annotations) {
		List<NamedProperty<QName>> list = new ArrayList<>();
		for(Annotation a : annotations)
		{
			list.add(a.getValue());
		}
		QName qName = value.getName();
		QName nestedQName = getNestedQName();
		URI nestedURI = getNestedIdentity();
		value = NamedProperty(qName, NestedDocument(nestedQName, nestedURI, NamedProperties(list)));
	}

	/**
	 * Returns the type of the nested {@code value} property.
	 *
	 * @return the nested QName of the {@code value} property if its value is
	 * of a nested list of Annotations, or {@code null} otherwise.
	 */
	public QName getNestedQName() {
		if (value.getValue() instanceof NestedDocument<?>) {
			return ((NestedDocument<QName>) value.getValue()).getType();
		}
		return null;
	}

	/**
	 * Returns the identity URI of the nested {@code value} property.
	 *
	 * @return the identity URI of the nested QName {@code value} property if its value is
	 * of a nested list of Annotations, or {@code null} otherwise.
	 */
	public URI getNestedIdentity() {
		if (value.getValue() instanceof NestedDocument<?>) {
			return ((NestedDocument<QName>) value.getValue()).getIdentity();
		}
		return null;
	}

	/**
	 * Checks if the annotation is a nested {@code value} property.
	 *
	 * @return true if the annotation is a nested {@code value} property.
	 */
	public boolean isNestedAnnotations() {
		if (value.getValue() instanceof NestedDocument<?>) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the list of Annotations of the nested {@code value} property.
	 *
	 * @return the list of Annotations of the nested {@code value} property if its value is
	 * of a nested list of Annotations, or {@code null} otherwise.
	 */
	public List<Annotation> getAnnotations() {
		if (value.getValue() instanceof NestedDocument<?>) {
			List<Annotation> annotations = new ArrayList<>();
			for (NamedProperty<QName> namedProperty : ((NestedDocument<QName>) value.getValue()).getProperties()) {
				annotations.add(new Annotation(namedProperty));
			}
			return annotations;
		}
		return null;
	}

	/**
	 * Returns the value of this Annotation object.
	 *
	 * @return the value of this Annotation object.
	 */
	NamedProperty<QName> getValue() {
		return value;
	}

	/**
	 * Sets the value of this Annotation object to the specified argument.
	 */
	void setValue(NamedProperty<QName> value) {
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
		} else if (!value.equals(other.value)) {
			if (!this.getQName().equals(other.getQName())) {
				return false;
			} else if ((this.getValue().getValue() instanceof StringLiteral<?>) &&
					(other.getValue().getValue() instanceof StringLiteral<?>)) {
				if (!this.getStringValue().equals(other.getStringValue())) {
					return false;
				}
			} else if ((this.getValue().getValue() instanceof BooleanLiteral<?>) &&
					(other.getValue().getValue() instanceof BooleanLiteral<?>)) {
				if (!this.getBooleanValue().equals(other.getBooleanValue())) {
					return false;
				}
			} else if ((this.getValue().getValue() instanceof DoubleLiteral<?>) &&
					(other.getValue().getValue() instanceof DoubleLiteral<?>)) {
				if (!this.getDoubleValue().equals(other.getDoubleValue())) {
					return false;
				}
			} else if ((this.getValue().getValue() instanceof IntegerLiteral<?>) &&
					(other.getValue().getValue() instanceof IntegerLiteral<?>)) {
				if (!this.getIntegerValue().equals(other.getIntegerValue())) {
					return false;
				}
			} else if ((this.getValue().getValue() instanceof UriLiteral<?>) &&
					(other.getValue().getValue() instanceof UriLiteral<?>)) {
				if (!this.getURIValue().equals(other.getURIValue())) {
					return false;
				}
			} else if ((this.getValue().getValue() instanceof NestedDocument<?>) &&
					(other.getValue().getValue() instanceof NestedDocument<?>)) {
				if (!this.getNestedQName().equals(other.getNestedQName())) {
					return false;
				}
				if (!this.getNestedIdentity().equals(other.getNestedIdentity())) {
					return false;
				}
				if (this.getAnnotations().size()!=other.getAnnotations().size()) {
					return false;
				}
				boolean equal = true;
				for (Annotation annotation1 : this.getAnnotations()) {
					boolean foundIt = false;
					for (Annotation annotation2 : other.getAnnotations()) {
						if (annotation1.equals(annotation2)) {
							foundIt = true;
							break;
						}
					}
					if (foundIt==false) {
						equal = false;
						break;
					}
				}
				return equal;
			} else {
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Annotation [value=" + value + "]";
	}

}
