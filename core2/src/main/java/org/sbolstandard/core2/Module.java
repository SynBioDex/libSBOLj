package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the SBOL Module data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class Module extends Identified {

	private HashMap<URI, MapsTo> mapsTos;
	private URI definition;
	private ModuleDefinition moduleDefinition = null;

	Module(URI identity, URI moduleDefinition) throws SBOLValidationException {
		super(identity);
		this.mapsTos = new HashMap<>();
		setDefinition(moduleDefinition);
	}

	private Module(Module module) throws SBOLValidationException {
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
	 * @param definitionURI The definition URI for this Module.
	 * @throws SBOLValidationException if the given {@code definitionURI} is {@code null}
	 * @throws SBOLValidationException if the SBOLDocument instance already completely
	 * specifies all URIs and the given {@code definitionURI} argument is not found in
	 * its list of ModuleDefinition instances.
	 */
	public void setDefinition(URI definitionURI) throws SBOLValidationException {
		if (definitionURI==null) {
			throw new SBOLValidationException("sbol-11702",this);
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModuleDefinition(definitionURI)==null) {
				throw new SBOLValidationException("sbol-11703",this);
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
	 * @param identity The displayId identifier for this Module
	 * @param refinement the refinement type for this MapsTo object
	 * @param local The reference URI local object for this MapsTo object
	 * @param remote The reference URI remote object for this MapsTo object
	 * @return new MapsTo object
	 * @throws SBOLValidationException
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement,
			URI local, URI remote) throws SBOLValidationException {
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
	 * @param displayId The displayId identifier for this MapsTo
	 * @param refinement The refinementType for this MapsTo object
	 * @param localId The second “higher level” ComponentInstance for this MapsTo object
	 * @param remoteId The first “lower level” ComponentInstance for this MapsTo object
	 * @return a MapsTo instance
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
	 * @param displayId The displayId identifier for this MapsTo object
	 * @param refinement he rela- 25 tionship between its local and remote ComponentInstance objects using one of the REQUIRED refinement URIs
	 * @param local The second “higher level” ComponentInstance for this MapsTo object
	 * @param remote The first “lower level” ComponentInstance for this MapsTo object
	 * @return a MapsTo instance
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
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) throws SBOLValidationException {
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
	 * @throws SBOLValidationException
	 */
	void addMapsTo(MapsTo mapsTo) throws SBOLValidationException {
		mapsTo.setSBOLDocument(this.sbolDocument);
		mapsTo.setModuleDefinition(moduleDefinition);
		mapsTo.setModule(this);
		if (sbolDocument != null) {
			if (mapsTo.getLocal()==null) {
				//throw new SBOLValidationException("Functional component '" + mapsTo.getLocalURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10804", mapsTo);
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (mapsTo.getRemote()==null) {
				//throw new SBOLValidationException("Functional component '" + mapsTo.getRemoteURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10809", mapsTo);
			}
			if (mapsTo.getRemote().getAccess().equals(AccessType.PRIVATE)) {
				//throw new SBOLValidationException("Functional Component '" + mapsTo.getRemoteURI() + "' is private.");
				throw new SBOLValidationException("sbol-10807", mapsTo);
			}
			if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
				if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
					//throw new SBOLValidationException("MapsTo '" + mapsTo.getIdentity() + "' have non-identical local and remote Functional Component");
					throw new SBOLValidationException("sbol-10811", mapsTo);
				}
			}
		}
		if (moduleDefinition!=null) {
			SBOLValidate.checkModuleDefinitionMapsTos(moduleDefinition, mapsTo);
		}
		addChildSafely(mapsTo, mapsTos, "mapsTo");
	}

	/**
	 * Removes the given MapsTo instance from the list of MapsTo
	 * instances.
	 * <p>
	 * If this Module object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param mapsTo The MapsTo object to be removed
	 * @return {@code true} if the matching MapsTo instance is removed successfully, {@code false} otherwise.
	 */
	public boolean removeMapsTo(MapsTo mapsTo) {
		return removeChildSafely(mapsTo,mapsTos);
	}

	/**
	 * Returns the MapsTo instance owned by this Module object that matches the given {@code displayId}
	 *
	 * @param displayId The displayId identifier for this MapsTo object
	 * @return the matching MapsTo instance
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
	 * Returns the MapsTo instance owned by this Module object that matches the given {@code displayId}
	 *
	 * @param referenceURI The MapsTo URI object
	 * @return the matching MapsTo instance URI
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
	 */
	public void clearMapsTos() {
		Object[] valueSetArray = mapsTos.values().toArray();
		for (Object mapsTo : valueSetArray) {
			removeMapsTo((MapsTo)mapsTo);
		}
	}

	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException
	 */
	void setMapsTos(Set<MapsTo> mappings) throws SBOLValidationException {
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
	protected Module deepCopy() throws SBOLValidationException {
		return new Module(this);
	}

	/**
	 * Assume this Module object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ModuleDefinition#copy(String, String, String)}.
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

	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}

	@Override
	public String toString() {
		return "Module ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", definition=" + definition 
				+ (this.getMapsTos().size()>0?", mapsTos=" + this.getMapsTos():"") 
				+ "]";
	}

}

