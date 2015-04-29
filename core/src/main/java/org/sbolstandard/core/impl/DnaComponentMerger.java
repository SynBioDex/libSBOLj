package org.sbolstandard.core.impl;

import org.sbolstandard.core.*;

import java.net.URI;
import java.util.ArrayList;

/**
 * Merger for DnaComponent instances.
 *
 * @author Matthew Pocock
 */
public abstract class DnaComponentMerger extends AbstractMerger<DnaComponent> {
    public static final Merger<DnaComponent> IDENTITY = new MergeIdentical<>();
    private static final Merger<URI> identicalUri = new MergeIdentical<>();

    public DnaComponentMerger() {
    }

    public abstract Merger<SequenceAnnotation> getAnnotationMerger();

    public abstract Merger<DnaSequence> getDnaSequenceMerger();

    @Override
    public DnaComponent merge(DnaComponent e1, DnaComponent e2) throws MergerException {
        DnaComponentImpl m = new DnaComponentImpl();

        m.setDescription(nullSafeIdentical(e1.getDescription(), e2.getDescription(), "description"));
        m.setDisplayId(nullSafeIdentical(e1.getDisplayId(), e2.getDisplayId(), "displayId"));
        m.setDnaSequence(nullSafeMerge(e1.getDnaSequence(), e2.getDnaSequence(), "dnaSequence", getDnaSequenceMerger()));
        m.setName(nullSafeIdentical(e1.getName(), e2.getName(), "name"));
        m.setURI(nullSafeIdentical(e1.getURI(), e2.getURI(), "uri"));

        java.util.Collection<SequenceAnnotation> ac = nullSafeCollectionMerge(
                e1.getAnnotations(), e2.getAnnotations(), "annotations",
                new ArrayList<SequenceAnnotation>(), getAnnotationMerger());
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
