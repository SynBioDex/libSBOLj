package org.sbolstandard.core;

import java.net.URI;

/**
 * resolve URIs to SBOL objects.
 *
 * @author Matthew Pocock
 */
public interface UriResolver<E extends SBOLObject> extends Resolver<E, URI> {
    /**
     * Resolve the uri, or return null if this resolver doesn't know anyting about it.
     * The object returned will have a getURI() method. The value of this must equal the
     * uri used for querying.
     *
     * @param uri
     * @return
     */
    E resolve(URI uri);
}
