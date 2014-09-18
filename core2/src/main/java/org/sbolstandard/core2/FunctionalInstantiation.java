package org.sbolstandard.core2;

import java.net.URI;

public class FunctionalInstantiation extends ComponentInstantiation{
		
	private DirectionType direction;
	
	public FunctionalInstantiation(URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description,
			AccessType access, DirectionType direction) {
		super(identity, persistentIdentity, version, displayId, name, description,
				access);
		this.direction = direction;
	}

	public DirectionType getDirection() {
		return direction;
	}

	public void setDirection(DirectionType direction) {
		this.direction = direction;
	}
}
