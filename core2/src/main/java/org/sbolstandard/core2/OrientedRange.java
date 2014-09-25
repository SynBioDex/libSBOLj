package org.sbolstandard.core2;

import java.net.URI;

public class OrientedRange extends Range{
	
	private Orientation orientation;
	
	public OrientedRange(URI identity, Integer start, Integer end, Orientation orientation) {
		super(identity, start, end);
		this.orientation = orientation;		
	}

	public Orientation getOrientation() {		
		return orientation;
	}

	public void setOrientation(Orientation ori) {
		orientation = ori;		
	}
	
	

}
