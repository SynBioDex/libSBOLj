package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.abstract_classes.TopLevel;

import static org.sbolstandard.core2.util.URIcompliance.*;

import static org.sbolstandard.core2.util.Version.*;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

public class SBOLDocument {

	private HashMap<URI, GenericTopLevel> genericTopLevels;
	private HashMap<URI, Collection> collections;
	private HashMap<URI, ComponentDefinition> componentDefinitions;
	private HashMap<URI, Model> models;
	private HashMap<URI, ModuleDefinition> moduleDefinitions;
	private HashMap<URI, Sequence> sequences;
	private HashMap<URI,NamespaceBinding> nameSpaces;
	private String defaultURIprefix;

	public SBOLDocument() {
		genericTopLevels = new HashMap<URI, GenericTopLevel>();
		collections = new HashMap<URI, Collection>();
		componentDefinitions = new HashMap<URI, ComponentDefinition>();
		models = new HashMap<URI, Model>();
		moduleDefinitions = new HashMap<URI, ModuleDefinition>();
		sequences = new HashMap<URI, Sequence>();
		nameSpaces = new HashMap<URI, NamespaceBinding>();
		nameSpaces.put(URI.create(Sbol2Terms.sbol2.getNamespaceURI()), Sbol2Terms.sbol2);
		nameSpaces.put(URI.create(Sbol1Terms.rdf.getNamespaceURI()), Sbol1Terms.rdf);
		nameSpaces.put(URI.create(Sbol2Terms.dc.getNamespaceURI()), Sbol2Terms.dc);
	}

	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @param displayId
	 * @param version
	 * @param roles
	 * @return the created {@link ModuleDefinition} object.
	 */
	public ModuleDefinition createModuleDefinition(String displayId, String version, Set<URI> roles) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newModuleDefinitionURI = URI.create(defaultURIprefix + '/' + displayId + '/' + version);
		return createModuleDefinition(newModuleDefinitionURI, roles);
	}
	
	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @param identity
	 * @param roles
	 * @return the {@link ModuleDefinition} object.
	 */
	public ModuleDefinition createModuleDefinition(URI identity, Set<URI> roles) {
		ModuleDefinition newModule = new ModuleDefinition(identity, roles);
		if (addModuleDefinition(newModule)) {
			return newModule;	
		}
		else {
			return null;
		}
	}

	/**
	 * Appends the specified {@code ModuleDefinition} object to the end of the list of module definitions.
	 * @param newModuleDefinition
	 * @return {@code true} if the {@code newModuleDefinition} is successfully added, {@code false} otherwise. 
	 */
	public boolean addModuleDefinition(ModuleDefinition newModuleDefinition) {
		if (newModuleDefinition.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newModuleDefinition.getIdentity()));
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(moduleDefinitions.keySet(), persistentId)) {
				// Check if URI exists in the moduleDefinitions map.
				if (!moduleDefinitions.containsKey(newModuleDefinition.getIdentity())) {
					moduleDefinitions.put(newModuleDefinition.getIdentity(), newModuleDefinition);
					ModuleDefinition latestModuleDefinition = moduleDefinitions.get(persistentId);
					if (latestModuleDefinition == null) {
						moduleDefinitions.put(persistentId, newModuleDefinition);
					}
					else {
						if (isFirstVersionNewer(
								extractVersion(newModuleDefinition.getIdentity()),
								extractVersion(latestModuleDefinition.getIdentity()))){
							moduleDefinitions.put(persistentId, newModuleDefinition);
						}
					}
					return true;
				}
				else // key exists in moduleDefinitions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if sequence's URI exists in all maps.
			if (!keyExistsInOtherMaps(moduleDefinitions.keySet(), newModuleDefinition.getIdentity())) {
				if (!moduleDefinitions.containsKey(newModuleDefinition.getIdentity())) {
					moduleDefinitions.put(newModuleDefinition.getIdentity(), newModuleDefinition);
					return true;
				}
				else // key exists in moduleDefinitions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}

	/**
	 * Removes the object matching the specified URI from the list of modules if present.
	 * @param moduleURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition removeModuleDefinition(URI moduleURI) {
		return moduleDefinitions.remove(moduleURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param moduleURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition getModuleDefinition(URI moduleURI) {
		return moduleDefinitions.get(moduleURI);
	}

	/**
	 * Returns the list of <code>Module</code> objects owned by this object.
	 * @return the list of <code>Module</code> objects owned by this object
	 */
	public List<ModuleDefinition> getModuleDefinitions() {
		List<ModuleDefinition> moduleDefinitions = new ArrayList<ModuleDefinition>();
		moduleDefinitions.addAll(this.moduleDefinitions.values());
		return moduleDefinitions;

	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
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
	 * Create a new {@link Collection} object.
	 * @param identity
	 * @return {@link Collection} object.
	 */
	public Collection createCollection(URI identity) {
		Collection newCollection = new Collection(identity);
		if (addCollection(newCollection)) {
			return newCollection;
		}
		else
			return null;
	}
	
	/**
	 * Create a new {@link Collection} object with the default URI prefix. 
	 * @param displayId
	 * @param version
	 * @return the created {@link Collection} object.
	 */
	public Collection createCollection(String displayId, String version) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newCollectionURI = URI.create(defaultURIprefix + '/' + displayId + '/' + version);
		return createCollection(newCollectionURI);
	}

	/**
	 * Appends the specified {@code newCollection} object to the end of the list of collections.
	 * @param newCollection
	 * @return {@code true} if the {@code newCollection} is successfully added, {@code false} otherwise.
	 */
	public boolean addCollection(Collection newCollection) {
		if (newCollection.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newCollection.getIdentity()));
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(collections.keySet(), persistentId)) {
				// Check if URI exists in the collections map.
				if (!collections.containsKey(newCollection.getIdentity())) {
					collections.put(newCollection.getIdentity(), newCollection);
					Collection latestCollection = collections.get(persistentId);
					if (latestCollection == null) {
						collections.put(persistentId, newCollection);
					}
					else {
						if (isFirstVersionNewer(extractVersion(newCollection.getIdentity()),
								extractVersion(latestCollection.getIdentity()))) {
							collections.put(persistentId, newCollection);
						}
					}
					return true;
				}
				else // key exists in collections map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if collection's URI exists in all maps.
			if (!keyExistsInOtherMaps(collections.keySet(), newCollection.getIdentity())) {
				if (!collections.containsKey(newCollection.getIdentity())) {
					collections.put(newCollection.getIdentity(), newCollection);
					return true;
				}
				else // key exists in collections map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}


	/**
	 * Removes the object matching the specified URI from the list of collections if present.
	 * @param collectionURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Collection removeCollection(URI collectionURI) {
		return collections.remove(collectionURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param collectionURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}

	/**
	 * Returns the list of <code>Collection</code> objects owned by this object.
	 * @return the list of <code>Collection</code> objects owned by this object
	 */
	public List<Collection> getCollections() {
		//		return (List<Collection>) collections.values();
		List<Collection> collections = new ArrayList<Collection>();
		collections.addAll(this.collections.values());
		return collections;
	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
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
	 * Create a new {@link Model} object.
	 * @param displayId
	 * @param version
	 * @param source
	 * @param language
	 * @param framework
	 * @param roles
	 * @return the created {@link Model} object. 
	 */
	public Model createModel(String displayId, String version, 
			URI source, URI language, URI framework, Set<URI> roles) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newModelURI = URI.create(defaultURIprefix + '/' + displayId + '/' +version);
		return createModel(newModelURI, source, language, framework, roles);
	}

	/**
	 * Create a new {@link Model} object.
	 * @param identity
	 * @param source
	 * @param language
	 * @param framework
	 * @param roles
	 * @return {@link Model} object.
	 */
	public Model createModel(URI identity, URI source, URI language, URI framework, Set<URI> roles) {
		Model newModel = new Model(identity, source, language, framework, roles);
		if (addModel(newModel)) {
			return newModel;	
		}
		else {
			return null;
		}
	}

	/**
	 * Appends the specified <code>model</code> to the end of the list of models.
	 * @param newModel
	 * @return {@code true} if the {@code newModel} is successfully added, {@code false} otherwise.
	 */
	public boolean addModel(Model newModel) {
		if (newModel.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newModel.getIdentity()));
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(models.keySet(), persistentId)) {
				// Check if URI exists in the models map.
				if (!models.containsKey(newModel.getIdentity())) {
					models.put(newModel.getIdentity(), newModel);
					Model latestModel = models.get(persistentId);
					if (latestModel == null) {
						models.put(persistentId, newModel);
					}
					else {
						if (isFirstVersionNewer(
								extractVersion(newModel.getIdentity()),
								extractVersion(latestModel.getIdentity()))){
							models.put(persistentId, newModel);
						}
					}
					return true;
				}
				else // key exists in models map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if model's URI exists in all maps.
			if (!keyExistsInOtherMaps(models.keySet(), newModel.getIdentity())) {
				if (!models.containsKey(newModel.getIdentity())) {
					models.put(newModel.getIdentity(), newModel);
					return true;
				}
				else // key exists in models map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}

	/**
	 * Removes the object matching the specified URI from the list of models if present.
	 * @param modelURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Model removeModel(URI modelURI) {
		return models.remove(modelURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param modelURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Model getModel(URI modelURI) {
		return models.get(modelURI);
	}

	/**
	 * Returns the list of <code>Model</code> objects owned by this object.
	 * @return the list of <code>Model</code> objects owned by this object
	 */
	public List<Model> getModels() {
		//		return (List<Model>) models.values();
		List<Model> models = new ArrayList<Model>();
		models.addAll(this.models.values());
		return models;
	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
	 */
	public void clearModels() {
		Object[] keySetArray = models.keySet().toArray();
		for (Object key : keySetArray) {
			removeModel((URI) key);
		}
	}

	/**
	 * Clears the existing list <code>models</code>, then appends all of the elements in the specified model to the end of this list.
	 * @param models
	 */
	public void setModels(List<Model> models) {
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	}

	/**
	 * Create a new {@link ComponentDefinition} object.
	 * @param identity
	 * @param type
	 * @param roles
	 * @return {@link ComponentDefinition} object.
	 */
	public ComponentDefinition createComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, type, roles);
		if (addComponentDefinition(newComponentDefinition)) {
			return newComponentDefinition;
		}
		else 
			return null;
	}

	/**
	 * Create a new {@link ComponentDefinition} object with the default URI prefix.
	 * @param displayId
	 * @param version
	 * @param types
	 * @param roles
	 * @return {@code true} if the {@code newComponentDefinition} is successfully added, {@code false} otherwise.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types, Set<URI> roles) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newComponentDefinitionURI = URI.create(defaultURIprefix + '/' + displayId + '/' + version);
		return createComponentDefinition(newComponentDefinitionURI, types, roles);
	}

	/**
	 * Appends the specified element to the end of the list of component definitions.
	 * @param newComponentDefinition
	 * @return {@code true} if the {@code newComponentDefinition} is successfully added, {@code false} otherwise.
	 */
	public boolean addComponentDefinition(ComponentDefinition newComponentDefinition) {		
		if (newComponentDefinition.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newComponentDefinition.getIdentity()));
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(componentDefinitions.keySet(), persistentId)) {
				// Check if URI exists in the componentDefinitions map.	
				if (!componentDefinitions.containsKey(newComponentDefinition.getIdentity())) {
					componentDefinitions.put(newComponentDefinition.getIdentity(), newComponentDefinition);
					ComponentDefinition latestComponentDefinition = componentDefinitions.get(persistentId);
					if (latestComponentDefinition == null) {
						componentDefinitions.put(persistentId, newComponentDefinition);
					}
					else {
						if (isFirstVersionNewer(
								extractVersion(newComponentDefinition.getIdentity()),
								extractVersion(latestComponentDefinition.getIdentity()))) {
							componentDefinitions.put(persistentId, newComponentDefinition);
						}
					}
					return true;
				}
				else // key exists in componentDefinitions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if sequence's URI exists in all maps.
			if (!keyExistsInOtherMaps(componentDefinitions.keySet(), newComponentDefinition.getIdentity())) {
				if (!componentDefinitions.containsKey(newComponentDefinition.getIdentity())) {
					componentDefinitions.put(newComponentDefinition.getIdentity(), newComponentDefinition);

					return true;
				}
				else // key exists in componentDefinitions map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}

	/**
	 * Removes the object matching the specified URI from the list of component definitions if present.
	 * @param componentDefinitionURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition removeComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.remove(componentDefinitionURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of component definitions if present.
	 * @param componentDefinitionURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}

	/**
	 * Returns the list of <code>ComponentDefinition</code> objects owned by this object.
	 * @return the list of <code>ComponentDefinition</code> objects owned by this object
	 */
	public List<ComponentDefinition> getComponentDefinitions() {
		//		return (List<Component>) components.values();
		List<ComponentDefinition> components = new ArrayList<ComponentDefinition>();
		components.addAll(this.componentDefinitions.values());
		return components;
	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
	 */
	public void clearComponentDefinitions() {
		Object[] keySetArray = componentDefinitions.keySet().toArray();
		for (Object key : keySetArray) {
			removeComponentDefinition((URI) key);
		}
	}

	/**
	 * Clears the existing list of component definitions, then appends all of the elements in the specified model to the end of this list.
	 * @param componentDefinitions
	 */
	public void setComponentDefinitions(List<ComponentDefinition> componentDefinitions) {
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @param identity
	 * @param elements
	 * @param encoding
	 * @return {@link Sequence} object.
	 */
	public Sequence createSequence(URI identity, String elements, URI encoding) {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		if (addSequence(newSequence)) {
			return newSequence;
		}
		else
			// TODO return exception
			return null;
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @param URIprefix
	 * @param displayId
	 * @param elements
	 * @param encoding
	 * @return the created Sequence object.
	 */
	public Sequence createSequence(String URIprefix, String displayId, String version, String elements, URI encoding) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newSequenceURI = URI.create(defaultURIprefix + '/' + displayId + '/' + version);		
		return createSequence(newSequenceURI, elements, encoding);
	}
	
	/**
 	 * Create a copy of the given top-level object, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link TopLevel} with the given version, and add it to its corresponding top-level objects list.
	 * @param toplevel
	 * @param newURIprefix
	 * @return the created {@link TopLevel} object
	 */
	public TopLevel createCopyWithURIprefix(TopLevel toplevel, String newURIprefix) {
		String olddisplayId = extractDisplayId(((Collection) toplevel).getIdentity(), 0);
		String oldVersion = extractVersion(toplevel.getIdentity());
		return createCopy(toplevel, newURIprefix, olddisplayId, oldVersion);
	}

	/**
	 * Create a copy of the given top-level object, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link TopLevel} with the given version, and add it to its corresponding top-level objects list.
	 * @param toplevel
	 * @param newVersion
	 * @return {@link TopLevel} object
	 */
	public TopLevel createCopyWithVersion(TopLevel toplevel, String newVersion) {
		String oldURIprefix = extractURIprefix(((Collection) toplevel).getIdentity());
		String olddisplayId = extractDisplayId(((Collection) toplevel).getIdentity(), 0);	
		return createCopy(toplevel, oldURIprefix, olddisplayId, newVersion);
	}
	
	/**
	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link GenericTopLevel} with the given display ID, and add it to its corresponding top-level objects list.
	 * @param toplevel
	 * @param newDisplayId
	 * @return {@link TopLevel} object
	 */
	public TopLevel createCopyWithDisplayId(TopLevel toplevel, String newDisplayId) {
		String oldURIprefix = extractURIprefix(toplevel.getIdentity());
		String oldVersion = extractVersion(toplevel.getIdentity());
		return createCopy(toplevel, oldURIprefix, 
				newDisplayId, oldVersion);
	}
	
	/**
	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link GenericTopLevel} with the given URIprefix and display ID, and add it to its corresponding top-level objects list.
	 * @param toplevel
	 * @param newDisplayId
	 * @return {@link TopLevel} object
	 */
	public TopLevel createCopyWithPersistentId(TopLevel toplevel, String newURIprefix, String newDisplayId) {
		String oldVersion = extractVersion(toplevel.getIdentity());
		return createCopy(toplevel, newURIprefix, 
				newDisplayId, oldVersion);
	}

//	/**
//	 * Create an object of the top-level classes, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
//	 * {@link Sequence}, or {@link TopLevel} with a new display ID, and add it to its corresponding top-level objects list.
//	 * @param toplevel
//	 * @param newPrefix
//	 * @return {@link TopLevel} object
//	 */
//	public TopLevel createCopyWithNewPrefix(TopLevel toplevel, String newPrefix) {
//		if (toplevel objectof Collection) {			
//			Collection newCollection = ((Collection) toplevel).copy(newPrefix);			
//			if (addCollection(newCollection)) {
//				return newCollection;
//			}
//			else {
//				return null;
//			}
//		}
//		else if (toplevel objectof ComponentDefinition) {
//			ComponentDefinition newComponentDefinition = ((ComponentDefinition) toplevel).copy(newPrefix);
//			if (addComponentDefinition(newComponentDefinition)) {
//				return newComponentDefinition;
//			}
//			else {
//				return null;
//			}
//		}
//		else if (toplevel objectof Model) {
//			Model newModel = ((Model) toplevel).copy(newPrefix);			
//			if (addModel(newModel)) {
//				return newModel;
//			}
//			else {
//				return null;
//			}
//		}
//		else if (toplevel objectof ModuleDefinition) {
//			ModuleDefinition newModuleDefinition = ((ModuleDefinition) toplevel).copy(newPrefix);
//			if (addModuleDefinition(newModuleDefinition)) {
//				return newModuleDefinition;
//			}
//			else {
//				return null;
//			}
//		}
//		else if (toplevel objectof Sequence) {
//			Sequence newSequence = ((Sequence) toplevel).copy(newPrefix);
//			if (addSequence(newSequence)) {
//				return newSequence;
//			}
//			else {
//				return null;
//			}
//		}
//		else if (toplevel objectof GenericTopLevel) {
//			GenericTopLevel newGenericTopLevel = ((GenericTopLevel) toplevel).copy(newPrefix);
//			if (addGenericTopLevel(newGenericTopLevel)) {
//				return newGenericTopLevel;
//			}
//			else {
//				return null;
//			}
//		}
//		else {
//			return null;
//		}
//	}
	
	/**
 	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link GenericTopLevel} with the given URIprefix, display ID, and version. Then add it to its corresponding top-level objects list.
	 * @param toplevel
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @return the copied {@link TopLevel} object
	 */
	public TopLevel createCopy(TopLevel toplevel, String URIprefix, String displayId, String version) {
		if (toplevel instanceof Collection) {			
			Collection newCollection = ((Collection) toplevel).copy(URIprefix, displayId, version);
			if (addCollection(newCollection)) {
				return newCollection;
			}
			else {
				return null;
			}
		}
		else if (toplevel instanceof ComponentDefinition) {
			ComponentDefinition newComponentDefinition = ((ComponentDefinition) toplevel).copy(URIprefix, displayId, version);
			if (addComponentDefinition(newComponentDefinition)) {
				return newComponentDefinition;
			}
			else {
				return null;
			}
		}
		else if (toplevel instanceof Model) {
			Model newModel = ((Model) toplevel).copy(URIprefix, displayId, version);			
			if (addModel(newModel)) {
				return newModel;
			}
			else {
				return null;
			}
		}
		else if (toplevel instanceof ModuleDefinition) {
			ModuleDefinition newModuleDefinition = ((ModuleDefinition) toplevel).copy(URIprefix, displayId, version);
			if (addModuleDefinition(newModuleDefinition)) {
				return newModuleDefinition;
			}
			else {
				return null;
			}
		}
		else if (toplevel instanceof Sequence) {
			Sequence newSequence = ((Sequence) toplevel).copy(URIprefix, displayId, version);
			if (addSequence(newSequence)) {
				return newSequence;
			}
			else {
				return null;
			}
		}
		else if (toplevel instanceof GenericTopLevel) {
			GenericTopLevel newGenericTopLevel = ((GenericTopLevel) toplevel).copy(URIprefix, displayId, version);
			if (addGenericTopLevel(newGenericTopLevel)) {
				return newGenericTopLevel;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}

	/**
	 * Appends the specified <code>sequence</code> to the end of the list of sequences.
	 * @param newSequence
	 * @return <code>true</code> if the specified sequence is successfully added.
	 */
	public boolean addSequence(Sequence newSequence) {
		if (newSequence.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newSequence.getIdentity()));
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(sequences.keySet(), persistentId)) {
				// Check if URI exists in the sequences map.
				if (!sequences.containsKey(newSequence.getIdentity())) {
					sequences.put(newSequence.getIdentity(), newSequence);
					Sequence latestSequence = sequences.get(persistentId);
					if (latestSequence == null) {
						sequences.put(persistentId, newSequence);
					}
					else {
						if (isFirstVersionNewer(
								extractVersion(newSequence.getIdentity()),
								extractVersion(latestSequence.getIdentity()))){
							sequences.put(persistentId, newSequence);
						}
					}
					return true;
				}
				else // key exists in sequences map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if sequence's URI exists in all maps.
			if (!keyExistsInOtherMaps(sequences.keySet(), newSequence.getIdentity())) {
				if (!sequences.containsKey(newSequence.getIdentity())) {
					sequences.put(newSequence.getIdentity(), newSequence);
					return true;
				}
				else // key exists in sequences map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}

	/**
	 * Removes the object matching the specified URI from the list of structures if present.
	 * @param sequenceURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Sequence removeSequence(URI sequenceURI) {
		return sequences.remove(sequenceURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param sequenceURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}

	/**
	 * Returns the list of <code>Structure</code> objects owned by this object.
	 * @return the list of <code>Structure</code> objects owned by this object
	 */
	public List<Sequence> getSequences() {
		//		return (List<Structure>) structures.values();
		List<Sequence> structures = new ArrayList<Sequence>();
		structures.addAll(this.sequences.values());
		return structures;
	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
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
	 * Create a new {@link GenericTopLevel} object.
	 * @param displayId
	 * @param version
	 * @param rdfType
	 * @return the created {@link GenericTopLevel} object.
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) {
		if (!isDisplayIdCompliant(displayId)) {
			return null;
		}
		if (!isVersionCompliant(version)) {
			return null;
		}
		if (defaultURIprefix == null) {
			// TODO: Error: defaultURIprefix is null. 
			return null;
		}
		URI newGenericTopLevelURI = URI.create(defaultURIprefix + '/' + displayId + '/' + version);
		return createGenericTopLevel(newGenericTopLevelURI, rdfType);
	}

	/**
	 * Create a new {@link GenericTopLevel} object.
	 * @param identity
	 * @param rdfType
	 * @return {@link GenericTopLevel} object.
	 */
	public GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) {
		GenericTopLevel newGenericTopLevel = new GenericTopLevel(identity,rdfType);
		if (addGenericTopLevel(newGenericTopLevel)) {
			return newGenericTopLevel;			
		}
		else {
			return null;
		}
			

	}

	/**
	 * Appends the specified {@code TopLevel} object to the end of the list of topLevels.
	 * @param newGenericTopLevel
	 * @return {@code true} if the {@code newTopLevel} is successfully added, {@code false} otherwise.
	 */
	public boolean addGenericTopLevel(GenericTopLevel newGenericTopLevel) {
		if (newGenericTopLevel.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newGenericTopLevel.getIdentity()));
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(genericTopLevels.keySet(), persistentId)) {
				// Check if URI exists in the genericTopLevels map.
				if (!genericTopLevels.containsKey(newGenericTopLevel.getIdentity())) {
					genericTopLevels.put(newGenericTopLevel.getIdentity(), newGenericTopLevel);
					GenericTopLevel latestGenericTopLevel = genericTopLevels.get(persistentId);
					if (latestGenericTopLevel == null) {
						genericTopLevels.put(persistentId, newGenericTopLevel);
					}
					else {
						if (isFirstVersionNewer(
								extractVersion(newGenericTopLevel.getIdentity()),
								extractVersion(latestGenericTopLevel.getIdentity()))){
							genericTopLevels.put(persistentId, newGenericTopLevel);
						}
					}
					return true;
				}
				else // key exists in genericTopLevels map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if genericTopLevel's URI exists in all maps.
			if (!keyExistsInOtherMaps(genericTopLevels.keySet(), newGenericTopLevel.getIdentity())) {
				if (!genericTopLevels.containsKey(newGenericTopLevel.getIdentity())) {
					genericTopLevels.put(newGenericTopLevel.getIdentity(), newGenericTopLevel);
					return true;
				}
				else // key exists in genericTopLevels map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		//genericTopLevels.put(topLevel.getIdentity(), topLevel);

	}

	/**
	 * Removes the object matching the specified URI from the list of topLevels if present.
	 * @param topLevelURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel removeGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.remove(topLevelURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param topLevelURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the list of <code>GenericTopLevel</code> objects owned by this object.
	 * @return the list of <code>GenericTopLevel</code> objects owned by this object
	 */
	public List<GenericTopLevel> getGenericTopLevels() {
		//		return (List<GenericTopLevel>) topLevels.values();
		List<GenericTopLevel> topLevels = new ArrayList<GenericTopLevel>();
		topLevels.addAll(this.genericTopLevels.values());
		return topLevels;
	}

	/**
	 * Removes all entries of the list of structuralConstraint objects owned by this object. The list will be empty after this call returns.
	 */
	public void clearGenericTopLevels() {
		Object[] keySetArray = genericTopLevels.keySet().toArray();
		for (Object key : keySetArray) {
			removeGenericTopLevel((URI) key);
		}
	}

	/**
	 * Clears the existing list <code>topLevels</code>, then appends all of the elements in the specified topLevels to the end of this list.
	 * @param topLevels
	 */
	public void setGenericTopLevels(List<GenericTopLevel> topLevels) {
		clearGenericTopLevels();
		for (GenericTopLevel topLevel : topLevels) {
			addGenericTopLevel(topLevel);
		}
	}

	/**
	 * Adds a namespace URI and its prefix. Deprecated method. Use {@link #addNamespaceBinding(NamespaceBinding)}.
	 * 
	 * @param nameSpaceUri The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 * @deprecated
	 */
	public void addNamespaceBinding(URI nameSpaceUri, String prefix) {
		nameSpaces.put(nameSpaceUri, NamespaceBinding(nameSpaceUri.toString(), prefix));
	}
	
	/**
	 * @param namespaceBinding
	 */
	public void addNamespaceBinding(NamespaceBinding namespaceBinding) {
		nameSpaces.put(URI.create(namespaceBinding.getNamespaceURI()), namespaceBinding);
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
	
	/**
	 * Removes the object matching the specified URI from the list of nameSpaces if present.
	 * @param nameSpaceURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public NamespaceBinding removeNamespaceBinding(URI nameSpaceURI) {
		return nameSpaces.remove(nameSpaceURI);
	}
	
	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @param namespaceBinding
	 */
	public void setNameSpaceBindings(List<NamespaceBinding> namespaceBinding) {
		clearNamespaceBindings();
		for (NamespaceBinding namespace : namespaceBinding) {
			addNamespaceBinding(namespace);
		}
	}
	
	/**
	 * 
	 */
	public void clearNamespaceBindings() {
		Object[] keySetArray = nameSpaces.keySet().toArray();
		for (Object key : keySetArray) {
			removeNamespaceBinding((URI) key);
		}		
	}


	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @param nameSpaceURI
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public NamespaceBinding getNameSpaceBinding(URI nameSpaceURI) {
		return nameSpaces.get(nameSpaceURI);
	}

	/**
	 * Check if the specified key exists in any hash maps in this class other than the one with the specified keySet. This method
	 * constructs a set of key sets for other hash maps first, and then checks if the key exists.
	 * @param keySet
	 * @param key
	 * @return <code>true</code> if the specified key exists in other hash maps.
	 */
	private boolean keyExistsInOtherMaps(Set<URI> keySet, URI key) {
		Set<Set<URI>> complementSet = new HashSet<Set<URI>>();
		complementSet.add(collections.keySet());
		complementSet.add(componentDefinitions.keySet());
		complementSet.add(models.keySet());
		complementSet.add(moduleDefinitions.keySet());
		complementSet.add(nameSpaces.keySet());
		complementSet.add(sequences.keySet());
		complementSet.remove(keySet);
		for (Set<URI> otherKeySet : complementSet) {
			if (otherKeySet.contains(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collections == null) ? 0 : collections.hashCode());
		result = prime * result
				+ ((componentDefinitions == null) ? 0 : componentDefinitions.hashCode());
		result = prime * result + ((genericTopLevels == null) ? 0 : genericTopLevels.hashCode());
		result = prime * result + ((models == null) ? 0 : models.hashCode());
		result = prime * result + ((moduleDefinitions == null) ? 0 : moduleDefinitions.hashCode());
		result = prime * result + ((nameSpaces == null) ? 0 : nameSpaces.hashCode());
		result = prime * result + ((sequences == null) ? 0 : sequences.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SBOLDocument other = (SBOLDocument) obj;
		if (collections == null) {
			if (other.collections != null)
				return false;
		} else if (!collections.equals(other.collections))
			return false;
		if (componentDefinitions == null) {
			if (other.componentDefinitions != null)
				return false;
		} else if (!componentDefinitions.equals(other.componentDefinitions))
			return false;
		if (genericTopLevels == null) {
			if (other.genericTopLevels != null)
				return false;
		} else if (!genericTopLevels.equals(other.genericTopLevels))
			return false;
		if (models == null) {
			if (other.models != null)
				return false;
		} else if (!models.equals(other.models))
			return false;
		if (moduleDefinitions == null) {
			if (other.moduleDefinitions != null)
				return false;
		} else if (!moduleDefinitions.equals(other.moduleDefinitions))
			return false;
		if (nameSpaces == null) {
			if (other.nameSpaces != null)
				return false;
		} else if (!nameSpaces.equals(other.nameSpaces))
			return false;
		if (sequences == null) {
			if (other.sequences != null)
				return false;
		} else if (!sequences.equals(other.sequences))
			return false;
		return true;
	}
	
	/**
	 * Set the default URI prefix to the given prefix.
	 * @param defaultURIprefix
	 */
	public void setDefaultURIprefix(String defaultURIprefix) {
		if (isURIprefixCompliant(defaultURIprefix)) {
			this.defaultURIprefix = defaultURIprefix;	
		}
		else {
			// TODO: Generate warning message: invalid or null defaultURIprefix
		}
	}
	
	/**
	 * Returns the default URI prefix.
	 * @return the default URI prefix.
	 */
	public String getDefaultURIprefix() {
		return defaultURIprefix;
	}
}
