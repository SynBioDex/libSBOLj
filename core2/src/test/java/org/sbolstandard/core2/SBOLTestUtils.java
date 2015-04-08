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
import org.sbolstandard.core2.abstract_classes.Location;

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
		Sequence sequence = document.createSequence("http://www.async.ece.utah.edu",
				id, "1/0", id + "_element", URI.create("http://encodings.org/encoding"));

		sequence.setName(id);
		sequence.setDescription(id);
		return sequence;
	}

	public static ComponentDefinition createComponentDefinition(SBOLDocument document, String id,
			Set<URI> type, Set<URI> role, URI sequenceId,
			List<SequenceAnnotation> sequenceAnnotations,
			List<SequenceConstraint> sequenceConstraints,
			List<Component> subComponents)
	{
		ComponentDefinition componentDefinition = document.createComponentDefinition(
				"http://www.async.ece.utah.edu", id,
				type, role);

		componentDefinition.setName(id);
		componentDefinition.setDescription(id);

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
		GenericTopLevel genericToplevel =  document.createGenericTopLevel(
				URI.create("http://www.async.ece.utah.edu/"+id+"/1/0"),
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

	public static Model createModel(SBOLDocument document, String id, List<Annotation> annotations)
	{
		Model model = document.createModel(URI.create(id),
				URI.create(id + "_source"),
				URI.create(id + "_language"),
				URI.create(id + "_framework"),
				getSetPropertyURI(id + "_role"));

		model.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		model.setDisplayId(id);
		model.setName(id);
		model.setDescription(id);

		if(annotations != null)
			model.setAnnotations(annotations);
		return model;
	}

	//TODO: consider removing
	//	public static Annotation createAnnotation(QName relation, Turtle literal)
	//	{
	//		if(relation == null || literal == null)
	//			return null;
	//		return new Annotation(relation, literal);
	//	}

	public static FunctionalComponent createFunctionalComponent(String id,
			AccessType accessType, DirectionType directionType, URI instantiatedComponent)
	{
		return new FunctionalComponent(URI.create(id), accessType, instantiatedComponent, directionType);

	}


	public static MapsTo createMapTo (String id, RefinementType refinementType,
			URI pre_fi, URI post_fi)
	{
		return new MapsTo(URI.create(id), refinementType, pre_fi, post_fi);
	}

	public static ModuleDefinition createModuleDefinition(SBOLDocument document, String id,
			Set<URI> roles,
			List<FunctionalComponent> functionalComponents,
			List<Interaction> interactions,
			List<Module> submodules,
			Set<URI> models,
			List<Annotation> annotations)
	{
		ModuleDefinition m = document.createModuleDefinition(URI.create(id), roles);
		m.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		m.setDisplayId(id);
		m.setName(id);
		m.setDescription(id);

		if(annotations != null)
			m.setAnnotations(annotations);
		if(functionalComponents != null)
			m.setComponents(functionalComponents);
		if(interactions != null)
			m.setInteractions(interactions);
		if(submodules != null)
			m.setSubModules(submodules);
		if(models != null)
			m.setModels(models);

		return m;
	}

	//TODO: check
	public static Module createModuleData(SBOLDocument document, String id,
			URI instantiatedModule,
			List<MapsTo> maps)
	{
		Module modInstantiation = new Module(URI.create(id), instantiatedModule);

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


	public static SequenceAnnotation createSequenceAnnotation(String id, Location location)
	{
		return new SequenceAnnotation(URI.create(id), location);
	}

	public static Interaction createInteraction(String id, Set<URI> type,
			List<Participation> participations)
	{
		return new Interaction(URI.create(id), type, participations);
	}

	public static SequenceConstraint createSequenceConstraint(String id,
			URI subject, URI object, RestrictionType restrictionType)
	{
		return new SequenceConstraint(URI.create(id), restrictionType, subject, object);
	}

	public static Component createComponent(String id, AccessType accessType, URI instantiatedComponent)
	{
		return new Component(URI.create(id), accessType, instantiatedComponent);
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
