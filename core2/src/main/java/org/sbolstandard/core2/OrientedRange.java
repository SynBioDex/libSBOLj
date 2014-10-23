package org.sbolstandard.core2;

import java.net.URI;

public class OrientedRange extends Range{
	
	private Orientation orientation;
	
	public OrientedRange(URI identity, Integer start, Integer end, Orientation orientation) {
		super(identity, start, end);
		setOrientation(orientation);		
	}

	/**
	 * Returns field variable <code>orientation</code>.
	 * @return field variable <code>orientation</code>
	 */
	public Orientation getOrientation() {		
		return orientation;
	}

	/**
	 * Sets field variable <code>orientation</code> to the specified element.
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;		
	}

}
