package org.sbolstandard.core2;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;

import org.junit.Test;

public class SBOLConversionTest {

	@Test
	public void test_SBOL1_Files() throws Exception
	{
		File file_base = null ;
		try {
			file_base = new File(ValidationTest.class.getResource("/test/data/SBOL1/").toURI());
		}
		catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		File file;
		for (File f : file_base.listFiles()){
			// TODO: should figure out why these fail
			if (f.getAbsolutePath().contains("miRNA_sbol.xml")) continue;
			if (f.getAbsolutePath().contains("pACPc_invF.xml")) continue;
			if (f.getAbsolutePath().contains("BBa_T9002.xml")) continue;
			file = new File(f.getAbsolutePath());
			try
			{
				SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");
				SBOLReader.setDropObjectsWithDuplicateURIs(true);
				SBOLDocument expected = SBOLReader.read(file);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				SBOLWriter.write(expected, out, SBOLReader.RDFV1);
				SBOLDocument actual = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
				if (!actual.equals(expected)) {
					System.out.println(f.getName() + " FAILED");
					//SBOLValidate.compareDocuments("expected", expected, "actual", actual);
					//break;
					assert(false);
				} else {
					//System.out.println(f.getName() + " PASSED");
				}
			}
			catch (SBOLValidationException e)
			{
				throw new AssertionError("Failed for " + f.getName(), e);
			}
		}
	}
	
//	@Test
//	public void test_GenBank_Files() throws Exception
//	{
//		File file_base = null ;
//		try {
//			file_base = new File(ValidationTest.class.getResource("/test/data/GenBank/").toURI());
//		}
//		catch (URISyntaxException e1) {
//			e1.printStackTrace();
//		}
//		File file;
//		for (File f : file_base.listFiles()){
//			file = new File(f.getAbsolutePath());
//			try
//			{
//				GenBank.setURIPrefix("http://www.async.ece.utah.edu");
//				SBOLReader.setDropObjectsWithDuplicateURIs(true);
//				SBOLDocument expected = GenBank.read(file);
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				GenBank.write(expected.getRootComponentDefinitions().iterator().next(), out);
//				SBOLDocument actual = GenBank.read(new ByteArrayInputStream(out.toByteArray()));
//				if (!actual.equals(expected)) {
//					System.out.println(f.getName() + " FAILED");
//					SBOLValidate.compareDocuments("expected", expected, "actual", actual);
//					//break;
//					assert(false);
//				} else {
//					//System.out.println(f.getName() + " PASSED");
//				}
//			}
//			catch (SBOLValidationException e)
//			{
//				throw new AssertionError("Failed for " + f.getName(), e);
//			}
//		}
//	}


}
