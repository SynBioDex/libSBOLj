package org.sbolstandard.core2.libraryTests;

import static org.junit.Assert.*;
import static org.sbolstandard.core2.util.URIcompliance.*;

import java.net.URI;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class UriComplianceTest {
	String uri1Str = "http://www.async.ece.utah.edu/LacI_Inv/1.0";
	String uri2Str = "http://www.AsYNc.eCe.utAH.edu/LacI_Inv/1.0";
	String uri3Str = "http://www.async.ece.utah.edu/LAcI_inV/1.0";
	String uri4Str = "http://www.async.ece.utah.edu/LacI_Inv/1/0";
	String uri5Str = "http://www.async.ece.utah.edu/LacI_Inv/LacIIn/1.0.1-alpha";
	String uri6Str = "http://www.async.ece.utah.edu/LacI_Inv/~LacIIn/1.0.1-alpha";	
	String uri7Str = "http://www.async.ece.utah.edu/LacI_Inv/interaction_1/participant1/1.0.1-beta";
	String uri8Str = "http://www.async.ece.utah.edu/LacI_Inv/interaction_1/participant#1/1.0.1";
	String uri9Str = "http://www.async.ece.utah.edu/pLac/ptetlacISeq/multi_range/p2@struct&Annotate_range*/1.0.02-alpha";
	String uri10Str = "http://www.async.ece.utah.edu/pLac/ptetlacISeq/multi_range/_p2_structAnnotate_range/1.0.02-SNAPSHOT";
	
	String uri1PersistIdStr = "http://www.async.ece.utah.edu/LacI_Inv";
	String uri5PersistIdStr = "http://www.async.ece.utah.edu/LacI_Inv/LacIIn";
	String uri7PersistIdStr = "http://www.async.ece.utah.edu/LacI_Inv/interaction_1/participant1";
	String uri10PersistIdStr = "http://www.async.ece.utah.edu/pLac/ptetlacISeq/multi_range/_p2_structAnnotate_range";
	String uri7prefixStr = "http://www.async.ece.utah.edu";
	String uri1toplevelDisplayId = "LacI_Inv";	
	String uri5childDisplayId = "LacIIn";
	String uri7grandChildDisplayId = "interaction_1";
	String uri10greatGrandChildDisplayId = "_p2_structAnnotate_range";
	
	
	URI uri1 = URI.create(uri1Str);
	URI uri2 = URI.create(uri2Str);
	URI uri3 = URI.create(uri3Str);
	URI uri4 = URI.create(uri4Str);
	URI uri5 = URI.create(uri5Str);
	URI uri6 = URI.create(uri6Str);
	URI uri7 = URI.create(uri7Str);
	URI uri8 = URI.create(uri8Str);
	URI uri9 = URI.create(uri9Str);
	URI uri10 = URI.create(uri10Str);

	@Test
	public void testURLcase() {
		assertEquals(uri1, uri2);
	}
	
	@Test
	public void testIDcase() {		
		assertThat(uri1, is(not(uri3)));	
	}
	
	/**
	 * Test <code>null<\code> for top-level URI not compliant in extractPersistentId.
	 */
	@Test
	public void testExtractPersistentId1() {
		String extractedPersistentId = extractPersistentId(uri4);
		//System.out.println(extractedPersistentId);
		assertNull(extractedPersistentId);
	}
		
	
	/**
	 * Test the extracted persistent ID string for the compliant top-level URI. 
	 */
	@Test
	public void testExtractPersistentId2() {
		String extractedPersistentId = extractPersistentId(uri1);
		//System.out.println(extractedPersistentId);
		assertEquals(uri1PersistIdStr, extractedPersistentId);		
	}
	
	/**
	 * Test <code>null<\code> for child URI not compliant in extractPersistentId.
	 */
	@Test
	public void testExtractPersistentId3() {
		String extractedPersistentId = extractPersistentId(uri6);
		//System.out.println(extractedPersistentId);
		assertNull(extractedPersistentId);
	}
	
	/**
	 * Test the extracted persistent ID string for the compliant child URI. 
	 */
	@Test
	public void testExtractPersistentId4() {
		String extractedPersistentId = extractPersistentId(uri5);
		//System.out.println(extractedPersistentId);
		assertEquals(uri5PersistIdStr, extractedPersistentId);		
	}
	
	/**
	 * Test <code>null<\code> for grand child URI not compliant in extractPersistentId.
	 */
	@Test
	public void testExtractPersistentId5() {
		String extractedPersistentId = extractPersistentId(uri8);
		//System.out.println(extractedPersistentId);
		assertNull(extractedPersistentId);
	}
	
	/**
	 * Test the extracted persistent ID string for the compliant grand child URI. 
	 */
	@Test
	public void testExtractPersistentId6() {
		String extractedPersistentId = extractPersistentId(uri7);
		//System.out.println(extractedPersistentId);
		assertEquals(uri7PersistIdStr, extractedPersistentId);		
	}
	
	/**
	 * Test <code>null<\code> for great grand child URI not compliant in extractPersistentId.
	 */
	@Test
	public void testExtractPersistentId7() {
		String extractedPersistentId = extractPersistentId(uri9);
		//System.out.println(extractedPersistentId);
		assertNull(extractedPersistentId);
	}
	
	/**
	 * Test the extracted persistent ID string for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractPersistentId8() {
		String extractedPersistentId = extractPersistentId(uri10);
		System.out.println(extractedPersistentId);
		assertEquals(uri10PersistIdStr, extractedPersistentId);		
	}
	
	/**
	 * Test <code>null<\code> for grand child URI not compliant in extractPersistentId.
	 */
	@Test
	public void testExtractURIprefix1() {
		String extractedPrefix = extractURIprefix(uri8);
		//System.out.println(extractedPersistentId);
		assertNull(extractedPrefix);
	}
	
	/**
	 * Test the extracted persistent ID string for the compliant grand child URI. 
	 */
	@Test
	public void testExtractURIprefix2() {
		String extractedURIprefix = extractURIprefix(uri7);
		//System.out.println(extractedURIprefix);
		assertEquals(uri7prefixStr, extractedURIprefix);		
	}
	
	/**
	 * Test the extracted top-level display ID string for the compliant top-level URI. 
	 */
	@Test
	public void testExtractDisplayId1() {
		String extractedDisplayId = extractDisplayId(uri1, 0);
		//System.out.println(extractedDisplayId);
		assertEquals(uri1toplevelDisplayId, extractedDisplayId);		
	}
	
	/**
	 * Test the extracted top-level display ID string for the compliant child URI. 
	 */
	@Test
	public void testExtractDisplayId2() {
		String extractedDisplayId = extractDisplayId(uri5, 0);
		// System.out.println(extractedDisplayId);
		assertEquals(uri1toplevelDisplayId, extractedDisplayId);		
	}
	
	/**
	 * Test the extracted top-level display ID string for the compliant grand child URI. 
	 */
	@Test
	public void testExtractDisplayId3() {
		String extractedDisplayId = extractDisplayId(uri7, 0);
		// System.out.println(extractedDisplayId);
		assertEquals(uri1toplevelDisplayId, extractedDisplayId);		
	}
	
	/**
	 * Test the extracted top-level display ID string for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractDisplayId4() {
		String extractedDisplayId = extractDisplayId(uri10, 0);
		//System.out.println(extractedDisplayId);
		assertEquals("pLac", extractedDisplayId);		
	}
	
	/**
	 * Test the extracted child display ID string for the compliant child URI. 
	 */
	@Test
	public void testExtractDisplayId5() {
		String extractedDisplayId = extractDisplayId(uri5, 1);
		// System.out.println(extractedDisplayId);
		assertEquals(uri5childDisplayId, extractedDisplayId);		
	}
	
	/**
	 * Test the extracted child display ID string for the compliant grand child URI. 
	 */
	@Test
	public void testExtractDisplayId6() {
		String extractedDisplayId = extractDisplayId(uri7, 1);
		//System.out.println(extractedDisplayId);
		assertEquals("interaction_1", extractedDisplayId);		
	}
	
	/**
	 * Test the extracted child display ID string for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractDisplayId7() {
		String extractedDisplayId = extractDisplayId(uri10, 1);
		//System.out.println(extractedDisplayId);
		assertEquals("ptetlacISeq", extractedDisplayId);		
	}

	/**
	 * Test the extracted grand child display ID string for the compliant grand child URI. 
	 */
	@Test
	public void testExtractDisplayId8() {
		String extractedDisplayId = extractDisplayId(uri7, 2);
		//System.out.println(extractedDisplayId);
		assertEquals("participant1", extractedDisplayId);		
	}

	/**
	 * Test the extracted grand child display ID string for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractDisplayId9() {
		String extractedDisplayId = extractDisplayId(uri10, 2);
		//System.out.println(extractedDisplayId);
		assertEquals("multi_range", extractedDisplayId);		
	}
	
	/**
	 * Test the extracted great grand child display ID string for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractDisplayId10() {
		String extractedDisplayId = extractDisplayId(uri10, 3);
		//System.out.println(extractedDisplayId);
		assertEquals("_p2_structAnnotate_range", extractedDisplayId);		
	}
	
	/**
	 * Test <code>null<\code> for extractDisplayId for the compliant child URI. 
	 */
	@Test
	public void testExtractDisplayId11() {
		String extractedDisplayId = extractDisplayId(uri6, 3);
		//System.out.println(extractedDisplayId);
		assertNull(extractedDisplayId);
	}
	
	/**
	 * Test <code>null<\code> for extractDisplayId for the compliant grand child URI. 
	 */
	@Test
	public void testExtractDisplayId12() {
		String extractedDisplayId = extractDisplayId(uri8, 3);
		//System.out.println(extractedDisplayId);
		assertNull(extractedDisplayId);
	}
	
	/**
	 * Test <code>null<\code> for extractDisplayId for the compliant great grand child URI. 
	 */
	@Test
	public void testExtractDisplayId13() {
		String extractedDisplayId = extractDisplayId(uri9, 3);
		//System.out.println(extractedDisplayId);
		assertNull(extractedDisplayId);
	}
	
	/**
	 * Test <code>null<\code> for extractVersion for the compliant top-level URI.
	 */
	@Test
	public void testExtractVersion1() {
		String extractedVersion = extractVersion(uri4);
		//System.out.println(extractedVersion);
		assertNull(extractedVersion);		
	}
	
	/**
	 * Test <code>null<\code> for extractVersion for the compliant child URI.
	 */
	@Test
	public void testExtractVersion2() {
		String extractedVersion = extractVersion(uri6);
		//System.out.println(extractedVersion);
		assertNull(extractedVersion);		
	}
	
	/**
	 * Test <code>null<\code> for extractVersion for the compliant grand child URI.
	 */
	@Test
	public void testExtractVersion3() {
		String extractedVersion = extractVersion(uri8);
		//System.out.println(extractedVersion);
		assertNull(extractedVersion);		

	}
	
	/**
	 * Test <code>null<\code> for extractVersion for the compliant great grand child URI.
	 */
	@Test
	public void testExtractVersion4() {
		String extractedVersion = extractVersion(uri9);
		//System.out.println(extractedVersion);
		assertNull(extractedVersion);		
	}
	
	/**
	 * Test the extracted version string for extractVersion for the compliant top-level URI.
	 */
	@Test
	public void testExtractVersion5() {
		String extractedVersion = extractVersion(uri1);
		//System.out.println(extractedVersion);
		assertEquals("1.0", extractedVersion);
	}
	
	/**
	 * Test the extracted version string for extractVersion for the compliant child URI.
	 */
	@Test
	public void testExtractVersion6() {
		String extractedVersion = extractVersion(uri5);
		//System.out.println(extractedVersion);
		assertEquals("1.0.1-alpha", extractedVersion);
	}
	
	/**
	 * Test the extracted version string for extractVersion for the compliant grand child URI.
	 */
	@Test
	public void testExtractVersion7() {
		String extractedVersion = extractVersion(uri7);
		//System.out.println(extractedVersion);
		assertEquals("1.0.1-beta", extractedVersion);
	}
	
	/**
	 * Test the extracted version string for extractVersion for the compliant great grand child URI.
	 */
	@Test
	public void testExtractVersion8() {
		String extractedVersion = extractVersion(uri10);
		//System.out.println(extractedVersion);
		assertEquals("1.0.02-SNAPSHOT", extractedVersion);
	}
		
	@Test
	public void testIsChildURIcompliant1() {
		assertFalse(isChildURIcompliant(uri1, uri5));
	}
	
	@Test
	public void testIsChildURIcompliant2() {
		assertTrue(isChildURIcompliant(URI.create("http://www.async.ece.utah.edu/LacI_Inv/interaction_1/1.0.1-beta"), uri7));
	}
	
	@Test
	public void testIsChildURIcompliant3() {
		assertTrue(isChildURIcompliant(URI.create("http://www.async.ece.utah.edu/pLac/ptetlacISeq/multi_range/1.0.02-SNAPSHOT"), uri10));
	}
	
	@Test
	public void testIsChildURIcompliant4() {
		assertFalse(isChildURIcompliant(URI.create("http://www.async.ece.utah.edu/LacI_Inv/interaction_1/1.0.1-alpha"), uri7));
	}
	
	@Test
	public void testIsDisplayIdCompliant1() {
		assertFalse(isDisplayIdCompliant("1/0"));
	}

	@Test
	public void testIsDisplayIdCompliant2() {
		assertFalse(isDisplayIdCompliant("+asYnc513+"));
	}

	@Test
	public void testIsDisplayIdCompliant3() {
		assertTrue(isDisplayIdCompliant("_l2I3DDv"));
	}

	@Test
	public void testIsTopLevelURIcompliant1() {
		assertTrue(isURIcompliant(uri1, 0));
	}
	
	@Test
	public void testIsTopLevelURIcompliant2() {
		assertFalse(isURIcompliant(uri5, 0));
	}
	
	@Test
	public void testIsTopLevelURIcompliant3() {
		assertFalse(isURIcompliant(uri10, 0));
	}

	@Test
	public void testIsTopLevelURIcompliant4() {
		assertFalse(isURIcompliant(uri4, 0));
	}
	
	// TODO: Test isVersionCompliant
}
