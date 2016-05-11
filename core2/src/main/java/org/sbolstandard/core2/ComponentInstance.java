
package org.sbolstandard.core2;

import java.net.URI;

/**
 * Represents the SBOL ComponentInstance data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public abstract class ComponentInstance extends Identified {

	private AccessType access;
	protected URI definition;
	/**
	 * @param identity
	 * @param access
	 * @param definition
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setAccess(AccessType)}; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setDefinition(URI)}. </li>
	 */
	ComponentInstance(URI identity, AccessType access, URI definition) throws SBOLValidationException {
		super(identity);
		setAccess(access);
		setDefinition(definition);
	}

	/**
	 * @param component
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setAccess(AccessType)}; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setDefinition(URI)}.</li>
	 */
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
	 * @param access Sets the access property of this object to the given one.
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10607
	 */
	public void setAccess(AccessType access) throws SBOLValidationException {
		if (access==null) {
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
	 * @param definition Sets the definition property of this object to the given one
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 10602, 10604.
	 */
	public void setDefinition(URI definition) throws SBOLValidationException {
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
