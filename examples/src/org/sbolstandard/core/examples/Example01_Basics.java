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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.util.SBOLPrettyWriter;

/**
 * This example shows the basics of creating SBOL objects, serializing the contents of an SBOL document as XML and
 * reading back the contents.
 * 
 * @author Evren Sirin
 */
public class Example01_Basics {
	public static void main(String[] args) throws Exception {		
		System.out.format("Create a very simple SBOL document%n%n");
		// create a DnaComponent and set some of its properties 
		DnaComponent dnaComponent = SBOLFactory.createDnaComponent();
		dnaComponent.setURI(URI.create("http://example.com/MyDnaComponent"));
		dnaComponent.setDisplayId("MyDnaComponent");
		dnaComponent.setName("myDNA");
		dnaComponent.setDescription("This is a very simple example");
		
		// create an empty document populated with some SBOL objects
		SBOLDocument document = SBOLFactory.createDocument();
		// add the DnaComponent to this document
		document.addContent(dnaComponent);
		
		// write the contents of the document as an XML file to stdout
		System.out.format("Serialize the SBOL document in the official XML format:%n");
		SBOLFactory.write(document, System.out);
		
		// now serialize the contents into a buffer so we can read it back
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		SBOLFactory.write(document, buffer);
		// create a new document using the byte buffer as our input
		SBOLDocument newDocument = SBOLFactory.read(new ByteArrayInputStream(buffer.toByteArray()));
		
		// write the contents of the new document in amore human-readable format
		System.out.format("%nSerialize the SBOL document in a more readable presentation format:%n");
		new SBOLPrettyWriter().write(newDocument, System.out);		
	}
}
