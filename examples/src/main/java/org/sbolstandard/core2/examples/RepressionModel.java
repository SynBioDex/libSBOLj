package org.sbolstandard.core2.examples;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Module;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.RefinementType;
import org.sbolstandard.core2.RestrictionType;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.Sequence;
import org.sbolstandard.core2.SequenceOntology;
import org.sbolstandard.core2.SystemsBiologyOntology;

import uk.ac.ncl.intbio.core.io.CoreIoException;

/*
 * CRISPR_Repression Model Example
 * 
 * @author Meher Samineni
 * @author Zach Zundel
 * 
 */

public class RepressionModel {

	public static void main(String[] args) throws URISyntaxException {
		
		SBOLDocument doc = new SBOLDocument();

		doc.setDefaultURIprefix("http://sbols.org/CRISPR_Example/");
		doc.setComplete(true);
		doc.setCreateDefaults(true);
		
		String version = "1.0";
		
		// Create ComponentDefinition for cas9_generic protein
		doc.createComponentDefinition("cas9_generic", version, ComponentDefinition.PROTEIN);
		
		// Create ComponentDefinition for gRNA_generic RNA
		doc.createComponentDefinition("gRNA_generic",version, ComponentDefinition.RNA);

		// Create ComponentDefinition for cas9_gRNA_complex
		doc.createComponentDefinition("cas9_gRNA_complex",version, ComponentDefinition.COMPLEX);

		// Create ComponentDefinition for target gene
		doc.createComponentDefinition("target_gene",version, ComponentDefinition.DNA).addRole(SequenceOntology.PROMOTER);

		// Create ComponentDefinition for target protein
		doc.createComponentDefinition("target",version, ComponentDefinition.PROTEIN);

		// Create ModuleDefinition for CRISPR_Repression_Template		
		ModuleDefinition  CRISPR_Template = doc.createModuleDefinition("CRISPR_Template", version);

		// Complex Formation Interaction for Cas9m_BFP and gRNA 
		Interaction Cas9Complex_Formation = CRISPR_Template.createInteraction("cas9_complex_formation", SystemsBiologyOntology.NON_COVALENT_BINDING);
		Cas9Complex_Formation.createParticipation("cas9_generic", "cas9_generic").addRole(SystemsBiologyOntology.REACTANT);
		Cas9Complex_Formation.createParticipation("gRNA_generic", "gRNA_generic").addRole(SystemsBiologyOntology.REACTANT);
		Cas9Complex_Formation.createParticipation("cas9_gRNA_complex", "cas9_gRNA_complex").addRole(SystemsBiologyOntology.PRODUCT);

		// Production of target from target gene
		Interaction EYFP_production = CRISPR_Template.createInteraction("target_production", SystemsBiologyOntology.GENETIC_PRODUCTION);
		EYFP_production.createParticipation("target_gene", "target_gene").addRole(SystemsBiologyOntology.PROMOTER);
		EYFP_production.createParticipation("target", "target").addRole(SystemsBiologyOntology.PRODUCT);
	
		// Inhibition of target by cas9m_BFP_gRNA 
		Interaction target_generic_gene_inhibition = CRISPR_Template.createInteraction("target_gene_inhibition", SystemsBiologyOntology.GENETIC_SUPPRESSION);
		target_generic_gene_inhibition.createParticipation("cas9_gRNA_complex", "cas9_gRNA_complex").addRole(SystemsBiologyOntology.INHIBITOR);
		target_generic_gene_inhibition.createParticipation("target_gene", "target_gene").addRole(SystemsBiologyOntology.PROMOTER);
		
		// Create Sequence for CRa_U6 promoter
		String CRa_U6_seq_elements = "GGTTTACCGAGCTCTTATTGGTTTTCAAACTTCATTGACTGTGCC" +
                "AAGGTCGGGCAGGAAGAGGGCCTATTTCCCATGATTCCTTCATAT" +
                "TTGCATATACGATACAAGGCTGTTAGAGAGATAATTAGAATTAAT" +
                "TTGACTGTAAACACAAAGATATTAGTACAAAATACGTGACGTAGA" +
                "AAGTAATAATTTCTTGGGTAGTTTGCAGTTTTAAAATTATGTTTT" +
                "AAAATGGACTATCATATGCTTACCGTAACTTGAAATATAGAACCG" +
                "ATCCTCCCATTGGTATATATTATAGAACCGATCCTCCCATTGGCT" +
                "TGTGGAAAGGACGAAACACCGTACCTCATCAGGAACATGTGTTTA" +
                "AGAGCTATGCTGGAAACAGCAGAAATAGCAAGTTTAAATAAGGCT" +
                "AGTCCGTTATCAACTTGAAAAAGTGGCACCGAGTCGGTGCTTTTT" +
                "TTGGTGCGTTTTTATGCTTGTAGTATTGTATAATGTTTTT";
		doc.createSequence("CRa_U6_seq", version, CRa_U6_seq_elements, Sequence.IUPAC_DNA); 
		
		// Create Sequence for gRNA_b coding sequence
		String gRNA_b_elements = "AAGGTCGGGCAGGAAGAGGGCCTATTTCCCATGATTCCTTCATAT" +
                "TTGCATATACGATACAAGGCTGTTAGAGAGATAATTAGAATTAAT" +
                "TTGACTGTAAACACAAAGATATTAGTACAAAATACGTGACGTAGA" +
                "AAGTAATAATTTCTTGGGTAGTTTGCAGTTTTAAAATTATGTTTT" +
                "AAAATGGACTATCATATGCTTACCGTAACTTGAAAGTATTTCGAT" +
                "TTCTTGGCTTTATATATCTTGTGGAAAGGACGAAACACCGTACCT" +
                "CATCAGGAACATGTGTTTAAGAGCTATGCTGGAAACAGCAGAAAT" +
                "AGCAAGTTTAAATAAGGCTAGTCCGTTATCAACTTGAAAAAGTGG" +
                "CACCGAGTCGGTGCTTTTTTT";
		doc.createSequence("gRNA_b_seq", version, gRNA_b_elements, Sequence.IUPAC_DNA);
		
		// Create Sequence for mKate
		String mKate_seq_elements = "TCTAAGGGCGAAGAGCTGATTAAGGAGAACATGCACATGAAGCTG" +
                "TACATGGAGGGCACCGTGAACAACCACCACTTCAAGTGCACATCC" +
                "GAGGGCGAAGGCAAGCCCTACGAGGGCACCCAGACCATGAGAATC" +
                "AAGGTGGTCGAGGGCGGCCCTCTCCCCTTCGCCTTCGACATCCTG" +
                "GCTACCAGCTTCATGTACGGCAGCAAAACCTTCATCAACCACACC" +
                "CAGGGCATCCCCGACTTCTTTAAGCAGTCCTTCCCTGAGGTAAGT" +
                "GGTCCTACCTCATCAGGAACATGTGTTTTAGAGCTAGAAATAGCA" +
                "AGTTAAAATAAGGCTAGTCCGTTATCAACTTGAAAAAGTGGCACC" +
                "GAGTCGGTGCTACTAACTCTCGAGTCTTCTTTTTTTTTTTCACAG" +
                "GGCTTCACATGGGAGAGAGTCACCACATACGAAGACGGGGGCGTG" +
                "CTGACCGCTACCCAGGACACCAGCCTCCAGGACGGCTGCCTCATC" +
                "TACAACGTCAAGATCAGAGGGGTGAACTTCCCATCCAACGGCCCT" +
                "GTGATGCAGAAGAAAACACTCGGCTGGGAGGCCTCCACCGAGATG" +
                "CTGTACCCCGCTGACGGCGGCCTGGAAGGCAGAAGCGACATGGCC" +
                "CTGAAGCTCGTGGGCGGGGGCCACCTGATCTGCAACTTGAAGACC" +
                "ACATACAGATCCAAGAAACCCGCTAAGAACCTCAAGATGCCCGGC" +
                "GTCTACTATGTGGACAGAAGACTGGAAAGAATCAAGGAGGCCGAC" +
                "AAAGAGACCTACGTCGAGCAGCACGAGGTGGCTGTGGCCAGATAC" +
                "TGCG";
		doc.createSequence("mKate_seq", version, mKate_seq_elements, Sequence.IUPAC_DNA);

		// Create Sequence for CRP_b promoter
		String CRP_b_seq_elements =  "GCTCCGAATTTCTCGACAGATCTCATGTGATTACGCCAAGCTACG" +
                "GGCGGAGTACTGTCCTCCGAGCGGAGTACTGTCCTCCGAGCGGAG" +
                "TACTGTCCTCCGAGCGGAGTACTGTCCTCCGAGCGGAGTTCTGTC" +
                "CTCCGAGCGGAGACTCTAGATACCTCATCAGGAACATGTTGGAAT" +
                "TCTAGGCGTGTACGGTGGGAGGCCTATATAAGCAGAGCTCGTTTA" +
                "GTGAACCGTCAGATCGCCTCGAGTACCTCATCAGGAACATGTTGG" +
                "ATCCAATTCGACC";
		doc.createSequence("CRP_b_seq", version, CRP_b_seq_elements, Sequence.IUPAC_DNA);
		
		// Create ComponentDefinition for a Constitutive Promoter
		doc.createComponentDefinition("pConst", version, ComponentDefinition.DNA);
		
		// Create ComponentDefinition for cas9m_BFP coding sequence
		doc.createComponentDefinition("cas9m_BFP_cds", version, ComponentDefinition.DNA);
		
		// Create ComponentDefinition for cas9m_BFP gene
		ComponentDefinition cas9m_BFP_gene = doc.createComponentDefinition("cas9m_BFP_gene", version, ComponentDefinition.DNA);
		cas9m_BFP_gene.addRole(SequenceOntology.PROMOTER);
		cas9m_BFP_gene.createSequenceConstraint("cas9m_BFP_gene_constraint", RestrictionType.PRECEDES, "pConst", "cas9m_BFP_cds");
		
		// Create ComponentDefintion for cas9m_BFP protein
		doc.createComponentDefinition("cas9m_BFP", version, ComponentDefinition.PROTEIN);
		
		// Create ComponentDefintion for CRa_U6 promoter
		ComponentDefinition CRa_U6 = doc.createComponentDefinition("CRa_U6", version, ComponentDefinition.DNA);
		CRa_U6.addRole(SequenceOntology.PROMOTER);
		CRa_U6.addSequence("CRa_U6_seq");
		
		// Create ComponentDefintion for gRNA_b coding sequence
		ComponentDefinition gRNA_b_nc = doc.createComponentDefinition("gRNA_b_nc", version, ComponentDefinition.DNA);
		// TODO: need to change gRNA gene
		gRNA_b_nc.addRole(SequenceOntology.CDS);
		gRNA_b_nc.addSequence("gRNA_b_seq");
		
		// Create ComponentDefinition for gRNA_b terminator
		doc.createComponentDefinition("gRNA_b_terminator", version, ComponentDefinition.DNA).addRole(SequenceOntology.TERMINATOR); 
		
		// Create ComponentDefinition for gRNA_b gene
		ComponentDefinition gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", version, ComponentDefinition.DNA);
		gRNA_b_gene.addRole(SequenceOntology.PROMOTER);
		cas9m_BFP_gene.createSequenceConstraint("gRNA_b_gene_constraint1", RestrictionType.PRECEDES, "CRa_U6", "gRNA_b_nc");
		cas9m_BFP_gene.createSequenceConstraint("gRNA_b_gene_constraint2", RestrictionType.PRECEDES, "gRNA_b_nc","gRNA_b_terminator");

		// Create ComponentDefinition for gRNA_b RNA
		doc.createComponentDefinition("gRNA_b", version, ComponentDefinition.RNA);
		  
		// Create ComponentDefinition for cas9m_BFP gRNA_b complex 
		doc.createComponentDefinition("cas9m_BFP_gRNA_b", version, ComponentDefinition.COMPLEX);
		
		// Create ComponentDefinition for mKate coding sequence
		ComponentDefinition mKate_cds = doc.createComponentDefinition("mKate_cds", version, ComponentDefinition.DNA);
		mKate_cds.addRole(SequenceOntology.CDS);
		mKate_cds.addSequence("mKate_seq");
		
		// Create ComponentDefinition for mKate gene
		ComponentDefinition mKate_gene = doc.createComponentDefinition("mKate_gene", version, ComponentDefinition.DNA);
		mKate_gene.addRole(SequenceOntology.PROMOTER);
		mKate_gene.createSequenceConstraint("mKate_gene_constraint", RestrictionType.PRECEDES, "pConst", "mKate_cds");
		
		// Create ComponentDefinition for mKate protein
		doc.createComponentDefinition("mKate", version, ComponentDefinition.PROTEIN);

		// Create ComponentDefinition for Gal4VP16 coding sequence
		ComponentDefinition Gal4VP16_cds = doc.createComponentDefinition("Gal4VP16_cds", version, ComponentDefinition.DNA);
		Gal4VP16_cds.addRole(SequenceOntology.CDS);
		
		// Create ComponentDefintion for Gal4VP16 gene
		ComponentDefinition Gal4VP16_gene = doc.createComponentDefinition("Gal4VP16_gene", version, ComponentDefinition.DNA);
		Gal4VP16_gene.addRole(SequenceOntology.PROMOTER);
		Gal4VP16_gene.createSequenceConstraint("GAL4VP16_gene_constraint", RestrictionType.PRECEDES, "pConst", "Gal4VP16_cds");
		
		// Create ComponentDefintion for Gal4VP16 protein
		doc.createComponentDefinition("Gal4VP16", version, ComponentDefinition.PROTEIN);
		
		// Create ComponentDefinition for CRP_b promoter
		ComponentDefinition CRP_b = doc.createComponentDefinition("CRP_b", version, ComponentDefinition.DNA);
		CRP_b.addRole(SequenceOntology.PROMOTER);
		CRP_b.addSequence("CRP_b_seq");
		
		// Create ComponentDefintiion for EYFP coding sequence
		ComponentDefinition EYFP_cds = doc.createComponentDefinition("EYFP_cds", version, ComponentDefinition.DNA);
		EYFP_cds.addRole(SequenceOntology.CDS);
		
		// Create ComponentDefinition for EYFP gene
		ComponentDefinition EYFP_gene = doc.createComponentDefinition("EYFP_gene", version, ComponentDefinition.DNA);
		EYFP_gene.addRole(SequenceOntology.PROMOTER);
		EYFP_gene.createSequenceConstraint("EYFP_gene_constraint", RestrictionType.PRECEDES, "CRP_b", "EYFP_cds");

		// Create ComponentDefintiion for EYFP protein
		doc.createComponentDefinition("EYFP", version, ComponentDefinition.PROTEIN);

		// Create ModuleDefintion for CRISPR Repression
		ModuleDefinition CRPb_circuit = doc.createModuleDefinition("CRPb_characterization_Circuit", version);
		
		// Create the FunctionalComponents for the ModuleDefinition CRISPR_Repression
		CRPb_circuit.createFunctionalComponent("cas9m_BFP", AccessType.PRIVATE, "cas9m_BFP", version, DirectionType.NONE);
		CRPb_circuit.createFunctionalComponent("cas9m_BFP_gene", AccessType.PRIVATE, "cas9m_BFP_gene", version, DirectionType.NONE);
		
		CRPb_circuit.createFunctionalComponent("gRNA_b", AccessType.PRIVATE, "gRNA_b", version, DirectionType.NONE);
		CRPb_circuit.createFunctionalComponent("gRNA_b_gene", AccessType.PRIVATE, "gRNA_b_gene", version, DirectionType.NONE);

		CRPb_circuit.createFunctionalComponent("mKate", AccessType.PRIVATE, "mKate", version, DirectionType.NONE);
		CRPb_circuit.createFunctionalComponent("mKate_gene", AccessType.PRIVATE, "mKate_gene", version, DirectionType.NONE);

		CRPb_circuit.createFunctionalComponent("Gal4VP16", AccessType.PRIVATE, "Gal4VP16", version, DirectionType.NONE);
		CRPb_circuit.createFunctionalComponent("Gal4VP16_gene", AccessType.PRIVATE, "Gal4VP16_gene", version, DirectionType.NONE);

		CRPb_circuit.createFunctionalComponent("EYFP", AccessType.PRIVATE, "EYFP", version, DirectionType.NONE);
		CRPb_circuit.createFunctionalComponent("EYFP_gene", AccessType.PRIVATE, "EYFP_gene", version, DirectionType.NONE);

		CRPb_circuit.createFunctionalComponent("cas9m_BFP_gRNA_b", AccessType.PRIVATE,"cas9m_BFP_gRNA_b", version, DirectionType.NONE);

		/* Production of mKate from the mKate gene */
		Interaction mKate_production = CRPb_circuit.createInteraction("mKate_production", SystemsBiologyOntology.GENETIC_PRODUCTION);
		mKate_production.createParticipation("mKate", "mKate").addRole(SystemsBiologyOntology.PRODUCT);
		mKate_production.createParticipation("mKate_gene", "mKate_gene").addRole(SystemsBiologyOntology.PROMOTER);

		// Production of GAL4VP16 from the GAL4VP16 gene
		Interaction GAL4VP16_production = CRPb_circuit.createInteraction("Gal4VP16_production", SystemsBiologyOntology.GENETIC_PRODUCTION);
		GAL4VP16_production.createParticipation("Gal4VP16_gene", "Gal4VP16_gene").addRole(SystemsBiologyOntology.PROMOTER);
		GAL4VP16_production.createParticipation("Gal4VP16", "Gal4VP16").addRole(SystemsBiologyOntology.PRODUCT);

		// Production of cas9m_BFP from the cas9m_BFP gene
		Interaction cas9m_BFP_production = CRPb_circuit.createInteraction("cas9m_BFP_production", SystemsBiologyOntology.GENETIC_PRODUCTION);
		cas9m_BFP_production.createParticipation("cas9m_BFP_gene", "cas9m_BFP_gene").addRole(SystemsBiologyOntology.PROMOTER);
		cas9m_BFP_production.createParticipation("cas9m_BFP", "cas9m_BFP").addRole(SystemsBiologyOntology.PRODUCT);

		// Production of gRNA_b from the gRNA_b gene
		Interaction gRNA_b_production = CRPb_circuit.createInteraction("gRNA_b_production", SystemsBiologyOntology.GENETIC_PRODUCTION);
		gRNA_b_production.createParticipation("gRNA_b_gene", "gRNA_b_gene").addRole(SystemsBiologyOntology.PROMOTER);
		gRNA_b_production.createParticipation("gRNA_b", "gRNA_b").addRole(SystemsBiologyOntology.PRODUCT);
		
		// Activation of EYFP production by GAL4VP16
		Interaction EYFP_Activation = CRPb_circuit.createInteraction("EYFP_Activation", SystemsBiologyOntology.GENETIC_ENHANCEMENT);
		EYFP_Activation.createParticipation("Gal4VP16", "Gal4VP16").addRole(SystemsBiologyOntology.STIMULATOR);
		EYFP_Activation.createParticipation("EYFP_gene", "EYFP_gene").addRole(SystemsBiologyOntology.PROMOTER);
		
		// Degradation of mKate
		Interaction mKate_deg = CRPb_circuit.createInteraction("mKate_deg", SystemsBiologyOntology.DEGRADATION);
		mKate_deg.createParticipation("mKate", "mKate").addRole(SystemsBiologyOntology.REACTANT);
		
		// Degradation of GAL4VP16
		Interaction GAL4VP16_deg = CRPb_circuit.createInteraction("Gal4VP16_deg", SystemsBiologyOntology.DEGRADATION);
		GAL4VP16_deg.createParticipation("Gal4VP16", "Gal4VP16").addRole(SystemsBiologyOntology.REACTANT);
		
		// Degradation of cas9m_BFP
		Interaction cas9m_BFP_deg = CRPb_circuit.createInteraction("cas9m_BFP_deg", SystemsBiologyOntology.DEGRADATION);
		cas9m_BFP_deg.createParticipation("cas9m_BFP", "cas9m_BFP").addRole(SystemsBiologyOntology.REACTANT);
		
		// Degradation of gRNA_b
		Interaction gRNA_b_deg = CRPb_circuit.createInteraction("gRNA_b_deg", SystemsBiologyOntology.DEGRADATION);
		gRNA_b_deg.createParticipation("gRNA_b", "gRNA_b").addRole(SystemsBiologyOntology.REACTANT);
		
		// Degradation of EYFP
		Interaction EYFP_deg = CRPb_circuit.createInteraction("EYFP_deg", SystemsBiologyOntology.DEGRADATION);
		EYFP_deg.createParticipation("EYFP", "EYFP").addRole(SystemsBiologyOntology.REACTANT);
		
		// Degradation of cas9m_BFP_gRNA_b
		Interaction cas9m_BFP_gRNA_b_deg = CRPb_circuit.createInteraction("cas9m_BFP_gRNA_b_deg", SystemsBiologyOntology.DEGRADATION);
		cas9m_BFP_gRNA_b_deg.createParticipation("cas9m_BFP_gRNA_b", "cas9m_BFP_gRNA_b").addRole(SystemsBiologyOntology.REACTANT);
		
		// Create Template Module
		Module Template_Module = CRPb_circuit.createModule("CRISPR_Template", "CRISPR_Template", version);
		
		// Add MapsTos to Template Module 
		Template_Module.createMapsTo("cas9m_BFP_map", RefinementType.USELOCAL, "cas9m_BFP", "cas9_generic");
		Template_Module.createMapsTo("gRNA_b_map", RefinementType.USELOCAL, "gRNA_b", "gRNA_generic");
		Template_Module.createMapsTo("cas9m_BFP_gRNA_map", RefinementType.USELOCAL, "cas9m_BFP_gRNA_b", "cas9_gRNA_complex");
		Template_Module.createMapsTo("EYFP_map", RefinementType.USELOCAL, "EYFP", "target");
		Template_Module.createMapsTo("EYFP_gene_map", RefinementType.USELOCAL, "EYFP_gene", "target_gene");
		
		try {
			SBOLWriter.write(doc, "/Users/myers/Downloads/crispr.rdf");
		}
		catch (XMLStreamException | FactoryConfigurationError | CoreIoException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
