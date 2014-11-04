package org.sbolstandard.core2;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;
/**
 * Provides qualified names for SBOL2.0 objects.
 *
 */
public class Sbol2Terms
{	
	/**
	 * The namespace binding for SBOL2.0 
	 */
	  public static final NamespaceBinding sbol2 = NamespaceBinding("http://sbols.org/v2#", "sbol2");
	  
	  /**
	   * A group of qualified terms for annotation related SBOL objects
	   *
	   */
	  public static final class Annotation {
		  public static final QName Annotation = sbol2.withLocalPart("Annotation");
		  public static final QName relation   = sbol2.withLocalPart("relation"); 
		  public static final QName value	   = sbol2.withLocalPart("value");  
	  }
	  
	  /**
	   * A group of qualified terms for collection related SBOL objects
	   *
	   */
	  public static final class Collection {
		  public static final QName Collection		   = sbol2.withLocalPart("Collection");
		  public static final QName hasMembers		   = sbol2.withLocalPart("members"); 
//		  public static final QName access 			   = sbol2.withLocalPart("access"); 
		  public static final QName displayId 	   	   = Documented.displayId;  
		  public static final QName name 			   = Documented.name;
		  public static final QName description 	   = Documented.description;
		  public static final QName identity 		   = Identified.identity;  
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 	   	   = Identified.version;
		  public static final QName timeStamp 	   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for component related SBOL objects
	   *
	   */
	  public static final class Component {
		  public static final QName Component 	 				= sbol2.withLocalPart("Component");
		  public static final QName types 	  	 				= sbol2.withLocalPart("types");
		  public static final QName roles 	  	 				= sbol2.withLocalPart("roles");
		  public static final QName hasStructure 				= sbol2.withLocalPart("structure");
		  public static final QName hasStructuralAnnotations 	= sbol2.withLocalPart("structuralAnnotations");
		  public static final QName hasStructuralConstraints 	= sbol2.withLocalPart("structuralConstraints");
		  public static final QName hasStructuralInstantiations = sbol2.withLocalPart("structuralInstantiations");
		  public static final QName displayId 		   			= Documented.displayId;  
		  public static final QName name 			   			= Documented.name;
		  public static final QName description 	   			= Documented.description;
		  public static final QName identity 					= Identified.identity; 
		  public static final QName persistentIdentity 			= Identified.persistentIdentity;
		  public static final QName version 	   				= Identified.version;
		  public static final QName timeStamp 	   				= Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for component instantiation related SBOL objects
	   *
	   */
	  public static final class ComponentInstantiation {
		  public static final QName ComponentInstantiation 	 = sbol2.withLocalPart("ComponentInstantiation");
		  public static final QName access 					 = sbol2.withLocalPart("access");  
		  public static final QName hasInstantiatedComponent = sbol2.withLocalPart("instantiatedComponent");
		  public static final QName hasReferences 			 = sbol2.withLocalPart("references"); 
		  public static final QName displayId 		   		 = Documented.displayId;  
		  public static final QName name 			   		 = Documented.name;
		  public static final QName description 	   		 = Documented.description;
		  public static final QName identity 		   		 = Identified.identity; 
		  public static final QName persistentIdentity 		 = Identified.persistentIdentity;
		  public static final QName version 		   		 = Identified.version;
		  public static final QName timeStamp 		   		 = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for cut related SBOL objects
	   *
	   */
	  public static final class Cut {
		  public static final QName Cut = sbol2.withLocalPart("Cut");
		  public static final QName at 	= sbol2.withLocalPart("at");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for the SBOL documented interface
	   *
	   */
	  public static final class Documented {
		  public static final QName Documented 		   = sbol2.withLocalPart("Documented");		  
		  public static final QName displayId 		   = sbol2.withLocalPart("displayId");
		  public static final QName name 			   = sbol2.withLocalPart("name");
		  public static final QName description 	   = sbol2.withLocalPart("description");
		  public static final QName identity 		   = Identified.identity;
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 		   = Identified.version;
		  public static final QName timeStamp 		   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for functional instantiation related SBOL objects
	   *
	   */
	  public static final class FunctionalInstantiation {
		  public static final QName FunctionalInstantiation = sbol2.withLocalPart("FunctionalInstantiation");
		  public static final QName direction 				= sbol2.withLocalPart("direction");
		  public static final QName access 					= sbol2.withLocalPart("access"); 
		  public static final QName displayId 		   		= Documented.displayId;  
		  public static final QName name 			   		= Documented.name;
		  public static final QName description 	   		= Documented.description;
		  public static final QName identity 		   		= Identified.identity; 
		  public static final QName persistentIdentity 		= Identified.persistentIdentity;
		  public static final QName version 	   			= Identified.version;
		  public static final QName timeStamp 	   			= Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for indentified related SBOL objects
	   *
	   */
	  public static final class Identified {
		  public static final QName Identified 	   	   = sbol2.withLocalPart("Identified"); 
		  public static final QName identity   	   	   = sbol2.withLocalPart("identity"); 
//		  public static final QName hasAnnotations 	   = sbol2.withLocalPart("annotations");
		  public static final QName persistentIdentity = sbol2.withLocalPart("persistentIdentity");
		  public static final QName version   	  	   = sbol2.withLocalPart("version");
		  public static final QName timeStamp   	   = sbol2.withLocalPart("timeStamp"); 
	  }
	  
	  /**
	   * A group of qualified terms for interaction related SBOL objects
	   *
	   */
	  public static final class Interaction {
		  public static final QName Interaction 	   = sbol2.withLocalPart("Interaction");
		  public static final QName type 			   = sbol2.withLocalPart("type");
		  public static final QName hasParticipations  = sbol2.withLocalPart("participations");
		  public static final QName displayId 		   = Documented.displayId;  
		  public static final QName name 			   = Documented.name;
		  public static final QName description 	   = Documented.description;
		  public static final QName identity 		   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 		   = Identified.version;
		  public static final QName timeStamp 		   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for location related SBOL objects
	   *
	   */
	  public static final class Location {
		  public static final QName Location   		   = sbol2.withLocalPart("Location");
		  public static final QName identity   	   	   = Identified.identity;
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for model related SBOL objects
	   *
	   */
	  public static final class Model {
		  public static final QName Model			   = sbol2.withLocalPart("Model");
		  public static final QName source			   = sbol2.withLocalPart("source");
		  public static final QName language 		   = sbol2.withLocalPart("language");
		  public static final QName framework 		   = sbol2.withLocalPart("framework");
		  public static final QName roles			   = sbol2.withLocalPart("roles");
		  public static final QName displayId 		   = Documented.displayId;  
		  public static final QName name 			   = Documented.name;
		  public static final QName description        = Documented.description;
		  public static final QName identity 		   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 	       = Identified.version;
		  public static final QName timeStamp 	       = Identified.timeStamp;
		
	  }
	  
	  /**
	   * A group of qualified terms for module related SBOL objects
	   *
	   */
	  public static final class Module {
		  public static final QName Module						= sbol2.withLocalPart("Module");
		  public static final QName roles						= sbol2.withLocalPart("roles");
		  public static final QName hasModuleInstantiations		= sbol2.withLocalPart("moduleInstantiation");
		  public static final QName hasInteractions 			= sbol2.withLocalPart("interaction");
		  public static final QName hasModels 					= sbol2.withLocalPart("model");
		  public static final QName hasfunctionalInstantiations = sbol2.withLocalPart("functionalInstantiation");
		  public static final QName displayId 		   			= Documented.displayId;  
		  public static final QName name 			   			= Documented.name;
		  public static final QName description 	   			= Documented.description;
		  public static final QName identity 					= Identified.identity; 
		  public static final QName persistentIdentity 			= Identified.persistentIdentity;
		  public static final QName version 	   				= Identified.version;
		  public static final QName timeStamp 	   				= Identified.timeStamp;
		  
	  }
	  
	  /**
	   * A group of qualified terms for module instantiation related SBOL objects
	   *
	   */
	  public static final class ModuleInstantiation {
		  public static final QName ModuleInstantiation 	= sbol2.withLocalPart("ModuleInstantiation");
		  public static final QName hasReferences			= sbol2.withLocalPart("reference");
		  public static final QName hasInstantiatedModule	= sbol2.withLocalPart("instantiatedModule");
		  public static final QName displayId 		   		= Documented.displayId;  
		  public static final QName name 			   		= Documented.name;
		  public static final QName description 	   		= Documented.description; 
		  public static final QName identity 		   		= Identified.identity; 
		  public static final QName persistentIdentity 		= Identified.persistentIdentity;
		  public static final QName version 		   		= Identified.version;
		  public static final QName timeStamp 		   		= Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for moeity related SBOL objects
	   *
	   */
	  public static final class Moeity {
		  public static final QName Moeity 			   = sbol2.withLocalPart("Moeity");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for multirange related SBOL objects
	   *
	   */
	  public static final class MultiRange {
		  public static final QName MultiRange 		   = sbol2.withLocalPart("MultiRange");
		  public static final QName hasRanges 		   = sbol2.withLocalPart("ranges");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for oriented cut related SBOL objects
	   *
	   */
	  public static final class OrientedCut {
		  public static final QName OrientedCut 	   = sbol2.withLocalPart("OrientedCut");
		  public static final QName orientation 	   = sbol2.withLocalPart("orientation");
		  public static final QName at				   = sbol2.withLocalPart("at");
		  public static final QName Integer 		   = sbol2.withLocalPart("Integer");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for oriented range related SBOL objects
	   *
	   */
	  public static final class OrientedRange {
		  public static final QName OrientedRange 	   = sbol2.withLocalPart("OrientedRange");
		  public static final QName orientation   	   = sbol2.withLocalPart("orientation");
		  public static final QName start 			   = sbol2.withLocalPart("start");
		  public static final QName end   			   = sbol2.withLocalPart("end");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for participation related SBOL objects
	   *
	   */
	  public static final class Participation {
		  public static final QName Participation  	   = sbol2.withLocalPart("Participation");
		  public static final QName role 		   	   = sbol2.withLocalPart("role");
		  public static final QName hasParticipant 	   = sbol2.withLocalPart("participant");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
		  
	  }
	  
	  /**
	   * A group of qualified terms for range related SBOL objects
	   *
	   */
	  public static final class Range {
		  public static final QName Range 			   = sbol2.withLocalPart("Range");
		  public static final QName start 			   = sbol2.withLocalPart("start");
		  public static final QName end   			   = sbol2.withLocalPart("end");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for refersTo related SBOL objects
	   *
	   */
	  public static final class RefersTo {
		  public static final QName RefersTo 		   = sbol2.withLocalPart("RefersTo");
		  public static final QName refinement 		   = sbol2.withLocalPart("refinement");
		  public static final QName hasRemote 		   = sbol2.withLocalPart("remote"); 
		  public static final QName hasLocal 		   = sbol2.withLocalPart("local");
		  public static final QName identity   	   	   = Identified.identity; 
		  public static final QName persistentIdentity = Identified.persistentIdentity; 
		  public static final QName version   	  	   = Identified.version; 
		  public static final QName timeStamp   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for structure related SBOL objects
	   *
	   */
	  public static final class Structure {
		  public static final QName Structure 		   = sbol2.withLocalPart("Structure"); 
		  public static final QName elements  		   = sbol2.withLocalPart("elements"); 
		  public static final QName encoding  		   = sbol2.withLocalPart("encoding"); 
		  public static final QName displayId 		   = Documented.displayId;  
		  public static final QName name 			   = Documented.name;
		  public static final QName description 	   = Documented.description;
		  public static final QName identity 		   = Identified.identity;
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 	   	   = Identified.version;
		  public static final QName timeStamp 	   	   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for structural annotation related SBOL objects
	   *
	   */
	  public static final class StructuralAnnotation {
		  public static final QName StructuralAnnotation 	   = sbol2.withLocalPart("StructuralAnnotation");
		  public static final QName hasStructuralInstantiation = sbol2.withLocalPart("structuralInstantiation"); 
		  public static final QName hasLocation 			   = sbol2.withLocalPart("location"); 
		  public static final QName displayId 		   		   = Documented.displayId;  
		  public static final QName name 			   		   = Documented.name;
		  public static final QName description 	   		   = Documented.description;
		  public static final QName identity 		   		   = Identified.identity; 
		  public static final QName persistentIdentity 		   = Identified.persistentIdentity;
		  public static final QName version 		   		   = Identified.version;
		  public static final QName timeStamp 		   		   = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for structural constraint related SBOL objects
	   *
	   */
	  public static final class StructuralConstraint {
		  public static final QName StructuralConstraint = sbol2.withLocalPart("StructuralConstraint"); 
		  public static final QName restriction 		 = sbol2.withLocalPart("restriction"); 
		  public static final QName hasSubject 			 = sbol2.withLocalPart("subject"); 
		  public static final QName hasObject 			 = sbol2.withLocalPart("object"); 
		  public static final QName identity   	   	     = Identified.identity; 
		  public static final QName persistentIdentity   = Identified.persistentIdentity; 
		  public static final QName version   	  	     = Identified.version; 
		  public static final QName timeStamp   	     = Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for structural instantiation related SBOL objects
	   *
	   */
	  public static final class StructuralInstantiation {
		  public static final QName StructuralInstantiation = sbol2.withLocalPart("StructuralInstantiation"); 
		  public static final QName access 					= sbol2.withLocalPart("access"); 
		  public static final QName displayId 		   		= Documented.displayId;   
		  public static final QName name 			   		= Documented.name;
		  public static final QName description 	   		= Documented.description;
		  public static final QName identity 		   		= Identified.identity; 
		  public static final QName persistentIdentity 		= Identified.persistentIdentity;
		  public static final QName version 	   			= Identified.version;
		  public static final QName timeStamp 	   			= Identified.timeStamp;
	  }
	  
	  /**
	   * A group of qualified terms for top level related SBOL objects
	   *
	   */
	  public static final class TopLevel {
		  public static final QName TopLevel 		   = sbol2.withLocalPart("TopLevel"); 
		  public static final QName displayId 		   = Documented.displayId; 
		  public static final QName name 			   = Documented.name;
		  public static final QName description 	   = Documented.description;
		  public static final QName identity 		   = Identified.identity;  
		  public static final QName persistentIdentity = Identified.persistentIdentity;
		  public static final QName version 	   	   = Identified.version;
		  public static final QName timeStamp 	   	   = Identified.timeStamp;
	  }
	  
}