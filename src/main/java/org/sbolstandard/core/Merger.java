package org.sbolstandard.core;

/**
 * Merge two SBOL entities.
 *
 * @author Matthew Pocock
 */
public interface Merger<E> {
    /**
     * Merge the two entities. The result should contain all the data in the two inputs.
     * If the data is incompattible, raise an exception.
     *
     * @param e1    the first entity
     * @param e2    the second entity
     * @return      a merge of the two
     */
    public E merge(E e1, E e2);
}
