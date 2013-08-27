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
import java.net.URI;
import java.util.List;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SequenceAnnotation;

/**
 * This example shows how the validator can be used to find errors in an SBOL data model.
 * 
 * @author Evren Sirin
 */
public class Example03_Validation {
	public static void main(String[] args) throws Exception {
		String file = "examples/data/BBa_I0462.xml";

		// read the BBa_I0462 example from file
		SBOLDocument document = SBOLFactory.read(new FileInputStream(file));

		// we know this example contains a single component so let's get it
		DnaComponent dnaComponent = (DnaComponent) document.getContents().iterator().next();

		// get the first two sequence annotations
		List<SequenceAnnotation> annotations = dnaComponent.getAnnotations();
		SequenceAnnotation annotation1 = annotations.get(0);
		SequenceAnnotation annotation2 = annotations.get(1);

		// add an invalid precedes relation (contradicts the bioStart/bioEnd values)
		System.out.println("Adding an invalid precedes relation");
		annotation2.addPrecede(annotation1);

		try {
			System.out.println("Validating the document");	
			// validate the contents of the file
			SBOLFactory.validate(document);
			System.out.println("This message will never be printed since validation throws an exception");
		}
		catch (SBOLValidationException e) {
			System.out.println("Validation failed: " + e.getMessage());
		}
		
		// now fix the error
		System.out.println("Removing the invalid precedes relation");
		annotation2.removePrecede(annotation1);
		
		// introduce a new error by adding a new dna component to the document missing the required field displayId
		System.out.println("Adding a new DNA component that does not have the required field displayId");
		DnaComponent newComponent = SBOLFactory.createDnaComponent();
		newComponent.setURI(URI.create("http://example.com/annotation4"));
		document.addContent(newComponent);
		
		try {
			System.out.println("Trying to write the document contents");
			// try to serialize the file which will also trigger a validation exception
			SBOLFactory.write(document, System.out);
			System.out.println("This message will never be printed since validation throws an exception");
		}
		catch (SBOLValidationException e) {
			System.out.println("Writing failed due to validation errors: " + e.getMessage());
		}
		
		// fix the error by adding the displayId
		System.out.println("Adding the missing displayId value");
		newComponent.setDisplayId("newComponent");

		System.out.println("Validating the document");	
		SBOLFactory.validate(document);
		System.out.println("Validation successful");		
	}
}
