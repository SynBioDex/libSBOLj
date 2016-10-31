package org.sbolstandard.core2.examples;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
import java.net.URI;
import java.sql.NClob;
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
 * This example shows how to add provenance information using generic top level entities and custom annotations to derive strains.
 * In the example, provenance is incorporated to document the derivation of the B.subtilis 168 strain form the Ncib 3610 strain using X-ray mutagenesis.
 */
public class Provenance_StrainDerivation {
	
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
		
		ComponentDefinition b168 = getCds(document, "bsubtilis168","Bacillus subtilis 168");
		ComponentDefinition b3610 = getCds(document,"bsubtilisncib3610", "Bacillus subtilis Ncbi 3610");		
		b168.setWasDerivedFrom(b3610.getIdentity());
				
		//Create the agent definition to represent X-ray		
		GenericTopLevel agent=document.createGenericTopLevel("x_ray", provNs.withLocalPart("Agent"));
		agent.setName("X-ray");		
				
		//Create the generic top level entity for the X-ray mutagenesis activity
		GenericTopLevel activity=document.createGenericTopLevel("xraymutagenesis", provNs.withLocalPart("Activity"));
		activity.setName("X-ray mutagenesis");		
		
		//Create the qualifiedUsage annotation to describe the use of the parent strain 
		activity.createAnnotation(provNs.withLocalPart("qualifiedUsage"), 
				provNs.withLocalPart("Usage"), 
				URI.create(activity.getIdentity().toString() + "/" + "usage"), 
				new ArrayList<Annotation>(
						Arrays.asList(
								new Annotation(provNs.withLocalPart("entity"), b3610.getIdentity()),
								new Annotation(provNs.withLocalPart("hadRole"), URI.create("http://sbols.org/v2#source"))								
								)));
		
		//Create the qualifiedAssociation annotation to describe the use of the agent used in the activity
		activity.createAnnotation(provNs.withLocalPart("qualifiedAssociation"), 
				provNs.withLocalPart("Association"), 
				URI.create(activity.getIdentity().toString() + "/" + "association"), 
				new ArrayList<Annotation>(
						Arrays.asList(
								new Annotation(provNs.withLocalPart("agent"), agent.getIdentity()),
								new Annotation(provNs.withLocalPart("hadRole"), myAppNs.namespacedUri("mutagen"))
								)));
		
		b168.createAnnotation(provNs.withLocalPart("wasGeneratedBy"), b3610.getIdentity());
		
		SBOLWriter.write(document,(System.out));		
    }
}
