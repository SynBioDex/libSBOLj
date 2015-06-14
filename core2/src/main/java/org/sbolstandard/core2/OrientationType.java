package org.sbolstandard.core2;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public enum OrientationType {
	/**
	 * The region specified by this Location MUST be on the elements of a Sequence.
	 */
	INLINE("inline"), 
	/**
	 * The region specified by this Location MUST be on the reverse-complement translation of the elements of a Sequence. 
	 * The exact nature of this translation depends on the encoding of the Sequence.
	 */
	REVERSECOMPLEMENT("reverseComplement");
	private final String orientationType;

	OrientationType(String orientationType) {
		this.orientationType = orientationType;
	}

	/**
	 * Returns the orientation type.
	 * @return orientation type.
	 */
	String getOrientationType() {
		return orientationType;
	}

	@Override
	public String toString() {
		return orientationType;
	}

	/**
	 * Convert the specified URI to its corresponding OrientationType instance. 
	 * @return the corresponding OrientationType instance
	 */
	static OrientationType convertToOrientationType(URI orientation) {
		if (orientation.equals(inline)) {
			return OrientationType.INLINE;
		} 
		else if (orientation.equals(reverseComplement)) {
			return OrientationType.REVERSECOMPLEMENT;
		}
		else {
			throw new IllegalArgumentException("Unknown orientation URI `" + orientation + "'");
		}
	}

	/**
	 * Returns the orientation type in URI.
	 * @return orientation type in URI
	 */
	static URI convertToURI(OrientationType orientation) {
		if (orientation != null) {
			if (orientation.equals(OrientationType.INLINE)) {
				return inline;
			}
			else if (orientation.equals(OrientationType.REVERSECOMPLEMENT)) {
				return reverseComplement;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	

	static final URI inline 		  = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "inline");
	static final URI reverseComplement = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "reverseComplement");
}
