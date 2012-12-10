package org.sbolstandard.core.impl;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.Merger;
import org.sbolstandard.core.SequenceAnnotation;

import java.util.ArrayList;

/**
 * Sequence annotation merger.
 *
 * @author Matthew Pocock
 */
public class SequenceAnnotationMerger extends AbstractMerger<SequenceAnnotation> {
    public static final Merger<SequenceAnnotation> IDENTITY = new MergeIdentical<SequenceAnnotation>();

    private Merger<DnaComponent> subComponentMerger = DnaComponentMerger.IDENTITY;
    private Merger<SequenceAnnotation> precedesMerger = IDENTITY;


    @Override
    public SequenceAnnotation merge(SequenceAnnotation e1, SequenceAnnotation e2) {
        SequenceAnnotationImpl m = new SequenceAnnotationImpl();

        m.setBioEnd(nullSafeIdentical(e1.getBioEnd(), e2.getBioEnd(), "bioEnd"));
        m.setBioStart(nullSafeIdentical(e1.getBioStart(), e2.getBioStart(), "bioStart"));
        m.setStrand(nullSafeIdentical(e1.getStrand(), e2.getStrand(), "strand"));
        m.setSubComponent(nullSafeMerge(e1.getSubComponent(), e2.getSubComponent(), "subComponent", subComponentMerger));

        java.util.Collection<SequenceAnnotation> pm = nullSafeCollectionMerge(
                e1.getPrecedes(), e2.getPrecedes(), "precedes",
                new ArrayList<SequenceAnnotation>(), precedesMerger);
        for(SequenceAnnotation p : pm) {
            m.addPrecede(p);
        }

        return m;
    }
}
