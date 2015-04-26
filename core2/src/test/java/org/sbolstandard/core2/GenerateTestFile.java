package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

public class GenerateTestFile
{

	public static void main(String[] args)
	{

		SBOLDocument document = new SBOLDocument();
		document.setDefaultURIprefix("http://www.async.ece.utah.edu");
		document.addNamespaceBinding(NamespaceBinding("http://myannotation.org", "annot"));
		document.addNamespaceBinding(NamespaceBinding("urn:bbn.com:tasbe:grn", "grn"));

		List<Annotation> moduleDefinition_annotations = new ArrayList<Annotation>();
		moduleDefinition_annotations.add(new Annotation(NamedProperty(new QName("http://myannotation.org", "thisAnnotation", "annot"),
				"TurtleString")));
		SBOLTestUtils.createModuleDefinition(document, "someModDef", null, null, null, null, null, moduleDefinition_annotations);


		writeRdfFile(document, "moduleAnnotation.rdf");

	}

	public static void writeRdfFile(SBOLDocument document, String fileName)
	{
		try {
			SBOLWriter.writeRDF(document, new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
