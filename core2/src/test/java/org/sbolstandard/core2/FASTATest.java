package org.sbolstandard.core2;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Random;

import org.junit.Test;

public class FASTATest {
	

//	@Test
//	public void testWriteSequenceString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWriteSBOLDocumentString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWriteSequenceFile() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWriteSBOLDocumentFile() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWriteSequenceOutputStream() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWriteSBOLDocumentOutputStream() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public void testReadInputStreamStringStringStringURI() {
		String URIprefix = "http://sbols.org/test";
		String version = "1.0";


		// one FASTA entry
		String fasta = 
				">test" + SBOLTestUtils.NEWLINE + 
				"acgt";
		try (
				ByteArrayInputStream bais = 
					new ByteArrayInputStream(fasta.getBytes());
			) {
		
			SBOLDocument doc = FASTA.read(
					bais, URIprefix, "test", version, Sequence.IUPAC_DNA);

			assertTrue(doc.getSequences().size() == 1);

			assertTrue(null != doc.getSequence("test", version));
		} catch(Exception e) {
			assertTrue(false);	// no exception allowed
		}

//		// two FASTA entries
//		fasta = ">test1" + NEWLINE + 
//				"acgt" + NEWLINE +
//				">test2" + NEWLINE +
//				"cgta";
//		try (
//				ByteArrayInputStream bais = 
//					new ByteArrayInputStream(fasta.getBytes());
//			) {
//		
//			SBOLDocument doc = FASTA.read(
//					bais, URIprefix, "test", version, Sequence.IUPAC_DNA);
//
//			assertTrue(doc.getSequences().size() == 2);
//
//			// how can I retrieve a sequence nicely from the Document?
//			assertTrue(null != doc.getSequence("test", version));
//		} catch(Exception e) {
//			assertTrue(false);	// no exception allowed
//		}
	
		// one multi-line FASTA entry
		fasta = ">test1" + SBOLTestUtils.NEWLINE + 
				"acgt" + SBOLTestUtils.NEWLINE +
				"acgt" + SBOLTestUtils.NEWLINE;
		try (
				ByteArrayInputStream bais = 
					new ByteArrayInputStream(fasta.getBytes());
			) {
		
			SBOLDocument doc = FASTA.read(
					bais, URIprefix, "test", version, Sequence.IUPAC_DNA);

			assertTrue(doc.getSequences().size() == 1);

			// how can I retrieve a sequence nicely from the Document?
			assertTrue(null != doc.getSequence("test", version));
			Sequence seq = doc.getSequence("test", version);
			assertTrue("acgtacgt".equals(seq.getElements()));
		} catch(Exception e) {
			assertTrue(false);	// no exception allowed
		}
	}
	

//	@Test
//	public void testReadFileStringStringStringURI() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testReadStringStringStringStringURI() {
//		fail("Not yet implemented"); // TODO
//	}

}