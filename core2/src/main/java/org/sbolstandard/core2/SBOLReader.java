package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.Reader;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {

	public static void read( DocumentRoot<QName> document) {
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
