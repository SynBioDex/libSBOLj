package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Location;

public class MultiRange extends Location{
	
	private List<Range> ranges;

	public MultiRange(URI identity, URI persistentIdentity, String version, 
			List<Range> ranges) {
		super(identity);
		this.ranges = ranges;
	}
	

	public List<Range> getRanges() {
		return ranges;
	}

	public void setRanges(List<Range> ranges) {
		this.ranges = ranges;
	}

}
