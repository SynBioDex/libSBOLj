
package org.sbolstack.frontend;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.sbolstandard.core2.*;

/**
 * Provides a Java API to SBOL Stack instances.
 * @author James McLaughlin
 *
 */
public class StackFrontend
{
    PoolingHttpClientConnectionManager connectionManager;
    HttpClient client;
    String backendUrl;
    String user = null;

    /**
     * Creates an instance of the SBOL Stack API.
     * @param backendUrl - URL for the SBOL Stack instance.
     */
    public StackFrontend(String backendUrl)
    {
        this.backendUrl = backendUrl;

        connectionManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    /**
     * Returns the URL for the SBOL Stack instance.
     * @return the URL for the SBOL Stack instance.
     */
    public String getBackendUrl()
    {
        return this.backendUrl;
    }

    /**
     * Return the total number of ComponentDefinition instances present in the default store.
     *
     * @return the number of ComponentDefinition instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countComponentDefinitions() throws StackException
    {
        return countComponentDefinitions(null);
    }

    /**
     * Return the total number of ComponentDefinition instances present in a given store.
     *
     * @param storeName The name of the store to query
     *
     * @return the number of ComponentDefinition instances as an integer
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countComponentDefinitions(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/component/count");
    }

    /**
     * Return the total number of ModuleDefinition instances present in the default store.
     *
     * @return the number of ModuleDefinition instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countModuleDefinitions() throws StackException
    {
        return countModuleDefinitions(null);
    }

    /**
     * Return the total number of ModuleDefinition instances present in a given store.
     *
     * @return the number of ModuleDefinition instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countModuleDefinitions(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/module/count");
    }

    /**
     * Return the total number of Collection instances present in the default store.
     *
     * @return the number of Collection instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countCollections() throws StackException
    {
        return countCollections(null);
    }

    /**
     * Return the total number of Collection instances present in a given store.
     *
     * @return the number of Collection instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countCollections(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/collection/count");
    }
    
    /**
     * Return the total number of Model instances present in the default store.
     *
     * @return the number of Model instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countModels() throws StackException
    {
        return countModels(null);
    }

    /**
     * Return the total number of Model instances present in a given store.
     *
     * @return the number of Model instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countModels(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/model/count");
    }

    /**
     * Return the total number of GenericTopLevel instances present in the default store.
     *
     * @return the number of GenericTopLevel instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countGenericTopLevels() throws StackException
    {
        return countGenericTopLevels(null);
    }

    /**
     * Return the total number of GenericTopLevel instances present in a given store.
     *
     * @return the number of GenericTopLevel instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countGenericTopLevels(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/genericTopLevel/count");
    }

    /**
     * Return the total number of TopLevel instances present in the default store.
     *
     * @return the number of TopLevel instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countTopLevels() throws StackException
    {
        return countTopLevels(null);
    }

    /**
     * Return the total number of GenericTopLevel instances present in a given store.
     *
     * @return the number of GenericTopLevel instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countTopLevels(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/topLevel/count");
    }

    /**
     * Return the total number of Sequence instances present in the default store.
     *
     * @return the number of Sequence instances as an integer
     *
     * @throws StackException if there was an error communicating with the Stack
     */
    public int countSequences() throws StackException
    {
        return countSequences(null);
    }

    /**
     * Return the total number of Sequence instances present in a given store.
     *
     * @return the number of Sequence instances as an integer
     *
     * @param storeName The name of the store to query
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public int countSequences(String storeName) throws StackException
    {
        return fetchCount(backendUrl + storeUriFragment(storeName) + "/sequence/count");
    }

    /**
     * Retrieve a ComponentDefinition from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param componentUri The URI of the component to retrieve
     *
     * @return A libSBOLj ComponentDefinition instance corresponding to the component
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public ComponentDefinition fetchComponentDefinition(String storeName, URI componentUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/component/" + encodeUri(componentUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, componentUri);

        if(! (topLevel instanceof ComponentDefinition))
            throw new StackException("Expected ComponentDefinition, found " + topLevel.getClass().getName());

        return (ComponentDefinition) topLevel;
    }

    /**
     * Retrieve a ComponentDefinition from the default store by URI.
     *
     * @param componentUri The URI of the component to retrieve
     *
     * @return A libSBOLj ComponentDefinition instance corresponding to the component
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public ComponentDefinition fetchComponentDefinition(URI componentUri) throws StackException
    {
        return fetchComponentDefinition(null, componentUri);
    }

    /**
     * Retrieve a ModuleDefinition from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param moduleUri The URI of the module to retrieve
     *
     * @return A libSBOLj ModuleDefinition instance corresponding to the module
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public ModuleDefinition fetchModuleDefinition(String storeName, URI moduleUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/module/" + encodeUri(moduleUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, moduleUri);

        if(! (topLevel instanceof ModuleDefinition))
            throw new StackException("Expected ModuleDefinition, found " + topLevel.getClass().getName());

        return (ModuleDefinition) topLevel;
    }

    /**
     * Retrieve a ModuleDefinition from the default store by URI.
     *
     * @param moduleUri The URI of the module to retrieve
     *
     * @return A libSBOLj ModuleDefinition instance corresponding to the module
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public ModuleDefinition fetchModuleDefinition(URI moduleUri) throws StackException
    {
        return fetchModuleDefinition(null, moduleUri);
    }

    // TODO: should these fetch deep or just the object?
    /**
     * Retrieve a Collection from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param collectionUri The URI of the collection to retrieve
     *
     * @return A libSBOLj Collection instance corresponding to the collection
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public Collection fetchCollection(String storeName, URI collectionUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/collection/" + encodeUri(collectionUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, collectionUri);

        if(! (topLevel instanceof Collection))
            throw new StackException("Expected Collection, found " + topLevel.getClass().getName());

        return (Collection) topLevel;
    }

    /**
     * Retrieve a Collection from the default store by URI.
     *
     * @param collectionUri The URI of the collection to retrieve
     *
     * @return A libSBOLj Collection instance corresponding to the collection
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public Collection fetchCollection(URI collectionUri) throws StackException
    {
        return fetchCollection(null, collectionUri);
    }

    /**
     * Retrieve a Sequence from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param sequenceUri The URI of the sequence to retrieve
     *
     * @return A libSBOLj Sequence instance corresponding to the sequence
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public Sequence fetchSequence(String storeName, URI sequenceUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/sequence/" + encodeUri(sequenceUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, sequenceUri);

        if(! (topLevel instanceof Sequence))
            throw new StackException("Expected Sequence, found " + topLevel.getClass().getName());

        return (Sequence) topLevel;
    }

    /**
     * Retrieve a Sequence from the default store by URI.
     *
     * @param sequenceUri The URI of the sequence
     *
     * @return A libSBOLj Sequence instance corresponding to the sequence
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public Sequence fetchSequence(URI sequenceUri) throws StackException
    {
        return fetchSequence(null, sequenceUri);
    }
    
    /**
     * Retrieve a Model from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param modelUri The URI of the model to retrieve
     *
     * @return A libSBOLj Model instance corresponding to the model
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public Model fetchModel(String storeName, URI modelUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/model/" + encodeUri(modelUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, modelUri);

        if(! (topLevel instanceof Model))
            throw new StackException("Expected Model, found " + topLevel.getClass().getName());

        return (Model) topLevel;
    }

    /**
     * Retrieve a Model from the default store by URI.
     *
     * @param modelUri The URI of the model
     *
     * @return A libSBOLj Model instance corresponding to the model
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public Model fetchModel(URI modelUri) throws StackException
    {
        return fetchModel(null, modelUri);
    }
    
    /**
     * Retrieve a GenericTopLevel from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param genericTopLevelUri The URI of the genericTopLevel to retrieve
     *
     * @return A libSBOLj GenericTopLevel instance corresponding to the genericTopLevel
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public GenericTopLevel fetchGenericTopLevel(String storeName, URI genericTopLevelUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/genericTopLevel/" + encodeUri(genericTopLevelUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, genericTopLevelUri);

        if(! (topLevel instanceof GenericTopLevel))
            throw new StackException("Expected GenericTopLevel, found " + topLevel.getClass().getName());

        return (GenericTopLevel) topLevel;
    }

    /**
     * Retrieve a GenericTopLevel from the default store by URI.
     *
     * @param genericTopLevelUri The URI of the GenericTopLevel
     *
     * @return A libSBOLj GenericTopLevel instance corresponding to the genericTopLevel
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public GenericTopLevel fetchGenericTopLevel(URI genericTopLevelUri) throws StackException
    {
        return fetchGenericTopLevel(null, genericTopLevelUri);
    }
    
    /**
     * Retrieve a TopLevel from a given store by URI.
     *
     * @param storeName The name of the store to query
     * @param topLevelUri The URI of the TopLevel to retrieve
     *
     * @return A libSBOLj TopLevel instance corresponding to the TopLevel
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public TopLevel fetchTopLevel(String storeName, URI topLevelUri) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/topLevel/" + encodeUri(topLevelUri) + "/sbol";

        TopLevel topLevel = fetchTopLevelFromStack(url, topLevelUri);

        if(! (topLevel instanceof TopLevel))
            throw new StackException("Expected TopLevel, found " + topLevel.getClass().getName());

        return (TopLevel) topLevel;
    }

    /**
     * Retrieve a TopLevel from the default store by URI.
     *
     * @param TopLevelUri The URI of the TopLevel
     *
     * @return A libSBOLj TopLevel instance corresponding to the TopLevel
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public TopLevel fetchTopLevel(URI TopLevelUri) throws StackException
    {
        return fetchTopLevel(null, TopLevelUri);
    }
    
//    /**
//     * Search a given store for ComponentDefinition instances matching a ComponentDefinition template.
//     *
//     * @param storeName The name of the store to query
//     * @param template An SBOL document containing the ComponentDefinition template to match
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     *
//     * @return An SBOL2 document with a summary of all matching components.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     * @throws StackException if the specified store name does not exist
//     */
//    public SBOLDocument searchComponentDefinitions(String storeName, SBOLDocument template, Integer offset, Integer limit) throws StackException
//    {
//        String url = backendUrl + storeUriFragment(storeName) + "/component/search/template";
//
//        HttpPost request = new HttpPost(url);
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//        params.add(new BasicNameValuePair("sbol", serializeDocument(template)));
//
//        if(offset != null)
//            params.add(new BasicNameValuePair("offset", Integer.toString(offset)));
//
//        if(limit != null)
//            params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
//
//        try
//        {
//            request.setEntity(new UrlEncodedFormEntity(params));
//
//            HttpResponse response = client.execute(request);
//
//            checkResponseCode(response);
//
//            InputStream inputStream = response.getEntity().getContent();
//
//            SBOLDocument resultDocument = SBOLReader.read(inputStream);
//
//            return resultDocument;
//        }
//        catch (Exception e)
//        {
//            throw new StackException(e);
//        }
//        finally
//        {
//            request.releaseConnection();
//        }
//    }


//    /**
//     * Search a given store for ComponentDefinition instances matching a name and/or a set of roles
//     *
//     * @param storeName The name of the store to query
//     * @param name The dcterms:title to search for, or null
//     * @param roles A set of role URIs to search for, or null
//     * @param types A set of type URIs to search for, or null
//     * @param collections A set of Collection URIs to search for, or null
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     *
//     * @return An SBOL2 document with a summary of all matching components.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     * @throws StackException if the specified store name does not exist
//     */
//    public SBOLDocument searchComponentDefinitions(String storeName, String name, Set<URI> roles, Set<URI> types, Set<URI> collections, Integer offset, Integer limit)
//            throws StackException
//    {
//        String url = backendUrl + storeUriFragment(storeName) + "/component/search/sbol";
//
//        SearchQuery query = new SearchQuery();
//
//        query.offset = offset;
//        query.limit = limit;
//
//        for(URI uri : roles)
//        {
//            SearchCriteria roleCriteria = new SearchCriteria();
//
//            roleCriteria.key = "role";
//            roleCriteria.value = uri.toString();
//        }
//
//        for(URI uri : types)
//        {
//            SearchCriteria typeCriteria = new SearchCriteria();
//
//            typeCriteria.key = "type";
//            typeCriteria.value = uri.toString();
//        }
//
//        for(URI uri : collections)
//        {
//            SearchCriteria collectionCriteria = new SearchCriteria();
//
//            collectionCriteria.key = "collection";
//            collectionCriteria.value = uri.toString();
//        }
//
//        if(name != null)
//        {
//            SearchCriteria nameCriteria = new SearchCriteria();
//
//            nameCriteria.key = "name";
//            nameCriteria.value = name;
//        }
//
//        Gson gson = new Gson();
//
//        HttpPost request = new HttpPost(url);
//
//        try
//        {
//            request.setHeader("Content-Type", "application/json");
//            request.setEntity(new StringEntity(gson.toJson(query)));
//
//            HttpResponse response = client.execute(request);
//
//            checkResponseCode(response);
//
//            InputStream inputStream = response.getEntity().getContent();
//
//            SBOLDocument resultDocument = SBOLReader.read(inputStream);
//
//            return resultDocument;
//        }
//        catch (Exception e)
//        {
//            throw new StackException(e);
//        }
//        finally
//        {
//            request.releaseConnection();
//        }
//    }

//    /**
//     * Search the default store for ComponentDefinition instances matching a name and/or a set of roles
//     *
//     * @param name The dcterms:title to search for, or null
//     * @param roles A set of role URIs to search for, or null
//     * @param types A set of type URIs to search for, or null
//     * @param collections A set of Colleciton URIs to search for, or null
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     *
//     * @return An SBOL2 document with a summary of all matching components.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     */
//    public SBOLDocument searchComponentDefinitions(String name, Set<URI> roles, Set<URI> types, Set<URI> collections, Integer offset, Integer limit)
//            throws StackException
//    {
//        return searchComponentDefinitions(null, name, roles, types, collections, offset, limit);
//    }

    
 //    /**
//     * Search the default store for ComponentDefinition instances matching a ComponentDefinition template.
//     * 
//     * @param template An SBOL document containing the ComponentDefinition template to match
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     * 
//     * @return An SBOL2 document with a summary of all matching components.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     */
//    public SBOLDocument searchComponentDefinitions(SBOLDocument template, Integer offset, Integer limit) throws StackException
//    {
//        return searchComponentDefinitions(null, template, offset, limit);
//    }


   /**
     * Search the default store for ComponentDefinition instances matching a name and/or a set of roles
     *
     * @param name The dcterms:title to search for, or null
     * @param roles A set of role URIs to search for, or null
     * @param types A set of type URIs to search for, or null
     * @param collections A set of Collection URIs to search for, or null
     * @param offset The offset of the results to begin at, or null to begin at 0
     * @param limit The maximum number of results to return, or null to return all results
     *
     * @return An ArrayList of ComponentDefinitionMetaData objects with a summary of all matching ComponentDefinitions.
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public ArrayList<IdentifiedMetadata> searchComponentDefinitionMetadata(String name, Set<URI> roles, Set<URI> types, Set<URI> collections, Integer offset, Integer limit)
            throws StackException
    {
        return searchComponentDefinitionMetadata(null, name, roles, types, collections, offset, limit);
    }

    
    /**
     * Search a given store for ComponentDefinition instances matching a name and/or a set of roles
     *
     * @param storeName The name of the store to query
     * @param name The dcterms:title to search for, or null
     * @param roles A set of role URIs to search for, or null
     * @param types A set of type URIs to search for, or null
     * @param collections A set of Collection URIs to search for, or null
     * @param offset The offset of the results to begin at, or null to begin at 0
     * @param limit The maximum number of results to return, or null to return all results
     *
     * @return An ArrayList of ComponentDefinitionMetaData objects with a summary of all matching componentDefinitions.
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public ArrayList<IdentifiedMetadata> searchComponentDefinitionMetadata(String storeName, String name, Set<URI> roles, Set<URI> types, Set<URI> collections, Integer offset, Integer limit)
            throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/component/search/metadata";

        SearchQuery query = new SearchQuery();

        query.offset = offset;
        query.limit = limit;

        if (roles != null) {
        	for(URI uri : roles)
        	{
        		SearchCriteria roleCriteria = new SearchCriteria();

        		roleCriteria.key = "role";
        		roleCriteria.value = uri.toString();

        		query.criteria.add(roleCriteria);
        	}
        }
        
        if (types != null) {
        	for(URI uri : types)
        	{
        		SearchCriteria typeCriteria = new SearchCriteria();

        		typeCriteria.key = "type";
        		typeCriteria.value = uri.toString();

        		query.criteria.add(typeCriteria);
        	}
        }
        
        if (collections != null) {
        	for(URI uri : collections)
        	{
        		SearchCriteria collectionCriteria = new SearchCriteria();

        		collectionCriteria.key = "collection";
        		collectionCriteria.value = uri.toString();

        		query.criteria.add(collectionCriteria);
        	}
        }

        if(name != null)
        {
            SearchCriteria nameCriteria = new SearchCriteria();

            nameCriteria.key = "name";
            nameCriteria.value = name;

            query.criteria.add(nameCriteria);
        }

        Gson gson = new Gson();

        HttpPost request = new HttpPost(url);

        try
        {
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(gson.toJson(query)));

            HttpResponse response = client.execute(request);

            checkResponseCode(response);

            InputStream inputStream = response.getEntity().getContent();

            ArrayList<IdentifiedMetadata> metadataList = gson.fromJson(
            		new InputStreamReader(inputStream),
            			new TypeToken<ArrayList<IdentifiedMetadata>>(){}.getType());
            
            return metadataList;
        }
        catch (Exception e)
        {
            throw new StackException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }


//    /**
//     * Search a given store for Collection instances matching a name
//     *
//     * @param storeName The name of the store to query
//     * @param name The dcterms:title to search for, or null
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     *
//     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     * @throws StackException if the specified store name does not exist
//     */
//    public ArrayList<IdentifiedMetadata> searchCollectionMetadata(String storeName, String name, Integer offset, Integer limit)
//            throws StackException
//    {
//        String url = backendUrl + storeUriFragment(storeName) + "/collection/search/metadata";
//
//        SearchQuery query = new SearchQuery();
//
//        query.offset = offset;
//        query.limit = limit;
//
//        if(name != null)
//        {
//            SearchCriteria nameCriteria = new SearchCriteria();
//
//            nameCriteria.key = "name";
//            nameCriteria.value = name;
//
//            query.criteria.add(nameCriteria);
//        }
//
//        Gson gson = new Gson();
//
//        HttpPost request = new HttpPost(url);
//
//        try
//        {
//            request.setHeader("Content-Type", "application/json");
//            request.setEntity(new StringEntity(gson.toJson(query)));
//
//            HttpResponse response = client.execute(request);
//
//            checkResponseCode(response);
//
//            InputStream inputStream = response.getEntity().getContent();
//
//            ArrayList<IdentifiedMetadata> metadataList = gson.fromJson(
//            		new InputStreamReader(inputStream),
//            			new TypeToken<ArrayList<IdentifiedMetadata>>(){}.getType());
//            
//            return metadataList;
//        }
//        catch (Exception e)
//        {
//            throw new StackException(e);
//        }
//        finally
//        {
//            request.releaseConnection();
//        }
//    }
    
//    /**
//     * Search the default store for Collections instances matching a name.
//     *
//     * @param name The dcterms:title to search for, or null
//     * @param offset The offset of the results to begin at, or null to begin at 0
//     * @param limit The maximum number of results to return, or null to return all results
//     *
//     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
//     *
//     * @throws StackException if there was an error communicating with the stack
//     */    
//    public ArrayList<IdentifiedMetadata> searchCollectionMetadata(String name, Integer offset, Integer limit)
//            throws StackException
//    {
//    	return searchCollectionMetadata(null, name, offset, limit);
//    }

    /**
     * Search the default store for Collections that are not members of any other Collections
     *
     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
     *
     * @throws StackException if there was an error communicating with the stack
     */    
    public ArrayList<IdentifiedMetadata> searchRootCollectionMetadata()
            throws StackException
    {
    	return searchRootCollectionMetadata(null);
    }

    /**
     * Search a given store for Collections that are not members of any other Collections
     *
     * @param storeName The name of the store to query
     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
     *
     * @throws StackException if there was an error communicating with the stack
     */    
    public ArrayList<IdentifiedMetadata> searchRootCollectionMetadata(String storeName)
            throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/collection/roots";

        Gson gson = new Gson();

        HttpGet request = new HttpGet(url);

        try
        {
            HttpResponse response = client.execute(request);

            checkResponseCode(response);

            InputStream inputStream = response.getEntity().getContent();

            ArrayList<IdentifiedMetadata> metadataList = gson.fromJson(
            		new InputStreamReader(inputStream),
            			new TypeToken<ArrayList<IdentifiedMetadata>>(){}.getType());
            
            return metadataList;
        }
        catch (Exception e)
        {
            throw new StackException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }
    
    /**
     * Search the default store for Collections that are members of the specified Collection
     *
     * @param parentCollectionUri URI for Collection to search for member Collections 
     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
     *
     * @throws StackException if there was an error communicating with the stack
     */    
    public ArrayList<IdentifiedMetadata> searchSubCollectionMetadata(URI parentCollectionUri)
            throws StackException
    {
    	return searchSubCollectionMetadata(null, parentCollectionUri);
    }
    
    /**
     * Search a given store for Collections that are members of the specified Collection
     *
     * @param storeName The name of the store to query
     * @param parentCollectionUri URI for Collection to search for member Collections 
     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
     *
     * @throws StackException if there was an error communicating with the stack
     */    
    public ArrayList<IdentifiedMetadata> searchSubCollectionMetadata(String storeName, URI parentCollectionUri)
            throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName) + "/collection/" + encodeUri(parentCollectionUri) + "/subCollections";

        Gson gson = new Gson();

        HttpGet request = new HttpGet(url);

        try
        {
            HttpResponse response = client.execute(request);

            checkResponseCode(response);

            InputStream inputStream = response.getEntity().getContent();

            ArrayList<IdentifiedMetadata> metadataList = gson.fromJson(
            		new InputStreamReader(inputStream),
            			new TypeToken<ArrayList<IdentifiedMetadata>>(){}.getType());
            
            return metadataList;
        }
        catch (Exception e)
        {
            throw new StackException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }
    
//    /**
//     * Return the number of ComponentDefinition instances matching a ComponentDefinition template in a given store.
//     * 
//     * @param storeName The name of the store to query
//     * @param template An SBOL document containing the ComponentDefinition template to match
//     * 
//     * @return the number of matching instances as an integer
//     *
//     * @throws StackException if there was an error communicating with the stack
//     * @throws StackException if the specified store name does not exist
//     */
//    public int countMatchingComponentDefinitions(String storeName, SBOLDocument template) throws StackException
//    {
//        String url = backendUrl + storeUriFragment(storeName) + "/component/search/template";
//
//        HttpPost request = new HttpPost(url);
//        
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("sbol", serializeDocument(template)));
//                
//        try
//        {
//            request.setEntity(new UrlEncodedFormEntity(params));
//
//            HttpResponse response = client.execute(request);
//
//            checkResponseCode(response);
//            
//            InputStream inputStream = response.getEntity().getContent();
//            
//            return Integer.parseInt(inputStreamToString(inputStream));
//        }
//        catch (Exception e)
//        {
//            throw new StackException(e);
//        }
//        finally
//        {
//            request.releaseConnection();
//        }
//    }

 //    /**
//     * Return the number of ComponentDefinition instances matching a ComponentDefinition template in the default store.
//     * 
//     * @param template An SBOL document containing the ComponentDefinition template to match
//     * 
//     * @return the number of matching instances as an integer
//     *
//     * @throws StackException if there was an error communicating with the stack
//     */
//    public int countMatchingComponentDefinitions(SBOLDocument template) throws StackException
//    {
//        return countMatchingComponentDefinitions(null, template);
//    }
    

    /**
     * Upload an SBOLDocument to the stack.
     * 
     * @param storeName The name of the store to upload to
     * @param document The document to upload
     *
     * @throws StackException if there was an error communicating with the stack
     * @throws StackException if the specified store name does not exist
     */
    public void upload(String storeName, SBOLDocument document) throws StackException
    {
        String url = backendUrl + storeUriFragment(storeName);

        HttpPost request = new HttpPost(url);
                
        try
        {
            request.setEntity(new StringEntity(serializeDocument(document)));
            request.setHeader("Content-Type", "application/rdf+xml");
            
            HttpResponse response = client.execute(request);
            
            checkResponseCode(response);
        }
        catch (Exception e)
        {
            throw new StackException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }

    
    /**
     * Upload an SBOLDocument to the stack.
     * 
     * @param document The document to upload
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public void upload(SBOLDocument document) throws StackException
    {
        upload(null, document);
    }
    

    /**
     * Create a new store.
     * 
     * @param storeName The name of the store to create
     *
     * @throws StackException if there was an error communicating with the stack
     */
    public void createStore(String storeName) throws StackException
    {
        String url = backendUrl + "/store/create";

        HttpPost request = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("storeName", storeName));
                
        try
        {
            request.setEntity(new UrlEncodedFormEntity(params));

            request.setHeader("Content-Type", "application/rdf+xml");
            
            HttpResponse response = client.execute(request);
            
            checkResponseCode(response);
        }
        catch (Exception e)
        {
            throw new StackException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }
    
    /**
	 * Sets the user to null to indicate that no user is logged in.
     */
    public void logout() 
    {
    	user = null;
    }

    /**
     * Login to the stack.
     * @param email The user's email
     * @param password The user's password
     * 
     * @throws StackException if there was an error communicating with the stack
     */
    public void login(String email, String password) throws StackException
    {    	
        String url = backendUrl + "/remoteLogin";

        HttpPost request = new HttpPost(url);
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
                
        try
        {
            request.setEntity(new UrlEncodedFormEntity(params));
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            
            HttpResponse response = client.execute(request);
            checkResponseCode(response);

            HttpEntity entity = response.getEntity();
            user = inputStreamToString(entity.getContent());
        }
        catch (Exception e)
        {
            throw new StackException(e);
            
        }
        finally
        {
            request.releaseConnection();
        }
    }   
    
    /**
     * Submit to the stack.
     * @param id The submission identifier
     * @param version The submission version
     * @param name The submission name
     * @param description The 
     * @param citations The pubMedIds for this submission
     * @param keywords A comma separated list of keywords
     * @param overwrite_merge '0' prevent, '1' overwrite, '2' merge
     * @param document 
     * 
     * @throws StackException if there was an error communicating with the stack
     */
    public void submit(String id, String version, String name, String description, String citations,
    		String keywords, String overwrite_merge, SBOLDocument document) throws StackException
    {
    	if (user==null) {
    		Exception e = new Exception("Must be logged in to submit.");
    		throw new StackException(e);
    	}
        String url = backendUrl + "/remoteSubmit";

        HttpPost request = new HttpPost(url);
        
        MultipartEntity params = new MultipartEntity();
        try {
			params.addPart("id", new StringBody(id));
	        params.addPart("version", new StringBody(version));
	        params.addPart("name", new StringBody(name));
	        params.addPart("description", new StringBody(description));
	        params.addPart("citations", new StringBody(citations));
	        params.addPart("keywords", new StringBody(keywords));
	        params.addPart("overwrite_merge", new StringBody(overwrite_merge));
	        params.addPart("user", new StringBody(user));
	        params.addPart("file", new StringBody(serializeDocument(document)));
		}
		catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new StackException(e1);
		}
	        
        try
        {
            request.setEntity(params);
            //request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(request);
            checkResponseCode(response);
        }
        catch (Exception e)
        {
        	//e.printStackTrace();
            throw new StackException(e);
            
        }
        finally
        {
            request.releaseConnection();
        }
    }   
     
    private String serializeDocument(SBOLDocument document) throws StackException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        try
        {
            SBOLWriter.write(document,  outputStream);
            
            return outputStream.toString("UTF-8");
        }
        catch(Exception e)
        {
            throw new StackException("Error serializing SBOL document", e);
        }
        
    }

    private TopLevel fetchTopLevelFromStack(String url, URI topLevelUri) throws StackException
    {
    	HttpStream stream;
        
        try
        {
            stream = fetchContentAsInputStream(url);
        }
        catch (Exception e)
        {
            throw new StackException("Error connecting to stack endpoint", e);
        }
        
        SBOLDocument document;
        
        try
        {
            document = SBOLReader.read(stream.inputStream);
        }
        catch (Exception e)
        {
            throw new StackException("Error reading SBOL", e);
        }
        finally
        {
        	stream.request.releaseConnection();
        }
        
        TopLevel topLevel = document.getTopLevel(topLevelUri);
        
        if(topLevel == null)
        {
            throw new StackException("Matching top-level not found in response");
        }
        
        return topLevel;
    }

    private int fetchCount(String url) throws StackException
    {
        try
        {
            return Integer.parseInt(fetchContentAsString(url));
        }
        catch(Exception e)
        {
            throw new StackException(e);    
        }
    }
    
    private String fetchContentAsString(String url) throws StackException, IOException
    {
    	HttpStream stream = fetchContentAsInputStream(url);
       
    	String str;
    	
    	try
    	{
    		str = inputStreamToString(stream.inputStream);
    	}
    	finally
    	{
    		stream.request.releaseConnection();        	
    	}
    	
    	return str;
    }

    private String inputStreamToString(InputStream inputStream) throws IOException
    {
        StringWriter writer = new StringWriter();

        IOUtils.copy(inputStream, writer);
        
        return writer.toString();
    }
    
    class HttpStream
    {
    	public InputStream inputStream;
    	public HttpRequestBase request;
    }
    
    private HttpStream fetchContentAsInputStream(String url) throws StackException, IOException
    {
		HttpGet request = new HttpGet(url);
		
    	try
    	{
			HttpResponse response = client.execute(request);
	
			checkResponseCode(response);
	        
			HttpStream res = new HttpStream();
			
			res.inputStream = response.getEntity().getContent();
			res.request = request;
			
			return res;
    	}
    	catch(StackException e)
    	{
    		request.releaseConnection();
    		
    		throw e;
    	}
    	catch(IOException e)
    	{
    		request.releaseConnection();
    		
    		throw e;
    	}
    }
    
    private String storeUriFragment(String storeName)
    {
        return storeName != null ? "/store/" + storeName : "";
    }

    private String encodeUri(URI uri)
    {
        try
        {
            return URLEncoder.encode(uri.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }
    
    private void checkResponseCode(HttpResponse response) throws StackException
    {
        int statusCode = response.getStatusLine().getStatusCode();
                
        if(statusCode >= 300)
        {
            switch(statusCode)
            {
            case 401:
                throw new PermissionException();
            
            case 404:
                throw new NotFoundException();
            
            default:
            	HttpEntity entity = response.getEntity();
                try {
					throw new StackException(inputStreamToString(entity.getContent()));
				}
				catch (UnsupportedOperationException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
            }
        }
    }
}

