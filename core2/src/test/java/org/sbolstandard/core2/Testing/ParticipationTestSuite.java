package org.sbolstandard.core2.Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sbolstandard.core.util.SequenceOntology;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Module;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.Participation;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SystemsBiologyOntology;

public class ParticipationTestSuite {
	private SBOLDocument doc = null;
	private ComponentDefinition TetR = null;
	private FunctionalComponent TetRInverter_fc = null;
	private ModuleDefinition TetRInverter_MD = null;
	private Module TetRInverter = null;

	@Before
	public void setUp() throws Exception {
		doc = new SBOLDocument();
		doc.setDefaultURIprefix("http://sbols.org/CRISPR_Example/");
		doc.setComplete(true);
		doc.setCreateDefaults(true);
		doc.setComplete(true);
		TetRInverter_MD = doc.createModuleDefinition("TetRInverter_MD");

		TetR = doc.createComponentDefinition("TetR", ComponentDefinition.PROTEIN);
		TetRInverter_fc = TetRInverter_MD.createFunctionalComponent(
				"TetRInverter_fc", AccessType.PUBLIC, TetR.getIdentity(), DirectionType.INOUT);

	}

	@Test
	public void test_RoleMethods()
	{
		TetR.addRole(SystemsBiologyOntology.INHIBITOR);
		assertTrue(TetR.containsRole(SystemsBiologyOntology.INHIBITOR));
		TetR.removeRole(SystemsBiologyOntology.INHIBITOR);
		assertTrue(TetR.getRoles().size() == 0);
	}
	
	@Test
	public void test_ParticipantMethods() throws SBOLValidationException
	{
		Interaction TetR_Interaction = TetRInverter_MD.createInteraction("TetR_Interaction", SystemsBiologyOntology.NON_COVALENT_BINDING);
		Participation TetR_part = TetR_Interaction.createParticipation("TetR", "TetR", SystemsBiologyOntology.PRODUCT);
		assertTrue(TetR_Interaction.getParticipation("TetR").equals(TetR_part));
		assertNotNull(TetR_part);
	}
	

}
