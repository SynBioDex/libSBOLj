package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.DocumentRoot;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperties;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamedProperty;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBindings;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NestedDocument;
import static uk.ac.ncl.intbio.core.datatree.Datatree.TopLevelDocument;
import static uk.ac.ncl.intbio.core.datatree.Datatree.TopLevelDocuments;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javanet.staxutils.IndentingXMLStreamWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.ac.intbio.core.io.turtle.TurtleIo;
import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.CoreIoException;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

/**
 * Provides methods to output SBOL files in XML/RDF format.
 * 
 * @author Tramy Nguyen
 * @author Chris Myers
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @version 2.1
 */

public class SBOLWriter
{
	/**
	 * A {@code true} value of the {@code keepGoing} flag tells the SBOL writer
	 * to continue writing an output file, after it encounters an SBOL conversion exception;
	 * a {@code false} value forces the writer to stop writing after it encounters
	 * an SBOL conversion exception.
	 */
	public static boolean keepGoing = true;
	
	private static List<String> errors = new ArrayList<String>();

	/**
	 * Returns the value of the {@code keepGoing} flag.
	 * @return the value of the {@code keepGoing} flag
	 */
	public static boolean isKeepGoing() {
		return keepGoing;
	}

	/**
	 * Sets the value of the {@code keepGoing} flag to the specified Boolean value.
	 * @param keepGoing The specified Boolean value
	 */
	public static void setKeepGoing(boolean keepGoing) {
		SBOLWriter.keepGoing = keepGoing;
	}

	/**
	 * Sets the error list that is used to store SBOL conversion exceptions 
	 * during reading to empty. 
	 */
	public static void clearErrors() {
		errors = new ArrayList<String>();
	}

	/**
	 * Returns the error list that is used to store SBOL conversion exceptions.
	 * @return the error list that is used to store SBOL conversion exceptions
	 */
	public static List<String> getErrors() {
		return errors;
	}

	/**
	 * Returns the number of errors in the error list. 
	 * @return the number of errors in the error list
	 */
	public static int getNumErrors() {
		return errors.size();
	}

	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output file
	 * in RDF format.
	 * @param doc the given SBOLDocument object
	 * @param file the serialized output file
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException - problem found during serialization 
	 */
	public static void write(SBOLDocument doc, File file) throws IOException, SBOLConversionException {
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		write(doc, buffer);
		stream.close();
		buffer.close();
	}
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output file
	 * in the specified fileType format.
	 * @param doc the given SBOLDocument object
	 * @param file the serialized output file
	 * @param fileType the given file format, such as RDF/XML, JSON, or Turtle. 
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException - problem found during serialization 
	 */
	public static void write(SBOLDocument doc, File file, String fileType) throws IOException, SBOLConversionException
	{
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		write(doc, buffer, fileType);
		stream.close();
		buffer.close();
	}

	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * in RDF format.
	 * @param doc the given SBOLDocument object
	 * @param out the serialized output stream
	 * @throws SBOLConversionException - problem found during serialization 
	 */
	public static void write(SBOLDocument doc, OutputStream out) throws SBOLConversionException
	{
		try {
			writeRDF(new OutputStreamWriter(out),
					DocumentRoot( NamespaceBindings(doc.getNamespaceBindings()),
							TopLevelDocuments(getTopLevelDocument(doc))));
		}
		catch (XMLStreamException e) {
			throw new SBOLConversionException(e.getMessage());
		}
		catch (FactoryConfigurationError e) {
			throw new SBOLConversionException(e.getMessage());
		}
		catch (CoreIoException e) {
			throw new SBOLConversionException(e.getMessage());
		}
	}

	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output
	 * file name in RDF format
	 * @param doc the given SBOLDocument object
	 * @param filename the name of the serialized output file
	 * @throws IOException see {@link IOException}	 
	 * @throws SBOLConversionException - problem found during serialization 
	 */
	public static void write(SBOLDocument doc, String filename) throws IOException, SBOLConversionException
	{
		write(doc, new File(filename));
	}
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output
	 * filename in specified fileType format
	 * @param doc the given SBOLDocument object
	 * @param filename the name of the serialized output file
	 * @param fileType the given file format, such as RDF/XML, JSON, or Turtle.
	 * @throws IOException see {@link IOException}
	 * @throws SBOLConversionException - problem found during serialization 
	 */
	public static void write(SBOLDocument doc, String filename, String fileType) throws IOException, SBOLConversionException
	{
		write(doc, new File(filename), fileType);
	}

	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * in the specified fileType format.
	 * @param doc the given SBOLDocument object
	 * @param out the serialized output stream
	 * @param fileType the given file format, such as RDF/XML, JSON, or Turtle.
	 * @throws SBOLConversionException - problem found during serialization
	 * @throws IOException see {@link IOException}
	 */
	public static void write(SBOLDocument doc, OutputStream out, String fileType) throws SBOLConversionException, IOException
	{
		clearErrors();
		if (fileType.equals(SBOLDocument.FASTAformat)) {
			FASTA.write(doc, out);
		} else if (fileType.equals(SBOLDocument.GENBANK)) {
			GenBank.write(doc, out);
		} else if (fileType.equals(SBOLDocument.JSON)) {
			try {
				writeJSON(new OutputStreamWriter(out),
						DocumentRoot( NamespaceBindings(doc.getNamespaceBindings()),
								TopLevelDocuments(getTopLevelDocument(doc))));
			}
			catch (CoreIoException e) {
				throw new SBOLConversionException(e.getMessage());
			}
		} else if (fileType.equals(SBOLDocument.TURTLE)){
			try {
				writeTurtle(new OutputStreamWriter(out),
						DocumentRoot( NamespaceBindings(doc.getNamespaceBindings()),
								TopLevelDocuments(getTopLevelDocument(doc))));
			}
			catch (CoreIoException e) {
				throw new SBOLConversionException(e.getMessage());
			}
		} else if (fileType.equals(SBOLDocument.RDFV1)){
			try {
				writeRDF(new OutputStreamWriter(out),
						DocumentRoot( NamespaceBindings(getNamespaceBindingsV1()),
								TopLevelDocuments(convertToV1Document(doc))));
			}
			catch (XMLStreamException e) {
				throw new SBOLConversionException(e.getMessage());
			}
			catch (FactoryConfigurationError e) {
				throw new SBOLConversionException(e.getMessage());
			}
			catch (CoreIoException e) {
				throw new SBOLConversionException(e.getMessage());
			}
		} else {
			try {
				writeRDF(new OutputStreamWriter(out),
						DocumentRoot( NamespaceBindings(doc.getNamespaceBindings()),
								TopLevelDocuments(getTopLevelDocument(doc))));
			}
			catch (XMLStreamException e) {
				throw new SBOLConversionException(e.getMessage());
			}
			catch (FactoryConfigurationError e) {
				throw new SBOLConversionException(e.getMessage());
			}
			catch (CoreIoException e) {
				throw new SBOLConversionException(e.getMessage());
			}
		}
	}

	private static void writeJSON(Writer stream, DocumentRoot<QName> document) throws CoreIoException
	{
		HashMap<String, Object> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGenerator writer = Json.createGeneratorFactory(config).createGenerator(stream);
		JsonIo jsonIo = new JsonIo();
		jsonIo.createIoWriter(writer).write(StringifyQName.qname2string.mapDR(document));
		writer.flush();
		writer.close();
	}

	private static void writeRDF(Writer stream, DocumentRoot<QName> document) throws XMLStreamException, FactoryConfigurationError, CoreIoException
	{
		XMLStreamWriter xmlWriter = new IndentingXMLStreamWriter(XMLOutputFactory.newInstance().createXMLStreamWriter(stream));
		RdfIo rdfIo = new RdfIo();
		rdfIo.createIoWriter(xmlWriter).write(document);
		xmlWriter.flush();
		xmlWriter.close();
	}

	private static void writeTurtle(Writer stream, DocumentRoot<QName> document) throws CoreIoException
	{
		PrintWriter printWriter = new PrintWriter(stream);
		TurtleIo turtleIo = new TurtleIo();
		turtleIo.createIoWriter(printWriter).write(document);
		printWriter.flush();
	}

	private static void formatCollections (Set<Collection> collections, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Collection c : collections)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonTopLevelData(list, c);
			for (URI member : c.getMemberURIs())
			{
				list.add(NamedProperty(Sbol2Terms.Collection.hasMembers, member));
			}
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Collection.Collection, c.getIdentity(), NamedProperties(list)));
		}
	}

	private static void formatCommonIdentifiedData (List<NamedProperty<QName>> list, Identified t)
	{
		if(t.isSetPersistentIdentity())
			list.add(NamedProperty(Sbol2Terms.Identified.persistentIdentity, t.getPersistentIdentity()));
		if(t.isSetDisplayId())
			list.add(NamedProperty(Sbol2Terms.Identified.displayId, t.getDisplayId()));
		if(t.isSetVersion())
			list.add(NamedProperty(Sbol2Terms.Identified.version, t.getVersion()));
		if(t.isSetWasDerivedFrom())
			list.add(NamedProperty(Sbol2Terms.Identified.wasDerivedFrom, t.getWasDerivedFrom()));
		if(t.isSetName())
			list.add(NamedProperty(Sbol2Terms.Identified.title, t.getName()));
		if(t.isSetDescription())
			list.add(NamedProperty(Sbol2Terms.Identified.description, t.getDescription()));
		for(Annotation annotation : t.getAnnotations())
		{
			if (!annotation.getValue().getName().getPrefix().equals("sbol"))
				list.add(annotation.getValue());
		}
	}

	private static void formatCommonTopLevelData (List<NamedProperty<QName>> list, TopLevel t)
	{
		formatCommonIdentifiedData(list,t);
	}

	private static void formatComponentDefinitions (Set<ComponentDefinition> componentDefinitions, List<TopLevelDocument<QName>> topLevelDoc)
	{

		for(ComponentDefinition c : componentDefinitions)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();

			formatCommonTopLevelData(list,c);
			for(URI types : c.getTypes())
			{
				list.add(NamedProperty(Sbol2Terms.ComponentDefinition.type, types));
			}
			for (URI roles : c.getRoles())
			{
				list.add(NamedProperty(Sbol2Terms.ComponentDefinition.roles, roles));
			}
			formatComponents(c.getComponents(),list);
			formatSequenceAnnotations(c.getSequenceAnnotations(),list);
			formatSequenceConstraints(c.getSequenceConstraints(),list);
			for(URI sUri: c.getSequenceURIs())
				formatSequence(sUri, list);

			topLevelDoc.add(TopLevelDocument(Sbol2Terms.ComponentDefinition.ComponentDefinition, c.getIdentity(), NamedProperties(list)));
		}
	}

	/**
	 * formatFunctionalComponents for Module
	 * @param functionalInstantiation
	 * @param properties
	 */
	private static void formatFunctionalComponents(Set<FunctionalComponent> functionalInstantiation,
			List<NamedProperty<QName>> properties)
	{
		for(FunctionalComponent f : functionalInstantiation)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();

			formatCommonIdentifiedData(list, f);

			list.add(NamedProperty(Sbol2Terms.ComponentInstance.hasComponentDefinition, f.getDefinitionURI()));
			list.add(NamedProperty(Sbol2Terms.ComponentInstance.access, AccessType.convertToURI(f.getAccess())));
			list.add(NamedProperty(Sbol2Terms.FunctionalComponent.direction, DirectionType.convertToURI(f.getDirection())));
			List<NestedDocument<QName>> referenceList = getMapsTo(f.getMapsTos());
			for(NestedDocument<QName> n : referenceList)
			{
				list.add(NamedProperty(Sbol2Terms.ComponentInstance.hasMapsTo, n));
			}

			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasfunctionalComponent,
					NestedDocument( Sbol2Terms.FunctionalComponent.FunctionalComponent,
							f.getIdentity(), NamedProperties(list))));
		}
	}

	/**
	 * formatInteractions for Module
	 * @param interactions
	 * @param properties
	 */
	private static void formatInteractions (Set<Interaction> interactions,
			List<NamedProperty<QName>> properties)
	{
		for(Interaction i : interactions)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, i);
			for(URI type : i.getTypes())
			{
				list.add(NamedProperty(Sbol2Terms.Interaction.type, type));
			}
			List<NestedDocument<QName>> participantList = formatParticipations(i.getParticipations());
			for(NestedDocument<QName> n : participantList)
			{
				list.add(NamedProperty(Sbol2Terms.Interaction.hasParticipations, n));
			}

			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasInteractions,
					NestedDocument( Sbol2Terms.Interaction.Interaction,
							i.getIdentity(), NamedProperties(list))));
		}
	}

	private static void formatModels (Set<Model> models, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Model m : models)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonTopLevelData(list,m);
			list.add(NamedProperty(Sbol2Terms.Model.source, m.getSource()));
			list.add(NamedProperty(Sbol2Terms.Model.language, m.getLanguage()));
			list.add(NamedProperty(Sbol2Terms.Model.framework, m.getFramework()));
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Model.Model, m.getIdentity(), NamedProperties(list)));
		}
	}

	private static void formatModelProperties(Set<URI> models, List<NamedProperty<QName>> list)
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
	private static void formatModule (Set<Module> module,
			List<NamedProperty<QName>> properties)
	{
		for(Module m : module)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, m);
			list.add(NamedProperty(Sbol2Terms.Module.hasDefinition, m.getDefinitionURI()));
			List<NestedDocument<QName>> referenceList = getMapsTo(m.getMapsTos());
			for(NestedDocument<QName> n : referenceList)
			{
				list.add(NamedProperty(Sbol2Terms.Module.hasMapsTo, n));
			}
			properties.add(NamedProperty(Sbol2Terms.ModuleDefinition.hasModule,
					NestedDocument( Sbol2Terms.Module.Module,
							m.getIdentity(), NamedProperties(list))));
		}
	}

	private static void formatModuleDefinitions(Set<ModuleDefinition> module, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for (ModuleDefinition m : module)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonTopLevelData(list,m);
			for (URI role : m.getRoles())
			{
				list.add(NamedProperty(Sbol2Terms.ModuleDefinition.roles, role));
			}
			formatFunctionalComponents(m.getFunctionalComponents(),list);
			formatInteractions(m.getInteractions(),list);
			formatModelProperties(m.getModelURIs(),list);
			formatModule(m.getModules(),list);
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.ModuleDefinition.ModuleDefinition, m.getIdentity(), NamedProperties(list)));
		}
	}

	private static List<NestedDocument<QName>> formatParticipations(Set<Participation> participations)
	{
		List<NestedDocument<QName>> nestedDoc = new ArrayList<>();
		for(Participation p : participations)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, p);
			for(URI r : p.getRoles())
				list.add(NamedProperty(Sbol2Terms.Participation.role, r));
			list.add(NamedProperty(Sbol2Terms.Participation.hasParticipant, p.getParticipantURI()));
			nestedDoc.add(NestedDocument(Sbol2Terms.Participation.Participation, p.getIdentity(), NamedProperties(list)));
		}
		return nestedDoc;
	}

	private static void formatSequence(URI sequence, List<NamedProperty<QName>> list)
	{
		list.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequence, sequence));
	}


	private static void formatSequenceAnnotations(Set<SequenceAnnotation> sequenceAnnotations,
			List<NamedProperty<QName>> properties)
	{
		for(SequenceAnnotation s : sequenceAnnotations)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, s);
			for (URI roles : s.getRoles())
			{
				list.add(NamedProperty(Sbol2Terms.SequenceAnnotation.roles, roles));
			}
			if (s.isSetRoleIntegration()) {
				list.add(NamedProperty(Sbol2Terms.Component.roleIntegration, RoleIntegrationType.convertToURI(s.getRoleIntegration())));
			}
			for (Location location : s.getLocations()) {
				list.add(getLocation(location));
			}
			if(s.isSetComponent())
				list.add(NamedProperty(Sbol2Terms.SequenceAnnotation.hasComponent, s.getComponentURI()));
			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations,
					NestedDocument( Sbol2Terms.SequenceAnnotation.SequenceAnnotation,
							s.getIdentity(), NamedProperties(list))));
		}

	}

	private static void formatSequenceConstraints(Set<SequenceConstraint> sequenceConstraint,
			List<NamedProperty<QName>> properties)
	{
		for(SequenceConstraint s : sequenceConstraint)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, s);
			list.add(NamedProperty(Sbol2Terms.SequenceConstraint.restriction, s.getRestrictionURI()));
			list.add(NamedProperty(Sbol2Terms.SequenceConstraint.hasSubject, s.getSubjectURI()));
			list.add(NamedProperty(Sbol2Terms.SequenceConstraint.hasObject, s.getObjectURI()));
			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasSequenceConstraints,
					NestedDocument( Sbol2Terms.SequenceConstraint.SequenceConstraint,
							s.getIdentity(), NamedProperties(list))));
		}

	}

	private static void formatSequences (Set<Sequence> sequences, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(Sequence s : sequences)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonTopLevelData(list, s);
			list.add(NamedProperty(Sbol2Terms.Sequence.elements, s.getElements()));
			list.add(NamedProperty(Sbol2Terms.Sequence.encoding, s.getEncoding()));
			topLevelDoc.add(TopLevelDocument(Sbol2Terms.Sequence.Sequence, s.getIdentity(), NamedProperties(list)));
		}
	}

	private static void formatComponents(Set<Component> components,
			List<NamedProperty<QName>> properties)
	{
		for(Component s : components)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, s);
			for (URI roles : s.getRoles())
			{
				list.add(NamedProperty(Sbol2Terms.Component.roles, roles));
			}
			if (s.isSetRoleIntegration()) {
				list.add(NamedProperty(Sbol2Terms.Component.roleIntegration, RoleIntegrationType.convertToURI(s.getRoleIntegration())));
			}
			list.add(NamedProperty(Sbol2Terms.ComponentInstance.access, AccessType.convertToURI(s.getAccess())));
			list.add(NamedProperty(Sbol2Terms.ComponentInstance.hasComponentDefinition, s.getDefinitionURI()));
			List<NestedDocument<QName>> referenceList = getMapsTo(s.getMapsTos());
			for(NestedDocument<QName> n : referenceList)
			{
				list.add(NamedProperty(Sbol2Terms.ComponentInstance.hasMapsTo, n));
			}
			properties.add(NamedProperty(Sbol2Terms.ComponentDefinition.hasComponent,
					NestedDocument( Sbol2Terms.Component.Component,
							s.getIdentity(), NamedProperties(list))));
		}
	}

	private static void formatGenericTopLevel(Set<GenericTopLevel> topLevels, List<TopLevelDocument<QName>> topLevelDoc)
	{
		for(GenericTopLevel t : topLevels)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonTopLevelData(list, t);
			topLevelDoc.add(TopLevelDocument(t.getRDFType(), t.getIdentity(), NamedProperties(list)));
		}
	}

	private static NamedProperty<QName> getLocation(Location location)
	{
		List<NamedProperty<QName>> property = new ArrayList<>();
		formatCommonIdentifiedData(property, location);

		if(location instanceof Range)
		{
			Range range = (Range) location;
			property.add(NamedProperty(Sbol2Terms.Range.start, range.getStart()));
			property.add(NamedProperty(Sbol2Terms.Range.end, range.getEnd()));
			if(range.isSetOrientation())
				property.add(NamedProperty(Sbol2Terms.Range.orientation, OrientationType.convertToURI(range.getOrientation())));
			return NamedProperty(Sbol2Terms.Location.Location,
					NestedDocument(Sbol2Terms.Range.Range, range.getIdentity(), NamedProperties(property)));
		}
		else if(location instanceof Cut)
		{
			Cut cut = (Cut) location;
			property.add(NamedProperty(Sbol2Terms.Cut.at, cut.getAt()));
			if (cut.isSetOrientation())
				property.add(NamedProperty(Sbol2Terms.Cut.orientation, OrientationType.convertToURI(cut.getOrientation())));
			return NamedProperty(Sbol2Terms.Location.Location,
					NestedDocument(Sbol2Terms.Cut.Cut, cut.getIdentity(), NamedProperties(property)));
		}
		else 
		{
			GenericLocation genericLocation = (GenericLocation) location;
			if (genericLocation.isSetOrientation())
				property.add(NamedProperty(Sbol2Terms.GenericLocation.orientation, OrientationType.convertToURI(genericLocation.getOrientation())));
			return NamedProperty(Sbol2Terms.Location.Location,
					NestedDocument(Sbol2Terms.GenericLocation.GenericLocation, genericLocation.getIdentity(), NamedProperties(property)));
		}
	}

	private static List<NestedDocument<QName>> getMapsTo(Set<MapsTo> references)
	{
		List<NestedDocument<QName>> nestedDoc = new ArrayList<>();
		for(MapsTo m : references)
		{
			List<NamedProperty<QName>> list = new ArrayList<>();
			formatCommonIdentifiedData(list, m);
			list.add(NamedProperty(Sbol2Terms.MapsTo.refinement, RefinementType.convertToURI(m.getRefinement())));
			list.add(NamedProperty(Sbol2Terms.MapsTo.hasRemote, m.getRemoteURI()));
			list.add(NamedProperty(Sbol2Terms.MapsTo.hasLocal, m.getLocalURI()));
			nestedDoc.add(NestedDocument(Sbol2Terms.MapsTo.MapsTo, m.getIdentity(), NamedProperties(list)));
		}
		return nestedDoc;
	}
	
	private static NestedDocument<QName> getSequenceV1(Sequence sequence)
	{
		List<NamedProperty<QName>> list = new ArrayList<>();
		list.add(NamedProperty(Sbol1Terms.DNASequence.nucleotides, sequence.getElements()));
		return NestedDocument(Sbol1Terms.DNASequence.DNASequence, sequence.getIdentity(), NamedProperties(list));
	}
	
	private static NestedDocument<QName> getSequenceAnnotationV1(SequenceAnnotation sequenceAnnotation, 
			ComponentDefinition componentDefinition) throws SBOLConversionException
	{
		List<NamedProperty<QName>> list = new ArrayList<>();
		for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
			if (sequenceConstraint.getRestriction().equals(RestrictionType.PRECEDES)) {
				if (sequenceConstraint.getSubjectURI().equals(sequenceAnnotation.getComponentURI())) {
					for (SequenceAnnotation annotation : componentDefinition.getSequenceAnnotations()) {
						if (sequenceConstraint.getObjectURI().equals(annotation.getComponentURI())) {
							list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.precedes,annotation.getIdentity()));
						}
					}
				}
			}
		}
		if (sequenceAnnotation.getLocations().size()!=1) {
			if (keepGoing) {
				errors.add("SBOL 1.1 only allows a single location.\n:"+sequenceAnnotation.getIdentity());
			} else {
				throw new SBOLConversionException("SBOL 1.1 only allows a single location.\n:"+sequenceAnnotation.getIdentity());
			}
		}
		for (Location location : sequenceAnnotation.getLocations()) {
			if (location instanceof Range) {
				Range range = (Range)location;
				list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.bioStart, range.getStart()));
				list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.bioEnd, range.getEnd()));
				if (range.isSetOrientation()) {
					if (range.getOrientation()==OrientationType.INLINE) {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.strand, "+"));
					} else if (range.getOrientation()==OrientationType.REVERSECOMPLEMENT) {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.strand, "-"));
					} 
				} 
			} else if (location instanceof GenericLocation) {
				GenericLocation genericLocation = (GenericLocation)location;
				if (genericLocation.isSetOrientation()) {
					if (genericLocation.getOrientation()==OrientationType.INLINE) {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.strand, "+"));
					} else if (genericLocation.getOrientation()==OrientationType.REVERSECOMPLEMENT) {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.strand, "-"));
					} 
				} 
			} else {
				if (keepGoing) {
					errors.add("SBOL 1.1 only supports Ranges and GenericLocations.\n:"+sequenceAnnotation.getIdentity());
				} else {
					throw new SBOLConversionException("SBOL 1.1 only supports Ranges and GenericLocations."+sequenceAnnotation.getIdentity());
				}				
			}
		}
		if (sequenceAnnotation.isSetComponent()) {
			list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.subComponent, 
					getSubComponent(sequenceAnnotation.getComponent().getDefinition())));
		}
		return NestedDocument(Sbol1Terms.SequenceAnnotations.SequenceAnnotation, 
				sequenceAnnotation.getIdentity(), NamedProperties(list));
	}
	
	
	private static NestedDocument<QName> getComponentV1(Component component, 
			ComponentDefinition componentDefinition) throws SBOLConversionException
	{
		List<NamedProperty<QName>> list = new ArrayList<>();
		for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
			if (sequenceConstraint.getRestriction().equals(RestrictionType.PRECEDES)) {
				if (sequenceConstraint.getSubjectURI().equals(component.getIdentity())) {
					SequenceAnnotation annotation = componentDefinition.getSequenceAnnotation(sequenceConstraint.getObject());
					if (annotation!=null) {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.precedes,annotation.getIdentity()));
					} else {
						list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.precedes,sequenceConstraint.getObjectURI()));
					}
				}
			}
		}
		list.add(NamedProperty(Sbol1Terms.SequenceAnnotations.subComponent, 
				getSubComponent(component.getDefinition())));
		return NestedDocument(Sbol1Terms.SequenceAnnotations.SequenceAnnotation, 
				component.getIdentity(), NamedProperties(list));
	}
	
	private static NestedDocument<QName> getSubComponent(ComponentDefinition componentDefinition) throws SBOLConversionException {
		List<NamedProperty<QName>> list = new ArrayList<>();
		if (componentDefinition==null) {
			throw new SBOLConversionException("ComponentDefinition not found.\n:");
		}
		if (!componentDefinition.getTypes().contains(ComponentDefinition.DNA)) {
			throw new SBOLConversionException("SBOL 1.1 only supports DNA ComponentDefinitions.\n:"+componentDefinition.getIdentity());
		}
		if(componentDefinition.isSetDisplayId())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.displayId, componentDefinition.getDisplayId()));
		if(componentDefinition.isSetName())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.name, componentDefinition.getName()));
		if(componentDefinition.isSetDescription())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.description, componentDefinition.getDescription()));
		for(Annotation annotation : componentDefinition.getAnnotations())
		{
			if (!annotation.getValue().getName().getPrefix().equals("sbol"))
				list.add(annotation.getValue());
		}
		for (URI role : componentDefinition.getRoles())
		{
			URI purlRole = URI.create(role.toString().replace("http://identifiers.org/so/SO:", "http://purl.obolibrary.org/obo/SO_"));
			list.add(NamedProperty(Sbol1Terms.DNAComponent.type, purlRole));
		}
		Sequence sequence = componentDefinition.getSequenceByEncoding(Sequence.IUPAC_DNA);
		if ((sequence==null && componentDefinition.getSequences().size()>0) || 
				(componentDefinition.getSequences().size()>1)) {
			if (keepGoing) {
				errors.add("SBOL 1.1 only supports a single IUPAC_DNA Sequence.\n:"+componentDefinition.getIdentity());
			} else {
				throw new SBOLConversionException("SBOL 1.1 only supports a single IUPAC_DNA Sequence.\n:"+componentDefinition.getIdentity());
			}
		}
		if (sequence!=null) {
			list.add(NamedProperty(Sbol1Terms.DNAComponent.dnaSequence, getSequenceV1(sequence)));
		}
		for (Component component : componentDefinition.getComponents()) {
			SequenceAnnotation sequenceAnnotation = componentDefinition.getSequenceAnnotation(component);
			if (sequenceAnnotation!=null) {
				list.add(NamedProperty(Sbol1Terms.DNAComponent.annotations, getSequenceAnnotationV1(sequenceAnnotation,componentDefinition)));
			} else {
				list.add(NamedProperty(Sbol1Terms.DNAComponent.annotations, getComponentV1(component,componentDefinition)));
			}
		}
		for (SequenceAnnotation sequenceAnnotation : componentDefinition.getSequenceAnnotations()) {
			if (!sequenceAnnotation.isSetComponent()) {
				if (keepGoing) {
					errors.add("Dropping SequenceAnnotation without a Component.\n:"+sequenceAnnotation.getIdentity());
				} else {
					throw new SBOLConversionException("Dropping SequenceAnnotation without a Component.\n:"+sequenceAnnotation.getIdentity());
				}				
			}
		}

		return NestedDocument(Sbol1Terms.DNAComponent.DNAComponent, 
				componentDefinition.getIdentity(), NamedProperties(list));		
	}
	
	private static void formatDNAComponent(ComponentDefinition componentDefinition, List<TopLevelDocument<QName>> topLevelDoc) throws SBOLConversionException {
		List<NamedProperty<QName>> list = new ArrayList<>();

		if(componentDefinition.isSetDisplayId())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.displayId, componentDefinition.getDisplayId()));
		if(componentDefinition.isSetName())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.name, componentDefinition.getName()));
		if(componentDefinition.isSetDescription())
			list.add(NamedProperty(Sbol1Terms.DNAComponent.description, componentDefinition.getDescription()));
		for(Annotation annotation : componentDefinition.getAnnotations())
		{
			if (!annotation.getValue().getName().getPrefix().equals("sbol"))
				list.add(annotation.getValue());
		}
		for (URI role : componentDefinition.getRoles())
		{
			URI purlRole = URI.create(role.toString().replace("http://identifiers.org/so/SO:", "http://purl.obolibrary.org/obo/SO_"));
			list.add(NamedProperty(Sbol1Terms.DNAComponent.type, purlRole));
		}
		Sequence sequence = componentDefinition.getSequenceByEncoding(Sequence.IUPAC_DNA);
		if ((sequence==null && componentDefinition.getSequences().size()>0) || 
				(componentDefinition.getSequences().size()>1)) {
			if (keepGoing) {
				errors.add("SBOL 1.1 only supports a single IUPAC_DNA Sequence.\n:"+componentDefinition.getIdentity());
			} else {
				throw new SBOLConversionException("SBOL 1.1 only supports a single IUPAC_DNA Sequence.\n:"+componentDefinition.getIdentity());
			}
		}
		if (sequence!=null) {
			list.add(NamedProperty(Sbol1Terms.DNAComponent.dnaSequence, getSequenceV1(sequence)));
		}
		for (Component component : componentDefinition.getComponents()) {
			SequenceAnnotation sequenceAnnotation = componentDefinition.getSequenceAnnotation(component);
			if (sequenceAnnotation!=null) {
				list.add(NamedProperty(Sbol1Terms.DNAComponent.annotations, getSequenceAnnotationV1(sequenceAnnotation,componentDefinition)));
			} else {
				list.add(NamedProperty(Sbol1Terms.DNAComponent.annotations, getComponentV1(component,componentDefinition)));
			}
		}
		for (SequenceAnnotation sequenceAnnotation : componentDefinition.getSequenceAnnotations()) {
			if (!sequenceAnnotation.isSetComponent()) {
				if (keepGoing) {
					errors.add("Dropping SequenceAnnotation without a Component.\n:"+sequenceAnnotation.getIdentity());
				} else {
					throw new SBOLConversionException("Dropping SequenceAnnotation without a Component.\n:"+sequenceAnnotation.getIdentity());
				}				
			}
		}

		topLevelDoc.add(TopLevelDocument(Sbol1Terms.DNAComponent.DNAComponent, 
				componentDefinition.getIdentity(), NamedProperties(list)));
	}
	
	
	private static void formatCollectionV1(Collection collection, List<TopLevelDocument<QName>> topLevelDoc) throws SBOLConversionException {
		List<NamedProperty<QName>> list = new ArrayList<>();

		if(collection.isSetDisplayId())
			list.add(NamedProperty(Sbol1Terms.Collection.displayId, collection.getDisplayId()));
		if(collection.isSetName())
			list.add(NamedProperty(Sbol1Terms.Collection.name, collection.getName()));
		if(collection.isSetDescription())
			list.add(NamedProperty(Sbol1Terms.Collection.description, collection.getDescription()));
		for(Annotation annotation : collection.getAnnotations())
		{
			if (!annotation.getValue().getName().getPrefix().equals("sbol"))
				list.add(annotation.getValue());
		}
		for (TopLevel topLevel : collection.getMembers()) {
			if (topLevel instanceof ComponentDefinition) {
				ComponentDefinition componentDefinition = (ComponentDefinition) topLevel;
				list.add(NamedProperty(Sbol1Terms.Collection.component, getSubComponent(componentDefinition)));
			} else {
				if (keepGoing) {
					errors.add("SBOL 1.1 only supports Collections of DNA ComponentDefinitions.\n:"+topLevel.getIdentity());
				} else {
					throw new SBOLConversionException("SBOL 1.1 only supports Collections of DNA ComponentDefinitions.\n:"+topLevel.getIdentity());
				}	
			}
		}

		topLevelDoc.add(TopLevelDocument(Sbol1Terms.Collection.Collection, 
				collection.getIdentity(), NamedProperties(list)));
	}
	
	private static void formatDNASequence(Sequence sequence, List<TopLevelDocument<QName>> topLevelDoc)
	{
		List<NamedProperty<QName>> list = new ArrayList<>();
		list.add(NamedProperty(Sbol1Terms.DNASequence.nucleotides, sequence.getElements()));
		topLevelDoc.add(TopLevelDocument(Sbol1Terms.DNASequence.DNASequence, sequence.getIdentity(), NamedProperties(list)));
	}
	
	private static List<NamespaceBinding> getNamespaceBindingsV1() {
		List<NamespaceBinding> bindings = new ArrayList<>();
		bindings.add(NamespaceBinding("http://sbols.org/v1#",""));
		bindings.add(NamespaceBinding("http://www.w3.org/1999/02/22-rdf-syntax-ns#","rdf"));
		return bindings;
	}

	private static List<TopLevelDocument<QName>> convertToV1Document(SBOLDocument doc) throws SBOLConversionException {
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<>();
		if (doc.getModuleDefinitions().size()>0) {
			if (keepGoing) {
				errors.add("SBOL 1.1 does not support ModuleDefinitions.");
			} else {
				throw new SBOLConversionException("SBOL 1.1 does not support ModuleDefinitions.\n");
			}	
		}
		if (doc.getModels().size()>0) {
			if (keepGoing) {
				errors.add("SBOL 1.1 does not support Models.");
			} else {
				throw new SBOLConversionException("SBOL 1.1 does not support Models.\n");
			}	
		}
		if (doc.getGenericTopLevels().size()>0) {
			if (keepGoing) {
				errors.add("SBOL 1.1 does not support GenericTopLevels.");
			} else {
				throw new SBOLConversionException("SBOL 1.1 does not support GenericTopLevels.\n");
			}	
		}
		for (Collection collection : doc.getCollections()) {
			formatCollectionV1(collection, topLevelDoc);
		}
		for (ComponentDefinition componentDefinition : doc.getRootComponentDefinitions()) {
			if (componentDefinition.getTypes().contains(ComponentDefinition.DNA)) {
				boolean skip = false;
				for (Collection collection : doc.getCollections()) {
					if (collection.getMemberURIs().contains(componentDefinition.getIdentity())) {
						skip = true;
						break;
					}
				}
				if (!skip) {
					formatDNAComponent(componentDefinition, topLevelDoc);
				}
			} else {
				if (keepGoing) {
					errors.add("SBOL 1.1 only supports DNA ComponentDefinitions.\n:"+componentDefinition.getIdentity());
				} else {
					throw new SBOLConversionException("SBOL 1.1 only supports DNA ComponentDefinitions.\n:"+componentDefinition.getIdentity());
				}	
			}
		}
		for (Sequence sequence : doc.getSequences()) {
			boolean skip = false;
			for (ComponentDefinition componentDefinition : doc.getComponentDefinitions()) {
				if (componentDefinition.getSequenceURIs().contains(sequence.getIdentity())) {
					skip = true;
					break;
				}
			}
			if (!skip) {
				formatDNASequence(sequence, topLevelDoc);
			}
		}
		return topLevelDoc;
	}

	private static List<TopLevelDocument<QName>> getTopLevelDocument(SBOLDocument doc) {
		List<TopLevelDocument<QName>> topLevelDoc = new ArrayList<>();
		formatCollections(doc.getCollections(), topLevelDoc);
		formatModuleDefinitions(doc.getModuleDefinitions(), topLevelDoc);
		formatModels(doc.getModels(), topLevelDoc);
		formatComponentDefinitions(doc.getComponentDefinitions(), topLevelDoc);
		formatSequences(doc.getSequences(), topLevelDoc);
		formatGenericTopLevel(doc.getGenericTopLevels(), topLevelDoc);
		return topLevelDoc;
	}

}
