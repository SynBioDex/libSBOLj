package org.sbolstandard.core;

/**
 * A full bundle of resolvers.
 *
 * @author Matthew Pocock
 */
public interface SbolResolver {
    public UriResolver<Collection> getCollectionUriResolver();
    public UriResolver<DnaComponent> getComponentUriResolver();
    public UriResolver<DnaSequence> getSequenceUriResolver();
    public UriResolver<SequenceAnnotation> getAnnotationUriResolver();

    public DisplayIdResolver<Collection> getCollectionDisplayIdResolver();
    public DisplayIdResolver<DnaComponent> getComponentDisplayIdResolver();
}
