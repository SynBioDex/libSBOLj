package org.sbolstandard.core2;

import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import javanet.staxutils.IndentingXMLStreamWriter;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {
	
	public static SBOLDocument read(String fileName) throws Throwable {
		SBOLDocument SBOLDoc = new SBOLDocument();
		 DocumentRoot<QName> document= read(new StringReader(fileName));
		 readRdf(SBOLDoc, document);
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
	private static void readRdf(SBOLDocument SBOLDoc, DocumentRoot<QName> document) { 
		for(TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			if(topLevel.getType() == Sbol2Terms.Sequence.Sequence)
				parseSequence(SBOLDoc, topLevel.getProperties()); 
			
			
		}
	}
	
	private static DocumentRoot<QName> read(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();		
	}

	
	private static void parseSequence(SBOLDocument SBOLDoc, List<NamedProperty<QName>> list)
	{
		URI identity = null; 
		String name = null; 
		String description = null; 
		String elements = null; 
		URI encoding = null; 
		/*
		 * loop through each list then check for it's name (getName() i.e. displayId() ) then retrieve the value for that type. 
		 */
		for(NamedProperty<QName> namedProperty : list)
		{
			if(namedProperty.getName() == Sbol2Terms.Identified.identity)
			{
				identity = URI.create("http://www.async.ece.utah.edu/sequenceIdentityURI"); 
			}
			if(namedProperty.getName() == Sbol2Terms.Documented.name)
			{
				name = namedProperty.getValue().toString(); 
			}
			else if(namedProperty.getName() == Sbol2Terms.Documented.description)
			{
				description = namedProperty.getValue().toString(); 
			}
			else if(namedProperty.getName() == Sbol2Terms.Sequence.elements)
			{
				elements = namedProperty.getValue().toString(); 
			}
			else if(namedProperty.getName() == Sbol2Terms.Sequence.encoding)
			{
//				encoding = namedProperty.getValue(); 
			}
			
		}
		
		SBOLDoc.createSequence(identity, elements, encoding);
		
		
	}
	
}
