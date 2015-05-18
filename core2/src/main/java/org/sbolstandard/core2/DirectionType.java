package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.FunctionalComponent.Direction;

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
			if (direction.equals(Direction.INOUT)) {
				return DirectionType.INOUT;
			} else if (direction.equals(Direction.INPUT)) {
				return DirectionType.INPUT;
			} else if (direction.equals(Direction.NONE)) {
				return DirectionType.NONE;
			} else if (direction.equals(Direction.OUTPUT)) {
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
				return Direction.INOUT;
			}
			else if (direction.equals(DirectionType.INPUT)) {
				return Direction.INPUT;
			}
			else if (direction.equals(DirectionType.OUTPUT)) {
				return Direction.OUTPUT;
			}
			else if (direction.equals(DirectionType.NONE)) {
				return Direction.NONE;
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