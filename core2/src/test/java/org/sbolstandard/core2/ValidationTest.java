/**
 * 
 */
package org.sbolstandard.core2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
	public void testValidation() throws CoreIoException, XMLStreamException, FactoryConfigurationError {
		// TODO: generalize this test to perform on all files in directory in a loop
		File file_base = null ;
		try {
			file_base = new File(ValidationTest.class.getResource("/test/data/Validation/").toURI());
		}
		catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file;
		SBOLDocument doc = null;
		HashSet<Integer> testedRules = new HashSet<Integer>(); 
		for (File f : file_base.listFiles()){
			//InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
			file = new File(f.getAbsolutePath());
			System.out.println(f.getName().replace(".rdf", ""));
			SBOLReader.setKeepGoing(true);
			try {
				doc = SBOLReader.read(file);
			} catch (SBOLValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SBOLValidate.validateSBOL(doc, true, true, true);


			if (SBOLReader.getNumErrors() > 0) {
				// TODO: check if error number matches file name
				for(String error : SBOLReader.getErrors())
				{
					System.out.println(error);
					if(!error.split(":")[0].equals((f.getName()).replace(".rdf", "")))
					{
						//fail();

					}
					else {
						String ruleId = error.split(":")[0].replace(".rdf", "").replace("sbol-", "").trim();
						testedRules.add(Integer.parseInt(ruleId));
					}
				}

			} else if (SBOLValidate.getNumErrors() > 0) {
				// TODO: check if error number matches file name
				for(String error : SBOLValidate.getErrors())
				{
					System.out.println(error);
					if(!error.split(":")[0].equals(f.getName().replace(".rdf", "")))
					{
						//fail();
					}
					else {
						String ruleId = error.split(":")[0].replace(".rdf", "").replace("sbol-", "").trim();
						testedRules.add(Integer.parseInt(ruleId));
					}

				}
			} else {
				// TODO: fail
				//fail();
			}
		}
		// Print out remaining rules that have not had a test yet.
		//System.out.println(testedRules);
		HashSet<Integer> green = new HashSet<Integer>(Arrays.asList(
				10101, 10102, 10103, 10104, 
				10202, 10203, 10204, 10206, 10208, 10209, 10210, 10211, 10212, 10213, 10215, 10216, 10217, 10218, 10219, 
				10402, 10403, 10405, 10406, 
				10502, 10503, 10505, 10507, 10510, 10511, 10512, 10513, 10516, 10518, 10519, 10521, 10522, 10523, 10524, 
				10602, 10603, 10604, 10605, 10606, 10607, 
				10802, 10803, 10804, 10805, 10806, 10807, 10808, 10809, 10810, 10811, 
				10902, 10903, 10904, 10905,
				11002, 11102, 11103, 11104, 
				11202, 
				11402, 11403, 11404, 11405, 11406, 11407, 11412, 
				11502, 11504, 11506, 11508, 11510, 11511, 
				11602, 11604, 11605, 11606,	11607, 11608, 
				11703, 11704, 11705, 11706, 
				11802, 
				11902, 11906, 
				12001, 12002, 12003, 12004, 12006, 12007, 
				12102, 12103));
		HashSet<Integer> yellow = new HashSet<Integer>(Arrays.asList(
				10201, 10214, 10517, 10409, 10410, 10411, 11507, 11509,
				12201, 12202, 12203, 12204, 12205, 12206, 12302));
		HashSet<Integer> pink = new HashSet<Integer>(Arrays.asList(
				10205, 10207, 10301, 10401, 10404, 10501, 10504, 10506, 10508, 10509, 10514, 
				10515, 10520, 10601, 10701, 10801, 10901, 11001, 11101, 11201, 11301, 11401,
				11408, 11501, 11503, 11505, 11601, 11603, 11701, 11801, 11901, 11903, 11904,
				12001, 12005, 12101, 12301));
		if (pink.retainAll(testedRules) == true && pink.size() == 0) {
			System.out.println();
			System.out.println("No tests for pink rules were created.");
		}
		else {
			System.out.println();
			System.out.println("Mismatch: tests below were created but are marked pink.");
			System.out.println(sortIntegerHashSet(pink));
		}
		if (yellow.retainAll(testedRules) == true && yellow.size() == 0) {
			System.out.println();
			System.out.println("No tests for yellow rules were created.");
		}
		else {
			System.out.println();
			System.out.println("Warning: tests below were created but are marked yellow:");
			System.out.println(sortIntegerHashSet(yellow));
		}
		//System.out.println("tested rules: ");
		//System.out.println(sortIntegerHashSet(testedRules));
		
		HashSet<Integer> greenNotTested = (HashSet<Integer>) green.clone();
		green.retainAll(testedRules); // Remove all elements from green that are not in testedRules
		//System.out.println(sortIntegerHashSet(green));
		greenNotTested.removeAll(green);
		System.out.println();
		System.out.println("Rules that are checked in the library but not tested: ");
		System.out.println(sortIntegerHashSet(greenNotTested));
		
		
		
		
//		greenNotTested.retainAll(green);
//		ArrayList<Integer> sortedGreenNotTested = new ArrayList<Integer>(greenNotTested);
//		Collections.sort(sortedGreenNotTested);
//		System.out.println("Rules that need tests:");
//		System.out.println(sortedGreenNotTested);
		
//		ArrayList<Integer> sortedTestRules = new ArrayList<Integer>(testedRules);	// 
//		Collections.sort(sortedTestRules);
//		System.out.println(sortedTestRules);
} 

	public ArrayList<Integer> sortIntegerHashSet(HashSet<Integer> set) {
		ArrayList<Integer> sorted= new ArrayList<Integer>(set);
		Collections.sort(sorted);
		return sorted;
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
