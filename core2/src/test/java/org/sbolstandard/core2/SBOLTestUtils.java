package org.sbolstandard.core2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.MapsTo.RefinementType;
import org.sbolstandard.core2.SequenceConstraint.RestrictionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;
import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.Identified;
import org.sbolstandard.core2.abstract_classes.Location;
import org.sbolstandard.core2.abstract_classes.TopLevel;

import uk.ac.ncl.intbio.core.io.CoreIoException;

/**
 * Construction of TopLevel objects along with any of its' sub-parts.
 * @author Tramy Nguyen
 *
 */
public class SBOLTestUtils {
	private SBOLTestUtils() {
	}

	//	public static GenericTopLevel createGenericTopLevel(SBOLDocument SBOL2Doc_test, List<String> topLevelData)
	public static GenericTopLevel createGenericTopLevel(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion)
	{

		//TODO: ask if genericTopLevel is always set to the following QName
		//		GenericTopLevel toplevel =  SBOL2Doc_test.createGenericTopLevel(identity, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		GenericTopLevel genericToplevel =  new GenericTopLevel(identity, new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));

		//TODO: commented this NameSpaceBinding and moved it to createDocument() in this class
		//		SBOL2Doc_test.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");

		setCommonTopLevelData(genericToplevel, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);
		return genericToplevel;
	}


	//	public static Collection createCollection(SBOLDocument SBOL2Doc_test, List<String> collectionData,
	//			List<Annotation> annotations)
	public static Collection createCollection(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			List<Annotation> annotations)
	{
		//		Collection collection = SBOL2Doc_test.createCollection(identity);
		Collection collection = new Collection(identity);
		setCommonTopLevelData(collection, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);
		if(annotations != null)
			collection.setAnnotations(annotations);
		return collection;
	}

	//TODO: consider putting annotation in or not.
	public static Annotation createAnnotation(QName relation, Turtle literal)
	{
		return new Annotation(relation, literal);
	}

	//	public static ComponentDefinition createComponentDefinitionData(SBOLDocument SBOL2Doc_test,
	//			Set<URI> type, Set<URI> roles,
	//			List<String> componentData,
	//			Sequence structureData,
	//			List<Component> structureInstantiationData,
	//			List<SequenceAnnotation> structureAnnotationData,
	//			List<SequenceConstraint> structureConstraintData)
	public static ComponentDefinition createComponentDefinitionData(
			Set<URI> type, Set<URI> roles,
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			Sequence structureData,
			List<Component> structureInstantiationData,
			List<SequenceAnnotation> structureAnnotationData,
			List<SequenceConstraint> structureConstraintData)
	{
		//		ComponentDefinition c = SBOL2Doc_test.createComponentDefinition(identity, type, roles);
		ComponentDefinition c = new ComponentDefinition(identity, type, roles);
		setCommonTopLevelData(c, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);

		if(structureData != null)
			c.setSequence(structureData.getIdentity());
		if(structureInstantiationData != null)
		{
			c.setSubComponents(structureInstantiationData);
			if(structureAnnotationData != null && structureConstraintData == null)
				c.setSequenceAnnotations(structureAnnotationData);
			else if(structureConstraintData != null)
				c.setSequenceConstraints(structureConstraintData);
		}
		return c;
	}

	public static FunctionalComponent createFunctionalComponentData(
			AccessType access, DirectionType direction,
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			ComponentDefinition c)
	{
		//		AccessType access = null;
		//		if(functionalInstantiation_data.get(6).equals("public"))
		//			access = AccessType.PUBLIC;
		//		else if(functionalInstantiation_data.get(6).equals("private"))
		//			access = AccessType.PRIVATE;
		//
		//		DirectionType direction = null;
		//		if(functionalInstantiation_data.get(7).equals("input"))
		//			direction = DirectionType.INPUT;
		//		else if(functionalInstantiation_data.get(7).equals("output"))
		//			direction = DirectionType.OUTPUT;
		//		else if(functionalInstantiation_data.get(7).equals("inout"))
		//			direction = DirectionType.INOUT;
		//		else if(functionalInstantiation_data.get(7).equals("none"))
		//			direction = DirectionType.NONE;

		URI instantiatedComponent = c.getIdentity();

		FunctionalComponent f = new FunctionalComponent(identity, access, instantiatedComponent, direction);
		setCommonDocumentedData(f, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);

		return f;
	}

	public static Interaction createInteractionData(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			List<Participation> participations,
			Set<URI> type)
	{
		Interaction interaction = new Interaction(identity, type, participations);

		setCommonDocumentedData(interaction, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);


		return interaction;
	}

	public static Turtle createTurtle()
	{
		return new Turtle("turtleString");
	}

	public static MapsTo createMapTo (URI identity, RefinementType refinement,
			FunctionalComponent pre_fi, FunctionalComponent post_fi)
	{
		return new MapsTo(identity, refinement, pre_fi.getIdentity(), post_fi.getIdentity());
	}


	//	public static Model createModelData(SBOLDocument doc, List<String> modeldata, Set<URI> roles,
	//			URI source, URI language, URI framework)
	public static Model createModelData(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			Set<URI> roles,
			URI source, URI language, URI framework)
	{
		Model model = new Model(identity, source, language, framework, roles);
		setCommonTopLevelData(model, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);

		return model;
	}

	//	public static ModuleDefinition createModuleDefinitionData(SBOLDocument SBOL2Doc_test,
	//			Set<URI> type, Set<URI> roles,
	//			List<String> module_data,
	//			List<FunctionalComponent> functionalInstantiation_data,
	//			List<Interaction> interactionData,
	//			List<Module> moduleInstantiation_data,
	//			Set<URI> model_data,
	//			List<Annotation> annotations)
	public static ModuleDefinition createModuleDefinitionData(
			Set<URI> type, Set<URI> roles,
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			List<FunctionalComponent> functionalInstantiation_data,
			List<Interaction> interactionData,
			List<Module> moduleInstantiation_data,
			Set<URI> model_data,
			List<Annotation> annotations)
	{
		ModuleDefinition m = new ModuleDefinition(identity, roles);
		setCommonTopLevelData(m, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);

		if(annotations != null)
			m.setAnnotations(annotations);
		if(functionalInstantiation_data != null)
			m.setComponents(functionalInstantiation_data);
		if(interactionData != null)
			m.setInteractions(interactionData);
		if(moduleInstantiation_data != null)
			m.setSubModules(moduleInstantiation_data);
		if(model_data != null)
			m.setModels(model_data);

		return m;
	}

	public static Module createModuleData(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			ModuleDefinition m,
			List<MapsTo> maps)
	{
		Module modInstantiation = new Module(identity, m.getIdentity());
		setCommonDocumentedData(modInstantiation, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);

		for(MapsTo map : maps)
			modInstantiation.addMapping(map);

		return modInstantiation;
	}

	public static Participation createParticipationData(
			URI identity, Set<URI> roles, FunctionalComponent fi)
	{
		return new Participation(identity, roles, fi.getIdentity());
	}

	public static SequenceAnnotation createSequenceAnnotationData(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			Component ref_component,
			int startRange, int endRange,
			URI locationURI)
	{
		Range r = new Range(locationURI, startRange, endRange);
		r.setOrientation(Sbol2Terms.Orientation.inline); //TODO: check if this is always inline?

		Location location 	   = r;

		SequenceAnnotation s = new SequenceAnnotation(identity, location);

		setCommonDocumentedData(s, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);


		return s;
	}

	public static SequenceConstraint createSequenceConstraintData(
			URI subject, URI object,
			URI identity, URI persistentIdentity,
			Integer majorVersion, Integer minorVersion,
			RestrictionType restriction)
	{
		SequenceConstraint s = new SequenceConstraint(identity, restriction, subject, object);
		setCommonIdentifiedData(s, identity, persistentIdentity,
				majorVersion, minorVersion);

		return s;
	}

	public static Component createComponentData(
			AccessType access,
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			ComponentDefinition c)
	{

		//		AccessType access = null;
		//		if(structuralInstantiations_data.get(6).equals("public"))
		//			access = AccessType.PUBLIC;
		//		else if(structuralInstantiations_data.get(6).equals("private"))
		//			access = AccessType.PRIVATE;

		URI instantiatedComponent = c.getIdentity();

		Component s = new Component(identity, access, instantiatedComponent);
		setCommonDocumentedData(s, identity, persistentIdentity,
				displayId, name, description, majorVersion, minorVersion);


		return s;
	}

	//	public static Sequence createSequenceData(SBOLDocument SBOL2Doc_test, List<String> structureData,
	//			URI encoding)
	public static Sequence createSequenceData(
			URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion,
			String element, URI encoding)
	{
		Sequence structure = new Sequence(identity, element, encoding);
		setCommonTopLevelData(structure, identity, persistentIdentity,
				displayId, name, description,  majorVersion,  minorVersion);

		return structure;
	}

	public static List<Annotation> getAnnotation_List(Annotation ... a)
	{
		return new ArrayList<Annotation>(Arrays.asList(a));
	}

	public static List<FunctionalComponent> getFunctionalComponent_List(FunctionalComponent ... fi)
	{
		return new ArrayList<FunctionalComponent>(Arrays.asList(fi));
	}

	public static List<Module> getModule_List(Module ... mi)
	{
		return new ArrayList<Module>(Arrays.asList(mi));
	}

	public static List<Interaction> getInteraction_List(Interaction ... i)
	{
		return new ArrayList<Interaction>(Arrays.asList(i));
	}

	public static List<MapsTo> getMapsTo_List(MapsTo ... maps)
	{
		return new ArrayList<MapsTo>(Arrays.asList(maps));
	}

	//	public static Collection createCollection(int id, DnaComponent... components) {
	//		Collection coll1 = SBOLFactory.createCollection();
	//		coll1.setURI(uri("http://example.com/collection" + id));
	//		coll1.setDisplayId("Coll" + id);
	//		coll1.setName("Collection" + id);
	//		for (DnaComponent component : components) {
	//	        coll1.addComponent(component);
	//        }
	//		return coll1;
	//	}

	public static void setCommonTopLevelData (TopLevel t, URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion)
	{
		setCommonDocumentedData(t, identity, persistentIdentity, displayId, name, description,
				majorVersion,  minorVersion);
	}

	public static void setCommonDocumentedData (Documented d, URI identity, URI persistentIdentity,
			String displayId, String name, String description,
			Integer majorVersion, Integer minorVersion)
	{
		if(displayId != null)
			d.setDisplayId(displayId);
		if(name != null)
			d.setName(name);
		if(description != null)
			d.setDescription(description);

		setCommonIdentifiedData(d, identity, persistentIdentity, majorVersion, minorVersion);
	}

	public static void setCommonIdentifiedData (Identified i, URI identity, URI persistentIdentity,
			Integer majorVersion, Integer minorVersion)
	{
		if(identity != null)
			i.setIdentity(identity);
		if(persistentIdentity != null)
			i.setPersistentIdentity(persistentIdentity);
		if(majorVersion != null)
			i.setMajorVersion(majorVersion);
		if(minorVersion != null)
			i.setMinorVersion(minorVersion);
		//		i.setTimeStamp(timeStamp); //TODO: is timeStamp provided by user?
	}

	public static SBOLDocument createDocument(TopLevel... contents) {
		SBOLDocument document = new SBOLDocument();
		document.addNameSpaceBinding(URI.create("http://myannotation.org"), "annot");
		for(TopLevel topLevel: contents)
		{
			if(topLevel instanceof Collection)
			{
				document.addCollection((Collection)topLevel);
			}
			else if(topLevel instanceof ModuleDefinition)
			{
				document.addModuleDefinition((ModuleDefinition)topLevel);
			}
			else if(topLevel instanceof Model)
			{
				document.addModel((Model)topLevel);
			}
			else if(topLevel instanceof ComponentDefinition)
			{
				document.addComponentDefinition((ComponentDefinition)topLevel);
			}
			else if(topLevel instanceof Sequence)
			{
				document.addSequence((Sequence)topLevel);
			}
			else if(topLevel instanceof GenericTopLevel)
			{
				document.addGenericTopLevel((GenericTopLevel)topLevel);
				//TODO: this might cause issue due to duplicate namespace?
				document.addNameSpaceBinding(URI.create("urn:bbn.com:tasbe:grn"), "grn");
			}
		}
		return document;
	}

	public static SBOLDocument writeAndRead(SBOLDocument doc)
			throws SBOLValidationException, IOException, XMLStreamException, FactoryConfigurationError, CoreIoException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SBOLWriter.write(doc, out);

		return SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
	}

	public static URI createCompliantIdentity(String id)
	{
		return URI.create("http://www.async.ece.utah.edu/" + id + "/1/0");
	}

	public static URI createCompliantPersistentIdentity(String id)
	{
		return URI.create("http://www.async.ece.utah.edu/" + id);
	}

	public static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}

	public static Set<URI> getSetPropertyURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getPropertyURI(append));
		}
		return list;
	}

	public static URI getPropertyURI(String append)
	{
		return URI.create("http://some.ontology.org/" + append);
	}

	//	public static URI uri(String uri)
	//	{
	//		return URI.create(uri);
	//	}

	//	public static void assertInvalid(final String fileName, String expectedMessage) throws Exception {
	//		try {
	//			assertValid(fileName);
	//			fail("Invalid file passed validation: " + fileName);
	//		}
	//		catch (SBOLValidationException e) {
	//			String msgFormat = "Validation exception message does not contain expected message:%n  Expected=%s%n  Actual=%s";
	//			String msg = String.format(msgFormat, expectedMessage, e.getMessage());
	//			if(!e.getMessage().contains(expectedMessage) && (e.getCause() == null || !e.getCause().getMessage().contains(expectedMessage))) {
	//                throw(AssertionError) new AssertionError(msg).initCause(e);
	//            }
	//		}
	//		catch (Exception e) {
	//			throw e;
	//		}
	//	}
	//
	//	public static void assertValid(final String fileName) throws Exception {
	//		// reading the document ensures validity
	//        InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
	//        if(resourceAsStream == null) resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);
	//
	//        assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";
	//
	//        try {
	//            SBOLFactory.read(resourceAsStream);
	//        } catch (IOException e) {
	//            throw new AssertionError("Failed to validate " + fileName, e);
	//        } catch (SBOLValidationException e) {
	//            throw new SBOLValidationException("Failed to validate " + fileName, e);
	//        }
	//	}
	//
	//	public static void assertInvalid(final SBOLDocument doc, String expectedMessage) throws Exception {
	//		try {
	//			assertValid(doc);
	//			fail("Invalid doc passed validation: " + doc);
	//		}
	//		catch (SBOLValidationException e) {
	//			String msgFormat = "Validation exception message does not contain expected message:%n  Expected=%s%n  Actual=%s";
	//			String msg = String.format(msgFormat, expectedMessage, e.getMessage());
	//            if(!e.getMessage().contains(expectedMessage)) {
	//                throw new AssertionError(msg, e);
	//            }
	//		}
	//	}
	//
	//	public static void assertValid(final SBOLDocument doc) throws Exception {
	//		SBOLFactory.validate(doc);
	//	}
}