package org.sbolstandard.core2;

import java.net.URI;

/**
 * Represents the SBOL top-level classes.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public abstract class TopLevel extends Identified {

	/**
	 * The abbreviation for the Collection type in URI
	 */
	public static final String COLLECTION = "col";
	/**
	 * The abbreviation for the ModuleDefinition type in URI
	 */
	public static final String MODULE_DEFINITION = "md";
	/**
	 * The abbreviation for the Model type in URI
	 */
	public static final String MODEL = "mod";
	/**
	 * The abbreviation for the ComponentDefinition type in URI
	 */
	public static final String COMPONENT_DEFINITION = "cd";
	/**
	 * The abbreviation for the Sequence type in URI
	 */
	public static final String SEQUENCE = "seq";
	/**
	 * The abbreviation for the GenericTopLevel type in URI
	 */
	public static final String GENERIC_TOP_LEVEL = "gen";
		
	TopLevel(URI identity) throws SBOLValidationException {
		super(identity);
	}

	protected TopLevel(TopLevel toplevel) throws SBOLValidationException {
		super(toplevel);
	}
	
	@Override
	protected abstract Identified deepCopy() throws SBOLValidationException;
	
	/**
	 * Make a copy of a top-level object whose URI and its descendants' URIs (children, grandchildren, etc) are all compliant. 
	 * It first makes a deep copy of this object, then updates its own identity URI and all of its descendants' identity URIs
	 * according to the given {@code URIprefix, displayId}, and {@code version}. This method also updates the {@code displayId}
	 * and {@code version} fields for each updated object.
	 * @return the copied top-level object if this object and all of its descendants have compliant URIs, and {@code null} otherwise.
	 */
	abstract Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException;

	/**
	 * Test if the given object's identity URI is compliant with the form {@code ⟨prefix⟩/(⟨displayId⟩/)}{1,3}⟨version⟩.
	 * The prefix is established by the owner of this object. The number of displayIds can range from 1 to 4, depending on
	 * the level of the given object. 
	 * @param objURI
	 * @throws SBOLValidationException 
	 */
	void isURIcompliant() throws SBOLValidationException {	
		URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
		try {
			URIcompliance.isURIcompliant(this);
		}
		catch (SBOLValidationException e) {
			throw new SBOLValidationException(e.getRule(),this);
		}
		this.checkDescendantsURIcompliance();
	}

	/**
	 * The getDocument method returns the SBOLDocument that was used 
	 * to instantiate this TopLevel object.
	 * 
	 * @return the SBOLDocument of the TopLevel's origin
	 */
	public SBOLDocument getDocument() {
		return this.sbolDocument;
	}

	
	/**
	 * Check if this top-level object's and all of its descendants' URIs are all compliant. 
	 * @throws SBOLValidationException 
	 */
	protected abstract void checkDescendantsURIcompliance() throws SBOLValidationException;

}
