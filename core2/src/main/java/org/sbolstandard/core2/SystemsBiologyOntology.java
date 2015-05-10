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

public class SystemsBiologyOntology {

	// the map that contains all SBO terms 
	// will get initialized at the first access
	
	//private static Map<String, URI> sbo = null;
	
	private static final String URI_PREFIX = "http://identifiers.org/biomodels.sbo/";
	
	/**
	 * Namespace of the Systems Biology Ontology (<a href="http://identifiers.org/biomodels.sbo/">http://identifiers.org/biomodels.sbo/</a>).
	 */
	public static final URI NAMESPACE = URI.create(URI_PREFIX);
	
	/*
	public static String getTerm(URI uri) {

		// if the SO terms have not been loaded,
		// then load them now
		if(null == sbo) {
			loadSBO();
		}
		
		if(sbo.containsValue(uri) && null != uri) {
			// here, we need to iterate over the SBO terms
			for(String term : sbo.keySet()) {
				// if the URI of the current SBO term matches the provided URI, 
				// then we return the term
				if(sbo.get(term).toString().equalsIgnoreCase(uri.toString())) {
					return term;
				}
			}
		}
		
		return null;
	}

	public static URI getURI(String term) {
		// if the SO terms have not been loaded,
		// then load them now
		if(null == sbo) {
			loadSBO();
		}
		
		if(null != term) {
			return sbo.get(term.toUpperCase());
		}
		
		return null;
	}
	
	private static void loadSBO() {

		// this needs to be enhanced, of course
		sbo = new HashMap<>();

		// interaction types
		sbo.put("PROMOTER", URI.create(URI_PREFIX + "SBO:0000167"));

		// participation roles
		
	}
	*/
	
	/**
	 * Creates a new URI from the Systems Biology Ontology namespace with the given local name. For example, the function call
	 * <code>term("SBO_0000001")</code> will return the URI <a>http://purl.obolibrary.org/obo/SBO_0000001</a>
	 */
	public static final URI type(String localName) {
		return URI.create(URI_PREFIX+localName);
	}
	
	// Interaction types
	public static final URI BIOLOGICAL_ACTIVITY = type("SBO:0000412"); 
	public static final URI PROCESS = type("SBO:0000375"); 
	public static final URI BIOCHEMICAL_OR_TRANSPORT_REACTION = type("SBO:0000167"); 
	public static final URI BIOCHEMICAL_REACTION = type("SBO:0000176"); 
	public static final URI ACID_BASE_REACTION = type("SBO:0000208"); 
	public static final URI DEPROTONATION = type("SBO:0000213"); 
	public static final URI PROTONATION = type("SBO:0000212"); 
	public static final URI CONFORMATIONAL_TRANSITION = type("SBO:0000181"); 
	public static final URI CONVERSION = type("SBO:0000182"); 
	public static final URI ADDITION_OF_A_CHEMICAL_GROUP = type("SBO:0000210"); 
	public static final URI ACETYLATION = type("SBO:0000215"); 
	public static final URI GLYCOSYLATION = type("SBO:0000217"); 
	public static final URI HYDROXYLATION = type("SBO:0000233"); 
	public static final URI METHYLATION = type("SBO:0000214"); 
	public static final URI MYRISTOYLATION = type("SBO:0000219"); 
	public static final URI PALMITOYLATION = type("SBO:0000218"); 
	public static final URI PHOSPHORYLATION = type("SBO:0000216"); 
	public static final URI PRENYLATION = type("SBO:0000221"); 
	public static final URI FARNESYLATION = type("SBO:0000222"); 
	public static final URI GERANYLGERANYLATION = type("SBO:0000223"); 
	public static final URI SULFATION = type("SBO:0000220"); 
	public static final URI UBIQUITINATION = type("SBO:0000224"); 
	public static final URI CLEAVAGE = type("SBO:0000178"); 
	public static final URI REMOVAL_OF_A_CHEMICAL_GROUP = type("SBO:0000211"); 
	public static final URI DEAMINATION = type("SBO:0000401"); 
	public static final URI DECARBONYLATION = type("SBO:0000400"); 
	public static final URI DECARBOXYLATION = type("SBO:0000399"); 
	public static final URI DEPHOSPHORYLATION = type("SBO:0000330"); 
	public static final URI TRANSFER_OF_A_CHEMICAL_GROUP = type("SBO:0000402"); 
	public static final URI TRANSAMINATION = type("SBO:0000403"); 
	public static final URI DEGRADATION = type("SBO:0000179"); 
	public static final URI DISSOCIATION = type("SBO:0000180"); 
	public static final URI HYDROLYSIS = type("SBO:0000376"); 
	public static final URI IONISATION = type("SBO:0000209"); 
	public static final URI ISOMERISATION = type("SBO:0000377"); 
	public static final URI NON_COVALENT_BINDING = type("SBO:0000177"); 
	public static final URI REDOX_REACTION = type("SBO:0000200"); 
	public static final URI OXIDATION = type("SBO:0000201"); 
	public static final URI REDUCTION = type("SBO:0000202"); 
	public static final URI TRANSPORT_REACTION = type("SBO:0000185"); 
	public static final URI TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION = type("SBO:0000588"); 
	public static final URI TRANSCELLULAR_MEMBRANE_INFLUX_REACTION = type("SBO:0000587"); 
	public static final URI BIOLOGICAL_EFFECT_OF_A_PERTURBATION = type("SBO:0000357"); 
	public static final URI COMPOSITE_BIOCHEMICAL_PROCESS = type("SBO:0000205"); 
	public static final URI DNA_REPLICATION = type("SBO:0000204");
	public static final URI GENETIC_PRODUCTION = type("SBO:0000589");
	public static final URI TRANSCRIPTION = type("SBO:0000183");
	public static final URI TRANSLATION = type("SBO:0000184");
	public static final URI ENCAPSULATING_PROCESS = type("SBO:0000395");
	public static final URI MOLECULAR_OR_GENETIC_INTERACTION = type("SBO:0000342");
	public static final URI GENETIC_INTERACTION = type("SBO:0000343");
	public static final URI GENETIC_ENHANCEMENT = type("SBO:0000501");
	public static final URI GENETIC_SUPPRESSION = type("SBO:0000500");
	public static final URI SYNTHETIC_LETHALITY = type("SBO:0000502");
	public static final URI MOLECULAR_INTERACTION = type("SBO:0000344");
	public static final URI PROTEIN_COMPLEX_FORMATION = type("SBO:0000526");
	public static final URI OMITTED_PROCESS = type("SBO:0000397");
	public static final URI PHENOTYPE = type("SBO:0000358");
	public static final URI STATE_VARIABLE_ASSIGNMENT = type("SBO:0000464");
	public static final URI PETRI_NET_TRANSITION = type("SBO:0000591");
	public static final URI UNCERTAIN_PROCESS = type("SBO:0000396");
	public static final URI RELATIONSHIP = type("SBO:0000374");
	public static final URI CONTROL = type("SBO:0000168");
	public static final URI EQUIVALENCE = type("SBO:0000392");
	public static final URI LOGICAL_COMBINATION = type("SBO:0000237");
	public static final URI LOGICAL_RELATIONSHIP = type("SBO:0000398");
	public static final URI POSITIONAL_RELATIONSHIP = type("SBO:0000413");

	// Participant roles
	public static final URI FUNCTIONAL_COMPARTMENT = type("SBO:0000289");
	public static final URI MODIFIER = type("SBO:0000019");
	public static final URI DUAL_ACTIVITY_MODIFIER = type("SBO:0000595");
	public static final URI INHIBITOR = type("SBO:0000020");
	public static final URI COMPETITIVE_INHIBITOR = type("SBO:0000206");
	public static final URI NON_COMPETITIVE_INHIBITOR = type("SBO:0000207");
	public static final URI SILENCER = type("SBO:0000597");
	public static final URI MODIFIER_OF_UNKNOWN_ACTIVITY = type("SBO:0000596");
	public static final URI STIMULATOR = type("SBO:0000459");
	public static final URI CATALYST = type("SBO:0000013");
	public static final URI ENZYMATIC_CATALYST = type("SBO:0000460");
	public static final URI ESSENTIAL_ACTIVATOR = type("SBO:0000461");
	public static final URI BINDING_ACTIVATOR = type("SBO:0000535");
	public static final URI CATALYTIC_ACTIVATOR = type("SBO:0000534");
	public static final URI SPECIFIC_ACTIVATOR = type("SBO:0000533");
	public static final URI NON_ESSENTIAL_ACTIVATOR = type("SBO:0000462");
	public static final URI POTENTIATOR = type("SBO:0000021");
	public static final URI NEUTRAL_PARTICIPANT = type("SBO:0000594");
	public static final URI PRODUCT = type("SBO:0000011");
	public static final URI SIDE_PRODUCT = type("SBO:0000603");
	public static final URI PROMOTER = type("SBO:0000598");
	public static final URI REACTANT = type("SBO:0000010");
	public static final URI INTERACTOR = type("SBO:0000336");
	public static final URI SUBSTRATE = type("SBO:0000015");
	public static final URI SIDE_SUBSTRATE = type("SBO:0000604");
}
