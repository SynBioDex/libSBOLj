
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
	 * <ul>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setAccess(AccessType)}; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setDefinition(URI)}. </li>
	 * </ul>
	 */
	ComponentInstance(URI identity, AccessType access, URI definition) throws SBOLValidationException {
		super(identity);
		setAccess(access);
		setDefinition(definition);
	}

	/**
	 * @param component
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <ul>
	 * <li>an SBOL validation rule violation occurred in {@link Identified#Identified(URI)};</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setAccess(AccessType)}; or</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setDefinition(URI)}.</li>
	 * </ul>
	 */
	protected ComponentInstance(ComponentInstance component) throws SBOLValidationException {
		super(component);
		setAccess(component.getAccess());
		setDefinition(component.getDefinitionURI());
	}

	/**
	 * Returns the access property.
	 *
	 * @return the access property
	 */
	public AccessType getAccess() {
		return access;
	}

	/**
	 * Sets the access property to the given one.
	 *
	 * @param access the given access type to be set
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10607
	 */
	public void setAccess(AccessType access) throws SBOLValidationException {
		if (access==null) {
			throw new SBOLValidationException("sbol-10607", this);
		}
		this.access = access;
	}

	/**
	 * Returns the reference component definition URI.
	 *
	 * @return the reference component definition URI
	 */
	public URI getDefinitionURI() {
		return definition;
	}

	/**
	 * Returns the component definition referenced by this component or functional component. 
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null} or no matching
	 * component definition referenced by this component or functional component exits; 
	 * or the matching component definition otherwise.
	 */
	public ComponentDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getComponentDefinition(definition);
	}

	/**
	 * Sets the definition property to the given one.
	 *
	 * @param definition the given definition URI to be set
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponentInstance other = (ComponentInstance) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (access != other.access)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ComponentInstance [access=" + access + ", definition=" + definition + ", identity="
				+ identity + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
	}

}
