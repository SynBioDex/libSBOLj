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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
	 * Checks if reading should continue after encountering an SBOL validation exception.
	 * 
	 * @return {@code true} if it should continue, {@code false} otherwise
	 */
	public static boolean isKeepGoing() {
		return keepGoing;
	}

	/**
	 * Sets the value for the keepGoing flag to the given boolean value.
	 * <p>
	 * A {@code true} value means that reading will keep going after encountering an SBOL validation exception, 
	 * and a {@code false} value means otherwise.
	 * 
	 * @param keepGoing the boolean value for the keepGoing flag 
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
	 * 
	 * @return the error list that is used to store SBOL validation exceptions
	 */
	public static List<String> getErrors() {
		return errors;
	}

	/**
	 * Returns the number of errors in the error list. 
	 * 
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
	 * @return {@code true} if document is to be read as being compliant, {@code false} otherwise
	 */
	public static boolean isCompliant() {
		return compliant;
	}

	/**
	 * Sets the compliant flag to the given value.
	 * <p>
	 * A {@code true} value means that the SBOL document is to be read as compliant, 
	 * and a {@code false} value means otherwise.
	 *
	 * @param compliant the boolean value for the compliant flag
	 */
	public static void setCompliant(boolean compliant) {
		SBOLReader.compliant = compliant;
	}

	/**
	 * Sets the specified authority as the prefix.
	 *
	 *  @param URIprefix the given URI prefix
	 */
	public static void setURIPrefix(String URIprefix)
	{
		SBOLReader.URIPrefix = URIprefix;
	}

	/**
	 * Sets the URI prefix for this reader to {@code null}.
	 */
	public static void unsetURIPrefix()
	{
		SBOLReader.URIPrefix = null;
	}

	/**
	 * Sets the SBOL version for this reader. 
	 *
	 * @param version the given version
	 */
	public static void setVersion(String version)
	{
		SBOLReader.version = version;
	}

	/**
	 * Sets the value for the typesInURI flag.
	 * <p>
	 * A {@code true} value means that types are to be inserted into each top-level URI when it is created,
	 * and a {@code false} value means otherwise.
	 *
	 * @param typesInURI the boolean value for the typesInURI flag
	 */
	public static void setTypesInURI(boolean typesInURI)
	{
		SBOLReader.typesInURI = typesInURI;
	}

	/**
	 * Check if objects with duplicate URIs should be dropped.
	 *
	 * @return {@code true} if objects with duplicate URIs should be dropped, {@code false} otherwise
	 */
	public static boolean isDropObjectsWithDuplicateURIs() {
		return dropObjectsWithDuplicateURIs;
	}

	/**
	 * Sets the value of the dropObjectsWithDuplicateURIs flag.
	 * <p>
	 * A {@code true} value means that instances with duplicate URIs should be dropped, 
	 * and a {@code false} value means otherwise.
	 *
	 * @param dropObjectsWithDuplicateURIs the boolean value for the dropObjectsWithDuplicateURIs flag
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
	 * Sets the defaultsequenceEndocding flag to the given value.
	 * 
	 * @param defaultSequenceEncoding the given defaultSequenceEncoding URI
	 */
	public static void setDefaultSequenceEncoding(URI defaultSequenceEncoding) {
		SBOLReader.defaultSequenceEncoding = defaultSequenceEncoding;
	}

	/**
	 * @param document
	 * @return
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated:
	 * 10101, 10102.
	 */
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
	 * Takes in a given RDF file name and returns the SBOL version of the file.
	 *
	 * @param fileName a given RDF file name
	 * @return the SBOL version of the file
	 * @throws FileNotFoundException if file was not found.
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 10201.
	 * @throws SBOLConversionException if file is empty
	 */
	public static String getSBOLVersion(String fileName) throws FileNotFoundException, SBOLValidationException, SBOLConversionException
	{
		return getSBOLVersion(fileName,SBOLDocument.RDF);
	}

	/**
	 * Takes in a given file name and file type, and returns the SBOL version of the file.
	 *
	 * @param fileName the given file name
	 * @return the SBOL version of the file.
	 * @throws FileNotFoundException if file was not found.
	 * @throws SBOLValidationException if if an SBOL validation rule violation occurred in the following method:
	 * {@link #getSBOLVersion(InputStream, String)}.
	 * @throws SBOLConversionException if file is empty
	 */
	private static String getSBOLVersion(String fileName, String fileType) throws FileNotFoundException, SBOLValidationException, SBOLConversionException
	{
		FileInputStream stream     = new FileInputStream(new File(fileName));
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return getSBOLVersion(buffer,fileType);
	}

	/**
	 * Takes in the given RDF filename and converts the file to an SBOLDocument.
	 *
	 * @param fileName the name of the given RDF file
	 * @return the converted SBOLDocument
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 
	 * 10201, 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405, 
	 * 10501, 10502, 10503, 10504, 10507, 10508, 10512, 10513, 10519, 10522, 10526, 
	 * 10602, 10603, 10604, 10605, 10606, 10607, 
	 * 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10806, 10807, 10808, 10809, 10810, 10811, 
	 * 10901, 10902, 10904, 10905, 
	 * 11002, 11101, 11102, 11103, 11104, 
	 * 11201, 11202, 
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 11407, 11412, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11602, 11604, 11605, 11606, 11607, 11608, 11609, 
	 * 11701, 11702, 11703, 11704, 11705, 11706, 
	 * 11801, 11802, 
	 * 11901, 11902, 11906, 
	 * 12001, 12002, 12003, 12004, 
	 * 12101, 12102, 12103, 
	 * 12301, 12302.
	 * @throws SBOLConversionException see {@link SBOLConversionException#SBOLConversionException}
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
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(File, String)}.
	 * @throws SBOLConversionException if conversion fails
	 * @throws IOException see {@link IOException} 
	 */
	private static SBOLDocument read(String fileName,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		return read(new File(fileName),fileType);
	}

	/**
	 * Takes in a given RDF file and returns its SBOL version.
	 *
	 * @param file the given RDF file
	 * @return the SBOL version of the file
	 * @throws FileNotFoundException if file was not found.
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 10201.
	 * @throws SBOLConversionException if file is empty
	 */
	public static String getSBOLVersion(File file) throws FileNotFoundException, SBOLValidationException, SBOLConversionException
	{
		return getSBOLVersion(file,SBOLDocument.RDF);
	}

	/**
	 * Parses the given RDF file and stores its contents in an SBOLDocument object.
	 *
	 * @param file the given RDF file
	 * @return an SBOLDocument object that stores the RDF file information
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 
	 * 10201, 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405, 
	 * 10501, 10502, 10503, 10504, 10507, 10508, 10512, 10513, 10519, 10522, 10526, 
	 * 10602, 10603, 10604, 10605, 10606, 10607, 
	 * 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10806, 10807, 10808, 10809, 10810, 10811, 
	 * 10901, 10902, 10904, 10905, 
	 * 11002, 11101, 11102, 11103, 11104, 
	 * 11201, 11202, 
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 11407, 11412, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11602, 11604, 11605, 11606, 11607, 11608, 11609, 
	 * 11701, 11702, 11703, 11704, 11705, 11706, 
	 * 11801, 11802, 
	 * 11901, 11902, 11906, 
	 * 12001, 12002, 12003, 12004, 
	 * 12101, 12102, 12103, 
	 * 12301, 12302.
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
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(InputStream, String)}.
	 * @throws SBOLConversionException if file is empty
	 * @throws IOException see {@link IOException} 
	 */
	private static SBOLDocument read(File file,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return read(buffer,fileType);
	}

	/**
	 * Takes in a given file and file type, and returns the SBOL version of the file.
	 *
	 * @param file the given file
	 * @return the SBOL version of the file
	 * @throws FileNotFoundException if file was not found.
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #getSBOLVersion(InputStream, String)}.
	 * @throws SBOLConversionException 
	 */
	private static String getSBOLVersion(File file,String fileType) throws FileNotFoundException, SBOLValidationException, SBOLConversionException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return getSBOLVersion(buffer,fileType);
	}

	/**
	 * Takes in a given input stream and fie type, and returns the SBOL version of the file.
	 *
	 * @param in the given input stream
	 * @param fileType the given file type
	 * @return the SBOL version of the given file.
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any
	 * of the following methods:
	 * <ul>
	 * <li>{@link #readJSON(Reader)},</li>
	 * <li>{@link #readTurtle(Reader)},</li>
	 * <li>{@link #readRDF(Reader)}, or</li>
	 * <li>{@link #getSBOLVersion(DocumentRoot)}.</li>
	 * </ul>
	 * @throws SBOLConversionException if file is empty
	 */
	private static String getSBOLVersion(InputStream in,String fileType) throws SBOLValidationException, SBOLConversionException
	{
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString;
		try {
			inputStreamString = scanner.useDelimiter("\\A").next();
		} catch (NoSuchElementException e) {
			scanner.close();
			throw new SBOLConversionException("File is empty.");
		}
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
	 * @throws SBOLConversionException see {@link SBOLConversionException}
	 * @throws IOException see {@link IOException}
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 
	 * 10201, 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10220, 
	 * 10303, 10304, 10305, 
	 * 10401, 10402, 10403, 10405, 
	 * 10501, 10502, 10503, 10504, 10507, 10508, 10512, 10513, 10519, 10522, 10526, 
	 * 10602, 10603, 10604, 10605, 10606, 10607, 
	 * 10701, 
	 * 10801, 10802, 10803, 10804, 10805, 10806, 10807, 10808, 10809, 10810, 10811, 
	 * 10901, 10902, 10904, 10905, 
	 * 11002, 
	 * 11101, 11102, 11103, 11104, 
	 * 11201, 11202, 
	 * 11301, 
	 * 11401, 11402, 11403, 11404, 11405, 11406, 11407, 11412, 
	 * 11501, 11502, 11504, 11508, 
	 * 11601, 11602, 11604, 11605, 11606, 11607, 11608, 11609, 
	 * 11701, 11702, 11703, 11704, 11705, 11706, 
	 * 11801, 11802, 
	 * 11901, 11902, 11906, 
	 * 12001, 12002, 12003, 12004, 
	 * 12101, 12102, 12103, 
	 * 12301, 12302.
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
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #read(SBOLDocument, InputStream, String)}.
	 * @throws SBOLConversionException if file is empty
	 * @throws IOException see {@link IOException} 
	 */
	public static SBOLDocument read(InputStream in,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		SBOLDocument SBOLDoc     = new SBOLDocument();
		SBOLDoc.setCompliant(compliant);
		if (URIPrefix!=null) {
			SBOLDoc.setDefaultURIprefix(URIPrefix);
		}
		read(SBOLDoc,in,fileType);
		return SBOLDoc;
	}

	/**
	 * @param SBOLDoc
	 * @param in
	 * @param fileType
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>If {@link #keepGoing} was set to {@code false}, and an SBOL validation rule violation occurred in
	 * any of the following methods:
	 * 	<ul>
	 * 		<li>{@link FASTA#read(SBOLDocument, String, String, String, URI)},</li>
	 * 		<li>{@link GenBank#read(SBOLDocument, String, String)},</li>
	 * 		<li>{@link #readJSON(Reader)}, </li>
	 * 		<li>{@link #readRDF(Reader)}, </li>
	 * 		<li>{@link #readTurtle(Reader)}, </li>
	 * 		<li>{@link #getSBOLVersion(DocumentRoot)}, or</li>
	 * 		<li>{@link #readV1(SBOLDocument, DocumentRoot)}; or</li>
	 * 	</ul></li>
	 * <li>an SBOL validation rule violation occurred in {@link #readTopLevelDocs(SBOLDocument, DocumentRoot)}.</li>
	 * </ul>
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException
	 */
	static void read(SBOLDocument SBOLDoc,InputStream in,String fileType) throws SBOLValidationException, IOException, SBOLConversionException
	{
		compliant = SBOLDoc.isCompliant();
		Scanner scanner = new Scanner(in, "UTF-8");
		String inputStreamString;
		try {
			inputStreamString = scanner.useDelimiter("\\A").next();
		} catch (NoSuchElementException e) {
			scanner.close();
			throw new SBOLConversionException("File is empty.");
		}
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
				GenBank.read(SBOLDoc, inputStreamString, URIPrefix, version);
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
	 * Takes in a given RDF input stream and returns the SBOL version of the file.
	 *
	 * @param in a given RDF input stream
	 * @return the SBOL version of the file.
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10101, 10102, 10105, 10201.
	 * @throws SBOLConversionException if file is empty
	 */
	public static String getSBOLVersion(InputStream in) throws SBOLValidationException, SBOLConversionException
	{
		return getSBOLVersion(in,SBOLDocument.RDF);
	}

	/**
	 * @param SBOLDoc
	 * @param document
	 * @return
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #readTopLevelDocsV1(SBOLDocument, DocumentRoot)}.
	 * @throws SBOLConversionException
	 */
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

	/**
	 * @param stream
	 * @return
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10105.
	 */
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
			throw new SBOLValidationException("sbol-10105",e);
		}
		return StringifyQName.string2qname.mapDR(root);
	}

	/**
	 * @param reader
	 * @return
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 10105, 10201.
	 */
	private static DocumentRoot<QName> readRDF(Reader reader) throws SBOLValidationException
	{
		try {
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
			RdfIo rdfIo 			  = new RdfIo();
			return rdfIo.createIoReader(xmlReader).read();
		}
		catch (FactoryConfigurationError e) {
			throw new SBOLValidationException("sbol-10105",e);
		}
		catch (XMLStreamException e) {
			throw new SBOLValidationException("sbol-10105",e);
		}
		catch (CoreIoException e) {
			throw new SBOLValidationException("sbol-10105",e);
		}
		catch (ClassCastException e) {
			if (e.getMessage().contains("IdentifiableDocument")) {
				throw new SBOLValidationException("sbol-10201",e);
			}
			throw new SBOLValidationException("sbol-10105",e);
		}
		catch (IllegalArgumentException e) {
			if (e.getCause() instanceof URISyntaxException) {
				throw new SBOLValidationException("sbol-10201",e);
			}
			throw new SBOLValidationException("sbol-10105",e);
		}
	}

	/**
	 * @param reader
	 * @return
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10105.
	 */
	private static DocumentRoot<QName> readTurtle(Reader reader) throws SBOLValidationException
	{
		TurtleIo turtleIo = new TurtleIo();
		try {
			return turtleIo.createIoReader(reader).read();
		}
		catch (CoreIoException e) {
			throw new SBOLValidationException("sbol-10105",e);
		}
	}

	/**
	 * @param SBOLDoc
	 * @param document
	 * @throws SBOLValidationException If {@link #keepGoing} was set to {@code false}, and an SBOL validation rule violation occurred in
	 * any of the following methods:
	 * <ul>
	 * <li>{@link #parseDnaComponentV1(SBOLDocument, IdentifiableDocument)},</li>
	 * <li>{@link #parseDnaSequenceV1(SBOLDocument, IdentifiableDocument)},</li>
	 * <li>{@link #parseCollectionV1(SBOLDocument, IdentifiableDocument)}, or</li>
	 * <li>{@link #parseGenericTopLevel(SBOLDocument, TopLevelDocument)}.</li>
	 * </ul>
	 * @throws SBOLConversionException
	 */
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

	/**
	 *
	 * @param SBOLDoc
	 * @param document
	 * @throws SBOLValidationException if {@link #keepGoing} was set to {@code false}, and either of the following conditions is satisfied:
	 * <ul> 
	 * 	<li>the following SBOL validation rule was violated: 12302; or</li>
	 * 	<li>an SBOL validation rule violation occurred in any of the following methods:
	 * 		<ul>
	 * 			<li>{@link #parseCollections(SBOLDocument, TopLevelDocument)},</li>
	 * 			<li>{@link #parseModuleDefinition(SBOLDocument, TopLevelDocument, Map)},</li>
	 * 			<li>{@link #parseModels(SBOLDocument, TopLevelDocument)},</li>
	 * 			<li>{@link #parseSequences(SBOLDocument, TopLevelDocument)}, </li>
	 * 			<li>{@link #parseComponentDefinitions(SBOLDocument, TopLevelDocument, Map)}, or</li>
	 * 			<li>{@link #parseGenericTopLevel(SBOLDocument, TopLevelDocument)}.</li>
	 * 		</ul>
	 * 	</li>
	 * </ul>
	 */
	private static void readTopLevelDocs(SBOLDocument SBOLDoc, DocumentRoot<QName> document) throws SBOLValidationException
	{
		Map<URI, NestedDocument<QName>> nested = new HashMap<URI, NestedDocument<QName>>();
		List<TopLevelDocument<QName>> topLevels = new ArrayList<TopLevelDocument<QName>>();
		clearErrors();

		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments()) {

			if (topLevel.getType().equals(Sbol2Terms.Description.Description)) {
				if (topLevel.getPropertyValues(Sbol2Terms.Description.type).isEmpty()) {
					if (keepGoing) {
						errors.add(new SBOLValidationException("sbol-12302",topLevel.getIdentity()).getMessage());
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
					parseCollections(SBOLDoc, topLevel, nested);
				else if (topLevel.getType().equals(Sbol2Terms.ModuleDefinition.ModuleDefinition))
					parseModuleDefinition(SBOLDoc, topLevel, nested);
				else if (topLevel.getType().equals(Sbol2Terms.Model.Model))
					parseModels(SBOLDoc, topLevel);
				else if (topLevel.getType().equals(Sbol2Terms.Sequence.Sequence))
					parseSequences(SBOLDoc, topLevel);
				else if (topLevel.getType().equals(Sbol2Terms.ComponentDefinition.ComponentDefinition))
					parseComponentDefinitions(SBOLDoc, topLevel, nested);
				else if (topLevel.getType().equals(Sbol2Terms.CombinatorialDerivation.CombinatorialDerivation))
					parseCombinatorialDerivation(SBOLDoc, topLevel, nested);
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

	/**
	 * @param SBOLDoc
	 * @param componentDef
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>if an SBOL validation rule violation occurred in any of the following constructors or methods:
	 * 	<ul>
	 * 		<li>{@link URIcompliance#createCompliantURI(String, String, String, String, boolean)},</li>
	 * 		<li>{@link #parseSequenceAnnotationV1(SBOLDocument, NestedDocument, List, String, int, Set)},</li>
	 * 		<li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 * 		<li>{@link Component#Component(URI, AccessType, URI)},</li>
	 * 		<li>{@link Component#setDisplayId(String)}, </li>
	 * 		<li>{@link Component#setVersion(String)}</li>
	 * 		<li>{@link SequenceAnnotation#setComponent(URI)}, </li>
	 * 		<li>{@link #parseDnaSequenceV1(SBOLDocument, IdentifiableDocument)}</li>
	 * 		<li>{@link RestrictionType#convertToURI(RestrictionType)},</li>
	 * 		<li>{@link SequenceConstraint#SequenceConstraint(URI, URI, URI, URI)},</li>
	 * 		<li>{@link SequenceConstraint#setDisplayId(String)},</li>
	 * 		<li>{@link SequenceConstraint#setVersion(String)},</li>
	 * 		<li>{@link ComponentDefinition#ComponentDefinition(URI, Set)},</li>
	 * 		<li>{@link ComponentDefinition#setVersion(String)},</li>
	 * 		<li>{@link ComponentDefinition#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)},</li>
	 * 		<li>{@link ComponentDefinition#setComponents(Set)}</li>
	 * 		<li>{@link ComponentDefinition#setSequenceConstraints(Set)}</li>
	 * 		<li>{@link ComponentDefinition#addSequence(URI)}</li>
	 * 		<li>{@link ComponentDefinition#addSequenceAnnotation(SequenceAnnotation)},</li>
	 * 		<li>{@link SBOLDocument#addComponentDefinition(ComponentDefinition)}, or</li>
	 * 		<li>{@link ComponentDefinition#copy(String, String, String)}; or</li>
	 * 	</ul> 
	 * </li>
	 * <li>the following SBOL validation rule was violated: 10202.</li>
	 * </ul>
	 * @throws SBOLConversionException
	 */
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
		type.add(SequenceOntology.LINEAR);

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
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", componentDef.getIdentity());
				}
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
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", componentDef.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", componentDef.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.type))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10507", componentDef.getIdentity());
				}
				URI convertedSO = SequenceOntology.convertSeqOntologyV1(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				roles.add(convertedSO);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.annotations))
			{
				if (namedProperty.getValue() instanceof IdentifiableDocument) {
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
				} else {
					throw new SBOLConversionException("SequenceAnnotation must be nested in SBOL1.");
				}
			}
			else if (namedProperty.getName().equals(Sbol1Terms.DNAComponent.dnaSequence))
			{
				if (seq_identity != null) {
					throw new SBOLValidationException("sbol-10512", componentDef.getIdentity());
				}
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-10512", componentDef.getIdentity());
					}
					seq_identity =  URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				} else {
					seq_identity = parseDnaSequenceV1(SBOLDoc,
							(NestedDocument<QName>) namedProperty.getValue()).getIdentity();
				}
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
			c.addWasDerivedFrom(componentDef.getIdentity());
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

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>if an SBOL validation rule violation occurred in any of the following constructors or methods:
	 * 	<ul>
	 * 		<li>{@link URIcompliance#createCompliantURI(String, String, String, String, boolean)},</li>
	 * 		<li>{@link Sequence#Sequence(URI, String, URI)},</li>
	 * 		<li>{@link Sequence#setVersion(String)},</li>
	 * 		<li>{@link Sequence#setWasDerivedFrom(URI)},</li>
	 * 		<li>{@link Sequence#setDisplayId(String)},</li>
	 * 		<li>{@link Identified#setAnnotations(List)},</li>
	 * 		<li>{@link Sequence#setIdentity(URI)}, or </li>
	 * 		<li>{@link SBOLDocument#addSequence(Sequence)}; or</li>
	 * 	</ul> 
	 * </li>
	 * <li>any of the following SBOL validation rules was violated: 10202, 10204, 10212, 10213.</li>
	 * </ul>
	 */
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
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
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
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
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

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLConversionException
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>if an SBOL validation rule violation occurred in any of the following constructors or methods:
	 * 	<ul>
	 * 		<li>{@link URIcompliance#createCompliantURI(String, String, String, String, boolean)},</li>
	 * 		<li>{@link Collection#Collection(URI)},</li>
	 * 		<li>{@link Collection#setVersion(String)},</li>
	 * 		<li>{@link Collection#setWasDerivedFrom(URI)},</li>
	 * 		<li>{@link Collection#setDisplayId(String)},</li>
	 * 		<li>{@link Collection#setMembers(Set)},</li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addCollection(Collection)}; or</li>
	 * 	</ul> 
	 * </li>
	 * <li>the following SBOL validation rule was violated: 10202.</li>
	 * </ul>
	 */
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
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
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
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.Collection.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.Collection.component))
			{
				if (namedProperty.getValue() instanceof Literal) {
					members.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
				} else {
					members.add(parseDnaComponentV1(SBOLDoc,
							(NestedDocument<QName>) namedProperty.getValue()).getIdentity());
				}
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
			c.addWasDerivedFrom(topLevel.getIdentity());
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

	/**
	 * @param SBOLDoc
	 * @param sequenceAnnotation
	 * @param precedePairs
	 * @param parentURI
	 * @param sa_num
	 * @param instantiatedComponents
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>if an SBOL validation rule violation occurred in any of the following constructors or methods:
	 * 	<ul>
	 * 		<li>{@link URIcompliance#createCompliantURI(String, String, String)}, </li>
	 * 		<li>{@link #parseDnaComponentV1(SBOLDocument, IdentifiableDocument)}, </li>
	 * 		<li>{@link Range#Range(URI, int, int)}, </li>
	 * 		<li>{@link Range#setDisplayId(String)}, </li>
	 * 		<li>{@link Range#setVersion(String)}, </li>
	 * 		<li>{@link GenericLocation#GenericLocation(URI)}, </li>
	 * 		<li>{@link SequenceAnnotation#SequenceAnnotation(URI, Set)}, </li>
	 * 		<li>{@link SequenceAnnotation#setDisplayId(String)}, </li>
	 * 		<li>{@link SequenceAnnotation#setVersion(String)}, </li>
	 * 		<li>{@link SequenceAnnotation#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link SequenceAnnotation#setComponent(URI)}, or </li>
	 * 		<li>{@link SequenceAnnotation#setAnnotations(List)}; or</li>
	 * 	</ul> 
	 * </li>
	 * <li>the following SBOL validation rule was violated: 11002.</li>
	 * </ul>
	 * @throws SBOLConversionException
	 */
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
				if (!(namedProperty.getValue() instanceof Literal) || start != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11102",sequenceAnnotation.getIdentity());
				}
				String temp = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				start = Integer.parseInt(temp);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.bioEnd))
			{
				if (!(namedProperty.getValue() instanceof Literal) || end != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11103",sequenceAnnotation.getIdentity());
				}
				String temp2 = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				end = Integer.parseInt(temp2);
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.strand))
			{
				if (!(namedProperty.getValue() instanceof Literal) || strand != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11002",sequenceAnnotation.getIdentity());
				}
				strand = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.subComponent))
			{
				if (componentURI != null) {
					throw new SBOLValidationException("sbol-10904", sequenceAnnotation.getIdentity());
				}
				if (namedProperty.getValue() instanceof NestedDocument) {
					componentURI = parseDnaComponentV1(SBOLDoc,
							(NestedDocument<QName>) namedProperty.getValue()).getIdentity();
				} else {
					if (!(namedProperty.getValue() instanceof Literal) ||
							(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
						throw new SBOLValidationException("sbol-10904", sequenceAnnotation.getIdentity());
					}
					componentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				}
			}
			else if (namedProperty.getName().equals(Sbol1Terms.SequenceAnnotations.precedes))
			{
				URI left 	  = sequenceAnnotation.getIdentity();
				URI right	  = null;
				if (namedProperty.getValue() instanceof NestedDocument) {
					// TODO: need to check if ++sa_num here okay
					right = parseSequenceAnnotationV1(SBOLDoc,
							(NestedDocument<QName>) namedProperty.getValue(), precedePairs, parentURI, ++sa_num, instantiatedComponents).getIdentity();
				} else {
					if (!(namedProperty.getValue() instanceof Literal) ||
							(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
						throw new SBOLValidationException("sbol-11404", sequenceAnnotation.getIdentity());
					}
					right 	  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				}
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
					location.setOrientation(OrientationType.INLINE);
				}
				else if (strand.equals("-"))
				{
					location.setOrientation(OrientationType.REVERSECOMPLEMENT);
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
					location.setOrientation(OrientationType.INLINE);
				}
				else if (strand.equals("-"))
				{
					location.setOrientation(OrientationType.REVERSECOMPLEMENT);
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
			s.addWasDerivedFrom(sequenceAnnotation.getIdentity());
		if (componentURI != null)
			s.setComponent(componentURI);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);

		return s;
	}

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10502, 10507, 10512, or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseComponent(NestedDocument, Map)},</li>
	 * 		<li>{@link #parseSequenceAnnotation(NestedDocument, Map)},</li>
	 * 		<li>{@link ComponentDefinition#ComponentDefinition(URI, Set)}, </li>
	 * 		<li>{@link ComponentDefinition#setDisplayId(String)}, </li>
	 * 		<li>{@link ComponentDefinition#setVersion(String)}, </li>
	 * 		<li>{@link ComponentDefinition#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)},</li>
	 * 		<li>{@link ComponentDefinition#setComponents(Set)},</li>
	 * 		<li>{@link ComponentDefinition#setSequenceAnnotations(Set)},</li>
	 * 		<li>{@link ComponentDefinition#setSequenceConstraints(Set)}, or</li>
	 * 		<li>{@link SBOLDocument#addComponentDefinition(ComponentDefinition)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	private static ComponentDefinition parseComponentDefinitions(SBOLDocument SBOLDoc, 
			IdentifiableDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
		Set<URI> type 		   = new HashSet<>();
		Set<URI> roles 	  	   = new HashSet<>();
		Set<URI> sequences	   = new HashSet<>();

		Set<Component> components 					 = new HashSet<>();
		List<Annotation> annotations 				 = new ArrayList<>();
		Set<SequenceAnnotation> sequenceAnnotations = new HashSet<>();
		Set<SequenceConstraint> sequenceConstraints = new HashSet<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.type))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10502", topLevel.getIdentity());
				}
				type.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.roles))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10507", topLevel.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasComponent)||
					namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSubComponent))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Component.Component)) {
						throw new SBOLValidationException("sbol-10519",topLevel.getIdentity());
					}
					components.add(parseComponent(SBOLDoc,((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Component.Component)) {
						throw new SBOLValidationException("sbol-10519",topLevel.getIdentity());
					}
					components.add(parseComponent(SBOLDoc,nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequence))
			{
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-10512", topLevel.getIdentity());
					}
					sequences.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
				}
				else if (namedProperty.getValue() instanceof IdentifiableDocument) {
					if (((IdentifiableDocument<QName>)namedProperty).getType().equals(Sbol2Terms.Sequence.Sequence)) {
						Sequence sequence = parseSequences(SBOLDoc,(IdentifiableDocument<QName>)namedProperty.getValue());
						sequences.add(sequence.getIdentity());
					} else {
						throw new SBOLValidationException("sbol-10512", topLevel.getIdentity());
					}
				}
				else {
					throw new SBOLValidationException("sbol-10512", topLevel.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation)) {
						throw new SBOLValidationException("sbol-10521",topLevel.getIdentity());
					}
					sequenceAnnotations.add(parseSequenceAnnotation(((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.SequenceAnnotation.SequenceAnnotation)) {
						throw new SBOLValidationException("sbol-10521",topLevel.getIdentity());
					}
					sequenceAnnotations.add(parseSequenceAnnotation(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceConstraints))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint)) {
						throw new SBOLValidationException("sbol-10524",topLevel.getIdentity());
					}
					sequenceConstraints.add(parseSequenceConstraint(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.SequenceConstraint.SequenceConstraint)) {
						throw new SBOLValidationException("sbol-10524",topLevel.getIdentity());
					}
					sequenceConstraints.add(parseSequenceConstraint(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		if (!sequences.isEmpty())
			c.setSequences(sequences);
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
		c.setWasDerivedFroms(wasDerivedFroms);

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
	
	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @param nested
	 * @return
	 */
	private static CombinatorialDerivation parseCombinatorialDerivation(SBOLDocument SBOLDoc, 
			IdentifiableDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		URI template 		   = null;
		String version 		   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();

		Set<VariableComponent> variableComponents    = new HashSet<>();
		List<Annotation> annotations 				 = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.CombinatorialDerivation.template))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-XXXXX", topLevel.getIdentity());
				}
				template  = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.CombinatorialDerivation.variableComponents))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					
					if (nestedDocument.getType() == null || 
							!nestedDocument.getType().equals(Sbol2Terms.VariableComponent.VariableComponent)) {
						throw new SBOLValidationException("sbol-XXXXX",topLevel.getIdentity());
					}
					variableComponents.add(parseVariableComponent(SBOLDoc,((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument == null || nestedDocument.getType() == null || 
							!nestedDocument.getType().equals(Sbol2Terms.VariableComponent.VariableComponent)) {
						throw new SBOLValidationException("sbol-XXXXX",topLevel.getIdentity());
					}
					variableComponents.add(parseVariableComponent(SBOLDoc,nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
			}
		}

		CombinatorialDerivation c = new CombinatorialDerivation(topLevel.getIdentity(), template);

		if (displayId != null)
			c.setDisplayId(displayId);
		if (persistentIdentity != null)
			c.setPersistentIdentity(persistentIdentity);
		if (name != null)
			c.setName(name);
		if (!variableComponents.isEmpty()) 
			c.setVariableComponents(variableComponents);
		if (description != null)
			c.setDescription(description);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		if (version != null)
			c.setVersion(version);
		c.setWasDerivedFroms(wasDerivedFroms);

		CombinatorialDerivation oldC = SBOLDoc.getCombinatorialDerivation(topLevel.getIdentity());
		if (oldC == null) {
			SBOLDoc.addCombinatorialDerivation(c);
		} else {
			if (!c.equals(oldC)) {
				throw new SBOLValidationException("sbol-10202", c);
			}
		}
		return c;
	}

	private static VariableComponent parseVariableComponent(SBOLDocument sBOLDoc, NestedDocument<QName> nestedDocument,
			Map<URI, NestedDocument<QName>> nested) {
		// TODO Auto-generated method stub
		return null;
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
		Set<URI> wasDerivedFroms	 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : sequenceConstraint.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", sequenceConstraint.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.restriction))
			{
				if (!(namedProperty.getValue() instanceof Literal) || restriction != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11407", sequenceConstraint.getIdentity());
				}
				restriction = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());

			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasSubject))
			{
				if (!(namedProperty.getValue() instanceof Literal) || subject != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11402", sequenceConstraint.getIdentity());
				}
				subject = URI
						.create(((Literal<QName>) namedProperty.getValue())
								.getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasObject))
			{
				if (!(namedProperty.getValue() instanceof Literal) || object != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11404", sequenceConstraint.getIdentity());
				}
				object = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", sequenceConstraint.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", sequenceConstraint.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",sequenceConstraint.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", sequenceConstraint.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",sequenceConstraint.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		s.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		return s;
	}

	/**
	 * @param sequenceAnnotation
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10203, 10204, 10206, 10208, 10212, 10213, 
	 * 10512,
	 * 10904; or 
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseLocation(NestedDocument)},</li>
	 * 		<li>{@link SequenceAnnotation#SequenceAnnotation(URI, Set)},</li>
	 * 		<li>{@link SequenceAnnotation#setDisplayId(String)},</li>
	 * 		<li>{@link SequenceAnnotation#setVersion(String)},</li>
	 * 		<li>{@link SequenceAnnotation#setComponent(URI)},</li>
	 * 		<li>{@link SequenceAnnotation#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static SequenceAnnotation parseSequenceAnnotation(NestedDocument<QName> sequenceAnnotation, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(sequenceAnnotation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(sequenceAnnotation.getIdentity()));
		Location location 	   = null;
		URI componentURI 	   = null;
		String version   	   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
		Set<URI> roles 	  	   = new HashSet<>();
		Set<Location> locations = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", sequenceAnnotation.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", sequenceAnnotation.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", sequenceAnnotation.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceAnnotation.roles))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10906", sequenceAnnotation.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Location.Location))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!(nestedDocument.getType().equals(Sbol2Terms.Range.Range) ||
									nestedDocument.getType().equals(Sbol2Terms.Cut.Cut) ||
									nestedDocument.getType().equals(Sbol2Terms.GenericLocation.GenericLocation))) {
						throw new SBOLValidationException("sbol-10902",sequenceAnnotation.getIdentity());
					}
					location = parseLocation((NestedDocument<QName>) namedProperty.getValue());
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!(nestedDocument.getType().equals(Sbol2Terms.Range.Range) ||
									nestedDocument.getType().equals(Sbol2Terms.Cut.Cut) ||
									nestedDocument.getType().equals(Sbol2Terms.GenericLocation.GenericLocation))) {
						throw new SBOLValidationException("sbol-10902",sequenceAnnotation.getIdentity());
					}
					location = parseLocation(nested.get(uri));
				}
				locations.add(location);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.SequenceAnnotation.hasComponent))
			{
				if (!(namedProperty.getValue() instanceof Literal) || componentURI != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10904", sequenceAnnotation.getIdentity());
				}
				componentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", sequenceAnnotation.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", sequenceAnnotation.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",sequenceAnnotation.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		s.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		if (!roles.isEmpty())
			s.setRoles(roles);
//		if (roleIntegration != null)
//			try {
//				s.setRoleIntegration(RoleIntegrationType.convertToRoleIntegrationType(roleIntegration));
//			} catch (SBOLValidationException e) {
//				throw new SBOLValidationException("sbol-10912",s);
//			}
		return s;
	}

	/**
	 * @param location
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>the following SBOL validation rules was violated: 10902
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseRange(NestedDocument)},</li>
	 * 		<li>{@link #parseCut(NestedDocument)}, or</li>
	 * 		<li>{@link #parseGenericLocation(NestedDocument)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
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
		return l;

	}

	/**
	 * @param typeGenLoc
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10203, 10204, 10206, 10208, 10212, 10213, 
	 * 11002; or  
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link GenericLocation#GenericLocation(URI)},</li>
	 * 		<li>{@link GenericLocation#setDisplayId(String)},</li>
	 * 		<li>{@link GenericLocation#setVersion(String)},</li>	
	 * 		<li>{@link GenericLocation#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static GenericLocation parseGenericLocation(NestedDocument<QName> typeGenLoc) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(typeGenLoc.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(typeGenLoc.getIdentity()));
		URI orientation 			 = null;
		String version        	     = null;
		Set<URI> wasDerivedFroms	 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeGenLoc.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.GenericLocation.orientation)||
					namedProperty.getName().equals(Sbol2Terms.GenericLocation.Orientation))
			{
				if (!(namedProperty.getValue() instanceof Literal) || orientation != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11002", typeGenLoc.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", typeGenLoc.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", typeGenLoc.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",typeGenLoc.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", typeGenLoc.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", typeGenLoc.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",typeGenLoc.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		gl.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			gl.setAnnotations(annotations);

		return gl;
	}

	/**
	 * @param typeCut
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10203, 10204, 10206, 10208, 10212, 10213,
	 * 11002, 11202; or 
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Cut#Cut(URI, int)},</li>
	 * 		<li>{@link Cut#setDisplayId(String)},</li>
	 * 		<li>{@link Cut#setVersion(String)},</li>
	 * 		<li>{@link Cut#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static Cut parseCut(NestedDocument<QName> typeCut) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(typeCut.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(typeCut.getIdentity()));
		Integer at 			   = null;
		URI orientation 	   = null;
		String version 		   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeCut.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", typeCut.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", typeCut.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", typeCut.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",typeCut.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Cut.at))
			{
				if (!(namedProperty.getValue() instanceof Literal) || at != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11202",typeCut.getIdentity());
				}
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
				if (!(namedProperty.getValue() instanceof Literal) || orientation != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11002", typeCut.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", typeCut.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", typeCut.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		c.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	/**
	 * @param typeRange
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10201, 10203, 10204, 10206, 10208, 10212, 10213, 11002, 11102, 11103; or
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Range#Range(URI, int, int)},</li>
	 * 		<li>{@link Range#setDisplayId(String)},</li>
	 * 		<li>{@link Range#setVersion(String)},</li>
	 * 		<li>{@link Range#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
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
		Set<URI> wasDerivedFroms = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : typeRange.getProperties())
		{
			String temp;
			if (namedProperty.getName().equals(Sbol2Terms.Range.start))
			{
				if (!(namedProperty.getValue() instanceof Literal) || start != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11102",typeRange.getIdentity());
				}
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
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", typeRange.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", typeRange.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", typeRange.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",typeRange.getIdentity());
				}

				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Range.end))
			{
				if (!(namedProperty.getValue() instanceof Literal) || end != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-11103",typeRange.getIdentity());
				}
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
				if (!(namedProperty.getValue() instanceof Literal) || orientation != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11002", typeRange.getIdentity());
				}
				orientation = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", typeRange.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",typeRange.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		r.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			r.setAnnotations(annotations);
		return r;
	}

	/**
	 * @param component
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10203, 10204, 10206, 10208, 10212, 10213, 10519, 10602, 10607; or 
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseMapsTo(NestedDocument, boolean)},</li>
	 * 		<li>{@link Component#Component(URI, AccessType, URI)},</li>
	 * 		<li>{@link Component#setVersion(String)},</li>
	 * 		<li>{@link Component#setDisplayId(String)},</li>
	 * 		<li>{@link Component#setAccess(AccessType)},</li>
	 * 		<li>{@link Component#setMapsTos(Set)},</li>
	 * 		<li>{@link Component#setDefinition(URI)},</li>
	 * 		<li>{@link Component#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>	
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	private static Component parseComponent(SBOLDocument SBOLDoc, NestedDocument<QName> component, 
			Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(component.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(component.getIdentity()));
		String version 		   = null;
		URI subComponentURI    = null;
		AccessType access 	   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
		Set<URI> roles 	  	   = new HashSet<>();
		URI roleIntegration = null;
		List<Annotation> annotations = new ArrayList<>();
		Set<MapsTo> mapsTo 		 = new HashSet<>();

		for (NamedProperty<QName> namedProperty : component.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", component.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", component.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", component.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Component.roles))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10702", component.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Component.roleIntegration))
			{
				if (!(namedProperty.getValue() instanceof Literal) || roleIntegration != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10708", component.getIdentity());
				}
				roleIntegration = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				if (!(namedProperty.getValue() instanceof Literal) || access != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
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
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-10606",component.getIdentity());
					}
					mapsTo.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),false));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-10606",component.getIdentity());
					}
					mapsTo.add(parseMapsTo(nested.get(uri),false));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				if (subComponentURI != null) {
					throw new SBOLValidationException("sbol-10602", component.getIdentity());
				}
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-10602", component.getIdentity());
					}
					subComponentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				}
				else if (namedProperty.getValue() instanceof IdentifiableDocument) {
					if (((IdentifiableDocument<QName>)namedProperty).getType().equals(Sbol2Terms.ComponentDefinition.ComponentDefinition)) {
						ComponentDefinition componentDefinition = parseComponentDefinitions(SBOLDoc,
								(IdentifiableDocument<QName>)namedProperty.getValue(),nested);
						subComponentURI = componentDefinition.getIdentity();
					} else {
						throw new SBOLValidationException("sbol-10602", component.getIdentity());
					}
				}
				else {
					throw new SBOLValidationException("sbol-10602", component.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", component.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",component.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", component.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		if (roleIntegration != null)
			try {
				c.setRoleIntegration(RoleIntegrationType.convertToRoleIntegrationType(roleIntegration));
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-10708",c);
			}
		if (!roles.isEmpty())
			c.setRoles(roles);
		if (!mapsTo.isEmpty())
			c.setMapsTos(mapsTo);
		if (subComponentURI != null)
			c.setDefinition(subComponentURI);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		c.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 12102; or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link GenericTopLevel#GenericTopLevel(URI, QName)}, </li>
	 * 		<li>{@link GenericTopLevel#setDisplayId(String)}, </li>
	 * 		<li>{@link GenericTopLevel#setVersion(String)}, </li>
	 * 		<li>{@link GenericTopLevel#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addGenericTopLevel(GenericTopLevel)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static GenericTopLevel parseGenericTopLevel(SBOLDocument SBOLDoc,
			IdentifiableDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
		QName type 			   = topLevel.getType();

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Description.type)) {
				String typeStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
				String nameSpace = URIcompliance.extractNamespace(URI.create(typeStr));
				String localPart = URIcompliance.extractDisplayId(URI.create(typeStr));
				String prefix = null;
				if (nameSpace == null) {
					if (typeStr.lastIndexOf('/') > typeStr.lastIndexOf('#')) {
						if (typeStr.lastIndexOf('/') > typeStr.lastIndexOf(':')) {
							nameSpace = typeStr.substring(0, typeStr.lastIndexOf('/')+1);
							localPart = typeStr.substring(typeStr.lastIndexOf('/')+1);
						} else {
							nameSpace = typeStr.substring(0, typeStr.lastIndexOf(':')+1);
							localPart = typeStr.substring(typeStr.lastIndexOf(':')+1);
						}
					} else if (typeStr.lastIndexOf('#') > typeStr.lastIndexOf(':')) {
						nameSpace = typeStr.substring(0, typeStr.lastIndexOf('#')+1);
						localPart = typeStr.substring(typeStr.lastIndexOf('#')+1);
					} else {
						nameSpace = typeStr.substring(0, typeStr.lastIndexOf(':')+1);
						localPart = typeStr.substring(typeStr.lastIndexOf(':')+1);
					}
					prefix = SBOLDoc.getNamespacePrefix(URI.create(nameSpace));
					if (prefix == null) {
						prefix = "ns0";
						int prefixCnt = 0;
						while (SBOLDoc.getNamespace(prefix) != null) {
							prefixCnt++;
							prefix = "ns" + prefixCnt;
						}
						SBOLDoc.addNamespace(new QName(nameSpace,localPart,prefix));
					}
				} else {
					prefix = SBOLDoc.getNamespacePrefix(URI.create(nameSpace));
				}
				type = new QName(nameSpace,localPart,prefix);
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		t.setWasDerivedFroms(wasDerivedFroms);
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

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10502, 10504, 10508; or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Model#Model(URI, URI, URI, URI)}, </li>
	 * 		<li>{@link Model#setDisplayId(String)}, </li>
	 * 		<li>{@link Model#setVersion(String)}, </li>
	 * 		<li>{@link Model#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addModel(Model)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static Model parseModels(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		URI source 			   = null;
		URI language 		   = null;
		URI framework 	 	   = null;
		Set<URI> wasDerivedFroms = new HashSet<>();

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.source))
			{
				if (!(namedProperty.getValue() instanceof Literal) || source != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10502", topLevel.getIdentity());
				}
				source = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.language))
			{
				if (!(namedProperty.getValue() instanceof Literal) || language != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10504", topLevel.getIdentity());
				}
				language = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Model.framework))
			{
				if (!(namedProperty.getValue() instanceof Literal) || framework != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10508", topLevel.getIdentity());
				}
				framework = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		m.setWasDerivedFroms(wasDerivedFroms);
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

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 12102; or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Collection#Collection(URI)}, </li>
	 * 		<li>{@link Collection#setDisplayId(String)}, </li>
	 * 		<li>{@link Collection#setVersion(String)}, </li>
	 * 		<li>{@link Collection#setMembers(Set)}, </li>
	 * 		<li>{@link Collection#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addCollection(Collection)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Collection parseCollections(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel,
			Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		Set<URI> wasDerivedFroms	 = new HashSet<>();
		Set<URI> members 			 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Collection.hasMembers))
			{
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-12102", topLevel.getIdentity());
					}
					members.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
				} else if (namedProperty.getValue() instanceof NestedDocument) {
					if (((IdentifiableDocument)namedProperty).getType().equals(Sbol2Terms.Collection.Collection))
						parseCollections(SBOLDoc, (IdentifiableDocument)namedProperty, nested);
					else if (((IdentifiableDocument)namedProperty).equals(Sbol2Terms.ModuleDefinition.ModuleDefinition))
						parseModuleDefinition(SBOLDoc, (IdentifiableDocument)namedProperty, nested);
					else if (((IdentifiableDocument)namedProperty).equals(Sbol2Terms.Model.Model))
						parseModels(SBOLDoc, (IdentifiableDocument)namedProperty);
					else if (((IdentifiableDocument)namedProperty).equals(Sbol2Terms.Sequence.Sequence))
						parseSequences(SBOLDoc, (IdentifiableDocument)namedProperty);
					else if (((IdentifiableDocument)namedProperty).equals(Sbol2Terms.ComponentDefinition.ComponentDefinition))
						parseComponentDefinitions(SBOLDoc, (IdentifiableDocument)namedProperty, nested);
					else
						parseGenericTopLevel(SBOLDoc, (IdentifiableDocument)namedProperty);
				} else {
					throw new SBOLValidationException("sbol-12102", topLevel.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		c.setWasDerivedFroms(wasDerivedFroms);
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

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @param nested
	 * @return
	 * 
 	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 11602, 11607 or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseModule(NestedDocument, Map)},</li>
	 * 		<li>{@link #parseInteraction(NestedDocument, Map)},</li>
	 * 		<li>{@link #parseFunctionalComponent(NestedDocument, Map)},</li>	
	 * 		<li>{@link ModuleDefinition#ModuleDefinition(URI)}, </li>
	 * 		<li>{@link ModuleDefinition#setDisplayId(String)}, </li>
	 * 		<li>{@link ModuleDefinition#setVersion(String)}, </li>
	 * 		<li>{@link ModuleDefinition#setFunctionalComponents(Set)}, </li>
	 * 		<li>{@link ModuleDefinition#setInteractions(Set)}, </li>
	 * 		<li>{@link ModuleDefinition#setModels(Set)}, </li>
	 * 		<li>{@link ModuleDefinition#setModules(Set)}, </li>
	 * 		<li>{@link ModuleDefinition#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link ModuleDefinition#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addModuleDefinition(ModuleDefinition)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	private static ModuleDefinition parseModuleDefinition(SBOLDocument SBOLDoc,
			IdentifiableDocument<QName> topLevel, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 	       = null;
		Set<URI> wasDerivedFroms = new HashSet<>();
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
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.roles))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11602", topLevel.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModule) ||
					namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasSubModule))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Module.Module)) {
						throw new SBOLValidationException("sbol-11604",topLevel.getIdentity());
					}
					subModules.add(parseModule(SBOLDoc, ((NestedDocument<QName>) namedProperty.getValue()), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Module.Module)) {
						throw new SBOLValidationException("sbol-11604",topLevel.getIdentity());
					}
					subModules.add(parseModule(SBOLDoc, nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasInteractions))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Interaction.Interaction)) {
						throw new SBOLValidationException("sbol-11605",topLevel.getIdentity());
					}
					interactions.add(parseInteraction((NestedDocument<QName>) namedProperty.getValue(), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Interaction.Interaction)) {
						throw new SBOLValidationException("sbol-11605",topLevel.getIdentity());
					}
					interactions.add(parseInteraction(nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasfunctionalComponent)||
					namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasComponent))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent)) {
						throw new SBOLValidationException("sbol-11606",topLevel.getIdentity());
					}
					functionalComponents
					.add(parseFunctionalComponent(SBOLDoc, (NestedDocument<QName>) namedProperty.getValue(), nested));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.FunctionalComponent.FunctionalComponent)) {
						throw new SBOLValidationException("sbol-11606",topLevel.getIdentity());
					}
					functionalComponents.add(parseFunctionalComponent(SBOLDoc, nested.get(uri), nested));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModels))
			{
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-11607", topLevel.getIdentity());
					}
					models.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
				}
				else if (namedProperty.getValue() instanceof IdentifiableDocument) {
					if (((IdentifiableDocument<QName>)namedProperty).getType().equals(Sbol2Terms.Model.Model)) {
						Model model = parseModels(SBOLDoc,(IdentifiableDocument<QName>)namedProperty.getValue());
						models.add(model.getIdentity());
					} else {
						throw new SBOLValidationException("sbol-11607", topLevel.getIdentity());
					}
				}
				else {
					throw new SBOLValidationException("sbol-11607", topLevel.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208",topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		moduleDefinition.setWasDerivedFroms(wasDerivedFroms);
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

	/**
	 * @param module
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10203, 10204, 10206, 10208, 10212, 10213, 11604, 11702; or 
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseMapsTo(NestedDocument, boolean)},</li>
	 * 		<li>{@link Module#Module(URI, URI)},</li>
	 * 		<li>{@link Module#setDisplayId(String)},</li>
	 * 		<li>{@link Module#setVersion(String)},</li>
	 * 		<li>{@link Module#setWasDerivedFrom(URI)},</li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link Module#setMapsTos(Set)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	private static Module parseModule(SBOLDocument SBOLDoc, NestedDocument<QName> module, 
			Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(module.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(module.getIdentity()));
		String version 		   = null;
		URI definitionURI 	   = null;
		Set<URI> wasDerivedFroms     = new HashSet<>();
		Set<MapsTo> mappings 		 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : module.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", module.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", module.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", module.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Module.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-11706",module.getIdentity());
					}
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),false));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-11706",module.getIdentity());
					}
					mappings.add(parseMapsTo(nested.get(uri),false));
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
				if (definitionURI != null) {
					throw new SBOLValidationException("sbol-11702", module.getIdentity());
				}
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-11702", module.getIdentity());
					}	
					definitionURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				}
				else if (namedProperty.getValue() instanceof IdentifiableDocument) {
					if (((IdentifiableDocument<QName>)namedProperty).getType().equals(Sbol2Terms.ModuleDefinition.ModuleDefinition)) {
						ModuleDefinition moduleDefinition = parseModuleDefinition(SBOLDoc,
								(IdentifiableDocument<QName>)namedProperty.getValue(),nested);
						definitionURI = moduleDefinition.getIdentity();
					} else {
						throw new SBOLValidationException("sbol-11702", module.getIdentity());
					}
				}
				else {
					throw new SBOLValidationException("sbol-11702", module.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", module.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", module.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", module.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		submodule.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			submodule.setAnnotations(annotations);
		return submodule;
	}

	/**
	 * @param mapsTo
	 * @param inModule
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10203, 10204, 10206, 10208, 10212, 10213, 10606, 10802, 10805, 10810, 11706; or
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link MapsTo#MapsTo(URI, RefinementType, URI, URI)},</li> 		
	 * 		<li>{@link MapsTo#setDisplayId(String)},</li>
	 * 		<li>{@link MapsTo#setVersion(String)},</li>
	 * 		<li>{@link MapsTo#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
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
		Set<URI> wasDerivedFroms     = new HashSet<>();

		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : mapsTo.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", mapsTo.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", mapsTo.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", mapsTo.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",mapsTo.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", mapsTo.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.MapsTo.refinement))
			{
				if (!(namedProperty.getValue() instanceof Literal) || refinement != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10810", mapsTo.getIdentity());
				}
				String refinementStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
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
			else if (namedProperty.getName().equals(Sbol2Terms.MapsTo.hasRemote))
			{
				if (!(namedProperty.getValue() instanceof Literal) || remote != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10805", mapsTo.getIdentity());
				}
				remote = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.MapsTo.hasLocal))
			{
				if (!(namedProperty.getValue() instanceof Literal) || local != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10802", mapsTo.getIdentity());
				}
				local = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", mapsTo.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
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
		map.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			map.setAnnotations(annotations);
		return map;
	}

	/**
	 * @param interaction
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10203, 10204, 10206, 10208,  10212, 10213, 11605, 11902; or 
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseParticipation(NestedDocument)},</li>
	 * 		<li>{@link Interaction#Interaction(URI, Set)},</li>
	 * 		<li>{@link Interaction#setParticipations(Set)}, </li>
	 * 		<li>{@link Interaction#setDisplayId(String)}, </li>
	 * 		<li>{@link Interaction#setVersion(String)}, </li>
	 * 		<li>{@link Interaction#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}. </li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static Interaction parseInteraction(NestedDocument<QName> interaction, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(interaction.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(interaction.getIdentity()));
		String version 		   = null;
		Set<URI> wasDerivedFroms   = new HashSet<>();
		Set<URI> type 		   			   = new HashSet<>();
		Set<Participation> participations = new HashSet<>();
		List<Annotation> annotations 	   = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : interaction.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", interaction.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", interaction.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", interaction.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Interaction.type))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11902", interaction.getIdentity());
				}
				type.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Interaction.hasParticipations))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Participation.Participation)) {
						throw new SBOLValidationException("sbol-11906",interaction.getIdentity());
					}
					participations.add(parseParticipation(((NestedDocument<QName>) namedProperty.getValue())));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.Participation.Participation)) {
						throw new SBOLValidationException("sbol-11906",interaction.getIdentity());
					}
					participations.add(parseParticipation(nested.get(uri)));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", interaction.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", interaction.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", interaction.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
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
		i.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			i.setAnnotations(annotations);
		return i;
	}

	/**
	 * @param participation
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10203, 10204, 10206, 10208, 10212, 10213, 11906, 12002, 12004; or  
	 *</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Participation#Participation(URI, URI, Set)},</li>
	 * 		<li>{@link Participation#setDisplayId(String)},</li>
	 * 		<li>{@link Participation#setVersion(String)},</li>
	 * 		<li>{@link Participation#setWasDerivedFrom(URI)}, or</li>
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static Participation parseParticipation(NestedDocument<QName> participation) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(participation.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(participation.getIdentity()));
		String version 		   = null;
		Set<URI> roles 		   = new HashSet<>();
		URI participant        = null;
		Set<URI> wasDerivedFroms	 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : participation.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", participation.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", participation.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", participation.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", participation.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", participation.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Participation.role))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-12004", participation.getIdentity());
				}
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue()
						.toString()));
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Participation.hasParticipant))
			{
				if (!(namedProperty.getValue() instanceof Literal) || participant != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-12002", participation.getIdentity());
				}
				participant = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", participation.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
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
		p.setWasDerivedFroms(wasDerivedFroms);
		if(!annotations.isEmpty())
			p.setAnnotations(annotations);
		return p;
	}

	/**
	 * @param functionalComponent
	 * @param nested
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated:
	 * 10203, 10204, 10206, 10208, 10212, 10213, 10602, 10607, 11606, 11802; or  
	 * </li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link #parseMapsTo(NestedDocument, boolean)},</li>
	 * 		<li>{@link FunctionalComponent#FunctionalComponent(URI, AccessType, URI, DirectionType)},</li>
	 * 		<li>{@link FunctionalComponent#setDisplayId(String)},</li>
	 * 		<li>{@link FunctionalComponent#setVersion(String)},</li>
	 * 		<li>{@link FunctionalComponent#setWasDerivedFrom(URI)},</li>
	 * 		<li>{@link FunctionalComponent#setMapsTos(Set)}, or</li>	
	 * 		<li>{@link Identified#setAnnotations(List)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	private static FunctionalComponent parseFunctionalComponent(SBOLDocument SBOLDoc,
			NestedDocument<QName> functionalComponent, Map<URI, NestedDocument<QName>> nested) throws SBOLValidationException
	{
		String displayId 	   = null;//URIcompliance.extractDisplayId(functionalComponent.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null;//URI.create(URIcompliance.extractPersistentId(functionalComponent.getIdentity()));
		String version 			   = null;
		AccessType access 		   = null;
		DirectionType direction    = null;
		URI functionalComponentURI = null;
		Set<URI> wasDerivedFroms	 = new HashSet<>();

		List<Annotation> annotations = new ArrayList<>();
		Set<MapsTo> mappings 		 = new HashSet<>();

		for (NamedProperty<QName> namedProperty : functionalComponent.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", functionalComponent.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", functionalComponent.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", functionalComponent.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				if (!(namedProperty.getValue() instanceof Literal) || access != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10607", functionalComponent.getIdentity());
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
					throw new SBOLValidationException("sbol-10607",functionalComponent.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.FunctionalComponent.direction))
			{
				if (!(namedProperty.getValue() instanceof Literal) || direction != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-11802", functionalComponent.getIdentity());
				}
				String directionTypeStr = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
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
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasMapsTo))
			{
				if (namedProperty.getValue() instanceof NestedDocument) {
					NestedDocument<QName> nestedDocument = ((NestedDocument<QName>) namedProperty.getValue());
					if (nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-10606",functionalComponent.getIdentity());
					}
					mappings.add(parseMapsTo(((NestedDocument<QName>) namedProperty.getValue()),false));
				}
				else {
					URI uri = (URI) ((Literal<QName>)namedProperty.getValue()).getValue();
					NestedDocument<QName> nestedDocument = nested.get(uri);
					if (nestedDocument==null || nestedDocument.getType()==null || 
							!nestedDocument.getType().equals(Sbol2Terms.MapsTo.MapsTo)) {
						throw new SBOLValidationException("sbol-10606",functionalComponent.getIdentity());
					}
					mappings.add(parseMapsTo(nested.get(uri),false));
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				if (functionalComponentURI != null) {
					throw new SBOLValidationException("sbol-10602", functionalComponent.getIdentity());
				}
				if (namedProperty.getValue() instanceof Literal) {
					if (!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI)) {
						throw new SBOLValidationException("sbol-10602", functionalComponent.getIdentity());
					}
					functionalComponentURI = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				}
				else if (namedProperty.getValue() instanceof IdentifiableDocument) {
					if (((IdentifiableDocument<QName>)namedProperty).getType().equals(Sbol2Terms.ComponentDefinition.ComponentDefinition)) {
						ComponentDefinition componentDefinition = parseComponentDefinitions(SBOLDoc,
								(IdentifiableDocument<QName>)namedProperty.getValue(),nested);
						functionalComponentURI = componentDefinition.getIdentity();
					} else {
						throw new SBOLValidationException("sbol-10602", functionalComponent.getIdentity());
					}
				}
				else {
					throw new SBOLValidationException("sbol-10602", functionalComponent.getIdentity());
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", functionalComponent.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213", functionalComponent.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", functionalComponent.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
			}
			else
			{
				annotations.add(new Annotation(namedProperty));
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
		fc.setWasDerivedFroms(wasDerivedFroms);
		if (!annotations.isEmpty())
			fc.setAnnotations(annotations);
		return fc;
	}

	/**
	 * @param SBOLDoc
	 * @param topLevel
	 * @return
	 * @throws SBOLValidationException if either of the following conditions is satisfied:
	 * <ul>
	 * <li>any of the following SBOL validation rules was violated: 
	 * 10202, 10203, 10204, 10206, 10208, 10212, 10213, 10402, 10403, ; or</li>
	 * <li>an SBOL validation rule violation occurred in the following constructor or methods:
	 * 	<ul>
	 * 		<li>{@link Sequence#Sequence(URI, String, URI)}, </li>
	 * 		<li>{@link Sequence#setDisplayId(String)}, </li>
	 * 		<li>{@link Sequence#setVersion(String)}, </li>
	 * 		<li>{@link Sequence#setWasDerivedFrom(URI)}, </li>
	 * 		<li>{@link Identified#setAnnotations(List)}, or</li>
	 * 		<li>{@link SBOLDocument#addSequence(Sequence)}.</li>
	 * 	</ul>
	 * </li>
	 * </ul>
	 */
	private static Sequence parseSequences(SBOLDocument SBOLDoc, IdentifiableDocument<QName> topLevel) throws SBOLValidationException
	{
		String displayId 	   = null; //URIcompliance.extractDisplayId(topLevel.getIdentity());
		String name 	 	   = null;
		String description 	   = null;
		URI persistentIdentity = null; //URI.create(URIcompliance.extractPersistentId(topLevel.getIdentity()));
		String version 		   = null;
		String elements 	   = null;
		URI encoding 		   = null;
		Set<URI> wasDerivedFroms	 = new HashSet<>();
		List<Annotation> annotations = new ArrayList<>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if (namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
			{
				if (!(namedProperty.getValue() instanceof Literal) || persistentIdentity != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10203", topLevel.getIdentity());
				}
				persistentIdentity = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				if (!(namedProperty.getValue() instanceof Literal) || version != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10206", topLevel.getIdentity());
				}
				version  = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.displayId))
			{
				if (!(namedProperty.getValue() instanceof Literal) || displayId != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10204", topLevel.getIdentity());
				}
				displayId = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.elements))
			{
				if (!(namedProperty.getValue() instanceof Literal) || elements != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10402", topLevel.getIdentity());
				}
				elements = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Sequence.encoding))
			{
				if (!(namedProperty.getValue() instanceof Literal) || encoding != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10403", topLevel.getIdentity());
				}
				encoding = URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString());
				if (encoding.toString().equals("http://dx.doi.org/10.1021/bi00822a023")) {
					encoding = Sequence.IUPAC_DNA;
				}
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.title))
			{
				if (!(namedProperty.getValue() instanceof Literal) || name != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10212", topLevel.getIdentity());
				}
				name = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.description))
			{
				if (!(namedProperty.getValue() instanceof Literal) || description != null ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof String))) {
					throw new SBOLValidationException("sbol-10213",topLevel.getIdentity());
				}
				description = ((Literal<QName>) namedProperty.getValue()).getValue().toString();
			}
			else if (namedProperty.getName().equals(Sbol2Terms.Identified.wasDerivedFrom))
			{
				if (!(namedProperty.getValue() instanceof Literal) ||
						(!(((Literal<QName>) namedProperty.getValue()).getValue() instanceof URI))) {
					throw new SBOLValidationException("sbol-10208", topLevel.getIdentity());
				}
				wasDerivedFroms.add(URI.create(((Literal<QName>) namedProperty.getValue()).getValue().toString()));
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
		sequence.setWasDerivedFroms(wasDerivedFroms);
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
