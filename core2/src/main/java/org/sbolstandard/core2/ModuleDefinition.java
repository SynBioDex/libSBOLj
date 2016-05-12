package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.isChildURIcompliant;
import static org.sbolstandard.core2.URIcompliance.isTopLevelURIformCompliant;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the SBOL ModuleDefinition data model.
 *
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class ModuleDefinition extends TopLevel {

	private Set<URI>							roles;
	private HashMap<URI, Module>				modules;
	private HashMap<URI, Interaction>			interactions;
	private HashMap<URI, FunctionalComponent>	functionalComponents;
	private Set<URI>							models;

	ModuleDefinition(URI identity) throws SBOLValidationException {
		super(identity);
		this.roles = new HashSet<>();
		this.modules = new HashMap<>();
		this.interactions = new HashMap<>();
		this.functionalComponents = new HashMap<>();
		this.models = new HashSet<>();
	}

	private ModuleDefinition(ModuleDefinition moduleDefinition) throws SBOLValidationException {
		super(moduleDefinition);
		this.roles = new HashSet<>();
		this.modules = new HashMap<>();
		this.interactions = new HashMap<>();
		this.functionalComponents = new HashMap<>();
		this.models = new HashSet<>();
		for (URI role : moduleDefinition.getRoles()) {
			this.addRole(role);
		}
		for (FunctionalComponent component : moduleDefinition.getFunctionalComponents()) {
			this.addFunctionalComponent(component.deepCopy());
		}
		for (Module subModule : moduleDefinition.getModules()) {
			this.addModule(subModule.deepCopy());
		}
		for (Interaction interaction : moduleDefinition.getInteractions()) {
			this.addInteraction(interaction.deepCopy());
		}
		this.setModels(moduleDefinition.getModelURIs());
	}

	/**
	 * Adds the given role URI to this ModuleDefinition's set of role URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI The referenced URI for this ModuleDefinition's object
	 * @return {@code true} if this set did not already contain the specified role.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}

	/**
	 * Removes the given role reference from the set of role references.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI The referenced role URI for this object
	 * @return {@code true} if the matching role reference is removed successfully, {@code false} otherwise.
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of role references first, then adds the given
	 * set of the role references to this ModuleDefinition object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roles The set of URI roles to be added to this ModuleDefinition
	 */
	public void setRoles(Set<URI> roles) {
		clearRoles();
		if (roles == null)
			return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the set of role URIs owned by this ModuleDefinition object.
	 *
	 * @return the set of role URIs owned by this ModuleDefinition object.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}

	/**
	 * Checks if the given role URI is included in this ModuleDefinition
	 * object's set of reference role URIs.
	 *
	 * @param roleURI The referenced role URI for this ModuleDefinition
	 * @return {@code true} if this set contains the specified URI.
	 */
	public boolean containsRole(URI roleURI) {
		return roles.contains(roleURI);
	}

	/**
	 * Removes all entries of this ModuleDefinition object's set of role URIs.
	 * The set will be empty after this call returns.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 */
	public void clearRoles() {
		roles.clear();
	}

	/**
	 * Calls the ModuleInstantiation constructor to create a new instance using
	 * the specified parameters,
	 * then adds to the list of ModuleInstantiation instances owned by this
	 * ModuleDefinition object.
	 *
	 * @param identity the identifier for this object
	 * @param moduleDefinitionURI The moduleDefinition URI reference for this object
	 * @return a Module instance
	 * @throws SBOLValidationException
	 */
	Module createModule(URI identity, URI moduleDefinitionURI) throws SBOLValidationException {
		Module module = new Module(identity, moduleDefinitionURI);
		addModule(module);
		return module;
	}

	/**
	 * Creates a child Module instance for this ModuleDefinition object with the
	 * specified arguments, and then adds to this ModuleDefinition's list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Module URI with the default URI prefix
	 * for this SBOLDocument instance, and the given {@code moduleDefinitionId} and {@code version}.
	 * It then calls {@link #createModule(String, URI)} with this component
	 * definition URI.
	 *
	 * @param displayId The displayId identifier for this Module
	 * @param moduleDefinitionId The ModuleDefinitionId for this Module object
	 * @param version The version for this Module object
	 * @return a Module instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Module createModule(String displayId, String moduleDefinitionId, String version) throws SBOLValidationException {
		URI module = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.MODULE_DEFINITION, moduleDefinitionId, version, sbolDocument.isTypesInURIs());
		return createModule(displayId, module);
	}

	/**
	 * Creates a child Module instance for this ModuleDefinition object with the
	 * specified arguments, and then adds to this ModuleDefinition's list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Module URI with the default URI prefix
	 * for this SBOLDocument instance, and the given {@code moduleDefinitionId}.
	 * It then calls {@link #createModule(String, URI)} with this component
	 * definition URI.
	 *
	 * @param displayId The displayId identifier for this object
	 * @param moduleDefinitionId The moduleDefinition URI reference for this object
	 * @return a Module instance
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	public Module createModule(String displayId, String moduleDefinitionId) throws SBOLValidationException {
		return createModule(displayId, moduleDefinitionId, "");
	}

	/**
	 * Creates a child Module instance for this ModuleDefinition object with the
	 * specified arguments, and then adds to this ModuleDefinition's list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all URIs and
	 * the given {@code moduleDefinitionURI} is not found in them, then an
	 * {@link SBOLValidationException} is thrown.
	 * <p>
	 * This method creates a compliant Module URI with the default URI prefix
	 * for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 *
	 * @param displayId The displayId identifier for this Module object
	 * @param moduleDefinitionURI The moduleDefinition URI reference for this object
	 * @return a Module instance
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely specifies all URIs and the given {@code definitionURI}
               is not found in them.
	 */
	public Module createModule(String displayId, URI moduleDefinitionURI) throws SBOLValidationException {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModuleDefinition(moduleDefinitionURI) == null) {
				throw new SBOLValidationException("sbol-11703",this);
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newModuleURI = createCompliantURI(URIprefix, displayId, version);
		Module m = createModule(newModuleURI, moduleDefinitionURI);
		m.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		m.setDisplayId(displayId);
		m.setVersion(version);
		return m;
	}

	/**
	 * Adds the given {@code module} argument to this ModuleDefinition's list of
	 * Module instances, and then associates it with the SBOLDocument instance that also contains
	 * this ModuleDefinition object.
	 * @param module the given module
	 * @throws SBOLValidationException
	 */
	void addModule(Module module) throws SBOLValidationException {
		module.setSBOLDocument(this.sbolDocument);
		module.setModuleDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (module.getDefinition() == null) {
				throw new SBOLValidationException("sbol-11703", module);
			}
		}
		if (module.getDefinition()!=null &&
				this.getIdentity().equals(module.getDefinition().getIdentity())) {
			throw new SBOLValidationException("sbol-11704", module);
		}
		Set<URI> visited = new HashSet<>();
		visited.add(this.getIdentity());
		try { 
			SBOLValidate.checkModuleDefinitionCycle(sbolDocument, module.getDefinition(), visited);
		} catch (SBOLValidationException e) {
			throw new SBOLValidationException("sbol-11705", module);
		}
		addChildSafely(module, modules, "module", functionalComponents, interactions);
		for (MapsTo mapsTo : module.getMapsTos()) {
			if (this.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new SBOLValidationException("sbol-10804", mapsTo);
			}
			mapsTo.setSBOLDocument(sbolDocument);
			mapsTo.setModuleDefinition(this);
			mapsTo.setModule(module);
		}
	}

	/**
	 * Removes the specified Module instance from the list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param module The module to be removed
	 * @return {@code true} if the matching Module instance is removed successfully, {@code false} otherwise.
	 *
	 */
	public boolean removeModule(Module module) {
		return removeChildSafely(module, modules);
	}

	/**
	 * Returns the Module instance matching the given displayId from the list of
	 * Module instances.
	 *
	 * @param displayId The displayId identifier for this Module
	 * @return the matching Module instance if present, or {@code null} otherwise.
	 */
	public Module getModule(String displayId) {
		try {
			return modules.get(createCompliantURI(this.getPersistentIdentity().toString(), displayId,
					this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given URI from the list of Module instances.
	 *
	 * @param moduleURI The reference URI for this Module
	 * @return the matching Module instance if present, or {@code null} otherwise.
	 */
	public Module getModule(URI moduleURI) {
		return modules.get(moduleURI);
	}

	/**
	 *
	 * Returns the set of Module instances owned by this ModuleDefinition object.
	 *
	 * @return the set of Module instances owned by this ModuleDefinition object.
	 *
	 */
	public Set<Module> getModules() {
		return new HashSet<>(modules.values());
	}

	/**
	 * Removes all entries of this ModuleDefinition object's list of Module
	 * objects. The set will be empty after this call returns.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 */
	public void clearModules()  {
		Object[] valueSetArray = modules.values().toArray();
		for (Object module : valueSetArray) {
			removeModule((Module) module);
		}

	}

	/**
	 * Clears the existing set of Module instances first, then adds the given
	 * set of the Module instances to this ModuleDefinition object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param modules The set of modules to be added to the SBOL document
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 *
	 */
	void setModules(Set<Module> modules) throws SBOLValidationException {
		clearModules();
		if (modules == null)
			return;
		for (Module module : modules) {
			addModule(module);
		}
	}

	/**
	 * Calls the Interaction constructor to create a new instance using the
	 * given parameters, then adds to the list of Interaction instances owned by this
	 * ModuleDefinition object.
	 *
	 * @param identity the identifier for this object
	 * @param types
	 * @return the created Interaction instance.
	 * @throws SBOLValidationException
	 */
	Interaction createInteraction(URI identity, Set<URI> types) throws SBOLValidationException {
		Interaction interaction = new Interaction(identity, types);
		addInteraction(interaction);
		return interaction;
	}

	/**
	 * Creates a child Interaction object for this ModuleDefinition object with
	 * the given arguments, and then adds to this ModuleDefinition's list of Interaction instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Interaction URI with the default URI
	 * prefix for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 *
	 * @param displayId The displayId identifier for this Interaction object
	 * @param types The set of types to be added to the Interaction
	 * @return the created Interaction instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Interaction createInteraction(String displayId, Set<URI> types) throws SBOLValidationException {
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newInteractionURI = createCompliantURI(URIprefix, displayId, version);
		Interaction i = createInteraction(newInteractionURI, types);
		i.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		i.setDisplayId(displayId);
		i.setVersion(version);
		return i;
	}

	/**
	 * Creates a child Interaction object for this ModuleDefinition object with
	 * the given arguments, and then adds to this ModuleDefinition's list of Interaction instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Interaction URI with the default URI
	 * prefix for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 *
	 * @param displayId The displayId identifier for this object
	 * @param type The type to be added to the Interaction
	 * @return the created Interaction instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Interaction createInteraction(String displayId, URI type) throws SBOLValidationException {
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newInteractionURI = createCompliantURI(URIprefix, displayId, version);
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		Interaction i = createInteraction(newInteractionURI, types);
		i.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		i.setDisplayId(displayId);
		i.setVersion(version);
		return i;
	}

	/**
	 * Adds the given Interaction instance to the list of Interaction instances.
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	void addInteraction(Interaction interaction) throws SBOLValidationException {
		addChildSafely(interaction, interactions, "interaction", functionalComponents, modules);
		interaction.setSBOLDocument(this.sbolDocument);
		interaction.setModuleDefinition(this);
		for (Participation participation : interaction.getParticipations()) {
			if (this.getFunctionalComponent(participation.getParticipantURI())==null) {
				throw new SBOLValidationException("sbol-12003",participation);
			}
			participation.setSBOLDocument(sbolDocument);
			participation.setModuleDefinition(this);
		}
	}

	/**
	 * Removes the given Interaction instance from the list of Interaction
	 * instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param interaction The interaction to be removed
	 * @return {@code true} if the matching Interaction instance is removed successfully, {@code false} otherwise.
	 */
	public boolean removeInteraction(Interaction interaction) {
		return removeChildSafely(interaction, interactions);
	}

	/**
	 * Returns the instance matching the given Interaction displayId from the
	 * list of Interaction instances.
	 *
	 * @param displayId The displayId identifier for this Interaction
	 * @return the matching instance if present, {@code null} otherwise.
	 */
	public Interaction getInteraction(String displayId) {
		try {
			return interactions.get(createCompliantURI(this.getPersistentIdentity().toString(),
					displayId, this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given Interaction URI from the list of
	 * Interaction instances.
	 *
	 * @param interactionURI The referenced URI for the Interaction object
	 * @return the matching instance if present, {@code null} otherwise.
	 */
	public Interaction getInteraction(URI interactionURI) {
		return interactions.get(interactionURI);
	}

	/**
	 * Returns the set of interaction instances owned by this ModuleDefinition
	 * object.
	 *
	 * @return the set of interaction instances owned by this ModuleDefinition
	 *         object.
	 */
	public Set<Interaction> getInteractions() {
		return new HashSet<>(interactions.values());
	}

	/**
	 * Removes all entries of this ModuleDefinition object's list of Instance objects.
	 * The list will be empty after this call returns.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 */
	public void clearInteractions() {
		Object[] valueSetArray = interactions.values().toArray();
		for (Object interaction : valueSetArray) {
			removeInteraction((Interaction) interaction);
		}
	}

	/**
	 * Clears the existing list of interaction instances, then appends all of
	 * the elements in the given collection to the end of this list.
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	void setInteractions(Set<Interaction> interactions) throws SBOLValidationException {
		clearInteractions();
		if (interactions == null)
			return;
		for (Interaction interaction : interactions) {
			addInteraction(interaction);
		}
	}

	/**
	 * @param identity the identifier for this object
	 * @param access indicates whether the ComponentInstance can be referred to remotely
	 * @param definitionURI
	 * @param direction specify whether this FunctionalComponent is an input, output, both, or neither
	 * @return the created functional component
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>an SBOL validation rule violation occurred in {@link FunctionalComponent#FunctionalComponent(URI, AccessType, URI, DirectionType)}</li>
	 * <li>an SBOL validation rule violation occurred in {@link #addFunctionalComponent(FunctionalComponent)}</li>
	 * </ul>
	 */

	FunctionalComponent createFunctionalComponent(URI identity, AccessType access,
			URI definitionURI, DirectionType direction) throws SBOLValidationException {
		FunctionalComponent functionalComponent =
				new FunctionalComponent(identity, access, definitionURI, direction);
		addFunctionalComponent(functionalComponent);
		return functionalComponent;
	}

	/**
	 * Creates a child functional component for this module definition with the given arguments, 
	 * and then adds to this module definition's list of functional components.
	 * <p>
	 * This method first creates a compliant component definition URI referenced by the functional 
	 * component. This compliant URI is created with the associated SBOLDocument instance's URI prefix, 
	 * followed by the given component definition's display ID, which is followed by this module definition's version. 
	 * It then calls {@link #createFunctionalComponent(String, AccessType, URI,DirectionType)}
	 * with the created component definition URI.
	 *
	 * @param displayId the display ID of the functional component to be created
	 * @param access the access property of the functional component to be created
	 * @param definitionId the display ID of the component definition referenced by the functional component to be created
	 * @param version the version of the functional component to be created 
	 * @param direction the direction property of the functional component to be created
	 * @return the created functional component
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>if either of the following SBOL validation rules was violated: 10204, 10206; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #createFunctionalComponent(String, AccessType, URI, DirectionType)}</li>
	 * </ul>
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			String definitionId, String version, DirectionType direction) throws SBOLValidationException {
		URI definitionURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, definitionId, version, sbolDocument.isTypesInURIs());
		return createFunctionalComponent(displayId, access, definitionURI, direction);
	}

	/**
	 * Creates a child FunctionalComponent instance for this ModuleDefinition
	 * object with the given arguments, and then adds to this ModuleDefinition's list of FunctionalComponent
	 * instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant FunctionalComponent URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code definitionId}.
	 * It then calls {@link #createFunctionalComponent(String, AccessType, URI,DirectionType)}
	 * with this component definition URI.
	 *
	 * @param displayId The displayId identifier for this object
	 * @param access indicates whether the ComponentInstance can be referred to remotely
	 * @param definitionId refers to the ComponentDefinition of the ComponentInstance
	 * @param direction specify whether this FunctionalComponent is an input, output, both, or neither
	 * @return a FunctionalComponent instance
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			String definitionId, DirectionType direction) throws SBOLValidationException {
		return createFunctionalComponent(displayId, access, definitionId, "", direction);
	}

	/**
	 * Creates a child FunctionalComponent instance for this ModuleDefinition
	 * object with the given arguments, and then adds to this ModuleDefinition's list of FunctionalComponent
	 * instances.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all URIs and
	 * the given {@code fcURI} is not found in them, then an {@link SBOLValidationException} is thrown.
	 * <p>
	 * This method creates a compliant FunctionalComponent URI with the default
	 * URI prefix for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 *
	 * @param displayId The displayId identifier for this object
	 * @param access indicates whether the ComponentInstance can be referred to remotely
	 * @param componentDefinitionURI The URI of the ComponentDefinition that this FunctionalComponent refers to
	 * @param direction specify whether this FunctionalComponent is an input, output, both, or neither
	 * @return a FunctionalComponent instance
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * if any of the following SBOL validation rules was violated: 
	 * 10201, 10204, 10206, 10602, 10604, 10607, 11802.
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			URI componentDefinitionURI, DirectionType direction) throws SBOLValidationException {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(componentDefinitionURI) == null) {
				throw new SBOLValidationException("sbol-10604",this);
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI functionalComponentURI = createCompliantURI(URIprefix, displayId, version);
		FunctionalComponent fc = createFunctionalComponent(functionalComponentURI, access,
				componentDefinitionURI, direction);
		fc.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		fc.setDisplayId(displayId);
		fc.setVersion(version);
		return fc;
	}

	/**
	 * Adds the given instance to the list of components.
	 * @throws SBOLValidationException if either of the following condition is satisfied:
	 * <ul>
	 * <li>if either of the following SBOL validation rule was violated: 10604, 10804; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#addChildSafely(Identified, java.util.Map, String, java.util.Map...)}</li>
	 * </ul>
	 */
	void addFunctionalComponent(FunctionalComponent functionalComponent) throws SBOLValidationException {
		functionalComponent.setSBOLDocument(this.sbolDocument);
		functionalComponent.setModuleDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (functionalComponent.getDefinition()== null) {
				throw new SBOLValidationException("sbol-10604", functionalComponent);
			}
		}
		addChildSafely(functionalComponent, functionalComponents, "functionalComponent",
				interactions, modules);
		for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
			if (this.getFunctionalComponent(mapsTo.getLocalURI())==null) {
				throw new SBOLValidationException("sbol-10804", mapsTo);
			}
			mapsTo.setSBOLDocument(sbolDocument);
			mapsTo.setModuleDefinition(this);
			mapsTo.setComponentInstance(functionalComponent);
		}
	}

	void addFunctionalComponentNoCheck(FunctionalComponent functionalComponent) throws SBOLValidationException {
		functionalComponent.setSBOLDocument(this.sbolDocument);
		functionalComponent.setModuleDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (functionalComponent.getDefinition()== null) {
				throw new SBOLValidationException("sbol-10604", functionalComponent);
			}
		}
		addChildSafely(functionalComponent, functionalComponents, "functionalComponent",
				interactions, modules);
	}

	void checkMapsTosLocalURIs() throws SBOLValidationException {
		for (FunctionalComponent functionalComponent : this.getFunctionalComponents()) {
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				if (this.getFunctionalComponent(mapsTo.getLocalURI())==null) {
					throw new SBOLValidationException("sbol-10804", mapsTo);
				}
				mapsTo.setSBOLDocument(sbolDocument);
				mapsTo.setModuleDefinition(this);
				mapsTo.setComponentInstance(functionalComponent);
			}
		}
	}

	/**
	 * Removes the given FunctionalComponent instance from this ModuleDefinition object's list of
	 * FunctionalComponent instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * Before removing the given FunctionalComponent instance, this method
	 * checks if it is referenced by any children and grandchildren instances of this ModuleDefinition object.
	 *
	 * @param functionalComponent The FunctionalComponent to be removed
	 * @return {@code true} if the matching FunctionalComponent instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException the given FunctionalComponent instance is referenced.
	 */
	public boolean removeFunctionalComponent(FunctionalComponent functionalComponent) throws SBOLValidationException {
		for (Interaction i : interactions.values()) {
			for (Participation p : i.getParticipations()) {
				if (p.getParticipantURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("sbol-12003", p);
				}
			}
		}
		for (FunctionalComponent c : functionalComponents.values()) {
			for (MapsTo mt : c.getMapsTos()) {
				if (mt.getLocalURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("sbol-10804", mt);
				}
			}
		}
		for (Module m : modules.values()) {
			for (MapsTo mt : m.getMapsTos()) {
				if (mt.getLocalURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("sbol-10804", mt);
				}
			}
		}
		if (sbolDocument != null) {
			for (ModuleDefinition md : sbolDocument.getModuleDefinitions()) {
				for (Module m : md.getModules()) {
					for (MapsTo mt : m.getMapsTos()) {
						if (mt.getRemoteURI().equals(functionalComponent.getIdentity())) {
							throw new SBOLValidationException("sbol-10806", functionalComponent);
						}
					}
				}
			}
		}
		return removeChildSafely(functionalComponent, functionalComponents);
	}

	/**
	 * Returns the FunctionalComponent instance matching the given {@code displayId} from
	 * this ModuleDefinition object's list of FunctionalComponent instances.
	 *
	 * @param displayId The displayId identifier for this object
	 * @return the matching instance if present, or {@code null} otherwise.
	 */
	public FunctionalComponent getFunctionalComponent(String displayId) {
		try {
			return functionalComponents.get(createCompliantURI(this.getPersistentIdentity().toString(),
					displayId, this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the FunctionalComponent instance matching the given {@code componentURI} from this
	 * ModuleDefinition object's list of FunctionalComponent instances.
	 *
	 * @param functionalComponentURI The URI of the FunctionalComponent to be removed
	 * @return the matching FunctionalComponent instance if present, or
	 *         {@code null} otherwise.
	 */
	public FunctionalComponent getFunctionalComponent(URI functionalComponentURI) {
		return functionalComponents.get(functionalComponentURI);
	}

	/**
	 * Returns the set of FunctionalComponent instances owned by this
	 * ModuleDefinition object.
	 *
	 * @return the set of FunctionalComponent instances owned by this
	 *         ModuleDefinition object.
	 */
	public Set<FunctionalComponent> getFunctionalComponents() {
		return new HashSet<>(functionalComponents.values());
	}

	/**
	 * Removes all entries of this ModuleDefinition object's list of
	 * FunctionalComponent objects. The list will be empty after this call returns.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public void clearFunctionalComponents() throws SBOLValidationException {
		Object[] valueSetArray = functionalComponents.values().toArray();
		for (Object functionalComponent : valueSetArray) {
			removeFunctionalComponent((FunctionalComponent) functionalComponent);
		}
	}

	/**
	 * Clears the existing list of FunctionalComponent instances, then appends
	 * all of the elements in the given collection to the end of this list.
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	void setFunctionalComponents(Set<FunctionalComponent> components) throws SBOLValidationException {
		clearFunctionalComponents();
		if (components == null)
			return;
		for (FunctionalComponent component : components) {
			addFunctionalComponentNoCheck(component);
		}
		checkMapsTosLocalURIs();
	}

	/**
	 * Adds the URI of the given Model instance to this ModuleDefinition's
	 * set of reference Model URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all its
	 * reference URIs and the given model's URI
	 * is not found in them, then an {@link SBOLValidationException} is thrown.
	 * <p>
	 * This method calls {@link #addModel(URI)} with this component definition URI.
	 *
	 * @param model the given Model instance to this ModuleDefinition
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely specifies all URIs
	 *             and the given Model instance's URI is not found in them.
	 */
	public boolean addModel(Model model) throws SBOLValidationException {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModel(model.getIdentity()) == null) {
				throw new SBOLValidationException("sbol-11608", model);
			}
		}
		return this.addModel(model.getIdentity());
	}

	/**
	 * Creates a compliant Model URI and then adds it to this ModuleDefinition
	 * object's set of reference Model URIs. The model argument specifies the reference
	 * Model's display ID, and the version argument specifies its version.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed be edited.
	 * <p>
	 * This method creates a compliant Model URI with the default URI prefix for this SBOLDocument instance,
	 * and the given {@code definition} and {@code version}.
	 * This method then calls {@link #addModel(URI)} with this component definition URI.
	 *
	 * @param modelId the given Model URI instance to this ModuleDefinition
	 * @param version The given version for this object
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance URI.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean addModel(String modelId, String version) throws SBOLValidationException {
		URI modelURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.MODEL, modelId, version, sbolDocument.isTypesInURIs());
		return addModel(modelURI);
	}

	/**
	 * Creates a compliant Model URI and then adds it to this ModuleDefinition
	 * object's set of reference Model URIs. The model argument specifies the reference
	 * Model's display ID, and the version argument specifies its version.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed be edited.
	 * <p>
	 * This method creates a compliant Model URI with the default URI prefix for this SBOLDocument instance,
	 * and the given {@code modelId}.
	 * This method then calls {@link #addModel(URI)} with this component definition URI.
	 *
	 * @param modelId the given Model URI instance to this ModuleDefinition
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance URI.
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	public boolean addModel(String modelId) throws SBOLValidationException {
		return addModel(modelId,"");
	}

	/**
	 * Adds the given Model URI to this ModuleDefinition's set of reference
	 * Model URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all its
	 * reference URIs and the given {@code modelURI} is not found in them, then
	 * an {@link SBOLValidationException} is thrown.
	 *
	 * @param modelURI the given Model URI instance to this ModuleDefinition
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely
	 *             specifies all URIs and the given {@code modelURI} is not found in them.

	 */
	public boolean addModel(URI modelURI) throws SBOLValidationException {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModel(modelURI) == null) {
				throw new SBOLValidationException("sbol-11608", this);
			}
		}
		return models.add(modelURI);
	}

	/**
	 * Removes the given Model reference from the set of Model references.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param modelURI the given Model URI instance to this ModuleDefinition
	 * @return {@code true} if the matching Model reference is removed successfully,
	 *         {@code false} otherwise.
	 */
	public boolean removeModel(URI modelURI) {
		return models.remove(modelURI);
	}

	/**
	 * Clears the existing set of Model references first, then adds the given
	 * set of the Model references to this ModuleDefinition object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param models the given set of the Models to be added
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public void setModels(Set<URI> models) throws SBOLValidationException {
		clearModels();
		if (models == null)
			return;
		for (URI model : models) {
			addModel(model);
		}
	}

	/**
	 * Returns the set of Model URIs referenced by this ModuleDefinition object.
	 *
	 * @return the set of Model URIs referenced by this ModuleDefinition object.
	 */
	public Set<URI> getModelURIs() {
		Set<URI> result = new HashSet<>();
		result.addAll(models);
		return result;
	}

	/**
	 * Returns the set of Model instances referenced by this ModuleDefinition object.
	 *
	 * @return the set of Model instances referenced by this ModuleDefinition object
	 */
	public Set<Model> getModels() {
		Set<Model> result = new HashSet<>();
		for (URI modelURI : models) {
			Model model = sbolDocument.getModel(modelURI);
			result.add(model);
		}
		return result;
	}

	/**
	 * Checks if the given model URI is included in this ModuleDefinition
	 * object's set of reference Model URIs.
	 *
	 * @param modelURI the given Model URI instance to this ModuleDefinition
	 * @return {@code true} if this set contains the given URI.
	 */
	public boolean containsModel(URI modelURI) {
		return models.contains(modelURI);
	}

	/**
	 * Removes all entries of this ModuleDefinition object's set of reference
	 * Model URIs. The set will be empty after this call returns.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 */
	public void clearModels() {
		models.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((functionalComponents == null) ? 0 : functionalComponents.hashCode());
		result = prime * result + ((interactions == null) ? 0 : interactions.hashCode());
		result = prime * result + ((models == null) ? 0 : models.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((modules == null) ? 0 : modules.hashCode());
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
		ModuleDefinition other = (ModuleDefinition) obj;
		if (functionalComponents == null) {
			if (other.functionalComponents != null)
				return false;
		} else if (!functionalComponents.equals(other.functionalComponents))
			return false;
		if (interactions == null) {
			if (other.interactions != null)
				return false;
		} else if (!interactions.equals(other.interactions))
			return false;
		if (models == null) {
			if (other.models != null)
				return false;
		} else if (!models.equals(other.models))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (modules == null) {
			if (other.modules != null)
				return false;
		} else if (!modules.equals(other.modules))
			return false;
		return true;
	}

	@Override
	protected ModuleDefinition deepCopy() throws SBOLValidationException {
		return new ModuleDefinition(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	@Override
	ModuleDefinition copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		ModuleDefinition cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix, displayId, version);
		if (!this.getIdentity().equals(newIdentity)) {
			cloned.setWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFrom(this.getWasDerivedFrom());
		}
		cloned.setIdentity(newIdentity);
		int count = 0;
		for (FunctionalComponent component : cloned.getFunctionalComponents()) {
			if (!component.isSetDisplayId())
				component.setDisplayId("functionalComponent" + ++count);
			component.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					component.getDisplayId(), version);
			cloned.removeChildSafely(component, cloned.functionalComponents);
			cloned.addFunctionalComponent(component);
		}
		count = 0;
		for (Module module : cloned.getModules()) {
			if (!module.isSetDisplayId())
				module.setDisplayId("module" + ++count);
			module.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					module.getDisplayId(), version);
			cloned.removeChildSafely(module, cloned.modules);
			cloned.addModule(module);
		}
		count = 0;
		for (Interaction interaction : cloned.getInteractions()) {
			if (!interaction.isSetDisplayId())
				interaction.setDisplayId("interaction" + ++count);
			interaction.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					interaction.getDisplayId(), version);
			cloned.removeChildSafely(interaction, cloned.interactions);
			cloned.addInteraction(interaction);
		}
		return cloned;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.sbolstandard.core2.abstract_classes.TopLevel#updateCompliantURI(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	protected void checkDescendantsURIcompliance() throws SBOLValidationException {
		isTopLevelURIformCompliant(this.getIdentity());
		if (!this.getModules().isEmpty()) {
			for (Module module : this.getModules()) {
				try {
					isChildURIcompliant(this, module);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),module);
				}
				if (!module.getMapsTos().isEmpty()) {
					// Check compliance of Module's children
					for (MapsTo mapsTo : module.getMapsTos()) {
						try {
							isChildURIcompliant(module, mapsTo);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),mapsTo);
						}
					}
				}
			}
		}
		if (!this.getFunctionalComponents().isEmpty()) {
			for (FunctionalComponent functionalComponent : this.getFunctionalComponents()) {
				try {
					isChildURIcompliant(this, functionalComponent);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),functionalComponent);
				}
				if (!functionalComponent.getMapsTos().isEmpty()) {
					// Check compliance of Component's children
					for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
						try {
							isChildURIcompliant(functionalComponent, mapsTo);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),mapsTo);
						}
					}
				}
			}
		}
		if (!this.getInteractions().isEmpty()) {
			for (Interaction interaction : this.getInteractions()) {
				try {
					isChildURIcompliant(this, interaction);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),interaction);
				}
				for (Participation participation : interaction.getParticipations()) {
					try {
						isChildURIcompliant(interaction, participation);
					}
					catch (SBOLValidationException e) {
						throw new SBOLValidationException(e.getRule(),participation);
					}
				}
			}
		}
	}

	/**
	 * Returns a flattened copy of the ModuleDefinition object matching the given arguments, which has all internal
	 * hierarchy removed.   
	 * 
	 * @param URIprefix The default URI prefix for the ModuleDefinition to be flattened
	 * @param displayId The displayId identifier for the ModuleDefinition to be flattened
	 * @param version The version for the ModuleDefinition to be flattened
	 * @return The flattened copy of this ModuleDefinition
	 * @throws SBOLValidationException if an SBOL validation rule is violated
	 */
	ModuleDefinition flatten(String URIprefix,String displayId,String version) throws SBOLValidationException {
		return flattenRecurse().copy(URIprefix, displayId, version);
	}

	private ModuleDefinition flattenRecurse() throws SBOLValidationException {
		ModuleDefinition flatModuleDefinition = this.deepCopy();
		for (Module module : this.getModules()) {
			ModuleDefinition flatModule = module.getDefinition().flattenRecurse();
			for (FunctionalComponent fc : flatModule.getFunctionalComponents()) {
				boolean foundIt = false;
				URI oldURI = fc.getIdentity();
				URI newURI = null;
				for (MapsTo mapsTo : module.getMapsTos()) {
					if (mapsTo.getRemoteURI().equals(fc.getIdentity())) {
						newURI = mapsTo.getLocalURI();
						FunctionalComponent topFc = flatModuleDefinition.getFunctionalComponent(newURI);
						if (mapsTo.getRefinement()==RefinementType.USEREMOTE) {
							topFc.setDefinition(fc.getDefinitionURI());
						} else if (mapsTo.getRefinement()==RefinementType.VERIFYIDENTICAL) {
							if (!topFc.getDefinitionURI().equals(fc.getDefinitionURI())) {
								//								throw new SBOLValidationException("Component definitions in mapsTo '" + mapsTo.getIdentity()
								//										+ "' are not identical.");
								throw new SBOLValidationException("sbol-10811", mapsTo);
							}
						} else if (mapsTo.getRefinement()==RefinementType.MERGE) {
							// TODO: merge?
						}
						foundIt = true;
						break;
					}
				}
				if (!foundIt) {
					FunctionalComponent newFC = fc.deepCopy();
					newFC.updateCompliantURI(this.getPersistentIdentity().toString(),
							module.getDisplayId() + "__" + fc.getDisplayId(), this.getVersion());
					newURI = newFC.getIdentity();
					flatModuleDefinition.addFunctionalComponent(newFC);
				}
				for (Interaction i : flatModule.getInteractions()) {
					for (Participation p : i.getParticipations()) {
						if (p.getParticipantURI().equals(oldURI)) {
							p.setParticipant(newURI);
						}
					}
				}
			}
			for (Interaction i : flatModule.getInteractions()) {
				flatModuleDefinition.addInteraction(i.deepCopy());
			}
		}
		flatModuleDefinition.clearModules();
		return flatModuleDefinition;
	}

	@Override
	public String toString() {
		return "ModuleDefinition ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 				
				+ (this.getRoles().size()>0?", roles=" + this.getRoles():"") 
				+ (this.getFunctionalComponents().size()>0?", functionalComponents=" + this.getFunctionalComponents():"") 
				+ (this.getModules().size()>0?", modules=" + this.getModules():"") 
				+ (this.getInteractions().size()>0?", interactions=" + this.getInteractions():"") 
				+ (this.getModels().size()>0?", models=" + this.getModelURIs():"") 
				+ "]";
	}
}
