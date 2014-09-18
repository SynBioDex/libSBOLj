package org.sbolstandard.core2;

import java.net.URI;

public class OrientedCut extends Cut {
	
	private Orientation orientation;
	
	public OrientedCut(URI identity, URI persistentIdentity, String version,
			Integer at, Orientation orientation) {
		super(identity, persistentIdentity, version, at);
		this.orientation = orientation;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	
}
