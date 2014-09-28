package org.sbolstandard.core2;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;

import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class mainTester {

	private static final NamespaceBinding sbolExample = 
			new NamespaceBinding("http://sbolstandard.org/example#", "example");
	
	public static void main( String[] args ) throws Exception
	  {
		//fill up data
		SBOLDocument doc = new SBOLDocument(); 
				doc.createModule(sbolExample.namespacedUri("module_LacI_inverter/LacI_instantiation"), "IPTG");
		
		
		  DocumentRoot<QName> originalDocument = null; 
		  write(new OutputStreamWriter(System.out), originalDocument);
		  StringWriter stringWriter=new StringWriter();
		  write (stringWriter, originalDocument);
		  
		  System.out.println("------------------------------------------------------");
		  
		  String output=stringWriter.getBuffer().toString();
		  stringWriter.close();
		  
		  System.out.print(output);
	  }
	  
	  private static void write(Writer stream, DocumentRoot<QName> document) throws Exception
		{
			XMLStreamWriter xmlWriter = new IndentingXMLStreamWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(stream));
			RdfIo rdfIo = new RdfIo();
			// rdfIo.createIoWriter(xmlWriter).write(makeDocument());
			rdfIo.createIoWriter(xmlWriter).write(document);
			xmlWriter.flush();
			xmlWriter.close();
		}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
