package org.sbolstandard.core2;

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
	
