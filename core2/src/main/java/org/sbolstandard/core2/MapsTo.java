package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.ComponentInstantiation;
import org.sbolstandard.core2.abstract_classes.Identified;

public class MapsTo extends Identified{
	
	private RefinementType refinement;
	private URI local; // URI of a local component instantiation.
	private URI remote; // URI of a remote component instantiation

	public MapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		super(identity);
		setRefinement(refinement);
		setLocal(local);
		setRemote(remote);		
	}

	public RefinementType getRefinement() {
		return refinement;
	}

	public void setRefinement(RefinementType refinement) {
		this.refinement = refinement;
	}

	/**
	 * @return the URI of the local <code>ComponentInstantiation</code> instance.
	 */
	public URI getLocal() {
		return local;
	}

	public void setLocal(URI local) {
		this.local = local;
	}

	/**
	 * @return the URI of the remote <code>ComponentInstantiation</code> instance.
	 */
	public URI getRemote() {
		return remote;
	}

	public void setRemote(URI remote) {
		this.remote = remote;
	}
	
}
