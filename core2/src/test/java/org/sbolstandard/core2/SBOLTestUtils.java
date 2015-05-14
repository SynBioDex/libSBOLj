package org.sbolstandard.core2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.sbolstandard.core.SBOLValidationException;
import org.sbolstandard.core2.ComponentInstance.AccessType;
import org.sbolstandard.core2.FunctionalComponent.DirectionType;
import org.sbolstandard.core2.MapsTo.RefinementType;
import org.sbolstandard.core2.SequenceConstraint.RestrictionType;

import uk.ac.ncl.intbio.core.io.CoreIoException;

/**
 * Construction of TopLevel objects along with any of its' sub-parts.
 * @author Tramy Nguyen
 *
 */
public class SBOLTestUtils {
	private SBOLTestUtils() {
	}

	public static SBOLDocument convertSBOL1(String fileName, String fileType)
	{
		InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
		if (resourceAsStream == null)
			resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

		assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";
		SBOLDocument actual = null;
		SBOLReader.setURIPrefix("http://www.async.ece.utah.edu");

		try {
			if(fileType.equals("rdf"))
				actual = SBOLReader.readRDF(resourceAsStream);
			else if(fileType.equals("json"))
				actual = SBOLReader.readJSON(resourceAsStream);
			else if(fileType.equals("turtle"))
				actual = SBOLReader.readTurtle(resourceAsStream);
			else
				actual = SBOLReader.read(resourceAsStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actual;

	}

	public static Sequence createSequence(SBOLDocument document,String id, List<Annotation> annotations)
	{
		//		Sequence sequence = document.createSequence("http://www.async.ece.utah.edu",
		//				id, "1.0", id + "_element", URI.create("http://encodings.org/encoding"));

		document.setDefaultURIprefix("http://www.async.ece.utah.edu");
		Sequence sequence = document.createSequence(
				id, "1.0", id + "_element", URI.create("http://encodings.org/encoding"));

		sequence.setName(id);
		sequence.setDescription(id);

		if(annotations != null)
			sequence.setAnnotations(annotations);
		return sequence;
	}


	public static ComponentDefinition createComponentDefinition(SBOLDocument document, String id,
			Set<URI> type, Set<URI> role, URI sequenceId,
			List<SequenceAnnotation> sequenceAnnotations,
			List<SequenceConstraint> sequenceConstraints,
			List<Component> subComponents, List<Annotation> annotations)
	{
		//		ComponentDefinition componentDefinition = document.createComponentDefinition(
		//				"http://www.async.ece.utah.edu", id, "1/0",
		//				type, role);

		document.setDefaultURIprefix("http://www.async.ece.utah.edu");
		//		ComponentDefinition componentDefinition = document.createComponentDefinition(
		//				id, "1.0",type, role);
		ComponentDefinition componentDefinition = document.createComponentDefinition(
				id, "1.0",type);
		if(role != null)
			componentDefinition.setRoles(role);

		//		componentDefinition.setName(id);
		//		componentDefinition.setDescription(id);

		if (sequenceId!= null)
			componentDefinition.addSequence(sequenceId);
		if (sequenceAnnotations!= null)
			componentDefinition.setSequenceAnnotations(sequenceAnnotations);
		if (sequenceConstraints!= null)
			componentDefinition.setSequenceConstraints(sequenceConstraints);
		if (subComponents!= null)
			componentDefinition.setComponents(subComponents);
		if (annotations != null)
			componentDefinition.setAnnotations(annotations);
		return componentDefinition;
	}


	public static GenericTopLevel createGenericTopLevel(SBOLDocument document, String id,
			List<Annotation> annotations)
	{
		GenericTopLevel genericToplevel =  document.createGenericTopLevel(
				URI.create("http://www.async.ece.utah.edu/"+id+"/1/0"),
				new QName("urn:bbn.com:tasbe:grn", "RegulatoryReaction", "grn"));
		genericToplevel.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		genericToplevel.setDisplayId(id);
		genericToplevel.setName(id);
		genericToplevel.setDescription(id);
		if(annotations != null)
			genericToplevel.setAnnotations(annotations);
		return genericToplevel;
	}


	public static Collection createCollection(SBOLDocument document, String id,
			List<Annotation> annotations)
	{
		//		Collection collection = document.createCollection(URI.create("http://www.async.ece.utah.edu/"+id+"/1/0"));
		Collection collection = document.createCollection(URI.create(id));
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
				URI.create(id + "_framework"));
		model.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		model.setDisplayId(id);
		model.setName(id);
		model.setDescription(id);
		if(annotations != null)
			model.setAnnotations(annotations);
		return model;
	}


	public static Module createModule(String id, URI instantiatedModule,
			List<Annotation> annotations)
	{
		Module m = new Module(URI.create(id), instantiatedModule);
		if(annotations != null)
			m.setAnnotations(annotations);
		return m;
	}


	public static Participation createParticipation(String id, URI instantiatedModule,
			List<Annotation> annotations)
	{
		Participation m = new Participation(URI.create(id), null);
		if(annotations != null)
			m.setAnnotations(annotations);
		return m;
	}


	public static FunctionalComponent createFunctionalComponent(String id,
			AccessType accessType, DirectionType directionType, URI instantiatedComponent,
			List<Annotation> annotations)
	{
		FunctionalComponent f = new FunctionalComponent(URI.create(id), accessType, instantiatedComponent, directionType);
		if (annotations != null)
			f.setAnnotations(annotations);
		return f;
	}


	public static MapsTo createMapTo (String id, RefinementType refinementType,
			URI pre_fi, URI post_fi, List<Annotation> annotations)
	{
		MapsTo m = new MapsTo(URI.create(id), refinementType, pre_fi, post_fi);
		if (annotations != null)
			m.setAnnotations(annotations);
		return m;
	}


	public static ModuleDefinition createModuleDefinition(SBOLDocument document, String id,
			Set<URI> roles,
			List<FunctionalComponent> functionalComponents,
			List<Interaction> interactions,
			List<Module> submodules,
			Set<URI> models,
			List<Annotation> annotations)
	{
		ModuleDefinition m = document.createModuleDefinition(URI.create(id));
		m.setRoles(roles);
		//		m.setPersistentIdentity(URI.create("http://www.async.ece.utah.edu/"+id));
		m.setDisplayId(id);
		m.setName(id);
		m.setDescription(id);

		if(annotations != null)
			m.setAnnotations(annotations);
		if(functionalComponents != null)
			m.setFunctionalComponents(functionalComponents);
		if(interactions != null)
			m.setInteractions(interactions);
		if(submodules != null)
			m.setModules(submodules);
		if(models != null)
			m.setModels(models);

		return m;
	}

	public static SequenceAnnotation createSequenceAnnotation(String id, List<Location> locations,
			List<Annotation> annotations)
	{
		SequenceAnnotation sa = new SequenceAnnotation(URI.create(id), locations);
		if(annotations != null)
			sa.setAnnotations(annotations);
		return sa;
	}


	public static Interaction createInteraction(String id, Set<URI> type,
			List<Participation> participations, List<Annotation> annotations)
	{
		Interaction i = new Interaction(URI.create(id), type);
		if (participations != null) {
			i.setParticipations(participations);
		}
		if (annotations != null)
			i.setAnnotations(annotations);
		return i;
	}

	//TODO:
	//	public static ComponentInstance createComponentInstance(SBOLDocument document, String id, Set<URI> type,
	//			List<Participation> participations, List<Annotation> annotations)
	//	{
	//		ComponentInstance i = new Co
	//		if (annotations != null)
	//			i.setAnnotations(annotations);
	//		return i;
	//	}


	public static Location createLocation(String id, Location location, List<Annotation> annotations)
	{
		Location l = location;
		if (annotations != null)
			l.setAnnotations(annotations);
		return l;
	}


	public static SequenceConstraint createSequenceConstraint(String id,
			URI subject, URI object, RestrictionType restrictionType,
			List<Annotation> annotations)
	{
		SequenceConstraint s = new SequenceConstraint(URI.create(id), restrictionType, subject, object);
		if (annotations != null)
			s.setAnnotations(annotations);
		return s;
	}


	public static Component createComponent(String id, AccessType accessType, URI instantiatedComponent,
			List<Annotation> annotations)
	{
		Component c = new Component(URI.create(id), accessType, instantiatedComponent);
		if(annotations != null)
			c.setAnnotations(annotations);
		return c;
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
