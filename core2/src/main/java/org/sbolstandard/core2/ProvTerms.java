package org.sbolstandard.core2;

import static uk.ac.ncl.intbio.core.datatree.Datatree.NamespaceBinding;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;

/**
 * @author Tramy Nguyen
 *
 * Provides qualified names for prov objects.
 *
 */
public class ProvTerms
{
	/**
	 * The namespace binding for prov
	 */
	public static final NamespaceBinding prov = NamespaceBinding("http://www.w3.org/ns/prov", "prov");

	/**
	 * A qualified term for wasDerivedFrom related prov object
	 *
	 */
	public static final class Prov {
		public static final QName wasDerivedFrom   = prov.withLocalPart("wasDerivedFrom");
	}

}
