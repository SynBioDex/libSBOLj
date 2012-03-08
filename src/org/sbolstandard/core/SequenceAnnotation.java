/*
 * Copyright (c) 2012 Michal Galdzicki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core;

import java.util.List;

/**
 * The SBOL data model's SequenceAnnotation for RDF and Json.
 *
 * Sequence Annotations give the location and direction of SequenceFeatures of
 * a DnaComponent. The location is specified by the start and stop positions of
 * the Sequence Feature along the DNA sequence. We are using the convention that
 * the first base pair of a DNA sequence is position 1. This convention is
 * established by the broader Molecular biology community, in literature.
 * The direction of the feature is specified by the strand [+/-]. Sequences used
 * are by convention assumed 5' to 3', therefore the <code>+</code> strand is
 * 5' to 3' and the <code>-</code> strand is 3' to 5'.
 */
public interface SequenceAnnotation extends SBOLObject {
    public List<SequenceAnnotation> getPrecedes();
    public void addPrecede(SequenceAnnotation precede);
	public void removePrecede(SequenceAnnotation precede);

    public DnaComponent getSubComponent();
    public void setSubComponent(DnaComponent subComponent);

    /**
     * First position of the Sequence Feature being annotated.
     * Start coordinate is in terms of the DnaSequence of the DnaComponent
     * annotated.
     * @return positive integer coordinate of first base of the SequenceFeature.
     */
    public Integer getBioStart();
    public void setBioStart(Integer bioStart);

    /**
     * Last position of the Sequence Feature on the DnaComponent.
     * Coordinate in terms of the DnaSequence of the DnaComponent annotated.
     * @return positive integer coordinate of last base of the SequenceFeature
     */
    public Integer getBioEnd();
    public void setBioEnd(Integer bioEnd);

    /**
     * Orientation of feature is the + or - strand.
     * 
     * Sequences used are by convention assumed 5' to 3', therefore the 
     * <code>+</code> strand is 5' to 3' and the <code>-</code> strand 
     * is 3' to 5'.
     *
     * @return <code>+</code> if feature aligns in same direction as DnaComponent,
     *         <code>-</code> if feature aligns in opposite direction as DnaComponent.
     */
    public StrandType getStrand();
    public void setStrand(StrandType strand);
}
