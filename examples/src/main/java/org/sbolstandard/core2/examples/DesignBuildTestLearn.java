package org.sbolstandard.core2.examples;

import java.net.URI;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.Activity;
import org.sbolstandard.core2.Agent;
import org.sbolstandard.core2.Association;
import org.sbolstandard.core2.Attachment;
import org.sbolstandard.core2.EDAMOntology;
import org.sbolstandard.core2.Experiment;
import org.sbolstandard.core2.ExperimentalData;
import org.sbolstandard.core2.Implementation;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.Plan;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SystemsBiologyOntology;

public class DesignBuildTestLearn {

	public static void main( String[] args ) throws Exception
    {
		String version = "1";
		SBOLDocument doc = new SBOLDocument();
		doc.setDefaultURIprefix("https://www.async.ece.utah.edu");
		
		Agent iBioSim = doc.createAgent("iBioSim", "3.0");
		iBioSim.setName("iBioSim");
		iBioSim.setDescription("iBioSim is a computer-aided design (CAD) tool aimed for the modeling, analysis, and design of genetic circuits. " +
				"It is capable of importing and exporting models specified using the Systems Biology Markup Language (SBML). " +
				"iBioSim also supports the Synthetic Biology Open Language (SBOL), an emerging standard for information exchange in synthetic biology.");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Chris Myers");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Nathan Barker");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Scott Glass");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Kevin Jones");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Hiroyuki Kuwahara");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Curtis Madsen");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Nam Nguyen");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Tramy Nguyen");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Tyler Patterson");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Nicholas Roehner");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Jason Stevens");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Leandro Watanabe");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Michael Zhang");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Zhen Zhang");
		iBioSim.createAnnotation(new QName("http://purl.org/dc/elements/1.1/","creator","dc"), "Zach Zundel");

		Agent cello = doc.createAgent("Cello", "2.0");
		cello.setName("Cello");
		cello.setDescription("The Cello input is a high-level logic specification written in Verilog, a hardware description language. The code is parsed to generate a truth table, and logic synthesis produces a circuit diagram with the genetically available gate types to implement the truth table. The gates in the circuit are assigned using experimentally characterized genetic gates. In assignment, a predicted circuit score guides a breadth-first search, or a Monte Carlo simulated annealing search. The assignment with the highest score is chosen, and this assignment can be physically implemented in a combinatorial number of different genetic layouts. The Eugene language is used for rule-based constrained combinatorial design of one or more final DNA sequence(s) for the designed circuit.");
		
		Agent ginkgo = doc.createAgent("GinkgoBioworks","1.0");
		ginkgo.setName("Ginkgo Bioworks");
		ginkgo.setDescription("Ginkgo Bioworks is the organism company. We design custom organisms for customers across multiple markets. We build our foundries to scale the process of organism engineering using software and hardware automation. Organism engineers at Ginkgo learn from nature to develop new organisms that replace technology with biology.");

		Agent transcriptic = doc.createAgent("Transcriptic","1.0");
		transcriptic.setName("Transcriptic");
		transcriptic.setDescription("The Transcriptic Common Lab Environment (TCLE) coordinates scientific processes, instruments, and robotics to create a programmatically addressable lab environment that enables unparalleled scale, efficiency, and accuracy for the discovery process.");
		
		doc.read("/Users/myers/research/talks/BioTalks/DARPA0719/DARPADemo.sbol");
		Attachment verilogSource = doc.createAttachment("Verilog0x8E", version, URI.create("file:model0x8E.v"));
		Model model = doc.createModel("VerilogModel0x8E", version, verilogSource.getIdentity(),
				URI.create("http://ece.utah.edu/verilog"), 
				SystemsBiologyOntology.DISCRETE_FRAMEWORK);
		Attachment logic = doc.createAttachment("Logic0x8E", version, URI.create("file:logic0x8E.png"));
		logic.setFormat(EDAMOntology.PNG);
		model.addAttachment(logic);
		Activity designActivity = doc.createActivity("Design0x8E", version);
		designActivity.createUsage("Model", model.getIdentity());
		Association designAssoc = designActivity.createAssociation("association", cello.getIdentity());
		Plan designPlan = doc.createPlan("DesignPlan", version);
		designAssoc.setPlan(designPlan.getIdentity());
		ModuleDefinition md = doc.getModuleDefinition("Circuit0x8E","");
		md.addWasGeneratedBy(designActivity.getIdentity());
		Activity buildActivity = doc.createActivity("Build0x8E", version);
		buildActivity.createUsage("Design", md.getIdentity());
		Association buildAssoc = buildActivity.createAssociation("GinkgoBioworks", ginkgo.getIdentity());
		Plan buildPlan = doc.createPlan("AssemblyPlan", version);
		buildAssoc.setPlan(buildPlan.getIdentity());
		Implementation imp = doc.createImplementation("Plasmid0x8E", version);
		imp.setBuilt(md);
		imp.addWasGeneratedBy(buildActivity.getIdentity());
		Activity testActivity = doc.createActivity("Test0x8E", version);
		testActivity.createUsage("Build", imp.getIdentity());
		Association testAssoc = testActivity.createAssociation("Transcriptic", transcriptic.getIdentity());
		Plan testPlan = doc.createPlan("FlowCytometryPlan", version);
		testAssoc.setPlan(testPlan.getIdentity());
		Experiment expt = doc.createExperiment("Experiment0x8E", version);
		expt.addWasGeneratedBy(testActivity.getIdentity());
		ExperimentalData data = doc.createExperimentalData("Data0x8E", version);
		data.addWasGeneratedBy(testActivity.getIdentity());
		expt.addExperimentalData(data.getIdentity());
		Attachment dataFile = doc.createAttachment("TimeCourse0x8E", version, URI.create("file:timeCourse0x8E.png"));
		dataFile.setFormat(EDAMOntology.PNG);
		dataFile.addWasGeneratedBy(testActivity.getIdentity());
		data.addAttachment(dataFile);
		Activity learnActivity = doc.createActivity("Learn0x8E", version);
		learnActivity.createUsage("Learn", expt.getIdentity());
		Association learnAssoc = learnActivity.createAssociation("association", iBioSim.getIdentity());
		Plan learnPlan = doc.createPlan("ModelGenerationPlan", version);
		learnAssoc.setPlan(learnPlan.getIdentity());
		//Attachment modelSource = doc.createAttachment("SBML0x8E", version, URI.create("file:model0x8E.sbml"));
		Activity analysisActivity = doc.getActivity("Analysis0x8E", version);
		Association analysisAssoc = analysisActivity.getAssociation("association");
		analysisAssoc.setAgent(iBioSim.getIdentity());
		Model sbmlModel = doc.getModel("Circuit0x8E_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("circuit_0x8E_H1_HlyIIR_module_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("circuit_0x8E_A1_AmtR_module_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("circuit_0x8E_YFP_module_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("circuit_0x8E_E1_BetI_module_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("circuit_0x8E_P3_PhlF_module_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		sbmlModel = doc.getModel("Simulation_Environment_model", version);
		sbmlModel.addWasGeneratedBy(learnActivity.getIdentity());
		analysisActivity.createUsage(sbmlModel.getDisplayId(), sbmlModel.getIdentity());
		Attachment simSource = doc.createAttachment("Simulation0x8E", version, URI.create("file:simulation0x8E.png"));
		simSource.setFormat(EDAMOntology.PNG);
		simSource.addWasGeneratedBy(analysisActivity.getIdentity());
		doc.write("/Users/myers/research/talks/BioTalks/DARPA0719/DARPADemo.xml");
    }
	
}
