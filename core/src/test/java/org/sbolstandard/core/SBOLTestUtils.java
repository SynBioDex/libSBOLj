package org.sbolstandard.core;

import static org.junit.Assert.fail;

import java.io.*;
import java.net.URI;

public class SBOLTestUtils {
	private SBOLTestUtils() {		
	}
	
	public static Collection createCollection(int id, DnaComponent... components) {
		Collection coll1 = SBOLFactory.createCollection();
		coll1.setURI(uri("http://example.com/collection" + id));
		coll1.setDisplayId("Coll" + id);
		coll1.setName("Collection" + id);
		for (DnaComponent component : components) {
	        coll1.addComponent(component);
        }
		return coll1;
	}
	
	public static DnaComponent createDnaComponent(int id) {
		DnaComponent comp1 = SBOLFactory.createDnaComponent();
		comp1.setURI(uri("http://example.com/dc" + id));
		comp1.setDisplayId("DC" + id);
		comp1.setName("DnaComponent" + id);
		return comp1;
	}
	
	public static DnaSequence createDnaSequence(int id) {
		DnaSequence ds1 = SBOLFactory.createDnaSequence();
		ds1.setURI(uri("http://example.com/ds" + id));
		ds1.setNucleotides("tccctatcagtgat");
		return ds1;
	}
	
	public static SequenceAnnotation createSequenceAnnotation(int id) {
		SequenceAnnotation sa = SBOLFactory.createSequenceAnnotation();
		sa.setURI(uri("http://example.com/sa" + id));
		return sa;
	}
	
	public static SBOLDocument createDocument(SBOLRootObject... contents) {
		SBOLDocument doc = SBOLFactory.createDocument();
		for (SBOLRootObject obj : contents) {
	        doc.addContent(obj);
        }
		return doc;
	}
	
	public static SBOLDocument writeAndRead(SBOLDocument doc) throws SBOLValidationException, IOException {		
//		new SBOLPrettyWriter().write(doc, System.out);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SBOLFactory.write(doc, out);
		
		return SBOLFactory.read(new ByteArrayInputStream(out.toByteArray())); 
	}
	
	public static URI uri(String uri) {
		return URI.create(uri);
	}

	public static void assertInvalid(final String fileName, String expectedMessage) throws Exception {
		try {
			assertValid(fileName);
			fail("Invalid file passed validation: " + fileName);
		}
		catch (SBOLValidationException e) {
			String msgFormat = "Validation exception message does not contain expected message:%n  Expected=%s%n  Actual=%s";
			String msg = String.format(msgFormat, expectedMessage, e.getMessage());
			if(!e.getMessage().contains(expectedMessage) && (e.getCause() == null || !e.getCause().getMessage().contains(expectedMessage))) {
                throw(AssertionError) new AssertionError(msg).initCause(e);
            }
		}
		catch (Exception e) {
			throw e;
		}
	}

	public static void assertValid(final String fileName) throws Exception {
		// reading the document ensures validity
        InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
        if(resourceAsStream == null) resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

        assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";

        try {
            SBOLFactory.read(resourceAsStream);
        } catch (IOException e) {
            throw new AssertionError("Failed to validate " + fileName, e);
        } catch (SBOLValidationException e) {
            throw new SBOLValidationException("Failed to validate " + fileName, e);
        }
	}

	public static void assertInvalid(final SBOLDocument doc, String expectedMessage) throws Exception {
		try {
			assertValid(doc);
			fail("Invalid doc passed validation: " + doc);
		}
		catch (SBOLValidationException e) {
			String msgFormat = "Validation exception message does not contain expected message:%n  Expected=%s%n  Actual=%s";
			String msg = String.format(msgFormat, expectedMessage, e.getMessage());
            if(!e.getMessage().contains(expectedMessage)) {
                throw new AssertionError(msg, e);
            }
		}
	}

	public static void assertValid(final SBOLDocument doc) throws Exception {
		SBOLFactory.validate(doc);
	}
}