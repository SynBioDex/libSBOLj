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

	// Modeling frameworks
	/**
	 * Modelling approach where the quantities of participants are considered continuous,
	 * and represented by real values (<a href="http://identifiers.org/biomodels.sbo/SBO:0000062">SBO:0000062</a>). 
	 * The associated simulation methods make use of differential equations.
	 */
	public static final URI CONTINUOUS_FRAMEWORK 		 	 = type("SBO:0000062");

	/**
	 * Modelling approach where the quantities of participants are considered continuous, and represented by
	 * real values. The associated simulation methods make use of differential equations. The models do not
	 * take into account the distribution of the entities and describe only the temporal fluxes.
	 */
	public static final URI NON_SPATIAL_CONTINUOUS_FRAMEWORK = type("SBO:0000293");

	/**
	 * Modelling approach where the quantities of participants are considered continuous, and represented
	 * by real values. The associated simulation methods make use of differential equations. The models
	 * take into account the distribution of the entities and describe the spatial fluxes.
	 */
	public static final URI SPATIAL_CONTINUOUS_FRAMEWORK 	 = type("SBO:0000292");

	/**
	 * Modelling approach where the quantities of participants are considered discrete, and represented by
	 * integer values. The associated simulation methods can be deterministic or stochastic.
	 */
	public static final URI DISCRETE_FRAMEWORK 				 = type("SBO:0000063");

	/**
	 * Modelling approach where the quantities of participants are considered discrete, and represented by
	 * integer values. The associated simulation methods can be deterministic or stochastic.The models do
	 * not take into account the distribution of the entities and describe only the temporal fluxes.
	 */
	public static final URI NON_SPATIAL_DISCRETE_FRAMEWORK 	 = type("SBO:0000295");

	/**
	 * Modelling approach where the quantities of participants are considered discrete, and represented
	 * by integer values. The associated simulation methods can be deterministic or stochastic. The models
	 * take into account the distribution of the entities and describe the spatial fluxes.
	 */
	public static final URI SPATIAL_DISCRETE_FRAMEWORK 		 = type("SBO:0000294");

	/**
	 * Modelling approach, typically used for metabolic models, where the flow of metabolites (flux)
	 * through a network can be calculated. This approach will generally produce a set of solutions
	 * (solution space), which may be reduced using objective functions and constraints on individual fluxes.
	 */
	public static final URI FLUX_BALANCE_FRAMEWORK 			 = type("SBO:0000624");

	/**
	 * Modelling approach, pioneered by Rene Thomas and Stuart Kaufman, where the evolution of a system
	 * is described by the transitions between discrete activity states of "genes" that control each other.
	 */
	public static final URI LOGICAL_FRAMEWORK 				 = type("SBO:0000234");

	/**
	 * Equationally defined algebraic framework usually interpreted as a two-valued logic using the basic
	 * Boolean operations (conjunction, disjunction and negation), together with the constants '0' and '1'
	 * denoting false and true values, respectively.
	 */
	public static final URI BOOLEAN_LOGICAL_FRAMEWORK 		 = type("SBO:0000547");

	// Interaction types
	/**
	 * The potential action that a biological entity has on other entities. Example are
	 * enzymatic activity, binding activity etc.
	 */
	public static final URI BIOLOGICAL_ACTIVITY 				   = type("SBO:0000412");

	/**
	 * A sequential series of actions, motions, or occurrences, such as chemical reactions,
	 * that affect one or more entities in a phenomenologically characteristic manner.
	 */
	public static final URI PROCESS 							   = type("SBO:0000375");

	/**
	 * An event involving one or more physical entities that modifies the structure, location
	 * or free energy of at least one of the participants.
	 */
	public static final URI BIOCHEMICAL_OR_TRANSPORT_REACTION 	   = type("SBO:0000167");

	/**
	 * An event involving one or more chemical entities that modifies the electrochemical
	 * structure of at least one of the participants.
	 */
	public static final URI BIOCHEMICAL_REACTION 				   = type("SBO:0000176");

	/**
	 * Chemical reaction where a proton is given by a compound, the acid, to another one, the
	 * base (Brønsted-Lowry definition). An alternative, more general, definition is a reaction
	 * where a compound, the base, gives a pair of electrons to another, the acid
	 * (Lewis definition).
	 */
	public static final URI ACID_BASE_REACTION 					   = type("SBO:0000208");

	/**
	 * Removal of a proton (hydrogen ion H+) from a chemical entity.
	 */
	public static final URI DEPROTONATION 						   = type("SBO:0000213");

	/**
	 * Addition of a proton (H+) to a chemical entity.
	 */
	public static final URI PROTONATION 						   = type("SBO:0000212");

	/**
	 * Biochemical reaction that does not result in the modification of covalent bonds of
	 * reactants, but rather modifies the conformation of some reactants, that is the relative
	 * position of their atoms in space.
	 */
	public static final URI CONFORMATIONAL_TRANSITION 			   = type("SBO:0000181");

	/**
	 * Biochemical reaction that results in the modification of some covalent bonds.
	 */
	public static final URI CONVERSION 							   = type("SBO:0000182");

	/**
	 * Covalent reaction that results in the addition of a chemical group on a molecule.
	 */
	public static final URI ADDITION_OF_A_CHEMICAL_GROUP 		   = type("SBO:0000210");

	/**
	 * Addition of an acetyl group (-COCH3) to a chemical entity.
	 */
	public static final URI ACETYLATION 						   = type("SBO:0000215");

	/**
	 * Addition of a saccharide group to a chemical entity.
	 */
	public static final URI GLYCOSYLATION 						   = type("SBO:0000217");

	/**
	 * Addition of an hydroxyl group (-OH) to a chemical entity.
	 */
	public static final URI HYDROXYLATION 						   = type("SBO:0000233");

	/**
	 * Addition of a methyl group (-CH3) to a chemical entity.
	 */
	public static final URI METHYLATION 						   = type("SBO:0000214");

	/**
	 * Addition of a myristoyl (CH3-[CH2]12-CO-) to a chemical entity.
	 */
	public static final URI MYRISTOYLATION 						   = type("SBO:0000219");

	/**
	 * Addition of a palmitoyl group (CH3-[CH2]14-CO-) to a chemical entity.
	 */
	public static final URI PALMITOYLATION 						   = type("SBO:0000218");

	/**
	 * Addition of a phosphate group (-H2PO4) to a chemical entity.
	 */
	public static final URI PHOSPHORYLATION 					   = type("SBO:0000216");

	/**
	 * Addition of a prenyl group (generic sense) to a chemical entity.
	 */
	public static final URI PRENYLATION 						   = type("SBO:0000221");

	/**
	 * Addition of a farnesyl group (CH2-CH=C(CH3)-CH2-CH2-CH=C(CH3)-CH2-CH2-CH=C(CH3)2)
	 * to a chemical entity.
	 */
	public static final URI FARNESYLATION 						   = type("SBO:0000222");

	/**
	 * Addition of a geranylgeranyl group
	 * (CH2-CH=C(CH3)-CH2-CH2-CH=C(CH3)-CH2-CH2-CH=C(CH3)-CH2-CH2-CH=C(CH3)2)
	 * to a chemical entity.
	 */
	public static final URI GERANYLGERANYLATION 				   = type("SBO:0000223");

	/**
	 * Addition of a sulfate group (SO4--) to a chemical entity.
	 */
	public static final URI SULFATION 							   = type("SBO:0000220");

	/**
	 * Covalent linkage to the protein ubiquitin.
	 */
	public static final URI UBIQUITINATION 						   = type("SBO:0000224");

	/**
	 * Rupture of a covalent bond resulting in the conversion of one physical entity into
	 * several physical entities.
	 */
	public static final URI CLEAVAGE 							   = type("SBO:0000178");

	/**
	 * Covalent reaction that results in the removal of a chemical group from a molecule.
	 */
	public static final URI REMOVAL_OF_A_CHEMICAL_GROUP 		   = type("SBO:0000211");

	/**
	 * Removal of an amine group from a molecule, often under the addition of water
	 */
	public static final URI DEAMINATION 						   = type("SBO:0000401");

	/**
	 * Removal of a carbonyl group (-C-O-) from a molecule, usually as carbon monoxide
	 */
	public static final URI DECARBONYLATION 				  	   = type("SBO:0000400");

	/**
	 * A process in which a carboxyl group (COOH) is removed from a molecule as carbon dioxide.
	 */
	public static final URI DECARBOXYLATION 					   = type("SBO:0000399");

	/**
	 * Removal of a phosphate group (-H2PO4) from a chemical entity.
	 */
	public static final URI DEPHOSPHORYLATION 					   = type("SBO:0000330");

	/**
	 * Covalent reaction that results in the transfer of a chemical group from one molecule
	 * to another.
	 */
	public static final URI TRANSFER_OF_A_CHEMICAL_GROUP 		   = type("SBO:0000402");

	/**
	 * The transfer of an amino group between two molecules. Commonly in biology this is
	 * restricted to reactions between an amino acid and an alpha-keto carbonic acid, whereby
	 * the reacting amino acid is converted into an alpha-keto acid, and the alpha-keto acid
	 * reactant into an amino acid.
	 */
	public static final URI TRANSAMINATION 						   = type("SBO:0000403");

	/**
	 * Complete disappearance of a physical entity.
	 */
	public static final URI DEGRADATION 						   = type("SBO:0000179");

	/**
	 * Transformation of a non-covalent complex that results in the formation of several
	 * independent biochemical entities
	 */
	public static final URI DISSOCIATION 						   = type("SBO:0000180");

	/**
	 * Decomposition of a compound by reaction with water, where the hydroxyl and H groups are
	 * incorporated into different products
	 */
	public static final URI HYDROLYSIS 							   = type("SBO:0000376");

	/**
	 * Ionization is the physical process of converting an atom or molecule into an ion by
	 * changing the difference between the number of protons and electrons.
	 */
	public static final URI IONISATION 							   = type("SBO:0000209");

	/**
	 * A reaction in which the principal reactant and principal product are isomers of each other
	 */
	public static final URI ISOMERISATION 						   = type("SBO:0000377");

	/**
	 * Interaction between several biochemical entities that results in the formation of a
	 * non-covalent complex
	 */
	public static final URI NON_COVALENT_BINDING 				   = type("SBO:0000177");

	/**
	 * Chemical process in which atoms have their oxidation number (oxidation state) changed.
	 */
	public static final URI REDOX_REACTION 						   = type("SBO:0000200");

	/**
	 * Chemical process during which a molecular entity loses electrons.
	 */
	public static final URI OXIDATION 							   = type("SBO:0000201");

	/**
	 * Chemical process in which a molecular entity gain electrons.
	 */
	public static final URI REDUCTION 							   = type("SBO:0000202");

	/**
	 * Movement of a physical entity without modification of the structure of the entity.
	 */
	public static final URI TRANSPORT_REACTION 					   = type("SBO:0000185");

	/**
	 * A transport reaction which results in the removal of the transported entity from the cell.
	 */
	public static final URI TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION = type("SBO:0000588");

	/**
	 * A transport reaction which results in the entry of the transported entity, into the cell.
	 */
	public static final URI TRANSCELLULAR_MEMBRANE_INFLUX_REACTION = type("SBO:0000587");

	/**
	 * Biochemical networks can be affected by external influences. Those influences can be
	 * well-defined physical perturbations, such as a light pulse, or a change in temperature
	 * but also more complex of not well defined phenomena, for instance a biological process,
	 * an experimental setup, or a mutation.
	 */
	public static final URI BIOLOGICAL_EFFECT_OF_A_PERTURBATION    = type("SBO:0000357");

	/**
	 * Process that involves the participation of chemical or biological entities and is
	 * composed of several elementary steps or reactions.
	 */
	public static final URI COMPOSITE_BIOCHEMICAL_PROCESS 		   = type("SBO:0000205");

	/**
	 * Process in which a DNA duplex is transformed into two identical DNA duplexes.
	 */
	public static final URI DNA_REPLICATION 					   = type("SBO:0000204");

	/**
	 * A composite biochemical process through which a gene sequence is fully converted into
	 * mature gene products. These gene products may include RNA species as well as proteins,
	 * and the process encompasses all intermediate steps required to generate the active form
	 * of the gene product.
	 */
	public static final URI GENETIC_PRODUCTION 					   = type("SBO:0000589");

	/**
	 * Process through which a DNA sequence is copied to produce a complementary RNA.
	 */
	public static final URI TRANSCRIPTION 						   = type("SBO:0000183");

	/**
	 * Process in which a polypeptide chain is produced from a messenger RNA.
	 */
	public static final URI TRANSLATION 						   = type("SBO:0000184");

	/**
	 * An aggregation of interactions and entities into a single process.
	 */
	public static final URI ENCAPSULATING_PROCESS 				   = type("SBO:0000395");

	/**
	 * Mutual or reciprocal action or influence between molecular entities.
	 */
	public static final URI MOLECULAR_OR_GENETIC_INTERACTION 	   = type("SBO:0000342");

	/**
	 * A phenomenon whereby an observed phenotype, qualitative or quantative, is not explainable
	 * by the simple additive effects of the individual gene pertubations alone. Genetic
	 * interaction between perturbed genes is usually expected to generate a 'defective'
	 * phenotype. The level of defectiveness is often used to sub-classify this phenomenon.
	 */
	public static final URI GENETIC_INTERACTION 				   = type("SBO:0000343");

	/**
	 * Genetic enhancement is said to have occurred when the phenotypic effect of an initial
	 * mutation in a gene is made increasingly severe by a subsequent mutation.
	 */
	public static final URI GENETIC_ENHANCEMENT 				   = type("SBO:0000501");

	/**
	 * Genetic suppression is said to have occurred when the phenotypic effect of an initial
	 * mutation in a gene is less severe, or entirely negated, by a subsequent mutation.
	 */
	public static final URI GENETIC_SUPPRESSION 				   = type("SBO:0000500");

	/**
	 * Synthetic lethality is said to have occurred where gene mutations, each of which map to
	 * a separate locus, fail to complement in an offspring to correct a phenotype, as would be
	 * expected.
	 */
	public static final URI SYNTHETIC_LETHALITY 				   = type("SBO:0000502");

	/**
	 * Relationship between molecular entities, based on contacts, direct or indirect.
	 */
	public static final URI MOLECULAR_INTERACTION 				   = type("SBO:0000344");

	/**
	 * The process by which two or more proteins interact non-covalently to form a protein
	 * complex.
	 */
	public static final URI PROTEIN_COMPLEX_FORMATION 			   = type("SBO:0000526");

	/**
	 * One or more processes that are not represented in certain representations or
	 * interpretations of a model.
	 */
	public static final URI OMITTED_PROCESS 					   = type("SBO:0000397");

	/**
	 * A biochemical network can generate phenotypes or affects biological processes. Such
	 * processes can take place at different levels and are independent of the biochemical
	 * network itself.
	 */
	public static final URI PHENOTYPE 							   = type("SBO:0000358");

	/**
	 * Assignment of a state or a value to a state variable, characteristic or property, of a
	 * biological entity.
	 */
	public static final URI STATE_VARIABLE_ASSIGNMENT 			   = type("SBO:0000464");

	/**
	 * A process that can modify the state of petri net 'places'.
	 */
	public static final URI PETRI_NET_TRANSITION 				   = type("SBO:0000591");

	/**
	 * An equivocal or conjectural process, whose existence is assumed but not proven.
	 */
	public static final URI UNCERTAIN_PROCESS 					   = type("SBO:0000396");

	/**
	 * connectedness between entities and/or interactions representing their relatedness or
	 * influence.
	 */
	public static final URI RELATIONSHIP 						   = type("SBO:0000374");

	/**
	 * Modification of the execution of an event or a process.
	 */
	public static final URI CONTROL 							   = type("SBO:0000168");

	/**
	 * Regulation of the influence of a reaction participant by binding an effector to a
	 * binding site of the participant different of the site of the participant conveying the
	 * influence.
	 */
	public static final URI ALLOSTERIC_CONTROL 				       = type("SBO:0000239");

	/**
	 * Decrease in amount of a material or conceptual entity.
	 */
	public static final URI CONSUMPTION 						   = type("SBO:0000394");

	/**
	 * Negative modulation of the execution of a process.
	 */
	public static final URI INHIBITION 							   = type("SBO:0000169");

	/**
	 * Control that precludes the execution of a process.
	 */
	public static final URI ABSOLUTE_INHIBITION 				   = type("SBO:0000407");

	/**
	 * Generation of a material or conceptual entity.
	 */
	public static final URI PRODUCTION 							   = type("SBO:0000393");

	/**
	 * Positive modulation of the execution of a process.
	 */
	public static final URI STIMULATION 						   = type("SBO:0000170");

	/**
	 * Control that always triggers the controlled process.
	 */
	public static final URI ABSOLUTE_STIMULATION 				   = type("SBO:0000411");

	/**
	 * Modification of the velocity of a reaction by lowering the energy of the transition state.
	 */
	public static final URI CATALYSIS 							   = type("SBO:0000172");

	/**
	 * Control that is necessary to the execution of a process.
	 */
	public static final URI NECESSARY_STIMULATION 				   = type("SBO:0000171");

	/**
	 * Term to signify those material or conceptual entities that are identical in some respect
	 * within a frame of reference
	 */
	public static final URI EQUIVALENCE 					       = type("SBO:0000392");

	/**
	 * Combining the influence of several entities or events in a unique influence.
	 */
	public static final URI LOGICAL_COMBINATION 				   = type("SBO:0000237");

	/**
	 * All the preceding events or participating entities are necessary to perform the control.
	 */
	public static final URI AND 								   = type("SBO:0000173");

	/**
	 * The preceding event or participating entity cannot participate to the control.
	 */
	public static final URI NOT 								   = type("SBO:0000238");

	/**
	 * Any of the preceding events or participating entities are necessary to perform the control.
	 */
	public static final URI OR 									   = type("SBO:0000174");

	/**
	 * Only one of the preceding events or participating entities can perform the control at one time.
	 */
	public static final URI XOR 								   = type("SBO:0000175");

	/**
	 * Relationship between entities (material or conceptual) and logical operators, or between
	 * logical operators themselves.
	 */
	public static final URI LOGICAL_RELATIONSHIP 				   = type("SBO:0000398");

	/**
	 * The connectedness between entities as related by their position
	 */
	public static final URI POSITIONAL_RELATIONSHIP 			   = type("SBO:0000413");

	/**
	 * Positional relationship between entities on the same strand (e.g. in DNA), or on the same side.
	 */
	public static final URI CIS 								   = type("SBO:0000414");

	/**
	 * An entity that is a subset of another entity or object.
	 */
	public static final URI CONTAINMENT 						   = type("SBO:0000469");

	/**
	 * Positional relationship between entities on different sides, or strands
	 */
	public static final URI TRANS 								   = type("SBO:0000415");

	// Participant roles
	/**
	 * Logical or physical subset of the event space that contains pools, that is sets of participants
	 * considered identical when it comes to the event they are involved into. A compartment can have any
	 * number of dimensions, including 0, and be of any size including null.
	 */
	public static final URI FUNCTIONAL_COMPARTMENT 		 = type("SBO:0000289");

	/**
	 * Substance that changes the velocity of a process without itself being consumed or
	 * transformed by the reaction.
	 */
	public static final URI MODIFIER 					 = type("SBO:0000019");

	/**
	 * A modifier that can exhibit either inhibitory or stimulatory effects on a process
	 * depending on the context in which it occurs. For example, the observed effect may be
	 * dependent upon the concentration of the modifier.
	 */
	public static final URI DUAL_ACTIVITY_MODIFIER 		 = type("SBO:0000595");

	/**
	 * Substance that decreases the probability of a chemical reaction without itself being
	 * consumed or transformed by the reaction.
	 */
	public static final URI INHIBITOR 					 = type("SBO:0000020");

	/**
	 * Substance that decreases the probability of a chemical reaction, without itself being
	 * consumed or transformed by the reaction, by stericaly hindering the interaction between
	 * reactants.
	 */
	public static final URI COMPETITIVE_INHIBITOR 	 	 = type("SBO:0000206");

	/**
	 * Substance that decreases the probability of a chemical reaction, without itself being
	 * consumed or transformed by the reaction, and without sterically hindering the
	 * interaction between reactants.
	 */
	public static final URI NON_COMPETITIVE_INHIBITOR 	 = type("SBO:0000207");

	/**
	 * A silencer is a modifier which acts in a manner that completely prevents an event or
	 * process from occurring. For example, a silencer in gene expression is usually a
	 * transcription factor that binds a DNA sequence in such a way as to completely prevent
	 * the binding of RNA polymerase, and thus fully suppresses transcription.
	 */
	public static final URI SILENCER 					 = type("SBO:0000597");

	/**
	 * A modifier whose activity is not known or has not been specified.
	 */
	public static final URI MODIFIER_OF_UNKNOWN_ACTIVITY = type("SBO:0000596");

	/**
	 * Substance that accelerates the velocity of a chemical reaction without itself being
	 * consumed or transformed.
	 */
	public static final URI STIMULATOR 				     = type("SBO:0000459");

	/**
	 * Substance that accelerates the velocity of a chemical reaction without itself being
	 * consumed or transformed. This effect is achieved by lowering the free energy of the
	 * transition state.
	 */
	public static final URI CATALYST 					 = type("SBO:0000013");

	/**
	 * A substance that accelerates the velocity of a chemical reaction without itself being
	 * consumed or transformed, by lowering the free energy of the transition state. The
	 * substance acting as a catalyst is an enzyme.
	 */
	public static final URI ENZYMATIC_CATALYST 			 = type("SBO:0000460");

	/**
	 * A substance that is absolutely required for occurrence and stimulation of a reaction.
	 */
	public static final URI ESSENTIAL_ACTIVATOR 		 = type("SBO:0000461");

	/**
	 * An essential activator that affects the apparent value of the Michaelis constant(s).
	 */
	public static final URI BINDING_ACTIVATOR 			 = type("SBO:0000535");

	/**
	 * An essential activator that affects the apparent value of the catalytic constant.
	 */
	public static final URI CATALYTIC_ACTIVATOR 		 = type("SBO:0000534");

	/**
	 * An essential activator that affects the apparent value of the specificity constant.
	 * Mechanistically, the activator would need to be bound before reactant and product
	 * binding can take place.
	 */
	public static final URI SPECIFIC_ACTIVATOR 			 = type("SBO:0000533");

	/**
	 * An activator which is not necessary for an enzymatic reaction, but whose presence will
	 * further increase enzymatic activity.
	 */
	public static final URI NON_ESSENTIAL_ACTIVATOR 	 = type("SBO:0000462");

	/**
	 * Substance that increases the probability of a chemical reaction without itself being
	 * consumed or transformed by the reaction. This effect is achieved by increasing the
	 * difference of free energy between the reactant(s) and the product(s)
	 */
	public static final URI POTENTIATOR 				 = type("SBO:0000021");

	/**
	 * A participant whose presence does not alter the velocity of a process or event.
	 */
	public static final URI NEUTRAL_PARTICIPANT 		 = type("SBO:0000594");

	/**
	 * Substance that is produced in a reaction. In a chemical equation the Products are the
	 * elements or compounds on the right hand side of the reaction equation. A product can be
	 * produced and consumed by the same reaction, its global quantity remaining unchanged.
	 */
	public static final URI PRODUCT 					 = type("SBO:0000011");

	/**
	 * A substance that is produced in a chemical reaction but is not itself the primary
	 * product or focus of that reaction. Examples include, but are not limited to, currency
	 * compounds such as ATP, NADPH and protons.
	 */
	public static final URI SIDE_PRODUCT 				 = type("SBO:0000603");

	/**
	 * A region of DNA to which various transcription factors and RNA polymerase must bind
	 * in order to initiate transcription for a gene.
	 */
	public static final URI PROMOTER 				 	 = type("SBO:0000598");

	/**
	 * Substance consumed by a chemical reaction. Reactants react with each other to form the
	 * products of a chemical reaction. In a chemical equation the Reactants are the elements
	 * or compounds on the left hand side of the reaction equation. A reactant can be consumed
	 * and produced by the same reaction, its global quantity remaining unchanged.
	 */
	public static final URI REACTANT 					 = type("SBO:0000010");

	/**
	 * Entity participating in a physical or functional interaction.
	 */
	public static final URI INTERACTOR 					 = type("SBO:0000336");

	/**
	 * Molecule which is acted upon by an enzyme. The substrate binds with the enzyme's active
	 * site, and the enzyme catalyzes a chemical reaction involving the substrate.
	 */
	public static final URI SUBSTRATE 					 = type("SBO:0000015");

	/**
	 * A substance that is consumed in a chemical reaction but is not itself the primary
	 * substrate or focus of that reaction. Examples include, but are not limited to, currency
	 * compounds such as ATP, NADPH and protons.
	 */
	public static final URI SIDE_SUBSTRATE 				 = type("SBO:0000604");
}
