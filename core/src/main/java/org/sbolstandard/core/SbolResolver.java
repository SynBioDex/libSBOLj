package org.sbolstandard.core;

/**
 * A full bundle of resolvers.
 *
 * @author Matthew Pocock
 */
@Deprecated
public interface SbolResolver {
    UriResolver<Collection> getCollectionUriResolver();
    UriResolver<DnaComponent> getComponentUriResolver();
    UriResolver<DnaSequence> getSequenceUriResolver();
    UriResolver<SequenceAnnotation> getAnnotationUriResolver();

    DisplayIdResolver<Collection> getCollectionDisplayIdResolver();
    DisplayIdResolver<DnaComponent> getComponentDisplayIdResolver();
}
