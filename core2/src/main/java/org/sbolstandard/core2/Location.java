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
	 * Test if optional field variable <code>orientation</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetOrientation() {
		return orientation != null;
	}

	/**
	 * Returns field variable <code>orientation</code>.
	 * @return field variable <code>orientation</code>
	 */
	public OrientationType getOrientation() {
		return this.orientation;
	}

	/**
	 * Set field variable <code>orientation</code> to the specified element.
	 */
	public void setOrientation(OrientationType orientation) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.orientation = orientation;
	}

	/**
	 * Set optional field variable <code>orientation</code> to <code>null</code>.
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
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
	}
}
