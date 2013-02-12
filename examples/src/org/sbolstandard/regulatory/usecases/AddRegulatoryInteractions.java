package org.sbolstandard.regulatory.usecases;

import java.io.FileInputStream;
import java.util.Iterator;

import org.sbolstandard.core.Collection;
import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLRootObject;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.regulatory.Regulation;
import org.sbolstandard.regulatory.RegulationType;
import org.sbolstandard.regulatory.RegulationTypes;
import org.sbolstandard.regulatory.RegulatoryFactory;

/*
 * in this example, we read an SBOL document 
 * and add regulatory interactions to the DNA components 
 */
public class AddRegulatoryInteractions {

	public static void main(String[] args)
			throws Exception {
		
		// first, read the SBOL file
		/** doesn't work currently... why?? 
		SBOLDocument t9002doc = SBOLFactory.read(
			new FileInputStream("./examples/data/BBa_T9002.xml"));
		**/
		
		SBOLDocument t9002doc = SBOLFactory.read(
				new FileInputStream("./examples/wetwarestudiodesign.xml"));
		
		// print the components of the document
		Collection objCollection = (Collection)t9002doc.getContents().iterator().next();
		
		Iterator<DnaComponent> it = objCollection.getComponents().iterator();
		DnaComponent comp1 = it.next();
		DnaComponent comp2 = it.next();

		// get the CDS of the first component
		SequenceAnnotation sa_left = (SequenceAnnotation)comp1.getAnnotations().get(2);
		
		// get the Promoter of the second component
		SequenceAnnotation sa_right = (SequenceAnnotation)comp1.getAnnotations().get(0);
		
		// now, define a repressing relationship

		// define regulatory interactions between two sequence annotations
		
		// REGULATION TYPES:
		// Option 1: create your own regulation types
		// 1. inducible 
		RegulationType ind = RegulatoryFactory.createRegulationType();
		// 2. repress
		RegulationType rep = RegulatoryFactory.createRegulationType();
		
		/**
		// Option 2: use the pre-defined regulation types
		RegulationType ind = RegulationTypes.getInducibleRegulation();
		RegulationType rep = RegulationTypes.getRepressingRegulation();
		**/

		Regulation reg01 = RegulatoryFactory.createRegulation();
		reg01.setDescription("repressing regulation");
		reg01.setRegulation(sa_left, rep, sa_right);
		
		// TODO: serialization of the regulatory interactions
	}

}
