package org.sbolstandard.core2.examples;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.sbolstandard.core2.Collection;
import org.sbolstandard.core2.Component;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.RestrictionType;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SequenceOntology;
import org.sbolstandard.core2.TopLevel;
import org.synbiohub.frontend.IdentifiedMetadata;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

public class SBOLWorkshop2018 {
	
public static int len(SBOLDocument sbolDocument) {
	// TODO 
	int length = 0;
	length += sbolDocument.getCollections().size();
	length += sbolDocument.getComponentDefinitions().size();
	length += sbolDocument.getSequences().size();
	
	return length;
}

public static void printCounts(SBOLDocument sbolDocument) {
	// TODO
	System.out.println("Collection...................." + sbolDocument.getCollections().size());
	System.out.println("ComponentDefinition..........." + sbolDocument.getComponentDefinitions().size());
	System.out.println("Sequence......................" + sbolDocument.getSequences().size());
	System.out.println("---");
	System.out.println("Total........................." + len(sbolDocument));
}
	
public static void main(String[] args) throws SBOLValidationException, IOException, SBOLConversionException, SynBioHubException {	
	
	/* Getting a Device from an SBOL Compliant XML */
	
	// Start a new SBOL Document to hold the device
	SBOLDocument doc = new SBOLDocument();
	doc.setCreateDefaults(true);
	
	// Set your Homespace. All new SBOL objects will be created in this namespace
	String my_namespace = "http://my_namespace.org/";
	String version = "1";
	doc.setDefaultURIprefix(my_namespace);

	// Create a new device
	ComponentDefinition my_device = doc.createComponentDefinition("my_device", version, ComponentDefinition.DNA);
	System.out.println(my_device.getIdentity());
	System.out.println("");
	
	// Load some genetic parts taken from the Cello paper
	SBOLDocument cello_parts = SBOLReader.read("/Users/myers/Downloads/parts.xml");
	System.out.println(len(cello_parts));
	printCounts(cello_parts);
	System.out.println("");
	
	// Explore document contents. Notice it is composed of
	// componentDefinitions and sequences
	for (TopLevel topLevel : cello_parts.getTopLevels()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	// Import these objects into your Document
	doc.createCopy(cello_parts);
	for (TopLevel topLevel : doc.getTopLevels()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	// Retrieve an object from the Document using its uniform resource identifier (URI)
	Collection promoter_collection = doc.getCollection(URI.create("http://examples.org/Collection/promoters/1"));

	// A Collection contains a list of URI references to objects, not the object themselves
	for (TopLevel topLevel : promoter_collection.getMembers()) {
		System.out.println(topLevel.getIdentity());
	}
	System.out.println("");
	
	// Retrieve a component, using its full URI
	ComponentDefinition promoter = doc.getComponentDefinition(URI.create("http://examples.org/ComponentDefinition/pPhlF/1"));

	// Review the BioPAX and Sequence Ontology terms that describe this component
	System.out.println(promoter.getTypes());
	System.out.println(promoter.getRoles());
	System.out.println("");
	
	/* Getting a Device from Synbiohub */
	
	// Start an interface to the part shop
	SynBioHubFrontend part_shop = new SynBioHubFrontend("http://localhost:7777","https://synbiohub.org");
	
	// Search for records from the interlab study
	ArrayList<IdentifiedMetadata> records = part_shop.getMatchingComponentDefinitionMetadata("interlab", null, null, null, 0, 50);
	for (IdentifiedMetadata record : records) {
		System.out.println(record.getDisplayId()+": "+record.getUri());
	}
	System.out.println("");
	
	// Import the medium device into the user's Document
	doc.createCopy(part_shop.getSBOL(URI.create("https://synbiohub.org/public/iGEM_2016_interlab/Medium_2016Interlab/1")));

	// Explore the new parts
	for (TopLevel topLevel : doc.getTopLevels()) {
		System.out.println(topLevel.getClass().getSimpleName()+": "+topLevel.getIdentity());
	}
	System.out.println("");
	
	/* Extracting a ComponentDefinition from a Pre-existing Device */
	
	// Extract the medium strength promoter
	ComponentDefinition medium_strength_promoter = doc.getComponentDefinition(URI.create("https://synbiohub.org/public/igem/BBa_J23106/1"));
	
	// Get parts for a new circuit
	ComponentDefinition rbs = doc.getComponentDefinition(URI.create("http://examples.org/ComponentDefinition/Q2/1"));
	ComponentDefinition cds = doc.getComponentDefinition(URI.create("http://examples.org/ComponentDefinition/LuxR/1"));
	ComponentDefinition terminator = doc.getComponentDefinition(URI.create("http://examples.org/ComponentDefinition/ECK120010818/1"));
	
	// Assemble a new gene
	my_device.createSequenceConstraint("constraint1", RestrictionType.PRECEDES, medium_strength_promoter.getIdentity(), rbs.getIdentity());
	my_device.createSequenceConstraint("constraint2", RestrictionType.PRECEDES, rbs.getIdentity(), cds.getIdentity());
	my_device.createSequenceConstraint("constraint3", RestrictionType.PRECEDES, cds.getIdentity(), terminator.getIdentity());

	// Annotate the target construct with a Sequence Ontology term
	my_device.addRole(SequenceOntology.ENGINEERED_REGION);
	
	// Explore the newly assembled gene
	for (Component component : my_device.getComponents()) {
		System.out.println(component.getDisplayId());
	}
	System.out.println("");

	// TODO: Compile sequence
	
	/* Managing a Design-Build-Test-Learn workflow */
	
	// TODO
	
//	design = doc.designs.create('my_device')
//	design.structure = my_device
//	design.function = None  # This tutorial does not cover ModuleDefinitions and interactions
//
//	workflow_step_1 = Activity('build_1')
//	workflow_step_2 = Activity('build_2')
//	workflow_step_3 = Activity('test_1')
//	workflow_step_4 = Activity('analysis_1')
//
//	workflow_step_1.plan = Plan('gibson_assembly')
//	workflow_step_2.plan = Plan('transformation')
//	workflow_step_3.plan = Plan('promoter_characterization')
//	workflow_step_4.plan = Plan('parameter_optimization')
//
//	setHomespace('')
//	Config.setOption('sbol_compliant_uris', False)  # Temporarily disable auto-construction of URIs
//
//	workflow_step_1.agent = Agent('mailto:jdoe@%s' %my_namespace)
//	workflow_step_2.agent = workflow_step_1.agent
//	workflow_step_3.agent = Agent('http://sys-bio.org/plate_reader_1')
//	workflow_step_4.agent = Agent('http://tellurium.analogmachine.org')
//
//	setHomespace(my_namespace)
//	Config.setOption('sbol_compliant_uris', True)
//
//	doc.addActivity([workflow_step_1, workflow_step_2, workflow_step_3, workflow_step_4])
//
//	gibson_mix = workflow_step_1.generateBuild('gibson_mix', design)
//	clones = workflow_step_2.generateBuild(['clone1', 'clone2', 'clone3'], design, gibson_mix)
//	experiment1 = workflow_step_3.generateTest('experiment1', clones)
//	analysis1 = workflow_step_4.generateAnalysis('analysis1', experiment1)
//
//	# Validate the Document
//
//	print(doc.validate())
	
//	print(analysis1.identity)
	
	/* Uploading the Device back to SynBioHub */
	
	String email = "myers@ece.utah.edu";
	String user_name = "myers";
	String password = "test";
	part_shop.login(user_name, password);
	
	// Upon submission, the Document will be converted to a Collection with the following properties
	// The new Collection will have a URI that conforms to the following pattern:
	// https://synbiohub.org/user/<USERNAME>/<DOC.DISPLAYID>/<DOC.DISPLAYID>_collection
	String displayId = "my_device";
	String name = "my device";
	String description = "a description of the cassette";
	part_shop.createCollection(displayId, version, name, description, "", true, doc);
	
	// TODO:
	
	//attachment_path = '/Users/palchicz/projects/sbol/gene_cassette.xml'

	//# Attach raw experimental data to the Test object here. Note the pattern
	//test_uri = 'https://synbiohub.org/user/' + 'zeke7781' + '/' + doc.displayId + '/Test_experiment1/1'
	//part_shop.attachFile(test_uri, attachment_path)
	
	// # Attach processed experimental data here
	// other_attachement_path = '/Users/palchicz/projects/sbol/attachement.txt'
	// part_shop.attachFile('https://synbiohub.org/user/' + 'zeke7781' + '/' + doc.displayId + '/Analysis/analysis1/1', other_attachement_path)

}
}
