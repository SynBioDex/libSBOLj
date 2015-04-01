package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.abstract_classes.Identified;
import org.sbolstandard.core2.abstract_classes.TopLevel;

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

	public SBOLDocument() {
		//identityMap = new HashMap<URI, Identified>();
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
	 * Create a new {@link ModuleDefinition} instance.
	 * @param identity
	 * @param roles
	 * @return the {@link ModuleDefinition} instance.
	 */
	public ModuleDefinition createModuleDefinition(URI identity, Set<URI> roles) {
		ModuleDefinition newModule = new ModuleDefinition(identity, roles);
		addModuleDefinition(newModule);
		return newModule;
	}

	/**
	 * Appends the specified <code>module</code> to the end of the list of modules.
	 * @param newModuleDefinition
	 * @return
	 */
	public boolean addModuleDefinition(ModuleDefinition newModuleDefinition) {
		if (newModuleDefinition.isSetPersistentIdentity() && newModuleDefinition.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(moduleDefinitions.keySet(), newModuleDefinition.getPersistentIdentity())) {
				// Check if URI exists in the moduleDefinitions map.
				if (!moduleDefinitions.containsKey(newModuleDefinition.getIdentity())) {
					moduleDefinitions.put(newModuleDefinition.getIdentity(), newModuleDefinition);
					ModuleDefinition latestModuleDefinition = moduleDefinitions.get(newModuleDefinition.getPersistentIdentity());
					if (latestModuleDefinition == null) {
						moduleDefinitions.put(newModuleDefinition.getPersistentIdentity(), newModuleDefinition);
					}
					else {
						if (isFirstVersionNewer(newModuleDefinition.getVersion(), latestModuleDefinition.getVersion())) {
							moduleDefinitions.put(newModuleDefinition.getPersistentIdentity(), newModuleDefinition);
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
	 * Removes the instance matching the specified URI from the list of modules if present.
	 * @param moduleURI
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
	 * @return {@link Collection} instance.
	 */
	public Collection createCollection(URI identity) {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}

	/**
	 * Appends the specified <code>collection</code> to the end of the list of collections.
	 * @param newCollection
	 * @return
	 */
	public boolean addCollection(Collection newCollection) {
		if (newCollection.isSetPersistentIdentity() && newCollection.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(collections.keySet(), newCollection.getPersistentIdentity())) {
				// Check if URI exists in the collections map.
				if (!collections.containsKey(newCollection.getIdentity())) {
					collections.put(newCollection.getIdentity(), newCollection);
					Collection latestCollection = collections.get(newCollection.getPersistentIdentity());
					if (latestCollection == null) {
						collections.put(newCollection.getPersistentIdentity(), newCollection);
					}
					else {
						if (isFirstVersionNewer(newCollection.getVersion(), latestCollection.getVersion())) {
							collections.put(newCollection.getPersistentIdentity(), newCollection);
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


	private boolean isFirstVersionNewer(String version1, String version2) {
		// TODO compare according to Maven's version scheme.
		return false;
	}


	/**
	 * Removes the instance matching the specified URI from the list of collections if present.
	 * @param collectionURI
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
	 * @param source
	 * @param language
	 * @param framework
	 * @param roles
	 * @return {@link Model} instance.
	 */
	public Model createModel(URI identity, URI source, URI language, URI framework, Set<URI> roles) {
		Model newModel = new Model(identity, source, language, framework, roles);
		addModel(newModel);
		return newModel;
	}

	/**
	 * Appends the specified <code>model</code> to the end of the list of models.
	 * @param newModel
	 * @return
	 */
	public boolean addModel(Model newModel) {
		if (newModel.isSetPersistentIdentity() && newModel.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(models.keySet(), newModel.getPersistentIdentity())) {
				// Check if URI exists in the models map.
				if (!models.containsKey(newModel.getIdentity())) {
					models.put(newModel.getIdentity(), newModel);
					Model latestModel = models.get(newModel.getPersistentIdentity());
					if (latestModel == null) {
						models.put(newModel.getPersistentIdentity(), newModel);
					}
					else {
						if (isFirstVersionNewer(newModel.getVersion(), latestModel.getVersion())) {
							models.put(newModel.getPersistentIdentity(), newModel);
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
	 * Removes the instance matching the specified URI from the list of models if present.
	 * @param modelURI
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
	 * Create a new {@link ComponentDefinition} instance.
	 * @param identity
	 * @param type
	 * @param roles
	 * @return {@link ComponentDefinition} instance.
	 */
	public ComponentDefinition createComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, type, roles);
		addComponentDefinition(newComponentDefinition);
		return newComponentDefinition;
	}

	/**
	 * Create a new {@link ComponentDefinition} instance.
	 * @param URIprefix
	 * @param id
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance.
	 */
	public ComponentDefinition createComponentDefinition(String URIprefix, String id, String version, Set<URI> type, Set<URI> roles) {
		URI newComponentDefinitionURI = URI.create(URIprefix + '/' + id + version);
		if (Identified.isURIcompliant(newComponentDefinitionURI)) {
			ComponentDefinition newComponentDefinition = new ComponentDefinition(newComponentDefinitionURI, type, roles);
			if (addComponentDefinition(newComponentDefinition)) {
				return newComponentDefinition;
			}
			else
				return null;
		}
		else {
			// TODO: Non-compliant URI
			return null;
		}
	}

	/**
	 * Appends the specified element to the end of the list of component definitions.
	 * @param newComponentDefinition
	 * @return
	 */
	public boolean addComponentDefinition(ComponentDefinition newComponentDefinition) {
		if (newComponentDefinition.isSetPersistentIdentity() && newComponentDefinition.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(componentDefinitions.keySet(), newComponentDefinition.getPersistentIdentity())) {
				// Check if URI exists in the componentDefinitions map.
				if (!componentDefinitions.containsKey(newComponentDefinition.getIdentity())) {
					componentDefinitions.put(newComponentDefinition.getIdentity(), newComponentDefinition);
					ComponentDefinition latestComponentDefinition = componentDefinitions.get(newComponentDefinition.getPersistentIdentity());
					if (latestComponentDefinition == null) {
						componentDefinitions.put(newComponentDefinition.getPersistentIdentity(), newComponentDefinition);
					}
					else {
						if (isFirstVersionNewer(newComponentDefinition.getVersion(), latestComponentDefinition.getVersion())) {
							componentDefinitions.put(newComponentDefinition.getPersistentIdentity(), newComponentDefinition);
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
	 * Removes the instance matching the specified URI from the list of component definitions if present.
	 * @param componentDefinitionURI
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
	 * Create a new {@link Sequence} instance.
	 * @param identity
	 * @param elements
	 * @param encoding
	 * @return {@link Sequence} instance.
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
	 * Create a new {@link Sequence} instance.
	 * @param URIprefix
	 * @param id
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance.
	 */
	public Sequence createSequence(String URIprefix, String id, String version, String elements, URI encoding) {
		//Sequence newSequence = new Sequence(authority, id, elements, encoding);
		URI newSequenceURI = URI.create(URIprefix + '/' + id + '/' + version);
		if (Identified.isURIcompliant(newSequenceURI)) {
			Sequence newSequence = new Sequence(newSequenceURI, elements, encoding);
			if (addSequence(newSequence)) {
				return newSequence;
			}
			else
				return null;
		}
		else {
			// TODO: Non-compliant URI
			return null;
		}
	}

	/**
	 * Create an instance of the top-level classes, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link TopLevel} with a new major version, and add it to its corresponding top-level instances list.
	 * @param toplevel
	 * @return {@link TopLevel} instance
	 */
	public TopLevel createNewVersion(TopLevel toplevel, String version) {
		TopLevel newTopLevel = toplevel.newVersion(version);
		if (newTopLevel instanceof Collection) {
			if (addCollection((Collection) newTopLevel)) {
				return newTopLevel;
			}
			else {
				return null;
			}
		}
		else if (newTopLevel instanceof ComponentDefinition) {
			if (addComponentDefinition((ComponentDefinition) newTopLevel)) {
				return newTopLevel;
			}
			else {
				return null;
			}
		}
		else if (newTopLevel instanceof Model) {
			if (addModel((Model) newTopLevel)) {
				return newTopLevel;
			}
			else {
				return null;
			}
		}
		else if (newTopLevel instanceof ModuleDefinition) {
			if (addModuleDefinition((ModuleDefinition) newTopLevel)) {
				return newTopLevel;
			}
			else {
				return null;
			}
		}
		else if (newTopLevel instanceof Sequence) {
			if (addSequence((Sequence) newTopLevel)) {
				return newTopLevel;
			}
			else {
				return null;
			}
		}
		else if (newTopLevel instanceof TopLevel) {
			if (addGenericTopLevel((GenericTopLevel) newTopLevel)) {
				return newTopLevel;
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
	 * @param sequence
	 * @return <code>true</code> if the specified sequence is successfully added.
	 */
	public boolean addSequence(Sequence sequence) {
		if (sequence.isSetPersistentIdentity() && sequence.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(sequences.keySet(), sequence.getPersistentIdentity())) {
				// Check if URI exists in the sequences map.
				if (!sequences.containsKey(sequence.getIdentity())) {
					sequences.put(sequence.getIdentity(), sequence);
					Sequence latestSequence = sequences.get(sequence.getPersistentIdentity());
					if (latestSequence == null) {
						sequences.put(sequence.getPersistentIdentity(), sequence);
					}
					else {
						// TODO Extract the version of latestCollection and compare according to Maven's versioning scheme.
						//						if (latestSequence.getMajorVersion() < sequence.getMajorVersion()) {
						//							sequences.put(sequence.getPersistentIdentity(), sequence);
						//						}
						//						else if (latestSequence.getMajorVersion() == sequence.getMajorVersion()){
						//							if (latestSequence.getMinorVersion() < sequence.getMinorVersion()) {
						//								sequences.put(sequence.getPersistentIdentity(), sequence);
						//							}
						//						}
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
			if (!keyExistsInOtherMaps(sequences.keySet(), sequence.getIdentity())) {
				if (!sequences.containsKey(sequence.getIdentity())) {
					sequences.put(sequence.getIdentity(), sequence);
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
	 * Removes the instance matching the specified URI from the list of structures if present.
	 * @param sequenceURI
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
	 * Create a new {@link GenericTopLevel} instance.
	 * @param identity
	 * @param rdfType
	 * @return {@link GenericTopLevel} instance.
	 */
	public GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) {
		GenericTopLevel newGenericTopLevel = new GenericTopLevel(identity,rdfType);
		addGenericTopLevel(newGenericTopLevel);
		return newGenericTopLevel;
	}

	/**
	 * Appends the specified <code>topLevel</code> to the end of the list of topLevels.
	 * @param newGenericTopLevel
	 * @return
	 */
	public boolean addGenericTopLevel(GenericTopLevel newGenericTopLevel) {
		if (newGenericTopLevel.isSetPersistentIdentity() && newGenericTopLevel.isSetVersion()) {
			// Compliant URI should come in here.
			// Check if persistent identity exists in other maps.
			if (!keyExistsInOtherMaps(genericTopLevels.keySet(), newGenericTopLevel.getPersistentIdentity())) {
				// Check if URI exists in the genericTopLevels map.
				if (!genericTopLevels.containsKey(newGenericTopLevel.getIdentity())) {
					genericTopLevels.put(newGenericTopLevel.getIdentity(), newGenericTopLevel);
					GenericTopLevel latestGenericTopLevel = genericTopLevels.get(newGenericTopLevel.getPersistentIdentity());
					if (latestGenericTopLevel == null) {
						genericTopLevels.put(newGenericTopLevel.getPersistentIdentity(), newGenericTopLevel);
					}
					else {
						if (isFirstVersionNewer(newGenericTopLevel.getVersion(), latestGenericTopLevel.getVersion())) {
							genericTopLevels.put(newGenericTopLevel.getPersistentIdentity(), newGenericTopLevel);
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
	 * Removes the instance matching the specified URI from the list of topLevels if present.
	 * @param topLevelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel removeGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.remove(topLevelURI);
	}

	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param topLevelURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the list of <code>GenericTopLevel</code> instances owned by this instance.
	 * @return the list of <code>GenericTopLevel</code> instances owned by this instance
	 */
	public List<GenericTopLevel> getGenericTopLevels() {
		//		return (List<GenericTopLevel>) topLevels.values();
		List<GenericTopLevel> topLevels = new ArrayList<GenericTopLevel>();
		topLevels.addAll(this.genericTopLevels.values());
		return topLevels;
	}

	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
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
	 * Adds a namespace URI and its prefix
	 * @param nameSpaceUri The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 */
	public void addNameSpaceBinding(URI nameSpaceUri, String prefix) {
		// TODO @addNameSpaceBinding: Check for duplicates.
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
}
