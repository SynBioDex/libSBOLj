package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * Represents the SBOL SequenceConstraint data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class SequenceConstraint extends Identified {

	//private RestrictionType restriction;
	private URI restriction;
	private URI subject;
	private URI object;
	private ComponentDefinition componentDefinition = null;
	
	SequenceConstraint(URI identity, URI restriction, URI subject, URI object) throws SBOLValidationException {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	/**
	 * @param identity
	 * @param restriction
	 * @param subject
	 * @param object
	 * @throws SBOLValidationException if any of the following condition is met:
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setRestriction(RestrictionType)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setSubject(URI)}; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setObject(URI)}.</li>
	 */
	SequenceConstraint(URI identity, RestrictionType restriction, URI subject, URI object) throws SBOLValidationException {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	private SequenceConstraint(SequenceConstraint sequenceConstraint) throws SBOLValidationException {
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
		try {
			return RestrictionType.convertToRestrictionType(restriction);
		}
		catch (SBOLValidationException e) {
			return null;
		}
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
	 * @param restriction the restriction type
 	 * @throws SBOLValidationException if either of the following SBOL validation rule was violated: 11407, 11412.
	 */
	public void setRestriction(RestrictionType restriction) throws SBOLValidationException {
		if (restriction==null) {
			throw new SBOLValidationException("sbol-11407",this);
		}
		try {
			this.restriction = RestrictionType.convertToURI(restriction);
		} catch (SBOLValidationException e) {
			throw new SBOLValidationException("sbol-11412",this);
		}
	}
	
	/**
	 * Sets the restriction property to the given {@code restrictionURI}.
	 * <p>
	 * If this SequenceConstraint restriction belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. 
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * 
	 * @param restrictionURI the identity URI of the restriction
 	 * @throws SBOLValidationException if no restriction is provided
	 */
	public void setRestriction(URI restrictionURI) throws SBOLValidationException {
		if (restrictionURI==null) {
			throw new SBOLValidationException("sbol-11407",this);
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
	 * @param subjectURI the identity URI of the subject component
	 * @throws SBOLValidationException if the associated ComponentDefinition subject
	 * is not {@code null}, and the given {@code subjectURI} does not exist in 
	 * its associated ComponentDefinition subject's
	 * list of Component instances.
	 * @throws SBOLValidationException if any of the following SBOL validation rule violation was violated: 11402, 11403, 11406.
	 */
	public void setSubject(URI subjectURI) throws SBOLValidationException {
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(subjectURI)==null) {
				throw new SBOLValidationException("sbol-11403",this);
			}
		}
		if (subjectURI==null) {
			throw new SBOLValidationException("sbol-11402", this);
		}
		if (subjectURI.equals(object)) {
			throw new SBOLValidationException("sbol-11406", this);
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
	 * @param objectURI the identity URI of the object component
	 * @throws SBOLValidationException if the associated ComponentDefinition object
	 * is not {@code null}, and the given {@code objectURI} does not exist in 
	 * its associated ComponentDefinition object's
	 * list of Component instances.
	 * @throws SBOLValidationException if any of the following SBOL validation rule was violated: 11402, 11404, 11405. 
	 */
	public void setObject(URI objectURI) throws SBOLValidationException {
		if (componentDefinition != null) {
			if (componentDefinition.getComponent(objectURI)==null) {
				throw new SBOLValidationException("sbol-11405",this);
			}
		}
		if (objectURI==null) {
			throw new SBOLValidationException("sbol-11404", this);
		}
		if (objectURI==subject) {
			throw new SBOLValidationException("sbol-11402", this);
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
	protected SequenceConstraint deepCopy() throws SBOLValidationException {		
		return new SequenceConstraint(this);
	}

	/**
	 * Assume this SequenceConstraint object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @throws SBOLValidationException 
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
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
		return "SequenceConstraint ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", restriction=" + restriction 
				+ ", subject=" + subject
				+ ", object=" + object 
				+ "]";
	}
}
