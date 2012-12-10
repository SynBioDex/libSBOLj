package org.sbolstandard.core.impl;

import org.sbolstandard.core.Merger;

/**
 * A Merger strategy that requires the two values to be identical. Behaviour is undefined if either is null.
 *
 * @author Matthew Pocock
 */
public class MergeIdentical<E> implements Merger<E> {
    @Override
    public E merge(E e1, E e2) {
        if(e1.equals(e2)) {
            return e1;
        } else {
            throw new IllegalArgumentException("Can't merge non-identical values");
        }
    }
}
