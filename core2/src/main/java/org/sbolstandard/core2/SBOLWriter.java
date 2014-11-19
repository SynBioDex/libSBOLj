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
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.CoreIoException;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.intbio.core.io.turtle.TurtleIo;

import javax.json.*;
import javax.json.stream.JsonGenerator;

import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.namespace.QName; 
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.Identified;
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
	 * Make sure Range, Cut...etc should be included in the correct method. 
	 * fix URI for those that are not referencing. 
	 * url(authority)/id/major.vr/minor.vr
	 */
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * @param doc
	 * @param out
	 * @throws CoreIoException 
	 * @throws FactoryConfigurationError 
	 * @throws XMLStreamException 
	 */
	public static void write(SBOLDocument doc, OutputStream out) throws XMLStreamException, FactoryConfigurationError, CoreIoException {
		
			writeRdf(new OutputStreamWriter(out), 
					DocumentRoot( NamespaceBindings(NamespaceBinding("http://sbols.org/v2#","sbol2")),
							TopLevelDocuments(getTopLevelDocument(doc))));
//			writeJson(new OutputStreamWriter(out), DocumentRoot(TopLevelDocuments(topLevelDoc)));
//			writeTurtle(new OutputStreamWriter(out), DocumentRoot(TopLevelDocuments(topLevelDoc)));

	}
	
	public static void write(SBOLDocument doc, String filename) throws FileNotFoundException {
		write(doc, new File(filename));
	}
	
	public static void write(SBOLDocument doc, File file) throws FileNotFoundException{
		FileOutputStream stream = new FileOutputStream(file);
	    BufferedOutputStream buffer = new BufferedOutputStream(stream);
	    try {
			write(doc, buffer);
	    } catch (XMLStreamException | FactoryConfigurationError | CoreIoException e) {
	      
	    } finally {
	      try {
	        try {
	          stream.close();
	        } finally {
	          buffer.close();
	        }
	      } catch (IOException e) {
	      }
	    }
	}
	
	private static List<TopLevelDocument<QName>> getTopLevelDocument(SBOLDocument doc) {
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<TopLevelDocument<QName>>();
		formatCollections(doc.getCollections(), topLevelDoc);
		formatModuleDefinitions(doc.getModuleDefinitions(), topLevelDoc); 		
		formatModels(doc.getModels(), topLevelDoc); 			
		formatComponentDefinitions(doc.getComponentDefinitions(), topLevelDoc);  
		formatSequences(doc.getSequences(), topLevelDoc); 
		return topLevelDoc;
	}
	
	public static void writeRdf(Writer stream, DocumentRoot<QName> document) throws XMLStreamException, FactoryConfigurationError, CoreIoException
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
			list.add(NamedProperty(Sbol2Terms.Identified.timeStamp, t.getTimeStamp().toString()));
		
		if(t.getAnnotations() != null) 
		{
			List<NestedDocument> annotationList = getAnnotations(t.getAnnotations());

			for(NestedDocument annotation : annotationList)
			{
				list.add(NamedProperty(Sbol2Terms.Identified.hasAnnotations, annotation));
			}
		}
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
	
	private static void formatComponentDefinitions (List<ComponentDefinition> componentDefinitions, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(ComponentDefinition c : componentDefinitions)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

			getCommonTopLevelData(list,c);
			
			if(c.getRoles() != null)
			{
				for (URI roles : c.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.ComponentDefinition.roles, roles)); 
				}
			}
			if(c.getType() != null)
			{	
				for(URI types : c.getType())
				{
					list.add(NamedProperty(Sbol2Terms.ComponentDefinition.type, types));
				}
			}
		
			getSubComponents(c.getSubComponents(),list);
			getSequenceAnnotations(c.getSequenceAnnotations(),list);
			getSequenceConstraints(c.getSequenceConstraints(),list);
			if(c.getSequence() != null)
				getSequence(c.getSequence(), list); 
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.ComponentDefinition.ComponentDefinition, c.getIdentity(), NamedProperties(list)));
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
	
	private static void formatModuleDefinitions(List<ModuleDefinition> module, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for (ModuleDefinition m : module)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			getCommonTopLevelData(list,m);

			if(m.getRoles() != null)
			{
				for (URI role : m.getRoles())
				{
					list.add(NamedProperty(Sbol2Terms.ModuleDefinition.roles, role));
				}
			}
			
			getFunctionalComponents(m.getComponents(),list);	
			getInteractions(m.getInteractions(),list); 
			getModels(m.getModels(),list); 
			getModule(m.getSubModule(),list);
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.ModuleDefinition.ModuleDefinition, m.getIdentity(), NamedProperties(list)));
		}	
	}
	
	private static void formatSequences (List<Sequence> sequences, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Sequence s : sequences)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
	
			getCommonTopLevelData(list, s);
			if(s.getElements() != null)
				list.add(NamedProperty(Sbol2Terms.Sequence.elements, s.getElements()));
			if(s.getEncoding() != null)
				list.add(NamedProperty(Sbol2Terms.Sequence.encoding, s.getEncoding()));
			
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Sequence.Sequence, s.getIdentity(), NamedProperties(list)));
		}
		
	}
	
	private static List<NestedDocument> getAnnotations(List<Annotation> annotations)//, List<NamedProperty<QName>> list)
	{
		List<NestedDocument> nestedDoc = new ArrayList<NestedDocument>(); 
		for(Annotation a : annotations)
		{
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			if(a.getRelation() != null)
				list.add(NamedProperty(Sbol2Terms.Annotation.relation, a.getRelation()));
			if(a.getLiteral() != null)
				list.add(NamedProperty(Sbol2Terms.Annotation.value, a.getLiteral().getTurtleStr()));
			
			//TODO: annotation does not have identity. Should I use getRelation() instead?
			nestedDoc.add(NestedDocument(Sbol2Terms.Annotation.Annotation, a.getRelation(), NamedProperties(list)));
		}
		return nestedDoc;
	}
	
	/**
	 * getFunctionalComponents for Module
	 * @param functionalInstantiation
	 * @param properties
	 */
	private static void getFunctionalComponents(List<FunctionalComponent> functionalInstantiation,
			List<NamedProperty<QName>> properties)
	{
		for(FunctionalComponent f : functionalInstantiation)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
		
			getCommonDocumentedData(list, f);
			
			if(f.getDefinition() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstance.hasInstantiatedComponent, f.getDefinition()));
			if(f.getAccess() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstance.access, f.getAccess().getAccessTypeAlias()));
			if(f.getDirection() != null)
				list.add(NamedProperty(Sbol2Terms.FunctionalComponent.direction, f.getDirection().name()));	
				
			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasFunctionalComponent, 
					NestedDocument( Sbol2Terms.FunctionalComponent.FunctionalComponent, 
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
			
			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasInteractions, 
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
			list.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasModels, m));
		}
	}
	
	/**
	 * getModule for Module
	 * @param module
	 * @param properties
	 */
	private static void getModule (List<Module> module, 
			List<NamedProperty<QName>> properties)
	{
		for(Module m : module)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

			getCommonDocumentedData(list, m);

			if(m.getDefinition() != null)
				list.add(NamedProperty(Sbol2Terms.Module.hasInstantiatedModule, m.getDefinition()));	

			if(m.getMappings() != null)	
			{
				List<NestedDocument> referenceList = getReferences(m.getMappings());

				for(NestedDocument n : referenceList)
				{
					list.add(NamedProperty(Sbol2Terms.Module.hasMappings, n));
				}
			}

			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasSubModule, 
					NestedDocument( Sbol2Terms.Module.Module, 
							m.getIdentity(), NamedProperties(list))));
		}
	}

	private static List<NestedDocument> getReferences(List<MapsTo> references)
	{
		List<NestedDocument> nestedDoc = new ArrayList<NestedDocument>(); 

		for(MapsTo m : references)
		{
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>(); 
			//TODO: should mapsTo need to retreive identity? 
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
	
	private static void getSequence(URI sequence, List<NamedProperty<QName>> list)
	{
			list.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequence, sequence));
	}
	
	private static void getSequenceAnnotations(List<SequenceAnnotation> sequenceAnnotations,
			List<NamedProperty<QName>> properties)
	{
		for(SequenceAnnotation s : sequenceAnnotations)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

			getCommonDocumentedData(list, s);
			if(s.getLocation() != null)
			{
				list.add(getLocation(s.getLocation())); 
			}

			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations, 
					NestedDocument( Sbol2Terms.SequenceAnnotation.SequenceAnnotation, 
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
	
	private static void getSequenceConstraints(List<SequenceConstraint> sequenceConstraint,
			List<NamedProperty<QName>> properties)
	{
		for(SequenceConstraint s : sequenceConstraint)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();
			
			if(s.getPersistentIdentity() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.persistentIdentity, s.getPersistentIdentity()));
			if(s.getVersion() != null)
				list.add(NamedProperty(Sbol2Terms.Identified.version, s.getVersion()));
			if(s.getRestriction() != null)
				list.add(NamedProperty(Sbol2Terms.SequenceConstraint.restriction, s.getRestriction()));
			if(s.getSubject() != null)
				list.add(NamedProperty(Sbol2Terms.SequenceConstraint.hasSubject, s.getSubject()));
			if(s.getObject() != null)
				list.add(NamedProperty(Sbol2Terms.SequenceConstraint.hasObject, s.getObject()));
			
			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequenceConstraints, 
					NestedDocument( Sbol2Terms.SequenceConstraint.SequenceConstraint, 
					s.getIdentity(), NamedProperties(list))));
		}

	}
	
	private static void getSubComponents(List<Component> components,
			List<NamedProperty<QName>> properties)
	{
		for(Component s : components)
		{	
			List<NamedProperty<QName>> list = new ArrayList<NamedProperty<QName>>();

			getCommonDocumentedData(list, s);
			if(s.getDefinition() != null)
				list.add(NamedProperty(Sbol2Terms.ComponentInstance.ComponentInstance, s.getDefinition()));

			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSubComponents, 
					NestedDocument( Sbol2Terms.Component.Component, 
					s.getIdentity(), NamedProperties(list))));
		}
	}

}
