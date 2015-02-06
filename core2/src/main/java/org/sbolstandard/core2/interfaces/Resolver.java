package org.sbolstandard.core2.interfaces;

import org.sbolstandard.core2.MergerException;

/**
 * Generic resolution service.
 *
 * @type E   entity type that is resolved
 * @type ID  the identifier type.
 */
public interface Resolver<E, ID> {
    /**
     * Resolve the ID or return null.
     *
     * @param id    the identifier
     * @return      the resolution of the identifier, or null if it could not be resolved
     */
    public E resolve(ID id) throws MergerException;
}
