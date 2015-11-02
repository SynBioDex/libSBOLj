package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.InputStream;
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
	private HashMap<String, NamespaceBinding> nameSpaces;
	private String defaultURIprefix;
	private boolean complete = false;
	private boolean compliant = true;
	private boolean typesInURIs = false;
	private boolean createDefaults = false;

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
	}

	/**
	 * Creates a ModuleDefinition instance with this SBOLDocument object's {@code defaultURIprefix}, 
	 * the given arguments, and an empty version string, and then    
	 * adds it to this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} to do the following
	 * validity checks and create a ModuleDefinition instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param displayId
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ModuleDefinition instances.
	 */
	public ModuleDefinition createModuleDefinition(String displayId) {
		return createModuleDefinition(defaultURIprefix,displayId,"");
	}
		
	/**
	 * Creates a ModuleDefinition instance with this SBOLDocument object's {@code defaultURIprefix} 
	 * and the given arguments, and then adds it to this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This method calls {@link #createModuleDefinition(String, String, String)} to do the following
	 * validity checks and create a ModuleDefinition instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param version
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ModuleDefinition instances.
	 */
	public ModuleDefinition createModuleDefinition(String displayId, String version) {
		return createModuleDefinition(defaultURIprefix,displayId,version);
	}
	
	/**
	 * Creates a ModuleDefinition instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of ModuleDefinition instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @return the created ModuleDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ModuleDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ModuleDefinition instances.
	 */
	public ModuleDefinition createModuleDefinition(String URIprefix,String displayId, String version) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
		ModuleDefinition md = createModuleDefinition(createCompliantURI(URIprefix, TopLevel.MODULE_DEFINITION, displayId, version, typesInURIs));
		md.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.MODULE_DEFINITION, displayId, "", typesInURIs));
		md.setDisplayId(displayId);
		md.setVersion(version);
		return md;
	}
	
	/**
	 * @param identity
	 * @return the new module definition
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
		for (FunctionalComponent functionalComponent : newModuleDefinition.getFunctionalComponents()) {
			functionalComponent.setSBOLDocument(this);
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (Module module : newModuleDefinition.getModules()) {
			module.setSBOLDocument(this);
			for (MapsTo mapsTo : module.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (Interaction interaction : newModuleDefinition.getInteractions()) {
			interaction.setSBOLDocument(this);
			for (Participation participation : interaction.getParticipations()) {
				participation.setSBOLDocument(this);
			}
		}
	}
	
	/**
	 * Removes the given {@code moduleDefinition} from this SBOLDocument object's list of ModuleDefinition instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param moduleDefinition
	 * @return {@code true} if the given {@code moduleDefinition} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and one of its ModuleDefinition instances has a Module instance that refers to the given 
	 * {@code moduleDefinition} (see {@link Module#getDefinitionURI()}).
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code moduleDefinition} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeModuleDefinition(ModuleDefinition moduleDefinition) {
		checkReadOnly();
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				for (Module m : md.getModules()) {
					if (m.getDefinitionURI().equals(moduleDefinition.getIdentity())) {
						throw new SBOLValidationException("Cannot remove " + moduleDefinition.getIdentity() + 
								" since it is in use.");
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
	 * @param displayId
	 * @param version
	 * @return the matching ModuleDefinition instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public ModuleDefinition getModuleDefinition(String displayId,String version) {
		validateIdentityData(displayId,version);
		return moduleDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.MODULE_DEFINITION,displayId,version, typesInURIs));
	}
	
	/**
	 * Returns the ModuleDefinition instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of ModuleDefinition instances.
	 * 
	 * @param moduleURI
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
	 * Creates a Collection instance with this SBOLDocument object's {@code defaultURIprefix}, 
	 * the given {@code displayId} argument, and an empty version string, and then    
	 * adds it to this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} to do the following
	 * validity checks and create a Collection instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @return the created Collection instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the defaultURIprefix is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Collection instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Collection instance's identity URI
	 * exists in this SBOLDocument object's list of Collection instances.
	 */
	public Collection createCollection(String displayId) {
		return createCollection(defaultURIprefix,displayId,"");
	}
	
	/**
	 * Creates a Collection instance with this SBOLDocument object's {@code defaultURIprefix} and 
	 * the given arguments, and then adds it to this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This method calls {@link #createCollection(String, String, String)} to do the following
	 * validity checks and create a Collection instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param version
	 * @return the created Collection instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the defaultURIprefix is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Collection instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Collection instance's identity URI
	 * exists in this SBOLDocument object's list of Collection instances.
	 */
	public Collection createCollection(String displayId, String version) {
		return createCollection(defaultURIprefix,displayId,version);
	}
	
	/**
	 * Creates a Collection instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Collection instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @return the created Collection instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the defaultURIprefix is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Collection instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Collection instance's identity URI
	 * exists in this SBOLDocument object's list of Collection instances.
	 */
	public Collection createCollection(String URIprefix, String displayId, String version) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
		Collection c = createCollection(createCompliantURI(URIprefix, TopLevel.COLLECTION, displayId, version, typesInURIs));
		c.setDisplayId(displayId);
		c.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.COLLECTION, displayId, "", typesInURIs));
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
	 * Removes the given {@code collection} from this SBOLDocument object's list of Collection instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param collection
	 * @return {@code true} if the given {@code collection} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code collection} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeCollection(Collection collection) {
		checkReadOnly();
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
	 * @param displayId
	 * @param version
	 * @return the matching Collection instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public Collection getCollection(String displayId,String version) {
		validateIdentityData(displayId,version);
		return collections.get(createCompliantURI(defaultURIprefix,TopLevel.COLLECTION,displayId,version, typesInURIs));
	}	
	
	/**
	 * Returns the Collection instance matching the given {@code collectionURI} from this
	 * SBOLDocument object's list of Collection instances.
	 * 
	 * @param collectionURI
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
	 * Creates a Model instance with this SBOLDocument object's {@code defaultURIprefix}, 
	 * the given arguments, and an empty version string, and then    
	 * adds it to this SBOLDocument object's list of Model instances.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} to do the following
	 * validity checks and create a Model instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param displayId
	 * @param source
	 * @param language
	 * @param framework
	 * @return the created Model instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Model instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Model instance's identity URI
	 * exists in this SBOLDocument object's list of Model instances.
	 */
	public Model createModel(String displayId, URI source, URI language, URI framework) {
		return createModel(defaultURIprefix,displayId,"",source,language,framework);
	}	

	/**
	 * Creates a Model instance with this SBOLDocument object's {@code defaultURIprefix} 
	 * and the given arguments, and then adds it to this SBOLDocument object's list of Model instances.
	 * <p>
	 * This method calls {@link #createModel(String, String, String, URI, URI, URI)} to do the following
	 * validity checks and create a Model instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param version
	 * @param source
	 * @param language
	 * @param framework
	 * @return the created Model instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Model instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Model instance's identity URI
	 * exists in this SBOLDocument object's list of Model instances.
	 */
	public Model createModel(String displayId, String version, URI source, URI language, URI framework) {
		return createModel(defaultURIprefix,displayId,version,source,language,framework);
	}	

	/**
	 * Creates a Model instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Model instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param source
	 * @param language
	 * @param framework
	 * @return the created Model instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Model instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Model instance's identity URI
	 * exists in this SBOLDocument object's list of Model instances.
	 */
	public Model createModel(String URIprefix, String displayId, String version, URI source, URI language, URI framework) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
		Model model = createModel(createCompliantURI(URIprefix, TopLevel.MODEL, displayId, version, typesInURIs),
				source, language, framework);
		model.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.MODEL, displayId, "", typesInURIs));
		model.setDisplayId(displayId);
		model.setVersion(version);
		return model;
	}

	/**
	 * @param identity
	 * @param source
	 * @param language
	 * @param framework
	 * @return the new model
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
	 * Removes the given {@code model} from this SBOLDocument object's list of Model instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param model
	 * @return {@code true} if the given {@code model} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and one of its ModuleDefinition instances refers to the given {@code model}
	 * (see {@link ModuleDefinition#containsModel(URI)}).
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code model} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeModel(Model model) {
		checkReadOnly();
		if (complete) {
			for (ModuleDefinition md : moduleDefinitions.values()) {
				if (md.containsModel(model.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + model.getIdentity() + 
								" since it is in use.");
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
	 * @param displayId
	 * @param version
	 * @return the matching Model instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public Model getModel(String displayId,String version) {
		validateIdentityData(displayId,version);
		return models.get(createCompliantURI(defaultURIprefix,TopLevel.MODEL,displayId,version, typesInURIs));
	}
	
	/**
	 * Returns the Model instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of Model instances.
	 * 
	 * @param modelURI
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
	 * @param identity
	 * @param types
	 * @return the new component definition
	 */
	ComponentDefinition createComponentDefinition(URI identity, Set<URI> types) {
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
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param types
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ComponentDefinition instances.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, Set<URI> types) {
		return createComponentDefinition(defaultURIprefix,displayId,"",types);
	}

	/**
	 * Creates a ComponentDefinition instance with this SBOLDocument object's {@code defaultURIprefix} 
	 * and the given arguments, and then adds it to this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This method calls {@link #createComponentDefinition(String, String, String, Set)} to do the following
	 * validity checks and create a ComponentDefinition instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param version
	 * @param types
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ComponentDefinition instances.
	 */
	public ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types) {
		return createComponentDefinition(defaultURIprefix,displayId,version,types);
	}
	
	/**
	 * Creates a ComponentDefinition instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of ComponentDefinition instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param types
	 * @return the created ComponentDefinition instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created ComponentDefinition instance's identity URI
	 * exists in this SBOLDocument object's list of ComponentDefinition instances.
	 */
	public ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, Set<URI> types) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
		ComponentDefinition cd = createComponentDefinition(createCompliantURI(URIprefix, TopLevel.COMPONENT_DEFINITION,
				displayId, version, typesInURIs), types);
		cd.setDisplayId(displayId);
		cd.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.COMPONENT_DEFINITION, displayId,"", typesInURIs));
		cd.setVersion(version);
		return cd;
	}

	/**
	 * Appends the specified element to the end of the list of component definitions.
	 */
	void addComponentDefinition(ComponentDefinition newComponentDefinition) {
		addTopLevel(newComponentDefinition, componentDefinitions, "componentDefinition",
                collections, genericTopLevels, models, moduleDefinitions, sequences);
		for (Component component : newComponentDefinition.getComponents()) {
			component.setSBOLDocument(this);
			for (MapsTo mapsTo : component.getMapsTos()) {
				mapsTo.setSBOLDocument(this);
			}
		}
		for (SequenceAnnotation sequenceAnnotation : newComponentDefinition.getSequenceAnnotations()) {
			sequenceAnnotation.setSBOLDocument(this);
			for (Location location : sequenceAnnotation.getLocations()) {
				location.setSBOLDocument(this);
			}
		}
		for (SequenceConstraint sequenceConstraint : newComponentDefinition.getSequenceConstraints()) {
			sequenceConstraint.setSBOLDocument(this);
		}
	}

	/**
	 * Removes the given {@code componentDefinition} from this SBOLDocument object's list of ComponentDefinition instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param componentDefinition
	 * @return {@code true} if the given {@code componentDefinition} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code componentDefinition} is referenced by any of its ComponentDefinition instances.
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and one of its ModuleDefinition instances owns a FunctionalComponent instance that
	 * refers to the given {@code componentDefinition} as its {@code definition} 
	 * (see {@link FunctionalComponent#getDefinitionURI()}).
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code componentDefinition} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeComponentDefinition(ComponentDefinition componentDefinition) {
		checkReadOnly();
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				for (Component c : cd.getComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLValidationException("Cannot remove " + componentDefinition.getIdentity() + 
								" since it is in use.");
					}
				}
			}
			for (ModuleDefinition md : moduleDefinitions.values()) {
				for (FunctionalComponent c : md.getFunctionalComponents()) {
					if (c.getDefinitionURI().equals(componentDefinition.getIdentity())) {
						throw new SBOLValidationException("Cannot remove " + componentDefinition.getIdentity() + 
								" since it is in use.");
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
	 * @param displayId
	 * @param version
	 * @return the matching ComponentDefinition instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public ComponentDefinition getComponentDefinition(String displayId,String version) {
		validateIdentityData(displayId,version);
		return componentDefinitions.get(createCompliantURI(defaultURIprefix,TopLevel.COMPONENT_DEFINITION,displayId,version, typesInURIs));
	}
	
	/**
	 * Returns the ComponentDefinition instance matching the given {@code componentDefinitionURI} from this
	 * SBOLDocument object's list of ComponentDefinition instances.
	 * 
	 * @param componentDefinitionURI
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
		//		return (List<Component>) components.values();
		Set<ComponentDefinition> components = new HashSet<>();
		components.addAll(this.componentDefinitions.values());
		return components;
	}

	/**
	 * Removes all entries in the list of ComponentDefinition instances 
	 * owned by this SBOLDocument object. The list will be empty after this call returns.
	 */
	public void clearComponentDefinitions() {
		Object[] valueSetArray = componentDefinitions.values().toArray();
		for (Object componentDefinition : valueSetArray) {
			removeComponentDefinition((ComponentDefinition)componentDefinition);
		}
	}

	/**
	 * @param componentDefinitions
	 */
	void setComponentDefinitions(List<ComponentDefinition> componentDefinitions) {
		checkReadOnly();
		clearComponentDefinitions();
		for (ComponentDefinition componentDefinition : componentDefinitions) {
			addComponentDefinition(componentDefinition);
		}
	}


	/**
	 * @param identity
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance
	 */
	Sequence createSequence(URI identity, String elements, URI encoding) {
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
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param displayId
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Sequence instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Sequence instance's identity URI
	 * exists in this SBOLDocument object's list of Sequence instances.
	 */
	public Sequence createSequence(String displayId, String elements, URI encoding) {
		return createSequence(defaultURIprefix,displayId,"",elements,encoding);
	}

	/**
	 * Creates a Sequence instance with this SBOLDocument object's {@code defaultURIprefix} 
	 * and the given arguments, and then adds it to this SBOLDocument object's list of Sequence instances.
	 * <p>
	 * This method calls {@link #createSequence(String, String, String, String, URI)} to do the following
	 * validity checks and create a Sequence instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param displayId
	 * @param version
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Sequence instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Sequence instance's identity URI
	 * exists in this SBOLDocument object's list of Sequence instances.
	 */
	public Sequence createSequence(String displayId, String version, String elements, URI encoding) {
		return createSequence(defaultURIprefix,displayId,version,elements,encoding);
	}
	
	/**
	 * Creates a Sequence instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of Sequence instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param elements
	 * @param encoding
	 * @return the created Sequence instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created Sequence instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created Sequence instance's identity URI
	 * exists in this SBOLDocument object's list of Sequence instances.
	 */
	public Sequence createSequence(String URIprefix, String displayId, String version, String elements, URI encoding) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
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
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param topLevel
	 * @return the created top-level instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created top-level instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created top-level instance's identity URI
	 * already exists.
	 * @throws IllegalArgumentException if the given {@code topLevel} instance is not an instance
	 * of a top-level object
	 */
	public TopLevel createCopy(TopLevel topLevel) {
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
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param topLevel
	 * @param displayId
	 * @return the created top-level instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created top-level instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created top-level instance's identity URI
	 * already exists.
	 * @throws IllegalArgumentException if the given {@code topLevel} instance is not an instance
	 * of a top-level object
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId) {
		return createCopy(topLevel,defaultURIprefix,displayId,"");
	}
	
	/**
 	 * Creates a copy of the given TopLevel instance with this SBOLDocument object's {@code defaultURIprefix}
 	 * and the given arguments, and then adds it to the 
 	 * corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel, String, String, String)} to do the following
	 * validity checks and create a copy top-level instance. 
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param topLevel
	 * @param displayId
	 * @param version
	 * @return the created top-level instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created top-level instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created top-level instance's identity URI
	 * already exists.
	 * @throws IllegalArgumentException if the given {@code topLevel} instance is not an instance
	 * of a top-level object
	 */
	public TopLevel createCopy(TopLevel topLevel, String displayId, String version) {
		return createCopy(topLevel,defaultURIprefix,displayId,version);
	}
	
	/**
 	 * Creates a copy of the given TopLevel instance with the given arguments, and then adds it to 
 	 * the corresponding top-level list owned by this SBOLDocument object.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param topLevel
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @return the created top-level instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created top-level instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created top-level instance's identity URI
	 * already exists.
	 * @throws IllegalArgumentException if the given {@code topLevel} instance is not an instance
	 * of a top-level object
	 */
	public TopLevel createCopy(TopLevel topLevel, String URIprefix, String displayId, String version) {
		checkReadOnly();
		if (!URIcompliance.isTopLevelURIcompliant(topLevel)) {
			throw new SBOLValidationException("Cannot copy a non-compliant SBOL object");
		}
		if (URIprefix == null) {
			URIprefix = extractURIprefix(topLevel.getIdentity());
			URIprefix = checkURIprefix(URIprefix);
		} else {
			URIprefix = checkURIprefix(URIprefix);
		}
		if (displayId == null) {
			displayId = topLevel.getDisplayId();
		}
		if (version == null) {
			version = topLevel.getVersion();
		}
		validateIdVersion(displayId,version);
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
	 * Appends the specified <code>sequence</code> to the end of the list of sequences.
	 */
	void addSequence(Sequence newSequence) {
		addTopLevel(newSequence, sequences, "sequence",
                collections, componentDefinitions, genericTopLevels, models, moduleDefinitions);
	}

	/**
	 * Removes the given {@code sequence} from this SBOLDocument object's list of Sequence instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param sequence
	 * @return {@code true} if the given {@code sequence} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and one of its ComponentDefinition instances refers to the given {@code sequence}
	 * (see {@link ComponentDefinition#containsSequence(URI)}).
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code sequence} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeSequence(Sequence sequence) {
		checkReadOnly();
		if (complete) {
			for (ComponentDefinition cd : componentDefinitions.values()) {
				if (cd.containsSequence(sequence.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + sequence.getIdentity() + 
							" since it is in use.");
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
	 * @param displayId
	 * @param version
	 * @return the matching Sequence instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public Sequence getSequence(String displayId,String version) {
		validateIdentityData(displayId,version);
		return sequences.get(createCompliantURI(defaultURIprefix,TopLevel.SEQUENCE,displayId,version, typesInURIs));
	}

	/**
	 * Returns the Sequence instance matching the given {@code modelURI} from this
	 * SBOLDocument object's list of Sequence instances.
	 * 
	 * @param sequenceURI
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
	 * Creates a GenericTopLevel instance with this SBOLDocument object's {@code defaultURIprefix}, 
	 * the given arguments, and an empty version string, and then    
	 * adds it to this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} to do the following
	 * validity checks and create a GenericTopLevel instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param rdfType
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's identity URI
	 * exists in this SBOLDocument object's list of GenericTopLevel instances.
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, QName rdfType) {
		return createGenericTopLevel(defaultURIprefix,displayId,"",rdfType);
	}

	/**
	 * Creates a GenericTopLevel instance with this SBOLDocument object's {@code defaultURIprefix} 
	 * and the given arguments, and then adds it to this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This method calls {@link #createGenericTopLevel(String, String, String, QName)} to do the following
	 * validity checks and create a GenericTopLevel instance.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
	 * @param displayId
	 * @param version
	 * @param rdfType
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's identity URI
	 * exists in this SBOLDocument object's list of GenericTopLevel instances.
	 */
	public GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) {
		return createGenericTopLevel(defaultURIprefix,displayId,version,rdfType);
	}
	
	/**
	 * Creates a GenericTopLevel instance with the given arguments, and then adds it to this SBOLDocument
	 * object's list of GenericTopLevel instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
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
 	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param rdfType
	 * @return the created GenericTopLevel instance
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's persistent
	 * identity exists in this SBOLDocument object's other lists of top-level instances.
	 * @throws IllegalArgumentException if the created GenericTopLevel instance's identity URI
	 * exists in this SBOLDocument object's list of GenericTopLevel instances.
	 */
	public GenericTopLevel createGenericTopLevel(String URIprefix, String displayId, String version, QName rdfType) {
		checkReadOnly();
		URIprefix = checkURIprefix(URIprefix);
		validateIdVersion(displayId, version);
		GenericTopLevel g = createGenericTopLevel(createCompliantURI(URIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, version, typesInURIs), rdfType);
		g.setPersistentIdentity(createCompliantURI(URIprefix, TopLevel.GENERIC_TOP_LEVEL, displayId, "", typesInURIs));
		g.setDisplayId(displayId);
		g.setVersion(version);
		return g;
	}

	/**
	 * @param identity
	 * @param rdfType
	 * @return the new generic top level
	 */
	GenericTopLevel createGenericTopLevel(URI identity, QName rdfType) {
		if (rdfType.getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI()) ||
				rdfType.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI())) {
			throw new SBOLValidationException(rdfType.getLocalPart()+" is not an SBOL object, so it cannot be in the SBOL namespace.");
		}
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
	 * Removes the given {@code genericTopLevel} from this SBOLDocument object's list of GenericTopLevel instances.
	 * <p>
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param genericTopLevel
	 * @return {@code true} if the given {@code genericTopLevel} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code genericTopLevel} is referenced by any of its Collection instances as a member.
	 */
	public boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) {
		checkReadOnly();
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
	 * @param displayId
	 * @param version
	 * @return the matching GenericTopLevel instance if present, or {@code null} otherwise.
	 * @throws IllegalArgumentException if the {@code defaultURIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code displayId} is invalid
	 * @throws IllegalArgumentException if the given {@code version} is invalid
	 */
	public GenericTopLevel getGenericTopLevel(String displayId, String version) {
		validateIdentityData(displayId,version);
		return genericTopLevels.get(createCompliantURI(defaultURIprefix,TopLevel.GENERIC_TOP_LEVEL,displayId,version, typesInURIs));
	}

	/**
	 * Returns the GenericTopLevel instance matching the given {@code topLevelURI} from this
	 * SBOLDocument object's list of GenericTopLevel instances.
	 * 
	 * @param topLevelURI
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
	
	/**
	 * Returns the top-level instance matching the given {@code topLevelURI} from this
	 * SBOLDocument object's lists of top-level instances.
	 * 
	 * @param topLevelURI
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
		checkReadOnly();
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
	 * @param namespaceURI
	 * @return the matching instance if present, or {@code null} otherwise.      
	 */
	public QName getNamespace(URI namespaceURI) {
		if (nameSpaces.get(namespaceURI)==null) return null;
		return new QName(namespaceURI.toString(), "", nameSpaces.get(namespaceURI).getPrefix());
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
	 * This SBOLDcouement object is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param namespaceURI
	 * @throws SBOLValidationException if this SBOLDocument object is not compliant
	 * @throws IllegalStateException if the given {@code namespaceURI} belongs to one of the
	 * following required namespace binding: {@link Sbol2Terms#sbol2}, {@link Sbol2Terms#dc},
	 * {@link Sbol2Terms#prov}, or {@link Sbol1Terms#rdf}. 
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
	
	/**
	 * Removes the given {@code topLevel} from this SBOLDocument object's list of TopLevel instances.
	 * 
	 * @param topLevel
	 * @param instancesMap
	 * @return {@code true} if the given {@code topLevel} is successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if this SBOLDocument object is complete ({@link SBOLDocument#isComplete()}),
	 * and the given {@code topLevel} is referenced by one of its Collection instances as a member.
	 */
	private final <TL extends TopLevel> boolean removeTopLevel(TopLevel topLevel, Map<URI, TL> instancesMap) {
		if (complete) {
			for (Collection c : collections.values()) {
				if (c.containsMember(topLevel.getIdentity())) {
					throw new SBOLValidationException("Cannot remove " + topLevel.getIdentity() + 
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
	 * Check the given {@code URIprefix} to make sure it is not {@code null} and is compliant,
	 * and if URIprefix does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of the given {@code URIprefix}.
	 *   
	 * @param URIprefix
	 * @return URIprefix
	 * @throws IllegalArgumentException if the given {@code URIprefix} is {@code null}
	 * @throws IllegalArgumentException if the given {@code URIprefix} is non-compliant
	 */
	String checkURIprefix(String URIprefix) {
		if (URIprefix==null) {
			throw new IllegalArgumentException("URI prefix must not be null");
		}
		if (!URIprefix.endsWith("/") && !URIprefix.endsWith(":") && !URIprefix.endsWith("#")) {
			URIprefix += "/";
		}
		if (!isURIprefixCompliant(URIprefix)) {
			throw new IllegalArgumentException("URI prefix '"+URIprefix+"' is invalid");
		}
		return URIprefix;
	}
	
	/**
	 * Sets the default URI prefix to the given {@code defaultURIprefix}.
	 * 
	 * @param defaultURIprefix
	 */
	
	public void setDefaultURIprefix(String defaultURIprefix) {
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
	 * @param complete
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
	 * @param typesInURIs
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
	 * @param createDefaults
	 */
	public void setCreateDefaults(boolean createDefaults) {
		this.createDefaults = createDefaults;
	}

	void checkReadOnly() {
		if (!compliant) {
			throw new SBOLValidationException("Cannot modify a non-compliant SBOL document");
		}
	}
	
	// TODO: NEEDS JAVADOC
	void read(InputStream in) {
		SBOLReader.read(this, in);
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
