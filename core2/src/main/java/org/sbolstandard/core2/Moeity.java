package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Moeity extends Location{

	public Moeity(URI identity, URI persistentIdentity, String version) {
		super(identity, persistentIdentity, version);
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {		
		
	}

}
