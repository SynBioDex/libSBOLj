package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonReader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import uk.ac.intbio.core.io.turtle.TurtleIo;
import uk.ac.ncl.intbio.core.datatree.Datatree;
import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.IdentifiableDocument;
import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.PropertyValue;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.IoReader;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

/**
 * @author zhangz
 *
 */
public class SBOLReader
{
	static class SBOLPair
	{
		private URI left;
		private URI right;

		public SBOLPair(URI left, URI right)
		{
			this.left = left;
			this.right = right;
		}

		public URI getLeft() {
			return left;
		}

		public void setLeft(URI left) {
			this.left = left;
		}

		public URI getRight() {
			return right;
		}

		public void setRight(URI right) {
			this.right = right;
		}
	} //end of SBOLPair class

	private static String URIPrefix	= null;
	private static String version = "";
	private static boolean typesInURI = false;

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 *  @param URIprefix
	 */
	public static void setURIPrefix(String URIprefix)
	{
		SBOLReader.URIPrefix = URIprefix;
	}

	/**
	 * Remove the default URI prefix
	 */
	public static void unsetURIPrefix()
	{
		SBOLReader.URIPrefix = null;
	}

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 * @param version
	 */
	public static void setVersion(String version)
	{
		SBOLReader.version = version;
	}

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 * @param typesInURI
	 */
	public static void setTypesInURI(boolean typesInURI)
	{
		SBOLReader.typesInURI = typesInURI;
	}
	
	private static String getSBOLVersion(DocumentRoot<QName> document) 
	{
		boolean foundRDF = false;
		boolean foundDC = false;
		boolean foundProv = false;
		boolean foundSBOL1 = false;
		boolean foundSBOL2 = false;
		for (NamespaceBinding n : document.getNamespaceBindings())
		{
			if (n.getNamespaceURI().equals(Sbol1Terms.rdf.getNamespaceURI())) foundRDF = true;
			if (n.getNamespaceURI().equals(Sbol2Terms.prov.getNamespaceURI())) foundProv = true;
			if (n.getNamespaceURI().equals(Sbol2Terms.dc.getNamespaceURI())) foundDC = true;
			if (n.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI()))	foundSBOL1 = true;
			if (n.getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI()))	foundSBOL2 = true;
		}
		if (!foundSBOL1 && !foundSBOL2) {
			throw new SBOLValidationException("No SBOL namespace found.");
		}
		else if (foundSBOL1 && !foundSBOL2) return "v1";
		else if (foundSBOL2 && !foundSBOL1) {
			if (!foundRDF) {
				throw new SBOLValidationException("No RDF namespace found.");
			}
			if (!foundDC) {
				throw new SBOLValidationException("No dublin core namespace found.");
			}
			if (!foundProv) {
				throw new SBOLValidationException("No provenance namespace found.");
			}		
			return "v2";
		}
		else {
			throw new SBOLValidationException("A SBOL document cannot have SBOL namespaces with different versions.");
		}
	}

	/**
	 * Takes in the given RDF filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readRDF(File)}.
	 *
	 * @param fileName
	 * @return the converted SBOLDocument
	 * @throws Throwable
	 */
	public static SBOLDocument read(String fileName) throws Throwable
	{
		//FileInputStream fis 	 = new FileInputStream(fileName);
		//String inputStreamString = new Scanner(fis, "UTF-8").useDelimiter("\\A").next();
		return readRDF(new File(fileName));
	}

	/**
	 * Takes in the given JSON filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readJSON(File)}
	 *
	 * @param fileName
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readJSON(String fileName) throws Throwable
	{
		return readJSON(new File(fileName));
	}

	/**
	 * Takes in the given RDF filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readRDF(File)}
	 *
	 * @param fileName
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readRDF(String fileName) throws Throwable
	{
		return readRDF(new File(fileName));
	}

	/**
	 * Takes in the given Turtle filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readTurtle(File)}.
	 *
	 * @param fileName
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readTurtle(String fileName) throws Throwable
	{
		return readTurtle(new File(fileName));
	}

	/**
	 * Takes in the given JSON file and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readJSON(InputStream)}.
	 *
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readJSON(File file) throws Throwable
	{
		FileInputStream stream 	   = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);

		return readJSON(buffer);
	}

	/**
	 * Takes in the given RDF file and converts the file to an SBOLDocument.
	 *
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument read(File file) throws Throwable
	{
		FileInputStream stream 	   = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);

		return read(buffer);
	}

	/**
	 * Takes in the given RDF file and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #readRDF(InputStream)}.
	 *
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readRDF(File file) throws Throwable
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return readRDF(buffer);
	}

	/**
	 * Takes in the given Turtle file and converts the file to an SBOLDocument
	 * <p>
	 * This method calls {@link #readTurtle(InputStream)}
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws Throwable
	 */
	public static SBOLDocument readTurtle(File file) throws Throwable
	{
		FileInputStream stream 	   = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);

		return readTurtle(buffer);
	}

	/**
	 * Takes in a given JSON InputStream and converts the file to an SBOLDocument
	 *
	 * @param in
	 * @return the converted SBOLDocument instance
	 * @throws Exception
	 */
	public static SBOLDocument readJSON(InputStream in) throws Exception
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		SBOLDocument SBOLDoc     = new SBOLDocument();
		try
		{
			DocumentRoot<QName> document = readJSON(new StringReader(inputStreamString));

			if (getSBOLVersion(document).equals("v1")) 
			{
				scanner.close();
				readV1(SBOLDoc,document);	
				return SBOLDoc;
			}
			for (NamespaceBinding n : document.getNamespaceBindings())
			{
				SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
			}

			readTopLevelDocs(SBOLDoc, document);

		}
		catch (IOException e)
		{
			scanner.close();
			e.printStackTrace();
		}
		scanner.close();
		try {
			SBOLValidate.validateCompliance(SBOLDoc);
		} catch (SBOLValidationException e) {
			SBOLDoc.setCompliant(false);
		}
		return SBOLDoc;
	}

	/**
	 * Takes in a given RDF InputStream and converts the file to an SBOLDocument.
	 *
	 * @param in
	 * @return the converted SBOLDocument instance
	 */
	public static SBOLDocument read(InputStream in)
	{
		SBOLDocument SBOLDoc     = new SBOLDocument();
		read(SBOLDoc,in);
		return SBOLDoc;
	}

	static void read(SBOLDocument SBOLDoc,InputStream in)
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		try
		{
			DocumentRoot<QName> document = readRDF(new StringReader(inputStreamString));
			if (getSBOLVersion(document).equals("v1")) 
			{
				scanner.close();
				readV1(SBOLDoc,document);	
				return;
			}
			for (NamespaceBinding n : document.getNamespaceBindings())
			{
				SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
			}

			readTopLevelDocs(SBOLDoc, document);

		}
		catch (Exception e)
		{
			scanner.close();
			e.printStackTrace();
		}

		scanner.close();
	}

	/**
	 * Takes in a given RDF InputStream and converts the file to an SBOLDocument.
	 *
	 * @param in
	 * @return the converted SBOLDocument instance
	 * @throws Exception
	 * @throws IOException
	 */
	public static SBOLDocument readRDF(InputStream in) throws Exception
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		SBOLDocument SBOLDoc     = new SBOLDocument();

		try
		{
			DocumentRoot<QName> document = readRDF(new StringReader(inputStreamString));
			
			if (getSBOLVersion(document).equals("v1")) 
			{
				scanner.close();
				readV1(SBOLDoc,document);	
				return SBOLDoc;
			}
			for (NamespaceBinding n : document.getNamespaceBindings())
			{
				SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
			}
			
			readTopLevelDocs(SBOLDoc, document);
		}
		catch (IOException e)
		{
			scanner.close();
			e.printStackTrace();
		}
		scanner.close();
		try {
			SBOLValidate.validateCompliance(SBOLDoc);
		} catch (SBOLValidationException e) {
			SBOLDoc.setCompliant(false);
		}
		return SBOLDoc;
	}

	/**
	 * Takes in a given Turtle InputStream and converts the file to an SBOLDocument
	 *
	 * @param in
	 * @return the converted SBOLDocument instance
	 * @throws Exception
	 */
	public static SBOLDocument readTurtle(InputStream in) throws Exception
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		SBOLDocument SBOLDoc     = new SBOLDocument();

		try
		{
			DocumentRoot<QName> document = readTurtle(new StringReader(inputStreamString));
			if (getSBOLVersion(document).equals("v1")) 
			{
				scanner.close();
				readV1(SBOLDoc,document);	
				return SBOLDoc;
			}
			for (NamespaceBinding n : document.getNamespaceBindings())
			{
				SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
			}
			readTopLevelDocs(SBOLDoc, document);
		}
		catch (IOException e)
		{
			scanner.close();
			e.printStackTrace();
		}
		scanner.close();
		try {
			SBOLValidate.validateCompliance(SBOLDoc);
		} catch (SBOLValidationException e) {
			SBOLDoc.setCompliant(false);
		}
		return SBOLDoc;
	}

	private static SBOLDocument readV1(SBOLDocument SBOLDoc, DocumentRoot<QName> document)
	{
		for (NamespaceBinding n : document.getNamespaceBindings())
		{
			if (n.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI()))
			{
				SBOLDoc.addNamespaceBinding(NamespaceBinding(Sbol2Terms.sbol2.getNamespaceURI(),
						Sbol2Terms.sbol2.getPrefix()));
			}
			else
			{
				SBOLDoc.addNamespaceBinding(
						NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
			}
		}
		SBOLDoc.addNamespaceBinding(NamespaceBinding(Sbol2Terms.prov.getNamespaceURI(),
				Sbol2Terms.prov.getPrefix()));
		readTopLevelDocsV1(SBOLDoc, document);
		try {
			SBOLValidate.validateCompliance(SBOLDoc);
		} catch (SBOLValidationException e) {
			SBOLDoc.setCompliant(false);
		}
		return SBOLDoc;
	}

	private static DocumentRoot<QName> readJSON(Reader stream) throws Exception
	{
		JsonReader reader 		  = Json.createReaderFactory(Collections.<String, Object> emptyMap()).createReader(stream);
		JsonIo jsonIo 	  		  = new JsonIo();
		IoReader<String> ioReader = jsonIo.createIoReader(reader.read());
		DocumentRoot<String> root = ioReader.read();
		return StringifyQName.string2qname.mapDR(root);
	}

	private static DocumentRoot<QName> readRDF(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo 			  = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();
	}

	private static DocumentRoot<QName> readTurtle(Reader reader) throws Exception
	{
		TurtleIo turtleIo = new TurtleIo();
		return turtleIo.createIoReader(reader).read();
	}

	private static void readTopLevelDocsV1(SBOLDocument SBOLDoc, DocumentRoot<QName> document)
	{
		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			if (topLevel.getType().equals(Sbol1Terms.DNAComponent.DNAComponent))
				parseDnaComponentV1(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol1Terms.DNASequence.DNASequence))
				parseDnaSequenceV1(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol1Terms.Collection.Collection))
				parseCollectionV1(SBOLDoc, topLevel);
			else
			{
				parseGenericTopLevel(SBOLDoc, topLevel);
			}
		}
	}

	private static void readTopLevelDocs(SBOLDocument SBOLDoc, DocumentRoot<QName> document)
	{
		Map<URI, NestedDocument<QName>> nested = new HashMap<URI, NestedDocument<QName>>();
		List<TopLevelDocument<QName>> topLevels = new ArrayList<TopLevelDocument<QName>>();
		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments()) {
			if (topLevel.getType().equals(NamespaceBinding("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf")
					.withLocalPart("Description"))) {
				for (PropertyValue<QName> value : topLevel.getPropertyValues(
						NamespaceBinding("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf").withLocalPart("type"))) {
					Literal<QName> type = ((Literal<QName>) value);
					if (type.getValue().toString()
							.equals(Sbol2Terms.Component.Component.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Component.Component, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Cut.Cut.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Cut.Cut, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.FunctionalComponent.FunctionalComponent.toString()
											.replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.FunctionalComponent.FunctionalComponent, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.GenericLocation.GenericLocation.toString().replaceAll("\\{|\\}",
											""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.GenericLocation.GenericLocation, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Interaction.Interaction.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Interaction.Interaction, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Location.Location.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Location.Location, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.MapsTo.MapsTo.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.MapsTo.MapsTo, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Module.Module.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Module.Module, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Participation.Participation.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Participation.Participation, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.Range.Range.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.Range.Range, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
									.equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation.toString()
											.replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.SequenceAnnotation.SequenceAnnotation, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint
									.toString().replaceAll("\\{|\\}", ""))) {
						nested.put(topLevel.getIdentity(),
								Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
										Sbol2Terms.SequenceConstraint.SequenceConstraint, topLevel.getIdentity(),
										Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
							.equals(Sbol2Terms.Collection.Collection.toString().replaceAll("\\{|\\}", ""))) {
						topLevels.add(Datatree.TopLevelDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								Sbol2Terms.Collection.Collection, topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
							.equals(Sbol2Terms.ModuleDefinition.ModuleDefinition.toString().replaceAll("\\{|\\}", ""))) {
						topLevels.add(Datatree.TopLevelDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								Sbol2Terms.ModuleDefinition.ModuleDefinition, topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
							.equals(Sbol2Terms.Model.Model.toString().replaceAll("\\{|\\}", ""))) {
						topLevels.add(Datatree.TopLevelDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								Sbol2Terms.Model.Model, topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type.getValue().toString()
							.equals(Sbol2Terms.Sequence.Sequence.toString().replaceAll("\\{|\\}", ""))) {
						topLevels.add(Datatree.TopLevelDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								Sbol2Terms.Sequence.Sequence, topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
					}
					else if (type
							.getValue()
							.toString()
							.equals(Sbol2Terms.ComponentDefinition.ComponentDefinition.toString().replaceAll("\\{|\\}",
									""))) {
						topLevels.add(Datatree.TopLevelDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								Sbol2Terms.ComponentDefinition.ComponentDefinition, topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
					}
					else {
						topLevels.add(topLevel);
					}
				}
			} else if (topLevel.getType().equals(Sbol2Terms.Component.Component)
					|| topLevel.getType().equals(Sbol2Terms.Cut.Cut)
					|| topLevel.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent)
					|| topLevel.getType().equals(Sbol2Terms.GenericLocation.GenericLocation)
					|| topLevel.getType().equals(Sbol2Terms.Interaction.Interaction)
					|| topLevel.getType().equals(Sbol2Terms.Location.Location)
					|| topLevel.getType().equals(Sbol2Terms.MapsTo.MapsTo)
					|| topLevel.getType().equals(Sbol2Terms.Module.Module)
					|| topLevel.getType().equals(Sbol2Terms.Participation.Participation)
					|| topLevel.getType().equals(Sbol2Terms.Range.Range)
					|| topLevel.getType().equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation)
					|| topLevel.getType().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint)) {
				nested.put(topLevel.getIdentity(),
						Datatree.NestedDocument(Datatree.NamespaceBindings(topLevel.getNamespaceBindings()),
								topLevel.getType(), topLevel.getIdentity(),
								Datatree.NamedProperties(topLevel.getProperties())));
			} else {
				topLevels.add(topLevel);
			}
		}
		
		for (TopLevelDocument<QName> topLevel : topLevels) {
//			if (topLevel.getType()
//					.equals(NamespaceBinding("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf").withLocalPart(
//							"Description"))) {
//				boolean sbol2Namespace = false;
//				for (PropertyValue<QName> value : topLevel.getPropertyValues(NamespaceBinding(
//						"http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf").withLocalPart("type"))) {
//					Literal<QName> type = ((Literal<QName>) value);
//					if (type.getValue().toString()
//							.equals(Sbol2Terms.Collection.Collection.toString().replaceAll("\\{|\\}", ""))) {
//						parseCollections(SBOLDoc, topLevel);
//						sbol2Namespace = true;
//						break;
//					}
//					else if (type.getValue().toString()
//							.equals(Sbol2Terms.ModuleDefinition.ModuleDefinition.toString().replaceAll("\\{|\\}", ""))) {
//						parseModuleDefinition(SBOLDoc, topLevel, nested);
//						sbol2Namespace = true;
//						break;
//					}
//					else if (type.getValue().toString()
//							.equals(Sbol2Terms.Model.Model.toString().replaceAll("\\{|\\}", ""))) {
//						parseModels(SBOLDoc, topLevel);
//						sbol2Namespace = true;
//						break;
//					}
//					else if (type.getValue().toString()
//							.equals(Sbol2Terms.Sequence.Sequence.toString().replaceAll("\\{|\\}", ""))) {
//						parseSequences(SBOLDoc, topLevel);
//						sbol2Namespace = true;
//						break;
//					}
//					else if (type
//							.getValue()
//							.toString()
//							.equals(Sbol2Terms.ComponentDefinition.ComponentDefinition.toString().replaceAll("\\{|\\}",
//									""))) {
//						parseComponentDefinitions(SBOLDoc, topLevel);
//						sbol2Namespace = true;
//						break;
//					}
//					else if (type.getValue().toString().contains(Sbol2Terms.sbol2.getNamespaceURI())) {
//						sbol2Namespace = true;
//						break;
//					}
//				}
//				if (!sbol2Namespace) {
//					parseGenericTopLevel(SBOLDoc, topLevel);
//				}
//			}
			if (topLevel.getType().equals(Sbol2Terms.Collection.Collection))
				parseCollections(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol2Terms.ModuleDefinition.ModuleDefinition))
				parseModuleDefinition(SBOLDoc, topLevel, nested);
			else if (topLevel.getType().equals(Sbol2Terms.Model.Model))
				parseModels(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol2Terms.Sequence.Sequence))
				parseSequences(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol2Terms.ComponentDefinition.ComponentDefinition))
				parseComponentDefinitions(SBOLDoc, topLevel, nested);
			else
				parseGenericTopLevel(SBOLDoc, topLevel);
		}
	}

	private static ComponentDefinition parseDnaComponentV1(
			SBOLDocument SBOLDoc, IdentifiableDocument<QName> componentDef)
	{
		String displayId   = null;
		String name 	   = null;
		String description = null;
		URI seq_identity   = null;
		Set<URI> roles 	   = new HashSet<>();
		URI identity 	   = componentDef.getIdentity();
		String persIdentity = "";

		List<Annotation> annotations 				 = new ArrayList<>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<>();
		List<Component> components 					 = new ArrayList<>();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<>();
		List<SBOLPair> precedePairs 				 = new ArrayList<>();
		Map<URI, URI> componentDefMap 				 = new HashMap<>();

		Set<URI> type = new HashSet<>();
		type.add(ComponentDefinition.DNA);

		int component_num = 0;
		int sa_num 		  = 0;

		if (URIPrefix != null)
		{
			displayId = findDisplayId(componentDef.getIdentity().toString());
			identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
			persIdentity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,"",typesInURI).toString();
		}

		for (NamedProperty<QName> namedProperty : componentDef.getProperties())
		{
			if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				displayId = fixDisplayId(displayId);
				if (URIPrefix != null )
				{
					persIdentity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,"",typesInURI).toString();
					identity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,version,typesInURI);
				}
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.name))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.type))
			{
				// TODO: conversion to proper SO term when possible
				URI convertedSO = SequenceOntology.convertSeqOntologyV1(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				roles.add(convertedSO);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.annotations))
			{
				SequenceAnnotation sa = parseSequenceAnnotationV1(SBOLDoc,
						((NestedDocument<QName>) namedProperty.getValue()),
						precedePairs, persIdentity, ++sa_num);

				sequenceAnnotations.add(sa);

				URI component_identity    = createCompliantURI(persIdentity,"component" + component_num,version);
				AccessType access 		  = AccessType.PUBLIC;
				URI instantiatedComponent = sa.getComponentURI();
				URI originalURI 		  = ((NestedDocument<QName>) namedProperty.getValue()).getIdentity();

				componentDefMap.put(originalURI, component_identity);
				sa.setComponent(component_identity);

				Component component = new Component(component_identity, access, instantiatedComponent);
				if (!persIdentity.equals("")) {
					component.setPersistentIdentity(createCompliantURI(persIdentity,"component" + component_num,""));
					component.setDisplayId("component"+component_num);
					component.setVersion(version);
				}
				component_num++;
				components.add(component);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.dnaSequence))
			{
				seq_identity = parseDnaSequenceV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue()).getIdentity();
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		if (roles.isEmpty())
			roles.add(SequenceOntology.ENGINEERED_REGION);

		int sc_number = 0;

		for (SBOLPair pair : precedePairs)
		{
			URI sc_identity    			= createCompliantURI(persIdentity,"sequenceConstraint" + ++sc_number,version);
			URI restrictionURI 			= RestrictionType.convertToURI(RestrictionType.PRECEDES);
			//RestrictionType restriction = RestrictionType.convertToRestrictionType(restrictionURI);

			URI subject = null;
			URI object  = null;

			for (URI key : componentDefMap.keySet())
			{
				if (pair.getLeft().equals(key))
				{
					subject = componentDefMap.get(key);
				}
				else if (pair.getRight().equals(key))
				{
					object = componentDefMap.get(key);
				}
			}

			SequenceConstraint sc = new SequenceConstraint(sc_identity, restrictionURI, subject, object);
			if (!persIdentity.equals("")) {
				sc.setPersistentIdentity(createCompliantURI(persIdentity,"sequenceConstraint"+sc_number,version));
				sc.setDisplayId("sequenceConstraint"+sc_number);
				sc.setVersion(version);
			}
			sequenceConstraints.add(sc);
		}

		ComponentDefinition c = new ComponentDefinition(identity, type);
		if (!persIdentity.equals("")) {
			c.setPersistentIdentity(URI.create(persIdentity));
			c.setVersion(version);
		}
		if(roles != null)
			c.setRoles(roles);
		if(identity != componentDef.getIdentity())
			c.setWasDerivedFrom(componentDef.getIdentity());
		if (displayId != null)
			c.setDisplayId(displayId);
		if (name != null && !name.isEmpty())
			c.setName(name);
		if (description != null && !description.isEmpty())
			c.setDescription(description);
		if (seq_identity != null)
			c.addSequence(seq_identity);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		if (!sequenceAnnotations.isEmpty())
			c.setSequenceAnnotations(sequenceAnnotations);
		if (!components.isEmpty())
			c.setComponents(components);
		if (!sequenceConstraints.isEmpty())
			c.setSequenceConstraints(sequenceConstraints);

		//TODO: to fix
		ComponentDefinition oldC = SBOLDoc.getComponentDefinition(identity);
		if (oldC == null) {
			SBOLDoc.addComponentDefinition(c);
		} else {
			if (!c.equals(oldC)) {
				//System.out.println(c.toString());
				//System.out.println(oldC.toString());
				throw new SBOLValidationException("Multiple non-identical ComponentDefinitions with identity "+identity);
			}
		}
		return c;
	}

	private static Sequence parseDnaSequenceV1(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel)
	{
		String elements    = null;
		String displayId   = null;
		String name   	   = null;
		String description = null;
		URI identity 	   = topLevel.getIdentity();
		URI persistentIdentity = null;
		URI encoding 	   = Sbol2Terms.SequenceURI.DnaSequenceV1;
		List<Annotation> annotations = new ArrayList<>();

		if (URIPrefix != null)
		{
			displayId = findDisplayId(topLevel.getIdentity().toString());
			identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
			persistentIdentity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,"",typesInURI);
		}

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol1Terms.DNASequence.nucleotides))
			{
				elements = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				if (URIPrefix != null)
				{
					identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Sequence sequence = new Sequence(identity, elements, encoding);
		if(persistentIdentity!=null) {
			sequence.setPersistentIdentity(persistentIdentity);
			sequence.setVersion(version);
		}
		if(identity != topLevel.getIdentity())
			sequence.setWasDerivedFrom(topLevel.getIdentity());
		if (displayId != null)
			sequence.setDisplayId(displayId);
		if (name != null)
			sequence.setName(name);
		if (description != null)
			sequence.setDescription(description);
		if (!annotations.isEmpty())
			sequence.setAnnotations(annotations);

		Sequence oldS = SBOLDoc.getSequence(identity);
		if (oldS == null) {
			SBOLDoc.addSequence(sequence);
		} else {
			if (!sequence.equals(oldS)) {
				throw new SBOLValidationException("Multiple non-identical Sequences with identity "+identity);
			}
		}
		return sequence;
	}

	private static String fixDisplayId(String displayId) {
		displayId = displayId.replaceAll("[^a-zA-Z0-9_]", "_");
		displayId = displayId.replace(" ", "_");
		if (Character.isDigit(displayId.charAt(0))) {
			displayId = "_" + displayId;
		}
		return displayId;
	}

	private static String findDisplayId(String topLevelIdentity) {
		String displayId = null;

		topLevelIdentity = topLevelIdentity.trim();
		while (topLevelIdentity.endsWith("/")||
				topLevelIdentity.endsWith("#")||
				topLevelIdentity.endsWith(":")) {
			topLevelIdentity = topLevelIdentity.replaceAll("/$","");
			topLevelIdentity = topLevelIdentity.replaceAll("#$","");
			topLevelIdentity = topLevelIdentity.replaceAll(":$","");
		}
		int slash = topLevelIdentity.lastIndexOf('/');
		int pound = topLevelIdentity.lastIndexOf('#');
		int colon = topLevelIdentity.lastIndexOf(':');

		if (slash!=-1 /*&& slash > pound && slash > colon*/) {
			displayId = topLevelIdentity.substring(slash + 1);
		} else if (pound!=-1 && pound > colon) {
			displayId = topLevelIdentity.substring(pound + 1);
		} else if (colon!=-1) {
			displayId = topLevelIdentity.substring(colon + 1);
		} else {
			displayId = topLevelIdentity.toString();
		}
		displayId = fixDisplayId(displayId);
		return displayId;
	}

	private static Collection parseCollectionV1(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel)
	{
		URI identity 	   = topLevel.getIdentity();
		URI persistentIdentity = null;
		String displayId   = null;
		String name 	   = null;
		String description = null;

		Set<URI> members 			 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		if (URIPrefix != null)
		{
			displayId = findDisplayId(topLevel.getIdentity().toString());
			identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
			persistentIdentity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,"",typesInURI);
		}
		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol1Terms.Collection.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				displayId = fixDisplayId(displayId);
				if (URIPrefix != null)
				{
					identity = createCompliantURI(URIPrefix,TopLevel.COLLECTION,displayId,version,typesInURI);
					persistentIdentity = createCompliantURI(URIPrefix,TopLevel.COLLECTION,displayId,"",typesInURI);
				}
			}
			else if (namedProperty.getName().equals(Sbol1Terms.Collection.name))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.Collection.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.Collection.component))
			{
				members.add(parseDnaComponentV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue()).getIdentity());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		Collection c = SBOLDoc.createCollection(identity);
		Collection c = new Collection(identity);
		if (persistentIdentity!=null) {
			c.setPersistentIdentity(persistentIdentity);
			c.setVersion(version);
		}
		if(identity != topLevel.getIdentity())
			c.setWasDerivedFrom(topLevel.getIdentity());
		if (displayId != null)
			c.setDisplayId(displayId);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (!members.isEmpty())
			c.setMembers(members);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		Collection oldC = SBOLDoc.getCollection(topLevel.getIdentity());
		if (oldC == null) {
			SBOLDoc.addCollection(c);
		} else {
			if (!c.equals(oldC)) {
				throw new SBOLValidationException("The specified Collection does not exist.");
			}
		}
		return c;
	}

	private static SequenceAnnotation parseSequenceAnnotationV1(
			SBOLDocument SBOLDoc, NestedDocument<QName> sequenceAnnotation,
			List<SBOLPair> precedePairs, String parentURI, int sa_num)
	{
		Integer start 	 = null;
		Integer end 	 = null;
		String strand    = null;
		URI componentURI = null;
		URI identity 	 = sequenceAnnotation.getIdentity();
		String persIdentity = "";
		List<Annotation> annotations = new ArrayList<>();

		if (URIPrefix != null)
		{
			persIdentity = createCompliantURI(parentURI,"annotation"+sa_num,"").toString();
			identity = createCompliantURI(parentURI,"annotation"+sa_num,version);
		}

		if (!sequenceAnnotation.getType().equals(Sbol1Terms.SequenceAnnotations.SequenceAnnotation))
		{
			throw new IllegalArgumentException("QName has to be" + Sbol1Terms.SequenceAnnotations.SequenceAnnotation.toString());
		}

		for (NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.bioStart))
			{
				String temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				start = Integer.parseInt(temp);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.bioEnd))
			{
				String temp2 = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				end = Integer.parseInt(temp2);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.strand))
			{
				strand = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.subComponent))
			{
				componentURI = parseDnaComponentV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue()).getIdentity();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.precedes))
			{
				URI left 	  = sequenceAnnotation.getIdentity();
				URI right 	  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				SBOLPair pair = new SBOLPair(left, right);
				precedePairs.add(pair);
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Location location = null; // Note: Do not create a seqAnnotation if Location is empty

		if (start != null && end != null) // create SequenceAnnotation & Component
		{
			URI range_identity = createCompliantURI(persIdentity,"range",version);
			location = new Range(range_identity, start, end);
			if (!persIdentity.equals("")) {
				location.setPersistentIdentity(createCompliantURI(persIdentity,"range",""));
				location.setDisplayId("range");
				location.setVersion(version);
			}
			if (strand != null)
			{
				if (strand.equals("+"))
				{
					location.setOrientation(OrientationType.convertToOrientationType(OrientationType.inline));
				}
				else if (strand.equals("-"))
				{
					location.setOrientation(OrientationType.convertToOrientationType(OrientationType.reverseComplement));
				}
			}
		}
		else
		{
			URI dummyGenericLoc_id = createCompliantURI(persIdentity,"genericLocation",version);
			location = new GenericLocation(dummyGenericLoc_id);
			if (!persIdentity.equals("")) {
				location.setPersistentIdentity(createCompliantURI(persIdentity,"genericLocation",""));
				location.setDisplayId("genericLocation");
				location.setVersion(version);
			}
			if (strand != null)
			{
				if (strand.equals("+"))
				{
					location.setOrientation(OrientationType.convertToOrientationType(OrientationType.inline));
				}
				else if (strand.equals("-"))
				{
					location.setOrientation(OrientationType.convertToOrientationType(OrientationType.reverseComplement));
				}
			}
		}

		List<Location> locations = new ArrayList<>();
		locations.add(location);
		SequenceAnnotation s = new SequenceAnnotation(identity, locations);
		if(!persIdentity.equals("")) {
			s.setPersistentIdentity(URI.create(persIdentity));
			s.setDisplayId("annotation" + sa_num);
			s.setVersion(version);
		}
		if(identity != sequenceAnnotation.getIdentity())
			s.setWasDerivedFrom(sequenceAnnotation.getIdentity());
		if (componentURI != null)
			s.setComponent(componentURI);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);

		return s;
	}

	private static ComponentDefinition parseComponentDefinitions(
			SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		URI structure 		   = null;
		String version 		   = null;
		URI wasDerivedFrom     = null;
		Set<URI> type 		   = new HashSet<>();
		Set<URI> roles 	  	   = new HashSet<>();

		List<Component> components 					 = new ArrayList<>();
		List<Annotation> annotations 				 = new ArrayList<>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<>();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.type))
			{
				type.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.roles))
			{
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasComponent))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					components.add(parseComponent(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					components.add(parseComponent(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSubComponent))
			{
				System.out.println("Warning: tag should be sbol:component, not sbol:subComponent.");
				if (namedProperty.getValue() instanceof NestedDocument) {
					components.add(parseComponent(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					components.add(parseComponent(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequence))
			{
				structure = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					sequenceAnnotations.add(parseSequenceAnnotation(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					sequenceAnnotations.add(parseSequenceAnnotation(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceConstraints))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					sequenceConstraints.add(parseSequenceConstraint(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					sequenceConstraints.add(parseSequenceConstraint(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//ComponentDefinition c = SBOLDoc.createComponentDefinition(topLevel.getIdentity(), type, roles);
		//c.setPersistentIdentity(topLevel.getOptionalUriPropertyValue(Sbol2Terms.Identified.persistentIdentity));
		//		ComponentDefinition c = SBOLDoc.createComponentDefinition(topLevel.getIdentity(), type);
		ComponentDefinition c = new ComponentDefinition(topLevel.getIdentity(), type);
		if(roles != null)
			c.setRoles(roles);
		if (displayId != null)
			c.setDisplayId(displayId);
		if (persistentIdentity != null)
			c.setPersistentIdentity(persistentIdentity);
		if (structure != null)
			c.addSequence(structure);
		if (!components.isEmpty())
			c.setComponents(components);
		if (!sequenceAnnotations.isEmpty())
			c.setSequenceAnnotations(sequenceAnnotations);
		if (!sequenceConstraints.isEmpty())
			c.setSequenceConstraints(sequenceConstraints);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		if (version != null)
			c.setVersion(version);
		if (wasDerivedFrom != null)
			c.setWasDerivedFrom(wasDerivedFrom);

		ComponentDefinition oldC = SBOLDoc.getComponentDefinition(topLevel.getIdentity());
		if (oldC == null) {
			SBOLDoc.addComponentDefinition(c);
		} else {
			if (!c.equals(oldC)) {
				throw new SBOLValidationException("The specified ComponentDefinition does not exist.");
			}
		}
		return c;
	}

	private static SequenceConstraint parseSequenceConstraint(NestedDocument<QName> sequenceConstraint)
	{
		String displayId 	   = URIcompliance.extractDisplayId(sequenceConstraint.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(sequenceConstraint.getIdentity()));
		URI restriction  			 = null;
		URI subject 				 = null;
		URI object 					 = null;
		String version 				 = null;
		URI wasDerivedFrom 			 = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!sequenceConstraint.getType().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint)) {
			throw new SBOLValidationException(sequenceConstraint.getType() + " is not a valid sequence constraint.");
		}
		for (NamedProperty<QName> namedProperty : sequenceConstraint.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.restriction))
			{
				restriction = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());

			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasSubject))
			{
				subject = URI
						.create(((Literal<QName>) namedProperty.getValue())
								.getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasObject))
			{
				object = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		SequenceConstraint s = new SequenceConstraint(sequenceConstraint.getIdentity(), restriction, subject, object);
		if (displayId != null)
			s.setDisplayId(displayId);
		if (name != null)
			s.setName(name);
		if (description != null)
			s.setDescription(description);
		if (persistentIdentity != null)
			s.setPersistentIdentity(persistentIdentity);
		if (version != null)
			s.setVersion(version);
		if (wasDerivedFrom != null)
			s.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		return s;
	}

	private static SequenceAnnotation parseSequenceAnnotation(NestedDocument<QName> sequenceAnnotation, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(sequenceAnnotation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(sequenceAnnotation.getIdentity()));
		Location location 	   = null;
		URI componentURI 	   = null;
		String version   	   = null;
		URI wasDerivedFrom 	   = null;
		List<Location> locations = new ArrayList<>();
		List<Annotation> annotations = new ArrayList<>();

		if (!sequenceAnnotation.getType().equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation)) {
			throw new SBOLValidationException(sequenceAnnotation.getType() + " is not a valid sequence annotation.");
		}
		for (NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Location.Location))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					location = parseLocation((NestedDocument<QName>) namedProperty.getValue());
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					location = parseLocation(nested.get(uri));
				}
				locations.add(location);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceAnnotation.hasComponent))
			{
				componentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		SequenceAnnotation s = new SequenceAnnotation(sequenceAnnotation.getIdentity(), locations);

		if (persistentIdentity != null)
			s.setPersistentIdentity(persistentIdentity);
		if(version != null)
			s.setVersion(version);
		if (displayId != null)
			s.setDisplayId(displayId);
		if (componentURI != null)
			s.setComponent(componentURI);
		if (name != null)
			s.setName(name);
		if (description != null)
			s.setDescription(description);
		if (wasDerivedFrom != null)
			s.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		return s;
	}

	private static Location parseLocation(NestedDocument<QName> location)
	{
		Location l 					 = null;
		if (location.getType().equals(Sbol2Terms.Range.Range))
		{
			l = parseRange(location);
		}
		else if (location.getType().equals(Sbol2Terms.Cut.Cut))
		{
			l = parseCut(location);
		}
		else if (location.getType().equals(Sbol2Terms.GenericLocation.GenericLocation))
		{
			l = parseGenericLocation(location);
		}
		else
		{
			throw new SBOLValidationException(location.getType() + " is not a valid location type.");
		}
		return l;

	}

	private static GenericLocation parseGenericLocation(NestedDocument<QName> typeGenLoc)
	{
		String displayId 	   = URIcompliance.extractDisplayId(typeGenLoc.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(typeGenLoc.getIdentity()));
		URI orientation 			 = null;
		String version        	     = null;
		URI wasDerivedFrom 			 = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!typeGenLoc.getType().equals(Sbol2Terms.GenericLocation.GenericLocation))
		{
			throw new IllegalArgumentException("QName has to be" + Sbol2Terms.GenericLocation.GenericLocation.toString());
		}

		for (NamedProperty<QName> namedProperty : typeGenLoc.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.GenericLocation.orientation))
			{
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		GenericLocation gl = new GenericLocation(typeGenLoc.getIdentity());
		if(displayId != null)
			gl.setDisplayId(displayId);
		if (name != null)
			gl.setName(name);
		if (description != null)
			gl.setDescription(description);
		if(orientation != null)
			gl.setOrientation(OrientationType.convertToOrientationType(orientation));
		if(persistentIdentity != null)
			gl.setPersistentIdentity(persistentIdentity);
		if(version != null)
			gl.setVersion(version);
		if(wasDerivedFrom != null)
			gl.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			gl.setAnnotations(annotations);

		return gl;
	}

	private static Cut parseCut(NestedDocument<QName> typeCut)
	{
		String displayId 	   = URIcompliance.extractDisplayId(typeCut.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(typeCut.getIdentity()));
		Integer at 			   = null;
		URI orientation 	   = null;
		String version 		   = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!typeCut.getType().equals(Sbol2Terms.Cut.Cut))
		{
			throw new IllegalArgumentException("QName has to be" + Sbol2Terms.Cut.Cut.toString());
		}

		for (NamedProperty<QName> namedProperty : typeCut.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Cut.at))
			{
				String temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				at 			= Integer.parseInt(temp);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Cut.orientation))
			{
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		if (at == null)
		{
			throw new SBOLValidationException("Cut requires at property.");
		}

		Cut c = new Cut(typeCut.getIdentity(), at);
		if (persistentIdentity != null)
			c.setPersistentIdentity(persistentIdentity);
		if (displayId != null)
			c.setDisplayId(displayId);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (orientation != null)
			c.setOrientation(OrientationType.convertToOrientationType(orientation));
		if(version != null)
			c.setVersion(version);
		if (wasDerivedFrom != null)
			c.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	private static Location parseRange(NestedDocument<QName> typeRange)
	{
		String displayId 	   = URIcompliance.extractDisplayId(typeRange.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(typeRange.getIdentity()));
		Integer start 		   = null;
		Integer end 		   = null;
		URI orientation 	   = null;
		String version 		   = null;
		URI wasDerivedFrom     = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!typeRange.getType().equals(Sbol2Terms.Range.Range))
		{
			throw new IllegalArgumentException("QName has to be" + Sbol2Terms.Range.Range.toString());
		}

		for (NamedProperty<QName> namedProperty : typeRange.getProperties())
		{
			String temp;
			if (namedProperty.getName().equals(Sbol2Terms.Range.start))
			{
				temp  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				start = Integer.parseInt(temp);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Range.end))
			{
				temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				end  = Integer.parseInt(temp);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Range.orientation))
			{
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Location r = new Range(typeRange.getIdentity(), start, end);
		if (displayId != null)
			r.setDisplayId(displayId);
		if (name != null)
			r.setName(name);
		if (description != null)
			r.setDescription(description);
		if (persistentIdentity != null)
			r.setPersistentIdentity(persistentIdentity);
		if (orientation != null)
			r.setOrientation(OrientationType.convertToOrientationType(orientation));
		if(version != null)
			r.setVersion(version);
		if (wasDerivedFrom != null)
			r.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			r.setAnnotations(annotations);
		return r;
	}

	private static Component parseComponent(NestedDocument<QName> component, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(component.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(component.getIdentity()));
		String version 		   = null;
		URI subComponentURI    = null;
		AccessType access 	   = null;
		URI wasDerivedFrom 	   = null;

		List<Annotation> annotations = new ArrayList<>();
		List<MapsTo> mapsTo 		 = new ArrayList<>();

		if (!component.getType().equals(Sbol2Terms.Component.Component))
		{
			throw new IllegalArgumentException("QName has to be " + Sbol2Terms.Component.Component.toString());
		}

		if (!component.getType().equals(Sbol2Terms.Component.Component)) {
			throw new SBOLValidationException(component.getType() + " is not a valid component.");
		}
		for (NamedProperty<QName> namedProperty : component.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				String accessTypeStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				if (accessTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for access types should be http://sbols.org/v2#");
					accessTypeStr = accessTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
				}
				access = AccessType.convertToAccessType(URI.create(accessTypeStr));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					mapsTo.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mapsTo.add(parseMapsTo(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				subComponentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Component c = new Component(component.getIdentity(), access, subComponentURI);
		if (persistentIdentity != null)
			c.setPersistentIdentity(persistentIdentity);
		if(version != null)
			c.setVersion(version);
		if (displayId != null)
			c.setDisplayId(displayId);
		if (access != null)
			c.setAccess(access);
		if (!mapsTo.isEmpty())
			c.setMapsTo(mapsTo);
		if (subComponentURI != null)
			c.setDefinition(subComponentURI);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (wasDerivedFrom != null)
			c.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	private static GenericTopLevel parseGenericTopLevel(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom 	   = null;

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		GenericTopLevel t = SBOLDoc.createGenericTopLevel(topLevel.getIdentity(), topLevel.getType());
		GenericTopLevel t = new GenericTopLevel(topLevel.getIdentity(), topLevel.getType());
		if (persistentIdentity != null)
			t.setPersistentIdentity(persistentIdentity);
		if (version != null)
			t.setVersion(version);
		if (displayId != null)
			t.setDisplayId(displayId);
		if (name != null)
			t.setName(name);
		if (description != null)
			t.setDescription(description);
		if (wasDerivedFrom != null)
			t.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			t.setAnnotations(annotations);

		GenericTopLevel oldG = SBOLDoc.getGenericTopLevel(topLevel.getIdentity());
		if (oldG == null) {
			SBOLDoc.addGenericTopLevel(t);
		} else {
			if (!t.equals(oldG)) {
				throw new SBOLValidationException("The specified GenericTopLevel does not exist.");
			}
		}
		return t;
	}

	private static Model parseModels(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI source 			   = null;
		URI language 		   = null;
		URI framework 	 	   = null;
		URI wasDerivedFrom 	   = null;

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.source))
			{
				source = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.language))
			{
				language = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.framework))
			{
				framework = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		Model m = SBOLDoc.createModel(topLevel.getIdentity(), source, language, framework);
		Model m = new Model(topLevel.getIdentity(), source, language, framework);
		if (persistentIdentity != null)
			m.setPersistentIdentity(persistentIdentity);
		if (version != null)
			m.setVersion(version);
		if (displayId != null)
			m.setDisplayId(displayId);
		if (name != null)
			m.setName(name);
		if (description != null)
			m.setDescription(description);
		if (wasDerivedFrom != null)
			m.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			m.setAnnotations(annotations);

		Model oldM = SBOLDoc.getModel(topLevel.getIdentity());
		if (oldM == null) {
			SBOLDoc.addModel(m);
		} else {
			if (!m.equals(oldM)) {
				throw new SBOLValidationException("The specified Model does not exist.");
			}
		}
		return m;
	}

	private static Collection parseCollections(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom 	   = null;

		Set<URI> members 			 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Collection.hasMembers))
			{
				members.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Collection c = new Collection(topLevel.getIdentity());
		if (displayId != null)
			c.setDisplayId(displayId);
		if (version != null)
			c.setVersion(version);
		if (persistentIdentity != null)
			c.setPersistentIdentity(persistentIdentity);
		if (!members.isEmpty())
			c.setMembers(members);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if( wasDerivedFrom != null)
			c.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		Collection oldC = SBOLDoc.getCollection(topLevel.getIdentity());
		if (oldC == null) {
			SBOLDoc.addCollection(c);
		} else {
			if (!c.equals(oldC)) {
				throw new SBOLValidationException("The specified Collection does not exist.");
			}
		}
		return c;
	}

	private static ModuleDefinition parseModuleDefinition(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 	       = null;
		URI wasDerivedFrom 	   = null;
		Set<URI> roles 		   = new HashSet<>();
		Set<URI> models 	   = new HashSet<>();

		List<FunctionalComponent> functionalComponents = new ArrayList<>();
		List<Interaction> interactions 				   = new ArrayList<>();
		List<Module> subModules 					   = new ArrayList<>();
		List<Annotation> annotations 				   = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.roles))
			{
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModule))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					subModules.add(parseModule(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					subModules.add(parseModule(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasSubModule))
			{
				System.out.println("Warning: tag should be sbol:module, not sbol:subModule.");
				if (namedProperty.getValue() instanceof NestedDocument) {
					subModules.add(parseModule(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					subModules.add(parseModule(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasInteractions))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					interactions.add(parseInteraction((NestedDocument<QName>) namedProperty.getValue(), nested));
				} else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					interactions.add(parseInteraction(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasfunctionalComponent))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					functionalComponents
							.add(parseFunctionalComponent((NestedDocument<QName>) namedProperty.getValue(), nested));
				} else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					functionalComponents.add(parseFunctionalComponent(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasComponent))
			{
				System.out.println("Warning: tag should be sbol:functionalComponent, not sbol:component.");
				if (namedProperty.getValue() instanceof NestedDocument) {
					functionalComponents
							.add(parseFunctionalComponent((NestedDocument<QName>) namedProperty.getValue(), nested));
				} else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					functionalComponents.add(parseFunctionalComponent(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModels))
			{
				models.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		ModuleDefinition moduleDefinition = SBOLDoc.createModuleDefinition(topLevel.getIdentity());
		ModuleDefinition moduleDefinition = new ModuleDefinition(topLevel.getIdentity());
		if (!roles.isEmpty())
			moduleDefinition.setRoles(roles);
		if (persistentIdentity != null)
			moduleDefinition.setPersistentIdentity(persistentIdentity);
		if (version != null)
			moduleDefinition.setVersion(version);
		if (displayId != null)
			moduleDefinition.setDisplayId(displayId);
		if (!functionalComponents.isEmpty())
			moduleDefinition.setFunctionalComponents(functionalComponents);
		if (!interactions.isEmpty())
			moduleDefinition.setInteractions(interactions);
		if (!models.isEmpty())
			moduleDefinition.setModels(models);
		if (!subModules.isEmpty())
			moduleDefinition.setModules(subModules);
		if (name != null)
			moduleDefinition.setName(name);
		if (description != null)
			moduleDefinition.setDescription(description);
		if (wasDerivedFrom != null)
			moduleDefinition.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			moduleDefinition.setAnnotations(annotations);

		ModuleDefinition oldM = SBOLDoc.getModuleDefinition(topLevel.getIdentity());
		if (oldM == null) {
			SBOLDoc.addModuleDefinition(moduleDefinition);
		} else {
			if (!moduleDefinition.equals(oldM)) {
				throw new SBOLValidationException("The specified ModuleDefinition does not exist.");
			}
		}
		return moduleDefinition;
	}

	private static Module parseModule(NestedDocument<QName> module, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(module.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(module.getIdentity()));
		String version 		   = null;
		URI definitionURI 	   = null;
		URI wasDerivedFrom 	   = null;
		List<MapsTo> mappings 		 = new ArrayList<>();
		List<Annotation> annotations = new ArrayList<>();

		if (!module.getType().equals(Sbol2Terms.Module.Module))
		{
			throw new IllegalArgumentException("QName has to be " + Sbol2Terms.Module.Module.toString());
		}

		if (!module.getType().equals(Sbol2Terms.Module.Module)) {
			throw new SBOLValidationException(module.getType() + " is not a valid module.");
		}
		for (NamedProperty<QName> namedProperty : module.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapping))
			{
				System.out.println("Warning: tag should be sbol:mapTo, not sbol:mapping.");
				if (namedProperty.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasDefinition))
			{
				definitionURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		Module submodule = new Module(module.getIdentity(), definitionURI);
		if (persistentIdentity != null)
			submodule.setPersistentIdentity(persistentIdentity);
		if (version != null)
			submodule.setVersion(version);
		if (displayId != null)
			submodule.setDisplayId(displayId);
		if (!mappings.isEmpty())
			submodule.setMapsTos(mappings);
		if (name != null)
			submodule.setName(name);
		if (description != null)
			submodule.setDescription(description);
		if( wasDerivedFrom != null)
			submodule.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			submodule.setAnnotations(annotations);
		return submodule;
	}

	private static MapsTo parseMapsTo(NestedDocument<QName> mapsTo)
	{
		String displayId 	   = URIcompliance.extractDisplayId(mapsTo.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(mapsTo.getIdentity()));
		String version 	  	 	  = null;
		URI remote 				  = null;
		RefinementType refinement = null;
		URI local 				  = null;
		URI wasDerivedFrom 		  = null;

		List<Annotation> annotations = new ArrayList<>();

		if (!mapsTo.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
			throw new SBOLValidationException(mapsTo.getType() + " is not a valid mapsTo.");
		}
		for (NamedProperty<QName> m : mapsTo.getProperties())
		{
			if (m.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.refinement))
			{
				String refinementStr = ((Literal<QName>) m.getValue()).getValue().toString();
				if (!refinementStr.startsWith("http://sbols.org/v2#")) {
					System.out.println("Warning: namespace for refinement types should be http://sbols.org/v2#");
					refinementStr = "http://sbols.org/v2#" + refinementStr;
				}
				refinement = RefinementType.convertToRefinementType(URI.create(refinementStr));
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.hasRemote))
			{
				remote = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.hasLocal))
			{
				local = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(m));
			}
		}

		MapsTo map = new MapsTo(mapsTo.getIdentity(), refinement, local, remote);
		if (displayId != null)
			map.setDisplayId(displayId);
		if (name != null)
			map.setName(name);
		if (description != null)
			map.setDescription(description);
		if (persistentIdentity != null)
			map.setPersistentIdentity(persistentIdentity);
		if (version != null)
			map.setVersion(version);
		if (wasDerivedFrom != null)
			map.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			map.setAnnotations(annotations);
		return map;
	}

	private static Interaction parseInteraction(NestedDocument<QName> interaction, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(interaction.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(interaction.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom	   = null;

		Set<URI> type 		   			   = new HashSet<>();
		List<Participation> participations = new ArrayList<>();
		List<Annotation> annotations 	   = new ArrayList<>();

		if (!interaction.getType().equals(Sbol2Terms.Interaction.Interaction)) {
			throw new SBOLValidationException(interaction.getType() + " is not a valid interaction.");
		}
		for (NamedProperty<QName> i : interaction.getProperties())
		{
			if (i.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) i.getValue()).getValue().toString());
			}
			else if (i.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Interaction.type))
			{
				type.add(URI.create(((Literal<QName>) i.getValue()).getValue().toString()));
			}
			else if (i.getName().equals(Sbol2Terms.Interaction.hasParticipations))
			{
				if (i.getValue() instanceof NestedDocument) {
					participations.add(parseParticipation(((NestedDocument<QName>) i.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)i.getValue()).getValue();
					participations.add(parseParticipation(nested.get(uri)));
				}
			}
			else if (i.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) i.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(i));
			}
		}

		Interaction i = new Interaction(interaction.getIdentity(), type);
		if (!participations.isEmpty())
			i.setParticipations(participations);
		if (persistentIdentity != null)
			i.setPersistentIdentity(persistentIdentity);
		if (version != null)
			i.setVersion(version);
		if (displayId != null)
			i.setDisplayId(displayId);
		if (name != null)
			i.setName(name);
		if (description != null)
			i.setDescription(description);
		if (wasDerivedFrom != null)
			i.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			i.setAnnotations(annotations);
		return i;
	}

	private static Participation parseParticipation(NestedDocument<QName> participation)
	{
		String displayId 	   = URIcompliance.extractDisplayId(participation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(participation.getIdentity()));
		String version 		   = null;
		Set<URI> roles 		   = new HashSet<>();
		URI participant        = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!participation.getType().equals(Sbol2Terms.Participation.Participation))
		{
			throw new IllegalArgumentException("QName has to be " + Sbol2Terms.Participation.Participation.toString());
		}

		if (!participation.getType().equals(Sbol2Terms.Participation.Participation)) {
			throw new SBOLValidationException(participation.getType() + " is not a valid participation.");
		}
		for (NamedProperty<QName> p : participation.getProperties())
		{
			if (p.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else if (p.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Participation.role))
			{
				roles.add(URI.create(((Literal<QName>) p.getValue()).getValue()
						.toString()));
			}
			else if (p.getName().equals(Sbol2Terms.Participation.hasParticipant))
			{
				participant = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else if (p.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(p));
			}
		}

		Participation p = new Participation(participation.getIdentity(), participant);
		if (!roles.isEmpty())
			p.setRoles(roles);
		if (displayId != null)
			p.setDisplayId(displayId);
		if (name != null)
			p.setName(name);
		if (description != null)
			p.setDescription(description);
		if (persistentIdentity != null)
			p.setPersistentIdentity(persistentIdentity);
		if (version != null)
			p.setVersion(version);
		if( wasDerivedFrom != null)
			p.setWasDerivedFrom(wasDerivedFrom);
		if(!annotations.isEmpty())
			p.setAnnotations(annotations);
		return p;
	}

	private static FunctionalComponent parseFunctionalComponent(NestedDocument<QName> functionalComponent, Map<URI, NestedDocument<QName>> nested)
	{
		String displayId 	   = URIcompliance.extractDisplayId(functionalComponent.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(functionalComponent.getIdentity()));
		String version 			   = null;
		AccessType access 		   = null;
		DirectionType direction    = null;
		URI functionalComponentURI = null;
		URI wasDerivedFrom 		   = null;

		List<Annotation> annotations = new ArrayList<>();
		List<MapsTo> mappings 		 = new ArrayList<>();


		if (!functionalComponent.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent))
		{
			throw new IllegalArgumentException("QName has to be " + Sbol2Terms.FunctionalComponent.FunctionalComponent.toString());
		}

		if (!functionalComponent.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent)) {
			throw new SBOLValidationException(functionalComponent.getType() + " is not a valid functional component.");
		}
		for (NamedProperty<QName> f : functionalComponent.getProperties())
		{
			if (f.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) f.getValue()).getValue().toString());
			}
			else if (f.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				String accessTypeStr = ((Literal<QName>) f.getValue()).getValue().toString();
				if (accessTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for access types should be http://sbols.org/v2#");
					accessTypeStr = accessTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
				}
				access = AccessType.convertToAccessType(URI.create(accessTypeStr));
			}
			else if (f.getName().equals(Sbol2Terms.FunctionalComponent.direction))
			{
				String directionTypeStr = ((Literal<QName>) f.getValue()).getValue().toString();
				if (directionTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for direction types should be http://sbols.org/v2#");
					directionTypeStr = directionTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
					directionTypeStr = directionTypeStr.replace("input","in");
					directionTypeStr = directionTypeStr.replace("output","out");
				}
				direction = DirectionType.convertToDirectionType(URI.create(directionTypeStr));
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.hasMapsTo))
			{
				if (f.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) f.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)f.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri)));
				}
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				functionalComponentURI = URI.create(((Literal<QName>) f.getValue()).getValue().toString());
			}
			else if (f.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) f.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(f));
			}

		}
		FunctionalComponent fc = new FunctionalComponent(
				functionalComponent.getIdentity(), access,
				functionalComponentURI, direction);
		if (persistentIdentity != null)
			fc.setPersistentIdentity(persistentIdentity);
		if (version != null)
			fc.setVersion(version);
		if (displayId != null)
			fc.setDisplayId(displayId);
		if (!mappings.isEmpty())
			fc.setMapsTo(mappings);
		if (name != null)
			fc.setName(name);
		if (description != null)
			fc.setDescription(description);
		if (wasDerivedFrom != null)
			fc.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			fc.setAnnotations(annotations);
		return fc;
	}

	private static Sequence parseSequences(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String displayId 	   = URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		String elements 	   = null;
		URI encoding 		   = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.elements))
			{
				elements = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.encoding))
			{
				encoding = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(), elements, encoding);
		Sequence sequence = new Sequence(topLevel.getIdentity(), elements, encoding);
		if (persistentIdentity != null)
			sequence.setPersistentIdentity(persistentIdentity);
		if (version != null)
			sequence.setVersion(version);
		if (displayId != null)
			sequence.setDisplayId(displayId);
		if (name != null)
			sequence.setName(name);
		if (description != null)
			sequence.setDescription(description);
		if (wasDerivedFrom != null)
			sequence.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			sequence.setAnnotations(annotations);

		Sequence oldS = SBOLDoc.getSequence(topLevel.getIdentity());
		if (oldS == null) {
			SBOLDoc.addSequence(sequence);
		} else {
			if (!sequence.equals(oldS)) {
				throw new SBOLValidationException("The specified Sequence does not exist.");
			}
		}
		return sequence;
	}

	/*private static Timestamp getTimestamp(String timeStamp)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date date  = null;
		try
		{
			date = sdf.parse(timeStamp);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}*/

	/*
	private static URI getParentURI(URI identity)
	{
		String regex       = ".*[/]\\d+[/]\\d+";
		String regex_minor = ".*[/]\\d+[/]";
		String regex_major = ".*[/]\\d+";
		String regex_end   = ".*[/]";

		String identity_str = identity.toString();

		while (identity_str.matches(regex)
				|| identity_str.matches(regex_minor)
				|| identity_str.matches(regex_major)
				|| identity_str.matches(regex_end))
		{
			identity_str = identity_str.substring(0, identity_str.length() - 1);
		}

		return URI.create(identity_str);
	}
	 */
}
