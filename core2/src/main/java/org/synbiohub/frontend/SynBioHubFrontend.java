
package org.synbiohub.frontend;

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
    String user = null;

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
     * Return the total number of TopLevels of a specified type in the repository.
     *
     * @return the total number of TopLevels of a specified type in the repository.
     *
     * @param topLevelType The TopLevel type to count 
     * (Collection, ComponentDefinition, Sequence, ModuleDefinition, Model).
     *
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */ 
    public int getCountTopLevels(String topLevelType) throws SynBioHubException
    {
        return fetchCount(backendUrl + "/" + topLevelType + "/count");
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
        String url = backendUrl + "/component/search/metadata";

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
        String url = backendUrl + "/collection/roots";

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
        String url = backendUrl + "/collection/" + encodeUri(parentCollectionUri) + "/subCollections";

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
    	user = null;
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
            throw new SynBioHubException(e);
            
        }
        finally
        {
            request.releaseConnection();
        }
    }   
    
    /**
     * Submit to the SynBioHub.
     * @param id The submission identifier
     * @param version The submission version
     * @param name The submission name
     * @param description The 
     * @param citations The pubMedIds for this submission
     * @param keywords A comma separated list of keywords
     * @param overwrite_merge '0' prevent, '1' overwrite, '2' merge
     * @param document 
     * 
     * @throws SynBioHubException if there was an error communicating with the SynBioHub
     */
    public void submit(String id, String version, String name, String description, String citations,
    		String keywords, String overwrite_merge, SBOLDocument document) throws SynBioHubException
    {
    	if (user==null) {
    		Exception e = new Exception("Must be logged in to submit.");
    		throw new SynBioHubException(e);
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

