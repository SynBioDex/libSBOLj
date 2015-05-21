package org.sbolstandard.core2;

import java.net.URI;

public enum RefinementType {
	VERIFYIDENTICAL("verifyIdentical"),
	USELOCAL("useLocal"), 
	USEREMOTE("useRemote"),
	MERGE("merge");
	private final String refinementType;

	RefinementType(String refinementType) {
		this.refinementType = refinementType;
	}

	/**
	 * Returns the refinement type.
	 * @return refinement type.
	 */
	String getRefinementType() {
		return refinementType;
	}

	@Override
	public String toString() {
		return refinementType;
	}

	/**
	 * Convert the specified URI to its corresponding RefinementType instance. 
	 * @return the corresponding RefinementType instance
	 */
	static RefinementType convertToRefinementType(URI refinement) {
		if (refinement.equals(merge)) {
			return RefinementType.MERGE;
		} 
		else if (refinement.equals(useLocal)) {
			return RefinementType.USELOCAL;
		}
		else if (refinement.equals(useRemote)) {
			return RefinementType.USEREMOTE;
		}
		else if (refinement.equals(verifyIdentical)) {
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
	static URI convertToURI(RefinementType refinement) {
		if (refinement != null) {
			if (refinement.equals(RefinementType.MERGE)) {
				return merge;
			}
			else if (refinement.equals(RefinementType.USELOCAL)) {
				return useLocal;
			}
			else if (refinement.equals(RefinementType.USEREMOTE)) {
				return useRemote;
			}
			else if (refinement.equals(RefinementType.VERIFYIDENTICAL)) {
				return verifyIdentical;
			}				
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	static final URI merge = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "merge");
	static final URI useLocal = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "useLocal");
	static final URI useRemote = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "useRemote");
	static final URI verifyIdentical = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "verifyIdentical");

}