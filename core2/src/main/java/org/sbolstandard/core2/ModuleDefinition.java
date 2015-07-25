package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.*;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class ModuleDefinition extends TopLevel {

	private Set<URI>							roles;
	private HashMap<URI, Module>				modules;
	private HashMap<URI, Interaction>			interactions;
	private HashMap<URI, FunctionalComponent>	functionalComponents;
	private Set<URI>							models;

	ModuleDefinition(URI identity) {
		super(identity);
		this.roles = new HashSet<>();
		this.modules = new HashMap<>();
		this.interactions = new HashMap<>();
		this.functionalComponents = new HashMap<>();
		this.models = new HashSet<>();
	}

	private ModuleDefinition(ModuleDefinition moduleDefinition) {
		super(moduleDefinition);
		this.roles = new HashSet<>();
		this.modules = new HashMap<>();
		this.interactions = new HashMap<>();
		this.functionalComponents = new HashMap<>();
		this.models = new HashSet<>();
		for (URI role : moduleDefinition.getRoles()) {
			this.addRole(role);
		}
		for (Module subModule : moduleDefinition.getModules()) {
			this.addModule(subModule.deepCopy());
		}
		for (Interaction interaction : moduleDefinition.getInteractions()) {
			this.addInteraction(interaction.deepCopy());
		}
		for (FunctionalComponent component : moduleDefinition.getFunctionalComponents()) {
			this.addFunctionalComponent(component.deepCopy());
		}
		this.setModels(moduleDefinition.getModelURIs());
	}

	/**
	 * Adds the given role URI to this ModuleDefinition's set of role URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI
	 * @return {@code true} if this set did not already contain the specified role.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addRole(URI roleURI) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		return roles.add(roleURI);
	}

	/**
	 * Removes the given role reference from the set of role references.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param roleURI
	 * @return {@code true} if the matching role reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeRole(URI roleURI) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of role references first, then adds the given
	 * set of the role references to this ModuleDefinition object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roles
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setRoles(Set<URI> roles) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	 * @param roleURI
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
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearRoles() {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		roles.clear();
	}

	/**
	 * Calls the ModuleInstantiation constructor to create a new instance using
	 * the specified parameters,
	 * then adds to the list of ModuleInstantiation instances owned by this
	 * ModuleDefinition object.
	 * 
	 * @param identity
	 * @param moduleDefinitionURI
	 * @return a Module instance
	 */
	Module createModule(URI identity, URI moduleDefinitionURI) {
		Module module = new Module(identity, moduleDefinitionURI);
		addModule(module);
		return module;
	}

	/**
	 * Creates a child Module instance for this ModuleDefinition object with the
	 * specified arguments, and then adds to this ModuleDefinition's list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Module URI with the default URI prefix
	 * for this SBOLDocument instance, and the given {@code displayId} and {@code version}.
	 * It then calls {@link #createModule(String, URI)} with this component
	 * definition URI.
	 * 
	 * @param displayId
	 * @param moduleDefinitionId
	 * @param version
	 * @return a Module instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public Module createModule(String displayId, String moduleDefinitionId, String version) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		URI module = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
		TopLevel.MODULE_DEFINITION, moduleDefinitionId, version, sbolDocument.isTypesInURIs());
		return createModule(displayId, module);
	}

	/**
	 * Creates a child Module instance for this ModuleDefinition object with the
	 * specified arguments, and then adds to this ModuleDefinition's list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all URIs and
	 * the given {@code moduleDefinitionURI} is not found in them, then an
	 * {@link IllegalArgumentException} is thrown.
	 * <p>
	 * This method creates a compliant Module URI with the default URI prefix
	 * for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 * 
	 * @param displayId
	 * @param moduleDefinitionURI
	 * @return a Module instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely specifies all URIs and the given {@code definitionURI}
               is not found in them.
	 */
	public Module createModule(String displayId, URI moduleDefinitionURI) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModuleDefinition(moduleDefinitionURI) == null) {
				throw new IllegalArgumentException("Module definition '" + moduleDefinitionURI
						+ "' does not exist.");
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
	 * @param module
	 */
	void addModule(Module module) {
		addChildSafely(module, modules, "module", functionalComponents, interactions);
		module.setSBOLDocument(this.sbolDocument);
		module.setModuleDefinition(this);
	}

	/**
	 * Removes the specified Module instance from the list of Module instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param module
	 * @return {@code true} if the matching Module instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 *             
	 */
	public boolean removeModule(Module module) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		return removeChildSafely(module, modules);
	}

	/**
	 * Returns the Module instance matching the given displayId from the list of
	 * Module instances.
	 * 
	 * @param displayId
	 * @return the matching Module instance if present, or {@code null} otherwise.
	 */
	public Module getModule(String displayId) {
		return modules.get(createCompliantURI(this.getPersistentIdentity().toString(), displayId,
				this.getVersion()));
	}

	/**
	 * Returns the instance matching the given URI from the list of Module instances.
	 * 
	 * @param moduleURI
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
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant 
	 */
	public void clearModules() {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param modules
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 *             
	 */
	void setModules(List<Module> modules) {
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
	 * @return the created Interaction instance.
	 */
	Interaction createInteraction(URI identity, Set<URI> type) {
		Interaction interaction = new Interaction(identity, type);
		addInteraction(interaction);
		return interaction;
	}

	/**
	 * Creates a child Interaction object for this ModuleDefinition object with
	 * the given arguments, and then adds to this ModuleDefinition's list of Interaction instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Interaction URI with the default URI
	 * prefix for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 * 
	 * @param displayId
	 * @param types
	 * @return the created Interaction instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public Interaction createInteraction(String displayId, Set<URI> types) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	 * Adds the given Interaction instance to the list of Interaction instances.
	 */
	void addInteraction(Interaction interaction) {
		addChildSafely(interaction, interactions, "interaction", functionalComponents, modules);
		interaction.setSBOLDocument(this.sbolDocument);
		interaction.setModuleDefinition(this);
	}

	/**
	 * Removes the given Interaction instance from the list of Interaction
	 * instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param interaction
	 * @return {@code true} if the matching Interaction instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeInteraction(Interaction interaction) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		return removeChildSafely(interaction, interactions);
	}

	/**
	 * Returns the instance matching the given Interaction displayId from the
	 * list of Interaction instances.
	 * 
	 * @param displayId
	 * @return the matching instance if present, {@code null} otherwise.
	 */
	public Interaction getInteraction(String displayId) {
		return interactions.get(createCompliantURI(this.getPersistentIdentity().toString(),
				displayId, this.getVersion()));
	}

	/**
	 * Returns the instance matching the given Interaction URI from the list of
	 * Interaction instances.
	 *
	 * @param interactionURI
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
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearInteractions() {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		Object[] valueSetArray = interactions.values().toArray();
		for (Object interaction : valueSetArray) {
			removeInteraction((Interaction) interaction);
		}
	}

	/**
	 * Clears the existing list of interaction instances, then appends all of
	 * the elements in the given collection to the end of this list.
	 */
	void setInteractions(
	List<Interaction> interactions) {
		clearInteractions();
		if (interactions == null)
			return;
		for (Interaction interaction : interactions) {
			addInteraction(interaction);
		}
	}

	/**
	 * @param identity
	 * @param access
	 * @param definitionURI
	 * @param direction
	 * @return a FunctionalComponent instance.
	 */

	FunctionalComponent createFunctionalComponent(URI identity, AccessType access,
			URI definitionURI, DirectionType direction) {
		FunctionalComponent functionalComponent =
				new FunctionalComponent(identity, access, definitionURI, direction);
		addFunctionalComponent(functionalComponent);
		return functionalComponent;
	}

	/**
	 * Creates a child FunctionalComponent instance for this ModuleDefinition
	 * object with the given arguments, and then adds to this ModuleDefinition's list of FunctionalComponent
	 * instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant FunctionalComponent URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code definition} and {@code version}.
	 * It then calls {@link #createFunctionalComponent(String, AccessType, URI,DirectionType)}
	 * with this component definition URI.
	 * 
	 * @param displayId
	 * @param access
	 * @param definitionId
	 * @param version
	 * @param direction
	 * @return a FunctionalComponent instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			String definitionId, String version, DirectionType direction) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all URIs and
	 * the given {@code fcURI} is not found in them, then an {@link IllegalArgumentException} is thrown.
	 * <p>
	 * This method creates a compliant FunctionalComponent URI with the default
	 * URI prefix for this SBOLDocument instance, the given {@code displayId}, and this
	 * ModuleDefinition object's version.
	 * 
	 * @param displayId
	 * @param access
	 * @param componentDefinitionURI
	 * @param direction
	 * @return a FunctionalComponent instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely
	           specifies all URIs and the given {@code definitionURI} is not found in them.
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			URI componentDefinitionURI, DirectionType direction) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(componentDefinitionURI) == null) {
				throw new IllegalArgumentException("Component definition '" + componentDefinitionURI
						+ "' does not exist.");
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
	 */
	void addFunctionalComponent(FunctionalComponent functionalComponent) {
		addChildSafely(functionalComponent, functionalComponents, "functionalComponent",
				interactions, modules);
		functionalComponent.setSBOLDocument(this.sbolDocument);
		functionalComponent.setModuleDefinition(this);
	}

	/**
	 * Removes the given FunctionalComponent instance from this ModuleDefinition object's list of
	 * FunctionalComponent instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * Before removing the given FunctionalComponent instance, this method
	 * checks if it is referenced by any children and grandchildren instances of this ModuleDefinition object.
	 *	
	 * @param functionalComponent
	 * @return {@code true} if the matching FunctionalComponent instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException the given FunctionalComponent instance is referenced.
	 */
	public boolean removeFunctionalComponent(FunctionalComponent functionalComponent) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		for (Interaction i : interactions.values()) {
			for (Participation p : i.getParticipations()) {
				if (p.getParticipantURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + functionalComponent.getIdentity() +
					" since it is in use.");
				}
			}
		}
		for (FunctionalComponent c : functionalComponents.values()) {
			for (MapsTo mt : c.getMapsTos()) {
				if (mt.getLocalURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + functionalComponent.getIdentity() +
					" since it is in use.");
				}
			}
		}
		for (Module m : modules.values()) {
			for (MapsTo mt : m.getMapsTos()) {
				if (mt.getLocalURI().equals(functionalComponent.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + functionalComponent.getIdentity() +
					" since it is in use.");
				}
			}
		}
		if (sbolDocument != null) {
			for (ModuleDefinition md : sbolDocument.getModuleDefinitions()) {
				for (Module m : md.getModules()) {
					for (MapsTo mt : m.getMapsTos()) {
						if (mt.getRemoteURI().equals(functionalComponent.getIdentity())) {
							throw new SBOLValidationException("Cannot remove "
									+ functionalComponent.getIdentity() +
									" since it is in use.");
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
	 * @param displayId
	 * @return the matching instance if present, or {@code null} otherwise.
	 */	
	public FunctionalComponent getFunctionalComponent(String displayId) {
		return functionalComponents.get(createCompliantURI(this.getPersistentIdentity().toString(),
				displayId, this.getVersion()));
	}

	/**
	 * Returns the FunctionalComponent instance matching the given {@code componentURI} from this
	 * ModuleDefinition object's list of FunctionalComponent instances.
	 * 
	 * @param functionalComponentURI
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
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearFunctionalComponents() {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		Object[] valueSetArray = functionalComponents.values().toArray();
		for (Object functionalComponent : valueSetArray) {
			removeFunctionalComponent((FunctionalComponent) functionalComponent);
		}
	}

	/**
	 * Clears the existing list of FunctionalComponent instances, then appends
	 * all of the elements in the given collection to the end of this list.
	 */
	void setFunctionalComponents(
	List<FunctionalComponent> components) {
		clearFunctionalComponents();
		if (components == null)
			return;
		for (FunctionalComponent component : components) {
			addFunctionalComponent(component);
		}
	}

	/**
	 * Adds the URI of the given Model instance to this ModuleDefinition's
	 * set of reference Model URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all its
	 * reference URIs and the given model's URI
	 * is not found in them, then an {@link IllegalArgumentException} is thrown.
	 * <p>
	 * This method calls {@link #addModel(URI)} with this component definition URI.
	 * 
	 * @param model
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely specifies all URIs
	 *             and the given Model instance's URI is not found in them.
	 */
	public boolean addModel(Model model) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModel(model.getIdentity()) == null) {
				throw new IllegalArgumentException("Model '" + model.getIdentity()
						+ "' does not exist.");
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
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed be edited.
	 * <p>
	 * This method creates a compliant Model URI with the default URI prefix for this SBOLDocument instance, 
	 * and the given {@code definition} and {@code version}. 
	 * This method then calls {@link #addModel(URI)} with this component definition URI.
	 * 
	 * @param modelId
	 * @param version
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addModel(String modelId, String version) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		URI modelURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
		TopLevel.MODEL, modelId, version, sbolDocument.isTypesInURIs());
		return addModel(modelURI);
	}

	/**
	 * Adds the given Model URI to this ModuleDefinition's set of reference
	 * Model URIs.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all its
	 * reference URIs and the given {@code modelURI} is not found in them, then 
	 * an {@link IllegalArgumentException} is thrown.
	 * 
	 * @param modelURI
	 * @return {@code true} if this set did not already contain the given Model
	 *         instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely
	 *             specifies all URIs and the given {@code modelURI} is not found in them.

	 */
	public boolean addModel(URI modelURI) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModel(modelURI) == null) {
				throw new IllegalArgumentException("Model '" + modelURI + "' does not exist.");
			}
		}
		return models.add(modelURI);
	}

	/**
	 * Removes the given Model reference from the set of Model references.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param modelURI
	 * @return {@code true} if the matching Model reference is removed successfully,
	 *         {@code false} otherwise.      
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeModel(URI modelURI) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
		return models.remove(modelURI);
	}

	/**
	 * Clears the existing set of Model references first, then adds the given
	 * set of the Model references to this ModuleDefinition object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param models
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setModels(Set<URI> models) {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	 * @param modelURI
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
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearModels() {
		if (sbolDocument != null)
			sbolDocument.checkReadOnly();
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
	protected ModuleDefinition deepCopy() {
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
	ModuleDefinition copy(String URIprefix, String displayId, String version) {
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

	protected boolean checkDescendantsURIcompliance() {
		// codereview: spaghetti
		if (!isTopLevelURIformCompliant(this.getIdentity())) return false;
		boolean allDescendantsCompliant = true;
		if (!this.getModules().isEmpty()) {
			for (Module module : this.getModules()) {
				allDescendantsCompliant = allDescendantsCompliant
				&& isChildURIcompliant(this, module);
				if (!allDescendantsCompliant) { // Current sequence constraint
												// has non-compliant URI.
					return allDescendantsCompliant;
				}
				if (!module.getMapsTos().isEmpty()) {
					// Check compliance of Module's children
					for (MapsTo mapsTo : module.getMapsTos()) {
						allDescendantsCompliant = allDescendantsCompliant
						&& isChildURIcompliant(module, mapsTo);
						if (!allDescendantsCompliant) { // Current mapsTo has
														// non-compliant URI.
							return allDescendantsCompliant;
						}
					}
				}
			}
		}
		if (!this.getFunctionalComponents().isEmpty()) {
			for (FunctionalComponent functionalComponent : this.getFunctionalComponents()) {
				allDescendantsCompliant = allDescendantsCompliant
				&& isChildURIcompliant(this, functionalComponent);
				if (!allDescendantsCompliant) { // Current component has
												// non-compliant URI.
					return allDescendantsCompliant;
				}
				if (!functionalComponent.getMapsTos().isEmpty()) {
					// Check compliance of Component's children
					for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
						allDescendantsCompliant = allDescendantsCompliant
								&& isChildURIcompliant(functionalComponent, mapsTo);
						if (!allDescendantsCompliant) { // Current mapsTo has
														// non-compliant URI.
							return allDescendantsCompliant;
						}
					}
				}
			}
		}
		if (!this.getInteractions().isEmpty()) {
			for (Interaction interaction : this.getInteractions()) {
				allDescendantsCompliant = allDescendantsCompliant
				&& isChildURIcompliant(this, interaction);
				if (!allDescendantsCompliant) { // Current interaction has
												// non-compliant URI.
					return allDescendantsCompliant;
				}
				for (Participation participation : interaction.getParticipations()) {
					allDescendantsCompliant = allDescendantsCompliant
					&& isChildURIcompliant(interaction, participation);
					if (!allDescendantsCompliant) { // Current participation has
													// non-compliant URI.
						return allDescendantsCompliant;
					}
				}
			}
		}
		// All descendants of this ComponentDefinition object have compliant
		// URIs.
		return allDescendantsCompliant;
	}

	protected boolean isComplete() {
		if (sbolDocument == null)
			return false;
		for (URI modelURI : models) {
			if (sbolDocument.getModel(modelURI) == null)
				return false;
		}
		for (FunctionalComponent functionalComponent : getFunctionalComponents()) {
			if (functionalComponent.getDefinition() == null)
				return false;
		}
		for (Module module : getModules()) {
			if (module.getDefinition() == null)
				return false;
		}
		return true;
	}
}
