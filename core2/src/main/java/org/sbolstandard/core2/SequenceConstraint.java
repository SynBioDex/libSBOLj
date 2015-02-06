package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class SequenceConstraint extends Identified {

	private URI restriction;
	private URI subject;
	private URI object;

	public SequenceConstraint(URI identity, URI restriction, URI subject,
			URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}

	/**
	 * Returns field variable <code>restriction</code>.
	 * 
	 * @return field variable <code>restriction</code>
	 */
	public URI getRestriction() {
		return restriction;
	}

	/**
	 * Sets field variable <code>restriction</code> to the specified element.
	 * 
	 * @param restriction
	 */
	public void setRestriction(URI restriction) {
		this.restriction = restriction;
	}

	/**
	 * Returns field variable <code>subject</code>.
	 * 
	 * @return field variable <code>subject</code>
	 */
	public URI getSubject() {
		return subject;
	}

	/**
	 * Sets field variable <code>subject</code> to the specified element.
	 * 
	 * @param subject
	 */
	public void setSubject(URI subject) {
		this.subject = subject;
	}

	/**
	 * Returns field variable <code>object</code>.
	 * 
	 * @return field variable <code>object</code>
	 */
	public URI getObject() {
		return object;
	}

	/**
	 * Sets field variable <code>object</code> to the specified element.
	 * 
	 * @param object
	 */
	public void setObject(URI object) {
		this.object = object;
	}

}
