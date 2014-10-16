package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class StructuralConstraint extends Identified {

	private URI restriction;
	private URI subject;
	private URI object;
	
	public StructuralConstraint(URI identity, URI restriction, 
			URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}

	public URI getRestriction() {
		return restriction;
	}

	public void setRestriction(URI restriction) {
		this.restriction = restriction;
	}

	public URI getSubject() {
		return subject;
	}

	public void setSubject(URI subject) {
		this.subject = subject;
	}

	public URI getObject() {
		return object;
	}

	public void setObject(URI object) {
		this.object = object;
	}

}
