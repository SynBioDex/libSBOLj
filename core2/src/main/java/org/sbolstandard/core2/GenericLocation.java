package org.sbolstandard.core2;

import java.net.URI;

/**
 * Represents the GenericLocation extension of the SBOL Location class.
 * 
 * @author Zhen Zhang
 * @author Chris Myers
 * @version 2.1
 */

public class GenericLocation extends Location{
	
	/**
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link Location#Location(URI)}.
	 */
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
		return "GenericLocation ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ (this.isSetOrientation()?", orientation=" + orientation:"") 
				+ "]";
	}

	@Override
	public int compareTo(Location locaction) {
		return Integer.MAX_VALUE;
	}
}
