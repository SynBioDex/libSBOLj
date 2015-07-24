package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.*;

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

public abstract class Location extends Identified{

	protected OrientationType orientation;

	Location(URI identity) {
		super(identity);		
	}
	
	protected Location(Location location) {
		super(location);
		this.setOrientation(location.getOrientation());
	}
	
	protected abstract Location deepCopy();

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
	 * @param orientation
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setOrientation(OrientationType orientation) {
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
	public void unsetOrientation() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		orientation = null;
	}
	
	/**
	 * Assume this Range object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
	}
}
