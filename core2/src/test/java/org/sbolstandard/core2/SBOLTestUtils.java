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
import java.util.Random;
import java.util.Set;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * Construction of TopLevel objects along with any of its' sub-parts.
 * @author Tramy Nguyen
 *
 */
class SBOLTestUtils {

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

	public static SBOLDocument convertSBOL1(String fileName, String URIprefix, String fileType, boolean dropDuplicates)
	{
		InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
		if (resourceAsStream == null)
			resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

		assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";
		SBOLDocument actual = null;
		SBOLReader.setURIPrefix(URIprefix);
		SBOLReader.setDropObjectsWithDuplicateURIs(dropDuplicates);
		if (URIprefix==null) {
			SBOLReader.setCompliant(false);
		} else {
			SBOLReader.setCompliant(true);
		}

		try {
			if(fileType.equals("rdf"))
				actual = SBOLReader.read(resourceAsStream);
			else if(fileType.equals("json"))
				actual = SBOLReader.read(resourceAsStream,SBOLDocument.JSON);
			else if(fileType.equals("turtle"))
				actual = SBOLReader.read(resourceAsStream,SBOLDocument.TURTLE);
			else
				actual = SBOLReader.read(resourceAsStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actual;

	}

	public static SBOLDocument convertRDFTripleStore(String fileName, String fileType, boolean compliant)
	{
		InputStream resourceAsStream = SBOLReaderTest.class.getResourceAsStream(fileName);
		if (resourceAsStream == null)
			resourceAsStream = SBOLReaderTest.class.getResourceAsStream("/" + fileName);

		assert resourceAsStream != null : "Failed to find test resource '" + fileName + "'";
		SBOLReader.setCompliant(false);
		SBOLDocument actual = null;
		try {
			if(fileType.equals("rdf"))
				actual = SBOLReader.read(resourceAsStream);
			else if(fileType.equals("json"))
				actual = SBOLReader.read(resourceAsStream,SBOLDocument.JSON);
			else if(fileType.equals("turtle"))
				actual = SBOLReader.read(resourceAsStream,SBOLDocument.TURTLE);
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

		interaction.createParticipation(
				promoter.getDisplayId(),
				laciInverterModuleDef_promoter.getIdentity(),
				SystemsBiologyOntology.PROMOTER);

		interaction.createParticipation(
				TF.getDisplayId(),
				laciInverterModuleDef_TF.getIdentity(),
				SystemsBiologyOntology.INHIBITOR);
	}

	public static ComponentDefinition createComponenDefinition(SBOLDocument document,QName identifier,String name, URI type, URI role,String description) throws SBOLValidationException
	{
		ComponentDefinition componentDef = document.createComponentDefinition(
				identifier.getLocalPart(),
				new HashSet<URI>(Arrays.asList(type)));
		componentDef.addRole(role);
		componentDef.setName(name);
		componentDef.setDescription(description);
		return componentDef;
	}

	public static Sequence addPRSequence(SBOLDocument document, ComponentDefinition componentDef, String elements) throws SBOLValidationException
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

	public static Sequence addSequence(SBOLDocument document, ComponentDefinition componentDef, String displayId, URI sequenceType, String elements) throws SBOLValidationException
	{
		Sequence sequence=document.createSequence(displayId,elements,sequenceType);
		componentDef.addSequence(sequence.getIdentity());
		return sequence;
	}

	public static URI toURI(QName name)
	{
		return URI.create(name.getNamespaceURI() + name.getLocalPart());
	}


	public static SBOLDocument writeAndRead(SBOLDocument doc, boolean compliant)
			throws SBOLValidationException, SBOLConversionException, IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SBOLWriter.write(doc, out);
		SBOLReader.setCompliant(compliant);
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

	/*------------------------
	 * UTILS FOR SEQUENCES
	 *------------------------*/
	public static final String NEWLINE = System.lineSeparator();
	public static final String[] sigmaDNA = new String[]{
			"A","C","G","T"};
	public static final String[] sigmaRNA = new String[]{
			"A","C","G","U"};
	public static final String[] sigmaAminoAcids = new String[]{
			"F","L","I","M","V","S","P","T","A","Y","H","N","K","D","E","C","R","W","G","Q","*"};
	private static Random rand;

	/**
	 * The generateRandomSequence generates a random sequence of a 
	 * give length N from a given alphabet sigma
	 * @param N  ... the length of the desired random sequence
	 * @param sigma ... the alphabet from that the sequence letters should be chosen
	 * 
	 * @return a random sequence as String
	 */
	public String generateRandomSequence(int N, String[] sigma) {
		StringBuilder seq = new StringBuilder();
		for(int i=0; i<N; i++) {
			seq.append(
				getRandomLetter(sigma));
		}
		return seq.toString();
	}

	/**
	 * The getRandomLetter returns a randomly chosen letter 
	 * from a given alphabet sigma
	 * 
	 * @param sigma ... the alphabet
	 * 
	 * @return a randomly chosen letter from the alphabet
	 */
	public String getRandomLetter(String[] sigma) {
		if(null == rand) {
			rand = new Random();
		}
		int randomIdx = this.getRandomNumber(0, sigma.length - 1);
		return sigma[randomIdx];
	}

	/**
	 * The getRandomNumber returns a random number in the range [min..max] 
	 * (both inclusive). If the min greater than max, then the method returns -1.
	 * 
	 * @param min ... the minimum number
	 * @param max ... the maximum number
	 * 
	 * @return a random number in the range [min..max]
	 */
	public int getRandomNumber(int min, int max) {
		if(min <= max) {
			// pick a random number from min to max
			return new Random().nextInt((max - min) + 1) + min;
		}
		return -1;
	}
}
