package org.sbolstandard.core2;

import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import uk.ac.ncl.intbio.core.io.CoreIoException;

public class writeTester_myDNA {

	public static void main(String[] args) {
				SBOLDocument SBOL2Doc_test = new SBOLDocument();
				get_myDNA(SBOL2Doc_test);
		//		SBOLWriter.write(SBOL2Doc_test,(System.out));
	}

	public static void write(SBOLDocument doc, OutputStream out) throws XMLStreamException, FactoryConfigurationError, CoreIoException {

		//		writeRdf(new OutputStreamWriter(out),
		//				DocumentRoot( NamespaceBindings(NamespaceBinding("http://sbols.org/v2#","sbol2")),
		//						TopLevelDocuments(getTopLevelDocument(doc))));
	}

	//	private static List<TopLevelDocument<QName>> getTopLevelDocument(SBOLDocument doc) {
	//		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
	//		formatCollections(doc.getCollections(), topLevelDoc);
	//		formatModules(doc.getModules(), topLevelDoc);
	//		formatModels(doc.getModels(), topLevelDoc);
	//		formatComponents(doc.getComponents(), topLevelDoc);
	//		formatStructures(doc.getStructures(), topLevelDoc);
	//		return topLevelDoc;
	//	}

	/*
	 * SBOLDocument SBOL2Doc_test,
			Set<URI> type, Set<URI> roles,
			List<String> componentData,
			Structure structureData,
			List<StructuralInstantiation> structureInstantiationData,
			List<StructuralAnnotation> structureAnnotationData,
			List<StructuralConstraint> structureConstraintData)
	 */
	private static ComponentDefinition get_myDNA (SBOLDocument SBOL2Doc_test)
	{
		return createComponentData(SBOL2Doc_test,
				getSetOfURI("chebi:DNA/1/0"),
				getSetOfURI("so:region/1/0"),
				getData("MyDnaComponent/1/0","MyDnaComponent","1.0","MyDnaComponent","myDNA", "This is a very simple example"),
				"pLacSeq",
				null, null, null);
	}

	private static ComponentDefinition createComponentData(SBOLDocument SBOL2Doc_test,
			Set<URI> type, Set<URI> roles,
			List<String> componentData,
			String structureData,
			List<Component> structureInstantiationData,
			List<SequenceAnnotation> structureAnnotationData,
			List<SequenceConstraint> structureConstraintData)
	{
		URI identity 		   = getURI(componentData.get(0));
		URI persistentIdentity = getURI(componentData.get(1));
		String version 		   = componentData.get(2);
		String displayId 	   = componentData.get(3);
		String name 		   = componentData.get(4);
		String description 	   = componentData.get(5);

		ComponentDefinition c = SBOL2Doc_test.createComponentDefinition(identity, type);
		//		ComponentDefinition c = SBOL2Doc_test.createComponentDefinition(identity, type, roles);
		setCommonTopLevelData(c, identity, persistentIdentity, version, displayId, name, description);

		if(structureData != null)
			c.setSequence(structureData,version);
		if(structureInstantiationData != null)
		{
			c.setComponents(structureInstantiationData);
			//			c.setSubComponents(structureInstantiationData);
			if(structureAnnotationData != null && structureConstraintData == null)
				c.setSequenceAnnotations(structureAnnotationData);
			else if(structureConstraintData != null)
				c.setSequenceConstraints(structureConstraintData);
		}

		return c;
	}

	private static Sequence createSequenceData(SBOLDocument SBOL2Doc_test, List<String> structureData)
	{
		URI identity 		   = getURI(structureData.get(0));
		URI persistentIdentity = getURI(structureData.get(1));
		String version 		   = structureData.get(2);
		String displayId 	   = structureData.get(3);
		String name 		   = structureData.get(4);
		String description 	   = structureData.get(5);
		String element 		   = structureData.get(6);
		URI encoding  		   = getURI(structureData.get(7));

		Sequence structure = SBOL2Doc_test.createSequence(identity, element, encoding);
		setCommonTopLevelData(structure, identity, persistentIdentity, version, displayId, name, description);

		return structure;
	}

	private static void setCommonTopLevelData(TopLevel t, URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description)
	{
		setCommonDocumentedData(t, identity, persistentIdentity, version, displayId, name, description);
	}

	private static void setCommonDocumentedData (Documented d, URI identity, URI persistentIdentity,
			String version, String displayId, String name, String description)
	{
		d.setDisplayId(displayId);
		d.setName(name);
		d.setDescription(description);

		setCommonIdentifiedData(d, identity, persistentIdentity, version);
	}

	private static void setCommonIdentifiedData(Identified i, URI identity, URI persistentIdentity,
			String version)
	{
		i.setIdentity(identity);
		i.setPersistentIdentity(persistentIdentity);
		i.setVersion(version);
		//		i.setTimeStamp(timeStamp);
	}

	/**
	 * data[] = identity
	 * data[] = persistentIdentity
	 * data[] = version
	 * data[] = displayID
	 * data[] = Name
	 * data[] = Description
	 * @param data
	 * @return
	 */
	private static List<String> getData(String ... data)
	{
		List<String> list = new ArrayList<String>();
		for(String d : data)
		{
			list.add(d);
		}
		return list;
	}

	private static Set<URI> getSetOfURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list;
	}

	private static URI getURI(String append)
	{
		return URI.create("http://example.com/" + append);
	}

}
