/**
 * 
 */
package org.sbolstandard.core2;

import static org.junit.Assert.fail;

import java.io.InputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.ac.ncl.intbio.core.io.CoreIoException;

/**
 * @author Meher Samineni
 *
 */
public class ValidationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
		if(file == null)
			file = ValidationTest.class.getResourceAsStream("/" + "test/data/Validation/" + "sbol-10101.rdf");
		try {
			SBOLReader.read(file);
			fail();
		}
		catch (CoreIoException e) {
			e.printStackTrace();
		}
		catch (XMLStreamException e) {
			e.printStackTrace();
		}
		catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
		catch (SBOLValidationException e) {
			e.printStackTrace();
		} //"/test/data/Validation/sbol-10101.rdf");
		//fail("Not yet implemented");
	}
	
	@Test
	public void test10101() throws CoreIoException, XMLStreamException, FactoryConfigurationError {
		InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
		if(file == null)
			file = ValidationTest.class.getResourceAsStream("/" + "test/data/Validation/" + "sbol-10101.rdf");
		try {
			SBOLReader.read(file);
		} catch (SBOLValidationException e) {			
			e.printStackTrace();
		}
	}
	
	
//	@Test (expected=SBOLValidationException.class)	
//	public void test10101_alt1() throws CoreIoException, XMLStreamException, FactoryConfigurationError, SBOLValidationException{
//		InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
//		if(file == null)
//			file = ValidationTest.class.getResourceAsStream("/" + "test/data/Validation/" + "sbol-10101.rdf");
//		SBOLReader.read(file);
//	}
//	
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//	@Test	
//	public void test10101_alt2() throws CoreIoException, XMLStreamException, FactoryConfigurationError, SBOLValidationException{
//		InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
//		if(file == null)
//			file = ValidationTest.class.getResourceAsStream("/" + "test/data/Validation/" + "sbol-10101.rdf");
//		thrown.expect(SBOLValidationException.class);
//		thrown.expectMessage("sbol-10102");
//		SBOLReader.read(file);		
//		//thrown.reportMissingExceptionWithMessage("Should throw %s");
//	}

}
