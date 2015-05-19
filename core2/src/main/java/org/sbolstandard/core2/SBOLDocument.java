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
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
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
	private boolean compliant = true;
	private boolean typesInURIs = true;

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
		nameSpaces.put(URI.create(Sbol2Terms.prov.getNamespaceURI()), Sbol2Terms.prov);
	}

	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @return the created {@link ModuleDefinition} object.
	 */
	public ModuleDefinition createModuleDefinition(String displayId, String version) {
		checkReadOnly();
		validateIdentityData(displayId, version);
		ModuleDefinition md = createModuleDefinition(createCompliantURI(defaultURIprefix, TopLevel.MODULE_DEFINITION, 
				displayId, version, typesInURIs));
		md.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.MODULE_DEFINITION, 
				displayId, "", typesInURIs));
		md.setDisplayId(displayId);
		md.setVersion(version);
		return md;
	}
	
	/**
	 * Create a new {@link ModuleDefinition} object.
	 * @return the {@link ModuleDefinition} object.
	 */
	ModuleDefinition createModuleDefinition(URI identity) {
		ModuleDefinition newModule = new ModuleDefinition(identity);
		addModuleDefinition(newModule);
		return newModule;
	}

	/**
	 * Appends the specified {@code ModuleDefinition} object to the end of the list of module definitions.
	 */
	void addModuleDefinition(ModuleDefinition newModuleDefinition) {
		addTopLevel(newModuleDefinition, moduleDefinitions, "moduleDefinition",
                collections, componentDefinitions, genericTopLevels, models, sequences);
	}

	/**
	 * Removes a module definition from the list of module definitions, if present.
	 * @param moduleDefinition object to remove.
	 * @return true if the moduleDefinition is present and removed.
	 */
	public boolean removeModuleDefinition(ModuleDefinition moduleDefinition) {
		checkReadOnly();
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				for (Module m : md.getModules()) {
					if (m.getDefinitionURI().equals(moduleDefinition.getIdentity())) {
						throw new SBOLException("Cannot remove " + moduleDefinition.getIdentity() + 
								" since it is in use.");
					}
				}
			}
		}
		return removeTopLevel(moduleDefinition,moduleDefinitions);
	}

	/**
	 * Returns the object matching the specified displayId/version from the list of model definitions, if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ModuleDefinition getModuleDefinition(String displayId,String version) {
		validateIdentityData(displayId,version);
		return moduleDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.MODULE_DEFINITION,displayId,version, typesInURIs));
	}
	
	/**
	 * Returns the object matching the specified URI from the list of model definitions, if present.
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
		checkReadOnly();
		Object[] valueSetArray = moduleDefinitions.values().toArray();
		for (Object moduleDefinition : valueSetArray) {
			removeModuleDefinition((ModuleDefinition)moduleDefinition);
		}
	}

	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setModuleDefinitions(List<ModuleDefinition> moduleDefinitions) {
		clearModuleDefinitions();
		for (ModuleDefinition module : moduleDefinitions) {
			addModuleDefinition(module);
		}
	}

	/**
	 * Create a new {@link Collection} object.
	 * @return {@link Collection} object.
	 */
	Collection createCollection(URI identity) {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}
	
	/**
	 * Create a new {@link Collection} object with the default URI prefix. 
	 * @return the created {@link Collection} object.
	 */
	public Collection createCollection(String displayId, String version) {
		checkReadOnly();
		validateIdentityData(displayId, version);
		Collection c = createCollection(
				createCompliantURI(defaultURIprefix, TopLevel.COLLECTION, displayId, version, typesInURIs));
		c.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.COLLECTION, displayId, "", typesInURIs));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}

	/**
	 * Appends the specified {@code newCollection} object to the end of the list of collections.
	 */
	void addCollection(Collection collection) {
		addTopLevel(collection, collections, "collection",
                componentDefinitions, genericTopLevels, models, moduleDefinitions, sequences);
	}


	/**
	 * Removes a collection from the list of collections, if present.
	 * @param collection object to remove.
	 * @return true if the collection is present and removed.
	 */
	public boolean removeCollection(Collection collection) {
		checkReadOnly();
		return removeTopLevel(collection,collections);
	}

	/**
	 * Returns the object matching the specified displayId/version from the list of collections, if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Collection getCollection(String displayId,String version) {
		validateIdentityData(displayId,version);
		return collections.get(createCompliantURI(defaultURIprefix,TopLevel.COLLECTION,displayId,version, typesInURIs));
	}	
	
	/**
	 * Returns the object matching the specified URI from the list of collections, if present.
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
		checkReadOnly();
		Object[] valueSetArray = collections.values().toArray();
		for (Object collection : valueSetArray) {
			removeCollection((Collection)collection);
		}
	}

	/**
	 * Clears the existing list <code>collections</code>, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setCollections(List<Collection> collections) {
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
		checkReadOnly();
		validateIdentityData(displayId, version);
		Model model = createModel(createCompliantURI(defaultURIprefix, TopLevel.MODEL, displayId, version, typesInURIs),
				source, language, framework);
		model.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.MODEL, displayId, "", typesInURIs));
		model.setDisplayId(displayId);
		model.setVersion(version);
		return model;
	}

	/**
	 * Create a new {@link Model} object.
	 * @return {@link Model} object.
	 */
	Model createModel(URI identity, URI source, URI language, URI framework) {
		Model newModel = new Model(identity, source, language, framework);
		addModel(newModel);
		return newModel;
	}

	/**
	 * Appends the specified <code>model</code> to the end of the list of models.
	 */
	void addModel(Model newModel) {
		addTopLevel(newModel, models, "model",
                collections, componentDefinitions, genericTopLevels, moduleDefinitions, sequences);
	}

	/**
	 * Removes a model from the list of models, if present.
	 * @param model object to remove.
	 * @return true if the model is present and removed.
	 */
	public boolean removeModel(Model model) {
		checkReadOnly();
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				if (md.containsModel(model.getIdentity())) {
					throw new SBOLException("Cannot remove " + model.getIdentity() + 
								" since it is in use.");
				}
			}
		}
		return removeTopLevel(model,models);
	}
	
	/**
	 * Returns the object matching the specified displayId/version from the list of models, if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Model getModel(String displayId,String version) {
		validateIdentityData(displayId,version);
		return models.get(createCompliantURI(defaultURIprefix,TopLevel.MODEL,displayId,version, typesInURIs));
	}
	
	/**
	 * Returns the object matching the specified URI from the list of models, if present.
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
		checkReadOnly();
		Object[] valueSetArray = models.values().toArray();
		for (Object model : valueSetArray) {
			removeModel((Model)model);
		}
	}

	/**
	 * Clears the existing list <code>models</code>, then appends all of the elements in the specified model to the end of this list.
	 */
	void setModels(List<Model> models) {
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	}

	/**
	 * Create a new {@link ComponentDefinition} object.
	 * @return {@link ComponentDefinition} object.
	 */
	ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) {
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
		checkReadOnly();
		validateIdentityData(displayId, version);
		ComponentDefinition cd = createComponentDefinition(createCompliantURI(defaultURIprefix, TopLevel.COMPONENT_DEFINITION,
				displayId, version, typesInURIs), types);
		cd.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.COMPONENT_DEFINITION, displayId,"", typesInURIs));
		cd.setDisplayId(displayId);
		cd.setVersion(version);
		return cd;
	}

	/**
	 * Appends the specified element to the end of the list of component definitions.
	 */
	void addComponentDefinition(ComponentDefinition newComponentDefinition) {
		addTopLevel(newComponentDefinition, componentDefinitions, "componentDefinition",
                collections, genericTopLevels, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes a component definition from the list of component definitions, if present.
	 * @param componentDefinition object to remove.
	 * @return true if the componentDefinition is present and removed.
	 */
	public boolean removeComponentDefinition(ComponentDefinition componentDefinition) {
		checkReadOnly();
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				for (Component c : cd.getComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLException("Cannot remove " + componentDefinition.getIdentity() + 
								" since it is in use.");
					}
				}
			}
			for (ModuleDefinition cd : moduleDefinitions.values()) {
				for (FunctionalComponent c : cd.getFunctionalComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLException("Cannot remove " + componentDefinition.getIdentity() + 
								" since it is in use.");
					}
				}
			}
		}
		return removeTopLevel(componentDefinition,componentDefinitions);
	}
	
	/**
	 * Returns the object matching the displayId/version from the list of component definitions if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public ComponentDefinition getComponentDefinition(String displayId,String version) {
		validateIdentityData(displayId,version);
		return componentDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.COMPONENT_DEFINITION,displayId,version, typesInURIs));
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
		Object[] valueSetArray = componentDefinitions.values().toArray();
		for (Object componentDefinition : valueSetArray) {
			removeComponentDefinition((ComponentDefinition)componentDefinition);
		}
	}

	/**
	 * Clears the existing list of component definitions, then appends all of the elements in the specified model to the end of this list.
	 */
	void setComponentDefinitions(List<ComponentDefinition> componentDefinitions) {
		checkReadOnly();
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @return {@link Sequence} object.
	 */
	Sequence createSequence(URI identity, String elements, URI encoding) {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}

	/**
	 * Create a new {@link Sequence} object.
	 * @return the created Sequence object.
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) {
		checkReadOnly();
		validateIdentityData(displayId, version);
		Sequence s = createSequence(createCompliantURI(defaultURIprefix, TopLevel.SEQUENCE, displayId, version, typesInURIs), 
				elements, encoding);
		s.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.SEQUENCE, displayId, "", typesInURIs));
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
	public TopLevel createCopy(TopLevel toplevel, String URIprefix, String displayId, String version) {
		checkReadOnly();
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
	void addSequence(Sequence newSequence) {
		addTopLevel(newSequence, sequences, "sequence",
                collections, componentDefinitions, genericTopLevels, models, moduleDefinitions);
	}

	/**
	 * Removes a sequence from the list of sequences, if present.
	 * @param sequence object to remove.
	 * @return true if the sequence is present and removed.
	 */
	public boolean removeSequence(Sequence sequence) {
		checkReadOnly();
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				if (cd.containsSequence(sequence.getIdentity())) {
					throw new SBOLException("Cannot remove " + sequence.getIdentity() + 
							" since it is in use.");
				}
			}
		}
		return removeTopLevel(sequence,sequences);
	}
	
	/**
	 * Returns the object matching the specified displayId/version from the list of sequences, if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public Sequence getSequence(String displayId,String version) {
		validateIdentityData(displayId,version);
		return sequences.get(createCompliantURI(defaultURIprefix,TopLevel.SEQUENCE,displayId,version, typesInURIs));
	}

	/**
	 * Returns the object matching the specified URI from the list of sequences, if present.
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
		checkReadOnly();
		Object[] valueSetArray = sequences.values().toArray();
		for (Object sequence : valueSetArray) {
			removeSequence((Sequence)sequence);
		}
	}

	/**
	 * Clears the existing list <code>structures</code>, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setSequences(List<Sequence> sequences) {
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
		checkReadOnly();
		validateIdentityData(displayId, version);
		GenericTopLevel g = createGenericTopLevel(createCompliantURI(defaultURIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, version, typesInURIs), rdfType);
		g.setPersistentIdentity(createCompliantURI(defaultURIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, "", typesInURIs));
		g.setDisplayId(displayId);
		g.setVersion(version);
		return g;
	}

	/**
	 * Create a new {@link GenericTopLevel} object.
	 * @return {@link GenericTopLevel} object.
	 */
	GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) {
		GenericTopLevel newGenericTopLevel = new GenericTopLevel(identity,rdfType);
		addGenericTopLevel(newGenericTopLevel);
		return newGenericTopLevel;
	}

	/**
	 * Appends the specified {@code TopLevel} object to the end of the list of topLevels.
	 */
	void addGenericTopLevel(GenericTopLevel newGenericTopLevel) {
		addTopLevel(newGenericTopLevel, genericTopLevels, "genericTopLevel",
                collections, componentDefinitions, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes a generic top level from the list of generic top levels, if present.
	 * @param genericTopLevel object to remove.
	 * @return true if the genericTopLevel is present and removed.
	 */
	public boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) {
		checkReadOnly();
		return removeTopLevel(genericTopLevel,genericTopLevels);
	}

	/**
	 * Returns the object matching the specified displayId/version from the list of generic top levels if present.
	 * @return the matching object if present, or <code>null</code> if not present.
	 */
	public GenericTopLevel getGenericTopLevel(String displayId, String version) {
		validateIdentityData(displayId,version);
		return genericTopLevels.get(createCompliantURI(defaultURIprefix,TopLevel.GENERIC_TOP_LEVEL,displayId,version, typesInURIs));
	}

	/**
	 * Returns the object matching the specified URI from the list of generic top levels if present.
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
		checkReadOnly();
		Object[] valueSetArray = genericTopLevels.values().toArray();
		for (Object genericTopLevel : valueSetArray) {
			removeGenericTopLevel((GenericTopLevel)genericTopLevel);
		}
	}

	/**
	 * Clears the existing list <code>topLevels</code>, then appends all of the elements in the specified topLevels to the end of this list.
	 */
	void setGenericTopLevels(List<GenericTopLevel> topLevels) {
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
	 * Adds a namespace URI and its prefix to a SBOL document 
	 * 
	 * @param nameSpaceURI The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 */
	public void addNamespace(URI nameSpaceURI, String prefix) {
		nameSpaces.put(nameSpaceURI, NamespaceBinding(nameSpaceURI.toString(), prefix));
	}
	
	/**
	 * Adds a namespace {@link QName} to a SBOL document
	 * 
	 * @param qName Qualified name ({@link QName}) for a namespace 
	 */
	public void addNamespace(QName qName) {
		nameSpaces.put(URI.create(qName.getNamespaceURI()), NamespaceBinding(qName.getNamespaceURI(), 
				qName.getPrefix()));
	}
	
	void addNamespaceBinding(NamespaceBinding namespaceBinding) {
		nameSpaces.put(URI.create(namespaceBinding.getNamespaceURI()), namespaceBinding);
	}
	
	
	/**
	 *  Removes all non-required namespaces from the SBOL document.
	 */
	public void clearNamespaces() {
		checkReadOnly();
		Object[] keySetArray = nameSpaces.keySet().toArray();
		for (Object key : keySetArray) {
			if (isRequiredNamespaceBinding((URI)key)) continue;
			removeNamespace((URI) key);
		}		
	}

	/**
	 * Returns the namespace matching the specified URI from the list of namespaces if present.
	 * @return the matching namespace {@link QName} if present, or <code>null</code> if not present.
	 */
	public QName getNamespace(URI namespaceURI) {
		if (nameSpaces.get(namespaceURI)==null) return null;
		return new QName(namespaceURI.toString(), "", nameSpaces.get(namespaceURI).getPrefix());
	}
	
	/**
	 * Gets the namespace bindings for the document
	 * @return A list of {@link QName}s
	 */
	public List<QName> getNamespaces() {
		List<QName> bindings = new ArrayList<>();
		for (NamespaceBinding namespaceBinding : this.nameSpaces.values()) {
			bindings.add(new QName(namespaceBinding.getNamespaceURI(), "", namespaceBinding.getPrefix()));
		}
		return bindings;
	}
	
	/**
	 * Gets the namespace bindings for the document
	 * @return A list of {@link NamespaceBinding}
	 */
	List<NamespaceBinding> getNamespaceBindings() {
		List<NamespaceBinding> bindings = new ArrayList<>();
		bindings.addAll(this.nameSpaces.values());
		return bindings;
	}
	
	/**
	 * Removes the object matching the specified URI from the list of nameSpaces, if present and not required.
	 * @param namespaceURI {@link URI} for a namespace 
	 */
	public void removeNamespace(URI namespaceURI) {
		checkReadOnly();
		if (isRequiredNamespaceBinding(namespaceURI)) {
			throw new IllegalStateException("Cannot remove required namespace " + namespaceURI.toString());
		}
		nameSpaces.remove(namespaceURI);
	}
	
	/**
	 * Clears the existing list of <code>namespaces</code>, then appends all of the namespaces to the end of this list.
	 */
	void setNameSpaceBindings(List<NamespaceBinding> namespaceBinding) {
		clearNamespaces();
		for (NamespaceBinding namespace : namespaceBinding) {
			addNamespaceBinding(namespace);
		}
	}
	
	boolean isRequiredNamespaceBinding(URI namespaceURI) {
		if (namespaceURI.toString().equals(Sbol2Terms.sbol2.getNamespaceURI())) return true;
		if (namespaceURI.toString().equals(Sbol2Terms.dc.getNamespaceURI())) return true;
		if (namespaceURI.toString().equals(Sbol2Terms.prov.getNamespaceURI())) return true;
		if (namespaceURI.toString().equals(Sbol1Terms.rdf.getNamespaceURI())) return true;
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

	private void validateIdentityData(String displayId, String version) {
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
	
	private final <TL extends TopLevel> boolean removeTopLevel(TopLevel topLevel, Map<URI, TL> instancesMap) {
		if (complete) {
			for (Collection c : collections.values()) {
				if (c.containsMember(topLevel.getIdentity())) {
					throw new SBOLException("Cannot remove " + topLevel.getIdentity() + 
								" since it is in use.");
				}
			}
		}
		Set<TopLevel> setToRemove = new HashSet<>();
		setToRemove.add(topLevel);
		boolean changed = instancesMap.values().removeAll(setToRemove);
		URI latestVersion = null;
		for (TL tl : instancesMap.values()) {
			if (topLevel.getPersistentIdentity().toString().equals(tl.getPersistentIdentity().toString())) {
				if (latestVersion==null) {
					latestVersion = tl.getIdentity();
				} else if (isFirstVersionNewer(extractVersion(tl.getIdentity()),extractVersion(latestVersion))) {
					latestVersion = tl.getIdentity();
				}
			}
		}
		if (latestVersion != null) {
			instancesMap.put(topLevel.getPersistentIdentity(),instancesMap.get(latestVersion));
		}
		return changed;
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
	
	public boolean isCompliant() {
		return compliant;
	}

	void setCompliant(boolean compliant) {
		this.compliant = compliant;
	}
	
	public boolean isTypesInURIs() {
		return typesInURIs;
	}

	public void setTypesInURIs(boolean typesInURIs) {
		this.typesInURIs = typesInURIs;
	}

	void checkReadOnly() {
		if (!compliant) {
			throw new SBOLException("Cannot modify a non-compliant SBOL document");
		}
	}
}
