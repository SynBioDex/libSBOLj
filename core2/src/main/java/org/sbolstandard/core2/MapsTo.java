package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class MapsTo extends Identified{
	
	private RefinementType refinement;
	private URI local; // URI of a local component instantiation.
	private URI remote; // URI of a remote component instantiation
	private ModuleDefinition moduleDefinition = null;
	private Module module = null;

	MapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		super(identity);
		setRefinement(refinement);
		setLocal(local);
		setRemote(remote);		
	}

	private MapsTo(MapsTo mapsTo) {
		super(mapsTo);
		this.setRefinement(mapsTo.getRefinement());
		this.setLocal(mapsTo.getLocalURI());
		this.setRemote(mapsTo.getRemoteURI());
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
	URI getRefinementURI() {
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
	 */
	public void setRefinement(RefinementType refinement) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.refinement = refinement;
	}
	
	/**
	 * Sets field variable <code>refinement</code> according to the specified URI.
	 */
	void setRefinement(URI refinement) {
		if (refinement != null && refinement.equals(Refinement.merge)) {
			this.refinement = RefinementType.MERGE;
		}
		else if (refinement != null && refinement.equals(Refinement.useLocal)) {
			this.refinement = RefinementType.USELOCAL;
		}
		else if (refinement != null && refinement.equals(Refinement.useRemote)) {
			this.refinement = RefinementType.USEREMOTE;
		}
		else if (refinement != null && refinement.equals(Refinement.verifyIdentical)) {
			this.refinement = RefinementType.VERIFYIDENTICAL;
		} else {
			throw new IllegalArgumentException("Not a valid refinement type.");
		}
	}

	/**
	 * Returns field variable <code>local</code>.
	 * @return field variable <code>local</code>
	 */
	public URI getLocalURI() {
		return local;
	}
	
	public FunctionalComponent getLocal() {
		if (moduleDefinition==null) return null;
		return moduleDefinition.getFunctionalComponent(local);
	}

	/**
	 * Sets field variable <code>local</code> to the specified element.
	 */
	public void setLocal(URI local) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (local==null) {
			throw new IllegalArgumentException("MapsTo "+this.getIdentity()+" must specify a local component.");
		}
		if (moduleDefinition!=null) {
			if (moduleDefinition.getFunctionalComponent(local)==null) {
				throw new IllegalArgumentException("Functional Component '" + local + "' does not exist.");
			}
			if (moduleDefinition.getFunctionalComponent(local).getAccess().equals(AccessType.PRIVATE)) {
				throw new IllegalArgumentException("Functional Component '" + local + "' is private.");
			}
		}
		this.local = local;
	}

	/**
	 * Returns field variable <code>remote</code>.
	 * @return field variable <code>remote</code>
	 */
	public URI getRemoteURI() {
		return remote;
	}
	
	public FunctionalComponent getRemote() {
		if (module==null) return null;
		if (module.getDefinition()==null) return null;
		return module.getDefinition().getFunctionalComponent(remote);
	}

	/**
	 * Sets filed variable <code>remote</code> to the specified element.
	 */
	public void setRemote(URI remote) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (remote==null) {
			throw new IllegalArgumentException("MapsTo "+this.getIdentity()+" must specify a remote component.");
		}
		if (module!=null) {
			if (module.getDefinition().getFunctionalComponent(remote)==null) {
				throw new IllegalArgumentException("Functional Component '" + remote + "' does not exist.");
			}
			if (module.getDefinition().getFunctionalComponent(remote).getAccess().equals(AccessType.PRIVATE)) {
				throw new IllegalArgumentException("Functional Component '" + remote + "' is private.");
			}
		}
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
	
	
	static final class Refinement {
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

	/**
	 * Assume this MapsTo object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link Component#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId,
			String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 2 indicates that this object is a grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + grandparentDisplayId + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}

	/**
	 * @return the moduleDefinition
	 */
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	/**
	 * @param moduleDefinition the moduleDefinition to set
	 */
	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}


	Module getModule() {
		return module;
	}

	void setModule(Module module) {
		this.module = module;
	}

}
