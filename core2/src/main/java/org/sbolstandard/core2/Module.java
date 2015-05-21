package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class Module extends Identified {
	
	private HashMap<URI, MapsTo> mapsTos;
	private URI definition;
	private ModuleDefinition moduleDefinition = null;

	Module(URI identity, URI moduleDefinition) {
		super(identity);
		setDefinition(moduleDefinition);
		this.mapsTos = new HashMap<>();
	}
	
	private Module(Module module) {
		super(module);
		this.setDefinition(module.getDefinitionURI());
		if (!module.getMapsTos().isEmpty()) {
			List<MapsTo> mappings = new ArrayList<>();
			for (MapsTo mapping : module.getMapsTos()) {
				mappings.add(mapping.deepCopy());
			}
			this.setMapsTos(mappings);
		}
	}

	
	/**
	 * Returns field variable <code>instantiatedModule</code>.
	 * @return field variable <code>instantiatedModule</code>
	 */
	public URI getDefinitionURI() {
		return definition;
	}
		
	public ModuleDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getModuleDefinition(definition);
	}

	/**
	 * Sets field variable <code>instantiatedModule</code> to the specified element.
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
	 * Calls the MapsTo constructor to create a new instance using the specified parameters, 
	 * then adds to the list of MapsTo instances owned by this component.
	 * @return the created MapsTo instance.
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}
	
	public MapsTo createMapsTo(String displayId, RefinementType refinement, String localId, String remoteId) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI local = URIcompliance.createCompliantURI(moduleDefinition.getPersistentIdentity().toString(), 
				localId, moduleDefinition.getVersion());
		URI remote = URIcompliance.createCompliantURI(getDefinition().getPersistentIdentity().toString(), 
				remoteId, getDefinition().getVersion());
		return createMapsTo(displayId,refinement,local,remote);
	}
	
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		URI newMapsToURI = createCompliantURI(parentPersistentIdStr, displayId, version);
		MapsTo m = createMapsTo(newMapsToURI, refinement, local, remote);
		m.setPersistentIdentity(createCompliantURI(parentPersistentIdStr, displayId, ""));
		m.setDisplayId(displayId);
		m.setVersion(version);
		return m;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 */
	void addMapsTo(MapsTo mapsTo) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getLocalURI() + "' does not exist.");
			}
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI()).getAccess().equals(AccessType.PRIVATE)) {
				throw new IllegalArgumentException("Functional Component '" + mapsTo.getLocalURI() + "' is private.");
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
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeMapsTo(MapsTo mapsTo) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(mapsTo,mapsTos);
	}
	
	/**
	 * Returns the instance matching the specified displayId from the list of maps to objects, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapsTo(String displayId) {
		return mapsTos.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of maps to objects, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapsTo(URI referenceURI) {
		return mapsTos.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance.
	 * @return the list of reference instances owned by this instance.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}
	
	/**
	 * Removes all entries of the list of mapsTos owned by this instance. The list will be empty after this call returns.
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
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));		
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		for (MapsTo mapsTo : this.getMapsTos()) {
			mapsTo.updateCompliantURI(this.getPersistentIdentity().toString(), 
					mapsTo.getDisplayId(), version);
			this.removeChildSafely(mapsTo, this.mapsTos);
			this.addMapsTo(mapsTo);
			// TODO: update local 
		}
	}
	
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}

}

