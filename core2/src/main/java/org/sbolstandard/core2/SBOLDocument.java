package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import static uk.ac.ncl.intbio.core.datatree.Datatree.*;

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
	private HashMap<URI, ComponentDefinition> componentDefinitions;
	private HashMap<URI, Model> models;
	private HashMap<URI, ModuleDefinition> moduleDefinitions;
	private HashMap<URI, Sequence> sequences;
	private HashMap<URI,NamespaceBinding> nameSpaces;

	public SBOLDocument() {
		//identityMap = new HashMap<URI, Identified>();
		topLevels = new HashMap<URI, TopLevel>();
		collections = new HashMap<URI, Collection>();
		componentDefinitions = new HashMap<URI, ComponentDefinition>();
		models = new HashMap<URI, Model>();
		moduleDefinitions = new HashMap<URI, ModuleDefinition>();
		sequences = new HashMap<URI, Sequence>();
		nameSpaces = new HashMap<URI, NamespaceBinding>();
		nameSpaces.put(URI.create(Sbol2Terms.sbol2.getNamespaceURI()), Sbol2Terms.sbol2);
	}
	
	
	/**
	 * Create a new {@link ModuleDefinition} instance.
	 * @param identity
	 * @param roles
	 * @return
	 */
	public ModuleDefinition createModuleDefinition(URI identity, Set<URI> roles) {
		ModuleDefinition newModule = new ModuleDefinition(identity, roles);
		addModuleDefinition(newModule);		
		return newModule;
	}
	
	/**
	 * Appends the specified <code>module</code> to the end of the list of modules.
	 * @param moduleDefinition
	 */
	public void addModuleDefinition(ModuleDefinition moduleDefinition) {
		// TODO: Recursively check the uniqueness of URIs of each Module and its field variables. 
		moduleDefinitions.put(moduleDefinition.getIdentity(), moduleDefinition);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of modules if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition removeModuleDefinition(URI moduleURI) {
		return moduleDefinitions.remove(moduleURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param moduleURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition getModuleDefinition(URI moduleURI) {
		return moduleDefinitions.get(moduleURI);
	}
	
	/**
	 * Returns the list of <code>Module</code> instances owned by this instance.
	 * @return the list of <code>Module</code> instances owned by this instance
	 */
	public List<ModuleDefinition> getModuleDefinitions() {
		List<ModuleDefinition> moduleDefinitions = new ArrayList<ModuleDefinition>(); 
		moduleDefinitions.addAll(this.moduleDefinitions.values());
		return moduleDefinitions; 
		
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearModuleDefinitions() {
		Object[] keySetArray = moduleDefinitions.keySet().toArray();
		for (Object key : keySetArray) {
			removeModuleDefinition((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param moduleDefinitions
	 */
	public void setModuleDefinitions(List<ModuleDefinition> moduleDefinitions) {		
		clearModuleDefinitions();
		for (ModuleDefinition module : moduleDefinitions) {
			addModuleDefinition(module);
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
	 * Create a new {@link ComponentDefinition} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public ComponentDefinition createComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, type, roles);
		addComponentDefinition(newComponentDefinition);
		return newComponentDefinition;
	}
	
	/**
	 * Appends the specified element to the end of the list of component definitions.
	 * @param componentDefinition
	 */
	public void addComponentDefinition(ComponentDefinition componentDefinition) {
		// TODO: Recursively check the uniqueness of URIs of each Component and its field variables. 
		componentDefinitions.put(componentDefinition.getIdentity(), componentDefinition);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of component definitions if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition removeComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.remove(componentDefinitionURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of component definitions if present.
	 * @param componentDefinitionURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}
	
	/**
	 * Returns the list of <code>ComponentDefinition</code> instances owned by this instance.
	 * @return the list of <code>ComponentDefinition</code> instances owned by this instance
	 */
	public List<ComponentDefinition> getComponentDefinitions() {
//		return (List<Component>) components.values();
		List<ComponentDefinition> components = new ArrayList<ComponentDefinition>(); 
		components.addAll(this.componentDefinitions.values());
		return components; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponentDefinitions() {
		Object[] keySetArray = componentDefinitions.keySet().toArray();
		for (Object key : keySetArray) {
			removeComponentDefinition((URI) key);
		}
	}
	
	/**
	 * Clears the existing list of component definitions, then appends all of the elements in the specified collection to the end of this list.
	 * @param componentDefinitions
	 */
	public void setComponentDefinitions(List<ComponentDefinition> componentDefinitions) {		
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}
	
	/**
	 * Create a new {@link Sequence} instance.
	 * @param identity
	 * @param displayId
	 * @return
	 */
	public Sequence createSequence(URI identity, String elements, URI encoding) {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}
	
	/**
	 * Appends the specified <code>structure</code> to the end of the list of structures.
	 * @param sequence
	 */
	public void addSequence(Sequence sequence) {
		// TODO: Recursively check the uniqueness of URIs of each Structure and its field variables. 
		sequences.put(sequence.getIdentity(), sequence);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structures if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Sequence removeSequence(URI sequenceURI) {
		return sequences.remove(sequenceURI);
	}
		
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param sequenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}
	
	/**
	 * Returns the list of <code>Structure</code> instances owned by this instance.
	 * @return the list of <code>Structure</code> instances owned by this instance
	 */
	public List<Sequence> getSequences() {
//		return (List<Structure>) structures.values();
		List<Sequence> structures = new ArrayList<Sequence>(); 
		structures.addAll(this.sequences.values());
		return structures; 
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSequences() {
		Object[] keySetArray = sequences.keySet().toArray();
		for (Object key : keySetArray) {
			removeSequence((URI) key);
		}
	}
	
	/**
	 * Clears the existing list <code>structures</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param sequences
	 */
	public void setSequences(List<Sequence> sequences) {		
		clearSequences();
		for (Sequence sequence : sequences) {
			addSequence(sequence);
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
	
	/**
	 * Adds a namespace URI and its prefix
	 * @param nameSpaceUri The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 */
	public void addNameSpaceBinding(URI nameSpaceUri, String prefix) {
		nameSpaces.put(nameSpaceUri, NamespaceBinding(nameSpaceUri.toString(), prefix));
	}
	
	/**
	 * Gets the namespace bindings for the document
	 * @return A list of {@link NamespaceBinding}
	 */
	public List<NamespaceBinding> getNameSpaceBindings() {
		List<NamespaceBinding> bindings = new ArrayList<NamespaceBinding>(); 
		bindings.addAll(this.nameSpaces.values());
		return bindings; 
	}
	
	
}