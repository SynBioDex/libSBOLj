package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.ComponentInstantiation;
import org.sbolstandard.core2.abstract_classes.Identified;

public class MapsTo extends Identified{
	
	private RefinementType refinement;
	private ComponentInstantiation local;
	private ComponentInstantiation remote;

	public MapsTo(URI identity, RefinementType refinement, 
			ComponentInstantiation local, ComponentInstantiation remote) {
		super(identity);
		this.refinement = refinement;
		this.local = local;
		this.remote = remote;
	}

	public RefinementType getRefinement() {
		return refinement;
	}

	public void setRefinement(RefinementType refinement) {
		this.refinement = refinement;
	}

	public ComponentInstantiation getLocal() {
		return local;
	}

	public void setLocal(ComponentInstantiation local) {
		this.local = local;
	}

	public ComponentInstantiation getRemote() {
		return remote;
	}

	public void setRemote(ComponentInstantiation remote) {
		this.remote = remote;
	}
	
}
