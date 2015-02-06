package org.sbolstandard.core2.interfaces;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Identified;

/**
 * resolve URIs to SBOL objects.
 *
 * @author Matthew Pocock
 */
public interface UriResolver<E extends Identified> extends Resolver<E, URI> {
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
