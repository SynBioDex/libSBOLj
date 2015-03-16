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

import org.sbolstandard.core.SBOLValidationException;
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

	public static Sequence createSequence(SBOLDocument document,String id)
	{
		Sequence sequence = document.createSequence("http://www.async.ece.utah.edu", id, "1/0", id + "_element", URI.create("http://encodings.org/encoding"));

		sequence.setName(id);
		sequence.setDescription(id);
		return sequence;
	}

	public static ComponentDefinition createComponentDefinition(SBOLDocument document, String id,
			String type, String role, URI sequenceId,
			List<SequenceAnnotation> sequenceAnnotations,
			List<SequenceConstraint> sequenceConstraints,
			List<Component> subComponents)
	{
		ComponentDefinition componentDefinition = document.createComponentDefinition("http://www.async.ece.utah.edu", id,
				SBOLTestUtils.getSetPropertyURI(type), SBOLTestUtils.getSetPropertyURI(role));

		componentDefinition.setName(id);
		componentDefinition.setDescription(id);

		//URI.create("http://www.async.ece.utah.edu/"+sequenceId+"/1/0"
		if (sequenceId!=null)
			componentDefinition.setSequence(sequenceId);
		if (sequenceAnnotations!=null)
			componentDefinition.setSequenceAnnotations(sequenceAnnotations);
		if (sequenceConstraints!=null)
			componentDefinition.setSequenceConstraints(sequenceConstraints);
		if (subComponents!=null)
			componentDefinition.setSubComponents(subComponents);

		return componentDefinition;
	}

	public static GenericTopLevel createGenericTopLevel(SBOLDocument document, String id)
	{
		GenericTopLevel genericToplevel =  document.createGenericTopLevel(URI.create("http://www.async.ece.utah.edu/"+id+"/1/0"),
				new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		genericToplevel.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		genericToplevel.setDisplayId(id);
		genericToplevel.setName(id);
		genericToplevel.setDescription(id);
		return genericToplevel;
	}

	public static Collection createCollection(SBOLDocument document, String id,
			List<Annotation> annotations)
	{
		Collection collection = document.createCollection(URI.create("http://www.async.ece.utah.edu/"+id+"/1/0"));

		collection.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		collection.setDisplayId(id);
		collection.setName(id);
		collection.setDescription(id);

		if(annotations != null)
			collection.setAnnotations(annotations);
		return collection;
	}

	//TODO: consider removing
	public static Annotation createAnnotation(QName relation, Turtle literal)
	{
		if(relation == null || literal == null)
			return null;
		return new Annotation(relation, literal);
	}

	public static FunctionalComponent createFunctionalComponent(String id,
			String access, String direction, URI instantiatedComponent)
	{
		AccessType accessType = null;
		if(access.equals("public"))
			accessType = AccessType.PUBLIC;
		else if(access.equals("private"))
			accessType = AccessType.PRIVATE;

		DirectionType directionType = null;
		if(direction.equals("input"))
			directionType = DirectionType.INPUT;
		else if(direction.equals("output"))
			directionType = DirectionType.OUTPUT;
		else if(direction.equals("inout"))
			directionType = DirectionType.INOUT;
		else if(direction.equals("none"))
			directionType = DirectionType.NONE;

		return new FunctionalComponent(URI.create(id), accessType, instantiatedComponent, directionType);
	}

	public static Interaction createInteraction(String id,
			List<Participation> participations, Set<URI> type)
	{
		return new Interaction(URI.create(id), type, participations);
	}

	//TODO: consider removing
	public static Turtle createTurtle()
	{
		return new Turtle("turtleString");
	}

	public static MapsTo createMapTo (String id, String refinement,
			URI pre_fi, URI post_fi)
	{
		RefinementType refinementType = null;
		if(refinement.equals("verifyIdentical"))
			refinementType =  RefinementType.VERIFYIDENTICAL;
		else if(refinement.equals("useLocal"))
			refinementType =  RefinementType.USELOCAL;
		else if(refinement.equals("useRemote"))
			refinementType =  RefinementType.USEREMOTE;
		else if(refinement.equals("merge"))
			refinementType =  RefinementType.MERGE;

		return new MapsTo(URI.create(id), refinementType, pre_fi, post_fi);
	}

	public static void createModel(SBOLDocument document, String id,
			String source, String language, String framework, Set<URI> roles)
	{
		Model model = document.createModel(URI.create(id), URI.create(source), URI.create(language), URI.create(framework), roles);
		model.setName(id);
		model.setDescription(id);
	}

	public static ModuleDefinition createModuleDefinition(SBOLDocument document, String id,
			Set<URI> roles,
			List<FunctionalComponent> functionalComponent_data,
			List<Interaction> interactionData,
			List<Module> submodule_data,
			Set<URI> model_data,
			List<Annotation> annotations)
	{
		ModuleDefinition m = document.createModuleDefinition(URI.create(id), roles);

		m.setName(id);
		m.setDescription(id);

		if(annotations != null)
			m.setAnnotations(annotations);
		if(functionalComponent_data != null)
			m.setComponents(functionalComponent_data);
		if(interactionData != null)
			m.setInteractions(interactionData);
		if(submodule_data != null)
			m.setSubModules(submodule_data);
		if(model_data != null)
			m.setModels(model_data);

		return m;
	}

	//TODO: check
	public static Module createModuleData(SBOLDocument document, String id,
			String instantiatedModule,
			List<MapsTo> maps)
	{
		Module modInstantiation = new Module(URI.create(id), URI.create(instantiatedModule));

		modInstantiation.setName(id);
		modInstantiation.setDescription(id);

		for(MapsTo map : maps)
			modInstantiation.addMapping(map);

		return modInstantiation;
	}

	//TODO: consider adding particpation
	public static Participation createParticipation(
			String id, Set<URI> roles, URI fi)
	{
		return new Participation(URI.create(id), roles, fi);
	}

	//TODO: decide if SequenceAnnotation is always a Range for location
	public static SequenceAnnotation createSequenceAnnotation(
			String id,
			Component ref_component,
			Location location)
	{
		Location loc = location;

		return new SequenceAnnotation(URI.create(id), loc);
	}

	public static SequenceConstraint createSequenceConstraint(String id,
			String subject, String object, String restriction)
	{
		RestrictionType restrictionType = null;
		if(restriction.equals("precedes"))
			restrictionType = restrictionType.PRECEDES;

		return new SequenceConstraint(URI.create(id), restrictionType, URI.create(subject), URI.create(object));
	}

	public static Component createComponent(String id, String access, String instantiatedComponent)
	{
		AccessType accessType = null;
		if(access.equals("public"))
			accessType = AccessType.PUBLIC;
		else if(access.equals("private"))
			accessType = AccessType.PRIVATE;

		return new Component(URI.create(id), accessType, URI.create(instantiatedComponent));
	}

	//TODO: See if this is ever called.
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
		//		if(majorVersion != null)
		//			i.setMajorVersion(majorVersion);
		//		if(minorVersion != null)
		//			i.setMinorVersion(minorVersion);
		// TODO: Insert version here.
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
}
