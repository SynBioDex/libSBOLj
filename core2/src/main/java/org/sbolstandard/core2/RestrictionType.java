package org.sbolstandard.core2;

import java.net.URI;

/**
 * Represents the relationship between a sequence constraint's subject and object components.
 * 
 * @author Zhen Zhang
 * @author Chris Myers
 * @version 2.1
 */

public enum RestrictionType {
	
	/**
	 * The position of the subject Component MUST precede that of the object Component. 
	 * If each one is associated with a SequenceAnnotation, then the SequenceAnnotation 
	 * associated with the subject Component MUST specify a region that starts before 
	 * the region specified by the SequenceAnnotation associated with the object Component.
	 */
	PRECEDES("precedes"),
	/**
	 * The subject and object Component objects MUST have the same orientation. 
	 * If each one is associated with a SequenceAnnotation, then the orientation URIs
	 * of the Location objects of the first SequenceAnnotation MUST be represented
	 * among those of the second SequenceAnnotation, and vice versa.  
	 */
	SAME_ORIENTATION_AS("sameOrienationAs"),
	/**
	 * The subject and object Component objects MUST have opposite orientations.
	 * If each one is associated with a SequenceAnnotation, then the orientation URIs
	 * of the Location objects of one SequenceAnnotation MUST NOT be represented
	 * among those of the other SequenceAnnotation.  
	 */
	OPPOSITE_ORIENTATION_AS("oppositeOrienationAs");
	
	private final String restrictionType;

	RestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}
	
	/**
	 * Returns the restriction type.
	 * @return restriction type.
	 */
	String getRestrictionType() {
		return restrictionType;
	}

	@Override
	public String toString() {
		return restrictionType;
	}
	
			
	/**
	 * Convert the specified URI to its corresponding RestrictionType instance.
	 * @return the corresponding RestrictionType instance.
	 * @throws SBOLValidationException 
	 */
	static RestrictionType convertToRestrictionType(URI restriction) throws SBOLValidationException {
		if (restriction!=null) {
			if (restriction.equals(precedes)) {
				return RestrictionType.PRECEDES;
			} else if (restriction.equals(sameOrientationAs)) {
				return RestrictionType.SAME_ORIENTATION_AS;
			} else if (restriction.equals(oppositeOrientationAs)) {
				return RestrictionType.OPPOSITE_ORIENTATION_AS;
			} 
			else {
				throw new SBOLValidationException("sbol-11412");
			}
		} else {
			throw new SBOLValidationException("sbol-11412");
		}
	}
	
	/**
	 * Returns the restriction type in URI.
	 * @return restriction type in URI
	 * @throws SBOLValidationException 
	 */
	static URI convertToURI(RestrictionType restriction) throws SBOLValidationException {
		if (restriction != null) {
			if (restriction.equals(RestrictionType.PRECEDES)) {
				return precedes;
			} else if (restriction.equals(RestrictionType.SAME_ORIENTATION_AS)) {
				return sameOrientationAs;
			} else if (restriction.equals(RestrictionType.OPPOSITE_ORIENTATION_AS)) {
				return oppositeOrientationAs;
			} 
			else {
				throw new SBOLValidationException("sbol-11412");
			}
		}
		else {
			throw new SBOLValidationException("sbol-11412");
		}
	}
	
	static final URI precedes = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "precedes");
	static final URI sameOrientationAs = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "sameOrientationAs");
	static final URI oppositeOrientationAs = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "oppositeOrientationAs");

}