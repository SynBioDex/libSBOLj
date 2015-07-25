package org.sbolstandard.core2;

import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SequenceOntology {

	// the map that contains all SO terms 
	// will get initialized at the first access
	
	// EO: does the SO have a 1-1 key-value mapping?
	
	// private static Map<String, URI> so = null;
	
	private static final String URI_PREFIX = "http://identifiers.org/so/";
	
	/**
	 * Namespace of the Sequence Ontology (<a href="http://identifiers.org/so/">http://identifiers.org/so/</a>).
	 */
	public static final URI NAMESPACE = URI.create(URI_PREFIX);
	
	/*
	public static String getTerm(URI uri) {

		// if the SO terms have not been loaded,
		// then load them now
		if(null == so) {
			loadSO();
		}
		

		// EO: is there a more efficient solution?
		// the answer will depend on the answer to the question above
		if(so.containsValue(uri) && null != uri) {
			// here, we need to iterate over the SO terms
			for(String term : so.keySet()) {
				// if the URI of the current SO term matches the provided URI, 
				// then we return the term
				if(so.get(term).toString().equalsIgnoreCase(uri.toString())) {
					return term;
				}
			}
		}
		
		return null;
	}

	public static URI getURI(String term) {
		// if the SO terms have not been loaded,
		// then load them now
		if(null == so) {
			loadSO();
		}
		
		if(null != term) {
			return so.get(term.toUpperCase());
		}
		
		return null;
	}
	
	private static void loadSO() {

		// this needs to be enhanced, of course
		so = new HashMap<>();

		// types
		so.put("DNA", URI.create(URI_PREFIX + "SO:0000352"));		
		so.put("PROTEIN", URI.create(URI_PREFIX + "SO:0001217"));
		so.put("COMPLEX", URI.create(URI_PREFIX + "SO:0001784"));
		so.put("GENE", URI.create(URI_PREFIX + "SO:0000704"));
		so.put("SMALL MOLECULE", URI.create(URI_PREFIX + "SO:0001854"));
		
		
		// sequence types
		so.put("PROMOTER", PROMOTER);
		so.put("RBS", URI.create(URI_PREFIX + "SO:0000139"));
		so.put("TRANSCRIPT", URI.create(URI_PREFIX + "SO:0000673"));
		so.put("CDS", URI.create(URI_PREFIX + "SO:0000673"));
		so.put("TERMINATOR", URI.create(URI_PREFIX + "SO:0000673"));
		
	}
	*/
	
	// TODO: need method to convert from 1.1 SO term to 2.0
	
	/**
	 * Creates a new URI from the Sequence Ontology namespace with the given local name. For example, the function call
	 * <code>term("SO:0000001")</code> will return the URI <a>http://identifiers.org/so/SO:0000001</a>
	 * @param localName 
	 * @return the created URI
	 */
	public static final URI type(String localName) {
		return URI.create(URI_PREFIX+localName);
	}
	
	/**
	 * A regulatory_region composed of the TSS(s) and binding sites for TF_complexes of the basal transcription
	 * machinery (<a href="http://identifiers.org/so/SO:0000167">SO:0000167</a>).
	 */
	public static final URI PROMOTER = type("SO:0000167");

	/**
	 * A regulatory element of an operon to which activators or repressors bind, thereby effecting translation of genes
	 * in that operon (<a href="http://identifiers.org/so/SO:0000057">SO:0000057</a>).
	 */
	public static final URI OPERATOR = type("SO:0000057");

	/**
	 * A contiguous sequence which begins with, and includes, a start codon, and ends with, and includes, a stop codon
	 * (<a href="http://identifiers.org/so/SO:0000316">SO:0000316</a>).
	 */
	public static final URI CDS = type("SO:0000316");

	/**
	 * A region at the 5' end of a mature transcript (preceding the initiation codon) that is not translated into a
	 * protein (<a href="http://identifiers.org/so/SO:0000204">SO:0000204</a>).
	 */
	public static final URI FIVE_PRIME_UTR = type("SO:0000204");

	/**
	 * The sequence of DNA located either at the end of the transcript that causes RNA polymerase to terminate
	 * transcription (<a href="http://identifiers.org/so/SO:0000141">SO:0000141</a>).
	 */
	public static final URI TERMINATOR = type("SO:0000141");

	/**
	 * A transcriptional cis regulatory region that, when located between a CM and a gene's promoter, prevents the CRM
	 * from modulating that genes expression (<a href="http://identifiers.org/so/SO:0000627">SO:0000627</a>)
	 */
	public static final URI INSULATOR = type("SO:0000627");

	/**
	 * The origin of replication; starting site for duplication of a nucleic acid molecule to give two identical copies
	 * (<a href="http://identifiers.org/so/SO:0000296">SO:0000296</a>).
	 */
	public static final URI ORIGIN_OF_REPLICATION = type("SO:0000296");

	/**
	 * Non-covalent primer binding site for initiation of replication, transcription, or reverse transcription (<a
	 * href="http://identifiers.org/so/SO:0005850">SO:0005850</a>)
	 */
	public static final URI PRIMER_BINDING_SITE = type("SO:0005850");

	/**
	 * Region in mRNA where ribosome assembles (<a
	 * href="http://identifiers.org/so/SO:0000139">SO:0000193</a>)
	 */
	public static final URI RIBOSOME_ENTRY_SITE = type("SO:0000139");

	/**
	 * A region (or regions) that includes all of the sequence elements necessary to encode a functional transcript. 
	 * A gene may include regulatory regions, transcribed regions and/or other functional sequence regions (<a
	 * href="http://identifiers.org/so/SO:0000704">SO:0000704</a>)
	 */
	public static final URI GENE = type("SO:0000704");

	/**
	 * Messenger RNA is the intermediate molecule between DNA and protein. It includes UTR and coding sequences. It does not contain introns (<a
	 * href="http://identifiers.org/so/SO:0000234">SO:0000234</a>)
	 */
	public static final URI MRNA = type("SO:0000234");
	
	/**
	 * Represents a region of a DNA molecule which is a nucleotide region (usually a palindrome) that is recognized by a
	 * restriction enzyme (<a href="http://identifiers.org/so/SO:0001687">SO:0001687</a>).
	 */
	public static final URI RESTRICTION_ENZYME_RECOGNITION_SITE = type("SO:0001687");

	/**
	 * A gene that is engineered (<a href="http://identifiers.org/so/SO:0000280">SO:0000280</a>).
	 */
	public static final URI ENGINEERED_GENE = type("SO:0000280");
	
	/**
	 * A region that is engineered (<a href="http://identifiers.org/so/SO:0000804">SO:0000804</a>).
	 */
	public static final URI ENGINEERED_REGION = type("SO:0000804");

}
