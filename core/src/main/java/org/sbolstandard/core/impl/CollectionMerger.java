package org.sbolstandard.core.impl;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.Merger;
import org.sbolstandard.core.MergerException;

import java.util.ArrayList;

/**
 * Merge collections.
 *
 * @author Matthew Pocock
 */
public abstract class CollectionMerger extends AbstractMerger<Collection> {
    public static final Merger<Collection> IDENTITY = new MergeIdentical<>();

    public CollectionMerger() {
    }

    public abstract Merger<DnaComponent> getComponentMerger();

    @Override
    public Collection merge(Collection e1, Collection e2) throws MergerException {
        CollectionImpl m = new CollectionImpl();

        m.setDescription(nullSafeIdentical(e1.getDescription(), e2.getDescription(), "description"));
        m.setDisplayId(nullSafeIdentical(e1.getDisplayId(), e2.getDisplayId(), "displayId"));
        m.setName(nullSafeIdentical(e1.getName(), e2.getName(), "name"));
        m.setURI(nullSafeIdentical(e1.getURI(), e2.getURI(), "uri"));

        java.util.Collection<DnaComponent> dc = nullSafeCollectionMerge(
                e1.getComponents(), e2.getComponents(), "components",
                new ArrayList<DnaComponent>(), getComponentMerger());
        for(DnaComponent c : dc) {
            m.addComponent(c);
        }

        return m;
    }

}
