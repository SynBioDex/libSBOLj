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

package org.sbolstandard.core.impl;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.sbolstandard.core.SBOLFactory;

/**
 * Defines several constants used for JAXB implementation
 * 
 * @author Evren Sirin
 */
public class JAXB {
	// This class only provides static fields, cannot be instantiated
	private JAXB() {		
	}
	
	/**
     * The shared JAXB context.
     */
    public static final JAXBContext CONTEXT;
    
	/**
     * The shared schema instance.
     */
	public static final Schema SCHEMA;
	
	static {
		try {
	        CONTEXT = JAXBContext.newInstance(SBOLDocumentImpl.class); 	 
        }
        catch (Exception e) {
        	System.err.println("Cannot initialize JAXB context");
        	e.printStackTrace();
	        throw new RuntimeException(e);
        }
        
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			URL sbolSchema = SBOLFactory.class.getResource("/sbol.xsd");
			URL rdfSchema = SBOLFactory.class.getResource("/rdf.xsd");			 
			
			Source[] sources= { 
				new StreamSource(sbolSchema.openStream(), sbolSchema.toString()), 
				new StreamSource(rdfSchema.openStream(), rdfSchema.toString())
			};

			SCHEMA = factory.newSchema(sources);
        }
        catch (Exception e) {
        	System.err.println("Cannot load SBOL schema");
        	e.printStackTrace();
	        throw new RuntimeException(e);
        }
        
	}

}
