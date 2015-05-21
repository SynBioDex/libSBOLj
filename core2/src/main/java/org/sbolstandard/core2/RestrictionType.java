package org.sbolstandard.core2;

import java.net.URI;

public enum RestrictionType {
	PRECEDES("precedes"),
	SAME_ORIENTATION_AS("sameOrienationAs"),
	OPPOSITE_ORIENTATION_AS("oppositeOrienationAs");
	
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
		if (restriction.equals(precedes)) {
			return RestrictionType.PRECEDES;
		} else if (restriction.equals(sameOrientationAs)) {
			return RestrictionType.SAME_ORIENTATION_AS;
		} else if (restriction.equals(oppositeOrientationAs)) {
			return RestrictionType.OPPOSITE_ORIENTATION_AS;
		} 
		else {
			throw new IllegalArgumentException("Not a valid restriction type.");
		}
	}
	
	/**
	 * Returns the restriction type in URI.
	 * @return restriction type in URI
	 */
	public static URI convertToURI(RestrictionType restriction) {
		if (restriction != null) {
			if (restriction.equals(RestrictionType.PRECEDES)) {
				return precedes;
			} else if (restriction.equals(RestrictionType.SAME_ORIENTATION_AS)) {
				return sameOrientationAs;
			} else if (restriction.equals(RestrictionType.OPPOSITE_ORIENTATION_AS)) {
				return oppositeOrientationAs;
			} 
			else {
				throw new IllegalArgumentException("Not a valid restriction type.");
			}
		}
		else {
			return null;
		}
	}
	
	static final URI precedes = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "precedes");
	static final URI sameOrientationAs = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "sameOrientationAs");
	static final URI oppositeOrientationAs = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "oppositeOrientationAs");

}