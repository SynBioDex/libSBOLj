package org.sbolstandard.core2.test;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.sbolstandard.core2.MapsTo;
import org.sbolstandard.core2.Module;
import org.sbolstandard.core2.ModuleInstantiation;
import org.sbolstandard.core2.RefinementType;
import org.sbolstandard.core2.SBOLDocument;

public class libTester {

	public static void main( String[] args ) throws Exception {
		URI toggleSwitchURI = getURI("toggle_switch");
		URI inv1URI = getURI("Inverter1");
		URI LacI_InverterURI = getURI("LacI_Inverter");
		URI LacI_localURI = getURI("LacI_local");
		URI LacI_remoteURI = getURI("LacI_remote");
		URI mapto_LacI_inputURI = getURI("mapto_LacI_input");
		URI mapto_TetR_outputURI = getURI("mapto_TetR_output");
		URI TetR_localURI = getURI("TetR_local");
		URI TetR_remoteURI = getURI("TetR_remote");
		
		
		URI inv2URI = getURI("Inverter2");
		URI TetR_InverterURI = getURI("TetR_Inverter");
		URI mapto_TetR_InverterURI = getURI("mapto_TetR_Inverter");
		
		
		SBOLDocument toggleSwitch = new SBOLDocument();
		Module toggleModule = toggleSwitch.createModule(toggleSwitchURI, getSetOfURIs("switch"));
		// Instantiate LacI inverter
		ModuleInstantiation inv1 = toggleModule.createModuleInstantiation(inv1URI, LacI_InverterURI);
		toggleModule.addModuleInstantiation(inv1);
		
		
		MapsTo mapto_LacI_input = new MapsTo(mapto_LacI_inputURI, RefinementType.verifyIdentical, LacI_localURI, LacI_remoteURI);
		MapsTo mapto_TetR_output = new MapsTo(mapto_TetR_outputURI, RefinementType.verifyIdentical, TetR_localURI, TetR_remoteURI);
		inv1.addReference(mapto_LacI_input);
		
		
		// Instantiate TetR Inverter
		ModuleInstantiation inv2 = toggleModule.createModuleInstantiation(inv2URI, TetR_InverterURI);
		MapsTo mapto_TetR_Inverter = new MapsTo(mapto_TetR_InverterURI, RefinementType.verifyIdentical, inv2URI, TetR_InverterURI);
		inv2.addReference(mapto_TetR_Inverter);
		
	}

	private static URI getURI(String append)
	{
		return URI.create("http://www.async.ece.utah.edu/" + append);
	}
	
	private static Set<URI> getSetOfURIs(String ... appends)
	{
		Set<URI> list = new HashSet<URI>();
		for(String append : appends)
		{
			list.add(getURI(append));
		}
		return list; 
	}

}


