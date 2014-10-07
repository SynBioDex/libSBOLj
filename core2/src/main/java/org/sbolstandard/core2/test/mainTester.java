package org.sbolstandard.core2.test;

//import java.awt.List;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core2.*; 

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.examples.DataTreeCreator;
//
import javax.xml.namespace.QName;

public class mainTester {
	/*
	 * TODO: generate data for:
	 *  formatStructures(), 
	 *  formatComponents(), 
	 *  formatCollections()
	 */
	public static void main( String[] args ) throws Exception
	{
		SBOLWriter.write(createModuleData(),(System.out));
		
//		DocumentRoot<QName> originalDocument = DataTreeCreator.makeSBOL2Document();
//		writer.write(new OutputStreamWriter(System.out), originalDocument);
	}

	public static SBOLDocument createModuleData()
	{
		SBOLDocument moduleSBOL2Doc_test = new SBOLDocument(); 
		
		URI moduleUri 				   = getURI("http://www.async.ece.utah.edu/module_identity");
		
		List<URI> roles = new ArrayList<URI>();
		roles.add(getURI("http://www.async.ece.utah.edu/repressor"));
		
		Module m = moduleSBOL2Doc_test.createModule(moduleUri, roles); 
		m.setDescription("LacI_Inverter_Description");
		m.setDisplayId("LacI_Inverter");
		m.setIdentity(moduleUri);
		
		m.setFunctionalInstantiations(generateFunctionalInstantiation(roles));
		m.setModels(generateModel(roles, moduleSBOL2Doc_test));
		m.setInteractions(generateInteractions());
		m.setModuleInstantiations(generateModuleInstantiation(roles));
		
		
		return moduleSBOL2Doc_test;
	}
	
	public static List<Interaction> generateInteractions()
	{
		URI persistentIdentity = getURI("http://www.async.ece.utah.edu/persistentIdentity_Interaction");
		URI identity 		   = getURI("http://www.async.ece.utah.edu/LacI_Repression_interactionIdentity");
		URI list 			   = getURI("http://www.async.ece.utah.edu/Interaction");
		List<URI> type 		   = new ArrayList<URI>(); 
		type.add(list);
		
		Interaction interactions = new Interaction(identity, type);
		interactions.setDescription("Interaction_description");
		interactions.setDisplayId("LacI_Repression");
		interactions.setName("LacI_Repression_Interaction_Name");
		interactions.setIdentity(identity);
		interactions.setPersistentIdentity(persistentIdentity);
		interactions.setVersion("0.1");
		
		List<Interaction> i = new ArrayList<Interaction>();
		i.add(interactions);
		return i; 
	}
	
	public static List<Model> generateModel(List<URI> roles, SBOLDocument doc)
	{
		URI identity 		   = getURI("http://www.async.ece.utah.edu/LacI_identity");
		URI source 		   	   = getURI("http://www.async.ece.utah.edu/LacI_Inverter.xml");
		URI language 		   = getURI("http://www.async.ece.utah.edu/SBML");
		URI framework 		   = getURI("http://www.async.ece.utah.edu/ODE");
		URI persistentIdentity = getURI("http://www.async.ece.utah.edu/persistentIdentity_Model");
		
		Model model = doc.createModel(persistentIdentity, source, language, framework, roles);
		
		model.setDescription("Model_description");
		model.setDisplayId("LacI_Inverter");
		model.setName("LacI_Inverter_Model_Name");
		model.setIdentity(identity);
		model.setPersistentIdentity(persistentIdentity);
		model.setVersion("0.1");
		
		List<Model> m = new ArrayList<Model>();
		m.add(model);
		return m;
	}
	
	public static List<ModuleInstantiation> generateModuleInstantiation(List<URI> roles)
	{
		URI identity 		   		   = getURI("http://www.async.ece.utah.edu/ModuleInstantiation_identity");
		URI moduleInstantiationURI = getURI("http://www.async.ece.utah.edu/moduleInstantiation_identity");
		URI persistentIdentity		   = getURI("http://www.async.ece.utah.edu/persistentIdentity_moduleInstantiation");
		
		Module instantiatedModule = new Module(persistentIdentity, roles); 
		
		ModuleInstantiation modInstantiation = new ModuleInstantiation(identity, instantiatedModule);
		modInstantiation.setDescription("functionalInstantiation_description");
		modInstantiation.setDisplayId("LacI");
		modInstantiation.setName("LacI_functionInstantiation_Name");
		modInstantiation.setIdentity(moduleInstantiationURI);
		modInstantiation.setPersistentIdentity(persistentIdentity);
		modInstantiation.setVersion("0.1");
		
		List<ModuleInstantiation> m = new ArrayList<ModuleInstantiation>();
		m.add(modInstantiation);
		return m;
	}

	public static List<FunctionalInstantiation> generateFunctionalInstantiation(List<URI> roles)
	{
		URI identity 		   		   = getURI("http://www.async.ece.utah.edu/LacI_identity");
		URI componentIdentity 		   = getURI("http://www.async.ece.utah.edu/component_Identity");
		URI functionalInstantiationURI = getURI("http://www.async.ece.utah.edu/functionalInstantiation_identity");
		URI persistentIdentity		   = getURI("http://www.async.ece.utah.edu/persistentIdentity_functionalInstantiation");
		AccessType access = AccessType.PUBLIC;
		DirectionType direction = DirectionType.input;
		
		List<URI> type = new ArrayList<URI>();
		type.add(getURI("http://www.async.ece.utah.edu/type1"));
		
		FunctionalInstantiation f = new FunctionalInstantiation(identity, componentIdentity, 
				access, type, roles, direction); 
		f.setDescription("functionalInstantiation_description");
		f.setDisplayId("LacI");
		f.setName("LacI_functionInstantiation_Name");
		f.setIdentity(functionalInstantiationURI);
		f.setPersistentIdentity(persistentIdentity);
		f.setVersion("0.1");
		
		List<FunctionalInstantiation> funct_instant = new ArrayList<FunctionalInstantiation>();
		funct_instant.add(f);
		return funct_instant;
	}
	
	private static URI getURI(String uri)
	{
		return URI.create(uri);
	}

}
