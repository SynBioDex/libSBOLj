package org.sbolstandard.core2;

import java.net.URI;

public class StructuralInstantiation extends ComponentInstantiation{

	public StructuralInstantiation(URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description,
			AccessType access) {
		super(identity, persistentIdentity, version, displayId, name, description,
				access);		
	}



}
