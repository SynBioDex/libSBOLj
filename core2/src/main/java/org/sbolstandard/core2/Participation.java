package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class Participation extends Identified {
	
	private Set<URI> roles;
	private URI participant;
	private ModuleDefinition moduleDefinition = null;
	
	Participation(URI identity, URI participant) {
		super(identity);
		roles = new HashSet<>();
		setParticipant(participant);
	}
	
	private Participation(Participation participation) {
		super(participation);
		roles = new HashSet<>();
		for (URI role : participation.getRoles()) {
			roles.add(role);
		}		
		this.setRoles(roles);
		this.setParticipant(participation.getParticipantURI());
	}

	/**
	 * Returns the FunctionalComponent URI that this Particiaption object refers to.
	 * 
	 * @return the FunctionalComponent URI that this Particiaption object refers to
	 */
	public URI getParticipantURI() {
		return participant;
	}
	
	/**
	 * Returns the FunctionalComponent instance that this Participation object refers to.
	 * 
	 * @return the FunctionalComponent instance that this Participation object refers to
	 * if the associated SBOLDocument instance is not {@code null}, {@code null} otherwise 
	 */
	public FunctionalComponent getParticipant() {
		if (moduleDefinition==null) return null;
		return moduleDefinition.getFunctionalComponent(participant);
	}

	/**
	 * Get the component definition for the participant of this participation.
	 * @return the component definition for the participant of this participation.
	 */
	public ComponentDefinition getParticipantDefinition() {
		if (moduleDefinition!=null) {
			return moduleDefinition.getFunctionalComponent(participant).getDefinition();
		}
		return null;
	}
	
	/**
	 * Sets the participant property of this object to the given one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param participant
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the given {@code participant} argument is {@code null}
	 * @throws IllegalArgumentException if the associated ModuleDefinition instance is not {@code null} and
	 * given {@code participant} URI is not found in its list of FunctionalComponent instances.
	 */
	public void setParticipant(URI participant) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (participant == null) {
			throw new IllegalArgumentException("Participation is required to have a participant.");
		}
		if (moduleDefinition != null && moduleDefinition.getFunctionalComponent(participant)==null) {
			throw new IllegalArgumentException("Functional component '" + participant + "' does not exist.");
		}
		this.participant = participant;
	}
	
	/**
	 * Adds the given role URI to this Participation's set of role URIs.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI
	 * @return {@code true} if this set did not already contain the specified role.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addRole(URI roleURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the given role reference from the set of role references.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param roleURI
	 * @return {@code true} if the matching role reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeRole(URI roleURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.remove(roleURI);
	}
	
	/**
	 * Clears the existing set of role references first, then adds the given
	 * set of the role references to this Participation object.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roles
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setRoles(Set<URI> roles) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the set of role URIs owned by this Participation object.
	 * 
	 * @return the set of role URIs owned by this Participation object.
	 */
	public Set<URI> getRoles() {
		return roles;
	}
	
	/**
	 * Checks if the given role URI is included in this Participation
	 * object's set of reference role URIs.
	 * 
	 * @param roleURI
	 * @return {@code true} if this set contains the specified URI.
	 */
	public boolean containsRole(URI roleURI) {
		return roles.contains(roleURI);
	}

	/**
	 * Removes all entries of this Participation object's set of role URIs.
	 * The set will be empty after this call returns.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearRoles() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		roles.clear();
	}

	@Override
	protected Participation deepCopy() {
		return new Participation(this);
	}

	/**
	 * Assume this Participation object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link Interaction#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
	}

	/**
	 * @return the moduleDefinition
	 */
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	/**
	 * @param moduleDefinition the moduleDefinition to set
	 */
	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}

	@Override
	public String toString() {
		return "Participation [roles=" + roles + ", participant=" + participant
				+ ", moduleDefinition=" + moduleDefinition + "]";
	}

}
