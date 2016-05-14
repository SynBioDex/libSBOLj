package org.sbolstandard.core2.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidate;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;

public class ModuleDefModuleTree {

	private Random rand = new Random();
	private int maxDepth;
	private int numOfModules;
	private String expectedError = null;
	
	public ModuleDefModuleTree(int maxDepth, int numOfModules) {
		this.maxDepth = maxDepth;
		this.numOfModules = numOfModules;
	}

	public void setRandomSeed(long seed) {
		rand.setSeed(seed);
	}

	public String generateModel() throws SBOLValidationException, IOException, SBOLConversionException {
		SBOLDocument doc = new SBOLDocument();
		String version = "1.0";

		doc.setDefaultURIprefix("http://sbols.org/CRISPR_Example/");
		doc.setComplete(true);
		doc.setCreateDefaults(true);

		ModuleDefinition md1;
		md1 = doc.createModuleDefinition("md1", version);
		ArrayList<String> mdNames = new ArrayList<String>();
		mdNames.add("md1");
		int depth = maxDepth;
		ArrayList<Integer> address = new ArrayList<Integer>();
		int selectedDepth = randInt(1, maxDepth);
		ArrayList<Integer> addrSelfRef = new ArrayList<Integer>();
		for (int i=0; i<selectedDepth; i++) {
			addrSelfRef.add(randInt(1, numOfModules));
		}
		String expectedError = createModuleMDGraph(doc, depth, mdNames, address, md1, addrSelfRef);
		SBOLWriter.write(doc, "TestCircularModules.rdf");
		System.out.println("Finished writing.");
		//SBOLReader.read("/Users/zhangz/libSBOLproject/libSBOLj/examples/TestCircularModules.rdf");
		//SBOLReader.read("/Users/zhangz/libSBOLproject/libSBOLj/core2/src/test/resources/test/data/Validation/sbol-11705.rdf");
		SBOLValidate.validateSBOL(doc, true, true, true);
		if (SBOLValidate.getNumErrors() > 0) {
			for (String error : SBOLValidate.getErrors()) {
				System.out.println(error);
			}			
		}
		return expectedError;
	}
	
	public String createModuleMDGraph(SBOLDocument doc, int depth, ArrayList<String> mdNames, ArrayList<Integer> address, 
			ModuleDefinition parentMd, ArrayList<Integer> addrSelfRef) throws SBOLValidationException {
		//System.out.println("depth = " + depth);		
		if (depth > 0) {
			for (int i=1; i<=numOfModules; i++) {				
				address.add(i);
				String mdId ="md_" + intArrayListToString(address);			
				String mId = "m_" + intArrayListToString(address);
				if (intArrayListToString(address).equals(intArrayListToString(addrSelfRef))) {
					int circleRefIndex = randInt(0,mdNames.size()-1);
					System.out.println("circleRefIndex = " + circleRefIndex);
					System.out.println("address = " + intArrayListToString(address));
					System.out.println("addrSelfRef = " + intArrayListToString(addrSelfRef));
					if (circleRefIndex == mdNames.size() -1 ) {
						//System.out.println("Expect 11704");
						expectedError = "11704";
					}
					else {
						//System.out.println("Expect 11705");
						expectedError = "11705";
					}
					System.out.println("Last module displayId = " + mId);
					System.out.println(mdNames);
					System.out.println("mdName = " + mdNames.get(circleRefIndex));
					parentMd.createModule(mId, mdNames.get(circleRefIndex));
				}
				else {
					ModuleDefinition childMd = doc.createModuleDefinition(mdId);
					parentMd.createModule(mId, mdId);				
					mdNames.add(mdId);
					//System.out.println(mdId);
					//System.out.println("Interm. Module displayId = " + m.getDisplayId());
					createModuleMDGraph(doc, depth-1, mdNames, address, childMd, addrSelfRef);
					mdNames.remove(mdId);
				}
				
				address.remove(address.size()-1);				
			}
		}
		return expectedError;
	}

	private static String intArrayListToString(ArrayList<Integer> address) {
		String str = ""; 
		for (Integer j: address) {
			str = str + j + "_";
		}
		
		return str;		
	}
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public int randInt(int min, int max) {

		// NOTE: This will (intentionally) not run as written so that folks
		// copy-pasting have to think about how to initialize their
		// Random instance.  Initialization of the Random instance is outside
		// the main scope of the question, but some decent options are to have
		// a field that is initialized once and then re-used as needed or to
		// use ThreadLocalRandom (if using at least Java 1.7).
		//Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
