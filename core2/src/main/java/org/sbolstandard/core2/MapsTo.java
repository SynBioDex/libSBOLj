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
	private ComponentDefinition componentDefinition = null;
	private ComponentInstance componentInstance = null;

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
	 * Returns the refinement property of this MapsTo object.
	 * 
	 * @return the refinement property of this MapsTo object.
	 */
	public RefinementType getRefinement() {
		return refinement;
	}

	/**
	 * Sets the refinement property of this MapsTo object to the given one. 
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param refinement
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void setRefinement(RefinementType refinement) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.refinement = refinement;
	}

	/**
	 * Returns the local FunctionalComponent URI that this object refers to.
	 * 
	 * @return the local FunctionalComponent URI that this object refers to
	 */
	public URI getLocalURI() {
		return local;
	}
	
	/**
	 * Returns the local ComponentInstance instance that this object refers to.
	 * 
	 * @return the local ComponentInstance instance that this object refers to
	 * if this MapsTo object's reference ModuleDefinition instance is not {@code null},
	 * or if this MapsTo object's parent ComponentInstance instance is not {@code null}; 
	 * or {@code null} otherwise.    
	 */
	public ComponentInstance getLocal() {
		if (moduleDefinition!=null) {
			return moduleDefinition.getFunctionalComponent(local);
		} else if (componentDefinition!=null) {
			return componentDefinition.getComponent(local);
		}
		return null;
	}
	
	/**
	 * Get the component definition for the local element of this mapsTo.
	 * @return the component definition for the local element of this mapsTo.
	 */
	public ComponentDefinition getLocalDefinition() {
		if (moduleDefinition!=null) {
			return moduleDefinition.getFunctionalComponent(local).getDefinition();
		} else if (componentDefinition!=null) {
			return componentDefinition.getComponent(local).getDefinition();
		}
		return null;
	}

	/**
	 * Sets the local property of this MapsTo object to the given one.
 	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param local
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the given {@code local} argument is {@code null}
	 * @throws IllegalArgumentException if the given {@code local} argument is not found in 
	 * this MapsTo object's reference ModuleDefinition instance's list of functional components.
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
		} else if (componentDefinition!=null) {
			if (componentDefinition.getComponent(local)==null) {
				throw new IllegalArgumentException("Component '" + local + "' does not exist.");
			}
		}
		this.local = local;
	}

	/**
	 * Returns the remote FunctionalComponent URI that this object refers to.
	 * 
	 * @return the remote FunctionalComponent URI that this object refers to
	 */

	public URI getRemoteURI() {
		return remote;
	}
 
	/**
	 * Returns the remote ComponentInstance instance that this object refers to.
	 * 
	 * @return the remote ComponentInstance instance that this object refers to,
	 * if this MapsTo object's parent Module instance is not {@code null} and its
	 * reference ModuleDefinition instance is not {@code null}, 
	 * or if this MapsTo object's parent ComponentInstance instance is not {@code null} 
	 * and its reference ComponentDefinition instance is not {@code null};
	 * or {@code null} otherwise.    
	 */
	public ComponentInstance getRemote() {
		if (module!=null) {
			if (module.getDefinition()==null) return null;
			return module.getDefinition().getFunctionalComponent(remote);
		} else if (componentInstance!=null) {
			if (componentInstance.getDefinition()==null) return null;
			return componentInstance.getDefinition().getComponent(remote);
		}
		return null;
	}
	
	/**
	 * Get the component definition for the remote element of this mapsTo.
	 * @return the component definition for the remote element of this mapsTo.
	 */
	public ComponentDefinition getRemoteDefinition() {
		if (module!=null) {
			if (module.getDefinition()==null) return null;
			return module.getDefinition().getFunctionalComponent(remote).getDefinition();
		} else if (componentInstance!=null) {
			if (componentInstance.getDefinition()==null) return null;
			return componentInstance.getDefinition().getComponent(remote).getDefinition();
		}
		return null;
	}
	
	/**
	 * Sets the remote property of this MapsTo object to the given one.
 	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param remote
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the given {@code remote} argument is {@code null}
	 * @throws IllegalArgumentException if the given {@code remote} argument is not found in 
	 * the list of functional components that are owned by the ModuleDefinition instance that
	 * this MapsTo object's parent Module instance refers to.
	 * @throws IllegalArgumentException if the given {@code remote} argument refers to a FunctionalComponent
	 * with {@code private} access type that is owned by the ModuleDefinition instance that
	 * this MapsTo object's parent Module instance refers to.
	 */
	public void setRemote(URI remote) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (remote==null) {
			throw new IllegalArgumentException("MapsTo "+this.getIdentity()+" must specify a remote component.");
		}
		if (module!=null) {
			if (module.getDefinition()!=null) {
				if (module.getDefinition().getFunctionalComponent(remote)==null) {
					throw new IllegalArgumentException("Functional Component '" + remote + "' does not exist.");
				}
				if (module.getDefinition().getFunctionalComponent(remote).getAccess().equals(AccessType.PRIVATE)) {
					throw new IllegalArgumentException("Functional Component '" + remote + "' is private.");
				}
			}
		} else if (componentInstance!=null) {
			if (componentInstance.getDefinition()!=null) {
				if (componentInstance.getDefinition().getComponent(remote)==null) {
					throw new IllegalArgumentException("Component '" + remote + "' does not exist.");
				}
				if (componentInstance.getDefinition().getComponent(remote).getAccess().equals(AccessType.PRIVATE)) {
					throw new IllegalArgumentException("Component '" + remote + "' is private.");
				}
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

	@Override
	protected MapsTo deepCopy() {
		return new MapsTo(this);
	}

	/**
	 * Assume this MapsTo object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link Component#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
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

	ComponentDefinition getComponentDefinition() {
		return componentDefinition;
	}

	void setComponentDefinition(ComponentDefinition componentDefinition) {
		this.componentDefinition = componentDefinition;
	}

	ComponentInstance getComponentInstance() {
		return componentInstance;
	}

	void setComponentInstance(ComponentInstance componentInstance) {
		this.componentInstance = componentInstance;
	}

	@Override
	public String toString() {
		return "MapsTo [refinement=" + refinement + ", local=" + local + ", remote=" + remote
				+ ", moduleDefinition=" + moduleDefinition + ", module=" + module
				+ ", componentDefinition=" + componentDefinition + ", componentInstance="
				+ componentInstance + "]";
	}

}
