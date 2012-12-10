package org.sbolstandard.core.impl;

import org.sbolstandard.core.Merger;

import java.util.Collection;

/**
 * An abstract class for merging SBOL entities, providing utilities to implementations.
 *
 * @author Matthew Pocock
 */
public abstract class AbstractMerger<E> implements Merger<E> {

    protected <A> A nullSafeIdentical(A a1, A a2, String fieldName) {
        return nullSafeMerge(a1, a2, fieldName, new MergeIdentical<A>());
    }

    protected <A, C extends Collection<A>> C nullSafeCollectionMerge(C c1, C c2, String fieldName, C empty, Merger<A> merger) {
        OUTER:
        for(A a1 : c1) {
            for(A a2 : c2) {
                if(a1.equals(a2)) {
                    empty.add(merger.merge(a1, a2));
                    continue OUTER;
                }
            }

            empty.add(a1);
        }

        for(A a2 : c2) {
            if(! c1.contains(a2)) {
                empty.add(a2);
            }
        }

        return empty;
    }

    protected <A> A nullSafeMerge(A a1, A a2, String fieldName, Merger<A> merger) {
        if(a1 != null && a2 != null) {
            try {
                return merger.merge(a1, a2);
            } catch (IllegalArgumentException iae) {
                throw new IllegalArgumentException("Could not merge field " + fieldName, iae);
            }
        } else if(a1 != null) {
            return a1;
        } else {
            return a2;
        }
    }


}
