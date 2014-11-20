package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.datatree.PropertyValue;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {
	
	public static SBOLDocument read(String fileName) {
		SBOLDocument SBOLDoc = new SBOLDocument(); 
		return SBOLDoc;
	}
	
	//read in rdf input turn into document root then call each properties in it. 
	//input file, filename, stream
	//return sboldocument
	/**
	 * 
	 * @param document
	 * @return SBOLDocument
	 */
	private void read( DocumentRoot<QName> document) { //DocumentRoot<QName> document
		for(TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			if(topLevel.getType() == Sbol2Terms.Sequence.Sequence)
				formatSequences(topLevel.getNamespaceBindings()); 
			
			
		}
		
//		for(int index = 0; index < document.getNamespaceBindings().size(); index++)	
//		{
//			String SbolTerms; 
//			switch (document.getNamespaceBindings().get(index).getNamespaceURI()) {
//				case "Component": ; 
//					break; 
//			
//			}
//		}
	}
	
	private DocumentRoot<QName> readRdf(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();		
	}
	
	private void formatSequences(List<NamespaceBinding> list)
	{
		String displayId;
		String name;
		String description;
	}
	
}
