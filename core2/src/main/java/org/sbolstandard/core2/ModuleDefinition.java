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
import static org.sbolstandard.core2.Version.*;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class ModuleDefinition extends TopLevel {
	
	private Set<URI> roles;
	private HashMap<URI, Module> modules;
	private HashMap<URI, Interaction> interactions;
	private HashMap<URI, FunctionalComponent> functionalComponents;
	private Set<URI> models;
	
	public ModuleDefinition(URI identity, Set<URI> roles) {
		super(identity);
		setRoles(roles);
		this.modules = new HashMap<URI, Module>();
		this.interactions = new HashMap<URI, Interaction>();
		this.functionalComponents = new HashMap<URI, FunctionalComponent>();
		this.models = new HashSet<URI>();		
	}
	
	private ModuleDefinition(ModuleDefinition moduleDefinition) {
		super(moduleDefinition);
		Set<URI> roles = new HashSet<URI>();
		for (URI role : moduleDefinition.getRoles()) {
			roles.add(role);
		}		
		this.setRoles(roles);
		if (!moduleDefinition.getModules().isEmpty()) {
			List<Module> subModules = new ArrayList<Module>();
			for (Module subModule : moduleDefinition.getModules()) {
				subModules.add(subModule.deepCopy());
			}
			this.setModules(subModules);
		}
		if (!moduleDefinition.getInteractions().isEmpty()) {
			List<Interaction> interactions = new ArrayList<Interaction>();
			for (Interaction interaction : moduleDefinition.getInteractions()) {
				interactions.add(interaction.deepCopy());
			}
			this.setInteractions(interactions);
		}
		if (!moduleDefinition.getFunctionalComponents().isEmpty()) {
			List<FunctionalComponent> components = new ArrayList<FunctionalComponent>();
			for (FunctionalComponent component : moduleDefinition.getFunctionalComponents()) {
				components.add(component.deepCopy());
			}
			this.setFunctionalComponents(components);
		}
		if (!moduleDefinition.getModels().isEmpty()) {
			Set<URI> models = new HashSet<URI>();
			for (URI model : moduleDefinition.getModels()) {
				models.add(model);
			}
		}
	}


	/**
	 * Adds the specified element to the set <code>roles</code> if it is not already present. 
	 * @param roleURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @param roleURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 * @param roles
	 */
	public void setRoles(Set<URI> roles) {
		this.roles = roles;
	}
	
	/**
	 * Returns the field variable <code>roles</code>.
	 * @return
	 */
	public Set<URI> getRoles() {
		return roles;
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
	 * @param identity
	 * @param location
	 * @return the created ModuleInstantiation instance. 
	 */
	public Module createModule(URI identity, URI moduleDefinitionURI) {
		Module subModule = new Module(identity, moduleDefinitionURI);
		if (addModule(subModule)) {
			return subModule;	
		}
		else {
			return null;
		}
	}
	
	/**
	 * @param displayId
	 * @param moduleDefinitionURI
	 * @return
	 */
	public Module createModule(String displayId, URI moduleDefinitionURI) {
		URI newModuleURI = URI.create(extractURIprefix(this.getIdentity())
				+ '/' + displayId + '/' + extractVersion(this.getIdentity()));
		if (isChildURIcompliant(this.getIdentity(), newModuleURI)) {
			return createModule(newModuleURI, moduleDefinitionURI);
		}
		else {
			// TODO: Generate warning message here.
			return null;
		}
	}
	
	
	
	/**
	 * Adds the specified instance to the list of subModules. 
	 * @param newModule
	 * @return 
	 */
	public boolean addModule(Module newModule) {
		if (isChildURIcompliant(this.getIdentity(), newModule.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(newModule.getIdentity()));
			if (!keyExistsInOtherMaps(modules.keySet(), persistentId)) {
				// Check if URI exists in the subModules map.
				if (!modules.containsKey(newModule.getIdentity())) {
					modules.put(newModule.getIdentity(), newModule);
					Module latestSubModule = modules.get(persistentId);
					if (latestSubModule == null) {
						modules.put(persistentId, newModule);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(newModule.getIdentity()),
								extractVersion(latestSubModule.getIdentity()))) {
							modules.put(persistentId, newModule);
						}
					}
					return true;
				}
				else // key exists in subModules map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if subModule's URI exists in all maps.
			if (!keyExistsInOtherMaps(modules.keySet(), newModule.getIdentity())) {
				if (!modules.containsKey(newModule.getIdentity())) {
					modules.put(newModule.getIdentity(), newModule);					
					return true;
				}
				else // key exists in subModules map
					return false;
			}
			else // key exists in other maps
				return false;
		}		
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of subModules if present.
	 * @param moduleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module removeModule(URI moduleURI) {
		return modules.remove(moduleURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of subModules if present.
	 * @param subModuleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module getModule(URI subModuleURI) {
		return modules.get(subModuleURI);
	}
	
	/**
	 * Returns the list of subModule instances owned by this instance. 
	 * @return the list of subModule instances owned by this instance.
	 */
	public List<Module> getModules() {
		return new ArrayList<Module>(modules.values());
	}
	
	/**
	 * Removes all entries of the list of subModule instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModules() {
		Object[] keySetArray = modules.keySet().toArray();
		for (Object key : keySetArray) {
			removeModule((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of subModule instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param subModules
	 */
	public void setModules(
			List<Module> subModules) {
		if(!getModules().isEmpty())
			clearModules();		
		for (Module subModule : subModules) {
			addModule(subModule);
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
	 * @param identity
	 * @param location
	 * @return the  created Interaction instance. 
	 */
	public Interaction createInteraction(URI identity, Set<URI> type, List<Participation> participations) {
		Interaction interaction = new Interaction(identity, type, participations);
		if (addInteraction(interaction)) {
			return interaction;	
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * @param displayId
	 * @param type
	 * @param participations
	 * @return
	 */
	public Interaction createInteraction(String displayId, Set<URI> type, List<Participation> participations) {
		URI newInteractionURI = URI.create(extractURIprefix(this.getIdentity())
				+ '/' + displayId + '/' + extractVersion(this.getIdentity()));
		if (isChildURIcompliant(this.getIdentity(), newInteractionURI)) {
			return createInteraction(newInteractionURI, type, participations);
		}
		else {
			// TODO: Generate warning messages here.
			return null;
		}
	}
	
	
	/**
	 * Adds the specified instance to the list of interactions. 
	 * @param interaction
	 */
	public boolean addInteraction(Interaction interaction) {
		if (isChildURIcompliant(this.getIdentity(), interaction.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(interaction.getIdentity()));
			if (!keyExistsInOtherMaps(interactions.keySet(), persistentId)) {
				// Check if URI exists in the interactions map.
				if (!interactions.containsKey(interaction.getIdentity())) {
					interactions.put(interaction.getIdentity(), interaction);
					Interaction latestInteraction = interactions.get(persistentId);
					if (latestInteraction == null) {
						interactions.put(persistentId, interaction);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(interaction.getIdentity()),
								extractVersion(latestInteraction.getIdentity()))) {
							interactions.put(persistentId, interaction);
						}
					}
					return true;
				}
				else // key exists in interactions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if interaction's URI exists in all maps.
			if (!keyExistsInOtherMaps(interactions.keySet(), interaction.getIdentity())) {
				if (!interactions.containsKey(interaction.getIdentity())) {
					interactions.put(interaction.getIdentity(), interaction);					
					return true;
				}
				else // key exists in interactions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of interactions if present.
	 * @param interactionURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Interaction removeInteraction(URI interactionURI) {
		return interactions.remove(interactionURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of interactions if present.
	 * @param interactionURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Interaction getInteraction(URI interactionURI) {
		return interactions.get(interactionURI);
	}
	
	/**
	 * Returns the list of interaction instances owned by this instance. 
	 * @return the list of interaction instances owned by this instance.
	 */
	public List<Interaction> getInteractions() {
//		return (List<Interaction>) interactions.values();
		return new ArrayList<Interaction>(interactions.values());
	}
	
	/**
	 * Removes all entries of the list of interaction instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearInteractions() {
		Object[] keySetArray = interactions.keySet().toArray();
		for (Object key : keySetArray) {
			removeInteraction((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of interaction instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param interactions
	 */
	public void setInteractions(
			List<Interaction> interactions) {
		clearInteractions();		
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
	 * @param identity
	 * @param location
	 * @return the created {@link FunctionalComponent} instance. 
	 */
	public FunctionalComponent createFunctionalComponent(URI identity, AccessType access, 
			URI functionalComponentURI, DirectionType direction) {
		FunctionalComponent functionalComponent = 
				new FunctionalComponent(identity, access, functionalComponentURI, direction);
		if(addFunctionalComponent(functionalComponent)) {
			return functionalComponent;	
		}
		else {
			return null;
		}
	}

	/**
	 * @param displayId
	 * @param access
	 * @param functionalComponentURI
	 * @param direction
	 * @return
	 */
	public FunctionalComponent createFunctionalComponent(String displayId, AccessType access, 
			URI functionalComponentURI, DirectionType direction) {
		URI newComponentDefinitionURI = URI.create(extractURIprefix(this.getIdentity())
				+ '/' + displayId + '/' + extractVersion(this.getIdentity()));
		if (isChildURIcompliant(this.getIdentity(), newComponentDefinitionURI)) {		
			return createFunctionalComponent(newComponentDefinitionURI, access, functionalComponentURI, direction);
		}
		else {
			// TODO: Generate a warning here?
			return null;
		}
	}
	
	/**
	 * Adds the specified instance to the list of components.
	 * @param functionalComponent
	 */
	public boolean addFunctionalComponent(FunctionalComponent functionalComponent) {
		if (isChildURIcompliant(this.getIdentity(), functionalComponent.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(functionalComponent.getIdentity()));
			if (!keyExistsInOtherMaps(functionalComponents.keySet(), persistentId)) {
				// Check if URI exists in the components map.
				if (!functionalComponents.containsKey(functionalComponent.getIdentity())) {
					functionalComponents.put(functionalComponent.getIdentity(), functionalComponent);
					FunctionalComponent latestFunctionalComponent = functionalComponents.get(persistentId);
					if (latestFunctionalComponent == null) {
						functionalComponents.put(persistentId, functionalComponent);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(functionalComponent.getIdentity()),
								extractVersion(latestFunctionalComponent.getIdentity()))) {
							functionalComponents.put(persistentId, functionalComponent);
						}
					}
					return true;
				}
				else // key exists in components map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if component's URI exists in all maps.
			if (!keyExistsInOtherMaps(functionalComponents.keySet(), functionalComponent.getIdentity())) {
				if (!functionalComponents.containsKey(functionalComponent.getIdentity())) {
					functionalComponents.put(functionalComponent.getIdentity(), functionalComponent);					
					return true;
				}
				else // key exists in components map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param componentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalComponent removeFunctionalComponent(URI componentURI) {
		return functionalComponents.remove(componentURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param componentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalComponent getFunctionalComponent(URI componentURI) {
		return functionalComponents.get(componentURI);
	}
	
	/**
	 * Returns the list of functionalInstantiation instances owned by this instance. 
	 * @return the list of functionalInstantiation instances owned by this instance.
	 */
	public List<FunctionalComponent> getFunctionalComponents() {
		return new ArrayList<FunctionalComponent>(functionalComponents.values());
	}
	
	/**
	 * Removes all entries of the list of functionalInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponents() {
		Object[] keySetArray = functionalComponents.keySet().toArray();
		for (Object key : keySetArray) {
			removeFunctionalComponent((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of functionalInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param components
	 */
	public void setFunctionalComponents(
			List<FunctionalComponent> components) {
		clearComponents();		
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
	
	/**
	 * Adds the specified instance to the list of models. 
	 * @param modelURI
	 */
	public void addModel(URI modelURI) {
		models.add(modelURI);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of models if present.
	 * @param modelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeModel(URI modelURI) {
		return models.remove(modelURI);
	}
	
	/**
	 * Clears the existing list of model instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param models
	 */
	public void setModels(Set<URI> models) {
		this.models = models;
	}
	
	/**
	 * Returns the list of model instances referenced by this instance.
	 * @return the list of model instances referenced by this instance
	 */
	public Set<URI> getModels() {
		return models;
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
	 * @param keySet
	 * @param key
	 * @return <code>true</code> if the specified key exists in other hash maps.
	 */
	private boolean keyExistsInOtherMaps(Set<URI> keySet, URI key) {
		Set<Set<URI>> complementSet = new HashSet<Set<URI>>();
		complementSet.add(functionalComponents.keySet());
		complementSet.add(interactions.keySet());		
		complementSet.remove(keySet);
		for (Set<URI> otherKeySet : complementSet) {
			if (otherKeySet.contains(key)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ModuleDefinition copy(String URIprefix, String displayId, String version) {
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
	
}
