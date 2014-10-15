package org.sbolstandard.core2;

import java.net.URI;

public class Annotation {

	private URI relation;
	private Turtle literal;
	
	public Annotation(URI relation, Turtle literal) {
		setRelation(relation);
		setLiteral(literal);
	}

	public URI getRelation() {
		return relation;
	}

	public void setRelation(URI relation) {
		this.relation = relation;
	}

	public Turtle getLiteral() {
		return literal;
	}

	public void setLiteral(Turtle literal) {
		this.literal = literal;
	}
}
