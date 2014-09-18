package org.sbolstandard.core2;

import java.net.URI;

public class OrientedRange extends Range{
	
	private Orientation orientation;
	
	public OrientedRange(URI identity, URI persistentIdentity, String version,
			Integer start, Integer end, Orientation orientation) {
		super(identity, identity, version, start, end);
		this.orientation = orientation;		
	}

	public Orientation getOrientation() {		
		return orientation;
	}

	public void setOrientation(Orientation ori) {
		orientation = ori;		
	}
	
	

}
