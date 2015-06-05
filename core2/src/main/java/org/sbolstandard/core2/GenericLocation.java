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
	
	GenericLocation(URI identity) {
		super(identity);
	}
	
	private GenericLocation(GenericLocation genericLocation) {
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
	protected GenericLocation deepCopy() {
		return new GenericLocation(this);
	}
}
