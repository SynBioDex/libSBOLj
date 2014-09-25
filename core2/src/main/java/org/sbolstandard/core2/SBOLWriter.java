package org.sbolstandard.core2;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.Literal;
//import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.PropertyValue;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;

import javax.xml.namespace.QName;

import static uk.ac.ncl.intbio.core.datatree.Datatree.*;
import static uk.ac.ncl.intbio.core.datatree.IdentifiableDocument.*;

/*
 * writing imports
 */
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.stream.XMLStreamWriter;

import uk.ac.ncl.intbio.core.io.graphviz.LiteralStyler;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;

/**
 * @author Zhen Zhang
 * @version 2.0
 *
 */
public class SBOLWriter {
	  
	  //NOTE: create Module()
	public DocumentRoot<QName> write(SBOLDocument doc, OutputStream out) {
		
		List <TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
		
		List<NamedProperty<QName, Literal>> t = new ArrayList<NamedProperty<QName, Literal>>(); 
		NamedProper
		for (Module m : doc.getModule())
		{
			List<NamedProperty<QName, PropertyValue>> properties = new ArrayList<NamedProperty<QName, PropertyValue>>(); 
			
			getComponentInstantiations(m.getComponentInstantiations(),properties);			
			getInteractions(m.getInteractions(),properties); 
			getModel(m.getModels(),properties); 
			getModuleInstantiation(m.getSubModuleInstantiations(),properties);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Module.Module, m.getIdentity(), NamedProperties(properties)));
		}
//		DocumentRoot
//		DocumentRoot(TopLevelDocuments(topLevelDoc));
	
		return DocumentRoot(NamespaceBindings(Sbol2Terms.sbol2),  TopLevelDocuments(topLevelDoc), properties);
		
	}//end of write()
	
	public void getComponentInstantiations(Collection<ComponentInstantiation> componentInstantiation,
			List<NamedProperty<QName, PropertyValue>> properties)
	{
		List<NamedProperty<QName, PropertyValue>> list = new ArrayList<NamedProperty<QName, 
				PropertyValue>>();
		
		for(ComponentInstantiation c : componentInstantiation)
		{	
			if(c.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, c.getDescription()));
			if(c.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, c.getDisplayId()));
			if(c.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, c.getName()));

			properties.add(NamedProperty(Sbol2Terms.Module.hasfunctionalInstantiations, 
					NestedDocument( Sbol2Terms.FunctionalInstantiations.FunctionalInstantiations, 
					c.getIdentity(), NamedProperties(list))));
		}
	}
	
	
	public void getInteractions (Collection<Interaction> interactions,
			List<NamedProperty<QName, PropertyValue>> properties)
	{
		
		for(Interaction i : interactions)
		{
			List<NamedProperty<QName, PropertyValue>> list = new ArrayList<NamedProperty<QName, 
					PropertyValue>>();
			
			if(i.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, i.getDescription()));
			if(i.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, i.getDisplayId()));
			if(i.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, i.getName()));
			
			properties.add(NamedProperty(Sbol2Terms.Module.nteraction, 
					NestedDocument( Sbol2Terms.Interaction.Interaction, 
					i.getIdentity(), NamedProperties(list))));
		}
	}
	
	public void getModel (Collection<Model> model,
			List<NamedProperty<QName, PropertyValue>> properties)
	{
		List<NamedProperty<QName, PropertyValue>> list = new ArrayList<NamedProperty<QName, 
				PropertyValue>>();
		
		for(Model m : model)
		{	
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			
			properties.add(NamedProperty(Sbol2Terms.Module.Module, 
					NestedDocument( Sbol2Terms.Model.Model, 
					m.getIdentity(), NamedProperties(list))));
		}
	}
	
	public void getModuleInstantiation (Collection<ModuleInstantiation> moduleInstantiation, 
			List<NamedProperty<QName, PropertyValue>> properties)
	{
		List<NamedProperty<QName, PropertyValue>> list = new ArrayList<NamedProperty<QName, 
				PropertyValue>>();
		
		for(ModuleInstantiation m : moduleInstantiation)
		{	
			if(m.getDescription() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.description, m.getDescription()));
			if(m.getDisplayId() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.displayId, m.getDisplayId()));
			if(m.getName() != null)
				list.add(NamedProperty(Sbol2Terms.Documented.name, m.getName()));
			
			properties.add(NamedProperty(Sbol2Terms.Module.Module, 
					NestedDocument( Sbol2Terms.ModuleInstantiation.ModuleInstantiation, 
					m.getIdentity(), NamedProperties(list))));
		}
	}

}
