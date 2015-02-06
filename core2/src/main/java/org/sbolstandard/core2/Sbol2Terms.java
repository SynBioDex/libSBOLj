package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import java.net.URI;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * Provides qualified names for SBOL2.0 objects.
 *
 */
public class Sbol2Terms {
	/**
	 * The namespace binding for SBOL2.0
	 */
	public static final NamespaceBinding sbol2 = NamespaceBinding(
			"http://sbols.org/v2#", "sbol");

	/**
	 * A group of qualified terms for Annotation related SBOL objects
	 *
	 */
	public static final class Annotation {
		public static final QName Annotation = sbol2
				.withLocalPart("Annotation");
		public static final QName relation = sbol2.withLocalPart("relation");
		public static final QName value = sbol2.withLocalPart("value");
	}

	/**
	 * A group of qualified terms for Component related SBOL objects
	 *
	 */
	public static final class Component {
		public static final QName Component = sbol2.withLocalPart("Component");
	}

	/**
	 * A group of qualified terms for Collection related SBOL objects
	 *
	 */
	public static final class Collection {
		public static final QName Collection = sbol2
				.withLocalPart("Collection");
		public static final QName hasMembers = sbol2.withLocalPart("member");
		// public static final QName access = sbol2.withLocalPart("access");
	}

	/**
	 * A group of qualified terms for ComponentDefinition related SBOL objects
	 *
	 */
	public static final class ComponentDefinition {
		public static final QName ComponentDefinition = sbol2
				.withLocalPart("ComponentDefinition");
		public static final QName type = sbol2.withLocalPart("type");
		public static final QName roles = sbol2.withLocalPart("role");
		public static final QName hasSequence = sbol2.withLocalPart("sequence");
		public static final QName hasSequenceAnnotations = sbol2
				.withLocalPart("sequenceAnnotation");
		public static final QName hasSequenceConstraints = sbol2
				.withLocalPart("sequenceConstraint");
		public static final QName hasSubComponents = sbol2
				.withLocalPart("subComponent");
	}

	/**
	 * A group of qualified terms for ComponentInstance related SBOL objects
	 *
	 */
	public static final class ComponentInstance {
		public static final QName ComponentInstance = sbol2
				.withLocalPart("componentInstance");
		public static final QName access = sbol2.withLocalPart("access");
		// TODO: double check on hasInstantiatedComponent
		public static final QName hasMappings = sbol2.withLocalPart("Mapping");
		public static final QName hasComponentDefinition = sbol2
				.withLocalPart("definition");
	}

	/**
	 * A group of qualified terms for Cut related SBOL objects
	 *
	 */
	public static final class Cut {
		public static final QName Cut = sbol2.withLocalPart("Cut");
		public static final QName at = sbol2.withLocalPart("at");
		public static final QName orientation = sbol2
				.withLocalPart("orientation");
	}

	/**
	 * A group of qualified terms for the SBOL Documented interface
	 *
	 */
	public static final class Documented {
		public static final QName Documented = sbol2
				.withLocalPart("Documented");
		public static final QName displayId = sbol2.withLocalPart("displayId");
		public static final QName name = sbol2.withLocalPart("name");
		public static final QName description = sbol2
				.withLocalPart("description");
	}

	/**
	 * A group of qualified terms for FunctionalComponent related SBOL objects
	 *
	 */
	public static final class FunctionalComponent {
		public static final QName FunctionalComponent = sbol2
				.withLocalPart("FunctionalComponent");
		public static final QName direction = sbol2.withLocalPart("direction");
	}

	/**
	 * A group of qualified terms for Identified related SBOL objects
	 *
	 */
	public static final class Identified {
		public static final QName Identified = sbol2
				.withLocalPart("Identified");
		public static final QName identity = sbol2.withLocalPart("identity");
		public static final QName persistentIdentity = sbol2
				.withLocalPart("persistentIdentity");
		public static final QName version = sbol2.withLocalPart("version");
		public static final QName timeStamp = sbol2.withLocalPart("timeStamp");
		public static final QName hasAnnotations = sbol2
				.withLocalPart("annotation");
	}

	/**
	 * A group of qualified terms for Interaction related SBOL objects
	 *
	 */
	public static final class Interaction {
		public static final QName Interaction = sbol2
				.withLocalPart("Interaction");
		public static final QName type = sbol2.withLocalPart("type");
		public static final QName hasParticipations = sbol2
				.withLocalPart("participation");
	}

	/**
	 * A group of qualified terms for Location related SBOL objects
	 *
	 */
	public static final class Location {
		public static final QName Location = sbol2.withLocalPart("location");
	}

	/**
	 * A group of qualified terms for MapsTo related SBOL objects
	 *
	 */
	public static final class MapsTo {
		public static final QName MapsTo = sbol2.withLocalPart("MapsTo");
		public static final QName refinement = sbol2
				.withLocalPart("refinement");
		public static final QName hasRemote = sbol2.withLocalPart("remote");
		public static final QName hasLocal = sbol2.withLocalPart("local");
	}

	/**
	 * A group of qualified terms for Model related SBOL objects
	 *
	 */
	public static final class Model {
		public static final QName Model = sbol2.withLocalPart("Model");
		public static final QName source = sbol2.withLocalPart("source");
		public static final QName language = sbol2.withLocalPart("language");
		public static final QName framework = sbol2.withLocalPart("framework");
		public static final QName roles = sbol2.withLocalPart("role");

	}

	/**
	 * A group of qualified terms for ModuleDefinition related SBOL objects
	 *
	 */
	public static final class ModuleDefinition {
		public static final QName ModuleDefinition = sbol2
				.withLocalPart("ModuleDefinition");
		public static final QName roles = sbol2.withLocalPart("role");
		public static final QName hasSubModule = sbol2
				.withLocalPart("subModule");
		public static final QName hasInteractions = sbol2
				.withLocalPart("interaction");
		public static final QName hasModels = sbol2.withLocalPart("model");
		public static final QName hasComponents = sbol2
				.withLocalPart("component");

	}

	/**
	 * A group of qualified terms for Module related SBOL objects
	 *
	 */
	public static final class Module {
		public static final QName Module = sbol2.withLocalPart("Module");
		public static final QName hasMappings = sbol2.withLocalPart("mapping");
		public static final QName hasDefinition = sbol2
				.withLocalPart("definition");
		// TODO: double check hasInstantiatedModule
		public static final QName hasInstantiatedModule = sbol2
				.withLocalPart("instantiatedModule");
	}

	/**
	 * A group of qualified terms for multirange related SBOL objects
	 *
	 */
	public static final class MultiRange {
		public static final QName MultiRange = sbol2
				.withLocalPart("MultiRange");
		public static final QName hasRanges = sbol2.withLocalPart("range");
	}

	/**
	 * A group of qualified terms for Participation related SBOL objects
	 *
	 */
	public static final class Participation {
		public static final QName Participation = sbol2
				.withLocalPart("Participation");
		public static final QName role = sbol2.withLocalPart("role");
		public static final QName hasParticipant = sbol2
				.withLocalPart("participant");

	}

	/**
	 * A group of qualified terms for Range related SBOL objects
	 *
	 */
	public static final class Range {
		public static final QName Range = sbol2.withLocalPart("Range");
		public static final QName start = sbol2.withLocalPart("start");
		public static final QName end = sbol2.withLocalPart("end");
		public static final QName orientation = sbol2
				.withLocalPart("orientation");
	}

	//
	/**
	 * A group of qualified terms for Sequence related SBOL objects
	 *
	 */
	public static final class Sequence {
		public static final QName Sequence = sbol2.withLocalPart("Sequence");
		public static final QName elements = sbol2.withLocalPart("elements");
		public static final QName encoding = sbol2.withLocalPart("encoding");

	}

	//
	/**
	 * A group of qualified terms for SequenceAnnotation related SBOL objects
	 *
	 */
	public static final class SequenceAnnotation {
		public static final QName SequenceAnnotation = sbol2
				.withLocalPart("SequenceAnnotation");
		public static final QName hasComponent = sbol2
				.withLocalPart("Component");
		public static final QName hasLocation = sbol2.withLocalPart("location");
	}

	/**
	 * A group of qualified terms for SequenceConstraint related SBOL objects
	 *
	 */
	public static final class SequenceConstraint {
		public static final QName SequenceConstraint = sbol2
				.withLocalPart("SequenceConstraint");
		public static final QName restriction = sbol2
				.withLocalPart("restriction");
		public static final QName hasSubject = sbol2.withLocalPart("subject");
		public static final QName hasObject = sbol2.withLocalPart("object");
	}

	/**
	 * A group of qualified terms for TopLevel related SBOL objects
	 *
	 */
	public static final class GenericTopLevel {
		public static final QName GenericTopLevel = sbol2
				.withLocalPart("GenericTopLevel");
		public static final QName rdfType = sbol2.withLocalPart("rdfType");
	}

	public static final class SequenceURI {
		public static final URI encoding = URI.create(sbol2.getNamespaceURI()
				+ "encoding");
		public static final URI DnaSequenceV1 = URI
				.create("http://dx.doi.org/10.1021/bi00822a023");
	}

	public static final class DnaComponentV1URI {
		public static final URI roles = URI
				.create("http://purl.obolibrary.org/obo/SO_0000804");
		public static final URI type = URI
				.create("http://purl.obolibrary.org/obo/CHEBI_16991");
	}

	public static final class Access {
		public static final URI PUBLIC = URI.create(sbol2.getNamespaceURI()
				+ "public");
		public static final URI PRIVATE = URI.create(sbol2.getNamespaceURI()
				+ "private");
	}

	public static final class Direction {
		public static final URI input = URI.create(sbol2.getNamespaceURI()
				+ "input");
		public static final URI output = URI.create(sbol2.getNamespaceURI()
				+ "output");
		public static final URI inout = URI.create(sbol2.getNamespaceURI()
				+ "inout");
		public static final URI none = URI.create(sbol2.getNamespaceURI()
				+ "none");
	}

	public static final class Orientation {
		public static final URI inline = URI.create(sbol2.getNamespaceURI()
				+ "inline");
		public static final URI reverseComplement = URI.create(sbol2
				.getNamespaceURI() + "reverseComplement");
	}

}