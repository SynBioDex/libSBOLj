package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.ComponentInstantiation;

public class StructuralInstantiation extends ComponentInstantiation{

	public StructuralInstantiation(URI identity, AccessType access, URI instantiatedComponent) {
		super(identity, access, instantiatedComponent);
	}
}
