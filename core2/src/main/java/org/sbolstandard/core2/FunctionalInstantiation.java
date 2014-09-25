package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.ComponentInstantiation;

public class FunctionalInstantiation extends ComponentInstantiation{
		
	private DirectionType direction;
	
	public FunctionalInstantiation(URI identity, URI componentIdentity, AccessType access, 
			List<URI> type, List<URI> roles, DirectionType direction) {
		super(identity, componentIdentity, access, type, roles);
		this.direction = direction;
	}

	public DirectionType getDirection() {
		return direction;
	}

	public void setDirection(DirectionType direction) {
		this.direction = direction;
	}
}
