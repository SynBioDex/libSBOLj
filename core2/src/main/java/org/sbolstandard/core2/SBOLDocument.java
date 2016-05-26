package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractPersistentId;
import static org.sbolstandard.core2.URIcompliance.extractURIprefix;
import static org.sbolstandard.core2.URIcompliance.extractVersion;
import static org.sbolstandard.core2.URIcompliance.isURIprefixCompliant;
import static org.sbolstandard.core2.URIcompliance.keyExistsInAnyMap;
import static org.sbolstandard.core2.Version.isFirstVersionNewer;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * Represents the SBOL document where all top-level instances can be created and manipulated.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class SBOLDocument {

	private HashMap<URI, GenericTopLevel> genericTopLevels;
	private HashMap<URI, Collection> collections;
	private HashMap<URI, ComponentDefinition> componentDefinitions;
	private HashMap<URI, Model> models;
	private HashMap<URI, ModuleDefinition> moduleDefinitions;
	private HashMap<URI, Sequence> sequences;
	private HashMap<String, NamespaceBinding> nameSpaces;
	private Set<String> prefixes;
	private String defaultURIprefix;
	private boolean complete = false;
	private boolean compliant = true;
	private boolean typesInURIs = false;
	private boolean createDefaults = false;
	
	/**
	 * Constant representing TURTLE file format
	 */
	public static final String TURTLE = "TURTLE";
	/**
	 * Constant representing JSON file format
	 */
	public static final String JSON = "JSON";
	/**
	 * Constant representing the format of an SBOL version 1.1 output file as being RDF format
	 */
	public static final String RDFV1 = "RDFV1";
	/**
	 * Constant representing RDF file format
	 */
	public static final String RDF = "RDF";
	/**
	 * Constant representing FASTA file format
	 */
	public static final String FASTAformat = "FASTA";
	/**
	 * Constant representing GenBank file format
	 */
	public static final String GENBANK = "GENBANK";

	/**
	 * Creates a new SBOLDocument instance with one empty list for the namespaces and one for each top-level instance,
	 * and then adds the following namespaces: {@link Sbol2Terms#sbol2}, {@link Sbol1Terms#rdf}, {@link Sbol2Terms#dc},
	 * and {@link Sbol2Terms#prov}.
	 */
	public SBOLDocument() {
		genericTopLevels = new HashMap<>();
		collections = new HashMap<>();
		componentDefinitions = new HashMap<>();
		models = new HashMap<>();
		moduleDefinitions = new HashMap<>();
		sequences = new HashMap<>();
		nameSpaces = new HashMap<>();
		nameSpaces.put(Sbol2Terms.sbol2.getPrefix(), Sbol2Terms.sbol2);
		nameSpaces.put(Sbol1Terms.rdf.getPrefix(), Sbol1Terms.rdf);
		nameSpaces.put(Sbol2Terms.dc.getPrefix(), Sbol2Terms.dc);
		nameSpaces.put(Sbol2Terms.prov.getPrefix(), Sbol2Terms.prov);
		prefixes = new HashSet<>();
	}

	/**
	 * Creates a module definition, and then adds it to this SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} with the default URI
	 * prefix of this SBOL document, the given module definition display ID, and an empty version string.
	 * 
	 * @param displayId the display ID of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createModuleDefinition(String, String, String)} . 
	 */
	public ModuleDefinition createModuleDefinition(String displayId) throws SBOLValidationException {
		return createModuleDefinition(defaultURIprefix,displayId,"");
	}


	/**
	 * Creates a module definition, and then adds it to this SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} with the default URI
	 * prefix of this SBOL document, the given module definition display ID and version.
	 * 
	 * @param displayId the display ID of the module definition to be created
	 * @param version the version of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createModuleDefinition(String, String, String)} .
	 */ 
	public ModuleDefinition createModuleDefinition(String displayId, String version) throws SBOLValidationException {
		return createModuleDefinition(defaultURIprefix,displayId,version);
	}

	/**
	 * Creates a module definition, and then adds it to this SBOL document's list of module definitions.
	 * <p>
	 * This method first creates a compliant URI for the module definition to be created. It starts with the 
	 * given URI prefix, followed by the given display ID, and ends with the given version. 
	 * 
	 * @param URIprefix the URI prefix used to construct the compliant URI for the module definition to be created
	 * @param displayId the display ID of the module definition to be created
	 * @param version the version of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10201, 10202, 10204, 10206, 10220.
	 */
	public ModuleDefinition createModuleDefinition(String URIprefix, String displayId, String version) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		ModuleDefinition md = createModuleDefinition(createCompliantURI(URIprefix, TopLevel.MODULE_DEFINITION, displayId, version, typesInURIs));
		md.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.MODULE_DEFINITION, displayId, "", typesInURIs));
		md.setDisplayId(displayId);
		md.setVersion(version);
		return md;
	}

	/**
	 * @param identity a given identifier for this object
	 * @return the new module definition
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 10201, 10202, 10220.
	 */
	ModuleDefinition createModuleDefinition(URI identity) throws SBOLValidationException {
		ModuleDefinition newModule = new ModuleDefinition(identity);
		addModuleDefinition(newModule);
		return newModule;
	}

	/**
	 * Appends the specified {@code moduleDefinition} object to the end of the list of module definitions.
	 *
	 * @param moduleDefinition the ModuleDefinition to be added
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link #addTopLevel(TopLevel, Map, String, Map...)}.
	 */
	void addModuleDefinition(ModuleDefinition moduleDefinition) throws SBOLValidationException {
		addTopLevel(moduleDefinition, moduleDefinitions, "moduleDefinition",
				collections, componentDefinitions, genericTopLevels, models, sequences);
		for (FunctionalComponent functionalComponent : moduleDefinition.getFunctionalComponents()) {
			functionalComponent.setSBOLDocument(this);
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (Module module : moduleDefinition.getModules()) {
			module.setSBOLDocument(this);
			module.setModuleDefinition(moduleDefinition);
			for (MapsTo mapsTo : module.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (Interaction interaction : moduleDefinition.getInteractions()) {
			interaction.setSBOLDocument(this);
			interaction.setModuleDefinition(moduleDefinition);
			for (Participation participation : interaction.getParticipations()) {
				participation.setSBOLDocument(this);
				participation.setModuleDefinition(moduleDefinition);
			}
		}
	}

	/**
	 * Removes the given {@code moduleDefinition} from this SBOLDocument object's list of ModuleDefinition instances.
	 *
	 * @param moduleDefinition The moduleDefinition to be removed
	 * @return {@code true} if the given {@code moduleDefinition} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean removeModuleDefinition(ModuleDefinition moduleDefinition) throws SBOLValidationException {
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				for (Module m : md.getModules()) {
					if (m.getDefinitionURI().equals(moduleDefinition.getIdentity())) {
						throw new SBOLValidationException("sbol-11703");
					}
				}
			}
		}
		return removeTopLevel(moduleDefinition,moduleDefinitions);
	}
	
	/**
	 * Returns the module definition matching the given display ID and version.
	 * <p>
	 * A compliant URI for the module definition to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#MODULE_DEFINITION}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the module
	 * definition in this SBOL document.
	 *
	 * @param displayId the display ID of the module definition to be retrieved
	 * @param version the given version of the module definition to be retrieved
	 * @return the matching module definition if present, or {@code null} otherwise
	 */
	public ModuleDefinition getModuleDefinition(String displayId,String version) {
		try {
			return moduleDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.MODULE_DEFINITION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the module definition matching the given identity URI.
	 * 
	 * @param moduleDefinitionURI the identity URI of the module definition to be retrieved
	 * @return the matching module definition if present, or {@code null} otherwise
	 */
	public ModuleDefinition getModuleDefinition(URI moduleDefinitionURI) {
		return moduleDefinitions.get(moduleDefinitionURI);
	}

	/**
	 * Returns the set of module definitions owned by this SBOL document.
	 *
	 * @return the set of module definitions owned by this SBOL document
	 */
	public Set<ModuleDefinition> getModuleDefinitions() {
		Set<ModuleDefinition> moduleDefinitions = new HashSet<>();
		moduleDefinitions.addAll(this.moduleDefinitions.values());
		return moduleDefinitions;
	}

	/**
	 * Removes all entries in the list of module definitions owned by this SBOL document.
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeModuleDefinition(ModuleDefinition)} to iteratively remove
	 * each module definition.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeModuleDefinition(ModuleDefinition)}.
	 */
	public void clearModuleDefinitions() throws SBOLValidationException {
		Object[] valueSetArray = moduleDefinitions.values().toArray();
		for (Object moduleDefinition : valueSetArray) {
			removeModuleDefinition((ModuleDefinition)moduleDefinition);
		}
	}

	/**
	 * Clears the existing list <code>modules</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void setModuleDefinitions(Set<ModuleDefinition> moduleDefinitions) throws SBOLValidationException {
		clearModuleDefinitions();
		for (ModuleDefinition module : moduleDefinitions) {
			addModuleDefinition(module);
		}
	}

	/**
	 * Create a new collection by calling the constructor {@link Collection#Collection(URI)}, and then
	 * adds it to the list of collections to this SBOL document. 
	 * 
	 * @return the created collection
	 * @throws SBOLValidationException
	 */
	Collection createCollection(URI identity) throws SBOLValidationException {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}

	/**
	 * Creates a collection first, and then adds to this SBOL document's list of collections.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} with the default URI prefix
	 * for this SOBL document, the given display ID of the collection to be created, and an empty version
	 * string. 
	 *
	 * @param displayId the display ID of the collection to be created
	 * @return the created collection 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred 
	 * in {@link #createCollection(String, String, String)}.
	 */
	public Collection createCollection(String displayId) throws SBOLValidationException {
		return createCollection(defaultURIprefix,displayId,"");
	}

	/**
	 * Creates a collection first, and then adds to this SBOL document's list of collections.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} with the default URI prefix
	 * for this SOBL document, the given display ID and version of the collection to be created.
	 * string. 
	 *
	 * @param displayId the display ID of the collection to be created
	 * @param version the version of the collection to be created
	 * @return the created collection 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred 
	 * in {@link #createCollection(String, String, String)}.
	 */
	public Collection createCollection(String displayId, String version) throws SBOLValidationException {
		return createCollection(defaultURIprefix,displayId,version);
	}

	/**
	 * Creates a collection first, and then adds to this SBOL document's list of collections.
	 * <p>
	 * This method creates a compliant URI for the collection to be created first. It starts with 
	 * the given URI prefix after its been successfully validated, followed by the given display ID, 
	 * and ends with the given version.
	 *  
	 * @param URIprefix the URI prefix for the collection to be created
	 * @param displayId the display ID of the collection to be created
	 * @param version the version of the collection to be created
	 * @return the created collection 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10201, 10204, 10206.
	 */
	public Collection createCollection(String URIprefix, String displayId, String version) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		Collection c = createCollection(createCompliantURI(URIprefix, TopLevel.COLLECTION, displayId, version, typesInURIs));
		c.setDisplayId(displayId);
		c.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.COLLECTION, displayId, "", typesInURIs));
		c.setVersion(version);
		return c;
	}

	/**
	 * Adds the given collection to this SBOL document's list of collections.
	 *
	 * @param collection the collection object to be added
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #addTopLevel(TopLevel, Map, String, Map...)}
	 */
	void addCollection(Collection collection) throws SBOLValidationException {
		addTopLevel(collection, collections, "collection",
				componentDefinitions, genericTopLevels, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code collection} from this SBOL document's list of collections.
	 *
	 * @param collection the given collection object to be removed
	 * @return {@code true} if the given {@code collection} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean removeCollection(Collection collection) throws SBOLValidationException {
		return removeTopLevel(collection,collections);
	}

	/**
	 * Returns the collection matching the given display ID and version.
	 * <p>
	 * A compliant URI for the collection to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#COLLECTION}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the collection
	 * in this SBOL document.
	 *
	 * @param displayId the display ID of the collection to be retrieved
	 * @param version the given version of the collection to be retrieved
	 * @return the matching collection if present, or {@code null} otherwise
	 */
	public Collection getCollection(String displayId,String version) {
		try {
			return collections.get(createCompliantURI(defaultURIprefix,TopLevel.COLLECTION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}
	
	/**
	 * Returns the collection matching the given identity URI.
	 *
	 * @param collectionURI the identity URI of the collection to be retrieved
	 * @return the matching collection if present, or {@code null} otherwise
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}

	/**
	 * Returns the set of collections owned by this SBOL document.
	 *
	 * @return the set of collections owned by this SBOL document
	 */
	public Set<Collection> getCollections() {
		Set<Collection> collections = new HashSet<>();
		collections.addAll(this.collections.values());
		return collections;
	}

	/**
	 * Removes all entries in the list of collections owned by this SBOL document. 
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeCollection(Collection)} to iteratively remove each collection.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeCollection(Collection)}.
	 */
	public void clearCollections() throws SBOLValidationException {
		Object[] valueSetArray = collections.values().toArray();
		for (Object collection : valueSetArray) {
			removeCollection((Collection)collection);
		}
	}

	/**
	 * Clears the existing list <code>collections</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void setCollections(Set<Collection> collections) throws SBOLValidationException {
		clearCollections();
		for (Collection collection : collections) {
			addCollection(collection);
		}
	}

	/**
	 * Creates a model, and then adds it to this SBOL document's list of models.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} with this SBOL document's
	 * default URI prefix, an empty version string, and all given arguments.
	 * 
	 * @param displayId the display ID of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createModel(String, String, String, URI, URI, URI)};
	 */
	public Model createModel(String displayId, URI source, URI language, URI framework) throws SBOLValidationException {
		return createModel(defaultURIprefix,displayId,"",source,language,framework);
	}

	/**
	 * Creates a model, and then adds it to this SBOL document's list of models.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} with this SBOL document's
	 * default URI prefix, and all given arguments.
	 * 
	 * @param displayId the display ID of the model to be created
	 * @param version the version of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createModel(String, String, String, URI, URI, URI)};
	 * 
	 */
	public Model createModel(String displayId, String version, URI source, URI language, URI framework) throws SBOLValidationException {
		return createModel(defaultURIprefix,displayId,version,source,language,framework);
	}

	/**
	 * Creates a model, and then adds it to this SBOL document's list of models.
	 * <p>
	 * This method first creates a compliant URI for the model to be created. It starts with the 
	 * given URI prefix, followed by the given display ID, and ends with the given version. 
	 *
	 * @param URIprefix the URI prefix used to construct the compliant URI for the model to be created
	 * @param displayId the display ID of the model to be created
	 * @param version the version of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10201, 10202, 10204, 10206, 10220,  
	 * 10303, 10304, 10305, 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11701, 11801, 11901, 12001, 12301.
	 */
	public Model createModel(String URIprefix, String displayId, String version, URI source, URI language, URI framework) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		Model model = createModel(createCompliantURI(URIprefix, TopLevel.MODEL, displayId, version, typesInURIs),
				source, language, framework);
		model.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.MODEL, displayId, "", typesInURIs));
		model.setDisplayId(displayId);
		model.setVersion(version);
		return model;
	}

	/**
	 * @param identity a given identifier for this object
	 * @param version The given version for this object
	 * @param source location of the actual content of the model
	 * @param language the language in which the model is implemented
	 * @param framework the framework in which the model is implemented
	 * @return the new model
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in either of the following
	 * constructor or method:
	 * <ul>
	 * <li>{@link Model#Model(URI, URI, URI, URI)}, or</li>
	 * <li>{@link #addModel(Model)}.</li>
	 * </ul>
	 */
	Model createModel(URI identity, URI source, URI language, URI framework) throws SBOLValidationException {
		Model newModel = new Model(identity, source, language, framework);
		addModel(newModel);
		return newModel;
	}

	/**
	 *
	 * @param model The model to be added to the document
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link #addTopLevel(TopLevel, Map, String, Map...)}.
	 */
	void addModel(Model model) throws SBOLValidationException {
		addTopLevel(model, models, "model",
				collections, componentDefinitions, genericTopLevels, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code model} from this SBOL document's list of Model instances.
	 *
	 * @param model the given model to be removed
	 * @return {@code true} if the given {@code model} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean removeModel(Model model) throws SBOLValidationException {
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				if (md.containsModel(model.getIdentity())) {
					throw new SBOLValidationException("sbol-11608", md);
				}
			}
		}
		return removeTopLevel(model,models);
	}

	/**
 	 * Returns the model matching the given display ID and version.
	 * <p>
	 * A compliant URI for the model to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#MODEL}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the model
	 * in this SBOL document.
	 *
	 * @param displayId the display ID of the model to be retrieved
	 * @param version the given version of the model to be retrieved
	 * @return the matching model if present, or {@code null} otherwise
	 */
	public Model getModel(String displayId,String version) {
		try {
			return models.get(createCompliantURI(defaultURIprefix,TopLevel.MODEL,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the model matching the given identity URI.
	 *
	 * @param modelURI the identity URI of the model to be retrieved
	 * @return the matching model if present, or {@code null} otherwise
	 */
	public Model getModel(URI modelURI) {
		return models.get(modelURI);
	}

	/**
	 * Returns the set of models owned by this SBOL document.
	 *
	 * @return the set of models owned by this SBOL document
	 */
	public Set<Model> getModels() {
		//		return (List<Model>) models.values();
		Set<Model> models = new HashSet<>();
		models.addAll(this.models.values());
		return models;
	}

	/**
	 * Removes all entries in the list of models owned by this SBOL document.
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeModel(Model)} to iteratively remove each model.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeModel(Model)}.
	 */
	public void clearModels() throws SBOLValidationException {
		Object[] valueSetArray = models.values().toArray();
		for (Object model : valueSetArray) {
			removeModel((Model)model);
		}
	}

	/**
	 * Clears the existing list <code>models</code>, then appends all of the elements in the specified model to the end of this list.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void setModels(Set<Model> models) throws SBOLValidationException {
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	}

	/**
	 * @param identity 
	 * @param types 
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in the following constructor
	 * or method:
	 * <ul>
	 * <li>{@link ComponentDefinition#ComponentDefinition(URI, Set)}, or</li>
	 * <li>{@link #addComponentDefinition(ComponentDefinition)}.</li>
	 * </ul> 
	 */
	ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) throws SBOLValidationException {
		//ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types, roles);
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types);
		addComponentDefinition(newComponentDefinition);
		return newComponentDefinition;
	}

	/**
	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, Set)} with the default URI
	 * prefix of this SBOL document, the given component definition display ID, and an empty version string.
	 * given types. 
	 *
	 * @param displayId the display ID of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createComponentDefinition(String, String, String, Set)}.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, Set<URI> types) throws SBOLValidationException {
		return createComponentDefinition(defaultURIprefix,displayId,"",types);
	}

	/**
	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
 	 * <p>
 	 * This method first creates an empty set of types, and then adds to this set the given type. It then calls
 	 * {@link #createComponentDefinition(String, String, String, Set)} with the given display ID, and version, 
 	 * and the created types.	
	 *
	 * @param displayId the display ID of the component definition to be created
	 * @param type the type to be added to the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link #createComponentDefinition(String, String, String, Set)}.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(defaultURIprefix,displayId,"",types);
	}

	/**
	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, Set)} with the given 
	 * component definition display ID, version, and the given types. 
	 *
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createComponentDefinition(String, String, String, Set)}.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types) throws SBOLValidationException {
		return createComponentDefinition(defaultURIprefix,displayId,version,types);
	}

	/**
	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
 	 * <p>
 	 * This method first creates an empty set of types, and then adds to this set the given type. It then calls
 	 * {@link #createComponentDefinition(String, String, String, Set)} with the default URI prefix of this SBOL document,
 	 * display ID, and version, 
 	 * and the created types.	
	 *
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param type the type to be added to the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createComponentDefinition(String, String, String, Set)}.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(defaultURIprefix,displayId,version,types);
	}

	/**
	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
	 * <p>
 	 * This method creates a compliant URI for the component definition to be created first. It starts with 
	 * the given URI prefix after its been successfully validated, followed by the given display ID, 
	 * and ends with the given version.
	 *
	 * @param URIprefix the URI prefix used to construct the compliant URI for the component definition to be created
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10201, 10202, 10204, 10206, 10220, 10303, 10304, 10305, 10401, 10501, 10502, 10503, 
	 * 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 */
	public ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, Set<URI> types) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		ComponentDefinition cd = createComponentDefinition(createCompliantURI(URIprefix, TopLevel.COMPONENT_DEFINITION,
				displayId, version, typesInURIs), types);
		cd.setDisplayId(displayId);
		cd.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.COMPONENT_DEFINITION, displayId,"", typesInURIs));
		cd.setVersion(version);
		return cd;
	}

	/**
 	 * Creates a component definition, and then adds it to this SBOL document's list of component definitions.
 	 * <p>
 	 * This method first creates an empty set of types, and then adds to this set the given type. It then calls
 	 * {@link #createComponentDefinition(String, String, String, Set)} with the given URI prefix, display ID, and version, 
 	 * and the created types.	
	 *
	 * @param URIprefix the URI prefix used to construct the compliant URI for the component definition to be created
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param type the type to be added to the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createComponentDefinition(String, String, String, Set)}.
	 */
	public ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(URIprefix,displayId,version,types);
	}

	/**
	 * Adds the given component definition to this SBOL document's list of component definitions.
	 *
	 * @param componentDefinition the component definition to be added
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #addTopLevel(TopLevel, Map, String, Map...)}.
	 */
	void addComponentDefinition(ComponentDefinition componentDefinition) throws SBOLValidationException {
		addTopLevel(componentDefinition, componentDefinitions, "componentDefinition",
				collections, genericTopLevels, models, moduleDefinitions, sequences);
		for (Component component : componentDefinition.getComponents()) {
			component.setSBOLDocument(this);
			for (MapsTo mapsTo : component.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (SequenceAnnotation sa : componentDefinition.getSequenceAnnotations()) {
			sa.setSBOLDocument(this);
			sa.setComponentDefinition(componentDefinition);
			for (Location location : sa.getLocations()) {
				location.setSBOLDocument(this);
			}
		}
		for (SequenceConstraint sc : componentDefinition.getSequenceConstraints()) {
			sc.setSBOLDocument(this);
			sc.setComponentDefinition(componentDefinition);
		}
	}

	/**
	 * Removes the given {@code componentDefinition} from this SBOL document's list of component definitions.
	 *
	 * @param componentDefinition The ComponentDefinition to be removed
	 * @return {@code true} if the given {@code componentDefinition} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean removeComponentDefinition(ComponentDefinition componentDefinition) throws SBOLValidationException {
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				for (Component c : cd.getComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLValidationException("sbol-10604", c);
					}
				}
			}
			for (ModuleDefinition md : moduleDefinitions.values()) {
				for (FunctionalComponent c : md.getFunctionalComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLValidationException("sbol-10604", c);
					}
				}
			}
		}
		return removeTopLevel(componentDefinition,componentDefinitions);
	}

	/**
	 * Returns the component definition matching the given display ID and version.
	 * <p>
	 * A compliant URI for the component definition to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#COMPONENT_DEFINITION}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the component
	 * definition in this SBOL document.
	 *
	 * @param displayId the display ID of the component definition to be retrieved
	 * @param version the given version of the component definition to be retrieved
	 * @return the matching component definition if present, or {@code null} otherwise
	 */
	public ComponentDefinition getComponentDefinition(String displayId,String version) {
		try {
			return componentDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.COMPONENT_DEFINITION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the component definition matching the given identity URI.
	 * 
	 * @param componentDefinitionURI the identity URI of the component definition to be retrieved
	 * @return the matching component definition if present, or {@code null} otherwise
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}

	/**
	 * Returns the set of component definitions owned by this SBOL document.
	 *
	 * @return the set of component definitions owned by this SBOL document
	 */
	public Set<ComponentDefinition> getComponentDefinitions() {
		Set<ComponentDefinition> components = new HashSet<>();
		components.addAll(this.componentDefinitions.values());
		return components;
	}

	/**
	 * Returns the set of root component definitions owned by this SBOL document. 
	 * A root component definition is a component definition that is not referenced by a child component
	 * of another component definition in the same SBOL document
	 *  
	 * @return the set of root component definitions
	 */
	public Set<ComponentDefinition> getRootComponentDefinitions() {
		Set<ComponentDefinition> components = getComponentDefinitions();
		for (ComponentDefinition componentDefinition : getComponentDefinitions()) {
			for (Component component : componentDefinition.getComponents()) {
				ComponentDefinition childDefinition = component.getDefinition();
				if (childDefinition != null && components.contains(childDefinition)) {
					components.remove(childDefinition);
				}
			}
		}
		return components;
	}

	/**
	 * Removes all entries in the list of component definitions owned by this SBOL document. 
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeComponentDefinition(ComponentDefinition)} to iteratively
	 * remove each component definition.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeComponentDefinition(ComponentDefinition)}.
	 */
	public void clearComponentDefinitions() throws SBOLValidationException {
		Object[] valueSetArray = componentDefinitions.values().toArray();
		for (Object componentDefinition : valueSetArray) {
			removeComponentDefinition((ComponentDefinition)componentDefinition);
		}
	}

	/**
	 * @param componentDefinitions The given set of ComponentDefinitions to be added
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	void setComponentDefinitions(Set<ComponentDefinition> componentDefinitions) throws SBOLValidationException {
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}


	/**
	 * @param identity a given identifier for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in either of the 
	 * following constructor or method:
	 * <ul>
	 * <li>{@link Sequence#Sequence(URI, String, URI)}, or</li>
	 * <li>{@link #addSequence(Sequence)}.</li>
	 * </ul>
	 */
	Sequence createSequence(URI identity, String elements, URI encoding) throws SBOLValidationException {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to this SBOL document's
	 * list of sequences.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} create a sequence
	 * with this SBOL document's default URI prefix, the given display ID, an empty version string, the
	 * given elements, and encoding.
	 * 
  	 * @param displayId the display ID of the sequence to be created 
	 * @param elements the elements property of the sequence to be created
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence 
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createSequence(String, String, String, String, URI)}.
	 */
	public Sequence createSequence(String displayId, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,"",elements,encoding);
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to this SBOL document's
	 * list of sequences.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to create a sequence
	 * with this SBOL document's default URI prefix, the given display ID, version, elements, and encoding.
	 * 
 	 * @param displayId the display ID of the sequence to be created 
	 * @param version the version of the sequence to be created
	 * @param elements the elements property of the sequence to be created
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence 
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link #createSequence(String, String, String, String, URI)}.
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,version,elements,encoding);
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to this SBOL document's
	 * list of sequences.
	 * <p>
	 * This method first creates a compliant URI for the sequence to be created. It starts with the 
	 * given URI prefix, followed by the given display ID, and ends with the given version. 
	 *
	 * @param URIprefix the given URI prefix that is used to create a compliant URI for the sequence to be created
	 * @param displayId the display ID of the sequence to be created 
	 * @param version the version of the sequence to be created
	 * @param elements the elements property of the sequence to be created
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10201, 10204, 10206, 10402, 10403, 10405.
	 */
	public Sequence createSequence(String URIprefix, String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		Sequence s = createSequence(createCompliantURI(URIprefix, TopLevel.SEQUENCE, displayId, version, typesInURIs),
				elements, encoding);
		s.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.SEQUENCE, displayId, "", typesInURIs));
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
	 * Creates an identical copy of the given top-level, and then adds the created top-level to the corresponding
	 * list of top-levels in this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} with the given top-level instance,
	 * and {@code null} for other arguments.
	 *
	 * @param topLevel the top-level to be copied from
	 * @return the copied top-level instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #createCopy(TopLevel, String, String, String)}.
	 */
	public TopLevel createCopy(TopLevel topLevel) throws SBOLValidationException {
		return createCopy(topLevel,null,null,null);
	}

	/**	
	 * Creates a copy of the given top-level with the given display ID, and then adds the created top-level to the corresponding
	 * list of top-levels in this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} with the given top-level instance,
	 * the default URI prefix for this SBOL document, the given display ID, and the empty version string. 
	 * 
	 * @param topLevel the top-level to be copied from
	 * @param displayId the display ID of the created copy
	 * @return the copied top-level instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createCopy(TopLevel, String, String, String)}.
	 *
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId) throws SBOLValidationException {
		return createCopy(topLevel,defaultURIprefix,displayId,"");
	}

	/**
	 * Renames the given TopLevel instance with this SBOL document's {@code defaultURIprefix}
	 * the given arguments, and an empty version string, and then adds it to the
	 * corresponding top-level list owned by this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * If the {@code defaultURIprefix} is {@code null}, then it is extracted from the given
	 * {@code topLevel} instance. If it does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * If either the given {@code displayId} or {@code version}, then the corresponding field
	 * is extracted from the given {@code topLevel} instance. Both extracted fields are required
	 * to be valid and not {@code null}.
	 * <p>
	 * A top-level instance with a compliant URI using the given arguments,
	 * and then its display ID, persistent identity, and version fields are set. This
	 * instance is then added to the corresponding top-level list owned by this SBOL document.
	 *
	 * @param topLevel The topLevel object to be renamed from this SBOLDocument
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public TopLevel rename(TopLevel topLevel, String displayId) throws SBOLValidationException {
		TopLevel renamedTopLevel = createCopy(topLevel,defaultURIprefix,displayId,"");
		removeTopLevel(topLevel);
		return renamedTopLevel;
	}

	/**
	 * Creates a copy of the given top-level with the given display ID and version, 
	 * and then adds the created top-level to the corresponding list of top-levels in this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} with the given top-level instance,
	 * the default URI prefix for this SBOL document, the given display ID, and the empty version string. 
	 * 
	 * @param topLevel the top-level to be copied from
	 * @param displayId the display ID of the created copy
	 * @param version the version of the created copy
	 * @return the copied top-level instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createCopy(TopLevel, String, String, String)}
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId, String version) throws SBOLValidationException {
		return createCopy(topLevel,defaultURIprefix,displayId,version);
	}

	/**
	 * Renames the given TopLevel instance with this SBOL document's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to the
	 * corresponding top-level list owned by this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * If the {@code defaultURIprefix} is {@code null}, then it is extracted from the given
	 * {@code topLevel} instance. If it does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * If either the given {@code displayId} or {@code version}, then the corresponding field
	 * is extracted from the given {@code topLevel} instance. Both extracted fields are required
	 * to be valid and not {@code null}.
	 * <p>
	 * A top-level instance with a compliant URI using the given arguments,
	 * and then its display ID, persistent identity, and version fields are set. This
	 * instance is then added to the corresponding top-level list owned by this SBOL document.
	 *
	 * @param topLevel The topLevel object to be rename from this SBOLDocument
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public TopLevel rename(TopLevel topLevel, String displayId, String version) throws SBOLValidationException {
		TopLevel renamedTopLevel = createCopy(topLevel,defaultURIprefix,displayId,version);
		removeTopLevel(topLevel);
		return renamedTopLevel;
	}

	/**
	 * Creates a copy of the given top-level with the given URI prefix, display ID and version, 
	 * and then adds the created top-level to the corresponding list of top-levels in this SBOL document.
	 * <p>
	 * This method creates a compliant URI for the copied top-level with the given default URI prefix, display ID, and version.
	 * 
	 * @param topLevel the top-level to be copied from
	 * @param URIprefix the URI prefix used to create the compliant URI for the created copy 
	 * @param displayId the display ID of the created copy
	 * @param version the version of the created copy
	 * @return the copied top-level instance
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10201, 10202, 10204, 10206, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405,
	 * 10501, 10503, 10522, 10526, 
	 * 10602, 10604, 10605, 10607, 
	 * 10701, 10801, 10802, 
	 * 10803, 10804, 10805, 10807, 10808, 10809, 10811, 
	 * 10901, 10905,
	 * 11101, 
	 * 11201,
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11608, 11609, 
	 * 11701, 11703, 11704, 11705, 
	 * 11801, 10802, 10803, 10804, 10807, 10808, 10809, 10811,  
	 * 11901, 
	 * 12001, 12002, 12003,  
	 * 12101, 
	 * 12103, 
	 * 12301, 12302.
	 */
//	 * checkURIprefix: 10201
//	 * addTopLevel (topLevel): 10202, 10220.
//	 * Identified.Identified(Identified):
//	 * 10201, 10202, 10204, 10206, 10220, 10303, 10304, 10305, 10401, 10501, 10701, 10801, 10901, 11101, 11201,
//	 * 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12103, 12301.
//	 * Collection.copy: 10201, 10204, 10206, 10303, 10304, 10305, 12103
//	 * ComponentDefinition.copy: 10201, 10202, 10204, 10206, 10303, 10304, 10305, 10503, 10522, 10526, 10602, 10604, 10605, 10607,
//	 * 10802, 10803, 10804, 10805, 10807, 10808, 10809, 10811, 10905, 11402, 11403, 11404, 11405, 11406.
//	 * Model.copy:10201, 10204, 10206, 10303, 10304, 10305, 11502, 11504, 11508.
//	 * ModuleDefinition.copy: 10201, 10202, 10204, 10206, 10303, 10304, 10305, 10604, 10802, 10803, 10804, 
//	 * 10807, 10808, 10809, 10811, 11608, 11609, 11703, 11704, 11705, 12002, 12003.
//	 * Sequence.copy: 10201, 10204, 10206, 10303, 10304, 10305, 10402, 10403, 10405.
//	 * GenericTopLevel.copy: 10201, 10204, 10206, 10303, 10304, 10305, 12302
	public TopLevel createCopy(TopLevel topLevel, String URIprefix, String displayId, String version) throws SBOLValidationException {
		// topLevel.isURIcompliant();
		if (URIprefix == null) {
			URIprefix = extractURIprefix(topLevel.getIdentity());
			URIprefix = URIcompliance.checkURIprefix(URIprefix);
		} else {
			URIprefix = URIcompliance.checkURIprefix(URIprefix);
		}
		if (displayId == null) {
			displayId = topLevel.getDisplayId();
		}
		if (version == null) {
			version = topLevel.getVersion();
		}
		//validateIdVersion(displayId,version);
		if (topLevel instanceof Collection) {
			Collection newCollection = ((Collection) topLevel).copy(URIprefix, displayId, version);
			addCollection(newCollection);
			return newCollection;
		}
		else if (topLevel instanceof ComponentDefinition) {
			ComponentDefinition newComponentDefinition = ((ComponentDefinition) topLevel).copy(URIprefix, displayId, version);
			addComponentDefinition(newComponentDefinition);
			return newComponentDefinition;
		}
		else if (topLevel instanceof Model) {
			Model newModel = ((Model) topLevel).copy(URIprefix, displayId, version);
			addModel(newModel);
			return newModel;
		}
		else if (topLevel instanceof ModuleDefinition) {
			ModuleDefinition newModuleDefinition = ((ModuleDefinition) topLevel).copy(URIprefix, displayId, version);
			addModuleDefinition(newModuleDefinition);
			return newModuleDefinition;
		}
		else if (topLevel instanceof Sequence) {
			Sequence newSequence = ((Sequence) topLevel).copy(URIprefix, displayId, version);
			addSequence(newSequence);
			return newSequence;
		}
		else if (topLevel instanceof GenericTopLevel) {
			GenericTopLevel newGenericTopLevel = ((GenericTopLevel) topLevel).copy(URIprefix, displayId, version);
			addGenericTopLevel(newGenericTopLevel);
			return newGenericTopLevel;
		}
		else {
			throw new IllegalArgumentException("Unable to copy " + topLevel.getIdentity());
		}
	}

	/**
	 * Creates an identical copy of the given top-level, including all of its dependencies, 
	 * and stores the copy in a new SBOLDocument instance, which gets returned.
	 *
	 * @param topLevel the top-level to be recursively copied
	 * @return the created SBOLDocument containing the copied top-level
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10201, 10202, 10204, 10206, 10220, 10303, 10304, 10305, 10401, 10402, 10403, 10405, 10501, 
	 * 10503, 10522, 10526, 10602, 10604, 10605, 10607, 10701, 10801, 10802, 10803, 10804, 10805, 
	 * 10807, 10808, 10809, 10811, 10901, 10905, 11101, 11201, 11301, 11401, 11402, 11403, 11404, 
	 * 11405, 11406, 11501, 11502, 11504, 11508, 11601, 11608, 11609, 11701, 11703, 11704, 11705, 
	 * 11801, 10802, 10803, 10804, 10807, 10808, 10809, 10811, 11901, 12001, 12002, 12003, 12101, 
	 * 12103, 12301, 12302.
	 */
	public SBOLDocument createRecursiveCopy(TopLevel topLevel) throws SBOLValidationException {
		SBOLDocument document = new SBOLDocument();
		createRecursiveCopy(document,topLevel);
		return document;
	}
	
	/**
	 * @param document
	 * @param topLevel
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #createCopy(TopLevel)}.
	 */
	private void createRecursiveCopy(SBOLDocument document, TopLevel topLevel) throws SBOLValidationException {
		if (document.getTopLevel(topLevel.getIdentity())!=null) return;
		if (topLevel instanceof GenericTopLevel || topLevel instanceof Sequence || topLevel instanceof Model) {
			document.createCopy(topLevel);
		} else if (topLevel instanceof Collection) {
			for (TopLevel member : ((Collection)topLevel).getMembers()) {
				createRecursiveCopy(document,member);
			}
			document.createCopy(topLevel);
		} else if (topLevel instanceof ComponentDefinition) {
			for (Component component : ((ComponentDefinition)topLevel).getComponents()) {
				if (component.getDefinition()!=null) {
					createRecursiveCopy(document,component.getDefinition());
				}
			}
			for (TopLevel sequence : ((ComponentDefinition)topLevel).getSequences()) {
				createRecursiveCopy(document,sequence);
			}
			document.createCopy(topLevel);
		} else if (topLevel instanceof ModuleDefinition) {
			for (FunctionalComponent functionalComponent : ((ModuleDefinition)topLevel).getFunctionalComponents()) {
				if (functionalComponent.getDefinition()!=null) {
					createRecursiveCopy(document,functionalComponent.getDefinition());
				}
			}
			for (Module module : ((ModuleDefinition)topLevel).getModules()) {
				if (module.getDefinition()!=null) {
					createRecursiveCopy(document,module.getDefinition());
				}
			}
			for (Model model : ((ModuleDefinition)topLevel).getModels()) {
				if (document.getModel(model.getIdentity())!=null) continue;
				document.createCopy(model);
			}
			document.createCopy(topLevel);
		}
	}

	/**
	 * Creates a copy of the given TopLevel instance with the given arguments, and then adds it to
	 * the corresponding top-level list owned by this SBOL document.
	 * <p>
	 * If the given {@code URIprefix} is {@code null}, then it is extracted from the given
	 * {@code topLevel} instance. If it does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * If either the given {@code displayId} or {@code version}, then the corresponding field
	 * is extracted from the given {@code topLevel} instance. Both extracted fields are required
	 * to be valid and not {@code null}.
	 * <p>
	 * A top-level instance with a compliant URI is created using the given arguments,
	 * and then its display ID, persistent identity, and version fields are set. This
	 * instance is then added to the corresponding top-level list owned by this SBOL document.
	 *
	 * @param topLevel The topLevel object to be renamed from this SBOLDocument
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public TopLevel rename(TopLevel topLevel, String URIprefix, String displayId, String version) throws SBOLValidationException {
		TopLevel renamedTopLevel = createCopy(topLevel,URIprefix,displayId,version);
		removeTopLevel(topLevel);
		return renamedTopLevel;
	}

	/**
	 * Appends the specified {@code sequence} object to the end of the list of sequences.
	 *
	 * @param sequence The given sequence to be added
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #addTopLevel(TopLevel, Map, String, Map...)}.
	 */
	void addSequence(Sequence sequence) throws SBOLValidationException {
		addTopLevel(sequence, sequences, "sequence",
				collections, componentDefinitions, genericTopLevels, models, moduleDefinitions);
	}

	/**
	 * Removes the given {@code sequence} from this SBOL document's list of Sequence instances.
	 *
	 * @param sequence The given sequence to be removed
	 * @return {@code true} if the given {@code sequence} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public boolean removeSequence(Sequence sequence) throws SBOLValidationException {
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				if (cd.containsSequence(sequence.getIdentity())) {
					throw new SBOLValidationException("sbol-10513", cd);
				}
			}
		}
		return removeTopLevel(sequence,sequences);
	}

	/**
 	 * Returns the sequence matching the given display ID and version.
	 * <p>
	 * A compliant URI for the sequence to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#SEQUENCE}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the sequence
	 * in this SBOL document.
	 *
	 * @param displayId the display ID of the sequence to be retrieved
	 * @param version the given version of the sequence to be retrieved
	 * @return the matching sequence if present, or {@code null} otherwise
	 */
	public Sequence getSequence(String displayId,String version) {
		try {
			return sequences.get(createCompliantURI(defaultURIprefix,TopLevel.SEQUENCE,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the sequence matching the given identity URI.
	 *
	 * @param sequenceURI the identity URI of the sequence to be retrieved
	 * @return the matching sequence if present, or {@code null} otherwise
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}

	/**
	 * Returns the set of sequences owned by this SBOL document.
	 *
	 * @return the set of sequences owned by this SBOL document
	 */
	public Set<Sequence> getSequences() {
		//		return (List<Structure>) structures.values();
		Set<Sequence> structures = new HashSet<>();
		structures.addAll(this.sequences.values());
		return structures;
	}

	/**
	 * Removes all entries in the list of sequences owned by this SBOL document. 
	 * The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeSequence(Sequence)} to iteratively remove each sequence.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeSequence(Sequence)}.
	 */
	public void clearSequences() throws SBOLValidationException {
		Object[] valueSetArray = sequences.values().toArray();
		for (Object sequence : valueSetArray) {
			removeSequence((Sequence)sequence);
		}
	}

	/**
	 * Clears the existing list <code>structures</code>, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	void setSequences(Set<Sequence> sequences) throws SBOLValidationException {
		clearSequences();
		for (Sequence sequence : sequences) {
			addSequence(sequence);
		}
	}

	/**
 	 * Creates a generic top-level, and then adds it to this SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} with the default URI
	 * prefix of this SBOL document, the given component definition display ID, an empty version string, 
	 * and the given RDF type. 
	 *
	 * @param displayId the display ID of the generic top-level to be created
	 * @param rdfType the types of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createGenericTopLevel(String, String, String, QName)}. 
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, QName rdfType) throws SBOLValidationException {
		return createGenericTopLevel(defaultURIprefix,displayId,"",rdfType);
	}

	/**
	 * Creates a generic top-level, and then adds it to this SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} with the default URI
	 * prefix of this SBOL document, the given component definition display ID and version, and the given RDF type. 
	 *
	 * @param displayId the display ID of the generic top-level to be created
	 * @param version the version of the generic top-level to be created
	 * @param rdfType the types of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link #createGenericTopLevel(String, String, String, QName)}. 
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) throws SBOLValidationException {
		return createGenericTopLevel(defaultURIprefix,displayId,version,rdfType);
	}

	/**
	 * Creates a generic top-level, and then adds it to this SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} with the default URI
	 * prefix of this SBOL document, the given component definition display ID and version, and the given RDF type. 
	 * 
	 * @param URIprefix the given URI prefix used to create a compliant URI for the generic top-level to be created 
	 * @param displayId the display ID of the generic top-level to be created
	 * @param version the version of the generic top-level to be created
	 * @param rdfType the types of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rules was violated:
	 * 10201, 10202, 10204, 10206, 10220, 10303, 10304, 10305, 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 
	 * 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12301, 12302. 
	 */
	public GenericTopLevel createGenericTopLevel(String URIprefix, String displayId, String version, QName rdfType) throws SBOLValidationException {
		URIprefix = URIcompliance.checkURIprefix(URIprefix);
		GenericTopLevel g = createGenericTopLevel(createCompliantURI(URIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, version, typesInURIs), rdfType);
		g.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, "", typesInURIs));
		g.setDisplayId(displayId);
		g.setVersion(version);
		return g;
	}

	/**
	 * @param identity a given identifier for this object
	 * @param rdfType a given QName for this annotated GenericTopLevel object
	 * @return the new generic top level
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <ul>
	 * <li>the following SBOL validation rule was violated: 12302;</li>
	 * <li>an SBOL validation rule violation occurred in {@link GenericTopLevel#GenericTopLevel(URI, QName)}; or </li>
	 * <li>an SBOL validation rule violation occurred in {@link #addGenericTopLevel(GenericTopLevel)}.</li>
	 * </ul>
	 */
	GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) throws SBOLValidationException {
		if (rdfType.getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI()) ||
				rdfType.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI())) {
			throw new SBOLValidationException("sbol-12302");
		}
		GenericTopLevel newGenericTopLevel = new GenericTopLevel(identity,rdfType);
		addGenericTopLevel(newGenericTopLevel);
		return newGenericTopLevel;
	}

	/**
	 * Appends the specified {@code genericTopLevel} object to the end of the list of generic top levels.
	 *
	 * @param genericTopLevel Adds the given TopLevel object to this document
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link #addTopLevel(TopLevel, Map, String, Map...)}.
	 */
	void addGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		addTopLevel(genericTopLevel, genericTopLevels, "genericTopLevel",
				collections, componentDefinitions, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code genericTopLevel} from this SBOL document's list of GenericTopLevel instances.
	 *
	 * @param genericTopLevel The given GenericTopLevel object to be removed from this document
	 * @return {@code true} if the given {@code genericTopLevel} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		return removeTopLevel(genericTopLevel,genericTopLevels);
	}

	/**
 	 * Returns the generic top-level matching the given display ID and version.
	 * <p>
	 * A compliant URI for the generic top-level to be retrieved is created first.
	 * It starts with this SBOL document's default URI prefix, optionally followed
	 * by its type {@link TopLevel#GENERIC_TOP_LEVEL}, followed by the given display ID,
	 * and ends with the given version. This compliant URI is used to retrieve the generic top-level
	 * in this SBOL document.
	 *
	 * @param displayId the display ID of the generic top-level to be retrieved
	 * @param version the given version of the generic top-level to be retrieved
	 * @return the matching generic top-level if present, or {@code null} otherwise
	 */
	public GenericTopLevel getGenericTopLevel(String displayId, String version) {
		try {
			return genericTopLevels.get(createCompliantURI(defaultURIprefix,TopLevel.GENERIC_TOP_LEVEL,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
 	 * Returns the generic top-level matching the given identity URI.
	 *
	 * @param topLevelURI the identity URI of the generic top-level to be retrieved
	 * @return the matching generic top-level if present, or {@code null} otherwise
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the set of generic top-levels owned by this SBOL document.
	 *
	 * @return the set of generic top-levels owned by this SBOL document
	 */
	public Set<GenericTopLevel> getGenericTopLevels() {
		//		return (List<GenericTopLevel>) topLevels.values();
		Set<GenericTopLevel> topLevels = new HashSet<>();
		topLevels.addAll(this.genericTopLevels.values());
		return topLevels;
	}

	/**
	 * Removes all entries in the list of generic top-levels
	 * owned by this SBOL document. The list will be empty after this call returns.
	 * <p>
	 * This method calls {@link #removeGenericTopLevel(GenericTopLevel)} to iteratively
	 * remove each generic top-level.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #removeGenericTopLevel(GenericTopLevel)}.
	 */
	public void clearGenericTopLevels() throws SBOLValidationException {
		Object[] valueSetArray = genericTopLevels.values().toArray();
		for (Object genericTopLevel : valueSetArray) {
			removeGenericTopLevel((GenericTopLevel)genericTopLevel);
		}
	}

	/**
	 * Clears the existing list <code>topLevels</code>, then appends all of the elements in the specified topLevels to the end of this list.
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	void setGenericTopLevels(Set<GenericTopLevel> topLevels) throws SBOLValidationException {
		clearGenericTopLevels();
		for (GenericTopLevel topLevel : topLevels) {
			addGenericTopLevel(topLevel);
		}
	}

	/**
	 * Returns the top-level matching the given identity URI.
	 *
	 * @param topLevelURI the identity URI of the top-level to be retrieved
	 * @return the matching top-level if present, or {@code null} otherwise
	 */
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
	 * Returns the set of top-levels owned by this SBOL document.
	 *
	 * @return the set of top-levels owned by this SBOL document
	 */
	public Set<TopLevel> getTopLevels() {
		Set<TopLevel> topLevels = new HashSet<>();
		for (Collection topLevel : collections.values()) {
			topLevels.add(topLevel);
		}
		for (Sequence topLevel : sequences.values()) {
			topLevels.add(topLevel);
		}
		for (Model topLevel : models.values()) {
			topLevels.add(topLevel);
		}
		for (GenericTopLevel topLevel : genericTopLevels.values()) {
			topLevels.add(topLevel);
		}
		for (ComponentDefinition topLevel : componentDefinitions.values()) {
			topLevels.add(topLevel);
		}
		for (ModuleDefinition topLevel : moduleDefinitions.values()) {
			topLevels.add(topLevel);
		}
		return topLevels;
	}

	/**
	 * Returns a set of top-levels that match the given URI for wasDerivedFrom.
	 *  
	 * @param wasDerivedFrom the given wasDerivedFrom  
	 * @return a set of top-levels that match the given URI for wasDerivedFrom
	 */
	public Set<TopLevel> getByWasDerivedFrom(URI wasDerivedFrom) {
		Set<TopLevel> topLevels = new HashSet<>();
		for (Collection topLevel : collections.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		for (Sequence topLevel : sequences.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		for (Model topLevel : models.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		for (GenericTopLevel topLevel : genericTopLevels.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		for (ComponentDefinition topLevel : componentDefinitions.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		for (ModuleDefinition topLevel : moduleDefinitions.values()) {
			if (topLevel.isSetWasDerivedFrom() &&
					topLevel.getWasDerivedFrom().equals(wasDerivedFrom)) {
				topLevels.add(topLevel);
			}
		}
		return topLevels;
	}

	/**
	 * Adds the given namespace URI and its prefix to this SBOL document.
	 *
	 * @param nameSpaceURI the URI used to construct a new namespace
	 * @param prefix the prefix used to construct a new namespace
	 */
	public void addNamespace(URI nameSpaceURI, String prefix) {

		//		if (!URIcompliance.isURIprefixCompliant(nameSpaceURI.toString())) {
		//			throw new SBOLException("Namespace URI " + nameSpaceURI.toString() + " is not valid.");
		//		}
		nameSpaces.put(prefix, NamespaceBinding(nameSpaceURI.toString(), prefix));

	}

	/**
	 * Adds the given namespace QName to this SBOL document.
	 *
	 * @param qName the QName to be added
	 */
	public void addNamespace(QName qName) {

		nameSpaces.put(qName.getPrefix(), NamespaceBinding(qName.getNamespaceURI(),
				qName.getPrefix()));
	}

	void addNamespaceBinding(NamespaceBinding namespaceBinding) {
		nameSpaces.put(namespaceBinding.getPrefix(), namespaceBinding);
	}

	/**
	 *  Removes all non-required namespaces from this SBOL document.
	 *  <p>
	 *  This method calls {@link #removeNamespace(URI)} to iteratively remove each non-required namespace.
	 */
	public void clearNamespaces() {
		Object[] keySetArray = nameSpaces.keySet().toArray();
		for (Object key : keySetArray) {
			if (isRequiredNamespaceBinding((URI)key)) continue;
			removeNamespace((URI) key);
		}
	}

	/**
	 * Returns the QName matching the given namespace URI from this
	 * SBOL document's list of namespaces.
	 *
	 * @param namespaceURI the namespace URI of the QName to be retrieved
	 * @return the matching QName if present, or {@code null} otherwise
	 */
	public QName getNamespace(URI namespaceURI) {
		//if (nameSpaces.get(namespaceURI)==null) return null;
		for (NamespaceBinding namespaceBinding : nameSpaces.values()) {
			if (namespaceBinding.getNamespaceURI().equals(namespaceURI.toString())) {
				return new QName(namespaceBinding.getNamespaceURI(), "", namespaceBinding.getPrefix());
			}
		}
		return null;
	}

	/**
	 * Returns the list of namespace bindings owned by this SBOL document.
	 *
	 * @return the list of namespace bindings owned by this SBOL document.
	 */
	public List<QName> getNamespaces() {
		List<QName> bindings = new ArrayList<>();
		for (NamespaceBinding namespaceBinding : this.nameSpaces.values()) {
			bindings.add(new QName(namespaceBinding.getNamespaceURI(), "", namespaceBinding.getPrefix()));
		}
		return bindings;
	}

	/**
	 * Returns the namespace bindings for this SBOL document
	 * @return A list of {@link NamespaceBinding}
	 */
	List<NamespaceBinding> getNamespaceBindings() {
		List<NamespaceBinding> bindings = new ArrayList<>();
		bindings.addAll(this.nameSpaces.values());
		return bindings;
	}

	/**
	 * Removes the given {@code namespaceURI} from this SBOL document's list of ModuleDefinition instances.
	 *
	 * @param namespaceURI the namespaceURI to be removed from this SBOLDocument
	 */
	public void removeNamespace(URI namespaceURI) {
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

	/**
	 * @param newTopLevel
	 * @param instancesMap
	 * @param typeName
	 * @param maps
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated:
	 * 10202, 10220. 
	 */
	@SafeVarargs
	private final <TL extends TopLevel> void addTopLevel(TL newTopLevel, Map<URI, TL> instancesMap, String typeName, Map<URI, ? extends Identified> ... maps) throws SBOLValidationException {
		boolean childrenCompliant = true;
		try {
			newTopLevel.checkDescendantsURIcompliance();
		} catch (SBOLValidationException e) {
			childrenCompliant = false;
		}
		if (compliant && childrenCompliant) {
			URI persistentId = URI.create(extractPersistentId(newTopLevel.getIdentity()));
			if (keyExistsInAnyMap(persistentId, maps))
				throw new SBOLValidationException("sbol-10220", newTopLevel);
			if (instancesMap.containsKey(newTopLevel.getIdentity()))
				throw new SBOLValidationException("sbol-10202", newTopLevel);
			String prefix = extractURIprefix(persistentId);
			while (prefix!=null) {
				if (keyExistsInAnyMap(URI.create(prefix), maps))
					throw new SBOLValidationException("sbol-10202", newTopLevel);
				if (instancesMap.containsKey(URI.create(prefix)))
					throw new SBOLValidationException("sbol-10202", newTopLevel);
				prefix = extractURIprefix(URI.create(prefix));
			}
			if (prefixes.contains(persistentId.toString())) {
				throw new IllegalArgumentException("Persistent identity `" + persistentId.toString() +
						"' matches URI prefix in document.");
			}
			prefix = extractURIprefix(persistentId);
			while (prefix!=null) {
				prefixes.add(prefix);
				prefix = extractURIprefix(URI.create(prefix));
			}
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
		else { // Only check if URI exists in all maps.
			if (keyExistsInAnyMap(newTopLevel.getIdentity()))
				throw new SBOLValidationException("sbol-10202", newTopLevel);
			if (instancesMap.containsKey(newTopLevel.getIdentity()))
				throw new SBOLValidationException("sbol-10202", newTopLevel);
			instancesMap.put(newTopLevel.getIdentity(), newTopLevel);
			if (newTopLevel.isSetPersistentIdentity()) {
				Identified latest = instancesMap.get(newTopLevel.getPersistentIdentity());
				if (latest == null) {
					instancesMap.put(newTopLevel.getPersistentIdentity(), newTopLevel);
				}
				else {
					if (isFirstVersionNewer(
							extractVersion(newTopLevel.getIdentity()),
							extractVersion(latest.getIdentity()))){
						instancesMap.put(newTopLevel.getPersistentIdentity(), newTopLevel);
					}
				}
			}
		}
		newTopLevel.setSBOLDocument(this);
	}

	/**
	 * Removes the given {@code topLevel} from this SBOL document's list of TopLevel instances.
	 *
	 * @param topLevel The topLevel object to be removed from this SBOLDocument
	 * @param instancesMap map of toplevel instances
	 * @return {@code true} if the given {@code topLevel} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	private final <TL extends TopLevel> boolean removeTopLevel(TopLevel topLevel, Map<URI, TL> instancesMap) throws SBOLValidationException {
		if (complete) {
			for (Collection c : collections.values()) {
				if (c.containsMember(topLevel.getIdentity())) {
					throw new SBOLValidationException("sbol-12103", c);
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

	private void removeTopLevel(TopLevel topLevel) throws SBOLValidationException {
		if (topLevel instanceof GenericTopLevel) removeGenericTopLevel((GenericTopLevel) topLevel);
		else if (topLevel instanceof Collection) removeCollection((Collection) topLevel);
		else if (topLevel instanceof Sequence) removeSequence((Sequence) topLevel);
		else if (topLevel instanceof ComponentDefinition) removeComponentDefinition((ComponentDefinition) topLevel);
		else if (topLevel instanceof Model) removeModel((Model) topLevel);
		else if (topLevel instanceof ModuleDefinition) removeModuleDefinition((ModuleDefinition) topLevel);
	}

	/**
	 * Sets the default URI prefix to the given {@code defaultURIprefix}.
	 *
	 * @param defaultURIprefix the given default URI prefix
	 * @throws IllegalArgumentException noncompliant URI
	 */

	public void setDefaultURIprefix(String defaultURIprefix) throws IllegalArgumentException {
		if (!defaultURIprefix.endsWith("/") && !defaultURIprefix.endsWith(":") && !defaultURIprefix.endsWith("#")) {
			defaultURIprefix += "/";
		}
		if (isURIprefixCompliant(defaultURIprefix)) {
			this.defaultURIprefix = defaultURIprefix;
		}
		else {
			throw new IllegalArgumentException(
					"Unable to set default URI prefix to non-compliant value `" + defaultURIprefix + "'");
		}
	}

	/**
	 * Returns the default URI prefix of this SBOL document.
	 *
	 * @return the default URI prefix of this SBOL document
	 */
	public String getDefaultURIprefix() {
		return defaultURIprefix;
	}

	/**
	 * Returns {@code true} if the complete flag for this SBOL document is set.
	 * A {@code true} value of this flag means that all URI references pointing 
	 * to SBOL instances can be dereferenced in this SBOL document, 
	 * i.e., the referenced instances exist in this SBOL document.
	 *
	 * @return {@code true} if the complete flag is set, {@code false} otherwise
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Sets the complete flag which when true indicates this SBOL document is complete
	 * and any URIs that cannot be dereferenced to a valid object cause an exception to be thrown.
	 *
	 * @param complete A flag indicator which when true indicates this SBOL document is complete
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * Returns {@code true} if all URIs in this SBOL document are compliant.
	 *
	 * @return {@code true} if all URIs in this SBOL document are compliant, or
	 * {@code false} otherwise
	 */
	public boolean isCompliant() {
		return compliant;
	}

	void setCompliant(boolean compliant) {
		this.compliant = compliant;
	}

	/**
	 * Returns {@code true} if types are to be inserted into top-level URIs.
	 *
	 * @return {@code true} if types are to be inserted into top-level URIs, {@code false} otherwise
	 */
	public boolean isTypesInURIs() {
		return typesInURIs;
	}

	/**
	 * Sets the flag to the given {@code typesInURIs} to determine if types are to be inserted into top-level URIs.
	 *
	 * @param typesInURIs A flag to determine if types are to be inserted into top-level URIs
	 */
	public void setTypesInURIs(boolean typesInURIs) {
		this.typesInURIs = typesInURIs;
	}

	/**
	 * Returns {@code true} if default components should be automatically created when not present. See 
	 * {@link ComponentDefinition#createSequenceConstraint(String, RestrictionType, String, String)}, and
	 * {@link Component#createMapsTo(String, RefinementType, String, String)} for examples.
	 *
	 * @return {@code true} if default component instances should be automatically created when not present,
	 * or {@code false} otherwise
	 */
	public boolean isCreateDefaults() {
		return createDefaults;
	}

	/**
	 * Sets the flag to the given {@code createDefaults} to determine if default component instances
	 * should be created when not present.
	 *
	 * @param createDefaults A flag to determine if default component instances should be created when not present.
	 */
	public void setCreateDefaults(boolean createDefaults) {
		this.createDefaults = createDefaults;
	}

	/**
	 * Takes in the given RDF fileName and adds the data read to this SBOLDocument.
	 *
	 * @param fileName a given RDF fileName
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(File)}.
	 * @throws SBOLConversionException if an SBOL conversion exception occurred in {@link #read(File)}.
	 * @throws IOException see {@link IOException}
	 */
	public void read(String fileName) throws SBOLValidationException, IOException, SBOLConversionException {
		read(new File(fileName));
	}

	/**
	 * Takes in the given RDF File and adds the data read to this SBOLDocument.
	 *
	 * @param file a given RDF File
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(InputStream)}.
	 * @throws SBOLConversionException if an SBOL conversion exception occurred in {@link #read(InputStream)}.
	 * @throws IOException see {@link IOException}
	 */
	public void read(File file) throws SBOLValidationException, IOException, SBOLConversionException {
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		read(buffer);
	}

	/**
	 * Takes in a given RDF InputStream and add the data read to this SBOLDocument.
	 *
	 * @param in a given RDF InputStream
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>If {@link SBOLReader#keepGoing} was set to {@code false}, and an SBOL validation rule violation occurred in
	 * any of the following methods:
	 * 	<ul>
	 * 		<li>{@link FASTA#read(SBOLDocument, String, String, String, URI)},</li>
	 * 		<li>{@link GenBank#read(SBOLDocument, String, String)},</li>
	 * 		<li>{@link SBOLReader#readJSON(Reader)}, </li>
	 * 		<li>{@link SBOLReader#readRDF(Reader)}, </li>
	 * 		<li>{@link SBOLReader#readTurtle(Reader)}, </li>
	 * 		<li>{@link SBOLReader#getSBOLVersion(DocumentRoot)}, or</li>
	 * 		<li>{@link SBOLReader#readV1(SBOLDocument, DocumentRoot)}; or</li>
	 * 	</ul></li>
	 * <li>an SBOL validation rule violation occurred in {@link SBOLReader#readTopLevelDocs(SBOLDocument, DocumentRoot)}.</li>
	 * </ul>
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException TODO
	 */
	public void read(InputStream in) throws SBOLValidationException, IOException, SBOLConversionException {
		SBOLReader.read(this,in,SBOLDocument.RDF);
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * file name in RDF format
	 * @param filename the given output file name
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(String filename) throws IOException, SBOLConversionException
	{
		SBOLWriter.write(this, new File(filename));
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * file name in fileType format
	 * @param filename the given output file name
	 * @param fileType the file type to be written out to
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(String filename,String fileType) throws IOException, SBOLConversionException
	{
		SBOLWriter.write(this, new File(filename), fileType);
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * file in RDF format
	 * @param file the given output file in RDF format
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(File file) throws IOException, SBOLConversionException
	{
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		SBOLWriter.write(this, buffer);
		stream.close();
		buffer.close();
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * file in fileType format
	 * @param file the given output file
	 * @param fileType the file type of the given output file
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(File file,String fileType) throws IOException, SBOLConversionException
	{
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		SBOLWriter.write(this, buffer, fileType);
		stream.close();
		buffer.close();
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * stream in RDF format
	 * @param out the given output stream
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(OutputStream out) throws SBOLConversionException
	{
		SBOLWriter.write(this, out);
	}

	/**
	 * Serializes SBOLDocument and outputs the data from the serialization to the given output
	 * stream in fileType format
	 * @param out the given output stream
	 * @param fileType specify what file type for the the given output stream
	 * @throws SBOLConversionException see {@link SBOLConversionException}   
	 * @throws IOException see {@link IOException} 
	 */
	public void write(OutputStream out,String fileType) throws SBOLConversionException, IOException 
	{
		SBOLWriter.write(this, out, fileType);
	}

	@Override
	public String toString() {
		return "SBOLDocument [genericTopLevels=" + genericTopLevels + ", collections="
				+ collections + ", componentDefinitions=" + componentDefinitions + ", models="
				+ models + ", moduleDefinitions=" + moduleDefinitions + ", sequences=" + sequences
				+ ", nameSpaces=" + nameSpaces + ", defaultURIprefix=" + defaultURIprefix
				+ ", complete=" + complete + ", compliant=" + compliant + ", typesInURIs="
				+ typesInURIs + ", createDefaults=" + createDefaults + "]";

	}
}
