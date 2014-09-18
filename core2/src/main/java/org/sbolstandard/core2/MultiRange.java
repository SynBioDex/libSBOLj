package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Location;

public class MultiRange extends Location{
	
	private List<Range> ranges;

	public MultiRange(URI identity, URI persistentIdentity, String version, 
			List<Range> ranges) {
		super(identity, persistentIdentity, version);
		this.ranges = ranges;
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO add a visit method here.		
	}

}
