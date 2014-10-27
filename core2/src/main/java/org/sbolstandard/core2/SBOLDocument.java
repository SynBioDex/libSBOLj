package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 * @param <Identified>
 *
 */

public class SBOLDocument {
	
	//private HashMap<URI, Identified> identityMap;
	private HashMap<URI, TopLevel> topLevels;
	private HashMap<URI, Collection> collections;
	private HashMap<URI, Component> components;
	private HashMap<URI, Model> models;
	private HashMap<URI, Module> modules;
	private HashMap<URI, Structure> structures;

	public SBOLDocument() {
		//identityMap = new HashMap<URI, Identified>();
		topLevels = new HashMap<URI, TopLevel>();
		collections = new HashMap<URI, Collection>();
		components = new HashMap<URI, Component>();
		models = new HashMap<URI, Model>();
		modules = new HashMap<URI, Module>();
		structures = new HashMap<URI, Structure>();
	}
	
	
	/**
	 * Create a new {@link Module} instance.
	 * @param identity
	 * @param roles
	 * @return
	 */
	public Module createModule(URI identity, Set<URI> roles) {
		Module newModule = new Module(identity, roles);
		addModule(newModule);		
		return newModule;
	}
	
	/**
	 * Appends the specified <code>module</code> to the end of the list of modules.
	 * @param module
	 */
	public void addModule(Module module) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables. 
		modules.put(module.getIdentity(), module);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of modules if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module removeModule(URI moduleURI) {
		return modules.remove(moduleURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param moduleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Module getModule(URI moduleURI) {
		return modules.get(moduleURI);
	}
	
	/**
	 * Returns the list of <code>Module</code> instances owned by this instance.
	 * @return the list of <code>Module</code> instances owned by this instance
	 */
	public List<Module> getModules() {
//		return (List<Module>) modules.values();
		List<Module> modules = new ArrayList<Module>(); 
		modules.addAll(this.modules.values());
		return modules; 
		
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModules() {
		Object[] keySetArray = modules.keySet().toArray();
		for (Object key : keySetArray) {
			removeModule((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param modules
	 */
	public void setModules(List<Module> modules) {		
		clearModules();
		for (Module module : modules) {
			addModule(module);
		}
	}
	
	/**
	 * Create a new {@link Collection} instance.
	 * @param identity
	 * @return
	 */
	public Collection createCollection(URI identity) {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}
	
	/**
	 * Appends the specified <code>collection</code> to the end of the list of collections.
	 * @param collection
	 */
	public void addCollection(Collection collection) {
		// TODO: Recursively check the uniqueness of URIs of each Collection and its field variables. 
		collections.put(collection.getIdentity(), collection);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of collections if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Collection removeCollection(URI collectionURI) {
		return collections.remove(collectionURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param collectionURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}
	
	/**
	 * Returns the list of <code>Collection</code> instances owned by this instance.
	 * @return the list of <code>Collection</code> instances owned by this instance
	 */
	public List<Collection> getCollections() {
//		return (List<Collection>) collections.values();
		List<Collection> collections = new ArrayList<Collection>(); 
		collections.addAll(this.collections.values());
		return collections; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearCollections() {
		Object[] keySetArray = collections.keySet().toArray();
		for (Object key : keySetArray) {
			removeCollection((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>collections</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param collections
	 */
	public void setCollections(List<Collection> collections) {		
		clearCollections();
		for (Collection collection : collections) {
			addCollection(collection);
		}
	}

	/**
	 * Create a new {@link Model} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Model createModel(URI identity, URI source, URI language, URI framework, Set<URI> roles) {
		Model newModel = new Model(identity, source, language, framework, roles);
		addModel(newModel);
		return newModel;
	}
	
		/**
	 * Appends the specified <code>model</code> to the end of the list of models.
	 * @param model
	 */
	public void addModel(Model model) {
		// TODO: Recursively check the uniqueness of URIs of each Model and its field variables. 
		models.put(model.getIdentity(), model);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of models if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Model removeModel(URI modelURI) {
		return models.remove(modelURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param modelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Model getModel(URI modelURI) {
		return models.get(modelURI);
	}
	
	/**
	 * Returns the list of <code>Model</code> instances owned by this instance.
	 * @return the list of <code>Model</code> instances owned by this instance
	 */
	public List<Model> getModels() {
//		return (List<Model>) models.values();
		List<Model> models = new ArrayList<Model>(); 
		models.addAll(this.models.values());
		return models; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModels() {
		Object[] keySetArray = models.keySet().toArray();
		for (Object key : keySetArray) {
			removeModel((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>models</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param models
	 */
	public void setModels(List<Model> models) {		
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	}
	
	/**
	 * Create a new {@link Component} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Component createComponent(URI identity, Set<URI> type, Set<URI> roles) {
		Component newComponent = new Component(identity, type, roles);
		addComponent(newComponent);
		return newComponent;
	}
	
	/**
	 * Appends the specified <code>component</code> to the end of the list of components.
	 * @param component
	 */
	public void addComponent(Component component) {
		// TODO: Recursively check the uniqueness of URIs of each Component and its field variables. 
		components.put(component.getIdentity(), component);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of components if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component removeComponent(URI componentURI) {
		return components.remove(componentURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param componentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getComponent(URI componentURI) {
		return components.get(componentURI);
	}
	
	/**
	 * Returns the list of <code>Component</code> instances owned by this instance.
	 * @return the list of <code>Component</code> instances owned by this instance
	 */
	public List<Component> getComponents() {
//		return (List<Component>) components.values();
		List<Component> components = new ArrayList<Component>(); 
		components.addAll(this.components.values());
		return components; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponents() {
		Object[] keySetArray = components.keySet().toArray();
		for (Object key : keySetArray) {
			removeComponent((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>components</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param components
	 */
	public void setComponents(List<Component> components) {		
		clearComponents();
		for (Component component : components) {
			addComponent(component);
		}
	}
	
	/**
	 * Create a new {@link Structure} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Structure createStructure(URI identity, String elements, URI encoding) {
		Structure newStructure = new Structure(identity, elements, encoding);
		addStructure(newStructure);
		return newStructure;
	}
	
	/**
	 * Appends the specified <code>structure</code> to the end of the list of structures.
	 * @param structure
	 */
	public void addStructure(Structure structure) {
		// TODO: Recursively check the uniqueness of URIs of each Structure and its field variables. 
		structures.put(structure.getIdentity(), structure);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structures if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Structure removeStructure(URI structureURI) {
		return structures.remove(structureURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param structureURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Structure getStructure(URI structureURI) {
		return structures.get(structureURI);
	}
	
	/**
	 * Returns the list of <code>Structure</code> instances owned by this instance.
	 * @return the list of <code>Structure</code> instances owned by this instance
	 */
	public List<Structure> getStructures() {
//		return (List<Structure>) structures.values();
		List<Structure> structures = new ArrayList<Structure>(); 
		structures.addAll(this.structures.values());
		return structures; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearStructures() {
		Object[] keySetArray = structures.keySet().toArray();
		for (Object key : keySetArray) {
			removeStructure((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>structures</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param structures
	 */
	public void setStructures(List<Structure> structures) {		
		clearStructures();
		for (Structure structure : structures) {
			addStructure(structure);
		}
	}

	/**
	 * Create a new {@link TopLevel} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public TopLevel createTopLevel(URI identity) {
		TopLevel newTopLevel = new TopLevel(identity);
	    addTopLevel(newTopLevel);
		return newTopLevel;
	}

	/**
	 * Appends the specified <code>topLevel</code> to the end of the list of topLevels.
	 * @param topLevel
	 */
	public void addTopLevel(TopLevel topLevel) {
		// TODO: Recursively check the uniqueness of URIs of each TopLevel and its field variables. 
		topLevels.put(topLevel.getIdentity(), topLevel);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of topLevels if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public TopLevel removeTopLevel(URI topLevelURI) {
		return topLevels.remove(topLevelURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param topLevelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public TopLevel getTopLevel(URI topLevelURI) {
		return topLevels.get(topLevelURI);
	}
	
	/**
	 * Returns the list of <code>TopLevel</code> instances owned by this instance.
	 * @return the list of <code>TopLevel</code> instances owned by this instance
	 */
	public List<TopLevel> getTopLevels() {
//		return (List<TopLevel>) topLevels.values();
		List<TopLevel> topLevels = new ArrayList<TopLevel>(); 
		topLevels.addAll(this.topLevels.values());
		return topLevels; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearTopLevels() {
		Object[] keySetArray = topLevels.keySet().toArray();
		for (Object key : keySetArray) {
			removeTopLevel((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>topLevels</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param topLevels
	 */
	public void setTopLevels(List<TopLevel> topLevels) {		
		clearTopLevels();
		for (TopLevel topLevel : topLevels) {
			addTopLevel(topLevel);
		}
	}
	
}