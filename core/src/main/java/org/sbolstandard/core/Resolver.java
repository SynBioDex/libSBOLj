package org.sbolstandard.core;

/**
 * Generic resolution service.
 */
@Deprecated
public interface Resolver<E, ID> {
    /**
     * Resolve the ID or return null.
     *
     * @param id    the identifier
     * @return      the resolution of the identifier, or null if it could not be resolved
     */
    E resolve(ID id) throws MergerException;
}
