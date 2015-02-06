package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
/**
 * Provides qualified names for sbol1.0 objects.
 *
 */
public class Sbol1Terms
{

	/**
	 * The namespacebinding for SBOL1.0
	 */
	public static final NamespaceBinding sbol1 = NamespaceBinding("http://sbols.org/v1#", "sbol");

	/**
	 * A group of qualified terms for Collection related SBOL objects
	 *
	 */
	public static final class Collection {
		public static final QName Collection  = sbol1.withLocalPart("Collection");
		public static final QName uri   	  = sbol1.withLocalPart("uri");
		public static final QName displayId   = sbol1.withLocalPart("displayId");
		public static final QName name	      = sbol1.withLocalPart("name");
		public static final QName description = sbol1.withLocalPart("description");
		public static final QName component  = sbol1.withLocalPart("component");
	}

	/**
	 * A group of qualified terms for DNAComponent related SBOL objects
	 *
	 */
	public static final class DNAComponent {
		public static final QName DNAComponent = sbol1.withLocalPart("DnaComponent");
		public static final QName uri   	   = sbol1.withLocalPart("uri");
		public static final QName displayId    = sbol1.withLocalPart("displayId");
		public static final QName name	   	   = sbol1.withLocalPart("name");
		public static final QName description  = sbol1.withLocalPart("description");
		public static final QName type   	   = sbol1.withLocalPart("type");
		public static final QName annotations  = sbol1.withLocalPart("annotation");
		public static final QName dnaSequence  = sbol1.withLocalPart("dnaSequence");

	}

	/**
	 * A group of qualified terms for DNASequence related SBOL objects
	 *
	 */
	public static final class DNASequence {
		public static final QName DNASequence = sbol1.withLocalPart("DnaSequence");
		public static final QName uri   	  = sbol1.withLocalPart("uri");
		public static final QName nucleotides = sbol1.withLocalPart("nucleotides");
	}

	/**
	 * A group of qualified terms for SequenceAnnotations related SBOL objects
	 *
	 */
	public static final class SequenceAnnotations {
		public static final QName SequenceAnnotation  = sbol1.withLocalPart("SequenceAnnotation");
		public static final QName uri   	   		  = sbol1.withLocalPart("uri");
		public static final QName bioStart	   		  = sbol1.withLocalPart("bioStart");
		public static final QName bioEnd	   		  = sbol1.withLocalPart("bioEnd");
		public static final QName strand	   		  = sbol1.withLocalPart("strand");
		public static final QName subComponent 		  = sbol1.withLocalPart("subComponent");
		public static final QName precedes	   		  = sbol1.withLocalPart("precedes");
	}


}