package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

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
	 * The abbreviation for the Implementation type in URI
	 */
	public static final String IMPLEMENTATION = "imp";
	/**
	 * The abbreviation for the Attachment type in URI
	 */
	public static final String ATTACHMENT = "atch";

	private HashSet<URI> attachments;

	/**
	 * @param identity
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in
	 *             {@link Identified#Identified(URI)}.
	 */
	TopLevel(URI identity) throws SBOLValidationException {
		super(identity);
	}

	/**
	 * @param toplevel
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in
	 *             {@link Identified#Identified(Identified)}.
	 */
	TopLevel(TopLevel toplevel) throws SBOLValidationException {
		super(toplevel);
	}

	void copy(TopLevel topLevel) throws SBOLValidationException {
		((Identified) this).copy((Identified) topLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbolstandard.core2.Identified#deepCopy()
	 */
	@Override
	abstract Identified deepCopy() throws SBOLValidationException;

	/**
	 * Make a copy of a top-level object whose URI and its descendants' URIs
	 * (children, grandchildren, etc) are all compliant. It first makes a deep copy
	 * of this object, then updates its own identity URI and all of its descendants'
	 * identity URIs according to the given {@code URIprefix, displayId}, and
	 * {@code version}. This method also updates the {@code displayId} and
	 * {@code version} fields for each updated object.
	 * 
	 * @return the copied top-level object if this object and all of its descendants
	 *         have compliant URIs, and {@code null} otherwise.
	 */
	abstract Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException;

	/**
	 * Test if the given object's identity URI is compliant.
	 * 
	 * @param objURI
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following methods:
	 *             <ul>
	 *             <li>{@link URIcompliance#isTopLevelURIformCompliant(URI)},</li>
	 *             <li>{@link URIcompliance#isURIcompliant(Identified)}, or</li>
	 *             <li>{@link #checkDescendantsURIcompliance()}.</li>
	 *             </ul>
	 */
	void isURIcompliant() throws SBOLValidationException {
		// URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
		try {
			URIcompliance.isURIcompliant(this);
		} catch (SBOLValidationException e) {
			throw new SBOLValidationException(e.getRule(), this);
		}
		this.checkDescendantsURIcompliance();
	}

	/**
	 * Returns the SBOL document that hosts this top-level.
	 * 
	 * @return the SBOL document that hosts this top-level
	 */
	public SBOLDocument getDocument() {
		// return this.sbolDocument;
		return this.getSBOLDocument();
	}

	/**
	 * Check if this top-level object's and all of its descendants' URIs are all
	 * compliant.
	 * 
	 * @throws SBOLValidationException
	 *             validation error
	 */
	abstract void checkDescendantsURIcompliance() throws SBOLValidationException;

	/**
	 * Adds the given attachment to the list of attachments.
	 * 
	 * @param attachment
	 * 
	 * @throws SBOLValidationException 
	 *              if the following SBOL validation rule was violated: XXXXX
	 */
	private void addAttachment(URI attachment) throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (getAttachment(attachment) == null) {
				throw new SBOLValidationException("sbol-XXXXX", this);
			}
		}
		
		attachments.add(attachment);
	}

	/**
	 * Returns the attachment matching the given URI.
	 *
	 * @param attachmentURI
	 *            the identity URI of the attachment to be retrieved
	 * @return the matching attachment if present, or {@code null} otherwise.
	 */
	public Attachment getAttachment(URI attachmentURI) {
		return this.getSBOLDocument().getAttachment(attachmentURI);
	}

	/**
	 * Returns the set of attachments owned by this top level.
	 *
	 * @return the set of attachments owned by this top level.
	 */
	public Set<URI> getAttachments() {
		Set<URI> attachments = new HashSet<>(this.attachments);
		return attachments;
	}

	/**
	 * Removes all entries of this top level's list of attachments. The list will be
	 * empty after this call returns.
	 * <p>
	 * This method calls {@link #removeAttachment(URI attachmentURI)} to iteratively
	 * remove each attachment.
	 */
	public void clearAttachments() {
		Object[] valueSetArray = attachments.toArray();
		for (Object attachment : valueSetArray) {
			removeAttachmentURI((URI) attachment);
		}
	}
	
	/**
	 * Removes the given attachment from the list of attachments.
	 *
	 * @param attachment
	 *            an attachment uri be removed
	 * @return {@code true} if the matching attachment is removed
	 *         successfully, {@code false} otherwise.
	 */
	public boolean removeAttachmentURI(URI attachment) {
		return attachments.remove(attachment);
	}

	/**
	 * Clears the existing set of attachments first, and then adds the given set of
	 * the attachments to this top level.
	 *
	 * @param attachments
	 *            The set of attachments for this top level.
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following methods:
	 *             <ul>
	 *             <li>{@link #clearAttachments()} or</li>
	 *             <li>{@link #addAttachment(Attachment)}</li>
	 *             </ul>
	 */
	public void setAttachments(Set<URI> attachments) throws SBOLValidationException {
		clearAttachments();
		for (URI attachment : attachments) {
			addAttachment(attachment);
		}
	}

}
