package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Range extends Location{
	
	private Integer start;
	private Integer end;
	private URI orientation;
	
	public Range(URI identity, Integer start, Integer end) {
		super(identity);
		setStart(start);
		setEnd(end);
	}
	
	
	/**
	 * Sets field variable <code>start</code> to the specified element.
	 * @param value
	 */
	public void setStart(Integer value) {
		start = value;		
	}
	
	/**
	 * Returns field variable <code>start</code>.
	 * @return field variable <code>start</code>
	 */
	public int getStart() {		
		return start;
	}

	/**
	 * Returns field variable <code>end</code>.
	 * @return field variable <code>end</code>
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Sets field variable <code>end</code> to the specified element.
	 * @param value
	 */
	public void setEnd(Integer value) {
		end = value;
	}
	
	
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
	public URI getOrientation() {
		return this.orientation;
	}
		
	/**
	 * Set field variable <code>orientation</code> to the specified element.
	 * @param orientation
	 */
	// Created for backward compatibility to 1.1. 
	public void setOrientation(URI orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Set optional field variable <code>orientation</code> to <code>null</code>.
	 */
	public void unsetOrientation() {
		orientation = null;
	}

}
