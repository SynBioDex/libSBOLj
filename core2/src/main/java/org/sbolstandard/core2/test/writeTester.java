package org.sbolstandard.core2.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.*; 
import org.sbolstandard.core2.abstract_classes.Location;

public class writeTester {
	
	//TODO: create data for each toplevel entity individually in own method.
	//TODO: rename getData() to getIdentificationData()
	public static void main( String[] args ) throws Exception
	{
		SBOLDocument SBOL2Doc_test = new SBOLDocument(); 
		//TODO: call createCollection(); 
		
		createLacI_Inverter(SBOL2Doc_test);
		createTetR_Inverter(SBOL2Doc_test);
		
	
		SBOLWriter.write(SBOL2Doc_test,(System.out));
	}
	
	private static void createLacI_Inverter(SBOLDocument SBOL2Doc_test)
	{
		Structure pLacSeq = createStructureData(SBOL2Doc_test, 
				getData("pLacSeq_identity","pLacSeq_persistentIdentity","pLacSeq_v0.1","pLacSeq_displayID","pLacSeq_Name", "pLacSeq_Description", "pLacSeq_element", "pLacSeq_encoding")); 
		Structure tetRSeq = createStructureData(SBOL2Doc_test, 
				getData("tetRSeq_identity","tetRSeq_persistentIdentity","tetRSeq_v0.1","tetRSeq_displayID","tetRSeq_Name", "tetRSeq_Description", "tetRSeq_element", "tetRSeq_encoding")); 
		Structure pLactetRSeq = createStructureData(SBOL2Doc_test, 
				getData("pLactetRSeq_identity","pLactetRSeq_persistentIdentity","pLactetRSeq_v0.1","pLactetRSeq_displayID","pLactetRSeq_Name", "pLactetRSeq_Description", "pLactetRSeq_element", "pLactetRSeq_encoding")); 
	
		Component pLac = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("Promoter_role"),
				getData("pLac_identity","pLac_persistentIdentity","pLac_v0.1","pLac_displayID","pLac_Name", "pLac_Description"),
				pLacSeq, 
				null, null, null); 
		
		Component tetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("CDS_role"),
				getData("tetR_identity","tetR_persistentIdentity","tetR_v0.1","tetR_displayID","tetR_Name", "tetR_Description"),
				tetRSeq, 
				null, null, null);
		
		
		StructuralInstantiation P = createStructuralInstantiationData(
				getData("P_identity", "P_persistentIdentity", "P_v1.0", "P_displayId", "P_name", "P_description", "public"), 
				pLac);
		
		StructuralInstantiation C = createStructuralInstantiationData(
				getData("C_identity", "C_persistentIdentity", "C_v1.0", "C_displayId", "C_name", "C_description", "public"), 
				tetR);
		
		//NOTE: either use StructuralAnnotation OR StructuralConstraint. Never both 
		StructuralAnnotation p_structAnnotate  = createStructuralAnnotationData(
				getData("p_structAnnotate_identity", "p_structAnnotate_persistentIdentity", "p_structAnnotate_v1.0", "p_structAnnotate_displayId", "p_structAnnotate_name", "p_structAnnotate_description"), 
				P, 
				0, 10);
		
		StructuralAnnotation c_structAnnotate  = createStructuralAnnotationData(
				getData("c_structAnnotate_identity", "c_structAnnotate_persistentIdentity", "c_structAnnotate_v1.0", "c_structAnnotate_displayId", "c_structAnnotate_name", "c_structAnnotate_description"), 
				C, 
				11, 20); 
		
		StructuralConstraint struct_constraint = createStructuralConstraintData(
				getData("struct_constraint_identity", "struct_constraint_persistentIdentity", "struct_constraint_v1.0", "precedes"), 
				P, 
				C); 

		Component pLactetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("Gene_role"),
				getData("pLactetR_identity","pLactetR_persistentIdentity","pLactetR_v0.1","pLactetR_displayID","pLactetR_Name", "pLactetR_Description"),
				pLactetRSeq, 
				getStructuralInstantiation_List(P, C), 
				null, //getStructuralAnnotation_List(p_structAnnotate, c_structAnnotate), 
				getStructuralConstraint_List(struct_constraint)); 
		
		Component LacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein_type"),
				getSetOfURI("Transcriptionfactor_role"),
				getData("LacI_identity","LacI_persistentIdentity","LacI_v0.1","LacI_displayID", "LacI_Name", "LacI_Description"),
				null, 
				null, null, null); 
		
		Component TetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein_type"),
				getSetOfURI("T.F._role"),
				getData("TetR_identity","TetR_persistentIdentity","TetR_v0.1","TetR_displayID", "TetR_Name", "TetR_Description"),
				null, 
				null, null, null); 
		
		FunctionalInstantiation LacIIn = createFunctionalInstantiationData(
				getData("LacIIn_identity","LacIIn_persistentIdentity","LacIIn_v0.1","LacIIn_displayID","LacIIn_Name", "LacIIn_Description","public", "input"),
				LacI);
		
		FunctionalInstantiation TetROut = createFunctionalInstantiationData(
				getData("TetROut_identity","TetROut_persistentIdentity","TetROut_v0.1","TetROut_displayID","TetROut_Name", "TetROut_Description","public", "output"),
				TetR);
		
		FunctionalInstantiation Inv = createFunctionalInstantiationData(
				getData("Inv_identity","Inv_persistentIdentity","Inv_v0.1","Inv_displayID","Inv_Name", "Inv_Description","private", "none"),
				pLactetR);
		
		Participation p1 = createParticipationData(
				getURI("participation1_identity"), 
				getSetOfURI("repressor"), 
				LacIIn);
		
		Participation p2 = createParticipationData(
				getURI("participation2_identity"), 
				getSetOfURI("repressed"), 
				Inv);
		
		Interaction interact1 =  createInteractionData(
				getData("interact1_identity","interact1_persistentIdentity","interact1_v0.1","interact1_displayID","interact1_Name", "interact1_Description"),
				getParticipation_List(p1, p2), 
				getSetOfURI("repression")); 
		
		//TODO: check if this is right for producer, produces, and identity?
		Participation p3 = createParticipationData(
				getURI("participation3_identity"), 
				getSetOfURI("producer"), 
				Inv);
		
		Participation p4 = createParticipationData(
				getURI("participation4_identity"), 
				getSetOfURI("produced"), 
				TetROut);
		
		Interaction interact2 =  createInteractionData(
				getData("interact2_identity","interact2_persistentIdentity","interact2_v0.1","interact2_displayID","interact2_Name", "interact2_Description"),
				getParticipation_List(p3, p4), 
				getSetOfURI("produces")); 
		
		Module LacI_Inv = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Inverter_type"),
				getSetOfURI("T.F._role"),
				getData("LacI_Inv_identity","LacI_Inv_persistentIdentity","LacI_Inv_v0.1","LacI_Inv_displayID","LacI_Inv_Name", "LacI_Inv_Description"),
				getFunctionalInstantiation_List(LacIIn, TetROut, Inv), 
				getInteraction_List(interact1, interact2), null, null
				);
		
		FunctionalInstantiation LacISp = createFunctionalInstantiationData(
				getData("LacISp_identity","LacISp_persistentIdentity","LacISp_v0.1","LacISp_displayID","LacISp_Name", "LacISp_Description","private", "none"),
				LacI);
		
		FunctionalInstantiation TetRSp = createFunctionalInstantiationData(
				getData("TetRSp_identity","TetRSp_persistentIdentity","TetRSp_v0.1","TetRSp_displayID","TetRSp_Name", "TetRSp_Description","private", "none"),
				TetR);
		
		MapsTo LacISp_LacIIn = createMapTo(
				getURI("LacISp_Mapsto_LacIIn_identity"), 
				getRefinement("merge"), 
				LacISp, LacIIn); 
		
		MapsTo TetRSp_TetROut = createMapTo(
				getURI("TetRSp_Mapsto_TetROut_identity"), 
				getRefinement("merge"), 
				TetRSp, TetROut); 
		
		ModuleInstantiation Inv1 = createModuleInstantiationData(
				getData("Inv1_identity","Inv1_persistentIdentity","Inv1_v0.1","Inv1_displayID","Inv1_Name", "Inv1_Description"),
				LacI_Inv, 
				getMapsTo_List(LacISp_LacIIn, TetRSp_TetROut)); 
		
		Model Togglemodel = createModelData(SBOL2Doc_test, 
				getData("Togglemodel_identity","Togglemodel_persistentIdentity","Togglemodel_v0.1","Togglemodel_displayID","Togglemodel_Name", "Togglemodel_Description", "Togglemodel_source", "Togglemodel_language", "Togglemodel_framework"),
				getSetOfURI("Togglemodel_role")
				); 
		
		Module Toggle = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Toggle_type"),
				getSetOfURI("Toggle_role"),
				getData("Toggle_identity","Toggle_persistentIdentity","Toggle_v0.1","Toggle_displayID","Toggle_Name", "Toggle_Description"),
				getFunctionalInstantiation_List(LacISp, TetRSp), 
				getInteraction_List(interact1, interact2), null, null
				);
	}
	
	private static void createTetR_Inverter(SBOLDocument SBOL2Doc_test)
	{
		Structure pLacSeq = createStructureData(SBOL2Doc_test, 
				getData("pLacSeq_identity","pLacSeq_persistentIdentity","pLacSeq_v0.1","pLacSeq_displayID","pLacSeq_Name", "pLacSeq_Description", "pLacSeq_element", "pLacSeq_encoding")); 
		Structure tetRSeq = createStructureData(SBOL2Doc_test, 
				getData("tetRSeq_identity","tetRSeq_persistentIdentity","tetRSeq_v0.1","tetRSeq_displayID","tetRSeq_Name", "tetRSeq_Description", "tetRSeq_element", "tetRSeq_encoding")); 
		Structure pLactetRSeq = createStructureData(SBOL2Doc_test, 
				getData("pLactetRSeq_identity","pLactetRSeq_persistentIdentity","pLactetRSeq_v0.1","pLactetRSeq_displayID","pLactetRSeq_Name", "pLactetRSeq_Description", "pLactetRSeq_element", "pLactetRSeq_encoding")); 
	
		Component ptet = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("Promoter_role"),
				getData("ptet_identity","ptet_persistentIdentity","ptet_v0.1","ptet_displayID","ptet_Name", "ptet_Description"),
				pLacSeq, 
				null, null, null); 
		
		Component lacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("CDS_role"),
				getData("lacI_identity","lacI_persistentIdentity","lacI_v0.1","lacI_displayID","lacI_Name", "lacI_Description"),
				tetRSeq, 
				null, null, null);
		
		
		StructuralInstantiation P = createStructuralInstantiationData(
				getData("P_identity", "P_persistentIdentity", "P_v1.0", "P_displayId", "P_name", "P_description", "public"), 
				ptet);
		
		StructuralInstantiation C = createStructuralInstantiationData(
				getData("C_identity", "C_persistentIdentity", "C_v1.0", "C_displayId", "C_name", "C_description", "public"), 
				lacI);
		
		//NOTE: either use StructuralAnnotation OR StructuralConstraint. Never both 
		StructuralAnnotation p_structAnnotate  = createStructuralAnnotationData(
				getData("p_structAnnotate_identity", "p_structAnnotate_persistentIdentity", "p_structAnnotate_v1.0", "p_structAnnotate_displayId", "p_structAnnotate_name", "p_structAnnotate_description"), 
				P, 
				0, 10);
		
		StructuralAnnotation c_structAnnotate  = createStructuralAnnotationData(
				getData("c_structAnnotate_identity", "c_structAnnotate_persistentIdentity", "c_structAnnotate_v1.0", "c_structAnnotate_displayId", "c_structAnnotate_name", "c_structAnnotate_description"), 
				C, 
				11, 20); 
		
		StructuralConstraint struct_constraint = createStructuralConstraintData(
				getData("struct_constraint_identity", "struct_constraint_persistentIdentity", "struct_constraint_v1.0", "precedes"), 
				P, 
				C); 

		Component ptetLacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("DNA_type"),
				getSetOfURI("Gene_role"),
				getData("ptetLacI_identity","ptetLacI_persistentIdentity","ptetLacI_v0.1","ptetLacI_displayID","ptetLacI_Name", "ptetLacI_Description"),
				pLactetRSeq, 
				getStructuralInstantiation_List(P, C), 
				null, //getStructuralAnnotation_List(p_structAnnotate, c_structAnnotate), 
				getStructuralConstraint_List(struct_constraint)); 
		
		Component TetR = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein_type"),
				getSetOfURI("Transcriptionfactor_role"),
				getData("TetR_identity","TetR_persistentIdentity","TetR_v0.1","TetR_displayID", "TetR_Name", "TetR_Description"),
				null, 
				null, null, null); 
		
		Component LacI = createComponentData(SBOL2Doc_test,
				getSetOfURI("Protein_type"),
				getSetOfURI("T.F._role"),
				getData("LacI_identity","LacI_persistentIdentity","LacI_v0.1","LacI_displayID", "LacI_Name", "LacI_Description"),
				null, 
				null, null, null); 
		
		FunctionalInstantiation TetRIn = createFunctionalInstantiationData(
				getData("TetRIn_identity","TetRIn_persistentIdentity","TetRIn_v0.1","TetRIn_displayID","TetRIn_Name", "TetRIn_Description","public", "input"),
				TetR);
		
		FunctionalInstantiation LacIOut = createFunctionalInstantiationData(
				getData("LacIOut_identity","LacIOut_persistentIdentity","LacIOut_v0.1","LacIOut_displayID","LacIOut_Name", "LacIOut_Description","public", "output"),
				LacI);
		
		FunctionalInstantiation Inv = createFunctionalInstantiationData(
				getData("Inv_identity","Inv_persistentIdentity","Inv_v0.1","Inv_displayID","Inv_Name", "Inv_Description","private", "none"),
				ptetLacI);
		
		Participation p1 = createParticipationData(
				getURI("participation1_identity"), 
				getSetOfURI("repressor"), 
				TetRIn);
		
		Participation p2 = createParticipationData(
				getURI("participation2_identity"), 
				getSetOfURI("repressed"), 
				Inv);
		
		Interaction interact1 =  createInteractionData(
				getData("interact1_identity","interact1_persistentIdentity","interact1_v0.1","interact1_displayID","interact1_Name", "interact1_Description"),
				getParticipation_List(p1, p2), 
				getSetOfURI("repression")); 
		
		Participation p3 = createParticipationData(
				getURI("participation3_identity"), 
				getSetOfURI("producer"), 
				Inv);
		
		Participation p4 = createParticipationData(
				getURI("participation4_identity"), 
				getSetOfURI("produced"), 
				LacIOut);
		
		Interaction interact2 =  createInteractionData(
				getData("interact2_identity","interact2_persistentIdentity","interact2_v0.1","interact2_displayID","interact2_Name", "interact2_Description"),
				getParticipation_List(p3, p4), 
				getSetOfURI("produces")); 
		
		Module LacI_Inv = createModuleData(SBOL2Doc_test, 
				getSetOfURI("Inverter_type"),
				getSetOfURI("T.F._role"),
				getData("LacI_Inv_identity","LacI_Inv_persistentIdentity","LacI_Inv_v0.1","LacI_Inv_displayID","LacI_Inv_Name", "LacI_Inv_Description"),
				getFunctionalInstantiation_List(TetRIn, LacIOut, Inv), 
				getInteraction_List(interact1, interact2), null, null
				);
		
		//---------------------DOUBLE CHECK
//		MapsTo LacISp_LacIOut = createMapTo(
//				getURI("LacISp_Mapsto_LacIOut_identity"), 
//				getRefinement("merge"), 
//				LacISp, LacIOut); 
//		
//		MapsTo TetRSp_TetRIn = createMapTo(
//				getURI("TetRSp_Mapsto_TetRIn_identity"), 
//				getRefinement("merge"), 
//				TetRSp, TetRIn); 
//		
//		ModuleInstantiation Inv2 = createModuleInstantiationData(
//				getData("Inv2_identity","Inv2_persistentIdentity","Inv2_v0.1","Inv2_displayID","Inv2_Name", "Inv2_Description"),
//				TetR_Inv, 
//				getMapsTo_List(LacISp_LacIOut, TetRSp_TetRIn)); 
	}
	
	private static Component createComponentData(SBOLDocument SBOL2Doc_test, 
			Set<URI> type, Set<URI> roles,
			List<String> componentData,
			Structure structureData, 
			List<StructuralInstantiation> structureInstantiationData, 
			List<StructuralAnnotation> structureAnnotationData, 
			List<StructuralConstraint> structureConstraintData)
	{
		URI identity 		   = getURI(componentData.get(0));
		URI persistentIdentity = getURI(componentData.get(1));
		String version 		   = componentData.get(2);
		String displayId 	   = componentData.get(3);
		String name 		   = componentData.get(4);
		String description 	   = componentData.get(5);
		
		Component c = SBOL2Doc_test.createComponent(identity, type, roles);
		c.setPersistentIdentity(persistentIdentity);
		c.setVersion(version);
		c.setDisplayId(displayId);
		c.setName(name);
		c.setDescription(description);
		
		if(structureData != null)
			c.setStructure(structureData.getIdentity()); 
		if(structureInstantiationData != null)
		{
			c.setStructuralInstantiations(structureInstantiationData);
			if(structureAnnotationData != null && structureConstraintData == null)
				c.setStructuralAnnotations(structureAnnotationData);
			else if(structureConstraintData != null)
				c.setStructuralConstraints(structureConstraintData);
		}
		
		return c; 
	}
	
	private static FunctionalInstantiation createFunctionalInstantiationData(
			List<String> functionalInstantiation_data, 
			Component c)
	{	
		URI identity 		   = getURI(functionalInstantiation_data.get(0)); 
		URI persistentIdentity = getURI(functionalInstantiation_data.get(1)); 
		String version 		   = functionalInstantiation_data.get(2); 
		String displayId 	   = functionalInstantiation_data.get(3);
		String name 		   = functionalInstantiation_data.get(4); 
		String description 	   = functionalInstantiation_data.get(5); 
		
		AccessType access = null; 
		if(functionalInstantiation_data.get(6).equals("public"))
			access = AccessType.PUBLIC; 
		else if(functionalInstantiation_data.get(6).equals("private"))
			access = AccessType.PRIVATE; 
		
		DirectionType direction = null; 
		if(functionalInstantiation_data.get(7).equals("input"))
			direction = DirectionType.input;
		else if(functionalInstantiation_data.get(7).equals("output"))
			direction = DirectionType.output;
		else if(functionalInstantiation_data.get(7).equals("inout"))
			direction = DirectionType.inout; 
		else if(functionalInstantiation_data.get(7).equals("none"))
			direction = DirectionType.none; 
		
		URI instantiatedComponent = c.getIdentity();
		
		FunctionalInstantiation f = new FunctionalInstantiation(identity, access, instantiatedComponent, direction);
		f.setIdentity(identity);
		f.setPersistentIdentity(persistentIdentity);
		f.setVersion(version);
		f.setDisplayId(displayId);
		f.setName(name);
		f.setDescription(description);
		
		return f;
	}
	
	private static Interaction createInteractionData(
			List<String> interaction_data, 
			List<Participation> participations,
			Set<URI> type)
	{	
		URI identity 		   = getURI(interaction_data.get(0)); 
		URI persistentIdentity = getURI(interaction_data.get(1));
		String version 		   = interaction_data.get(2);
		String displayId 	   = interaction_data.get(3); 
		String name 		   = interaction_data.get(4); 
		String description 	   = interaction_data.get(5); 
		
		Interaction interaction = new Interaction(identity, type, participations);
		
		interaction.setPersistentIdentity(persistentIdentity);
		interaction.setVersion(version);
		interaction.setDisplayId(displayId);
		interaction.setName(name);
		interaction.setDescription(description);
		
		return interaction;
	}
	
	private static MapsTo createMapTo (URI identity, RefinementType refinement, 
			FunctionalInstantiation pre_fi, FunctionalInstantiation post_fi)
	{
		return new MapsTo(identity, refinement, pre_fi.getIdentity(), post_fi.getIdentity());
	}
	
	private static Module createModuleData(SBOLDocument SBOL2Doc_test, 
			Set<URI> type, Set<URI> roles,
			List<String> module_data,
			List<FunctionalInstantiation> functionalInstantiation_data,
			List<Interaction> interactionData,
			List<ModuleInstantiation> moduleInstantiation_data,
			Set<URI> model_data)
	{
		URI identity 		   = getURI(module_data.get(0)); 
		URI persistentIdentity = getURI(module_data.get(1));
		String version 		   = module_data.get(2); 
		String displayId 	   = module_data.get(3); 
		String name 		   = module_data.get(4); 
		String description 	   = module_data.get(5);  
		
		Module m = SBOL2Doc_test.createModule(identity, roles);
		m.setPersistentIdentity(persistentIdentity);
		m.setVersion(version);
		m.setDisplayId(displayId);
		m.setName(name);
		m.setDescription(description);
		
		if(functionalInstantiation_data != null)
			m.setFunctionalInstantiations(functionalInstantiation_data);
		if(interactionData != null)
			m.setInteractions(interactionData);
		if(moduleInstantiation_data != null)
			m.setModuleInstantiations(moduleInstantiation_data);
		if(model_data != null)
			m.setModels(model_data);
		
		return m; 
	}
	
	private static ModuleInstantiation createModuleInstantiationData(
			List<String> moduleInstantiation_data, 
			Module m, 
			List<MapsTo> maps)
	{	
		URI identity 		   = getURI(moduleInstantiation_data.get(0)); 
		URI persistentIdentity = getURI(moduleInstantiation_data.get(1));
		String version 		   = moduleInstantiation_data.get(2); 
		String displayId 	   = moduleInstantiation_data.get(3); 
		String name 		   = moduleInstantiation_data.get(4); 
		String description 	   = moduleInstantiation_data.get(5); 
		
		ModuleInstantiation modInstantiation = new ModuleInstantiation(identity, m.getIdentity()); 
		modInstantiation.setPersistentIdentity(persistentIdentity);
		modInstantiation.setVersion(version);
		modInstantiation.setDisplayId(displayId);
		modInstantiation.setName(name);
		modInstantiation.setDescription(description);
		
		for(MapsTo map : maps)
			modInstantiation.addReference(map);
		return modInstantiation; 
	}
	
	private static Participation createParticipationData(
			URI identity, Set<URI> roles, FunctionalInstantiation fi)
	{
		return new Participation(identity, roles, fi.getIdentity());
	}
	
	private static StructuralAnnotation createStructuralAnnotationData(
			List<String> structuralAnnotations_data,
			StructuralInstantiation ref_structInstant,
			int startRange, int endRange)
	{
		URI identity 		   = getURI(structuralAnnotations_data.get(0));
		URI persistentIdentity = getURI(structuralAnnotations_data.get(1));
		String version 		   = structuralAnnotations_data.get(2);
		String displayId 	   = structuralAnnotations_data.get(3);
		String name 		   = structuralAnnotations_data.get(4);
		String description 	   = structuralAnnotations_data.get(5);
		Location location 	   = new Range(ref_structInstant.getIdentity(), startRange, endRange);
		
		StructuralAnnotation s = new StructuralAnnotation(identity, location);
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		s.setDisplayId(displayId);
		s.setName(name);
		s.setDescription(description);
		
		return s; 
	}
	
	private static StructuralConstraint createStructuralConstraintData(
			List<String> structuralConstraints_data,  
			StructuralInstantiation pre_structInstant, 
			StructuralInstantiation post_structInstant)
	{
		URI identity 		   = getURI(structuralConstraints_data.get(0));
		URI persistentIdentity = getURI(structuralConstraints_data.get(1));
		String version 		   = structuralConstraints_data.get(2);
		URI restriction 	   = getURI(structuralConstraints_data.get(3)); 
		URI subject 		   = pre_structInstant.getIdentity(); 
		URI object 			   = post_structInstant.getIdentity(); 
		
		StructuralConstraint s = new StructuralConstraint(identity, restriction, subject, object);
		
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		
		return s; 
	}
	
	private static StructuralInstantiation createStructuralInstantiationData(
			List<String> structuralInstantiations_data, 
			Component c)
	{
		URI identity 		   = getURI(structuralInstantiations_data.get(0));
		URI persistentIdentity = getURI(structuralInstantiations_data.get(1));
		String version 		   = structuralInstantiations_data.get(2);
		String displayId 	   = structuralInstantiations_data.get(3);
		String name 		   = structuralInstantiations_data.get(4);
		String description	   = structuralInstantiations_data.get(5);
		
		AccessType access = null; 
		if(structuralInstantiations_data.get(6).equals("public"))
			access = AccessType.PUBLIC; 
		else if(structuralInstantiations_data.get(6).equals("private"))
			access = AccessType.PRIVATE; 
		
		URI instantiatedComponent = c.getIdentity(); //TODO: is component (c.identity) instantiatedComponent?
		
		StructuralInstantiation s = new StructuralInstantiation(identity, access, instantiatedComponent);
		s.setIdentity(identity);
		s.setPersistentIdentity(persistentIdentity);
		s.setVersion(version);
		s.setDisplayId(displayId);
		s.setName(name);
		s.setDescription(description);
		
		return s; 
	}
	
	private static Structure createStructureData(SBOLDocument SBOL2Doc_test, List<String> structureData)
	{
		URI identity 		   = getURI(structureData.get(0)); 
		URI persistentIdentity = getURI(structureData.get(1)); 
		String version 		   = structureData.get(2); 
		String displayId 	   = structureData.get(3); 
		String name 		   = structureData.get(4); 
		String description 	   = structureData.get(5); 
		String element 		   = structureData.get(6); 
		URI encoding  		   = getURI(structureData.get(7));
		
		Structure structure = SBOL2Doc_test.createStructure(identity, element, encoding);
		structure.setPersistentIdentity(persistentIdentity);
		structure.setVersion(version);
		structure.setDisplayId(displayId);
		structure.setName(name);
		structure.setDescription(description);
		return structure;
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
	
	private static List<FunctionalInstantiation> getFunctionalInstantiation_List(FunctionalInstantiation ... fi)
	{
		return new ArrayList<FunctionalInstantiation>(Arrays.asList(fi)); 
	}
	
	private static List<Interaction> getInteraction_List(Interaction ... i)
	{
		return new ArrayList<Interaction>(Arrays.asList(i)); 
	}
	
	private static List<MapsTo> getMapsTo_List(MapsTo ... maps)
	{
		return new ArrayList<MapsTo>(Arrays.asList(maps));
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
	
	private static RefinementType getRefinement(String s)
	{
		if(s.equals("verifyIdentical"))
			return RefinementType.verifyIdentical; 
		else if(s.equals("useLocal"))
			return RefinementType.useLocal; 
		else if(s.equals("useRemote"))
			return RefinementType.useRemote; 
		else if(s.equals("merge"))
			return RefinementType.merge; 
		return RefinementType.merge; 
	}
	
	private static List<Participation> getParticipation_List(Participation ... p)
	{
		return new ArrayList<Participation>(Arrays.asList(p)); 
	}
	
	private static Set<URI> getSetOfURI(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list; 
	}
	
	private static List<StructuralAnnotation> getStructuralAnnotation_List(StructuralAnnotation ... sa)
	{
		return new ArrayList<StructuralAnnotation>(Arrays.asList(sa)); 
	}
	
	private static List<StructuralConstraint> getStructuralConstraint_List(StructuralConstraint ... sc)
	{
		return new ArrayList<StructuralConstraint>(Arrays.asList(sc)); 
	}
	
	private static List<StructuralInstantiation> getStructuralInstantiation_List(StructuralInstantiation ... si)
	{
		return new ArrayList<StructuralInstantiation>(Arrays.asList(si)); 
	}
	
	
	private static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}
	
	//TODO: FIX
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
	
	//TODO: FIX
	private static Model createModelData(SBOLDocument doc, List<String> modeldata, Set<URI> roles)
	{	
		URI identity 		   = getURI(modeldata.get(0)); 
		URI persistentIdentity = getURI(modeldata.get(1)); 
		String version 		   = modeldata.get(2);
		String displayId 	   = modeldata.get(3); 
		String name 		   = modeldata.get(4);
		String description     = modeldata.get(5);  
		URI source 			   = getURI(modeldata.get(6)); 
		URI language 		   = getURI(modeldata.get(7));
		URI framework 		   = getURI(modeldata.get(8)); 
		
		//(URI identity, URI source, URI language, URI framework, Set<URI> roles)
		Model model = doc.createModel(identity, source, language, framework, roles);
	
		model.setIdentity(identity);
		model.setPersistentIdentity(persistentIdentity);
		model.setDisplayId(displayId);
		model.setName(name);
		model.setDescription(description);
		model.setVersion(version);
		
		return model;
	}

}
