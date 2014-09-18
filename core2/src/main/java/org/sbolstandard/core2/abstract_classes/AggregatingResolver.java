package org.sbolstandard.core2.abstract_classes;


import org.sbolstandard.core2.MergerException;
import org.sbolstandard.core2.interfaces.Merger;
import org.sbolstandard.core2.interfaces.Resolver;

import java.util.ArrayList;
import java.util.List;

/**
 * A resolver that uses the results from multiple child resolvers.
 *
 * @author Matthew Pocock
 * @version 2.0
 */
public abstract class AggregatingResolver<E, ID> implements Resolver<E, ID> {
    private List<Resolver<E, ID>> resolvers;

    public AggregatingResolver() {
        resolvers = new ArrayList<Resolver<E, ID>>();
    }

    public AggregatingResolver(List<Resolver<E, ID>> resolvers) {
        this.resolvers = resolvers;
    }

    public List<Resolver<E, ID>> getResolvers() {
        return resolvers;
    }

    public void setResolvers(List<Resolver<E, ID>> resolvers) {
        this.resolvers = resolvers;
    }

    @Override
    public E resolve(ID id) throws MergerException {
        E found = null;

        for(Resolver<E, ID> r : resolvers) {
            E e = r.resolve(id);
            if(e != null) {
                if(stopSearching(id, e)) {
                    return e;
                } else if(found == null) {
                    found = e;
                } else {
                    found = getMerger().merge(found, e);
                }
            }
        }

        return found;
    }

    protected abstract boolean stopSearching(ID id, E entity);

    protected abstract Merger<E> getMerger();

    public static class UseFirstFound<E, ID> extends AggregatingResolver<E, ID> {
        @Override
        protected boolean stopSearching(ID id, E entity) {
            return true;
        }

        @Override
        protected Merger<E> getMerger() {
            throw new UnsupportedOperationException("First found resolver does not merge");
        }
    }
}
