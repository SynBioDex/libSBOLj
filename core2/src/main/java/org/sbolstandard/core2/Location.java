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
}
