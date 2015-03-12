package org.sbolstandard.core2.abstract_classes;

import java.net.URI;

import org.sbolstandard.core2.Range;

public abstract class Location extends Identified{

	public Location(URI identity) {
		super(identity);		
	}
	
	protected Location(Location location) {
		super(location);
	}
	
	public abstract Location deepCopy();
}
