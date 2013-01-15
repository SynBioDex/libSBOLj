package org.sbolstandard.core;

import static org.sbolstandard.core.SBOLTestUtils.assertInvalid;
import static org.sbolstandard.core.SBOLTestUtils.assertValid;

import org.junit.Ignore;
import org.junit.Test;

public class SBOLValidatorTest {
	@Test
	public void valid01() throws Exception {
		assertValid("test/data/valid01_dna_component_empty.xml");
	}

	@Test
	public void valid02() throws Exception {
		assertValid("test/data/valid02_dna_component.xml");
	}

	@Test
	public void valid03_dna() throws Exception {
		assertValid("test/data/valid03_dna_component_sequence.xml");
	}

	@Test
	public void valid04() throws Exception {
		assertValid("test/data/valid04_dna_component_annotation.xml");
	}

	@Test
	public void valid05() throws Exception {
		assertValid("test/data/valid05_dna_component_so_type.xml");
	}

	@Test
	public void valid06() throws Exception {
		assertValid("test/data/valid06_dna_component_so_types.xml");
	}

	@Test
	public void valid07() throws Exception {
		assertValid("test/data/valid07_dna_component_extension.xml");
	}

	@Test
	public void valid08() throws Exception {
		assertValid("test/data/valid08_dna_component_detailed.xml");
	}

	@Test
	public void valid09() throws Exception {
		assertValid("test/data/valid09_dna_component_multiple.xml");
	}

	@Test
	public void valid10() throws Exception {
		assertValid("test/data/valid10_collection_empty.xml");
	}

	@Test
	public void valid11() throws Exception {
		assertValid("test/data/valid11_collection_components.xml");
	}

	@Test
	public void valid12() throws Exception {
		assertValid("test/data/valid12_collection_multiple.xml");
	}

	@Test
	public void valid13() throws Exception {
		assertValid("test/data/valid13_dna_sequence.xml");
	}

	@Test
	public void invalid01() throws Exception {
		assertInvalid("test/data/invalid01_missing_displayId.xml",
		                "Invalid content was found starting with element 'description'");
	}

	@Test
	public void invalid02() throws Exception {
		assertInvalid("test/data/invalid02_no_rdf_root.xml", "Cannot find the declaration of element 'DnaComponent'");
	}

	@Test
	public void invalid03() throws Exception {
		assertInvalid("test/data/invalid03_bad_ordering.xml", "Invalid content was found starting with element 'name'");
	}

	@Test
	public void invalid04() throws Exception {
		assertInvalid("test/data/invalid04_bad_nucleotide_char.xml", "Value 'jtccctatcagtgat' is not facet-valid");
	}

	@Test
	public void invalid05() throws Exception {
		assertInvalid("test/data/invalid05_empty_nucleotides.xml", "Value '' is not facet-valid");
	}

	@Test
	public void invalid06() throws Exception {
		assertInvalid("test/data/invalid06_missing_nucleotides.xml",
		                "The content of element 'DnaSequence' is not complete");
	}

	@Test
	public void invalid07() throws Exception {
		assertInvalid("test/data/invalid07_negative_bioStart.xml", "Value '-1' is not facet-valid");
	}

	@Test
	public void invalid08() throws Exception {
		assertInvalid("test/data/invalid08_bioStart_exists_bioEnd_missing.xml",
		                "Invalid content was found starting with element 'strand'");
	}

	@Test
	public void invalid09() throws Exception {
		assertInvalid("test/data/invalid09_multiple_bioStart.xml",
		                "Invalid content was found starting with element 'bioStart'");
	}

	@Test
	public void invalid10() throws Exception {
		assertInvalid("test/data/invalid10_bad_strand.xml", "Value '*' is not facet-valid");
	}

	@Test
	public void invalid11() throws Exception {
		assertInvalid("test/data/invalid11_standalone_sequence_annotation.xml",
		                "Invalid content was found starting with element 'SequenceAnnotation'");
	}

	@Test
	public void invalid12() throws Exception {
		assertInvalid("test/data/invalid12_subComponent_missing.xml",
		                "The content of element 'SequenceAnnotation' is not complete");
	}

	@Ignore @Test
	public void invalid13() throws Exception {
		assertInvalid("test/data/invalid13_type_not_so.xml",
		                "Value 'http://partsregistry.org/type/signalling' is not facet-valid");
	}

	@Test
	public void invalid14() throws Exception {
		assertInvalid("test/data/invalid14_cyclic_precedes.xml", "Cyclic precedes relation");
	}

	@Test
	public void invalid15() throws Exception {
		assertInvalid("test/data/invalid15_inconsistent_precedes.xml", "Inconsistent precedes and relative position");
	}

	@Test
	public void invalid16() throws Exception {
		assertInvalid("test/data/invalid16_bioStart_exceeds_bioEnd.xml", "Inconsistent bioStart and bioEnd values");
	}

	@Test
	public void invalid17() throws Exception {
		assertInvalid("test/data/invalid17_annotation_dna_sequence_length.xml",
		                "DnaSequence length does not match bioStart and bioEnd values");
	}

	@Test
	public void invalid18() throws Exception {
		assertInvalid("test/data/invalid18_annotation_dna_sequence_contents.xml",
		                "Sequence in the annotation does not match the parent DnaComponent sequence");
	}

	@Test
	public void invalid19() throws Exception {
		assertInvalid("test/data/invalid19_no_uri.xml", "Attribute 'about' must appear on element 'DnaComponent'");
	}
}