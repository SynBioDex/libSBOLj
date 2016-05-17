package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
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
import uk.ac.ncl.intbio.core.io.CoreIoException;
import uk.ac.ncl.intbio.core.io.IoReader;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

/**
 * Provides methods to read input SBOL files. 
 *
 * @author Tramy Nguyen
 * @author Chris Myers
 * @author Zhen Zhang
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @version 2.1
 */

public class SBOLReader
{

	/**
	 * Constant representing SBOL version 1.1
	 */
	public static final String SBOLVERSION1 = "v1";
	
	/**
	 * Constant representing SBOL version 2.0
	 */
	public static final String SBOLVERSION2 = "v2";
	
		
	/**
	 * A {@code true} value of the {@code keepGoing} flag tells the SBOL reader
	 * to continue reading an SBOL input file, after it encounters an SBOL validation exception;
	 * a {@code false} value forces the reader to stop reading after it encounters
	 * an SBOL validation exception.
	 */
	public static boolean keepGoing = false;
	
	private static List<String> errors = new ArrayList<String>();

	/**
	 * Returns the value of the {@code keepGoing} flag.
	 * @return the value of the {@code keepGoing} flag
	 */
	public static boolean isKeepGoing() {
		return keepGoing;
	}

	/**
	 * Sets the value of the {@code keepGoing} flag to the specified Boolean value.
	 * @param keepGoing The specified Boolean value
	 */
	public static void setKeepGoing(boolean keepGoing) {
		SBOLReader.keepGoing = keepGoing;
	}

	/**
	 * Sets the error list that is used to store SBOL validation exceptions 
	 * during reading to empty. 
	 */
	public static void clearErrors() {
		errors = new ArrayList<String>();
	}

	/**
	 * Returns the error list that is used to store SBOL validation exceptions.
	 * @return the error list that is used to store SBOL validation exceptions
	 */
	public static List<String> getErrors() {
		return errors;
	}

	/**
	 * Returns the number of errors in the error list. 
	 * @return the number of errors in the error list
	 */
	public static int getNumErrors() {
		return errors.size();
	}

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
	private static boolean dropObjectsWithDuplicateURIs = false;
	private static boolean compliant = true;
	private static URI defaultSequenceEncoding = Sequence.IUPAC_DNA;

	/**
	 * Check if document is to be read as being compliant.
	 *
	 * @return if document is to be read as being compliant
	 */
	public static boolean isCompliant() {
		return compliant;
	}

	/**
	 * Set if document is to be read as compliant.
	 *
	 * @param compliant A flag to indicate if document is to be read as compliant.
	 */
	public static void setCompliant(boolean compliant) {
		SBOLReader.compliant = compliant;
	}

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 *  @param URIprefix maps to a domain over which the user has control
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
	 * @param version The given version for this object
	 */
	public static void setVersion(String version)
	{
		SBOLReader.version = version;
	}

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 * @param typesInURI A flag to determine if types are to be inserted into top-level URIs
	 */
	public static void setTypesInURI(boolean typesInURI)
	{
		SBOLReader.typesInURI = typesInURI;
	}

	/**
	 * Check if objects with duplicate URIs should be dropped.
	 *
	 * @return if objects with duplicate URIs should be dropped.
	 */
	public static boolean isDropObjectsWithDuplicateURIs() {
		return dropObjectsWithDuplicateURIs;
	}

	/**
	 * Set if objects with duplicate URIs should be dropped.
	 *
	 * @param dropObjectsWithDuplicateURIs A flag to indicate if objects with duplicate URIs should be dropped
	 */
	public static void setDropObjectsWithDuplicateURIs(boolean dropObjectsWithDuplicateURIs) {
		SBOLReader.dropObjectsWithDuplicateURIs = dropObjectsWithDuplicateURIs;
	}

	/**
	 * Sets the default sequence encoding for FASTA conversion.
	 * 
	 * @return the defaultSequenceEncoding
	 */
	public static URI getDefaultSequenceEncoding() {
		return defaultSequenceEncoding;
	}

	/**
	 * @param defaultSequenceEncoding the defaultSequenceEncoding to set
	 */
	public static void setDefaultSequenceEncoding(URI defaultSequenceEncoding) {
		SBOLReader.defaultSequenceEncoding = defaultSequenceEncoding;
	}

	private static String getSBOLVersion(DocumentRoot<QName> document) throws SBOLValidationException
	{
		boolean foundRDF = false;
		boolean foundSBOL1 = false;
		boolean foundSBOL2 = false;
		for (NamespaceBinding n : document.getNamespaceBindings())
		{
			if (n.getNamespaceURI().equals(Sbol1Terms.rdf.getNamespaceURI())) foundRDF = true;
			if (n.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI()))	foundSBOL1 = true;
			if (n.getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI()))	foundSBOL2 = true;
		}
		if (foundSBOL2) {
			if (!foundRDF) {
				throw new SBOLValidationException("sbol-10102");
			}
			return SBOLVERSION2;
		} else if (foundSBOL1) {
			if (!foundRDF) {
				throw new SBOLValidationException("sbol-10102");
			}
			return SBOLVERSION1;
		} else {
			throw new SBOLValidationException("sbol-10101");
		}
	}

	/**
	 * Takes in a given RDF filename and returns the SBOL version of the file.
	 *
	 * @param fileName a given RDF filename
	 * @return the SBOL version of the file.
	 * @throws FileNotFoundException if file not found
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	public static String getSBOLVersion(String fileName) throws FileNotFoundException, SBOLValidationException
	{
		return getSBOLVersion(fileName,SBOLDocument.RDF);
	}

	/**
	 * Takes in a given filename and fileType, and returns the SBOL version of the file.
	 *
	 * @param fileName
	 * @return the SBOL version of the file.
	 * @throws FileNotFoundException if file not found
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	static String getSBOLVersion(String fileName, String fileType) throws FileNotFoundException, SBOLValidationException
	{
		FileInputStream stream     = new FileInputStream(new File(fileName));
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return getSBOLVersion(buffer,fileType);
	}

	/**
	 * Takes in the given RDF filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #read(File)}.
	 *
	 * @param fileName the name of the given RDF file
	 * @return the converted SBOLDocument
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static SBOLDocument read(String fileName) throws SBOLValidationException, IOException, SBOLConversionException
	{
		return read(fileName,SBOLDocument.RDF);
	}

	/**
	 * Takes in the given filename and fileType, and converts the file to an SBOLDocument.
	 *
	 * @param fileName the name of the given file
	 * @param fileType the file type of the given file 
	 * @return the converted SBOLDocument
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 * @throws SBOLConversionException 
	 * @throws IOException 
	 */
	static SBOLDocument read(String fileName,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		return read(new File(fileName),fileType);
	}

	/**
	 * Takes in a given RDF File and returns the SBOL version of the file.
	 *
	 * @param file the given RDF file
	 * @return the SBOL version of the file.
	 * @throws FileNotFoundException if file not found
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	public static String getSBOLVersion(File file) throws FileNotFoundException, SBOLValidationException
	{
		return getSBOLVersion(file,SBOLDocument.RDF);
	}

	/**
	 * Parses the given RDF file and stores its contents in an SBOLDocument object.
	 *
	 * @param file the given RDF file
	 * @return an SBOLDocument object that stores the RDF file information
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static SBOLDocument read(File file) throws SBOLValidationException, IOException, SBOLConversionException
	{
		return read(file,SBOLDocument.RDF);
	}

	/**
	 * Takes in the given file and fileType, and convert the file to an SBOLDocument.
	 *
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws FactoryConfigurationError
	 * @throws XMLStreamException
	 * @throws CoreIoException
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 * @throws SBOLConversionException 
	 * @throws IOException 
	 */
	static SBOLDocument read(File file,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return read(buffer,fileType);
	}

	/**
	 * Takes in a given File and fileType, and returns the SBOL version of the file.
	 *
	 * @param file
	 * @return the SBOL version of the file.
	 * @throws FileNotFoundException if file not found
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	static String getSBOLVersion(File file,String fileType) throws FileNotFoundException, SBOLValidationException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return getSBOLVersion(buffer,fileType);
	}

	/**
	 * Takes in a given InputStream and fieType, and returns the SBOL version of the file.
	 *
	 * @param in a given InputStream
	 * @param fileType a given file type
	 * @return the SBOL version of the JSON file.
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	static String getSBOLVersion(InputStream in,String fileType) throws SBOLValidationException
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		DocumentRoot<QName> document = null;
		if (fileType.equals(SBOLDocument.JSON)) {
			document = readJSON(new StringReader(inputStreamString));
		} else if (fileType.equals(SBOLDocument.TURTLE)) {
			document = readTurtle(new StringReader(inputStreamString));
		} else {
			document = readRDF(new StringReader(inputStreamString));
		}
		scanner.close();
		return getSBOLVersion(document);
	}

	/**
	 * Takes in a given RDF InputStream and converts the file to an SBOLDocument.
	 *
	 * @param in a given RDF InputStream
	 * @return the converted SBOLDocument instance
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 */
	public static SBOLDocument read(InputStream in) throws SBOLValidationException, IOException, SBOLConversionException
	{
		SBOLDocument SBOLDoc     = new SBOLDocument();
		SBOLDoc.setCompliant(compliant);
		read(SBOLDoc,in,SBOLDocument.RDF);
		return SBOLDoc;
	}

	/**
	 * Takes in a given InputStream and fileType, and convert the file to an SBOLDocument.
	 *
	 * @param in a given InputStream
	 * @param fileType a given file type
	 * @return the converted SBOLDocument instance
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 * @throws SBOLConversionException 
	 * @throws IOException 
	 */
	static SBOLDocument read(InputStream in,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		SBOLDocument SBOLDoc     = new SBOLDocument();
		SBOLDoc.setCompliant(compliant);
		if (URIPrefix!=null) {
			SBOLDoc.setDefaultURIprefix(URIPrefix);
		}
		read(SBOLDoc,in,fileType);
		return SBOLDoc;
	}

	static void read(SBOLDocument SBOLDoc,InputStream in,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		compliant = SBOLDoc.isCompliant();
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString = scanner.useDelimiter("\\A").next();
		clearErrors();

		DocumentRoot<QName> document = null;
		try {
			if (FASTA.isFastaString(inputStreamString)) {
				SBOLDoc.setCreateDefaults(true);
				SBOLDoc.setCompliant(true);
				if (URIPrefix==null) {
					scanner.close();
					throw new SBOLConversionException("No URI prefix has been provided.");
				}
				SBOLDoc.setDefaultURIprefix(URIPrefix);
				FASTA.read(SBOLDoc, inputStreamString, URIPrefix, version, defaultSequenceEncoding);
				scanner.close();
				return;
			} else if (GenBank.isGenBankString(inputStreamString)) {
				SBOLDoc.setCreateDefaults(true);
				SBOLDoc.setCompliant(true);
				if (URIPrefix==null) {
					scanner.close();
					throw new SBOLConversionException("No URI prefix has been provided.");
				}
				SBOLDoc.setDefaultURIprefix(URIPrefix);
				GenBank.read(SBOLDoc, inputStreamString, URIPrefix);
				scanner.close();
				return;
			} else if (fileType.equals(SBOLDocument.JSON)) {
				document = readJSON(new StringReader(inputStreamString));
			} else if (fileType.equals(SBOLDocument.TURTLE)){
				document = readTurtle(new StringReader(inputStreamString));
			} else {
				document = readRDF(new StringReader(inputStreamString));
			}
			if (getSBOLVersion(document).equals(SBOLVERSION1))
			{
				scanner.close();
				readV1(SBOLDoc,document);
				return;
			}
		} catch (SBOLValidationException e) {
			if (keepGoing) {
				errors.add(e.getMessage());
				return;
			} else {
				throw new SBOLValidationException(e);
			}
		}

		for (NamespaceBinding n : document.getNamespaceBindings())

		{
			if (SBOLDoc.getNamespace(URI.create(n.getNamespaceURI()))==null) {
				if (n.getPrefix()==null) {
					SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), ""));
				} else {
					SBOLDoc.addNamespaceBinding(NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
				}
			}

		}

		readTopLevelDocs(SBOLDoc, document);
		scanner.close();
		SBOLValidate.clearErrors();
		SBOLValidate.validateCompliance(SBOLDoc);
		if (SBOLValidate.getNumErrors()>0) {
			SBOLDoc.setCompliant(false);
		}
	}

	/**
	 * Takes in a given RDF InputStream and returns the SBOL version of the file.
	 *
	 * @param in a given RDF InputStream
	 * @return the SBOL version of the file.
	 * @throws SBOLValidationException if an SBOL validation rule is violated.
	 */
	public static String getSBOLVersion(InputStream in) throws SBOLValidationException
	{
		return getSBOLVersion(in,SBOLDocument.RDF);
	}

	private static SBOLDocument readV1(SBOLDocument SBOLDoc, DocumentRoot<QName> document) throws SBOLValidationException, SBOLConversionException
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
				if (SBOLDoc.getNamespace(URI.create(n.getNamespaceURI()))==null) {
					SBOLDoc.addNamespaceBinding(
							NamespaceBinding(n.getNamespaceURI(), n.getPrefix()));
				}
			}
		}
		SBOLDoc.addNamespaceBinding(NamespaceBinding(Sbol2Terms.prov.getNamespaceURI(),
				Sbol2Terms.prov.getPrefix()));
		readTopLevelDocsV1(SBOLDoc, document);
		SBOLValidate.clearErrors();
		SBOLValidate.validateCompliance(SBOLDoc);
		if (SBOLValidate.getNumErrors()>0) {
			SBOLDoc.setCompliant(false);
		}
		return SBOLDoc;
	}

	private static DocumentRoot<QName> readJSON(Reader stream) throws SBOLValidationException
	{
		JsonReader reader 		  = Json.createReaderFactory(Collections.<String, Object> emptyMap()).createReader(stream);
		JsonIo jsonIo 	  		  = new JsonIo();
		IoReader<String> ioReader = jsonIo.createIoReader(reader.read());
		DocumentRoot<String> root;
		try {
			root = ioReader.read();
		}
		catch (CoreIoException e) {
			throw new SBOLValidationException("sbol-10105");
		}
		return StringifyQName.string2qname.mapDR(root);
	}

	private static DocumentRoot<QName> readRDF(Reader reader) throws SBOLValidationException
	{
		try {
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
			RdfIo rdfIo 			  = new RdfIo();
			return rdfIo.createIoReader(xmlReader).read();
		}
		catch (FactoryConfigurationError e) {
			throw new SBOLValidationException("sbol-10105");
		}
		catch (XMLStreamException e) {
			throw new SBOLValidationException("sbol-10105");
		}
		catch (CoreIoException e) {
			throw new SBOLValidationException("sbol-10105");
		}
		catch (ClassCastException e) {
			if (e.getMessage().contains("IdentifiableDocument")) {
				throw new SBOLValidationException("sbol-10201");
			}
			throw new SBOLValidationException("sbol-10105");
		}
	}

	private static DocumentRoot<QName> readTurtle(Reader reader) throws SBOLValidationException
	{
		TurtleIo turtleIo = new TurtleIo();
		try {
			return turtleIo.createIoReader(reader).read();
		}
		catch (CoreIoException e) {
			throw new SBOLValidationException("sbol-10105");
		}
	}

	private static void readTopLevelDocsV1(SBOLDocument SBOLDoc, DocumentRoot<QName> document) throws SBOLValidationException, SBOLConversionException
	{
		clearErrors();
		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			try {
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
			} catch (SBOLValidationException e) {
				if (keepGoing) {
					errors.add(e.getMessage());
				} else {
					throw new SBOLValidationException(e);
				}
			}
		}
	}

	private static void readTopLevelDocs(SBOLDocument SBOLDoc, DocumentRoot<QName> document) throws SBOLValidationException
	{
		Map<URI, NestedDocument<QName>> nested = new HashMap<URI, NestedDocument<QName>>();
		List<TopLevelDocument<QName>> topLevels = new ArrayList<TopLevelDocument<QName>>();
		clearErrors();

		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments()) {

			if (topLevel.getType().equals(Sbol2Terms.Description.Description)) {
				if (topLevel.getPropertyValues(Sbol2Terms.Description.type).isEmpty()) {
					if (keepGoing) {
						errors.add(new SBOLValidationException("sbol-12302",topLevel.getIdentity()).getExceptionMessage());
					} else {
						throw new SBOLValidationException("sbol-12302",topLevel.getIdentity());
					}
				}
				for (PropertyValue<QName> value : topLevel.getPropertyValues(Sbol2Terms.Description.type)) {
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
			try {
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
			} catch (SBOLValidationException e) {
				if (keepGoing) {
					errors.add(e.getMessage());
				} else {
					throw new SBOLValidationException(e);
				}
			}
		}
	}

	private static ComponentDefinition parseDnaComponentV1(
			SBOLDocument SBOLDoc, IdentifiableDocument<QName> componentDef) throws SBOLValidationException, SBOLConversionException
	{
		String displayId   = null;
		String name 	   = null;
		String description = null;
		URI seq_identity   = null;
		Set<URI> roles 	   = new HashSet<>();
		URI identity 	   = componentDef.getIdentity();
		String persIdentity = componentDef.getIdentity().toString();

		List<Annotation> annotations 				 = new ArrayList<>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<>();
		Set<String> instantiatedComponents	         = new HashSet<>();
		Set<Component> components 					 = new HashSet<>();
		Set<SequenceConstraint> sequenceConstraints = new HashSet<>();
		List<SBOLPair> precedePairs 				 = new ArrayList<>();
		Map<URI, URI> componentDefMap 				 = new HashMap<>();

		Set<URI> type = new HashSet<>();
		type.add(ComponentDefinition.DNA);

		int component_num = 0;
		int sa_num 		  = 0;

		if (URIPrefix != null)
		{
			displayId = findDisplayId(componentDef.getIdentity().toString());
			identity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,version,typesInURI);
			persIdentity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,"",typesInURI).toString();
		}

		for (NamedProperty<QName> namedProperty : componentDef.getProperties())
		{
			if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.displayId))
			{
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				displayId = URIcompliance.fixDisplayId(displayId);
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
				URI convertedSO = SequenceOntology.convertSeqOntologyV1(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				roles.add(convertedSO);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.annotations))
			{
				SequenceAnnotation sa = parseSequenceAnnotationV1(SBOLDoc,
						((NestedDocument<QName>) namedProperty.getValue()),
						precedePairs, persIdentity, ++sa_num, instantiatedComponents);

				sequenceAnnotations.add(sa);

				URI component_identity    = createCompliantURI(persIdentity,"component" + component_num,version);
				URI component_persIdentity = createCompliantURI(persIdentity,"component" + component_num,"");
				String component_displayId = "component"+component_num;
				AccessType access 		  = AccessType.PUBLIC;
				URI instantiatedComponent = sa.getComponentURI();
				ComponentDefinition instantiatedDef = SBOLDoc.getComponentDefinition(instantiatedComponent);
				if (compliant && instantiatedDef != null && instantiatedDef.isSetDisplayId() &&
						!instantiatedComponents.contains(instantiatedDef.getDisplayId())) {
					component_identity = createCompliantURI(persIdentity, instantiatedDef.getDisplayId(),version);
					component_persIdentity = createCompliantURI(persIdentity, instantiatedDef.getDisplayId(),"");
					component_displayId = instantiatedDef.getDisplayId();
					instantiatedComponents.add(instantiatedDef.getDisplayId());
				} else {
					//System.out.println("Repeated: " + instantiatedDef.getDisplayId());
					component_num++;
				}

				Component component = new Component(component_identity, access, instantiatedComponent);
				if (!persIdentity.equals("")) {
					component.setPersistentIdentity(component_persIdentity);
					component.setDisplayId(component_displayId);
					component.setVersion(version);
				}
				components.add(component);

				URI originalURI 		  = ((NestedDocument<QName>) namedProperty.getValue()).getIdentity();
				componentDefMap.put(originalURI, component_identity);
				sa.setComponent(component_identity);
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
			URI sc_identity    	= createCompliantURI(persIdentity,"sequenceConstraint" + ++sc_number,version);
			URI restrictionURI 	= RestrictionType.convertToURI(RestrictionType.PRECEDES);
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

			SequenceConstraint sc = null;
			if (compliant && !persIdentity.equals("")) {
				String subjectId = URIcompliance.extractDisplayId(subject);
				String objectId = URIcompliance.extractDisplayId(object);
				sc_identity = createCompliantURI(persIdentity,subjectId+"_cons_"+objectId,version);
				sc = new SequenceConstraint(sc_identity, restrictionURI, subject, object);
				sc.setPersistentIdentity(createCompliantURI(persIdentity,subjectId+"_cons_"+objectId,""));
				sc.setDisplayId(subjectId+"_cons_"+objectId);
				sc.setVersion(version);
			} else {
				sc = new SequenceConstraint(sc_identity, restrictionURI, subject, object);
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
		if (!components.isEmpty())
			c.setComponents(components);
		if (!sequenceAnnotations.isEmpty()) {
			for (SequenceAnnotation sa : sequenceAnnotations) {
				if (!dropObjectsWithDuplicateURIs || c.getSequenceAnnotation(sa.getIdentity())==null) {
					c.addSequenceAnnotation(sa);
				}
			}
		}
		if (!sequenceConstraints.isEmpty())
			c.setSequenceConstraints(sequenceConstraints);

		ComponentDefinition oldC = SBOLDoc.getComponentDefinition(identity);
		if (oldC == null) {
			SBOLDoc.addComponentDefinition(c);
		} else if (c.isSetWasDerivedFrom() && oldC.isSetWasDerivedFrom() &&
				!c.getWasDerivedFrom().equals(oldC.getWasDerivedFrom())) {
			Set<TopLevel> topLevels = SBOLDoc.getByWasDerivedFrom(c.getWasDerivedFrom());
			for (TopLevel topLevel : topLevels) {
				if (topLevel instanceof ComponentDefinition) {
					return (ComponentDefinition) topLevel;
				}
			}
			do {
				displayId = displayId + "_";
				identity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,version,typesInURI);
				persIdentity = createCompliantURI(URIPrefix,TopLevel.COMPONENT_DEFINITION,displayId,"",typesInURI).toString();
			} while (SBOLDoc.getComponentDefinition(identity)!=null);
			c = c.copy(URIPrefix, displayId, version);
			if(identity != componentDef.getIdentity())
				c.setWasDerivedFrom(componentDef.getIdentity());
			SBOLDoc.addComponentDefinition(c);
		} else if (dropObjectsWithDuplicateURIs) {
			return oldC;
		} else {
			if (!c.equals(oldC)) {
				throw new SBOLValidationException("sbol-10202",c);
			}
		}
		return c;
	}

	private static Sequence parseDnaSequenceV1(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel) throws SBOLValidationException
	{
		String elements    = null;
		String displayId   = null;
		String name   	   = null;
		String description = null;
		URI identity 	   = topLevel.getIdentity();
		URI persistentIdentity = topLevel.getIdentity();
		URI encoding 	   = Sequence.IUPAC_DNA;
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				if (URIPrefix != null)
				{
					identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", topLevel.getIdentity());
				}
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
		} else if (sequence.isSetWasDerivedFrom() && oldS.isSetWasDerivedFrom() &&
				!sequence.getWasDerivedFrom().equals(oldS.getWasDerivedFrom())) {
			Set<TopLevel> topLevels = SBOLDoc.getByWasDerivedFrom(sequence.getWasDerivedFrom());
			for (TopLevel top : topLevels) {
				if (top instanceof Sequence) {
					return (Sequence) top;
				}
			}
			do {
				displayId = displayId + "_";
				identity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,version,typesInURI);
				persistentIdentity = createCompliantURI(URIPrefix,TopLevel.SEQUENCE,displayId,"",typesInURI);
			} while (SBOLDoc.getSequence(identity)!=null);
			sequence.setIdentity(identity);
			sequence.setDisplayId(displayId);
			sequence.setPersistentIdentity(persistentIdentity);
			SBOLDoc.addSequence(sequence);
		} else if (dropObjectsWithDuplicateURIs) {
			return oldS;
		} else {
			if (!sequence.equals(oldS)) {
				throw new SBOLValidationException("sbol-10202",sequence);
			}
		}
		return sequence;
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
		displayId = URIcompliance.fixDisplayId(displayId);
		return displayId;
	}

	private static Collection parseCollectionV1(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel) throws SBOLValidationException, SBOLConversionException
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
				displayId = URIcompliance.fixDisplayId(displayId);
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
				throw new SBOLValidationException("sbol-10202",c);
			}
		}
		return c;
	}

	private static SequenceAnnotation parseSequenceAnnotationV1(
			SBOLDocument SBOLDoc, NestedDocument<QName> sequenceAnnotation,
			List<SBOLPair> precedePairs, String parentURI, int sa_num,
			Set<String> instantiatedComponents) throws SBOLValidationException, SBOLConversionException
	{
		Integer start 	 = null;
		Integer end 	 = null;
		String strand    = null;
		URI componentURI = null;
		URI identity 	 = sequenceAnnotation.getIdentity();
		String persIdentity = sequenceAnnotation.getIdentity().toString();
		List<Annotation> annotations = new ArrayList<>();

		if (URIPrefix != null)
		{
			persIdentity = createCompliantURI(parentURI,"annotation"+sa_num,"").toString();
			identity = createCompliantURI(parentURI,"annotation"+sa_num,version);
		}

		if (!sequenceAnnotation.getType().equals(Sbol1Terms.SequenceAnnotations.SequenceAnnotation))
		{
			throw new SBOLConversionException("QName has to be" + Sbol1Terms.SequenceAnnotations.SequenceAnnotation.toString());
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
		String componentDisplayId = URIcompliance.extractDisplayId(componentURI);
		String displayId = "annotation" + sa_num;
		if (compliant && componentDisplayId!=null && 
				!instantiatedComponents.contains(componentDisplayId)) {
			identity = createCompliantURI(parentURI,componentDisplayId+"_annotation",version);
			persIdentity = createCompliantURI(parentURI,componentDisplayId+"_annotation","").toString();
			displayId = componentDisplayId + "_annotation";
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
					try {
						location.setOrientation(OrientationType.convertToOrientationType(OrientationType.inline));
					} catch (SBOLValidationException e) {
						throw new SBOLValidationException("sbol-11002",location);
					}
				}
				else if (strand.equals("-"))
				{
					try {
						location.setOrientation(OrientationType.convertToOrientationType(OrientationType.reverseComplement));
					} catch (SBOLValidationException e) {
						throw new SBOLValidationException("sbol-11002",location);
					}
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
					try {
						location.setOrientation(OrientationType.convertToOrientationType(OrientationType.inline));
					} catch (SBOLValidationException e) {
						throw new SBOLValidationException("sbol-11002",location);
					}
				}
				else if (strand.equals("-"))
				{
					try {
						location.setOrientation(OrientationType.convertToOrientationType(OrientationType.reverseComplement));
					} catch (SBOLValidationException e) {
						throw new SBOLValidationException("sbol-11002",location);
					}
				}
			}
		}

		Set<Location> locations = new HashSet<>();
		locations.add(location);
		SequenceAnnotation s = new SequenceAnnotation(identity, locations);
		if(!persIdentity.equals("")) {
			s.setPersistentIdentity(URI.create(persIdentity));
			s.setDisplayId(displayId);
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
			SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom     = null;
		Set<URI> type 		   = new HashSet<>();
		Set<URI> roles 	  	   = new HashSet<>();
		Set<URI> structures	   = new HashSet<>();

		Set<Component> components 					 = new HashSet<>();
		List<Annotation> annotations 				 = new ArrayList<>();
		Set<SequenceAnnotation> sequenceAnnotations = new HashSet<>();
		Set<SequenceConstraint> sequenceConstraints = new HashSet<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.type))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10502", topLevel.getIdentity());
				}
				type.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.roles))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10507", topLevel.getIdentity());
				}
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10512", topLevel.getIdentity());
				}
				structures.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
				//structure = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",topLevel.getIdentity());
				}
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
		if (!structures.isEmpty())
			c.setSequences(structures);
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
				throw new SBOLValidationException("sbol-10202",c);
			}
		}
		return c;
	}

	private static SequenceConstraint parseSequenceConstraint(NestedDocument<QName> sequenceConstraint) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(sequenceConstraint.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(sequenceConstraint.getIdentity()));
		URI restriction  			 = null;
		URI subject 				 = null;
		URI object 					 = null;
		String version 				 = null;
		URI wasDerivedFrom 			 = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!sequenceConstraint.getType().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint)) {
			throw new SBOLValidationException("sbol-10524",sequenceConstraint.getIdentity());
		}
		for (NamedProperty<QName> namedProperty : sequenceConstraint.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", sequenceConstraint.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.restriction))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11407", sequenceConstraint.getIdentity());
				}
				restriction = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());

			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasSubject))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11402", sequenceConstraint.getIdentity());
				}
				subject = URI
						.create(((Literal<QName>) namedProperty.getValue())
								.getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasObject))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11404", sequenceConstraint.getIdentity());
				}
				object = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", sequenceConstraint.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", sequenceConstraint.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",sequenceConstraint.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", sequenceConstraint.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",sequenceConstraint.getIdentity());
				}
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

	private static SequenceAnnotation parseSequenceAnnotation(NestedDocument<QName> sequenceAnnotation, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(sequenceAnnotation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(sequenceAnnotation.getIdentity()));
		Location location 	   = null;
		URI componentURI 	   = null;
		String version   	   = null;
		URI wasDerivedFrom 	   = null;
		Set<URI> roles 	  	   = new HashSet<>();
		URI roleIntegration = null;
		Set<Location> locations = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		if (!sequenceAnnotation.getType().equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation)) {
			throw new SBOLValidationException("sbol-10521",sequenceAnnotation.getIdentity());
		}
		for (NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", sequenceAnnotation.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", sequenceAnnotation.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", sequenceAnnotation.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceAnnotation.roles))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					// TODO: need a proper validation error number
					throw new SBOLValidationException("TBD", sequenceAnnotation.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceAnnotation.roleIntegration))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					// TODO: need a proper validation error number
					throw new SBOLValidationException("TBD", sequenceAnnotation.getIdentity());
				}
				roleIntegration = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10904", sequenceAnnotation.getIdentity());
				}
				componentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", sequenceAnnotation.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", sequenceAnnotation.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",sequenceAnnotation.getIdentity());
				}
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
		if (!roles.isEmpty())
			s.setRoles(roles);
		if (roleIntegration != null)
			try {
				s.setRoleIntegration(RoleIntegrationType.convertToRoleIntegrationType(roleIntegration));
			} catch (SBOLValidationException e) {
				// TODO: need proper validation error
				throw new SBOLValidationException("TBD",s);
			}
		return s;
	}

	private static Location parseLocation(NestedDocument<QName> location) throws SBOLValidationException
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
			throw new SBOLValidationException("sbol-10902",location.getIdentity());
		}
		return l;

	}

	private static GenericLocation parseGenericLocation(NestedDocument<QName> typeGenLoc) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(typeGenLoc.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(typeGenLoc.getIdentity()));
		URI orientation 			 = null;
		String version        	     = null;
		URI wasDerivedFrom 			 = null;
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeGenLoc.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.GenericLocation.orientation))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11002", typeGenLoc.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", typeGenLoc.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", typeGenLoc.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",typeGenLoc.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", typeGenLoc.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", typeGenLoc.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",typeGenLoc.getIdentity());
				}
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
			try {
				gl.setOrientation(OrientationType.convertToOrientationType(orientation));
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-11002",gl);
			}
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

	private static Cut parseCut(NestedDocument<QName> typeCut) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(typeCut.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(typeCut.getIdentity()));
		Integer at 			   = null;
		URI orientation 	   = null;
		String version 		   = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeCut.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", typeCut.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", typeCut.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", typeCut.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",typeCut.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Cut.at))
			{
				String temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				//at 			= Integer.parseInt(temp);
				try{
					at = Integer.parseInt(temp);
				}
				catch (NumberFormatException e) {
					throw new SBOLValidationException("sbol-11202",typeCut.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Cut.orientation))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11002", typeCut.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", typeCut.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", typeCut.getIdentity());
				}
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
			try {
				c.setOrientation(OrientationType.convertToOrientationType(orientation));
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-11002",c);
			}
		if(version != null)
			c.setVersion(version);
		if (wasDerivedFrom != null)
			c.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	private static Location parseRange(NestedDocument<QName> typeRange) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(typeRange.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(typeRange.getIdentity()));
		Integer start 		   = null;
		Integer end 		   = null;
		URI orientation 	   = null;
		String version 		   = null;
		URI wasDerivedFrom     = null;
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeRange.getProperties())
		{
			String temp;
			if (namedProperty.getName().equals(Sbol2Terms.Range.start))
			{
				temp  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				//start = Integer.parseInt(temp);
				try{
					start = Integer.parseInt(temp);
				}
				catch (NumberFormatException e) {
					throw new SBOLValidationException("sbol-11102",typeRange.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", typeRange.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", typeRange.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", typeRange.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",typeRange.getIdentity());
				}

				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Range.end))
			{
				temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				//end  = Integer.parseInt(temp);
				try{
					end = Integer.parseInt(temp);
				}
				catch (NumberFormatException e) {
					throw new SBOLValidationException("sbol-11103",typeRange.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Range.orientation))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11002", typeRange.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", typeRange.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",typeRange.getIdentity());
				}
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
			try {
				r.setOrientation(OrientationType.convertToOrientationType(orientation));
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-11002",r);
			}
		if(version != null)
			r.setVersion(version);
		if (wasDerivedFrom != null)
			r.setWasDerivedFrom(wasDerivedFrom);
		if (!annotations.isEmpty())
			r.setAnnotations(annotations);
		return r;
	}

	private static Component parseComponent(NestedDocument<QName> component, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(component.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(component.getIdentity()));
		String version 		   = null;
		URI subComponentURI    = null;
		AccessType access 	   = null;
		URI wasDerivedFrom 	   = null;
		Set<URI> roles 	  	   = new HashSet<>();
		URI roleIntegration = null;
		List<Annotation> annotations = new ArrayList<>();
		Set<MapsTo> mapsTo 		 = new HashSet<>();

		if (!component.getType().equals(Sbol2Terms.Component.Component))
		{
			throw new SBOLValidationException("sbol-10519",component.getIdentity());
		}
		for (NamedProperty<QName> namedProperty : component.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", component.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", component.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", component.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Component.roles))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					// TODO: need a proper validation error number
					throw new SBOLValidationException("TBD", component.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Component.roleIntegration))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					// TODO: need a proper validation error number
					throw new SBOLValidationException("TBD", component.getIdentity());
				}
				roleIntegration = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10607", component.getIdentity());
				}
				String accessTypeStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				if (accessTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for access types should be http://sbols.org/v2#");
					accessTypeStr = accessTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
				}
				try {
					access = AccessType.convertToAccessType(URI.create(accessTypeStr));
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-10607",component.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					mapsTo.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),false));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mapsTo.add(parseMapsTo(nested.get(uri),false));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10602", component.getIdentity());
				}
				subComponentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", component.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",component.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", component.getIdentity());
				}
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
		if (!roles.isEmpty())
			c.setRoles(roles);
		if (roleIntegration != null)
			try {
				c.setRoleIntegration(RoleIntegrationType.convertToRoleIntegrationType(roleIntegration));
			} catch (SBOLValidationException e) {
				// TODO: need proper validation error
				throw new SBOLValidationException("TBD",c);
			}
		if (!mapsTo.isEmpty())
			c.setMapsTos(mapsTo);
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
			TopLevelDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom 	   = null;
		QName type 			   = topLevel.getType();

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Description.type)) {
				String typeStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				String nameSpace = URIcompliance.extractURIprefix(URI.create(typeStr))+"/";
				String localPart = URIcompliance.extractDisplayId(URI.create(typeStr));
				String prefix = SBOLDoc.getNamespace(URI.create(nameSpace)).getPrefix();
				type = new QName(nameSpace,localPart,prefix);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
				wasDerivedFrom = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		//		GenericTopLevel t = SBOLDoc.createGenericTopLevel(topLevel.getIdentity(), topLevel.getType());
		GenericTopLevel t = new GenericTopLevel(topLevel.getIdentity(), type);
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
				throw new SBOLValidationException("sbol-10202",t);
			}
		}
		return t;
	}

	private static Model parseModels(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.source))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10502", topLevel.getIdentity());
				}
				source = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.language))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10504", topLevel.getIdentity());
				}
				language = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.framework))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10508", topLevel.getIdentity());
				}
				framework = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
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
				throw new SBOLValidationException("sbol-10202",m);
			}
		}
		return m;
	}

	private static Collection parseCollections(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom 	   = null;

		Set<URI> members 			 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Collection.hasMembers))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-12102", topLevel.getIdentity());
				}
				members.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
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
				throw new SBOLValidationException("sbol-10202",c);
			}
		}
		return c;
	}

	private static ModuleDefinition parseModuleDefinition(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 	       = null;
		URI wasDerivedFrom 	   = null;
		Set<URI> roles 		   = new HashSet<>();
		Set<URI> models 	   = new HashSet<>();

		Set<FunctionalComponent> functionalComponents = new HashSet<>();
		Set<Interaction> interactions 				   = new HashSet<>();
		Set<Module> subModules 					   = new HashSet<>();
		List<Annotation> annotations 				   = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.roles))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11602", topLevel.getIdentity());
				}
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
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11607", topLevel.getIdentity());
				}
				models.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208",topLevel.getIdentity());
				}
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
				throw new SBOLValidationException("sbol-10202",moduleDefinition);
			}
		}
		return moduleDefinition;
	}

	private static Module parseModule(NestedDocument<QName> module, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(module.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(module.getIdentity()));
		String version 		   = null;
		URI definitionURI 	   = null;
		URI wasDerivedFrom 	   = null;
		Set<MapsTo> mappings 		 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		if (!module.getType().equals(Sbol2Terms.Module.Module))
		{
			throw new SBOLValidationException("sbol-11604",module.getIdentity());
		}
		for (NamedProperty<QName> namedProperty : module.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", module.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", module.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", module.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),true));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri),true));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapping))
			{
				System.out.println("Warning: tag should be sbol:mapTo, not sbol:mapping.");
				if (namedProperty.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),true));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri),true));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasDefinition))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11702", module.getIdentity());
				}
				definitionURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", module.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", module.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", module.getIdentity());
				}
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

	private static MapsTo parseMapsTo(NestedDocument<QName> mapsTo, boolean inModule) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(mapsTo.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(mapsTo.getIdentity()));
		String version 	  	 	  = null;
		URI remote 				  = null;
		RefinementType refinement = null;
		URI local 				  = null;
		URI wasDerivedFrom 		  = null;

		List<Annotation> annotations = new ArrayList<>();

		if (!mapsTo.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
			if (inModule) {
				throw new SBOLValidationException("sbol-11706",mapsTo.getIdentity());
			} else {
				throw new SBOLValidationException("sbol-10606",mapsTo.getIdentity());
			}
		}
		for (NamedProperty<QName> m : mapsTo.getProperties())
		{
			if (m.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", mapsTo.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", mapsTo.getIdentity());
				}
				displayId = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", mapsTo.getIdentity());
				}
				name = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",mapsTo.getIdentity());
				}
				description = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", mapsTo.getIdentity());
				}
				version  = ((Literal<QName>) m.getValue()).getValue().toString();
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.refinement))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10810", mapsTo.getIdentity());
				}
				String refinementStr = ((Literal<QName>) m.getValue()).getValue().toString();
				if (!refinementStr.startsWith("http://sbols.org/v2#")) {
					System.out.println("Warning: namespace for refinement types should be http://sbols.org/v2#");
					refinementStr = "http://sbols.org/v2#" + refinementStr;
				}
				try {
					refinement = RefinementType.convertToRefinementType(URI.create(refinementStr));
				} catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-10810",mapsTo.getIdentity());
				}
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.hasRemote))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10805", mapsTo.getIdentity());
				}
				remote = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.MapsTo.hasLocal))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10802", mapsTo.getIdentity());
				}
				local = URI.create(((Literal<QName>) m.getValue()).getValue().toString());
			}
			else if (m.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) m.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", mapsTo.getIdentity());
				}
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

	private static Interaction parseInteraction(NestedDocument<QName> interaction, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(interaction.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(interaction.getIdentity()));
		String version 		   = null;
		URI wasDerivedFrom	   = null;

		Set<URI> type 		   			   = new HashSet<>();
		Set<Participation> participations = new HashSet<>();
		List<Annotation> annotations 	   = new ArrayList<>();

		if (!interaction.getType().equals(Sbol2Terms.Interaction.Interaction)) {
			throw new SBOLValidationException("sbol-11605",interaction.getIdentity());
		}
		for (NamedProperty<QName> i : interaction.getProperties())
		{
			if (i.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", interaction.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) i.getValue()).getValue().toString());
			}
			else if (i.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", interaction.getIdentity());
				}
				version  = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", interaction.getIdentity());
				}
				displayId = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Interaction.type))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11902", interaction.getIdentity());
				}
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
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", interaction.getIdentity());
				}
				name = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", interaction.getIdentity());
				}
				description = ((Literal<QName>) i.getValue()).getValue().toString();
			}
			else if (i.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) i.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", interaction.getIdentity());
				}
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

	private static Participation parseParticipation(NestedDocument<QName> participation) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(participation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(participation.getIdentity()));
		String version 		   = null;
		Set<URI> roles 		   = new HashSet<>();
		URI participant        = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		if (!participation.getType().equals(Sbol2Terms.Participation.Participation))
		{
			throw new SBOLValidationException("sbol-11906",participation.getIdentity());
		}
		for (NamedProperty<QName> p : participation.getProperties())
		{
			if (p.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", participation.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else if (p.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", participation.getIdentity());
				}
				version  = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", participation.getIdentity());
				}
				displayId = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", participation.getIdentity());
				}
				name = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", participation.getIdentity());
				}
				description = ((Literal<QName>) p.getValue()).getValue().toString();
			}
			else if (p.getName().equals(Sbol2Terms.Participation.role))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-12004", participation.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) p.getValue()).getValue()
						.toString()));
			}
			else if (p.getName().equals(Sbol2Terms.Participation.hasParticipant))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-12002", participation.getIdentity());
				}
				participant = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else if (p.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) p.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", participation.getIdentity());
				}
				wasDerivedFrom = URI.create(((Literal<QName>) p.getValue()).getValue().toString());
			}
			else
			{
				annotations.add(new Annotation(p));
			}
		}

		Participation p = new Participation(participation.getIdentity(), participant, roles);
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

	private static FunctionalComponent parseFunctionalComponent(NestedDocument<QName> functionalComponent, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(functionalComponent.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(functionalComponent.getIdentity()));
		String version 			   = null;
		AccessType access 		   = null;
		DirectionType direction    = null;
		URI functionalComponentURI = null;
		URI wasDerivedFrom 		   = null;

		List<Annotation> annotations = new ArrayList<>();
		Set<MapsTo> mappings 		 = new HashSet<>();


		if (!functionalComponent.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent))
		{
			throw new SBOLValidationException("sbol-11606",functionalComponent.getIdentity());
		}
		for (NamedProperty<QName> f : functionalComponent.getProperties())
		{
			if (f.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", functionalComponent.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) f.getValue()).getValue().toString());
			}
			else if (f.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", functionalComponent.getIdentity());
				}
				version  = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", functionalComponent.getIdentity());
				}
				displayId = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10607", functionalComponent.getIdentity());
				}
				String accessTypeStr = ((Literal<QName>) f.getValue()).getValue().toString();
				if (accessTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for access types should be http://sbols.org/v2#");
					accessTypeStr = accessTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
				}
				try {
					access = AccessType.convertToAccessType(URI.create(accessTypeStr));
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-10607",functionalComponent.getIdentity());
				}
			}
			else if (f.getName().equals(Sbol2Terms.FunctionalComponent.direction))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-11802", functionalComponent.getIdentity());
				}
				String directionTypeStr = ((Literal<QName>) f.getValue()).getValue().toString();
				if (directionTypeStr.startsWith("http://www.sbolstandard.org/")) {
					System.out.println("Warning: namespace for direction types should be http://sbols.org/v2#");
					directionTypeStr = directionTypeStr.replace("http://www.sbolstandard.org/", "http://sbols.org/v2#");
					directionTypeStr = directionTypeStr.replace("input","in");
					directionTypeStr = directionTypeStr.replace("output","out");
				}
				try {
					direction = DirectionType.convertToDirectionType(URI.create(directionTypeStr));
				} catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-11802",functionalComponent.getIdentity());
				}
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.hasMapsTo))
			{
				if (f.getValue() instanceof NestedDocument) {
					mappings.add(parseMapsTo(((NestedDocument<QName>) f.getValue()),false));
				}
				else {
					URI uri = (URI) ((Literal<QName>)f.getValue()).getValue();
					mappings.add(parseMapsTo(nested.get(uri),false));
				}
			}
			else if (f.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10602", functionalComponent.getIdentity());
				}
				functionalComponentURI = URI.create(((Literal<QName>) f.getValue()).getValue().toString());
			}
			else if (f.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", functionalComponent.getIdentity());
				}
				name = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213", functionalComponent.getIdentity());
				}
				description = ((Literal<QName>) f.getValue()).getValue().toString();
			}
			else if (f.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) f.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", functionalComponent.getIdentity());
				}
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
			fc.setMapsTos(mappings);
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

	private static Sequence parseSequences(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null; //URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null; //URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		String elements 	   = null;
		URI encoding 		   = null;
		URI wasDerivedFrom 	   = null;
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.elements))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10402", topLevel.getIdentity());
				}
				elements = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.encoding))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10403", topLevel.getIdentity());
				}
				encoding = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				if (encoding.toString().equals("http://dx.doi.org/10.1021/bi00822a023")) {
					encoding = Sequence.IUPAC_DNA;
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String)) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
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
				throw new SBOLValidationException("sbol-10202",sequence);
			}
		}
		return sequence;
	}
}
