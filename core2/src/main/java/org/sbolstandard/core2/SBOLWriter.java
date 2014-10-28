package org.sbolstandard.core2;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Set;
import java.net.URI;
//import java.util.Collection;
import java.util.List;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import static uk.ac.ncl.intbio.core.datatree.Datatree.*;
import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.namespace.QName; 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.sbolstandard.core2.abstract_classes.Location;

/**
 * @author Tramy Nguyen
 * @version 2.0
 *
 */
public class SBOLWriter {
	  
	/*
	 * Note: 
	 * all checks for != null will be change to isSet()
	 * 
	 * TODO:
	 * make various types of write() with diff. params.
	 * make common data (i.e. persistentidentity, name, displayId into seperate methods. 
	 * 
	 */
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * @param doc
	 * @param out
	 */
	public static void write(SBOLDocument doc, OutputStream out) {
		//TODO: annotation
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
		formatCollections(doc.getCollections(), topLevelDoc);
		formatModules(doc.getModules(), topLevelDoc); 		
		formatModels(doc.getModels(), topLevelDoc); 			
		formatComponents(doc.getComponents(), topLevelDoc);  
		formatStructures(doc.getStructures(), topLevelDoc); 
		formatTopLevels(doc.getTopLevels(), topLevelDoc); //TODO: add formatTopLevels();
		
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
	
	private static void formatTopLevels (List<TopLevel> topLevels, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(TopLevel t : topLevels)
		{
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(t.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, t.getPersistentIdentity()));
			if(t.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, t.getVersion()));
			if(t.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, t.getDisplayId()));
			if(t.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, t.getName()));
			if(t.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, t.getDescription()));
//			t.getAnnotations()
		}
		
	}
	
	private static void formatCollections (List<Collection> collections, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Collection c : collections)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
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
			{
				for (URI roles : c.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.Component.roles, roles)); 
				}
			}
			if(c.getType() != null)
			{	
				for(URI types : c.getType())
				{
					list.add(NamedProperty(Sbol2Terms.Component.types, types));
				}
			}
		
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
			
			getModels(m.getModels(), list);
			
			getModuleInstantiation(m.getModuleInstantiations(),list);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Module.Module, m.getIdentity(), NamedProperties(list)));
		}	
	}
	
	private static void formatStructures (List<Structure> structures, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Structure s : structures)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
	
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
			if(f.getInstantiatedComponent() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstantiation.ComponentInstantiation, f.getInstantiatedComponent()));
			
			if(f.getTimeStamp() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.timeStamp, f.getTimeStamp().toString()));

				

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
			
			//TODO: participation is its own class. 
			//TODO: iterate through each getType()
			if (i.getParticipations() != null)
				list.add(NamedProperty(Sbol2Terms.Interaction.type, i.getParticipations().toString()));
			//TODO: is getParticipation() == getType() because Interaction does not have a getType(). 
			//in mainTester, a List<URI> type must be declared to create an Interaction. 
			
			properties.add(NamedProperty(Sbol2Terms.Module.hasInteractions, 
					NestedDocument( Sbol2Terms.Interaction.Interaction, 
					i.getIdentity(), NamedProperties(list))));
		}
	}
	
	private static void getModels(Set<URI> models, List<NamedProperty<QName>> list)
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
			list.add(NamedProperty(Sbol2Terms.Component.hasStructure, structure));
	}
	
	private static void getStructuralAnnotations(List<StructuralAnnotation> structuralAnnotations,
			List<NamedProperty<QName>> properties)
	{
		for(StructuralAnnotation s : structuralAnnotations)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

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
			if(s.getLocation() != null)
				list.add(getLocation(s.getLocation())); 
			//TODO:
//			getRefStructuralInstantiation(s.getStructuralInstantiation()); 

			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralAnnotations, 
					NestedDocument( Sbol2Terms.StructuralAnnotation.StructuralAnnotation, 
					s.getIdentity(), NamedProperties(list))));
		}

	}
	
	private static NamedProperty<QName> getLocation(Location location)
	{
		List<NamedProperty<QName>> property = new ArrayList<NamedProperty<QName>>();
		if(location instanceof Range)
		{
			Range range = (Range) location; 
			property.add(NamedProperty(Sbol2Terms.Range.start, range.start)); 
			property.add(NamedProperty(Sbol2Terms.Range.end, range.end)); 
		}
		return NamedProperty(Sbol2Terms.Location.Location, 
				NestedDocument(Sbol2Terms.Range.Range, location.getIdentity(), NamedProperties(property))); 
		
	}
	
//	private static void getRefStructuralInstantiation(s.getStructuralInstantiation())
//	{
//		
//	}
	
	private static void getStructuralConstraints(List<StructuralConstraint> structuralConstraint,
			List<NamedProperty<QName>> properties)
	{
		/*
		 * URI identity 		   = getURI(structuralConstraints_data.get(0));
		URI persistentIdentity = getURI(structuralConstraints_data.get(1));
		String version 		   = structuralConstraints_data.get(2);
		URI restriction = getURI(structuralConstraints_data.get(3)); //TODO: is this how you would set restriction?
		URI subject = pre_structInstant.getIdentity(); 
		URI object = post_structInstant.getIdentity(); 
		 */
		for(StructuralConstraint s : structuralConstraint)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.version, s.getVersion()));
			if(s.getRestriction() != null)
				list.add(NamedProperty(Sbol2Terms.StructuralConstraint.restriction, s.getRestriction()));
			if(s.getSubject() != null)
				list.add(NamedProperty(Sbol2Terms.StructuralConstraint.hasSubject, s.getSubject()));
			if(s.getObject() != null)
				list.add(NamedProperty(Sbol2Terms.StructuralConstraint.hasObject, s.getObject()));
			
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
			if(s.getInstantiatedComponent() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstantiation.ComponentInstantiation, s.getInstantiatedComponent()));
					

			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralInstantiations, 
					NestedDocument( Sbol2Terms.StructuralInstantiation.StructuralInstantiation, 
					s.getIdentity(), NamedProperties(list))));
		}
	}

}
