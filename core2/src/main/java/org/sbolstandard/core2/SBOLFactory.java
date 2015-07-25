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

package org.sbolstandard.core2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import uk.ac.ncl.intbio.core.io.CoreIoException;

//import org.sbolstandard.core.impl.CollectionImpl;
//import org.sbolstandard.core.impl.DnaComponentImpl;
//import org.sbolstandard.core.impl.DnaSequenceImpl;
//import org.sbolstandard.core.impl.SBOLDocumentImpl;
//import org.sbolstandard.core.impl.SBOLReaderImpl;
//import org.sbolstandard.core.impl.SBOLValidatorImpl;
//import org.sbolstandard.core.impl.SBOLWriterImpl;
//import org.sbolstandard.core.impl.SequenceAnnotationImpl;

/**
 * SBOL factory class that provides functionality to create SBOL objects and utility classes such as readers, writers,
 * and validators.
 *
 * @author Evren Sirin
 */
public class SBOLFactory {
	// This class only provides static fields, cannot be instantiated
	private SBOLFactory() {
	}

	/**
	 * Creates a fresh SBOL document instance and populates its contents from the given XML source. The reader will
	 * perform validation automatically. The documents read by this reader are guaranteed to be valid so it is not
	 * necessary to perform {@link #validate(SBOLDocument) validation}. If the contents of the document is changed
	 * afterwards, validation will be needed.
	 *
	 * @throws SBOLValidationException if the contents of the document is not valid
	 */
	public static SBOLDocument read(InputStream in) throws IOException, SBOLValidationException {
		return SBOLReader.read(in);
	}

	/**
	 * Writes the contents of an SBOL document in XML format to the given output stream. The writer will
	 * perform validation automatically.
	 *
	 * @throws SBOLValidationException if the contents of the document is not valid
	 */
	public static void write(SBOLDocument doc, OutputStream out) throws IOException, SBOLValidationException {
		try
		{
			SBOLWriter.write(doc, out);
		}
		catch (XMLStreamException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FactoryConfigurationError e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CoreIoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
