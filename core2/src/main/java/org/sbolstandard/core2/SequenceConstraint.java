package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SequenceConstraint extends Identified {

	//private RestrictionType restriction;
	private RestrictionType restriction;
	private URI subject;
	private URI object;
	private ComponentDefinition componentDefinition = null;
	
	SequenceConstraint(URI identity, RestrictionType restriction, 
			URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	private SequenceConstraint(SequenceConstraint sequenceConstraint) {
		super(sequenceConstraint);
		this.setRestriction(sequenceConstraint.getRestriction());
		this.setSubject(sequenceConstraint.getSubjectURI());
		this.setObject(sequenceConstraint.getObjectURI());
	}


	/**
	 * Returns field variable <code>restriction</code>.
	 * @return field variable <code>restriction</code>
	 */
	public RestrictionType getRestriction() {
		return restriction;
	}

	/**
	 * Sets field variable <code>restriction</code> to the specified element.
	 */
	public void setRestriction(RestrictionType restriction) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (restriction==null) {
			throw new NullPointerException("Not a valid restriction type.");
		}
		this.restriction = restriction;
	}

	/**
	 * Returns field variable <code>subject</code>.
	 * @return field variable <code>subject</code>
	 */
	public URI getSubjectURI() {
		return subject;
	}

	/**
	 * Returns the Functional Component referenced as the subject.
	 * @return the Functional Component referenced as the subject.
	 */
	public Component getSubject() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(subject);
	}

	/**
	 * Sets field variable <code>subject</code> to the specified element.
	 */
	public void setSubject(URI subject) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(subject)==null) {
				throw new IllegalArgumentException("Component '" + subject + "' does not exist.");
			}
		}
		if (subject==null) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have a subject.");
		}
		this.subject = subject;
	}

	/**
	 * Returns field variable <code>object</code>. 
	 * @return field variable <code>object</code>
	 */
	public URI getObjectURI() {
		return object;
	}
	
	/**
	 * Returns the Functional Component referenced as the object.
	 * @return the Functional Component referenced as the object.
	 */
	public Component getObject() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(object);
	}

	/**
	 * Sets field variable <code>object</code> to the specified element.
	 */
	public void setObject(URI object) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(object)==null) {
				throw new IllegalArgumentException("Component '" + object + "' does not exist.");
			}
		}
		if (object==null) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have an object.");
		}
		this.object = object;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((restriction == null) ? 0 : restriction.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SequenceConstraint other = (SequenceConstraint) obj;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (restriction != other.restriction)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}


	@Override
	protected SequenceConstraint deepCopy() {		
		return new SequenceConstraint(this);
	}

	/**
	 * Assume this SequenceConstraint object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}

	/**
	 * @return the componentDefinition
	 */
	ComponentDefinition getComponentDefinition() {
		return componentDefinition;
	}

	/**
	 * @param componentDefinition the componentDefinition to set
	 */
	void setComponentDefinition(ComponentDefinition componentDefinition) {
		this.componentDefinition = componentDefinition;
	}
}
