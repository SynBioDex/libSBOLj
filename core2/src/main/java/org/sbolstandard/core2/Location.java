package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;

/**
 * Represents the SBOL Location data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public abstract class Location extends Identified implements Comparable<Location> {

	protected OrientationType orientation;

	/**
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link Identified#Identified(URI)}.
	 */
	Location(URI identity) throws SBOLValidationException {
		super(identity);
	}

	/**
	 * @param location
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link Identified#Identified(Identified)}.
	 */
	protected Location(Location location) throws SBOLValidationException {
		super(location);
		this.setOrientation(location.getOrientation());
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.Identified#deepCopy()
	 */
	@Override
	protected abstract Location deepCopy() throws SBOLValidationException;

	/**
	 * Checks if the orientation property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetOrientation() {
		return orientation != null;
	}

	/**
	 * Returns the orientation property of this location.
	 * @return the orientation property of this location
	 */
	public OrientationType getOrientation() {
		return this.orientation;
	}

	/**
	 * Sets the orientation property of this location to the given one.
	 *
	 * @param orientation the orientation to set to
	 */
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}

	/**
	 * Sets the orientation property of this object to {@code null}.
	 */
	public void unsetOrientation() {
		orientation = null;
	}

	/**
	 * Updates this location's identity URI with a compliant URI. 
	 * 
 	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following methods:
	 * <ul>
	 * <li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 * <li>{@link #setWasDerivedFrom(URI)},</li>
	 * <li>{@link Identified#setIdentity(URI)},</li>
	 * <li>{@link Identified#setDisplayId(String)}, or</li>
	 * <li>{@link Identified#setVersion(String)}. </li>
	 * </ul> 
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
	}

	@Override
	public String toString() {
		return "Location ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ (this.isSetOrientation()?", orientation=" + orientation:"") 
				+ "]";
	}

//	@Override
//	public int compareTo(Location loc) {
//		return 0;
//	}
}
