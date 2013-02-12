package org.sbolstandard.core.examples;

import java.io.IOException;

import org.sbolstandard.core.PrimitiveDevice;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLValidationException;

public class Example04_Device {

	private static final int N = 100;
	
	public static void main(String[] args) {

		// 1. create a primitive device and add some dna componentes to it...
		PrimitiveDevice pd = SBOLFactory.createPrimitiveDevice();
		for(int i=0; i<N; i++) {
			pd.addComponent(SBOLFactory.createDnaComponent());
		}
		
		
		
		// 2. serialize the primitive device
		SBOLDocument doc = SBOLFactory.createDocument();		
		doc.addContent(pd);
		
		
		
		// write the contents of the document as an XML file to stdout
		System.out.format("Serialize the SBOL document in the official XML format:%n");
		try {
			SBOLFactory.write(doc, System.out);
		} catch (SBOLValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
