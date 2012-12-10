package org.sbolstandard.core.impl;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.Merger;
import org.sbolstandard.core.SequenceAnnotation;

import java.net.URI;
import java.util.ArrayList;

/**
 * Merger for DnaComponent instances.
 *
 * @author Matthew Pocock
 */
public class DnaComponentMerger extends AbstractMerger<DnaComponent> {
    public static final Merger<DnaComponent> IDENTITY = new MergeIdentical<DnaComponent>();
    private static final Merger<URI> identicalUri = new MergeIdentical<URI>();

    private Merger<DnaSequence> dnaSequenceMerger;
    private Merger<SequenceAnnotation> annotationMerger = new MergeIdentical<SequenceAnnotation>();

    public DnaComponentMerger() {
    }

    public DnaComponentMerger(Merger<DnaSequence> dnaSequenceMerger, Merger<SequenceAnnotation> annotationMerger) {
        this.dnaSequenceMerger = dnaSequenceMerger;
        this.annotationMerger = annotationMerger;
    }

    public Merger<SequenceAnnotation> getAnnotationMerger() {
        return annotationMerger;
    }

    public void setAnnotationMerger(Merger<SequenceAnnotation> annotationMerger) {
        this.annotationMerger = annotationMerger;
    }

    public Merger<DnaSequence> getDnaSequenceMerger() {
        return dnaSequenceMerger;
    }

    public void setDnaSequenceMerger(Merger<DnaSequence> dnaSequenceMerger) {
        this.dnaSequenceMerger = dnaSequenceMerger;
    }

    @Override
    public DnaComponent merge(DnaComponent e1, DnaComponent e2) {
        DnaComponentImpl m = new DnaComponentImpl();

        m.setDescription(nullSafeIdentical(e1.getDescription(), e2.getDescription(), "description"));
        m.setDisplayId(nullSafeIdentical(e1.getDisplayId(), e2.getDisplayId(), "displayId"));
        m.setDnaSequence(nullSafeMerge(e1.getDnaSequence(), e2.getDnaSequence(), "dnaSequence", dnaSequenceMerger));
        m.setName(nullSafeIdentical(e1.getName(), e2.getName(), "name"));
        m.setURI(nullSafeIdentical(e1.getURI(), e2.getURI(), "uri"));

        java.util.Collection<SequenceAnnotation> ac = nullSafeCollectionMerge(
                e1.getAnnotations(), e2.getAnnotations(), "annotations",
                new ArrayList<SequenceAnnotation>(), annotationMerger);
        for(SequenceAnnotation a : ac) {
            m.addAnnotation(a);
        }

        java.util.Collection<URI> tp = nullSafeCollectionMerge(
                e1.getTypes(), e2.getTypes(), "types",
                new ArrayList<URI>(), identicalUri);
        for(URI t : tp) {
            m.addType(t);
        }

        return m;
    }
}
