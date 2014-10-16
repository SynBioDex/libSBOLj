package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Identified;

/**
 * @author Zhen Zhang
 * @version 2.0
 * @param <Identified>
 *
 */

public class SBOLDocument {
	
	//private HashMap<URI, Identified> identityMap;
	private List<TopLevel> topLevels;
	private List<Collection> collections;
	private List<Component> components;
	private List<Model> models;
	private List<Module> modules;
	private List<Structure> structures;

	public SBOLDocument() {
		//identityMap = new HashMap<URI, Identified>();
		topLevels = new ArrayList<TopLevel>();
		collections = new ArrayList<Collection>();
		components = new ArrayList<Component>();
		models = new ArrayList<Model>();
		modules = new ArrayList<Module>();
		structures = new ArrayList<Structure>();
	}
	
	/**
	 * Create a new {@link Module} instance.
	 * @param identity
	 * @param roles
	 * @return
	 */
	public Module createModule(URI identity, List<URI> roles) {
		Module newModule = new Module(identity, roles);
		addModule(newModule);		
		return newModule;
	}
	
	/**
	 * Appends the specified Module to the end of the list of modules.
	 * @param module
	 */
	public void addModule(Module module) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables. 
		
//		// Check if moduleToAdd's URI exists already.		
//		for (Module mod : modules) {
//			if (mod.getIdentity().equals(moduleToAdd.getIdentity())) {
//				System.err.println("Identical URI for module " + mod.getDisplayId() + " already exists in the module list.");
//				break;
//			}
//		}
		modules.add(module);
	}
	
//	/**
//	 * Remove a module from the module list by matching its URI to those in the list. 
//	 * @param moduleToRemove
//	 */
//	public void removeModule(Module moduleToRemove) {
//		for (Module mod : modules) {
//			if (mod.getIdentity().equals(moduleToRemove.getIdentity())) {
//				modules.remove(mod);
//			}
//		}					
//	}
	
	/**
	 * Remove the module matching the given identity from the list of modules. 
	 * @return true if a matching module is removed from the module list.
	 * @param identity
	 */
	public boolean removeModule(URI identity) {
		for (Module mod : modules) {
			if (mod.getIdentity().equals(identity)) {
				modules.remove(mod);
				return true;
			}
		}
		return false;
	}
	
	public Module getModule(URI id) {
		for (Module mod : modules) {
			if (mod.getIdentity().equals(id)) {
				return mod;
			}			
		}
		return null;
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
	
	public void addCollection(Collection collection) {
			// TODO: Recursively check the uniqueness of URIs of each Module and its field variables.
		collections.add(collection);
	}
	
	/**
	 * Remove the collection matching the given identity from the list of collections.
	 * @param identity
	 * @return true if a matching collection is removed from the collection list.
	 */
	public boolean removeCollection(URI identity) {
		for (Collection collection : collections) {
			if (collection.getIdentity().equals(identity)) {
				collections.remove(collection);
				return true;
			}
		}
		return false;
	}

	/**
	 * Create a new {@link Model} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Model createModel(URI identity, URI source, URI language, URI framework, List<URI> roles) {
		Model newModel = new Model(identity, source, language, framework, roles);
		addModel(newModel);
		return newModel;
	}
	
	public void addModel(Model model) {
	// TODO: Recursively check the uniqueness of URIs of each Module and its field variables.
		models.add(model);
	}
	
	/**
	 * Remove the model matching the given identity from the list of models.
	 * @param identity
	 * @return true if a matching model is removed from the model list.
	 */
	public boolean removeModel(URI identity) {
		for (Model model : models) {
			if (model.getIdentity().equals(identity)) {
				models.remove(model);
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Create a new {@link Component} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Component createComponent(URI identity, List<URI> type, List<URI> roles) {
		Component newComponent = new Component(identity, type, roles);
		addComponent(newComponent);
		return newComponent;
	}
	
	public void addComponent(Component component) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables.
		components.add(component);
	}
	
	/**
	 * Remove the component matching the given identity from the list of components.
	 * @param identity
	 * @return true if a matching component is removed from the component list.
	 */
	public boolean removeComponent(URI identity) {
		for (Component component : components) {
			if (component.getIdentity().equals(identity)) {
				components.remove(component);
				return true;
			}
		}
		return false;
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
	
	public void addStructure(Structure structure) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables.
		structures.add(structure);
	}
	
	/**
	 * Remove the structure matching the given identity from the list of structures.
	 * @param identity
	 * @return true if a matching structure is removed from the structure list.
	 */
	public boolean removeStructure(URI identity) {
		for (Structure structure : structures) {
			if (structure.getIdentity().equals(identity)) {
				structures.remove(structure);
				return true;
			}
		}
		return false;
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

	public void addTopLevel(TopLevel topLevel) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables.
		topLevels.add(topLevel);
	}
	
	/**
	 * Remove the top-level instance matching the given identity from the topLevel list.
	 * @param identity
	 * @return true if a matching top-level instance is removed from the topLevel list.
	 */
	public boolean removeTopLevel(URI identity) {
		for (TopLevel topLevel : topLevels) {
			if (topLevel.getIdentity().equals(identity)) {
				components.remove(topLevel);
				return true;
			}
		}
		return false;
	}

	public List<TopLevel> getTopLevels() {
		return topLevels;
	}

	public void setTopLevels(List<TopLevel> topLevels) {
		this.topLevels = topLevels;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Structure> getStructures() {
		return structures;
	}

	public void setStructures(List<Structure> structures) {
		this.structures = structures;
	}
	
	public void clearCollections() {
		collections.clear();
	}
	
	public void clearComponents() {
		components.clear();
	}
	
	public void clearModels() {
		models.clear();
	}
	
	public void clearModules() {
		modules.clear();
	}
	
	public void clearStructures() {
		structures.clear();
	}
	
	public void clearTopLevels() {
		topLevels.clear();
	}
	
	
	
}