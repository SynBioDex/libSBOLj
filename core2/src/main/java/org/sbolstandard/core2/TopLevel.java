package org.sbolstandard.core2;

import java.net.URI;

/**
 * Represents a TopLevel object in the SBOL data model.
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
	/**
	 * The abbreviation for the Activity type in URI
	 */
	public static final String ACTIVITY = "act";
	/**
	 * The abbreviation for the Agent type in URI
	 */
	public static final String AGENT = "agent";
	/**
	 * The abbreviation for the Plan type in URI
	 */
	public static final String PLAN = "plan";
	/**
	 * The abbreviation for the CombinatorialDerivation type in URI
	 */
	public static final String COMBINATORIAL_DERIVATION = "comb";
	
	/**
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link Identified#Identified(URI)}.
	 */
	TopLevel(URI identity) throws SBOLValidationException {
		super(identity);
	}

	/**
	 * @param toplevel
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link Identified#Identified(Identified)}.
	 */
	TopLevel(TopLevel toplevel) throws SBOLValidationException {
		super(toplevel);
	}
	
	void copy(TopLevel topLevel) throws SBOLValidationException {
		((Identified)this).copy((Identified)topLevel);
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.Identified#deepCopy()
	 */
	@Override
	abstract Identified deepCopy() throws SBOLValidationException;
	
	/**
	 * Make a copy of a top-level object whose URI and its descendants' URIs (children, grandchildren, etc) are all compliant. 
	 * It first makes a deep copy of this object, then updates its own identity URI and all of its descendants' identity URIs
	 * according to the given {@code URIprefix, displayId}, and {@code version}. This method also updates the {@code displayId}
	 * and {@code version} fields for each updated object.
	 * @return the copied top-level object if this object and all of its descendants have compliant URIs, and {@code null} otherwise.
	 */
	abstract Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException;

	/**
	 * Test if the given object's identity URI is compliant.
	 * 
	 * @param objURI
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following methods:
	 * <ul>
	 * <li>{@link URIcompliance#isTopLevelURIformCompliant(URI)},</li> 
	 * <li>{@link URIcompliance#isURIcompliant(Identified)}, or</li>
	 * <li>{@link #checkDescendantsURIcompliance()}.</li>
	 * </ul>
	 */
	void isURIcompliant() throws SBOLValidationException {	
		//URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
		try {
			URIcompliance.isURIcompliant(this);
		}
		catch (SBOLValidationException e) {
			throw new SBOLValidationException(e.getRule(),this);
		}
		this.checkDescendantsURIcompliance();
	}

	/**
	 * Returns the SBOL document that hosts this top-level.
	 * 
	 * @return the SBOL document that hosts this top-level
	 */
	public SBOLDocument getDocument() {
		//return this.sbolDocument;
		return this.getSBOLDocument();	
	}

	
	/**
	 * Check if this top-level object's and all of its descendants' URIs are all compliant. 
	 * @throws SBOLValidationException 
	 */
	abstract void checkDescendantsURIcompliance() throws SBOLValidationException;

}

