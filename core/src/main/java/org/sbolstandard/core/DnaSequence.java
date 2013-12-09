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

/**
 * The SBOL data model's DnaSequence.
 *
 * DNA Sequence holds either the actual sequence string or a reference pointer,
 * a URI to it. The SBOL data model is focused on the description of these DNA
 * sequences as they used in assembly of new synthetic biological systems.
 * Information specifying the exact base pair sequence of DNA components and
 * Sequence Features is very important for the ability to replicate synthetic
 * biology work. Both experimental work and theoretical sequence composition
 * research heavily depends on this information.
 */
public interface DnaSequence extends SBOLRootObject {
    /**
     * The sequence of DNA base pairs which are described.
     * @return a string representation of the DNA base-pair sequence
     */
    public String getNucleotides();

    /**
     * The sequence of DNA base pairs which are going to be described.
     *
     *  a.The DNA sequence will use the IUPAC ambiguity recommendation. (See
     * http://www.genomatix.de/online_help/help/sequence_formats.html)
     * b.Blank lines, spaces, or other symbols must not be included in the
     * sequence text.
     * c.The sequence text must be in ASCII or UTF-8 encoding. For the alphabets
     * used, the two are identical.
     *
     * @param nucleotides a sequence of [a|c|t|g] letters
     */
    public void setNucleotides(String nucleotides);
}
