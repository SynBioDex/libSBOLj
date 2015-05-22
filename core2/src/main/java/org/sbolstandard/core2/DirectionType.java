package org.sbolstandard.core2;

import java.net.URI;

public enum DirectionType {
	IN("in"), OUT("out"), INOUT("inout"), NONE("none");
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
			} else if (direction.equals(in)) {
				return DirectionType.IN;
			} else if (direction.equals(none)) {
				return DirectionType.NONE;
			} else if (direction.equals(out)) {
				return DirectionType.OUT;
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
			else if (direction.equals(DirectionType.IN)) {
				return in;
			}
			else if (direction.equals(DirectionType.OUT)) {
				return out;
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

	static final URI	in		= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "in");
	static final URI	out		= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "out");
	static final URI	inout	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "inout");
	static final URI	none	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "none");

}