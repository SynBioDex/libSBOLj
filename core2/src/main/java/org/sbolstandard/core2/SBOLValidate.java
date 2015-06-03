package org.sbolstandard.core2;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SBOLValidate {
	
	/**
	 * the current SBOL version
	 */
	public static final String SBOLVersion = "2.0";
	
	private static void usage() {		
		System.err.println("libSBOLj version " + SBOLVersion);
		System.err.println("Description: Validates the contents of an SBOL document,\n" 
				+ "converting from SBOL 1.1 to SBOL 2.0, if necessary,\n" 
				+ "and printing the document contents if validation succeeds");
		System.err.println();
		System.err.println("Usage:");
		System.err.println("\tjava --jar libSBOLj.jar [options] <inputFile> [-o <outputFile> -p <URIprefix> -v <version>]");
		System.err.println();
		System.err.println("-t  uses types in URIs");
		System.err.println("-i  incomplete SBOL document");
		System.err.println("-n  non-compliant SBOL document");
		System.exit(1);
	}
	
	public static void validateCompliance(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			if (!collection.checkDescendantsURIcompliance()) 
				throw new SBOLException("Collection contains non-compliant URI",collection);
		}
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!sequence.checkDescendantsURIcompliance()) 
				throw new SBOLException("Sequence contains non-compliant URI",sequence);
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (!componentDefinition.checkDescendantsURIcompliance()) 
				throw new SBOLException("Component definition contains non-compliant URI",componentDefinition);
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (!moduleDefinition.checkDescendantsURIcompliance()) 	
				throw new SBOLException("Module definition contains non-compliant URI",moduleDefinition);
		}
		for (Model model : sbolDocument.getModels()) {
			if (!model.checkDescendantsURIcompliance()) 
				throw new SBOLException("Model contains non-compliant URI",model);
		}
		for (GenericTopLevel genericTopLevel : sbolDocument.getGenericTopLevels()) {
			if (!genericTopLevel.checkDescendantsURIcompliance()) 
				throw new SBOLException("Generic top level contains non-compliant URI",genericTopLevel);
		}
	}

	public static void validateCompleteness(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			if (!collection.isComplete()) 
				throw new SBOLException("Collection is not complete",collection);
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (!componentDefinition.isComplete()) 
				throw new SBOLException("Component definition is not complete",componentDefinition);
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (!moduleDefinition.isComplete()) 	
				throw new SBOLException("Module definition is not complete",moduleDefinition);
		}
	}
	
	public static void main(String[] args) {
		String fileName = "";
		String outputFile = "";
		String URIPrefix = "";
		String version = "";
		boolean complete = true;
		boolean compliant = true;
		boolean typesInURI = false;
		int i = 0;
		while (i < args.length) {
			if (args[i].equals("-i")) {
				complete = false;
			} else if (args[i].equals("-t")) {
				typesInURI = true;
			} else if (args[i].equals("-n")) {
				compliant = false;
			} else if (args[i].equals("-o")) {
				if (i+1 >= args.length) {
					usage();
				}
				outputFile = args[i+1];
				i++;
			} else if (args[i].equals("-p")) {
				if (i+1 >= args.length) {
					usage();
				}
				URIPrefix = args[i+1];
				i++;
			} else if (args[i].equals("-v")) {
				if (i+1 >= args.length) {
					usage();
				}
				version = args[i+1];
				i++;
			} else if (fileName.equals("")) {
				fileName = args[i];
			} else {
				usage();
			}
			i++;
		}
		if (fileName.equals("")) usage();
		try {
			if (!URIPrefix.equals("")) {
				SBOLReader.setURIPrefix(URIPrefix);
			}
			SBOLReader.setTypesInURI(typesInURI);
			SBOLReader.setVersion(version);
	        SBOLDocument doc = SBOLReader.read(fileName);
	        if (compliant) validateCompliance(doc);
	        if (complete) validateCompleteness(doc);
	        System.out.println("Validation successful, no errors.");
	        if (outputFile.equals("")) {
	        	SBOLWriter.write(doc, (System.out));
	        } else {
	        	SBOLWriter.write(doc, outputFile);
	        }
		}
		catch (Exception e) {
        	System.err.println("Validation failed.\n" + e.getMessage());
		}
		catch (Throwable e) {
        	System.err.println("Validation failed.\n" + e.getMessage());
		}
	}

}
