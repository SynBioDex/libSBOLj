package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class GenericLocation extends Location{

	public GenericLocation(URI identity) {
		super(identity);
	}
	
	private OrientationType orientation;
	
		/**
	 * Test if optional field variable <code>orientation</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetOrientation() {
		if (orientation == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Returns field variable <code>orientation</code>.
	 * @return field variable <code>orientation</code>
	 */
	// @return the documented object's display ID
	public OrientationType getOrientation() {
		return this.orientation;
	}
		
	/**
	 * Set field variable <code>orientation</code> to the specified element.
	 * @param orientation
	 */
	// Created for backward compatibility to 1.1. 
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Set optional field variable <code>orientation</code> to <code>null</code>.
	 */
	public void unsetOrientation() {
		orientation = null;
	}

}
