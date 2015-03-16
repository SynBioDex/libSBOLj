package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;
import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.TopLevel;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class ModuleDefinition extends TopLevel {
	
	private Set<URI> roles;
	private HashMap<URI, Module> subModules;
	private HashMap<URI, Interaction> interactions;
	private HashMap<URI, FunctionalComponent> components;
	private Set<URI> models;
	
	public ModuleDefinition(URI identity, Set<URI> roles) {
		super(identity);
		setRoles(roles);
		this.subModules = new HashMap<URI, Module>();
		this.interactions = new HashMap<URI, Interaction>();
		this.components = new HashMap<URI, FunctionalComponent>();
		this.models = new HashSet<URI>();		
	}
	
	private ModuleDefinition(ModuleDefinition moduleDefinition) {
		super(moduleDefinition);
		Set<URI> roles = new HashSet<URI>();
		for (URI role : moduleDefinition.getRoles()) {
			roles.add(role);
		}		
		this.setRoles(roles);
		if (moduleDefinition.isSetSubModules()) {
			List<Module> subModules = new ArrayList<Module>();
			for (Module subModule : moduleDefinition.getSubModule()) {
				subModules.add(subModule.deepCopy());
			}
			this.setSubModules(subModules);
		}
		if (moduleDefinition.isSetInteractions()) {
			List<Interaction> interactions = new ArrayList<Interaction>();
			for (Interaction interaction : moduleDefinition.getInteractions()) {
				interactions.add(interaction.deepCopy());
			}
			this.setInteractions(interactions);
		}
		if (moduleDefinition.isSetComponents()) {
			List<FunctionalComponent> components = new ArrayList<FunctionalComponent>();
			for (FunctionalComponent component : moduleDefinition.getComponents()) {
				components.add(component.deepCopy());
			}
			this.setComponents(components);
		}
		if (moduleDefinition.isSetModels()) {
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
	
	/**
	 * Test if field variable <code>subModules</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */
	public boolean isSetSubModules() {
		if (subModules.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the ModuleInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of ModuleInstantiation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the created ModuleInstantiation instance. 
	 */
	public Module createSubModule(URI identity, URI subModuleURI) {
		Module subModule = new Module(identity, subModuleURI);
		addSubModule(subModule);
		return subModule;
	}
	
	/**
	 * Adds the specified instance to the list of subModules. 
	 * @param subModule
	 */
	public void addSubModule(Module subModule) {
		// TODO: @addModuleInstantiation, Check for duplicated entries.
		subModules.put(subModule.getIdentity(), subModule);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of subModules if present.
	 * @param subModuleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module removeSubModule(URI subModuleURI) {
		return subModules.remove(subModuleURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of subModules if present.
	 * @param subModuleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module getSubModule(URI subModuleURI) {
		return subModules.get(subModuleURI);
	}
	
	/**
	 * Returns the list of subModule instances owned by this instance. 
	 * @return the list of subModule instances owned by this instance.
	 */
	public List<Module> getSubModule() {
		return new ArrayList<Module>(subModules.values());
	}
	
	/**
	 * Removes all entries of the list of subModule instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSubModules() {
		Object[] keySetArray = subModules.keySet().toArray();
		for (Object key : keySetArray) {
			removeSubModule((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of subModule instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param subModules
	 */
	public void setSubModules(
			List<Module> subModules) {
		if(isSetSubModules())
			clearSubModules();		
		for (Module subModule : subModules) {
			addSubModule(subModule);
		}
	}
	
	/**
	 * Test if field variable <code>interactions</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */		
	public boolean isSetInteractions() {
		if (interactions.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Calls the Interaction constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Interaction instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created Interaction instance. 
	 */
	public Interaction createInteraction(URI identity, Set<URI> type, List<Participation> participations) {
		Interaction interaction = new Interaction(identity, type, participations);
		addInteraction(interaction);
		return interaction;
	}
	
	/**
	 * Adds the specified instance to the list of interactions. 
	 * @param interaction
	 */
	public void addInteraction(Interaction interaction) {
		// TODO: @addInteraction, Check for duplicated entries.
		interactions.put(interaction.getIdentity(), interaction);
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
		
	/**
	 * Test if field variable <code>functionalInstantiations</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */
	public boolean isSetComponents() {
		if (components.isEmpty()) 
			return false;
		else
			return true;
	}
 	
	/**
	 * Calls the FunctionalInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of FunctionalInstantiation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the created {@link FunctionalComponent} instance. 
	 */
	public FunctionalComponent createComponent(URI identity, AccessType access, 
			URI functionalComponentURI, DirectionType direction) {
		FunctionalComponent functionalComponent = 
				new FunctionalComponent(identity, access, functionalComponentURI, direction);
		addComponent(functionalComponent);
		return functionalComponent;
	}
	
	/**
	 * Adds the specified instance to the list of functionalInstantiations. 
	 * @param component
	 */
	public void addComponent(FunctionalComponent component) {
		// TODO: @addFunctionalInstantiation, Check for duplicated entries.
		components.put(component.getIdentity(), component);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param componentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalComponent removeComponent(URI componentURI) {
		return components.remove(componentURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param componentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalComponent getComponent(URI componentURI) {
		return components.get(componentURI);
	}
	
	/**
	 * Returns the list of functionalInstantiation instances owned by this instance. 
	 * @return the list of functionalInstantiation instances owned by this instance.
	 */
	public List<FunctionalComponent> getComponents() {
		return new ArrayList<FunctionalComponent>(components.values());
	}
	
	/**
	 * Removes all entries of the list of functionalInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponents() {
		Object[] keySetArray = components.keySet().toArray();
		for (Object key : keySetArray) {
			removeComponent((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of functionalInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param components
	 */
	public void setComponents(
			List<FunctionalComponent> components) {
		clearComponents();		
		for (FunctionalComponent component : components) {
			addComponent(component);
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
	
	/**
	 * Test if field variable <code>models</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */
	public boolean isSetModels() {
		if (models.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Adds the specified instance to the list of models. 
	 * @param modelURI
	 */
	public void addModel(URI modelURI) {
		// TODO: @addModel, Check for duplicated entries.
		models.add(modelURI);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of models if present.
	 * @param modelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeModel(URI modelURI) {
		// TODO: Need to check if the set of models' URIs is empty. 
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
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((interactions == null) ? 0 : interactions.hashCode());
		result = prime * result + ((models == null) ? 0 : models.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((subModules == null) ? 0 : subModules.hashCode());
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
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
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
		if (subModules == null) {
			if (other.subModules != null)
				return false;
		} else if (!subModules.equals(other.subModules))
			return false;
		return true;
	}


	@Override
	protected ModuleDefinition deepCopy() {
		return new ModuleDefinition(this);
	}
	
//	/**
//	 * Set optional field variable <code>models</code> to an empty list.
//	 */
//	public void unsetModels() {
//		models.clear();
//	}
	
	
	


}
