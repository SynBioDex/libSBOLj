package org.sbolstandard.core.impl;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.Merger;
import org.sbolstandard.core.MergerException;
import org.sbolstandard.core.SequenceAnnotation;

import java.util.ArrayList;

/**
 * Sequence annotation merger.
 *
 * @author Matthew Pocock
 */
public abstract class SequenceAnnotationMerger extends AbstractMerger<SequenceAnnotation> {
    public static final Merger<SequenceAnnotation> IDENTITY = new MergeIdentical<>();

    public abstract Merger<DnaComponent> getSubComponentMerger();

    public abstract Merger<SequenceAnnotation> getPrecedesMerger();

    @Override
    public SequenceAnnotation merge(SequenceAnnotation e1, SequenceAnnotation e2) throws MergerException {
        SequenceAnnotationImpl m = new SequenceAnnotationImpl();

        m.setBioEnd(nullSafeIdentical(e1.getBioEnd(), e2.getBioEnd(), "bioEnd"));
        m.setBioStart(nullSafeIdentical(e1.getBioStart(), e2.getBioStart(), "bioStart"));
        m.setStrand(nullSafeIdentical(e1.getStrand(), e2.getStrand(), "strand"));
        m.setSubComponent(nullSafeMerge(e1.getSubComponent(), e2.getSubComponent(), "subComponent", getSubComponentMerger()));

        java.util.Collection<SequenceAnnotation> pm = nullSafeCollectionMerge(
                e1.getPrecedes(), e2.getPrecedes(), "precedes",
                new ArrayList<SequenceAnnotation>(), getPrecedesMerger());
        for(SequenceAnnotation p : pm) {
            m.addPrecede(p);
        }

        return m;
    }
}
