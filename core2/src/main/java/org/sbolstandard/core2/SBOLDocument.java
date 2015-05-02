package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.net.URI;
import java.util.*;

import javax.xml.namespace.QName;

import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.*;
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
	private HashMap<URI, NamespaceBinding> nameSpaces;
	private String defaultURIprefix;
	private boolean complete = false;
	
	/**
	 * Top level types
	 *
	 */
	static final class TopLevelTypes {
		static final String collection = "col";
		static final String moduleDefinition = "md";
		static final String model = "mod";
		static final String componentDefinition = "cd";
		static final String sequence = "seq";
		static final String genericTopLevel = "gen";
	}

	public SBOLDocument() {
		genericTopLevels = new HashMap<>();
		collections = new HashMap<>();
		componentDefinitions = new HashMap<>();
		models = new HashMap<>();
		moduleDefinitions = new HashMap<>();
		sequences = new HashMap<>();
		nameSpaces = new HashMap<>();
		nameSpaces.put(URI.create(Sbol2Terms.sbol2.getNamespaceURI()), Sbol2Terms.sbol2);
		nameSpaces.put(URI.create(Sbol1Terms.rdf.getNamespaceURI()), Sbol1Terms.rdf);
		nameSpaces.put(URI.create(Sbol2Terms.dc.getNamespaceURI()), Sbol2Terms.dc);
	}

	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @return the created {@link ModuleDefinition} object.
	 */
	public ModuleDefinition createModuleDefinition(String displayId, String version) {
		validateCreationData(displayId, version);
		ModuleDefinition md = createModuleDefinition(createCompliantURI(defaultURIprefix, TopLevelTypes.moduleDefinition, 
				displayId, version));
		md.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.moduleDefinition, 
				displayId, ""));
		md.setDisplayId(displayId);
		md.setVersion(version);
		return md;
	}
	
	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @return the {@link ModuleDefinition} object.
	 */
	public ModuleDefinition createModuleDefinition(URI identity) {
		ModuleDefinition newModule = new ModuleDefinition(identity);
		addModuleDefinition(newModule);
		return newModule;
	}

	/**
	 * Appends the specified {@code ModuleDefinition} object to the end of the list of module definitions.
	 */
	public void addModuleDefinition(ModuleDefinition newModuleDefinition) {
		addTopLevel(newModuleDefinition, moduleDefinitions, "moduleDefinition",
                collections, componentDefinitions, genericTopLevels, models, sequences);
	}

	/**
	 * Removes the object matching the specified URI from the list of modules if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition removeModuleDefinition(URI moduleURI) {
		return moduleDefinitions.remove(moduleURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition getModuleDefinition(URI moduleURI) {
		return moduleDefinitions.get(moduleURI);
	}

	/**
	 * Returns the list of <code>Module</code> objects owned by this object.
	 * @return the list of <code>Module</code> objects owned by this object
	 */
	public Set<ModuleDefinition> getModuleDefinitions() {
		Set<ModuleDefinition> moduleDefinitions = new HashSet<>();
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
	 */
	public void setModuleDefinitions(List<ModuleDefinition> moduleDefinitions) {
		clearModuleDefinitions();
		for (ModuleDefinition module : moduleDefinitions) {
			addModuleDefinition(module);
		}
	}

	/**
	 * Create a new {@link Collection} object.
	 * @return {@link Collection} object.
	 */
	public Collection createCollection(URI identity) {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}
	
	/**
	 * Create a new {@link Collection} object with the default URI prefix. 
	 * @return the created {@link Collection} object.
	 */
	public Collection createCollection(String displayId, String version) {
		validateCreationData(displayId, version);
		Collection c = createCollection(
				createCompliantURI(defaultURIprefix, TopLevelTypes.collection, displayId, version));
		c.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.collection, displayId, ""));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}

	/**
	 * Appends the specified {@code newCollection} object to the end of the list of collections.
	 */
	public void addCollection(Collection newCollection) {
		addTopLevel(newCollection, collections, "collection",
                componentDefinitions, genericTopLevels, models, moduleDefinitions, sequences);
	}


	/**
	 * Removes the object matching the specified URI from the list of collections if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Collection removeCollection(URI collectionURI) {
		return collections.remove(collectionURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}

	/**
	 * Returns the list of <code>Collection</code> objects owned by this object.
	 * @return the list of <code>Collection</code> objects owned by this object
	 */
	public Set<Collection> getCollections() {
		Set<Collection> collections = new HashSet<>();
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
	 */
	public void setCollections(List<Collection> collections) {
		clearCollections();
		for (Collection collection : collections) {
			addCollection(collection);
		}
	}
	
	/**
	 * Create a new {@link Model} object.
	 * @return the created {@link Model} object.
	 */
	public Model createModel(String displayId, String version, URI source, URI language, URI framework) {
		validateCreationData(displayId, version);
		Model model = createModel(createCompliantURI(defaultURIprefix, TopLevelTypes.model, displayId, version),
				source, language, framework);
		model.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.model, displayId, ""));
		model.setDisplayId(displayId);
		model.setVersion(version);
		return model;
	}

	/**
	 * Create a new {@link Model} object.
	 * @return {@link Model} object.
	 */
	public Model createModel(URI identity, URI source, URI language, URI framework) {
		Model newModel = new Model(identity, source, language, framework);
		addModel(newModel);
		return newModel;
	}

	/**
	 * Appends the specified <code>model</code> to the end of the list of models.
	 */
	public void addModel(Model newModel) {
		addTopLevel(newModel, models, "model",
                collections, componentDefinitions, genericTopLevels, moduleDefinitions, sequences);
	}

	/**
	 * Removes the object matching the specified URI from the list of models if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Model removeModel(URI modelURI) {
		return models.remove(modelURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Model getModel(URI modelURI) {
		return models.get(modelURI);
	}

	/**
	 * Returns the list of <code>Model</code> objects owned by this object.
	 * @return the list of <code>Model</code> objects owned by this object
	 */
	public Set<Model> getModels() {
		//		return (List<Model>) models.values();
		Set<Model> models = new HashSet<>();
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
	 */
	public void setModels(List<Model> models) {
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	}

	/**
	 * Create a new {@link ComponentDefinition} object.
	 * @return {@link ComponentDefinition} object.
	 */
	public ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) {
		//ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types, roles);
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types);
		addComponentDefinition(newComponentDefinition);
		return newComponentDefinition;
	}

	/**
	 * Create a new {@link ComponentDefinition} object with the default URI prefix.
	 * @return {@code true} if the {@code newComponentDefinition} is successfully added, {@code false} otherwise.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types) {
		validateCreationData(displayId, version);
		ComponentDefinition cd = createComponentDefinition(createCompliantURI(defaultURIprefix, TopLevelTypes.componentDefinition,
				displayId, version), types);
		cd.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.componentDefinition, displayId,""));
		cd.setDisplayId(displayId);
		cd.setVersion(version);
		return cd;
	}

	/**
	 * Appends the specified element to the end of the list of component definitions.
	 */
	public void addComponentDefinition(ComponentDefinition newComponentDefinition) {
		addTopLevel(newComponentDefinition, componentDefinitions, "componentDefinition",
                collections, genericTopLevels, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the object matching the specified URI from the list of component definitions if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition removeComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.remove(componentDefinitionURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of component definitions if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}

	/**
	 * Returns the list of <code>ComponentDefinition</code> objects owned by this object.
	 * @return the list of <code>ComponentDefinition</code> objects owned by this object
	 */
	public Set<ComponentDefinition> getComponentDefinitions() {
		//		return (List<Component>) components.values();
		Set<ComponentDefinition> components = new HashSet<>();
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
	 */
	public void setComponentDefinitions(List<ComponentDefinition> componentDefinitions) {
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @return {@link Sequence} object.
	 */
	public Sequence createSequence(URI identity, String elements, URI encoding) {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @return the created Sequence object.
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) {
		validateCreationData(displayId, version);
		Sequence s = createSequence(createCompliantURI(defaultURIprefix, TopLevelTypes.sequence, displayId, version), 
				elements, encoding);
		s.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.sequence, displayId, ""));
		s.setDisplayId(displayId);
		s.setVersion(version);
		return s;
	}
	
//	/**
// 	 * Create a copy of the given top-level object, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
//	 * {@link Sequence}, or {@link TopLevel} with the given version, and add it to its corresponding top-level objects list.
//	 * @param toplevel
//	 * @param newURIprefix
//	 * @return the created {@link TopLevel} object
//	 */
//	public TopLevel createCopyWithURIprefix(TopLevel toplevel, String newURIprefix) {
//		String olddisplayId = extractDisplayId(((Collection) toplevel).getIdentity(), 0);
//		String oldVersion = extractVersion(toplevel.getIdentity());
//		return createCopy(toplevel, newURIprefix, olddisplayId, oldVersion);
//	}
//
//	/**
//	 * Create a copy of the given top-level object, i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
//	 * {@link Sequence}, or {@link TopLevel} with the given version, and add it to its corresponding top-level objects list.
//	 * @param toplevel
//	 * @param newVersion
//	 * @return {@link TopLevel} object
//	 */
//	public TopLevel createCopyWithVersion(TopLevel toplevel, String newVersion) {
//		String oldURIprefix = extractURIprefix(((Collection) toplevel).getIdentity());
//		String olddisplayId = extractDisplayId(((Collection) toplevel).getIdentity(), 0);	
//		return createCopy(toplevel, oldURIprefix, olddisplayId, newVersion);
//	}
//	
//	/**
//	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
//	 * {@link Sequence}, or {@link GenericTopLevel} with the given display ID, and add it to its corresponding top-level objects list.
//	 * @param toplevel
//	 * @param newDisplayId
//	 * @return {@link TopLevel} object
//	 */
//	public TopLevel createCopyWithDisplayId(TopLevel toplevel, String newDisplayId) {
//		String oldURIprefix = extractURIprefix(toplevel.getIdentity());
//		String oldVersion = extractVersion(toplevel.getIdentity());
//		return createCopy(toplevel, oldURIprefix, 
//				newDisplayId, oldVersion);
//	}
//	
//	/**
//	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
//	 * {@link Sequence}, or {@link GenericTopLevel} with the given URIprefix and display ID, and add it to its corresponding top-level objects list.
//	 * @param toplevel
//	 * @param newDisplayId
//	 * @return {@link TopLevel} object
//	 */
//	public TopLevel createCopyWithPersistentId(TopLevel toplevel, String newURIprefix, String newDisplayId) {
//		String oldVersion = extractVersion(toplevel.getIdentity());
//		return createCopy(toplevel, newURIprefix, 
//				newDisplayId, oldVersion);
//	}

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
	 * This method is ONLY valid for compliant URIs.
 	 * Create a copy of the given top-level object, which is i.e.{@link Collection}, {@link ComponentDefinition}, {@link Model}, {@link ModuleDefinition},
	 * {@link Sequence}, or {@link GenericTopLevel} with the given URIprefix, display ID, and version. Then add it to its corresponding top-level objects list.
	 * @return the copied {@link TopLevel} object
	 */
	public Identified createCopy(Identified toplevel, String URIprefix, String displayId, String version) {
		if (URIprefix == null) {
			URIprefix = extractURIprefix(toplevel.getIdentity());
		}
		if (displayId == null) {
			displayId = extractDisplayId(toplevel.getIdentity());
		}
		if (version == null) {
			version = extractVersion(toplevel.getIdentity());
		}
		if (toplevel instanceof Collection) {			
			Collection newCollection = ((Collection) toplevel).copy(URIprefix, displayId, version);
			addCollection(newCollection);
			return newCollection;
		}
		else if (toplevel instanceof ComponentDefinition) {
			ComponentDefinition newComponentDefinition = ((ComponentDefinition) toplevel).copy(URIprefix, displayId, version);
			addComponentDefinition(newComponentDefinition);
			return newComponentDefinition;
		}
		else if (toplevel instanceof Model) {
			Model newModel = ((Model) toplevel).copy(URIprefix, displayId, version);			
			addModel(newModel);
			return newModel;
		}
		else if (toplevel instanceof ModuleDefinition) {
			ModuleDefinition newModuleDefinition = ((ModuleDefinition) toplevel).copy(URIprefix, displayId, version);
			addModuleDefinition(newModuleDefinition);
			return newModuleDefinition;
		}
		else if (toplevel instanceof Sequence) {
			Sequence newSequence = ((Sequence) toplevel).copy(URIprefix, displayId, version);
			addSequence(newSequence);
			return newSequence;
		}
		else if (toplevel instanceof GenericTopLevel) {
			GenericTopLevel newGenericTopLevel = ((GenericTopLevel) toplevel).copy(URIprefix, displayId, version);
			addGenericTopLevel(newGenericTopLevel);
			return newGenericTopLevel;
		}
		else {
			throw new IllegalArgumentException("Unable to copy " + toplevel.getIdentity());
		}
		
	}

	/**
	 * Appends the specified <code>sequence</code> to the end of the list of sequences.
	 */
	public void addSequence(Sequence newSequence) {
		addTopLevel(newSequence, sequences, "sequence",
                collections, componentDefinitions, genericTopLevels, models, moduleDefinitions);
	}

	/**
	 * Removes the object matching the specified URI from the list of structures if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Sequence removeSequence(URI sequenceURI) {
		return sequences.remove(sequenceURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}

	/**
	 * Returns the list of <code>Structure</code> objects owned by this object.
	 * @return the list of <code>Structure</code> objects owned by this object
	 */
	public Set<Sequence> getSequences() {
		//		return (List<Structure>) structures.values();
		Set<Sequence> structures = new HashSet<>();
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
	 */
	public void setSequences(List<Sequence> sequences) {
		clearSequences();
		for (Sequence sequence : sequences) {
			addSequence(sequence);
		}
	}
	
	/**
	 * Create a new {@link GenericTopLevel} object.
	 * @return the created {@link GenericTopLevel} object.
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) {
		validateCreationData(displayId, version);
		GenericTopLevel g = createGenericTopLevel(createCompliantURI(defaultURIprefix, TopLevelTypes.genericTopLevel, displayId, version), rdfType);
		g.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevelTypes.genericTopLevel, displayId, ""));
		g.setDisplayId(displayId);
		g.setVersion(version);
		return g;
	}

	/**
	 * Create a new {@link GenericTopLevel} object.
	 * @return {@link GenericTopLevel} object.
	 */
	public GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) {
		GenericTopLevel newGenericTopLevel = new GenericTopLevel(identity,rdfType);
		addGenericTopLevel(newGenericTopLevel);
		return newGenericTopLevel;
	}

	/**
	 * Appends the specified {@code TopLevel} object to the end of the list of topLevels.
	 */
	public void addGenericTopLevel(GenericTopLevel newGenericTopLevel) {
		addTopLevel(newGenericTopLevel, genericTopLevels, "genericTopLevel",
                collections, componentDefinitions, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the object matching the specified URI from the list of topLevels if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel removeGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.remove(topLevelURI);
	}

	/**
	 * Returns the object matching the specified URI from the list of structuralConstraints if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the list of <code>GenericTopLevel</code> objects owned by this object.
	 * @return the list of <code>GenericTopLevel</code> objects owned by this object
	 */
	public Set<GenericTopLevel> getGenericTopLevels() {
		//		return (List<GenericTopLevel>) topLevels.values();
		Set<GenericTopLevel> topLevels = new HashSet<>();
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
	 */
	public void setGenericTopLevels(List<GenericTopLevel> topLevels) {
		clearGenericTopLevels();
		for (GenericTopLevel topLevel : topLevels) {
			addGenericTopLevel(topLevel);
		}
	}
	
	public TopLevel getTopLevel(URI topLevelURI) {
		TopLevel topLevel = collections.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		topLevel = moduleDefinitions.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		topLevel = models.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		topLevel = componentDefinitions.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		topLevel = sequences.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		topLevel = genericTopLevels.get(topLevelURI);
		if (topLevel!=null) {
			return topLevel;
		}
		return null;
	}

	/**
	 * Adds a namespace URI and its prefix. 
	 * 
	 * @param nameSpaceURI The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 */
	public void addNamespaceBinding(URI nameSpaceURI, String prefix) {
		nameSpaces.put(nameSpaceURI, NamespaceBinding(nameSpaceURI.toString(), prefix));
	}
	
	public void addNamespaceBinding(NamespaceBinding namespaceBinding) {
		nameSpaces.put(URI.create(namespaceBinding.getNamespaceURI()), namespaceBinding);
	}

	/**
	 * Gets the namespace bindings for the document
	 * @return A list of {@link NamespaceBinding}
	 */
	public List<NamespaceBinding> getNameSpaceBindings() {
		List<NamespaceBinding> bindings = new ArrayList<>();
		bindings.addAll(this.nameSpaces.values());
		return bindings;
	}
	
	/**
	 * Removes the object matching the specified URI from the list of nameSpaces if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public NamespaceBinding removeNamespaceBinding(URI nameSpaceURI) {
		return nameSpaces.remove(nameSpaceURI);
	}
	
	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
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
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public NamespaceBinding getNameSpaceBinding(URI nameSpaceURI) {
		return nameSpaces.get(nameSpaceURI);
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

	private void validateCreationData(String displayId, String version) {
		validateIdVersion(displayId, version);
		if (defaultURIprefix == null) {
			throw new IllegalStateException("The defaultURIprefix is not set. Please set it to a non-null value");
		}
	}

	@SafeVarargs
	private final <TL extends TopLevel> void addTopLevel(TL newTopLevel, Map<URI, TL> instancesMap, String typeName, Map<URI, ? extends Identified> ... maps) {
		if (newTopLevel.checkDescendantsURIcompliance()) {
			URI persistentId = URI.create(extractPersistentId(newTopLevel.getIdentity()));
			if (keyExistsInAnyMap(persistentId, maps))
				throw new IllegalArgumentException(
						"Instance for identity `" + newTopLevel.identity +
								"' and persistent identity `" + persistentId + "' exists for a non-" + typeName);
		   	if (instancesMap.containsKey(newTopLevel.getIdentity()))
				throw new IllegalArgumentException(
						"Instance for identity `" + newTopLevel.identity +
								"' and persistent identity `" + persistentId + "' already exists for a " + typeName);

			instancesMap.put(newTopLevel.getIdentity(), newTopLevel);
			Identified latest = instancesMap.get(persistentId);
			if (latest == null) {
				instancesMap.put(persistentId, newTopLevel);
			}
			else {
				if (isFirstVersionNewer(
						extractVersion(newTopLevel.getIdentity()),
						extractVersion(latest.getIdentity()))){
					instancesMap.put(persistentId, newTopLevel);
				}
			}
		}
		else { // Only check if sequence's URI exists in all maps.
			if (keyExistsInAnyMap(newTopLevel.getIdentity()))
				throw new IllegalArgumentException(
						"Instance for identity `" + newTopLevel.identity + "' exists for a non-" + typeName);
			if (instancesMap.containsKey(newTopLevel.getIdentity()))
				throw new IllegalArgumentException(
						"Instance for identity `" + newTopLevel.identity + "' exists for a " + typeName);
			instancesMap.put(newTopLevel.getIdentity(), newTopLevel);
		}
		newTopLevel.setSBOLDocument(this);
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
			throw new IllegalArgumentException(
					"Unable to set default URI prefix to non-compliant value `" + defaultURIprefix + "'");
		}
	}
	
	/**
	 * Returns the default URI prefix.
	 * @return the default URI prefix.
	 */
	public String getDefaultURIprefix() {
		return defaultURIprefix;
	}

	/** 
	 * Returns true if the complete flag is set.
	 * @return true if the complete flag is set.
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Sets the complete flag which when true indicates the SBOL Document is complete and any URIs that 
	 * cannot be dereferenced to a valid object cause an exception to be thrown.
	 * @param complete
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}
