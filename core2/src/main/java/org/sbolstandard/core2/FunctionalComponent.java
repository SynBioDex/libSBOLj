package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
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

public class FunctionalComponent extends ComponentInstance {

	private DirectionType direction;
	protected HashMap<URI, MapsTo> mapsTos;
	private ModuleDefinition moduleDefinition = null;

	FunctionalComponent(URI identity, AccessType access, URI definitionURI,
			DirectionType direction) throws SBOLValidationException {
		super(identity, access, definitionURI);
		this.mapsTos = new HashMap<>();
		setDirection(direction);
	}

	private FunctionalComponent(FunctionalComponent functionalComponent) throws SBOLValidationException {
		super(functionalComponent);
		this.setDirection(functionalComponent.getDirection());
		this.mapsTos = new HashMap<>();
		if (!functionalComponent.getMapsTos().isEmpty()) {
			Set<MapsTo> mapsTos = new HashSet<>();
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				mapsTos.add(mapsTo.deepCopy());
			}
			this.setMapsTos(mapsTos);
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
	 * @param direction The direction for the FunctionalComponent
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the given {@code direction} is {@code null}
	 *
	 */
	public void setDirection(DirectionType direction) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (direction==null) {
			throw new SBOLValidationException("sbol-11802",this);
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
	protected FunctionalComponent deepCopy() throws SBOLValidationException {
		return new FunctionalComponent(this);
	}

	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @throws SBOLValidationException 
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
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
	 * @throws SBOLValidationException 
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, URI local, URI remote) throws SBOLValidationException {
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
	 * @throws SBOLValidationException if the SBOLDocument instance already completely
	 * specifies all URIs and the given {@code local} argument is not found in the list
	 * of functional components that are owned by the ModuleDefinition instance that
	 * this Module object refers to.
	 * @throws SBOLValidationException if the SBOLDocument instance already completely
	 * specifies all URIs and the given {@code remote} argument is not found in
	 * the list of functional components that are owned by the ModuleDefinition instance that
	 * this Module object refers to.
	 * @throws SBOLValidationException if the SBOLDocument instance already completely
	 * specifies all URIs and the given {@code remote} URI refers to a FunctionalComponent
	 * with {@code private} access type that is owned by the ModuleDefinition instance that
	 * this Module object refers to.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, String localId, String remoteId) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI localURI = URIcompliance.createCompliantURI(moduleDefinition.getPersistentIdentity().toString(),
				localId, moduleDefinition.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() && moduleDefinition!=null &&
				moduleDefinition.getFunctionalComponent(localURI)==null) {
			moduleDefinition.createFunctionalComponent(localId,AccessType.PUBLIC,localId,"",DirectionType.INOUT);
		}
		URI remoteURI = URIcompliance.createCompliantURI(getDefinition().getPersistentIdentity().toString(),
				remoteId, getDefinition().getVersion());
		return createMapsTo(displayId,refinement,localURI,remoteURI);
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
	 * @param displayId The displayId identifier for this SequenceAnnotation
	 * @param refinement Specify the relationship between its local and remote ComponentInstance objects using one of the REQUIRED refinement URIs.
	 * @param local refer to the ComponentInstance contained by the “higher level” ComponentDefinition or ModuleDefinition
	 * @param remote refer to the ComponentInstance contained by the “lower level” ComponentDefinition or ModuleDefinition
	 * @return a MapsTo instance
	 * @throws SBOLValidationException 
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) throws SBOLValidationException {
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
	 * @throws SBOLValidationException 
	 */
	void addMapsTo(MapsTo mapsTo) throws SBOLValidationException {
		if (sbolDocument != null) {
			if (moduleDefinition.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				//throw new SBOLValidationException("Functional component '" + mapsTo.getLocalURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10804", mapsTo);
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (getDefinition().getComponent(mapsTo.getRemoteURI())==null) {
				//throw new SBOLValidationException("Component '" + mapsTo.getRemoteURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10808", mapsTo);
			}
			if (getDefinition().getComponent(mapsTo.getRemoteURI()).getAccess().equals(AccessType.PRIVATE)) {
				//throw new SBOLValidationException("Component '" + mapsTo.getRemoteURI() + "' is private.");
				throw new SBOLValidationException("sbol-10807", mapsTo);
			}
			if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
				if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
					//throw new SBOLValidationException("MapsTo '" + mapsTo.getIdentity() + "' have non-identical local and remote Functional Component");
					throw new SBOLValidationException("sbol-10811", mapsTo);
				}
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
	 * @param mapsTo The mapsTo object to be removed
	 * @return {@code true} if the matching MapsTo instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 *
	 */
	public boolean removeMapsTo(MapsTo mapsTo) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(mapsTo,mapsTos);
	}

	/**
	 * Returns the MapsTo instance owned by this object that matches the given display ID.
	 *
	 * @param displayId The displayId identifier for this SequenceAnnotation
	 * @return the MapsTo instance owned by this object that matches the given display ID
	 */
	public MapsTo getMapsTo(String displayId) {
		try {
			return mapsTos.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the MapsTo instance owned by this object that matches the given URI.
	 *
	 * @param mapsToURI The URI reference for the MapsTo object
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
	public void clearMapsTos() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = mapsTos.values().toArray();
		for (Object mapsTo : valueSetArray) {
			removeMapsTo((MapsTo)mapsTo);
		}
	}

	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException 
	 */
	void setMapsTos(Set<MapsTo> mapsTos) throws SBOLValidationException {
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
				+ ", definition=" + definition + ", identity=" + identity + ", displayId="
				+ displayId + ", name=" + name + ", description=" + description + "]";
	}
}
