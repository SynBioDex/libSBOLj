package org.sbolstandard.core2.examples;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import org.sbolstandard.core2.Annotation;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.GenericTopLevel;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.SequenceOntology;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * This example shows how to add provenance information using generic top level entities and custom annotations.
 * A codon optimization of a CDS component from another is documented using PROV-O's Activity, Agent, Usage and Association classes.
 */
public class Provenance_CodonOptimization {
	
	private static ComponentDefinition getCds(SBOLDocument document, String id, String name) throws SBOLValidationException
	{
		ComponentDefinition cds = document.createComponentDefinition(id, ComponentDefinition.DNA);
		cds.addRole(SequenceOntology.CDS);
		cds.setName(name);
		return cds;
	}
	public static void main( String[] args ) throws Exception
    {		
		NamespaceBinding provNs = NamespaceBinding("http://www.w3.org/ns/prov#", "prov");
		NamespaceBinding myAppNs = NamespaceBinding("http://myapp.com/", "myapp");
		
		SBOLDocument document = new SBOLDocument();				
		document.setTypesInURIs(true);		
		document.addNamespace(URI.create(myAppNs.getNamespaceURI()),myAppNs.getPrefix());
		document.setDefaultURIprefix(myAppNs.getNamespaceURI());	
						
		ComponentDefinition optimizedCds = getCds(document, "codon_optimized","Codon optimised CDS");
		ComponentDefinition sourceCds = getCds(document,"non_codon_optimized", "Non Codon optimised CDS");		
		optimizedCds.addWasDerivedFrom(sourceCds.getIdentity());
		
		//Create the agent definition for the codon optimization software		
		GenericTopLevel agent=document.createGenericTopLevel("codon_optimization_software", provNs.withLocalPart("Agent"));
		agent.setName("Codon Optimization Software");		
				
		//Create the generic top level entity for the codon optimization activity
		GenericTopLevel activity=document.createGenericTopLevel("codon_optimization_activity", provNs.withLocalPart("Activity"));
		activity.setName("Codon Optimization Activity");		
		
		//Create the qualifiedUsage annotation to describe the use of the non codon optimized CDS component
		activity.createAnnotation(provNs.withLocalPart("qualifiedUsage"), 
				provNs.withLocalPart("Usage"), 
				URI.create(activity.getIdentity().toString() + "/" + "usage"), 
				new ArrayList<Annotation>(
						Arrays.asList(
								new Annotation(provNs.withLocalPart("entity"),sourceCds.getIdentity()),
								new Annotation(provNs.withLocalPart("hadRole"), URI.create("http://sbols.org/v2#source"))								
								)));
		
		//Create the qualifiedAssociation annotation to describe the use of the software agent used in the activity
		activity.createAnnotation(provNs.withLocalPart("qualifiedAssociation"), 
				provNs.withLocalPart("Association"), 
				URI.create(activity.getIdentity().toString() + "/" + "association"), 
				new ArrayList<Annotation>(
						Arrays.asList(
								new Annotation(provNs.withLocalPart("agent"), agent.getIdentity()),
								new Annotation(provNs.withLocalPart("hadRole"), myAppNs.namespacedUri("codonoptimiser"))
								)));
								
		optimizedCds.createAnnotation(provNs.withLocalPart("wasGeneratedBy"), activity.getIdentity());
		SBOLWriter.write(document,"/Users/myers/Provenance_CodonOptimization.xml");		
    }
}
