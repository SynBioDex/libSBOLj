package org.sbolstandard.core2;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ComponentDefinitionTests {

	private SBOLDocument doc = null;
	private ComponentDefinition cas9_generic = null;
	private ComponentDefinition cas9m_BFP_gene = null;
	private ModuleDefinition CRISPR_Template = null;
	private Interaction Cas9Complex_Formation = null;
	private Participation cas9_generic_part = null;
	private String CRa_U6_seq = "GGTTTACCGAGCTCTTATTGGTTTTCAAACTTCATTGACTGTGCC" +
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
	private ComponentDefinition TetR_promoter = null;
	private HashSet <URI> types = null; 
	private Sequence s = null;
	private SBOLDocument document1 = null;
	private Sequence s2 = null;
	
	@Before
	public void setUp() throws Exception {
		//actually add stuff to the doc. like cds, mds, seqs, modules, seqAnns, seqConstraints, etc....
				String prURI="http://partsregistry.org";
				//String prPrefix="pr";
				doc = new SBOLDocument();
				doc.setDefaultURIprefix(prURI);
				doc.setTypesInURIs(false);
				types = new HashSet <URI >(Arrays.asList(ComponentDefinition.DNA));
				TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);
				s = new Sequence(Sequence.IUPAC_DNA, "", Sequence.IUPAC_DNA);
				
				//case 2: document is not null
				String preURI="http://doesnotexist.com";
				document1 = new SBOLDocument();
				document1.setDefaultURIprefix(preURI);
				document1.setTypesInURIs(true);
				
			//	document1.addComponentDefinition(TetR_promoter);
				s2 = new Sequence(Sequence.IUPAC_PROTEIN, "", Sequence.IUPAC_DNA);
				TetR_promoter.addSequence(s2);
				document1.addComponentDefinition(TetR_promoter);
		
	}
	
	@Test
	public void test_CD_addSequence() throws SBOLValidationException {
		
		doc.createComponentDefinition("CRa_U6","",ComponentDefinition.DNA);
		try
		{
			doc.getComponentDefinition("CRa_U6", "").addSequence(CRa_U6_seq);
				
			int size = doc.getSequence("CRa_U6", "").toString().length();
			assertTrue(size != 0);
		}
		catch(Exception e){}
	} 
	
	@Test
	public void test_CD_createComp() throws SBOLValidationException
	{

		ComponentDefinition gRNA_b_gene = doc.createComponentDefinition("gRNA_b_gene", "", ComponentDefinition.DNA);
		Component gRNA_promoter = gRNA_b_gene.createComponent("gRNA_gene_promoter", AccessType.PUBLIC, "gRNA_b_gene_promoter");
		assertNotNull(doc.getComponentDefinition("gRNA_b_gene", "").getComponent("gRNA_gene_promoter"));
		doc.getComponentDefinition("gRNA_b_gene", "").removeComponent(gRNA_promoter);
		assertNull(doc.getComponentDefinition("gRNA_b_gene", "").getComponent("gRNA_gene_promoter"));
	} 
	
	/* The following are a series of tests pertaining to ComponentDefinition class */
	
	@Test
	public void addType_CD() throws URISyntaxException, SBOLValidationException
	{

		TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);

		try{TetR_promoter.addType(ComponentDefinition.DNA);fail();} 
		catch(SBOLValidationException e){}
		
		types = new HashSet <URI >(Arrays.asList(ComponentDefinition.RNA));
		TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);

		try{TetR_promoter.addType(ComponentDefinition.RNA);fail();}
		catch(SBOLValidationException e){}
		
		types = new HashSet <URI >(Arrays.asList(ComponentDefinition.PROTEIN));
		TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);

		try{TetR_promoter.addType(ComponentDefinition.PROTEIN);fail();}
		catch(SBOLValidationException e){}
		
		types = new HashSet <URI >(Arrays.asList(ComponentDefinition.SMALL_MOLECULE));
		TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);

		try{TetR_promoter.addType(ComponentDefinition.SMALL_MOLECULE); fail();}
		catch(SBOLValidationException e){}
		
		types = new HashSet <URI >(Arrays.asList(ComponentDefinition.SMALL_MOLECULE));
		TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);

		try{assertTrue(TetR_promoter.addType(ComponentDefinition.DNA));}
		catch(SBOLValidationException e){}
		
		//else a SBOLDocument can't be null --> this is checked further up the hierarchy
	}
	
		@Test
		public void removeType_CD() throws URISyntaxException, SBOLValidationException
		{
			TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);
			try{TetR_promoter.removeType(ComponentDefinition.DNA);fail();}
			catch(SBOLValidationException e){}
			TetR_promoter.addType( URI.create("http://identifiers.org/chebi/CHEBI:4705"));
			assertTrue(TetR_promoter.removeType(ComponentDefinition.DNA));
		}
		
		@Test
		public void removeRole_CD() throws URISyntaxException, SBOLValidationException
		{
			TetR_promoter = new ComponentDefinition(new URI("http://partsregistry.org"), types);
			URI promoter_role = new URI("http://identifiers.org/so/SO:0000167");
			assertTrue(TetR_promoter.addRole(promoter_role));
			assertTrue(TetR_promoter.removeRole(promoter_role));
			
		}
		
		@Test
		public void containsRole_CD() throws URISyntaxException, SBOLValidationException
		{
			URI promoter_role = new URI("http://identifiers.org/so/SO:0000167");
			assertTrue(TetR_promoter.addRole(promoter_role));
			assertTrue(TetR_promoter.containsRole(promoter_role));
		}
		
		@Test 
		public void addSeq_CD() throws URISyntaxException, SBOLValidationException 
		{			
			doc.addComponentDefinition(TetR_promoter);
			try{assertTrue(TetR_promoter.addSequence(s));}
			catch(SBOLValidationException e){}
			
			try{TetR_promoter.addSequence(new Sequence(null, null, null));fail();	}
			catch(SBOLValidationException e){}
			ComponentDefinition TetR_promoter2 = new ComponentDefinition(new URI("http://partsregistry.org"), types);
			try{assertTrue(TetR_promoter2.addSequence(s));}
			catch(SBOLValidationException e){}
			
		} 
		
		@Test
		public void removeSeq_CD() throws URISyntaxException, SBOLValidationException
		{		
			TetR_promoter.addSequence(s);
			//case 1: document1 is null
			assertTrue(TetR_promoter.containsSequence(Sequence.IUPAC_DNA));
			assertTrue(TetR_promoter.removeSequence(Sequence.IUPAC_DNA));	
			TetR_promoter.clearSequences();
			//assertTrue(TetR_promoter.containsSequence(Sequence.IUPAC_PROTEIN));
			//assertFalse(TetR_promoter.containsSequence(Sequence.IUPAC_RNA));
			//assertTrue(TetR_promoter.removeSequence(Sequence.IUPAC_PROTEIN));	
			//assertTrue(TetR_promoter.getSequences().size() == 0);
		}
		
		
		

	@After
	public void tearDown() throws Exception {
	}


}
