package org.sbolstandard.core2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.oboparser.obo.OBOOntology;
import org.oboparser.obo.OBOParser;
import org.oboparser.obo.OBOStanza;

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
	protected OBOOntology sequenceOntology = null;
	
	SequenceOntology() {
		OBOParser oboParser = new OBOParser();
		//File f = new File("src/main/resources/ontologies/SequenceOntology/so-xp.obo");
		InputStreamReader f = new InputStreamReader(getClass().
				getResourceAsStream("/ontologies/SequenceOntology/so-xp.obo"));
		try {
			oboParser.parse(f);
			sequenceOntology = oboParser.getOntology();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: need method to convert from 1.1 SO term to 2.0
	static URI convertSeqOntologyV1(String term)
	{
		String v1SO 	   = "http://purl.obolibrary.org/obo/SO_";
		String v2SO 	   = "http://identifiers.org/so/SO:";
		String convertedSO = term;
		if(term.startsWith(v1SO))
		{
			convertedSO = convertedSO.replace(v1SO, v2SO);
			return URI.create(convertedSO);
		}
		else if (term.startsWith("SO:")) {
			convertedSO = convertedSO.replace("SO:", v2SO);
		} else if (term.startsWith("so:")) {
			convertedSO = convertedSO.replace("so:", v2SO);
		}  
		return URI.create(convertedSO);
	}
	
	/**
	 * Returns the extracted ID of the given stanza's URI. 
	 * 
	 * @param stanzaURI
	 * @return the extracted ID of the given stanza's URI.
	 */
	public final String getId(URI stanzaURI) {
		String stanzaURIstr = stanzaURI.toString().trim();
		if (!stanzaURIstr.startsWith(URI_PREFIX)) {
			try {
				throw new SBOLValidationException("Illegal " + stanzaURI.toString() + ". It does not begin with the URI prefix " + URI_PREFIX);
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		int beginIndex = stanzaURIstr.lastIndexOf("/") + 1;
		return stanzaURIstr.substring(beginIndex, stanzaURIstr.length());
	}
	
	/**
	 * Returns the ID field of the stanza whose name matches the given name. If multiple matches are found, only the first matching
	 * one is returned.
	 *  
	 * @param stanzaName
	 * @return the ID the matching stanza, or {@code null} if no match is found.
	 */
	public final String getId(String stanzaName) {
		List<String> IdList = new ArrayList<String>();	
		for (OBOStanza stanza : sequenceOntology.getStanzas()) {
			if (stanzaName.trim().equals(stanza.getName().trim())) {
				IdList.add(stanza.getId());
			}
		}
		if (IdList.isEmpty()) {
			try {
				throw new SBOLValidationException("Illegal name " + stanzaName + ". It does not exit.");
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		return IdList.get(0);
	}
	
	
	/**
	 * Returns the name field of the stanza that matches the ID for the given stanzaURI.
	 * 
	 * @param stanzaURI
	 * @return the name field of the stanza that matches the ID in the given stanzaURI.
	 */
	public final String getName(URI stanzaURI) {
		String oboURIstr = stanzaURI.toString().trim();
		if (!oboURIstr.startsWith(URI_PREFIX)) {
			try {
				throw new SBOLValidationException("Illegal " + stanzaURI.toString() + ". It does not contain URI prefix " + URI_PREFIX);
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		int beginIndex = oboURIstr.lastIndexOf("/") + 1;
		String id = oboURIstr.substring(beginIndex, oboURIstr.length());
		OBOStanza oboStanza = sequenceOntology.getStanza(id);
		if (oboStanza == null) {
			try {
				throw new SBOLValidationException("ID " + id + " does not exist.");
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		return oboStanza.getName();
	}
	
	/**
	 * Returns the name field of the stanza that matches the ID in the given stanzaURI.
	 * 
	 * @param stanzaId
	 * @return the name field of the stanza that matches the ID in the given stanzaURI,
				or {@code null} if this no match is found.
	 */
	public final String getName(String stanzaId) {
		OBOStanza oboStanza = sequenceOntology.getStanza(stanzaId);
		if (oboStanza == null) {
			try {
				throw new SBOLValidationException("Illegal ID " + stanzaId + " does not exist.");
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		return oboStanza.getName();
	}
	
	/**
	 * Returns the URI, i.e. the Sequence Ontology namespace URL followed by an ID of an sequence ontology term, 
	 * of the stanza whose name matches the given name. If multiple matches are found, only the first matching
	 * one is returned. 
	 * 
	 * @param stanzaName
	 * @return the URI of the given SO name.
	 */
	public final URI getURIbyName(String stanzaName) {
		return getURIbyId(getId(stanzaName));
	}
	
	/** 
	 * Creates a new URI from the Sequence Ontology namespace with the given ID. For example, the function call
	 * <code>type("SO:0000001")</code> will return the URI <a>http://identifiers.org/so/SO:0000001</a>
	 * @param stanzaId
	 * @return the created URI
	 */
	public final URI getURIbyId(String stanzaId) {
		if (stanzaId==null) return null;
		OBOStanza oboStanza = sequenceOntology.getStanza(stanzaId.trim());
		if (oboStanza == null) {
			try {
				throw new SBOLValidationException("ID " + stanzaId + " does not exist.");
			}
			catch (SBOLValidationException e) {
				return null;
			}
		}
		return URI.create(URI_PREFIX+stanzaId);
	}

	/**
	 * Returns {@code true} if the stanza with Id1 is a descendant of the stanza with Id2.  
	 * @param Id1
	 * @param Id2
	 * @return {@code true} if the stanza with Id1 is a descendant of the stanza with Id2, {@code false} otherwise.
	 */
	public boolean isDescendantOf(String Id1, String Id2) {
		OBOStanza stanza1 = sequenceOntology.getStanza(Id1);
		OBOStanza stanza2 = sequenceOntology.getStanza(Id2);
		if (stanza1 == null) {
			try {
				throw new SBOLValidationException("Illegal ID: " + Id1 + ". No match was found.");
			}
			catch (SBOLValidationException e) {
				return false;
			}
		}
		if (stanza2 == null) {
			try {
				throw new SBOLValidationException("Illegal ID: " + Id2 + ". No match was found.");
			}
			catch (SBOLValidationException e) {
				return false;
			}
		}
		return sequenceOntology.isDescendantOf(stanza1, stanza2);
	}

	/**
	 * Creates a new URI from the Sequence Ontology namespace with the given ID. For example, the function call
	 * <code>type("SO:0000001")</code> will return the URI <a>http://identifiers.org/so/SO:0000001</a>
	 * @param id
	 * @return the created URI
	 */
	public static final URI type(String id) {
		return URI.create(URI_PREFIX+id);
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
