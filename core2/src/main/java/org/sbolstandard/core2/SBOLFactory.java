package org.sbolstandard.core2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

/**
 * This is a helper class that avoids the need for the user to explicitly create an SBOLDocument instance,
 * and allows the user to directly create and manipulate top-level instances.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */
public final class SBOLFactory {

	private static SBOLDocument document = new SBOLDocument();
	
	/**
	 * Sets the internal SBOL document used by the factory to the given one.
	 * 
	 * @param sbolDocument the SBOL document used internally by the factory
	 */
	public static void setSBOLDocument(SBOLDocument sbolDocument) {
		document = sbolDocument;
	}

	/**
	 * Sets the internal SBOL document used by the factory to a new SBOL document.
	 */
	public static void clear() {
		document = new SBOLDocument();
	}

	/**
	 * Creates a module definition, and then adds it to the factory's internal SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#createModuleDefinition(String)} by passing the given
	 * display ID.
	 * 
	 * @param displayId the display ID of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#createModuleDefinition(String)}.
	 */
	public static ModuleDefinition createModuleDefinition(String displayId) throws SBOLValidationException {
		return document.createModuleDefinition(displayId);
	}

	/**
	 * Creates a module definition, and then adds it to the factory's internal SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#createModuleDefinition(String, String)} by passing the given
	 * display ID and version.
	 * 
	 * @param displayId the display ID of the module definition to be created
	 * @param version the version of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link SBOLDocument#createModuleDefinition(String, String)}.
	 */
	public static ModuleDefinition createModuleDefinition(String displayId, String version) throws SBOLValidationException {
		return document.createModuleDefinition(displayId,version);
	}

	/**
	 * Creates a module definition, and then adds it to the factory's internal SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#createModuleDefinition(String, String, String)} by passing the given
	 * display ID and version.
	 * 
	 * @param URIprefix the URI prefix used to construct the compliant URI for the module definition to be created
	 * @param displayId the display ID of the module definition to be created
	 * @param version the version of the module definition to be created
	 * @return the created module definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link SBOLDocument#createModuleDefinition(String, String, String)}. 
	 */
	public static ModuleDefinition createModuleDefinition(String URIprefix,String displayId, String version) throws SBOLValidationException {
		return document.createModuleDefinition(URIprefix, displayId, version);
	}

	/**
	 * Removes the given module definition from this SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#removeModuleDefinition(ModuleDefinition)}.
	 *
	 * @param moduleDefinition the module definition to be removed
	 * @return {@code true} if the given module definition was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#removeModuleDefinition(ModuleDefinition)}.
	 */
	public static boolean removeModuleDefinition(ModuleDefinition moduleDefinition) throws SBOLValidationException {
		return document.removeModuleDefinition(moduleDefinition);
	}

	/**
	 * Returns the module definition matching the given display ID and version from the factory's internal SBOL document's
	 * list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#getModuleDefinition(String, String)} with the given
	 * arguments.
	 * 
	 * @param displayId the display ID of the module definition to be retrieved
	 * @param version the version of the module definition to be retrieved
	 * @return the matching module definition if present, or {@code null} otherwise
	 */
	public static ModuleDefinition getModuleDefinition(String displayId,String version) {
		return document.getModuleDefinition(displayId, version);
	}

	/**
	 * Returns the module definition matching the given identity URI from 
	 * the factory's internal SBOL document's list of module definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#getModuleDefinition(URI)} with the given argument.
	 * 
	 * @param moduleURI the give identity URI of the module definition to be retrieved
	 * @return the matching module definition if present, or {@code null} otherwise
	 */
	public static ModuleDefinition getModuleDefinition(URI moduleURI) {
		return document.getModuleDefinition(moduleURI);
	}

	/**
	 * Returns the set of module definitions owned by the factory's internal SBOL document.
	 *
	 * @return the set of module definitions owned by the factory's internal SBOL document
	 */
	public static Set<ModuleDefinition> getModuleDefinitions() {
		return document.getModuleDefinitions();
	}

	/**
	 * Removes all entries in the list of module definitions
	 * owned by the internal SBOL document of the factory.
	 * The list will be empty after this call returns.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#clearModuleDefinitions()}.
	 */
	public static void clearModuleDefinitions() throws SBOLValidationException {
		document.clearModuleDefinitions();
	}

	/**
	 * Creates a collection first, and then adds to the factory's internal SBOL document's list of collections.
	 * <p>
	 * This method calls {@link SBOLDocument#createCollection(String)} with the given display ID.
	 * 
	 * @param displayId the display ID of the collection to be created
	 * @return the created collection
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createCollection(String)}.
	 */
	public static Collection createCollection(String displayId) throws SBOLValidationException {
		return document.createCollection(displayId);
	}

	/**
	 * Creates a collection first, and then adds to the factory's internal SBOL document's list of collections.
	 * <p>
	 * This method calls {@link SBOLDocument#createCollection(String, String)} with the given display ID and version.
	 *
	 * @param displayId the display ID of the collection to be created
	 * @param version the version of the collection to be created 
	 * @return the created collection
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createCollection(String, String)}.
	 */
	public static Collection createCollection(String displayId, String version) throws SBOLValidationException {
		return document.createCollection(displayId, version);
	}

	/**
	 * Creates a collection first, and then adds to factory's internal SBOL document's list of collections.
	 * <p>
	 * This method calls {@link SBOLDocument#createCollection(String, String, String)} with the 
	 * given URI prefix, display ID, and version.
	 *
	 * @param URIprefix the URI prefix for the collection to be created
	 * @param displayId the display ID of the collection to be created
	 * @param version the version of the collection to be created
	 * @return the created collection
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createCollection(String, String, String)}.
	 */
	public static Collection createCollection(String URIprefix, String displayId, String version) throws SBOLValidationException {
		return document.createCollection(URIprefix, displayId, version);
	}

	/**
	 * Removes the given collection from the factory's internal SBOL document's list of collections.
	 * <p>
	 * This method calls {@link SBOLDocument#removeCollection(Collection)}.
	 *
	 * @param collection the collection to be removed
	 * @return {@code true} if the given collection was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#removeCollection(Collection)}.
	 */
	public static boolean removeCollection(Collection collection) throws SBOLValidationException {
		return document.removeCollection(collection);
	}

	/**
	 * Returns the collection matching the given display ID and version from the factory's internal
	 * SBOL document's list of collections.
	 * <p>
	 * This method calls {@link SBOLDocument#getCollection(String, String)} with the given arguments.
	 *
	 * @param displayId the display ID of the collection to be retrieved
	 * @param version the version of the collection to be retrieved
	 * @return the matching collection if present, or {@code null} otherwise.
	 */
	public static Collection getCollection(String displayId,String version) {
		return document.getCollection(displayId, version);
	}

	/**
	 * Returns the collection matching the given identity URI from the factory's internal 
	 * SBOL document's list of collections.
 	 * <p>
	 * This method calls {@link SBOLDocument#getCollection(URI)} with the given argument.
	 *
	 * @param collectionURI the given identity URI of the collection to be retrieved
	 * @return the matching collection if present, or {@code null} otherwise
	 */
	public static Collection getCollection(URI collectionURI) {
		return document.getCollection(collectionURI);
	}

	/**
	 * Returns the set of collections owned by the factory's internal SBOL document
	 * <p>
	 * This method calls {@link SBOLDocument#getCollections()}.
	 *
	 * @return the set of collections owned by the factory's internal SBOL document 
	 */
	public static Set<Collection> getCollections() {
		return document.getCollections();
	}

	/**
	 * Removes all entries in the list of collections owned by the internal SBOL document of
	 * the factory. The list will be empty after this call returns.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#clearCollections()}.
	 */
	public static void clearCollections() throws SBOLValidationException {
		document.clearCollections();
	}

	/**
	 * Creates a model, and then adds it to the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#createModel(String, String, URI, URI, URI)} with the given arguments
	 * and an empty string for version.
	 * 
	 * @param displayId the display ID of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createModel(String, String, URI, URI, URI)}.
	 */
	public static Model createModel(String displayId, URI source, URI language, URI framework) throws SBOLValidationException {
		return document.createModel(displayId,"",source,language,framework);
	}

	/**
	 * Creates a model, and then adds it to the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#createModel(String, String, URI, URI, URI)} with the given arguments.
	 * 
	 * @param displayId the display ID of the model to be created
	 * @param version the version of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createModel(String, String, URI, URI, URI)}.
	 */ 
	public static Model createModel(String displayId, String version, URI source, URI language, URI framework) throws SBOLValidationException {
		return document.createModel(displayId,version,source,language,framework);
	}

	/**
	 * Creates a model, and then adds it to the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#createModel(String, String, String, URI, URI, URI)} with the given arguments.
	 * 
	 * @param URIprefix the URI prefix used to construct the compliant URI for the model to be created
	 * @param displayId the display ID of the model to be created
	 * @param version the version of the model to be created
	 * @param source the source of the model to be created
	 * @param language the language of the model to be created
	 * @param framework the framework of the model to be created
	 * @return the created model
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#createModel(String, String, String, URI, URI, URI)}.
	 */
	public static Model createModel(String URIprefix, String displayId, String version, URI source, URI language, URI framework) throws SBOLValidationException {
		return document.createModel(URIprefix,displayId,version,source,language,framework);
	}

	/**
	 * Removes the given model from the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#removeModel(Model)}.
	 *
	 * @param model the model to be removed
	 * @return {@code true} if the given model was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#removeModel(Model)}.
	 */
	public static boolean removeModel(Model model) throws SBOLValidationException {
		return document.removeModel(model);
	}

	/**
	 * Returns the model matching the given display ID and version from 
	 * the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#getModel(String, String)} with the given arguments.
	 *
	 * @param displayId the display ID of the model to be retrieved
	 * @param version the version of the model to be retrieved
	 * @return the matching model if present, or {@code null} otherwise
	 */
	public static Model getModel(String displayId,String version) {
		return document.getModel(displayId,version);
	}

	/**
	 * Returns the model matching the given identity URI from 
	 * the factory's internal SBOL document's list of models.
	 * <p>
	 * This method calls {@link SBOLDocument#getModel(URI)} with the given argument.
	 * 
	 * @param modelURI the identity URI of the model to be retrieved
	 * @return the matching model if present, or {@code null} otherwise
	 */
	public static Model getModel(URI modelURI) {
		return document.getModel(modelURI);
	}

	/**
	 * Returns the set of models owned by the factory's internal SBOL document.
 	 * <p>
	 * This method calls {@link SBOLDocument#getModels()}.
	 *
	 * @return the set of models owned by the factory's internal SBOL document
	 */
	public static Set<Model> getModels() {
		return document.getModels();
	}

	/**
	 * Removes all entries in the list of models owned by the internal SBOL document 
	 * of thie factory. The list will be empty after this call returns.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#clearModels()}.
	 */
	public static void clearModels() throws SBOLValidationException {
		document.clearModels();
	}

	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, Set)} with the given component definition display ID
	 * and types. 
	 * 
	 * @param displayId the display ID of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#createComponentDefinition(String, Set)}.
	 */
	public static ComponentDefinition createComponentDefinition(String displayId, Set<URI> types) throws SBOLValidationException {
		return document.createComponentDefinition(displayId, types);
	}


	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, URI)} with the given component definition display ID
	 * and type. 
	 * 
	 * @param displayId the display ID of the component definition to be created
	 * @param type the type to be added to the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#createComponentDefinition(String, URI)}.
	 */
	public static ComponentDefinition createComponentDefinition(String displayId, URI type) throws SBOLValidationException {
		return document.createComponentDefinition(displayId, type);
	}

	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
 	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, String, Set)} with the given component definition display ID, 
	 * version, and types. 
	 *
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in
	 * {@link SBOLDocument#createComponentDefinition(String, String, Set)}.
	 */
	public static ComponentDefinition createComponentDefinition(String displayId, String version, Set<URI> types) throws SBOLValidationException {
		return document.createComponentDefinition(displayId, version, types);
	}

	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
 	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, String, URI)} with the given component definition display ID, 
	 * version, and type.
	 * 
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param type the type of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in
	 * {@link SBOLDocument#createComponentDefinition(String, String, URI)}.
	 */
	public static ComponentDefinition createComponentDefinition(String displayId, String version, URI type) throws SBOLValidationException {
		return document.createComponentDefinition(displayId, version, type);
	}

	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
 	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, String, String, Set)} with the given URI prefix, 
	 * component definition display ID, version, and types. 
	 *
	 * @param URIprefix the URI prefix used to construct the compliant URI for the component definition to be created
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param types the types of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException  if an SBOL validation rule was violated in
	 * {@link SBOLDocument#createComponentDefinition(String, String, String, Set)}.
	 */
	public static ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, Set<URI> types) throws SBOLValidationException {
		return document.createComponentDefinition(URIprefix, displayId, version, types);
	}

	/**
	 * Creates a component definition, and then adds it to the factory's internal SBOL document's list of component definitions.
 	 * <p>
	 * This method calls {@link SBOLDocument#createComponentDefinition(String, String, String, URI)} with the given URI prefix, 
	 * component definition display ID, version, and type.
	 * 
 	 * @param URIprefix the URI prefix used to construct the compliant URI for the component definition to be created
	 * @param displayId the display ID of the component definition to be created
	 * @param version the version of the component definition to be created
	 * @param type the type of the component definition to be created
	 * @return the created component definition
	 * @throws SBOLValidationException if an SBOL validation rule was violated in
	 * {@link SBOLDocument#createComponentDefinition(String, String, String, URI)}.
	 */
	public static ComponentDefinition createComponentDefinition(String URIprefix,String displayId, String version, URI type) throws SBOLValidationException {
		return document.createComponentDefinition(URIprefix, displayId, version, type);
	}

	/**
	 * Removes the given component definition from the factory's internal SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#removeComponentDefinition(ComponentDefinition)}.
	 *
	 * @param componentDefinition the component definition to be removed
	 * @return {@code true} if the given component definition was successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#removeComponentDefinition(ComponentDefinition)}.
	 */
	public static boolean removeComponentDefinition(ComponentDefinition componentDefinition) throws SBOLValidationException {
		return document.removeComponentDefinition(componentDefinition);
	}

	/**
	 * Returns the component definition matching the given display ID and version from 
	 * the factory's internal SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#getComponentDefinition(String, String)} with 
	 * the given arguments.
	 * 
	 * @param displayId the display ID of the component definition to be retrieved
	 * @param version the version of the component definition to be retrieved
	 * @return the matching component definition if present, or {@code null} otherwise 
	 */
	public static ComponentDefinition getComponentDefinition(String displayId,String version) {
		return document.getComponentDefinition(displayId, version);
	}

	/**
	 * Returns the component definition matching the given identity URI from the factory's 
	 * internal SBOL document's list of component definitions.
	 * <p>
	 * This method calls {@link SBOLDocument#getComponentDefinition(URI)} with 
	 * the given argument.
	 * 
	 * @param componentDefinitionURI the given identity URI of the component definition to be retrieved
	 * @return the matching component definition if present, or {@code null} otherwise.
	 */
	public static ComponentDefinition getComponentDefinition(URI componentDefinitionURI) {
		return document.getComponentDefinition(componentDefinitionURI);
	}

	/**
	 * Returns the set of component definitions owned by the factory's 
	 * internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#getComponentDefinitions()}.
	 *
	 * @return the set of component definitions owned by by the factory's 
	 * internal SBOL document
	 */
	public static Set<ComponentDefinition> getComponentDefinitions() {
		return document.getComponentDefinitions();
	}
	
	/**
	 * Returns the set of root component definitions.
	 * @return the set of root component definitions
	 */
	public static Set<ComponentDefinition> getRootComponentDefinitions() {
		return document.getRootComponentDefinitions();
	}

	/**
	 * Removes all entries in the list of component definitions
	 * owned by the internal SBOL document of the factory. 
	 * The list will be empty after this call returns.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#clearComponentDefinitions()}.
	 */
	public static void clearComponentDefinitions() throws SBOLValidationException {
		document.clearComponentDefinitions();
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to the factory's internal 
	 * SBOL document's list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#createSequence(String, String, URI)} by passing the given
	 * arguments.
	 * 
	 * @param displayId the display ID of the sequence to be created
	 * @param elements the elements property of the sequence to be created 
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link SBOLDocument#createSequence(String, String, URI)}. 
	 */
	public static Sequence createSequence(String displayId, String elements, URI encoding) throws SBOLValidationException {
		return document.createSequence(displayId,elements,encoding);
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to the factory's internal 
	 * SBOL document's list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#createSequence(String, String, String, URI)} by passing the given
	 * arguments.
	 * 
	 * @param displayId the display ID of the sequence to be created
	 * @param version the version of the sequence to be created
	 * @param elements the elements property of the sequence to be created 
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link SBOLDocument#createSequence(String, String, String, URI)}. 
	 */
	public static Sequence createSequence(String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		return document.createSequence(displayId,version,elements,encoding);
	}

	/**
	 * Creates a sequence with the given arguments, and then adds it to the factory's internal 
	 * SBOL document's list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#createSequence(String, String, String, String, URI)} by passing the given
	 * arguments.
	 * 
	 * @param URIprefix the URI prefix used to construct the compliant URI for the sequence to be created
	 * @param displayId the display ID of the sequence to be created
	 * @param version the version of the sequence to be created
	 * @param elements the elements property of the sequence to be created 
	 * @param encoding the encoding property of the sequence to be created
	 * @return the created sequence
	 * @throws SBOLValidationException if an SBOL validation rule was violated in 
	 * {@link SBOLDocument#createSequence(String, String, String, String, URI)}. 
	 */
	public static Sequence createSequence(String URIprefix, String displayId, String version, String elements, URI encoding) throws SBOLValidationException {
		return document.createSequence(URIprefix, displayId, version, elements, encoding);
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
	 * Creates an identical copy of each top-level element of the given SBOL document, and then adds the created top-level to the corresponding
	 * list of top-levels in the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link #createCopy(TopLevel)} by passing each top-level in the given SBOL document.
	 *
	 * @param document the document to be copied from
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #createCopy(TopLevel)}.
	 */
	public static void createCopy(SBOLDocument document) throws SBOLValidationException {
		for (TopLevel topLevel : document.getTopLevels()) {
			createCopy(topLevel);
		}
	}

	/**
	 * Creates an identical copy of the given top-level, and then adds the created top-level to the 
	 * corresponding list of top-levels in the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#createCopy(TopLevel)} by passing the given top-level.
	 *
	 * @param topLevel the given top-level to be copied
	 * @return the created top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createCopy(TopLevel)}.
	 */
	public static TopLevel createCopy(TopLevel topLevel) throws SBOLValidationException {
		return document.createCopy(topLevel);
	}

	/**
	 * Creates an identical copy of the given top-level, and then adds the created top-level to the 
	 * corresponding list of top-levels in the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#createCopy(TopLevel, String)} by passing the given top-level
	 * and the given display ID.
	 *
	 * @param topLevel the top-level to be copied from
	 * @param displayId the display ID of the created copy
	 * @return the created top-level instance
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createCopy(TopLevel, String)}.
	 */
	public static TopLevel createCopy(TopLevel topLevel, String displayId) throws SBOLValidationException {
		return document.createCopy(topLevel,displayId);
	}
	
	/**
	 * Creates an identical copy of the given top-level, and then adds the created top-level to the 
	 * corresponding list of top-levels in the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#createCopy(TopLevel, String, String)} by passing the given top-level,
	 * display ID, and version.
	 *
	 * @param topLevel the top-level to be copied from
	 * @param displayId the display ID of the created copy
	 * @param version the version of the created copy
	 * @return the created top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createCopy(TopLevel, String, String)}.
	 */
	public static TopLevel createCopy(TopLevel topLevel, String displayId, String version) throws SBOLValidationException {
		return document.createCopy(topLevel,displayId,version);
	}


	/**
	 * Creates an identical copy of the given top-level, and then adds the created top-level to the 
	 * corresponding list of top-levels in the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#createCopy(TopLevel, String, String, String)} by passing the given top-level, 
	 * URI prefix, display ID, and version.
	 *
	 * @param topLevel the top-level to be copied from
	 * @param URIprefix the URI prefix of the created copy
	 * @param displayId the display ID of the created copy
	 * @param version the version of the created copy
	 * @return the created top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createCopy(TopLevel, String, String, String)}.
	 */
	public static TopLevel createCopy(TopLevel topLevel, String URIprefix, String displayId, String version) throws SBOLValidationException {
		return document.createCopy(topLevel, URIprefix, displayId, version);
	}
	
	/**
	 * Creates an identical copy of the given top-level and returns it in 
	 * a new SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#createRecursiveCopy(TopLevel)} by passing the given
	 * top-level.
	 *
	 * @param topLevel the top-level to be copied
	 * @return the created SBOL document with the copied top-level
	 * @throws SBOLValidationException if an SBOL validation rule was violated in
	 * {@link SBOLDocument#createRecursiveCopy(TopLevel)}.
	 */
	public static SBOLDocument createRecursiveCopy(TopLevel topLevel) throws SBOLValidationException {
		return document.createRecursiveCopy(topLevel);
	}

	/**
	 * Removes the given sequence from the factory's internal SBOL document's list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#removeSequence(Sequence)}.
	 *
	 * @param sequence the given sequence to be removed
	 * @return {@code true} if the given sequence was successfully removed, {@code false} otherwise
	 * @throws SBOLValidationException if an SBOL validation rule was violated in {@link SBOLDocument#removeSequence(Sequence)}.
	 */
	public static boolean removeSequence(Sequence sequence) throws SBOLValidationException {
		return document.removeSequence(sequence);
	}

	/**
	 * Returns the sequence matching the given display ID and version from the factory's internal SBOL document's
	 * list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#getSequence(String, String)} with the given arguments.
	 *
	 * @param displayId the display ID of the sequence to be retrieved
	 * @param version the version of the sequence to be retrieved
	 * @return the matching sequence if present, or {@code null} otherwise.
	 */
	public static Sequence getSequence(String displayId,String version) {
		return document.getSequence(displayId, version);
	}

	/**
	 * Returns the sequence matching the given identity URI from the factory's internal SBOL document's
	 * list of sequences.
	 * <p>
	 * This method calls {@link SBOLDocument#getSequence(URI)} with the given arguments.
	 * 
	 * @param sequenceURI the identity URI of the sequence to be retrieved	 
	 * @return the matching sequence if present, or {@code null} otherwise.
	 */
	public static Sequence getSequence(URI sequenceURI) {
		return document.getSequence(sequenceURI);
	}

	/**
	 * Returns the set of sequences owned by the factory's internal SBOL document.
	 *
	 * @return the set of sequences owned by the factory's internal SBOL document
	 */
	public static Set<Sequence> getSequences() {
		return document.getSequences();
	}

	/**
	 * Removes all entries in the list of sequences owned by the internal SBOL document of
	 * the factory. The list will be empty after this call returns.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#clearSequences()}.
	 */
	public static void clearSequences() throws SBOLValidationException {
		document.clearSequences();
	}

	/**
	 * Creates a generic top-level, and then adds it to the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#createGenericTopLevel(String, QName)}
	 * with the given display ID and RDF type.
	 * 
	 * @param displayId the display ID of the generic top-level to be created
	 * @param rdfType the type of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createGenericTopLevel(String, QName)}.
	 */
	public static GenericTopLevel createGenericTopLevel(String displayId, QName rdfType) throws SBOLValidationException {
		return document.createGenericTopLevel(displayId, rdfType);
	}

	/**
 	 * Creates a generic top-level, and then adds it to the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#createGenericTopLevel(String, String, QName)}
	 * with the given display ID and RDF type.
	 * 
	 * @param displayId the display ID of the generic top-level to be created
	 * @param version the version of the generic top-level to be created
	 * @param rdfType the type of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createGenericTopLevel(String, String, QName)}.
	 */
	public static GenericTopLevel createGenericTopLevel(String displayId, String version, QName rdfType) throws SBOLValidationException {
		return document.createGenericTopLevel(displayId,version,rdfType);
	}

	/**
 	 * Creates a generic top-level, and then adds it to the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#createGenericTopLevel(String, String, String, QName)} with the given URI prefix,
	 * display ID, version, and RDF type.
	 * 
	 * @param URIprefix the given URI prefix used to create a compliant URI for the generic top-level to be created  
	 * @param displayId the display ID of the generic top-level to be created
	 * @param version the version of the generic top-level to be created
	 * @param rdfType the type of the generic top-level to be created
	 * @return the created generic top-level
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in 
	 * {@link SBOLDocument#createGenericTopLevel(String, String, String, QName)}.
	 */
	public static GenericTopLevel createGenericTopLevel(String URIprefix, String displayId, String version, QName rdfType) throws SBOLValidationException {
		return document.createGenericTopLevel(URIprefix, displayId, version, rdfType);
	}

	/**
	 * Removes the given generic top-level from the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#removeGenericTopLevel(GenericTopLevel)}.
	 *
	 * @param genericTopLevel the generic top-level to be removed
	 * @return {@code true} if the given generic top-level was successfully removed, {@code false} otherwise.
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#removeGenericTopLevel(GenericTopLevel)}.
	 */
	public static boolean removeGenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
		return document.removeGenericTopLevel(genericTopLevel);
	}

	/**
	 * Returns the generic top-level matching the given display ID and version from 
	 * the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#getGenericTopLevel(String, String)} with
	 * the given arguments.
	 * 
	 * @param displayId the display ID of the generic top-level to be retrieved
	 * @param version the version of the generic top-level to be retrieved
	 * @return the matching generic top-level if present, or null otherwise.
	 */
	public static GenericTopLevel getGenericTopLevel(String displayId, String version) {
		return document.getGenericTopLevel(displayId, version);
	}

	/**
	 * Returns the generic top-level matching the given identity URI from 
	 * the factory's internal SBOL document's list of generic top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#getGenericTopLevel(URI)} with
	 * the given argument.
	 * 
	 * @param topLevelURI the identity URI of the top-level to be retrieved
	 * @return the matching generic top-level if present, or {@code null} otherwise
	 */
	public static GenericTopLevel getGenericTopLevel(URI topLevelURI) {
		return document.getGenericTopLevel(topLevelURI);
	}

	/**
	 * Returns the set of generic topLevels owned by the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#getGenericTopLevels()}.
	 *
	 * @return the set of generic topLevels owned by the factory's internal SBOL document
	 */
	public static Set<GenericTopLevel> getGenericTopLevels() {
		return document.getGenericTopLevels();
	}

	/**
	 * Removes all entries in the list of generic top-levels
	 * owned by the internal SBOL document of the factory. The list will be empty after this call returns.
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in
	 * {@link SBOLDocument#clearGenericTopLevels()}.
	 */
	public static void clearGenericTopLevels() throws SBOLValidationException {
		document.clearGenericTopLevels();
	}

	/**
	 * Returns the top-level matching the given identity URI from the factory's internal SBOL document's
	 * list of top-levels.
	 * <p>
	 * This method calls {@link SBOLDocument#getTopLevel(URI)} with the given argument.
	 * 
	 * @param topLevelURI the identity URI of the sequence to be retrieved	 
	 * @return the matching top-level if present, or {@code null} otherwise
	 */
	public static TopLevel getTopLevel(URI topLevelURI) {
		return document.getTopLevel(topLevelURI);
	}

	/**
	 * Returns the set of top-levels owned by the factory's internal SBOL document.
	 *
	 * @return the set of top-levels owned by the factory's internal SBOL document
	 */
	public static Set<TopLevel> getTopLevels() {
		return document.getTopLevels();
	}

	/**
	 * Retrieves a set of top-levels in the factory's internal SBOL document whose wasDerivedFrom field matches the given one.
	 * <p>
	 * This method calls {@link SBOLDocument#getByWasDerivedFrom(URI)} by passing the given argument.
	 * 
	 * @param wasDerivedFrom the wasDerivedFrom field of which all matching top-levels to be retrieved
	 * @return a set of top-levels whose with the matching wasDerivedFrom field
	 */
	public static Set<TopLevel> getByWasDerivedFrom(URI wasDerivedFrom) {
		return document.getByWasDerivedFrom(wasDerivedFrom);
	}

	/**
	 * Adds the given namespace URI and its prefix to the internal SBOL document used by the factory.
	 * <p>
	 * This method calls {@link SBOLDocument#addNamespace(URI, String)} with the given
	 * namespace URI and prefix.
	 *
	 * @param namespaceURI the URI used to construct a new namespace
	 * @param prefix the prefix used to construct a new namespace
	 */
	public static void addNamespace(URI namespaceURI, String prefix) {
		document.addNamespace(namespaceURI, prefix);
	}

	/**
	 * Adds the given namespace QName to the internal SBOL document used by the factory.
	 * <p>
	 * This method calls {@link SBOLDocument#addNamespace(QName)} with the given QName.
	 *
	 * @param qName the QName to be added
	 */
	public static void addNamespace(QName qName) {
		document.addNamespace(qName);
	}

	/**
	 *  Removes all non-required namespaces from the internal SBOL document of the factory.
	 */
	public static void clearNamespaces() {
		document.clearNamespaces();
	}

	/**
	 * Returns the QName matching the given namespace URI from the factory's internal SBOL document's list of QNames.
	 * 
	 * @param namespaceURI the identity URI of the namespace to be retrieved
	 * @return the matching QName if present, or {@code null} otherwise
	 */
	public static QName getNamespace(URI namespaceURI) {
		return document.getNamespace(namespaceURI);
	}

	/**
	 * Returns the list of namespace bindings owned by the factory's internal SBOL document.
	 *
	 * @return the list of namespace bindings owned by the factory's internal SBOL document
	 */
	public static List<QName> getNamespaces() {
		return document.getNamespaces();
	}

	/**
	 * Removes the given namespace URI from the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#removeNamespace(URI)}.
	 *
	 * @param namespaceURI the namespace URI to be removed
	 */
	public static void removeNamespace(URI namespaceURI) {
		document.removeNamespace(namespaceURI);
	}

	@Override
	public int hashCode() {
		return document.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		return document.equals(obj);
	}

	/**
	 * Sets the default URI prefix to the given one.
	 * <p>
	 * This method calls {@link SBOLDocument#setDefaultURIprefix(String)}.
	 *
	 * @param defaultURIprefix the given default URI prefix
	 */

	public static void setDefaultURIprefix(String defaultURIprefix) {
		document.setDefaultURIprefix(defaultURIprefix);
	}

	/**
	 * Returns the default URI prefix of the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#getDefaultURIprefix()}.
	 *
	 * @return the default URI prefix of the factory's internal SBOL document
	 */
	public static String getDefaultURIprefix() {
		return document.getDefaultURIprefix();
	}

	/**
	 * Returns the value of the complete flag in the factory's internal SBOL document.
	 * <p>
	 * A {@code true} value means that all identity URI references should be able 
	 * to dereference to instances in the this document, and a false value means otherwise. 
	 * This method calls {@link SBOLDocument#isComplete()}.
	 *
	 * @return the value of the complete flag for the factory's internal SBOL document
	 */
	public static boolean isComplete() {
		return document.isComplete();
	}

	/**
 	 * Sets the complete flag to the given value. 
	 * <p>
	 * A {@code true} value indicates that the factory's internal SBOL document is complete:
	 * any identity URIs should be able to dereference to an instance in this document.
	 * This method calls {@link SBOLDocument#setComplete(boolean)}.
	 *
	 * @param complete the given boolean value for the complete flag
	 */
	public static void setComplete(boolean complete) {
		document.setComplete(complete);
	}

	/**
	 * Returns the value of the compliant flag in the factory's internal SBOL document.
	 * <p>
	 * A {@code true} value means that all URIs in the factory's internal SBOL document are compliant,
	 * and a {@code false} value means otherwise.
	 * This method calls {@link SBOLDocument#isCompliant()}.
	 * 
	 * @return the value of the compliant flag in this SBOL document.
	 */
	public static boolean isCompliant() {
		return document.isCompliant();
	}

	/**
 	 * Returns the value of the typesInURI flag in the factory's internal SBOL document. 
	 * <p>
	 * A {@code true} value means that types will be inserted into this document's each top-level's compliant URI when it is created,
	 * and a {@code false} value means otherwise. 
	 * This method calls {@link SBOLDocument#isTypesInURIs()}.
	 *
	 * @return the value of the typesInURI flag in the factory's internal SBOL document
	 */
	public static boolean isTypesInURIs() {
		return document.isTypesInURIs();
	}

	/**
	 * Sets the typesInURIs flag to the given value. 
	 * <p>
	 * A {@code true} value means that types are inserted into top-level 
	 * identity URIs when they are created. 
	 * This method calls {@link SBOLDocument#setTypesInURIs(boolean)}.
	 *
	 * @param typesInURIs the given boolean value for the typesInURIs flag 
	 */
	public static void setTypesInURIs(boolean typesInURIs) {
		document.setTypesInURIs(typesInURIs);
	}

	/**
	 * Returns the value of the createDefaults flag in the factory's internal SBOL document. 
	 * 
	 * A {@code true} value means that default components and/or functional components instances should be created 
	 * when not present in the factory's internal SBOL document, and a {@code false} value means otherwise.
	 * This method calls {@link SBOLDocument#isCreateDefaults()}.
	 *
	 * @return {@code true} if default component instances should be created when not present,
	 * {@code false} otherwise
	 */
	public static boolean isCreateDefaults() {
		return document.isCreateDefaults();
	}

	/**
	 * Sets the createDefaults flag to the given value.
	 * <p> 
	 * A {@code true} value means that default component instances should be created when not present.
	 * This method calls {@link SBOLDocument#setCreateDefaults(boolean)}.
	 *
	 * @param createDefaults the given boolean value for the createDefaults flag
	 */
	public static void setCreateDefaults(boolean createDefaults) {
		document.setCreateDefaults(createDefaults);
	}

	/**
	 * Takes in a given RDF file name and add the data read to the factory's internal SBOL document.
	 *
	 * @param fileName a given RDF file name
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#read(String)}. 
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static void read(String fileName) throws SBOLValidationException, IOException, SBOLConversionException {
		document.read(fileName);
	}

	/**
	 * Takes in a given RDF file and adds the data read to the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#read(File)}.
	 * 
	 * @param file a given RDF file
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#read(File)}.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static void read(File file) throws SBOLValidationException, IOException, SBOLConversionException {
		document.read(file);
	}

	/**
	 * Takes in a given RDF input stream and add the data read to the factory's internal SBOL document.
	 * <p>
	 * This method calls {@link SBOLDocument#read(InputStream)}.
	 *
	 * @param in a given RDF input stream
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link SBOLDocument#read(InputStream)}.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static void read(InputStream in) throws SBOLValidationException, IOException, SBOLConversionException {
		document.read(in);
	}


	/**
	 * Outputs the factory's internal SBOL document's data from the RDF/XML serialization to a new file with the given file name.
	 * <p>
	 * This method calls {@link SBOLDocument#write(String)}.
	 * 
	 * @param filename the given output file name
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLDocument#write(String)}.
	 */
	public static void write(String filename) throws IOException, SBOLConversionException 
	{
		document.write(filename);
	}
	
	/**
	 * Outputs the factory's internal SBOL document's data from serialization in the given serialization format
	 * to a new file with the given file name.
	 * <p>
	 * This method calls {@link SBOLDocument#write(File, String)}.
	 * 
	 * @param filename the given output file name
	 * @param fileType the given serialization format
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException {@link SBOLDocument#write(File, String)}.
	 */
	public static void write(String filename,String fileType) throws IOException, SBOLConversionException
	{
		document.write(new File(filename), fileType);
	}

	/**
	 * Outputs the factory's internal SBOL document's data from the RDF/XML serialization to the given file.
	 * This method calls {@link SBOLDocument#write(File)}.
	 * 
	 * @param file the given output file
	 * @throws IOException see {@link IOException} 
	 * @throws SBOLConversionException see {@link SBOLDocument#write(File)}
	 */
	public static void write(File file) throws IOException, SBOLConversionException
	{
		document.write(file);
	}
	
	/**
	 * Outputs the factory's internal SBOL document's data from the serialization in the given serialization format to the given file.
	 * <p>
	 * This method calls {@link SBOLDocument#write(File, String)}.
	 * 
	 * @param file the given output file
	 * @param fileType the given serialization format
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException see {@link SBOLDocument#write(File, String)}.
	 */
	public static void write(File file,String fileType) throws IOException, SBOLConversionException
	{
		document.write(file,fileType);
	}

	/**
	 * Outputs the factory's internal SBOL document's data from the RDF/XML serialization to the given output stream.
	 * <p>
	 * This method calls {@link SBOLDocument#write(OutputStream)}.
	 * 
	 * @param out the given output stream
	 * @throws SBOLConversionException see {@link SBOLDocument#write(OutputStream)}.
	 */
	public static void write(OutputStream out) throws SBOLConversionException 
	{
		document.write(out);
	}
	
	/**
	 * Outputs the factory's internal SBOL document's data from the serialization in the given serialization format to the given output stream.
	 * <p>
	 * This method calls {@link SBOLDocument#write(OutputStream, String)}.
	 * 
	 * @param out the given output stream
	 * @param fileType the serialization format
	 * @throws SBOLConversionException see {@link SBOLDocument#write(OutputStream, String)}.
	 * @throws IOException see {@link IOException}
	 */
	public static void write(OutputStream out,String fileType) throws SBOLConversionException, IOException 
	{
		document.write(out, fileType);
	}

	@Override
	public String toString() {
		return document.toString();
	}
}
