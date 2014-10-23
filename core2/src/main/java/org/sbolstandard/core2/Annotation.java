package org.sbolstandard.core2;

import java.net.URI;

public class Annotation {

	private URI relation;
	private Turtle literal;
	
	public Annotation(URI relation, Turtle literal) {
		setRelation(relation);
		setLiteral(literal);
	}

	/**
	 * Returns field variable <code>relation</code>.
	 * @return field variable <code>relation</code>
	 */
	public URI getRelation() {
		return relation;
	}

	/**
	 * Sets field variable <code>relation</code> to the specified element.
	 * @param relation
	 */
	public void setRelation(URI relation) {
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
