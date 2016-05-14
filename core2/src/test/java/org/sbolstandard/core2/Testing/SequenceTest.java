package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.Sequence;

public class SequenceTest {
	private Sequence s2 = null;
	private SBOLDocument doc = null;
	private Sequence generic_seq = null;
	@Before
	public void setUp() throws Exception 
	{
		String prURI="http://partsregistry.org";
		doc = new SBOLDocument();
		doc.setDefaultURIprefix(prURI);
		doc.setTypesInURIs(false);
		generic_seq = doc.createSequence("generic_seq", "ttgacagctagctcagtcctaggtataatgctagc", Sequence.IUPAC_DNA);

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void test_toString()
	{	
		String generic_seq_string = "generic_seq" + '@' + Integer.toHexString(generic_seq.hashCode());
		equals(doc.getSequence("generic_seq", "").toString() == generic_seq_string);
	}
	

}
