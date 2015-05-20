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

public abstract class ComponentInstance extends Identified {
	
	private AccessType access;
	protected URI definition;
	private HashMap<URI, MapsTo> mapsTos;
	
	ComponentInstance(URI identity, AccessType access, URI definition) {
		super(identity);
		setAccess(access);
		setDefinition(definition);		
		this.mapsTos = new HashMap<>();
		/*
		if (isURIcompliant(identity, 1)) {
			this.setVersion(extractVersion(identity));
			this.setDisplayId(extractDisplayId(identity, 0));
			this.setPersistentIdentity(URI.create(extractPersistentId(identity)));
		}
		*/
	}
	
	protected ComponentInstance(ComponentInstance component) {
		super(component);
		setAccess(component.getAccess());
		setDefinition(component.getDefinitionURI());	
		if (!component.getMapsTos().isEmpty()) {
			List<MapsTo> mapsTos = new ArrayList<>();
			for (MapsTo mapsTo : component.getMapsTos()) {
				mapsTos.add(mapsTo.deepCopy());
			}
			this.setMapsTo(mapsTos);
		}
	}

	/**
	 * Returns field variable <code>access</code>.
	 * @return field variable <code>access</code>
	 */
	public AccessType getAccess() {
		return access;
	}

	/**
	 * Sets field variable <code>access</code> to the specified element.
	 */
	public void setAccess(AccessType access) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (access==null) {
			throw new IllegalArgumentException("Not a valid access type.");
		}
		this.access = access;
	}
	
	/**
	 * Calls the MapsTo constructor to create a new instance using the specified parameters, 
	 * then adds to the list of MapsTo instances owned by this component.
	 *
	 * @return the created MapsTo instance.
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}
		
	/**
	 * @return the newly created MapsTo object.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		MapsTo m = createMapsTo(createCompliantURI(parentPersistentIdStr, displayId, version),
				refinement, local, remote);
		m.setPersistentIdentity(createCompliantURI(parentPersistentIdStr, displayId, ""));
		m.setDisplayId(displayId);
		m.setVersion(version);
		return m;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 */
	void addMapsTo(MapsTo mapsTo) {
		addChildSafely(mapsTo, mapsTos, "mapsTo");
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
	public MapsTo getMapsTo(URI mapsToURI) {
		return mapsTos.get(mapsToURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance. 
	 * @return the list of reference instances owned by this instance.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}
	
	/**
	 * Removes all entries of the list of mapsTo owned by this instance. The list will be empty after this call returns.
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
	void setMapsTo(
			List<MapsTo> mapsTos) {
		clearMapsTos();		
		for (MapsTo reference : mapsTos) {
			addMapsTo(reference);
		}
	}
	
	/**
	 * Returns field variable <code>definition</code>
	 * @return field variable <code>definition</code>
	 */
	public URI getDefinitionURI() {
		return definition;
	}
	
	/**
	 * Returns the Component Definition referenced by this Component Instance
	 * @return the Component Definition referenced by this Component Instance
	 */	
	public ComponentDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getComponentDefinition(definition);
	}

	/**
	 * Sets field variable <code>instantiatedComponent</code> to the specified element.
	 */
	public void setDefinition(URI definition) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (definition==null) {
			throw new IllegalArgumentException("Component "+this.getIdentity()+" must have a definition.");
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(definition)==null) {
				throw new IllegalArgumentException("Component definition '" + definition + "' does not exist.");
			}
		}
		this.definition = definition;
	}

	protected abstract ComponentInstance deepCopy();

}
