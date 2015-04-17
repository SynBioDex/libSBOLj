package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;
import static org.sbolstandard.core2.util.URIcompliance.*;

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

		@Override
		public String toString() {
			return restrictionType;
		}
		
				
		/**
		 * Convert the specified URI to its corresponding RestrictionType instance.
		 * @param restriction
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
	
	public SequenceConstraint(URI identity, RestrictionType restriction, 
			URI subject, URI object) {
		super(identity);
		setRestriction(restriction);
		setSubject(subject);
		setObject(object);
	}
	
	private SequenceConstraint(SequenceConstraint sequenceConstraint) {
		super(sequenceConstraint);
		this.setRestriction(sequenceConstraint.getRestriction());
		this.setSubject(sequenceConstraint.getSubject());
		this.setObject(sequenceConstraint.getObject());
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
	 * @param URIprefix
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 1); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// SequenceConstraint does not have any children. No need to update their URIs.
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}
}
