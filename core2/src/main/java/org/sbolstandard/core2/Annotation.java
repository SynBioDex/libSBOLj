package org.sbolstandard.core2;

import java.net.URI;
import javax.xml.namespace.QName;

public class Annotation {

	private QName relation;
	private Turtle literal;
	
	public Annotation(QName relation, Turtle literal) {
		setRelation(relation);
		setLiteral(literal);
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
	public void setRelation(QName relation) {
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
	public void setLiteral(Turtle literal) {
		this.literal = literal;
	}
}
