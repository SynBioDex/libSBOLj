package org.synbiohub.frontend;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.TopLevel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Provides a Java API to SynBioHub instances.
 * @author James McLaughlin
 * @author Chris Myers
 *
 */
public class SynBioHubFrontend
{
    PoolingHttpClientConnectionManager connectionManager;
    HttpClient client;
    String backendUrl;
    String uriPrefix;
    String user = "";

    /**
     * Creates an instance of the SynBioHub API.
     * @param backendUrl - URL for the SynBioHub instance.
     * @param uriPrefix - prefix for all URIs stored in this repository
     */
    public SynBioHubFrontend(String backendUrl,String uriPrefix)
    {
        this.backendUrl = backendUrl;
        this.uriPrefix = uriPrefix;

        connectionManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    /**
     * Creates an instance of the SynBioHub API.
     * @param backendUrl - URL for the SynBioHub instance.
     */
    public SynBioHubFrontend(String backendUrl)
    {
        this.backendUrl = backendUrl;
        this.uriPrefix = backendUrl;

        connectionManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    /**
     * Returns the URL for the SynBioHub instance.
     * @return the URL for the SynBioHub instance.
     */
    public String getBackendUrl()
    {
        return this.backendUrl;
    }

    /**
     * Return the total number of objects of a specified type in the repository.
     *
     * @return the total number of objects of a specified type in the repository.
     *
     * @param objectType The object type to count 
     * (Collection, ComponentDefinition, Sequence, ModuleDefinition, Model, etc.).
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */ 
    public int getCount(String objectType) throws SynBioHubException
    {
        return fetchCount(backendUrl + "/" + objectType + "/count");
    }
    
    /**
     * Retrieve SBOL TopLevel object from a SynBioHub instance using its URI.
     *
     * @param topLevelUri The URI of the SBOL TopLevel
     *
     * @return A libSBOLj TopLevel instance corresponding to the TopLevel
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public SBOLDocument getSBOL(URI topLevelUri) throws SynBioHubException
    {
        if (!topLevelUri.toString().startsWith(uriPrefix)) {
        	throw new SynBioHubException("Object URI does not start with correct URI prefix for this repository.");
        }
        String url = topLevelUri + "/sbol";
        url = url.replace(uriPrefix, backendUrl);

        SBOLDocument document = fetchFromSynBioHub(url);

        return document;
    }
    
    /**
     * Remove SBOL TopLevel object from a SynBioHub instance using its URI.
     *
     * @param topLevelUri The URI of the SBOL TopLevel
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public void removeSBOL(URI topLevelUri) throws SynBioHubException
    {
        if (!topLevelUri.toString().startsWith(uriPrefix)) {
        	throw new SynBioHubException("Object URI does not start with correct URI prefix for this repository.");
        }
        String url = topLevelUri + "/remove";
        url = url.replace(uriPrefix, backendUrl);

        fetchFromSynBioHub(url);
    }

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
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public ArrayList<IdentifiedMetadata> getMatchingComponentDefinitionMetadata(String name, Set<URI> roles, 
    		Set<URI> types, Set<URI> collections, Integer offset, Integer limit)
            throws SynBioHubException
    {
    	SearchQuery query = new SearchQuery();

    	query.setOffset(offset);
    	query.setLimit(limit);

    	SearchCriteria objectCriteria = new SearchCriteria();
    	objectCriteria.setKey("objectType");
    	objectCriteria.setValue("ComponentDefinition");
    	query.addCriteria(objectCriteria);
    	if (roles != null) {
    		for(URI uri : roles)
    		{
    			SearchCriteria roleCriteria = new SearchCriteria();

    			roleCriteria.setKey("role");
    			roleCriteria.setValue(uri.toString());

    			query.getCriteria().add(roleCriteria);
        	}	
        }
        
        if (types != null) {
        	for(URI uri : types)
        	{
        		SearchCriteria typeCriteria = new SearchCriteria();

        		typeCriteria.setKey("type");
        		typeCriteria.setValue(uri.toString());

        		query.getCriteria().add(typeCriteria);
        	}
        }
        
        if (collections != null) {
        	for(URI uri : collections)
        	{
        		SearchCriteria collectionCriteria = new SearchCriteria();

        		collectionCriteria.setKey("collection");
        		collectionCriteria.setValue(uri.toString());

        		query.getCriteria().add(collectionCriteria);
        	}
        }

        if(name != null)
        {
            SearchCriteria nameCriteria = new SearchCriteria();

            nameCriteria.setKey("name");
            nameCriteria.setValue(name);

            query.getCriteria().add(nameCriteria);
        }
    	return search(query);
    }

    /**
     * Search this SynBioHub instance for objects matching a search query
     * 
     * @param query the search query
     *
     * @return An ArrayList of MetaData for objects that match the specified search query
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public ArrayList<IdentifiedMetadata> search(SearchQuery query) throws SynBioHubException
    {
        String url = backendUrl + "/search/";

        //query.offset = offset;
        //query.limit = limit;

        String textQuery = "";
        boolean first = true;
        for (SearchCriteria criteria : query.getCriteria()) {
        	if (criteria.getKey().equals("objectType")) {
        		url += encodeUri(criteria.getKey()+"="+criteria.getValue()+"&");
        		continue;
        	}
        	if (criteria.getKey().equals("name")) {
        		if (first) first = false;
        		else textQuery = " ";
        		textQuery = criteria.getValue();
        		continue;
        	} 
        	if (criteria.getKey().startsWith("http")) {
        		url += encodeUri("<" + criteria.getKey() + ">=");
        	} else {
        		url += encodeUri(criteria.getKey()+"=");
        	}
        	if (criteria.getValue().startsWith("http")) {
        		url += encodeUri("<"+criteria.getValue()+">&");
        	} else {
        		url += encodeUri("'"+criteria.getValue()+"'&");
        	}
        }
        url += encodeUri(textQuery);
        if (query.getOffset()!=null && query.getLimit()!=null) {
        	url += "/?offset="+query.getOffset() + "&" + "limit="+query.getLimit();
        } else if (query.getOffset()!=null) {
        	url += "/?offset="+query.getOffset();
        } else if (query.getLimit()!=null) {
        	url += "/?limit="+query.getLimit();
        }

       	//System.out.println(url);
        Gson gson = new Gson();

        HttpGet request = new HttpGet(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "text/plain");

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
            throw new SynBioHubException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }
    
    /**
     * Search the default store for Collections that are not members of any other Collections
     *
     * @return An ArrayList of CollectionMetaData objects with a summary of all matching Collections.
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */    
    public ArrayList<IdentifiedMetadata> getRootCollectionMetadata()
            throws SynBioHubException
    {
        String url = backendUrl + "/rootCollections";

        Gson gson = new Gson();

        HttpGet request = new HttpGet(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "text/plain");

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
            throw new SynBioHubException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }
    
    /**
     * Perform a SPARQL query
     * @param query SPARQL query string
     *
     * @return result as a JSON string
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */    
    public String sparqlQuery(String query) throws SynBioHubException
    {
        String url = backendUrl + "/sparql";

        url	+= "?query="+encodeUri(query);
        
        HttpGet request = new HttpGet(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "application/json");

        try
        {
            HttpResponse response = client.execute(request);

            checkResponseCode(response);

            InputStream inputStream = response.getEntity().getContent();
            
            String result = inputStreamToString(inputStream);

            return result;
        }
        catch (Exception e)
        {
            throw new SynBioHubException(e);
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
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */    
    public ArrayList<IdentifiedMetadata> getSubCollectionMetadata(URI parentCollectionUri)
            throws SynBioHubException
    {
        if (!parentCollectionUri.toString().startsWith(uriPrefix)) {
        	throw new SynBioHubException("Object URI does not start with correct URI prefix for this repository.");
        }
        String url = parentCollectionUri + "/subCollections";
        url = url.replace(uriPrefix, backendUrl);

        Gson gson = new Gson();
        HttpGet request = new HttpGet(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "text/plain");

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
            throw new SynBioHubException(e);
        }
        finally
        {
            request.releaseConnection();
        }
    }

//    /**
//     * Upload an SBOLDocument to the SynBioHub.
//     * 
//     * @param document The document to upload
//     *
//     * @throws SynBioHubException if there was an error communicating with the SynBioHub
//     */
//    public void upload(SBOLDocument document) throws SynBioHubException
//    {
//        String url = backendUrl;
//
//        HttpPost request = new HttpPost(url);
//                
//        try
//        {
//            request.setEntity(new StringEntity(serializeDocument(document)));
//            request.setHeader("Content-Type", "application/rdf+xml");
//            
//            HttpResponse response = client.execute(request);
//            
//            checkResponseCode(response);
//        }
//        catch (Exception e)
//        {
//            throw new SynBioHubException(e);
//        }
//        finally
//        {
//            request.releaseConnection();
//        }
//    }
    
    /**
	 * Sets the user to null to indicate that no user is logged in.
     */
    public void logout() 
    {
    	user = "";
    }

    /**
     * Login to the SynBioHub.
     * @param email The user's email
     * @param password The user's password
     * 
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public void login(String email, String password) throws SynBioHubException
    {    	
        String url = backendUrl + "/login";

        HttpPost request = new HttpPost(url);
        request.setHeader("Accept", "text/plain");

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
            throw new SynBioHubException(e);
            
        }
        finally
        {
            request.releaseConnection();
        }
    } 
    
	/**
	 * Remove all parts from this registry from a given SBOL document
	 * 
	 * @param document The document to remove all registry parts from
	 */
	public void removeRegistryParts(SBOLDocument document) {
		for (TopLevel topLevel : document.getTopLevels()) {
			if (topLevel.getIdentity().toString().startsWith(uriPrefix)) {
				try {
					document.removeTopLevel(topLevel);
				}
				catch (SBOLValidationException e) {
					// TODO: ignore for now
				}
			}	
		}
	}
    
    /**
     * Submit to the SynBioHub.
     * @param id The submission identifier
     * @param version The submission version
     * @param name The submission name
     * @param description The submission description
     * @param citations The pubMedIds for this submission
     * @param collections A comma separated list of collections
     * @param overwrite_merge '0' prevent, '1' overwrite, '2' merge and prevent, '3' merge and overwrite
     * @param document the SBOL document to submit
     * 
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public void submit(String id, String version, String name, String description, String citations,
    		String collections, String overwrite_merge, SBOLDocument document) throws SynBioHubException
    {
    	InputStream sbolDoc = new ByteArrayInputStream(serializeDocument(document).getBytes());
    	submit(id, version, name, description, citations, collections, overwrite_merge, sbolDoc);
    }   
    
    public void submit(String id, String version, String name, String description, String citations,
    		String collections, String overwrite_merge, String fileToUpload) throws SynBioHubException, IOException
    {
    	if(fileToUpload != null){
    		submit(id, version, name, description, citations, collections, overwrite_merge, new File(fileToUpload));  
    	}
    }
    
    public void submit(String id, String version, String name, String description, String citations,
    		String collections, String overwrite_merge, File fileToUpload) throws SynBioHubException, IOException
    {

        	InputStream stream = new FileInputStream(fileToUpload);	  
        	submit(id, version, name, description, 
        			citations, collections, overwrite_merge, stream); 
    	    stream.close();
    }   
    
    public void submit(String id, String version, String name, String description, String citations,
    		String collections, String overwrite_merge, InputStream fileToUpload) throws SynBioHubException
    {
    	if (user.equals("")) 
    	{
    		Exception e = new Exception("Must be logged in to submit.");
    		throw new SynBioHubException(e);
    	}
    	
        String url = backendUrl + "/submit";
        HttpPost request = new HttpPost(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "text/plain");

        MultipartEntity params = new MultipartEntity();
        StringBody filebody = null; 
        try {
			params.addPart("id", new StringBody(id));
	        params.addPart("version", new StringBody(version));
	        params.addPart("name", new StringBody(name));
	        params.addPart("description", new StringBody(description));
	        params.addPart("citations", new StringBody(citations));
	        params.addPart("collectionChoices", new StringBody(collections));
	        params.addPart("overwrite_merge", new StringBody(overwrite_merge));
	        params.addPart("user", new StringBody(user));
	        if (fileToUpload != null) {
	        	params.addPart("file", submitData(fileToUpload));
	        } else {
	        	params.addPart("file", new StringBody(""));
	        }
		}
		catch (UnsupportedEncodingException e1) {
			throw new SynBioHubException(e1);
		}
	        
        try
        {
            request.setEntity(params);
            HttpResponse response = client.execute(request);
            checkResponseCode(response);
        }
        catch (Exception e)
        {
        	//e.printStackTrace();
            throw new SynBioHubException(e);
            
        }
        finally
        {
            request.releaseConnection();
        }
    }   
     
    private String serializeDocument(SBOLDocument document) throws SynBioHubException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        try
        {
            SBOLWriter.write(document,  outputStream); 
            return outputStream.toString("UTF-8");
        }
        catch(Exception e)
        {
            throw new SynBioHubException("Error serializing SBOL document", e);
        }
    }
    
    private StringBody submitData(InputStream fileToUpload) throws SynBioHubException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  
        try
        {
        	IOUtils.copy(fileToUpload, outputStream);
            return new StringBody(outputStream.toString("UTF-8"));
        }
        catch(Exception e)
        {
            throw new SynBioHubException("Error serializing data", e);
        }        
    }

    private SBOLDocument fetchFromSynBioHub(String url) throws SynBioHubException
    {
    	HttpStream stream;
        
        try
        {
            stream = fetchContentAsInputStream(url);
        }
        catch (Exception e)
        {
            throw new SynBioHubException("Error connecting to SynBioHub endpoint", e);
        }
        
        SBOLDocument document;
        
        try
        {
            document = SBOLReader.read(stream.inputStream);
        }
        catch (Exception e)
        {
            throw new SynBioHubException("Error reading SBOL", e);
        }
        finally
        {
        	stream.request.releaseConnection();
        }
        
        //TopLevel topLevel = document.getTopLevel(topLevelUri);
        
        //if(topLevel == null)
        //{
        //    throw new SynBioHubException("Matching top-level not found in response");
        //}
        
        return document;
    }

    private int fetchCount(String url) throws SynBioHubException
    {
        try
        {
            return Integer.parseInt(fetchContentAsString(url));
        }
        catch(Exception e)
        {
            throw new SynBioHubException(e);    
        }
    }
    
    private String fetchContentAsString(String url) throws SynBioHubException, IOException
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
    
    private HttpStream fetchContentAsInputStream(String url) throws SynBioHubException, IOException
    {
		HttpGet request = new HttpGet(url);
        request.setHeader("X-authorization", user);
        request.setHeader("Accept", "text/plain");

    	try
    	{
			HttpResponse response = client.execute(request);
	
			checkResponseCode(response);
	        
			HttpStream res = new HttpStream();
			
			res.inputStream = response.getEntity().getContent();
			res.request = request;
			
			return res;
    	}
    	catch(SynBioHubException e)
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

    private String encodeUri(String uri)
    {
        try
        {
            return URLEncoder.encode(uri, "UTF-8").replace("+", "%20");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }
    
    private void checkResponseCode(HttpResponse response) throws SynBioHubException
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
					throw new SynBioHubException(inputStreamToString(entity.getContent()));
				}
				catch (UnsupportedOperationException | IOException e) {
					throw new SynBioHubException(statusCode+"");
				}
            }
        }
    }
}

