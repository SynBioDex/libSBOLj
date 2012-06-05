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

import java.net.URI;
import java.util.Collection;
import java.util.List;

/**
 * The SBOL data model's DnaComponent.
 *
 * This objects of this type represent DNA components for biological engineering
 * which can be described by SequenceAnnotation objects and must specify their
 * DnaSequence object. DnaComponents are expected to be found inside
 * a SBOL Collection object.
 */

public interface DnaComponent extends SBOLNamedObject, SBOLRootObject {
    /**
     * Positions and directions of <code>SequenceFeature</code>[s] that describe
     * the DNA sequence.
     * @return 0 or more <code>SequenceAnnotation</code>[s] that describe the 
     * DNA composition
     * @see #addAnnotation
     */
    public List<SequenceAnnotation> getAnnotations();

    /**
     * New position and direction of a <code>SequenceFeature</code> that
     * describes the DNA sequence.
     * The DnaComponent could be left un-annotated, but that condition is not a very useful to users.
     * @param annotation a <code>SequenceAnnotation</code> that describes the DNA composition
     */
    public void addAnnotation(SequenceAnnotation annotation);
	public void removeAnnotation(SequenceAnnotation annotation);
	
    /**
     * Text which is for users to read and interpret what this component is.
     * (eg. engineered Lac promoter, repressible by LacI).
     * Could be lengthy, so it is the responsibility of the user application to
     * format and allow for arbitrary length.
     * @return Human readable text describing the component
     * @see #setDescription
     */
    public String getDescription();

    /**
     * Text which is written for users to read and interpret. 
     * It should describe what the component is used for and/or what it does.
     * Suggestion: it should provide information that cannot yet be represented in
     * the rest of the DNA components computable fields. Do not include <> tags
     * such as HTML or XML inside as that may break the RDF. Don't include {}
     * tags as that may break the Json.
     * @param description human readable text describing the component
     */
    public void setDescription(String description);

    /**
     * Identifier to display to users.
     * @return a human readable identifier
     */
    public String getDisplayId();

    /**
     * Identifier that users will see as reference to the DNA construct.
     * It should be unambiguous and is likely imported from source data. Otherwise
     * it should be generated.
     * It should be restricted to alphanumeric/underscore and starting with a
     * letter or underscore.
     * @param displayId a human readable identifier
     */
    public void setDisplayId(String displayId);

    /**
     * DNA sequence which this DnaComponent object represents.
     * @return 1 {@link DnaSequence} specifying the DNA sequence of this DnaComponent
     * @see DnaSequence
     */
    public DnaSequence getDnaSequence();

    /**
     * DNA sequence which this DnaComponent object represents.
     * @param dnaSequence specify the DnaSequence of this DnaComponent
     */
    public void setDnaSequence(DnaSequence dnaSequence);

    /**
     * The name is the most recognizable known identifier, it is often ambiguous.
     * (eg. pLac-O1) Useful for display to carry common meaning, see work on "shared
     * understanding" in CSCW field for more.
     * @return its name, commonly used to refer to this DnaComponent
     */
    public String getName();

    /**
     * Common name of DNA component, confers meaning of what it is.
     * (eg. pLac-O1) Often this name is the short meaningful string that is
     * informally used to identify the DNA component. Sometimes it is an acronym
     * which makes it likely to be short.
     * @param name its name, commonly used to refer to this DnaComponent (eg. pLac-O1)
     * @see #getName
     */
    public void setName(String name);

    /**
     * Sequence Ontology vocabulary provides a defined term for types of DNA
     * components.
     * TO DO: implement use of SO within libSBOLj.
     * @return a Sequence Ontology (SO) vocabulary term to describe the type of DnaComponent.
     */
    public Collection<URI> getTypes();

    /**
     * Sequence Ontology vocabulary provides a defined term for types of DNA
     * components.
     *
     * @param type Sequence Ontology URI specifying the type of the DnaComponent
     */
    public void addType(URI type);
	public void removeType(URI type);
}
