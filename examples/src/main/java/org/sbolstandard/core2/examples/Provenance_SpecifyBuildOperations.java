package org.sbolstandard.core2.examples;

import java.net.URI;

import org.sbolstandard.core2.Activity;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.Usage;

/**
 * Here, we exemplify how to specify a build operations using SBOL's PROV-O support.
 * 
 * The build operations are:
 * -- cut     ... linearize a (circular) vector using a restriction enzyme.
 * -- amplify ... amplification of a linear or circular construct using 5' and 3' primers
 * -- join    ... assembly of two construct using a ligase  
 * 
 * 
 * NOTE! All operations are protocol-agnostic. That is, we do not specify HOW
 *       these operations should be executed in the lab.
 *       
 * @author Ernst Oberortner
 * 
 */
public class Provenance_SpecifyBuildOperations {
	
	public static final String BUILD_PREFIX = "http://sbolstandard.org/build/";
	
	/**
	 * MAIN
	 * 
	 * @param args
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) 
			throws Exception {
		
		// exemplify the cut operation
		specifyCutOperation();
		
		// exemplify the amplify operation 
		specifyAmplifyOperation();
		
		// exemplify the join operation
		specifyJoinOperation();
	}

	/**
	 * specifies to perform a cut operation in order to linearize a vector using a restriction enzyme
	 * 
	 * @throws Exception
	 */
	public static void specifyCutOperation() 
			throws Exception {

		// instantiate a document
		SBOLDocument document = new SBOLDocument();				
		document.setDefaultURIprefix(BUILD_PREFIX);
		
		ComponentDefinition vector = document.createComponentDefinition(
				"vector", Ontologies.CIRCULAR_DOUBLE_STRANDED_DNA.getURI());
		vector.setName("vector");
		
		ComponentDefinition enzyme = document.createComponentDefinition(
				"restriction_enzyme", Ontologies.RESTRICTION_ENZYME_CUT_SITE.getURI());
		enzyme.setName("restriction_enzyme");

		//Create the generic top level entity for the cut operation
		Activity activity = document.createActivity("cut_" + vector.getName() + "_with_" + enzyme.getName());
		activity.setName("cut(" + vector.getName() + ", " + enzyme.getName() + ")");

		//Create the qualifiedUsage annotation to describe the inputs of the cut operation
		activity.createUsage("vector", vector.getIdentity()).addRole(Ontologies.VECTOR_PLASMID.getURI());
		activity.createUsage("enzyme", enzyme.getIdentity()).addRole(Ontologies.RESTRICTION_ENZYME_CUT_SITE.getURI());

		// the result of the cut operation
		ComponentDefinition linearized_vector = document.createComponentDefinition(
				"linearized_vector", Ontologies.LINEAR_DOUBLE_STRANDED_DNA.getURI());
		linearized_vector.setName("linearized_vector");
		linearized_vector.addWasGeneratedBy(activity.getIdentity());
		
		// serialize the document to System.out
		SBOLWriter.write(document,System.out);	
    }
	
	
	/**
	 * specifies the amplify operation, which amplifies a linear DNA construct using 
	 * 5' and 3' primers
	 * 
	 * @throws Exception
	 */
	public static void specifyAmplifyOperation() 
			throws Exception {
		
		// instantiate a document
		SBOLDocument document = new SBOLDocument();				
		document.setDefaultURIprefix(BUILD_PREFIX);
		
		// the linear DNA construct
		ComponentDefinition dnaConstruct = document.createComponentDefinition(
				"dna_construct", Ontologies.LINEAR_SINGLE_STRANDED_DNA.getURI());
		dnaConstruct.setName("dna_construct");
		
		// the 5' primer for amplification
		ComponentDefinition fivePrimer = document.createComponentDefinition(
				"five_primer", Ontologies.FORWARD_PRIMER.getURI());
		fivePrimer.setName("five_primer");
		
		// the 3' primer for amplification
		ComponentDefinition threePrimer = document.createComponentDefinition(
				"three_primer", Ontologies.REVERSE_PRIMER.getURI());
		threePrimer.setName("three_primer");
		
		
		//Create the generic top level entity for the amplify operation
		Activity amplifyOperation = document.createActivity(
				"amplify_" + dnaConstruct.getName() + 
				"_with_" + fivePrimer.getName() + 
				"_and_" + threePrimer.getName());
		
		amplifyOperation.setName("amplify(" + dnaConstruct.getName() + ", " + 
				fivePrimer.getName() + ", " + 
				threePrimer.getName() +")");
		
		// create the qualifiedUsage annotation to describe the inputs of the amplification operation
		// -- the amplicon
		Usage usageDNAConstruct = amplifyOperation.createUsage("dna_construct", dnaConstruct.getIdentity());
		usageDNAConstruct.addRole(URI.create("http://sbols.org/v2#source"));
		usageDNAConstruct.addRole(Ontologies.AMPLICON.getURI());
		// -- the forward primer
		Usage usageFwdPrimer = amplifyOperation.createUsage("forward_primer", fivePrimer.getIdentity());
		usageFwdPrimer.addRole(Ontologies.FORWARD_PRIMER.getURI());
		usageFwdPrimer.addRole(Ontologies.FORWARD_PRIMER.getURI());
		// -- the reverse primer
		Usage usageRevPrimer = amplifyOperation.createUsage("reverse_primer", threePrimer.getIdentity());
		usageRevPrimer.addRole(Ontologies.REVERSE_PRIMER.getURI());
		usageRevPrimer.addRole(Ontologies.REVERSE_PRIMER.getURI());

		// the result of the amplification operation
		ComponentDefinition amplified_construct = document.createComponentDefinition(
				"amplified_construct", Ontologies.LINEAR_DOUBLE_STRANDED_DNA.getURI());
		amplified_construct.setName("my_amplified_dna");
		amplified_construct.addWasGeneratedBy(amplifyOperation.getIdentity());
		
		// serialize the document to System.out
		SBOLWriter.write(document,System.out);	

	}


	/**
	 * specifies a join operation, which joins two linear DNA constructs
	 * 
	 * NOTE! at this point, we do not specify any further information 
	 * about how to execute the join operation!
	 * 
	 * @throws Exception
	 */
	public static void specifyJoinOperation() 
			throws Exception {
		
		// instantiate a document
		SBOLDocument document = new SBOLDocument();				
		document.setDefaultURIprefix(BUILD_PREFIX);

		// the first linear DNA construct
		ComponentDefinition cdPart1 = document.createComponentDefinition(
				"dna_part_1", Ontologies.LINEAR_DOUBLE_STRANDED_DNA.getURI());
		cdPart1.setName("dna_part_1");

		// the second linear DNA construct
		ComponentDefinition cdPart2 = document.createComponentDefinition(
				"dna_part_2", Ontologies.LINEAR_DOUBLE_STRANDED_DNA.getURI());
		cdPart2.setName("dna_part_2");

		//Create the generic top level entity for the join operation
		Activity joinOperation = document.createActivity(
				"join_" + cdPart1.getName() + 
				"_with_" + cdPart2.getName());
		
		joinOperation.setName("join(" + cdPart1.getName() + ", " + cdPart2.getName() + ")");
		
		// specify the "inputs" to the join operation
		joinOperation.createUsage("dna_part_1", cdPart1.getIdentity()).addRole(Ontologies.UPSTREAM.getURI());
		joinOperation.createUsage("dna_part_2", cdPart1.getIdentity()).addRole(Ontologies.DOWNSTREAM.getURI());
		
		// specify the "output" of the join operation
		ComponentDefinition cdJoinedPart = document.createComponentDefinition(
				"joined_dna_part", Ontologies.LINEAR_DOUBLE_STRANDED_DNA.getURI());
		cdJoinedPart.setName("joined_dna_part");
		
		cdJoinedPart.addWasGeneratedBy(joinOperation.getIdentity());
		
		// serialize the document to System.out
		SBOLWriter.write(document,System.out);	
	}
}

//------------------------------------------------------------
enum Ontologies {
	
	LINEAR_DOUBLE_STRANDED_DNA {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000957");
		}
		
	},
	
	LINEAR_SINGLE_STRANDED_DNA {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000956");
		}
	},
	
	CIRCULAR_DOUBLE_STRANDED_DNA {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000958");
		}
	},
	
	CIRCULAR_SINGLE_STRANDED_DNA {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000960");
		}
	},

	AMPLICON {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000006");
		}
	},
	
	FORWARD_PRIMER {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000121");
		}
	},
	
	REVERSE_PRIMER {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0000132");
		}
	},
	
	RESTRICTION_ENZYME_CUT_SITE {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO:0000168");
		}
	},
	
	VECTOR_PLASMID {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO:0000755");
		}
	},
	
	UPSTREAM {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0001631");
		}
	},
	
	DOWNSTREAM {
		@Override
		public URI getURI() {
			return URI.create("http://purl.obolibrary.org/obo/SO_0001632");
		}
		
	}
	
	;
	
	public abstract URI getURI();
}
//------------------------------------------------------------


