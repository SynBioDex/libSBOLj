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

	protected Location(Location location) throws SBOLValidationException {
		super(location);
		this.setOrientation(location.getOrientation());
	}

	@Override
	protected abstract Location deepCopy() throws SBOLValidationException;

	/**
	 * Test if the orientation property is set.
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetOrientation() {
		return orientation != null;
	}

	/**
	 * Returns the orientation property of this object.
	 * @return the orientation property of this object.
	 */
	public OrientationType getOrientation() {
		return this.orientation;
	}

	/**
	 * Sets the orientation property of this object to the given one.
	 *
	 * @param orientation Indicate how the region specified by the SequenceAnnotation and any associated double stranded Component is oriented on the elements of a Sequence from their parent ComponentDefinition.
	 */
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}

	/**
	 * Sets the orientation property of this object to {@code null}.
	 *
	 */
	public void unsetOrientation() {
		orientation = null;
	}

	/**
	 * Assume this Range object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 * @throws SBOLValidationException 
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
