package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core2.SystemsBiologyOntology;

public class SystemsBiologyOntologyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_getDescendantsMethods() {
		SystemsBiologyOntology descendents = new SystemsBiologyOntology();
		assertTrue(descendents.getDescendantsOf(SystemsBiologyOntology.PRODUCT).contains("SBO:0000603"));
		assertTrue(descendents.getDescendantsOf(SystemsBiologyOntology.PRODUCT).size() == 1);
		assertTrue(descendents.getDescendantsOf("SBO:0000003").contains("SBO:0000603"));
		assertTrue(descendents.getDescendantURIsOf(SystemsBiologyOntology.PRODUCT).contains(SystemsBiologyOntology.SIDE_PRODUCT));
		assertTrue(descendents.getDescendantURIsOf("SBO:0000003").contains(SystemsBiologyOntology.SIDE_PRODUCT));
		assertTrue(descendents.getDescendantURIsOf(SystemsBiologyOntology.PRODUCT).size() == 1);

	}

}
