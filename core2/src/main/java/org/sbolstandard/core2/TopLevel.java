package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Documented;

public class TopLevel extends Documented{

	public TopLevel(URI identity) {
		super(identity);	
	}

	public TopLevel(String authority, String id) {
		super(authority, id);
	}
	
	/**
	 * Clone the object first, set its display ID to the specified value, and set the major version to 1 and minor version to 0.
	 * @param id
	 * @return the copied object
	 */
	public TopLevel copy(String id) {
		// TODO: possible runtime typecast exception? 
		TopLevel cloned = (TopLevel) this.clone();
		cloned.setDisplayId(id);
		cloned.setMajorVersion(1);
		cloned.setMinorVersion(0);
		return cloned;
	}

}
