package org.sbolstandard.core;

/**
 * Merge two SBOL entities.
 *
 * @author Matthew Pocock
 */
@Deprecated
public interface Merger<E> {
    /**
     * Merge the two entities. The result should contain all the data in the two inputs.
     * If the data is incompattible, raise an exception.
     *
     * @param e1    the first entity
     * @param e2    the second entity
     * @return      a merge of the two
     */
    E merge(E e1, E e2) throws MergerException;

    class MergeNullWrapper<E> implements Merger<E> {
        private final Merger<E> delegate;

        public MergeNullWrapper(Merger<E> delegate) {
            this.delegate = delegate;
        }

        @Override
        public E merge(E e1, E e2) throws MergerException {
            if(e1 == null) return e2;
            if(e2 == null) return e1;
            return delegate.merge(e1, e2);
        }
    }
}
