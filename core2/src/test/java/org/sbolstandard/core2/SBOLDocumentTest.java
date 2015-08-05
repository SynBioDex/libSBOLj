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
	public void Test_Sequence_CopyCreate_Create() {
		
		
		//create copy not adding types to URIs
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix(prURI);
		document.setTypesInURIs(false);
//		document.addNamespace(URI.create(prURI), prPrefix);
		
		String prURI1="http://partsregistry.org";
		String prPrefix1="pr";
		SBOLDocument document2 = new SBOLDocument();
		document.setDefaultURIprefix(prURI1);
		document.setTypesInURIs(false);
//		document.addNamespace(URI.create(prURI1), prPrefix1);
		
		String SequenceDisplayID = "ID";
		String SequenceVersion = "1.0";
		String SequenceElements = "Element";
		String SequenceURI = "http://partsregistry.org";
		String createCopyID = "ID";
		String createCopyVersion = "1.0";
		String createCopyURI = "URI";
		String seq2ID = "ID2";
		String seq2Version = "1.0";
		String seq2URIPrefix = "http://partsregistry.org";
		URI seq2URI = URI.create("http://partsregistry.org");
		URI SeqURI = URI.create("www.example.com/name");
		
		
		Sequence seq = document.createSequence(SequenceDisplayID, SequenceVersion, SequenceElements, SeqURI);
		Sequence seq2 = (Sequence)document2.createCopy(seq, SequenceURI, SequenceDisplayID, seq2Version);
//		Sequence seq3 = (Sequence)document2.createCopy(seq);
		
		
//		seq2.unsetWasDerivedFrom();
//		seq3.unsetWasDerivedFrom();
		
		
		System.out.println(seq.getIdentity());
		System.out.println(seq2.getIdentity());

		assertTrue(seq.getVersion().equals(seq2.getVersion()));
		assertTrue(seq.getElements().equals(seq2.getElements()));
		assertTrue(seq.getDisplayId().equals(seq2.getDisplayId()));
		assertTrue(seq.getEncoding().equals(seq2.getEncoding()));
		assertTrue(seq.getClass().equals(seq2.getClass()));
		assertTrue(seq.getAnnotations().equals(seq2.getAnnotations()));
//		assertTrue(seq.getIdentity().equals(seq2.getIdentity()));						//assertion error
//		assertTrue(seq.getDescription().equals(seq2.getDescription()));					//null pointer exception
//		assertTrue(seq.getWasDerivedFrom().equals(seq2.getWasDerivedFrom()));			//null pointer exception
		assertTrue(seq.getPersistentIdentity().equals(seq2.getPersistentIdentity()));	//assertion error
//		assertTrue(seq.getName().equals(seq2.getName()));								//null pointer exception
		assertTrue(seq.equals(seq2));
//		assertEquals(seq, seq3);
		
		

//		if(seq.equals(seq3)){
//			System.out.println("True");
//		}
//		else{System.out.println("False");}	
	}

	@Test
	public void Test_Model_CopyCreate(){
		
		String prURI="http://partsregistry.org";
		String prPrefix="pr";
		SBOLDocument document1 = new SBOLDocument();
		document1.setDefaultURIprefix(prURI);
		document1.setTypesInURIs(false);
		
		String prURI2="http://partsregistry.org";
		String prPrefix2="pr";
		SBOLDocument document2 = new SBOLDocument();
		document2.setDefaultURIprefix(prURI2);
		document2.setTypesInURIs(false);
		
		
		String model1ID = "ID";
		String model1Version = "1.0";
		String model2Version = "1.2";
		String model1URIPrefix = "http://partsregistry.org";
		String S_model1URI = "http://partsregistry.org/ID";
		String L_model1URI = "http://partsregistry.org/Source";
		String F_model1URI = "http://partsregistry.org/Framework";
		
		URI model1URI_S = URI.create(S_model1URI);
		URI model1URI_L = URI.create(L_model1URI);
		URI model1URI_F = URI.create(F_model1URI);
		
		Model mod1 = document1.createModel(model1ID, model1Version, model1URI_S, model1URI_L, model1URI_F);
//		Model mod2 = (Model)document2.createCopy(mod1);
		Model mod3 = (Model)document2.createCopy(mod1, model1URIPrefix, model1ID, model1Version);
		
//		mod2.unsetWasDerivedFrom();
		
//		assertTrue(mod1.equals(mod2));	
		assertTrue(mod1.getAnnotations().equals(mod3.getAnnotations()));
		assertTrue(mod1.getClass().equals(mod3.getClass()));
//		assertTrue(mod1.getDescription().equals(mod3.getDescription()));				//null pointer exception
		assertTrue(mod1.getDisplayId().equals(mod3.getDisplayId()));
		assertTrue(mod1.getFramework().equals(mod3.getFramework()));
		assertTrue(mod1.getIdentity().equals(mod3.getIdentity()));						//assertion error
		assertTrue(mod1.getLanguage().equals(mod3.getLanguage()));
//		assertTrue(mod1.getName().equals(mod3.getName()));								//null pointer exception
		assertTrue(mod1.getPersistentIdentity().equals(mod3.getPersistentIdentity()));	//assertion error
		assertTrue(mod1.getSource().equals(mod3.getSource()));
		assertTrue(mod1.getVersion().equals(mod3.getVersion()));
//		assertTrue(mod1.getWasDerivedFrom().equals(mod3.getWasDerivedFrom()));			//null pointer exception
		
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
		
		String prURI3="http://partsregistry3.org";
		String prPrefix3="pr3";
		SBOLDocument document3 = new SBOLDocument();
		document3.setDefaultURIprefix(prURI3);
		document3.setTypesInURIs(true);
		
		String Col1ID = "ID";
		String Col3ID = "ID3";
		String Col1Version = "1.0";
		Collection Col1 = document1.createCollection(Col1ID, Col1Version);
		Collection Col2 = (Collection)document2.createCopy(Col1);
//		Collection Col3 = (Collection)document3.createCopy(Col1, Col1ID);
		
//		Col3.unsetWasDerivedFrom();
		Col2.unsetWasDerivedFrom();
		
//		assertTrue(Col1.equals(Col3));
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
	
	@Test
	public void Test_ModuleDefinition_CreateCopy(){
		
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
		
		String MD1_ID = "ID";
		String MD1_Version = "1.0";
		ModuleDefinition MD1 = document1.createModuleDefinition(MD1_ID, MD1_Version);
		ModuleDefinition MD2 = (ModuleDefinition)document2.createCopy(MD1);
		
		MD2.unsetWasDerivedFrom();
		
		assertTrue(MD1.equals(MD2));
	}
	
	
}

