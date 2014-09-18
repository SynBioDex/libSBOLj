package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class RefersTo extends Identified{
	
	private RefinementType refinement;
	private ComponentInstantiation local;
	private ComponentInstantiation remote;

	public RefersTo(URI identity, URI persistentIdentity, String version,
			RefinementType refinement, ComponentInstantiation local, ComponentInstantiation remote) {
		super(identity, persistentIdentity, version);
		this.refinement = refinement;
		this.local = local;
		this.remote = remote;
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
