package org.sbolstandard.core2;

import java.net.URI;

public class OrientedCut extends Cut {
	
	private Orientation orientation;
	
	public OrientedCut(URI identity, Integer at, Orientation orientation) {
		super(identity, at);
		setOrientation(orientation);
	}

	/**
	 * Returns field variable <code>orientation</code>.
	 * @return
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
