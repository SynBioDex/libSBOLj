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

public class FunctionalComponent extends ComponentInstance {

	private DirectionType direction;
	protected HashMap<URI, MapsTo> mapsTos;
	private ModuleDefinition moduleDefinition = null;

	FunctionalComponent(URI identity, AccessType access, URI definitionURI,
			DirectionType direction) {
		super(identity, access, definitionURI);
		this.mapsTos = new HashMap<>();
		setDirection(direction);
	}
	
	private FunctionalComponent(FunctionalComponent functionalComponent) {
		super(functionalComponent);
		this.setDirection(functionalComponent.getDirection());
		this.mapsTos = new HashMap<>();
		if (!functionalComponent.getMapsTos().isEmpty()) {
			List<MapsTo> mapsTos = new ArrayList<>();
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				mapsTos.add(mapsTo.deepCopy());
			}
			this.setMapsTo(mapsTos);
		}
	}

	/**
	 * Returns the direction property of this FunctionalComponent object.
	 * 
	 * @return the direction property of this FunctionalComponent object
	 */
	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * Sets the direction property of this FunctionalComponent object to the given one.
	 * <p>
	 * If this FunctionalComponent object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param direction
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the given {@code direction} is {@code null}
	 * 
	 */
	public void setDirection(DirectionType direction) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (direction==null) {
			throw new NullPointerException("Not a valid direction type.");
		}
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
		FunctionalComponent other = (FunctionalComponent) obj;
		return direction == other.direction;
	}

	@Override
	protected FunctionalComponent deepCopy() {
		return new FunctionalComponent(this);
	}

	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
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

	/**
	 * Calls the MapsTo constructor to create a new instance using the specified parameters, 
	 * then adds to the list of MapsTo instances owned by this component.
	 *
	 * @return the created MapsTo instance.
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, URI local, URI remote) {
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
		String parentPersistentIdStr = this.getPersistentIdentity().toString();
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
		if (sbolDocument != null) {
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new IllegalArgumentException("Functional component '" + mapsTo.getLocalURI() + "' does not exist.");
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (getDefinition().getComponent(mapsTo.getRemoteURI())==null) {
				throw new IllegalArgumentException("Component '" + mapsTo.getRemoteURI() + "' does not exist.");
			}
			if (getDefinition().getComponent(mapsTo.getRemoteURI()).getAccess().equals(AccessType.PRIVATE)) {
				throw new IllegalArgumentException("Component '" + mapsTo.getRemoteURI() + "' is private.");
			}
		}
		addChildSafely(mapsTo, mapsTos, "mapsTo");
		mapsTo.setSBOLDocument(this.sbolDocument);
        mapsTo.setModuleDefinition(moduleDefinition);
        mapsTo.setComponentInstance(this);
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
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 *
	 */
	public boolean removeMapsTo(MapsTo mapsTo) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(mapsTo,mapsTos);
	}

	/**
	 * Returns the MapsTo instance owned by this object that matches the given display ID.
	 * 
	 * @param displayId
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
	void setMapsTo(List<MapsTo> mapsTos) {
		clearMapsTos();		
		for (MapsTo reference : mapsTos) {
			addMapsTo(reference);
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
		return "FunctionalComponent [direction=" + direction + ", mapsTos=" + mapsTos
				+ ", moduleDefinition=" + moduleDefinition + "]";
	}
}
