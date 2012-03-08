/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core.examples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.DnaSequence;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.StrandType;
import org.sbolstandard.core.util.SBOLPrettyWriter;
import org.sbolstandard.core.util.SequenceOntology;

/**
 * This example shows how to create SBOL core data model for BioBrick(TM) BBa_I0462. This example is based on the
 * example from Section 9.1 of the SBOL specification. The code shows how to create instances of various SBOL classes,
 * how to use predefined SequenceOntology types, and how to create new SequenceOntology types if they are not
 * predefined. The example also shows serializing the data model as an XML file and reading XML files as SBOL objects.
 * 
 * @author Evren Sirin
 */
public class Example02_Detailed {
	public static void main(String[] args) throws Exception {
		String file = "BBa_I0462.xml";
		
		// create a document populated with some SBOL objects
		SBOLDocument document = createDocument();
		
		System.out.println("Created a new SBOL document with " + document.getContents().size() + " element(s)");

		// write the contents of the document as an XML file
		SBOLFactory.write(document, new FileOutputStream(file));
		
		System.out.println("Written the contents of the SBOL document to " + file);

		// read back the contents of the file
		SBOLDocument newDocument = SBOLFactory.read(new FileInputStream(file));
		
		// print the contents of the SBOL document in a more readable format
		System.out.println("Read back the contents of the SBOL document from " + file);

		new SBOLPrettyWriter().write(newDocument, System.out);
	}

	/**
	 * Creates a document with a single DnaComponent.
	 */
	public static SBOLDocument createDocument() {
		SBOLDocument document = SBOLFactory.createDocument();
		document.addContent(createDnaComponent());
		return document;
	}

	/**
	 * Creates a DnaComponent with a DnaSequence and three SequenceAnnotations.
	 */
	public static DnaComponent createDnaComponent() {
		DnaComponent dnaComponent = SBOLFactory.createDnaComponent();
		dnaComponent.setURI(URI.create("http://partsregistry.org/Part:BBa_I0462"));
		dnaComponent.setDisplayId("BBa_I0462");
		dnaComponent.setName("I0462");
		dnaComponent.setDescription("LuxR protein generator");
		dnaComponent.setDnaSequence(createDnaSequence());
		dnaComponent.addAnnotation(createAnnotation1());
		dnaComponent.addAnnotation(createAnnotation2());
		dnaComponent.addAnnotation(createAnnotation3());
		return dnaComponent;
	}

	/**
	 * Creates a DnaSequence instance.
	 */
	public static DnaSequence createDnaSequence() {
		DnaSequence dnaSequence = SBOLFactory.createDnaSequence();
		dnaSequence.setURI(URI.create("http://sbols.org/seq#d23749adb3a7e0e2f09168cb7267a6113b238973"));
		dnaSequence.setNucleotides("aaagaggagaaatactagatgaaaaacataaatgccgacgacacatacagaataattaataaaattaaagcttgtagaagcaataatga"
		                           + "tattaatcaatgcttatctgatatgactaaaatggtacattgtgaatattatttactcgcgatcatttatcctcattctatggttaaat"
		                           + "ctgatatttcaatcctagataattaccctaaaaaatggaggcaatattatgatgacgctaatttaataaaatatgatcctatagtagat"
		                           + "tattctaactccaatcattcaccaattaattggaatatatttgaaaacaatgctgtaaataaaaaatctccaaatgtaattaaagaagc"
		                           + "gaaaacatcaggtcttatcactgggtttagtttccctattcatacggctaacaatggcttcggaatgcttagttttgcacattcagaaa"
		                           + "aagacaactatatagatagtttatttttacatgcgtgtatgaacataccattaattgttccttctctagttgataattatcgaaaaata"
		                           + "aatatagcaaataataaatcaaacaacgatttaaccaaaagagaaaaagaatgtttagcgtgggcatgcgaaggaaaaagctcttggga"
		                           + "tatttcaaaaatattaggttgcagtgagcgtactgtcactttccatttaaccaatgcgcaaatgaaactcaatacaacaaaccgctgcc"
		                           + "aaagtatttctaaagcaattttaacaggagcaattgattgcccatactttaaaaattaataacactgatagtgctagtgtagatcacta"
		                           + "ctagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctac"
		                           + "tagagtcacactggctcaccttcgggtgggcctttctgcgtttata");

		return dnaSequence;
	}

	/**
	 * Creates the first SequenceAnnotation (ribosome entry site).
	 */
	public static SequenceAnnotation createAnnotation1() {
		SequenceAnnotation annotation1 = SBOLFactory.createSequenceAnnotation();
		annotation1.setURI(URI.create("http://sbols.org/anot#1234567"));
		annotation1.setBioStart(1);
		annotation1.setBioEnd(12);
		annotation1.setStrand(StrandType.POSITIVE);

		DnaComponent dnaComponent1 = SBOLFactory.createDnaComponent();
		dnaComponent1.setURI(URI.create("http://partsregistry.org/Part:BBa_B0034"));
		dnaComponent1.setDisplayId("BBa_B0034");
		dnaComponent1.setName("B0034");
		// there is no predefined constant for ribosome entry site so we will create the
		dnaComponent1.addType(SequenceOntology.type("SO_0000139"));

		annotation1.setSubComponent(dnaComponent1);

		return annotation1;
	}

	/**
	 * Creates the second SequenceAnnotation (CDS).
	 */
	public static SequenceAnnotation createAnnotation2() {
		SequenceAnnotation annotation2 = SBOLFactory.createSequenceAnnotation();
		annotation2.setURI(URI.create("http://sbols.org/anot#2345678"));
		annotation2.setBioStart(19);
		annotation2.setBioEnd(774);
		annotation2.setStrand(StrandType.POSITIVE);

		DnaComponent dnaComponent2 = SBOLFactory.createDnaComponent();
		dnaComponent2.setURI(URI.create("http://partsregistry.org/Part:BBa_C0062"));
		dnaComponent2.setDisplayId("BBa_C0062");
		dnaComponent2.setName("luxR");
		// use the predefined SequenceOntology constant for CDS
		dnaComponent2.addType(SequenceOntology.CDS);

		annotation2.setSubComponent(dnaComponent2);

		return annotation2;
	}

	/**
	 * Creates the third SequenceAnnotation (terminator).
	 */
	public static SequenceAnnotation createAnnotation3() {
		SequenceAnnotation annotation3 = SBOLFactory.createSequenceAnnotation();
		annotation3.setURI(URI.create("http://sbols.org/anot#3456789"));
		annotation3.setBioStart(808);
		annotation3.setBioEnd(936);
		annotation3.setStrand(StrandType.POSITIVE);

		DnaComponent dnaComponent3 = SBOLFactory.createDnaComponent();
		dnaComponent3.setURI(URI.create("http://partsregistry.org/Part:BBa_B0015"));
		dnaComponent3.setDisplayId("BBa_B0015");
		dnaComponent3.setName("B0015");
		// use the predefined SequenceOntology constant for terminator
		dnaComponent3.addType(SequenceOntology.TERMINATOR);

		annotation3.setSubComponent(dnaComponent3);

		return annotation3;
	}
}
