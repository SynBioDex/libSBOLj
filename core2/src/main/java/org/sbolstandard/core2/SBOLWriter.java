package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.net.URI;
//import java.util.Collection;
import java.util.List;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import static uk.ac.ncl.intbio.core.datatree.Datatree.*;

import javanet.staxutils.IndentingXMLStreamWriter;
import javax.xml.namespace.QName; 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Zhen Zhang and Tramy Nguyen
 * @version 2.0
 *
 */
public class SBOLWriter {
	  
	/*
	 * Note: 
	 * all checks for != null will be change to isSet()
	 * All getIdentity were removed. 
	 * 
	 * TODO:
	 * make contract headers for PUBLIC methods only
	 * make various types of write() with diff. params.
	 * 
	 */
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * @param doc
	 * @param out
	 */
	public static void write(SBOLDocument doc, OutputStream out) {
	
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
		formatCollections(doc.getCollections(), topLevelDoc);
		formatModules(doc.getModules(), topLevelDoc); 		
		formatModels(doc.getModels(), topLevelDoc); 			
		formatComponents(doc.getComponents(), topLevelDoc);  
		formatStructures(doc.getStructures(), topLevelDoc); 

		//TODO: add formatTopLevels();
		
		try {
			write(new OutputStreamWriter(out), DocumentRoot(TopLevelDocuments(topLevelDoc)));
		} 
		catch(Exception e) { e.printStackTrace();} 
		
	}
	
	private static void write(Writer stream, DocumentRoot<QName> document) throws Exception
	{
		XMLStreamWriter xmlWriter = new IndentingXMLStreamWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(stream));
		RdfIo rdfIo = new RdfIo();
		rdfIo.createIoWriter(xmlWriter).write(document);
		xmlWriter.flush();
		xmlWriter.close();
	}
	
	private static void formatCollections (List<Collection> collections, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Collection c : collections)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(c.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, c.getIdentity()));
			if(c.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, c.getPersistentIdentity()));
			if(c.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, c.getVersion()));
			if(c.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, c.getDisplayId()));
			if(c.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, c.getName()));
			if(c.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, c.getDescription()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Collection.Collection, c.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatComponents (List<Component> components, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Component c : components)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(c.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, c.getIdentity()));
			if(c.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, c.getPersistentIdentity()));
			if(c.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, c.getVersion()));
			if(c.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, c.getDisplayId()));
			if(c.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, c.getName()));
			if(c.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, c.getDescription()));
			if(c.getRoles() != null)
				list.add(NamedProperty(Sbol2Terms.Component.roles, c.getRoles().toString())); //TODO: iterate through each roles and types
			if(c.getType() != null)
				list.add(NamedProperty(Sbol2Terms.Component.types, c.getType().toString()));
			
			getStructuralInstantiations(c.getStructuralInstantiations(),list);
			getStructuralAnnotations(c.getStructuralAnnotations(),list);
			getStructuralConstraints(c.getStructuralConstraints(),list);
			if(c.getStructure() != null)
				getStructure(c.getStructure(), list); 
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Component.Component, c.getIdentity(), NamedProperties(list)));
		}
	}
	
	/**
	 * getModel for Module
	 * @param model
	 * @param properties
	 */
	private static void formatModels (List<Model> models, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Model m : models)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, m.getIdentity()));
			if(m.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, m.getPersistentIdentity()));
			if(m.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, m.getVersion()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getSource() != null)
				list.add(NamedProperty(Sbol2Terms.Model.source, m.getSource()));
			if(m.getLanguage() != null)
				list.add(NamedProperty(Sbol2Terms.Model.language, m.getLanguage()));
			if(m.getFramework() != null)
				list.add(NamedProperty(Sbol2Terms.Model.framework, m.getFramework()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Model.Model, m.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatModules(List<Module> module, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for (Module m : module)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, m.getIdentity()));
			if(m.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, m.getPersistentIdentity()));
			if(m.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, m.getVersion()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));

			if(m.getRoles() != null)
			{
				for (URI roles : m.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.Module.roles, roles.toString()));
				}
			}
			
			getFunctionalInstantiations(m.getFunctionalInstantiations(),list);	
			getInteractions(m.getInteractions(),list); 
			getModels(m.getModels(),list); //TODO: iterate through Models to obtain URI
			getModuleInstantiation(m.getModuleInstantiations(),list);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Module.Module, m.getIdentity(), NamedProperties(list)));
		}	
	}
	
	private static void formatStructures (List<Structure> structures, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Structure s : structures)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(s.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, s.getIdentity()));
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, s.getVersion()));
			if(s.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, s.getDisplayId()));
			if(s.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, s.getName()));
			if(s.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, s.getDescription()));
			if(s.getElements() != null)
				list.add(NamedProperty(Sbol2Terms.Structure.elements, s.getElements()));
			if(s.getEncoding() != null)
				list.add(NamedProperty(Sbol2Terms.Structure.encoding, s.getEncoding()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Structure.Structure, s.getIdentity(), NamedProperties(list)));
		}
		
	}
	
	
	/**
	 * getFunctionalInstantiations for Module
	 * @param functionalInstantiation
	 * @param properties
	 */
	private static void getFunctionalInstantiations(List<FunctionalInstantiation> functionalInstantiation,
			List<NamedProperty<QName>> properties)
	{
		for(FunctionalInstantiation f : functionalInstantiation)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(f.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, f.getIdentity()));
			if(f.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, f.getPersistentIdentity()));
			if(f.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, f.getVersion()));
			if(f.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, f.getDisplayId()));
			if(f.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, f.getName()));
			if(f.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, f.getDescription()));
			
			if(f.getTimeStamp() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.timeStamp, f.getTimeStamp().toString()));
//			if(f.getInstantiatedComponent() != null) 
			//TODO: Why does creating an object of FunctionalInstantiation requires a component identity? 
			//how to retrieve List<URI> type? in mainTester, you need to create a List<URI> type but there is no
			//method to retrieve this data. 
				

			properties.add(NamedProperty(Sbol2Terms.Module.hasfunctionalInstantiations, 
					NestedDocument( Sbol2Terms.FunctionalInstantiation.FunctionalInstantiation, 
					f.getIdentity(), NamedProperties(list))));
		}
	}
	
	/**
	 * getInteractions for Module
	 * @param interactions
	 * @param properties
	 */
	private static void getInteractions (List<Interaction> interactions,
			List<NamedProperty<QName>> properties)
	{
		
		for(Interaction i : interactions)
		{
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(i.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, i.getIdentity()));
			if(i.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, i.getPersistentIdentity()));
			if(i.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, i.getVersion()));
			
			if(i.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, i.getDisplayId()));
			if(i.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, i.getName()));
			if(i.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, i.getDescription()));
			
			if (i.getParticipations() != null)
				list.add(NamedProperty(Sbol2Terms.Interaction.type, i.getParticipations().toString()));
			//TODO: is getParticipation() == getType() because Interaction does not have a getType(). 
			//in mainTester, a List<URI> type must be declared to create an Interaction. 
			
			properties.add(NamedProperty(Sbol2Terms.Module.hasInteractions, 
					NestedDocument( Sbol2Terms.Interaction.Interaction, 
					i.getIdentity(), NamedProperties(list))));
		}
	}
	
	private static void getModels(List<URI> models, List<NamedProperty<QName>> list)
	{
		for(URI m : models)
		{
			list.add(NamedProperty(Sbol2Terms.Module.hasModels, m));
		}
	}
	
	/**
	 * getModuleInstantiation for Module
	 * @param moduleInstantiation
	 * @param properties
	 */
	private static void getModuleInstantiation (List<ModuleInstantiation> moduleInstantiation, 
			List<NamedProperty<QName>> properties)
	{
		for(ModuleInstantiation m : moduleInstantiation)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, m.getIdentity()));
			if(m.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, m.getPersistentIdentity()));
			if(m.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, m.getVersion()));

			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			
//			if(m.getInstantiatedModule() != null) //TODO
				
			properties.add(NamedProperty(Sbol2Terms.Module.hasModuleInstantiations, 
					NestedDocument( Sbol2Terms.ModuleInstantiation.ModuleInstantiation, 
					m.getIdentity(), NamedProperties(list))));
		}
	}
	
	private static void getStructure(URI structure, List<NamedProperty<QName>> list)
	{
//		for(URI s : structures)
//		{
			list.add(NamedProperty(Sbol2Terms.Component.hasStructure, structure));
//		}
	}
	
	private static void getStructuralAnnotations(List<StructuralAnnotation> structuralAnnotations,
			List<NamedProperty<QName>> properties)
	{
		for(StructuralAnnotation s : structuralAnnotations)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(s.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, s.getIdentity()));
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, s.getVersion()));
			if(s.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, s.getDisplayId()));
			if(s.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, s.getName()));
			if(s.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, s.getDescription()));
			

			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralAnnotations, 
					NestedDocument( Sbol2Terms.StructuralAnnotation.StructuralAnnotation, 
					s.getIdentity(), NamedProperties(list))));
		}

	}
	
	private static void getStructuralConstraints(List<StructuralConstraint> structuralConstraint,
			List<NamedProperty<QName>> properties)
	{
		for(StructuralConstraint s : structuralConstraint)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(s.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, s.getIdentity()));
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, s.getVersion()));
			
			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralConstraints, 
					NestedDocument( Sbol2Terms.StructuralConstraint.StructuralConstraint, 
					s.getIdentity(), NamedProperties(list))));
		}

	}
	
	private static void getStructuralInstantiations(List<StructuralInstantiation> structuralInstantiations,
			List<NamedProperty<QName>> properties)
	{
		for(StructuralInstantiation s : structuralInstantiations)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(s.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.identity, s.getIdentity()));
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, s.getVersion()));
			if(s.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, s.getDisplayId()));
			if(s.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, s.getName()));
			if(s.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, s.getDescription()));
		

			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralInstantiations, 
					NestedDocument( Sbol2Terms.StructuralInstantiation.StructuralInstantiation, 
					s.getIdentity(), NamedProperties(list))));
		}
	}

}
