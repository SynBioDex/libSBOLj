package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.ComponentInstance;

public class Component extends ComponentInstance{

	public Component(URI identity, URI access, URI instantiatedComponent) {
		super(identity, access, instantiatedComponent);
	}
}
