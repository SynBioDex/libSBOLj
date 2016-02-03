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

public class Component extends ComponentInstance{

	protected HashMap<URI, MapsTo> mapsTos;
	private ComponentDefinition componentDefinition = null;

	Component(URI identity, AccessType access, URI componentDefinition) throws SBOLValidationException {
		super(identity, access, componentDefinition);
		this.mapsTos = new HashMap<>();
	}

	protected Component(Component component) throws SBOLValidationException {
		super(component);
		this.mapsTos = new HashMap<>();
		if (!component.getMapsTos().isEmpty()) {
			Set<MapsTo> mapsTos = new HashSet<>();
			for (MapsTo mapsTo : component.getMapsTos()) {
				mapsTos.add(mapsTo.deepCopy());
			}
			this.setMapsTos(mapsTos);
		}
	}

	@Override
	protected Component deepCopy() throws SBOLValidationException {
		return new Component(this);
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
		URI localURI = URIcompliance.createCompliantURI(componentDefinition.getPersistentIdentity().toString(),
				localId, componentDefinition.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() && componentDefinition!=null &&
				componentDefinition.getComponent(localURI)==null) {
			componentDefinition.createComponent(localId,AccessType.PUBLIC,localId,"");
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
	 * @param displayId ntermediate between name and identity that is machine-readable, but more human-readable than the full URI of an identity
	 * @param refinement Specify the relationship between the local and remote ComponentInstance objects.
	 * @param local refers to the second "higher level" ComponentInstance
	 * @param remote refers to the first "lower level" ComponentInstance
	 * @return a MapsTo instance that specifies the identity relationship of two ComponentInstance
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
		mapsTo.setSBOLDocument(this.sbolDocument);
		mapsTo.setComponentDefinition(componentDefinition);
		mapsTo.setComponentInstance(this);
		if (sbolDocument != null) {
			if (componentDefinition.getComponent(mapsTo.getLocalURI())==null) {
				throw new SBOLValidationException("Component '" + mapsTo.getLocalURI() + "' does not exist.");
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (getDefinition().getComponent(mapsTo.getRemoteURI())==null) {
				throw new SBOLValidationException("Component '" + mapsTo.getRemoteURI() + "' does not exist.");
			}
			if (getDefinition().getComponent(mapsTo.getRemoteURI()).getAccess().equals(AccessType.PRIVATE)) {
				throw new SBOLValidationException("Component '" + mapsTo.getRemoteURI() + "' is private.");
			}
			if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
				if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
					throw new SBOLValidationException("MapsTo '" + mapsTo.getIdentity() + "' have non-identical local and remote Functional Component");
				}
			}
		}
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
	 * @param mapsTo Removes the specified MapsTo instance from the list of MapsTo instances.
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
	 * @param displayId - the displayId of the MapsTo object to be retrieved
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
	 * @param mapsToURI The URI of the MapsTo object to be retrieved
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

	ComponentDefinition getComponentDefinition() {
		return componentDefinition;
	}

	void setComponentDefinition(ComponentDefinition componentDefinition) {
		this.componentDefinition = componentDefinition;
	}

	@Override
	public String toString() {
		return "Component [mapsTos=" + mapsTos + ", definition=" + definition + ", identity="
				+ identity + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
	}
}
