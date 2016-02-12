package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

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

public abstract class Location extends Identified implements Comparable<Location> {

	protected OrientationType orientation;

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
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param orientation Indicate how the region specified by the SequenceAnnotation and any associated double stranded Component is oriented on the elements of a Sequence from their parent ComponentDefinition.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setOrientation(OrientationType orientation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.orientation = orientation;
	}

	/**
	 * Sets the orientation property of this object to {@code null}.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void unsetOrientation() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
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
		return "Location [orientation=" + orientation + ", identity=" + identity + ", displayId="
				+ displayId + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public int compareTo(Location loc) {
		// TODO Auto-generated method stub
		return 0;
	}
}
