package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the SBOL Component data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class Component extends ComponentInstance{

	protected HashMap<URI, MapsTo> mapsTos;
	private Set<URI> roles;
	private RoleIntegrationType roleIntegration;
	/**
	 * Parent component definition of this component
	 */
	private ComponentDefinition componentDefinition = null;

	/**
	 * @param identity
	 * @param access
	 * @param definition the referenced component definition
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred 
	 * in {@link ComponentInstance#ComponentInstance(URI, AccessType, URI)}
	 */
	Component(URI identity, AccessType access, URI definition) throws SBOLValidationException {
		super(identity, access, definition);
		this.mapsTos = new HashMap<>();
		this.roles = new HashSet<>();
	}

	protected Component(Component component) throws SBOLValidationException {
		super(component);
		this.roles = new HashSet<>();
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
	 * Assume this Component instance and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * 
 	 * @throws SBOLValidationException if any of the following is true:
 	 * <ul> 
	 * <li>an SBOL validation exception occurred in {@link URIcompliance#createCompliantURI(String, String, String)};</li>
	 * <li>an SBOL validation exception occurred in {@link #setWasDerivedFrom(URI)};</li>
	 * <li>an SBOL validation exception occurred in {@link #setIdentity(URI)};</li>
	 * <li>an SBOL validation exception occurred in {@link #setDisplayId(String)};</li>
	 * <li>an SBOL validation exception occurred in {@link #setVersion(String)};</li>
	 * <li>an SBOL validation exception occurred in {@link MapsTo#updateCompliantURI(String, String, String)};</li>
	 * <li>an SBOL validation exception occurred in {@link #addMapsTo(MapsTo)};</li>
	 * <li>an SBOL validation exception occurred in {@link MapsTo#setLocal(URI)};</li>
	 * </ul>
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
	 * Adds the given role URI to this component's set of role URIs.
	 *
	 * @param roleURI the role URI to be added
	 * @return {@code true} if this set did not already contain the specified role, {@code false} otherwise.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}

	/**
	 * Removes the given role URI from the set of roles.
	 *
	 * @param roleURI the given role URI to be removed
	 * @return {@code true} if the matching role reference was removed successfully, {@code false} otherwise.
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of roles first, and then adds the given
	 * set of the roles to this component.
	 *
	 * @param roles the set of roles to be set
	 */
	public void setRoles(Set<URI> roles) {
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the set of role URIs owned by this component. 
	 *
	 * @return the set of role URIs owned by this component.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}

	/**
	 * Checks if the given role URI is included in this component's set of role URIs.
	 *
	 * @param roleURI the role URI to be checked
	 * @return {@code true} if this set contains the given role URI, {@code false} otherwise.
	 */
	public boolean containsRole(URI roleURI) {
		return roles.contains(roleURI);
	}

	/**
	 * Removes all entries of this component's set of role URIs.
	 * The set will be empty after this call returns.	 
	 */
	public void clearRoles() {
		roles.clear();
	}
	
	/**
	 * Test if the roleIntegration property is set.
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetRoleIntegration() {
		return roleIntegration != null;
	}

	/**
	 * Returns the roleIntegration property of this object.
	 * @return the roleIntegration property of this object.
	 */
	public RoleIntegrationType getRoleIntegration() {
		return this.roleIntegration;
	}

	/**
	 * Sets the roleIntegration property of this object to the given one.
	 *
	 * @param roleIntegration indicates how role is to be integrated with related roles.
	 */
	public void setRoleIntegration(RoleIntegrationType roleIntegration) {
		this.roleIntegration = roleIntegration;
	}

	/**
	 * Sets the roleIntegration property of this object to {@code null}.
	 *
	 */
	public void unsetRoleIntegration() {
		roleIntegration = null;
	}

	/**
	 * Calls the MapsTo constructor to create a new instance using the specified parameters,
	 * then adds to the list of MapsTo instances owned by this component.
	 *
	 * @return the created MapsTo instance.
 	 * @throws SBOLValidationException if any of the following is true:
 	 * <ul> 
	 * <li>any of the following SBOL validation rules was violated: 10803, 10807, 10808, 10811;</li>
	 * <li>an SBOL validation exception occurred in {@link SBOLValidate#checkComponentDefinitionMapsTos(ComponentDefinition, MapsTo)};</li>
	 * <li>an SBOL validation exception occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)};</li>
	 * </ul>
	 */
	MapsTo createMapsTo(URI identity, RefinementType refinement, URI local, URI remote) throws SBOLValidationException {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}

	/**
	 * Creates a child mapsTo for this component with the given arguments, and then adds it to its list of mapsTos.
	 * <p>
	 * This method creates compliant local and remote URIs first.
	 * The compliant local URI is created with this component's persistent identity URI, followed by
	 * the given local component's display ID, followed by this component's version. 
	 * The compliant remote URI is created following the same pattern.
	 * It then calls {@link #createMapsTo(String, RefinementType, URI, URI)} to create
	 * a MapsTo instance.
	 * <p>
	 * This method automatically creates a local component if all of the following conditions are satisfied:
	 * <ul>
	 * <li>the associated SBOLDocument instance for this component is not {@code null};</li>
	 * <li>if default components should be automatically created when not present for the associated SBOLDocument instance,
	 * i.e., {@link SBOLDocument#isCreateDefaults} returns {@code true};</li>
	 * <li>if this component's parent ComponentDefinition instance exists; and</li>
	 * <li>if this component's parent ComponentDefinition instance does not already have a component
	 * with the created compliant local URI.</li> 
	 * </ul>
	 * 
	 * @param displayId the display ID of the MapsTo instance to be created 
	 * @param refinement the relationship between the local and remote components
	 * @param localId the display ID of the local component
	 * @param remoteId the display ID of the remote component
	 * @return the created mapsTo 
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <ul>
	 * <li>if either of the following SBOL validation rules was violated: 10204, 10206;</li>
	 * <li>an SBOL validation exception occurred in {@link ComponentDefinition#createComponent(String, AccessType, String, String)}; or</li>
	 * <li>an SBOL validation exception occurred in {@link #createMapsTo(String, RefinementType, URI, URI)}.</li>
	 * </ul>
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, String localId, String remoteId) throws SBOLValidationException {
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
	 * Creates a child MapsTo instance for this component with the given arguments,
	 * and then adds it to its list of MapsTo instances.
	 * 
	 * @param displayId the display ID of the MapsTo instance to be created
	 * @param refinement the relationship between the local and remote components
	 * @param local the identity URI of the local component
	 * @param remote the identity URI of the remote component
	 * @return the created MapsTo instance 
 	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
 	 * 10202, 10204, 10206, 10526, 10803, 10807, 10808, 10811.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) throws SBOLValidationException {
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
	 * @throws SBOLValidationException if any of the following is true:
	 * <ul> 
	 * <li>any of the following SBOL validation rules was violated: 10803, 10807, 10808, 10811;</li>
	 * <li>an SBOL validation exception occurred in {@link SBOLValidate#checkComponentDefinitionMapsTos(ComponentDefinition, MapsTo)}; or</li>
	 * <li>an SBOL validation exception occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}.</li>
	 * </ul>
	 */
	void addMapsTo(MapsTo mapsTo) throws SBOLValidationException {
		mapsTo.setSBOLDocument(this.sbolDocument);
		mapsTo.setComponentDefinition(componentDefinition);
		mapsTo.setComponentInstance(this);
		if (sbolDocument != null) {
			if (componentDefinition.getComponent(mapsTo.getLocalURI())==null) {
				//throw new SBOLValidationException("Component '" + mapsTo.getLocalURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10803", mapsTo);
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
		if (componentDefinition!=null) {
			SBOLValidate.checkComponentDefinitionMapsTos(componentDefinition, mapsTo);
		}
		addChildSafely(mapsTo, mapsTos, "mapsTo");
	}

	/**
	 * Removes the given MapsTo instance from the list of
	 * MapsTo instances.
	 *
	 * @param mapsTo a MapsTo instance to be removed
	 * @return {@code true} if the matching MapsTo instance is removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeMapsTo(MapsTo mapsTo) {
		return removeChildSafely(mapsTo,mapsTos);
	}

	/**
	 * Returns the MapsTo instance that matches the given display ID.
	 *
	 * @param displayId the displayId of the MapsTo instance to be retrieved
	 * @return the MapsTo instance that matches the given display ID
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
	 * Returns the MapsTo instance that matches the given URI.
	 *
	 * @param mapsToURI The URI of the MapsTo instance to be retrieved
	 * @return the MapsTo instance that matches the given URI
	 */
	public MapsTo getMapsTo(URI mapsToURI) {
		return mapsTos.get(mapsToURI);
	}

	/**
	 * Returns the set of MapsTo instances owned by this instance.
	 *
	 * @return the set of MapsTo instances owned by this instance.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}

	/**
	 * Removes all entries the list of MapsTo instances. The list will be empty after this call returns.
	 */
	public void clearMapsTos() {
		Object[] valueSetArray = mapsTos.values().toArray();
		for (Object mapsTo : valueSetArray) {
			removeMapsTo((MapsTo)mapsTo);
		}
	}

	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * 
 	 * @throws SBOLValidationException if any of the following is true:
 	 * <ul> 
	 * <li>any of the following SBOL validation rules was violated: 10803, 10807, 10808, 10811;</li>
	 * <li>an SBOL validation exception occurred in {@link SBOLValidate#checkComponentDefinitionMapsTos(ComponentDefinition, MapsTo)};</li>
	 * <li>an SBOL validation exception occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)};</li>
	 * </ul>
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Component other = (Component) obj;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (mapsTos == null) {
			if (other.mapsTos != null)
				return false;
		} else if (!mapsTos.equals(other.mapsTos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Component ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", access=" + this.getAccess()
				+ ", definition=" + definition 
				+ (roles.size()>0?", roles=" + roles:"")  
				+ (this.getMapsTos().size()>0?", mapsTos=" + this.getMapsTos():"") 
				+ "]";
	}
}
