//package org.sbolstandard.core2.test;
//
//import static uk.ac.ncl.intbio.core.datatree.Datatree.DocumentRoot;
//import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
//import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBindings;
//import static uk.ac.ncl.intbio.core.datatree.Datatree.TopLevelDocuments;
//
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.xml.namespace.QName;
//import javax.xml.stream.FactoryConfigurationError;
//import javax.xml.stream.XMLStreamException;
//
//import org.sbolstandard.core2.Component;
//import org.sbolstandard.core2.SBOLDocument;
//import org.sbolstandard.core2.SBOLWriter;
//import org.sbolstandard.core2.StructuralAnnotation;
//import org.sbolstandard.core2.StructuralConstraint;
//import org.sbolstandard.core2.StructuralInstantiation;
//import org.sbolstandard.core2.Structure;
//import org.sbolstandard.core2.TopLevel;
//import org.sbolstandard.core2.abstract_classes.Documented;
//import org.sbolstandard.core2.abstract_classes.Identified;
//
//import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
//import uk.ac.ncl.intbio.core.io.CoreIoException;
//
//public class writeTester_myDNA {
//
//	public static void main(String[] args) {
////		SBOLDocument SBOL2Doc_test = new SBOLDocument(); 
////		get_myDNA(SBOL2Doc_test);
////		SBOLWriter.write(SBOL2Doc_test,(System.out));
//	}
//
//	public static void write(SBOLDocument doc, OutputStream out) throws XMLStreamException, FactoryConfigurationError, CoreIoException {
//
////		writeRdf(new OutputStreamWriter(out), 
////				DocumentRoot( NamespaceBindings(NamespaceBinding("http://sbols.org/v2#","sbol2")),
////						TopLevelDocuments(getTopLevelDocument(doc))));
//	}
//	
////	private static List<TopLevelDocument<QName>> getTopLevelDocument(SBOLDocument doc) {
////		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
////		formatCollections(doc.getCollections(), topLevelDoc);
////		formatModules(doc.getModules(), topLevelDoc); 		
////		formatModels(doc.getModels(), topLevelDoc); 			
////		formatComponents(doc.getComponents(), topLevelDoc);  
////		formatStructures(doc.getStructures(), topLevelDoc); 
////		return topLevelDoc;
////	}
//	
//	/*
//	 * SBOLDocument SBOL2Doc_test, 
//			Set<URI> type, Set<URI> roles,
//			List<String> componentData,
//			Structure structureData, 
//			List<StructuralInstantiation> structureInstantiationData, 
//			List<StructuralAnnotation> structureAnnotationData, 
//			List<StructuralConstraint> structureConstraintData)
//	 */
//	private static Component get_myDNA (SBOLDocument SBOL2Doc_test)
//	{
//		return createComponentData(SBOL2Doc_test,
//				getSetOfURI("chebi:DNA/1/0"),
//				getSetOfURI("so:region/1/0"),
//				getData("MyDnaComponent/1/0","MyDnaComponent","1.0","MyDnaComponent","myDNA", "This is a very simple example"),
//				get_pLacSeq(SBOL2Doc_test), 
//				null, null, null); 	
//	}
//	
//	private static Structure get_pLacSeq (SBOLDocument SBOL2Doc_test)
//	{
//		return createStructureData(SBOL2Doc_test, 
//				getData("pLacSeq/1/0","pLacSeq","1.0","pLacSeq","pLacSeq", "pLacSeq", "pLacSeq_element", "pLacSeq/pLacSeq_encoding/1/0")); 
//	}
//	
//	private static Component createComponentData(SBOLDocument SBOL2Doc_test, 
//			Set<URI> type, Set<URI> roles,
//			List<String> componentData,
//			Structure structureData, 
//			List<StructuralInstantiation> structureInstantiationData, 
//			List<StructuralAnnotation> structureAnnotationData, 
//			List<StructuralConstraint> structureConstraintData)
//	{
//		URI identity 		   = getURI(componentData.get(0));
//		URI persistentIdentity = getURI(componentData.get(1));
//		String version 		   = componentData.get(2);
//		String displayId 	   = componentData.get(3);
//		String name 		   = componentData.get(4);
//		String description 	   = componentData.get(5);
//		
//		Component c = SBOL2Doc_test.createComponent(identity, type, roles);
//		setCommonTopLevelData(c, identity, persistentIdentity, version, displayId, name, description);
//		
//		if(structureData != null)
//			c.setStructure(structureData.getIdentity()); 
//		if(structureInstantiationData != null)
//		{
//			c.setStructuralInstantiations(structureInstantiationData);
//			if(structureAnnotationData != null && structureConstraintData == null)
//				c.setStructuralAnnotations(structureAnnotationData);
//			else if(structureConstraintData != null)
//				c.setStructuralConstraints(structureConstraintData);
//		}
//		
//		return c; 
//	}
//	
//	private static Structure createStructureData(SBOLDocument SBOL2Doc_test, List<String> structureData)
//	{
//		URI identity 		   = getURI(structureData.get(0)); 
//		URI persistentIdentity = getURI(structureData.get(1)); 
//		String version 		   = structureData.get(2); 
//		String displayId 	   = structureData.get(3); 
//		String name 		   = structureData.get(4); 
//		String description 	   = structureData.get(5); 
//		String element 		   = structureData.get(6); 
//		URI encoding  		   = getURI(structureData.get(7));
//		
//		Structure structure = SBOL2Doc_test.createStructure(identity, element, encoding);
//		setCommonTopLevelData(structure, identity, persistentIdentity, version, displayId, name, description);
//
//		return structure;
//	}
//	
//	private static void setCommonTopLevelData (TopLevel t, URI identity, URI persistentIdentity,
//			String version, String displayId, String name, String description)
//	{
//		setCommonDocumentedData(t, identity, persistentIdentity, version, displayId, name, description);
//	}
//	
//	private static void setCommonDocumentedData (Documented d, URI identity, URI persistentIdentity,
//			String version, String displayId, String name, String description)
//	{
//		d.setDisplayId(displayId);
//		d.setName(name);
//		d.setDescription(description);
//		
//		setCommonIdentifiedData(d, identity, persistentIdentity, version);
//	}
//	
//	private static void setCommonIdentifiedData (Identified i, URI identity, URI persistentIdentity,
//			String version)
//	{
//		i.setIdentity(identity);
//		i.setPersistentIdentity(persistentIdentity);
//		i.setVersion(version);
////		i.setTimeStamp(timeStamp);
//	}
//	
//	/**
//	 * data[] = identity
//	 * data[] = persistentIdentity
//	 * data[] = version
//	 * data[] = displayID
//	 * data[] = Name
//	 * data[] = Description
//	 * @param data
//	 * @return
//	 */
//	private static List<String> getData(String ... data)
//	{
//		List<String> list = new ArrayList<String>();
//		for(String d : data)
//		{
//			list.add(d);
//		}
//		return list;
//	}
//	
//	private static Set<URI> getSetOfURI(String ... appends)
//	{
//		Set<URI> list = new HashSet<URI>();
//		for(String append : appends)
//		{
//			list.add(getURI(append));
//		}
//		return list; 
//	}
//	
//	private static URI getURI(String append)
//	{
//		return URI.create("http://example.com/" + append);
//	}
//
//}
