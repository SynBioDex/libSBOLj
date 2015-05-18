package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.MapsTo.Refinement;

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
	static URI convertToURI(RefinementType refinement) {
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