package org.sbolstandard.core2;

import java.net.URI;
import java.sql.Timestamp;

import org.junit.Test;
public abstract class SBOLAbstractTests {

	//NOTE: All of these test cases does not do a deep check. The rdf file being compared with
	//      the parts generated will say it passes but it will not really check whether the two
	//      things being compared are the same.

	//	@Test
	/* Creates a single Collection with no members.
	 * Note: Works on SBOLReader but not SBOLWriter.
	 */
	//	public void valid01() throws Exception
	//	{
	//		// TODO: build SBOL document here to send
	//		runTest("test/data/singleCollection.rdf",
	//				get_Collection("myParts",
	//						SBOLTestUtils.createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
	//								SBOLTestUtils.createTurtle())));
	//	}
	//
	//	public void valid02() throws Exception
	//	{
	//		runTest("test/data/singleCollection.rdf",
	//				get_Collection("myParts", null));
	//	}
	//
	//	@Test //Creates a single GenericTopLevel
	//	public void valid03() throws Exception
	//	{
	//		runTest("test/data/singleGenericTopLevel.rdf", get_genericTopLevel("GenericTopLevel"));
	//	}

	@Test //Creates a single Sequence called pLacSeq
	public void valid04() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		SBOLTestUtils.createSequence(document,"pLacSeq");

		runTest("test/data/single_pLacSeq.rdf", document);
	}

	@Test //Creates a single Sequence called tetRSeq
	public void valid05() throws Exception
	{
		SBOLDocument document = new SBOLDocument();
		SBOLTestUtils.createSequence(document,"pLacSeq");
		SBOLTestUtils.createSequence(document,"tetRSeq");
		SBOLTestUtils.createSequence(document,"pLactetRSeq");
		runTest("test/data/multiple_sequences.rdf", document);
	}

	@Test //TODO: Still testing...
	public void valid07() throws Exception
	{

	}

	public Collection get_Collection (String topLevelName, Annotation...annotation)
	{
		URI identity 		   = SBOLTestUtils.getURI(topLevelName);
		URI persistentIdentity = SBOLTestUtils.getURI(topLevelName);
		String displayId 	   = topLevelName;
		String name 		   = topLevelName;
		String description 	   = topLevelName;
		Integer majorVersion   = 1;
		Integer minorVersion   = 0;
		Timestamp timeStamp    = null; //TODO: should timeStamp be given or is it default?

		if(annotation == null)
			return SBOLTestUtils.createCollection(
					identity, persistentIdentity, displayId,
					name, description, majorVersion, minorVersion,null);

		return SBOLTestUtils.createCollection(
				identity, persistentIdentity, displayId,
				name, description, majorVersion, minorVersion,
				SBOLTestUtils.getAnnotation_List(annotation));

	}

	public abstract void runTest(final String fileName, final SBOLDocument expected)
			throws Exception;

}