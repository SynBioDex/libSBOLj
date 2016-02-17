/**
 * 
 */
package org.sbolstandard.core2;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.InputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ncl.intbio.core.io.CoreIoException;

/**
 * @author Meher Samineni
 *
 */
public class ValidationTest {

	@BeforeClass
	public static void setUpBeforeClass() {
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

//	@Test
//	public void test() {
//		InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
//		if(file == null)
//			file = ValidationTest.class.getResourceAsStream("/" + "test/data/Validation/" + "sbol-10101.rdf");
//		try {
//			SBOLReader.read(file);
//			//fail();
//		}
//		catch (CoreIoException e) {
//			e.printStackTrace();
//		}
//		catch (XMLStreamException e) {
//			e.printStackTrace();
//		}
//		catch (FactoryConfigurationError e) {
//			e.printStackTrace();
//		}
//		catch (SBOLValidationException e) {
//			e.printStackTrace();
//		} //"/test/data/Validation/sbol-10101.rdf");
//		//fail("Not yet implemented");
//	}
	
	@Test
	public void test10101() throws CoreIoException, XMLStreamException, FactoryConfigurationError {
		// TODO: generalize this test to perform on all files in directory in a loop
		File file_base = new File("test/data/Validation/");
		InputStream file;
		SBOLDocument doc = null;
		for (File f : file_base.listFiles()){
			//InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
			file = ValidationTest.class.getResourceAsStream(f.getAbsolutePath());
			if(file == null)
				file = ValidationTest.class.getResourceAsStream(f.getAbsolutePath());//"/" + "test/data/Validation/" + "sbol-10101.rdf");
			SBOLReader.setKeepGoing(true);
			try {
				doc = SBOLReader.read(file);
			} catch (SBOLValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SBOLValidate.validateSBOL(doc, true, true, true);

		
		if (SBOLReader.getNumErrors() > 0) {
			// TODO: check if error number matches file name
			for(String error : SBOLReader.getErrors())
			{
				if(!error.split(":")[0].equals(f.getName()))
				{
					fail();
					
				}
			}
			
		} else if (SBOLValidate.getNumErrors() > 0) {
			// TODO: check if error number matches file name
			for(String error : SBOLValidate.getErrors())
			{
				if(!error.split(":")[0].equals(f.getName()))
				{
					fail();
				
				}
			}
		} else {
			// TODO: fail
			//fail();
		}
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
