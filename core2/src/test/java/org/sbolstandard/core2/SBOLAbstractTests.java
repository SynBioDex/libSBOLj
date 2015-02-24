package org.sbolstandard.core2;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.sbolstandard.core2.abstract_classes.TopLevel;
public abstract class SBOLAbstractTests {

	//NOTE: All of these test cases does not do a deep check. The rdf file being compared with
	//      the parts generated will say it passes but it will not really check whether the two
	//      things being compared are the same.

	@Test
	/* Creates a single Collection with no members.
	 * Note: Works on SBOLReader but not SBOLWriter.
	 */
	public void valid01() throws Exception
	{
		runTest("test/data/singleCollection.rdf",
				get_Collection("myParts",
						SBOLTestUtils.createAnnotation(new QName("http://myannotation.org", "thisAnnotation", "annot"),
								SBOLTestUtils.createTurtle())));
	}

	public void valid02() throws Exception
	{
		runTest("test/data/singleCollection.rdf",
				get_Collection("myParts", null));
	}

	@Test //Creates a single GenericTopLevel
	public void valid03() throws Exception
	{
		runTest("test/data/singleGenericTopLevel.rdf", get_genericTopLevel("GenericTopLevel"));
	}

	@Test //Creates a single Sequence called pLacSeq
	public void valid04() throws Exception
	{
		runTest("test/data/single_pLacSeq.rdf", get_Sequence("pLacSeq"));
	}

	@Test //Creates a single Sequence called tetRSeq
	public void valid05() throws Exception
	{
		runTest("test/data/single_tetRSeq.rdf", get_Sequence("tetRSeq"));
	}

	@Test //Creates a single Sequence called pLacTetRSeq
	public void valid06() throws Exception
	{
		runTest("test/data/single_pLacTetRSeq.rdf", get_Sequence("pLactetRSeq"));
	}

	@Test //TODO: Still testing...
	public void valid07() throws Exception
	{
		//TODO: change the rdf file name to its' correct generated object
		runTest("test/data/singleGenericTopLevel.rdf", get_pLac("pLac"));
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

	public GenericTopLevel get_genericTopLevel (String topLevelName)
	{
		URI identity 		   = SBOLTestUtils.getURI(topLevelName);
		URI persistentIdentity = SBOLTestUtils.getURI(topLevelName);
		String displayId 	   = topLevelName;
		String name 		   = topLevelName;
		String description     = topLevelName;
		Integer majorVersion   = 1;
		Integer minorVersion   = 0;
		Timestamp timeStamp    = null;

		return SBOLTestUtils.createGenericTopLevel(
				identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);
	}

	public Sequence get_Sequence (String topLevelName)
	{
		URI identity 		   = SBOLTestUtils.getURI(topLevelName);
		URI persistentIdentity = SBOLTestUtils.getURI(topLevelName);
		String displayId 	   = topLevelName;
		String name 		   = topLevelName;
		String description     = topLevelName;
		Integer majorVersion   = 1;
		Integer minorVersion   = 0;
		Timestamp timeStamp    = null;
		String element 		   = topLevelName+ "_element";
		URI encoding		   = SBOLTestUtils.getURI(topLevelName);

		return SBOLTestUtils.createSequenceData(
				identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion,
				element, encoding);
	}

	public ComponentDefinition get_pLac (String topLevelName)
	{
		URI identity 		   = SBOLTestUtils.getURI(topLevelName);
		URI persistentIdentity = SBOLTestUtils.getURI(topLevelName);
		String displayId 	   = topLevelName;
		String name 		   = topLevelName;
		String description     = topLevelName;
		Integer majorVersion   = 1;
		Integer minorVersion   = 0;
		Timestamp timeStamp    = null;
		Set<URI> type      	   = SBOLTestUtils.getSetPropertyURI("DNA");
		Set<URI> roles         = SBOLTestUtils.getSetPropertyURI("Promoter");

		return SBOLTestUtils.createComponentDefinitionData(
				type, roles,
				identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion,
				get_Sequence("pLacSeq"), null, null, null);
	}

	public abstract void runTest(final String fileName, final TopLevel... contents)
			throws Exception;

}