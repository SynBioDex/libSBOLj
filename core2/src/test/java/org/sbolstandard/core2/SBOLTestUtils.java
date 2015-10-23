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

	public static final NamespaceBinding example = NamespaceBinding ("http://sbolstandard.org/example/", "example");
	public static final NamespaceBinding biopax  = NamespaceBinding ("http://www.biopax.org/release/biopax-level3.owl#", "biopax");
	public static final NamespaceBinding so 	 = NamespaceBinding ("http://identifiers.org/so/", "so");
	public static final NamespaceBinding sbo	 = NamespaceBinding ("http://identifiers.org/biomodels.sbo/", "sbo");
	public static final NamespaceBinding pr 	 = NamespaceBinding ("http://www.partsregistry.org/", "pr");
	public static final NamespaceBinding vpr 	 = NamespaceBinding ("http://www.virtualparts.org/part/", "vpr");
	public static final NamespaceBinding uniprot = NamespaceBinding ("http://identifiers.org/uniprot/", "uniprot");


	public static class Terms
	{
		public static class sequenceTypes
		{
			public static URI nucleotides = URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html");
			public static URI aminoacids  = URI.create("http://www.chem.qmul.ac.uk/iupac/AminoAcid/");
			public static URI atoms  	  = URI.create("http://www.opensmiles.org/opensmiles.html");
		}

		public static class moduleRoles
		{
			public static URI inverter = URI.create("http://parts.igem.org/cgi/partsdb/pgroup.cgi?pgroup=inverter");

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
