/**
 * 
 */
package org.sbolstandard.core2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void testValidation() {
		// TODO: generalize this test to perform on all files in directory in a loop
		File file_base = null ;
		try {
			file_base = new File(ValidationTest.class.getResource("/test/data/Validation/").toURI());
		}
		catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		File file;
		SBOLDocument doc = null;
		HashSet<Integer> testedRules = new HashSet<Integer>(); 
		HashSet<Integer> failedTests = new HashSet<Integer>(); 
		for (File f : file_base.listFiles()){
			//InputStream file = ValidationTest.class.getResourceAsStream("test/data/Validation/sbol-10101.rdf");
			file = new File(f.getAbsolutePath());
			//System.out.println(f.getName().replace(".rdf", ""));
			SBOLReader.setKeepGoing(true);
			try {
				doc = SBOLReader.read(file);
			} catch (SBOLValidationException e) {
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			SBOLValidate.validateSBOL(doc, true, true, true);


			if (SBOLReader.getNumErrors() > 0) {
				for(String error : SBOLReader.getErrors())
				{
					//System.out.println(error);
					if(!error.split(":")[0].equals((f.getName()).replace(".rdf", "")))
					{
						fail();

					}
					else {
						String ruleId = error.split(":")[0].replace(".rdf", "").replace("sbol-", "").trim();
						testedRules.add(Integer.parseInt(ruleId));
					}
				}

			} else if (SBOLValidate.getNumErrors() > 0) {
				for(String error : SBOLValidate.getErrors())
				{
					System.out.println(error);
					if(!error.split(":")[0].equals(f.getName().replace(".rdf", "")))
					{
						String ruleId = f.getName().replace(".rdf", "").replace("sbol-", "").trim();
						failedTests.add(Integer.parseInt(ruleId));
						fail();
					}
					else {
						String ruleId = error.split(":")[0].replace(".rdf", "").replace("sbol-", "").trim();
						testedRules.add(Integer.parseInt(ruleId));
					}

				}
			} else {
				String ruleId = f.getName().replace(".rdf", "").replace("sbol-", "").trim();
				failedTests.add(Integer.parseInt(ruleId));
				fail();
			}
		}
		// Print out remaining rules that have not had a test yet.
		//System.out.println(testedRules);
		HashSet<Integer> red = new HashSet<Integer>(Arrays.asList(
				10101, 10105,
				10201, 10203, 10204, 10206, 10208, 10209, 10212, 10213, 10220,
				10401, 10402, 10403, 
				10501, 10502, 10503, 10507, 10512, 10519, 10521, 10522, 10524, 10526,
				10602, 10603, 10606, 10607, 
				10701,
				10801, 10802, 10803, 10804, 10805, 10810, 
				10901, 10902, 10904, 10905,
				11002, 
				11101, 11102, 11103, 11104, 
				11201, 11202, 
				11301,
				11401, 11402, 11403, 11404, 11405, 11406, 11407, 
				11501, 11502, 11504, 11508, 
				11601, 11602, 11604, 11605, 11606, 11607, 11609,
				11701, 11702, 11704, 11706, 
				11801, 11802, 
				11901, 11902, 11906, 
				12001, 12002, 12003, 12004, 
				12101, 12102,
				12301, 12302));
		HashSet<Integer> blue = new HashSet<Integer>(Arrays.asList(
				10202, 10210,
				10405, 
				10513, 10516,
				10604, 10605, 
				10807, 10808, 10809, 10811, 
				11409, 11410, 11411, 
				11608, 
				11703, 11705,
				12103
				));
		HashSet<Integer> green = new HashSet<Integer>(Arrays.asList(
				10207, 10215, 10216, 10217, 10218, 10219, 
				10407,
				10511, 10518, 10523, 10525, 10527,
				10903, 
				11412, 
				11507, 11511, 
				11905, 11907,
				12007 
				));
		HashSet<Integer> yellow = new HashSet<Integer>(Arrays.asList(
				10211, 10214, 
				10404, 10406, 
				10504, 10505, 10506, 10508, 10509, 10510, 10514, 10515, 10520,  
				11408,
				11503, 11505, 11506, 11509, 11510, 
				11603,
				11903, 11904, 
				12005, 12006, 
				12203, 12204, 12205, 12206
				));
		HashSet<Integer> removed = new HashSet<Integer>(Arrays.asList(
				10102, 10103, 10104, 
				10205,
				10301,
				10517,
				10601,
				10806, 
				12201, 12202
				));
		if (failedTests.size() > 0) {
			System.out.println();
			System.out.println("Warning: the following tests failed.");
			System.out.println(sortIntegerHashSet(failedTests));
		}
		HashSet<Integer> removedTested = (HashSet<Integer>) removed.clone();
		if (removedTested.retainAll(failedTests) == true && removedTested.size() == 0) {
		}
		else {
			System.out.println();
			System.out.println("Warning: tests below were created but are marked removed:");
			System.out.println(sortIntegerHashSet(removedTested));
		}
		if (removed.retainAll(testedRules) == true && removed.size() == 0) {
		}
		else {
			System.out.println();
			System.out.println("Warning: tests below were created but are marked removed:");
			System.out.println(sortIntegerHashSet(removed));
		}
		HashSet<Integer> yellowTested = (HashSet<Integer>) yellow.clone();
		if (yellowTested.retainAll(failedTests) == true && yellowTested.size() == 0) {
		}
		else {
			System.out.println();
			System.out.println("Warning: tests below were created but are marked yellow triangle.");
			System.out.println(sortIntegerHashSet(yellowTested));
		}
		if (yellow.retainAll(testedRules) == true && yellow.size() == 0) {
		}
		else {
			System.out.println();
			System.out.println("Warning: tests below were created but are marked yellow triangle.");
			System.out.println(sortIntegerHashSet(yellow));
		}
		//System.out.println("tested rules: ");
		//System.out.println(sortIntegerHashSet(testedRules));
		
		HashSet<Integer> redNotTested = (HashSet<Integer>) red.clone();
		red.retainAll(testedRules); 
		//System.out.println(sortIntegerHashSet(green));
		redNotTested.removeAll(red);
		if (!redNotTested.isEmpty()) {
			System.out.println();
			System.out.println("Red checked rules not tested: ");
			System.out.println(sortIntegerHashSet(redNotTested));
		}
		
		HashSet<Integer> blueNotTested = (HashSet<Integer>) blue.clone();
		blue.retainAll(testedRules); 
		//System.out.println(sortIntegerHashSet(green));
		blueNotTested.removeAll(blue);
		if (!blueNotTested.isEmpty()) {
			System.out.println();
			System.out.println("Blue partially checked rules not tested: ");
			System.out.println(sortIntegerHashSet(blueNotTested));
		}
		
		HashSet<Integer> greenNotTested = (HashSet<Integer>) green.clone();
		green.retainAll(testedRules); 
		//System.out.println(sortIntegerHashSet(green));
		greenNotTested.removeAll(green);
		if (!greenNotTested.isEmpty()) {
			System.out.println();
			System.out.println("Green best practice rules not tested: ");
			System.out.println(sortIntegerHashSet(greenNotTested));
		}
		
		
		
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
