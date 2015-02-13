package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.Sbol2Terms.Orientation;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

public enum OrientationType {
	INlINE("inline"), 
	REVERSECOMPLEMENT("reverseComplement");
	private final String orientationType;

	private OrientationType(String orientationType) {
		this.orientationType = orientationType;
	}

	/**
	 * Returns the orientation type.
	 * @return orientation type.
	 */
	public String getOrientationType() {
		return orientationType;
	}

	@Override
	public String toString() {
		return orientationType;
	}

	/**
	 * Convert the specified URI to its corresponding OrientationType instance. 
	 * @param orientation
	 * @return the corresponding OrientationType instance
	 */
	public static OrientationType convertToOrientationType(URI orientation) {
		if (orientation.equals(Orientation.inline)) {
			return OrientationType.INlINE;
		} 
		else if (orientation.equals(Orientation.reverseComplement)) {
			return OrientationType.REVERSECOMPLEMENT;
		}
		else {
			// TODO: Validation?
			return null;
		}
	}

	/**
	 * Returns the orientation type in URI.
	 * @return orientation type in URI
	 */
	public static URI convertToURI(OrientationType orientation) {
		if (orientation != null) {
			if (orientation.equals(OrientationType.INlINE)) {
				return Orientation.inline;
			}
			else if (orientation.equals(OrientationType.REVERSECOMPLEMENT)) {
				return Orientation.reverseComplement;
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
	
	
	//------Begin: Orientation from 1.1 ---------
//	/**
//	 * Represents <code>+</code> strand which is 5' to 3'.
//	 * @deprecated As of release 2.0, replaced by {@link #inline}}
//	 */
//	POSITIVE("+"), 
//	
//	/**
//	 * Represents <code>-</code> strand which is 3' to 5'.
//	 * @deprecated As of release 2.0, replaced by {@link #reverseComplement}}
//	 */
//	NEGATIVE("-");
	
//	Orientation() {
//		
//	}
//	
//	private String symbol;
//	
//	private OrientationType(String symbol) {
//		this.symbol = symbol;
//	}
//	
//	/**
//	 * Returns the symbol (inline or reverseComplement) for this strand type. 
//	 */
//	public String getSymbol() {
//		return symbol;
//	}
//	
//	@Override
//	public String toString() {
//		return symbol;
//	}
	//------End: Orientation from 1.1 ---------
	
