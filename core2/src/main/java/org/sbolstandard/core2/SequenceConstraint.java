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
	private URI restriction;
	private URI subject;
	private URI object;
	private ComponentDefinition componentDefinition = null;
	
	SequenceConstraint(URI identity, URI restriction, URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	SequenceConstraint(URI identity, RestrictionType restriction, URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	private SequenceConstraint(SequenceConstraint sequenceConstraint) {
		super(sequenceConstraint);
		this.setRestriction(sequenceConstraint.getRestrictionURI());
		this.setSubject(sequenceConstraint.getSubjectURI());
		this.setObject(sequenceConstraint.getObjectURI());
	}
	
	/**
	 * Returns the restriction property of this SequenceConstraint object.
	 * 
	 * @return the restriction property of this SequenceConstraint object
	 */
	public RestrictionType getRestriction() {
		return RestrictionType.convertToRestrictionType(restriction);
	}
	
	
	/**
	 * Returns the restriction property of this SequenceConstraint object.
	 * 
	 * @return the restriction property of this SequenceConstraint object
	 */
	public URI getRestrictionURI() {
		return restriction;
	}


	/**
	 * Sets the restriction property to the given {@code restriction}.
	 * <p>
	 * If this SequenceConstraint restriction belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @param restriction
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant	 
	 * @throws NullPointerException if the given {@code restriction} is {@code null}.
	 */
	public void setRestriction(RestrictionType restriction) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (restriction==null) {
			throw new NullPointerException("Not a valid restriction type.");
		}
		this.restriction = RestrictionType.convertToURI(restriction);
	}
	
	/**
	 * Sets the restriction property to the given {@code restrictionURI}.
	 * <p>
	 * If this SequenceConstraint restriction belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @param restrictionURI
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant	 
	 * @throws NullPointerException if the given {@code restriction} is {@code null}.
	 */
	public void setRestriction(URI restrictionURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (restrictionURI==null) {
			throw new NullPointerException("Not a valid restriction type.");
		}
		this.restriction = restrictionURI;
	}

	/**
	 * Returns the subject Component URI that this SequenceConstraint object refers to.
	 * 
	 * @return the subject Component URI that this SequenceConstraint object refers to
	 */
	public URI getSubjectURI() {
		return subject;
	}

	/**
	 * Returns the subject Component instance this SequenceConstraint object refers to.
	 * <p>
	 * This method calls {@link ComponentDefinition#getComponent(URI)},
	 * 
	 * @return the subject Component instance this SequenceConstraint object refers to,
	 * if the associated ComponentDefinition instance is not {@code null}, or {@code null} otherwise
	 */
	public Component getSubject() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(subject);
	}
	
	/**
	 * Get the component definition for the subject of this sequence constraint.
	 * @return the component definition for the subject of this sequence constraint.
	 */
	public ComponentDefinition getSubjectDefinition() {
		if (componentDefinition!=null) {
			return componentDefinition.getComponent(subject).getDefinition();
		}
		return null;
	}

	/**
	 * Sets the reference subject Component URI to the given {@code subjectURI}.
	 * <p>
	 * If this SequenceConstraint subject belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * 
	 * @param subjectURI
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated ComponentDefinition subject
	 * is not {@code null}, and the given {@code subjectURI} does not exist in 
	 * its associated ComponentDefinition subject's
	 * list of Component instances.
	 * @throws IllegalArgumentException if the given {@code subjectURI} is {@code null}.
	 */
	public void setSubject(URI subjectURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(subjectURI)==null) {
				throw new IllegalArgumentException("Component '" + subjectURI + "' does not exist.");
			}
		}
		if (subjectURI==null) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have a subject.");
		}
		if (subjectURI==object) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have different subject and object.");
		}
		this.subject = subjectURI;
	}

	/**
	 * Returns the object Component URI that this SequenceConstraint object refers to.
	 * 
	 * @return the object Component URI that this SequenceConstraint object refers to
	 */
	public URI getObjectURI() {
		return object;
	}
	
	/**
	 * Returns the object Component instance this SequenceConstraint object refers to.
	 * 
	 * @return the object Component instance this SequenceConstraint object refers to,
	 * if the associated ComponentDefinition instance is not {@code null}, or {@code null} otherwise
	 */
	public Component getObject() {
		if (componentDefinition==null) return null;
		return componentDefinition.getComponent(object);
	}
	
	
	/**
	 * Get the component definition for the object of this sequence constraint.
	 * @return the component definition for the object of this sequence constraint.
	 */
	public ComponentDefinition getObjectDefinition() {
		if (componentDefinition!=null) {
			return componentDefinition.getComponent(object).getDefinition();
		}
		return null;
	}

	/**
	 * Sets the reference object Component URI to the given {@code objectURI}.
	 * <p>
	 * If this SequenceConstraint object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * 
	 * @param objectURI
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated ComponentDefinition object
	 * is not {@code null}, and the given {@code objectURI} does not exist in 
	 * its associated ComponentDefinition object's
	 * list of Component instances.
	 * @throws IllegalArgumentException if the given {@code objectURI} is {@code null}.
	 */
	public void setObject(URI objectURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(objectURI)==null) {
				throw new IllegalArgumentException("Component '" + objectURI + "' does not exist.");
			}
		}
		if (objectURI==null) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have an object.");
		}
		if (objectURI==subject) {
			throw new IllegalArgumentException("Sequence constraint '" + this.getIdentity() + "' must have different subject and object.");
		}
		this.object = objectURI;
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
		if (!restriction.equals(other.restriction))
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
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		String subjectId = extractDisplayId(subject);
		this.setSubject(createCompliantURI(URIprefix,subjectId,version));
		String objectId = extractDisplayId(object);
		this.setObject(createCompliantURI(URIprefix,objectId,version));
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

	@Override
	public String toString() {
		return "SequenceConstraint [restriction=" + restriction + ", subject=" + subject
				+ ", object=" + object + ", componentDefinition=" + componentDefinition + "]";
	}
}
