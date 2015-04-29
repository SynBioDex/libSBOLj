package org.sbolstandard.core.impl;

import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.Merger;
import org.sbolstandard.core.MergerException;

/**
 * Merge DNA sequences.
 *
 * @author Matthew Pocock
 */
public class DnaSequenceMerger extends AbstractMerger<DnaSequence> {

    public static Merger<DnaSequence> IDENTITY = new MergeIdentical<>();

    @Override
    public DnaSequence merge(DnaSequence e1, DnaSequence e2) throws MergerException {
        DnaSequenceImpl m = new DnaSequenceImpl();

        m.setURI(nullSafeIdentical(e1.getURI(), e2.getURI(), "uri"));
        m.setNucleotides(nullSafeIdentical(e1.getNucleotides(), e2.getNucleotides(), "nucleotides"));

        return m;
    }
}
