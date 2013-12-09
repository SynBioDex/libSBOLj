/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
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

package org.sbolstandard.core.util;

import java.net.URI;

/**
 * The Sequence Ontology is used in SBOL to specify the types of DnaComponents. This class provides constants for some
 * of these types which are commonly used in synthetic biology designs. Additional Sequence Ontology types can be found
 * at the website (<a href="http://www.sequenceontology.org/">http://www.sequenceontology.org/<a>). URIs for additional
 * types can be created with the {@link #type(String)} function by providing only the local name of the type.
 * 
 * @author Evren Sirin
 */
public class SequenceOntology {
	private SequenceOntology() {
	}

	/**
	 * Creates a new URI from the Sequence Ontology namespace with the given local name. For example, the function call
	 * <value>term("SO_0000001")</value> will return the URI <value>http://purl.obolibrary.org/obo/SO_0000001</value>
	 */
	public static final URI type(String localName) {
		return NAMESPACE.resolve(localName);
	}

	/**
	 * Namespace of the Sequence Ontology (<a href="http://purl.obolibrary.org/obo/">http://purl.obolibrary.org/obo/</a>).
	 */
	public static final URI NAMESPACE = URI.create("http://purl.obolibrary.org/obo/");

	/**
	 * A regulatory_region composed of the TSS(s) and binding sites for TF_complexes of the basal transcription
	 * machinery (<a href="http://purl.obolibrary.org/obo/SO_0000167">SO_0000167</a>).
	 */
	public static final URI PROMOTER = type("SO_0000167");

	/**
	 * A regulatory element of an operon to which activators or repressors bind, thereby effecting translation of genes
	 * in that operon (<a href="http://purl.obolibrary.org/obo/SO_0000057">SO_0000057</a>).
	 */
	public static final URI OPERATOR = type("SO_0000057");

	/**
	 * A contiguous sequence which begins with, and includes, a start codon, and ends with, and includes, a stop codon
	 * (<a href="http://purl.obolibrary.org/obo/SO_0000316">SO_0000316</a>).
	 */
	public static final URI CDS = type("SO_0000316");

	/**
	 * A region at the 5' end of a mature transcript (preceding the initiation codon) that is not translated into a
	 * protein (<a href="http://purl.obolibrary.org/obo/SO_0000204">SO_0000204</a>).
	 */
	public static final URI FIVE_PRIME_UTR = type("SO_0000204");

	/**
	 * The sequence of DNA located either at the end of the transcript that causes RNA polymerase to terminate
	 * transcription (<a href="http://purl.obolibrary.org/obo/SO_0000141">SO_0000141</a>).
	 */
	public static final URI TERMINATOR = type("SO_0000141");

	/**
	 * A transcriptional cis regulatory region that, when located between a CM and a gene's promoter, prevents the CRM
	 * from modulating that genes expression (<a href="http://purl.obolibrary.org/obo/SO_0000627">SO_0000627</a>)
	 */
	public static final URI INSULATOR = type("SO_0000627");

	/**
	 * The origin of replication; starting site for duplication of a nucleic acid molecule to give two identical copies
	 * (<a href="http://purl.obolibrary.org/obo/SO_0000296">SO_0000296</a>).
	 */
	public static final URI ORIGIN_OF_REPLICATION = type("SO_0000296");

	/**
	 * Non-covalent primer binding site for initiation of replication, transcription, or reverse transcription (<a
	 * href="http://purl.obolibrary.org/obo/SO_0005850">SO_0005850<a/>)
	 */
	public static final URI PRIMER_BINDING_SITE = type("SO_0005850");

	/**
	 * Represents a region of a DNA molecule which is a nucleotide region (usually a palindrome) that is recognized by a
	 * restriction enzyme (<a href="http://purl.obolibrary.org/obo/SO_0001687">SO_0001687</a>).
	 */
	public static final URI RESTRICTION_ENZYME_RECOGNITION_SITE = type("SO_0001687");

}
