package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import javax.xml.namespace.QName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.GenericTopLevel;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;

public class GenericTopLevelTest {
	private SBOLDocument doc = null;
	@Before
	public void setUp() throws Exception {
		String prURI="http://partsregistry.org";
		doc = new SBOLDocument();
		doc.setDefaultURIprefix(prURI);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void test_toString() throws SBOLValidationException
	{
		GenericTopLevel topLevel=doc.createGenericTopLevel(
				"datasheet1",
				"",
				new QName("http://partsregistry.org", "Datasheet", ""));
		
		String topLevel_string = "datasheet1" + '@' + Integer.toHexString(topLevel.hashCode());
		equals(topLevel_string == topLevel.toString());
		
	}
	

}
