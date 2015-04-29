package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.MapsTo.RefinementType;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Module extends Documented {
	
	private HashMap<URI, MapsTo> mapsTos;
	private URI definition;
	private ModuleDefinition moduleDefinition = null;

	public Module(URI identity, URI moduleDefinition) {
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
	public MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}
	
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		URI newMapsToURI = createCompliantURI(parentPersistentIdStr, displayId, version);
		return createMapsTo(newMapsToURI, refinement, local, remote);
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 */
	public void addMapsTo(MapsTo mapsTo) {
		if (sbolDocument.isComplete()) {
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getLocal() + "' does not exist.");
			}
		}
		if (sbolDocument.isComplete()) {
			if (getDefinition().getFunctionalComponent(mapsTo.getRemoteURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getRemote() + "' does not exist.");
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
	public MapsTo removeMapsTo(URI referenceURI) {
		return mapsTos.remove(referenceURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
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
	 * Removes all entries of the list of reference instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearMapsTos() {
		Object[] keySetArray = mapsTos.keySet().toArray();
		for (Object key : keySetArray) {
			removeMapsTo((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	public void setMapsTos(
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
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 1); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		if (!this.getMapsTos().isEmpty()) {
			// Update children's URIs
			for (MapsTo mapsTo : this.getMapsTos()) {
				mapsTo.updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
			}
		}
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);		
	}
	
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}

}

