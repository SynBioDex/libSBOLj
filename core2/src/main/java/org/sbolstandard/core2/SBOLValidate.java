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
				+ "converting from SBOL 1.1 to SBOL " + SBOLVersion + ", if necessary,\n" 
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
	
	/**
	 * Validate SBOL objects are compliant in the given {@code sbolDocument}.
	 * 
	 * @param sbolDocument
	 * @throws SBOLValidationException if any top-level objects or any of their children or grandchildren 
	 * in the given {@code sbolDocument} contain a non-compliant URI.
	 */
	public static void validateCompliance(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			if (!URIcompliance.isTopLevelURIcompliant(collection) || !collection.checkDescendantsURIcompliance()) 
				throw new SBOLValidationException("Collection contains non-compliant URI",collection);
		}
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!URIcompliance.isTopLevelURIcompliant(sequence) || !sequence.checkDescendantsURIcompliance()) {
				throw new SBOLValidationException("Sequence contains non-compliant URI",sequence);
			}
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (!URIcompliance.isTopLevelURIcompliant(componentDefinition) || !componentDefinition.checkDescendantsURIcompliance()) {
				throw new SBOLValidationException("Component definition contains non-compliant URI",componentDefinition);
			}
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (!URIcompliance.isTopLevelURIcompliant(moduleDefinition) || !moduleDefinition.checkDescendantsURIcompliance()) 	
				throw new SBOLValidationException("Module definition contains non-compliant URI",moduleDefinition);
		}
		for (Model model : sbolDocument.getModels()) {
			if (!URIcompliance.isTopLevelURIcompliant(model) || !model.checkDescendantsURIcompliance()) 
				throw new SBOLValidationException("Model contains non-compliant URI",model);
		}
		for (GenericTopLevel genericTopLevel : sbolDocument.getGenericTopLevels()) {
			if (!URIcompliance.isTopLevelURIcompliant(genericTopLevel) || !genericTopLevel.checkDescendantsURIcompliance()) 
				throw new SBOLValidationException("Generic top level contains non-compliant URI",genericTopLevel);
		}
	}

	/**
	 * Validate if all URI references to SBOL objects are in the same given {@code sbolDocument}.
	 * 
	 * @param sbolDocument
	 * @throws SBOLValidationException if any reference made by Collection, ComponentDefinition,
	 * or ModuleDefinition is not in the given {@code sbolDocument}
	 */
	public static void validateCompleteness(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			if (!collection.isComplete()) 
				throw new SBOLValidationException("Collection is not complete",collection);
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (!componentDefinition.isComplete()) 
				throw new SBOLValidationException("Component definition is not complete",componentDefinition);
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (!moduleDefinition.isComplete()) 	
				throw new SBOLValidationException("Module definition is not complete",moduleDefinition);
		}
	}
	
	/**
	 * Command line method for reading an input file and producing an output file. 
	 * <p>
	 * By default, validations on compliance and completeness are performed, and types
	 * for top-level objects are not used in URIs.
	 * <p>
	 * Options:
	 * <p> 
	 * "-t" uses types in URIs,
	 * <p>
	 * "-i" turns off completeness checking,
	 * <p>
	 * "-n" indicates a non-compliant SBOL document,
	 * <p>
	 * "-o" specifies an output filename,
	 * <p>
	 * "-p" specifies the default URI prefix of the output file, and 
	 * <p>
	 * "-v" returns the version of the libSBOLj. 
	 * 
	 * @param args
	 */
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
	        doc.setTypesInURIs(typesInURI);
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
