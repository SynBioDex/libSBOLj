package org.sbolstandard.core2;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public abstract class Location extends Identified{

	Location(URI identity) {
		super(identity);		
	}
	
	protected Location(Location location) {
		super(location);
	}
	
	protected abstract Location deepCopy();
}
