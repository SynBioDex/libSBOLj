package org.sbolstandard.core2.examples;

import java.io.IOException;
import java.net.URI;

import org.sbolstandard.core2.Collection;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.TopLevel;

public class SBOLWorkshop2018 {
	
public static int len(SBOLDocument sbolDocument) {
	// TODO 
	int length = 0;
	length += sbolDocument.getCollections().size();
	length += sbolDocument.getComponentDefinitions().size();
	length += sbolDocument.getSequences().size();
	
	return length;
}

public static void printCounts(SBOLDocument sbolDocument) {
	// TODO
	System.out.println("Collection...................." + sbolDocument.getCollections().size());
	System.out.println("ComponentDefinition..........." + sbolDocument.getComponentDefinitions().size());
	System.out.println("Sequence......................" + sbolDocument.getSequences().size());
	System.out.println("---");
	System.out.println("Total........................." + len(sbolDocument));
}
	
public static void main(String[] args) throws SBOLValidationException, IOException, SBOLConversionException {	
	String my_namespace = "http://my_namespace.org/";
	String version = "1";
	
	SBOLDocument doc = new SBOLDocument();
	doc.setDefaultURIprefix(my_namespace);
	ComponentDefinition my_device = doc.createComponentDefinition("my_device", version, ComponentDefinition.DNA);
	System.out.println(my_device.getIdentity());
	System.out.println("");
	
	SBOLDocument cello_parts = SBOLReader.read("/Users/myers/Downloads/parts.xml");
	System.out.println(len(cello_parts));
	printCounts(cello_parts);
	System.out.println("");
	
	for (TopLevel topLevel : cello_parts.getTopLevels()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	doc.createCopy(cello_parts);
	for (TopLevel topLevel : doc.getTopLevels()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	Collection promoter_collection = doc.getCollection(URI.create("http://examples.org/Collection/promoters/1"));
	for (TopLevel topLevel : promoter_collection.getMembers()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	ComponentDefinition promoter = doc.getComponentDefinition(URI.create("http://examples.org/ComponentDefinition/pPhlF/1"));
	System.out.println(promoter.getTypes());
	System.out.println(promoter.getRoles());
	System.out.println("");
	
}	

}
