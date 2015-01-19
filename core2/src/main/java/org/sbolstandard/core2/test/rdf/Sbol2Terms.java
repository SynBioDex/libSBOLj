package org.sbolstandard.core2.test.rdf;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
/**
 * Provides qualified names for SBOL2.0 objects.
 *
 */
public class Sbol2Terms
{
	public static final NamespaceBinding sbol2 = NamespaceBinding("http://sbols.org/v2#", "sbol");

	public static final class Annotation {
		public static final QName Annotation = sbol2.withLocalPart("Annotation");
		public static final QName relation   = sbol2.withLocalPart("relation");
		public static final QName value	   	 = sbol2.withLocalPart("value");
	}


	public static final class Identified {
		public static final QName Identified 		 = sbol2.withLocalPart("Identified");
		public static final QName identity 	 		 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Documented {
		public static final QName Documented 		 = sbol2.withLocalPart("Documented");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class TopLevel {
		public static final QName TopLevel 	  		 = sbol2.withLocalPart("TopLevel");
		public static final QName displayId   		 = sbol2.withLocalPart("displayId");
		public static final QName name 		  		 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 	  		 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 		 	 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class GenericTopLevel {
		public static final QName GenericTopLevel 	 = sbol2.withLocalPart("GenericTopLevel");
		public static final QName rdfType  			 = sbol2.withLocalPart("rdfType");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Collection {
		public static final QName Collection 		 = sbol2.withLocalPart("Collection");
		public static final QName hasMembers 		 = sbol2.withLocalPart("members");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class ComponentDefinition {
		public static final QName ComponentDefinition 	= sbol2.withLocalPart("ComponentDefinition");
		public static final QName type 					= sbol2.withLocalPart("type");
		public static final QName roles 				= sbol2.withLocalPart("roles");
		public static final QName hasSubComponents 		= sbol2.withLocalPart("subComponents"); //Component
		public static final QName hasSequenceAnnotation = sbol2.withLocalPart("sequenceAnnotation"); //SequenceAnnotation
		public static final QName hasSequenceConstraint = sbol2.withLocalPart("sequenceConstraint"); //SequenceConstraint
		public static final QName hasSequence 		  	= sbol2.withLocalPart("sequence"); //Sequence
		public static final QName displayId   		 	= sbol2.withLocalPart("displayId");
		public static final QName name 		  		 	= sbol2.withLocalPart("name");
		public static final QName description 		 	= sbol2.withLocalPart("description");
		public static final QName identity 	  		 	= sbol2.withLocalPart("identity");
		public static final QName persistentIdentity 	= sbol2.withLocalPart("persistentIdentity");
		public static final QName version 		 	 	= sbol2.withLocalPart("version");
		public static final QName timestamp 		 	= sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 	= sbol2.withLocalPart("annotations");
	}

	public static final class ComponentInstance {
		public static final QName ComponentInstance  = sbol2.withLocalPart("ComponentInstance");
		public static final QName hasMappings 		 = sbol2.withLocalPart("mappings"); //MapsTo
		public static final QName hasDefinition 	 = sbol2.withLocalPart("definition"); //ComponentDefinition
		public static final QName access 			 = sbol2.withLocalPart("access");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 			 	 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Component {
		public static final QName Component 		 = sbol2.withLocalPart("Component");
		public static final QName access 			 = sbol2.withLocalPart("access");
		public static final QName hasMappings 		 = sbol2.withLocalPart("mappings"); //MapsTo
		public static final QName hasDefinition 	 = sbol2.withLocalPart("definition");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 		 	 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class FunctionalComponent {
		public static final QName FunctionalComponent = sbol2.withLocalPart("FunctionalComponent");
		public static final QName access 			  = sbol2.withLocalPart("access");
		public static final QName direction 		  = sbol2.withLocalPart("direction");
		public static final QName hasMappings 		 = sbol2.withLocalPart("mappings"); //MapsTo
		public static final QName hasDefinition 	 = sbol2.withLocalPart("definition");
		public static final QName displayId 		  = sbol2.withLocalPart("displayId");
		public static final QName name 				  = sbol2.withLocalPart("name");
		public static final QName description 		  = sbol2.withLocalPart("description");
		public static final QName identity 		  	  = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity  = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			  = sbol2.withLocalPart("version");
		public static final QName timestamp 		  = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	  = sbol2.withLocalPart("annotations");
	}

	public static final class Interaction {
		public static final QName Interaction 		 = sbol2.withLocalPart("Interaction");
		public static final QName hasParticipations  = sbol2.withLocalPart("participations"); //Participation
		public static final QName type  			 = sbol2.withLocalPart("type");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Location {
		public static final QName Location 			 = sbol2.withLocalPart("Location");
		public static final QName identity 		 	 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class MultiRange {
		public static final QName MultiRange 		 = sbol2.withLocalPart("MultiRange");
		public static final QName hasRanges 		 = sbol2.withLocalPart("ranges");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Range {
		public static final QName Range 			 = sbol2.withLocalPart("Range");
		public static final QName start 			 = sbol2.withLocalPart("start");
		public static final QName end 			 	 = sbol2.withLocalPart("end");
		public static final QName orientation 		 = sbol2.withLocalPart("orientation");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Cut {
		public static final QName Cut 				 = sbol2.withLocalPart("Cut");
		public static final QName at				 = sbol2.withLocalPart("at");
		public static final QName orientation 		 = sbol2.withLocalPart("orientation");
		public static final QName identity 		 	 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class MapsTo {
		public static final QName MapsTo 			 = sbol2.withLocalPart("MapsTo");
		public static final QName refinement 		 = sbol2.withLocalPart("refinement");
		public static final QName hasLocal 			 = sbol2.withLocalPart("local"); //ComponentInstance
		public static final QName hasRemote 		 = sbol2.withLocalPart("remote"); //ComponentInstance
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Model {
		public static final QName Model 			 = sbol2.withLocalPart("Model");
		public static final QName source 			 = sbol2.withLocalPart("source");
		public static final QName language 			 = sbol2.withLocalPart("language");
		public static final QName framework 		 = sbol2.withLocalPart("framework");
		public static final QName roles 			 = sbol2.withLocalPart("roles");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations     = sbol2.withLocalPart("annotations");
	}

	public static final class ModuleDefinition {
		public static final QName ModuleDefinition 	 = sbol2.withLocalPart("ModuleDefinition");
		public static final QName roles 			 = sbol2.withLocalPart("roles");
		public static final QName hasSubModules 	 = sbol2.withLocalPart("subModules"); //Module
		public static final QName hasInteractions 	 = sbol2.withLocalPart("interactions"); //Interaction
		public static final QName hasComponents 	 = sbol2.withLocalPart("components"); //FunctionalComponent
		public static final QName hasModels 		 = sbol2.withLocalPart("models"); //Model
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Module {
		public static final QName Module   			 = sbol2.withLocalPart("Module");
		public static final QName hasMappings 		 = sbol2.withLocalPart("mappings"); //MapsTo
		public static final QName hasDefinition 	 = sbol2.withLocalPart("definition"); //definition
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Participation {
		public static final QName Participation 	 = sbol2.withLocalPart("Participation");
		public static final QName role 				 = sbol2.withLocalPart("role");
		public static final QName hasParticipant 	 = sbol2.withLocalPart("participant"); //FunctionalComponent
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class SequenceAnnotation {
		public static final QName SequenceAnnotation = sbol2.withLocalPart("SequenceAnnotation");
		public static final QName hasComponent 		 = sbol2.withLocalPart("component");
		public static final QName hasLocation 		 = sbol2.withLocalPart("location");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class SequenceConstraint {
		public static final QName SequenceConstraint = sbol2.withLocalPart("SequenceConstraint");
		public static final QName restriction 		 = sbol2.withLocalPart("restriction");
		public static final QName hasSubject 		 = sbol2.withLocalPart("subject"); //Component
		public static final QName hasObject 		 = sbol2.withLocalPart("object"); //Component
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

	public static final class Sequence {
		public static final QName Sequence 			 = sbol2.withLocalPart("Sequence");
		public static final QName elements 			 = sbol2.withLocalPart("elements");
		public static final QName encoding 			 = sbol2.withLocalPart("encoding");
		public static final QName displayId 		 = sbol2.withLocalPart("displayId");
		public static final QName name 				 = sbol2.withLocalPart("name");
		public static final QName description 		 = sbol2.withLocalPart("description");
		public static final QName identity 			 = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		public static final QName version 			 = sbol2.withLocalPart("version");
		public static final QName timestamp 		 = sbol2.withLocalPart("timestamp");
		public static final QName hasAnnotations 	 = sbol2.withLocalPart("annotations");
	}

}