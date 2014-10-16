package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Location;

public class MultiRange extends Location{
	
	private List<URI> ranges;

	public MultiRange(URI identity, URI persistentIdentity, String version, 
			List<URI> ranges) {
		super(identity);
		this.ranges = ranges;
	}
	
	public List<URI> getRanges() {
		return ranges;
	}

	public void setRanges(List<URI> ranges) {
		this.ranges = ranges;
	}

}
