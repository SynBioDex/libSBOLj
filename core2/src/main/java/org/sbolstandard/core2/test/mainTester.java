package org.sbolstandard.core2.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.*; 
import org.sbolstandard.core2.Sbol2Terms.Identified;
import org.sbolstandard.core2.abstract_classes.Location;

public class mainTester {
	

	public static void main( String[] args ) throws Exception
	{
		SBOLDocument SBOL2Doc_test = new SBOLDocument(); 
		
		AccessType access = AccessType.PUBLIC;
		DirectionType direction = DirectionType.input;
		
		createModuleData(SBOL2Doc_test,
				getData("module_identity","module_persistentIdentity","module_displayId", "module_name", "module_description", "0.1"), 
				getListOfURI("module_roles", "module_roles2"),
				getData("ModuleInstantiation_identity", "persistentIdentity_moduleInstantiation", "LacI", "LacI_moduleInstantiation_Name", "moduleInstantiation_description", "0.1"),
				getData("LacI_identity", "persistentIdentity_functionalInstantiation","LacI","LacI_functionInstantiation_Name", "functionalInstantiation_description", "0.1", "component_Identity"),
				getData("LacI_Repression_interactionIdentity", "persistentIdentity_Interaction","LacI_Repression","LacI_Repression_Interaction_Name","Interaction_description", "0.1"),
				getData("LacI_identity","persistentIdentity_Model", "LacI_Inverter", "LacI_Inverter_Model_Name","Model_description", "0.1", "LacI_Inverter.xml", "SBML", "ODE"),
				getListOfURI("functionalInstantiation_type1"),
				getListOfURI("interaction_type1"),
				access, direction);
		
		
		AccessType structuralInstant_access = AccessType.PUBLIC;
		AccessType structuralConst_access = AccessType.PUBLIC;
		DirectionType structuralInstant_direction = DirectionType.input;
		DirectionType structuralConst_direction = DirectionType.input;
		
		createComponentData(SBOL2Doc_test, 
				getData("component_identity","component_persistentIdentity","component_displayId","component_name", "component_description", "0.1"),
				getData("structuralInstantiation_displayId", "structuralInstantiation_name", "structuralInstantiation_description","structuralInstantiation_identity", "structuralInstantiation_persistentIdentity", "0.1", "structuralInstantiation_componentIdentity"),
				getData("structuralAnnotation_displayId", "structuralAnnotation_name", "structuralAnnotation_description", "structuralAnnotation_identity", "structuralAnnotation_persistentIdentity", "0.1"), 
				getData("structuralConstraint_identity", "structuralConstraint_persistentIdentity", "0.1", "structuralConstraint_restriction", "structuralConstraint_componentIdentity"), 
				getData("structure_identity", "structure_elements", "structure_encoding"), 
				structuralInstant_access, structuralInstant_direction,
				structuralConst_access, structuralConst_direction,
				getListOfURI("structuralInstantiation_type"), getListOfURI("structuralInstantiation_roles"),
				getListOfURI("structuralConstraint_type"), getListOfURI("structuralConstraint_roles"),
				getListOfURI("component_type"), getListOfURI("component_roles"));
		
		
		createStructure(SBOL2Doc_test, 
				getURI("structure_identity"), 
				getURI("structure_encoding"), 
				getURI("structure_persistentIdentity"),
				"structure_element", "structure_displayId", "structure_name", "structure_description", "0.1");
		
		
		createCollection(SBOL2Doc_test,
				getURI("collection_identity"), 
				getURI("collection_persistentIdentity"),
				"collection_displayId","collection_name", "collection_description", "0.1");
		
		SBOLWriter.write(SBOL2Doc_test,(System.out));
	}
	
	private static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}
	
	private static List<URI> getListOfURI(String ... appends)
	{
		List<URI> list = new ArrayList<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list; 
	}
	
	private static List<String> getData(String ... data)
	{
		List<String> list = new ArrayList<String>();
		for(String d : data)
		{
			list.add(d);
		}
		return list;
		
	}
	
	private static void createComponentData(SBOLDocument SBOL2Doc_test, 
			List<String> componentData,
			List<String> structuralInstantiations_data,
			List<String> structuralAnnotations_data,
			List<String> structuralConstraints_data,
			List<String> structure_data,
			AccessType structuralInstant_access, DirectionType structuralInstant_direction,
			AccessType structuralConstraint_access, DirectionType structuralConstraint_direction,
			List<URI> structuralInstantiation_type, List<URI> structuralInstantiation_roles,
			List<URI> structuralConstraint_type, List<URI> structuralConstraint_roles,
			List<URI> type, List<URI> roles)
	{
		URI identity 		   = getURI(componentData.get(0));
		URI persistentIdentity = getURI(componentData.get(1));
		String displayId 	   = componentData.get(2);
		String name 		   = componentData.get(3);
		String description 	   = componentData.get(4);
		String version 		   = componentData.get(5);
		
		Component c = SBOL2Doc_test.createComponent(identity, type, roles);
		c.setDescription(description);
		c.setDisplayId(displayId);
		c.setName(name);
		c.setPersistentIdentity(persistentIdentity);
		c.setVersion(version);
		
		//TODO: setStructuralInstantiations will turn to addStructuralInstantiations. call it 2x to pass into 
		//addStructuralAnnotations()
		c.setStructuralInstantiations(generateStructuralInstantiation(structuralInstantiations_data, 
				structuralInstantiation_roles, structuralInstantiation_type, structuralInstant_access, 
				structuralInstant_direction));
		
		c.setStructuralAnnotations(generateStructuralAnnotation(structuralAnnotations_data));
		c.setStructuralConstraints(generateStructuralConstraint(structuralConstraints_data, structuralConstraint_access, type, roles, structuralConstraint_direction));
		c.setStructure(generateStructure(structure_data));
	}

	private static void createModuleData(SBOLDocument SBOL2Doc_test, 
			List<String> module_data,
			List<URI> roles,
			List<String> moduleInstantiation_data,
			List<String> functionalInstantiation_data,
			List<String> interactionData,
			List<String> model_data,
			List<URI> fi_type,
			List<URI> interaction_type,
			AccessType access, DirectionType direction)
	{
		URI identity = getURI(module_data.get(0)); 
		URI persistentIdentity = getURI(module_data.get(1)); 
		String displayId = module_data.get(2); 
		String name = module_data.get(3); 
		String description = module_data.get(4);  
		String version = module_data.get(5); 
		
		Module m = SBOL2Doc_test.createModule(identity, roles); 
		m.setDescription(description);
		m.setDisplayId(displayId);
		m.setIdentity(identity);
		m.setName(name);
		m.setPersistentIdentity(persistentIdentity);
		m.setVersion(version);
		
		m.setFunctionalInstantiations(
				generateFunctionalInstantiation(roles, fi_type, functionalInstantiation_data, access, direction));
		m.setModels(
				generateModel(SBOL2Doc_test, roles, model_data));
		m.setInteractions(
				generateInteraction(interactionData, interaction_type));
		m.setModuleInstantiations(
				generateModuleInstantiation(roles, moduleInstantiation_data));
	}
	
	private static void createCollection(SBOLDocument SBOL2Doc_test, URI identity, URI persistentIdentity,
			String displayId, String name, String description, String version)
	{	
		Collection collection = SBOL2Doc_test.createCollection(identity);
		collection.setDescription(description);
		collection.setDisplayId(displayId);
		collection.setName(name);
		collection.setIdentity(identity);
		collection.setPersistentIdentity(persistentIdentity);
		collection.setVersion(version);
	}
	
	private static void createStructure(SBOLDocument SBOL2Doc_test, URI identity, URI encoding, URI persistentIdentity,
			String element, String displayId, String name, String description, String version)
	{
		Structure structure = SBOL2Doc_test.createStructure(persistentIdentity, element, encoding);
		structure.setDescription(description);
		structure.setDisplayId(displayId);
		structure.setName(name);
		structure.setIdentity(identity);
		structure.setPersistentIdentity(persistentIdentity);
		structure.setVersion(version);
	}
	
	private static List<Interaction> generateInteraction(List<String> data, List<URI> type)
	{	
		URI identity 		   = getURI(data.get(0)); 
		URI persistentIdentity = getURI(data.get(1));
		String displayId 	   = data.get(2); 
		String name 		   = data.get(3); 
		String description 	   = data.get(4); 
		String version 		   = data.get(5);
		
		Interaction interactions = new Interaction(identity, type);
		interactions.setDisplayId(displayId);
		interactions.setName(name);
		interactions.setDescription(description);
		interactions.setIdentity(identity);
		interactions.setPersistentIdentity(persistentIdentity);
		interactions.setVersion(version);
		
		List<Interaction> i = new ArrayList<Interaction>();
		i.add(interactions);
		return i; 
	}
	
	/**
	 * data[0] = Identity 
	 * data[1] = PersistentIdentity
	 * data[2] = DisplayId 
	 * data[3] = Name 
	 * data[4] = Description
	 * data[5] = Version
	 * data[6] = source
	 * data[7] = language 
	 * data[8] = framework 
	 * @param doc
	 * @param roles
	 * @param data
	 * @return
	 */
	private static List<Model> generateModel(SBOLDocument doc, List<URI> roles, List<String> data)
	{	
		URI identity 		   = getURI(data.get(0)); 
		URI persistentIdentity = getURI(data.get(1)); 
		String displayId 	   = data.get(2); 
		String name 		   = data.get(3);
		String description     = data.get(4); 
		String version 		   = data.get(5); 
		URI source 			   = getURI(data.get(6)); 
		URI language 		   = getURI(data.get(7));
		URI framework 		   = getURI(data.get(8)); 
		
		Model model = doc.createModel(persistentIdentity, source, language, framework, roles);
	
		model.setIdentity(identity);
		model.setPersistentIdentity(persistentIdentity);
		model.setDisplayId(displayId);
		model.setName(name);
		model.setDescription(description);
		model.setVersion(version);
		
		List<Model> m = new ArrayList<Model>();
		m.add(model);
		return m;
	}
	
	/**
	 * data[0] = Identity
	 * data[1] = PersistentIdentity
	 * data[2] = DisplayId
	 * data[3] = Name 
	 * data[4] = Description 
	 * data[5] = Version 
	 * @param roles
	 * @param data
	 * @return
	 */
	private static List<ModuleInstantiation> generateModuleInstantiation(List<URI> roles, List<String> data)
	{	
		URI identity 		   = getURI(data.get(0)); 
		URI persistentIdentity = getURI(data.get(1)); 
		String displayId 	   = data.get(2); 
		String name 		   = data.get(3); 
		String description 	   = data.get(4); 
		String version 		   = data.get(5); 
		
		//TODO: make sure ModuleInstantiation should keep track of identities from the Module that it is referencing.
		Module instantiatedModule = new Module(identity, roles); 
		
		ModuleInstantiation modInstantiation = new ModuleInstantiation(identity, instantiatedModule); //TODO: instantiatedModule should be passing in a URI
		modInstantiation.setDisplayId(displayId);
		modInstantiation.setName(name);
		modInstantiation.setDescription(description);
		modInstantiation.setIdentity(identity);
		modInstantiation.setPersistentIdentity(persistentIdentity);
		modInstantiation.setVersion(version);
		
		List<ModuleInstantiation> m = new ArrayList<ModuleInstantiation>();
		m.add(modInstantiation);
		return m;
	}

	/**
	 * data[0] = Identity 
	 * data[1] = PersistentIdentity 
	 * data[2] = DisplayId 
	 * data[3] = Name 
	 * data[4] = Description 
	 * data[5] = Version 
	 * data[6] = Component Identity 
	 * @param roles
	 * @param type
	 * @param data
	 * @param access
	 * @param direction
	 * @return
	 */
	private static List<FunctionalInstantiation> generateFunctionalInstantiation(List<URI> roles, List<URI> type,
			List<String> data, AccessType access, DirectionType direction)
	{	
		URI identity 		   = getURI(data.get(0)); 
		URI persistentIdentity = getURI(data.get(1)); 
		String displayId 	   = data.get(2);
		String name 		   = data.get(3); 
		String description 	   = data.get(4); 
		String version 		   = data.get(5); 
		URI componentIdentity  = getURI(data.get(6)); 
		
		//TODO: Why does creating an object of FunctionalInstantiation requires a component identity? 
		//getInstantiatedComponent
		FunctionalInstantiation f = new FunctionalInstantiation(identity, componentIdentity, access, type, roles, direction); 
		f.setDisplayId(displayId);
		f.setName(name);
		f.setDescription(description);
		f.setIdentity(identity);
		f.setPersistentIdentity(persistentIdentity);
		f.setVersion(version);
		
		List<FunctionalInstantiation> funct_instant = new ArrayList<FunctionalInstantiation>();
		funct_instant.add(f);
		return funct_instant;
	}
	
	private static List<StructuralInstantiation> generateStructuralInstantiation(List<String> structuralInstantiations_data,
			List<URI> roles, List<URI> type, AccessType access, DirectionType direction)
	{
		String displayId 	   = structuralInstantiations_data.get(0);
		String name 		   = structuralInstantiations_data.get(1);
		String description 	   = structuralInstantiations_data.get(2);
		URI identity 		   = getURI(structuralInstantiations_data.get(3));
		URI persistentIdentity = getURI(structuralInstantiations_data.get(4));
		String version 		   = structuralInstantiations_data.get(5);
		URI componentIdentity  = getURI(structuralInstantiations_data.get(6));
		
		//TODO: Why does creating an object of StructuralInstantiation requires a component identity? 
		//getInstantiatedComponent
		StructuralInstantiation s = 
				new StructuralInstantiation(identity, componentIdentity, access, type, roles, direction);
		s.setDisplayId(displayId);
		s.setName(name);
		s.setDescription(description);
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		
		List<StructuralInstantiation> structuralInstantiation = new ArrayList<StructuralInstantiation>(); 
		structuralInstantiation.add(s);
		return structuralInstantiation; 
		
	}
	
	private static List<StructuralAnnotation> generateStructuralAnnotation(List<String> structuralAnnotations_data)
	{
		String displayId 	   = structuralAnnotations_data.get(0);
		String name 		   = structuralAnnotations_data.get(1);
		String description 	   = structuralAnnotations_data.get(2);
		URI identity 		   = getURI(structuralAnnotations_data.get(3));
		URI persistentIdentity = getURI(structuralAnnotations_data.get(4));
		String version 		   = structuralAnnotations_data.get(5);
		Location location 	   = new Cut(persistentIdentity, 0); 
		//TODO What type of Location is this referring to? Unable to create an object of Location
		
		StructuralAnnotation s = new StructuralAnnotation(identity, description, location);
		s.setDisplayId(displayId);
		s.setName(name);
		s.setDescription(description);
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		
		List<StructuralAnnotation> structuralInstantiation = new ArrayList<StructuralAnnotation>(); 
		structuralInstantiation.add(s);
		return structuralInstantiation; 
		
	}
	private static List<StructuralConstraint> generateStructuralConstraint(List<String> structuralConstraints_data, 
			AccessType access, List<URI> type, List<URI> roles, DirectionType direction)
	{
		URI identity 		   = getURI(structuralConstraints_data.get(0));
		URI persistentIdentity = getURI(structuralConstraints_data.get(1));
		String version 		   = structuralConstraints_data.get(2);
		URI restriction 	   = getURI(structuralConstraints_data.get(3));
		URI componentIdentity  = getURI(structuralConstraints_data.get(4)); 
		
		//TODO: What is the point of creating 2 objects of StructuralInstantiation?
		StructuralInstantiation subject = new StructuralInstantiation(identity, componentIdentity, access, type, roles, direction);
		StructuralInstantiation object = new StructuralInstantiation(identity, componentIdentity, access, type, roles, direction);
		
		StructuralConstraint s = new StructuralConstraint(identity, restriction, subject, object);
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		
		List<StructuralConstraint> structuralConstraint = new ArrayList<StructuralConstraint>();
		structuralConstraint.add(s);
		return structuralConstraint; 
	}
	
	private static Structure generateStructure(List<String> structure_data)
	{
		URI identity 	= getURI(structure_data.get(0));
		String elements = structure_data.get(1);
		URI encoding 	= getURI(structure_data.get(2));
		
		Structure s = new Structure(identity, elements, encoding);
		return s; 
	}

}
