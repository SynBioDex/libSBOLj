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

		/**
		 * Convert the specified URI to its corresponding RefinementType instance. 
		 * @param refinement
		 * @return the corresponding RefinementType instance
		 */
		public static RefinementType convertToRefinementType(URI refinement) {
			if (refinement.equals(Refinement.merge)) {
				return RefinementType.MERGE;
			} 
			else if (refinement.equals(Refinement.useLocal)) {
				return RefinementType.USELOCAL;
			}
			else if (refinement.equals(Refinement.useRemote)) {
				return RefinementType.USEREMOTE;
			}
			else if (refinement.equals(Refinement.verifyIdentical)) {
				return RefinementType.VERIFYIDENTICAL;
			}
			else {
				// TODO: Validation?
				return null;
			}
		}

		/**
		 * Returns the refinement type in URI.
		 * @return refinement type in URI
		 */
		public static URI convertToURI(RefinementType refinement) {
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
	}
	
	
	public MapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		super(identity);
		setRefinement(refinement);
		setLocal(local);
		setRemote(remote);		
	}

	private MapsTo(MapsTo mapsTo) {
		super(mapsTo);
		this.setRefinement(mapsTo.getRefinement());
		this.setLocal(mapsTo.getLocal());
		this.setRemote(mapsTo.getRemote());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((refinement == null) ? 0 : refinement.hashCode());
		result = prime * result + ((remote == null) ? 0 : remote.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapsTo other = (MapsTo) obj;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (refinement != other.refinement)
			return false;
		if (remote == null) {
			if (other.remote != null)
				return false;
		} else if (!remote.equals(other.remote))
			return false;
		return true;
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


	@Override
	protected MapsTo deepCopy() {
		return new MapsTo(this);
	}

}
