package org.sbolstandard.core2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {

	private static String name;

	/*
	 * TODO: When iterating nestedDocument, remember to go through each properties.
	 */

	public static SBOLDocument read(String fileName) throws Throwable {
		FileInputStream fis = new FileInputStream(fileName);
		String inputStreamString = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
		//		System.out.println(inputStreamString);

		return readRdf(new File(fileName));
	}

	/**
	 * Takes in the given .rdf file and converts the file to an SBOLDocument
	 */
	public static SBOLDocument readRdf(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		String inputStreamString = new Scanner(stream,"UTF-8").useDelimiter("\\A").next();

		DocumentRoot<QName> document= read(new StringReader(inputStreamString));
		return readRdf(buffer, document);
	}

	public static SBOLDocument readRdf(InputStream in, DocumentRoot<QName> document)
	{
		SBOLDocument SBOLDoc = new SBOLDocument();
		readRdf(SBOLDoc,document);
		return SBOLDoc;
	}



	private static DocumentRoot<QName> read(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();
	}

	private static void readRdf(SBOLDocument SBOLDoc, DocumentRoot<QName> document) {
		for(TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			if(topLevel.getType().equals( Sbol2Terms.Sequence.Sequence))
				parseSequence(SBOLDoc, topLevel);
		}
	}

	private static boolean isEmptyElementValue(Literal<QName> literal) {
		return /* literal instanceof Literal.QNameLiteral || */ literal instanceof Literal.UriLiteral;
	}

	private static void parseSequence(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		String elements ="";
		URI encoding = null;


		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				/*
				new PropertyValue.Visitor<QName>() {
					@Override
					public void visit(NestedDocument<QName> v) throws XMLStreamException {
					}

					@Override
					public void visit(Literal<QName> v) throws XMLStreamException {
						if(isEmptyElementValue(v)) {
						} else {
							name = v.getValue().toString();
						}
					}
				}.visit(namedProperty.getValue());
				 */
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = namedProperty.getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Sequence.elements))
			{
				elements = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if( namedProperty.getName().equals( Sbol2Terms.Sequence.encoding))
			{
				encoding = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else
			{
				// TODO: Everything else will be considered as an annotation
			}
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date parsedTimeStamp = null;
		try {
			parsedTimeStamp = dateFormat.parse(timeStamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());

		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(), elements, encoding);
		//TODO: wait until Zhen adds features to getter() for Timestamp: sequence.setTimeStamp(new Timestamp(timestamp));
		// TODO: do not set name if blank
		sequence.setName(name);
		sequence.setDescription(description);
		sequence.setTimeStamp(timestamp);


	}

}
