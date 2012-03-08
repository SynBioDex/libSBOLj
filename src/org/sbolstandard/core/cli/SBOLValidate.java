/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core.cli;

import java.io.FileInputStream;
import java.io.IOException;

import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLFactory;
import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core.SBOLVersion;
import org.sbolstandard.core.util.SBOLPrettyWriter;

/**
 * Command-line interface for validating SBOL documents. This program parses an SBOL document from the given file and
 * validates the contents. If validation is successful the contents of the document is written on standard output in a
 * human readable format. If not, validation errors are printed on standard error.
 * 
 * @author Evren Sirin
 */
public class SBOLValidate {
	private SBOLValidate() {		
	}
	
	private static void usage() {		
		System.err.println("libSBOLj version " + SBOLVersion.getInstance().getVersionString());
		System.err.println("Description: Validates the contents of an SBOL document and prints the document contents if "
		                   + "validation succeeds");
		System.err.println("Usage:");
		System.err.println("\tjava --jar libSBOLj.jar [--quiet] <filename.xml>");
		System.exit(1);
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1 || args.length > 2) {
			usage();
		}

		boolean quiet = args[0].equals("--quiet");

		if (quiet && args.length == 1) {
			usage();
		}
		
		String fileName = args[quiet ? 1 : 0];
		
		try {
	        SBOLDocument doc = SBOLFactory.read(new FileInputStream(fileName));
	        System.out.println("Validation successful, no errors.");
	        if (!quiet) {
	        	new SBOLPrettyWriter().write(doc, System.out);
	        }
        }
        catch (IOException e) {
	        System.err.println("I/O ERROR: " + e.getMessage());
        }
        catch (SBOLValidationException e) {
        	System.err.println("Validation failed, error: " + e.getMessage());
        }
	}
}
