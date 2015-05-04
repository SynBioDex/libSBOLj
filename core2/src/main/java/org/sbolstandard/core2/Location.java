package org.sbolstandard.core2;

import java.net.URI;

public abstract class Location extends Identified{

	Location(URI identity) {
		super(identity);		
	}
	
	protected Location(Location location) {
		super(location);
	}
	
	protected abstract Location deepCopy();
}
