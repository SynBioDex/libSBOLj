package org.sbolstandard.core2.examples;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.RestrictionType;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.Sequence;
import org.sbolstandard.core2.SequenceOntology;

/**
 * This simple example is used by the "Getting Started" document for libSBOLj 2.0. 
 * @author Zhen Zhang
 *
 */
public class GettingStartedExample {
	public static void main( String[] args ) throws Exception {
		String prURI="http://partsregistry.org"; 
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
		document.setComplete(true);
		document.setCreateDefaults(true);
		
		// Creating a Top-level SBOL Data Object
		HashSet<URI> types = new HashSet<URI>(Arrays.asList(
						ComponentDefinition.DNA,
						URI.create("http://identifiers.org/chebi/CHEBI:4705")
						));		
		ComponentDefinition TetR_promoter = document.createComponentDefinition(
				"BBa_R0040", 
				types);
		ComponentDefinition LacI_repressor = document.createComponentDefinition(
				"BBa_C0012", 
				types);
		ComponentDefinition pIKELeftCassette = document.createComponentDefinition(
				"pIKELeftCassette", 
				types);
		Sequence seq_187 = document.createSequence(
				"partseq_187",
				"tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcac",
				Sequence.IUPAC_DNA
				);
		String element2 = "atggtgaatgtgaaaccagtaacgttatacgatgtcgcagagtatgccggtgtc"
				+ "tcttatcagaccgtttcccgcgtggtgaaccaggccagccacgtttctgcgaaaacgcggga"
				+ "aaaagtggaagcggcgatggcggagctgaattacattcccaaccgcgtggcacaacaactgg"
				+ "cgggcaaacagtcgttgctgattggcgttgccacctccagtctggccctgcacgcgccgtcg"
				+ "caaattgtcgcggcgattaaatctcgcgccgatcaactgggtgccagcgtggtggtgtcgat"
				+ "ggtagaacgaagcggcgtcgaagcctgtaaagcggcggtgcacaatcttctcgcgcaacgcg"
				+ "tcagtgggctgatcattaactatccgctggatgaccaggatgccattgctgtggaagctgcc"
				+ "tgcactaatgttccggcgttatttcttgatgtctctgaccagacacccatcaacagtattat"
				+ "tttctcccatgaagacggtacgcgactgggcgtggagcatctggtcgcattgggtcaccagc"
				+ "aaatcgcgctgttagcgggcccattaagttctgtctcggcgcgtctgcgtctggctggctgg"
				+ "cataaatatctcactcgcaatcaaattcagccgatagcggaacgggaaggcgactggagtgc"
				+ "catgtccggttttcaacaaaccatgcaaatgctgaatgagggcatcgttcccactgcgatgc"
				+ "tggttgccaacgatcagatggcgctgggcgcaatgcgcgccattaccgagtccgggctgcgc"
				+ "gttggtgcggatatctcggtagtgggatacgacgataccgaagacagctcatgttatatccc"
				+ "gccgttaaccaccatcaaacaggattttcgcctgctggggcaaaccagcgtggaccgcttgc"
				+ "tgcaactctctcagggccaggcggtgaagggcaatcagctgttgcccgtctcactggtgaaa"
				+ "agaaaaaccaccctggcgcccaatacgcaaaccgcctctccccgcgcgttggccgattcatt"
				+ "aatgcagctggcacgacaggtttcccgactggaaagcgggcaggctgcaaacgacgaaaact"
				+ "acgctttagtagcttaataa";
				
		Sequence seq_153 = document.createSequence(
				"partseq_153",
				element2,
				Sequence.IUPAC_DNA
				);
		
		// Setting and editing optional fields
		TetR_promoter.setName("p(tetR)");
		LacI_repressor.setName("lacI");
		TetR_promoter.setDescription("TetR repressible promoter");
		LacI_repressor.setDescription("lacI repressor from E. coli (+LVA)");
		if (TetR_promoter.isSetName()) {
			TetR_promoter.unsetName();
		}
		TetR_promoter.setName("p(tetR)");

		TetR_promoter.addRole(SequenceOntology.PROMOTER);		
		LacI_repressor.addRole(SequenceOntology.CDS);
		URI TetR_promoter_role2 = URI.create("http://identifiers.org/so/SO:0000613"); 
		TetR_promoter.addRole(TetR_promoter_role2);
		if (TetR_promoter.containsRole(TetR_promoter_role2)) {
			TetR_promoter.removeRole(TetR_promoter_role2);
		}

		TetR_promoter.clearRoles();
		if (!TetR_promoter.getRoles().isEmpty()) {
			System.out.println("TetR_promoter set is not empty");
		}
		TetR_promoter.setRoles(new HashSet<URI>(Arrays.asList(
					SequenceOntology.PROMOTER)));
		
		// Creating and editing references
		TetR_promoter.addSequence(seq_187);		
		LacI_repressor.addSequence(seq_153);		
		pIKELeftCassette.addSequence(seq_187);
		pIKELeftCassette.clearSequences();
		// Adding the sequence below causes an exception because it cannot be found
		//pIKELeftCassette.addSequence(URI.create("http://partsregistry.org/seq/partseq_154"));
		
		// Creating and editing Child Objects
		// For pIKELeftCassette, create sequence constraint that says BBa_R0040 precedes BBa_C0012.
		// Note that with CreateDefaults that components get created automatically.
		// The position of the subject Component MUST precede that of the object Component. 
		pIKELeftCassette.createSequenceConstraint(
				"pIKELeftCassette_sc",
				RestrictionType.PRECEDES,
				TetR_promoter.getDisplayId(), 
				LacI_repressor.getDisplayId()
				);
		if (pIKELeftCassette.getComponent("BBa_R0040")==null) {
			System.out.println("TetR_promoter component is missing");
		}
		if (pIKELeftCassette.getComponent("BBa_C0012")==null) {
			System.out.println("LacI_repressor component is missing");
		}
		// Removing the subject component below causes an exception.		
		//pIKELeftCassette.removeComponent(pIKELeftCassette.getComponent("BBa_R0040"));
		
		// Copying objects
		ComponentDefinition TetR_promoter_copy = (ComponentDefinition) document.createCopy(TetR_promoter, "BBa_K137046");
		Sequence seq = document.createSequence(
				"seq_K137046",
				"gtgctcagtatctctatcactgatagggatgtcaatctctatcactgatagggactctagtatat"
				+ "aaacgcagaaaggcccacccgaaggtgagccagtgtgactctagtagagagcgttcaccgaca"
				+ "aacaacagataaaacgaaaggc",
				Sequence.IUPAC_DNA
				);	
		TetR_promoter_copy.addSequence(seq);
		
		SBOLWriter.write(document,(System.out));
	}
}
