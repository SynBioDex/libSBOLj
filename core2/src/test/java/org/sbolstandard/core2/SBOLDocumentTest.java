package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.xml.namespace.QName;

import static org.junit.Assert.*;
import org.junit.Test;

public class SBOLDocumentTest {

	@Test
	public void Test_Sequence_CopyCreate() {
		
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

	@Test
	public void Test_Model_CopyCreate(){
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document1 = new SBOLDocument();
		document1.setDefaultURIprefix(prURI);
		document1.setTypesInURIs(true);
		
		String prURI2="http://partsregistry2.org";
		String prPrefix2="pr2";
		SBOLDocument document2 = new SBOLDocument();
		document2.setDefaultURIprefix(prURI2);
		document2.setTypesInURIs(true);
		
		
		String model1ID = "ID";
		String model1Version = "1.0";
		String S_model1URI = "http://partsregistry.org/ID";
		String L_model1URI = "http://partsregistry.org/Source";
		String F_model1URI = "http://partsregistry.org/Framework";
		
		URI model1URI_S = URI.create(S_model1URI);
		URI model1URI_L = URI.create(L_model1URI);
		URI model1URI_F = URI.create(F_model1URI);
		
		Model mod1 = document1.createModel(model1ID, model1Version, model1URI_S, model1URI_L, model1URI_F);
		Model mod2 = (Model)document2.createCopy(mod1);
		
		mod2.unsetWasDerivedFrom();
		
		assertTrue(mod1.equals(mod2));		
	}
	
	@Test
	public void Test_GenericTopLevel_CopyCreate(){
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document1 = new SBOLDocument();
		document1.setDefaultURIprefix(prURI);
		document1.setTypesInURIs(true);
		
		String prURI2="http://partsregistry2.org";
		String prPrefix2="pr2";
		SBOLDocument document2 = new SBOLDocument();
		document2.setDefaultURIprefix(prURI2);
		document2.setTypesInURIs(true);
		
		String GTL1ID = "ID";
		String GTL1Version = "1.0";
		String GTL1Qname = "name";
		GenericTopLevel GTL1 = document1.createGenericTopLevel(GTL1ID, GTL1Version, QName.valueOf(GTL1Qname));
		GenericTopLevel GTL2 = (GenericTopLevel)document2.createCopy(GTL1);
		
		GTL2.unsetWasDerivedFrom();
		
		assertTrue(GTL1.equals(GTL2));
	}
	
	@Test
	public void Test_Collection_CopyCreate(){
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document1 = new SBOLDocument();
		document1.setDefaultURIprefix(prURI);
		document1.setTypesInURIs(true);
		
		String prURI2="http://partsregistry2.org";
		String prPrefix2="pr2";
		SBOLDocument document2 = new SBOLDocument();
		document2.setDefaultURIprefix(prURI2);
		document2.setTypesInURIs(true);
		
		String Col1ID = "ID";
		String Col1Version = "1.0";
		Collection Col1 = document1.createCollection(Col1ID, Col1Version);
		Collection Col2 = (Collection)document2.createCopy(Col1);
		
		Col2.unsetWasDerivedFrom();
		
		assertTrue(Col1.equals(Col2));
	}
	
	@Test
	public void Test_ComponentDefinition_CopyCreate(){
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document1 = new SBOLDocument();
		document1.setDefaultURIprefix(prURI);
		document1.setTypesInURIs(true);
		
		String prURI2="http://partsregistry2.org";
		String prPrefix2="pr2";
		SBOLDocument document2 = new SBOLDocument();
		document2.setDefaultURIprefix(prURI2);
		document2.setTypesInURIs(true);
		
		String CD1_ID = "ID";
		Set<URI> types = new HashSet<URI>();
		types.add(URI.create("www.example.com"));
		ComponentDefinition CD1 = document1.createComponentDefinition(CD1_ID, types);
		ComponentDefinition CD2 = (ComponentDefinition)document2.createCopy(CD1);

		CD2.unsetWasDerivedFrom();
		
		assertTrue(CD1.equals(CD2));
	}
}



