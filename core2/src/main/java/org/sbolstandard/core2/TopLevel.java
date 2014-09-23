package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Documented;

public class TopLevel extends Documented{

	public TopLevel(URI identity, URI persistentIdentity, String version,
			String displayId, String name, String description) {
		super(identity, persistentIdentity, version, displayId, name, description);
	
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO Auto-generated method stub
		
	}

}
