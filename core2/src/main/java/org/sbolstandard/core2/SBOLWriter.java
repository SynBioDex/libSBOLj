package org.sbolstandard.core2;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static uk.ac.ncl.intbio.core.datatree.Datatree.*;
import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.ncl.intbio.examples.SbolTerms;
import uk.ac.intbio.core.io.turtle.TurtleIo;

import javax.json.*;
import javax.json.stream.JsonGenerator;

import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.namespace.QName; 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.Identified;
import org.sbolstandard.core2.abstract_classes.Location;

import com.hp.hpl.jena.rdf.arp.impl.ANode;

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
	 * Make sure Range, Cut...etc should be included in the correct method. 
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
		
		try {
			writeRdf(new OutputStreamWriter(out), DocumentRoot( NamespaceBindings(doc.getNameSpaceBindings()),TopLevelDocuments(topLevelDoc)));
//			writeJson(new OutputStreamWriter(out), DocumentRoot(TopLevelDocuments(topLevelDoc)));
//			writeTurtle(new OutputStreamWriter(out), DocumentRoot(TopLevelDocuments(topLevelDoc)));
		} 
		catch(Exception e) { e.printStackTrace();} 
		
	}
	
	public static void writeRdf(Writer stream, DocumentRoot<QName> document) throws Exception
	{
		XMLStreamWriter xmlWriter = new IndentingXMLStreamWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(stream));
		RdfIo rdfIo = new RdfIo();
		rdfIo.createIoWriter(xmlWriter).write(document);
		xmlWriter.flush();
		xmlWriter.close();
	}
	
	public static void writeJson(Writer stream, DocumentRoot<QName> document) throws Exception
	{
		Map<String, Object> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGenerator writer = Json.createGeneratorFactory(config).createGenerator(stream);
		JsonIo jsonIo = new JsonIo();
		jsonIo.createIoWriter(writer).write(StringifyQName.qname2string.mapDR(document));
		writer.flush();
		writer.close();
	}
	
	public static void writeTurtle(Writer stream, DocumentRoot<QName> document) throws Exception
	{
		PrintWriter printWriter = new PrintWriter(stream);
		TurtleIo turtleIo = new TurtleIo();
		turtleIo.createIoWriter(printWriter).write(document);
		printWriter.flush();
	}
	
	private static void getCommonIdentifiedData (List<NamedProperty<QName>> list, Identified t)
	{
		//TODO: suppress getPersistentIdentity() & getVersion() for now. 
//		if(t.getPersistentIdentity() != null)
//			list.add(NamedProperty(Sbol2Terms.Documented.persistentIdentity, t.getPersistentIdentity()));
//		if(t.getVersion() != null)
//			list.add(NamedProperty(Sbol2Terms.Documented.version, t.getVersion()));
		if(t.getTimeStamp() != null)
			list.add(NamedProperty(Sbol2Terms.Documented.timeStamp, t.getTimeStamp().toString()));
		//TODO: wait until Zhen makes the correction in Turtle object before implementing. 
//		if(t.getAnnotations() != null) 
//			getAnnotations(t.getAnnotations(), list);
	}
	
	private static void getCommonDocumentedData (List<NamedProperty<QName>> list, Documented d)
	{
		getCommonIdentifiedData(list, d);
		//TODO: suppress getDisplayId() for now. 
//		if(d.getDisplayId() != null)
//			list.add(NamedProperty(Sbol2Terms.Documented.displayId, d.getDisplayId()));
		if(d.getName() != null)
			list.add(NamedProperty(Sbol2Terms.Documented.name, d.getName()));
		if(d.getDescription() != null)
			list.add(NamedProperty(Sbol2Terms.Documented.description, d.getDescription()));
	}
	
	private static void getCommonTopLevelData (List<NamedProperty<QName>> list, TopLevel t)
	{
		getCommonDocumentedData(list,t);
	}
	
	private static void formatCollections (List<Collection> collections, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Collection c : collections)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			getCommonTopLevelData(list, c);
			if(c.getMembers() != null)
			{
				for (URI member : c.getMembers())
				{
					list.add(NamedProperty(Sbol2Terms.Collection.hasMembers, member)); 
				}
			}
	
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Collection.Collection, c.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatComponents (List<Component> components, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Component c : components)
		{		
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

			getCommonTopLevelData(list,c);
			
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
			
			if(c.getAnnotations() != null)
			{	
				for(Annotation annotation : c.getAnnotations())
				{
					list.add(NamedProperty(annotation.getRelation(), annotation.getLiteral().getTurtleStr()));
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
	
	
	private static void formatModels (List<Model> models, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Model m : models)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			getCommonTopLevelData(list,m);
			
			if(m.getSource() != null)
				list.add(NamedProperty(Sbol2Terms.Model.source, m.getSource()));
			if(m.getLanguage() != null)
				list.add(NamedProperty(Sbol2Terms.Model.language, m.getLanguage()));
			if(m.getFramework() != null)
				list.add(NamedProperty(Sbol2Terms.Model.framework, m.getFramework()));
			if(m.getRoles() != null)
			{
				for (URI role : m.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.Model.roles, role));
				}
			}
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Model.Model, m.getIdentity(), NamedProperties(list)));
		}
	}
	
	private static void formatModules(List<Module> module, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for (Module m : module)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			getCommonTopLevelData(list,m);

			if(m.getRoles() != null)
			{
				for (URI role : m.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.Module.roles, role));
				}
			}
			
			getFunctionalInstantiations(m.getFunctionalInstantiations(),list);	
			getInteractions(m.getInteractions(),list); 
			getModels(m.getModels(),list); 
			getModuleInstantiation(m.getModuleInstantiations(),list);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Module.Module, m.getIdentity(), NamedProperties(list)));
		}	
	}
	
	private static void formatStructures (List<Structure> structures, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Structure s : structures)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
	
			getCommonTopLevelData(list, s);
			if(s.getElements() != null)
				list.add(NamedProperty(Sbol2Terms.Structure.elements, s.getElements()));
			if(s.getEncoding() != null)
				list.add(NamedProperty(Sbol2Terms.Structure.encoding, s.getEncoding()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Structure.Structure, s.getIdentity(), NamedProperties(list)));
		}
		
	}
	
	private static void getAnnotations(List<Annotation> annotations, List<NamedProperty<QName>> list)
	{
		//TODO: turtle value printing out weird due to .toString cast. and should annotation have own section?
//		List<NestedDocument> nestedDoc = new ArrayList<NestedDocument>(); 
		for(Annotation a : annotations)
		{
//			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			/* GM: Commented 
			 * if(a.getRelation() != null)
				list.add(NamedProperty(Sbol2Terms.Annotation.relation, a.getRelation()));
			if(a.getLiteral() != null)
				list.add(NamedProperty(Sbol2Terms.Annotation.value, a.getLiteral().toString()));
			*/
			//TODO: annotation does not have identity
//			nestedDoc.add(NestedDocument(Sbol2Terms.Annotation.Annotation, a.getIdentity(), NamedProperties(list)));
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
		
			getCommonDocumentedData(list, f);
			
			if(f.getInstantiatedComponent() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstantiation.hasInstantiatedComponent, f.getInstantiatedComponent()));
			if(f.getAccess() != null)
				list.add(NamedProperty(Sbol2Terms.FunctionalInstantiation.access, f.getAccess().getAccessTypeAlias()));
			if(f.getDirection() != null)
				list.add(NamedProperty(Sbol2Terms.FunctionalInstantiation.direction, f.getDirection().name()));	
				
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
			getCommonDocumentedData(list, i);
			
			if(i.getType() != null)
			{	
				for(URI type : i.getType())
				{
					list.add(NamedProperty(Sbol2Terms.Interaction.type, type));
				}
			}
			
			if(i.getParticipations() != null)
			{
				List<NestedDocument> participantList = getParticipations(i.getParticipations());

				for(NestedDocument n : participantList)
				{
					list.add(NamedProperty(Sbol2Terms.Interaction.hasParticipations, n));
				}
			}
			
			properties.add(NamedProperty(Sbol2Terms.Module.hasInteractions, 
					NestedDocument( Sbol2Terms.Interaction.Interaction, 
					i.getIdentity(), NamedProperties(list))));
		}
	}
	
	private static List<NestedDocument> getParticipations(List<Participation> participations)
	{
		List<NestedDocument> nestedDoc = new ArrayList<NestedDocument>(); 
		
			for(Participation p : participations)
			{
				List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>(); 
				
				//TODO: should participations identity be ignored like all identity?
//				if(p.getIdentity() != null)
//					list.add(NamedProperty(Sbol2Terms.Identified.identity,p.getIdentity()));
				if(p.getRoles() != null)
					for(URI r : p.getRoles())
						list.add(NamedProperty(Sbol2Terms.Participation.role, r));
				if(p.getParticipant() != null)
					list.add(NamedProperty(Sbol2Terms.Participation.hasParticipant, p.getParticipant())); 
				
				nestedDoc.add(NestedDocument(Sbol2Terms.Participation.Participation, p.getIdentity(), NamedProperties(list)));
			}
		
		return nestedDoc; 
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

			getCommonDocumentedData(list, m);

			if(m.getInstantiatedModule() != null)
				list.add(NamedProperty(Sbol2Terms.ModuleInstantiation.hasInstantiatedModule, m.getInstantiatedModule()));	

			if(m.getMappings() != null)	
			{
				List<NestedDocument> referenceList = getReferences(m.getMappings());

				for(NestedDocument n : referenceList)
				{
					list.add(NamedProperty(Sbol2Terms.ModuleInstantiation.hasMapping, n));
				}
			}

			properties.add(NamedProperty(Sbol2Terms.Module.hasModuleInstantiations, 
					NestedDocument( Sbol2Terms.ModuleInstantiation.ModuleInstantiation, 
							m.getIdentity(), NamedProperties(list))));
		}
	}

	private static List<NestedDocument> getReferences(List<MapsTo> references)
	{
		List<NestedDocument> nestedDoc = new ArrayList<NestedDocument>(); 

		for(MapsTo m : references)
		{
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>(); 

			if(m.getIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.MapsTo.identity, m.getIdentity()));
			if(m.getRefinement() != null)
				list.add(NamedProperty(Sbol2Terms.MapsTo.refinement, m.getRefinement().name()));
			if(m.getRemote() != null)
				list.add(NamedProperty(Sbol2Terms.MapsTo.hasRemote, m.getRemote())); 
			if(m.getLocal() != null)
				list.add(NamedProperty(Sbol2Terms.MapsTo.hasLocal, m.getLocal())); 

			nestedDoc.add(NestedDocument(Sbol2Terms.MapsTo.MapsTo, m.getIdentity(), NamedProperties(list)));
		}

		return nestedDoc; 
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

			getCommonDocumentedData(list, s);
			if(s.getLocation() != null)
			{
				list.add(getLocation(s.getLocation())); 
			}

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

			getCommonDocumentedData(list, s);
			if(s.getInstantiatedComponent() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstantiation.ComponentInstantiation, s.getInstantiatedComponent()));

			properties.add(NamedProperty(Sbol2Terms.Component.hasStructuralInstantiations, 
					NestedDocument( Sbol2Terms.StructuralInstantiation.StructuralInstantiation, 
					s.getIdentity(), NamedProperties(list))));
		}
	}

}
