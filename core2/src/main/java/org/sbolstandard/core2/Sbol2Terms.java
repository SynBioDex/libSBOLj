package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.net.URI;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
/**
 * Provides qualified names for SBOL2.0 objects.
 *
 */
class Sbol2Terms
{

	/**
	 * The namespace binding for SBOL2.0
	 */
	public static final NamespaceBinding sbol2 = NamespaceBinding("http://sbols.org/v2#", "sbol");
	public static final NamespaceBinding dc = NamespaceBinding("http://purl.org/dc/terms/", "dcterms");
	public static final NamespaceBinding prov = NamespaceBinding("http://www.w3.org/ns/prov#", "prov");


	/**
	 * A group of qualified terms for Annotation related SBOL objects
	 *
	 */
	static final class Annotation {
		static final QName Annotation = sbol2.withLocalPart("Annotation");
		static final QName relation   = sbol2.withLocalPart("relation");
		static final QName value	     = sbol2.withLocalPart("value");
	}

	/**
	 * A group of qualified terms for Component related SBOL objects
	 *
	 */
	static final class Component {
		static final QName Component = sbol2.withLocalPart("Component");
	}

	/**
	 * A group of qualified terms for Collection related SBOL objects
	 *
	 */
	static final class Collection {
		static final QName Collection = sbol2.withLocalPart("Collection");
		static final QName hasMembers = sbol2.withLocalPart("member");
		//		  static final QName access 	   = sbol2.withLocalPart("access");
	}

	/**
	 * A group of qualified terms for ComponentDefinition related SBOL objects
	 *
	 */
	static final class ComponentDefinition {
		static final QName ComponentDefinition    = sbol2.withLocalPart("ComponentDefinition");
		static final QName type 	  	 		  = sbol2.withLocalPart("type");
		static final QName roles 	  	 		  = sbol2.withLocalPart("role");
		static final QName hasSequence			  = sbol2.withLocalPart("sequence");
		static final QName hasSequenceAnnotations = sbol2.withLocalPart("sequenceAnnotation");
		static final QName hasSequenceConstraints = sbol2.withLocalPart("sequenceConstraint");
		static final QName hasComponent = sbol2.withLocalPart("component"); 
	}

	/**
	 * A group of qualified terms for ComponentInstance related SBOL objects
	 *
	 */
	static final class ComponentInstance {
		static final QName ComponentInstance 	   = sbol2.withLocalPart("componentInstance");
		static final QName access 				   = sbol2.withLocalPart("access");
		static final QName hasMapsTo 		   	   = sbol2.withLocalPart("mapsTo");
		static final QName hasComponentDefinition   = sbol2.withLocalPart("definition");
	}

	/**
	 * A group of qualified terms for Cut related SBOL objects
	 *
	 */
	static final class Cut {
		static final QName Cut 		  = sbol2.withLocalPart("Cut");
		static final QName at 		  = sbol2.withLocalPart("at");
		static final QName orientation = sbol2.withLocalPart("orientation");
	}

	/**
	 * A group of qualified terms for the SBOL Documented interface
	 *
	 */
	static final class Documented {
		static final QName Documented  = sbol2.withLocalPart("Documented");
		static final QName displayId   = sbol2.withLocalPart("displayId");
		static final QName title 	  = dc.withLocalPart("title");
		static final QName description = dc.withLocalPart("description");
	}

	/**
	 * A group of qualified terms for FunctionalComponent related SBOL objects
	 *
	 */
	static final class FunctionalComponent {
		static final QName FunctionalComponent = sbol2.withLocalPart("FunctionalComponent");
		static final QName direction   	   	  = sbol2.withLocalPart("direction");
	}

	static final class GenericLocation {
		static final QName GenericLocation = sbol2.withLocalPart("GenericLocation");
		static final QName orientation = sbol2.withLocalPart("Orientation");
	}

	/**
	 * A group of qualified terms for Identified related SBOL objects
	 *
	 */
	static final class Identified {
		static final QName Identified 	   	 = sbol2.withLocalPart("Identified");
		static final QName identity   	   	 = sbol2.withLocalPart("identity");
		static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		static final QName version   	 = sbol2.withLocalPart("version");
		static final QName timeStamp   	     = sbol2.withLocalPart("timeStamp");
		static final QName hasAnnotations 	 = sbol2.withLocalPart("annotation");
		static final QName wasDerivedFrom	 = prov.withLocalPart("wasDerivedFrom");
	}

	/**
	 * A group of qualified terms for Interaction related SBOL objects
	 *
	 */
	static final class Interaction {
		static final QName Interaction 	    = sbol2.withLocalPart("Interaction");
		static final QName type 			    = sbol2.withLocalPart("type");
		static final QName hasParticipations = sbol2.withLocalPart("participation");
	}

	/**
	 * A group of qualified terms for Location related SBOL objects
	 *
	 */
	static final class Location {
		static final QName Location = sbol2.withLocalPart("location");
	}

	/**
	 * A group of qualified terms for MapsTo related SBOL objects
	 *
	 */
	static final class MapsTo {
		static final QName MapsTo 	 = sbol2.withLocalPart("MapsTo");
		static final QName refinement = sbol2.withLocalPart("refinement");
		static final QName hasRemote  = sbol2.withLocalPart("remote");
		static final QName hasLocal   = sbol2.withLocalPart("local");
	}

	/**
	 * A group of qualified terms for Model related SBOL objects
	 *
	 */
	static final class Model {
		static final QName Model	    = sbol2.withLocalPart("Model");
		static final QName source	= sbol2.withLocalPart("source");
		static final QName language  = sbol2.withLocalPart("language");
		static final QName framework = sbol2.withLocalPart("framework");
		static final QName roles	    = sbol2.withLocalPart("role");

	}

	/**
	 * A group of qualified terms for ModuleDefinition related SBOL objects
	 *
	 */
	static final class ModuleDefinition {
		static final QName ModuleDefinition 	  = sbol2.withLocalPart("ModuleDefinition");
		static final QName roles			   	  = sbol2.withLocalPart("role");
		static final QName hasModule	       	  = sbol2.withLocalPart("module");
		static final QName hasInteractions  	  = sbol2.withLocalPart("interaction");
		static final QName hasModels 	   		  = sbol2.withLocalPart("model");
		static final QName hasfunctionalComponent = sbol2.withLocalPart("functionalComponent");

	}

	/**
	 * A group of qualified terms for Module related SBOL objects
	 *
	 */
	static final class Module {
		static final QName Module 				= sbol2.withLocalPart("Module");
		static final QName hasMapsTo			    = sbol2.withLocalPart("mapsTo");
		static final QName hasDefinition		    = sbol2.withLocalPart("definition");
		//static final QName hasInstantiatedModule = sbol2.withLocalPart("instantiatedModule");
	}

	/**
	 * A group of qualified terms for multirange related SBOL objects
	 *
	 */
	static final class MultiRange {
		static final QName MultiRange = sbol2.withLocalPart("MultiRange");
		static final QName hasRanges  = sbol2.withLocalPart("range");
	}


	/**
	 * A group of qualified terms for Participation related SBOL objects
	 *
	 */
	static final class Participation {
		static final QName Participation  = sbol2.withLocalPart("Participation");
		static final QName role 		   	 = sbol2.withLocalPart("role");
		static final QName hasParticipant = sbol2.withLocalPart("participant");

	}

	/**
	 * A group of qualified terms for Range related SBOL objects
	 *
	 */
	static final class Range {
		static final QName Range 	  = sbol2.withLocalPart("Range");
		static final QName start 	  = sbol2.withLocalPart("start");
		static final QName end   	  = sbol2.withLocalPart("end");
		static final QName orientation = sbol2.withLocalPart("orientation");
	}

	//
	/**
	 * A group of qualified terms for Sequence related SBOL objects
	 *
	 */
	static final class Sequence {
		static final QName Sequence = sbol2.withLocalPart("Sequence");
		static final QName elements = sbol2.withLocalPart("elements");
		static final QName encoding = sbol2.withLocalPart("encoding");

	}
	//
	/**
	 * A group of qualified terms for SequenceAnnotation related SBOL objects
	 *
	 */
	static final class SequenceAnnotation {
		static final QName SequenceAnnotation = sbol2.withLocalPart("SequenceAnnotation");
		static final QName hasComponent 		 = sbol2.withLocalPart("component");
		static final QName hasLocation 		 = sbol2.withLocalPart("location");
	}

	/**
	 * A group of qualified terms for SequenceConstraint related SBOL objects
	 *
	 */
	static final class SequenceConstraint {
		static final QName SequenceConstraint = sbol2.withLocalPart("SequenceConstraint");
		static final QName restriction 	     = sbol2.withLocalPart("restriction");
		static final QName hasSubject 		 = sbol2.withLocalPart("subject");
		static final QName hasObject 		 = sbol2.withLocalPart("object");
	}

	/**
	 * A group of qualified terms for TopLevel related SBOL objects
	 *
	 */
	static final class GenericTopLevel {
		static final QName GenericTopLevel = sbol2.withLocalPart("GenericTopLevel");
		static final QName rdfType 		  = sbol2.withLocalPart("rdfType");
	}


	static final class SequenceURI {
		static final URI encoding 	  = URI.create(sbol2.getNamespaceURI() + "encoding");
		static final URI DnaSequenceV1 = URI.create("http://dx.doi.org/10.1021/bi00822a023");
	}

//	static final class DnaComponentV1URI {
//		static final URI roles = URI.create("http://purl.obolibrary.org/obo/SO_0000804");
//		static final URI type  = URI.create("http://www.biopax.org/release/biopax-level3.owl#DnaRegion");
//		//static final URI restriction  = URI.create(sbol2.getNamespaceURI() + "precedes");
//	}

	// Moved to ComponentInstance
	//	static final class Access {
	//		static final URI PUBLIC  = URI.create(sbol2.getNamespaceURI() + "public");
	//		static final URI PRIVATE = URI.create(sbol2.getNamespaceURI() + "private");
	//	}

	// Moved to FunctionalComponent
	//	static final class Direction {
	//		static final URI input  = URI.create(sbol2.getNamespaceURI() + "input");
	//		static final URI output = URI.create(sbol2.getNamespaceURI() + "output");
	//		static final URI inout  = URI.create(sbol2.getNamespaceURI() + "inout");
	//		static final URI none   = URI.create(sbol2.getNamespaceURI() + "none");
	//	}

}
