package org.sbolstandard.core2.examples;

import org.sbolstandard.core2.SBOLValidationException;

public class TestCircularModules {
	private static int numOfModules = 5;
	private static int maxDepth = 4;
	private static int numOfRuns = 100;
	
	//TODO: Use random seeds; Automate the number of runs; Randomize depth [1, 4]  
	// Check if an SBOLValidationException is thrown in every test case, and check if it throws the right exception.
	public static void main(String[] args) throws Exception {
		ModuleDefModuleTree tree = new ModuleDefModuleTree(maxDepth, numOfModules);
		tree.setRandomSeed(12);
		for (int i = 0; i < numOfRuns ; i++) {
			boolean exceptionCaught = false;
			//String expectedError = null;
			try {
				 tree.generateModel();				 
			} catch (SBOLValidationException e) {
				exceptionCaught = true;				
				//System.out.println("expectedError = " + expectedError);
				//System.out.println("validation exception e = " + e);
				if (!e.getMessage().contains(tree.getExpectedError())) {
					throw new Exception("Unexpected exception!");
				}
			}  
			if (!exceptionCaught) {
				throw new Exception("Exception NOT caught!");
				//System.out.println("Exception NOT caught!");
			}
		}
	}
}
