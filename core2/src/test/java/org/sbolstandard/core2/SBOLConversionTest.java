package org.sbolstandard.core2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SBOLConversionTest {
	
	private File file;
	
	public SBOLConversionTest(File file) {
		this.file = file;
	}
	
	@Parameterized.Parameters
	public static java.util.Collection files() {
		File file_base = null ;
		try {
			file_base = new File(ValidationTest.class.getResource("/test/data/SBOL1/").toURI());
		}
		catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		java.util.Collection col = new HashSet();
		for (File f : file_base.listFiles()) {
			col.add(f);
		}
		return col;
	}

	//@Test
	public void test_SBOL1_Files() throws Exception
	{
		// TODO: should figure out why these fail
		if (file.getAbsolutePath().contains("miRNA_sbol.xml")) return;
		if (file.getAbsolutePath().contains("pACPc_invF.xml")) return;
		if (file.getAbsolutePath().contains("BBa_T9002.xml")) return;
		//if (f.getAbsolutePath().contains("BBa_I0462.xml")) continue;
		try
		{
			SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");
			SBOLReader.setDropObjectsWithDuplicateURIs(true);
			SBOLReader.setCompliant(true);
			SBOLDocument expected = SBOLReader.read(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			SBOLWriter.write(expected, out, SBOLDocument.RDFV1);
			SBOLDocument actual = SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
			if (!actual.equals(expected)) {
				System.out.println(file.getName() + " FAILED");
				//SBOLValidate.compareDocuments("expected", expected, "actual", actual);
				//break;
				assert(false);
				//throw new AssertionError("Failed for " + f.getName());
			} else {
				//System.out.println(f.getName() + " PASSED");
			}
		}
		catch (SBOLValidationException e)
		{
			System.out.println("Failed for " + file.getName() + "\n" + e.getMessage());
			assert(false);
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
