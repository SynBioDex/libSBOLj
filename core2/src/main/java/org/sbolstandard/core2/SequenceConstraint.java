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
	
	public enum RestrictionType {
		PRECEDES("precedes");
		private final String restrictionType;

		RestrictionType(String restrictionType) {
			this.restrictionType = restrictionType;
		}
		
		/**
		 * Returns the restriction type.
		 * @return restriction type.
		 */
		public String getRestrictionType() {
			return restrictionType;
		}

		@Override
		public String toString() {
			return restrictionType;
		}
		
				
		/**
		 * Convert the specified URI to its corresponding RestrictionType instance.
		 * @return the corresponding RestrictionType instance.
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
		
		/**
		 * Returns the restriction type in URI.
		 * @return restriction type in URI
		 */
		public static URI convertToURI(RestrictionType restriction) {
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
	}
	
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
	 * Returns the URI corresponding to the type of restriction.
	 * @return the URI corresponding to the type of restriction.
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
	 */
	public void setRestriction(RestrictionType restriction) {
		this.restriction = restriction;
	}
	
	/**
	 * Sets field variable <code>restriction</code> to the element corresponding to the specified URI.
	 */
	public void setRestriction(URI restriction) {
		if (restriction!=null && restriction.equals(Restriction.precedes)) {
			this.restriction = RestrictionType.PRECEDES;
		}
		else {
			throw new IllegalArgumentException("Not a valid restriction type.");
		}
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
	
	
	private static final class Restriction {
		public static final URI precedes = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "precedes");
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
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// SequenceConstraint does not have any children. No need to update their URIs.
		// TODO: need to set wasDerivedFrom here?
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
