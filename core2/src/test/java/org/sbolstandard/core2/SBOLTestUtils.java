package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.sbolstandard.core.SBOLValidationException;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.io.CoreIoException;

/**
 * Construction of TopLevel objects along with any of its' sub-parts.
 * @author Tramy Nguyen
 *
 */
public class SBOLTestUtils {

	private SBOLTestUtils()
	{
	}

	public static final NamespaceBinding example=NamespaceBinding ("http://sbolstandard.org/example/", "example");
	public static final NamespaceBinding biopax=NamespaceBinding ("http://www.biopax.org/release/biopax-level3.owl#", "biopax");
	public static final NamespaceBinding so=NamespaceBinding ("http://identifiers.org/so/", "so");
	public static final NamespaceBinding sbo=NamespaceBinding ("http://identifiers.org/biomodels.sbo/", "sbo");
	public static final NamespaceBinding pr=NamespaceBinding ("http://www.partsregistry.org/", "pr");
	public static final NamespaceBinding vpr=NamespaceBinding ("http://www.virtualparts.org/part/", "vpr");
	public static final NamespaceBinding uniprot=NamespaceBinding ("http://identifiers.org/uniprot/", "uniprot");


	public static class Terms
	{
		public static class sequenceTypes
		{
			public static URI nucleotides=URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html");
			public static URI aminoacids=URI.create("http://www.chem.qmul.ac.uk/iupac/AminoAcid/");
			public static URI atoms=URI.create("http://www.opensmiles.org/opensmiles.html");
		}

		public static class moduleRoles
		{
			public static URI inverter=URI.create("http://parts.igem.org/cgi/partsdb/pgroup.cgi?pgroup=inverter");

		}
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

	public static void setDefaultNameSpace(SBOLDocument document, String uri)
	{
		if (uri.endsWith("/"))
		{
			uri=uri.substring(0,uri.length()-1);
		}
		document.setDefaultURIprefix(uri);
	}

	public static void createInverter(SBOLDocument document, ModuleDefinition moduleDef, ComponentDefinition promoter, ComponentDefinition TF) throws Exception
	{
		FunctionalComponent  laciInverterModuleDef_promoter=moduleDef.createFunctionalComponent(
				"promoter",
				AccessType.PUBLIC,
				promoter.getIdentity(),
				DirectionType.INOUT);

		FunctionalComponent  laciInverterModuleDef_TF=moduleDef.createFunctionalComponent(
				"TF",
				AccessType.PUBLIC,
				TF.getIdentity(),
				DirectionType.INOUT);

		Interaction interaction=moduleDef.createInteraction(
				"LacI_pLacI",
				new HashSet<URI>(Arrays.asList(SystemsBiologyOntology.TRANSCRIPTION))); //TODO: is transcription a transcriptionalRepression?

		Participation participation=interaction.createParticipation(
				promoter.getDisplayId(),
				laciInverterModuleDef_promoter.getIdentity());
		participation.addRole(SystemsBiologyOntology.PROMOTER);

		Participation participation2=interaction.createParticipation(
				TF.getDisplayId(),
				laciInverterModuleDef_TF.getIdentity());
		participation2.addRole(SystemsBiologyOntology.INHIBITOR);
	}

	public static ComponentDefinition createComponenDefinition(SBOLDocument document,QName identifier,String name, URI type, URI role,String description)
	{
		ComponentDefinition componentDef = document.createComponentDefinition(
				identifier.getLocalPart(),
				new HashSet<URI>(Arrays.asList(type)));
		componentDef.addRole(role);
		componentDef.setName(name);
		componentDef.setDescription(description);
		return componentDef;
	}

	public static Sequence addPRSequence(SBOLDocument document, ComponentDefinition componentDef, String elements)
	{
		return addSequence(document, componentDef, componentDef.getDisplayId(), Terms.sequenceTypes.nucleotides, elements);
	}

	public static void addSubComponents(SBOLDocument document, ComponentDefinition componentDef, ComponentDefinition ... subComponents)	throws Exception
	{
		addSubComponents(document, componentDef, Arrays.asList(subComponents));
	}

	public static void addSubComponents(SBOLDocument document, ComponentDefinition componentDef, List<ComponentDefinition> subComponents)	throws Exception
	{
		int i=1;
		int start=0;
		int end=0;
		for (ComponentDefinition subComponent:subComponents)
		{
			Component component=componentDef.createComponent(
					subComponent.getDisplayId(),
					AccessType.PUBLIC,
					subComponent.getIdentity());

			start=end+1;
			end=start + getSequenceLength(document, subComponent);
			SequenceAnnotation annotation=componentDef.createSequenceAnnotation("anno" + i,"location" + i,  start, end, OrientationType.INLINE);
			annotation.setComponent(component.getIdentity());
			i++;
		}
	}

	public static int getSequenceLength (SBOLDocument document, ComponentDefinition componentDef) throws Exception
	{		if (componentDef.getSequences()!=null && componentDef.getSequences().size()>0)
	{
		Sequence sequence=componentDef.getSequences().iterator().next();
		return sequence.getElements().length();
	}
	else
	{
		int total=0;
		for (SequenceAnnotation annotation:componentDef.getSequenceAnnotations())
		{
			if (annotation.getComponent()!=null)
			{
				//Component component=getSubComponent(componentDef, annotation.getComponent());
				Component component=annotation.getComponent();

				ComponentDefinition subComponentDef=component.getDefinition();
				total= total + getSequenceLength(document, subComponentDef);
			}
			else
			{
				throw new Exception ("Can't get sequence length for an incomplete design");
			}
		}
		return total;
	}
	}

	public static Sequence addSequence(SBOLDocument document, ComponentDefinition componentDef, String displayId, URI sequenceType, String elements)
	{
		Sequence sequence=document.createSequence(displayId,elements,sequenceType);
		componentDef.addSequence(sequence.getIdentity());
		return sequence;
	}

	public static URI toURI(QName name)
	{
		return URI.create(name.getNamespaceURI() + name.getLocalPart());
	}

	/*
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
	 */

	public static SBOLDocument writeAndRead(SBOLDocument doc)
			throws SBOLValidationException, IOException, XMLStreamException, FactoryConfigurationError, CoreIoException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SBOLWriter.write(doc, out);

		return SBOLReader.read(new ByteArrayInputStream(out.toByteArray()));
	}


	/*
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
	 */

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
