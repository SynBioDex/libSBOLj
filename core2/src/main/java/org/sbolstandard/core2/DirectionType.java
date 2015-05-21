package org.sbolstandard.core2;

import java.net.URI;

public enum DirectionType {
	INPUT("input"), OUTPUT("output"), INOUT("inout"), NONE("none");
	private final String directionType;

	DirectionType(String directionType) {
		this.directionType = directionType;
	}

	/**
	 * Returns the direction type.
	 * 
	 * @return direction type.
	 */
	String getDirectionType() {
		return directionType;
	}

	@Override
	public String toString() {
		return directionType;
	}

	/**
	 * Convert the specified URI to its corresponding DirectionType instance.
	 * @param direction
	 * @return the corresponding DirectionType instance
	 */
	static DirectionType convertToDirectionType(URI direction) {
		if (direction != null) {
			if (direction.equals(inout)) {
				return DirectionType.INOUT;
			} else if (direction.equals(input)) {
				return DirectionType.INPUT;
			} else if (direction.equals(none)) {
				return DirectionType.NONE;
			} else if (direction.equals(output)) {
				return DirectionType.OUTPUT;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the direction type in URI.
	 * @return direction type in URI
	 */
	static URI convertToURI(DirectionType direction) {
		if (direction != null) {
			if (direction.equals(DirectionType.INOUT)) {
				return inout;
			}
			else if (direction.equals(DirectionType.INPUT)) {
				return input;
			}
			else if (direction.equals(DirectionType.OUTPUT)) {
				return output;
			}
			else if (direction.equals(DirectionType.NONE)) {
				return none;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	static final URI	input	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "input");
	static final URI	output	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "output");
	static final URI	inout	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "inout");
	static final URI	none	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "none");

}