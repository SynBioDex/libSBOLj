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
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

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
	 * Creates a module definition with the given arguments, and then adds it to this SBOL document's list of module definitions.
	 * <p>
	 * This method first creates a compliant URI for the module definition to be retrieved. It starts with 
	 * the given URI prefix after its been successfully validated, optionally followed by its type, namely {@link TopLevel#MODULE_DEFINITION},
	 * followed by the given display ID, and ends with the given version.
	 * 
	 * @param URIprefix the URI prefix used to construct the compliant URI for the module definition to be created
	 * @param displayId the display ID of the module definition to be created
	 * @param version the version of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10201, 10202, 10204, 10206, 10220.
	 */
	public ModuleDefinition createModuleDefinition(String URIprefix,String displayId, String version) throws SBOLValidationException {
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
	private ModuleDefinition createModuleDefinition(URI identity) throws SBOLValidationException {
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
	 * Removes the given module definition from this SBOL document's list of module definitions.
	 *
	 * @param moduleDefinition The moduleDefinition to be removed
	 * @return {@code true} if the given {@code moduleDefinition} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated:
	 * 11703, 12103.
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
	 * Returns the module definition matching the given display ID
	 * and version from this SBOLDocument object's list of
	 * module definitions.
	 * <p>
	 * This method first creates a compliant URI for the module definition to be retrieved. 
	 * It starts with the given URI prefix after its been successfully validated, 
	 * optionally followed by its type, namely {@link TopLevel#MODULE_DEFINITION}, 
	 * followed by the given display ID, and ends with the given version. 
	 * This URI is used to look up the module definition in this SBOL document.
	 * 
	 * @param displayId the display ID of the module definition to be retrieved
	 * @param version the version of the module definition to be retrieved
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
	 * Returns the module definition matching the given identity URI from this
	 * SBOL document object's list of module definitions.
	 *
	 * @param moduleURI the give identity URI of the module definition to be retrieved
	 * @return the matching module definition if present, or {@code null} otherwise
	 */
	public ModuleDefinition getModuleDefinition(URI moduleURI) {
		return moduleDefinitions.get(moduleURI);
	}

	/**
	 * Returns the set of module definitions owned by this SBOLDocument object.
	 *
	 * @return the set of module definitions owned by this SBOLDocument object.
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
	/*void setModuleDefinitions(Set<ModuleDefinition> moduleDefinitions) throws SBOLValidationException {
		clearModuleDefinitions();
		for (ModuleDefinition module : moduleDefinitions) {
			addModuleDefinition(module);
		}
	}*/

	/**
	 * Create a new collection by calling the constructor {@link Collection#Collection(URI)}, and then
	 * adds it to the list of collections to this SBOL document. 
	 * 
	 * @return the created collection
	 * @throws SBOLValidationException
	 */
	private Collection createCollection(URI identity) throws SBOLValidationException {
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
	 * Removes the given collection from this SBOL document's list of collections.
	 *
	 * @param collection the given collection to be removed
	 * @return {@code true} if the given collection was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 12103.
	 */
	public boolean removeCollection(Collection collection) throws SBOLValidationException {
		return removeTopLevel(collection,collections);
	}

	/**
	 * Returns the collection matching the given display ID and version from this SBOL document's list of
	 * collections.
	 * <p>
	 * A compliant Collection URI is created first. It starts with the given URI prefix after its been successfully validated,
	 * optionally followed by its type, namely {@link TopLevel#COLLECTION}, followed by the given display ID, 
	 * and ends with the given version. This URI is used to look up the module definition in this SBOL document.
	 *
	 * @param displayId the display ID of the collection to be retrieved
	 * @param version the version of the collection to be retrieved
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
	 * Returns the collection matching the given identity URI from this
	 * SBOL document's list of collections.
	 *
	 * @param collectionURI the given identity URI of the collection to be retrieved
	 * @return the matching collection if present, or {@code null} otherwise
	 *
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}

	/**
	 * Returns the set of {@code Collection} instances owned by this SBOL document.
	 *
	 * @return the set of {@code Collection} instances owned by this SBOL document.
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
	/*void setCollections(Set<Collection> collections) throws SBOLValidationException {
		clearCollections();
		for (Collection collection : collections) {
			addCollection(collection);
		}
	}*/

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
	private Model createModel(URI identity, URI source, URI language, URI framework) throws SBOLValidationException {
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
	 * Removes the given model from this SBOL document's list of models.
	 *
	 * @param model the given model to be removed
	 * @return {@code true} if the given {@code model} was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated:
	 * 11608, 12103.
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
	 * Returns the model matching the given display ID and version from this SBOL document's list of
	 * models.
	 * <p>
	 * This method first creates a compliant URI for the model to be retrieved.
	 * It starts with the given URI prefix after its been successfully validated, 
	 * optionally followed by its type, namely {@link TopLevel#MODEL}, 
	 * followed by the given display ID, and ends with the given version. 
	 * This URI is used to look up the module definition in this SBOL document.
	 *
	 * @param displayId the display ID of the model to be retrieved
	 * @param version the version of the model to be retrieved
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
	 * Returns the model matching the given identity URI from this
	 * SBOL document's list of models.
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
	 * @return the set of models owned by this SBOL document.
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
	/*void setModels(Set<Model> models) throws SBOLValidationException {
		clearModels();
		for (Model model : models) {
			addModel(model);
		}
	} 
	*/

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
	private ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) throws SBOLValidationException {
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
	 * 10201, 10202, 10204, 10206, 10220,
	 * 10502, 10503.  
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
	 * Removes the given component definition from this SBOL document's list of component definitions.
	 *
	 * @param componentDefinition the component definition to be removed
	 * @return {@code true} if the given component definition was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 10604, 12103.
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
	 * Returns the component definition matching the given display ID and version from this SBOL document's list of
	 * component definitions.
	 * <p>
	 * A compliant ComponentDefinition URI is created first.  It starts with this SBOL document's default URI prefix
	 * after its been successfully validated, optionally followed by its type, namely {@link TopLevel#COMPONENT_DEFINITION},
	 * followed by the given display ID, and ends with the given version. This URI is used to look up the module definition in this SBOL document.
	 *
	 * @param displayId the display ID of the component definition to be retrieved
	 * @param version the version of the component definition to be retrieved
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
	 * Returns the component definition matching the given identity URI from this
	 * SBOL document's list of component definitions.
	 *
	 * @param componentDefinitionURI the given identity URI of the component definition to be retrieved
	 * @return the matching component definition if present, or {@code null} otherwise.
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}

	/**
	 * Returns the set of component definitions owned by this SBOL document.
	 *
	 * @return the set of component definitions owned by this SBOL document.
	 */
	public Set<ComponentDefinition> getComponentDefinitions() {
		Set<ComponentDefinition> components = new HashSet<>();
		components.addAll(this.componentDefinitions.values());
		return components;
	}

	/**
	 * Returns the set of root component definitions. A root component definition is a component definition
	 * that is not referenced by a child component.
	 * @return the set of root component definitions
	 */
	public Set<ComponentDefinition> getRootComponentDefinitions() {
		Set<ComponentDefinition> componentDefs = getComponentDefinitions();
		for (ComponentDefinition componentDefinition : getComponentDefinitions()) {
			for (Component component : componentDefinition.getComponents()) {
				ComponentDefinition childDefinition = component.getDefinition();
				if (childDefinition != null && componentDefs.contains(childDefinition)) {
					componentDefs.remove(childDefinition);
				}
			}
		}
		return componentDefs;
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
	/*void setComponentDefinitions(Set<ComponentDefinition> componentDefinitions) throws SBOLValidationException {
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}*/


	/**
	 * @param identity a given identifier for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following constructors or methods:
	 * <ul>
	 * <li>{@link Sequence#Sequence(URI, String, URI)}, or </li>
	 * <li>{@link #addSequence(Sequence)}.</li>
	 * </ul>
	 */
	private Sequence createSequence(URI identity, String elements, URI encoding) throws SBOLValidationException {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}

	/**
	 * Creates a Sequence instance with this SBOL document's {@code defaultURIprefix},
	 * the given arguments, and an empty version string, and then
	 * adds it to this SBOL document's list of Sequence instances.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to do the following
	 * validity checks and create a Sequence instance.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given display ID is not {@code null} and is valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the this SBOL document's {@code defaultURIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given display ID, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#createSequence(String, String, String, String, URI)}.
	 */
	public Sequence createSequence(String displayId, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,"",elements,encoding);
	}

	/**
	 * Creates a Sequence instance with this SBOL document's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOL document's list of Sequence instances.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to do the following
	 * validity checks and create a Sequence instance.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given display ID and version arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the this SBOL document's {@code defaultURIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given display ID, and version.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#createSequence(String, String, String, String, URI)}.
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,version,elements,encoding);
	}

	/**
	 * Creates a Sequence instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Sequence instances.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, display ID,
	 * and version are not {@code null} and are valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given display ID, and version.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if if any of the following SBOL validation rules was violated:
	 * 10201, 10202, 10204, 10206, 10220, 10402, 10403, 10405. 
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

	/**
	 * Creates an identical copy of each top-level element of a document, and then adds the created top-level to the corresponding
	 * list of top-levels in this SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel)} for each top-level instance.
	 *
	 * @param document the document to be copied from
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #createCopy(TopLevel)}.
	 */
	public void createCopy(SBOLDocument document) throws SBOLValidationException {
		for (TopLevel topLevel : document.getTopLevels()) {
			createCopy(topLevel);
		}
	}

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
	 * Renames the given top-level's display ID with the given one.  
	 * <p>
	 * This method first calls {@link #createCopy(TopLevel, String, String, String)} to make a copy of
	 * the given top-level with this SBOL document's default URI prefix, the given display, and an empty
	 * string for version. It then removes the given top-level and then returns the newly-copied top-level. 
	 *
	 * @param topLevel the top-level to be renamed
	 * @param displayId the given display ID to be renamed to  
	 * @return the renamed top-level 
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10513, 10604, 11608, 11703, 12103; or</li>
	 * <li>an SBOL validation rule violation occurred in the following method: 
	 * {@link #createCopy(TopLevel, String, String, String)}.</li>
	 * </ul>
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
	 * Renames the given top-level's display ID and version with the given ones.  
	 * <p>
	 * This method first calls {@link #createCopy(TopLevel, String, String, String)} to make a copy of
	 * the given top-level with this SBOL document's default URI prefix, the given display ID and version.
	 * It then removes the given top-level and then returns the newly-copied top-level. 
	 *
	 * @param topLevel the top-level to be renamed
	 * @param displayId the given display ID to be renamed to  
	 * @param version the given version to be renamed to
	 * @return the renamed top-level 
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10513, 10604, 11608, 11703, 12103; or</li>
	 * <li>an SBOL validation rule violation occurred in the following method: 
	 * {@link #createCopy(TopLevel, String, String, String)}.</li>
	 * </ul>
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
	 * 10501, 10503, 10513, 10522, 10526, 
	 * 10602, 10604, 10605, 10607, 
	 * 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10807, 10808, 10809, 10811, 
	 * 10901, 10905, 
	 * 11101, 11102, 11103, 11104, 
	 * 11201, 11202, 
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11608, 11609, 
	 * 11701, 11703, 11704, 11705, 
	 * 11801, 
	 * 11901, 
	 * 12001, 12002, 12003, 
	 * 12103, 
	 * 12301, 12302.
	 */
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
	 * Creates an identical copy of the given top-level and returns it in 
	 * a new SBOLDocument.
	 *
	 * @param topLevel The topLevel object to be recursively copied from this SBOLDocument
	 * @return the created SBOLDocument with this top-level instance and all its dependencies
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10201, 10202, 10204, 10206, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405, 
	 * 10501, 10503, 10522, 10526, 
	 * 10602, 10604, 10605, 10607, 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10807, 10808, 10809, 10811, 
	 * 10901, 10905, 
	 * 11101, 11201, 11301, 11401, 11402, 11403, 11404, 11405, 11406, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11608, 11609, 
	 * 11701, 11703, 11704, 11705, 
	 * 11801, 10802, 10803, 10804, 10807, 10808, 10809, 10811, 
	 * 11901, 
	 * 12001, 12002, 12003, 
	 * 12101, 12103, 
	 * 12301, 12302.
	 */
	public SBOLDocument createRecursiveCopy(TopLevel topLevel) throws SBOLValidationException {
		SBOLDocument document = new SBOLDocument();
		createRecursiveCopy(document,topLevel);
		return document;
	}
	
	/**
	 * @param document
	 * @param topLevel
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#createCopy(TopLevel)}.
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
	 * Renames the given top-level's URI prefix, display ID, and version with the given ones.  
	 * <p>
	 * This method first calls {@link #createCopy(TopLevel, String, String, String)} to make a copy of
	 * the given top-level with the URI prefix, display ID, and version.
	 * It then removes the given top-level and then returns the newly-copied top-level. 
	 *
	 * @param topLevel the top-level to be renamed
	 * @param URIprefix the given URI prefix to be rename to 
	 * @param displayId the given display ID to be renamed to  
	 * @param version the given version to be renamed to
	 * @return the renamed top-level 
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10513, 10604, 11608, 11703, 12103; or</li>
	 * <li>an SBOL validation rule violation occurred in the following method: 
	 * {@link #createCopy(TopLevel, String, String, String)}.</li>
	 * </ul>
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
	 * Removes the given sequence from this SBOL document's list of sequences.
	 *
	 * @param sequence the given sequence to be removed
	 * @return {@code true} if the given sequence was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 
	 * 10513, 12103. 
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
	 * Returns the sequence matching the given display ID
	 * and version from this SBOL document's list of sequences.
	 * <p>
	 * This method first creates a compliant URI for the sequence to be retrieved. 
	 * It starts with the given URI prefix after its been successfully validated, 
	 * optionally followed by its type, namely {@link TopLevel#SEQUENCE}, followed by the given display ID, 
	 * and ends with the given version. This URI is used to look up the sequence in this SBOL document.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching sequence if present, or {@code null} otherwise.
	 */
	public Sequence getSequence(String displayId,String version) {
		try {
			return sequences.get(createCompliantURI(defaultURIprefix,TopLevel.SEQUENCE,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the sequence matching the given {@code modelURI} from this
	 * SBOL document's list of sequences.
	 *
	 * @param sequenceURI takes the given SequenceURI to retrieve the sequence from this SBOL document
	 * @return the matching sequence if present, or {@code null} otherwise.
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}

	/**
	 * Returns the set of sequences owned by this SBOL document.
	 *
	 * @return the set of sequences owned by this SBOL document.
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
	/*void setSequences(Set<Sequence> sequences) throws SBOLValidationException {
		clearSequences();
		for (Sequence sequence : sequences) {
			addSequence(sequence);
		}
	}*/

	/**
 	 * Creates a generic top-level, and then adds it to this SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} with the default URI
	 * prefix of this SBOL document, the given component definition display ID, an empty version string, 
	 * and the given RDF type. 
	 *
	 * @param displayId the display ID of the generic top-level to be created
	 * @param rdfType the type of the generic top-level to be created
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
	 * @param rdfType the type of the generic top-level to be created
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
	 * @param rdfType the type of the generic top-level to be created
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
	private GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) throws SBOLValidationException {
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
	 * Removes the given generic top-level from this SBOL document's list of generic top-levels.
	 *
	 * @param genericTopLevel the given generic top-level to be removed
	 * @return {@code true} if the given generic top-level was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 12103.
	 */
	public boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		return removeTopLevel(genericTopLevel,genericTopLevels);
	}

	/**
	 * Returns the generic top-level matching the given display ID and version from this SBOL document's list of
	 * generic top-levels.
	 * <p>
	 * A compliant generic top-level URI is created first. It starts with this SBOL document's default URI prefix
	 * after its been successfully validated, optionally followed by its type, namely {@link TopLevel#GENERIC_TOP_LEVEL}, 
	 * followed by the given display ID, and ends with the given version. This URI is used to look up the generic
	 * top-level in this SBOL document.
	 *
	 * @param displayId the display ID of the generic top-level to be retrieved
	 * @param version the version of the generic top-level to be retrieved
	 * @return the matching generic top-level if present, or {@code null} otherwise.
	 */
	public GenericTopLevel getGenericTopLevel(String displayId, String version) {
		try {
			return genericTopLevels.get(createCompliantURI(defaultURIprefix,TopLevel.GENERIC_TOP_LEVEL,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the generic top-level matching the given display identity URI from this SBOL document's list of
	 * generic top-levels.
	 *
	 * @param topLevelURI the identity URI of the top-level to be retrieved
	 * @return the matching generic top-level if present, or {@code null} otherwise.
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the set of generic top-levels owned by this SBOL document.
	 *
	 * @return the set of generic top-levels owned by this SBOL document.
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

//	/**
//	 * Clears the existing list <code>topLevels</code>, then appends all of the elements in the specified topLevels to the end of this list.
//	 * @throws SBOLValidationException see {@link SBOLValidationException} 
//	 */
	/*void setGenericTopLevels(Set<GenericTopLevel> topLevels) throws SBOLValidationException {
		clearGenericTopLevels();
		for (GenericTopLevel topLevel : topLevels) {
			addGenericTopLevel(topLevel);
		}
	}*/

	/**
	 * Returns the top-level matching the given identity URI from this
	 * SBOL document's lists of top-levels.
	 *
	 * @param topLevelURI the identity URI of the top-level to be retrieved
	 * @return the matching top-level if present, or {@code null} otherwise.
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
	 * Returns the set of all top-levels owned by this SBOL document.
	 *
	 * @return the set of all top-level owned by this SBOL document
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
	 * Retrieves a set of top-levels in this SBOL document whose {@code wasDerivedFrom} field matches
	 * the given one.
	 *  
	 * @param wasDerivedFrom the {@code wasDerivedFrom} field of which all matching top-levels to be retrieved
	 * @return a set of top-levels whose with the matching {@code wasDerivedFrom} field
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
			if (isRequiredNamespaceBinding(URI.create((String)key))) continue;
			removeNamespace(URI.create((String)key));
		}
	}

	/**
	 * Returns the QName matching the given namespace URI from this
	 * SBOL document's list of QNames.
	 *
	 * @param namespaceURI the identity URI of the namespace to be retrieved
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
	 * Returns the list of namespaces owned by this SBOL document.
	 *
	 * @return the list of namespaces owned by this SBOL document
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
	 * @return the list of namespace bindings for this SBOL document 
	 */
	List<NamespaceBinding> getNamespaceBindings() {
		List<NamespaceBinding> bindings = new ArrayList<>();
		bindings.addAll(this.nameSpaces.values());
		return bindings;
	}

	/**
	 * Removes the given namespace URI from this SBOL document's list of namespaces.
	 *
	 * @param namespaceURI the namespaceURI to be removed
	 */
	public void removeNamespace(URI namespaceURI) {
		if (isRequiredNamespaceBinding(namespaceURI)) {
			throw new IllegalStateException("Cannot remove required namespace " + namespaceURI.toString());
		}
		nameSpaces.remove(namespaceURI);
	}

//	/**
//	 * Clears the existing list of <code>namespaces</code>, then appends all of the namespaces to the end of this list.
//	 */
	/*void setNameSpaceBindings(List<NamespaceBinding> namespaceBinding) {
		clearNamespaces();
		for (NamespaceBinding namespace : namespaceBinding) {
			addNamespaceBinding(namespace);
		}
	}*/

	private boolean isRequiredNamespaceBinding(URI namespaceURI) {
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
	 * Removes the given top-level from this SBOL document's list of top-levels.
	 *
	 * @param topLevel the top-level to be removed
	 * @param instancesMap map of toplevel instances
	 * @return {@code true} if the given top-level was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 12103.
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

	/**
	 * @param topLevel
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following methods:
	 * <ul>
	 * <li>{@link #removeGenericTopLevel(GenericTopLevel)},</li>
	 * <li>{@link #removeCollection(Collection)},</li>
	 * <li>{@link #removeSequence(Sequence)},</li>
	 * <li>{@link #removeComponentDefinition(ComponentDefinition)},</li>
	 * <li>{@link #removeModel(Model)}, or</li>
	 * <li>{@link #removeModuleDefinition(ModuleDefinition)}.</li>
	 * </ul>
	 */
	private void removeTopLevel(TopLevel topLevel) throws SBOLValidationException {
		if (topLevel instanceof GenericTopLevel) removeGenericTopLevel((GenericTopLevel) topLevel);
		else if (topLevel instanceof Collection) removeCollection((Collection) topLevel);
		else if (topLevel instanceof Sequence) removeSequence((Sequence) topLevel);
		else if (topLevel instanceof ComponentDefinition) removeComponentDefinition((ComponentDefinition) topLevel);
		else if (topLevel instanceof Model) removeModel((Model) topLevel);
		else if (topLevel instanceof ModuleDefinition) removeModuleDefinition((ModuleDefinition) topLevel);
	}

	/**
	 * Sets the default URI prefix of this SBOL document to the given one. This means that any SBOL
	 * instances created subsequently in this document will have the given URI prefix as the beginning
	 * of its compliant identity URI. 
	 *
	 * @param defaultURIprefix the given default URI prefix
	 * @throws IllegalArgumentException if the given URI prefix is not compliant
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
	 * Returns the value of the complete flag for this SBOL document.
	 * <p>
	 * A {@code true} value means that all identity URI references
	 * should be able to dereference to instances in the this document, and a {@code false} value means otherwise.
	 *
	 * @return the value of the complete flag for this SBOL document
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Sets the complete flag to the given value. 
	 * <p>
	 * A {@code true} value indicates this SBOL document is complete:
	 * any identity URIs should be able to dereference to an instance in this document.
	 *
	 * @param complete the given boolean value for the complete flag
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * Returns the value of the compliant flag in this SBOL document.   
	 * <p>
	 * A {@code true} value means that all identity URIs in this SBOL document should be compliant, 
	 * and a {@code false} value means otherwise.
	 *
	 * @return the value of the compliant flag in this SBOL document
	 */
	public boolean isCompliant() {
		return compliant;
	}

	void setCompliant(boolean compliant) {
		this.compliant = compliant;
	}

	/**
	 * Returns the value of the typesInURI flag in this SBOL document. 
	 * <p>
	 * A {@code true} value means that types will be inserted into this document's each top-level's compliant URI when it is created,
	 * and a {@code false} value means otherwise.
	 *
	 * @return the value of the typesInURI flag in this SBOL document
	 */
	public boolean isTypesInURIs() {
		return typesInURIs;
	}

	/**
	 * Sets the typesInURIs flag to the given value. 
	 * <p>
	 * A {@code true} value means that types are inserted into top-level 
	 * identity URIs when they are created.
	 *
	 * @param typesInURIs the given boolean value for the typesInURIs flag 
	 */
	public void setTypesInURIs(boolean typesInURIs) {
		this.typesInURIs = typesInURIs;
	}

	/**
	 * Returns the value of the createDefaults flag in this SBOL document.
	 * <p>
	 * A {@code true} value means that default components and/or functional components instances should 
	 * be created when not present, and a {@code false} value means otherwise.
	 * 
	 * @return the value of the createDefaults flag in this SBOL document.
	 */
	public boolean isCreateDefaults() {
		return createDefaults;
	}

	/**
	 * Sets the createDefaults flag to the given value. A {@code true} value means that default component instances
	 * should be created when not present.
	 *
	 * @param createDefaults the given boolean value for the createDefaults flag
	 */
	public void setCreateDefaults(boolean createDefaults) {
		this.createDefaults = createDefaults;
	}

	/**
	 * Takes in a given RDF file name and adds the data read to this SBOLDocument.
	 *
	 * @param fileName a given RDF file name
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(File)}.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public void read(String fileName) throws SBOLValidationException, IOException, SBOLConversionException {
		read(new File(fileName));
	}

	/**
	 * Takes in a given RDF file and adds the data read to this SBOL document.
	 *
	 * @param file a given RDF file
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(InputStream)}.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public void read(File file) throws SBOLValidationException, IOException, SBOLConversionException {
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		read(buffer);
	}

	/**
	 * Takes in a given RDF input stream and adds the data read to this SBOL document.
	 *
	 * @param in a given RDF input stream
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 
	 * 10201, 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405, 
	 * 10501, 10502, 10503, 10504, 10507, 10508, 10512, 10513, 10519, 10522, 10526, 
	 * 10602, 10603, 10604, 10605, 10606, 10607, 
	 * 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10806, 10807, 10808, 10809, 10810, 10811, 
	 * 10901, 10902, 10904, 10905, 
	 * 11002, 
	 * 11101, 11102, 11103, 11104, 
	 * 11201, 11202, 
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 11407, 11412, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11602, 11604, 11605, 11606, 11607, 11608, 11609, 
	 * 11701, 11702, 11703, 11704, 11705, 11706, 
	 * 11801, 11802, 
	 * 11901, 11902, 11906, 
	 * 12001, 12002, 12003, 12004, 
	 * 12101, 12102, 12103, 
	 * 12301, 12302.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public void read(InputStream in) throws SBOLValidationException, IOException, SBOLConversionException {
		SBOLReader.read(this,in,SBOLDocument.RDF);
	}

	/**
	 * Outputs this SBOL document's data from the RDF/XML serialization to a new file with the given file name.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, File)} by passing this SBOL document, and a 
	 * new file with the given file name.
	 * 
	 * @param filename the given output file name
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(String filename) throws IOException, SBOLConversionException
	{
		SBOLWriter.write(this, new File(filename));
	}

	/**
	 * Outputs this SBOL document's data from serialization in the given serialization format
	 * to a new file with the given file name.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, File, String)} by passing this SBOL document, and a 
	 * new file with the given file name and type.
	 * 
	 * @param filename the given output file name
	 * @param fileType the serialization format
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(String filename,String fileType) throws IOException, SBOLConversionException
	{
		SBOLWriter.write(this, new File(filename), fileType);
	}

	/**
	 * Outputs this SBOL document's data from the RDF/XML serialization to the given file.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, File)}.
	 * 
	 * @param file the given output file
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
	 * Outputs this SBOL document's data from the serialization in the given serialization format 
	 * to the given file.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, OutputStream, String)}.
	 * 
	 * @param file the given output file
	 * @param fileType the given serialization format
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
	 * Outputs this SBOL document's data from the RDF/XML serialization to the given output stream.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, OutputStream)} by passing
	 * this SBOL document and the given output stream.
	 * 
	 * @param out the given output stream
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 */
	public void write(OutputStream out) throws SBOLConversionException
	{
		SBOLWriter.write(this, out);
	}

	/**
	 * Outputs this SBOL document's data from the serialization in the given serialization format 
	 * to the given output stream.
	 * <p>
	 * This method calls {@link SBOLWriter#write(SBOLDocument, OutputStream, String)} by passing
	 * this SBOL document, the given output stream and file type.
	 * 
	 * @param out the given output stream
	 * @param fileType the serialization format
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
