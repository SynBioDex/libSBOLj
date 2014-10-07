package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.namespace.QName; 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import static uk.ac.ncl.intbio.core.datatree.Datatree.*;

/**
 * @author Zhen Zhang and Tramy Nguyen
 * @version 2.0
 *
 */
public class SBOLWriter {
	  
	/*
	 * Note: 
	 * all checks for != null will be change to isSet()
	 * 
	 * TODO:
	 * make contract headers for PUBLIC methods only
	 * make various types of write() with diff. params.
	 */
	
	/**
	 * 
	 * @param doc
	 * @param out
	 */
	public static void write(SBOLDocument doc, OutputStream out) {
		
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
//		formatCollections(doc.getCollectionList(), topLevelDoc);//soon to be getCollections
		formatModules(doc.getModuleList(), topLevelDoc); 		//soon to be getModules
		formatModels(doc.getModelList(), topLevelDoc); 			//soon to be getModels
//		formatComponents(doc.getComponentList(), topLevelDoc);  //soon to be getComponents
//		formatStructures(doc.getStructureList(), topLevelDoc);  //soon to be getStructures
		
		
		try {
			write(new OutputStreamWriter(System.out), DocumentRoot(TopLevelDocuments(topLevelDoc)));
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
			if(c.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, c.getDescription()));
			if(c.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, c.getDisplayId()));
			if(c.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, c.getName()));
			if(c.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, c.getIdentity()));
			if(c.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, c.getPersistentIdentity()));
			if(c.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, c.getVersion()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Collection.Collection, c.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatComponents (List<Component> components, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Component c : components)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(c.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, c.getDescription()));
			if(c.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, c.getDisplayId()));
			if(c.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, c.getName()));
			if(c.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, c.getIdentity()));
			if(c.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, c.getPersistentIdentity()));
			if(c.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, c.getVersion()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Component.Component, c.getIdentity(), NamedProperties(list)));
		}
		
	}
	
	private static void formatStructures (List<Structure> structures, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Structure s : structures)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(s.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, s.getDescription()));
			if(s.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, s.getDisplayId()));
			if(s.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, s.getName()));
			if(s.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, s.getIdentity()));
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, s.getVersion()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Structure.Structure, s.getIdentity(), NamedProperties(list)));
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
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, m.getIdentity()));
			if(m.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, m.getPersistentIdentity()));
			if(m.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, m.getVersion()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Model.Model, m.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatModules(List<Module> module, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for (Module m : module)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			
			getFunctionalInstantiations(m.getFunctionalInstantiations(),list);	
			getInteractions(m.getInteractions(),list); 
			getModels(m.getModels(),list); //TODO: iterate through Models to obtain URI
			getModuleInstantiation(m.getModuleInstantiations(),list);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Module.Module, m.getIdentity(), NamedProperties(list)));
		}	
	}
	
	private static void getModels(List<Model> models, List<NamedProperty<QName>> list)
	{
		for(Model m : models)
		{
			list.add(NamedProperty(Sbol2Terms.Module.hasModels, m.getIdentity()));
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
			if(f.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, f.getDescription()));
//			if(f.getAccess() != null)
//				list.add(NamedProperty(Sbol2Terms.Documented.access, f.getAccess()));
			if(f.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, f.getDisplayId()));
			if(f.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, f.getName()));
			if(f.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, f.getIdentity()));
			if(f.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, f.getPersistentIdentity()));
			if(f.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, f.getVersion()));
			//timestamp?
			//access? does not exist in documented of sbol2terms. 

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
			if(i.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, i.getDescription()));
			if(i.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, i.getDisplayId()));
			if(i.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, i.getName()));
			if(i.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, i.getIdentity()));
			if(i.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, i.getPersistentIdentity()));
			if(i.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, i.getVersion()));
			
			properties.add(NamedProperty(Sbol2Terms.Module.hasInteractions, 
					NestedDocument( Sbol2Terms.Interaction.Interaction, 
					i.getIdentity(), NamedProperties(list))));
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
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.identity, m.getIdentity()));
			if(m.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, m.getPersistentIdentity()));
			if(m.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, m.getVersion()));
			
			properties.add(NamedProperty(Sbol2Terms.Module.hasModuleInstantiations, 
					NestedDocument( Sbol2Terms.ModuleInstantiation.ModuleInstantiation, 
					m.getIdentity(), NamedProperties(list))));
		}
	}

}
