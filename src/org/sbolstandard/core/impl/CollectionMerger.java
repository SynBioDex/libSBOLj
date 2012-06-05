package org.sbolstandard.core.impl;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.Merger;

import java.util.ArrayList;

/**
 * Merge collections.
 *
 * @author Matthew Pocock
 */
public class CollectionMerger extends AbstractMerger<Collection> {
    public static final Merger<CollectionMerger> IDENTITY = new MergeIdentical<CollectionMerger>();

    private Merger<DnaComponent> componentMerger = DnaComponentMerger.IDENTITY;

    public CollectionMerger() {
    }

    public CollectionMerger(Merger<DnaComponent> componentMerger) {
        this.componentMerger = componentMerger;
    }

    public Merger<DnaComponent> getComponentMerger() {
        return componentMerger;
    }

    public void setComponentMerger(Merger<DnaComponent> componentMerger) {
        this.componentMerger = componentMerger;
    }

    @Override
    public Collection merge(Collection e1, Collection e2) {
        CollectionImpl m = new CollectionImpl();

        m.setDescription(nullSafeIdentical(e1.getDescription(), e2.getDescription(), "description"));
        m.setDisplayId(nullSafeIdentical(e1.getDisplayId(), e2.getDisplayId(), "displayId"));
        m.setName(nullSafeIdentical(e1.getName(), e2.getName(), "name"));
        m.setURI(nullSafeIdentical(e1.getURI(), e2.getURI(), "uri"));

        componentMerger = new MergeIdentical<DnaComponent>();
        java.util.Collection<DnaComponent> dc = nullSafeCollectionMerge(
                e1.getComponents(), e2.getComponents(), "components",
                new ArrayList<DnaComponent>(), componentMerger);
        for(DnaComponent c : dc) {
            m.addComponent(c);
        }

        return m;
    }

}
