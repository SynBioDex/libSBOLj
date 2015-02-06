package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Cut extends Location {

	private Integer at;
	private URI orientation;

	public Cut(URI identity, Integer at) {
		super(identity);
		setAt(at);
	}

	/**
	 * Returns field variable <code>at</code>.
	 * 
	 * @return field variable <code>at</code>
	 */
	public Integer getAt() {
		return at;
	}

	/**
	 * Sets field variable <code>at</code> to the specified element.
	 * 
	 * @param at
	 */
	public void setAt(Integer at) {
		this.at = at;
	}

	/**
	 * Test if optional field variable <code>orientation</code> is set.
	 * 
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
	 * 
	 * @return field variable <code>orientation</code>
	 */
	// @return the documented object's display ID
	public URI getOrientation() {
		return this.orientation;
	}

	/**
	 * Set field variable <code>orientation</code> to the specified element.
	 * 
	 * @param orientation
	 */
	// Created for backward compatibility to 1.1.
	public void setOrientation(URI orientation) {
		this.orientation = orientation;
	}

	/**
	 * Set optional field variable <code>orientation</code> to <code>null</code>
	 * .
	 */
	public void unsetOrientation() {
		orientation = null;
	}
}
