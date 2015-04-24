package org.sbolstandard.core2;

import java.net.URI;

public abstract class Location extends Identified{

	public Location(URI identity) {
		super(identity);		
	}
	
	protected Location(Location location) {
		super(location);
	}
	
	protected abstract Location deepCopy();
}
