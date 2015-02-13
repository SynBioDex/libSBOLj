package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class SequenceConstraint extends Identified {

	//private RestrictionType restriction;
	private RestrictionType restriction;
	private URI subject;
	private URI object;
	
	public static enum RestrictionType {
		PRECEDES("precedes");
		private final String restrictionType;

		private RestrictionType(String restrictionType) {
			this.restrictionType = restrictionType;
		}
		
		/**
		 * Returns the restriction type.
		 * @return restriction type.
		 */
		public String getRestrictionType() {
			return restrictionType;
		}
		
		/**
		 * Sets field variable <code>access</code> to the element corresponding to the specified URI.
		 * @param restriction
		 */
		public static RestrictionType convertToRestrictionType(URI restriction) {
			if (restriction.equals(Restriction.precedes)) {
				return RestrictionType.PRECEDES;
			}
			else {
				// TODO: Validation?
				return null;
			}
		}

		@Override
		public String toString() {
			return restrictionType;
		}
	}
	
	public SequenceConstraint(URI identity, RestrictionType restriction, 
			URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	

	/**
	 * Returns field variable <code>restriction</code>.
	 * @return field variable <code>restriction</code>
	 */
	public RestrictionType getRestriction() {
		return restriction;
	}
	
	/**
	 * @return
	 */
	public URI getRestrictionURI() {
		if (restriction != null) {
			if (restriction.equals(RestrictionType.PRECEDES)) {
				return Restriction.precedes;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Sets field variable <code>restriction</code> to the specified element.
	 * @param restriction
	 */
	public void setRestriction(RestrictionType restriction) {
		this.restriction = restriction;
	}
	
	/**
	 * Sets field variable <code>restriction</code> to the element corresponding to the specified URI.
	 * @param restriction
	 */
	public void setRestriction(URI restriction) {
		if (restriction.equals(Restriction.precedes)) {
			this.restriction = RestrictionType.PRECEDES;
		}
		else {
			// TODO: Validation?
			this.restriction = null;
		}
	}

	/**
	 * Returns field variable <code>subject</code>.
	 * @return field variable <code>subject</code>
	 */
	public URI getSubject() {
		return subject;
	}

	/**
	 * Sets field variable <code>subject</code> to the specified element.
	 * @param subject
	 */
	public void setSubject(URI subject) {
		this.subject = subject;
	}

	/**
	 * Returns field variable <code>object</code>. 
	 * @return field variable <code>object</code>
	 */
	public URI getObject() {
		return object;
	}

	/**
	 * Sets field variable <code>object</code> to the specified element.
	 * @param object
	 */
	public void setObject(URI object) {
		this.object = object;
	}
	
	
	public static final class Restriction {
		public static final URI precedes = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "precedes");
	}
}
