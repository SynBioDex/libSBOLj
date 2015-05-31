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
	protected HashMap<URI, MapsTo> mapsTos;
	
	ComponentInstance(URI identity, AccessType access, URI definition) {
		super(identity);
		this.mapsTos = new HashMap<>();
		setAccess(access);
		setDefinition(definition);		
	}
	
	protected ComponentInstance(ComponentInstance component) {
		super(component);
		this.mapsTos = new HashMap<>();
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
	 * Returns the access property of this object.
	 * 
	 * @return the access property of this object
	 */
	public AccessType getAccess() {
		return access;
	}

	/**
	 * Sets the access property of this object to the given one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param access
	 * @throws SBOLException if the associated SBOLDocument is not compliant.
	 * @throws if the given {@code access} argument is {@code null}
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
	 * Creates a child MapsTo instance for this object with the given arguments, 
	 * and then adds to this object's list of MapsTo instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant MapsTo URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code displayId} 
	 * and this object's version.
	 * 
	 * @param displayId
	 * @param refinement
	 * @param local
	 * @param remote
	 * @return a MapsTo instance
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
	 * Removes the given MapsTo instance from the list of
	 * MapsTo instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *	
	 * @param mapsTo
	 * @return {@code true} if the matching MapsTo instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLException if the associated SBOLDocument is not compliant.
	 *
	 * @param mapsTo
	 * @return
	 */
	public boolean removeMapsTo(MapsTo mapsTo) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(mapsTo,mapsTos);
	}
	
	/**
	 * Returns the MapsTo instance owned by this object that matches the given display ID.
	 * 
	 * @param mapsToURI
	 * @return the MapsTo instance owned by this object that matches the given display ID
	 */	
	public MapsTo getMapsTo(String displayId) {
		return mapsTos.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}
	
	/**
	 * Returns the MapsTo instance owned by this object that matches the given URI.
	 * 
	 * @param mapsToURI
	 * @return the MapsTo instance owned by this object that matches the given URI
	 */	
	public MapsTo getMapsTo(URI mapsToURI) {
		return mapsTos.get(mapsToURI);
	}
	
	/**
	 * Returns the set of MapsTo instances owned by this object.
	 * 
	 * @return the set of MapsTo instances owned by this object.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}

	/**
	 * Removes all entries of this object's list of
	 * MapsTo objects. The list will be empty after this call returns.
 	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLException if the associated SBOLDocument is not compliant
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
	 * Returns the reference Component Definition URI.
	 * 
	 * @return the reference Component Definition URI
	 */	
	public URI getDefinitionURI() {
		return definition;
	}
	
	/**
	 * Returns the Component Definition instance referenced by this object.
	 * 
	 * @return {@code null} if the associated SBOLDocument instance is {@code null},
	 * the Component Definition instance referenced by this object otherwise.
	 */	
	public ComponentDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getComponentDefinition(definition);
	}

	/**
	 * Sets the definition property of this object to the given one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param definition
	 * @throws SBOLException if the associated SBOLDocument is not compliant.
	 * @throws if the given {@code definition} argument is {@code null}
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely specifies 
	 * 		all URIs and the given definition URI is not found in them.
	 *             
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
