package org.sbolstandard.core2;

import java.net.URI;

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

	/**
	 * Returns field variable <code>refinement</code>.
	 * @return field variable <code>refinement</code>
	 */
	public RefinementType getRefinement() {
		return refinement;
	}

	/**
	 * Sets field variable <code>refinement</code> to the specified element.
	 * @param refinement
	 */
	public void setRefinement(RefinementType refinement) {
		this.refinement = refinement;
	}

	/**
	 * Returns field variable <code>local</code>.
	 * @return field variable <code>local</code>
	 */
	public URI getLocal() {
		return local;
	}

	/**
	 * Sets field variable <code>local</code> to the specified element.
	 * @param local
	 */
	public void setLocal(URI local) {
		this.local = local;
	}

	/**
	 * Returns field variable <code>remote</code>.
	 * @return field variable <code>remote</code>
	 */
	public URI getRemote() {
		return remote;
	}

	/**
	 * Sets filed variable <code>remote</code> to the specified element.
	 * @param remote
	 */
	public void setRemote(URI remote) {
		this.remote = remote;
	}
	
}
