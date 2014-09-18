package org.sbolstandard.core2;


import java.io.IOException;
import java.io.InputStream;

import org.sbolstandard.core.SBOLDocument;
import org.sbolstandard.core.SBOLReader;
import org.sbolstandard.core.impl.SBOLReaderImpl;


/**
 * 
 * @author Zhen Zhang
 * @version 2.0
 *
 */
public class ConverterTest {

	private static final SBOLReader READER = new SBOLReaderImpl(false);//new SBOLReaderImpl(true);

	
	public SBOLDocument readLibSOBL1(final String fileName) throws Exception {

		InputStream resourceAsStream = ConverterTest.class.getResourceAsStream(fileName);
		if(resourceAsStream == null) {
			resourceAsStream = ConverterTest.class.getResourceAsStream("/" + fileName);
		}
		assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";

		try {						
			//SBOLDocument lib1Objects = READER.read(resourceAsStream);
			return READER.read(resourceAsStream);

		} catch(IOException e) {
			throw new AssertionError("Failed for " + fileName, e);
		}
	}
	
	

}
