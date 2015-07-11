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
	ComponentInstance(URI identity, AccessType access, URI definition) {
		super(identity);
		setAccess(access);
		setDefinition(definition);		
	}
	
	protected ComponentInstance(ComponentInstance component) {
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
	 * @throws IllegalArgumentException if the given {@code access} argument is {@code null}
	 */
	public void setAccess(AccessType access) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (access==null) {
			throw new IllegalArgumentException("Not a valid access type.");
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
	 * @throws IllegalArgumentException if the given {@code definition} argument is {@code null}
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely specifies 
	 * 		all URIs and the given definition URI is not found in them.
	 *             
	 */
	public void setDefinition(URI definition) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (definition==null) {
			throw new IllegalArgumentException("Component "+this.getIdentity()+" must have a definition.");
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(definition)==null) {
				throw new IllegalArgumentException("Component definition '" + definition + "' does not exist.");
			}
		}
		this.definition = definition;
	}

	protected abstract ComponentInstance deepCopy();

}
