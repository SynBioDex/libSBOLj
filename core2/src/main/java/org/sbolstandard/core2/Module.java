package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Module extends TopLevel {
	
	private Set<URI> roles;
	private HashMap<URI, ModuleInstantiation> moduleInstantiations;
	private HashMap<URI, Interaction> interactions;
	private HashMap<URI, FunctionalInstantiation> functionalInstantiations;
	private Set<URI> models;
	
	public Module(URI identity, Set<URI> roles) {
		super(identity);
		setRoles(roles);
		this.moduleInstantiations = new HashMap<URI, ModuleInstantiation>();
		this.interactions = new HashMap<URI, Interaction>();
		this.functionalInstantiations = new HashMap<URI, FunctionalInstantiation>();
		this.models = new HashSet<URI>();		
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
	 * Test if field variable <code>moduleInstantiations</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */
	public boolean isSetModuleInstantiations() {
		if (moduleInstantiations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the ModuleInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of ModuleInstantiation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created ModuleInstantiation instance. 
	 */
	public ModuleInstantiation createModuleInstantiation(URI identity, URI instantiatedModule) {
		ModuleInstantiation moduleInstantiation = new ModuleInstantiation(identity, instantiatedModule);
		addModuleInstantiation(moduleInstantiation);
		return moduleInstantiation;
	}
	
	/**
	 * Adds the specified instance to the list of moduleInstantiations. 
	 * @param moduleInstantiation
	 */
	public void addModuleInstantiation(ModuleInstantiation moduleInstantiation) {
		// TODO: @addModuleInstantiation, Check for duplicated entries.
		moduleInstantiations.put(moduleInstantiation.getIdentity(), moduleInstantiation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of moduleInstantiations if present.
	 * @param moduleInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ModuleInstantiation removeModuleInstantiation(URI moduleInstantiationURI) {
		return moduleInstantiations.remove(moduleInstantiationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of moduleInstantiations if present.
	 * @param moduleInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ModuleInstantiation getModuleInstantiation(URI moduleInstantiationURI) {
		return moduleInstantiations.get(moduleInstantiationURI);
	}
	
	/**
	 * Returns the list of moduleInstantiation instances owned by this instance. 
	 * @return the list of moduleInstantiation instances owned by this instance.
	 */
	public List<ModuleInstantiation> getModuleInstantiations() {
//		return (List<ModuleInstantiation>) moduleInstantiations.values();
		return new ArrayList<ModuleInstantiation>(moduleInstantiations.values());
	}
	
	/**
	 * Removes all entries of the list of moduleInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModuleInstantiations() {
		Object[] keySetArray = moduleInstantiations.keySet().toArray();
		for (Object key : keySetArray) {
			removeModuleInstantiation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of moduleInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param moduleInstantiations
	 */
	public void setModuleInstantiations(
			List<ModuleInstantiation> moduleInstantiations) {
		if(isSetModuleInstantiations())
			clearModuleInstantiations();		
		for (ModuleInstantiation moduleInstantiation : moduleInstantiations) {
			addModuleInstantiation(moduleInstantiation);
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
	public boolean isSetFunctionalInstantiations() {
		if (functionalInstantiations.isEmpty()) 
			return false;
		else
			return true;
	}
 	
	/**
	 * Calls the FunctionalInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of FunctionalInstantiation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created FunctionalInstantiation instance. 
	 */
	public FunctionalInstantiation createFunctionalInstantiation(URI identity, AccessType access, 
			URI instantiatedComponent, DirectionType direction) {
		FunctionalInstantiation functionalInstantiation = 
				new FunctionalInstantiation(identity, access, instantiatedComponent, direction);
		addFunctionalInstantiation(functionalInstantiation);
		return functionalInstantiation;
	}
	
	/**
	 * Adds the specified instance to the list of functionalInstantiations. 
	 * @param functionalInstantiation
	 */
	public void addFunctionalInstantiation(FunctionalInstantiation functionalInstantiation) {
		// TODO: @addFunctionalInstantiation, Check for duplicated entries.
		functionalInstantiations.put(functionalInstantiation.getIdentity(), functionalInstantiation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param functionalInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalInstantiation removeFunctionalInstantiation(URI functionalInstantiationURI) {
		return functionalInstantiations.remove(functionalInstantiationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of functionalInstantiations if present.
	 * @param functionalInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public FunctionalInstantiation getFunctionalInstantiation(URI functionalInstantiationURI) {
		return functionalInstantiations.get(functionalInstantiationURI);
	}
	
	/**
	 * Returns the list of functionalInstantiation instances owned by this instance. 
	 * @return the list of functionalInstantiation instances owned by this instance.
	 */
	public List<FunctionalInstantiation> getFunctionalInstantiations() {
//		return (List<FunctionalInstantiation>) functionalInstantiations.values();
		return new ArrayList<FunctionalInstantiation>(functionalInstantiations.values());
	}
	
	/**
	 * Removes all entries of the list of functionalInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearFunctionalInstantiations() {
		Object[] keySetArray = functionalInstantiations.keySet().toArray();
		for (Object key : keySetArray) {
			removeFunctionalInstantiation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of functionalInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param functionalInstantiations
	 */
	public void setFunctionalInstantiations(
			List<FunctionalInstantiation> functionalInstantiations) {
		clearFunctionalInstantiations();		
		for (FunctionalInstantiation functionalInstantiation : functionalInstantiations) {
			addFunctionalInstantiation(functionalInstantiation);
		}
	}
	
//	/**
//	 * Set optional field variable <code>moduleInstantiations</code> to an empty list.
//	 */
//	public void unsetModuleInstantiations() {
//		moduleInstantiations.clear();
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
	
//	/**
//	 * Set optional field variable <code>models</code> to an empty list.
//	 */
//	public void unsetModels() {
//		models.clear();
//	}
	
	
	


}
