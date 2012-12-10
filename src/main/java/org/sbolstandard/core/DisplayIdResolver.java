package org.sbolstandard.core;

/**
 * Resolve display names to SBOL objects.
 *
 * @author Matthew Pocock
 */
public interface DisplayIdResolver<E extends SBOLNamedObject> extends Resolver<E, String> {
    /**
     * Resolve the displayId, or return null if this resolver doesn't know anything about it.
     * The object returned will have a getDisplayId() method. The value of this must equal the
     * displayId used for querying.
     *
     * @param displayId
     * @return
     */
    E resolve(String displayId);
}
