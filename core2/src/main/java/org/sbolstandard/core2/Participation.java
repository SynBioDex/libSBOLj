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
		Set<URI> roles = new HashSet<>();
		for (URI role : participation.getRoles()) {
			roles.add(role);
		}		
		this.setRoles(roles);
		this.setParticipant(participation.getParticipantURI());
	}

	/**
	 * Returns field variable <code>participant</code>.
	 * @return field variable <code>participant</code>
	 */
	public URI getParticipantURI() {
		return participant;
	}

	public FunctionalComponent getParticipant() {
		if (moduleDefinition==null) return null;
		return moduleDefinition.getFunctionalComponent(participant);
	}
	
	/**
	 * Sets field variable <code>participant</code> to the specified element.
	 */
	public void setParticipant(URI participant) {
		if (participant == null) {
			throw new IllegalArgumentException("Participation is required to have a participant.");
		}
		if (moduleDefinition != null && moduleDefinition.getFunctionalComponent(participant)==null) {
			throw new IllegalArgumentException("Functional component '" + participant + "' does not exist.");
		}
		this.participant = participant;
	}
	
		/**
	 * Adds the specified element to the set <code>roles</code> if it is not already present. 
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 */
	public void setRoles(Set<URI> roles) {
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}
	
	/**
	 * Returns the field variable <code>roles</code>.
	 */
	public Set<URI> getRoles() {
		return roles;
	}
	
	/**
	 * Returns true if the set <code>roles</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsRole(URI rolesURI) {
		return roles.contains(rolesURI);
	}
	
	/**
	 * Removes all entries of the list of <code>roles</code> instances owned by this instance.
	 * The list will be empty after this call returns.
	 */
	public void clearRoles() {
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
	void updateCompliantURI(String URIprefix, String grandparentDisplayId,
			String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 2 indicates that this object is a grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + grandparentDisplayId + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
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

}
