package org.sbolstandard.core2;

import java.net.URI;
import static org.junit.Assert.*;
import org.junit.Test;

public class SBOLDocumentTest {

	@Test
	public void test_sample() {
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(true);
//		document.addNamespace(URI.create(prURI), prPrefix);
		
		
		String prURI1="http://partsregistry1.org";
		String prPrefix1="pr1";
		SBOLDocument document2 = new SBOLDocument();
		document.setDefaultURIprefix(prURI1);
		document.setTypesInURIs(true);
//		document.addNamespace(URI.create(prURI1), prPrefix1);
		
		String SequenceDisplayID = "ID";
		String SequenceVersion = "1.0";
		String SequenceElements = "Element";
		String SequenceURI = "http://partsregistry.org/"+SequenceDisplayID;
		String createCopyID = "ID";
		String createCopyVersion = "1.0";
		String createCopyURI = "URI";
		URI SeqURI = URI.create(SequenceURI);
		Sequence seq = document.createSequence(SequenceDisplayID, SequenceVersion, SequenceElements, SeqURI);
//		Sequence seq2 = (Sequence)document2.createCopy(seq, SequenceURI, SequenceDisplayID, SequenceVersion);
		Sequence seq3 = (Sequence)document2.createCopy(seq);
		
		seq3.unsetWasDerivedFrom();
		
		
		assertTrue(seq.equals(seq3));
		assertEquals(seq, seq3);
//		if(seq.equals(seq3)){
//			System.out.println("True");
//		}
//		else{System.out.println("False");}
//		
		
	}

}
