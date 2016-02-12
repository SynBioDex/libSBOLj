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

public class GenericLocation extends Location{
	
	GenericLocation(URI identity) throws SBOLValidationException {
		super(identity);
	}
	
	private GenericLocation(GenericLocation genericLocation) throws SBOLValidationException {
		super(genericLocation);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
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
		GenericLocation other = (GenericLocation) obj;
		return orientation == other.orientation;
	}

	@Override
	protected GenericLocation deepCopy() throws SBOLValidationException {
		return new GenericLocation(this);
	}

	@Override
	public String toString() {
		return "GenericLocation [orientation=" + orientation + ", identity=" + identity
				+ ", displayId=" + displayId + ", name=" + name + ", description=" + description
				+ "]";
	}

	@Override
	public int compareTo(Location locaction) {
		return Integer.MAX_VALUE;
	}
}
