package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.ComponentInstantiation;

public class StructuralInstantiation extends ComponentInstantiation{

	public StructuralInstantiation(URI identity, URI componentIdentity, AccessType access, 
			List<URI> type, List<URI> roles, DirectionType direction) {
		super(identity, componentIdentity, access, type, roles);		
	}



}
