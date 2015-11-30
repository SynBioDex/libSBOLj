package org.sbolstandard.core2.examples;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

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

		// ComponentDefinition types
		HashSet<URI> ProteinType = new HashSet<URI>();
		ProteinType.add(ComponentDefinition.PROTEIN);
		
		HashSet<URI> DNAType = new HashSet<URI>();
		DNAType.add(ComponentDefinition.DNA);
		
		HashSet<URI> RNAType = new HashSet<URI>();
		RNAType.add(ComponentDefinition.RNA);
		
		HashSet<URI> ComplexType = new HashSet<URI>();
		ComplexType.add(ComponentDefinition.COMPLEX);

        // Interaction Types
		HashSet<URI> non_covalent_type = new HashSet<URI>();
		non_covalent_type.add(SystemsBiologyOntology.NON_COVALENT_BINDING);
		
		HashSet<URI> enhancement = new HashSet<URI>();
		enhancement.add(SystemsBiologyOntology.GENETIC_ENHANCEMENT);
		
		HashSet<URI> production = new HashSet<URI>();
		production.add(SystemsBiologyOntology.GENETIC_PRODUCTION);
		
		HashSet<URI> suppression = new HashSet<URI>();
		suppression.add(SystemsBiologyOntology.GENETIC_SUPPRESSION);
		
		SBOLDocument doc = new SBOLDocument();

		doc.setDefaultURIprefix("http://sbols.org/CRISPR_Example/");
		doc.setComplete(true);
		doc.setCreateDefaults(true);
		
		String version = "1.0";
		
		// Create ComponentDefinition for cas9_generic gene
		doc.createComponentDefinition("cas9_generic_gene", version, DNAType).addRole(SequenceOntology.PROMOTER);
		
		// Create ComponentDefinition for cas9_generic protein
		doc.createComponentDefinition("cas9_generic", version, ProteinType);

		// Create ComponentDefinition for gRNA_generic gene
		doc.createComponentDefinition("gRNA_generic_gene",version, DNAType).addRole(SequenceOntology.PROMOTER);
		
		// Create ComponentDefinition for gRNA_generic RNA
		doc.createComponentDefinition("gRNA_generic",version, RNAType);

		// Create ComponentDefinition for cas9_gRNA_complex
		doc.createComponentDefinition("cas9_gRNA_complex",version, ComplexType);

		// Create ComponentDefinition for target gene
		doc.createComponentDefinition("target_gene",version, DNAType).addRole(SequenceOntology.PROMOTER);

		// Create ComponentDefinition for target protein
		doc.createComponentDefinition("target",version, ProteinType);

		// Create ModuleDefinition for CRISPR_Repression_Template		
		ModuleDefinition  CRISPR_Template = doc.createModuleDefinition("CRISPR_Template", version);

		// Complex Formation Interaction for Cas9m_BFP and gRNA 
		Interaction Cas9Complex_Formation = CRISPR_Template.createInteraction("cas9_complex_formation", non_covalent_type);
		Cas9Complex_Formation.createParticipation("cas9_generic", "cas9_generic").addRole(SystemsBiologyOntology.REACTANT);
		Cas9Complex_Formation.createParticipation("gRNA_generic", "gRNA_generic").addRole(SystemsBiologyOntology.REACTANT);
		Cas9Complex_Formation.createParticipation("cas9_gRNA_complex", "cas9_gRNA_complex").addRole(SystemsBiologyOntology.PRODUCT);
		
		// Production of cas9m_generic from cas9m_generic_gene
		Interaction cas9m_production = CRISPR_Template.createInteraction("cas9_generic_production", production);
		cas9m_production.createParticipation("cas9_generic_gene", "cas9_generic_gene").addRole(SystemsBiologyOntology.PROMOTER);
		cas9m_production.createParticipation("cas9_generic", "cas9_generic").addRole(SystemsBiologyOntology.PRODUCT);
		
		// Production of gRNA from gRNA gene
		Interaction gRNA_production = CRISPR_Template.createInteraction("gRNA_production", production);
		gRNA_production.createParticipation("gRNA_generic_gene", "gRNA_generic_gene").addRole(SystemsBiologyOntology.PROMOTER);
		gRNA_production.createParticipation("gRNA_generic", "gRNA_generic").addRole(SystemsBiologyOntology.PRODUCT);

		// Production of target from target gene
		Interaction EYFP_production = CRISPR_Template.createInteraction("target_production", production);
		EYFP_production.createParticipation("target_gene", "target_gene").addRole(SystemsBiologyOntology.PROMOTER);
		EYFP_production.createParticipation("target", "target").addRole(SystemsBiologyOntology.PRODUCT);
	
		// Inhibition of target by cas9m_BFP_gRNA 
		Interaction target_generic_gene_inhibition = CRISPR_Template.createInteraction("target_gene_inhibition", suppression);
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
		doc.createComponentDefinition("pConst", version, DNAType);
		
		// Create ComponentDefinition for cas9m_BFP coding sequence
		doc.createComponentDefinition("cas9m_BFP_cds", version, DNAType);
		
		// Create ComponentDefinition for cas9m_BFP gene
		ComponentDefinition cas9m_BFP_gene = doc.createComponentDefinition("cas9m_BFP_gene", version, DNAType);
		cas9m_BFP_gene.addRole(SequenceOntology.PROMOTER);
		cas9m_BFP_gene.createSequenceConstraint("cas9m_BFP_gene_constraint", RestrictionType.PRECEDES, "pConst", "cas9m_BFP_cds");
		
		// Create ComponentDefintion for cas9m_BFP protein
		doc.createComponentDefinition("cas9m_BFP", version, ProteinType);
		
		// Create ComponentDefintion for CRa_U6 promoter
		ComponentDefinition CRa_U6 = doc.createComponentDefinition("CRa_U6", version, DNAType);
		CRa_U6.addRole(SequenceOntology.PROMOTER);
		CRa_U6.addSequence("CRa_U6_seq",version);
		
		// Create ComponentDefintion for gRNA_b coding sequence
		ComponentDefinition gRNA_b_cds = doc.createComponentDefinition("gRNA_b_cds", version, DNAType);
		gRNA_b_cds.addRole(SequenceOntology.CDS);
		gRNA_b_cds.addSequence("gRNA_b_seq", version);
		
		// Create ComponentDefinition for gRNA_b terminator
		doc.createComponentDefinition("gRNA_b_terminator", version, DNAType).addRole(SequenceOntology.TERMINATOR); 
		
		// Create ComponentDefinition for gRNA_b gene
		ComponentDefinition gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", version, DNAType);
		gRNA_b_gene.addRole(SequenceOntology.PROMOTER);
		cas9m_BFP_gene.createSequenceConstraint("gRNA_b_gene_constraint1", RestrictionType.PRECEDES, "CRa_U6", "gRNA_b_cds");
		cas9m_BFP_gene.createSequenceConstraint("gRNA_b_gene_constraint2", RestrictionType.PRECEDES, "gRNA_b_cds","gRNA_b_terminator");

		// Create ComponentDefinition for gRNA_b RNA
		doc.createComponentDefinition("gRNA_b", version, RNAType);
		  
		// Create ComponentDefinition for cas9m_BFP gRNA_b complex 
		doc.createComponentDefinition("cas9m_BFP_gRNA_b", version, ComplexType);
		
		// Create ComponentDefinition for mKate coding sequence
		ComponentDefinition mKate_cds = doc.createComponentDefinition("mKate_cds", version, DNAType);
		mKate_cds.addRole(SequenceOntology.CDS);
		mKate_cds.addSequence("mKate_seq", version);
		
		// Create ComponentDefinition for mKate gene
		ComponentDefinition mKate_gene = doc.createComponentDefinition("mKate_gene", version, DNAType);
		mKate_gene.addRole(SequenceOntology.PROMOTER);
		mKate_gene.createSequenceConstraint("mKate_gene_constraint", RestrictionType.PRECEDES, "pConst", "mKate_cds");
		
		// Create ComponentDefinition for mKate protein
		doc.createComponentDefinition("mKate", version, ProteinType);

		// Create ComponentDefinition for Gal4VP16 coding sequence
		ComponentDefinition Gal4VP16_cds = doc.createComponentDefinition("Gal4VP16_cds", version, DNAType);
		Gal4VP16_cds.addRole(SequenceOntology.CDS);
		
		// Create ComponentDefintion for Gal4VP16 gene
		ComponentDefinition Gal4VP16_gene = doc.createComponentDefinition("Gal4VP16_gene", version, DNAType);
		Gal4VP16_gene.addRole(SequenceOntology.PROMOTER);
		Gal4VP16_gene.createSequenceConstraint("GAL4VP16_gene_constraint", RestrictionType.PRECEDES, "pConst", "Gal4VP16_cds");
		
		// Create ComponentDefintion for Gal4VP16 protein
		doc.createComponentDefinition("Gal4VP16", version, ProteinType);
		
		// Create ComponentDefinition for CRP_b promoter
		ComponentDefinition CRP_b = doc.createComponentDefinition("CRP_b", version, DNAType);
		CRP_b.addRole(SequenceOntology.PROMOTER);
		CRP_b.addSequence("CRP_b_seq", version);
		
		// Create ComponentDefintiion for EYFP coding sequence
		ComponentDefinition EYFP_cds = doc.createComponentDefinition("EYFP_cds", version, DNAType);
		EYFP_cds.addRole(SequenceOntology.CDS);
		
		// Create ComponentDefinition for EYFP gene
		ComponentDefinition EYFP_gene = doc.createComponentDefinition("EYFP_gene", version, DNAType);
		EYFP_gene.addRole(SequenceOntology.PROMOTER);
		EYFP_gene.createSequenceConstraint("EYFP_gene_constraint", RestrictionType.PRECEDES, "CRP_b", "EYFP_cds");

		// Create ComponentDefintiion for EYFP protein
		doc.createComponentDefinition("EYFP", version, ProteinType);

		// Create ModuleDefintion for CRISPR Repression
		ModuleDefinition CRISPR_Repression = doc.createModuleDefinition("CRISPR_Repression", version);
		
		// Create the FunctionalComponents for the ModuleDefinition CRISPR_Repression
		CRISPR_Repression.createFunctionalComponent("cas9m_BFP", AccessType.PRIVATE, "cas9m_BFP", version, DirectionType.NONE);
		CRISPR_Repression.createFunctionalComponent("cas9m_BFP_gene", AccessType.PRIVATE, "cas9m_BFP_gene", version, DirectionType.NONE);
		
		CRISPR_Repression.createFunctionalComponent("gRNA_b", AccessType.PRIVATE, "gRNA_b", version, DirectionType.NONE);
		CRISPR_Repression.createFunctionalComponent("gRNA_b_gene", AccessType.PRIVATE, "gRNA_b_gene", version, DirectionType.NONE);

		CRISPR_Repression.createFunctionalComponent("mKate", AccessType.PRIVATE, "mKate", version, DirectionType.NONE);
		CRISPR_Repression.createFunctionalComponent("mKate_gene", AccessType.PRIVATE, "mKate_gene", version, DirectionType.NONE);

		CRISPR_Repression.createFunctionalComponent("Gal4VP16", AccessType.PRIVATE, "Gal4VP16", version, DirectionType.NONE);
		CRISPR_Repression.createFunctionalComponent("Gal4VP16_gene", AccessType.PRIVATE, "Gal4VP16_gene", version, DirectionType.NONE);

		CRISPR_Repression.createFunctionalComponent("EYFP", AccessType.PRIVATE, "EYFP", version, DirectionType.NONE);
		CRISPR_Repression.createFunctionalComponent("EYFP_gene", AccessType.PRIVATE, "EYFP_gene", version, DirectionType.NONE);

		CRISPR_Repression.createFunctionalComponent("cas9m_BFP_gRNA_b", AccessType.PRIVATE,"cas9m_BFP_gRNA_b", version, DirectionType.NONE);

		/* Production of mKate from the mKate gene */
		Interaction mKate_production = CRISPR_Repression.createInteraction("mKate_production", production);
		mKate_production.createParticipation("mKate", "mKate").addRole(SystemsBiologyOntology.PRODUCT);
		mKate_production.createParticipation("mKate_gene", "mKate_gene").addRole(SystemsBiologyOntology.PROMOTER);

		// Production of GAL4VP16 from the GAL4VP16 gene
		Interaction GAL4VP16_production = CRISPR_Repression.createInteraction("GAL4VP16_production", production);
		GAL4VP16_production.createParticipation("GAL4VP16_gene", "Gal4VP16_gene").addRole(SystemsBiologyOntology.PROMOTER);
		GAL4VP16_production.createParticipation("GAL4VP16", "Gal4VP16").addRole(SystemsBiologyOntology.PRODUCT);
		
		// Activation of EYFP production by GAL4VP16
		Interaction EYFP_Activation = CRISPR_Repression.createInteraction("EYFP_Activation", enhancement);
		EYFP_Activation.createParticipation("GAL4VP16", "Gal4VP16").addRole(SystemsBiologyOntology.STIMULATOR);
		EYFP_Activation.createParticipation("EYFP_gene", "EYFP_gene").addRole(SystemsBiologyOntology.PROMOTER);
		
		// Create Template Module
		Module Template_Module = CRISPR_Repression.createModule("CRISPR_Template", "CRISPR_Template", version);
		
		// Add MapsTos to Template Module 
		Template_Module.createMapsTo("cas9m_BFP_map", RefinementType.USELOCAL, "cas9m_BFP", "cas9_generic");
		Template_Module.createMapsTo("cas9m_BFP_gene_map", RefinementType.USELOCAL, "cas9m_BFP_gene", "cas9_generic_gene");
		Template_Module.createMapsTo("gRNA_b_map", RefinementType.USELOCAL, "gRNA_b", "gRNA_generic");
		Template_Module.createMapsTo("gRNA_b_gene_map", RefinementType.USELOCAL, "gRNA_b_gene", "gRNA_generic_gene");
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
