package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.Reader;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {
	
//	public static SBOLDocument read(String fileName) {
//		
//	}
	//read in rdf input turn into document root then call each properties in it. 
	//input file, filename, stream
	//return sboldocument
	public static void read( DocumentRoot<QName> document) {
		for(TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			
		}
		for(int index = 0; index < document.getNamespaceBindings().size(); index++)	
		{
			String SbolTerms; 
			switch (document.getNamespaceBindings().get(index).getNamespaceURI()) {
				case "Component": ; 
					break; 
			
			}
		}
	}
	
	private static DocumentRoot<QName> readRdf(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();		
	}
	
}
