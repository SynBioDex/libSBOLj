package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the SBOL Participation data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public class Participation extends Identified {

	private Set<URI> roles;
	private URI participant;
	private ModuleDefinition moduleDefinition = null;

	Participation(URI identity, URI participant, Set<URI> roles) throws SBOLValidationException {
		super(identity);
		this.roles = new HashSet<>();
		setParticipant(participant);
		setRoles(roles);
	}

	private Participation(Participation participation) throws SBOLValidationException {
		super(participation);
		this.roles = new HashSet<>();
		for (URI role : participation.getRoles()) {
			this.addRole(URI.create(role.toString()));
		}
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
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param participant the participant property of this object
	 * @throws SBOLValidationException if the given {@code participant} argument is {@code null}
	 * @throws SBOLValidationException if the associated ModuleDefinition instance is not {@code null} and
	 * given {@code participant} URI is not found in its list of FunctionalComponent instances.
	 */
	public void setParticipant(URI participant) throws SBOLValidationException {
		if (participant == null) {
			throw new SBOLValidationException("sbol-12002",this);
		}
		if (moduleDefinition != null && moduleDefinition.getFunctionalComponent(participant)==null) {
			throw new SBOLValidationException("sbol-12003",this);
		}
		this.participant = participant;
	}

	/**
	 * Adds the given role URI to this Participation's set of role URIs.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI the given role URI to this Participation's set of role URIs
	 * @return {@code true} if this set did not already contain the specified role.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}

	/**
	 * Removes the given role reference from the set of role references.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI the given role reference from the set of role references.
	 * @return {@code true} if the matching role reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if removing the last role
	 */
	public boolean removeRole(URI roleURI) throws SBOLValidationException {
		if (roles.size()==1 && roles.contains(roleURI)) {
			throw new SBOLValidationException("sbol-12004", this);
		}
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of role references first, then adds the given
	 * set of the role references to this Participation object.
	 * <p>
	 * If this Participation object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roles the given role reference from the set of role references to be added
	 * @throws SBOLValidationException if there are no roles
	 */
	public void setRoles(Set<URI> roles) throws SBOLValidationException {
		if (roles==null || roles.size()==0) {
			throw new SBOLValidationException("sbol-12004", this);
		}
		clearRoles();
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
	 * @param roleURI the given role reference from the set of role references.
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
	 * then the SBOLDocument instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 */
	void clearRoles() {
		roles.clear();
	}

	@Override
	protected Participation deepCopy() throws SBOLValidationException {
		return new Participation(this);
	}

	/**
	 * Assume this Participation object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link Interaction#updateCompliantURI(String, String, String)}.
	 * @throws SBOLValidationException
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
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
		return "Participation ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 				
				+ ", roles=" + roles 
				+ ", participant=" + participant 
				+ "]";
	}

}
