package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;

public class Annotation {

	private QName relation;
	private Turtle literal;
	private NamedProperty<QName> namedProperty;


	public Annotation(QName relation, Turtle literal) {
		namedProperty = NamedProperty(relation, literal.getTurtleStr());
	}

	public Annotation(NamedProperty<QName> namedProperty) {
		this.namedProperty = namedProperty;
	}

	public NamedProperty<QName> getNamedProperty() {
		return namedProperty;
	}

	public void setNamedProperty(NamedProperty<QName> namedProperty) {
		this.namedProperty = namedProperty;
	}

}
