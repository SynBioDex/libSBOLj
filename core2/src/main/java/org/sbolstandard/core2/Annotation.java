package org.sbolstandard.core2;

import javax.xml.namespace.QName;
import static uk.ac.ncl.intbio.core.datatree.Datatree.*;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;

public class Annotation {

	private QName relation;
	private Turtle literal;	
	private NamedProperty<QName> namedProperty;
	
	
	public Annotation(QName relation, Turtle literal) {
		namedProperty = NamedProperty(relation, literal.getTurtleStr());
		setRelation(relation);
		setLiteral(literal);
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

	/**
	 * Returns field variable <code>relation</code>.
	 * @return field variable <code>relation</code>
	 */
	public QName getRelation() {
		return relation;
	}

	/**
	 * Sets field variable <code>relation</code> to the specified element.
	 * @param relation
	 */
	private void setRelation(QName relation) {
		this.relation = relation;
	}

	/**
	 * Returns field variable <code>literal</code>
	 * @return field variable <code>relation</code>
	 */
	public Turtle getLiteral() {
		return literal;
	}

	/**
	 * Sets field variable <code>relation</code> to the specified element.
	 * @param literal
	 */
	private void setLiteral(Turtle literal) {
		this.literal = literal;
	}
	
	
}
