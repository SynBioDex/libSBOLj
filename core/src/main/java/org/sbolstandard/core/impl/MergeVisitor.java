package org.sbolstandard.core.impl;

import org.sbolstandard.core.*;

/**
 * A visitor that merges according to the type.
 *
 * @author Matthew Pocock
 */
public class MergeVisitor implements SBOLVisitor<MergerException> {

    private Merger<Collection> collectionMerger = new Merger.MergeNullWrapper<>(new CollectionMerger() {
        @Override
        public Merger<DnaComponent> getComponentMerger() {
            return dnaComponentMerger;
        }
    });

    private Merger<DnaComponent> dnaComponentMerger = new Merger.MergeNullWrapper<>(new DnaComponentMerger() {
        @Override
        public Merger<SequenceAnnotation> getAnnotationMerger() {
            return sequenceAnnotationMerger;
        }

        @Override
        public Merger<DnaSequence> getDnaSequenceMerger() {
            return dnaSequenceMerger;
        }
    });

    private Merger<DnaSequence> dnaSequenceMerger = new Merger.MergeNullWrapper<>(new DnaSequenceMerger());

    private Merger<SequenceAnnotation> sequenceAnnotationMerger = new Merger.MergeNullWrapper<>(new SequenceAnnotationMerger() {
        @Override
        public Merger<DnaComponent> getSubComponentMerger() {
            return dnaComponentMerger;
        }

        @Override
        public Merger<SequenceAnnotation> getPrecedesMerger() {
            return sequenceAnnotationMerger;
        }
    });

    private SBOLObject merged;

    public MergeVisitor(SBOLObject merged) {
        this.merged = merged;
    }

    public SBOLObject getMerged() {
        return merged;
    }

    @Override
    public void visit(SBOLDocument doc) {
        throw new UnsupportedOperationException("Can not merge documents");
    }

    @Override
    public void visit(Collection coll) throws MergerException {
        merged = collectionMerger.merge(coll, (Collection) merged);
    }

    @Override
    public void visit(DnaComponent component) throws MergerException {
        merged = dnaComponentMerger.merge(component, (DnaComponent) merged);
    }

    @Override
    public void visit(DnaSequence sequence) throws MergerException {
        merged = dnaSequenceMerger.merge(sequence, (DnaSequence) merged);
    }

    @Override
    public void visit(SequenceAnnotation annotation) throws MergerException {
        merged = sequenceAnnotationMerger.merge(annotation, (SequenceAnnotation) merged);
    }
}
