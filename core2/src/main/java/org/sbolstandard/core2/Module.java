package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;
import static org.sbolstandard.core2.URIcompliance.extractPersistentId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class Module extends Identified {
	
	private HashMap<URI, MapsTo> mapsTos;
	private URI definition;
	private ModuleDefinition moduleDefinition = null;

	Module(URI identity, URI moduleDefinition) {
		super(identity);
		this.mapsTos = new HashMap<>();
		setDefinition(moduleDefinition);
	}
	
	private Module(Module module) {
		super(module);
		this.mapsTos = new HashMap<>();
		this.setDefinition(module.getDefinitionURI());
		for (MapsTo mapping : module.getMapsTos()) {
			this.addMapsTo(mapping.deepCopy());
		}
	}
	
	/**
	 * Returns the ModuleDefinition URI that this Module object refers to.
	 * 
	 * @return the ModuleDefinition URI that this Module object refers to
	 */
	public URI getDefinitionURI() {
		return definition;
	}
		
	/**
	 * Returns the ModuleDefinition instance that this Module object refers to.
	 * 
	 * @return the ModuleDefinition instance that this Module object refers to,
	 * if the associated SBOLDocument instance is not {@code null}; {@code null} otherwise 
	 */
	public ModuleDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getModuleDefinition(definition);
	}

	/**
	 * Sets the reference definition URI to the given {@code definitionURI}.
	 * <p>
	 * If this Module object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant local and a compliant remote URIs. 
	 * They are created with this Module object's persistent ID,
	 * the given {@code localId} or {@code remoteId}, and this Module object's version.
	 * It then calls {@link #createMapsTo(String, RefinementType, URI, URI)} to create
	 * a MapsTo instance.  
	 * 
	 * @param definitionURI
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the given {@code definitionURI} is {@code null} 
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely 
	 * specifies all URIs and the given {@code definitionURI} argument is not found in 
	 * its list of ModuleDefinition instances.
	 */
	public void setDefinition(URI definitionURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (definitionURI==null) {
			throw new IllegalArgumentException("Module "+this.getIdentity()+" must have a definition.");
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModuleDefinition(definitionURI)==null) {
				throw new IllegalArgumentException("Module definition '" + definition + "' does not exist.");
			}
		}
		this.definition = definitionURI;
	}
	
			
//	/**
//	 * Test if optional field variable <code>references</code> is set.
//	 * @return <code>true</code> if it is not an empty list
//	 */
//	public boolean isSetMappings() {
//		if (mapsTos.isEmpty())
//			return false;
//		else
//			return true;
//	}
	
	/**
	 * @param identity
	 * @param refinement
	 * @param local
	 * @param remote
	 * @return new MapsTo object
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}
	
	/**
	 * Creates a child MapsTo instance for this Module
	 * object with the given arguments, and then adds to this Module's list of MapsTo
	 * instances.
	 * <p>
	 * If this Module object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant local and a compliant remote URIs. 
	 * They are created with this Module object's persistent ID,
	 * the given {@code localId} or {@code remoteId}, and this Module object's version.
	 * It then calls {@link #createMapsTo(String, RefinementType, URI, URI)} to create
	 * a MapsTo instance.   
	 *  
	 * @param displayId
	 * @param refinement
	 * @param localId
	 * @param remoteId
	 * @return a MapsTo instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely 
	 * specifies all URIs and the given {@code local} argument is not found in the list 
	 * of functional components that are owned by the ModuleDefinition instance that 
	 * this Module object refers to.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely 
	 * specifies all URIs and the given {@code remote} argument is not found in 
	 * the list of functional components that are owned by the ModuleDefinition instance that 
	 * this Module object refers to.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely  
	 * specifies all URIs and the given {@code remote} URI refers to a FunctionalComponent
	 * with {@code private} access type that is owned by the ModuleDefinition instance that
	 * this Module object refers to.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, String localId, String remoteId) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI localURI = URIcompliance.createCompliantURI(moduleDefinition.getPersistentIdentity().toString(), 
				localId, moduleDefinition.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() && moduleDefinition!=null &&
				moduleDefinition.getFunctionalComponent(localURI)==null) {
			moduleDefinition.createFunctionalComponent(localId,AccessType.PUBLIC,localId,"",DirectionType.INOUT);
		}
		URI remote = URIcompliance.createCompliantURI(getDefinition().getPersistentIdentity().toString(), 
				remoteId, getDefinition().getVersion());
		return createMapsTo(displayId,refinement,localURI,remote);
	}
	
	/**
	 * Creates a child MapsTo instance for this Module
	 * object with the given arguments, and then adds to this Module's list of MapsTo
	 * instances.
	 * <p>
	 * If this Module object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant MapsTo URI with this Module object's persistent ID,
	 * the given {@code displayId}, and this Module object's version.
	 *  
	 * @param displayId
	 * @param refinement
	 * @param local
	 * @param remote
	 * @return a MapsTo instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely 
	 * specifies all URIs and the given {@code local} argument is not found in the list 
	 * of functional components that are owned by the ModuleDefinition instance that 
	 * this Module object refers to.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely 
	 * specifies all URIs and the given {@code remote} argument is not found in 
	 * the list of functional components that are owned by the ModuleDefinition instance that 
	 * this Module object refers to.
	 * @throws IllegalArgumentException if the SBOLDocument instance already completely  
	 * specifies all URIs and the given {@code remote} URI refers to a FunctionalComponent
	 * with {@code private} access type that is owned by the ModuleDefinition instance that
	 * this Module object refers to.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String parentPersistentIdStr = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newMapsToURI = createCompliantURI(parentPersistentIdStr, displayId, version);
		MapsTo m = createMapsTo(newMapsToURI, refinement, local, remote);
		m.setPersistentIdentity(createCompliantURI(parentPersistentIdStr, displayId, ""));
		m.setDisplayId(displayId);
		m.setVersion(version);
		return m;
	}

	/**
	 * @param mapsTo
	 */
	void addMapsTo(MapsTo mapsTo) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getLocalURI() + "' does not exist.");
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (getDefinition().getFunctionalComponent(mapsTo.getRemoteURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getRemoteURI() + "' does not exist.");
			}
			if (getDefinition().getFunctionalComponent(mapsTo.getRemoteURI()).getAccess().equals(AccessType.PRIVATE)) {
				throw new IllegalArgumentException("Functional Component '" + mapsTo.getRemoteURI() + "' is private.");
			}
		}
		addChildSafely(mapsTo, mapsTos, "mapsTo");
		mapsTo.setSBOLDocument(this.sbolDocument);
        mapsTo.setModuleDefinition(moduleDefinition);
        mapsTo.setModule(this);
	}
	
	/**
	 * Removes the given MapsTo instance from the list of MapsTo
	 * instances.
	 * <p>
	 * If this Module object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param mapsTo
	 * @return {@code true} if the matching MapsTo instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */	
	public boolean removeMapsTo(MapsTo mapsTo) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(mapsTo,mapsTos);
	}

	/**
	 * Returns the MapsTo instance owned by this Module object that matches the given {@code displayId}
	 * 
	 * @param displayId
	 * @return the matching MapsTo instance
	 */
	public MapsTo getMapsTo(String displayId) {
		return mapsTos.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}

	/**
	 * Returns the MapsTo instance owned by this Module object that matches the given {@code displayId}
	 * 
	 * @param referenceURI
	 * @return the matching MapsTo instnace URI
	 */
	public MapsTo getMapsTo(URI referenceURI) {
		return mapsTos.get(referenceURI);
	}
	
	/**
	 * Returns the set of MapsTo instances referenced by this Module object.
	 * 
	 * @return the set of MapsTo instances referenced by this Module object.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}

	/**
	 * Removes all entries of this Module object's list of MapsTo
	 * instances. The set will be empty after this call returns.
  	 * <p>
	 * If this Module object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant  
	 */
	public void clearMapsTos() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = mapsTos.values().toArray();
		for (Object mapsTo : valueSetArray) {
			removeMapsTo((MapsTo)mapsTo);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setMapsTos(
			List<MapsTo> mappings) {
		clearMapsTos();		
		for (MapsTo mapping : mappings) {
			addMapsTo(mapping);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + ((mapsTos == null) ? 0 : mapsTos.hashCode());
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
		Module other = (Module) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (mapsTos == null) {
			if (other.mapsTos != null)
				return false;
		} else if (!mapsTos.equals(other.mapsTos))
			return false;
		return true;
	}


	@Override
	protected Module deepCopy() {
		return new Module(this);
	}

	/**
	 * Assume this Module object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ModuleDefinition#copy(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));		
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		for (MapsTo mapsTo : this.getMapsTos()) {
			mapsTo.updateCompliantURI(this.getPersistentIdentity().toString(), 
					mapsTo.getDisplayId(), version);
			this.removeChildSafely(mapsTo, this.mapsTos);
			this.addMapsTo(mapsTo);
			String localId = extractDisplayId(mapsTo.getLocalURI());
			mapsTo.setLocal(createCompliantURI(URIprefix,localId,version));
		}
	}
	
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}

	@Override
	public String toString() {
		return "Module [mapsTos=" + mapsTos + ", definition=" + definition + ", moduleDefinition="
				+ moduleDefinition + "]";
	}

}

