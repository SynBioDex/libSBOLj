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
	 * Creates a new SBOLDocument instance with one empty list for the namespaces and for each top-level instance,
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
	 * Creates a ModuleDefinition instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and an empty version string, and then
	 * adds it to this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} to do the following
	 * validity checks and create a ModuleDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A ModuleDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#MODULE_DEFINITION},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ModuleDefinition createModuleDefinition(String displayId) throws SBOLValidationException {
		return createModuleDefinition(defaultURIprefix,displayId,"");
	}

	/**
	 * Creates a ModuleDefinition instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} to do the following
	 * validity checks and create a ModuleDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A ModuleDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#MODULE_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public ModuleDefinition createModuleDefinition(String displayId, String version) throws SBOLValidationException {
		return createModuleDefinition(defaultURIprefix,displayId,version);
	}

	/**
	 * Creates a ModuleDefinition instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of ModuleDefinition instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A ModuleDefinition instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#MODULE_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * Returns the ModuleDefinition instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * ModuleDefinition instances.
	 * <p>
	 * A compliant ModuleDefinition URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#MODULE_DEFINITION}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the ModuleDefinition instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version the version of this object
	 * @return the matching ModuleDefinition instance if present, or {@code null} otherwise.
	 */
	public ModuleDefinition getModuleDefinition(String displayId,String version) {
		try {
			return moduleDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.MODULE_DEFINITION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the ModuleDefinition instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of ModuleDefinition instances.
	 *
	 * @param moduleURI the given module URI from this document
	 * @return the matching ModuleDefinition instance if present, or {@code null} otherwise.
	 */
	public ModuleDefinition getModuleDefinition(URI moduleURI) {
		return moduleDefinitions.get(moduleURI);
	}

	/**
	 * Returns the set of {@code ModuleDefinition} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code ModuleDefinition} instances owned by this SBOLDocument object.
	 */
	public Set<ModuleDefinition> getModuleDefinitions() {
		Set<ModuleDefinition> moduleDefinitions = new HashSet<>();
		moduleDefinitions.addAll(this.moduleDefinitions.values());
		return moduleDefinitions;
	}

	/**
	 * Removes all entries in the list of ModuleDefinition instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Create a new {@link Collection} object.
	 * @return {@link Collection} object.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	Collection createCollection(URI identity) throws SBOLValidationException {
		Collection newCollection = new Collection(identity);
		addCollection(newCollection);
		return newCollection;
	}

	/**
	 * Creates a Collection instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given {@code displayId} argument, and an empty version string, and then
	 * adds it to this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} to do the following
	 * validity checks and create a Collection instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} argument is not {@code null} and is valid.
	 * <p>
	 * A Collection instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COLLECTION},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @return the created Collection instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Collection createCollection(String displayId) throws SBOLValidationException {
		return createCollection(defaultURIprefix,displayId,"");
	}

	/**
	 * Creates a Collection instance with this SBOLDocument object's {@code defaultURIprefix} and
	 * the given arguments, and then adds it to this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} to do the following
	 * validity checks and create a Collection instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code URIprefix}, {@code displayId}, and {@code version} are not
	 * {@code null} and valid.
	 * <p>
	 * A Collection instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COLLECTION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version the version for this object
	 * @return the created Collection instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Collection createCollection(String displayId, String version) throws SBOLValidationException {
		return createCollection(defaultURIprefix,displayId,version);
	}

	/**
	 * Creates a Collection instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Collection instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code URIprefix}, {@code displayId}, and {@code version} are not
	 * {@code null} and valid.
	 * <p>
	 * A Collection instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#COLLECTION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version the version for this object
	 * @return the created Collection instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Appends the specified {@code collection} object to the end of the list of collections.
	 *
	 * @param collection the collection object to be added
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void addCollection(Collection collection) throws SBOLValidationException {
		addTopLevel(collection, collections, "collection",
				componentDefinitions, genericTopLevels, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code collection} from this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param collection the given collection object to be removed
	 * @return {@code true} if the given {@code collection} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public boolean removeCollection(Collection collection) throws SBOLValidationException {
		return removeTopLevel(collection,collections);
	}

	/**
	 * Returns the Collection instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * Collection instances.
	 * <p>
	 * A compliant Collection URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#COLLECTION}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the Collection instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching Collection instance if present, or {@code null} otherwise.
	 */
	public Collection getCollection(String displayId,String version) {
		try {
			return collections.get(createCompliantURI(defaultURIprefix,TopLevel.COLLECTION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the Collection instance matching the given {@code collectionURI} from this
	 * SBOLDocument object's list of Collection instances.
	 *
	 * @param collectionURI the given collectionURI from this document
	 * @return the matching Collection instance if present, or {@code null} otherwise.
	 *
	 */
	public Collection getCollection(URI collectionURI) {
		return collections.get(collectionURI);
	}

	/**
	 * Returns the set of {@code Collection} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code Collection} instances owned by this SBOLDocument object.
	 */
	public Set<Collection> getCollections() {
		Set<Collection> collections = new HashSet<>();
		collections.addAll(this.collections.values());
		return collections;
	}

	/**
	 * Removes all entries in the list of Collection instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Creates a Model instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and an empty version string, and then
	 * adds it to this SBOLDocument object's list of Model instances.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} to do the following
	 * validity checks and create a Model instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A Model instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#MODEL},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param source location of the actual content of the model
	 * @param language the language in which the model is implemented
	 * @param framework the framework in which the model is implemented
	 * @return the created Model instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Model createModel(String displayId, URI source, URI language, URI framework) throws SBOLValidationException {
		return createModel(defaultURIprefix,displayId,"",source,language,framework);
	}

	/**
	 * Creates a Model instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of Model instances.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} to do the following
	 * validity checks and create a Model instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A Model instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#MODEL},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param source location of the actual content of the model
	 * @param language the language in which the model is implemented
	 * @param framework the framework in which the model is implemented
	 * @return the created Model instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public Model createModel(String displayId, String version, URI source, URI language, URI framework) throws SBOLValidationException {
		return createModel(defaultURIprefix,displayId,version,source,language,framework);
	}

	/**
	 * Creates a Model instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Model instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A Model instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#MODEL},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param source location of the actual content of the model
	 * @param language the language in which the model is implemented
	 * @param framework the framework in which the model is implemented
	 * @return the created Model instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	Model createModel(URI identity, URI source, URI language, URI framework) throws SBOLValidationException {
		Model newModel = new Model(identity, source, language, framework);
		addModel(newModel);
		return newModel;
	}

	/**
	 * Appends the specified {@code model} object to the end of the list of models.
	 *
	 * @param model The model to be added to the document
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	void addModel(Model model) throws SBOLValidationException {
		addTopLevel(model, models, "model",
				collections, componentDefinitions, genericTopLevels, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code model} from this SBOLDocument object's list of Model instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * Returns the Model instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * Model instances.
	 * <p>
	 * A compliant Model URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#MODEL}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the Model instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching Model instance if present, or {@code null} otherwise.
	 */
	public Model getModel(String displayId,String version) {
		try {
			return models.get(createCompliantURI(defaultURIprefix,TopLevel.MODEL,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the Model instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of Model instances.
	 *
	 * @param modelURI the modelURI
	 * @return the matching Model instance if present, or {@code null} otherwise.
	 */
	public Model getModel(URI modelURI) {
		return models.get(modelURI);
	}

	/**
	 * Returns the set of {@code Model} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code Model} instances owned by this SBOLDocument object.
	 */
	public Set<Model> getModels() {
		//		return (List<Model>) models.values();
		Set<Model> models = new HashSet<>();
		models.addAll(this.models.values());
		return models;
	}

	/**
	 * Removes all entries in the list of Model instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * @param identity The unique identifier for this object
	 * @param types specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the new component definition
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) throws SBOLValidationException {
		//ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types, roles);
		ComponentDefinition newComponentDefinition = new ComponentDefinition(identity, types);
		addComponentDefinition(newComponentDefinition);
		return newComponentDefinition;
	}

	/**
	 * Creates a ComponentDefinition instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and empty version string, and then
	 * adds it to this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, Set)} to do the following
	 * validity checks and create a ComponentDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param types specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ComponentDefinition createComponentDefinition(String displayId, Set<URI> types) throws SBOLValidationException {
		return createComponentDefinition(defaultURIprefix,displayId,"",types);
	}


	/**
	 * Creates a ComponentDefinition instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and empty version string, and then
	 * adds it to this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, URI)} to do the following
	 * validity checks and create a ComponentDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param type specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ComponentDefinition createComponentDefinition(String displayId, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(defaultURIprefix,displayId,"",types);
	}

	/**
	 * Creates a ComponentDefinition instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, Set)} to do the following
	 * validity checks and create a ComponentDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param types specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types) throws SBOLValidationException {
		return createComponentDefinition(defaultURIprefix,displayId,version,types);
	}

	/**
	 * Creates a ComponentDefinition instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, URI)} to do the following
	 * validity checks and create a ComponentDefinition instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param type specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(defaultURIprefix,displayId,version,types);
	}

	/**
	 * Creates a ComponentDefinition instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of ComponentDefinition instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param types specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Creates a ComponentDefinition instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of ComponentDefinition instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A ComponentDefinition instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#COMPONENT_DEFINITION},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param type specifies the category of biochemical or physical entity using appropriate ontologies
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, URI type) throws SBOLValidationException {
		HashSet<URI> types = new HashSet<URI>();
		types.add(type);
		return createComponentDefinition(URIprefix,displayId,version,types);
	}

	/**
	 * Appends the specified {@code componentDefinition} object to the end of the list of component definitions.
	 *
	 * @param componentDefinition The ComponentDefinition to be added
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Removes the given {@code componentDefinition} from this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * Returns the ComponentDefinition instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * ComponentDefinition instances.
	 * <p>
	 * A compliant ComponentDefinition URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#COMPONENT_DEFINITION}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the ComponentDefinition instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching ComponentDefinition instance if present, or {@code null} otherwise.
	 */
	public ComponentDefinition getComponentDefinition(String displayId,String version) {
		try {
			return componentDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.COMPONENT_DEFINITION,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the ComponentDefinition instance matching the given {@code componentDefinitionURI} from this
	 * SBOLDocument object's list of ComponentDefinition instances.
	 *
	 * @param componentDefinitionURI The ComponentDefinition URI
	 * @return the matching ComponentDefinition instance if present, or {@code null} otherwise.
	 */
	public ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return componentDefinitions.get(componentDefinitionURI);
	}

	/**
	 * Returns the set of {@code ComponentDefinition} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code ComponentDefinition} instances owned by this SBOLDocument object.
	 */
	public Set<ComponentDefinition> getComponentDefinitions() {
		Set<ComponentDefinition> components = new HashSet<>();
		components.addAll(this.componentDefinitions.values());
		return components;
	}

	/**
	 * Returns the set of root ComponentDefinitions.
	 * @return the set of root ComponentDefinitions.
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
	 * Removes all entries in the list of ComponentDefinition instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
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
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	Sequence createSequence(URI identity, String elements, URI encoding) throws SBOLValidationException {
		Sequence newSequence = new Sequence(identity, elements, encoding);
		addSequence(newSequence);
		return newSequence;
	}

	/**
	 * Creates a Sequence instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and an empty version string, and then
	 * adds it to this SBOLDocument object's list of Sequence instances.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to do the following
	 * validity checks and create a Sequence instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId an intermediate between name and identity that is machine-readable
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Sequence createSequence(String displayId, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,"",elements,encoding);
	}

	/**
	 * Creates a Sequence instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of Sequence instances.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to do the following
	 * validity checks and create a Sequence instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		return createSequence(defaultURIprefix,displayId,version,elements,encoding);
	}

	/**
	 * Creates a Sequence instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Sequence instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#SEQUENCE},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param elements characters that represents the constituents of a biological or chemical molecule (i.e. nucleotide bases of a molecule of DNA, the amino acid residues of a protein, or the atoms and chemical bonds of a small molecule)
	 * @param encoding Indicate how the elements property of a Sequence must be formed and interpreted
	 * @return the created Sequence instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
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
	 * Creates an identical copy of the given TopLevel instance.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
	 *
	 * @param topLevel The topLevel object to be copied from this SBOLDocument
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public TopLevel createCopy(TopLevel topLevel) throws SBOLValidationException {
		return createCopy(topLevel,null,null,null);
	}

	/**
	 * Creates a copy of the given TopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
	 * the given arguments, and an empty version string, and then adds it to the
	 * corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
	 *
	 * @param topLevel The topLevel object to be copied from this SBOLDocument
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId) throws SBOLValidationException {
		return createCopy(topLevel,defaultURIprefix,displayId,"");
	}

	/**
	 * Renames the given TopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
	 * the given arguments, and an empty version string, and then adds it to the
	 * corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
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
	 * Creates a copy of the given TopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to the
	 * corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
	 *
	 * @param topLevel The topLevel object to be copied from this SBOLDocument
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId, String version) throws SBOLValidationException {
		return createCopy(topLevel,defaultURIprefix,displayId,version);
	}

	/**
	 * Renames the given TopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to the
	 * corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
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
	 * Creates a copy of the given TopLevel instance with the given arguments, and then adds it to
	 * the corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
	 *
	 * @param topLevel The topLevel object to be copied from this SBOLDocument
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the created top-level instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Creates an identical copy of the given TopLevel instance and all its dependencies and returns them in 
	 * a new SBOLDocument.
	 *
	 * @param topLevel The topLevel object to be recursively copied from this SBOLDocument
	 * @return the created SBOLDocument with this top-level instance and all its dependencies
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public SBOLDocument createRecursiveCopy(TopLevel topLevel) throws SBOLValidationException {
		SBOLDocument document = new SBOLDocument();
		createRecursiveCopy(document,topLevel);
		return document;
	}
	
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
	 * the corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * instance is then added to the corresponding top-level list owned by this SBOLDocument object.
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
	 * Appends the specified {@code sequence} object to the end of the list of sequencess.
	 *
	 * @param sequence The given sequence to be added
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	void addSequence(Sequence sequence) throws SBOLValidationException {
		addTopLevel(sequence, sequences, "sequence",
				collections, componentDefinitions, genericTopLevels, models, moduleDefinitions);
	}

	/**
	 * Removes the given {@code sequence} from this SBOLDocument object's list of Sequence instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * Returns the Sequence instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * Sequence instances.
	 * <p>
	 * A compliant Sequence URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#SEQUENCE}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the Sequence instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching Sequence instance if present, or {@code null} otherwise.
	 */
	public Sequence getSequence(String displayId,String version) {
		try {
			return sequences.get(createCompliantURI(defaultURIprefix,TopLevel.SEQUENCE,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the Sequence instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of Sequence instances.
	 *
	 * @param sequenceURI takes the given SequenceURI to retrieve the sequence from this SBOLDocument object
	 * @return the matching Sequence instance if present, or {@code null} otherwise.
	 */
	public Sequence getSequence(URI sequenceURI) {
		return sequences.get(sequenceURI);
	}

	/**
	 * Returns the set of {@code Sequence} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code Sequence} instances owned by this SBOLDocument object.
	 */
	public Set<Sequence> getSequences() {
		//		return (List<Structure>) structures.values();
		Set<Sequence> structures = new HashSet<>();
		structures.addAll(this.sequences.values());
		return structures;
	}

	/**
	 * Removes all entries in the list of Sequence instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
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
	 * Creates a GenericTopLevel instance with this SBOLDocument object's {@code defaultURIprefix},
	 * the given arguments, and an empty version string, and then
	 * adds it to this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} to do the following
	 * validity checks and create a GenericTopLevel instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#",
	 * then "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} is not {@code null} and is valid.
	 * <p>
	 * A GenericTopLevel instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#GENERIC_TOP_LEVEL},
	 * the given {@code displayId}, and an empty version string.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param rdfType a given QName for this annotated GenericTopLevel object
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}   
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, QName rdfType) throws SBOLValidationException {
		return createGenericTopLevel(defaultURIprefix,displayId,"",rdfType);
	}

	/**
	 * Creates a GenericTopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
	 * and the given arguments, and then adds it to this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} to do the following
	 * validity checks and create a GenericTopLevel instance.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the {@code defaultURIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the {@code defaultURIprefix} field to be set, and
	 * the given {@code displayId} and {@code version} arguments are not {@code null}
	 * and are both valid.
	 * <p>
	 * A GenericTopLevel instance is created with a compliant URI. This URI is composed from
	 * the this SBOLDocument object's {@code defaultURIprefix}, the optional type {@link TopLevel#GENERIC_TOP_LEVEL},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param rdfType a given QName for this annotated GenericTopLevel object
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) throws SBOLValidationException {
		return createGenericTopLevel(defaultURIprefix,displayId,version,rdfType);
	}

	/**
	 * Creates a GenericTopLevel instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of GenericTopLevel instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the given {@code URIprefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires that the given {@code URIprefix}, {@code displayId},
	 * and {@code version} are not {@code null} and are valid.
	 * <p>
	 * A GenericTopLevel instance is created with a compliant URI. This URI is composed from
	 * the given {@code URIprefix}, the optional type {@link TopLevel#GENERIC_TOP_LEVEL},
	 * the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param URIprefix maps to a domain over which the user has control
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @param rdfType a given QName for this annotated GenericTopLevel object
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
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
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
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
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	void addGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		addTopLevel(genericTopLevel, genericTopLevels, "genericTopLevel",
				collections, componentDefinitions, models, moduleDefinitions, sequences);
	}

	/**
	 * Removes the given {@code genericTopLevel} from this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param genericTopLevel The given GenericTopLevel object to be removed from this document
	 * @return {@code true} if the given {@code genericTopLevel} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException see {@link SBOLValidationException}  
	 */
	public boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		return removeTopLevel(genericTopLevel,genericTopLevels);
	}

	/**
	 * Returns the GenericTopLevel instance matching the given {@code displayId}
	 * and {@code version} from this SBOLDocument object's list of
	 * GenericTopLevel instances.
	 * <p>
	 * A compliant GenericTopLevel URI is created first using the {@code defaultURIprefix},
	 * the optional type {@link TopLevel#GENERIC_TOP_LEVEL}, the given {@code displayId}
	 * and {@code version}. This URI is used to look up the GenericTopLevel instance
	 * in this SBOLDocument object.
	 *
	 * @param displayId  an intermediate between name and identity that is machine-readable
	 * @param version The given version for this object
	 * @return the matching GenericTopLevel instance if present, or {@code null} otherwise.
	 */
	public GenericTopLevel getGenericTopLevel(String displayId, String version) {
		try {
			return genericTopLevels.get(createCompliantURI(defaultURIprefix,TopLevel.GENERIC_TOP_LEVEL,displayId,version, typesInURIs));
		} catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the GenericTopLevel instance matching the given {@code topLevelURI} from this
	 * SBOLDocument object's list of GenericTopLevel instances.
	 *
	 * @param topLevelURI The topLevel object to be retrieved from this SBOLDocument
	 * @return the matching GenericTopLevel instance if present, or {@code null} otherwise.
	 */
	public GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return genericTopLevels.get(topLevelURI);
	}

	/**
	 * Returns the set of {@code GenericTopLevel} instances owned by this SBOLDocument object.
	 *
	 * @return the set of {@code GenericTopLevel} instances owned by this SBOLDocument object.
	 */
	public Set<GenericTopLevel> getGenericTopLevels() {
		//		return (List<GenericTopLevel>) topLevels.values();
		Set<GenericTopLevel> topLevels = new HashSet<>();
		topLevels.addAll(this.genericTopLevels.values());
		return topLevels;
	}

	/**
	 * Removes all entries in the list of GenericTopLevel instances
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 * @throws SBOLValidationException see {@link SBOLValidationException}
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
	 * Returns the top-level instance matching the given {@code topLevelURI} from this
	 * SBOLDocument object's lists of top-level instances.
	 *
	 * @param topLevelURI The topLevel object to be retrieved from this SBOLDocument
	 * @return the matching top-level instance if present, or {@code null} otherwise.
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
	 * Returns a set of all TopLevel objects.
	 *
	 * @return set of all TopLevel objects.
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
	 * Creates a set of TopLevels with derived from the same object
	 * as specified by the wasDerivedFrom parameter.
	 * @param wasDerivedFrom refers to another SBOL object or non-SBOL resource from which this object was derived.
	 * @return Set of TopLevels with a matching wasDerivedFrom URI.
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
	 * Adds a namespace URI and its prefix to a SBOL document
	 *
	 * @param nameSpaceURI The Namespace {@link URI}
	 * @param prefix The prefix {@link String}
	 */
	public void addNamespace(URI nameSpaceURI, String prefix) {

		//		if (!URIcompliance.isURIprefixCompliant(nameSpaceURI.toString())) {
		//			throw new SBOLException("Namespace URI " + nameSpaceURI.toString() + " is not valid.");
		//		}
		nameSpaces.put(prefix, NamespaceBinding(nameSpaceURI.toString(), prefix));

	}

	/**
	 * Adds a namespace {@link QName} to a SBOL document
	 *
	 * @param qName Qualified name ({@link QName}) for a namespace
	 */
	public void addNamespace(QName qName) {

		nameSpaces.put(qName.getPrefix(), NamespaceBinding(qName.getNamespaceURI(),
				qName.getPrefix()));
	}

	void addNamespaceBinding(NamespaceBinding namespaceBinding) {
		nameSpaces.put(namespaceBinding.getPrefix(), namespaceBinding);
	}

	/**
	 *  Removes all non-required namespaces from the SBOL document.
	 */
	public void clearNamespaces() {
		Object[] keySetArray = nameSpaces.keySet().toArray();
		for (Object key : keySetArray) {
			if (isRequiredNamespaceBinding((URI)key)) continue;
			removeNamespace((URI) key);
		}
	}

	/**
	 * Returns the {@link QName} instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of namespace QName instances.
	 *
	 * @param namespaceURI the namespaceURI to be retrieved from this SBOLDocument
	 * @return the matching instance if present, or {@code null} otherwise.
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
	 * Returns the list of namespace bindings owned by this SBOLDocument object.
	 *
	 * @return the list of namespace bindings owned by this SBOLDocument object.
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
	 * Removes the given {@code namespaceURI} from this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This SBOLDocument object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
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
	 * Removes the given {@code topLevel} from this SBOLDocument object's list of TopLevel instances.
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
	 * Returns the default URI prefix of this SBOLDocument object
	 *
	 * @return the default URI prefix of this SBOLDocument object
	 */
	public String getDefaultURIprefix() {
		return defaultURIprefix;
	}

	/**
	 * Returns {@code true} if the {@code complete} flag for this SBOLDocument object is set.
	 * This flag is set to {@code true} if all objects are must be present within this SBOLDocument object,
	 * i.e. all URI references point to actual objects.
	 *
	 * @return {@code true} if the complete flag is set, {@code false} otherwise
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Sets the complete flag which when true indicates this SBOLDocument object is complete
	 * and any URIs that cannot be dereferenced to a valid object cause an exception to be thrown.
	 *
	 * @param complete A flag indicator which when true indicates this SBOLDocument object is complete
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * Returns {@code true} if all URIs in this SBOLDocument object are compliant.
	 *
	 * @return {@code true} if all URIs in this SBOLDocument object are compliant,
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
	 * Returns {@code true} if default component instances should be created when not present.
	 *
	 * @return {@code true} if default component instances should be created when not present,
	 * {@code false} otherwise
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
	 * Takes in a given RDF fileName and add the data read to this SBOLDocument.
	 *
	 * @param fileName a given RDF fileName
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public void read(String fileName) throws SBOLValidationException, IOException, SBOLConversionException {
		read(new File(fileName));
	}

	/**
	 * Takes in a given RDF File and add the data read to this SBOLDocument.
	 *
	 * @param file a given RDF File
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
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
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
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
