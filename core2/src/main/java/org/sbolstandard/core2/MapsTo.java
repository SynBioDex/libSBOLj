package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

public class MapsTo extends Identified{
	
	private RefinementType refinement;
	private URI local; // URI of a local component instantiation.
	private URI remote; // URI of a remote component instantiation

	public static enum RefinementType {
		VERIFYIDENTICAL("verifyIdentical"),
		USELOCAL("useLocal"), 
		USEREMOTE("useRemote"),
		MERGE("merge");
		private final String refinementType;

		private RefinementType(String refinementType) {
			this.refinementType = refinementType;
		}

		/**
		 * Returns the refinement type.
		 * @return refinement type.
		 */
		public String getRefinementType() {
			return refinementType;
		}

		@Override
		public String toString() {
			return refinementType;
		}
	}
	
	
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
	 * Returns the URI constant corresponds to the field variable <code>refinement</code>.
	 * @return  <code>refinement</code>
	 */
	public URI getRefinementURI() {
		if (refinement != null) {
			if (refinement.equals(RefinementType.MERGE)) {
				return Refinement.merge;
			}
			else if (refinement.equals(RefinementType.USELOCAL)) {
				return Refinement.useLocal;
			}
			else if (refinement.equals(RefinementType.USEREMOTE)) {
				return Refinement.useRemote;
			}
			else if (refinement.equals(RefinementType.VERIFYIDENTICAL)) {
				return Refinement.verifyIdentical;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Sets field variable <code>refinement</code> to the specified element.
	 * @param refinement
	 */
	public void setRefinement(RefinementType refinement) {
		this.refinement = refinement;
	}
	
	/**
	 * Sets field variable <code>refinement</code> according to the specified URI.
	 * @param refinement
	 */
	public void setRefinement(URI refinement) {
		if (refinement.equals(Refinement.merge)) {
			this.refinement = RefinementType.MERGE;
		}
		else if (refinement.equals(Refinement.useLocal)) {
			this.refinement = RefinementType.USELOCAL;
		}
		else if (refinement.equals(Refinement.useRemote)) {
			this.refinement = RefinementType.USEREMOTE;
		}
		else if (refinement.equals(Refinement.verifyIdentical)) {
			this.refinement = RefinementType.VERIFYIDENTICAL;
		}
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
	
	
	public static final class Refinement {
		public static final URI merge = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "merge");
		public static final URI useLocal = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "useLocal");
		public static final URI useRemote = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "useRemote");
		public static final URI verifyIdentical = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "verifyIdentical");
	}

}
