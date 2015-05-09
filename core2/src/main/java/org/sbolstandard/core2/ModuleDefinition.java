package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.ComponentInstance.AccessType;
import org.sbolstandard.core2.FunctionalComponent.DirectionType;

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

public class ModuleDefinition extends TopLevel {
	
	private Set<URI> roles;
	private HashMap<URI, Module> modules;
	private HashMap<URI, Interaction> interactions;
	private HashMap<URI, FunctionalComponent> functionalComponents;
	private Set<URI> models;
	
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
		Set<URI> roles = new HashSet<>();
		for (URI role : moduleDefinition.getRoles()) {
			roles.add(role);
		}		
		this.setRoles(roles);
		if (!moduleDefinition.getModules().isEmpty()) {
			List<Module> subModules = new ArrayList<>();
			for (Module subModule : moduleDefinition.getModules()) {
				subModules.add(subModule.deepCopy());
			}
			this.setModules(subModules);
		}
		if (!moduleDefinition.getInteractions().isEmpty()) {
			List<Interaction> interactions = new ArrayList<>();
			for (Interaction interaction : moduleDefinition.getInteractions()) {
				interactions.add(interaction.deepCopy());
			}
			this.setInteractions(interactions);
		}
		if (!moduleDefinition.getFunctionalComponents().isEmpty()) {
			List<FunctionalComponent> components = new ArrayList<>();
			for (FunctionalComponent component : moduleDefinition.getFunctionalComponents()) {
				components.add(component.deepCopy());
			}
			this.setFunctionalComponents(components);
		}
		if (!moduleDefinition.getModels().isEmpty()) {
			Set<URI> models = new HashSet<>();
			for (URI model : moduleDefinition.getModelURIs()) {
				models.add(model);
			}
			this.setModels(models);
		}
	}


	/**
	 * Adds the specified element to the set <code>roles</code> if it is not already present. 
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @return <code>true</code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 */
	public void setRoles(Set<URI> roles) {
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}
	
	/**
	 * Returns the field variable <code>roles</code>.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}
	
	/**
	 * Returns true if the set <code>roles</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsRole(URI rolesURI) {
		return roles.contains(rolesURI);
	}
	
	/**
	 * Removes all entries of the list of <code>roles</code> instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearRoles() {
		roles.clear();
	}
	
//	/**
//	 * Test if field variable <code>subModules</code> is set.
//	 * @return <code>true</code> if it is not an empty list.
//	 */
//	public boolean isSetSubModules() {
//		if (subModules.isEmpty())
//			return false;
//		else
//			return true;					
//	}
	
	/**
	 * Calls the ModuleInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of ModuleInstantiation instances owned by this instance.
	 * @return the created ModuleInstantiation instance.
	 */
	Module createModule(URI identity, URI moduleDefinitionURI) {
		Module subModule = new Module(identity, moduleDefinitionURI);
		addModule(subModule);
		return subModule;
	}
	
	public Module createModule(String displayId, String moduleDefinitionId, String version) {
		URI moduleDefinition = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(), 
				TopLevel.moduleDefinition, moduleDefinitionId, version);
		return createModule(displayId,moduleDefinition);
	}

	public Module createModule(String displayId, URI moduleDefinitionURI) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModuleDefinition(moduleDefinitionURI)==null) {
				throw new IllegalArgumentException("Module definition '" + moduleDefinitionURI + "' does not exist.");
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
	 * Adds the specified instance to the list of subModules. 
	 */
	void addModule(Module module) {
		addChildSafely(module, modules, "module", functionalComponents, interactions);
		module.setSBOLDocument(this.sbolDocument);
		module.setModuleDefinition(this);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of subModules if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeModule(Module module) {
		return removeChildSafely(module,modules);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of subModules if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module getModule(URI subModuleURI) {
		return modules.get(subModuleURI);
	}
	
	/**
	 * Returns the list of subModule instances owned by this instance. 
	 * @return the list of subModule instances owned by this instance.
	 */
	public Set<Module> getModules() {
		return new HashSet<>(modules.values());
	}
	
	/**
	 * Removes all entries of the list of modules owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModules() {
		Object[] valueSetArray = modules.values().toArray();
		for (Object module : valueSetArray) {
			removeModule((Module)module);
		}
	}
		
	/**
	 * Clears the existing list of subModule instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setModules(List<Module> modules) {
		clearModules();
		if (modules==null) return;
		for (Module module : modules) {
			addModule(module);
		}
	}
	
//	/**
//	 * Test if field variable <code>interactions</code> is set.
//	 * @return <code>true</code> if it is not an empty list.
//	 */		
//	public boolean isSetInteractions() {
//		if (interactions.isEmpty())
//			return false;
//		else
//			return true;
//	}
	
	/**
	 * Calls the Interaction constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Interaction instances owned by this instance.
	 * @return the  created Interaction instance.
	 */
	Interaction createInteraction(URI identity, Set<URI> type) {
		Interaction interaction = new Interaction(identity, type);
		addInteraction(interaction);
		return interaction;
	}
	
	public Interaction createInteraction(String displayId, Set<URI> type) {
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newInteractionURI = createCompliantURI(URIprefix, displayId, version);
		Interaction i = createInteraction(newInteractionURI, type);
		i.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		i.setDisplayId(displayId);
		i.setVersion(version);
		return i;
	}
	
	
	/**
	 * Adds the specified instance to the list of interactions. 
	 */
	void addInteraction(Interaction interaction) {
		addChildSafely(interaction, interactions, "interaction", functionalComponents, modules);
		interaction.setSBOLDocument(this.sbolDocument);
        interaction.setModuleDefinition(this);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of interactions if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeInteraction(Interaction interaction) {
		return removeChildSafely(interaction,interactions);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of interactions if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Interaction getInteraction(URI interactionURI) {
		return interactions.get(interactionURI);
	}
	
	/**
	 * Returns the list of interaction instances owned by this instance. 
	 * @return the list of interaction instances owned by this instance.
	 */
	public Set<Interaction> getInteractions() {
		return new HashSet<>(interactions.values());
	}
	
	/**
	 * Removes all entries of the list of interactions owned by this instance. The list will be empty after this call returns.
	 */
	public void clearInteractions() {
		Object[] valueSetArray = interactions.values().toArray();
		for (Object interaction : valueSetArray) {
			removeInteraction((Interaction)interaction);
		}
	}
		
	/**
	 * Clears the existing list of interaction instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setInteractions(
			List<Interaction> interactions) {
		clearInteractions();	
		if (interactions==null) return;
		for (Interaction interaction : interactions) {
			addInteraction(interaction);
		}
	}
		
//	/**
//	 * Test if field variable <code>functionalInstantiations</code> is set.
//	 * @return <code>true</code> if it is not an empty list.
//	 */
//	public boolean isSetComponents() {
//		if (components.isEmpty()) 
//			return false;
//		else
//			return true;
//	}
 	
	/**
	 * Calls the FunctionalInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of FunctionalInstantiation instances owned by this instance.
	 * @return the created {@link FunctionalComponent} instance.
	 */
	FunctionalComponent createFunctionalComponent(URI identity, AccessType access, 
			URI definitionURI, DirectionType direction) {
		FunctionalComponent functionalComponent = 
				new FunctionalComponent(identity, access, definitionURI, direction);
		addFunctionalComponent(functionalComponent);
		return functionalComponent;
	}
	
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			String definition, String version, DirectionType direction) {
		URI definitionURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(), 
				TopLevel.componentDefinition, definition, version);
		return createFunctionalComponent(displayId,access,definitionURI,direction);
	}

	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access,
			URI definitionURI, DirectionType direction) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(definitionURI)==null) {
				throw new IllegalArgumentException("Component definition '" + definitionURI + "' does not exist.");
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI functionalComponentURI = createCompliantURI(URIprefix, displayId, version);
		FunctionalComponent fc = createFunctionalComponent(functionalComponentURI, access, definitionURI, direction);
		fc.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		fc.setDisplayId(displayId);
		fc.setVersion(version);
		return fc;
	}
	
	/**
	 * Adds the specified instance to the list of components.
	 */
	void addFunctionalComponent(FunctionalComponent functionalComponent) {
		addChildSafely(functionalComponent, functionalComponents, "functionalComponent", interactions, modules);
		functionalComponent.setSBOLDocument(this.sbolDocument);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeFunctionalComponent(FunctionalComponent functionalComponent) {
		return removeChildSafely(functionalComponent,functionalComponents);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalComponent getFunctionalComponent(URI componentURI) {
		return functionalComponents.get(componentURI);
	}
	
	/**
	 * Returns the list of functionalInstantiation instances owned by this instance. 
	 * @return the list of functionalInstantiation instances owned by this instance.
	 */
	public Set<FunctionalComponent> getFunctionalComponents() {
		return new HashSet<>(functionalComponents.values());
	}
	
	/**
	 * Removes all entries of the list of functional components owned by this instance. The list will be empty after this call returns.
	 */
	public void clearFunctionalComponents() {
		Object[] valueSetArray = functionalComponents.values().toArray();
		for (Object functionalComponent : valueSetArray) {
			removeFunctionalComponent((FunctionalComponent)functionalComponent);
		}
	}
		
	/**
	 * Clears the existing list of functionalInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setFunctionalComponents(
			List<FunctionalComponent> components) {
		clearFunctionalComponents();	
		if (components==null) return;
		for (FunctionalComponent component : components) {
			addFunctionalComponent(component);
		}
	}
	
//	/**
//	 * Set optional field variable <code>subModules</code> to an empty list.
//	 */
//	public void unsetModuleInstantiations() {
//		subModules.clear();
//	}
//	
//	/**
//	 * Set optional field variable <code>interactions</code> to an empty list.
//	 */
//	public void unsetInteractions() {
//		interactions.clear();
//	}
//	
//	/**
//	 * Set optional field variable <code>functionalInstantiations</code> to an empty list.
//	 */
//	public void unsetFunctionalInstantiations() {
//		functionalInstantiations.clear();
//	}
	
//	/**
//	 * Test if field variable <code>models</code> is set.
//	 * @return <code>true</code> if it is not an empty list.
//	 */
//	public boolean isSetModels() {
//		if (models.isEmpty())
//			return false;
//		else
//			return true;					
//	}
	
	public void addModel(String model,String version) {
		URI modelURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(), 
				TopLevel.model, model, version);
		addModel(modelURI);
	}
	
	/**
	 * Adds the specified instance to the list of models. 
	 */
	public void addModel(URI modelURI) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getModel(modelURI)==null) {
				throw new IllegalArgumentException("Model '" + modelURI + "' does not exist.");
			}
		}
		models.add(modelURI);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of models if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeModel(URI modelURI) {
		return models.remove(modelURI);
	}
	
	/**
	 * Clears the existing list of model instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	public void setModels(Set<URI> models) {
		clearModels();
		if (models==null) return;
		for (URI model : models) {
			addModel(model);
		}
	}
	
	/**
	 * Returns the set of model URIs referenced by this instance.
	 * @return the set of model URIs referenced by this instance
	 */
	public Set<URI> getModelURIs() {
		Set<URI> result = new HashSet<>();
		result.addAll(models);
		return result;
	}
	
	/**
	 * Returns the set of models referenced by this instance.
	 * @return the set of models referenced by this instance
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
	 * Returns true if the set <code>models</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsModel(URI modelURI) {
		return models.contains(modelURI);
	}

	/**
	 * Removes all entries of the list of model instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearModels() {
		models.clear();
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((functionalComponents == null) ? 0 : functionalComponents.hashCode());
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
	
//	/**
//	 * @param newDisplayId
//	 * @return
//	 */
//	public ModuleDefinition copy(String newDisplayId) {
//		ModuleDefinition cloned = (ModuleDefinition) super.copy(newDisplayId);		
//		cloned.updateCompliantURI(newDisplayId);
//		return cloned;
//	}
//	
//
//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateDisplayId(java.lang.String)
//	 */
//	protected void updateCompliantURI(String newDisplayId) {
//		super.updateCompliantURI(newDisplayId);
//		if (isTopLevelURIcompliant(this.getIdentity())) {					
//			// TODO Change all of its children's displayIds in their URIs.
//		}
//	}
//	
//	/**
//	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
//	 * @param newVersion
//	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
//	 */
//	public ModuleDefinition newVersion(String newVersion) {
//		ModuleDefinition cloned = (ModuleDefinition) super.newVersion(newVersion);		
//		cloned.updateVersion(newVersion);
//		return cloned;
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
//	 */
//	protected void updateVersion(String newVersion) {
//		super.updateVersion(newVersion);
//		if (isTopLevelURIcompliant(this.getIdentity())) {					
//			// TODO Change all of its children's versions in their URIs.
//		}
//	}
	
	/**
	 * Check if the specified key exists in any hash maps in this class other than the one with the specified keySet. This method
	 * constructs a set of key sets for other hash maps first, and then checks if the key exists.
	 * @return <code>true</code> if the specified key exists in other hash maps.
	 */
//	private boolean keyExistsInOtherMaps(Set<URI> keySet, URI key) {
//		Set<Set<URI>> complementSet = new HashSet<>();
//		complementSet.add(functionalComponents.keySet());
//		complementSet.add(interactions.keySet());		
//		complementSet.remove(keySet);
//		for (Set<URI> otherKeySet : complementSet) {
//			if (otherKeySet.contains(key)) {
//				return true;
//			}
//		}
//		return false;
//	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	ModuleDefinition copy(String URIprefix, String displayId, String version) {
		if (this.checkDescendantsURIcompliance() && isURIprefixCompliant(URIprefix)
				&& isDisplayIdCompliant(displayId) && isVersionCompliant(version)) {
			ModuleDefinition cloned = this.deepCopy();
			cloned.setWasDerivedFrom(this.getIdentity());
			cloned.setPersistentIdentity(URI.create(URIprefix + '/' + displayId));
			cloned.setDisplayId(displayId);
			cloned.setVersion(version);
			URI newIdentity = URI.create(URIprefix + '/' + displayId + '/' + version);			
			cloned.setIdentity(newIdentity);
			// Update all children's URIs
			if (!cloned.getModules().isEmpty()) {
				for (Module module : cloned.getModules()) {
					module.updateCompliantURI(URIprefix, displayId, version);
				}
			}
			if (!cloned.getInteractions().isEmpty()) {
				for (Interaction SequenceAnnotation : cloned.getInteractions()) {
					SequenceAnnotation.updateCompliantURI(URIprefix, displayId, version);
				}	
			}
			if (!cloned.getFunctionalComponents().isEmpty()) {
				for (FunctionalComponent component : cloned.getFunctionalComponents()) {
					component.updateCompliantURI(URIprefix, displayId, version);
				}
			}
			return cloned;
		}
		else {
			return null; 	
		}
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateCompliantURI(java.lang.String, java.lang.String, java.lang.String)
	 */
	protected boolean checkDescendantsURIcompliance() {
		// codereview: spaghetti
		if (!isURIcompliant(this.getIdentity(), 0)) { 	// ComponentDefinition to be copied has non-compliant URI.
			return false;
		}
		boolean allDescendantsCompliant = true;
		if (!this.getModules().isEmpty()) {
			for (Module module : this.getModules()) {
				allDescendantsCompliant = allDescendantsCompliant 
						&& isChildURIcompliant(this.getIdentity(), module.getIdentity());				
				if (!allDescendantsCompliant) { // Current sequence constraint has non-compliant URI. 
					return allDescendantsCompliant;
				}
				if (!module.getMapsTos().isEmpty()) {
					// Check compliance of Module's children
					for (MapsTo mapsTo : module.getMapsTos()) {
						allDescendantsCompliant = allDescendantsCompliant 
								&& isChildURIcompliant(module.getIdentity(), mapsTo.getIdentity());
						if (!allDescendantsCompliant) { // Current mapsTo has non-compliant URI. 
							return allDescendantsCompliant;
						}
					}					
				}
			}
		}
		if (!this.getFunctionalComponents().isEmpty()) {
			for (FunctionalComponent functionalComponent : this.getFunctionalComponents()) {
				allDescendantsCompliant = allDescendantsCompliant 
						&& isChildURIcompliant(this.getIdentity(), functionalComponent.getIdentity());
				if (!allDescendantsCompliant) { // Current component has non-compliant URI. 
					return allDescendantsCompliant;
				}
				if (!functionalComponent.getMapsTos().isEmpty()) {
					// Check compliance of Component's children
					for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
						allDescendantsCompliant = allDescendantsCompliant 
								&& isChildURIcompliant(functionalComponent.getIdentity(), mapsTo.getIdentity());
						if (!allDescendantsCompliant) { // Current mapsTo has non-compliant URI. 
							return allDescendantsCompliant;
						}
					}					
				}
			}
		}
		if (!this.getInteractions().isEmpty()) {
			for (Interaction interaction : this.getInteractions()) {
				allDescendantsCompliant = allDescendantsCompliant 
						&& isChildURIcompliant(this.getIdentity(), interaction.getIdentity());
				if (!allDescendantsCompliant) { // Current interaction has non-compliant URI. 
					return allDescendantsCompliant;
				}
				for (Participation participation : interaction.getParticipations()) {
					allDescendantsCompliant = allDescendantsCompliant 
							&& isChildURIcompliant(interaction.getIdentity(), participation.getIdentity());
					if (!allDescendantsCompliant) { // Current participation has non-compliant URI. 
						return allDescendantsCompliant;
					}
				}
			}
		}
		// All descendants of this ComponentDefinition object have compliant URIs.
		return allDescendantsCompliant;		
	}
		
	protected boolean isComplete() {
		if (sbolDocument==null) return false;
		for (URI modelURI : models) {
			if (sbolDocument.getModel(modelURI)==null) return false;
		}
		for (FunctionalComponent functionalComponent : getFunctionalComponents()) {
			if (functionalComponent.getDefinition()==null) return false;
		}
		for (Module module : getModules()) {
			if (module.getDefinition()==null) return false;
		}
		return true;
	}
	
}
