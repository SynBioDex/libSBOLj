
package org.sbolstandard.core2;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public abstract class ComponentInstance extends Identified {

	private AccessType access;
	protected URI definition;
	ComponentInstance(URI identity, AccessType access, URI definition) throws SBOLValidationException {
		super(identity);
		setAccess(access);
		setDefinition(definition);
	}

	protected ComponentInstance(ComponentInstance component) throws SBOLValidationException {
		super(component);
		setAccess(component.getAccess());
		setDefinition(component.getDefinitionURI());
	}

	/**
	 * Returns the access property of this object.
	 *
	 * @return the access property of this object
	 */
	public AccessType getAccess() {
		return access;
	}

	/**
	 * Sets the access property of this object to the given one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param access
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if the given {@code access} argument is {@code null}
	 */
	public void setAccess(AccessType access) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (access==null) {
			//throw new SBOLValidationException("Not a valid access type.");
			throw new SBOLValidationException("sbol-10607", this);
		}
		this.access = access;
	}

	/**
	 * Returns the reference ComponentDefinition URI.
	 *
	 * @return the reference ComponentDefinition URI
	 */
	public URI getDefinitionURI() {
		return definition;
	}

	/**
	 * Returns the ComponentDefinition instance referenced by this object.
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null},
	 * the ComponentDefinition instance referenced by this object otherwise.
	 */
	public ComponentDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getComponentDefinition(definition);
	}

	/**
	 * Sets the definition property of this object to the given one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param definition
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if the given {@code definition} argument is {@code null}
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely specifies
	 * 		all URIs and the given definition URI is not found in them.
	 *
	 */
	public void setDefinition(URI definition) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (definition==null) {
			throw new SBOLValidationException("sbol-10602",this);
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(definition)==null) {
				throw new SBOLValidationException("sbol-10604",this);
			}
		}
		this.definition = definition;
	}

	@Override
	protected abstract ComponentInstance deepCopy() throws SBOLValidationException;

	@Override
	public String toString() {
		return "ComponentInstance [access=" + access + ", definition=" + definition + ", identity="
				+ identity + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
	}

}
