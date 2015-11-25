package org.sbolstandard.core2.examples;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Module;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.RefinementType;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.Sequence;
import org.sbolstandard.core2.SystemsBiologyOntology;

/*
 * CRISPR_Repression Model Example
 * 
 * @author Meher Samineni
 * @author Zach Zundel
 * 
 */

public class RepressionModel {

	public static void main(String[] args) throws URISyntaxException {
	
		String prURI="http://async.utah.edu/examples/CRISPR_Repression";
		SBOLDocument repressionDoc = new SBOLDocument();

		repressionDoc.setDefaultURIprefix(prURI);
		repressionDoc.setTypesInURIs(false);
		repressionDoc.setComplete(true);
		repressionDoc.setCreateDefaults(true);
		
		
		//make sequences for CD		
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
		
		
		Sequence cra_u6_seq = repressionDoc.createSequence("CRa_U6_promoter_seq", CRa_U6_seq_elements, Sequence.IUPAC_DNA); //find promoter type from ontology
		
		String gRNA_b_elements = "AAGGTCGGGCAGGAAGAGGGCCTATTTCCCATGATTCCTTCATAT" +
                "TTGCATATACGATACAAGGCTGTTAGAGAGATAATTAGAATTAAT" +
                "TTGACTGTAAACACAAAGATATTAGTACAAAATACGTGACGTAGA" +
                "AAGTAATAATTTCTTGGGTAGTTTGCAGTTTTAAAATTATGTTTT" +
                "AAAATGGACTATCATATGCTTACCGTAACTTGAAAGTATTTCGAT" +
                "TTCTTGGCTTTATATATCTTGTGGAAAGGACGAAACACCGTACCT" +
                "CATCAGGAACATGTGTTTAAGAGCTATGCTGGAAACAGCAGAAAT" +
                "AGCAAGTTTAAATAAGGCTAGTCCGTTATCAACTTGAAAAAGTGG" +
                "CACCGAGTCGGTGCTTTTTTT";
		
		Sequence gRNA_b_seq = repressionDoc.createSequence("gRNA_b_seq", gRNA_b_elements, Sequence.IUPAC_DNA);
		
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
		
		Sequence mKate_seq = repressionDoc.createSequence("mKate_seq", mKate_seq_elements, Sequence.IUPAC_DNA);

		String CRP_b_seq_elements =  "GCTCCGAATTTCTCGACAGATCTCATGTGATTACGCCAAGCTACG" +
                "GGCGGAGTACTGTCCTCCGAGCGGAGTACTGTCCTCCGAGCGGAG" +
                "TACTGTCCTCCGAGCGGAGTACTGTCCTCCGAGCGGAGTTCTGTC" +
                "CTCCGAGCGGAGACTCTAGATACCTCATCAGGAACATGTTGGAAT" +
                "TCTAGGCGTGTACGGTGGGAGGCCTATATAAGCAGAGCTCGTTTA" +
                "GTGAACCGTCAGATCGCCTCGAGTACCTCATCAGGAACATGTTGG" +
                "ATCCAATTCGACC";
		
		Sequence CRP_b_seq = repressionDoc.createSequence("CRP_b_seq", CRP_b_seq_elements, Sequence.IUPAC_DNA);

		//make ComponentDefinitions for the ModuleDefinitions
		
		HashSet<URI> ProteinType = new HashSet<URI>();
		ProteinType.add(ComponentDefinition.PROTEIN);
		
		HashSet<URI> DNAType = new HashSet<URI>();
		DNAType.add(ComponentDefinition.DNA);
		
		HashSet<URI> RNAType = new HashSet<URI>();
		RNAType.add(ComponentDefinition.RNA);
		
		//the cas9m_BFP protein which is unspecified
		ComponentDefinition cas9m_unspecified_protein = repressionDoc.createComponentDefinition("cas9m_unspecified_protein", "1.0", ProteinType);
		
		//cas9m_BFP coding sequence
		ComponentDefinition cas9m_BFP_coding = repressionDoc.createComponentDefinition("cas9m_BFP", "1.0", DNAType);
		
		//pConst
		ComponentDefinition pConst = repressionDoc.createComponentDefinition("pConst", "1.0", DNAType);
		
		//pieced together version of cas9
		ComponentDefinition cas9m_BFP_components = repressionDoc.createComponentDefinition("cas9m_BFP_components", "1.0", DNAType);
		
		//add the components to cas9
		repressionDoc.getComponentDefinition(cas9m_BFP_components.getIdentity()).createComponent("cas9_BFP_component", AccessType.PUBLIC, cas9m_BFP_coding.getIdentity());
		repressionDoc.getComponentDefinition(cas9m_BFP_components.getIdentity()).createComponent("cas9m_BFP_promoter_component", AccessType.PUBLIC, pConst.getIdentity());

		//gRNA_b mRNA
		ComponentDefinition gRNA_b_mRNA = repressionDoc.createComponentDefinition("gRNA_b_mRNA", "1.0", RNAType);
		
		//CRa_U6 promoter
		ComponentDefinition CRa_U6_promoter = repressionDoc.createComponentDefinition("CRa_U6_promoter", "1.0", DNAType);
		
		repressionDoc.getComponentDefinition(CRa_U6_promoter.getIdentity()).addSequence(cra_u6_seq);
		
		//gRNA_b coding sequence
		ComponentDefinition gRNA_b_coding_sequence = repressionDoc.createComponentDefinition("gRNA_b_coding_sequence", "1.0", DNAType);
		repressionDoc.getComponentDefinition(gRNA_b_coding_sequence.getIdentity()).addSequence(gRNA_b_seq);
		
		//gRNA_b_terminator
		ComponentDefinition gRNA_b_terminator = repressionDoc.createComponentDefinition("gRNA_b_terminator", "1.0", DNAType); 
		
		//pieced together version of gRNA
		ComponentDefinition gRNA_components = repressionDoc.createComponentDefinition("gRNA_components", "1.0", DNAType);
		
		//add the components gRNA
		repressionDoc.getComponentDefinition(gRNA_components.getIdentity()).createComponent("CRa_U6_promoter_component", AccessType.PUBLIC, CRa_U6_promoter.getIdentity());
		repressionDoc.getComponentDefinition(gRNA_components.getIdentity()).createComponent("gRNA_b_coding_seq_component", AccessType.PUBLIC, gRNA_b_coding_sequence.getIdentity());
		
		
		gRNA_components.createComponent("gRNA_b_terminator_component", AccessType.PUBLIC, gRNA_b_terminator.getDisplayId(), "1.0");
		
		//unspecified_mKate_protein
		ComponentDefinition mKate = repressionDoc.createComponentDefinition("mKate", "1.0", DNAType);
		
		//mKate_coding_sequence
		ComponentDefinition mKate_coding_sequence = repressionDoc.createComponentDefinition("mKate_coding_sequence", "1.0", DNAType);
		repressionDoc.getComponentDefinition(mKate_coding_sequence.getIdentity()).addSequence(mKate_seq);
		
		//mKate CD pieced together
		ComponentDefinition mKate_components = repressionDoc.createComponentDefinition("mKate_components", "1.0", DNAType);
		
		//add components to mKate_components
		repressionDoc.getComponentDefinition(mKate.getIdentity()).createComponent("mKate_promoter_component", AccessType.PUBLIC, pConst.getIdentity());
		repressionDoc.getComponentDefinition(mKate_components.getIdentity()).createComponent("mKate_codingSeq_component", AccessType.PUBLIC, mKate_coding_sequence.getIdentity());
		
		//Gal4VP16 protein
		ComponentDefinition Gal4VP16 = repressionDoc.createComponentDefinition("Gal4VP16", "1.0", DNAType);

		//make Gal4VP16_codng Sequence
		ComponentDefinition Gal4VP16_coding_sequence = repressionDoc.createComponentDefinition("Gal4VP16_coding_sequence", "1.0", DNAType);
		
		//make pieced together version of Gal4VP16_components
		ComponentDefinition Gal4VP16_components = repressionDoc.createComponentDefinition("Gal4VP16_components", "1.0", DNAType);

		//add components to Gal4VP16_components
		repressionDoc.getComponentDefinition(Gal4VP16_components.getIdentity()).createComponent("Gal4VP16_promoter_component", AccessType.PUBLIC, pConst.getIdentity());
		repressionDoc.getComponentDefinition(Gal4VP16_components.getIdentity()).createComponent("Gal4VP16_codingSeq_component", AccessType.PUBLIC, Gal4VP16_coding_sequence.getIdentity());
  
		//cas9m_BFP_gRNA_b 
		ComponentDefinition cas9m_BFP_gRNA_b_components = repressionDoc.createComponentDefinition("cas9m_BFP_gRNA_b_components", "1.0", DNAType);
		
		//piece together components for cas9m_BFP_gRNA_b 
		repressionDoc.getComponentDefinition(cas9m_BFP_gRNA_b_components.getIdentity()).createComponent("mKate_component", AccessType.PUBLIC, mKate.getIdentity());
		repressionDoc.getComponentDefinition(cas9m_BFP_gRNA_b_components.getIdentity()).createComponent("gRNA_mRNA_component", AccessType.PUBLIC, gRNA_b_mRNA.getIdentity());

		//EYFP_protein
		ComponentDefinition EYFP = repressionDoc.createComponentDefinition("EYFP", "1.0", ProteinType);
		
		//EYFP genetic coding sequence
		ComponentDefinition EYFP_coding_sequence = repressionDoc.createComponentDefinition("EYFP_coding_sequence", "1.0", DNAType);
		
		//CRP_b_promoter
		ComponentDefinition CRP_b_promoter = repressionDoc.createComponentDefinition("CRP_b_promoter", "1.0", ProteinType);
		repressionDoc.getComponentDefinition(CRP_b_promoter.getIdentity()).addSequence(CRP_b_seq);
		
		//piece together components for EYFP_components
		ComponentDefinition EYFP_components = repressionDoc.createComponentDefinition("EYFP_components", "1.0", DNAType);
				
		//add components to EYFP_components
		repressionDoc.getComponentDefinition(EYFP_components.getIdentity()).createComponent("EYFP_coding_sequence_component", AccessType.PUBLIC, EYFP_coding_sequence.getIdentity());
		repressionDoc.getComponentDefinition(EYFP_components.getIdentity()).createComponent("CRP_b_promoter_component", AccessType.PUBLIC, CRP_b_promoter.getIdentity());
			
		//create Component Definition for CRISPR_Repression_Template
		
		//cas9_generic
		ComponentDefinition cas9_generic = repressionDoc.createComponentDefinition("cas9_generic","1.0", ProteinType);
				
		//cas9_gene_generic
		ComponentDefinition cas9_gene_generic = repressionDoc.createComponentDefinition("cas9_gene_generic","1.0", DNAType);

		//gRNA_generic
		ComponentDefinition gRNA_generic = repressionDoc.createComponentDefinition("gRNA_generic","1.0", ProteinType);

		//gRNA_gene_generic
		ComponentDefinition gRNA_gene_generic = repressionDoc.createComponentDefinition("gRNA_gene_generic","1.0", DNAType);

		//cas9_complex_generic
		ComponentDefinition cas9_complex_generic = repressionDoc.createComponentDefinition("cas9_complex_generic","1.0", ProteinType);

		//target_protein_generic
		ComponentDefinition target_protein_generic = repressionDoc.createComponentDefinition("target_protein_generic","1.0", ProteinType);

		//target_gene_generic
		ComponentDefinition target_gene_generic = repressionDoc.createComponentDefinition("target_gene_generic","1.0", DNAType);

		//add ModuleDefinition CRISPR_Repression_Template		
		ModuleDefinition  CRISPR_Repression_Template = repressionDoc.createModuleDefinition("CRISPR_Repression_Template", "1.0");
		
		//add Functional Components 
		FunctionalComponent cas9_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("cas9_generic_FC", AccessType.PUBLIC, cas9_generic.getIdentity(), DirectionType.NONE);
		FunctionalComponent cas9_gene_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("cas9_gene_generic_FC", AccessType.PUBLIC, cas9_gene_generic.getIdentity(), DirectionType.NONE);
		FunctionalComponent gRNA_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("gRNA_generic_FC", AccessType.PUBLIC, gRNA_generic.getIdentity(), DirectionType.NONE);
		FunctionalComponent gRNA_gene_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("gRNA_gene_generic_FC", AccessType.PUBLIC, gRNA_gene_generic.getIdentity(), DirectionType.NONE);
		FunctionalComponent cas9_complex_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("cas9_complex_generic_FC", AccessType.PUBLIC, cas9_complex_generic.getIdentity(), DirectionType.NONE);
		FunctionalComponent target_protein_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("target_protein_generic_FC", AccessType.PUBLIC, target_protein_generic.getIdentity(), DirectionType.OUT);
		FunctionalComponent target_gene_generic_FC = CRISPR_Repression_Template.createFunctionalComponent("target_gene_generic_FC", AccessType.PUBLIC, target_gene_generic.getIdentity(), DirectionType.NONE);
		
		
		ModuleDefinition CRISPR_Repression = repressionDoc.createModuleDefinition("CRISPR_Repression", "1.0");
		
		//create the functionalComponents for the module definition CRISPR_Repression
		FunctionalComponent cas9m_unspecified_protein_FC =  CRISPR_Repression.createFunctionalComponent("cas9m_unspecified_protein_FC", AccessType.PUBLIC,cas9m_unspecified_protein.getIdentity(), DirectionType.NONE);
		FunctionalComponent cas9m_BFP_components_FC = CRISPR_Repression.createFunctionalComponent("cas9m_BFP_components_FC", AccessType.PUBLIC,cas9m_BFP_components.getIdentity(), DirectionType.NONE);
		
		FunctionalComponent gRNA_b_mRNA_FC = CRISPR_Repression.createFunctionalComponent("gRNA_b_mRNA_FC", AccessType.PUBLIC,gRNA_b_mRNA.getIdentity(), DirectionType.NONE);
		FunctionalComponent gRNA_components_FC = CRISPR_Repression.createFunctionalComponent("gRNA_components_FC", AccessType.PUBLIC,gRNA_components.getIdentity(), DirectionType.NONE);

		FunctionalComponent mKate_FC = CRISPR_Repression.createFunctionalComponent("mKate_FC", AccessType.PUBLIC, mKate.getIdentity(), DirectionType.NONE);
		FunctionalComponent mKate_components_FC = CRISPR_Repression.createFunctionalComponent("mKate_components_FC", AccessType.PUBLIC,mKate_components.getIdentity(), DirectionType.NONE);

		FunctionalComponent Gal4VP16_FC = CRISPR_Repression.createFunctionalComponent("Gal4VP16_FC", AccessType.PUBLIC,Gal4VP16.getIdentity(), DirectionType.NONE);
		FunctionalComponent Gal4VP16_components_FC = CRISPR_Repression.createFunctionalComponent("Gal4VP16_components_FC", AccessType.PUBLIC,Gal4VP16_components.getIdentity(), DirectionType.NONE);
		FunctionalComponent EYFP_FC = CRISPR_Repression.createFunctionalComponent("EYFP_FC", AccessType.PUBLIC, EYFP.getIdentity(), DirectionType.NONE);
		FunctionalComponent EYFP_components_FC = CRISPR_Repression.createFunctionalComponent("EYFP_components_FC", AccessType.PUBLIC,EYFP_components.getIdentity(), DirectionType.NONE);

		FunctionalComponent cas9m_BFP_gRNA_b_components_FC = CRISPR_Repression.createFunctionalComponent("cas9m_BFP_gRNA_b_components_FC", AccessType.PUBLIC,"cas9m_BFP_gRNA_b_components", "1.0", DirectionType.NONE);

		
		//create template module
		Module CRISPR_Repression_Template_Module = CRISPR_Repression.createModule("CRISPR_Repression_Template_Module", "CRISPR_Repression_Template", "1.0");
		
		//add MapsTo of Module Template in CRISPR_MODULEDefinition
		CRISPR_Repression_Template_Module.createMapsTo("crispr_cas9_mapping", RefinementType.USELOCAL, cas9m_unspecified_protein_FC.getIdentity(), cas9_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("crispr_cas9_gene_mapping", RefinementType.USELOCAL, cas9m_BFP_components_FC.getIdentity(), cas9_gene_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("gRNA_b_mapping", RefinementType.USELOCAL, gRNA_b_mRNA_FC.getIdentity(), gRNA_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("gRNA_b_gene_mapping", RefinementType.USELOCAL, gRNA_components_FC.getIdentity(), gRNA_gene_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("cas9_complex_mapping", RefinementType.USELOCAL, cas9m_BFP_gRNA_b_components_FC.getIdentity(), cas9_complex_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("EYFP_mapping", RefinementType.USELOCAL, EYFP_FC.getIdentity(), target_protein_generic_FC.getIdentity());
		CRISPR_Repression_Template_Module.createMapsTo("EYFP_gene_mapping", RefinementType.USELOCAL, EYFP_components_FC.getIdentity(), target_gene_generic_FC.getIdentity());


        /*add interactions*/
		HashSet<URI> non_covalent_type = new HashSet<URI>();
		non_covalent_type.add(SystemsBiologyOntology.NON_COVALENT_BINDING);
		
		HashSet<URI> enhancement = new HashSet<URI>();
		enhancement.add(SystemsBiologyOntology.GENETIC_ENHANCEMENT);
		
		HashSet<URI> production = new HashSet<URI>();
		production.add(SystemsBiologyOntology.GENETIC_PRODUCTION);
		
		HashSet<URI> suppression = new HashSet<URI>();
		suppression.add(SystemsBiologyOntology.GENETIC_SUPPRESSION);
		
		Interaction Cas9Complex_Formation = CRISPR_Repression_Template.createInteraction("Cas9Complex_Formation", non_covalent_type);
		
		//get CAS9Complex_Formation interaction and add participants
		Cas9Complex_Formation.createParticipation("cas9m_participant", cas9_generic_FC.getIdentity()).addRole(new URI("SBO:0000011"));
		Cas9Complex_Formation.createParticipation("gRNA_generic_participant", gRNA_generic_FC.getIdentity()).addRole(new URI("SBO:0000011"));
		Cas9Complex_Formation.createParticipation("cas9_complex_generic_participant", cas9_complex_generic_FC.getIdentity()).addRole(new URI("SBO:0000010"));
		
	
		Interaction cas9m_production = CRISPR_Repression_Template.createInteraction("cas9m_production", production);
		
		cas9m_production.createParticipation("cas9m_participant", cas9_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PRODUCT);
		cas9m_production.createParticipation("cas9m_gene_participant", cas9_gene_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);

		
		Interaction gRNA_production = CRISPR_Repression_Template.createInteraction("gRNA_production", production);
		
		gRNA_production.createParticipation("gRNA_generic_participant", gRNA_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PRODUCT);
		gRNA_production.createParticipation("gRNA_gene_generic_participant", gRNA_gene_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);

		Interaction EYFP_production = CRISPR_Repression_Template.createInteraction("EYFP_production", production);
		
		EYFP_production.createParticipation("EYFP_generic_production", target_protein_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PRODUCT);
		EYFP_production.createParticipation("EYFP_gene_generic_production", target_gene_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);
	
		Interaction target_generic_gene_inhibition = CRISPR_Repression_Template.createInteraction("target_generic_gene_inhibition", suppression);

		target_generic_gene_inhibition.createParticipation("cas9m_BFP_gRNA_b_participant", cas9_complex_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.INHIBITOR);
		target_generic_gene_inhibition.createParticipation("EYFP_generic_production", target_protein_generic_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);

		/*add the interactions for the crispr_Repression*/
		Interaction mKate_production = CRISPR_Repression.createInteraction("mKate_production", production);
		
		mKate_production.createParticipation("mKate_participant", mKate_FC.getIdentity()).addRole(SystemsBiologyOntology.PRODUCT);
		mKate_production.createParticipation("mKate_gene_participant", mKate_components_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);

		
		Interaction GAL4VP16_production = CRISPR_Repression.createInteraction("GAL4VP16_production", production);
		
		GAL4VP16_production.createParticipation("GAL4VP16_participant", Gal4VP16_FC.getIdentity()).addRole(SystemsBiologyOntology.PRODUCT);
		GAL4VP16_production.createParticipation("GAL4VP16_gene_participant", Gal4VP16_components_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);
		
		Interaction EYFP_Activation = CRISPR_Repression.createInteraction("EYFP_Activation", enhancement);
		
		EYFP_Activation.createParticipation("GAL4VP16_activation_participant", Gal4VP16_FC.getIdentity()).addRole(SystemsBiologyOntology.STIMULATOR);
		EYFP_Activation.createParticipation("GAL4VP16_gene_activation_participant", EYFP_components_FC.getIdentity()).addRole(SystemsBiologyOntology.PROMOTER);

		
		try{
			SBOLWriter.write(repressionDoc, "crispr.rdf");
		}
		catch(Exception e){}
				
	}

}
